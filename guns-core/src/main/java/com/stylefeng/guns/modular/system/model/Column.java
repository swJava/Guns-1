package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 栏目
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-26
 */
@TableName("tb_column")
@ApiModel(value = "Column", description = "栏目")
public class Column extends Model<Column> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 栏目编码：LM + 6位序列码
     */
    @ApiModelProperty(name = "code", value = "编码", position = 0, example="LM000001")
    private String code;
    /**
     * 栏目名称
     */
    @ApiModelProperty(name = "name", value = "栏目名称", position = 1, example="科萃")
    private String name;
    /**
     * 图标
     */
    @ApiModelProperty(name = "iconImg", value = "图标", position = 2, example="http://192.168.10.2/icon.jpg")
    private String icon;
    /**
     * 父级栏目
     */
    @ApiModelProperty(name = "pcode", value = "父级栏目CODE", position = 3)
    private String pcode;
    /**
     * 祖先栏目，以 , (逗号) 分隔
     */
    @ApiModelProperty(name = "pcodes", value = "祖先栏目CODE", position = 4)
    private String pcodes;
    /**
     * 状态
     */
    @ApiModelProperty(name = "status", value = "状态", position = 4)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Column{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", icon=" + icon +
        ", pcode=" + pcode +
        ", pcodes=" + pcodes +
        ", status=" + status +
        "}";
    }
}
