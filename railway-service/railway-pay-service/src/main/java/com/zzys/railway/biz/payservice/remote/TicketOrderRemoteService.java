
package com.zzys.railway.biz.payservice.remote;


import com.zzys.railway.biz.payservice.remote.dto.TicketOrderDetailRespDTO;
import com.zzys.railway.framework.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 车票订单远程服务调用
 *
 */
@FeignClient(value = "railway-order-service")
public interface TicketOrderRemoteService {

    /**
     * 跟据订单号查询车票订单
     *
     * @param orderSn 列车订单号
     * @return 列车订单记录
     */
    @GetMapping("/api/order-service/order/ticket/query")
    Result<TicketOrderDetailRespDTO> queryTicketOrderByOrderSn(@RequestParam(value = "orderSn") String orderSn);
}
