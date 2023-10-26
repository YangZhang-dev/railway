
package com.zzys.railway.biz.payservice.controller;


import com.zzys.railway.biz.payservice.dto.req.RefundReqDTO;
import com.zzys.railway.biz.payservice.service.RefundService;
import com.zzys.railway.framework.starter.convention.result.Result;
import com.zzys.railway.framework.starter.web.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 退款控制层
 *
 */
@RestController
@RequiredArgsConstructor
public class RefundController {

    private final RefundService refundService;

    /**
     * 公共退款接口
     */
    @PostMapping("/api/pay-service/common/refund")
    public Result<Void> commonRefund(@RequestBody RefundReqDTO requestParam) {
        refundService.commonRefund(requestParam);
        return Results.success();
    }
}
