package com.zzys.railway.framework.starter.designpattern.config;

import com.zzys.railway.framework.starter.designpattern.chain.AbstractChainContext;
import com.zzys.railway.framework.starter.designpattern.strategy.AbstractStrategyChoose;
import com.zzys.railway.framework.starter.base.config.ApplicationBaseAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 设计模式自动装配
 *
 * @author YangZhang
 * @createTime 2023/09/23/ 11:22
 */
@ImportAutoConfiguration(ApplicationBaseAutoConfiguration.class)
public class DesignPatternAutoConfiguration {

    /**
     * 策略模式选择器
     */
    @Bean
    public AbstractStrategyChoose abstractStrategyChoose() {
        return new AbstractStrategyChoose();
    }

    /**
     * 责任链上下文
     */
    @Bean
    public AbstractChainContext abstractChainContext() {
        return new AbstractChainContext();
    }
}
