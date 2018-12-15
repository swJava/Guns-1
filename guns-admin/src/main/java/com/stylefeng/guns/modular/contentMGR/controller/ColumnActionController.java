package com.stylefeng.guns.modular.contentMGR.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.service.IColumnActionService;
import com.stylefeng.guns.modular.system.model.ColumnAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * columnMGR控制器
 *
 * @author fengshuonan
 * @Date 2018-12-15 15:18:04
 */
@Controller
@RequestMapping("/columnAction")
public class ColumnActionController extends BaseController {

    private String PREFIX = "/column/columnAction/";

    @Autowired
    private IColumnActionService columnActionService;

    /**
     * 跳转到columnMGR首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "columnAction.html";
    }

    /**
     * 跳转到添加columnMGR
     */
    @RequestMapping("/columnAction_add")
    public String columnActionAdd() {
        return PREFIX + "columnAction_add.html";
    }

    /**
     * 跳转到修改columnMGR
     */
    @RequestMapping("/columnAction_update/{columnActionId}")
    public String columnActionUpdate(@PathVariable Integer columnActionId, Model model) {
        ColumnAction columnAction = columnActionService.selectById(columnActionId);
        model.addAttribute("item",columnAction);
        LogObjectHolder.me().set(columnAction);
        return PREFIX + "columnAction_edit.html";
    }

    /**
     * 获取columnMGR列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return columnActionService.selectList(null);
    }

    /**
     * 新增columnMGR
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ColumnAction columnAction) {
        columnActionService.insert(columnAction);
        return SUCCESS_TIP;
    }

    /**
     * 删除columnMGR
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer columnActionId) {
        columnActionService.deleteById(columnActionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改columnMGR
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ColumnAction columnAction) {
        columnActionService.updateById(columnAction);
        return SUCCESS_TIP;
    }

    /**
     * columnMGR详情
     */
    @RequestMapping(value = "/detail/{columnActionId}")
    @ResponseBody
    public Object detail(@PathVariable("columnActionId") Integer columnActionId) {
        return columnActionService.selectById(columnActionId);
    }
}
