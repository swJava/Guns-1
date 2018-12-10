package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
<<<<<<< HEAD
import org.springframework.boot.context.properties.ConfigurationProperties;

=======
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
import java.io.Serializable;

/**
 * <p>
 * 班级入学试题策略
 * </p>
 *
<<<<<<< HEAD
 * @author stylefeng
 * @since 2018-11-30
=======
 * @author simple.song
 * @since 2018-12-06
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
 */
@TableName("tb_class_exam_strategy")
public class ClassExamStrategy extends Model<ClassExamStrategy> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 题目数量
     */
<<<<<<< HEAD
    private Integer count = 0;
    /**
     * 测试时间， 单位： 分钟
     */
    private Integer duration = 0;
    /**
     * 总分
     */
    @TableField("full_credit")
    private Integer fullCredit = 0;
=======
    private Integer count;
    /**
     * 测试时间， 单位： 分钟
     */
    private Integer duration;
    /**
     * 总分数
     */
    @TableField("full_credit")
    private Integer fullCredit;
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
    /**
     * 选择题占比， 1 - 100
     */
    @TableField("select_ratio")
<<<<<<< HEAD
    private BigDecimal selectRatio = new BigDecimal("0");
=======
    private Integer selectRatio;
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
    /**
     * 填空题占比， 1 - 100
     */
    @TableField("fill_ratio")
<<<<<<< HEAD
    private BigDecimal fillRatio = new BigDecimal("0");
=======
    private Integer fillRatio;
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
    /**
     * 主观题占比， 1 - 100
     */
    @TableField("subject_ratio")
<<<<<<< HEAD
    private BigDecimal subjectRatio = new BigDecimal("0");
=======
    private Integer subjectRatio;
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
    /**
     * 是否自动阅卷： 0 不阅卷   1  自动阅卷
     */
    @TableField("auto_marking")
    private Integer autoMarking;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFullCredit() {
        return fullCredit;
    }

    public void setFullCredit(Integer fullCredit) {
        this.fullCredit = fullCredit;
    }

<<<<<<< HEAD
    public BigDecimal getSelectRatio() {
        return selectRatio;
    }

    public void setSelectRatio(BigDecimal selectRatio) {
        this.selectRatio = selectRatio;
    }

    public BigDecimal getFillRatio() {
        return fillRatio;
    }

    public void setFillRatio(BigDecimal fillRatio) {
        this.fillRatio = fillRatio;
    }

    public BigDecimal getSubjectRatio() {
        return subjectRatio;
    }

    public void setSubjectRatio(BigDecimal subjectRatio) {
=======
    public Integer getSelectRatio() {
        return selectRatio;
    }

    public void setSelectRatio(Integer selectRatio) {
        this.selectRatio = selectRatio;
    }

    public Integer getFillRatio() {
        return fillRatio;
    }

    public void setFillRatio(Integer fillRatio) {
        this.fillRatio = fillRatio;
    }

    public Integer getSubjectRatio() {
        return subjectRatio;
    }

    public void setSubjectRatio(Integer subjectRatio) {
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
        this.subjectRatio = subjectRatio;
    }

    public Integer getAutoMarking() {
        return autoMarking;
    }

    public void setAutoMarking(Integer autoMarking) {
        this.autoMarking = autoMarking;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClassExamStrategy{" +
        "id=" + id +
        ", classCode=" + classCode +
        ", count=" + count +
        ", duration=" + duration +
<<<<<<< HEAD
=======
        ", fullCredit=" + fullCredit +
>>>>>>> 44eb44aecc64acebd68cd4d278dfa42de93bf9c6
        ", selectRatio=" + selectRatio +
        ", fillRatio=" + fillRatio +
        ", subjectRatio=" + subjectRatio +
        ", autoMarking=" + autoMarking +
        "}";
    }
}
