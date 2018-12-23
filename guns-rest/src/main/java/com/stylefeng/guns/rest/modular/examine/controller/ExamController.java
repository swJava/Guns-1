package com.stylefeng.guns.rest.modular.examine.controller;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.examineMGR.service.IExamineService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionItemService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionService;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.examine.requester.BeginExamineRequester;
import com.stylefeng.guns.rest.modular.examine.requester.ExamPaperSubmitRequester;
import com.stylefeng.guns.rest.modular.examine.requester.PaperQueryRequester;
import com.stylefeng.guns.rest.modular.examine.responser.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        Collection<Question> questionList = examineService.doBeginExamine(student, paper);

        return ExamineOutlineResponse.me(questionList);
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

    @ApiOperation(value="提交试卷", httpMethod = "POST")
    @RequestMapping("/paper/submit")
    public Responser submitPaper(@RequestBody ExamPaperSubmitRequester requester){
        return null;
    }
}
