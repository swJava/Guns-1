package com.stylefeng.guns.modular.contentMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.service.IContentCategoryService;
import com.stylefeng.guns.modular.contentMGR.warpper.ColumnWrapper;
import com.stylefeng.guns.modular.contentMGR.warpper.ContentCategoryWrapper;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.model.ContentCategory;
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
 * @Date 2018-12-15 15:18:29
 */
@Controller
@RequestMapping("/contentCategory")
public class ContentCategoryController extends BaseController {

    private String PREFIX = "/column/contentCategory/";

    @Autowired
    private IContentCategoryService contentCategoryService;

    /**
     * 跳转到关系栏目内容首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "contentCategory.html";
    }

    /**
     * 跳转到添加关系栏目内容
     */
    @RequestMapping("/contentCategory_add")
    public String contentCategoryAdd() {
        return PREFIX + "contentCategory_add.html";
    }

    /**
     * 跳转到修改关系栏目内容
     */
    @RequestMapping("/contentCategory_update/{contentCategoryId}")
    public String contentCategoryUpdate(@PathVariable Integer contentCategoryId, Model model) {
        ContentCategory contentCategory = contentCategoryService.selectById(contentCategoryId);
        model.addAttribute("item",contentCategory);
        LogObjectHolder.me().set(contentCategory);
        return PREFIX + "contentCategory_edit.html";
    }

    /**
     * 获取关系栏目内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<ContentCategory> page = new PageFactory<ContentCategory>().defaultPage();
        Page<Map<String, Object>> pageMap = contentCategoryService.selectMapsPage(page, new EntityWrapper<ContentCategory>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        //包装数据
        new ContentCategoryWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增关系栏目内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ContentCategory contentCategory) {
        contentCategoryService.insert(contentCategory);
        return SUCCESS_TIP;
    }

    /**
     * 删除关系栏目内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer contentCategoryId) {
        contentCategoryService.deleteById(contentCategoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改关系栏目内容
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ContentCategory contentCategory) {
        contentCategoryService.updateById(contentCategory);
        return SUCCESS_TIP;
    }

    /**
     * 关系栏目内容详情
     */
    @RequestMapping(value = "/detail/{contentCategoryId}")
    @ResponseBody
    public Object detail(@PathVariable("contentCategoryId") Integer contentCategoryId) {
        return contentCategoryService.selectById(contentCategoryId);
    }
}
