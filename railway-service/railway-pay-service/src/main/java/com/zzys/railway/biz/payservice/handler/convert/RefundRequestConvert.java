
package com.zzys.railway.biz.payservice.handler.convert;


import com.zzys.railway.biz.payservice.common.enums.PayChannelEnum;
import com.zzys.railway.biz.payservice.dto.domain.refund.AliRefundRequest;
import com.zzys.railway.biz.payservice.dto.domain.refund.RefundCommand;
import com.zzys.railway.biz.payservice.dto.domain.refund.RefundRequest;
import com.zzys.railway.framework.starter.common.utils.BeanUtil;

import java.util.Objects;

/**
 * 退款请求入参转换器
 *
 */
public final class RefundRequestConvert {

    /**
     * {@link RefundCommand} to {@link RefundRequest}
     *
     * @param refundCommand 退款请求参数
     * @return {@link RefundRequest}
     */
    public static RefundRequest command2RefundRequest(RefundCommand refundCommand) {
        RefundRequest refundRequest = null;
        if (Objects.equals(refundCommand.getChannel(), PayChannelEnum.ALI_PAY.getCode())) {
            refundRequest = BeanUtil.convert(refundCommand, AliRefundRequest.class);
        }
        return refundRequest;
    }
}
