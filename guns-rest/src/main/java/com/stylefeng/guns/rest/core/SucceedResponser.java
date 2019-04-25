package com.stylefeng.guns.rest.core;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/14 16:53
 * @Version 1.0
 */
public class SucceedResponser extends SimpleResponser {

    private static final long serialVersionUID = 9088245138562197549L;
    
    private Object data;

    public SucceedResponser(){
        super.setCode(SUCCEED);
        super.setMessage("请求成功");
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Responser response(Object data) {
        SucceedResponser responser = new SucceedResponser();
        responser.setData(data);

        return responser;
    }
}
