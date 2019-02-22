package com.stylefeng.guns.rest.modular.examine.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/2/20 01:42
 * @Version 1.0
 */
@ApiModel(value = "ExaminePaperDetailResponse", description = "试卷详情")
public class ExaminePaperDetailResponse extends SimpleResponser {

    @ApiModelProperty(name = "data", value = "试卷信息")
    private ExaminePaperDetail data;

    public static Responser me(ExaminePaperDetail examinePaperDetail) {
        ExaminePaperDetailResponse response = new ExaminePaperDetailResponse();

        response.setCode(SUCCEED);
        response.setMessage("查询成功");

        response.setData(examinePaperDetail);
        return response;
    }

    public ExaminePaperDetail getData() {
        return data;
    }

    public void setData(ExaminePaperDetail data) {
        this.data = data;
    }
}
