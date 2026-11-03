package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nationality.NationalityResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.documenttype.DocumentTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.passengertype.PassengerTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TitlePersonEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypePassengerTcBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponse {

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

    @JsonProperty("quotation")
    private QuotationResponse quotation;

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


    public static PassengerResponse from(PassengerDomain domain) {
        PassengerResponse passengerResponse = new PassengerResponse();
        passengerResponse.setId(domain.getId());
        passengerResponse.setName(domain.getName());
        passengerResponse.setDescription(domain.getDescription());
        passengerResponse.setIdentify(domain.getIdentify());
        passengerResponse.setSocialMediaInformation(domain.getSocialMediaInformation());
        passengerResponse.setWebSiteInformation(domain.getWebSiteInformation());
        passengerResponse.setContactInformation(domain.getContactInformation());
        passengerResponse.setAddress(domain.getAddress());
        passengerResponse.setDateInit(domain.getDateInit());
        passengerResponse.setDateEnd(domain.getDateEnd());
        passengerResponse.setDateInitStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateInit()));
        passengerResponse.setDateEndStr(LocalDateTimeUtil.toLocalDateFormatShort(domain.getDateEnd()));
        passengerResponse.setDateInitFormatStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateInit()));
        passengerResponse.setDateEndFormatStr(LocalDateTimeUtil.toLocalDateFormatShortOther(domain.getDateEnd()));
        passengerResponse.setDocument(domain.getDocument());
        passengerResponse.setObservation(domain.getObservation());
        passengerResponse.setImageUrl(domain.getImageUrl());

        passengerResponse.setPrincipal(domain.getPrincipal());
        if (domain.getPrincipal() != null){
            passengerResponse.setPrincipalDescription(domain.getPrincipal() ? "SI" : "NO");
        }else{
            passengerResponse.setPrincipalDescription("NO");
        }

        passengerResponse.setTitlePerson(domain.getTitlePerson());
        if (domain.getTitlePerson() != null && !domain.getTitlePerson().isEmpty() && !domain.getTitlePerson().isBlank()){
            TitlePersonEnum.TitlePersonModel titlePersonModelData = EnumMappeableService
                    .map(TitlePersonEnum.class)
                    .stream().filter(
                            titlePersonModel -> titlePersonModel.name().equals(domain.getTitlePerson()))
                    .findFirst().get();
            passengerResponse.setTitlePersonModel(titlePersonModelData);
            passengerResponse.setTitlePersonDescription(titlePersonModelData.description());
        }
        passengerResponse.setTypePassengerTcBooking(domain.getTypePassengerTcBooking());
        if (domain.getTypePassengerTcBooking() != null && !domain.getTypePassengerTcBooking().isEmpty() && !domain.getTypePassengerTcBooking().isBlank()){
            TypePassengerTcBookingEnum.TypePassengerTcBookingModel typePassengerTcBookingModelData = EnumMappeableService
                    .map(TypePassengerTcBookingEnum.class)
                    .stream().filter(
                            typePassengerTcBookingModel -> typePassengerTcBookingModel.name().equals(domain.getTypePassengerTcBooking()))
                    .findFirst().get();
            passengerResponse.setTypePassengerTcBookingModel(typePassengerTcBookingModelData);
            passengerResponse.setTypePassengerTcBookingDescription(typePassengerTcBookingModelData.description());
        }
        if (domain.getPerson() != null &&
                domain.getPerson().getId() != null &&
                !domain.getPerson().getId().isEmpty()){
            passengerResponse.setPerson(PersonResponse.from(domain.getPerson()));
        }
        if (domain.getNationality() != null &&
                domain.getNationality().getId() != null &&
                !domain.getNationality().getId().isEmpty()){
            passengerResponse.setNationality(NationalityResponse.from(domain.getNationality()));
        }
        if (domain.getDocumentType() != null &&
                domain.getDocumentType().getId() != null &&
                !domain.getDocumentType().getId().isEmpty()){
            passengerResponse.setDocumentType(DocumentTypeResponse.from(domain.getDocumentType()));
        }
        if (domain.getQuotation() != null &&
                domain.getQuotation().getId() != null &&
                !domain.getQuotation().getId().isEmpty()){
            passengerResponse.setQuotation(QuotationResponse.from(domain.getQuotation()));
        }
        if (domain.getPassengerType() != null &&
                domain.getPassengerType().getId() != null &&
                !domain.getPassengerType().getId().isEmpty()){
            passengerResponse.setPassengerType(PassengerTypeResponse.from(domain.getPassengerType()));
        }
        passengerResponse.setIndex(domain.getIndex());
        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            passengerResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            passengerResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            passengerResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        passengerResponse.setCreatedAt(domain.getCreatedAt());
        passengerResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        passengerResponse.setUpdatedAt(domain.getUpdatedAt());
        passengerResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        passengerResponse.setDeletedAt(domain.getDeletedAt());
        passengerResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return passengerResponse;
    }
}
