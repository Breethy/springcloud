package com.breethy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudalibabaProviderPayment9003 {
    public static void main(String[] args) {
        SpringApplication.run(CloudalibabaProviderPayment9003.class,args);
    }
}
