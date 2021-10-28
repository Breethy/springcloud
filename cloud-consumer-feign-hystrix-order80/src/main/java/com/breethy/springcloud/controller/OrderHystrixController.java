package com.breethy.springcloud.controller;

import com.breethy.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Autowired
    private OrderHystrixService orderHystrixService;

    @GetMapping("hystrix/ok")
    String paymentInfo_OK(@RequestParam("id") Integer id){
        return orderHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("hystrix/timeout")
    @HystrixCommand
    String paymentInfo_TimeOut(@RequestParam("id") Integer id){
//        int age = 10/0;
        return orderHystrixService.paymentInfo_TimeOut(id);
    }
    public String paymentTimeOutFallbackMethod(Integer id){
        return "我是消费者80,对方支付系统繁忙请10秒钟之后再试。";
    }

    //全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "这是默认降级方法,服务器繁忙";
    }
}
