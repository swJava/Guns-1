package com.stylefeng.guns.modular.contentMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.service.IColumnActionService;
import com.stylefeng.guns.modular.contentMGR.warpper.ColumnActionWrapper;
import com.stylefeng.guns.modular.contentMGR.warpper.ContentWrapper;
import com.stylefeng.guns.modular.system.model.ColumnAction;
import com.stylefeng.guns.modular.system.model.Content;
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
 * 关系栏目内容控制器
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
     * 跳转到关系栏目内容首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "columnAction.html";
    }

    /**
     * 跳转到添加关系栏目内容
     */
    @RequestMapping("/columnAction_add")
    public String columnActionAdd() {
        return PREFIX + "columnAction_add.html";
    }

    /**
     * 跳转到修改关系栏目内容
     */
    @RequestMapping("/columnAction_update/{columnActionId}")
    public String columnActionUpdate(@PathVariable Integer columnActionId, Model model) {
        ColumnAction columnAction = columnActionService.selectById(columnActionId);
        model.addAttribute("item",columnAction);
        LogObjectHolder.me().set(columnAction);
        return PREFIX + "columnAction_edit.html";
    }

    /**
     * 获取关系栏目内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<ColumnAction> page = new PageFactory<ColumnAction>().defaultPage();
        Page<Map<String, Object>> pageMap = columnActionService.selectMapsPage(page, new EntityWrapper<ColumnAction>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        //包装数据
        new ColumnActionWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增关系栏目内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ColumnAction columnAction) {
        columnActionService.insert(columnAction);
        return SUCCESS_TIP;
    }

    /**
     * 删除关系栏目内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer columnActionId) {
        columnActionService.deleteById(columnActionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改关系栏目内容
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ColumnAction columnAction) {
        columnActionService.updateById(columnAction);
        return SUCCESS_TIP;
    }

    /**
     * 关系栏目内容详情
     */
    @RequestMapping(value = "/detail/{columnActionId}")
    @ResponseBody
    public Object detail(@PathVariable("columnActionId") Integer columnActionId) {
        return columnActionService.selectById(columnActionId);
    }
}
