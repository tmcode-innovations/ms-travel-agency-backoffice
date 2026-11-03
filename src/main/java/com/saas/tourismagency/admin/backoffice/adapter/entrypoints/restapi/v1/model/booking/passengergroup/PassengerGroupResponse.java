package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalityResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.PassengerTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TitlePersonEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerTcBookingEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerGroupResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("socialMediaInformation")
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;

    @JsonProperty("webSiteInformation")
    private WebSiteInformationEmbeddedDomain webSiteInformation;

    @JsonProperty("contactInformation")
    private ContactInformationEmbeddedDomain contactInformation;

    @JsonProperty("address")
    private AddressEmbeddedDomain address;

    @JsonProperty("dateInit")
    private LocalDate dateInit;

    @JsonProperty("dateInitStr")
    private String dateInitStr;

    @JsonProperty("dateInitFormatStr")
    private String dateInitFormatStr;

    @JsonProperty("dateEnd")
    private LocalDate dateEnd;

    @JsonProperty("dateEndStr")
    private String dateEndStr;

    @JsonProperty("dateEndFormatStr")
    private String dateEndFormatStr;

    @JsonProperty("document")
    private String document;

    @JsonProperty("observation")
    private String observation;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("principal")
    private Boolean principal;

    @JsonProperty("principalDescription")
    private String principalDescription;

    @JsonProperty("titlePerson")
    private String titlePerson;

    @JsonProperty("titlePersonDescription")
    private String titlePersonDescription;

    @JsonProperty("titlePersonModel")
    private TitlePersonEnum.TitlePersonModel titlePersonModel;

    @JsonProperty("typePassengerTcBooking")
    private String typePassengerTcBooking;

    @JsonProperty("typePassengerTcBookingDescription")
    private String typePassengerTcBookingDescription;

    @JsonProperty("typePassengerTcBookingModel")
    private TypePassengerTcBookingEnum.TypePassengerTcBookingModel typePassengerTcBookingModel;

    @JsonProperty("person")
    private PersonResponse person;

    @JsonProperty("nationality")
    private NationalityResponse nationality;

    @JsonProperty("documentType")
    private DocumentTypeResponse documentType;

    @JsonProperty("registerGroup")
    private RegisterGroupResponse registerGroup;

    @JsonProperty("passengerType")
    private PassengerTypeResponse passengerType;

    @JsonProperty("index")
    private Integer index;

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

    public static PassengerGroupResponse from(PassengerGroupDomain domain) {
        PassengerGroupResponse response = new PassengerGroupResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setDescription(domain.getDescription());
        response.setIdentify(domain.getIdentify());
        response.setSocialMediaInformation(domain.getSocialMediaInformation());
        response.setWebSiteInformation(domain.getWebSiteInformation());
        response.setContactInformation(domain.getContactInformation());
        response.setAddress(domain.getAddress());
        response.setDateInit(domain.getDateInit());
        response.setDateEnd(domain.getDateEnd());
        response.setDateInitStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateInit()));
        response.setDateEndStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateEnd()));
        response.setDateInitFormatStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateInit()));
        response.setDateEndFormatStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateEnd()));
        response.setDocument(domain.getDocument());
        response.setObservation(domain.getObservation());
        response.setImageUrl(domain.getImageUrl());
        response.setPrincipal(domain.getPrincipal());
        if (domain.getPrincipal() != null){
            response.setPrincipalDescription(domain.getPrincipal() ? "SI" : "NO");
        }else{
            response.setPrincipalDescription("NO");
        }
        response.setTitlePerson(domain.getTitlePerson());
        if (domain.getTitlePerson() != null && !domain.getTitlePerson().isEmpty() && !domain.getTitlePerson().isBlank()){
            TitlePersonEnum.TitlePersonModel titlePersonModelData = EnumMappeableService
                    .map(TitlePersonEnum.class)
                    .stream().filter(
                            titlePersonModel -> titlePersonModel.name().equals(domain.getTitlePerson()))
                    .findFirst().get();
            response.setTitlePersonModel(titlePersonModelData);
            response.setTitlePersonDescription(titlePersonModelData.description());
        }
        response.setTypePassengerTcBooking(domain.getTypePassengerTcBooking());
        if (domain.getTypePassengerTcBooking() != null && !domain.getTypePassengerTcBooking().isEmpty() && !domain.getTypePassengerTcBooking().isBlank()){
            TypePassengerTcBookingEnum.TypePassengerTcBookingModel typePassengerTcBookingModelData = EnumMappeableService
                    .map(TypePassengerTcBookingEnum.class)
                    .stream().filter(
                            typePassengerTcBookingModel -> typePassengerTcBookingModel.name().equals(domain.getTypePassengerTcBooking()))
                    .findFirst().get();
            response.setTypePassengerTcBookingModel(typePassengerTcBookingModelData);
            response.setTypePassengerTcBookingDescription(typePassengerTcBookingModelData.description());
        }
        if (domain.getPerson() != null &&
                domain.getPerson().getId() != null &&
                !domain.getPerson().getId().isEmpty()){
            response.setPerson(PersonResponse.from(domain.getPerson()));
        }
        if (domain.getNationality() != null &&
                domain.getNationality().getId() != null &&
                !domain.getNationality().getId().isEmpty()){
            response.setNationality(NationalityResponse.from(domain.getNationality()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            response.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getRegisterGroup() != null &&
                domain.getRegisterGroup().getId() != null &&
                !domain.getRegisterGroup().getId().isEmpty()){
            response.setRegisterGroup(RegisterGroupResponse.from(domain.getRegisterGroup()));
        }
        if (domain.getPassengerType() != null &&
                domain.getPassengerType().getId() != null &&
                !domain.getPassengerType().getId().isEmpty()){
            response.setPassengerType(PassengerTypeResponse.from(domain.getPassengerType()));
        }
        response.setIndex(domain.getIndex());
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            response.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            response.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            response.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        response.setCreatedAt(domain.getCreatedAt());
        response.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        response.setUpdatedAt(domain.getUpdatedAt());
        response.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        response.setDeletedAt(domain.getDeletedAt());
        response.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return response;
    }
}
