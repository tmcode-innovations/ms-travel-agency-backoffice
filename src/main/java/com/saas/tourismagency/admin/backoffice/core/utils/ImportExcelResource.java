package com.saas.tourismagency.admin.backoffice.core.utils;

import org.springframework.stereotype.Service;

@Service
public class ImportExcelResource {

    public String getUrlImportObjectSecurityExcel(){
        String urlClass = "import_excel/import_data_object.xls";
        return urlClass;
    }

    public String getUrlImportNationalityExcel(){
        String urlClass = "import_excel/import_data_nationality.xls";
        return urlClass;
    }
}
