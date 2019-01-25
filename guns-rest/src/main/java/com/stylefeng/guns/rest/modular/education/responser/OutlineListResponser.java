package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 2:13
 * @Version 1.0
 */
@ApiModel(value = "OutlineListResponser", description = "课时列表")
public class OutlineListResponser extends SimpleResponser {

    @ApiModelProperty(name = "adjustTimes", value = "剩余可调课次数")
    private Integer adjustTimes = 0;

    @ApiModelProperty(name = "data", value = "课时集合")
    private List<OutlineResponse> data = new ArrayList<OutlineResponse>();

    public Integer getAdjustTimes() {
        return adjustTimes;
    }

    public void setAdjustTimes(Integer adjustTimes) {
        this.adjustTimes = adjustTimes;
    }

    public List<OutlineResponse> getData() {
        return data;
    }

    public void setData(List<OutlineResponse> data) {
        this.data = data;
    }

    public static Responser me(int remain, List<OutlineResponse> outlineResponseList) {
        OutlineListResponser response = new OutlineListResponser();

        response.setAdjustTimes(remain < 0 ? 0 : remain);
        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(outlineResponseList);
        return response;
    }
}
