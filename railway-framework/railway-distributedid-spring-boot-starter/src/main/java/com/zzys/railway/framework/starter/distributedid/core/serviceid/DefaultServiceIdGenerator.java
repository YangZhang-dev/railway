package com.zzys.railway.framework.starter.distributedid.core.serviceid;

import com.zzys.railway.framework.starter.distributedid.core.IdGenerator;
import com.zzys.railway.framework.starter.distributedid.core.snowflake.SnowflakeIdInfo;
import com.zzys.railway.framework.starter.distributedid.toolkit.SnowflakeIdUtil;

/**
 * 默认业务id生成器
 *
 * @author YangZhang
 * @createTime 2023/09/29/ 16:30
 */
public final class DefaultServiceIdGenerator implements ServiceIdGenerator {

    private final IdGenerator idGenerator;

    private long maxBizIdBitsLen;

    public DefaultServiceIdGenerator() {
        this(SEQUENCE_BIZ_BITS);
    }

    public DefaultServiceIdGenerator(long serviceIdBitLen) {
        idGenerator = SnowflakeIdUtil.getInstance();
        // 基因id二进制长度
        this.maxBizIdBitsLen = (long) Math.pow(2, serviceIdBitLen);
    }

    @Override
    public long nextId(long serviceId) {
        // 相同的整形变量的hashcode是相同的，有可能是负数且可能重复
        long id = Math.abs(Long.valueOf(serviceId).hashCode()) % (this.maxBizIdBitsLen);
        long nextId = idGenerator.nextId();
        return nextId | id;
    }

//    public static void main(String[] args) {
//        System.out.println(Long.valueOf(4611686021648613000L).hashCode()); -376
//        System.out.println(Long.valueOf(2305843010287436000L).hashCode()); 1610612960
//        System.out.println(Long.valueOf(1610612960).hashCode()); 1610612960
//    }
    @Override
    public String nextIdStr(long serviceId) {
        return Long.toString(nextId(serviceId));
    }

    @Override
    public SnowflakeIdInfo parseSnowflakeId(long snowflakeId) {
        SnowflakeIdInfo snowflakeIdInfo = SnowflakeIdInfo.builder()
                .workerId((int) ((snowflakeId >> WORKER_ID_SHIFT) & ~(-1L << WORKER_ID_BITS)))
                .dataCenterId((int) ((snowflakeId >> DATA_CENTER_ID_SHIFT) & ~(-1L << DATA_CENTER_ID_BITS)))
                .timestamp((snowflakeId >> TIMESTAMP_LEFT_SHIFT) + DEFAULT_TWEPOCH)
                .sequence((int) ((snowflakeId >> SEQUENCE_BIZ_BITS) & ~(-1L << SEQUENCE_ACTUAL_BITS)))
                .gene((int) (snowflakeId & ~(-1L << SEQUENCE_BIZ_BITS)))
                .build();
        return snowflakeIdInfo;
    }

    /**
     * 工作 ID 5 bit
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * 数据中心 ID 5 bit
     */
    private static final long DATA_CENTER_ID_BITS = 5L;

    /**
     * 序列号 12 位，表示只允许 workerId 的范围为 0-4095
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 真实序列号 bit
     */
    private static final long SEQUENCE_ACTUAL_BITS = 8L;

    /**
     * 基因 bit
     */
    private static final long SEQUENCE_BIZ_BITS = 4L;

    /**
     * 机器节点左移12位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 默认开始时间
     */
    private static long DEFAULT_TWEPOCH = 1288834974657L;

    /**
     * 数据中心节点左移 17 位
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间毫秒数左移 22 位
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
}
