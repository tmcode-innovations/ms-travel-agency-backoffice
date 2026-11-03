package com.saas.tourismagency.admin.backoffice.usecase.crm;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.nomenclature.StateProgramResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.nomenclature.StateQuotationResponse;

import java.util.List;

public interface CrmNomenclatureUseCase {

    public List<StateProgramResponse> searchAllStateProgram();

    public List<StateQuotationResponse> searchAllStateQuotation();

}
