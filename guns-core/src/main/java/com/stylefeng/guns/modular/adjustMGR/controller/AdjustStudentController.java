package com.stylefeng.guns.modular.adjustMGR.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.log.LogObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.AdjustStudent;
import com.stylefeng.guns.modular.adjustMGR.service.IAdjustStudentService;

/**
 * 调课管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-19 23:01:31
 */
@Controller
@RequestMapping("/adjustStudent")
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
     * 跳转到添加调课管理
     */
    @RequestMapping("/adjustStudent_add")
    public String adjustStudentAdd() {
        return PREFIX + "adjustStudent_add.html";
    }

    /**
     * 审核通过
     */
    @RequestMapping("/pass/{adjustStudentId}")
    public Object adjustStudentUpdate(@PathVariable Long adjustStudentId, Model model) {
        adjustStudentService.updateById(new AdjustStudent(){{setId(adjustStudentId);setWorkStatus(workStatusEnum.pass.getCode());}});
        return SUCCESS_TIP;
    }
    /**
     * 审核不通过
     */
    @RequestMapping("/pass_not/{adjustStudentId}")
    public Object adjustStudentUpdateNot(@PathVariable Long adjustStudentId, Model model) {
        adjustStudentService.updateById(new AdjustStudent(){{setId(adjustStudentId);setWorkStatus(workStatusEnum.back.getCode());}});
        return SUCCESS_TIP;
    }

    /**
     * 获取调课管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return adjustStudentService.selectList(null);
    }

    /**
     * 新增调课管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AdjustStudent adjustStudent) {
        adjustStudentService.insert(adjustStudent);
        return SUCCESS_TIP;
    }

    /**
     * 删除调课管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer adjustStudentId) {
        adjustStudentService.deleteById(adjustStudentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改调课管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AdjustStudent adjustStudent) {
        adjustStudentService.updateById(adjustStudent);
        return SUCCESS_TIP;
    }

    /**
     * 调课管理详情
     */
    @RequestMapping(value = "/detail/{adjustStudentId}")
    @ResponseBody
    public Object detail(@PathVariable("adjustStudentId") Integer adjustStudentId) {
        return adjustStudentService.selectById(adjustStudentId);
    }

    public enum workStatusEnum{

        create(10,"新建"),
        pass(11,"通过"),
        back(12,"打回")
        ;
        private int code;
        private String msg;

        workStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
