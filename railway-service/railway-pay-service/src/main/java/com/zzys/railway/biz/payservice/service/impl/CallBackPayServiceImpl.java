package com.zzys.railway.biz.payservice.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zzys.railway.biz.payservice.common.enums.TradeStatusEnum;
import com.zzys.railway.biz.payservice.dao.entity.PayDO;
import com.zzys.railway.biz.payservice.dao.mapper.PayMapper;
import com.zzys.railway.biz.payservice.dto.domain.callback.PayCallbackRequest;
import com.zzys.railway.biz.payservice.dto.req.PayCallbackResponse;
import com.zzys.railway.biz.payservice.handler.strategy.AliPayCallbackHandler;
import com.zzys.railway.biz.payservice.mq.event.PayResultCallbackOrderEvent;
import com.zzys.railway.biz.payservice.mq.produce.PayResultCallbackOrderSendProduce;
import com.zzys.railway.biz.payservice.service.CallBackPayService;
import com.zzys.railway.framework.starter.common.utils.BeanUtil;
import com.zzys.railway.framework.starter.convention.exception.ServiceException;
import com.zzys.railway.framework.starter.designpattern.strategy.AbstractStrategyChoose;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author YangZhang
 * @createTime 2023/10/26/ 14:18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CallBackPayServiceImpl implements CallBackPayService {
    private final PayMapper payMapper;
    private final PayResultCallbackOrderSendProduce payResultCallbackOrderSendProduce;
    private final AbstractStrategyChoose abstractStrategyChoose;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void commonCallBack(PayCallbackRequest requestParam) {
        /**
         * {@link AliPayCallbackHandler}
         */
        PayCallbackResponse response = abstractStrategyChoose.chooseAndExecuteResp(requestParam.buildMark(), requestParam);
        LambdaQueryWrapper<PayDO> queryWrapper = Wrappers.lambdaQuery(PayDO.class)
                .eq(PayDO::getOrderSn, response.getOrderSn());
        PayDO payDO = payMapper.selectOne(queryWrapper);
        if (Objects.isNull(payDO)) {
            log.error("支付单不存在，orderRequestId：{}", response.getOrderRequestId());
            throw new ServiceException("支付单不存在");
        }
        payDO.setTradeNo(response.getTradeNo());
        payDO.setStatus(response.getStatus());
        payDO.setPayAmount(response.getPayAmount());
        payDO.setGmtPayment(response.getGmtPayment());
        LambdaUpdateWrapper<PayDO> updateWrapper = Wrappers.lambdaUpdate(PayDO.class)
                .eq(PayDO::getOrderSn, response.getOrderSn());
        int result = payMapper.update(payDO, updateWrapper);
        if (result <= 0) {
            log.error("修改支付单支付结果失败，支付单信息：{}", JSON.toJSONString(payDO));
            throw new ServiceException("修改支付单支付结果失败");
        }
        // 交易成功，回调订单服务告知支付结果，修改订单流转状态
        if (Objects.equals(response.getStatus(), TradeStatusEnum.TRADE_SUCCESS.tradeCode())) {
            payResultCallbackOrderSendProduce.sendMessage(BeanUtil.convert(payDO, PayResultCallbackOrderEvent.class));
        }
    }
}
