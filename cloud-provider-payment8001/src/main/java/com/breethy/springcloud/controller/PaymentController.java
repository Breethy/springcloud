package com.breethy.springcloud.controller;

import com.breethy.springcloud.entity.CommonResult;
import com.breethy.springcloud.entity.Payment;
import com.breethy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        Boolean result = paymentService.create(payment);
        log.info("*************插入结果:"+result);
        if (result){
            return new CommonResult(200,"请求成功 serverPort"+serverPort,result);
        }else{
            return new CommonResult(500,"请求失败");
        }
    }
    @GetMapping("/query")
    public CommonResult<Payment> query(Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*************查询结果:"+payment);
        return new CommonResult<Payment>(200,"请求成功 serverPort"+serverPort,payment);
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***************element: "+ element );
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
