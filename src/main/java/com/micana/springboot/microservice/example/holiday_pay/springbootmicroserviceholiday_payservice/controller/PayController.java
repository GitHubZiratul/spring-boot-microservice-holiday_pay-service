package com.micana.springboot.microservice.example.holiday_pay.springbootmicroserviceholiday_payservice.controller;

import com.micana.springboot.microservice.example.holiday_pay.springbootmicroserviceholiday_payservice.service.PayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@AllArgsConstructor
public class PayController {
    private PayService payService;

    @GetMapping("/calculacte")
    public int getPay (@RequestParam("start") String vacationStart,@RequestParam("end") String vacationEnd,@RequestParam int money) throws ParseException {
        return payService.getPay(vacationStart,vacationEnd, money);
    }


}
