package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program.ProgramResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDateResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("showNameComplete")
    private String showNameComplete;

    @JsonProperty("program")
    private ProgramResponse program;

    @JsonProperty("tours")
    private Set<TourResponse> tours;

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

    public static ProgramDateResponse from(ProgramDateDomain domain) {
        ProgramDateResponse programDateResponse = new ProgramDateResponse();
        programDateResponse.setId(domain.getId());
        programDateResponse.setName(domain.getName());
        programDateResponse.setDescription(domain.getDescription());
        programDateResponse.setIdentify(domain.getIdentify());
        programDateResponse.setDay(domain.getDay());

        if (domain.getProgram() != null &&
                domain.getProgram().getId() != null &&
                !domain.getProgram().getId().isEmpty()){
            programDateResponse.setProgram(ProgramResponse.from(domain.getProgram()));
        }

        if (domain.getTours() != null && !domain.getTours().isEmpty()){
            programDateResponse.setTours(domain.getTours().stream().map(tourDomain -> TourResponse.from(tourDomain)).collect(Collectors.toSet()));
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            programDateResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            programDateResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            programDateResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }

        if (domain.getDay() != null){
            programDateResponse.setShowNameComplete(domain.getDay().toString());
        }

        // Data At
        programDateResponse.setCreatedAt(domain.getCreatedAt());
        programDateResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        programDateResponse.setUpdatedAt(domain.getUpdatedAt());
        programDateResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        programDateResponse.setDeletedAt(domain.getDeletedAt());
        programDateResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return programDateResponse;
    }
}
