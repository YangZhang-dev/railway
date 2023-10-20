package com.zzys.railway.biz.userservice.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzys.railway.framework.starter.database.base.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户邮箱实体
 *
 * @author YangZhang
 * @createTime 2023/10/09/ 19:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("t_user_mail")
public class UserMailDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱号
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
