package com.zzys.railway.biz.userservice.toolkit;

import static com.zzys.railway.biz.userservice.common.constant.UserConstant.USER_REGISTER_REUSE_SHARDING_COUNT;

/**
 * 用户名可重复利用
 *
 * @author YangZhang
 * @createTime 2023/10/09/ 18:34
 */
public class UserReuseUtil {
    public static int hashShardingIdx(String username) {
        return Math.abs(username.hashCode() % USER_REGISTER_REUSE_SHARDING_COUNT);
    }
}
