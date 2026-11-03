package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.GenderEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("dateBirthday")
    private LocalDate dateBirthday;

    @JsonProperty("dateBirthdayStr")
    private String dateBirthdayStr;

    @JsonProperty("dateBirthdayFormatStr")
    private String dateBirthdayFormatStr;

    @JsonProperty("identifyPerson")
    private String identifyPerson;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("genderModel")
    private GenderEnum.GenderModel genderModel;

    @JsonProperty("genderDescription")
    private String genderDescription;

    @JsonProperty("fullName")
    private String fullName;

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

    public static PersonResponse from(PersonDomain domain) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(domain.getId());
        personResponse.setName(domain.getName());
        personResponse.setDescription(domain.getDescription());
        personResponse.setIdentify(domain.getIdentify());
        personResponse.setFirstName(domain.getFirstName());
        personResponse.setLastName(domain.getLastName());
        personResponse.setAge(domain.getAge());
        personResponse.setDateBirthday(domain.getDateBirthday());
        personResponse.setDateBirthdayStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateBirthday()));
        personResponse.setDateBirthdayFormatStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateBirthday()));
        personResponse.setIdentifyPerson(domain.getIdentifyPerson());
        if (domain.getGender() != null && !domain.getGender().isEmpty()){
            personResponse.setGender(domain.getGender());
            GenderEnum.GenderModel genderModelData = EnumMappeableService
                    .map(GenderEnum.class)
                    .stream().filter(
                            genderModel -> genderModel.name().equals(domain.getGender()))
                    .findFirst().get();
            personResponse.setGenderDescription(genderModelData.description());
            personResponse.setGenderModel(genderModelData);
        }
        personResponse.setFullName(domain.getFirstName() + " " + domain.getLastName());
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            personResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            personResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            personResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        personResponse.setCreatedAt(domain.getCreatedAt());
        personResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        personResponse.setUpdatedAt(domain.getUpdatedAt());
        personResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        personResponse.setDeletedAt(domain.getDeletedAt());
        personResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return personResponse;
    }
}
