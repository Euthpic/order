package com.euthpic.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * order_detail
 * @author 
 */
@Data
@Entity
public class OrderDetail implements Serializable {
    @Id
    private String detailId;

    private String orderId;

    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 当前价格,单位分
     */
    private BigDecimal productPrice;

    /**
     * 数量
     */
    private Integer productQuantity;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 创建时间
     */
//    private Date createTime;
//
//    /**
//     * 修改时间
//     */
//    private Date updateTime;

    private static final long serialVersionUID = 1L;
}