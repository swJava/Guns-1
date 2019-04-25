package com.stylefeng.guns.rest.modular.education.responser;

import com.stylefeng.guns.modular.system.model.CourseOutline;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import sun.java2d.pipe.OutlineTextRenderer;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/17 2:12
 * @Version 1.0
 */
@ApiModel(value = "OutlineResponse", description = "课时")
public class OutlineResponse extends CourseOutline {

    /**
     * 能否调课s
     */
    @ApiModelProperty(name = "canAdjust", value = "能否调整", example = "true")
    private boolean canAdjust;

    public boolean isCanAdjust() {
        return canAdjust;
    }

    public void setCanAdjust(boolean canAdjust) {
        this.canAdjust = canAdjust;
    }

    public static OutlineResponse me(CourseOutline outline) {
        OutlineResponse outlineResponse = new OutlineResponse();

        BeanUtils.copyProperties(outline, outlineResponse);

        return outlineResponse;
    }
}
