package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequest.ChangeRequestResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestCommentDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestCommentResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("employee")
    private EmployeeResponse employee;

    @JsonProperty("user")
    private UserResponse user;

    @JsonProperty("changeRequest")
    private ChangeRequestResponse changeRequest;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("dateTimeStr")
    private String dateTimeStr;

    // Data By
    @JsonProperty("createdBy")
    private UserBaseResponse createdBy;

    @JsonProperty("updatedBy")
    private UserBaseResponse updatedBy;

    @JsonProperty("deletedBy")
    private UserBaseResponse deletedBy;

    // Data At

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("createdAtStrLong")
    private String createdAtStrLong;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("updatedAtStrLong")
    private String updatedAtStrLong;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    @JsonProperty("deletedAtStrLong")
    private String deletedAtStrLong;

    public static ChangeRequestCommentResponse from(ChangeRequestCommentDomain domain) {
        ChangeRequestCommentResponse changeRequestCommentResponse = new ChangeRequestCommentResponse();
        changeRequestCommentResponse.setId(domain.getId());
        changeRequestCommentResponse.setName(domain.getName());
        changeRequestCommentResponse.setDescription(domain.getDescription());
        changeRequestCommentResponse.setIdentify(domain.getIdentify());

        changeRequestCommentResponse.setDateTime(domain.getDateTime());
        changeRequestCommentResponse.setDateTimeStr(LocalDateTimeUtil.toLocalDateTimeFormatDateTime(domain.getDateTime()));

        if (domain.getUser() != null &&
                domain.getUser().getId() != null &&
                !domain.getUser().getId().isEmpty()){
            changeRequestCommentResponse.setUser(UserResponse.from(domain.getUser()));
        }
        if (domain.getEmployee() != null &&
                domain.getEmployee().getId() != null &&
                !domain.getEmployee().getId().isEmpty()){
            changeRequestCommentResponse.setEmployee(EmployeeResponse.from(domain.getEmployee()));
        }
        if (domain.getChangeRequest() != null &&
                domain.getChangeRequest().getId() != null &&
                !domain.getChangeRequest().getId().isEmpty()){
            changeRequestCommentResponse.setChangeRequest(ChangeRequestResponse.from(domain.getChangeRequest()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            changeRequestCommentResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            changeRequestCommentResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            changeRequestCommentResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        changeRequestCommentResponse.setCreatedAt(domain.getCreatedAt());
        changeRequestCommentResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        changeRequestCommentResponse.setUpdatedAt(domain.getUpdatedAt());
        changeRequestCommentResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        changeRequestCommentResponse.setDeletedAt(domain.getDeletedAt());
        changeRequestCommentResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return changeRequestCommentResponse;
    }
}
