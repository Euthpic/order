package com.euthpic.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"已完成"),
    CANCELLED(2,"已取消");
    String msg;
    Integer code;

    OrderStatusEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
