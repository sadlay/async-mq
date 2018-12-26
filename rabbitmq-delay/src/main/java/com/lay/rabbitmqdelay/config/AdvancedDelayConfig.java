package com.lay.rabbitmqdelay.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 18:50 2018/12/26
 * @Modified By:IntelliJ IDEA
 */
//@Configuration
public class AdvancedDelayConfig {
    public static String DELAY_QUEUE_PER_MESSAGE_TTL_NAME="";
    public static String DELAY_EXCHANGE_NAME="";
    public static String DELAY_PROCESS_QUEUE_NAME="";


    public static String DELAY_QUEUE_PER_QUEUE_TTL_NAME="";
    public static Long QUEUE_EXPIRATION=1000L;

    //我们通过Java Config的方式将上述的队列配置为Bean。
    // 由于我们添加了spring-boot-starter-amqp扩展，Spring Boot在启动时会根据我们的配置自动创建这些队列。
    // 为了方便接下来的测试，我们将delay_queue_per_message_ttl以及delay_queue_per_queue_ttl的DLX配置为同一个，
    // 且过期的消息都会通过DLX转发到delay_process_queue。



    //delay_queue_per_message_ttl：TTL配置在消息上的缓冲队列。
    /**
     *
     * @Description: 其中，x-dead-letter-exchange声明了队列里的死信转发到的DLX名称，
     * x-dead-letter-routing-key声明了这些死信在转发时携带的routing-key名称。
     * @param: []
     * @return: org.springframework.amqp.core.Queue
     * @auther: lay
     * @date: 18:56 2018/12/26
     */
    @Bean
    Queue delayQueuePerMessageTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_MESSAGE_TTL_NAME)
                    .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME)// DLX，dead letter发送到的exchange
                    .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME)// dead letter携带的routing key
                    .build();
    }
    //delay_queue_per_queue_ttl：TTL配置在队列上的缓冲队列。
    /**
     *
     * @Description: delay_queue_per_queue_ttl队列的配置比delay_queue_per_message_ttl队列的配置多了一个x-message-ttl，
     * 该配置用来设置队列的过期时间。
     * @param: []
     * @return: org.springframework.amqp.core.Queue
     * @auther: lay
     * @date: 18:56 2018/12/26
     */
    @Bean
    Queue delayQueuePerQueueTTL() {
        return QueueBuilder.durable(DELAY_QUEUE_PER_QUEUE_TTL_NAME)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) // DLX
                .withArgument("x-dead-letter-routing-key", DELAY_PROCESS_QUEUE_NAME) // dead letter携带的routing key
                .withArgument("x-message-ttl", QUEUE_EXPIRATION) // 设置队列的过期时间
                .build();
    }


    //delay_process_queue：实际消费队列。
    @Bean
    Queue delayProcessQueue() {
        return QueueBuilder.durable(DELAY_PROCESS_QUEUE_NAME)
                .build();
    }

    //配置Exchange 配置DLX
    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }
    //然后再将该DLX绑定到实际消费队列即delay_process_queue上。这样所有的死信都会通过DLX被转发到delay_process_queue：
    @Bean
    Binding dlxBinding(Queue delayProcessQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayProcessQueue)
                .to(delayExchange)
                .with(DELAY_PROCESS_QUEUE_NAME);
    }


/*    定义消费者

    我们创建一个最简单的消费者ProcessReceiver，这个消费者监听delay_process_queue队列，对于接受到的消息，他会：

    如果消息里的消息体不等于FAIL_MESSAGE，那么他会输出消息体。
    如果消息里的消息体恰好是FAIL_MESSAGE，那么他会模拟抛出异常，然后将该消息重定向到缓冲队列（对应延迟重试场景）。
    另外，我们还需要新建一个监听容器用于存放消费者，代码如下*/

    //我们创建一个最简单的消费者ProcessReceiver
    @Component
    public class ProcessReceiver{

    }

    @Bean
    SimpleMessageListenerContainer processContainer(ConnectionFactory connectionFactory, ProcessReceiver processReceiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DELAY_PROCESS_QUEUE_NAME); // 监听delay_process_queue
        container.setMessageListener(new MessageListenerAdapter(processReceiver));
        return container;
    }


}
