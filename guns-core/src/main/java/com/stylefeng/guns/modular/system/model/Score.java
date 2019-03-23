package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/14 21:02
 * @Version 1.0
 */
@TableName("tb_score")
@ApiModel(value = "Score", description = "考试分数")
public class Score extends Model<Score> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(name = "student", value = "学员名称", position = 0, example="小明")
    private String student;

    @TableField("mobile_number")
    @ApiModelProperty(name = "mobileNumber", value = "电话", position = 1, example="18580255110")
    private String mobileNumber;

    @TableField("examine_name")
    @ApiModelProperty(name = "examineName", value = "考试名称", position = 2, example="衔接班分班考试")
    private String examineName;

    @ApiModelProperty(name = "round", value = "考试场次", position = 3, example="第一场")
    private String round;

    @ApiModelProperty(name = "score", value = "学员得分数", position = 4, example="81")
    private Double score;

    @TableField("total_score")
    @ApiModelProperty(name = "totalScore", value = "总分数", position = 5, example="100")
    private Double totalScore;

    @ApiModelProperty(name = "rank", value = "排名", position = 6, example="2")
    private Integer rank;

    @ApiModelProperty(name = "remark", value = "备注", position = 7, example="可报名尖端班")
    private String remark;

    @TableField("import_date")
    @ApiModelProperty(hidden = true)
    private Date importDate;

    @TableField("student_code")
    @ApiModelProperty(hidden = true)
    private String studentCode;

    @TableField("class_code")
    @ApiModelProperty(name = "classCode", value = "可报班级编码，多个以逗号分割", position = 7, example="BJ000001,BJ000002")
    private String classCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getExamineName() {
        return examineName;
    }

    public void setExamineName(String examineName) {
        this.examineName = examineName;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setScore(String score){
        this.score = Double.parseDouble(score);
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public void setTotalScore(String totalScore){
        this.totalScore = Double.parseDouble(totalScore);
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setRank(String rank) {
        this.rank = Integer.parseInt(rank);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", student=" + student +
                ", mobileNumber=" + mobileNumber +
                ", examineName=" + examineName +
                ", round=" + round +
                ", score=" + score +
                ", totalScore=" + totalScore +
                ", rank=" + rank +
                ", remark=" + remark +
                ", studentCode=" + studentCode +
                ", classCode=" + classCode +
                "}";
    }
}
