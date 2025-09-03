package com.example.tax.service;

import com.core.lib.entity.TaxRecord;
import com.core.lib.exceptions.InvalidArgumentException;

public interface TaxService {
    TaxRecord calculateTax(String userName, double income) throws InvalidArgumentException;
}
