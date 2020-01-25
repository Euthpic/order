package com.euthpic.order.dao;

import com.euthpic.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

}