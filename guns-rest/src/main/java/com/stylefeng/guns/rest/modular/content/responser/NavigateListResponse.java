package com.stylefeng.guns.rest.modular.content.responser;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.modular.system.model.Column;
import com.stylefeng.guns.modular.system.model.ColumnAction;
import com.stylefeng.guns.rest.core.SimpleResponser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 罗华.
 */
@ApiModel(value = "NavigateListResponse", description = "导航栏目列表")
public class NavigateListResponse extends SimpleResponser {
    private static final long serialVersionUID = 6603654982583000301L;

    @ApiModelProperty(name = "data", value = "导航栏目集合")
    private List<Navigate> data;

    public List<Navigate> getData() {
        return data;
    }

    public NavigateListResponse(){
        this.data = new ArrayList<Navigate>();
    }

    public static NavigateListResponse me() {
        NavigateListResponse response = new NavigateListResponse();
        response.setCode(SUCCEED);
        response.setMessage("查询成功s");
        return response;
    }

    public void addNavigate(Column nav, ColumnAction action) {
        this.data.add(Navigate.me(nav, action));
    }

    @ApiModel
    public static class Navigate {

        @ApiModelProperty(name = "code", value = "栏目编码")
        private String code;

        @ApiModelProperty(name = "name", value = "栏目名称")
        private String name;

        @ApiModelProperty(name = "icon", value = "图标")
        private String icon;

        @ApiModelProperty(name = "action", value = "动作")
        private String action;

        @ApiModelProperty(name = "parameter", value = "动作参数")
        private Object parameter;

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

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public Object getParameter() {
            return parameter;
        }

        public void setParameter(Object parameter) {
            this.parameter = parameter;
        }

        public static Navigate me(Column nav, ColumnAction action) {
            Navigate navigate = new Navigate();
            navigate.setCode(nav.getCode());
            navigate.setName(nav.getName());
            navigate.setIcon(nav.getIcon());
            if (null != action) {
                navigate.setAction(action.getAction());
                navigate.setParameter(JSON.parseObject(action.getData()));
            }
            return navigate;
        }
    }
}
