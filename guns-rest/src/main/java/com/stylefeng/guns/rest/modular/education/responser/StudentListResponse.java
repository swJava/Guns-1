package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.Student;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by 罗华.
 */
@ApiModel(value = "StudentListResponse", description = "学员列表")
public class StudentListResponse extends SimpleResponser {
    private static final long serialVersionUID = 4440575740247388872L;

    @ApiModelProperty(name = "data", value = "学员集合")
    private List<Student> data;

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public static Responser me(List<Student> studentList) {
        StudentListResponse response = new StudentListResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(studentList);
        return response;
    }
}
