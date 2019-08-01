package com.stylefeng.guns.modular.classAuthorityMGR.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.classAuthorityMGR.service.IClassAuthorityService;
import com.stylefeng.guns.modular.system.model.ClassAuthority;
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
 * @Date 2019-07-30 12:58:24
 */
@Controller
@RequestMapping("/classAuthority")
public class ClassAuthorityController extends BaseController {

    private String PREFIX = "/classAuthorityMGR/";

    @Autowired
    private IClassAuthorityService classAuthorityService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "classAuthority.html";
    }

    /**
     * 跳转到添加课程
     */
    @RequestMapping("/classAuthority_add")
    public String classAuthorityAdd() {
//        return PREFIX + "classAuthority_add.html";
        return PREFIX + "sign_wizard.html";
    }
    /**
     * 跳转到添加关联学生
     */
    @RequestMapping("/classAuthority_add_student")
    public String classAuthorityAddStudent() {
        return PREFIX + "classAuthority_add_student.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/classAuthority_update/{classAuthorityId}")
    public String classAuthorityUpdate(@PathVariable Integer classAuthorityId, Model model) {
        ClassAuthority classAuthority = classAuthorityService.selectById(classAuthorityId);
        model.addAttribute("item",classAuthority);
        LogObjectHolder.me().set(classAuthority);
        return PREFIX + "classAuthority_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return classAuthorityService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClassAuthority classAuthority) {
        classAuthorityService.insert(classAuthority);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer classAuthorityId) {
        classAuthorityService.deleteById(classAuthorityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClassAuthority classAuthority) {
        classAuthorityService.updateById(classAuthority);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{classAuthorityId}")
    @ResponseBody
    public Object detail(@PathVariable("classAuthorityId") Integer classAuthorityId) {
        return classAuthorityService.selectById(classAuthorityId);
    }
}
