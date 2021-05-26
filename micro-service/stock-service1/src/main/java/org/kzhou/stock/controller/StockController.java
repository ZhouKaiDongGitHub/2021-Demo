package org.kzhou.stock.controller;

import org.kzhou.base.response.ResponseModel;
import org.kzhou.user.feign.StockServiceFeignApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController implements StockServiceFeignApi {

    private Logger logger = LoggerFactory.getLogger(StockController.class);


    @GetMapping("/reduceStock")
    public ResponseModel reduceStock() {
        logger.info("库存模块：减少库存成功1");
        return new ResponseModel().setCode("200").setMessage("减少库存成功1");
    }
}
