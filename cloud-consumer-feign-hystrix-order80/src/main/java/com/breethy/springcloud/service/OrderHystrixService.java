package com.breethy.springcloud.service;

import com.breethy.springcloud.service.fallback.HystrixFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = HystrixFallbackService.class)
public interface OrderHystrixService {
    @GetMapping("payment/hystrix/ok")
    String paymentInfo_OK(@RequestParam("id") Integer id);
    @GetMapping("payment/hystrix/timeout")
    String paymentInfo_TimeOut(@RequestParam("id") Integer id);

}
