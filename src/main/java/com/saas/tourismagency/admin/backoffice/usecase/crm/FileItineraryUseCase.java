package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.fileitinerary.*;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public interface FileItineraryUseCase {

    public FileItineraryResponse create(FileItineraryCreateRequest request);

    public FileItineraryResponse edit(String id, FileItineraryUpdateRequest request);

    public void deleteById(String id);

    public List<FileItineraryResponse> findAll();

    public FileItineraryResponse findById(String id);

    public FileItinerarySearchResponse searchAll(FileItinerarySearchRequest searchRequest);

    public FileItinerarySearchResponse searchFilterAll(FileItinerarySearchFilterRequest searchFilterRequest);

    public FileItineraryResponse findByName(String name);

    public FileItineraryResponse findByIdentify(String identify);

    public List<FileItineraryResponse> searchAllByQuotation(String idQuotation);

    public ByteArrayResource downloadDocument(String id);
}
