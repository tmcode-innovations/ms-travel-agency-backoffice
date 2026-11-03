package com.saas.tourismagency.admin.backoffice.usecase.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;

import java.util.List;

public interface OrderServiceUseCase {

    public OrderServiceResponse create(OrderServiceCreateRequest request);

    public OrderServiceResponse edit(String id, OrderServiceUpdateRequest request);

    public void deleteById(String id);

    public List<OrderServiceResponse> findAll();

    public OrderServiceResponse findById(String id);

    public OrderServiceDomain findByIdDomain(String id);

    public OrderServiceSearchResponse searchAll(OrderServiceSearchRequest bankSearchRequest);

    public OrderServiceSearchResponse searchFilterAll(OrderServiceSearchFilterRequest bankSearchFilterRequest);

    public List<OrderServiceItemResponse> findAllOrderServiceItemByOrderService(String idOrderService);

    public OrderServiceItemResponse addOrderServiceItem(OrderServiceItemCreateRequest request, String idOrderService);

    public OrderServiceItemResponse editOrderServiceItem(String id, OrderServiceItemUpdateRequest request, String idOrderService);

    public void deleteOrderServiceItem(String id, String idOrderService);

    public OrderServiceItemResponse findByIdAndOrderService(String id, String idOrderService);

    public OrderServiceResponse changeState(String id, OrderServiceChangeStateRequest request);

    public OrderServiceResponse generate(String idRequestOrderService);

    public List<RegisterBookingItemOperatorResponse> findAllRegisterBookingItemOperatorAvailable(String idBookingOperator, String idOrderService);

    public List<RegisterBookingItemOperatorResponse> findAllRegisterBookingItemOperatorAvailableByRequestOrderService(String idBookingOperator, String idRequestOrderService);

    public byte[] exportOrderServicePdf(String idOrderService);

    public     byte[] exportOrderServiceOperationPdf(String idOrderService);

    List<ServiceDomain> findAllServiceByRegisterGroup(String registerGroupId);

    List<OrderServiceItemResponse> findAllOrderServiceItemByRegisterGroupAndGuideTransfer(String registerGroupId, String guideTransferId);
}
