package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 关系栏目内容
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-26
 */
@TableName("tb_content_category")
public class ContentCategory extends Model<ContentCategory> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 栏目编码
     */
    @TableField("column_code")
    private String columnCode;
    /**
     * 栏目名称
     */
    @TableField("column_name")
    private String columnName;
    /**
     * 内容编码
     */
    @TableField("content_code")
    private String contentCode;
    /**
     * 文章标题
     */
    @TableField("content_name")
    private String contentName;
    /**
     * 状态
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
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
        return "ContentCategory{" +
        "id=" + id +
        ", columnCode=" + columnCode +
        ", columnName=" + columnName +
        ", contentCode=" + contentCode +
        ", contentName=" + contentName +
        ", status=" + status +
        "}";
    }
}
