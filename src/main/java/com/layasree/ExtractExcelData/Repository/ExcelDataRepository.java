package com.layasree.ExtractExcelData.Repository;

import com.layasree.ExtractExcelData.Entity.ExcelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelDataRepository extends JpaRepository<ExcelData,Long> {
}
