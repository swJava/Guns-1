package com.stylefeng.guns.modular.contentMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.contentMGR.warpper.ColumnWrapper;
import com.stylefeng.guns.modular.system.model.Column;
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
 * 栏目控制器
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
     * 跳转到栏目首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "column.html";
    }

    /**
     * 跳转到添加栏目
     */
    @RequestMapping("/column_add")
    public String columnAdd() {
        return PREFIX + "column_add.html";
    }

    /**
     * 跳转到修改栏目
     */
    @RequestMapping("/column_update/{columnId}")
    public String columnUpdate(@PathVariable Integer columnId, Model model) {
        Column column = columnService.selectById(columnId);
        model.addAttribute("item",column);
        LogObjectHolder.me().set(column);
        return PREFIX + "column_edit.html";
    }

    /**
     * 获取栏目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<Column> page = new PageFactory<Column>().defaultPage();
        Page<Map<String, Object>> pageMap = columnService.selectMapsPage(page, new EntityWrapper<Column>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        //包装数据
        new ColumnWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增栏目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Column column) {
        columnService.insert(column);
        return SUCCESS_TIP;
    }

    /**
     * 删除栏目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer columnId) {
        columnService.deleteById(columnId);
        return SUCCESS_TIP;
    }

    /**
     * 修改栏目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Column column) {
        columnService.updateById(column);
        return SUCCESS_TIP;
    }

    /**
     * 栏目详情
     */
    @RequestMapping(value = "/detail/{columnId}")
    @ResponseBody
    public Object detail(@PathVariable("columnId") Integer columnId) {
        return columnService.selectById(columnId);
    }
}
