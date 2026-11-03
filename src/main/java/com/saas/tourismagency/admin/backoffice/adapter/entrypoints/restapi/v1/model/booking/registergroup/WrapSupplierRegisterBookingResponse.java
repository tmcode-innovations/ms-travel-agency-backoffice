package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice.OrderServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice.RequestOrderServiceResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapSupplierRegisterBookingResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("supplierType")
    private SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("dateOrder")
    private LocalDateTime dateOrder;

    @JsonProperty("departmentRegion")
    private DepartmentRegionResponse departmentRegion;

    @JsonProperty("stateBooking")
    private String stateBooking;

    @JsonProperty("stateBookingDescription")
    private String stateBookingDescription;

    @JsonProperty("registerBookingBase")
    private RegisterBookingBaseResponse registerBookingBase;

    @JsonProperty("generateRequestOrderService")
    private Boolean generateRequestOrderService;

    @JsonProperty("requestOrderService")
    private RequestOrderServiceResponse requestOrderServiceResponse;

    @JsonProperty("listOrderService")
    private List<OrderServiceResponse> listOrderService;

    @JsonProperty("stateRequestOrderService")
    private String stateRequestOrderService;

    @JsonProperty("stateRequestOrderServiceDescription")
    private String stateRequestOrderServiceDescription;

    @JsonProperty("annulledBookingOperator")
    private Boolean annulledBookingOperator;

    @Getter(AccessLevel.NONE)
    private Boolean hasOrderServices;

    @JsonProperty("hasOrderServices")
    public Boolean getHasOrderServices(){
        Boolean result = false;
        if (listOrderService != null && !listOrderService.isEmpty()){
            result = true;
        }
        return result;
    }
}
