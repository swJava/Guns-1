package com.stylefeng.guns.modular.classMGR.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.classMGR.service.ICourseOutlineService;
import com.stylefeng.guns.modular.system.model.CourseOutline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 课程大纲管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-17 01:16:31
 */
@Controller
@RequestMapping("/courseOutline")
public class CourseOutlineController extends BaseController {

    private String PREFIX = "/classMGR/class/";

    @Autowired
    private ICourseOutlineService courseOutlineService;


    /**
     * 跳转到添加课程大纲管理
     */
    @RequestMapping("/class_add_kcdg")
    public String courseOutlineAdd() {
        return PREFIX + "class_add_kcdg.html";
    }

    /**
     * 跳转到修改课程大纲管理
     */
    @RequestMapping("/courseOutline_update/{courseOutlineId}")
    public String courseOutlineUpdate(@PathVariable Integer courseOutlineId, Model model) {
        CourseOutline courseOutline = courseOutlineService.selectById(courseOutlineId);
        model.addAttribute("item",courseOutline);
        LogObjectHolder.me().set(courseOutline);
        return PREFIX + "courseOutline_edit.html";
    }

    /**
     * 获取课程大纲管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return courseOutlineService.selectList(null);
    }

    /**
     * 新增课程大纲管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CourseOutline courseOutline) {
        courseOutlineService.insert(courseOutline);
        return SUCCESS_TIP;
    }

    /**
     * 删除课程大纲管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer courseOutlineId) {
        courseOutlineService.deleteById(courseOutlineId);
        return SUCCESS_TIP;
    }

    /**
     * 修改课程大纲管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CourseOutline courseOutline) {
        courseOutlineService.updateById(courseOutline);
        return SUCCESS_TIP;
    }

    /**
     * 课程大纲管理详情
     */
    @RequestMapping(value = "/detail/{courseOutlineId}")
    @ResponseBody
    public Object detail(@PathVariable("courseOutlineId") Integer courseOutlineId) {
        return courseOutlineService.selectById(courseOutlineId);
    }
}
