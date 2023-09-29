package com.zzys.railway.framework.starter.log.config;

import com.zzys.railway.framework.starter.log.annotation.ILog;
import com.zzys.railway.framework.starter.log.core.ILogPrintAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志自动配置类
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 20:29
 */
@Configuration
public class LogAutoConfiguration {

    /**
     * {@link ILog} 日志打印 AOP 切面
     */
    @Bean
    public ILogPrintAspect iLogPrintAspect() {
        return new ILogPrintAspect();
    }
}
