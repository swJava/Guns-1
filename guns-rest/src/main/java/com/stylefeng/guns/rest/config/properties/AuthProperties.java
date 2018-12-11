package com.stylefeng.guns.rest.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017-08-23 9:23
 */
@Configuration
@ConfigurationProperties(prefix = AuthProperties.AUTH_PREFIX)
public class AuthProperties {

    public static final String AUTH_PREFIX = "application.auth";

    private boolean enable = false;

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 604800L;

    private String path = "auth";

    private String md5Key = "randomKey";

    private List<String> excludePattern = new ArrayList<String>();

    public static String getAuthPrefix() {
        return AUTH_PREFIX;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public List<String> getExcludePattern() {
        return excludePattern;
    }

    public void setExcludePattern(List<String> excludePattern) {
        this.excludePattern = excludePattern;
    }
}
