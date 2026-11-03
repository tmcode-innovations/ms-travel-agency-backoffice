package com.saas.tourismagency.admin.backoffice.usecase.core;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature.GeneralConfigurationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature.QuotationTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature.RegisterGroupTypeResponse;

import java.util.List;

public interface CoreNomenclatureUseCase {

    public List<GeneralConfigurationResponse> searchAllGeneralConfiguration();

    public List<QuotationTypeResponse> searchAllQuotationType();

    public List<RegisterGroupTypeResponse> searchAllRegisterGroupType();
}
