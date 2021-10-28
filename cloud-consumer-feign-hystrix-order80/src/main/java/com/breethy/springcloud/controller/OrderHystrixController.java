package com.breethy.springcloud.controller;

import com.breethy.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderHystrixController {
    @Autowired
    private OrderHystrixService orderHystrixService;
    @GetMapping("hystrix/ok")
    String paymentInfo_OK(@RequestParam("id") Integer id){
        return orderHystrixService.paymentInfo_OK(id);
    }
    @GetMapping("hystrix/timeout")
    String paymentInfo_TimeOut(@RequestParam("id") Integer id){
        return orderHystrixService.paymentInfo_TimeOut(id);
    }
}
