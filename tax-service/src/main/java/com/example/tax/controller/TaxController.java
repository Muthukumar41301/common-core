package com.example.tax.controller;

import com.core.lib.entity.TaxRecord;
import com.example.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/calculate")
    public TaxRecord calculateTax(@RequestParam String userName, @RequestParam double income) {
        return taxService.calculateTax(userName, income);
    }
}
