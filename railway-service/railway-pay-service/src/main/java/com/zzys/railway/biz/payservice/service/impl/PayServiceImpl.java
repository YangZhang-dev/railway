
package com.zzys.railway.biz.payservice.service.impl;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zzys.railway.biz.payservice.common.enums.TradeStatusEnum;
import com.zzys.railway.biz.payservice.dao.entity.PayDO;
import com.zzys.railway.biz.payservice.dao.mapper.PayMapper;
import com.zzys.railway.biz.payservice.dto.domain.pay.PayRequest;
import com.zzys.railway.biz.payservice.dto.domain.pay.PayResponse;
import com.zzys.railway.biz.payservice.dto.resp.PayInfoRespDTO;
import com.zzys.railway.biz.payservice.dto.resp.PayRespDTO;
import com.zzys.railway.biz.payservice.handler.strategy.AliPayNativeHandler;
import com.zzys.railway.biz.payservice.service.PayService;
import com.zzys.railway.framework.starter.common.utils.BeanUtil;
import com.zzys.railway.framework.starter.convention.exception.ServiceException;
import com.zzys.railway.framework.starter.designpattern.strategy.AbstractStrategyChoose;
import com.zzys.railway.framework.starter.distributedid.toolkit.SnowflakeIdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 支付接口层实现
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayMapper payMapper;
    private final AbstractStrategyChoose abstractStrategyChoose;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PayRespDTO commonPay(PayRequest requestParam) {
        /**
         * {@link AliPayNativeHandler}
         */
        // 策略模式：通过策略模式封装支付渠道和支付场景，用户支付时动态选择对应的支付组件
        PayResponse result = abstractStrategyChoose.chooseAndExecuteResp(requestParam.buildMark(), requestParam);
        PayDO insertPay = BeanUtil.convert(requestParam, PayDO.class);
        String paySn = SnowflakeIdUtil.nextIdStrByService(requestParam.getOrderSn());
        insertPay.setPaySn(paySn);
        insertPay.setStatus(TradeStatusEnum.WAIT_BUYER_PAY.tradeCode());
        insertPay.setTotalAmount(requestParam.getTotalAmount().multiply(new BigDecimal("100")).setScale(0, RoundingMode.HALF_UP).intValue());
        int insert = payMapper.insert(insertPay);
        if (insert <= 0) {
            log.error("支付单创建失败，支付聚合根：{}", JSON.toJSONString(requestParam));
            throw new ServiceException("支付单创建失败");
        }
        return BeanUtil.convert(result, PayRespDTO.class);
    }

    @Override
    public PayInfoRespDTO getPayInfoByOrderSn(String orderSn) {
        LambdaQueryWrapper<PayDO> queryWrapper = Wrappers.lambdaQuery(PayDO.class)
                .eq(PayDO::getOrderSn, orderSn);
        PayDO payDO = payMapper.selectOne(queryWrapper);
        return BeanUtil.convert(payDO, PayInfoRespDTO.class);
    }

    @Override
    public PayInfoRespDTO getPayInfoByPaySn(String paySn) {
        LambdaQueryWrapper<PayDO> queryWrapper = Wrappers.lambdaQuery(PayDO.class)
                .eq(PayDO::getPaySn, paySn);
        PayDO payDO = payMapper.selectOne(queryWrapper);
        return BeanUtil.convert(payDO, PayInfoRespDTO.class);
    }

}
