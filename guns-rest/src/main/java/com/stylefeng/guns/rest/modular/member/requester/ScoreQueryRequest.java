package com.stylefeng.guns.rest.modular.member.requester;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.core.PageRequester;
import com.stylefeng.guns.rest.core.SimpleRequester;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/16 14:21
 * @Version 1.0
 */
@ApiModel(value = "ScoreQueryRequest", description = "查分")
public class ScoreQueryRequest extends SimpleRequester implements PageRequester {

    @ApiModelProperty(name = "keyword", value = "关键字", required = false, position = 1, example = "小明")
    private String keyword;

    @ApiModelProperty(name = "student", value = "学员关键字", required = false, position = 2, example = "小明")
    private String student;

    @ApiModelProperty(name = "examine", value = "考试名称（模糊）", required = false, position = 3, example = "期末考试")
    private String examine;

    @ApiModelProperty(name = "round", value = "场次", required = false, position = 4, example = "第一场")
    private String round;

    @ApiModelProperty(name = "page", value = "当前页数， 页数以1 开始", required = false, position = 5, example = "1")
    private Integer page = 1;

    @ApiModelProperty(name = "pageSize", value = "每页数据数", required = false, position = 6, example = "10")
    private Integer pageSize = 10;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean checkValidate() {
        return false;
    }

    @Override
    public Page getPage() {
        Page page = new Page(this.page, pageSize);
        page.setOpenSort(false);
        return page;
    }
}
