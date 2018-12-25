package com.stylefeng.guns.modular.adjustMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.adjustMGR.warpper.AdjustStudentWrapper;
import com.stylefeng.guns.modular.studentMGR.warpper.StudentWrapper;
import com.stylefeng.guns.modular.system.model.AdjustStudentTypeEnum;
import com.stylefeng.guns.modular.system.model.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.AdjustStudent;
import com.stylefeng.guns.modular.adjustMGR.service.IAdjustStudentService;

import java.util.HashMap;
import java.util.Map;

/**
 * 调课管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 23:01:31
 */
@Controller
@RequestMapping("/adjust")
public class AdjustStudentController extends BaseController {

    private String PREFIX = "/adjustMGR/adjustStudent/";

    @Autowired
    private IAdjustStudentService adjustStudentService;

    /**
     * 跳转到调课管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "adjustStudent.html";
    }

    /**
     * 跳转到添加调课管理
     */
    @RequestMapping("/adjustStudent_add")
    public String adjustStudentAdd() {
        return PREFIX + "adjustStudent_add.html";
    }

    /**
     * 跳转到修改调课管理
     */
    @RequestMapping("/adjustStudent_update/{adjustStudentId}")
    public String adjustStudentUpdate(@PathVariable Integer adjustStudentId, Model model) {
        AdjustStudent adjustStudent = adjustStudentService.selectById(adjustStudentId);
        model.addAttribute("item",adjustStudent);
        LogObjectHolder.me().set(adjustStudent);
        return PREFIX + "adjustStudent_edit.html";
    }

    /**
     * 获取调课管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        //分页查詢

        Map<String, Object> queryMap = new HashMap<String, Object>();


        Page<Map<String, Object>> pageMap = adjustStudentService.selectApplyMapsPage(AdjustStudentTypeEnum.Adjust, queryMap);
/*
        Page<Map<String, Object>> pageMap = adjustStudentService.selectMapsPage(page, new EntityWrapper<AdjustStudent>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("user_name", condition);
                }
            }
        });*/
        //包装数据
        new AdjustStudentWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增调课管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AdjustStudent adjustStudent) {
        adjustStudentService.insert(adjustStudent);
        return SUCCESS_TIP;
    }

    /**
     * 删除调课管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer adjustStudentId) {
        adjustStudentService.deleteById(adjustStudentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改调课管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AdjustStudent adjustStudent) {
        adjustStudentService.updateById(adjustStudent);
        return SUCCESS_TIP;
    }

    /**
     * 调课管理详情
     */
    @RequestMapping(value = "/detail/{adjustStudentId}")
    @ResponseBody
    public Object detail(@PathVariable("adjustStudentId") Integer adjustStudentId) {
        return adjustStudentService.selectById(adjustStudentId);
    }
}
