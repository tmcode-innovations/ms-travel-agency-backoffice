package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.touristdestine;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country.CountryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.district.DistrictResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.province.ProvinceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state.StateResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.UserBaseResponse;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristDestineResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("country")
    private CountryResponse country;

    @JsonProperty("departmentRegion")
    private DepartmentRegionResponse departmentRegion;

    @JsonProperty("province")
    private ProvinceResponse province;

    @JsonProperty("district")
    private DistrictResponse district;

    @JsonProperty("state")
    private StateResponse state;

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

    public static TouristDestineResponse from(TouristDestineDomain domain) {
        TouristDestineResponse touristDestineResponse = new TouristDestineResponse();
        touristDestineResponse.setId(domain.getId());
        touristDestineResponse.setName(domain.getName());
        touristDestineResponse.setDescription(domain.getDescription());
        touristDestineResponse.setIdentify(domain.getIdentify());
        touristDestineResponse.setCountry(
                (domain.getCountry() != null && domain.getCountry().getId() != null && !domain.getCountry().getId().isEmpty()) ? CountryResponse.from(domain.getCountry()) : null
        );
        touristDestineResponse.setDepartmentRegion(
                (domain.getDepartmentRegion() != null && domain.getDepartmentRegion().getId() != null && !domain.getDepartmentRegion().getId().isEmpty()) ? DepartmentRegionResponse.from(domain.getDepartmentRegion()) : null
        );
        touristDestineResponse.setProvince(
                (domain.getProvince() != null && domain.getProvince().getId() != null && !domain.getProvince().getId().isEmpty()) ? ProvinceResponse.from(domain.getProvince()) : null
        );
        touristDestineResponse.setDistrict(
                (domain.getDistrict() != null && domain.getDistrict().getId() != null && !domain.getDistrict().getId().isEmpty()) ? DistrictResponse.from(domain.getDistrict()) : null
        );
        touristDestineResponse.setState(
                (domain.getState() != null && domain.getState().getId() != null && !domain.getState().getId().isEmpty()) ? StateResponse.from(domain.getState()) : null
        );

        // Data By
        if (domain.getCreatedBy() != null &&
                domain.getCreatedBy().getId() != null &&
                !domain.getCreatedBy().getId().isEmpty()){
            touristDestineResponse.setCreatedBy(UserBaseResponse.from(domain.getCreatedBy()));
        }
        if (domain.getUpdatedBy() != null &&
                domain.getUpdatedBy().getId() != null &&
                !domain.getUpdatedBy().getId().isEmpty()){
            touristDestineResponse.setUpdatedBy(UserBaseResponse.from(domain.getUpdatedBy()));
        }
        if (domain.getDeletedBy() != null &&
                domain.getDeletedBy().getId() != null &&
                !domain.getDeletedBy().getId().isEmpty()){
            touristDestineResponse.setDeletedBy(UserBaseResponse.from(domain.getDeletedBy()));
        }
        // Data At
        touristDestineResponse.setCreatedAt(domain.getCreatedAt());
        touristDestineResponse.setCreatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getCreatedAt()));
        touristDestineResponse.setUpdatedAt(domain.getUpdatedAt());
        touristDestineResponse.setUpdatedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getUpdatedAt()));
        touristDestineResponse.setDeletedAt(domain.getDeletedAt());
        touristDestineResponse.setDeletedAtStrLong(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeLong(domain.getDeletedAt()));
        return touristDestineResponse;
    }
}
