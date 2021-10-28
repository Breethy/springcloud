package com.breethy.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //收集eureka上集群对象
    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
