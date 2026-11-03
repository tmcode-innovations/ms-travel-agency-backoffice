package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregress.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress.VoucherIngressChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherEgressUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.VoucherEgressController.VOUCHER_EGRESS;

@Slf4j
@Tag(name = "Finance - Voucher Egress", description = "Finance Module")
@RestController
@RequestMapping(VOUCHER_EGRESS)
public class VoucherEgressController {

    public static final String VOUCHER_EGRESS = "/voucher-egress";

    public static final String VOUCHER_EGRESS_ID = "voucher_egress_id";
    public static final String VOUCHER_EGRESS_DETAIL_ID = "voucher_egress_detail_id";
    public static final String VOUCHER_EGRESS_DETAIL_ITEM_ID = "voucher_egress_detail_item_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + VOUCHER_EGRESS_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + VOUCHER_EGRESS_ID + "}";
    public static final String DELETE = "/delete/{" + VOUCHER_EGRESS_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String CHANGE_STATE = "/change-state/{" + VOUCHER_EGRESS_ID + "}";

    public static final String ADD_VOUCHER_EGRESS_DETAIL = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/add";
    public static final String EDIT_VOUCHER_EGRESS_DETAIL = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/edit/{" + VOUCHER_EGRESS_DETAIL_ID + "}";
    public static final String DELETE_VOUCHER_EGRESS_DETAIL = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/delete/{" + VOUCHER_EGRESS_DETAIL_ID + "}";
    public static final String FIND_VOUCHER_EGRESS_DETAIL = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/find/{" + VOUCHER_EGRESS_DETAIL_ID + "}";
    public static final String FIND_ALL_VOUCHER_EGRESS_DETAIL = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/find-all";

    public static final String ADD_VOUCHER_EGRESS_DETAIL_ITEM = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/{" + VOUCHER_EGRESS_DETAIL_ID + "}/voucher_egress_detail_item/add";
    public static final String EDIT_VOUCHER_EGRESS_DETAIL_ITEM = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/{" + VOUCHER_EGRESS_DETAIL_ID + "}/voucher_egress_detail_item/edit/{" + VOUCHER_EGRESS_DETAIL_ITEM_ID + "}";
    public static final String DELETE_VOUCHER_EGRESS_DETAIL_ITEM = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/{" + VOUCHER_EGRESS_DETAIL_ID + "}/voucher_egress_detail_item/delete/{" + VOUCHER_EGRESS_DETAIL_ITEM_ID + "}";
    public static final String FIND_VOUCHER_EGRESS_DETAIL_ITEM = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/{" + VOUCHER_EGRESS_DETAIL_ID + "}/voucher_egress_detail_item/find/{" + VOUCHER_EGRESS_DETAIL_ITEM_ID + "}";
    public static final String FIND_ALL_VOUCHER_EGRESS_DETAIL_ITEM = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/{" + VOUCHER_EGRESS_DETAIL_ID + "}/voucher_egress_detail_item/find-all";

    public static final String FIND_DATA_UPDATED_VOUCHER_EGRESS = "/{" + VOUCHER_EGRESS_ID + "}/find-data-updated";
    public static final String FIND_DATA_UPDATED_VOUCHER_EGRESS_DETAIL = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/{" + VOUCHER_EGRESS_DETAIL_ID + "}/find-data-updated";

    public static final String EXPORT_VOUCHER_EGRESS = "/{" + VOUCHER_EGRESS_ID + "}/export-voucher-egress/pdf";
    public static final String EXPORT_VOUCHER_EGRESS_EXCEL = "/{" + VOUCHER_EGRESS_ID + "}/export-voucher-egress/excel";

    public static final String FIND_ALL_SIMPLE = "/find-all-simple";
    public static final String FIND_ALL_SIMPLE_PENDING = "/find-all-simple-pending";
    public static final String FIND_ALL_SIMPLE_PENDING_BOOKING = "/find-all-simple-pending-booking";

    public static final String DUPLICATE_VOUCHER_EGRESS_DETAIL = "/{" + VOUCHER_EGRESS_ID + "}/voucher_egress_detail/duplicate/{" + VOUCHER_EGRESS_DETAIL_ID + "}";

    @Autowired
    private VoucherEgressUseCase voucherEgressUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create voucher egress.",
            description = "Create voucher egress."
    )
    public ApiResponse create(
            @RequestBody VoucherEgressCreateRequest request) {
        return ApiResponse.success(
                voucherEgressUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find voucher egress by id.",
            description = "Find voucher egress by id."
    )
    public ApiResponse findBy(
            @PathVariable(VOUCHER_EGRESS_ID) String id) {
        return ApiResponse.success(
                voucherEgressUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all voucher egress.",
            description = "Find all voucher egress."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit voucher egress.",
            description = "Edit voucher egress."
    )
    public ApiResponse edit(
            @PathVariable(VOUCHER_EGRESS_ID) String id,
            @RequestBody VoucherEgressUpdateRequest request) {
        return ApiResponse.success(
                voucherEgressUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete voucher egress.",
            description = "Delete voucher egress."
    )
    public ApiResponse deleteById(
           @PathVariable(VOUCHER_EGRESS_ID) String id) {
        voucherEgressUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate voucher egress.",
            description = "Search all voucher egress."
    )
    public ApiResponse searchPaginate(
            @RequestBody VoucherEgressSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate voucher egress.",
            description = "Search filter paginate voucher egress."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody VoucherEgressSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state voucher egress.",
            description = "Change state voucher egress."
    )
    public ApiResponse changeStateVoucherEgress(
            @PathVariable(VOUCHER_EGRESS_ID) String id,
            @RequestBody VoucherEgressChangeStateRequest request) {
        return ApiResponse.success(
                voucherEgressUseCase.changeState(id, request)
        );
    }


    @PostMapping(ADD_VOUCHER_EGRESS_DETAIL)
    @Operation(
            summary = "add voucher egress detail.",
            description = "Add voucher egress detail."
    )
    public ApiResponse addVoucherEgressDetail(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @RequestBody VoucherEgressDetailCreateRequest request) {
        return ApiResponse.success(
                voucherEgressUseCase.addVoucherEgressDetail(request, idVoucherEgress)
        );
    }

    @PutMapping(EDIT_VOUCHER_EGRESS_DETAIL)
    @Operation(
            summary = "Edit voucher egress detail .",
            description = "Edit voucher egress detail."
    )
    public ApiResponse editVoucherEgressDetail(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail,
            @RequestBody VoucherEgressDetailUpdateRequest request) {
        return ApiResponse.success(
                voucherEgressUseCase.editVoucherEgressDetail(idVoucherEgressDetail, request, idVoucherEgress)
        );
    }

    @DeleteMapping(DELETE_VOUCHER_EGRESS_DETAIL)
    @Operation(
            summary = "Delete voucher egress detail.",
            description = "Delete voucher egress detail."
    )
    public ApiResponse deleteVoucherEgressDetail(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail
    ) {
        voucherEgressUseCase.deleteVoucherEgressDetail(idVoucherEgressDetail, idVoucherEgress);
        return ApiResponse.success();
    }

    @GetMapping(FIND_VOUCHER_EGRESS_DETAIL)
    @Operation(
            summary = "Find voucher egress detail by id and voucher egress.",
            description = "Find voucher egress detail by id and voucher egress."
    )
    public ApiResponse findVoucherEgressDetailByIdAndVoucherEgress(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findByIdAndVoucherEgress(idVoucherEgressDetail, idVoucherEgress)
        );
    }

    @GetMapping(FIND_ALL_VOUCHER_EGRESS_DETAIL)
    @Operation(
            summary = "Find all voucher egress detail by voucher egress.",
            description = "Find all voucher egress detail by voucher egress."
    )
    public ApiResponse findAllVoucherEgressDetailByVoucherEgress(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findAllVoucherEgressDetailByVoucherEgress(idVoucherEgress)
        );
    }

    @PostMapping(ADD_VOUCHER_EGRESS_DETAIL_ITEM)
    @Operation(
            summary = "add voucher egress detail item.",
            description = "Add voucher egress detail item."
    )
    public ApiResponse addVoucherEgressDetailItem(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail,
            @RequestBody VoucherEgressDetailItemCreateRequest request) {
        return ApiResponse.success(
                voucherEgressUseCase.addVoucherEgressDetailItem(request, idVoucherEgress, idVoucherEgressDetail)
        );
    }

    @PutMapping(EDIT_VOUCHER_EGRESS_DETAIL_ITEM)
    @Operation(
            summary = "Edit voucher egress detail item.",
            description = "Edit voucher egress detail item."
    )
    public ApiResponse editVoucherEgressDetailItem(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ITEM_ID) String idVoucherEgressDetailItem,
            @RequestBody VoucherEgressDetailItemUpdateRequest request) {
        return ApiResponse.success(
                voucherEgressUseCase.editVoucherEgressDetailItem(idVoucherEgressDetailItem, request, idVoucherEgress, idVoucherEgressDetail)
        );
    }

    @DeleteMapping(DELETE_VOUCHER_EGRESS_DETAIL_ITEM)
    @Operation(
            summary = "Delete voucher egress detail item.",
            description = "Delete voucher egress detail item."
    )
    public ApiResponse deleteVoucherEgressDetailItem(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ITEM_ID) String idVoucherEgressDetailItem
    ) {
        voucherEgressUseCase.deleteVoucherEgressDetailItem(idVoucherEgressDetailItem, idVoucherEgress, idVoucherEgressDetail);
        return ApiResponse.success();
    }

    @GetMapping(FIND_VOUCHER_EGRESS_DETAIL_ITEM)
    @Operation(
            summary = "Find voucher egress detail item by id and voucher egress.",
            description = "Find voucher egress detail item by id and voucher egress."
    )
    public ApiResponse findVoucherEgressDetailItemByIdAndVoucherEgressAndVoucherEgressDetail(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ITEM_ID) String idVoucherEgressDetailItem
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findByIdAndVoucherEgressAndVoucherEgressDetail(idVoucherEgressDetailItem, idVoucherEgress, idVoucherEgressDetail)
        );
    }

    @GetMapping(FIND_ALL_VOUCHER_EGRESS_DETAIL_ITEM)
    @Operation(
            summary = "Find all voucher egress detail item by voucher egress.",
            description = "Find all voucher egress detail item by voucher egress."
    )
    public ApiResponse findAllVoucherEgressDetailItemByVoucherEgressAndVoucherEgressDetail(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findAllVoucherEgressDetailItemByVoucherEgressAndVoucherEgressDetail(idVoucherEgress, idVoucherEgressDetail)
        );
    }

    @GetMapping(FIND_DATA_UPDATED_VOUCHER_EGRESS)
    @Operation(
            summary = "Find data updated by voucher egress.",
            description = "Find data updated by voucher egress."
    )
    public ApiResponse findDataUpdatedVoucherEgress(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherIngress) {
        return ApiResponse.success(
                voucherEgressUseCase.getVoucherEgressModificationData(idVoucherIngress)
        );
    }

    @GetMapping(FIND_DATA_UPDATED_VOUCHER_EGRESS_DETAIL)
    @Operation(
            summary = "Find data updated by voucher egress.",
            description = "Find data updated by voucher egress."
    )
    public ApiResponse findDataUpdatedVoucherEgress(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherIngress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail) {
        return ApiResponse.success(
                voucherEgressUseCase.getVoucherEgressDetailModificationData(idVoucherEgressDetail, idVoucherIngress)
        );
    }

    @GetMapping(EXPORT_VOUCHER_EGRESS)
    @Operation(
            summary = "Export voucher egress.",
            description = "Export voucher egress."
    )
    public ResponseEntity<Resource> exportVoucherEgressPdf(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress
    ) {
        byte[] reportContent = voucherEgressUseCase.exportVoucherEgressPdf(idVoucherEgress);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("voucher_egress_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_VOUCHER_EGRESS_EXCEL)
    @Operation(
            summary = "Export voucher egress to excel.",
            description = "Export voucher egress to excel."
    )
    public ResponseEntity<Resource> exportVoucherEgressExcel(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress
    ) {
        byte[] reportContent = voucherEgressUseCase.exportVoucherEgressExcel(idVoucherEgress);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("voucher_egress_report.xlsx").build().toString())
                .body(resource);
    }

    @GetMapping(FIND_ALL_SIMPLE)
    @Operation(
            summary = "Find all voucher egress.",
            description = "Find all voucher egress."
    )
    public ApiResponse findAllSimple(
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findAllSimple()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_PENDING)
    @Operation(
            summary = "Find all voucher egress pending.",
            description = "Find all voucher egress pending."
    )
    public ApiResponse findAllSimpleAperture(
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findAllSimplePending()
        );
    }

    @GetMapping(FIND_ALL_SIMPLE_PENDING_BOOKING)
    @Operation(
            summary = "Find all voucher egress pending booking.",
            description = "Find all voucher egress pending booking."
    )
    public ApiResponse findAllSimpleApertureBooking(
    ) {
        return ApiResponse.success(
                voucherEgressUseCase.findAllSimplePendingBooking()
        );
    }

    @PostMapping(DUPLICATE_VOUCHER_EGRESS_DETAIL)
    @Operation(
            summary = "duplicate voucher egress detail.",
            description = "duplicate voucher egress detail."
    )
    public ApiResponse duplicateVoucherEgressDetail(
            @PathVariable(VOUCHER_EGRESS_ID) String idVoucherEgress,
            @PathVariable(VOUCHER_EGRESS_DETAIL_ID) String idVoucherEgressDetail
    ) {
        voucherEgressUseCase.duplicateVoucherEgressDetail(idVoucherEgress, idVoucherEgressDetail);
        return ApiResponse.success();
    }
}
