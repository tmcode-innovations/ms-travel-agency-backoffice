package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.customer.CustomerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.service.ServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateServiceInvoiceEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferControlByTourBibleResponse {

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

    @JsonProperty("customer")
    CustomerResponse customer;

    @JsonProperty("registerGroup")
    RegisterGroupResponse registerGroup;

    @JsonProperty("itineraryDate")
    ItineraryDateResponse itineraryDate;

    @JsonProperty("itineraryDateTime")
    private LocalDateTime itineraryDateTime;

    @JsonProperty("itineraryDateTimeStr")
    private String itineraryDateTimeStr;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("lodgingForOperatorStr")
    private String lodgingForOperatorStr;

    @JsonProperty("paxTotal")
    private Integer paxTotal;

    @JsonProperty("stateItineraryService")
    private String stateItineraryService;

    @JsonProperty("stateItineraryServiceDescription")
    private String stateItineraryServiceDescription;

    @JsonProperty("note")
    private String note;

    // For Finance Exit Voucher

    @JsonProperty("stateServiceInvoice")
    private String stateServiceInvoice;

    @JsonProperty("stateServiceInvoiceDescription")
    private String stateServiceInvoiceDescription;

    @JsonProperty("noteInvoice")
    private String noteInvoice;

    @JsonProperty("documentDateInvoice")
    private LocalDate documentDateInvoice;

    @JsonProperty("documentDateInvoiceStr")
    private String documentDateInvoiceStr;

    public static TransferControlByTourBibleResponse from(ItineraryItemDomain domain) {
        TransferControlByTourBibleResponse transferControlResponse = new TransferControlByTourBibleResponse();
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
        if (domain.getCustomer() != null &&
                domain.getCustomer().getId() != null &&
                !domain.getCustomer().getId().isEmpty()){
            transferControlResponse.setCustomer(CustomerResponse.from(domain.getCustomer()));
        }
        transferControlResponse.setItineraryDateTime(domain.getItineraryDateTime());
        transferControlResponse.setItineraryDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeShort(domain.getItineraryDateTime()));
        transferControlResponse.setObservation(domain.getObservation());
        transferControlResponse.setLodgingForOperatorStr(domain.getLodgingForOperatorStr());
        if (domain.getPaxTotal() != null){
            transferControlResponse.setPaxTotal(domain.getPaxTotal());
        }else if (domain.getPaxAdultTotal() != null){
            transferControlResponse.setPaxTotal(domain.getPaxAdultTotal());
        }
        if (domain.getStateItineraryService() != null && !domain.getStateItineraryService().isEmpty()){
            transferControlResponse.setStateItineraryService(domain.getStateItineraryService());
            StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                    .map(StateBookingEnum.class)
                    .stream().filter(
                            stateBookingModel -> stateBookingModel.name().equals(domain.getStateItineraryService()))
                    .findFirst().get();
            transferControlResponse.setStateItineraryServiceDescription(stateBookingModelData.description());
        }
        transferControlResponse.setNote(domain.getNote());

        if (domain.getStateServiceInvoice() != null && !domain.getStateServiceInvoice().isEmpty()){
            transferControlResponse.setStateServiceInvoice(domain.getStateServiceInvoice());
            StateServiceInvoiceEnum.StateServiceInvoiceModel stateServiceInvoiceModelData = EnumMappeableService
                    .map(StateServiceInvoiceEnum.class)
                    .stream().filter(
                            stateServiceInvoiceModel -> stateServiceInvoiceModel.name().equals(domain.getStateServiceInvoice()))
                    .findFirst().get();
            transferControlResponse.setStateServiceInvoiceDescription(stateServiceInvoiceModelData.description());
        }
        transferControlResponse.setNoteInvoice(domain.getNoteInvoice());
        transferControlResponse.setDocumentDateInvoice(domain.getDocumentDateInvoice());
        transferControlResponse.setDocumentDateInvoiceStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDocumentDateInvoice()));

        return transferControlResponse;
    }
}
