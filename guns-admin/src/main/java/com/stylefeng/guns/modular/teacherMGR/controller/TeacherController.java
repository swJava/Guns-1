package com.stylefeng.guns.modular.teacherMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.teacherMGR.service.TeacherService;
import com.stylefeng.guns.modular.teacherMGR.warpper.TeacherWrapper;
import com.stylefeng.guns.util.CodeKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private TeacherService teacherService;

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
        model.addAttribute("item", teacher);
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
        Page<Map<String, Object>> mapPage = teacherService.selectMapsPage(page, new EntityWrapper<Teacher>() {
            {
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        new TeacherWrapper(mapPage.getRecords()).warp();
        return super.packForBT(page);
    }
    /**
     * 获取教师管理列表
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public Object listAll(String condition) {
        return teacherService.selectList(null);
    }

    /**
     * 新增教师管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Teacher teacher) {
        teacher.setCode(CodeKit.generateTeacher());
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
