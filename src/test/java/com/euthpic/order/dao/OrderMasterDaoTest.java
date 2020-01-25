package com.euthpic.order.dao;

import com.euthpic.order.enums.OrderStatusEnum;
import com.euthpic.order.enums.PayStatusEnum;
import com.euthpic.order.model.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterDaoTest {
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void save() {
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123");
        orderMaster.setBuyerName("肺炎");
        orderMaster.setBuyerPhone("1370000137");
        orderMaster.setBuyerAddress("武汉市");
        orderMaster.setBuyerOpenid("0000");
        orderMaster.setOrderAmount(new BigDecimal("20.20"));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        orderMasterDao.save(orderMaster);
    }
}