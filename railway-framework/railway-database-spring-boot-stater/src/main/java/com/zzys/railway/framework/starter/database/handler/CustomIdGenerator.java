package com.zzys.railway.framework.starter.database.handler;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.zzys.railway.framework.starter.distributedid.toolkit.SnowflakeIdUtil;

/**
 * 自定义雪花算法id生成器
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 20:01
 */
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return SnowflakeIdUtil.nextId();
    }
}
