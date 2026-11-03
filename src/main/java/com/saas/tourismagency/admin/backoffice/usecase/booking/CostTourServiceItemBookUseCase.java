package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;

import java.util.List;

public interface CostTourServiceItemBookUseCase {

    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request);

    public CostTourServiceItemResponse create(CostTourServiceItemCreateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain);

    public CostTourServiceItemResponse edit(String id, CostTourServiceItemUpdateRequest request);

    public CostTourServiceItemResponse edit(String id, CostTourServiceItemUpdateRequest request, RegisterGroupDomain registerGroupDomain, CostTourDomain costTourDomain);

    public void deleteById(String id);

    public List<CostTourServiceItemResponse> findAll();

    public CostTourServiceItemResponse findById(String id);

    public CostTourServiceItemSearchResponse searchAll(CostTourServiceItemSearchRequest searchRequest);

    public CostTourServiceItemSearchResponse searchFilterAll(CostTourServiceItemSearchFilterRequest searchFilterRequest);

    public List<CostTourServiceItemResponse> searchAllByRegisterGroup(String idRegisterGroup);

    public CostTourServiceItemResponse searchByRegisterGroupAndCostTourServiceItem(String idRegisterGroup, String idCostTourServiceItem);

    public CostTourServiceItemResponse searchByRegisterGroupAndCostTourAndCostTourServiceItem(String idRegisterGroup, String idCostTour, String idCostTourServiceItem);

    public List<CostTourServiceItemDomain> searchAllDomainByRegisterGroup(String idRegisterGroup);

    public List<CostTourServiceItemDomain> searchAllDomainByRegisterGroupAndCostTour(String idCostTour, String idRegisterGroup);
}
