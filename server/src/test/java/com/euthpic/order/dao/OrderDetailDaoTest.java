package com.euthpic.order.dao;

import java.math.BigDecimal;

import com.euthpic.order.server.OrderApplication;
import com.euthpic.order.server.dao.OrderDetailDao;
import com.euthpic.order.server.model.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OrderApplication.class)
class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("222");
        orderDetail.setProductId("22");
        orderDetail.setProductName("22");
        orderDetail.setProductPrice(new BigDecimal("0"));
        orderDetail.setProductQuantity(0);
        orderDetail.setProductIcon("22");
        orderDetailDao.save(orderDetail);

    }
}