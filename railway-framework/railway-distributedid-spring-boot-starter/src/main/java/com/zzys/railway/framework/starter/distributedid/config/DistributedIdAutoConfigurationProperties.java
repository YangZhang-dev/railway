package com.zzys.railway.framework.starter.distributedid.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 分布式id自动配置元数据
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 19:38
 */
@Data
@ConfigurationProperties(prefix = DistributedIdAutoConfigurationProperties.PREFIX)
public class DistributedIdAutoConfigurationProperties {
    public static final String PREFIX = "framework.distributed.id.snowflake";
    public boolean isUseSystemClock = false;
}
