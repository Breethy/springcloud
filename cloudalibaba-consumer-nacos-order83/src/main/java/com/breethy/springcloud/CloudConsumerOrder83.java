package com.breethy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudConsumerOrder83 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerOrder83.class,args);
    }
}
