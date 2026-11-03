package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateServiceInvoiceEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateServiceInvoiceResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public static StateServiceInvoiceResponse from(StateServiceInvoiceEnum.StateServiceInvoiceModel domain) {
        StateServiceInvoiceResponse stateServiceInvoiceResponse = new StateServiceInvoiceResponse();
        stateServiceInvoiceResponse.setId(domain.id());
        stateServiceInvoiceResponse.setName(domain.name());
        stateServiceInvoiceResponse.setDescription(domain.description());
        return stateServiceInvoiceResponse;
    }

    public static StateServiceInvoiceResponse from(StateServiceInvoiceEnum.StateServiceInvoiceModel domain, String generalConfigurationType) {
        StateServiceInvoiceResponse stateServiceInvoiceResponse = new StateServiceInvoiceResponse();
        stateServiceInvoiceResponse.setId(domain.id());
        stateServiceInvoiceResponse.setName(domain.name());
        if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            stateServiceInvoiceResponse.setDescription(domain.description().toUpperCase());
        }else{
            stateServiceInvoiceResponse.setDescription(domain.description());
        }
        return stateServiceInvoiceResponse;
    }
}
