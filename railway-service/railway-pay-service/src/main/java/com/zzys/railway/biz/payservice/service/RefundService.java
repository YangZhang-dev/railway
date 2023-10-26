
package com.zzys.railway.biz.payservice.service;

import com.zzys.railway.biz.payservice.dto.req.RefundReqDTO;

/**
 * 退款接口层
 *
 */
public interface RefundService {

    /**
     * 公共退款接口
     *
     * @param requestParam 退款请求参数
     * @return 退款返回详情
     */
    void commonRefund(RefundReqDTO requestParam);
}
