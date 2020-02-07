package com.euthpic.order;

import com.euthpic.order.server.OrderApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(classes = OrderApplication.class)
public class MqSenderTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void test(){
        amqpTemplate.convertAndSend("myQueue","now: "+new Date());
    }

}
