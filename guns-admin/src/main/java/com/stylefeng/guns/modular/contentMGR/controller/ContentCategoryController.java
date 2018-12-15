package com.stylefeng.guns.modular.contentMGR.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.service.IContentCategoryService;
import com.stylefeng.guns.modular.system.model.ContentCategory;
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
 * @Date 2018-12-15 15:18:29
 */
@Controller
@RequestMapping("/contentCategory")
public class ContentCategoryController extends BaseController {

    private String PREFIX = "/column/contentCategory/";

    @Autowired
    private IContentCategoryService contentCategoryService;

    /**
     * 跳转到columnMGR首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "contentCategory.html";
    }

    /**
     * 跳转到添加columnMGR
     */
    @RequestMapping("/contentCategory_add")
    public String contentCategoryAdd() {
        return PREFIX + "contentCategory_add.html";
    }

    /**
     * 跳转到修改columnMGR
     */
    @RequestMapping("/contentCategory_update/{contentCategoryId}")
    public String contentCategoryUpdate(@PathVariable Integer contentCategoryId, Model model) {
        ContentCategory contentCategory = contentCategoryService.selectById(contentCategoryId);
        model.addAttribute("item",contentCategory);
        LogObjectHolder.me().set(contentCategory);
        return PREFIX + "contentCategory_edit.html";
    }

    /**
     * 获取columnMGR列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return contentCategoryService.selectList(null);
    }

    /**
     * 新增columnMGR
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ContentCategory contentCategory) {
        contentCategoryService.insert(contentCategory);
        return SUCCESS_TIP;
    }

    /**
     * 删除columnMGR
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer contentCategoryId) {
        contentCategoryService.deleteById(contentCategoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改columnMGR
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ContentCategory contentCategory) {
        contentCategoryService.updateById(contentCategory);
        return SUCCESS_TIP;
    }

    /**
     * columnMGR详情
     */
    @RequestMapping(value = "/detail/{contentCategoryId}")
    @ResponseBody
    public Object detail(@PathVariable("contentCategoryId") Integer contentCategoryId) {
        return contentCategoryService.selectById(contentCategoryId);
    }
}
