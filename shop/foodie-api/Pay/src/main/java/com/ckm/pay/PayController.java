package com.ckm.pay;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @GetMapping("/hello")
    public String hello(){
        return "这是支付控制层!";
    }
}
