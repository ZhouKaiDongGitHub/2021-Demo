package org.kzhou.user.feign;

import org.kzhou.base.response.ResponseModel;
import org.kzhou.user.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "stock-service",configuration = FeignConfig.class)
// @FeignClient(value = "stock-service")
public interface StockServiceFeignApi {


    @RequestMapping("stock/reduceStock")
    ResponseModel reduceStock();
}
