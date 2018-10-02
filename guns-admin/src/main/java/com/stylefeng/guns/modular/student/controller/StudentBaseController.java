package com.stylefeng.guns.modular.student.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.modular.student.warpper.StudentWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.StudentBase;
import com.stylefeng.guns.modular.student.service.IStudentBaseService;

import java.util.List;
import java.util.Map;

/**
 * 学生管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-01 12:47:07
 */
@Controller
@RequestMapping("/studentBase")
public class StudentBaseController extends BaseController {

    private String PREFIX = "/student/studentBase/";

    @Autowired
    private IStudentBaseService studentBaseService;

    /**
     * 跳转到学生管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "studentBase.html";
    }

    /**
     * 跳转到添加学生管理
     */
    @RequestMapping("/studentBase_add")
    public String studentBaseAdd() {
        return PREFIX + "studentBase_add.html";
    }

    /**
     * 跳转到修改学生管理
     */
    @RequestMapping("/studentBase_update/{studentBaseId}")
    public String studentBaseUpdate(@PathVariable Integer studentBaseId, Model model) {
        StudentBase studentBase = studentBaseService.selectById(studentBaseId);
        model.addAttribute("item", studentBase);
        LogObjectHolder.me().set(studentBase);
        return PREFIX + "studentBase_edit.html";
    }

    /**
     * 获取学生管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<StudentBase> page = new PageFactory<StudentBase>().defaultPage();
        List<Map<String, Object>> list = this.studentBaseService.list(page, condition);
        page.setRecords((List<StudentBase>) new StudentWarpper(list).warp());
        return super.packForBT(page);
    }

    /**
     * 新增学生管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(StudentBase studentBase) {

        return this.studentBaseService.insert(studentBase);
    }

    /**
     * 删除学生管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer studentBaseId) {
        studentBaseService.deleteById(studentBaseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改学生管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(StudentBase studentBase) {
        studentBaseService.updateById(studentBase);
        return SUCCESS_TIP;
    }

    /**
     * 学生管理详情
     */
    @RequestMapping(value = "/detail/{studentBaseId}")
    @ResponseBody
    public Object detail(@PathVariable("studentBaseId") Integer studentBaseId) {
        return studentBaseService.selectById(studentBaseId);
    }
}
