package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.operation.OrderServiceUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation.OrderServiceController.ORDER_SERVICE;

@Slf4j
@Tag(name = "Operation - Order Service", description = "Operation Module")
@RestController
@RequestMapping(ORDER_SERVICE)
public class OrderServiceController {

    public static final String ORDER_SERVICE = "/order-services";

    public static final String ORDER_SERVICE_ID = "order_service_id";
    public static final String ORDER_SERVICE_ITEM_ID = "register_booking_item_id";
    public static final String REQUEST_ORDER_SERVICE_ID = "request_order_service_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + ORDER_SERVICE_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + ORDER_SERVICE_ID + "}";
    public static final String DELETE = "/delete/{" + ORDER_SERVICE_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_ORDER_SERVICE_ITEM = "/{" + ORDER_SERVICE_ID + "}/order-service-item/add";
    public static final String EDIT_ORDER_SERVICE_ITEM = "/{" + ORDER_SERVICE_ID + "}/order-service-item/edit/{" + ORDER_SERVICE_ITEM_ID + "}";
    public static final String DELETE_ORDER_SERVICE_ITEM = "/{" + ORDER_SERVICE_ID + "}/order-service-item/delete/{" + ORDER_SERVICE_ITEM_ID + "}";
    public static final String FIND_ORDER_SERVICE_ITEM = "/{" + ORDER_SERVICE_ID + "}/order-service-item/find/{" + ORDER_SERVICE_ITEM_ID + "}";
    public static final String FIND_ALL_ORDER_SERVICE_ITEM = "/{" + ORDER_SERVICE_ID + "}/order-service-item/find-all";

    public static final String CHANGE_STATE = "/change-state/{" + ORDER_SERVICE_ID + "}";

    public static final String GENERATE_ORDER_SERVICE = "/generate/{" + REQUEST_ORDER_SERVICE_ID + "}";
    public static final String FIND_ALL_REGISTER_BOOKING_ITEM_AVAILABLE = "/find-all-register-booking-item-available/{" + ORDER_SERVICE_ID + "}";

    public static final String EXPORT_ORDER_SERVICE = "/{" + ORDER_SERVICE_ID + "}/export-order-service/pdf";
    public static final String EXPORT_ORDER_SERVICE_OPERATION = "/{" + ORDER_SERVICE_ID + "}/export-order-service-operation/pdf";

    public static final String FIND_SERVICES_BY_REGISTER_GROUP = "/find-services-by-register-group/{register_group_id}";
    public static final String FIND_ORDER_SERVICE_ITEMS_BY_REGISTER_GROUP_AND_GUIDE_TRANSFER = "/find-order-service-items-by-register-group-and-guide-transfer";
    public static final String FIND_ALL_REGISTER_BOOKING_ITEM_AVAILABLE_REQUEST_ORDER_SERVICE = "/find-all-register-booking-item-available-request-order-service/{" + REQUEST_ORDER_SERVICE_ID + "}";

    @Autowired
    private OrderServiceUseCase orderServiceUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create order service.",
            description = "Create order service."
    )
    public ApiResponse create(
            @RequestBody OrderServiceCreateRequest request) {
        return ApiResponse.success(
                orderServiceUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find order service by id.",
            description = "Find order service by id."
    )
    public ApiResponse findBy(
            @PathVariable(ORDER_SERVICE_ID) String id) {
        return ApiResponse.success(
                orderServiceUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all order services.",
            description = "Find all order services."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                orderServiceUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit order service.",
            description = "Edit order service."
    )
    public ApiResponse edit(
            @PathVariable(ORDER_SERVICE_ID) String id,
            @RequestBody OrderServiceUpdateRequest request) {
        return ApiResponse.success(
                orderServiceUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete order service.",
            description = "Delete order service."
    )
    public ApiResponse deleteById(
           @PathVariable(ORDER_SERVICE_ID) String id) {
        orderServiceUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate order services.",
            description = "Search all order services."
    )
    public ApiResponse searchPaginate(
            @RequestBody OrderServiceSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                orderServiceUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate order services.",
            description = "Search filter paginate order services."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody OrderServiceSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                orderServiceUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_ORDER_SERVICE_ITEM)
    @Operation(
            summary = "add order service item.",
            description = "Add order service item."
    )
    public ApiResponse addOrderServiceItem(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService,
            @RequestBody OrderServiceItemCreateRequest request) {
        return ApiResponse.success(
                orderServiceUseCase.addOrderServiceItem(request, idOrderService)
        );
    }

    @PutMapping(EDIT_ORDER_SERVICE_ITEM)
    @Operation(
            summary = "Edit order service item .",
            description = "Edit order service item."
    )
    public ApiResponse editOrderServiceItem(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService,
            @PathVariable(ORDER_SERVICE_ITEM_ID) String idOrderServiceItem,
            @RequestBody OrderServiceItemUpdateRequest request) {
        return ApiResponse.success(
                orderServiceUseCase.editOrderServiceItem(idOrderServiceItem, request, idOrderService)
        );
    }

    @DeleteMapping(DELETE_ORDER_SERVICE_ITEM)
    @Operation(
            summary = "Delete order service item.",
            description = "Delete order service item."
    )
    public ApiResponse deleteOrderServiceItem(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService,
            @PathVariable(ORDER_SERVICE_ITEM_ID) String idOrderServiceItem
    ) {
        orderServiceUseCase.deleteOrderServiceItem(idOrderServiceItem, idOrderService);
        return ApiResponse.success();
    }

    @GetMapping(FIND_ORDER_SERVICE_ITEM)
    @Operation(
            summary = "Find order service item by order service.",
            description = "Find order service item by order service."
    )
    public ApiResponse findOrderServiceItemByIdAndOrderService(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService,
            @PathVariable(ORDER_SERVICE_ITEM_ID) String idOrderServiceItem
    ) {
        return ApiResponse.success(
                orderServiceUseCase.findByIdAndOrderService(idOrderServiceItem, idOrderService)
        );
    }

    @GetMapping(FIND_ALL_ORDER_SERVICE_ITEM)
    @Operation(
            summary = "Find all order service item by order service.",
            description = "Find all order service item by order service."
    )
    public ApiResponse findAllOrderServiceItemByOrderService(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService
    ) {
        return ApiResponse.success(
                orderServiceUseCase.findAllOrderServiceItemByOrderService(idOrderService)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state order service.",
            description = "Change state order service."
    )
    public ApiResponse changeStateOrderService(
            @PathVariable(ORDER_SERVICE_ID) String id,
            @RequestBody OrderServiceChangeStateRequest request) {
        return ApiResponse.success(
                orderServiceUseCase.changeState(id, request)
        );
    }

    @PostMapping(GENERATE_ORDER_SERVICE)
    @Operation(
            summary = "Generate order service.",
            description = "Generate order service."
    )
    public ApiResponse generateOrderService(
            @PathVariable(REQUEST_ORDER_SERVICE_ID) String idRequestOrderService) {
        return ApiResponse.success(
                orderServiceUseCase.generate(idRequestOrderService)
        );
    }

    @PostMapping(FIND_ALL_REGISTER_BOOKING_ITEM_AVAILABLE)
    @Operation(
            summary = "Find all register booking item available.",
            description = "Find all register booking item available."
    )
    public ApiResponse findAllRegisterBookingItemAvailable(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService,
            @RequestBody RegisterBookingItemOperatorAvailableRequest request
    ) {
        return ApiResponse.success(
                orderServiceUseCase.findAllRegisterBookingItemOperatorAvailable(request.getIdBookingOperator(), request.getIdOrderService())
        );
    }

    @GetMapping(EXPORT_ORDER_SERVICE)
    @Operation(
            summary = "Export order service.",
            description = "Export order service."
    )
    public ResponseEntity<Resource> exportOrderServicePdf(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService
    ) {
        byte[] reportContent = orderServiceUseCase.exportOrderServicePdf(idOrderService);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("order_service_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_ORDER_SERVICE_OPERATION)
    @Operation(
            summary = "Export order service operation.",
            description = "Export order service operation."
    )
    public ResponseEntity<Resource> exportOrderServiceOperationPdf(
            @PathVariable(ORDER_SERVICE_ID) String idOrderService
    ) {
        byte[] reportContent = orderServiceUseCase.exportOrderServiceOperationPdf(idOrderService);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("order_service_operation_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(FIND_SERVICES_BY_REGISTER_GROUP)
    @Operation(
            summary = "Find all services by register group.",
            description = "Find all services associated with order service items for a given register group."
    )
    public ApiResponse findServicesByRegisterGroup(
            @PathVariable("register_group_id") String registerGroupId
    ) {
        return ApiResponse.success(
                orderServiceUseCase.findAllServiceByRegisterGroup(registerGroupId)
        );
    }

    @PostMapping(FIND_ORDER_SERVICE_ITEMS_BY_REGISTER_GROUP_AND_GUIDE_TRANSFER)
    @Operation(
            summary = "Find all order service items by register group and guide transfer.",
            description = "Find all tour order service items by register group and guide transfer."
    )
    public ApiResponse findOrderServiceItemsByRegisterGroupAndGuideTransfer(
            @RequestBody ServiceExpenseGuideRequest request
    ) {
        List list = orderServiceUseCase.findAllOrderServiceItemByRegisterGroupAndGuideTransfer(request.getRegisterGroupId(), request.getGuideTransferId());
        return ApiResponse.success(
                list
        );
    }

    @PostMapping(FIND_ALL_REGISTER_BOOKING_ITEM_AVAILABLE_REQUEST_ORDER_SERVICE)
    @Operation(
            summary = "Find all register booking item available.",
            description = "Find all register booking item available."
    )
    public ApiResponse findAllRegisterBookingItemAvailableRequestOrderService(
            @PathVariable(REQUEST_ORDER_SERVICE_ID) String idRequestOrderService,
            @RequestBody RegisterBookingItemOperatorAvailableRequest request
    ) {
        return ApiResponse.success(
                orderServiceUseCase.findAllRegisterBookingItemOperatorAvailableByRequestOrderService(request.getIdBookingOperator(), request.getIdRequestOrderService())
        );
    }
}
