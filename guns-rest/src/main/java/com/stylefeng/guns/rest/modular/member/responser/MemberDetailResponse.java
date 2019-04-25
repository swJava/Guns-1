package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * Created by 罗华.
 */
@ApiModel(value = "MemberDetailResponse", description = "用户详情")
public class MemberDetailResponse extends SimpleResponser {
    private static final long serialVersionUID = 9099816556172032827L;

    @ApiModelProperty(name = "data", value = "用户")
    private MemberExt data;

    public void setData(MemberExt data) {
        this.data = data;
    }

    public MemberExt getData(){
        return data;
    }

    public static MemberDetailResponse me(Member member) {
        MemberDetailResponse response = new MemberDetailResponse();
        response.setCode(SUCCEED);
        MemberExt memberExt = new MemberExt();
        BeanUtils.copyProperties(member, memberExt);
        response.setData(memberExt);

        return response;
    }

    public MemberDetailResponse addAvatar(String avatarUrl) {
        this.data.setAvatar(avatarUrl);
        return this;
    }
}
