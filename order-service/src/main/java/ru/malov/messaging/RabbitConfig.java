package ru.malov.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "orders.exchange";
    public static final String Q_STORAGE_APPROVAL = "storage.order.approval.queue";
    public static final String Q_ORDER_RESULT = "order.approval.result.queue";
    public static final String RK_ORDER_SENT = "order.sent.for.approval";
    public static final String RK_ORDER_APPROVED = "order.approved";
    public static final String RK_ORDER_REJECTED = "order.rejected";

    @Bean
    TopicExchange ordersExchange() { return new TopicExchange(EXCHANGE); }

    @Bean
    Queue storageApprovalQueue() { return new Queue(Q_STORAGE_APPROVAL, true); }

    @Bean
    Queue orderResultQueue() { return new Queue(Q_ORDER_RESULT, true); }

    @Bean
    Binding b1() { return BindingBuilder.bind(storageApprovalQueue()).to(ordersExchange()).with(RK_ORDER_SENT); }

    @Bean
    Binding b2() { return BindingBuilder.bind(orderResultQueue()).to(ordersExchange()).with(RK_ORDER_APPROVED); }

    @Bean
    Binding b3() { return BindingBuilder.bind(orderResultQueue()).to(ordersExchange()).with(RK_ORDER_REJECTED); }
}
