package com.stylefeng.guns.modular.examineMGR.answer.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.examineMGR.answer.warpper.AnswerPaperWrapper;
import com.stylefeng.guns.modular.examineMGR.paper.warpper.PaperWrapper;
import com.stylefeng.guns.modular.examineMGR.service.IExamineAnswerDetailService;
import com.stylefeng.guns.modular.examineMGR.service.IExamineAnswerService;
import com.stylefeng.guns.modular.examineMGR.transfer.AnswerDetailDto;
import com.stylefeng.guns.modular.questionMGR.warpper.QuestionWrapper;
import com.stylefeng.guns.modular.system.model.ExamineAnswer;
import com.stylefeng.guns.modular.system.model.ExamineAnswerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/24 13:26
 * @Version 1.0
 */
@Controller
@RequestMapping("/examine/answer")
public class AnswerController extends BaseController {

    private String PREFIX = "/examineMGR/answer/";

    @Autowired
    private IExamineAnswerService examineAnswerService;

    @Autowired
    private IExamineAnswerDetailService examineAnswerDetailService;
    /**
     * 跳转到试卷首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "answer.html";
    }

    /**
     * 跳转到修改入学诊断
     */
    @RequestMapping("/view/{code}")
    public String questionUpdate(@PathVariable String code, Model model) {
        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{""});

        ExamineAnswer examineAnswer = examineAnswerService.get(code);

        if (null == examineAnswer)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"试卷"});

        model.addAttribute("item", examineAnswer);

        Wrapper<ExamineAnswerDetail> answerDetailQuery = new EntityWrapper<ExamineAnswerDetail>();
        answerDetailQuery.eq("answer_code", code);
        answerDetailQuery.eq("status", GenericState.Valid.code);

        List<AnswerDetailDto> answerDetailList = examineAnswerDetailService.selectDetailList(answerDetailQuery);
        model.addAttribute("answerDetails", answerDetailList);

        LogObjectHolder.me().set(examineAnswer);
        return PREFIX + "paper_detail.html";
    }

    /**
     * 获取答卷列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, Object> conditionMap) {
        //分页查詢
        Page<Map<String, Object>> pageMap = examineAnswerService.selectMapsPage(conditionMap);
        //包装数据
        new AnswerPaperWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }


    /**
     * 导出答卷汇总信息
     */
    @RequestMapping(value = "/export/gather")
    @ResponseBody
    public Object exportGather(@RequestParam Map<String, Object> conditionMap) {
        //分页查詢
        Page<Map<String, Object>> pageMap = examineAnswerService.selectMapsPage(conditionMap);
        //包装数据
        new QuestionWrapper(pageMap.getRecords()).warp();
        //包装数据
        return new PaperWrapper(pageMap.getRecords()).warp();
    }


    /**
     * 导出答卷明细信息
     */
    @RequestMapping(value = "/export/detail")
    @ResponseBody
    public Object exportDetail(@RequestParam Map<String, Object> conditionMap) {
        //分页查詢
        Page<Map<String, Object>> pageMap = examineAnswerService.selectMapsPage(conditionMap);
        //包装数据
        new QuestionWrapper(pageMap.getRecords()).warp();
        //包装数据
        return new PaperWrapper(pageMap.getRecords()).warp();
    }
}
