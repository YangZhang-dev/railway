
package com.zzys.railway.biz.orderservice.mq.consumer;


import com.zzys.railway.biz.orderservice.common.enums.OrderItemStatusEnum;
import com.zzys.railway.biz.orderservice.common.enums.OrderStatusEnum;
import com.zzys.railway.biz.orderservice.dto.domain.OrderStatusReversalDTO;
import com.zzys.railway.biz.orderservice.mq.event.PayResultCallbackOrderEvent;
import com.zzys.railway.biz.orderservice.service.OrderService;
import com.zzys.railway.framework.starter.idempotent.annotation.Idempotent;
import com.zzys.railway.framework.starter.idempotent.enums.IdempotentSceneEnum;
import com.zzys.railway.framework.starter.idempotent.enums.IdempotentTypeEnum;
import com.zzys.railway.framework.starter.mq.constant.RailwayRocketMQConstant;
import com.zzys.railway.framework.starter.mq.domain.MessageWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付结果回调订单消费者
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = RailwayRocketMQConstant.PAY_GLOBAL_TOPIC_KEY,
        selectorExpression = RailwayRocketMQConstant.PAY_RESULT_CALLBACK_TAG_KEY,
        consumerGroup = RailwayRocketMQConstant.PAY_RESULT_CALLBACK_ORDER_CG_KEY
)
public class PayResultCallbackOrderConsumer implements RocketMQListener<MessageWrapper<PayResultCallbackOrderEvent>> {

    private final OrderService orderService;

    @Idempotent(
            uniqueKeyPrefix = "railway-order:pay_result_callback:",
            key = "#message.getKeys()+'_'+#message.hashCode()",
            type = IdempotentTypeEnum.SPEL,
            scene = IdempotentSceneEnum.MQ,
            keyTimeout = 7200L
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onMessage(MessageWrapper<PayResultCallbackOrderEvent> message) {
        PayResultCallbackOrderEvent payResultCallbackOrderEvent = message.getMessage();
        OrderStatusReversalDTO orderStatusReversalDTO = OrderStatusReversalDTO.builder()
                .orderSn(payResultCallbackOrderEvent.getOrderSn())
                .orderStatus(OrderStatusEnum.ALREADY_PAID.getStatus())
                .orderItemStatus(OrderItemStatusEnum.ALREADY_PAID.getStatus())
                .build();
        orderService.statusReversal(orderStatusReversalDTO);
        orderService.payCallbackOrder(payResultCallbackOrderEvent);
    }
}
