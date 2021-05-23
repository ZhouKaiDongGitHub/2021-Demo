package org.kzhou.billing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {


    @GetMapping(value = "/createBilling")
    public String createBilling() {

        return "||支付成功";
    }
}
