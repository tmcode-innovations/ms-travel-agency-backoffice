package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TitlePersonEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitlePersonResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static TitlePersonResponse from(TitlePersonEnum.TitlePersonModel domain) {
        TitlePersonResponse titlePersonResponse = new TitlePersonResponse();
        titlePersonResponse.setId(domain.id());
        titlePersonResponse.setName(domain.name());
        titlePersonResponse.setDescription(domain.description());
        return titlePersonResponse;
    }
}
