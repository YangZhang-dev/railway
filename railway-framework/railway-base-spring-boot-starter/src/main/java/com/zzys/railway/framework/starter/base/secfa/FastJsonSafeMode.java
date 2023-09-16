package com.zzys.railway.framework.starter.base.secfa;

import org.springframework.beans.factory.InitializingBean;

/**
 * 开启fastjson2安全模式
 *
 * @author YangZhang
 * @createTime 2023/09/16/ 20:23
 */
public class FastJsonSafeMode implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.setProperty("fastjson2.parser.safeMode", "true");
    }
}