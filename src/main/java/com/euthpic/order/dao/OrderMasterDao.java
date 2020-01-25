package com.euthpic.order.dao;

import com.euthpic.order.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

}