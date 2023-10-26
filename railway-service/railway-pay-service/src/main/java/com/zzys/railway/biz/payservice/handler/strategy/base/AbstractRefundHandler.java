

package com.zzys.railway.biz.payservice.handler.strategy.base;


import com.zzys.railway.biz.payservice.dto.domain.refund.RefundRequest;
import com.zzys.railway.biz.payservice.dto.domain.refund.RefundResponse;

/**
 * 抽象退款组件
 *
 */
public abstract class AbstractRefundHandler {

    /**
     * 支付退款接口
     *
     * @param payRequest 退款请求参数
     * @return 退款响应参数
     */
    public abstract RefundResponse refund(RefundRequest payRequest);
}
