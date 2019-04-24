package com.stylefeng.guns.rest.core;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/16 15:19
 * @Version 1.0
 */
public interface PageRequester extends Requester {

    Page getPage();
}
