package com.stylefeng.guns.rest.config;

import com.stylefeng.guns.modular.payMGR.PayRequestBuilderFactory;
import com.stylefeng.guns.rest.config.properties.PayProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/1/20 14:59
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(PayProperties.class)
public class PayConfig {

    @Bean
    @ConditionalOnProperty(prefix = "application.pay.mock", name = "enable", havingValue = "false", matchIfMissing = false)
    public PayRequestBuilderFactory createPayRequestBuildFactory(PayProperties payProperties){

        PayRequestBuilderFactory payRequestBuilderFactory = new PayRequestBuilderFactory();
        payRequestBuilderFactory.setWeixinProperties(payProperties.getWeixinProperties());
        payRequestBuilderFactory.setUnionProperties(payProperties.getUnionProperties());

        return payRequestBuilderFactory;
    }
}
