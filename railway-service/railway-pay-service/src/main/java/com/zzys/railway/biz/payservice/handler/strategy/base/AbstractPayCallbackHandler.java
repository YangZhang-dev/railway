
package com.zzys.railway.biz.payservice.handler.strategy.base;


import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackRequest;
import com.zzys.railway.biz.payservice.dto.req.PayCallbackResponse;

/**
 * 抽象支付回调组件
 *
 */
public abstract class AbstractPayCallbackHandler {

    /**
     * 支付回调抽象接口
     *
     * @param payCallbackRequest 支付回调请求参数
     */
    public abstract PayCallbackResponse callback(PayCallbackRequest payCallbackRequest);
}
