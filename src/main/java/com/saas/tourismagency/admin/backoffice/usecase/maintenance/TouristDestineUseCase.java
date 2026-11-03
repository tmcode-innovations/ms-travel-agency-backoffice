package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine.*;

import java.util.List;

public interface TouristDestineUseCase {

    public TouristDestineResponse create(TouristDestineCreateRequest request);

    public TouristDestineResponse edit(String id, TouristDestineUpdateRequest request);

    public void deleteById(String id);

    public List<TouristDestineResponse> findAll();

    public TouristDestineResponse findById(String id);

    public TouristDestineSearchResponse searchAll(TouristDestineSearchRequest customerSearchRequest);

    public TouristDestineSearchResponse searchFilterAll(TouristDestineSearchFilterRequest customerSearchFilterRequest);

    public TouristDestineResponse findByName(String name);

    public TouristDestineResponse findByIdentify(String identify);
}
