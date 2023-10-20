package com.zzys.railway.biz.userservice.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzys.railway.framework.starter.database.base.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注销实体
 *
 * @author YangZhang
 * @createTime 2023/10/09/ 19:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user_deletion")
public class UserDeletionDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号
     */
    private String idCard;
}
