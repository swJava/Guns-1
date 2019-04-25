package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 教师表
 * </p>
 *
 * @author simple
 * @since 2018-11-03
 */
@TableName("tb_teacher")
@ApiModel(value = "Teacher", description = "教师")
public class Teacher extends Model<Teacher> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 教师编码： LS + 6位序列码
     */
    @ApiModelProperty(name = "code", value = "教师编码", position = 0, example="LS000001")
    private String code;
    /**
     * 教师名称
     */
    @ApiModelProperty(name = "name", value = "教师名称", position = 1, example="老明")
    private String name;
    /**
     * 头像
     */
    @ApiModelProperty(name = "avatar", value = "头像", position = 2, example="http://192.168.10.11/tx020313213.jpg")
    private String avatar;
    /**
     * 教师类型:1讲师； 2 辅导员； 3 外聘专家
     */
    @ApiModelProperty(name = "type", value = "教师类型 L 讲师； A 辅导员； E 外聘专家", position = 3, example="L")
    private Integer type;
    /**
     * 性别
     */
    @ApiModelProperty(name = "gender", value = "性别", position = 4, example="1")
    private Integer gender;
    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码", position = 5, example="18580255111")
    private String mobile;
    /**
     * 毕业院校
     */
    @ApiModelProperty(name = "graduate", value = "毕业院校", position = 6, example="重庆大学")
    private String graduate;
    /**
     * 授课年级
     */
    @ApiModelProperty(name = "grade", value = "授课年级", position = 7, example="小学一年级")
    private Integer grade;
    /**
     * 教学成果
     */
    @ApiModelProperty(name = "havest", value = "教学成果", position = 8, example="纯文本")
    private String havest;
    /**
     * 教学经验
     */
    @ApiModelProperty(name = "experience", value = "教学经验", position = 9, example="纯文本")
    private String experience;
    /**
     * 教学特点
     */
    @ApiModelProperty(name = "feature", value = "教学特点", position = 10, example="纯文本")
    private String feature;
    /**
     * 状态
     */
    @ApiModelProperty(hidden = true)
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getHavest() {
        return havest;
    }

    public void setHavest(String havest) {
        this.havest = havest;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
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
        return "Teacher{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", avatar=" + avatar +
        ", type=" + type +
        ", gender=" + gender +
        ", mobile=" + mobile +
        ", graduate=" + graduate +
        ", grade=" + grade +
        ", havest=" + havest +
        ", experience=" + experience +
        ", feature=" + feature +
        ", status=" + status +
        "}";
    }
}
