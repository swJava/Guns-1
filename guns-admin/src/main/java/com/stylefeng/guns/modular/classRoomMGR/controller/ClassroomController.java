package com.stylefeng.guns.modular.classRoomMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.classMGR.warpper.ClassWrapper;
import com.stylefeng.guns.modular.classRoomMGR.warpper.ClassroomWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Classroom;
import com.stylefeng.guns.modular.classRoomMGR.service.IClassroomService;

import java.util.Map;

/**
 * 教室管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-05 22:48:35
 */
@Controller
@RequestMapping("/classroom")
public class ClassroomController extends BaseController {

    private String PREFIX = "/classRoomMGR/classroom/";

    @Autowired
    private IClassroomService classroomService;

    /**
     * 跳转到教室管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "classroom.html";
    }

    /**
     * 跳转到添加教室管理
     */
    @RequestMapping("/classroom_add")
    public String classroomAdd() {
        return PREFIX + "classroom_add.html";
    }

    /**
     * 跳转到修改教室管理
     */
    @RequestMapping("/classroom_update/{classroomId}")
    public String classroomUpdate(@PathVariable Integer classroomId, Model model) {
        Classroom classroom = classroomService.selectById(classroomId);
        model.addAttribute("item",classroom);
        LogObjectHolder.me().set(classroom);
        return PREFIX + "classroom_edit.html";
    }

    /**
     * 获取教室管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<Classroom> page = new PageFactory<Classroom>().defaultPage();
        Page<Map<String, Object>> pageMap = classroomService.selectMapsPage(page, new EntityWrapper<Classroom>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        //包装数据
        new ClassroomWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }
    /**
     * 获取教室管理列表
     */
    @RequestMapping(value = "/listRoom")
    @ResponseBody
    public Object listRoom(String condition) {

        return classroomService.selectList(null);
    }

    /**
     * 新增教室管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Classroom classroom) {
        classroom.setCode(RandomStringUtils.randomNumeric(8));
        classroomService.insert(classroom);
        return SUCCESS_TIP;
    }

    /**
     * 删除教室管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer classroomId) {
        classroomService.deleteById(classroomId);
        return SUCCESS_TIP;
    }

    /**
     * 修改教室管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Classroom classroom) {
        classroomService.updateById(classroom);
        return SUCCESS_TIP;
    }

    /**
     * 教室管理详情
     */
    @RequestMapping(value = "/detail/{classroomId}")
    @ResponseBody
    public Object detail(@PathVariable("classroomId") Integer classroomId) {
        return classroomService.selectById(classroomId);
    }
}
