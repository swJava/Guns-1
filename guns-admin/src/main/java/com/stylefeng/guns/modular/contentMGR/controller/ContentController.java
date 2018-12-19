package com.stylefeng.guns.modular.contentMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.warpper.ContentWrapper;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.CodeKit;
import com.stylefeng.guns.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;

import java.util.List;
import java.util.Map;

/**
 * 资讯管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-25 00:41:19
 */
@Controller
@RequestMapping("/content")
public class ContentController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ContentController.class);

    private String PREFIX = "/contentMGR/content/";

    @Autowired
    private IContentService contentService;

    @Autowired
    private IAttachmentService attachmentService;

    /**
     * 跳转到资讯管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "content.html";
    }

    /**
     * 跳转到添加资讯管理
     */
    @RequestMapping("/content_add")
    public String contentAdd() {
        return PREFIX + "content_add.html";
    }

    /**
     * 跳转到修改资讯管理
     */
    @RequestMapping("/content_update/{contentId}")
    public String contentUpdate(@PathVariable Integer contentId, Model model) {
        Content content = contentService.selectById(contentId);
        model.addAttribute("item",content);
        LogObjectHolder.me().set(content);
        return PREFIX + "content_edit.html";
    }

    /**
     * 获取资讯管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<Content> page = new PageFactory<Content>().defaultPage();
        Page<Map<String, Object>> pageMap = contentService.selectMapsPage(page, new EntityWrapper<Content>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        //包装数据
        new ContentWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }
    /**
     * 获取资讯管理列表
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public Object listAll(String condition) {
        return contentService.selectList(null);
    }

    /**
     * 新增资讯管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Content content, String masterCode, String masterName) {
        Attachment icon = null;
        List<Attachment> attachmentList = attachmentService.listAttachment(masterName, masterCode);
        if (null != attachmentList || attachmentList.size() > 0){
            icon = attachmentList.get(0);
            content.setTimage(PathUtil.generate(iconVisitURL, String.valueOf(icon.getId())));
        }

        contentService.create(content);

        // 更新ICON资源
        if (null != icon && null != icon.getId())
            try {
                icon.setMasterName(Content.class.getSimpleName());
                icon.setMasterCode(String.valueOf(content.getId()));

                attachmentService.updateById(icon);
            }catch(Exception e){
                log.warn("更新图标失败");
            }
        return SUCCESS_TIP;
    }

    /**
     * 删除资讯管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long contentId) {
        contentService.deleteById(contentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改资讯管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Content content) {
        contentService.updateById(content);
        return SUCCESS_TIP;
    }

    /**
     * 资讯管理详情
     */
    @RequestMapping(value = "/detail/{contentId}")
    @ResponseBody
    public Object detail(@PathVariable("contentId") Integer contentId) {
        return contentService.selectById(contentId);
    }
}
