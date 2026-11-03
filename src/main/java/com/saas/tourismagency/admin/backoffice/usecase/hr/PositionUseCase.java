package com.saas.tourismagency.admin.backoffice.usecase.hr;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position.*;

import java.util.List;

public interface PositionUseCase {

    public PositionResponse create(PositionCreateRequest request);

    public PositionResponse edit(String id, PositionUpdateRequest request);

    public void deleteById(String id);

    public List<PositionResponse> findAll();

    public PositionResponse findById(String id);

    public PositionSearchResponse searchAll(PositionSearchRequest aeaSearchRequest);

    public PositionSearchResponse searchFilterAll(PositionSearchFilterRequest aeaSearchFilterRequest);

    public PositionResponse findByName(String name);

    public PositionResponse findByIdentify(String identify);
}
