package com.stylefeng.guns.modular.classExamStrategyMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.classExamStrategyMGR.service.IClassExamStrategyService;
import com.stylefeng.guns.modular.classExamStrategyMGR.warpper.ClassExamStrategyWrapper;
import com.stylefeng.guns.modular.system.model.ClassExamStrategy;
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
 * 试卷策略控制器
 *
 * @author fengshuonan
 * @Date 2018-12-06 23:33:01
 */
@Controller
@RequestMapping("/classExamStrategy")
public class ClassExamStrategyController extends BaseController {

    private String PREFIX = "/classExamStrategyMGR/classExamStrategy/";

    @Autowired
    private IClassExamStrategyService classExamStrategyService;

    /**
     * 跳转到试卷策略首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "classExamStrategy.html";
    }

    /**
     * 跳转到添加试卷策略
     */
    @RequestMapping("/classExamStrategy_add")
    public String classExamStrategyAdd() {
        return PREFIX + "classExamStrategy_add.html";
    }

    /**
     * 跳转到修改试卷策略
     */
    @RequestMapping("/classExamStrategy_update/{classExamStrategyId}")
    public String classExamStrategyUpdate(@PathVariable Integer classExamStrategyId, Model model) {
        ClassExamStrategy classExamStrategy = classExamStrategyService.selectById(classExamStrategyId);
        model.addAttribute("item",classExamStrategy);
        LogObjectHolder.me().set(classExamStrategy);
        return PREFIX + "classExamStrategy_edit.html";
    }

    /**
     * 获取试卷策略列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<ClassExamStrategy> page = new PageFactory<ClassExamStrategy>().defaultPage();
        Page<Map<String, Object>> pageMap = classExamStrategyService.selectMapsPage(page, new EntityWrapper<ClassExamStrategy>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        //包装数据
        new ClassExamStrategyWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增试卷策略
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClassExamStrategy classExamStrategy) {
        classExamStrategyService.insert(classExamStrategy);
        return SUCCESS_TIP;
    }

    /**
     * 删除试卷策略
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer classExamStrategyId) {
        classExamStrategyService.deleteById(classExamStrategyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改试卷策略
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClassExamStrategy classExamStrategy) {
        classExamStrategyService.updateById(classExamStrategy);
        return SUCCESS_TIP;
    }

    /**
     * 试卷策略详情
     */
    @RequestMapping(value = "/detail/{classExamStrategyId}")
    @ResponseBody
    public Object detail(@PathVariable("classExamStrategyId") Integer classExamStrategyId) {
        return classExamStrategyService.selectById(classExamStrategyId);
    }
}
