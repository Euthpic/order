package com.euthpic.order.server.message;

import com.euthpic.order.server.utils.JsonUtil;
import com.euthpic.product.common.ProductInfoOutput;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductInfoReceiver {
    private final StringRedisTemplate stringRedisTemplate;
    public static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    public ProductInfoReceiver(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        List<ProductInfoOutput> productInfoOutputs = (List<ProductInfoOutput>)
                JsonUtil.fromJson(message, new TypeReference<List<ProductInfoOutput>>() {});
        log.info("从队列{}收到消息: {}", "productInfo", productInfoOutputs);
        for (ProductInfoOutput productInfoOutput : productInfoOutputs) {
            stringRedisTemplate.opsForValue().set(String.format(
                    PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
