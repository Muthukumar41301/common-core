package com.example.tax.controller;

import com.core.lib.entity.TaxRecord;
import com.example.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/calculate")
    public TaxRecord calculateTax(@RequestParam String userName, @RequestParam double income) {
        return taxService.calculateTax(userName, income);
    }

    @GetMapping
    public Optional<TaxRecord> getTaxRecord(@RequestParam String userName) {
        return taxService.getTaxRecord(userName);
    }

    @GetMapping("/list")
    public List<TaxRecord> getTaxRecords() {
        return taxService.getTaxRecords();
    }
}
