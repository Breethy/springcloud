package com.breethy.springcloud.service.impl;

import com.breethy.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：  " +Thread.currentThread().getName()+"    paymentInfo_OK,id：     "+ id;
    }

    /**
     * 超时访问
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        int timenum=3;
        try {
            TimeUnit.SECONDS.sleep(timenum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  " +Thread.currentThread().getName()+"    paymentInfo_TimeOut,id：     "+ id+"     耗时"+timenum+"秒钟";
    }
}
