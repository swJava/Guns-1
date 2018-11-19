package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by 罗华.
 */
@TableName("tb_attachment")
@ApiModel(value = "Attachment", description = "附件")
public class Attachment extends Model<Attachment> {
    private static final long serialVersionUID = 8064117685034666974L;

    /**
     * 主键id
     */
    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    @TableField("master_code")
    @NotBlank
    @ApiModelProperty(name = "masterCode", value = "编码", position = 0, example="XY000001")
    private String masterCode;

    @TableField("master_name")
    @NotBlank
    @ApiModelProperty(name = "masterName", value = "类别名", hidden = true)
    private String masterName;

    @TableField("file_name")
    @ApiModelProperty(name = "fileName", value = "原文件名称", hidden = true)
    private String fileName;

    @TableField("attachment_name")
    @ApiModelProperty(name = "attachmentName", value = "附件名称", hidden = true)
    private String attachmentName;

    @ApiModelProperty(name = "type", value = "文件类型", hidden = true)
    private String type;

    @ApiModelProperty(name = "size", value = "文件大小", hidden = true)
    private Long size;

    @ApiModelProperty(name = "path", value = "文件存储路径", hidden = true)
    private String path;

    @ApiModelProperty(name = "status", value = "状态", hidden = true)
    private Integer status;

    @ApiModelProperty(name = "remark", value = "备注", hidden = true)
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        return null;
    }
}
