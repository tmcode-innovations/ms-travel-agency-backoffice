package com.saas.tourismagency.admin.backoffice.usecase.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;

import java.util.List;

public interface OrderServiceItemUseCase {

    public OrderServiceItemResponse create(OrderServiceItemCreateRequest request);

    public OrderServiceItemResponse create(OrderServiceItemCreateRequest request, OrderServiceDomain orderServiceDomain);

    public OrderServiceItemResponse edit(String id, OrderServiceItemUpdateRequest request);

    public OrderServiceItemResponse edit(String id, OrderServiceItemUpdateRequest request, OrderServiceDomain orderServiceDomain);

    public void deleteById(String id);

    public List<OrderServiceItemResponse> findAll();

    public OrderServiceItemResponse findById(String id);

    public OrderServiceItemDomain findByIdDomain(String id);

    public OrderServiceItemSearchResponse searchAll(OrderServiceItemSearchRequest bankSearchRequest);

    public OrderServiceItemSearchResponse searchFilterAll(OrderServiceItemSearchFilterRequest bankSearchFilterRequest);

    public List<OrderServiceItemResponse> searchAllByOrderService(String idOrderService);

    public OrderServiceItemResponse searchByOrderServiceAndOrderServiceItem(String idOrderService, String idOrderServiceItem);
}
