package com.zzys.railway.framework.starter.base.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * base配置元数据项
 *
 * @author YangZhang
 * @createTime 2023/09/16/ 21:23
 */
@Data
@ConfigurationProperties(prefix = ApplicationBaseAutoConfigurationProperties.PREFIX)
public class ApplicationBaseAutoConfigurationProperties {
    public static final String PREFIX = "framework.base";
    @Autowired
    private Fastjson fastjson;

    @Data
    @Component
    @ConfigurationProperties("framework.base.fastjson.safa-mode")
    public static class Fastjson {
        private Boolean safeMode;
    }

}
