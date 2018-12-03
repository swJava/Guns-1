package com.stylefeng.guns.modular.examine;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/30 19:50
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(DefaultClassExamStrategyProperties.class)
public class DefaultClassExamStrategyConfig {

    @Bean(name = "defaultClassExamStrategy")
    @Scope("prototype")
    public DefaultClassExamStrategy defaultClassExamStrategy(DefaultClassExamStrategyProperties config) {
        DefaultClassExamStrategy strategy = new DefaultClassExamStrategy();
        strategy.setConfig(config);
        return strategy;
    }
}
