package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryCreateRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("identify")
    private String identify;

    @JsonProperty("country_iso")
    private String countryIso;

    @JsonProperty("identify_alfa3")
    private String identifyAlfa3;

    @JsonProperty("identify_numeric")
    private String identifyNumeric;

    public CountryDomain to() {
        CountryDomain countryDomain = new CountryDomain();
        countryDomain.setName(name);
        countryDomain.setDescription(description);
        countryDomain.setIdentify(identify);
        countryDomain.setCountryIso(countryIso);
        countryDomain.setIdentifyAlfa3(identifyAlfa3);
        countryDomain.setIdentifyNumeric(identifyNumeric);
        return countryDomain;
    }
}
