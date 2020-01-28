package com.euthpic.order.server.dao;

import com.euthpic.order.server.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

}