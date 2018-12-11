package com.stylefeng.guns;

import com.stylefeng.guns.GunsRestApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Guns REST Web程序启动类
 *
 * @author fengshuonan
 * @date 2017年9月29日09:00:42
 */
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
//@EnableScheduling
//@SpringBootApplication(exclude = { RedisAutoConfiguration.class, TransactionAutoConfiguration.class, JmxAutoConfiguration.class })
public class GunsRestServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GunsRestApplication.class);
    }

}
