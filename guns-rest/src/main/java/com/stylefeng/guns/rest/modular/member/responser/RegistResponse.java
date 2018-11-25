package com.stylefeng.guns.rest.modular.member.responser;

import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 罗华.
 */
@ApiModel(value = "RegistResponse", description = "用户注册返回")
public class RegistResponse extends SimpleResponser {
    private static final long serialVersionUID = -7099321165688178138L;

    @ApiModelProperty(name = "data", value = "注册返回")
    private Data data;

    public static RegistResponse me(Member member){
        RegistResponse response = new RegistResponse();

        Data data = new Data();

        data.setUserName(member.getUserName());

        response.setCode(SUCCEED);
        response.setMessage("注册成功");
        response.setData(data);

        return response;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData(){
        return data;
    }


    @ApiModel()
    static class Data {

        @ApiModelProperty(value = "用户名", example = "18580255110")
        private String userName;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
