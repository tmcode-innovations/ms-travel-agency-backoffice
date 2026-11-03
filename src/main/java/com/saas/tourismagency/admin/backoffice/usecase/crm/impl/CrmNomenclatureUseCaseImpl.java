package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.nomenclature.StateProgramResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.nomenclature.StateQuotationResponse;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateProgramEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateQuotationEnum;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.crm.CrmNomenclatureUseCase;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CrmNomenclatureUseCaseImpl implements CrmNomenclatureUseCase {

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional(readOnly = true)
    public List<StateProgramResponse> searchAllStateProgram() {
        return EnumMappeableService
                .map(StateProgramEnum.class)
                .stream()
                .map(stateProgramModel -> StateProgramResponse.from(stateProgramModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateQuotationResponse> searchAllStateQuotation() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        return EnumMappeableService
                .map(StateQuotationEnum.class)
                .stream()
                .filter(stateQuotationModel -> belongsStateQuotationToTheConfiguration(stateQuotationModel, companyDomain.getSetting().getGeneralConfigurationType()))
                .map(stateQuotationModel -> StateQuotationResponse.from(stateQuotationModel))
                .toList();
    }

    private boolean belongsStateQuotationToTheConfiguration(StateQuotationEnum.StateQuotationModel model, String generalConfigurationType){
        if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            if (
                    model.name().equals(StateQuotationEnum.DRAFT.getName()) ||
                            model.name().equals(StateQuotationEnum.CANCEL.getName()) ||
                            model.name().equals(StateQuotationEnum.ACCEPTED.getName()) ||
                            model.name().equals(StateQuotationEnum.IN_BOOKING.getName()) ||
                            model.name().equals(StateQuotationEnum.SENT.getName())
            ){
                return true;
            }else{
                return false;
            }
        }else if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            if (
                    model.name().equals(StateQuotationEnum.DRAFT.getName()) ||
                            model.name().equals(StateQuotationEnum.CANCEL.getName()) ||
                            model.name().equals(StateQuotationEnum.ACCEPTED.getName()) ||
                            model.name().equals(StateQuotationEnum.IN_BOOKING.getName()) ||
                            model.name().equals(StateQuotationEnum.SENT.getName())
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}
