package com.breethy.springcloud.service;

import com.breethy.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public Boolean create(Payment payment);
    public Payment getPaymentById(Long id);
}
