package com.stylefeng.guns.modular.marqueeMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.controller.ContentController;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;
import com.stylefeng.guns.modular.contentMGR.warpper.ContentWrapper;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.DateUtil;
import com.stylefeng.guns.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/24 23:29
 * @Version 1.0
 */
@Controller
@RequestMapping("/content/marquee")
public class MarqueeController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ContentController.class);

    private String PREFIX = "/contentMGR/marquee/";

    private static final String COLUMN = "LM000002";

    @Autowired
    private IContentService contentService;

    @Autowired
    private IAttachmentService attachmentService;

    /**
     * 跳转到滚动广告管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "marquee.html";
    }

    /**
     * 跳转到添加滚动广告管理
     */
    @RequestMapping("/marquee_add")
    public String marqueeEdit(Model model) {
        return PREFIX + "marquee_add.html";
    }
    /**
     * 跳转到添加滚动广告管理
     */
    @RequestMapping("/marquee_update")
    public String marqueeForUpdate(String contentCode, Model model) {

        Content marqueeContent = contentService.get(contentCode);

        if (null == marqueeContent)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND);

        List<Attachment> avatarList = attachmentService.listAttachment(Content.class.getSimpleName(), String.valueOf(marqueeContent.getId()));
        if (null != avatarList && avatarList.size() > 0)
            marqueeContent.setTimage(String.valueOf(avatarList.get(0).getId()));

        model.addAttribute("item", marqueeContent);
        LogObjectHolder.me().set(marqueeContent);
        return PREFIX + "marquee_edit.html";
    }

    /**
     * 获取资讯管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {

        List<Content> marqueeList = contentService.findArticleOutline(COLUMN);

        Map<String, Object> rows = new HashMap<String, Object>();
        rows.put("rows", marqueeList);
        return rows;
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

        Date now = new Date();
        content.setDeadDate(DateUtil.add(now, Calendar.YEAR, 20));
        contentService.createAndPutInColumn(content, COLUMN);

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
     * 修改资讯管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Content content, String masterName, String masterCode) {
        Attachment icon = null;
        List<Attachment> attachmentList = attachmentService.listAttachment(masterName, masterCode);
        if (null != attachmentList && attachmentList.size() > 0){
            icon = attachmentList.get(0);
            content.setTimage(PathUtil.generate(iconVisitURL, String.valueOf(icon.getId())));
        }

        contentService.updateById(content);

        // 更新ICON资源
        if (null != icon && null != icon.getId())
            try {
                icon.setMasterName(Content.class.getSimpleName());
                icon.setMasterCode(String.valueOf(content.getId()));

                attachmentService.updateAndRemoveOther(icon);
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
    public Object delete(@RequestParam String contentCode) {
        contentService.delete(contentCode, new String[]{COLUMN});
        return SUCCESS_TIP;
    }
}
