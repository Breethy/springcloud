package com.breethy.springcloud.service;

import com.breethy.springcloud.entity.CommonResult;
import com.breethy.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("payment/query")
    CommonResult<Payment> query(@RequestParam("id") Long id);

    @GetMapping("payment/feign/timeout")
    String paymentFeignTimeout();
}
