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
 * 内容
 * </p>
 *
 * @author simple.song
 * @since 2018-10-26
 */
@TableName("tb_content")
public class Content extends Model<Content> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 内容编码： CT + 年月日（6位）+ 8位序列码
     */
    private String code;
    /**
     * 类型： 1 普通文章   2 外链文章  3 外链视频
     */
    private Integer type;
    /**
     * 标题图片
     */
    private String timage;
    /**
     * 标题
     */
    private String title;
    /**
     * 一句话简介
     */
    private String introduce;
    /**
     * 作者
     */
    private String author;
    /**
     * 发布类型： 1=引用   2=原创
     */
    @TableField("publish_type")
    private Integer publishType;
    /**
     * 内容，富文本内容
     */
    private String content;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 下架时间
     */
    @TableField("dead_date")
    private Date deadDate;
    /**
     * 状态: 1=启用，2=禁用
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTimage() {
        return timage;
    }

    public void setTimage(String timage) {
        this.timage = timage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
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
        return "Content{" +
        "id=" + id +
        ", code=" + code +
        ", type=" + type +
        ", timage=" + timage +
        ", title=" + title +
        ", introduce=" + introduce +
        ", author=" + author +
        ", publishType=" + publishType +
        ", content=" + content +
        ", createDate=" + createDate +
        ", deadDate=" + deadDate +
        ", status=" + status +
        "}";
    }
}
