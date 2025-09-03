package com.example.tax.service;

import com.core.lib.entity.TaxRecord;

public interface TaxService {
    TaxRecord calculateTax(String userName, double income);
}
