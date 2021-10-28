package com.breethy.springcloud.service.impl;

import com.breethy.springcloud.dao.PaymentDao;
import com.breethy.springcloud.entity.Payment;
import com.breethy.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public Boolean create(Payment payment) {
        return paymentDao.create(payment)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
