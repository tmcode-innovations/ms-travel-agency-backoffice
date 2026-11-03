package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.fileitinerary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.crm.FileItineraryDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileItineraryUpdateRequest {

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

    public FileItineraryDomain to() {
        FileItineraryDomain fileItineraryDomain = new FileItineraryDomain();
        fileItineraryDomain.setId(id);
        fileItineraryDomain.setName(name);
        fileItineraryDomain.setDescription(description);
        fileItineraryDomain.setIdentify(identify);
        fileItineraryDomain.setFileName(fileName);
        fileItineraryDomain.setExtension(extension);
        fileItineraryDomain.setPath(path);
        return fileItineraryDomain;
    }

    public FileItineraryDomain to(QuotationDomain quotation) {
        FileItineraryDomain fileItineraryDomain = new FileItineraryDomain();
        fileItineraryDomain.setId(id);
        fileItineraryDomain.setName(name);
        fileItineraryDomain.setDescription(description);
        fileItineraryDomain.setIdentify(identify);
        fileItineraryDomain.setFileName(fileName);
        fileItineraryDomain.setExtension(extension);
        fileItineraryDomain.setPath(path);
        if (quotation != null)
            fileItineraryDomain.setQuotation(quotation);
        return fileItineraryDomain;
    }

}
