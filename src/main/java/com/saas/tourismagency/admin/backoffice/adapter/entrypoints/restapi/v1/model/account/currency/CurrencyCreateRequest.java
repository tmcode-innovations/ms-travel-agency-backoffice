package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("abbrev")
    private String abbrev;

    public CurrencyDomain to() {
        CurrencyDomain domain = new CurrencyDomain();
        domain.setName(name);
        domain.setDescription(description);
        domain.setIdentify(identify);
        domain.setAbbrev(abbrev);
        return domain;
    }
}
