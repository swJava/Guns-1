package com.stylefeng.guns.rest.modular.examine.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.examineMGR.service.IExamineService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionItemService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionService;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.examine.requester.BeginExamineRequester;
import com.stylefeng.guns.rest.modular.examine.requester.ExamPaperSubmitRequester;
import com.stylefeng.guns.rest.modular.examine.requester.PaperQueryRequester;
import com.stylefeng.guns.rest.modular.examine.requester.QuestionRequester;
import com.stylefeng.guns.rest.modular.examine.responser.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 测试
 *
 * Created by 罗华.
 */
@RestController
@RequestMapping("/examine")
@Api(tags = "考试接口")
public class ExamController extends ApiController {

    @Autowired
    private IClassService classService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IExamineService examineService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IQuestionItemService questionItemService;

    @Autowired
    private IAttachmentService attachmentService;

    @ApiOperation(value="开始测试", httpMethod = "POST", response = ExamineOutlineResponse.class)
    @RequestMapping("/begin")
    public Responser beginExamine(
            @ApiParam(required = true, value = "开始测试请求")
            @RequestBody
            @Valid
            BeginExamineRequester requester
    ){
        Member member = currMember();

        Student student = studentService.get(requester.getStudent());

        ExaminePaper paper = examineService.getExaminePaper(requester.getPaperCode());

        Map<String, Collection<Question>> beginResult = examineService.doBeginExamine(student, paper);

        return ExamineOutlineResponse.me(beginResult);
    }

    @ApiOperation(value="试卷列表", httpMethod = "POST", response = PaperListResponse.class)
    @RequestMapping(value = "/paper/list", method = RequestMethod.POST)
    public Responser listPaper(
            @RequestBody @Valid
            PaperQueryRequester requester
    ){
        Member member = currMember();
        Set<PaperResponse> paperResponseList = new HashSet<>();

        Student student = studentService.get(requester.getStudent().getCode());

        if (null == student || !(student.isValid()))
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        // 查找之前没有做完的试卷
        List<ExaminePaper> unCompletePaperList = examineService.findUnCompletePaper(student);
        // 查找所有适合当前学员的试卷
        List<ExaminePaper> examinePaperList = examineService.findExaminePaper(student);

        for(ExaminePaper examinePaper : examinePaperList){
            List<com.stylefeng.guns.modular.system.model.Class> classInfoList = classService.findClassUsingExaming(Arrays.asList(new String[]{examinePaper.getCode()}));
            paperResponseList.add(PaperResponse.me(examinePaper, classInfoList));
        }

        return PaperListResponse.me(paperResponseList);
    }

    @ApiOperation(value="试题详情", response = QuestionDetailResponse.class)
    @RequestMapping(value = "/question/detail/{code}", method = {RequestMethod.POST})
    public Responser paperDetail(@PathVariable("code") String code){

        Question question = questionService.get(code);

        List<QuestionItem> questionItemList = questionItemService.findAll(question.getCode());

        QuestionResponse questionResponse = QuestionResponse.me(question);
        questionResponse.setItems(questionItemList);

        return QuestionDetailResponse.me(questionResponse);
    }

    @ApiOperation(value="提交试卷", httpMethod = "POST", response = SimpleResponser.class)
    @RequestMapping("/paper/submit")
    public Responser submitPaper(@RequestBody ExamPaperSubmitRequester requester){

        requester.parseSubmit();

        List<ExamineAnswerDetail> detailList = new ArrayList<>();
        for(QuestionRequester qr : requester.getSubmitItems()) {
            ExamineAnswerDetail answerDetail = new ExamineAnswerDetail();
            answerDetail.setTotalScore(0);
            answerDetail.setAnswerCode(requester.getCode());
            answerDetail.setQuestionCode(qr.getCode());
            answerDetail.setStudentAnswer(qr.getAnswer());
            answerDetail.setAnswer(qr.getExpactAnswer());
            answerDetail.setStatus(GenericState.Valid.code);

            detailList.add(answerDetail);
        }

        examineService.doFinishExamine(requester.getCode(), detailList);

        return SimpleResponser.success();
    }

    @ApiOperation(value="获取答案图片", httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "答案项ID", required = true, dataType = "String", example = "301")
    )
    @RequestMapping(value = "/view/question/item/{id}", method = RequestMethod.POST)
    public void download(@PathVariable("id")String id, HttpServletResponse response) {
        File zipFile = null;

        Wrapper<Attachment> attachmentWrapper = new EntityWrapper<>();
        attachmentWrapper.eq("master_name", "QuestionItem");
        attachmentWrapper.eq("master_code", id);
        attachmentWrapper.eq("status", GenericState.Valid.code);

        Attachment attachment = attachmentService.selectOne(attachmentWrapper);

        if (null == attachment)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        InputStream resStream = null;
        String fileName = "";

        try {

            resStream = new FileInputStream(new File(attachment.getPath()));
            fileName = attachment.getFileName();

        }catch(Exception e){}

        if (null == resStream)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS);

        try {
            // 输出文件
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(URLEncoder.encode(fileName, "UTF-8")) + "\"");
            byte[] buffer = new byte[1024];
            int byteread = 0;
            while ((byteread = resStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, byteread);
            }

        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            try {
                resStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
