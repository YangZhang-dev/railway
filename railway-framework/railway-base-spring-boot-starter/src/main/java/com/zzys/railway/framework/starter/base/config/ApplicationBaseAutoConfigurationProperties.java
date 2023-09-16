package com.zzys.railway.framework.starter.base.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * base配置元数据项
 *
 * @author YangZhang
 * @createTime 2023/09/16/ 21:23
 */
@Data
@ConfigurationProperties(prefix = ApplicationBaseAutoConfigurationProperties.PREFIX)
public class ApplicationBaseAutoConfigurationProperties {
    public static final String PREFIX = "framework.base.fastjson.safa-mode";
}
