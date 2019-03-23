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
 * 批量任务
 *
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/17 14:50
 * @Version 1.0
 */
@TableName("tb_batch_process")
@ApiModel(value = "BatchProcess", description = "批量处理任务")
public class BatchProcess extends Model<BatchProcess> {

    /**
     * 标示
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    private String code;

    private Integer service;

    private String description;

    @TableField("import_count")
    private Integer importCount;

    @TableField("complete_count")
    private Integer completeCount;

    private Integer status;

    @TableField("work_status")
    private Integer workStatus;

    @TableField("import_date")
    private Date importDate;

    @TableField("complete_date")
    private Date completeDate;

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

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImportCount() {
        return importCount;
    }

    public void setImportCount(Integer importCount) {
        this.importCount = importCount;
    }

    public Integer getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(Integer completeCount) {
        this.completeCount = completeCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", code=" + code +
                ", service=" + service +
                ", description=" + description +
                ", importCount=" + importCount +
                ", completeCount=" + completeCount +
                ", status=" + status +
                ", workStatus=" + workStatus +
                ", importDate=" + importDate +
                ", completeDate=" + completeDate +
                ", remark=" + remark +
                "}";
    }
}
