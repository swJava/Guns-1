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
 * <p>
 * 试卷
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-22
 */
@TableName("tb_examine_paper")
@ApiModel(value = "ExaminePaper", description = "试卷")
public class ExamineApply extends Model<ExamineApply> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 试卷编码： SJ + 8位序列码
     */
    @ApiModelProperty(name = "code", value = "编码", position = 0, example = "SJ00000001")
    private String paperCode;
    /**
     * 班次
     */
    @ApiModelProperty(name = "ability", value = "适应班次", position = 3, example = "3")
    private Integer ability;

    /**
     * 学期
     */
    @ApiModelProperty(name = "cycle", value = "适应学期", position = 4, example = "3")
    private Integer cycle;

    /**
     * 测试时间， 单位： 分钟
     */
    @ApiModelProperty(name = "examTime", value = "测试时间， 单位： 分钟", position = 5, example = "30")
    @TableField("exam_time")
    private Integer examTime;

    /**
     * 及格分数
     */
    @ApiModelProperty(name = "passScore", value = "及格分数", position = 6, example = "60")
    @TableField("pass_score")
    private Integer passScore;

    /**
     * 状态： 0 无效  1 有效
     */
    @ApiModelProperty(hidden = true)
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getExamTime() {
        return examTime;
    }

    public void setExamTime(Integer examTime) {
        this.examTime = examTime;
    }

    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
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
        ", paperCode=" + paperCode +
        ", ability=" + ability +
        ", cycle=" + cycle +
        ", examTime=" + examTime +
        ", passScore=" + passScore +
        ", status=" + status +
        "}";
    }
}
