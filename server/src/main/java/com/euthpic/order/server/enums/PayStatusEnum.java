package com.euthpic.order.server.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");
    String msg;
    Integer code;

    PayStatusEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
