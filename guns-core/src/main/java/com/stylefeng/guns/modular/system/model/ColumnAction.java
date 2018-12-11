package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 栏目
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-26
 */
@TableName("tb_column_action")
public class ColumnAction extends Model<ColumnAction> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 栏目编码：LM + 6位序列码
     */
    @TableField("column_code")
    private String columnCode;

    /**
     * 动作名称
     */
    private String name;
    /**
     * 类型： 1 H5页面  2 内置动作  3 跳转外部页面
     */
    private Integer type;
    /**
     * 动作： type = 1   该字段为URI 连接  type = 2  字段为APP内置动作； type = 3 字段为http开头的URL
     */
    private String action;
    /**
     * JsonObject 格式数据 : {name : "test"}
     */
    private String data;
    /**
     * 状态  0 无效  1 有效
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ColumnAction{" +
        "id=" + id +
        ", columnCode=" + columnCode +
        ", name=" + name +
        ", type=" + type +
        ", action=" + action +
        ", data=" + data +
        ", status=" + status +
        "}";
    }
}
