package com.paradise.message.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author jrf
 * @date 2023-3-24 16:24
 */
@Component
public class UserConsume {

    @RabbitListener(queues = "TestQueue1",concurrency = "1")//监听的队列名称 TestDirectQueue
    public void process(String testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage);
    }


}
