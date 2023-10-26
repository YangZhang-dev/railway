
package com.zzys.railway.biz.payservice.handler.strategy.base;


import com.zzys.railway.biz.payservice.dto.domain.pay.PayRequest;
import com.zzys.railway.biz.payservice.dto.domain.pay.PayResponse;

/**
 * 抽象支付组件
 *
 */
public abstract class AbstractPayHandler {

    /**
     * 支付抽象接口
     *
     * @param payRequest 支付请求参数
     * @return 支付响应参数
     */
    public abstract PayResponse pay(PayRequest payRequest);
}
