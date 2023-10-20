
package com.zzys.railway.biz.userservice.service.handler.filter.user;


import com.zzys.railway.biz.userservice.common.enums.UserChainMarkEnum;
import com.zzys.railway.biz.userservice.dto.req.UserRegisterReqDTO;
import com.zzys.railway.framework.starter.designpattern.chain.AbstractChainHandler;

/**
 * 用户注册责任链过滤器
 *
 * @公众号：马丁玩编程，回复：加群，添加马哥微信（备注：12306）获取项目资料
 */
public interface UserRegisterCreateChainFilter<T extends UserRegisterReqDTO> extends AbstractChainHandler<UserRegisterReqDTO> {

    @Override
    default String mark() {
        return UserChainMarkEnum.USER_REGISTER_FILTER.name();
    }
}
