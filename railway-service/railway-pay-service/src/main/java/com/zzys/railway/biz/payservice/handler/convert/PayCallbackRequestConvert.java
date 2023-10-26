
package com.zzys.railway.biz.payservice.handler.convert;


import com.zzys.railway.biz.payservice.common.enums.PayChannelEnum;
import com.zzys.railway.biz.payservice.dto.domain.callback.AliPayCallbackRequest;
import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackCommand;
import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackRequest;
import com.zzys.railway.framework.starter.common.utils.BeanUtil;

import java.util.Objects;

/**
 * 支付回调请求入参转换器
 *
 */
public final class PayCallbackRequestConvert {

    /**
     * {@link PayCallbackCommand} to {@link PayCallbackRequest}
     *
     * @param payCallbackCommand 支付回调请求参数
     * @return {@link PayCallbackRequest}
     */
    public static PayCallbackRequest command2PayCallbackRequest(PayCallbackCommand payCallbackCommand) {
        PayCallbackRequest payCallbackRequest = null;
        if (Objects.equals(payCallbackCommand.getChannel(), PayChannelEnum.ALI_PAY.getCode())) {
            payCallbackRequest = BeanUtil.convert(payCallbackCommand, AliPayCallbackRequest.class);
            ((AliPayCallbackRequest) payCallbackRequest).setOrderRequestId(payCallbackCommand.getOrderRequestId());
        }
        return payCallbackRequest;
    }
}
