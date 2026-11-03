package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;

import java.util.List;

public interface CostTourBookingUseCase {

    public CostTourResponse create(CostTourCreateRequest request);

    public CostTourResponse create(CostTourCreateRequest request, RegisterGroupDomain registerGroupDomain);

    public CostTourResponse edit(String id, CostTourUpdateRequest request);

    public CostTourResponse edit(String id, CostTourUpdateRequest request, RegisterGroupDomain registerGroupDomain);

    public void deleteById(String id);

    public List<CostTourResponse> findAll();

    public CostTourResponse findById(String id);

    public CostTourDomain findByIdDomain(String id);

    public CostTourSearchResponse searchAll(CostTourSearchRequest searchRequest);

    public CostTourSearchResponse searchFilterAll(CostTourSearchFilterRequest searchFilterRequest);

    public List<CostTourResponse> searchAllByRegisterGroup(String idRegisterGroup);

    public List<CostTourDomain> searchAllDomainByRegisterGroup(String idRegisterGroup);

    public CostTourResponse searchByRegisterGroupAndCostTour(String idRegisterGroup, String idCostTour);
}
