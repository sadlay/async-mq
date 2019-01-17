package com.lay.rabbitmqtwo.config;

import com.rabbitmq.client.ConfirmListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;

/**
 * @Description:通过实现ConfirmCallBack接口，消息发送到交换器Exchange后触发回调。
 * @Author: lay
 * @Date: Created in 10:20 2018/12/20
 * @Modified By:IntelliJ IDEA
 */

public class ConfirmListenerHandler implements ConfirmListener {

    @Override
    public void handleAck(long l, boolean b) throws IOException {

    }

    @Override
    public void handleNack(long l, boolean b) throws IOException {

    }
}
