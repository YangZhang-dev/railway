
package com.zzys.railway.biz.payservice.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import com.zzys.railway.biz.payservice.common.enums.PayChannelEnum;
import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackCommand;
import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackRequest;
import com.zzys.railway.biz.payservice.handler.convert.PayCallbackRequestConvert;
import com.zzys.railway.biz.payservice.service.CallBackPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 支付结果回调
 *
 */
@RestController
@RequiredArgsConstructor
public class PayCallbackController {

    private CallBackPayService callBackPayService;
    /**
     * 支付宝回调
     * 调用支付宝支付后，支付宝会调用此接口发送支付结果
     */
    @PostMapping("/api/pay-service/callback/alipay")
    public void callbackAlipay(@RequestParam Map<String, Object> requestParam) {
        PayCallbackCommand payCallbackCommand = BeanUtil.mapToBean(requestParam, PayCallbackCommand.class, true, CopyOptions.create());
        payCallbackCommand.setChannel(PayChannelEnum.ALI_PAY.getCode());
        payCallbackCommand.setOrderRequestId(requestParam.get("out_trade_no").toString());
        payCallbackCommand.setGmtPayment(DateUtil.parse(requestParam.get("gmt_payment").toString()));
        PayCallbackRequest payCallbackRequest = PayCallbackRequestConvert.command2PayCallbackRequest(payCallbackCommand);
        callBackPayService.commonCallBack(payCallbackRequest);
    }
}
