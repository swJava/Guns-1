package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 试卷
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
@TableName("tb_examine_paper")
public class ExaminePaper extends Model<ExaminePaper> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 试卷编码： SJ + 8位序列码
     */
    private String code;
    /**
     * 适应年级： 多年级以逗号分隔
     */
    private String grades;
    /**
     * 学科
     */
    private String subject;
    /**
     * 班次
     */
    private Integer ability;
    /**
     * 题目数量
     */
    private Integer count;
    /**
     * 测试时间， 单位： 分钟
     */
    @TableField("exam_time")
    private Integer examTime;
    /**
     * 总分数
     */
    @TableField("total_score")
    private Integer totalScore;
    /**
     * 出题人
     */
    private String teacher;
    /**
     * 出题时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 状态： 0 无效  1 有效
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

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return "ExaminePaper{" +
        "id=" + id +
        ", code=" + code +
        ", grades=" + grades +
        ", subject=" + subject +
        ", ability=" + ability +
        ", count=" + count +
        ", examTime=" + examTime +
        ", totalScore=" + totalScore +
        ", teacher=" + teacher +
        ", createDate=" + createDate +
        ", status=" + status +
        "}";
    }
}
