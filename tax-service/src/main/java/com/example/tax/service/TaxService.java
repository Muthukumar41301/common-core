package com.example.tax.service;

import com.core.lib.entity.TaxRecord;
import com.core.lib.exceptions.InvalidArgumentException;

import java.util.Optional;

public interface TaxService {
    TaxRecord calculateTax(String userName, double income) throws InvalidArgumentException;

    Optional<TaxRecord> getTaxRecord(String userName);
}
