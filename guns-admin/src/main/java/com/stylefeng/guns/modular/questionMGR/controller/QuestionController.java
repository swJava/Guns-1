package com.stylefeng.guns.modular.questionMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.constant.state.YesOrNoState;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.admin.Administrator;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionItemService;
import com.stylefeng.guns.modular.examineMGR.service.IQuestionService;
import com.stylefeng.guns.modular.questionMGR.warpper.QuestionWrapper;
import com.stylefeng.guns.modular.system.model.Question;
import com.stylefeng.guns.modular.system.model.QuestionItem;
import com.stylefeng.guns.util.CodeKit;
import com.stylefeng.guns.util.ToolUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.stylefeng.guns.common.constant.factory.MutiStrFactory.*;

/**
 * 入学诊断控制器
 *
 * @author fengshuonan
 * @Date 2018-11-04 11:32:55
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController {

    private String PREFIX = "/questionMGR/question/";

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IQuestionItemService questionItemService;

    /**
     * 跳转到入学诊断首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "question.html";
    }

    /**
     * 跳转到添加入学诊断
     */
    @RequestMapping("/question_add")
    public String questionAdd() {
        return PREFIX + "question_add.html";
    }

    /**
     * 跳转到修改入学诊断
     */
    @RequestMapping("/question_update/{code}")
    public String questionUpdate(@PathVariable String code, Model model) {
        Question question = questionService.get(code);

        if (null == question)
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);

        List<QuestionItem> questionItemList = questionItemService.selectList(new EntityWrapper<QuestionItem>().eq("question_code", question.getCode()).eq("status", GenericState.Valid.code));
        model.addAttribute("item", question);
        model.addAttribute("answerItemList", questionItemList);

        LogObjectHolder.me().set(question);
        return PREFIX + "question_edit.html";
    }

    /**
     * 获取入学诊断列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<Question> page = new PageFactory<Question>().defaultPage();
        Page<Map<String, Object>> pageMap = questionService.selectMapsPage(page, new EntityWrapper<Question>(){{
            if(StringUtils.isNotEmpty(condition)){
                like("code",condition);
            }

            eq("status", GenericState.Valid.code);
        }});
        //包装数据
        new QuestionWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    @RequestMapping(value = "/listObject")
    @ResponseBody
    public Object listObject(){
        return  questionService.selectList(null);
    }

    /**
     * 新增入学诊断
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Question question, String answerItems) {
        if (ToolUtil.isOneEmpty(question, answerItems)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        //解析dictValues
        List<Map<String, String>> items = parseKeyValue(answerItems);

        List<QuestionItem> questionItemList = new ArrayList<QuestionItem>();
        StringBuilder expectedAnswer = new StringBuilder();

        for(Map<String, String> item : items) {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setText(item.get(MUTI_STR_NAME));
            questionItem.setValue(item.get(MUTI_STR_CODE));
            questionItem.setExpect(YesOrNoState.No.code);

            boolean isAnswer = Boolean.valueOf(item.get(MUTI_STR_NUM));
            if (isAnswer) {
                questionItem.setExpect(YesOrNoState.Yes.code);
                expectedAnswer.append(questionItem.getValue()).append(",");
            }
            questionItemList.add(questionItem);
        }

        if (questionItemList.isEmpty())
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);

        Administrator currAdmin = ShiroKit.getUser();
        question.setExpactAnswer(expectedAnswer.substring(0, expectedAnswer.length() - 1));
        question.setTeacher(currAdmin.getAccount());
        question.setTeacherName(currAdmin.getName());
        questionService.create(question, questionItemList);

        return SUCCESS_TIP;
    }

    /**
     * 删除入学诊断
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String questionCode) {
        questionService.delete(questionCode);
        return SUCCESS_TIP;
    }

    /**
     * 修改入学诊断
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Question question, String answerItems) {
        if (ToolUtil.isOneEmpty(question, answerItems)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        //解析dictValues
        List<Map<String, String>> items = parseKeyValue(answerItems);

        List<QuestionItem> questionItemList = new ArrayList<QuestionItem>();
        StringBuilder expectedAnswer = new StringBuilder();

        for(Map<String, String> item : items) {
            QuestionItem questionItem = new QuestionItem();
            try {
                questionItem.setText(URLDecoder.decode(new String(Base64.decodeBase64(item.get(MUTI_STR_NAME)), "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            questionItem.setValue(item.get(MUTI_STR_CODE));
            questionItem.setExpect(YesOrNoState.No.code);

            boolean isAnswer = Boolean.valueOf(item.get(MUTI_STR_NUM));
            if (isAnswer) {
                questionItem.setExpect(YesOrNoState.Yes.code);
                expectedAnswer.append(questionItem.getValue()).append(",");
            }
            questionItemList.add(questionItem);
        }

        if (questionItemList.isEmpty())
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);

        Administrator currAdmin = ShiroKit.getUser();
        question.setExpactAnswer(expectedAnswer.substring(0, expectedAnswer.length() - 1));
        question.setTeacher(currAdmin.getAccount());
        question.setTeacherName(currAdmin.getName());
        questionService.update(question, questionItemList);

        return SUCCESS_TIP;
    }

    /**
     * 入学诊断详情
     */
    @RequestMapping(value = "/detail/{questionId}")
    @ResponseBody
    public Object detail(@PathVariable("questionId") Integer questionId) {
        return questionService.selectById(questionId);
    }
}
