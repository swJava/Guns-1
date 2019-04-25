package com.stylefeng.guns.task.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目相关配置
 *
 * @author fengshuonan
 * @date 2017年10月23日16:44:15
 */
@Configuration
@ConfigurationProperties(prefix = TaskProperties.REST_PREFIX)
public class TaskProperties {

    public static final String REST_PREFIX = "application";
}
