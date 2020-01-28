package com.euthpic.order.server.service;

import com.euthpic.order.server.dto.OrderDto;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    public OrderDto create(OrderDto orderDto);
}
