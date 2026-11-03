package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.program;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programtype.ProgramTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateProgramEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponse {

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

    @JsonProperty("stateProgram")
    private String stateProgram;

    @JsonProperty("stateProgramDescription")
    private String stateProgramDescription;

    @JsonProperty("programType")
    private ProgramTypeResponse programType;

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

    public static ProgramResponse from(ProgramDomain domain) {
        ProgramResponse programResponse = new ProgramResponse();
        programResponse.setId(domain.getId());
        programResponse.setName(domain.getName());
        programResponse.setDescription(domain.getDescription());
        programResponse.setIdentify(domain.getIdentify());
        programResponse.setDay(domain.getDay());
        programResponse.setStateProgram(domain.getStateProgram());
        if (domain.getStateProgram() != null && domain.getStateProgram().isEmpty()){
            StateProgramEnum.StateProgramModel stateProgramModelData = EnumMappeableService
                    .map(StateProgramEnum.class)
                    .stream().filter(
                            stateProgramModel -> stateProgramModel.name().equals(domain.getStateProgram()))
                    .findFirst().get();
            programResponse.setStateProgramDescription(stateProgramModelData.description());
        }
        if (domain.getProgramType() != null &&
                domain.getProgramType().getId() != null &&
                !domain.getProgramType().getId().isEmpty()){
            programResponse.setProgramType(ProgramTypeResponse.from(domain.getProgramType()));
        }
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            programResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            programResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            programResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        programResponse.setCreatedAt(domain.getCreatedAt());
        programResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        programResponse.setUpdatedAt(domain.getUpdatedAt());
        programResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        programResponse.setDeletedAt(domain.getDeletedAt());
        programResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return programResponse;
    }
}
