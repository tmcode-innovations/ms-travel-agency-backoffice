package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.fileitinerary.FileItineraryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.filequotation.FileQuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.ItineraryDateAllItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.ItineraryDateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.ItineraryDateUpdateOnlyNameRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.ItineraryModelViewResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuotationUseCase {

    public QuotationResponse create(QuotationCreateRequest request);

    public QuotationResponse create_v0(MultipartFile[] multipartFile);

    public QuotationResponse edit(String id, QuotationUpdateRequest request);

    public QuotationResponse edit_v0(String id, MultipartFile[] multipartFile);

    public void deleteById(String id);

    public List<QuotationResponse> findAll();

    public QuotationResponse findById(String id);

    public QuotationSearchResponse searchAll(QuotationSearchRequest quotationSearchRequest);

    public QuotationSearchResponse searchFilterAll(QuotationSearchFilterRequest quotationSearchFilterRequest);

    public QuotationResponse findByName(String name);

    public QuotationResponse findByIdentify(String identify);

    public PassengerResponse addPassenger(PassengerCreateRequest request, String idQuotation);

    public PassengerResponse editPassenger(String id, PassengerUpdateRequest request, String idQuotation);

    public void deletePassenger(String id, String idQuotation);

    public List<PassengerResponse> findAllPassengerByQuotation(String idQuotation);

    public PassengerResponse findByIdAndQuotation(String id, String idQuotation);

    public List<ItineraryItemResponse> findAllItineraryItemByQuotation(String idQuotation);

    public List<ItineraryDateResponse> findAllItineraryDateByQuotation(String idQuotation);

    public ItineraryItemResponse addItinerary(ItineraryItemCreateRequest request, String idQuotation);

    public void addItineraryMultiple(ItineraryItemCreateRequest request, String idQuotation);

    public void addItineraryAdvanced(ItineraryServiceAdvancedCreateRequest request, String idQuotation);

    public void addItineraryWithTour(ItineraryTourItemCreateRequest request, String idQuotation);

    public void addItineraryWithTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, String idQuotation);

    public ItineraryItemResponse editItinerary(String id, ItineraryItemUpdateRequest request, String idQuotation);

    public void deleteItinerary(String id, String idQuotation);

    public ItineraryItemResponse findByItineraryItemIdAndQuotation(String id, String idQuotation);

    public ItineraryDateResponse findByItineraryDateIdAndQuotation(String id, String idQuotation);

    public List<ItineraryDateAllItemResponse> findAllItineraryDateWithItineraryItemByQuotation(String idQuotation);

    public List<ItineraryModelViewResponse> findAllItineraryDateWithItineraryItemByQuotationModelView(String idQuotation);

    public QuotationResponse changeState(String id, QuotationChangeStateRequest request);

    public ItineraryDateResponse editItineraryDate(String id, ItineraryDateUpdateOnlyNameRequest request, String idQuotation);

    public void deleteItineraryDate(String id, String idQuotation);

    public ByteArrayResource downloadDocumentQuotation(String idQuotation);

    public ByteArrayResource downloadDocumentItinerary(String idQuotation);

    public List<FileItineraryResponse> findAllFileItineraryByQuotation(String idQuotation);

    public List<FileQuotationResponse> findAllFileQuotationByQuotation(String idQuotation);

    public ByteArrayResource downloadDocumentQuotationByFileItinerary(String idQuotation, String idFileItinerary);

    public ByteArrayResource downloadDocumentQuotationByFileQuotation(String idQuotation, String idFileQuotation);

    public void changeDateItineraryDate(String idQuotation, ChangeItineraryDateCrmRequest changeItineraryDateRequest);

    // Add days in the end
    public void changeDateItineraryDay(String idQuotation, ChangeItineraryDayCrmRequest changeItineraryDayRequest);

    // Add days in the begin
    public void changeDateItineraryDayInit(String idQuotation, ChangeItineraryDayCrmRequest changeItineraryDayRequest);

    // Add days in the middle
    public void changeDateItineraryDayMiddle(String idQuotation, ChangeItineraryDayCrmRequest changeItineraryDayRequest);

    public void updateOrderItineraryDate(String idQuotation, UpdateOrderItineraryDateCrmRequest updateOrderItineraryDateRequest);

    public void updateOrderItineraryItem(String idQuotation, UpdateOrderItineraryItemCrmRequest updateOrderItineraryItemRequest);

    public void updateOrderItineraryDateToItem(String idQuotation, UpdateOrderItineraryDateToItemCrmRequest updateOrderItineraryDateToItemRequest);

    public void updateOrderItineraryItemToDate(String idQuotation, UpdateOrderItineraryItemToDateCrmRequest updateOrderItineraryItemToDateRequest);

    public NewDateQuotationResponse getNewDateQuotation(String idQuotation, NewDateCrmRequest newDateRequest);

    // Export Data

    public byte[] exportQuotationTypeGuruPdf(String idQuotation);

    public QuotationResponse getQuotationModificationData(String idQuotation);

    public List<TourResponse> findAllToursByItineraryDate(String idQuotation, String idItineraryDate);

    public void removeToursByItineraryDate(String idQuotation, String idItineraryDate, String idTour);

    // Cost Tour

    public List<CostTourResponse> findAllCostTourByQuotation(String idQuotation);

    public CostTourResponse addCostTour(CostTourCreateRequest request, String idQuotation);

    public CostTourResponse editCostTour(String id, CostTourUpdateRequest request, String idQuotation);

    public void deleteCostTour(String id, String idQuotation);

    public CostTourResponse findByCostTourAndQuotation(String id, String idQuotation);

    // Cost Tour Item

    public CostTourItemResponse addCostTourItem(CostTourItemCreateRequest request, String idQuotation, String idCostTour);

    public CostTourItemResponse editCostTourItem(String id, CostTourItemUpdateRequest request, String idQuotation, String idCostTour);

    public void deleteCostTourItem(String id, String idQuotation, String idCostTour);

    public CostTourItemResponse findByCostTourItemAndQuotationAndCostTour(String id, String idQuotation, String idCostTour);

    public List<CostTourItemResponse> findAllCostTourItemByQuotationAndCostTour(String idQuotation, String idCostTour);


    public void removeToursAndCostTourByQuotation(String idQuotation, String idTour);

    public void removeToursAndCostTourByQuotationAndCostTour(String idQuotation, String idTour, String idCostTour);

    // Cost Tour Service Item

    public CostTourServiceItemResponse addCostTourServiceItem(CostTourServiceItemCreateRequest request, String idQuotation, String idCostTour);

    public CostTourServiceItemResponse editCostTourServiceItem(String id, CostTourServiceItemUpdateRequest request, String idQuotation, String idCostTour);

    public void deleteCostTourServiceItem(String id, String idQuotation, String idCostTour);

    public CostTourServiceItemResponse findByCostTourServiceItemAndQuotationAndCostTour(String id, String idQuotation, String idCostTour);

    public List<CostTourServiceItemResponse> findAllCostTourServiceItemByQuotationAndCostTour(String idQuotation, String idCostTour);

    public List<ItineraryDateResponse> findAllItineraryDateByQuotationFromCostTour(String idQuotation, String idCostTour);

    public void updatePriceQuotation(String idQuotation);

    public QuotationResponse resendFileToSale(String idRegisterGroup);

    public QuotationResponse getQuotationModificationUserData(String idQuotation);
}
