package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 序列号
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-25
 */
@TableName("sys_sequence")
public class Sequence extends Model<Sequence> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 序列号名称
     */
    private String name;
    /**
     * 长度
     */
    private Integer length;
    /**
     * 当前值
     */
    @TableField("current_val")
    private Long currentVal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Long getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(Long currentVal) {
        this.currentVal = currentVal;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sequence{" +
        "id=" + id +
        ", name=" + name +
        ", length=" + length +
        ", currentVal=" + currentVal +
        "}";
    }
}
