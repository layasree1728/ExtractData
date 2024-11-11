package com.layasree.ExtractExcelData.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class ExcelData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String rowDataJson; // Store JSON data as a single column
}