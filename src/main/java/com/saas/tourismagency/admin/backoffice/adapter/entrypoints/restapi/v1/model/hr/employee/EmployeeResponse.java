package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.area.AreaResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.partner.PartnerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position.PositionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("person")
    PersonResponse person;

    @JsonProperty("area")
    AreaResponse area;

    @JsonProperty("position")
    PositionResponse position;

    @JsonProperty("partner")
    PartnerResponse partner;

    @JsonProperty("user")
    UserResponse user;

    @JsonProperty("fileNameSign")
    private String fileNameSign;

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

    public static EmployeeResponse from(EmployeeDomain domain) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(domain.getId());
        employeeResponse.setDescription(domain.getDescription());
        employeeResponse.setIdentify(domain.getIdentify());
        if (domain.getPerson() != null &&
                domain.getPerson().getId() != null &&
                !domain.getPerson().getId().isEmpty()){
            employeeResponse.setPerson(PersonResponse.from(domain.getPerson()));
            employeeResponse.setName(employeeResponse.getPerson().getFullName());
        }
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            employeeResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getArea() != null &&
                domain.getArea().getId() != null &&
                !domain.getArea().getId().isEmpty()){
            employeeResponse.setArea(AreaResponse.from(domain.getArea()));
        }
        if (domain.getPosition() != null &&
                domain.getPosition().getId() != null &&
                !domain.getPosition().getId().isEmpty()){
            employeeResponse.setPosition(PositionResponse.from(domain.getPosition()));
        }
        employeeResponse.setFileNameSign(domain.getFileNameImageSign());
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            employeeResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            employeeResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            employeeResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        employeeResponse.setCreatedAt(domain.getCreatedAt());
        employeeResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        employeeResponse.setUpdatedAt(domain.getUpdatedAt());
        employeeResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        employeeResponse.setDeletedAt(domain.getDeletedAt());
        employeeResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return employeeResponse;
    }

    public static EmployeeResponse from(EmployeeDomain domain, UserDomain userDomain) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(domain.getId());
        employeeResponse.setDescription(domain.getDescription());
        employeeResponse.setIdentify(domain.getIdentify());
        if (domain.getPerson() != null &&
                domain.getPerson().getId() != null &&
                !domain.getPerson().getId().isEmpty()){
            employeeResponse.setPerson(PersonResponse.from(domain.getPerson()));
            employeeResponse.setName(employeeResponse.getPerson().getFullName());
        }
        if (domain.getPartner() != null &&
                domain.getPartner().getId() != null &&
                !domain.getPartner().getId().isEmpty()){
            employeeResponse.setPartner(PartnerResponse.from(domain.getPartner()));
        }
        if (domain.getArea() != null &&
                domain.getArea().getId() != null &&
                !domain.getArea().getId().isEmpty()){
            employeeResponse.setArea(AreaResponse.from(domain.getArea()));
        }
        if (domain.getPosition() != null &&
                domain.getPosition().getId() != null &&
                !domain.getPosition().getId().isEmpty()){
            employeeResponse.setPosition(PositionResponse.from(domain.getPosition()));
        }
        employeeResponse.setFileNameSign(domain.getFileNameImageSign());
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            employeeResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            employeeResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            employeeResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        if (userDomain != null){
            employeeResponse.setUser(UserResponse.from(userDomain));
        }else{
            employeeResponse.setUser(null);
        }

        // Data At
        employeeResponse.setCreatedAt(domain.getCreatedAt());
        employeeResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        employeeResponse.setUpdatedAt(domain.getUpdatedAt());
        employeeResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        employeeResponse.setDeletedAt(domain.getDeletedAt());
        employeeResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return employeeResponse;
    }
}
