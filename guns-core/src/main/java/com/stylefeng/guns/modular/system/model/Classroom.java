package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 教室表
 * </p>
 *
 * @author simple.song
 * @since 2018-11-05
 */
@TableName("tb_classroom")
public class Classroom extends Model<Classroom> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 教室编码， JS + 类型 + 5位序列号
     */
    private String code;
    /**
     * 教室名称
     */
    private String name;
    /**
     * 类型： 实体教室 1 ； 虚拟教室 2
     */
    private Integer type;
    /**
     * 教室地址
     */
    private String address;
    /**
     * 最大教学人数
     */
    @TableField("max_count")
    private Integer maxCount;
    /**
     * 状态
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
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
        return "Classroom{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", type=" + type +
        ", address=" + address +
        ", maxCount=" + maxCount +
        ", status=" + status +
        "}";
    }
}
