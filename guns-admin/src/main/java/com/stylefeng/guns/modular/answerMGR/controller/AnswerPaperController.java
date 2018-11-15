package com.stylefeng.guns.modular.answerMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.answerMGR.warpper.AnswerPaperWrapper;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.AnswerPaper;
import com.stylefeng.guns.modular.answerMGR.service.IAnswerPaperService;

import java.util.Map;

/**
 * 考试管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-15 22:48:29
 */
@Controller
@RequestMapping("/answerPaper")
public class AnswerPaperController extends BaseController {

    private String PREFIX = "/answerMGR/answerPaper/";

    @Autowired
    private IAnswerPaperService answerPaperService;
    @Autowired
    private IStudentService iStudentService;

    /**
     * 跳转到考试管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "answerPaper.html";
    }

    /**
     * 跳转到添加考试管理
     */
    @RequestMapping("/answerPaper_add")
    public String answerPaperAdd() {
        return PREFIX + "answerPaper_add.html";
    }

    /**
     * 跳转到修改考试管理
     */
    @RequestMapping("/answerPaper_update/{answerPaperId}")
    public String answerPaperUpdate(@PathVariable Integer answerPaperId, Model model) {
        AnswerPaper answerPaper = answerPaperService.selectById(answerPaperId);
        model.addAttribute("item",answerPaper);
        LogObjectHolder.me().set(answerPaper);
        return PREFIX + "answerPaper_edit.html";
    }

    /**
     * 获取考试管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Student student = iStudentService.getOne(new Student(){{setName(condition);}});
        String code = student == null?null:student.getCode();

        //分页查詢
        Page<AnswerPaper> page = new PageFactory<AnswerPaper>().defaultPage();
        Page<Map<String, Object>> pageMap = answerPaperService.selectMapsPage(page, new EntityWrapper<AnswerPaper>() {
            {
                if (StringUtils.isNotEmpty(code)) {
                    eq("student_code", code);
                }
            }
        });
        //包装数据
        new AnswerPaperWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增考试管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AnswerPaper answerPaper) {
        answerPaperService.insert(answerPaper);
        return SUCCESS_TIP;
    }

    /**
     * 删除考试管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer answerPaperId) {
        answerPaperService.deleteById(answerPaperId);
        return SUCCESS_TIP;
    }

    /**
     * 修改考试管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AnswerPaper answerPaper) {
        answerPaperService.updateById(answerPaper);
        return SUCCESS_TIP;
    }

    /**
     * 考试管理详情
     */
    @RequestMapping(value = "/detail/{answerPaperId}")
    @ResponseBody
    public Object detail(@PathVariable("answerPaperId") Integer answerPaperId) {
        return answerPaperService.selectById(answerPaperId);
    }
}
