package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by 罗华.
 */
@TableName("tb_column")
@ApiModel(value = "Column", description = "栏目")
public class Column extends Model<Column> {
    private static final long serialVersionUID = -1853304095528215678L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(name = "code", value = "编码", position = 0, example="LM000001")
    private String code;

    @ApiModelProperty(name = "name", value = "栏目名称", position = 1, example="科萃")
    private String name;

    @ApiModelProperty(name = "icon", value = "图标", position = 2, example="http://192.168.10.2/icon.jpg")
    private String icon;

    @ApiModelProperty(name = "type", value = "类型", position = 3, hidden = true)
    private Integer type;

    @ApiModelProperty(name = "href", value = "链接", position = 4, example="http://192.168.10.2/column/index?id=2")
    private String href;

    @ApiModelProperty(name = "parentColumn", value = "父栏目", hidden = true)
    private String parentColumn;

    @ApiModelProperty(name = "status", value = "状态", hidden = true)
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
