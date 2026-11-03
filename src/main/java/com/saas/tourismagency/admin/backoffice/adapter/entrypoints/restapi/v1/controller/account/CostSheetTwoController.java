package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwo.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.usecase.account.CostSheetTwoUseCase;
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

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.account.CostSheetTwoController.COST_SHEET_TWO;

@Slf4j
@Tag(name = "Finance - Cost Sheet Two", description = "Finance Module")
@RestController
@RequestMapping(COST_SHEET_TWO)
public class CostSheetTwoController {

    public static final String COST_SHEET_TWO = "/cost-sheet-two";

    public static final String COST_SHEET_TWO_ID = "cost_sheet_two_id";
    public static final String COST_SHEET_TWO_DETAIL_ID = "cost_sheet_two_detail_id";
    public static final String REGISTER_GROUP_ID = "register_group_id";

    public static final String CREATE = "/create";
    public static final String FIND_BY = "/find-by/{" + COST_SHEET_TWO_ID + "}";
    public static final String FIND_ALL = "/find-all";
    public static final String EDIT = "/edit/{" + COST_SHEET_TWO_ID + "}";
    public static final String DELETE = "/delete/{" + COST_SHEET_TWO_ID + "}";

    public static final String SEARCH_PAGINATE = "/search-paginate";
    public static final String SEARCH_FILTER_PAGINATE = "/search-filter-paginate";

    public static final String CHANGE_STATE = "/change-state/{" + COST_SHEET_TWO_ID + "}";

    public static final String ADD_COST_SHEET_TWO_DETAIL = "/{" + COST_SHEET_TWO_ID + "}/cost_sheet_two_detail/add";
    public static final String EDIT_COST_SHEET_TWO_DETAIL = "/{" + COST_SHEET_TWO_ID + "}/cost_sheet_two_detail/edit/{" + COST_SHEET_TWO_DETAIL_ID + "}";
    public static final String DELETE_COST_SHEET_TWO_DETAIL = "/{" + COST_SHEET_TWO_ID + "}/cost_sheet_two_detail/delete/{" + COST_SHEET_TWO_DETAIL_ID + "}";
    public static final String FIND_COST_SHEET_TWO_DETAIL = "/{" + COST_SHEET_TWO_ID + "}/cost_sheet_two_detail/find/{" + COST_SHEET_TWO_DETAIL_ID + "}";
    public static final String FIND_ALL_COST_SHEET_TWO_DETAIL = "/{" + COST_SHEET_TWO_ID + "}/cost_sheet_two_detail/find-all";

    public static final String EXIST_REGISTER_GROUP_IN_COST_SHEET_TWO = "/exist-register-group-in-cost-sheet-two/{" + REGISTER_GROUP_ID + "}";

    public static final String UPDATE_CALCULATE = "/update-calculate/{" + COST_SHEET_TWO_ID + "}";

    public static final String EXPORT_COST_SHEET_TWO_PDF = "/{" + COST_SHEET_TWO_ID + "}/export-cost-sheet-two/pdf";
    public static final String EXPORT_COST_SHEET_TWO_EXCEL = "/{" + COST_SHEET_TWO_ID + "}/export-cost-sheet-two/excel";

    @Autowired
    private CostSheetTwoUseCase costSheetTwoUseCase;

    @PostMapping(CREATE)
    @Operation(
            summary = "Create cost sheet two.",
            description = "Create cost sheet two."
    )
    public ApiResponse create(
            @RequestBody CostSheetTwoCreateRequest request) {
        return ApiResponse.success(
                costSheetTwoUseCase.create(request)
        );
    }

    @GetMapping(FIND_BY)
    @Operation(
            summary = "Find cost sheet two by id.",
            description = "Find cost sheet two by id."
    )
    public ApiResponse findBy(
            @PathVariable(COST_SHEET_TWO_ID) String id) {
        return ApiResponse.success(
                costSheetTwoUseCase.findById(id)
        );
    }

    @GetMapping(FIND_ALL)
    @Operation(
            summary = "Find all cost sheet two.",
            description = "Find all cost sheet two."
    )
    public ApiResponse findAll(
    ) {
        return ApiResponse.success(
                costSheetTwoUseCase.findAll()
        );
    }

    @PutMapping(EDIT)
    @Operation(
            summary = "Edit cost sheet two.",
            description = "Edit cost sheet two."
    )
    public ApiResponse edit(
            @PathVariable(COST_SHEET_TWO_ID) String id,
            @RequestBody CostSheetTwoUpdateRequest request) {
        return ApiResponse.success(
                costSheetTwoUseCase.edit(id, request)
        );
    }

    @DeleteMapping(DELETE)
    @Operation(
            summary = "Delete cost sheet two.",
            description = "Delete cost sheet two."
    )
    public ApiResponse deleteById(
           @PathVariable(COST_SHEET_TWO_ID) String id) {
        costSheetTwoUseCase.deleteById(id);
        return ApiResponse.success();
    }

    @PostMapping(SEARCH_PAGINATE)
    @Operation(
            summary = "Search paginate cost sheet two.",
            description = "Search all cost sheet two."
    )
    public ApiResponse searchPaginate(
            @RequestBody CostSheetTwoSearchRequest searchRequest
    ) {
        return ApiResponse.success(
                costSheetTwoUseCase.searchAll(searchRequest)
        );
    }

    @PostMapping(SEARCH_FILTER_PAGINATE)
    @Operation(
            summary = "Search filter paginate cost sheet two.",
            description = "Search filter paginate cost sheet two."
    )
    public ApiResponse searchFilterPaginate(
            @RequestBody CostSheetTwoSearchFilterRequest searchFilterRequest
    ) {
        return ApiResponse.success(
                costSheetTwoUseCase.searchFilterAll(searchFilterRequest)
        );
    }

