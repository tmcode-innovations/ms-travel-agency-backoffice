package com.saas.tourismagency.admin.backoffice.usecase.booking;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateAllItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.ItineraryModelViewResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinginstitution.RegisterBookingInstitutionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.RegisterBookingLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.RegisterBookingOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.ExportCuponTypePassengerBookingEnum;

import java.util.List;

public interface RegisterGroupUseCase {

    public RegisterGroupResponse create(RegisterGroupCreateRequest request);

    public RegisterGroupResponse opened_v0(String id, RegisterGroupUpdateRequest request);

    public RegisterGroupResponse edit(String id, RegisterGroupUpdateRequest request);

    public void deleteById(String id);

    public List<RegisterGroupResponse> findAll();

    public RegisterGroupResponse findById(String id);

    public RegisterGroupSearchResponse searchAll(RegisterGroupSearchRequest registerGroupSearchRequest);

    public RegisterGroupSearchResponse searchFilterAll(RegisterGroupSearchFilterRequest registerGroupSearchFilterRequest);

    public RegisterGroupResponse findByName(String name);

    public RegisterGroupResponse findByIdentify(String identify);

    public PassengerGroupResponse addPassengerGroup(PassengerGroupCreateRequest request, String idRegisterGroup);

    public PassengerGroupResponse editPassengerGroup(String id, PassengerGroupUpdateRequest request, String idRegisterGroup);

    public void deletePassengerGroup(String id, String idRegisterGroup);

    public List<PassengerGroupResponse> findAllPassengerGroupByRegisterGroup(String idRegisterGroup);

    public PassengerGroupResponse findByIdAndRegisterGroup(String id, String idRegisterGroup);

    public List<ItineraryItemResponse> findAllItineraryItemByRegisterGroup(String idRegisterGroup);

    public List<ItineraryDateResponse> findAllItineraryDateByRegisterGroup(String idRegisterGroup);

    public ItineraryItemResponse addItinerary(ItineraryItemCreateRequest request, String idRegisterGroup);

    public void addItineraryMultiple(ItineraryItemCreateRequest request, String idRegisterGroup);

    public void addItineraryAdvanced(ItineraryServiceAdvancedCreateRequest request, String idRegisterGroup);

    public void editItineraryAdvanced(ItineraryServiceAdvancedUpdateRequest request, String idRegisterGroup);

    public void addItineraryWithTour(ItineraryTourItemCreateRequest request, String idRegisterGroup);

