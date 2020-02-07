package com.euthpic.order.server.controller;

import com.euthpic.order.server.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GirlController {
    private final GirlConfig girlConfig;

    public GirlController(GirlConfig girlConfig) {
        this.girlConfig = girlConfig;
    }

    @GetMapping("/girl/print")
    public String print() {
        return "name: " + girlConfig.getName() +
                " age: " + girlConfig.getAge();
    }
}
