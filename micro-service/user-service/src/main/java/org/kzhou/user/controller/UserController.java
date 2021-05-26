package org.kzhou.user.controller;

import com.alibaba.fastjson.JSONObject;
import org.kzhou.user.feign.OrderServiceFeignApi;
import org.kzhou.user.feign.StockServiceFeignApi;
import org.kzhou.base.response.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceFeignApi orderServiceFeignApi;

    @Autowired
    private StockServiceFeignApi stockServiceFeignApi;

    @GetMapping("/putOrder")
    public ResponseModel putOrder(){
        ResponseModel orderRsp =  restTemplate.getForObject("http://order-service/order/createOrder",ResponseModel.class);
        logger.info("用户模块：调用order-service接口putOrder返回: " + JSONObject.toJSONString(orderRsp));
        if("200".equals(orderRsp.getCode())){
            ResponseModel stockRsp =  restTemplate.getForObject("http://stock-service/stock/reduceStock",ResponseModel.class);
            logger.info("用户模块：调用stock-service接口reduceStock返回: " + JSONObject.toJSONString(stockRsp));
            if("200".equals(stockRsp.getCode())){
                return new ResponseModel().setCode("200").setMessage("下单成功");
            }
        }
        return new ResponseModel().setCode("500").setMessage("下单失败");
    }

    @GetMapping("/putOrder2")
    public ResponseModel putOrder2(){
        ResponseModel orderRsp =  orderServiceFeignApi.createOrder();
        logger.info("用户模块：调用order-service接口putOrder返回: " + JSONObject.toJSONString(orderRsp));
        if("200".equals(orderRsp.getCode())){
            ResponseModel stockRsp =  stockServiceFeignApi.reduceStock();
            logger.info("用户模块：调用stock-service接口reduceStock返回: " + JSONObject.toJSONString(stockRsp));
            if("200".equals(stockRsp.getCode())){
                return new ResponseModel().setCode("200").setMessage("下单成功");
            }
        }
        return new ResponseModel().setCode("500").setMessage("下单失败");
    }

}
