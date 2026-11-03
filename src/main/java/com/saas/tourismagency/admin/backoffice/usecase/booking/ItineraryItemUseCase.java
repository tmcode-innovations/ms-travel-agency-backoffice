package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.EntityExistBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;

import java.util.List;

public interface ItineraryItemUseCase {

    public ItineraryItemResponse create(ItineraryItemCreateRequest request);

    public ItineraryItemResponse create(ItineraryItemCreateRequest request, RegisterGroupDomain registerGroupDomain);

    public void createMultiple(ItineraryItemCreateRequest request, RegisterGroupDomain registerGroupDomain);

    public void  createAdvanced(ItineraryServiceAdvancedCreateRequest request, RegisterGroupDomain registerGroupDomain);

    public void  editAdvanced(ItineraryServiceAdvancedUpdateRequest request, RegisterGroupDomain registerGroupDomain);

    public void createTour(ItineraryTourItemCreateRequest request, RegisterGroupDomain registerGroupDomain);

    public void createTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, RegisterGroupDomain registerGroupDomain);

    public ItineraryItemResponse edit(String id, ItineraryItemUpdateRequest request);

    public ItineraryItemResponse edit(String id, ItineraryItemUpdateRequest request, RegisterGroupDomain registerGroupDomain);

    public void deleteById(String id);

    public List<ItineraryItemResponse> findAll();

    public ItineraryItemResponse findById(String id);

    public ItineraryItemSearchResponse searchAll(ItineraryItemSearchRequest bankSearchRequest);

    public ItineraryItemSearchResponse searchFilterAll(ItineraryItemSearchFilterRequest bankSearchFilterRequest);

    public List<ItineraryItemResponse> searchAllByRegisterGroup(String idRegisterGroup);

    public ItineraryItemResponse searchByRegisterGroupAndItineraryItem(String idRegisterGroup, String idItineraryItem);

    public ItineraryItemResponse searchByRegisterGroupAndItineraryItemVoucherEgress(String idRegisterGroup, String idItineraryItem);

    public List<ItineraryItemDomain> searchAllDomainByRegisterGroup(String idRegisterGroup);

    public List<ItineraryItemDomain> searchAllDomainByRegisterGroupAndItineraryDate(String idItineraryDate, String idRegisterGroup);

    public List<ItineraryItemDomain> searchAllDomainSimpleDomainCaseGenerateBookingByRegisterGroupAndItineraryDate(String idItineraryDate, String idRegisterGroup);

    public List<ItineraryItemDomain> searchAllDomainByRegisterGroupAndItineraryDateAndSupplierType(String idSupplierType, String idItineraryDate, String idRegisterGroup);

    public List<ItineraryItemDomain> searchAllDomainByRegisterGroupAndSupplier(String idSupplier, String idRegisterGroup);

    public ItineraryItemResponse changeState(String id, ItineraryItemChangeStateRequest request);

    public ItineraryItemResponse changeSupplier(String id, ItineraryItemChangeSupplierRequest request);

    public ItineraryItemResponse changeNote(String id, ItineraryItemChangeNoteRequest request);

    public EntityExistBaseResponse existItineraryItemById(String id);

    public ItineraryItemResponse changeStateInvoice(String id, ItineraryItemChangeStateInvoiceRequest request);

    public ItineraryItemResponse changeDateInvoice(String id, ItineraryItemChangeDateInvoiceRequest request);

    public ItineraryItemResponse changeNoteInvoice(String id, ItineraryItemChangeNoteInvoiceRequest request);
}
