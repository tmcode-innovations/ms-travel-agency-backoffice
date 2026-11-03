package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsFinanceUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Analytics Finance", description = "Analytics Finance Reports")
@RestController
@RequestMapping("/analytics/account")
public class AnalyticsFinanceController {

    @Autowired
    private AnalyticsFinanceUseCase analyticsFinanceUseCase;

    // ---- POR FECHA DE EMISION ----

    @PostMapping("/search-filter-paginate-issue-date-proform-invoice")
    @Operation(summary = "Search filter paginate issue date proform invoice report")
    public ApiResponse searchFilterPaginateIssueDateProformInvoice(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateIssueDateProformInvoice(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-issue-date-proform-note-cred")
    @Operation(summary = "Search filter paginate issue date proform note cred report")
    public ApiResponse searchFilterPaginateIssueDateProformNoteCred(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateIssueDateProformNoteCred(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-issue-date-invoice")
    @Operation(summary = "Search filter paginate issue date invoice report")
    public ApiResponse searchFilterPaginateIssueDateInvoice(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateIssueDateInvoice(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-issue-date-boleta")
    @Operation(summary = "Search filter paginate issue date boleta report")
    public ApiResponse searchFilterPaginateIssueDateBoleta(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateIssueDateBoleta(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-issue-date-note-deb")
    @Operation(summary = "Search filter paginate issue date note deb report")
    public ApiResponse searchFilterPaginateIssueDateNoteDeb(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateIssueDateNoteDeb(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-issue-date-note-cred")
    @Operation(summary = "Search filter paginate issue date note cred report")
    public ApiResponse searchFilterPaginateIssueDateNoteCred(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateIssueDateNoteCred(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-issue-date-document-cobranza")
    @Operation(summary = "Search filter paginate issue date document cobranza report")
    public ApiResponse searchFilterPaginateIssueDateDocumentCobranza(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateIssueDateDocumentCobranza(searchFilterRequest));
    }

    // ---- POR FECHA DE CANCELACION ----

    @PostMapping("/search-filter-paginate-cancelation-date-proform-invoice")
    @Operation(summary = "Search filter paginate cancelation date proform invoice report")
    public ApiResponse searchFilterPaginateCancelationDateProformInvoice(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateCancelationDateProformInvoice(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-cancelation-date-proform-note-cred")
    @Operation(summary = "Search filter paginate cancelation date proform note cred report")
    public ApiResponse searchFilterPaginateCancelationDateProformNoteCred(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateCancelationDateProformNoteCred(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-cancelation-date-invoice")
    @Operation(summary = "Search filter paginate cancelation date invoice report")
    public ApiResponse searchFilterPaginateCancelationDateInvoice(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateCancelationDateInvoice(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-cancelation-date-boleta")
    @Operation(summary = "Search filter paginate cancelation date boleta report")
    public ApiResponse searchFilterPaginateCancelationDateBoleta(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateCancelationDateBoleta(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-cancelation-date-note-deb")
    @Operation(summary = "Search filter paginate cancelation date note deb report")
    public ApiResponse searchFilterPaginateCancelationDateNoteDeb(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateCancelationDateNoteDeb(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-cancelation-date-note-cred")
    @Operation(summary = "Search filter paginate cancelation date note cred report")
    public ApiResponse searchFilterPaginateCancelationDateNoteCred(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateCancelationDateNoteCred(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-cancelation-date-document-cobranza")
    @Operation(summary = "Search filter paginate cancelation date document cobranza report")
    public ApiResponse searchFilterPaginateCancelationDateDocumentCobranza(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateCancelationDateDocumentCobranza(searchFilterRequest));
    }

    // ---- PHASE 3 REPORTS ----

    @PostMapping("/search-filter-paginate-register-sale")
    @Operation(summary = "Search filter paginate register sale report")
    public ApiResponse searchFilterPaginateRegisterSale(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateRegisterSale(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-register-sale-operative")
    @Operation(summary = "Search filter paginate register sale operative report")
    public ApiResponse searchFilterPaginateRegisterSaleOperative(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateRegisterSaleOperative(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-register-sale-operative-passenger")
    @Operation(summary = "Search filter paginate register sale operative passenger report")
    public ApiResponse searchFilterPaginateRegisterSaleOperativePassenger(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateRegisterSaleOperativePassenger(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-state-account-customer")
    @Operation(summary = "Search filter paginate state account customer report")
    public ApiResponse searchFilterPaginateStateAccountCustomer(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateStateAccountCustomer(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-group-pending-invoice")
    @Operation(summary = "Search filter paginate group pending invoice report")
    public ApiResponse searchFilterPaginateGroupPendingInvoice(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateGroupPendingInvoice(searchFilterRequest));
    }

    @PostMapping("/search-filter-paginate-proform-ctrl")
    @Operation(summary = "Search filter paginate proform ctrl report")
    public ApiResponse searchFilterPaginateProformCtrl(
            @RequestBody AnalyticsFinanceSearchFilterRequest searchFilterRequest) {
        return ApiResponse.success(analyticsFinanceUseCase.searchFilterPaginateProformCtrl(searchFilterRequest));
    }

    // ---- EXPORT: ISSUE DATE ----

    @PostMapping("/export-proform-invoice-issue-date/pdf")
    @Operation(summary = "Export proform invoice by issue date in PDF")
    public ResponseEntity<Resource> exportProformInvoiceByIssueDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportProformInvoiceByIssueDatePdf(request), "proforma_factura_fecha_emision");
    }

    @PostMapping("/export-proform-invoice-issue-date/excel")
    @Operation(summary = "Export proform invoice by issue date in Excel")
    public ResponseEntity<Resource> exportProformInvoiceByIssueDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportProformInvoiceByIssueDateExcel(request), "proforma_factura_fecha_emision");
    }

    @PostMapping("/export-proform-note-cred-issue-date/pdf")
    @Operation(summary = "Export proform note cred by issue date in PDF")
    public ResponseEntity<Resource> exportProformNoteCredByIssueDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportProformNoteCredByIssueDatePdf(request), "proforma_nota_credito_fecha_emision");
    }

    @PostMapping("/export-proform-note-cred-issue-date/excel")
    @Operation(summary = "Export proform note cred by issue date in Excel")
    public ResponseEntity<Resource> exportProformNoteCredByIssueDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportProformNoteCredByIssueDateExcel(request), "proforma_nota_credito_fecha_emision");
    }

    @PostMapping("/export-invoice-issue-date/pdf")
    @Operation(summary = "Export invoice by issue date in PDF")
    public ResponseEntity<Resource> exportInvoiceByIssueDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportInvoiceByIssueDatePdf(request), "factura_fecha_emision");
    }

    @PostMapping("/export-invoice-issue-date/excel")
    @Operation(summary = "Export invoice by issue date in Excel")
    public ResponseEntity<Resource> exportInvoiceByIssueDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportInvoiceByIssueDateExcel(request), "factura_fecha_emision");
    }

    @PostMapping("/export-boleta-issue-date/pdf")
    @Operation(summary = "Export boleta by issue date in PDF")
    public ResponseEntity<Resource> exportBoletaByIssueDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportBoletaByIssueDatePdf(request), "boleta_fecha_emision");
    }

    @PostMapping("/export-boleta-issue-date/excel")
    @Operation(summary = "Export boleta by issue date in Excel")
    public ResponseEntity<Resource> exportBoletaByIssueDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportBoletaByIssueDateExcel(request), "boleta_fecha_emision");
    }

    @PostMapping("/export-note-deb-issue-date/pdf")
    @Operation(summary = "Export note deb by issue date in PDF")
    public ResponseEntity<Resource> exportNoteDebByIssueDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportNoteDebByIssueDatePdf(request), "nota_debito_fecha_emision");
    }

    @PostMapping("/export-note-deb-issue-date/excel")
    @Operation(summary = "Export note deb by issue date in Excel")
    public ResponseEntity<Resource> exportNoteDebByIssueDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportNoteDebByIssueDateExcel(request), "nota_debito_fecha_emision");
    }

    @PostMapping("/export-note-cred-issue-date/pdf")
    @Operation(summary = "Export note cred by issue date in PDF")
    public ResponseEntity<Resource> exportNoteCredByIssueDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportNoteCredByIssueDatePdf(request), "nota_credito_fecha_emision");
    }

    @PostMapping("/export-note-cred-issue-date/excel")
    @Operation(summary = "Export note cred by issue date in Excel")
    public ResponseEntity<Resource> exportNoteCredByIssueDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportNoteCredByIssueDateExcel(request), "nota_credito_fecha_emision");
    }

    @PostMapping("/export-document-cobranza-issue-date/pdf")
    @Operation(summary = "Export document cobranza by issue date in PDF")
    public ResponseEntity<Resource> exportDocumentCobranzaByIssueDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportDocumentCobranzaByIssueDatePdf(request), "documento_cobranza_fecha_emision");
    }

    @PostMapping("/export-document-cobranza-issue-date/excel")
    @Operation(summary = "Export document cobranza by issue date in Excel")
    public ResponseEntity<Resource> exportDocumentCobranzaByIssueDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportDocumentCobranzaByIssueDateExcel(request), "documento_cobranza_fecha_emision");
    }

    // ---- EXPORT: CANCELATION DATE ----

    @PostMapping("/export-proform-invoice-cancelation-date/pdf")
    @Operation(summary = "Export proform invoice by cancelation date in PDF")
    public ResponseEntity<Resource> exportProformInvoiceByCancelationDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportProformInvoiceByCancelationDatePdf(request), "proforma_factura_fecha_cancelacion");
    }

    @PostMapping("/export-proform-invoice-cancelation-date/excel")
    @Operation(summary = "Export proform invoice by cancelation date in Excel")
    public ResponseEntity<Resource> exportProformInvoiceByCancelationDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportProformInvoiceByCancelationDateExcel(request), "proforma_factura_fecha_cancelacion");
    }

    @PostMapping("/export-proform-note-cred-cancelation-date/pdf")
    @Operation(summary = "Export proform note cred by cancelation date in PDF")
    public ResponseEntity<Resource> exportProformNoteCredByCancelationDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportProformNoteCredByCancelationDatePdf(request), "proforma_nota_credito_fecha_cancelacion");
    }

    @PostMapping("/export-proform-note-cred-cancelation-date/excel")
    @Operation(summary = "Export proform note cred by cancelation date in Excel")
    public ResponseEntity<Resource> exportProformNoteCredByCancelationDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportProformNoteCredByCancelationDateExcel(request), "proforma_nota_credito_fecha_cancelacion");
    }

    @PostMapping("/export-invoice-cancelation-date/pdf")
    @Operation(summary = "Export invoice by cancelation date in PDF")
    public ResponseEntity<Resource> exportInvoiceByCancelationDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportInvoiceByCancelationDatePdf(request), "factura_fecha_cancelacion");
    }

    @PostMapping("/export-invoice-cancelation-date/excel")
    @Operation(summary = "Export invoice by cancelation date in Excel")
    public ResponseEntity<Resource> exportInvoiceByCancelationDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportInvoiceByCancelationDateExcel(request), "factura_fecha_cancelacion");
    }

    @PostMapping("/export-boleta-cancelation-date/pdf")
    @Operation(summary = "Export boleta by cancelation date in PDF")
    public ResponseEntity<Resource> exportBoletaByCancelationDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportBoletaByCancelationDatePdf(request), "boleta_fecha_cancelacion");
    }

    @PostMapping("/export-boleta-cancelation-date/excel")
    @Operation(summary = "Export boleta by cancelation date in Excel")
    public ResponseEntity<Resource> exportBoletaByCancelationDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportBoletaByCancelationDateExcel(request), "boleta_fecha_cancelacion");
    }

    @PostMapping("/export-note-deb-cancelation-date/pdf")
    @Operation(summary = "Export note deb by cancelation date in PDF")
    public ResponseEntity<Resource> exportNoteDebByCancelationDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportNoteDebByCancelationDatePdf(request), "nota_debito_fecha_cancelacion");
    }

    @PostMapping("/export-note-deb-cancelation-date/excel")
    @Operation(summary = "Export note deb by cancelation date in Excel")
    public ResponseEntity<Resource> exportNoteDebByCancelationDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportNoteDebByCancelationDateExcel(request), "nota_debito_fecha_cancelacion");
    }

    @PostMapping("/export-note-cred-cancelation-date/pdf")
    @Operation(summary = "Export note cred by cancelation date in PDF")
    public ResponseEntity<Resource> exportNoteCredByCancelationDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportNoteCredByCancelationDatePdf(request), "nota_credito_fecha_cancelacion");
    }

    @PostMapping("/export-note-cred-cancelation-date/excel")
    @Operation(summary = "Export note cred by cancelation date in Excel")
    public ResponseEntity<Resource> exportNoteCredByCancelationDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportNoteCredByCancelationDateExcel(request), "nota_credito_fecha_cancelacion");
    }

    @PostMapping("/export-document-cobranza-cancelation-date/pdf")
    @Operation(summary = "Export document cobranza by cancelation date in PDF")
    public ResponseEntity<Resource> exportDocumentCobranzaByCancelationDatePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportDocumentCobranzaByCancelationDatePdf(request), "documento_cobranza_fecha_cancelacion");
    }

    @PostMapping("/export-document-cobranza-cancelation-date/excel")
    @Operation(summary = "Export document cobranza by cancelation date in Excel")
    public ResponseEntity<Resource> exportDocumentCobranzaByCancelationDateExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportDocumentCobranzaByCancelationDateExcel(request), "documento_cobranza_fecha_cancelacion");
    }

    // ---- EXPORT: PHASE 3 REPORTS ----

    @PostMapping("/export-register-sale/pdf")
    @Operation(summary = "Export register sale report in PDF")
    public ResponseEntity<Resource> exportRegisterSalePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportRegisterSalePdf(request), "registro_ventas");
    }

    @PostMapping("/export-register-sale/excel")
    @Operation(summary = "Export register sale report in Excel")
    public ResponseEntity<Resource> exportRegisterSaleExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportRegisterSaleExcel(request), "registro_ventas");
    }

    @PostMapping("/export-register-sale-operative/pdf")
    @Operation(summary = "Export register sale operative report in PDF")
    public ResponseEntity<Resource> exportRegisterSaleOperativePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportRegisterSaleOperativePdf(request), "registro_ventas_operativas");
    }

    @PostMapping("/export-register-sale-operative/excel")
    @Operation(summary = "Export register sale operative report in Excel")
    public ResponseEntity<Resource> exportRegisterSaleOperativeExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportRegisterSaleOperativeExcel(request), "registro_ventas_operativas");
    }

    @PostMapping("/export-register-sale-operative-passenger/pdf")
    @Operation(summary = "Export register sale operative passenger report in PDF")
    public ResponseEntity<Resource> exportRegisterSaleOperativePassengerPdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportRegisterSaleOperativePassengerPdf(request), "registro_ventas_operativas_pasajero");
    }

    @PostMapping("/export-register-sale-operative-passenger/excel")
    @Operation(summary = "Export register sale operative passenger report in Excel")
    public ResponseEntity<Resource> exportRegisterSaleOperativePassengerExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportRegisterSaleOperativePassengerExcel(request), "registro_ventas_operativas_pasajero");
    }

    @PostMapping("/export-state-account-customer/pdf")
    @Operation(summary = "Export state account customer report in PDF")
    public ResponseEntity<Resource> exportStateAccountCustomerPdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportStateAccountCustomerPdf(request), "estado_cuenta_cliente");
    }

    @PostMapping("/export-state-account-customer/excel")
    @Operation(summary = "Export state account customer report in Excel")
    public ResponseEntity<Resource> exportStateAccountCustomerExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportStateAccountCustomerExcel(request), "estado_cuenta_cliente");
    }

    @PostMapping("/export-group-pending-invoice/pdf")
    @Operation(summary = "Export group pending invoice report in PDF")
    public ResponseEntity<Resource> exportGroupPendingInvoicePdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportGroupPendingInvoicePdf(request), "grupos_factura_pendiente");
    }

    @PostMapping("/export-group-pending-invoice/excel")
    @Operation(summary = "Export group pending invoice report in Excel")
    public ResponseEntity<Resource> exportGroupPendingInvoiceExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportGroupPendingInvoiceExcel(request), "grupos_factura_pendiente");
    }

    @PostMapping("/export-proform-ctrl/pdf")
    @Operation(summary = "Export proform ctrl report in PDF")
    public ResponseEntity<Resource> exportProformCtrlPdf(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildPdfResponse(analyticsFinanceUseCase.exportProformCtrlPdf(request), "control_proformas");
    }

    @PostMapping("/export-proform-ctrl/excel")
    @Operation(summary = "Export proform ctrl report in Excel")
    public ResponseEntity<Resource> exportProformCtrlExcel(@RequestBody AnalyticsFinanceSearchReportFilterRequest request) {
        return buildExcelResponse(analyticsFinanceUseCase.exportProformCtrlExcel(request), "control_proformas");
    }

    // ---- HELPER METHODS ----

    private ResponseEntity<Resource> buildPdfResponse(byte[] content, String filename) {
        ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".pdf\"")
                .body(resource);
    }

    private ResponseEntity<Resource> buildExcelResponse(byte[] content, String filename) {
        ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".xlsx\"")
                .body(resource);
    }

}
