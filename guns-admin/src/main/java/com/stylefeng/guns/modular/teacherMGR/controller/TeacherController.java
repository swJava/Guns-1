package com.stylefeng.guns.modular.teacherMGR.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.modular.teacherMGR.warpper.TeacherWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.teacherMGR.service.ITeacherService;

import java.util.List;
import java.util.Map;

/**
 * 教师管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-04 15:55:06
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    private String PREFIX = "/teacherMGR/teacher/";

    @Autowired
    private ITeacherService teacherService;

    /**
     * 跳转到教师管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teacher.html";
    }

    /**
     * 跳转到添加教师管理
     */
    @RequestMapping("/teacher_add")
    public String teacherAdd() {
        return PREFIX + "teacher_add.html";
    }

    /**
     * 跳转到修改教师管理
     */
    @RequestMapping("/teacher_update/{teacherId}")
    public String teacherUpdate(@PathVariable Integer teacherId, Model model) {
        Teacher teacher = teacherService.selectById(teacherId);
        model.addAttribute("item",teacher);
        LogObjectHolder.me().set(teacher);
        return PREFIX + "teacher_edit.html";
    }

    /**
     * 获取教师管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<Teacher> page = new PageFactory<Teacher>().defaultPage();
        List<Map<String, Object>> list = teacherService.selectTeachers(page, condition);
        page.setRecords((List<Teacher>) new TeacherWrapper(list).warp());
        return super.packForBT(page);
//        return teacherService.selectList(null);

    }

    /**
     * 新增教师管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Teacher teacher) {
        teacherService.insert(teacher);
        return SUCCESS_TIP;
    }

    /**
     * 删除教师管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer teacherId) {
        teacherService.deleteById(teacherId);
        return SUCCESS_TIP;
    }

    /**
     * 修改教师管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Teacher teacher) {
        teacherService.updateById(teacher);
        return SUCCESS_TIP;
    }

    /**
     * 教师管理详情
     */
    @RequestMapping(value = "/detail/{teacherId}")
    @ResponseBody
    public Object detail(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.selectById(teacherId);
    }
}
