package com.stylefeng.guns.rest.core;

import java.io.Serializable;

/**
 * 结果
 *
 * Created by 罗华.
 */
public interface Responser extends Serializable{

    /**
     * 是否成功
     *
     * @return
     */
    boolean isSuccess();
}
