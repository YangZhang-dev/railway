package com.zzys.railway.framework.starter.distributedid.core.serviceid;

import com.zzys.railway.framework.starter.distributedid.core.IdGenerator;
import com.zzys.railway.framework.starter.distributedid.toolkit.SnowflakeIdUtil;

/**
 * 默认业务id生成器
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 16:30
 */
public final class DefaultServiceIdGenerator implements ServiceIdGenerator {

    /**
     * 用于取余的基因位数
     */
    private static final int SEQUENCE_BIT = 6;

    private final IdGenerator idGenerator;

    private final int maxBizIdBitsLen;

    public DefaultServiceIdGenerator() {
        this(SEQUENCE_BIT);
    }

    public DefaultServiceIdGenerator(int serviceIdBitLen) {
        idGenerator = SnowflakeIdUtil.getInstance();
        // 基因id二进制长度
        this.maxBizIdBitsLen = serviceIdBitLen;
    }
    @Override
    public String nextIdStr(String serviceId) {
        return idGenerator.nextIdStr()+serviceId.substring(serviceId.length()-maxBizIdBitsLen);
    }

    // 由于基因的位数改变为了十进制，所以该方法暂时废弃
//    @Override
//    public SnowflakeIdInfo parseSnowflakeId(long snowflakeId) {
//        SnowflakeIdInfo snowflakeIdInfo = SnowflakeIdInfo.builder()
//                .workerId((int) ((snowflakeId >> WORKER_ID_SHIFT) & ~(-1L << WORKER_ID_BITS)))
//                .dataCenterId((int) ((snowflakeId >> DATA_CENTER_ID_SHIFT) & ~(-1L << DATA_CENTER_ID_BITS)))
//                .timestamp((snowflakeId >> TIMESTAMP_LEFT_SHIFT) + DEFAULT_TWEPOCH)
//                .sequence((int) ((snowflakeId >> SEQUENCE_BIZ_BITS) & ~(-1L << SEQUENCE_ACTUAL_BITS)))
//                .gene((int) (snowflakeId & ~(-1L << SEQUENCE_BIZ_BITS)))
//                .build();
//        return snowflakeIdInfo;
//    }
}
