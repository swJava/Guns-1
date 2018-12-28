package com.stylefeng.guns.modular.studentMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.log.LogObjectHolder;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.studentMGR.warpper.StudentWrapper;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.CodeKit;
import com.stylefeng.guns.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 学生管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-07 10:12:17
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private String PREFIX = "/studentMGR/student/";

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IAttachmentService attachmentService;

    /**
     * 跳转到学生管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "student.html";
    }

    /**
     * 跳转到添加学生管理
     */
    @RequestMapping("/student_add")
    public String studentAdd() {
        return PREFIX + "student_add.html";
    }

    /**
     * 跳转到修改学生管理
     */
    @RequestMapping("/student_update/{studentId}")
    public String studentUpdate(@PathVariable Integer studentId, Model model) {
        Student student = studentService.selectById(studentId);

        if (null == student)
            throw new ServiceException(MessageConstant.MessageCode.SYS_SUBJECT_NOT_FOUND, new String[]{"学员信息"});

        List<Attachment> avatarList = attachmentService.listAttachment(Student.class.getSimpleName(), String.valueOf(studentId));
        if (null != avatarList && avatarList.size() > 0)
            student.setAvatar(String.valueOf(avatarList.get(0).getId()));

        model.addAttribute("item", student);
        LogObjectHolder.me().set(student);
        return PREFIX + "student_edit.html";
    }

    /**
     * 获取学生管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

        //分页查詢
        Page<Student> page = new PageFactory<Student>().defaultPage();
        Page<Map<String, Object>> pageMap = studentService.selectMapsPage(page, new EntityWrapper<Student>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }
            }
        });
        //包装数据
        new StudentWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 新增学生管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Student student) {
        student.setCode(CodeKit.generateStudent());
        studentService.insert(student);
        return SUCCESS_TIP;
    }

    /**
     * 删除学生管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long studentId) {
        studentService.deleteById(studentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改学生管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Student student, String masterName, String masterCode) {

        Attachment icon = null;
        List<Attachment> attachmentList = attachmentService.listAttachment(masterName, masterCode);
        if (null != attachmentList && attachmentList.size() > 0){
            icon = attachmentList.get(0);
            student.setAvatar(PathUtil.generate(iconVisitURL, String.valueOf(icon.getId())));
        }

        studentService.updateById(student);

        // 更新ICON资源
        if (null != icon && null != icon.getId())
            try {
                icon.setMasterName(Student.class.getSimpleName());
                icon.setMasterCode(String.valueOf(student.getId()));

                attachmentService.updateAndRemoveOther(icon);
            }catch(Exception e){
                log.warn("更新图标失败");
            }
        return SUCCESS_TIP;
    }

    /**
     * 学生管理详情
     */
    @RequestMapping(value = "/detail/{studentId}")
    @ResponseBody
    public Object detail(@PathVariable("studentId") Integer studentId) {
        return studentService.selectById(studentId);
    }
}
