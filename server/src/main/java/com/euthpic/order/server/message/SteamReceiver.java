package com.euthpic.order.server.message;

import com.euthpic.order.server.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class SteamReceiver {

    @StreamListener("myMessage")
    public void process(OrderDto message){
        log.info("StreamReceiver: {}",message);
    }
}
