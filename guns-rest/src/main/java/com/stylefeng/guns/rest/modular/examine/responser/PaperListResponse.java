package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/22 9:21
 * @Version 1.0
 */
@ApiModel(value = "PaperListResponse", description = "试卷列表")
public class PaperListResponse extends SimpleResponser {

    @ApiModelProperty(name = "data", value = "试卷集合")
    private Set<PaperResponse> data = new HashSet<PaperResponse>();

    public Set<PaperResponse> getData() {
        return data;
    }

    public void setData(Set<PaperResponse> data) {
        this.data = data;
    }

    public static Responser me(Set<PaperResponse> paperResponseList) {
        PaperListResponse responser = new PaperListResponse();

        responser.setCode(SUCCEED);
        responser.setMessage("查询成功");

        if (null != paperResponseList)
            responser.setData(paperResponseList);

        return responser;
    }
}
