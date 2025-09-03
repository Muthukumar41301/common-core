package com.core.lib.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tax-record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private double income;
    private double taxAmount;
    private double netIncome;

    private LocalDateTime createdDate = LocalDateTime.now();
}
