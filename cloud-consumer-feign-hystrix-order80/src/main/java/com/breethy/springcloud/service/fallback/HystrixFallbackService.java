package com.breethy.springcloud.service.fallback;

import com.breethy.springcloud.service.OrderHystrixService;
import org.springframework.stereotype.Component;

@Component
public class HystrixFallbackService implements OrderHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "HystrixFallbackService.paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "HystrixFallbackService.paymentInfo_TimeOut";
    }
}
