package com.stylefeng.guns.rest.modular.member.controller;

import com.stylefeng.guns.modular.memberMGR.service.IMemberService;
import com.stylefeng.guns.modular.system.model.Class;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.rest.core.ApiController;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.modular.education.responser.ClassResponser;
import com.stylefeng.guns.rest.modular.member.responser.MyClassListResponser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 1:24
 * @Version 1.0
 */
@Api(tags = "个人中心")
@RestController
@RequestMapping("/my")
public class MyController extends ApiController {
    @Autowired
    private IMemberService memberService;

    @ApiOperation(value = "我的班级列表", httpMethod = "POST", response = MyClassListResponser.class)
    @RequestMapping(value = "/class/list", method = RequestMethod.POST)
    public Responser listMyClass(String student) {
        Member member = currMember();

        Map<String, Set<Class>> myClasses = memberService.findMyClasses(member.getUserName(), student);

        List<ClassResponser> classList = new ArrayList<ClassResponser>();

        Iterator<String> myClassIterator = myClasses.keySet().iterator();
        while(myClassIterator.hasNext()){
            String studentCode = myClassIterator.next();

            for (Class classInfo : myClasses.get(studentCode)) {
                ClassResponser classResponser = ClassResponser.me(classInfo);
                classResponser.setStudent(studentCode);
                classList.add(classResponser);
            }
        }

        return MyClassListResponser.me(classList);
    }
}
