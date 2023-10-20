package com.zzys.railway.biz.userservice.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzys.railway.framework.starter.database.base.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户复用实体
 *
 * @author YangZhang
 * @createTime 2023/10/09/ 19:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_reuse")
public final class UserReuseDO extends BaseDO {

    /**
     * 用户名
     */
    private String username;
}
