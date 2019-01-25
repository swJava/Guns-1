package com.stylefeng.guns.modular.adjustMGR.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.admin.Administrator;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.adjustMGR.service.IAdjustStudentService;
import com.stylefeng.guns.modular.adjustMGR.warpper.AdjustStudentWrapper;
import com.stylefeng.guns.modular.system.model.AdjustStudent;
import com.stylefeng.guns.modular.system.model.AdjustStudentApproveStateEnum;
import com.stylefeng.guns.modular.system.model.AdjustStudentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 调课管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 23:01:31
 */
@Controller
@RequestMapping("/adjust")
public class AdjustStudentController extends BaseController {

    private String PREFIX = "/adjustMGR/adjustStudent/";

    @Autowired
    private IAdjustStudentService adjustStudentService;

    /**
     * 跳转到调课管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "adjustStudent.html";
    }

    /**
     * 跳转到修改调课管理
     */
    @RequestMapping("/{op}")
    public String adjustStudentUpdate(@PathVariable String op, Long applyId, Model model) {
        AdjustStudent adjustStudent = adjustStudentService.selectById(applyId);

        if (null == adjustStudent)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"调课申请"});

        model.addAttribute("item",adjustStudent);
        model.addAttribute("op", op);
        LogObjectHolder.me().set(adjustStudent);
        return PREFIX + "changeStudent_edit.html";
    }

    /**
     * 获取调课管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        //分页查詢
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("status", GenericState.Valid.code);

        Page<Map<String, Object>> pageMap = adjustStudentService.selectApplyMapsPage(AdjustStudentTypeEnum.Adjust, queryMap);
        //包装数据
        new AdjustStudentWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 关闭调课申请
     */
    @RequestMapping(value = "/close")
    @ResponseBody
    public Object close(@RequestParam Long applyId) {

        Administrator currAdmin = ShiroKit.getUser();
        adjustStudentService.setAdministrator(currAdmin);
        AdjustStudent adjustStudent = adjustStudentService.closeApply(applyId);

        LogObjectHolder.me().set(adjustStudent);
        return SUCCESS_TIP;
    }

    /**
     * 拒绝调课申请
     */
    @RequestMapping(value = "/approve/reject")
    @ResponseBody
    public Object reject(@RequestParam Long applyId, String remark) {

        Administrator currAdmin = ShiroKit.getUser();
        adjustStudentService.setAdministrator(currAdmin);
        AdjustStudent adjustStudent = adjustStudentService.doAdjustApprove(applyId, AdjustStudentApproveStateEnum.Refuse, remark);
        return SUCCESS_TIP;
    }

    /**
     * 审批调课申请
     */
    @RequestMapping(value = "/approve/pass")
    @ResponseBody
    public Object pass(@RequestParam Long applyId, String remark) {

        Administrator currAdmin = ShiroKit.getUser();
        adjustStudentService.setAdministrator(currAdmin);
        AdjustStudent adjustStudent = adjustStudentService.doAdjustApprove(applyId, AdjustStudentApproveStateEnum.Appove, remark);
        return SUCCESS_TIP;
    }
}
