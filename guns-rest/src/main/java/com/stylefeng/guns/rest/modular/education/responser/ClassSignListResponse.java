package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.student.responser.StudentResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/30 18:50
 * @Version 1.0
 */
@ApiModel(value = "ClassSignListResponse", description = "班级报班列表")
public class ClassSignListResponse extends SimpleResponser {

    @ApiModelProperty(name = "students", value = "学员集合")
    private Collection<StudentResponse> students = new ArrayList<StudentResponse>();

    public Collection<StudentResponse> getStudents() {
        return students;
    }

    public void setStudents(Collection<StudentResponse> students) {
        this.students = students;
    }

    public static Responser me(Collection<StudentResponse> studentList) {
        ClassSignListResponse response = new ClassSignListResponse();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setStudents(studentList);
        return response;
    }
}
