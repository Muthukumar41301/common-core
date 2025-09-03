package com.example.tax.service;

import com.core.lib.entity.TaxRecord;

import java.util.Optional;

public interface TaxService {
    TaxRecord calculateTax(String userName, double income);

    Optional<TaxRecord> getTaxRecord(String userName);
}
