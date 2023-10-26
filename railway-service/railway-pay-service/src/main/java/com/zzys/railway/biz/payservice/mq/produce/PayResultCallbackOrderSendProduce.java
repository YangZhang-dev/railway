
package com.zzys.railway.biz.payservice.mq.produce;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.zzys.railway.biz.payservice.mq.event.PayResultCallbackOrderEvent;
import com.zzys.railway.framework.starter.mq.constant.RailwayRocketMQConstant;
import com.zzys.railway.framework.starter.mq.domain.MessageWrapper;
import com.zzys.railway.framework.starter.mq.producer.AbstractCommonSendProduceTemplate;
import com.zzys.railway.framework.starter.mq.producer.BaseSendExtendDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 支付结果回调订单生产者
 *
 */
@Slf4j
@Component
public class PayResultCallbackOrderSendProduce extends AbstractCommonSendProduceTemplate<PayResultCallbackOrderEvent> {


    public PayResultCallbackOrderSendProduce(@Autowired RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(PayResultCallbackOrderEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("支付结果回调订单")
                .keys(messageSendEvent.getOrderSn())
                .topic(RailwayRocketMQConstant.PAY_GLOBAL_TOPIC_KEY)
                .tag(RailwayRocketMQConstant.PAY_RESULT_CALLBACK_TAG_KEY)
                .sentTimeout(2000L)
                .build();
    }

    @Override
    protected Message<?> buildMessage(PayResultCallbackOrderEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(requestParam.getKeys(), messageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
