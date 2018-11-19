package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "MemberDetailResponse", description = "用户详情")
public class MemberDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = 9099816556172032827L;

    @ApiModelProperty(name = "data", value = "用户")
    private Member data;

    public Member getData(){
        return data;
    }
}
