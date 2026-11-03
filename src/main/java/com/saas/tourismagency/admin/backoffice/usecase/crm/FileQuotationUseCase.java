package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.filequotation.*;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public interface FileQuotationUseCase {

    public FileQuotationResponse create(FileQuotationCreateRequest request);

    public FileQuotationResponse edit(String id, FileQuotationUpdateRequest request);

    public void deleteById(String id);

    public List<FileQuotationResponse> findAll();

    public FileQuotationResponse findById(String id);

    public FileQuotationSearchResponse searchAll(FileQuotationSearchRequest searchRequest);

    public FileQuotationSearchResponse searchFilterAll(FileQuotationSearchFilterRequest searchFilterRequest);

    public FileQuotationResponse findByName(String name);

    public FileQuotationResponse findByIdentify(String identify);

    public List<FileQuotationResponse> searchAllByQuotation(String idQuotation);

    public ByteArrayResource downloadDocument(String id);
}
