package com.breethy.springcloud.service.impl;

import com.breethy.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
//        int age = 10/0;
        int timenum=3;
        try {
            TimeUnit.SECONDS.sleep(timenum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  " +Thread.currentThread().getName()+"    paymentInfo_TimeOut,id：     "+ id+"     耗时"+timenum+"秒钟";
//        return "线程池：  " +Thread.currentThread().getName()+"    8001paymentInfo_TimeOut,id：     "+ id;
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池：  " +Thread.currentThread().getName()+"    8001paymentInfo_TimeOutHandler,id：     "+ id +"系统繁忙，请稍后再试";
    }
}
