package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsBookingUseCase;
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

@Slf4j
@Tag(name = "Analytics Booking", description = "Analytics Booking Reports")
@RestController
@RequestMapping("/analytics/booking")
public class AnalyticsBookingController {

    @Autowired
    private AnalyticsBookingUseCase analyticsBookingUseCase;

    @PostMapping("/search-filter-paginate-itinerary")
    @Operation(summary = "Search filter paginate itinerary report")
    public ApiResponse searchFilterPaginateItinerary(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateItinerary(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-group")
    @Operation(summary = "Search filter paginate group report")
    public ApiResponse searchFilterPaginateGroup(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateGroup(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-bible-activity")
    @Operation(summary = "Search filter paginate bible general activity report")
    public ApiResponse searchFilterPaginateBibleActivity(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateBibleActivity(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-passenger")
    @Operation(summary = "Search filter paginate passenger report")
    public ApiResponse searchFilterPaginatePassenger(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginatePassenger(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-hotel")
    @Operation(summary = "Search filter paginate hotel report")
    public ApiResponse searchFilterPaginateHotel(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateHotel(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-operator")
    @Operation(summary = "Search filter paginate operator report")
    public ApiResponse searchFilterPaginateOperator(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateOperator(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-restaurant")
    @Operation(summary = "Search filter paginate restaurant report")
    public ApiResponse searchFilterPaginateRestaurant(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateRestaurant(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-transport")
    @Operation(summary = "Search filter paginate transport report")
    public ApiResponse searchFilterPaginateTransport(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateTransport(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-air-line")
    @Operation(summary = "Search filter paginate air line report")
    public ApiResponse searchFilterPaginateAirLine(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateAirLine(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-group-by-program")
    @Operation(summary = "Search filter paginate group by program report")
    public ApiResponse searchFilterPaginateGroupByProgram(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateGroupByProgram(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-group-by-activity")
    @Operation(summary = "Search filter paginate group by activity report")
    public ApiResponse searchFilterPaginateGroupByActivity(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateGroupByActivity(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-hotel-expiration")
    @Operation(summary = "Search filter paginate hotel expiration dates report")
    public ApiResponse searchFilterPaginateHotelExpiration(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateHotelExpiration(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-statistics-foreign-operator")
    @Operation(summary = "Search filter paginate statistics by foreign operator report")
    public ApiResponse searchFilterPaginateStatisticsForeignOperator(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateStatisticsForeignOperator(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-statistics-country")
    @Operation(summary = "Search filter paginate statistics by country report")
    public ApiResponse searchFilterPaginateStatisticsCountry(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateStatisticsCountry(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-statistics-responsible")
    @Operation(summary = "Search filter paginate statistics by responsible report")
    public ApiResponse searchFilterPaginateStatisticsResponsible(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateStatisticsResponsible(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-statistics-hotel")
    @Operation(summary = "Search filter paginate statistics by hotel report")
    public ApiResponse searchFilterPaginateStatisticsHotel(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateStatisticsHotel(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-guide")
    @Operation(summary = "Search filter paginate guide report")
    public ApiResponse searchFilterPaginateGuide(
            @RequestBody AnalyticsBookingSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsBookingUseCase.searchFilterPaginateGuide(searchFilterRequest));
    }

    // ===================== EXPORTS =====================

    private ResponseEntity<Resource> buildPdfResponse(byte[] content, String filename) {
        ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(filename + ".pdf").build().toString())
                .body(resource);
    }

    private ResponseEntity<Resource> buildExcelResponse(byte[] content, String filename) {
        ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(filename + ".xlsx").build().toString())
                .body(resource);
    }

    @PostMapping("/export-itinerary/pdf")
    @Operation(summary = "Export itinerary report in PDF")
    public ResponseEntity<Resource> exportItineraryPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportItineraryPdf(request), "reporte_itinerario");
    }

    @PostMapping("/export-itinerary/excel")
    @Operation(summary = "Export itinerary report in Excel")
    public ResponseEntity<Resource> exportItineraryExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportItineraryExcel(request), "reporte_itinerario");
    }

    @PostMapping("/export-group/pdf")
    @Operation(summary = "Export group report in PDF")
    public ResponseEntity<Resource> exportGroupPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportGroupPdf(request), "reporte_grupos");
    }

    @PostMapping("/export-group/excel")
    @Operation(summary = "Export group report in Excel")
    public ResponseEntity<Resource> exportGroupExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportGroupExcel(request), "reporte_grupos");
    }

    @PostMapping("/export-bible-activity/pdf")
    @Operation(summary = "Export bible activity report in PDF")
    public ResponseEntity<Resource> exportBibleActivityPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportBibleActivityPdf(request), "biblia_actividades");
    }

    @PostMapping("/export-bible-activity/excel")
    @Operation(summary = "Export bible activity report in Excel")
    public ResponseEntity<Resource> exportBibleActivityExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportBibleActivityExcel(request), "biblia_actividades");
    }

    @PostMapping("/export-passenger/pdf")
    @Operation(summary = "Export passenger report in PDF")
    public ResponseEntity<Resource> exportPassengerPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportPassengerPdf(request), "reporte_pasajeros");
    }

    @PostMapping("/export-passenger/excel")
    @Operation(summary = "Export passenger report in Excel")
    public ResponseEntity<Resource> exportPassengerExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportPassengerExcel(request), "reporte_pasajeros");
    }

    @PostMapping("/export-hotel/pdf")
    @Operation(summary = "Export hotel report in PDF")
    public ResponseEntity<Resource> exportHotelPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportHotelPdf(request), "reporte_alojamiento");
    }

    @PostMapping("/export-hotel/excel")
    @Operation(summary = "Export hotel report in Excel")
    public ResponseEntity<Resource> exportHotelExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportHotelExcel(request), "reporte_alojamiento");
    }

    @PostMapping("/export-operator/pdf")
    @Operation(summary = "Export operator report in PDF")
    public ResponseEntity<Resource> exportOperatorPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportOperatorPdf(request), "reporte_operador");
    }

    @PostMapping("/export-operator/excel")
    @Operation(summary = "Export operator report in Excel")
    public ResponseEntity<Resource> exportOperatorExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportOperatorExcel(request), "reporte_operador");
    }

    @PostMapping("/export-restaurant/pdf")
    @Operation(summary = "Export restaurant report in PDF")
    public ResponseEntity<Resource> exportRestaurantPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportRestaurantPdf(request), "reporte_restaurante");
    }

    @PostMapping("/export-restaurant/excel")
    @Operation(summary = "Export restaurant report in Excel")
    public ResponseEntity<Resource> exportRestaurantExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportRestaurantExcel(request), "reporte_restaurante");
    }

    @PostMapping("/export-transport/pdf")
    @Operation(summary = "Export transport report in PDF")
    public ResponseEntity<Resource> exportTransportPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportTransportPdf(request), "reporte_transporte");
    }

    @PostMapping("/export-transport/excel")
    @Operation(summary = "Export transport report in Excel")
    public ResponseEntity<Resource> exportTransportExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportTransportExcel(request), "reporte_transporte");
    }

    @PostMapping("/export-air-line/pdf")
    @Operation(summary = "Export airline report in PDF")
    public ResponseEntity<Resource> exportAirLinePdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportAirLinePdf(request), "reporte_aerolinea");
    }

    @PostMapping("/export-air-line/excel")
    @Operation(summary = "Export airline report in Excel")
    public ResponseEntity<Resource> exportAirLineExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportAirLineExcel(request), "reporte_aerolinea");
    }

    @PostMapping("/export-group-by-program/pdf")
    @Operation(summary = "Export group by program report in PDF")
    public ResponseEntity<Resource> exportGroupByProgramPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportGroupByProgramPdf(request), "grupos_por_programa");
    }

    @PostMapping("/export-group-by-program/excel")
    @Operation(summary = "Export group by program report in Excel")
    public ResponseEntity<Resource> exportGroupByProgramExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportGroupByProgramExcel(request), "grupos_por_programa");
    }

    @PostMapping("/export-group-by-activity/pdf")
    @Operation(summary = "Export group by activity report in PDF")
    public ResponseEntity<Resource> exportGroupByActivityPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportGroupByActivityPdf(request), "grupos_por_actividad");
    }

    @PostMapping("/export-group-by-activity/excel")
    @Operation(summary = "Export group by activity report in Excel")
    public ResponseEntity<Resource> exportGroupByActivityExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportGroupByActivityExcel(request), "grupos_por_actividad");
    }

    @PostMapping("/export-hotel-expiration/pdf")
    @Operation(summary = "Export hotel expiration report in PDF")
    public ResponseEntity<Resource> exportHotelExpirationPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportHotelExpirationPdf(request), "alojamiento_limite");
    }

    @PostMapping("/export-hotel-expiration/excel")
    @Operation(summary = "Export hotel expiration report in Excel")
    public ResponseEntity<Resource> exportHotelExpirationExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportHotelExpirationExcel(request), "alojamiento_limite");
    }

    @PostMapping("/export-statistics-foreign-operator/pdf")
    @Operation(summary = "Export statistics foreign operator report in PDF")
    public ResponseEntity<Resource> exportStatisticsForeignOperatorPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportStatisticsForeignOperatorPdf(request), "estadistica_operador");
    }

    @PostMapping("/export-statistics-foreign-operator/excel")
    @Operation(summary = "Export statistics foreign operator report in Excel")
    public ResponseEntity<Resource> exportStatisticsForeignOperatorExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportStatisticsForeignOperatorExcel(request), "estadistica_operador");
    }

    @PostMapping("/export-statistics-country/pdf")
    @Operation(summary = "Export statistics country report in PDF")
    public ResponseEntity<Resource> exportStatisticsCountryPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportStatisticsCountryPdf(request), "estadistica_pais");
    }

    @PostMapping("/export-statistics-country/excel")
    @Operation(summary = "Export statistics country report in Excel")
    public ResponseEntity<Resource> exportStatisticsCountryExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportStatisticsCountryExcel(request), "estadistica_pais");
    }

    @PostMapping("/export-statistics-responsible/pdf")
    @Operation(summary = "Export statistics responsible report in PDF")
    public ResponseEntity<Resource> exportStatisticsResponsiblePdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportStatisticsResponsiblePdf(request), "estadistica_responsable");
    }

    @PostMapping("/export-statistics-responsible/excel")
    @Operation(summary = "Export statistics responsible report in Excel")
    public ResponseEntity<Resource> exportStatisticsResponsibleExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportStatisticsResponsibleExcel(request), "estadistica_responsable");
    }

    @PostMapping("/export-statistics-hotel/pdf")
    @Operation(summary = "Export statistics hotel report in PDF")
    public ResponseEntity<Resource> exportStatisticsHotelPdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportStatisticsHotelPdf(request), "estadistica_hotel");
    }

    @PostMapping("/export-statistics-hotel/excel")
    @Operation(summary = "Export statistics hotel report in Excel")
    public ResponseEntity<Resource> exportStatisticsHotelExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportStatisticsHotelExcel(request), "estadistica_hotel");
    }

    @PostMapping("/export-guide/pdf")
    @Operation(summary = "Export guide report in PDF")
    public ResponseEntity<Resource> exportGuidePdf(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsBookingUseCase.exportGuidePdf(request), "reporte_guias");
    }

    @PostMapping("/export-guide/excel")
    @Operation(summary = "Export guide report in Excel")
    public ResponseEntity<Resource> exportGuideExcel(@RequestBody AnalyticsBookingSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsBookingUseCase.exportGuideExcel(request), "reporte_guias");
    }

}
