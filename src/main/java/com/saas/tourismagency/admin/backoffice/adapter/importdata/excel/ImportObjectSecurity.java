package com.saas.tourismagency.admin.backoffice.adapter.importdata.excel;

import com.saas.tourismagency.admin.backoffice.adapter.importdata.model.security.ObjectSecurity;
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
public class ImportObjectSecurity {

    @Autowired
    private ImportExcelResource importExcelResource;

    public List<ObjectSecurity> readExcel() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(importExcelResource.getUrlImportObjectSecurityExcel());
        InputStream inputStream = classPathResource.getInputStream();

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<ObjectSecurity> data = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() != 0){
                ObjectSecurity objectSecurity = new ObjectSecurity();
                DataFormatter formatter = new DataFormatter();
                String cellValueIndex = formatter.formatCellValue(row.getCell(0));
                objectSecurity.setIndex(Integer.parseInt(cellValueIndex));
                String cellValueName = formatter.formatCellValue(row.getCell(1));
                objectSecurity.setName(cellValueName);
                String cellValueSource = formatter.formatCellValue(row.getCell(2));
                objectSecurity.setSource(cellValueSource);
                String cellValueApp = formatter.formatCellValue(row.getCell(3));
                objectSecurity.setApp(cellValueApp);
                String cellValueIdentify = formatter.formatCellValue(row.getCell(4));
                objectSecurity.setIdentify(cellValueIdentify);
                data.add(objectSecurity);
            }
        }
        workbook.close();
        return data;
    }

}