    @PutMapping(CHANGE_STATE)
    @Operation(
            summary = "Change state cost sheet two.",
            description = "Change state cost sheet two."
    )
    public ApiResponse changeStateCostSheetTwo(
            @PathVariable(COST_SHEET_TWO_ID) String id,
            @RequestBody CostSheetTwoChangeStateRequest request) {
        return ApiResponse.success(
                costSheetTwoUseCase.changeState(id, request)
        );
    }


    @PostMapping(ADD_COST_SHEET_TWO_DETAIL)
    @Operation(
            summary = "add cost sheet two detail.",
            description = "Add cost sheet two detail."
    )
    public ApiResponse addCostSheetTwoDetail(
            @PathVariable(COST_SHEET_TWO_ID) String idCostSheetTwo,
            @RequestBody CostSheetTwoDetailCreateRequest request) {
        return ApiResponse.success(
                costSheetTwoUseCase.addCostSheetTwoDetail(request, idCostSheetTwo)
        );
    }

    @PutMapping(EDIT_COST_SHEET_TWO_DETAIL)
    @Operation(
            summary = "Edit cost sheet two detail .",
            description = "Edit cost sheet two detail."
    )
    public ApiResponse editCostSheetTwoDetail(
            @PathVariable(COST_SHEET_TWO_ID) String idCostSheetTwo,
            @PathVariable(COST_SHEET_TWO_DETAIL_ID) String idCostSheetTwoDetail,
            @RequestBody CostSheetTwoDetailUpdateRequest request) {
        return ApiResponse.success(
                costSheetTwoUseCase.editCostSheetTwoDetail(idCostSheetTwoDetail, request, idCostSheetTwo)
        );
    }

    @DeleteMapping(DELETE_COST_SHEET_TWO_DETAIL)
    @Operation(
            summary = "Delete cost sheet two detail.",
            description = "Delete cost sheet two detail."
    )
    public ApiResponse deleteVoucherEgressDetail(
            @PathVariable(COST_SHEET_TWO_ID) String idCostSheetTwo,
            @PathVariable(COST_SHEET_TWO_DETAIL_ID) String idCostSheetTwoDetail
    ) {
        costSheetTwoUseCase.deleteCostSheetTwoDetail(idCostSheetTwoDetail, idCostSheetTwo);
        return ApiResponse.success();
    }

    @GetMapping(FIND_COST_SHEET_TWO_DETAIL)
    @Operation(
            summary = "Find cost sheet two detail by id and cost sheet two.",
            description = "Find cost sheet two detail by id and cost sheet two."
    )
    public ApiResponse findVoucherEgressDetailByIdAndVoucherEgress(
            @PathVariable(COST_SHEET_TWO_ID) String idCostSheetTwo,
            @PathVariable(COST_SHEET_TWO_DETAIL_ID) String idCostSheetTwoDetail
    ) {
        return ApiResponse.success(
                costSheetTwoUseCase.findByIdAndCostSheetTwo(idCostSheetTwoDetail, idCostSheetTwo)
        );
    }

    @GetMapping(FIND_ALL_COST_SHEET_TWO_DETAIL)
    @Operation(
            summary = "Find all cost sheet two detail by cost sheet two.",
            description = "Find all cost sheet two detail by cost sheet two."
    )
    public ApiResponse findAllCostSheetTwoDetailByCostSheetTwo(
            @PathVariable(COST_SHEET_TWO_ID) String idCostSheetTwo
    ) {
        return ApiResponse.success(
                costSheetTwoUseCase.findAllCostSheetTwoDetailByCostSheetTwo(idCostSheetTwo)
        );
    }

    @GetMapping(EXIST_REGISTER_GROUP_IN_COST_SHEET_TWO)
    @Operation(
            summary = "Exist register group in cost sheet two.",
            description = "Exist register group in cost sheet two."
    )
    public ApiResponse existRegisterGroupInCostSheetTwo(
            @PathVariable(REGISTER_GROUP_ID) String id) {
        return ApiResponse.success(
                costSheetTwoUseCase.existRegisterGroupInCostSheetTwo(id)
        );
    }

    @PutMapping(UPDATE_CALCULATE)
    @Operation(
            summary = "Update calculate cost sheet two.",
            description = "Update calculate cost sheet two."
    )
    public ApiResponse updateCalculate(
            @PathVariable(COST_SHEET_TWO_ID) String id,
            @RequestBody CostSheetTwoUpdateRequest request) {
        return ApiResponse.success(
                costSheetTwoUseCase.updateCalculate(id, request)
        );
    }

    @GetMapping(EXPORT_COST_SHEET_TWO_PDF)
    @Operation(
            summary = "Export cost sheet two pdf.",
            description = "Export cost sheet two pdf."
    )
    public ResponseEntity<Resource> exportCostSheetTwoPdf(
            @PathVariable(COST_SHEET_TWO_ID) String idCostSheetTwo
    ) {
        byte[] reportContent = costSheetTwoUseCase.exportCostSheetTwoPdf(idCostSheetTwo);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("cost_sheet_two_report.pdf").build().toString())
                .body(resource);
    }

    @GetMapping(EXPORT_COST_SHEET_TWO_EXCEL)
    @Operation(
            summary = "Export cost sheet two to excel.",
            description = "Export cost sheet two to excel."
    )
    public ResponseEntity<Resource> exportCostSheetTwoExcel(
            @PathVariable(COST_SHEET_TWO_ID) String idCostSheetTwo
    ) {
        byte[] reportContent = costSheetTwoUseCase.exportCostSheetTwoExcel(idCostSheetTwo);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return  ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename("cost_sheet_two_report.xlsx").build().toString())
                .body(resource);
    }

}
