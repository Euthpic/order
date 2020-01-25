package com.euthpic.order.service;

import com.euthpic.order.dto.OrderDto;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    public OrderDto create(OrderDto orderDto);
}
