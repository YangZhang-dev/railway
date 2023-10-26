
package com.zzys.railway.biz.payservice.controller;


import com.zzys.railway.biz.payservice.dto.domain.pay.PayCommand;
import com.zzys.railway.biz.payservice.dto.domain.pay.PayRequest;
import com.zzys.railway.biz.payservice.dto.resp.PayInfoRespDTO;
import com.zzys.railway.biz.payservice.dto.resp.PayRespDTO;
import com.zzys.railway.biz.payservice.handler.convert.PayRequestConvert;
import com.zzys.railway.biz.payservice.service.PayService;
import com.zzys.railway.framework.starter.convention.result.Result;
import com.zzys.railway.framework.starter.web.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 支付控制层
 *
 */
@RestController
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    /**
     * 公共支付接口
     * 对接常用支付方式，比如：支付宝、微信以及银行卡等
     */
    @PostMapping("/api/pay-service/pay/create")
    public Result<PayRespDTO> pay(@RequestBody PayCommand requestParam) {
        PayRequest payRequest = PayRequestConvert.command2PayRequest(requestParam);
        return Results.success(payService.commonPay(payRequest));
    }

    /**
     * 跟据订单号查询支付单详情
     */
    @GetMapping("/api/pay-service/pay/query/order-sn")
    public Result<PayInfoRespDTO> getPayInfoByOrderSn(@RequestParam(value = "orderSn") String orderSn) {
        return Results.success(payService.getPayInfoByOrderSn(orderSn));
    }

    /**
     * 跟据支付流水号查询支付单详情
     */
    @GetMapping("/api/pay-service/pay/query/pay-sn")
    public Result<PayInfoRespDTO> getPayInfoByPaySn(@RequestParam(value = "paySn") String paySn) {
        return Results.success(payService.getPayInfoByPaySn(paySn));
    }
}
