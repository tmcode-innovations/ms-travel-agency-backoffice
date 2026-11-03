package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.CompanyCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.updatedata.UpdateDataCustomerProcessRequest;
import com.saas.tourismagency.admin.backoffice.adapter.importdata.excel.ImportObjectSecurity;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterGroupUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.UpdateDataUseCase;
import dev.mm.javaee.core.rest.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.controller.core.UpdateDataController.UPDATE_DATA;

@Slf4j
@Tag(name = "Core - Update Data", description = "Core Module")
@RestController
@RequestMapping(UPDATE_DATA)
public class UpdateDataController {

    public static final String UPDATE_DATA = "/update-data";

    public static final String QUOTATION_ID = "quotation_id";
    public static final String REGISTER_GROUP_IDENTIFY = "register_group_identify";

    public static final String UPDATE_DATE_TIME_ITINERARY_ITEM = "/update-date-time-itinerary-item-in-booking";
    public static final String UPDATE_CUSTOMER_ITINERARY_ITEM = "/update-customer-itinerary-item-in-booking";
    public static final String UPDATE_CHANGE_TYPE_DATE = "/update-change-type-date-in-account";
    public static final String UPDATE_CHANGE_ABBREV_SERVICE = "/update-change-abbrev-in-service-maintenance";
    public static final String UPDATE_CHANGE_SUMMARY_SUPPLIER = "/update-change-summary-in-supplier-maintenance";
    public static final String UPDATE_CHANGE_CUSTOMER_IN_PROCESS_GURU_QUO_BOOK_OPER = "/update-change-customer-process-guru-quo-book-oper";

    public static final String RESEND_QUOTATION_TO_BOOKING = "/resend-quotation-booking/{" + REGISTER_GROUP_IDENTIFY + "}";

    public static final String ADD_PROFILE_CONSULTANT_ACCOUNT = "/add-profile-consultant-account";

    public static final String UPDATE_EMPLOYEE_IN_QUOTATION_BY_CREATED_USER = "/update-employee-quotation-by-created-user";

    @Autowired
    private UpdateDataUseCase updateDataUseCase;

    @Autowired
    private RegisterGroupUseCase registerGroupUseCase;

    @GetMapping(UPDATE_DATE_TIME_ITINERARY_ITEM)
    @Operation(
            summary = "Update date time itinerary item.",
            description = "Update date time itinerary item."
    )
    public ApiResponse updateDateTimeItineraryItem() {
        updateDataUseCase.updateItineraryItemDateTimeInBooking();
        return ApiResponse.success();
    }

    @GetMapping(UPDATE_CUSTOMER_ITINERARY_ITEM)
    @Operation(
            summary = "Update customer itinerary item.",
            description = "Update customer itinerary item."
    )
    public ApiResponse updateCustomerItineraryItem() {
        updateDataUseCase.updateItineraryItemCustomerInBooking();
        return ApiResponse.success();
    }

    @GetMapping(UPDATE_CHANGE_TYPE_DATE)
    @Operation(
            summary = "Update date in change type.",
            description = "Update date in change type."
    )
    public ApiResponse updateChangeTypeDate() {
        updateDataUseCase.updateDateChangeTypeInAccount();
        return ApiResponse.success();
    }

    @GetMapping(UPDATE_CHANGE_ABBREV_SERVICE)
    @Operation(
            summary = "Update abbrev in service maintenance.",
            description = "Update abbrev in service maintenance."
    )
    public ApiResponse updateAbbrevInServiceMaintenance() {
        updateDataUseCase.updateFieldAbbrevInServiceMaintenance();
        return ApiResponse.success();
    }

    @GetMapping(UPDATE_CHANGE_SUMMARY_SUPPLIER)
    @Operation(
            summary = "Update summary in supplier maintenance.",
            description = "Update summary in supplier maintenance."
    )
    public ApiResponse updateSummaryInSupplierMaintenance() {
        updateDataUseCase.updateFieldSummaryInSupplierMaintenance();
        return ApiResponse.success();
    }

    @PostMapping(UPDATE_CHANGE_CUSTOMER_IN_PROCESS_GURU_QUO_BOOK_OPER)
    @Operation(
            summary = "Update change customer in process guru.",
            description = "Update change customer in process guru."
    )
    public ApiResponse updateChangeCustomerProcess(
            @RequestBody UpdateDataCustomerProcessRequest request
    ) {
        updateDataUseCase.updateChangeCustomerProcess(request);
        return ApiResponse.success();
    }

    @PutMapping(RESEND_QUOTATION_TO_BOOKING)
    @Operation(
            summary = "Resend qotation to booking.",
            description = "Resend qotation to booking."
    )
    public ApiResponse resendQuotationToBooking(
            @PathVariable(REGISTER_GROUP_IDENTIFY) String identify) {
        return ApiResponse.success(
                registerGroupUseCase.resendQuotationToBookingUpdateAdministration(identify)
        );
    }

    @GetMapping(ADD_PROFILE_CONSULTANT_ACCOUNT)
    @Operation(
            summary = "Add profile consultant account.",
            description = "Add profile consultant account."
    )
    public ApiResponse addProfileConsultatntAccount() {
        updateDataUseCase.addProfileConsultantAccount();
        return ApiResponse.success();
    }

    @GetMapping(UPDATE_EMPLOYEE_IN_QUOTATION_BY_CREATED_USER)
    @Operation(
            summary = "Update employee in quotation by created user.",
            description = "Update employee in quotation where employee is null, resolving the employee " +
                    "associated to the user that created the quotation."
    )
    public ApiResponse updateEmployeeInQuotationByCreatedUser() {
        updateDataUseCase.updateEmployeeInQuotationByCreatedUser();
        return ApiResponse.success();
    }
}
