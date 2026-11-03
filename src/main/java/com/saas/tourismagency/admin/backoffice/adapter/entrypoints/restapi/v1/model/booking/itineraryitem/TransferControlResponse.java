package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferControlResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("supplierType")
    SupplierTypeResponse supplierType;

    @JsonProperty("supplier")
    SupplierResponse supplier;

    @JsonProperty("service")
    ServiceResponse service;

    @JsonProperty("tour")
    TourResponse tour;

    @JsonProperty("registerGroup")
    RegisterGroupResponse registerGroup;

    @JsonProperty("itineraryDate")
    ItineraryDateResponse itineraryDate;

    @JsonProperty("orderServiceItem")
    OrderServiceItemResponse orderServiceItem;

    @JsonProperty("itineraryDateTime")
    private LocalDateTime itineraryDateTime;

    @JsonProperty("itineraryDateTimeStr")
    private String itineraryDateTimeStr;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    public static TransferControlResponse from(ItineraryItemDomain domain) {
        TransferControlResponse transferControlResponse = new TransferControlResponse();
        transferControlResponse.setId(domain.getId());
        transferControlResponse.setIndex(domain.getIndex());

        if (domain.getSupplierType() != null &&
                domain.getSupplierType().getId() != null &&
                !domain.getSupplierType().getId().isEmpty()){
            transferControlResponse.setSupplierType(SupplierTypeResponse.from(domain.getSupplierType()));
        }
        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()){
            transferControlResponse.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }
        if (domain.getService() != null &&
                domain.getService().getId() != null &&
                !domain.getService().getId().isEmpty()){
            transferControlResponse.setService(ServiceResponse.from(domain.getService()));
        }
        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            transferControlResponse.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }
        if (domain.getItineraryDate() != null &&
                domain.getItineraryDate().getId() != null &&
                !domain.getItineraryDate().getId().isEmpty()){
            transferControlResponse.setItineraryDate(ItineraryDateResponse.from(domain.getItineraryDate()));
        }
        if (domain.getTour() != null &&
                domain.getTour().getId() != null &&
                !domain.getTour().getId().isEmpty()){
            transferControlResponse.setTour(TourResponse.from(domain.getTour()));
        }
        if (domain.getOrderServiceItem() != null &&
                domain.getOrderServiceItem().getId() != null &&
                !domain.getOrderServiceItem().getId().isEmpty()){
            transferControlResponse.setOrderServiceItem(OrderServiceItemResponse.from(domain.getOrderServiceItem()));
        }
        transferControlResponse.setItineraryDateTime(domain.getItineraryDateTime());
        transferControlResponse.setItineraryDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getItineraryDateTime()));
        transferControlResponse.setObservation(domain.getObservation());
        transferControlResponse.setPaxTotal(domain.getCantPax());
        return transferControlResponse;
    }
}
