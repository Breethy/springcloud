package com.breethy.springcloud.controller;

import com.breethy.springcloud.entity.CommonResult;
import com.breethy.springcloud.entity.Payment;
import com.breethy.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @GetMapping("payment/query")
    public CommonResult<Payment> getPaymentById(Long id){
        return paymentFeignService.query(id);
    }

    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }

}
