package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.stylefeng.guns.common.constant.state.GenericState;

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
public class ScheduleStudent extends Model<ScheduleStudent> {

    private static final long serialVersionUID = 1L;

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 学员编码
     */
    @TableField("student_code")
    private String studentCode;
    /**
     * 学员名称
     */
    @TableField("student_name")
    private String studentName;
    /**
     * 班级编码
     */
    @TableField("class_code")
    private String classCode;
    /**
     * 班级名称
     */
    @TableField("class_name")
    private String className;
    /**
     * 课时编码
     */
    @TableField("outline_code")
    private String outlineCode;
    /**
     * 课时名称
     */
    private String outline;
    /**
     * 上课日期
     */
    @TableField("study_date")
    private Date studyDate;
    /**
     * 上一次排课标示
     */
    private String pcode;
    /**
     * 首次排课标示
     */
    private String fcode;
    /**
     * 历史排课标示
     */
    private String pcodes;
    /**
     * 状态： 0 失效  1  有效
     */
    private Integer status;
    /**
     * 备注
     */
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
