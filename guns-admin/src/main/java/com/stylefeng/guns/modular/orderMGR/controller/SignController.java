package com.stylefeng.guns.modular.orderMGR.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.classMGR.service.IClassService;
import com.stylefeng.guns.modular.classMGR.warpper.ClassWrapper;
import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.orderMGR.service.ICourseCartService;
import com.stylefeng.guns.modular.studentMGR.service.IStudentService;
import com.stylefeng.guns.modular.system.model.Attachment;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-18 22:46:16
 */
@Controller
@RequestMapping("/order/sign")
public class SignController extends BaseController {

    private String PREFIX = "/orderMGR/order/";

    @Autowired
    private ICourseCartService courseCartService;

    @Autowired
    private IClassService classService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IStudentService studentService;
    /**
     * 跳转到报名首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sign.html";
    }

    @RequestMapping("/sign_import/{classCode}")
    public String openImportDlg(@PathVariable("classCode") String code, Model model){

        if (null == code)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"班级信息"});

        Class classInfo = classService.get(code);

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"班级信息"});

        model.addAttribute("item", classInfo);
        return PREFIX + "sign_import.html";
    }

    /**
     * 获取需要预报名的班级列表
     */
    @RequestMapping(value = "/classlist")
    @ResponseBody
    public Object list(String condition) {
        //分页查詢
        Page<Class> page = new PageFactory<Class>().defaultPage();
        Page<Map<String, Object>> pageMap = classService.selectMapsPage(page, new EntityWrapper<Class>() {
            {
                //name条件分页
                if (StringUtils.isNotEmpty(condition)) {
                    like("name", condition);
                }

                gt("sign_start_date", new Date());
                eq("status", GenericState.Valid.code);

                orderBy("id", false);
            }
        });
        //包装数据
        new ClassWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }


    /**
     * 获取需要预报名的班级列表
     */
    @RequestMapping(value = "/import/student")
    @ResponseBody
    public Object importStudents(String classCode, String masterName, String masterCode) {

        Attachment file = null;
        List<Attachment> attachmentList = attachmentService.listAttachment(masterName, masterCode);
        if (null != attachmentList && attachmentList.size() > 0){
            file = attachmentList.get(0);
        }

        Class classInfo = classService.get(classCode);

        if (null == classInfo)
            throw new ServiceException(MessageConstant.MessageCode.SYS_MISSING_ARGUMENTS, new String[]{"班级信息"});

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(file.getPath())));

            String line = reader.readLine();
            while(null != line){
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                int idx = 0;
                Member member = null;
                Student student = null;
                while(tokenizer.hasMoreTokens()){
                    switch(idx){
                        case 0:
                            member = memberService.get(tokenizer.nextToken());
                            idx++;
                            break;
                        case 1:
                            student = studentService.get(tokenizer.nextToken());
                            idx++;
                            break;
                    }
                }

                line = reader.readLine();

                if (null == student || null == member)
                    continue;

                try {
                    courseCartService.join(member, student, classInfo);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return SUCCESS_TIP;
    }
}
