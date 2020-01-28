package com.euthpic.order.server.dao;

import com.euthpic.order.server.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

}