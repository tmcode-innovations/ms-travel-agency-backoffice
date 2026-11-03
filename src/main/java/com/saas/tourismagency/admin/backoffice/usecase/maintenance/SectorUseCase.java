package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.sector.*;

import java.util.List;

public interface SectorUseCase {

    public SectorResponse create(SectorCreateRequest request);

    public SectorResponse edit(String id, SectorUpdateRequest request);

    public void deleteById(String id);

    public List<SectorResponse> findAll();

    public SectorResponse findById(String id);

    public SectorSearchResponse searchAll(SectorSearchRequest sectorSearchRequest);

    public SectorSearchResponse searchFilterAll(SectorSearchFilterRequest sectorSearchFilterRequest);

    public SectorResponse findByName(String name);

    public SectorResponse findByIdentify(String identify);
}
