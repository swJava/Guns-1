package com.stylefeng.guns.modular.contentMGR.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.system.model.Column;
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
 * @Date 2018-12-15 15:17:50
 */
@Controller
@RequestMapping("/column")
public class ColumnController extends BaseController {

    private String PREFIX = "/column/column/";

    @Autowired
    private IColumnService columnService;

    /**
     * 跳转到columnMGR首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "column.html";
    }

    /**
     * 跳转到添加columnMGR
     */
    @RequestMapping("/column_add")
    public String columnAdd() {
        return PREFIX + "column_add.html";
    }

    /**
     * 跳转到修改columnMGR
     */
    @RequestMapping("/column_update/{columnId}")
    public String columnUpdate(@PathVariable Integer columnId, Model model) {
        Column column = columnService.selectById(columnId);
        model.addAttribute("item",column);
        LogObjectHolder.me().set(column);
        return PREFIX + "column_edit.html";
    }

    /**
     * 获取columnMGR列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return columnService.selectList(null);
    }

    /**
     * 新增columnMGR
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Column column) {
        columnService.insert(column);
        return SUCCESS_TIP;
    }

    /**
     * 删除columnMGR
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer columnId) {
        columnService.deleteById(columnId);
        return SUCCESS_TIP;
    }

    /**
     * 修改columnMGR
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Column column) {
        columnService.updateById(column);
        return SUCCESS_TIP;
    }

    /**
     * columnMGR详情
     */
    @RequestMapping(value = "/detail/{columnId}")
    @ResponseBody
    public Object detail(@PathVariable("columnId") Integer columnId) {
        return columnService.selectById(columnId);
    }
}
