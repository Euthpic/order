package com.euthpic.order.service.impl;

import com.euthpic.order.dao.OrderDetailDao;
import com.euthpic.order.dao.OrderMasterDao;
import com.euthpic.order.dto.OrderDto;
import com.euthpic.order.enums.OrderStatusEnum;
import com.euthpic.order.enums.PayStatusEnum;
import com.euthpic.order.model.OrderMaster;
import com.euthpic.order.service.OrderService;
import com.euthpic.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDetailDao orderDetailDao;
    private final OrderMasterDao orderMasterDao;

    public OrderServiceImpl(OrderDetailDao orderDetailDao, OrderMasterDao orderMasterDao) {
        this.orderDetailDao = orderDetailDao;
        this.orderMasterDao = orderMasterDao;
    }
    /**
     * 1. 查询商品信息(调用商品服务)
     * 2. 计算总价
     * 3. 扣库存(调用商品服务)
     * 4. 订单入库
     */
    //todo
    @Override
    public OrderDto create(OrderDto orderDto) {
        orderDto.setOrderId(KeyUtil.getUniqueKey());

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal("5"));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterDao.save(orderMaster);

        return orderDto;
    }
}
