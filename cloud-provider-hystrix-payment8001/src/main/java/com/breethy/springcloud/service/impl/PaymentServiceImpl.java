package com.breethy.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
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

    //以下为服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率
    })
    public String paymentCircuitBreaker(Integer id) throws Exception {
        if (id<0){
            throw new Exception("id不能为负数: "+id);
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreakerFallBack(Integer id){
        return "服务降级,id不能为负数"+id;
    }
}
