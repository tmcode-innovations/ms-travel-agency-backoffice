package com.saas.tourismagency.admin.backoffice.usecase.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature.GeneralConfigurationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature.QuotationTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.nomenclature.RegisterGroupTypeResponse;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.QuotationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.RegisterGroupTypeEnum;
import com.saas.tourismagency.admin.backoffice.usecase.core.CoreNomenclatureUseCase;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CoreNomenclatureUseCaseImpl implements CoreNomenclatureUseCase {

    @Override
    @Transactional(readOnly = true)
    public List<GeneralConfigurationResponse> searchAllGeneralConfiguration() {
        return EnumMappeableService
                .map(GeneralConfigurationTypeEnum.class)
                .stream()
                .map(generalConfigurationTypeModel -> GeneralConfigurationResponse.from(generalConfigurationTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuotationTypeResponse> searchAllQuotationType() {
        return EnumMappeableService
                .map(QuotationTypeEnum.class)
                .stream()
                .map(quotationTypeModel -> QuotationTypeResponse.from(quotationTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupTypeResponse> searchAllRegisterGroupType() {
        return EnumMappeableService
                .map(RegisterGroupTypeEnum.class)
                .stream()
                .map(registerGroupTypeModel -> RegisterGroupTypeResponse.from(registerGroupTypeModel))
                .toList();
    }

}
