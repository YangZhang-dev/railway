package com.zzys.railway.framework.starter.distributedid.core.snowflake.chooseworkid;

import cn.hutool.core.date.SystemClock;
import com.zzys.railway.framework.starter.base.ApplicationContextHolder;
import com.zzys.railway.framework.starter.distributedid.config.DistributedIdAutoConfigurationProperties;
import com.zzys.railway.framework.starter.distributedid.core.snowflake.Snowflake;
import com.zzys.railway.framework.starter.distributedid.core.snowflake.WorkIdWrapper;
import com.zzys.railway.framework.starter.distributedid.toolkit.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author YangZhang
 * @createTime 2023/09/29/ 14:54
 */
@Slf4j
public abstract class AbstractWorkIdChooseTemplate {


    /**
     * 根据自定义策略获取 WorkId 生成器
     *
     * @return
     */
    protected abstract WorkIdWrapper chooseWorkId();

    /**
     * 选择 WorkId 并初始化雪花
     */
    public void chooseAndInit() {
        // 模板方法模式: 通过抽象方法获取 WorkId 包装器创建雪花算法
        WorkIdWrapper workIdWrapper = chooseWorkId();
        long workId = workIdWrapper.getWorkId();
        long dataCenterId = workIdWrapper.getDataCenterId();
        // TODO 验证是否生效
        DistributedIdAutoConfigurationProperties properties = ApplicationContextHolder.getBean(DistributedIdAutoConfigurationProperties.class);
        Snowflake snowflake = new Snowflake(workId, dataCenterId, properties.isUseSystemClock);
        log.info("Snowflake type: {}, workId: {}, dataCenterId: {}", this.getClass().getSimpleName(), workId, dataCenterId);
        SnowflakeIdUtil.initSnowflake(snowflake);
    }
}
