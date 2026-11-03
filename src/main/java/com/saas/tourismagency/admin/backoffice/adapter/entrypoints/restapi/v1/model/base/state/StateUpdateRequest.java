package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.StateDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("country")
    private String country;

    public StateDomain to() {
        StateDomain stateDomain = new StateDomain();
        stateDomain.setId(id);
        stateDomain.setName(name);
        stateDomain.setDescription(description);
        stateDomain.setIdentify(identify);
        return stateDomain;
    }

    public StateDomain to(CountryDomain countryDomain) {
        StateDomain stateDomain = new StateDomain();
        stateDomain.setId(id);
        stateDomain.setName(name);
        stateDomain.setDescription(description);
        stateDomain.setIdentify(identify);
        if (countryDomain != null)
            stateDomain.setCountry(countryDomain);
        return stateDomain;
    }

}
