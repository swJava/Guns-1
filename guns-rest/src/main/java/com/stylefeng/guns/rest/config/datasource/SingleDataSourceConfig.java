package com.stylefeng.guns.rest.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.stylefeng.guns.core.config.properties.DruidProperties;
import com.stylefeng.guns.core.datascope.DataScopeInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 多数据源配置
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@ConditionalOnProperty(prefix = "guns.muti-datasource", name = "open", havingValue = "false", matchIfMissing = true)
@EnableTransactionManagement
@MapperScan(basePackages = {"com.stylefeng.guns.modular.*.dao"})
public class SingleDataSourceConfig {

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfig() {
        return new HikariCPWrapper();
    }

    @Primary
    @Bean
    public DataSource dataSourceMaster(HikariConfig config) {
        return new HikariDataSource(config);
    }
//    @Bean
//    public DruidDataSource dataSource(DruidProperties druidProperties) {
//        DruidDataSource dataSource = new DruidDataSource();
//        druidProperties.config(dataSource);
//        return dataSource;
//    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}

