package com.stylefeng.guns.modular.contentMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.contentMGR.service.IColumnService;
import com.stylefeng.guns.modular.contentMGR.warpper.ColumnWrapper;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.CodeKit;
import com.stylefeng.guns.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.httpserver.AuthFilter;

import java.util.HashMap;
import java.util.List;
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
    private static final Logger log = LoggerFactory.getLogger(ColumnController.class);

    private String PREFIX = "/column/column/";

    @Autowired
    private IColumnService columnService;

    @Autowired
    private IAttachmentService attachmentService;

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
    public String columnAdd(Model model) {
        Map<String, Object> iconInfo = new HashMap<>();
        iconInfo.put("id", 0);
        model.addAttribute("icon", iconInfo);

        return PREFIX + "column_add.html";
    }

    /**
     * 跳转到修改栏目
     */
    @RequestMapping("/column_update/{columnId}")
    public String columnUpdate(@PathVariable Integer columnId, Model model) {
        Column column = columnService.selectById(columnId);
        model.addAttribute("item",column);

        long id = 0L;
        List<Attachment> attachmentList = attachmentService.listAttachment(Column.class.getSimpleName(), String.valueOf(column.getId()));
        if (null != attachmentList && attachmentList.size() > 0)
            id = attachmentList.get(0).getId();
        Map<String, Object> iconInfo = new HashMap<>();
        iconInfo.put("id", id);
        model.addAttribute("icon", iconInfo);

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
     * 获取栏目列表
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    public Object listAll(String condition) {
        return columnService.selectList(null);
    }

    /**
     * 新增栏目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Column column, String masterCode, String masterName) {
        if(StringUtils.isNotEmpty(column.getPcode())){
            Column code = columnService.selectOne(new EntityWrapper<Column>() {
                {
                    eq("code", column.getPcode());
                }
            });
            column.setPcodes(code.getPcodes()+","+code.getCode());
        }else{
            column.setPcode("LM000000");
            column.setPcodes("LM000000");
        }

        Attachment icon = null;
        List<Attachment> attachmentList = attachmentService.listAttachment(masterName, masterCode);
        if (null != attachmentList || attachmentList.size() > 0){
            icon = attachmentList.get(0);
            column.setIcon(PathUtil.generate(iconVisitURL, String.valueOf(icon.getId())));
        }

        columnService.create(column);

        // 更新ICON资源
        if (null != icon && null != icon.getId())
            try {
                icon.setMasterName(Column.class.getSimpleName());
                icon.setMasterCode(String.valueOf(column.getId()));

                attachmentService.updateById(icon);
            }catch(Exception e){
                log.warn("更新图标失败");
            }
        return SUCCESS_TIP;
    }

    /**
     * 删除栏目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long columnId) {
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
