package com.stylefeng.guns.rest.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * Created by hh on 2018/8/21.
 */
public class HikariCPWrapper extends HikariConfig implements InitializingBean {
    @Autowired
    private DataSourceProperties basicProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO

        System.out.println("ok");
    }
}
