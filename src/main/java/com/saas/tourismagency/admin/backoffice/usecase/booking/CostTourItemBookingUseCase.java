package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;

import java.util.List;

public interface CostTourItemBookingUseCase {

    public CostTourItemResponse create(CostTourItemCreateRequest request);

    public CostTourItemResponse create(CostTourItemCreateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain);

    public CostTourItemResponse edit(String id, CostTourItemUpdateRequest request);

    public CostTourItemResponse edit(String id, CostTourItemUpdateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain);

    public void deleteById(String id);

    public List<CostTourItemResponse> findAll();

    public CostTourItemResponse findById(String id);

    public CostTourItemSearchResponse searchAll(CostTourItemSearchRequest searchRequest);

    public CostTourItemSearchResponse searchFilterAll(CostTourItemSearchFilterRequest searchFilterRequest);

    public List<CostTourItemResponse> searchAllByRegisterGroup(String idRegisterGroup);

    public CostTourItemResponse searchByRegisterGroupAndCostTourItem(String idRegisterGroup, String idCostTourItem);

    public CostTourItemResponse searchByRegisterGroupAndCostTourAndCostTourItem(String idRegisterGroup, String idCostTour, String idCostTourItem);

    public List<CostTourItemDomain> searchAllDomainByRegisterGroup(String idQuotation);

    public List<CostTourItemDomain> searchAllDomainByRegisterGroupAndCostTour(String idCostTour, String idRegisterGroup);
}
