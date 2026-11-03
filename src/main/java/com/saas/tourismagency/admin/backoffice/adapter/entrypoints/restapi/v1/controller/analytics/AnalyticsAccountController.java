package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsAccountUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.analytics.AnalyticsAccountController.ANALYTICS;

@Slf4j
@Tag(name = "Analytics", description = "Analytics")
@RestController
@RequestMapping(ANALYTICS)
public class AnalyticsAccountController {

    public static final String ANALYTICS = "/analytics";

    public static final String ACCOUNT = "/account";

    public static final String SEARCH_FILTER_PAGINATE_VOUCHER_PURCHASE_SUPPLIER = ACCOUNT + "/search-filter-paginate-voucher-purchase-supplier";

    public static final String EXPORT_VOUCHER_PURCHASE_SUPPLIER_PDF = ACCOUNT + "/export-voucher-purchase-supplier/pdf";
    public static final String EXPORT_VOUCHER_PURCHASE_SUPPLIER_EXCEL = ACCOUNT + "/export-voucher-purchase-supplier/excel";

    @Autowired
    private AnalyticsAccountUseCase analyticsAccountUseCase;


    @PostMapping(SEARCH_FILTER_PAGINATE_VOUCHER_PURCHASE_SUPPLIER)
    @Operation(
            summary = "Search filter paginate account payments.",
            description = "Search filter paginate account payments."
    )
    public ApiResponse searchFilterAllVoucherPurchaseBySupplier(
            @RequestBody VoucherEgressDetailSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                analyticsAccountUseCase.searchFilterAllVoucherPurchaseBySupplier(searchFilterRequest)
        );
    }

    @PostMapping(EXPORT_VOUCHER_PURCHASE_SUPPLIER_PDF)
    @Operation(
            summary = "Export purchase supplier in pdf.",
            description = "Export purchase supplier in pdf."
    )
    public ResponseEntity<Resource> exportPurchaseSupplierPdf(
            @RequestBody VoucherEgressDetailSearchReportFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = analyticsAccountUseCase.exportVoucherPurchaseBySupplierPdf(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("voucher_purchase_supplier_report.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(EXPORT_VOUCHER_PURCHASE_SUPPLIER_EXCEL)
    @Operation(
            summary = "Export purchase supplier in excel.",
            description = "Export purchase supplier in excel."
    )
    public ResponseEntity<Resource> exportByTourExcel(
            @RequestBody VoucherEgressDetailSearchReportFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = analyticsAccountUseCase.exportVoucherPurchaseBySupplierExcel(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("voucher_purchase_supplier_report.xlsx").build().toString())
                .body(resource);
    }

}
