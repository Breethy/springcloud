package com.breethy.springcloud.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
@Slf4j
public class OrderNacosController {
    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("paymentInfo")
    public String paymentInfo(String id){
        return restTemplate.getForObject(serviceUrl+"/payment/nacos?id="+id,String.class);
    }
}
