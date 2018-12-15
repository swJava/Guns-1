package com.stylefeng.guns.modular.answerMGR.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.examine.service.IAnswerService;
import com.stylefeng.guns.modular.system.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-14 23:44:37
 */
@Controller
@RequestMapping("/answer")
public class AnswerController extends BaseController {

    private String PREFIX = "/answerMGR/answer/";

    @Autowired
    private IAnswerService answerService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "answer.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/answer_add")
    public String answerAdd() {
        return PREFIX + "answer_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/answer_update/{answerId}")
    public String answerUpdate(@PathVariable Integer answerId, Model model) {
        Answer answer = answerService.selectById(answerId);
        model.addAttribute("item",answer);
        LogObjectHolder.me().set(answer);
        return PREFIX + "answer_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return answerService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Answer answer) {
        answerService.insert(answer);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer answerId) {
        answerService.deleteById(answerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Answer answer) {
        answerService.updateById(answer);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{answerId}")
    @ResponseBody
    public Object detail(@PathVariable("answerId") Integer answerId) {
        return answerService.selectById(answerId);
    }
}
