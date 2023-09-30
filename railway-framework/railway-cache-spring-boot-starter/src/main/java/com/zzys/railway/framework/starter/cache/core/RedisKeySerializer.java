package com.zzys.railway.framework.starter.cache.core;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * Redis序列化
 *
 * @author YangZhang
 * @createTime 2023/10/01/ 00:13
 */
@RequiredArgsConstructor
public class RedisKeySerializer implements InitializingBean, RedisSerializer<String> {

    private final String keyPrefix;

    private final String charsetName;

    private Charset charset;

    @Override
    public byte[] serialize(String key) throws SerializationException {
        String builderKey = keyPrefix + key;
        return builderKey.getBytes();
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return new String(bytes, charset);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        charset = Charset.forName(charsetName);
    }
}
