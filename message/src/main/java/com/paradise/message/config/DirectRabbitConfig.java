package com.paradise.message.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jrf
 * @date 2023-3-24 15:58
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 给队列起个名称
     */
    @Bean
    public Queue directRabbit(){
        return new Queue("TestQueue1",true);
    }

    /**
     * 给交换机起个名称
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("TestDirect1",true,false);
    }

    /**
     * 将队列 与 交换机绑定
     */
    @Bean
    Binding bindingDirect(){
         return BindingBuilder.bind(directRabbit()).to(directExchange()).with("TestBinding1");
    }

    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }


}
