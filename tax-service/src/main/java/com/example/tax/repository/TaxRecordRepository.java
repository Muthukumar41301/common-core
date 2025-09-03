package com.example.tax.repository;

import com.core.lib.entity.TaxRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRecordRepository extends JpaRepository<TaxRecord, Long> {
}
