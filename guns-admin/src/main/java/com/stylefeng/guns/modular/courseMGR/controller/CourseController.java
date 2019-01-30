package com.stylefeng.guns.modular.courseMGR.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.classMGR.service.ICourseOutlineService;
import com.stylefeng.guns.modular.classMGR.service.ICourseService;
import com.stylefeng.guns.modular.courseMGR.warpper.CourseWrapper;
import com.stylefeng.guns.modular.system.model.Course;
import com.stylefeng.guns.modular.system.model.CourseOutline;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/21 15:18
 * @Version 1.0
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private String PREFIX = "/courseMGR/course/";

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseOutlineService courseOutlineService;
    /**
     * 跳转到课程管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "course.html";
    }

    /**
     * 跳转到添加课程管理
     */
    @RequestMapping("/add")
    public String courseAdd() {
        return PREFIX + "course_add.html";
    }

    /**
     * 跳转到修改课程管理
     */
    @RequestMapping("/update/{courseCode}")
    public String courseUpdate(@PathVariable String courseCode, Model model) {
        Course courseInstance = courseService.get(courseCode);

        model.addAttribute("item",courseInstance);
        LogObjectHolder.me().set(courseInstance);

        return PREFIX + "course_edit.html";
    }

    /**
     * 删除课程管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String code) {
        courseService.delete(code);
        return SUCCESS_TIP;
    }


    /**
     * 跳转到课程大纲设置
     */
    @RequestMapping("/outline/setting/{courseCode}")
    public String courseOutline(@PathVariable String courseCode, Model model) {
        Course courseInstance = courseService.get(courseCode);

        model.addAttribute("item",courseInstance);

        List<CourseOutline> courseOutlineList = courseOutlineService.selectList(new EntityWrapper<CourseOutline>().eq("course_code", courseCode));

        model.addAttribute("outlines", courseOutlineList);
        LogObjectHolder.me().set(courseInstance);

        return PREFIX + "course_outline.html";
    }

    /**
     * 获取课程管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<Course> page = new PageFactory<Course>().defaultPage();
        Page<Map<String, Object>> pageMap = courseService.selectMapsPage(page, new EntityWrapper<Course>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }

                eq("status", GenericState.Valid.code);
            }
        });
        //包装数据
        new CourseWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Course course){

        Long id = course.getId();
        if (null == id) {
            courseService.create(course);
        }else{
            Course existCourse = courseService.get(course.getCode());
            if (id.equals(existCourse.getId())){
                courseService.update(course);
            }else{
                course.setId(null);
                courseService.create(course);
            }
        }

        return SUCCESS_TIP;
    }

    @RequestMapping(value = "/outline/save", method = RequestMethod.POST)
    @ResponseBody
    public Object setCourseOutline(@RequestParam String course, @RequestBody List<CourseOutline> outlines){

        if (null == course)
            return new ErrorTip(500, "请指定课程");

        Course existCourse = courseService.get(course);

        if (null == course)
            return new ErrorTip(500, "课程不存在");

        courseOutlineService.addCourseOutline(existCourse, outlines);

        return SUCCESS_TIP;
    }
}
