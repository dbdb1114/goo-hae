package com.kdt.goohae.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class CustomerController {

    @GetMapping (value = "customer/main")
    public String customerMain() { return "customer/customerMain";}
    @GetMapping (value = "customer/inquiry")
    public String customerInquiry() {return "customer/inquiry";}

}
