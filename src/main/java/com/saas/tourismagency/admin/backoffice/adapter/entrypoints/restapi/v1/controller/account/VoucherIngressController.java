package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherIngressUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.VoucherIngressController.VOUCHER_INGRESS;

@Slf4j
@Tag(name = "Finance - Voucher Ingress", description = "Finance Module")
@RestController
@RequestMapping(VOUCHER_INGRESS)
public class VoucherIngressController {

    public static final String VOUCHER_INGRESS = "/voucher-ingress";

    public static final String VOUCHER_INGRESS_ID = "voucher_ingress_id";
    public static final String VOUCHER_INGRESS_DETAIL_ID = "voucher_ingress_detail_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + VOUCHER_INGRESS_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + VOUCHER_INGRESS_ID + "}";
    public static final String DELETE = "/delete/{" + VOUCHER_INGRESS_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String ADD_VOUCHER_INGRESS_DETAIL = "/{" + VOUCHER_INGRESS_ID + "}/voucher_ingress_detail/add";
    public static final String EDIT_VOUCHER_INGRESS_DETAIL = "/{" + VOUCHER_INGRESS_ID + "}/voucher_ingress_detail/edit/{" + VOUCHER_INGRESS_DETAIL_ID + "}";
    public static final String DELETE_VOUCHER_INGRESS_DETAIL = "/{" + VOUCHER_INGRESS_ID + "}/voucher_ingress_detail/delete/{" + VOUCHER_INGRESS_DETAIL_ID + "}";
    public static final String FIND_VOUCHER_INGRESS_DETAIL = "/{" + VOUCHER_INGRESS_ID + "}/voucher_ingress_detail/find/{" + VOUCHER_INGRESS_DETAIL_ID + "}";
    public static final String FIND_ALL_VOUCHER_INGRESS_DETAIL = "/{" + VOUCHER_INGRESS_ID + "}/voucher_ingress_detail/find-all";

    public static final String CHANGE_STATE = "/change-state/{" + VOUCHER_INGRESS_ID + "}";

    public static final String FIND_DATA_UPDATED = "/{" + VOUCHER_INGRESS_ID + "}/find-data-updated";

    public static final String VERIFICATION_IMPORT = "/verification-import/{idRegisterGroup}";

    public static final String EXPORT_VOUCHER_INGRESS = "/{" + VOUCHER_INGRESS_ID + "}/export-voucher-ingress/pdf";
    public static final String EXPORT_VOUCHER_INGRESS_EXCEL = "/{" + VOUCHER_INGRESS_ID + "}/export-voucher-ingress/excel";

    @Autowired
    private VoucherIngressUseCase voucherIngressUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create voucher ingress.",
            description = "Create voucher ingress."
    )
    public ApiResponse create(
            @RequestBody VoucherIngressCreateRequest request) {
        return ApiResponse.success(
                voucherIngressUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find voucher ingress by id.",
            description = "Find voucher ingress by id."
    )
    public ApiResponse findBy(
            @PathVariable(VOUCHER_INGRESS_ID) String id) {
        return ApiResponse.success(
                voucherIngressUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all voucher ingress.",
            description = "Find all voucher ingress."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                voucherIngressUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit voucher ingress.",
            description = "Edit voucher ingress."
    )
    public ApiResponse edit(
            @PathVariable(VOUCHER_INGRESS_ID) String id,
            @RequestBody VoucherIngressUpdateRequest request) {
        return ApiResponse.success(
                voucherIngressUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete voucher ingress.",
            description = "Delete voucher ingress."
    )
    public ApiResponse deleteById(
           @PathVariable(VOUCHER_INGRESS_ID) String id) {
        voucherIngressUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate voucher ingress.",
            description = "Search all voucher ingress."
    )
    public ApiResponse searchPaginate(
            @RequestBody VoucherIngressSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                voucherIngressUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate voucher ingress.",
            description = "Search filter paginate voucher ingress."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody VoucherIngressSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                voucherIngressUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PostMapping(ADD_VOUCHER_INGRESS_DETAIL)
    @Operation(
            summary = "add voucher ingress detail.",
            description = "Add voucher ingress detail."
    )
    public ApiResponse addVoucherIngressDetail(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress,
            @RequestBody VoucherIngressDetailCreateRequest request) {
        return ApiResponse.success(
                voucherIngressUseCase.addVoucherIngressDetail(request, idVoucherIngress)
        );
    }

    @PutMapping(EDIT_VOUCHER_INGRESS_DETAIL)
    @Operation(
            summary = "Edit voucher ingress detail .",
            description = "Edit voucher ingress detail."
    )
    public ApiResponse editVoucherIngressDetail(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress,
            @PathVariable(VOUCHER_INGRESS_DETAIL_ID) String idVoucherIngressDetail,
            @RequestBody VoucherIngressDetailUpdateRequest request) {
        return ApiResponse.success(
                voucherIngressUseCase.editVoucherIngressDetail(idVoucherIngressDetail, request, idVoucherIngress)
        );
    }

    @DeleteMapping(DELETE_VOUCHER_INGRESS_DETAIL)
    @Operation(
            summary = "Delete voucher ingress detail.",
            description = "Delete voucher ingress detail."
    )
    public ApiResponse deleteVoucherIngressDetail(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress,
            @PathVariable(VOUCHER_INGRESS_DETAIL_ID) String idVoucherIngressDetail
    ) {
        voucherIngressUseCase.deleteVoucherIngressDetail(idVoucherIngressDetail, idVoucherIngress);
        return ApiResponse.success();
    }

    @GetMapping(FIND_VOUCHER_INGRESS_DETAIL)
    @Operation(
            summary = "Find voucher ingress detail by id and voucher ingress.",
            description = "Find voucher ingress detail by id and voucher ingress."
    )
    public ApiResponse findVoucherIngressDetailByIdAndVoucherIngress(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress,
            @PathVariable(VOUCHER_INGRESS_DETAIL_ID) String idVoucherIngressDetail
    ) {
        return ApiResponse.success(
                voucherIngressUseCase.findByIdAndVoucherIngress(idVoucherIngressDetail, idVoucherIngress)
        );
    }

    @GetMapping(FIND_ALL_VOUCHER_INGRESS_DETAIL)
    @Operation(
            summary = "Find all voucher ingress detail by voucher ingress.",
            description = "Find all voucher ingress detail by voucher ingress."
    )
    public ApiResponse findAllVoucherIngressDetailByVoucherIngress(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress
    ) {
        return ApiResponse.success(
                voucherIngressUseCase.findAllVoucherIngressDetailByVoucherIngress(idVoucherIngress)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state voucher ingress.",
            description = "Change state voucher ingress."
    )
    public ApiResponse changeStateVoucherIngress(
            @PathVariable(VOUCHER_INGRESS_ID) String id,
            @RequestBody VoucherIngressChangeStateRequest request) {
        return ApiResponse.success(
                voucherIngressUseCase.changeState(id, request)
        );
    }

    @GetMapping(FIND_DATA_UPDATED)
    @Operation(
            summary = "Find data updated by voucher ingress.",
            description = "Find data updated by voucher ingress."
    )
    public ApiResponse findDataUpdated(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress) {
        return ApiResponse.success(
                voucherIngressUseCase.getVoucherIngressModificationData(idVoucherIngress)
        );
    }

    @GetMapping(VERIFICATION_IMPORT)
    @Operation(
            summary = "Verify voucher ingress import against proforma invoice by register group.",
            description = "Return already registered amounts, proforma invoice totals and pending amounts by register group."
    )
    public ApiResponse verificationImport(
            @PathVariable("idRegisterGroup") String idRegisterGroup) {
        return ApiResponse.success(
                voucherIngressUseCase.verificationImport(idRegisterGroup)
        );
    }

    @GetMapping(EXPORT_VOUCHER_INGRESS)
    @Operation(
            summary = "Export voucher ingress.",
            description = "Export voucher ingress."
    )
    public ResponseEntity<Resource> exportVoucherIngressPdf(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress
    ) {
        byte[] reportContent = voucherIngressUseCase.exportVoucherIngressPdf(idVoucherIngress);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("voucher_ingress_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_VOUCHER_INGRESS_EXCEL)
    @Operation(
            summary = "Export voucher ingress.",
            description = "Export voucher ingress."
    )
    public ResponseEntity<Resource> exportVoucherIngressExcel(
            @PathVariable(VOUCHER_INGRESS_ID) String idVoucherIngress
    ) {
        byte[] reportContent = voucherIngressUseCase.exportVoucherIngressExcel(idVoucherIngress);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("voucher_ingress_report.xlsx").build().toString())
                .body(resource);
    }
}
