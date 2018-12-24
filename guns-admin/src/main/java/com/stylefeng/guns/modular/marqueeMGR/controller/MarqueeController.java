package com.stylefeng.guns.modular.marqueeMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.controller.ContentController;
import com.stylefeng.guns.modular.contentMGR.service.IContentService;
import com.stylefeng.guns.modular.contentMGR.warpper.ContentWrapper;
import com.stylefeng.guns.modular.system.model.Content;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

    private static final String COLUMN = "LM000001";

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
        model.addAttribute("item", marqueeContent);
        LogObjectHolder.me().set(marqueeContent);
        return PREFIX + "marquee_update.html";
    }

    /**
     * 获取资讯管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {

        List<Content> marqueeList = contentService.findArticleOutline(COLUMN);

        return marqueeList;
    }
}
