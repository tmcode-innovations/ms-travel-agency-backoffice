package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.TransferControlByTourBibleSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.TransferControlSearchFilterRequest;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.operation.TransferControlController.TRANSFER_CONTROL;

@Slf4j
@Tag(name = "Operation - Transfer Control", description = "Operation Module")
@RestController
@RequestMapping(TRANSFER_CONTROL)
public class TransferControlController {

    public static final String TRANSFER_CONTROL = "/transfer-control";
    public static final String TRANSFER_CONTROL_BY_TOUR_BIBLE = "/transfer-control-tour-bible";

    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";
    public static final String SEARCH_FILTER_BIBLE_PAGINATE = "/search-filter-bible-paginate";
    public static final String EXPORT_PDF = "/export-pdf";
    public static final String EXPORT_BIBLE_PDF = "/export-bible-pdf";
    public static final String EXPORT_BIBLE_EXCEL = "/export-bible-excel";

    @Autowired
    private TransferControlUseCase useCase;

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate transfer control.",
            description = "Search filter paginate transfer control."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody TransferControlSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAllTransferControl(searchFilterRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_BIBLE_PAGINATE)
    @Operation(
            summary = "Search filter paginate transfer control bible.",
            description = "Search filter paginate transfer control bible."
    )
    public ApiResponse searchFilterBiblePaginate(
            @RequestBody TransferControlByTourBibleSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                useCase.searchFilterAllTransferControlByTourBible(searchFilterRequest)
        );
    }

    @PostMapping(EXPORT_PDF)
    @Operation(
            summary = "Export transfer control.",
            description = "Export transfer control."
    )
    public ResponseEntity<Resource> exportPdf(
            @RequestBody TransferControlSearchFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = useCase.searchFilterAllTransferControlExport(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("transfer_control.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(EXPORT_BIBLE_PDF)
    @Operation(
            summary = "Export transfer control by tour in pdf.",
            description = "Export transfer control by tour in pdf."
    )
    public ResponseEntity<Resource> exportByTourPdf(
            @RequestBody TransferControlByTourBibleSearchFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = useCase.searchFilterAllTransferControlByTourBibleExportPdf(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("transfer_control_by_tour.pdf").build().toString())
                .body(resource);
    }

    @PostMapping(EXPORT_BIBLE_EXCEL)
    @Operation(
            summary = "Export transfer control by tour in excel.",
            description = "Export transfer control by tour in excel."
    )
    public ResponseEntity<Resource> exportByTourExcel(
            @RequestBody TransferControlByTourBibleSearchFilterRequest searchFilterRequest
    ) {
        byte[] reportContent = useCase.searchFilterAllTransferControlByTourBibleExportExcel(searchFilterRequest);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("transfer_control_by_tour.xlsx").build().toString())
                .body(resource);
    }

}
