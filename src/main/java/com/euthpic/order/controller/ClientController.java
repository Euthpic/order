package com.euthpic.order.controller;

import com.euthpic.order.client.ProductClient;
import com.euthpic.order.config.RestTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ClientController {

    private final ProductClient productClient;

    public ClientController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        String result=productClient.productMsg();
        log.info("response= {}", result);
        return result;
    }

//    private final LoadBalancerClient loadBalancerClient;
//    private final RestTemplate restTemplate;
//
//    public ClientController(LoadBalancerClient loadBalancerClient,RestTemplate restTemplate) {
//        this.loadBalancerClient = loadBalancerClient;
//        this.restTemplate=restTemplate;
//    }
//
//    @GetMapping("/getProductMsg")
//    public String getProductMsg() {
//        //1. 服务调用第一种方式,直接使用restTemplate,url写死
////        RestTemplate restTemplate = new RestTemplate();
////        String result = restTemplate.getForObject("http://localhost:8080/msg", String.class);
//
//        //2. 第二种方式,利用loadBalancerClient获取url,然后再使用restTemplate
////        RestTemplate restTemplate = new RestTemplate();
////        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
////        serviceInstance.getHost();
////        String url = String.format("http://%s:%s/msg", serviceInstance.getHost(), serviceInstance.getPort());
////        String result=restTemplate.getForObject(url,String.class);
//
//        //3.第三种方式,使用@LoadBalanced,可以用应用名替代url
//        String result = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("response= {}", result);
//        return result;
//    }
}
