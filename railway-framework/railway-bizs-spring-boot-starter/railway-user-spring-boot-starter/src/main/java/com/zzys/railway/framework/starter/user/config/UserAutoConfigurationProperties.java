package com.zzys.railway.framework.starter.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.time.temporal.ChronoUnit;

/**
 * 用户自动配置元数据
 *
 * @author YangZhang
 * @createTime 2023/09/21/ 17:28
 */
@Data
@ConfigurationProperties(prefix = UserAutoConfigurationProperties.PREFIX)
public class UserAutoConfigurationProperties {
    public static final String PREFIX = "framework.user.jwt";
    @DurationUnit(ChronoUnit.SECONDS)
    private Long EXPIRATION = 86400L;
    private String TOKEN_PREFIX = "Bearer ";
    private String ISS = "railway";
    private String SECRET = "SecretKey039245678901232039487623456783092349288901402967890140939827";
}
