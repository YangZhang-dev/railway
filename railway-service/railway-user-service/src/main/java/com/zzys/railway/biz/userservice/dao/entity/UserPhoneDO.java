package com.zzys.railway.biz.userservice.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzys.railway.framework.starter.database.base.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户手机号实体
 *
 * @author YangZhang
 * @createTime 2023/10/09/ 19:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("t_user_phone")
public class UserPhoneDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
