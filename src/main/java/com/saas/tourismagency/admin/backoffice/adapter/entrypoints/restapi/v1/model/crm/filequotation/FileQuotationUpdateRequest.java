package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.filequotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileQuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileQuotationUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("extension")
    private String extension;

    @JsonProperty("path")
    private String path;

    @JsonProperty("quotation")
    private String quotation;

    public FileQuotationDomain to() {
        FileQuotationDomain fileQuotationDomain = new FileQuotationDomain();
        fileQuotationDomain.setId(id);
        fileQuotationDomain.setName(name);
        fileQuotationDomain.setDescription(description);
        fileQuotationDomain.setIdentify(identify);
        fileQuotationDomain.setFileName(fileName);
        fileQuotationDomain.setExtension(extension);
        fileQuotationDomain.setPath(path);
        return fileQuotationDomain;
    }

    public FileQuotationDomain to(QuotationDomain quotation) {
        FileQuotationDomain fileQuotationDomain = new FileQuotationDomain();
        fileQuotationDomain.setId(id);
        fileQuotationDomain.setName(name);
        fileQuotationDomain.setDescription(description);
        fileQuotationDomain.setIdentify(identify);
        fileQuotationDomain.setFileName(fileName);
        fileQuotationDomain.setExtension(extension);
        fileQuotationDomain.setPath(path);
        if (quotation != null)
            fileQuotationDomain.setQuotation(quotation);
        return fileQuotationDomain;
    }

}
