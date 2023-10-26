package com.zzys.railway.biz.payservice.service;

import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackRequest;

public interface CallBackPayService {
    /**
     * 支付单回调
     *
     * @param requestParam 回调支付单实体
     */
    void commonCallBack(PayCallbackRequest requestParam);
}
