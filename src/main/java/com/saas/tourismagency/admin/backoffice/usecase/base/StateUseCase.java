package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state.*;

import java.util.List;

public interface StateUseCase {

    public StateResponse create(StateCreateRequest request);

    public StateResponse edit(String id, StateUpdateRequest request);

    public void deleteById(String id);

    public List<StateResponse> findAll();

    public StateResponse findById(String id);

    public StateSearchResponse searchAll(StateSearchRequest stateSearchRequest);

    public StateSearchResponse searchFilterAll(StateSearchFilterRequest stateSearchFilterRequest);

    public StateWithoutPageSearchResponse searchAllByCountry(BaseSearchRequestById baseSearchRequestById);
}
