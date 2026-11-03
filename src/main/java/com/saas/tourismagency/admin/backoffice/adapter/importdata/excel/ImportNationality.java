package com.saas.tourismagency.admin.backoffice.adapter.importdata.excel;

import com.saas.tourismagency.admin.backoffice.adapter.importdata.model.base.Nationality;
import com.saas.tourismagency.admin.backoffice.core.utils.ImportExcelResource;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportNationality {

    @Autowired
    private ImportExcelResource importExcelResource;

    public List<Nationality> readExcel() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(importExcelResource.getUrlImportNationalityExcel());
        InputStream inputStream = classPathResource.getInputStream();

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<Nationality> data = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() != 0){
                Nationality nationality = new Nationality();
                DataFormatter formatter = new DataFormatter();
                String cellValueName = formatter.formatCellValue(row.getCell(0));
                nationality.setName(cellValueName);
                data.add(nationality);
            }
        }
        workbook.close();
        return data;
    }

}
