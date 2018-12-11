package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "StudentDetailResponse", description = "学员详情")
public class StudentDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = 3562890692640879634L;

    @ApiModelProperty(name = "data", value = "学员")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public static Responser me(Student student) {

        StudentDetailResponse response = new StudentDetailResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setStudent(student);
        return response;
    }
}
