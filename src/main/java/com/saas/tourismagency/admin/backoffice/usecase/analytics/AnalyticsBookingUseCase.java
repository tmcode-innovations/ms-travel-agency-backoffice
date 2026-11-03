package com.saas.tourismagency.admin.backoffice.usecase.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchResponse;

public interface AnalyticsBookingUseCase {

    AnalyticsBookingSearchResponse<?> searchFilterPaginateItinerary(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateGroup(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateBibleActivity(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginatePassenger(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateHotel(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateOperator(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateRestaurant(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateTransport(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateAirLine(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateGroupByProgram(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateGroupByActivity(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateHotelExpiration(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsForeignOperator(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsCountry(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsResponsible(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsHotel(AnalyticsBookingSearchFilterRequest request);
    AnalyticsBookingSearchResponse<?> searchFilterPaginateGuide(AnalyticsBookingSearchFilterRequest request);

    byte[] exportItineraryPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportItineraryExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGroupPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGroupExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportBibleActivityPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportBibleActivityExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportPassengerPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportPassengerExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportHotelPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportHotelExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportOperatorPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportOperatorExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportRestaurantPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportRestaurantExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportTransportPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportTransportExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportAirLinePdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportAirLineExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGroupByProgramPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGroupByProgramExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGroupByActivityPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGroupByActivityExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportHotelExpirationPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportHotelExpirationExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsForeignOperatorPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsForeignOperatorExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsCountryPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsCountryExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsResponsiblePdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsResponsibleExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsHotelPdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportStatisticsHotelExcel(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGuidePdf(AnalyticsBookingSearchReportFilterRequest request);
    byte[] exportGuideExcel(AnalyticsBookingSearchReportFilterRequest request);

}
