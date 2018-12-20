package com.lay.rabbitmqdelay.config;

/**
 * @Description:消息队列枚举配置
 * @Author: lay
 * @Date: Created in 19:31 2018/12/20
 * @Modified By:IntelliJ IDEA
 */
public enum QueueEnum {
    //延迟消息通知队列
    MESSAGE_QUEUE("message.center.direct", "message.create", "message_center.create"),

    //消息通知ttl队列
    MESSAGE_TTL_QUEUE("message.center.topic.ttl", "message.create.ttl", "message_center.create.ttl");

    //交换机名称
    private String exchange;
    //队列名称
    private String name;
    //路由键
    private String routingKey;

    QueueEnum(String exchange, String name, String routingKey) {
        this.exchange = exchange;
        this.name = name;
        this.routingKey = routingKey;
    }
    public String getExchange() {
        return exchange;
    }


    public String getName() {
        return name;
    }

    public String getRoutingKey() {
        return routingKey;
    }


}
