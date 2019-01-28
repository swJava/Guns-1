package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.stylefeng.guns.common.constant.state.GenericState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 学员教学计划
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-17
 */
@TableName("tb_schedule_student")
@ApiModel(value = "ScheduleStudent", description = "学员排课信息")
public class ScheduleStudent extends Model<ScheduleStudent> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 编码
     */
    @ApiModelProperty()
    private String code;
    /**
     * 学员编码
     */
    @TableField("student_code")
    @ApiModelProperty(name = "studentCode", value = "学员号", example = "XY181201000001")
    private String studentCode;
    /**
     * 学员名称
     */
    @TableField("student_name")
    @ApiModelProperty(name = "studentName", value = "学员名称", example = "小明")
    private String studentName;
    /**
     * 班级编码
     */
    @TableField("class_code")
    @ApiModelProperty(name = "classCode", value = "班级编码", example = "BJ000001")
    private String classCode;
    /**
     * 班级名称
     */
    @TableField("class_name")
    @ApiModelProperty(name = "className", value = "班级名称", example = "寒假班小学一年级语文启航班")
    private String className;
    /**
     * 课时编码
     */
    @TableField("outline_code")
    @ApiModelProperty(name = "outlineCode", value = "课时编码", example = "KS000001")
    private String outlineCode;
    /**
     * 课时名称
     */
    @ApiModelProperty(name = "outline", value = "课时名称", example = "第一课")
    private String outline;
    /**
     * 上课日期
     */
    @TableField("study_date")
    @ApiModelProperty(name = "studyDate", value = "上课日期", example = "2018-12-30")
    private Date studyDate;
    /**
     * 上一次排课标示
     */
    @ApiModelProperty(hidden = true)
    private String pcode;
    /**
     * 首次排课标示
     */
    @ApiModelProperty(hidden = true)
    private String fcode;
    /**
     * 历史排课标示
     */
    @ApiModelProperty(hidden = true)
    private String pcodes;
    /**
     * 状态： 0 失效  1  有效
     */
    @ApiModelProperty(hidden = true)
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(name = "remark", value = "备注", example = "备注")
    private String remark;


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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOutlineCode() {
        return outlineCode;
    }

    public void setOutlineCode(String outlineCode) {
        this.outlineCode = outlineCode;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }

    public String getPcodes() {
        return pcodes;
    }

    public void setPcodes(String pcodes) {
        this.pcodes = pcodes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ScheduleStudent{" +
        "id=" + id +
        ", code=" + code +
        ", studentCode=" + studentCode +
        ", studentName=" + studentName +
        ", classCode=" + classCode +
        ", className=" + className +
        ", outlineCode=" + outlineCode +
        ", outline=" + outline +
        ", studyDate=" + studyDate +
        ", pcode=" + pcode +
        ", fcode=" + fcode +
        ", pcodes=" + pcodes +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }

    public boolean isValid() {
        return this.status == GenericState.Valid.code;
    }

    public boolean isOver() {
        Date now = new Date();

        if (null == this.studyDate)
            return true;

        return this.studyDate.before(now);
    }
}
