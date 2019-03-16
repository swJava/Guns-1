package com.stylefeng.guns.rest.modular.member.responser;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.Score;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/16 15:05
 * @Version 1.0
 */
@ApiModel(value = "ScorePageResponse", description = "查分详情")
public class ScorePageResponse extends SimpleResponser {
    @ApiModelProperty(name = "totalRecord", value = "记录总数", example = "100")
    private Long totalRecord;

    @ApiModelProperty(name = "data", value = "总页数", example = "10")
    private Long totalPage;

    @ApiModelProperty(name = "data", value = "当前页数", example = "1")
    private Integer page = 1;

    @ApiModelProperty(name = "data", value = "每页记录数", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(name = "data", value = "查分结果")
    private List<ScoreResponse> datas = new ArrayList<ScoreResponse>();

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPage() {
        return page;
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

    public List<ScoreResponse> getDatas() {
        return datas;
    }

    public void setDatas(List<ScoreResponse> datas) {
        this.datas = datas;
    }

    public static ScorePageResponse me(List<ScoreResponse> scoreResponseList, Page<Score> scorePage) {
        ScorePageResponse response = new ScorePageResponse();
        response.setCode(SUCCEED);
        response.setMessage("查询成功");
        response.setDatas(scoreResponseList);

        response.setTotalPage(scorePage.getPages());
        response.setPage(scorePage.getCurrent());
        response.setPageSize(scorePage.getSize());
        response.setTotalRecord(scorePage.getTotal());
        return response;

    }
}
