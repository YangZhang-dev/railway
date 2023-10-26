
package com.zzys.railway.biz.payservice.dto.domain.refund;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象退款入参实体
 *
 */
@Getter
@Setter
public abstract class AbstractRefundRequest implements RefundRequest {

    /**
     * 交易环境，H5、小程序、网站等
     */

    private Integer tradeType;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 支付渠道
     */
    private Integer channel;

    @Override
    public AliRefundRequest getAliRefundRequest() {
        return null;
    }

    @Override
    public String buildMark() {
        return null;
    }
}
