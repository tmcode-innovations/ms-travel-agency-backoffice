package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.idiom.IdiomResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.person.PersonResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.nomenclature.GuideTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.WebSiteInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.GenderEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.GuideTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuideResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("ruc")
    private String ruc;

    @JsonProperty("person")
    private PersonResponse person;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("idioms")
    private Set<IdiomResponse> idioms;

    @JsonProperty("idiomsDescription")
    private String idiomsDescription;

    @JsonProperty("guideTypes")
    private Set<GuideTypeResponse> guideTypes;

    @JsonProperty("guideTypesDescription")
    private String guideTypesDescription;

    @JsonProperty("address")
    private AddressEmbeddedDomain address;

    @JsonProperty("socialMediaInformation")
    private SocialMediaInformationEmbeddedDomain socialMediaInformation;

    @JsonProperty("webSiteInformation")
    private WebSiteInformationEmbeddedDomain webSiteInformation;

    @JsonProperty("contactInformation")
    private ContactInformationEmbeddedDomain contactInformation;

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

    public static GuideResponse from(GuideDomain domain) {
        GuideResponse guideResponse = new GuideResponse();
        guideResponse.setId(domain.getId());
        guideResponse.setName(domain.getName());
        guideResponse.setDescription(domain.getDescription());
        guideResponse.setIdentify(domain.getIdentify());
        guideResponse.setRuc(domain.getRuc());
        guideResponse.setAddress(domain.getAddress());
        guideResponse.setSocialMediaInformation(domain.getSocialMediaInformation());
        guideResponse.setWebSiteInformation(domain.getWebSiteInformation());
        guideResponse.setContactInformation(domain.getContactInformation());
        if (domain.getPerson() != null &&
                domain.getPerson().getId() != null &&
                !domain.getPerson().getId().isEmpty()
        ){
            guideResponse.setPerson(PersonResponse.from(domain.getPerson()));
        }
        if (domain.getSupplier() != null &&
                domain.getSupplier().getId() != null &&
                !domain.getSupplier().getId().isEmpty()
        ){
            guideResponse.setSupplier(SupplierResponse.from(domain.getSupplier()));
        }

        if (domain.getGuideTypes() != null && !domain.getGuideTypes().isEmpty()){
            guideResponse.setGuideTypes(domain.getGuideTypes().stream().map(s -> {
                GuideTypeEnum.GuideTypeModel guideTypeModelData = EnumMappeableService
                        .map(GuideTypeEnum.class)
                        .stream().filter(
                                guideTypeModel -> guideTypeModel.name().equals(s))
                        .findFirst().orElse(null);
                if (guideTypeModelData == null) {
                    return null;
                }
                return GuideTypeResponse.from(guideTypeModelData);
            }).filter(java.util.Objects::nonNull).collect(Collectors.toSet()));
            StringBuffer result = new StringBuffer();
            for (String guideType: domain.getGuideTypes()){
                GuideTypeEnum.GuideTypeModel guideTypeModelData = EnumMappeableService
                        .map(GuideTypeEnum.class)
                        .stream().filter(
                                guideTypeModel -> guideTypeModel.name().equals(guideType))
                        .findFirst().orElse(null);
                if (guideTypeModelData != null){
                    result.append(guideTypeModelData.description());
                    result.append(" , ");
                }
            }
            if (result.length() > 1){
                result.delete(result.length()-2, result.length()-1);
            }
            guideResponse.setGuideTypesDescription(result.toString());
        }


        if (domain.getIdioms() != null && !domain.getIdioms().isEmpty()){
            guideResponse.setIdioms(domain.getIdioms().stream().map(idiomDomain -> IdiomResponse.from(idiomDomain)).collect(Collectors.toSet()));
            StringBuffer result = new StringBuffer();
            for (IdiomDomain idiomDomain : domain.getIdioms()){
                result.append(idiomDomain.getName());
                result.append(" , ");
            }
            if (result.length() > 1){
                result.delete(result.length()-2, result.length()-1);
            }
            guideResponse.setIdiomsDescription(result.toString());
        }

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            guideResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            guideResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            guideResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        guideResponse.setCreatedAt(domain.getCreatedAt());
        guideResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        guideResponse.setUpdatedAt(domain.getUpdatedAt());
        guideResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        guideResponse.setDeletedAt(domain.getDeletedAt());
        guideResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return guideResponse;
    }
}