    public void addItineraryWithTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, String idRegisterGroup);

    public ItineraryItemResponse editItinerary(String id, ItineraryItemUpdateRequest request, String idRegisterGroup);

    public void deleteItinerary(String id, String idRegisterGroup);

    public ItineraryItemResponse findByItineraryItemIdAndRegisterGroup(String id, String idRegisterGroup);

    public ItineraryItemResponse findByItineraryItemIdAndRegisterGroupVoucherEgress(String id, String idRegisterGroup);

    public ItineraryDateResponse findByItineraryDateIdAndRegisterGroup(String id, String idRegisterGroup);

    public List<ItineraryDateAllItemResponse> findAllItineraryDateWithItineraryItemByRegisterGroup(String idRegisterGroup);

    public List<ItineraryModelViewResponse> findAllItineraryDateWithItineraryItemByRegisterGroupModelView(String idRegisterGroup);

    public RegisterGroupResponse changeState(String id, RegisterGroupChangeStateRequest request);

    public RegisterGroupResponse resendQuotationToBooking(String identify);

    public RegisterGroupResponse resendQuotationToBookingUpdateAdministration(String identify);

    public ItineraryDateResponse editItineraryDate(String id, ItineraryDateUpdateOnlyNameRequest request, String idRegisterGroup);

    void deleteItineraryDate(String id, String idRegisterGroup);

    public WrapSupplierRegisterBookingAllResponse findAllRegisterBookingGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request);

    public WrapSupplierRegisterBookingOpeAllResponse findAllRegisterBookingOperatorGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request);

    public WrapSupplierRegisterBookingRestAllResponse findAllRegisterBookingRestaurantGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request);

    public WrapSupplierRegisterBookingLodgingAllResponse findAllRegisterBookingLodgingGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request);

    public WrapSupplierRegisterBookingLodgerAllResponse findAllRegisterBookingLodgerGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request);

    public WrapSupplierRegisterBookingGlampingAllResponse findAllRegisterBookingGlampingGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request);

    public WrapSupplierRegisterBookingInstAllResponse findAllRegisterBookingInstitutionGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request);

    public RegisterBookingLodgingResponse generateRegisterBookingLodging(String idRegisterGroup, String idSupplier);

    public RegisterBookingLodgerResponse generateRegisterBookingLodger(String idRegisterGroup, String idSupplier);

    public RegisterBookingOperatorResponse generateRegisterBookingOperator(String idRegisterGroup, String idSupplier);

    public RegisterBookingRestaurantResponse generateRegisterBookingRestaurant(String idRegisterGroup, String idSupplier);

    public RegisterBookingGlampingResponse generateRegisterBookingGlamping(String idRegisterGroup, String idSupplier);

    public RegisterBookingInstitutionResponse generateRegisterBookingInstitution(String idRegisterGroup, String idSupplier);

    public List<WrapNoCuponCheckEntityResponse> findAllCuponByRegisterGroup(String idRegisterGroup);

    public List<WrapNoCuponCheckEntityResponse> findAllCuponByRegisterGroup(String idRegisterGroup, RegisterGroupAllCuponSearchRequest request);

    public List<WrapNoCuponCheckEntityResponse> findAllCuponByRegisterGroup(String idRegisterGroup, RegisterGroupAllCuponSearchRequest request, String exportCuponTypePassengerBooking);

    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingOperator(String idRegisterGroup, String idRegisterBooking);

    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingRestaurant(String idRegisterGroup, String idRegisterBooking);

    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingLodging(String idRegisterGroup, String idRegisterBooking);

    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingGlamping(String idRegisterGroup, String idRegisterBooking);

    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingInstitution(String idRegisterGroup, String idRegisterBooking);

    public void changeDateItineraryDate(String idRegisterGroup, ChangeItineraryDateRequest changeItineraryDateRequest);

    // Add days in the end
    public void changeDateItineraryDay(String idRegisterGroup, ChangeItineraryDayRequest changeItineraryDayRequest);

    // Add days in the begin
    public void changeDateItineraryDayInit(String idRegisterGroup, ChangeItineraryDayRequest changeItineraryDayRequest);

    // Add days in the middle
    public void changeDateItineraryDayMiddle(String idRegisterGroup, ChangeItineraryDayRequest changeItineraryDayRequest);

    public void updateOrderItineraryDate(String idRegisterGroup, UpdateOrderItineraryDateRequest updateOrderItineraryDateRequest);

    public void updateOrderItineraryItem(String idRegisterGroup, UpdateOrderItineraryItemRequest updateOrderItineraryItemRequest);

    public void updateOrderItineraryDateToItem(String idRegisterGroup, UpdateOrderItineraryDateToItemRequest updateOrderItineraryDateToItemRequest);

    public void updateOrderItineraryItemToDate(String idRegisterGroup, UpdateOrderItineraryItemToDateRequest updateOrderItineraryItemToDateRequest);

    public NewDateRegisterGroupResponse getNewDateRegisterGroup(String idRegisterGroup, NewDateRequest newDateRequest);

    // Export Data

    public byte[] exportPassengerGroupPdf(String idRegisterGroup);

    public byte[] exportPassengerGroupXls(String idRegisterGroup);

    public byte[] exportAllCuponPdf(String idRegisterGroup, WrapCuponAllRequest wrapCuponAllRequest);

    public byte[] exportAllCuponPdf(String idRegisterGroup, WrapCuponAllRequest wrapCuponAllRequest, String exportCuponTypePassengerBooking);

    public byte[] exportAllItineraryPdf(String idRegisterGroup);

    public byte[] exportCantByResponsiblePdf(RegisterGroupSearchFilterRequest registerGroupSearchFilterRequest);

    public List<TourResponse> findAllToursByItineraryDate(String idRegisterGroup, String idItineraryDate);

    public void removeToursByItineraryDate(String idRegisterGroup, String idItineraryDate, String idTour);

    public List<SimpleBaseResponse> findAllSimple();

    public List<RegisterGroupResponse> findAllSimpleAperture();

    public List<RegisterGroupResponse> findAllSimpleApertureDocumentFinance();

    public List<RegisterGroupResponse> findAllSimpleApertureDocumentFinanceByGuideTransferDrive(String idGuideTransferDrive);

    public List<RegisterGroupGuideTransferDriveResponse> findAllGuideTransferDriveRegisterGroup();

    public List<RegisterGroupResponse> findAllSimpleApertureVoucherIngress();

    public List<RegisterGroupResponse> findAllSimpleApertureVoucherEgress();

    public List<RegisterGroupResponse> findAllSimpleApertureReport();

    public List<RegisterGroupResponse> findAllSimpleApertureVoucherIngressByCustomer(String idCustomer);

    public List<RegisterGroupResponse> findAllSimpleApertureVoucherIngressBySupplier(String idSupplier);

    // Cost Tour

    public List<CostTourResponse> findAllCostTourByQuotation(String idRegisterGroup);

    public CostTourResponse addCostTour(CostTourCreateRequest request, String idRegisterGroup);

    public CostTourResponse editCostTour(String id, CostTourUpdateRequest request, String idRegisterGroup);

    public void deleteCostTour(String id, String idRegisterGroup);

    public CostTourResponse findByCostTourAndRegisterGroup(String id, String idRegisterGroup);

    // Cost Tour Item

    public CostTourItemResponse addCostTourItem(CostTourItemCreateRequest request, String idRegisterGroup, String idCostTour);

    public CostTourItemResponse editCostTourItem(String id, CostTourItemUpdateRequest request, String idRegisterGroup, String idCostTour);

    public void deleteCostTourItem(String id, String idRegisterGroup, String idCostTour);

    public CostTourItemResponse findByCostTourItemAndRegisterGroupAndCostTour(String id, String idRegisterGroup, String idCostTour);

    public List<CostTourItemResponse> findAllCostTourItemByRegisterGroupAndCostTour(String idRegisterGroup, String idCostTour);


    public void removeToursAndCostTourByRegisterGroup(String idRegisterGroup, String idTour);

    public void removeToursAndCostTourByRegisterGroupAndCostTour(String idRegisterGroup, String idTour, String idCostTour);

    public void generateAllRegisterBooking(String idRegisterGroup);


    // Cost Tour Service Item

    public CostTourServiceItemResponse addCostTourServiceItem(CostTourServiceItemCreateRequest request, String idRegisterGroup, String idCostTour);

    public CostTourServiceItemResponse editCostTourServiceItem(String id, CostTourServiceItemUpdateRequest request, String idRegisterGroup, String idCostTour);

    public void deleteCostTourServiceItem(String id, String idRegisterGroup, String idCostTour);

    public CostTourServiceItemResponse findByCostTourServiceItemAndRegisterGroupAndCostTour(String id, String idRegisterGroup, String idCostTour);

    public List<CostTourServiceItemResponse> findAllCostTourServiceItemByRegisterGroupAndCostTour(String idRegisterGroup, String idCostTour);

    public List<ItineraryDateResponse> findAllItineraryDateByRegisterGroupFromCostTour(String idRegisterGroup, String idCostTour);

    // Supplier

    public List<SupplierResponse> findAllSupplierByRegisterGroupAndSupplierType(String idRegisterGroup, String idSupplierType);

    public List<ItineraryItemResponse> findAllServiceRequest(String idRegisterGroup, String idSupplier);

    public ItineraryItemResponse findAllServiceRequestByItineraryItem(String idRegisterGroup, String idItineraryItem);

    public void changeStateAllBookingToAnnulled(String idRegisterGroup);

    // Change Cant

    public void changeCantPaxAdult(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest);

    public void changeCantPaxChild(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest);

    public void changeCantTc(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest);

    public void changeCantTcLocal(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest);

    public WrapItineraryEditAdvancedResponse getItineraryAdvancedToEdit(String idRegisterGroup);

    // Account Relation
    public List<RegisterGroupResponse> findAllRegisterGroupNotInCostSheetTwo();

    public List<RegisterGroupResponse> findAllRegisterGroupForCostSheetTwo();

    public RegisterGroupResponse getRegisterGroupModificationUserData(String idRegisterGroup);
}
