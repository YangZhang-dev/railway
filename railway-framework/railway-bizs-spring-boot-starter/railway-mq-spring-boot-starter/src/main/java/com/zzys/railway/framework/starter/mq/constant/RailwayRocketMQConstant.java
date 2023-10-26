package com.zzys.railway.framework.starter.mq.constant;

/**
 * RocketMQ 订单服务常量类
 *
 * @author YangZhang
 * @createTime 2023/10/21/ 22:38
 */
public class RailwayRocketMQConstant {
    /**
     * 订单服务相关业务 Topic Key
     */
    public static final String ORDER_DELAY_CLOSE_TOPIC_KEY = "railway_order-service_delay-close-order_topic";

    /**
     * 购票服务创建订单后延时关闭业务 Tag Key
     */
    public static final String ORDER_DELAY_CLOSE_TAG_KEY = "railway_order-service_delay-close-order_tag";
    /**
     * 支付服务相关业务 Topic Key
     */
    public static final String PAY_GLOBAL_TOPIC_KEY = "railway_pay-service_topic";

    /**
     * 支付结果回调状态 Tag Key
     */
    public static final String PAY_RESULT_CALLBACK_TAG_KEY = "railway_pay-service_pay-result-callback_tag";

    /**
     * 支付结果回调订单消费者组 Key
     */
    public static final String PAY_RESULT_CALLBACK_ORDER_CG_KEY = "railway_pay-service_pay-result-callback-order_cg";

    /**
     * 退款结果回调订单 Tag Key
     */
    public static final String REFUND_RESULT_CALLBACK_TAG_KEY = "railway_pay-service_refund-result-callback_tag";

    /**
     * 退款结果回调订单消费者组 Key
     */
    public static final String REFUND_RESULT_CALLBACK_ORDER_CG_KEY = "railway_pay-service_refund-result-callback-order_cg";

}
