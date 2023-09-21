package com.zzys.railway.framework.starter.user.config;

import com.zzys.railway.framework.starter.user.core.UserTransmitFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.zzys.railway.framework.starter.base.constant.FilterOrderConstant.USER_TRANSMIT_FILTER_ORDER;

/**
 * 用户自动配置类
 *
 * @author YangZhang
 * @createTime 2023/09/21/ 17:21
 */
@Configuration
@EnableConfigurationProperties(UserAutoConfigurationProperties.class)
@ConditionalOnWebApplication
public class UserAutoConfiguration {
    /**
     * 用户信息传递过滤器
     */
    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter() {
        FilterRegistrationBean<UserTransmitFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserTransmitFilter());
        registration.addUrlPatterns("/*");
        // 注意该过滤器位于比较靠后的位置
        registration.setOrder(USER_TRANSMIT_FILTER_ORDER);
        return registration;
    }
}
