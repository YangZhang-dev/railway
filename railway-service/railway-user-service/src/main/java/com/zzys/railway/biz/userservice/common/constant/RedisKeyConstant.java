package com.zzys.railway.biz.userservice.common.constant;

/**
 * Redis前缀
 *
 * @author YangZhang
 * @createTime 2023/10/09/ 18:33
 */
public final class RedisKeyConstant {

    /**
     * 用户注册锁，Key Prefix + 用户名
     */
    public static final String LOCK_USER_REGISTER = "railway-user-service:lock:user-register:";

    /**
     * 用户注销锁，Key Prefix + 用户名
     */
    public static final String USER_DELETION = "railway-user-service:user-deletion:";

    /**
     * 用户注册可复用用户名分片，Key Prefix + Idx
     */
    public static final String USER_REGISTER_REUSE_SHARDING = "railway-user-service:user-reuse:";

    /**
     * 用户乘车人列表，Key Prefix + 用户名
     */
    public static final String USER_PASSENGER_LIST = "railway-user-service:user-passenger-list:";
}

