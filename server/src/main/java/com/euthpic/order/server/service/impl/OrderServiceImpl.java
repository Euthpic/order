package com.euthpic.order.server.service.impl;


import com.euthpic.order.server.dao.OrderDetailDao;
import com.euthpic.order.server.dao.OrderMasterDao;
import com.euthpic.order.server.dto.OrderDto;
import com.euthpic.order.server.enums.OrderStatusEnum;
import com.euthpic.order.server.enums.PayStatusEnum;
import com.euthpic.order.server.model.OrderDetail;
import com.euthpic.order.server.model.OrderMaster;
import com.euthpic.order.server.service.OrderService;
import com.euthpic.order.server.utils.KeyUtil;
import com.euthpic.product.client.ProductClient;
import com.euthpic.product.common.DecreaseStockInput;
import com.euthpic.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDetailDao orderDetailDao;
    private final OrderMasterDao orderMasterDao;
    private final ProductClient productClient;

    public OrderServiceImpl(OrderDetailDao orderDetailDao, OrderMasterDao orderMasterDao, ProductClient productClient) {
        this.orderDetailDao = orderDetailDao;
        this.orderMasterDao = orderMasterDao;
        this.productClient = productClient;
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
        String orderId = KeyUtil.getUniqueKey();
        //查询商品信息(调用商品服务)
        List<String> productIdList = orderDto.getOrderDetails().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfos = productClient.listForOrder(productIdList);
        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDto.getOrderDetails()) {
            for (ProductInfoOutput productInfo : productInfos) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmount = productInfo.getProductPrice().multiply(
                            new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailDao.save(orderDetail);
                }
            }
        }
        //扣库存(调用商品服务)
        List<DecreaseStockInput> cartDtoList = orderDto.getOrderDetails().stream().map(
                e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).
                collect(Collectors.toList());
        productClient.decreaseStock(cartDtoList);
        //订单入库
        orderDto.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterDao.save(orderMaster);

        return orderDto;
    }
}
