package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

/**
 * Created by 罗华.
 */
@ApiModel(value = "ClassListResponse", description = "班级列表")
public class ClassListResponse extends SimpleResponser {
    private static final long serialVersionUID = 4265927729370374968L;

    @ApiModelProperty(name = "data", value = "班级集合")
    private Collection<ClassResponser> data = new ArrayList<ClassResponser>();

    public Collection<ClassResponser> getData() {
        return data;
    }

    public void setData(Collection<com.stylefeng.guns.modular.system.model.Class> data) {
        for(com.stylefeng.guns.modular.system.model.Class classInfo : data){
            //
            this.data.add(ClassResponser.me(classInfo));
        }
    }

    public static Responser me(Collection<com.stylefeng.guns.modular.system.model.Class> classList) {
        ClassListResponse response = new ClassListResponse();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(classList);
        return response;
    }
}
