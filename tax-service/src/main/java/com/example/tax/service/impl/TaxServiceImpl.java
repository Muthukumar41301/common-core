package com.example.tax.service.impl;

import com.core.lib.entity.TaxRecord;
import com.core.lib.service.RedisCacheProvider;
import com.example.tax.repository.TaxRecordRepository;
import com.example.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRecordRepository taxRecordRepository;

    @Autowired
    private RedisCacheProvider redisCacheProvider;

    @Override
    public TaxRecord calculateTax(String userName, double income) {
        double tax;

        if (income <= 250000) {
            tax = 0;
        } else if (income <= 500000) {
            tax = (income - 250000) * 0.05;
        } else if (income <= 1000000) {
            tax = (250000 * 0.05) + (income - 500000) * 0.20;
        } else {
            tax = (250000 * 0.05) + (500000 * 0.20) + (income - 1000000) * 0.30;
        }

        double netIncome = income - tax;

        TaxRecord record = TaxRecord.builder()
                .userName(userName)
                .income(income)
                .taxAmount(tax)
                .netIncome(netIncome)
                .build();

        TaxRecord taxRecord= taxRecordRepository.save(record);
        redisCacheProvider.addData("tax",taxRecord.getUserName(),record);
        return taxRecord;
    }

    @Override
    public Optional<TaxRecord> getTaxRecord(String userName) {
        return redisCacheProvider.getData("tax",userName,TaxRecord.class);
    }


}
