package com.stylefeng.guns.modular.system.model;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by 罗华.
 */
@TableName("tb_class")
@ApiModel(value = "Course", description = "课程")
public class Course extends Model<Course> {
    private static final long serialVersionUID = -2532590715217932359L;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
