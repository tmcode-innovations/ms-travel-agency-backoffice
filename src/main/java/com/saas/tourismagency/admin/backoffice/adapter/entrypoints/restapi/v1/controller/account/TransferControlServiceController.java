package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailChangeCantPaxRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.ItineraryItemChangeNoteInvoiceRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.TransferControlByTourBibleSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.TransferControlSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.TransferControlServiceUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherEgressDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.operation.TransferControlUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.TransferControlServiceController.TRANSFER_CONTROL_SERVICE;

@Slf4j
@Tag(name = "Operation - Transfer Control", description = "Operation Module")
@RestController
@RequestMapping(TRANSFER_CONTROL_SERVICE)
public class TransferControlServiceController {

    public static final String TRANSFER_CONTROL_SERVICE = "/transfer-control-service";

    public static final String SEARCH_FILTER_BIBLE_PAGINATE = "/search-filter-bible-paginate";
    public static final String EXPORT_BIBLE_PDF = "/export-bible-pdf";
    public static final String EXPORT_BIBLE_EXCEL = "/export-bible-excel";

    public static final String ITINERARY_ITEM_ID = "itinerary_item_id";
    public static final String CHANGE_CANT_PAX = "/change-cant-pax/{" + ITINERARY_ITEM_ID + "}";

    @Autowired
    private TransferControlServiceUseCase useCase;

    @Autowired
    private VoucherEgressDetailUseCase voucherEgressDetailUseCase;

    @PostMapping(SEARCH_FILTER_BIBLE_PAGINATE)
    @Operation(
            summary = "Search filter paginate transfer control bible.",
            description = "Search filter paginate transfer control bible."
    )
    public ApiResponse searchFilterBiblePaginate(
            @RequestBody TransferControlByTourBibleSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAllTransferControlByServiceBible(searchFilterRequest)
        );
    }

    @PostMapping(EXPORT_BIBLE_PDF)
    @Operation(
            summary = "Export transfer control by service in pdf.",
            description = "Export transfer control by service in pdf."
    )
    public ResponseEntity<Resource> exportByTourPdf(
            @RequestBody TransferControlByTourBibleSearchFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = useCase.searchFilterAllTransferControlByServiceBibleExportPdf(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("transfer_control_by_service.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(EXPORT_BIBLE_EXCEL)
    @Operation(
            summary = "Export transfer control by service in excel.",
            description = "Export transfer control by service in excel."
    )
    public ResponseEntity<Resource> exportByTourExcel(
            @RequestBody TransferControlByTourBibleSearchFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = useCase.searchFilterAllTransferControlByServiceBibleExportExcel(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("transfer_control_by_service.xlsx").build().toString())
                .body(resource);
    }

    @PutMapping(CHANGE_CANT_PAX)
    @Operation(
            summary = "Change cant pax.",
            description = "Change cant pax."
    )
    public ApiResponse changeCantPaxVoucherEgressDetail(
            @PathVariable(ITINERARY_ITEM_ID) String idItineraryItem,
            @RequestBody VoucherEgressDetailChangeCantPaxRequest request) {
        return ApiResponse.success(
                voucherEgressDetailUseCase.changeCantPax(idItineraryItem, request)
        );
    }

}
