package org.kzhou.user.feign;

import org.kzhou.base.response.ResponseModel;
import org.kzhou.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "order-service",configuration = FeignConfig.class)
// @FeignClient(value = "order-service")
public interface OrderServiceFeignApi {

     @RequestMapping("order/createOrder")
     ResponseModel createOrder();
}
