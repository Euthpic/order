package com.euthpic.order.server.controller;

import com.euthpic.order.server.dto.OrderDto;
import com.euthpic.order.server.message.StreamClient;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class SendMessageController {
    private final StreamClient streamClient;

    public SendMessageController(StreamClient streamClient) {
        this.streamClient = streamClient;
    }

    @GetMapping("/sendMessage")
    public void process() {
        OrderDto orderDto=new OrderDto();
        orderDto.setOrderId("34234");
        streamClient.output().send(MessageBuilder.withPayload(orderDto).build());
    }
}
