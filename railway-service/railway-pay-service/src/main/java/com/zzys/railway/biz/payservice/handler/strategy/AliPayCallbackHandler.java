
package com.zzys.railway.biz.payservice.handler.strategy;


import com.zzys.railway.biz.payservice.common.enums.PayChannelEnum;
import com.zzys.railway.biz.payservice.common.enums.TradeStatusEnum;
import com.zzys.railway.biz.payservice.dto.domain.callback.AliPayCallbackRequest;
import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackRequest;
import com.zzys.railway.biz.payservice.dto.req.PayCallbackResponse;
import com.zzys.railway.biz.payservice.handler.strategy.base.AbstractPayCallbackHandler;
import com.zzys.railway.framework.starter.designpattern.strategy.AbstractExecuteStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 阿里支付回调组件
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public final class AliPayCallbackHandler extends AbstractPayCallbackHandler implements AbstractExecuteStrategy<PayCallbackRequest, PayCallbackResponse> {


    @Override
    public PayCallbackResponse callback(PayCallbackRequest payCallbackRequest) {
        AliPayCallbackRequest aliPayCallBackRequest = payCallbackRequest.getAliPayCallBackRequest();
        return PayCallbackResponse.builder()
                .status(TradeStatusEnum.queryActualTradeStatusCode(aliPayCallBackRequest.getTradeStatus()))
                .payAmount(aliPayCallBackRequest.getBuyerPayAmount())
                .tradeNo(aliPayCallBackRequest.getTradeNo())
                .gmtPayment(aliPayCallBackRequest.getGmtPayment())
                .orderSn(aliPayCallBackRequest.getOrderRequestId())
                .build();
    }

    @Override
    public String mark() {
        return PayChannelEnum.ALI_PAY.name();
    }

    public PayCallbackResponse executeResp(PayCallbackRequest requestParam) {
        return callback(requestParam);
    }
}
