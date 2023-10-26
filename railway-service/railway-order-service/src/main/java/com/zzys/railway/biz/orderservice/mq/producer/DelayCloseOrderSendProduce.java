
package com.zzys.railway.biz.orderservice.mq.producer;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.zzys.railway.biz.orderservice.mq.event.DelayCloseOrderEvent;
import com.zzys.railway.framework.starter.mq.constant.RailwayRocketMQConstant;
import com.zzys.railway.framework.starter.mq.domain.MessageWrapper;
import com.zzys.railway.framework.starter.mq.producer.AbstractCommonSendProduceTemplate;
import com.zzys.railway.framework.starter.mq.producer.BaseSendExtendDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 延迟关闭订单生产者
 *
 */
@Slf4j
@Component
public class DelayCloseOrderSendProduce extends AbstractCommonSendProduceTemplate<DelayCloseOrderEvent> {


    public DelayCloseOrderSendProduce(RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(DelayCloseOrderEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("延迟关闭订单")
                .keys(messageSendEvent.getOrderSn())
                .topic(RailwayRocketMQConstant.ORDER_DELAY_CLOSE_TOPIC_KEY)
                .tag(RailwayRocketMQConstant.ORDER_DELAY_CLOSE_TAG_KEY)
                .sentTimeout(2000L)
                // RocketMQ 延迟消息级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                .delayLevel(14)
                .build();
    }

    @Override
    protected Message<?> buildMessage(DelayCloseOrderEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(requestParam.getKeys(), messageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
