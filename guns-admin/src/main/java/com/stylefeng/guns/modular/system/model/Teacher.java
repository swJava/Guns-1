package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author simple.song
 * @since 2018-10-04
 */
@TableName("tb_teacher")
public class Teacher extends Model<Teacher> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 教师编码
     */
    private String code;
    /**
     * 教师名称
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 教师类型: L 讲师； A 辅导员； E 外聘专家
     */
    private String type;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 毕业院校
     */
    private String graduate;
    /**
     * 授课年级
     */
    private Integer grade;
    /**
     * 教学成果
     */
    private String havest;
    /**
     * 教学经验
     */
    private String experience;
    /**
     * 教学特点
     */
    private String feature;
    /**
     * 状态:0=正常，1=辞职
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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
        ", graduate=" + graduate +
        ", grade=" + grade +
        ", havest=" + havest +
        ", experience=" + experience +
        ", feature=" + feature +
        ", status=" + status +
        "}";
    }
}
