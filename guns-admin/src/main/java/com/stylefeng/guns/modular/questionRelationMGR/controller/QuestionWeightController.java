package com.stylefeng.guns.modular.questionRelationMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.questionMGR.warpper.QuestionWrapper;
import com.stylefeng.guns.modular.questionRelationMGR.warpper.QuestionWeightWrapper;
import com.stylefeng.guns.modular.system.model.Question;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.QuestionWeight;
import com.stylefeng.guns.modular.questionRelationMGR.service.IQuestionWeightService;

import java.util.Map;

/**
 * 题库归档控制器
 *
 * @author fengshuonan
 * @Date 2018-12-04 22:40:07
 */
@Controller
@RequestMapping("/questionWeight")
public class QuestionWeightController extends BaseController {

    private String PREFIX = "/questionRelationMGR/questionWeight/";

    @Autowired
    private IQuestionWeightService questionWeightService;

    /**
     * 跳转到题库归档首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "questionWeight.html";
    }

    /**
     * 跳转到添加题库归档
     */
    @RequestMapping("/questionWeight_add")
    public String questionWeightAdd() {
        return PREFIX + "questionWeight_add.html";
    }

    /**
     * 跳转到修改题库归档
     */
    @RequestMapping("/questionWeight_update/{questionWeightId}")
    public String questionWeightUpdate(@PathVariable Integer questionWeightId, Model model) {
        QuestionWeight questionWeight = questionWeightService.selectById(questionWeightId);
        model.addAttribute("item",questionWeight);
        LogObjectHolder.me().set(questionWeight);
        return PREFIX + "questionWeight_edit.html";
    }

    /**
     * 获取题库归档列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<QuestionWeight> page = new PageFactory<QuestionWeight>().defaultPage();
        Page<Map<String, Object>> pageMap = questionWeightService.selectMapsPage(page, new EntityWrapper<QuestionWeight>(){{
            if(StringUtils.isNotEmpty(condition)){
                like("code",condition);
            }
        }});
        //包装数据
        new QuestionWeightWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增题库归档
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(QuestionWeight questionWeight) {
        questionWeightService.insert(questionWeight);
        return SUCCESS_TIP;
    }

    /**
     * 删除题库归档
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer questionWeightId) {
        questionWeightService.deleteById(questionWeightId);
        return SUCCESS_TIP;
    }

    /**
     * 修改题库归档
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(QuestionWeight questionWeight) {
        questionWeightService.updateById(questionWeight);
        return SUCCESS_TIP;
    }

    /**
     * 题库归档详情
     */
    @RequestMapping(value = "/detail/{questionWeightId}")
    @ResponseBody
    public Object detail(@PathVariable("questionWeightId") Integer questionWeightId) {
        return questionWeightService.selectById(questionWeightId);
    }
}
