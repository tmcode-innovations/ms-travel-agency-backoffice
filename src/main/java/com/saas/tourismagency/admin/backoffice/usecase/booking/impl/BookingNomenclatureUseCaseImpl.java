package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.nomenclature.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.*;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.BookingNomenclatureUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BookingNomenclatureUseCaseImpl implements BookingNomenclatureUseCase {

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional(readOnly = true)
    public List<StateBookingResponse> searchAllStateBooking() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        return EnumMappeableService
                .map(StateBookingEnum.class)
                .stream()
                .filter(stateBookingModel -> belongsStateBookingToTheConfiguration(stateBookingModel, companyDomain.getSetting().getGeneralConfigurationType()))
                .map(stateBookingModel -> StateBookingResponse.from(stateBookingModel, companyDomain.getSetting().getGeneralConfigurationType()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateRegisterGroupResponse> searchAllStateRegisterGroup() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        return EnumMappeableService
                .map(StateRegisterGroupEnum.class)
                .stream()
                .filter(stateRegisterGroupModel -> belongsStateRegisterGroupToTheConfiguration(stateRegisterGroupModel, companyDomain.getSetting().getGeneralConfigurationType()))
                .map(stateRegisterGroupModel -> StateRegisterGroupResponse.from(stateRegisterGroupModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeFeedingBookingResponse> searchAllTypeFeedingBooking() {
        return EnumMappeableService
                .map(TypeFeedingBookingEnum.class)
                .stream()
                .map(typeFeedingBookingModel -> TypeFeedingBookingResponse.from(typeFeedingBookingModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypePassengerTcBookingResponse> searchAllTypePassengerTcBooking() {
        return EnumMappeableService
                .map(TypePassengerTcBookingEnum.class)
                .stream()
                .map(typePassengerTcBookingModel -> TypePassengerTcBookingResponse.from(typePassengerTcBookingModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateServiceInvoiceResponse> searchAllStateServiceInvoice() {
        return EnumMappeableService
                .map(StateServiceInvoiceEnum.class)
                .stream()
                .map(stateServiceInvoiceModel -> StateServiceInvoiceResponse.from(stateServiceInvoiceModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateServiceInvoiceResponse> searchAllStateServiceInvoiceWithoutPending() {
        return EnumMappeableService
                .map(StateServiceInvoiceEnum.class)
                .stream()
                .filter(stateServiceInvoiceModel -> belongsStateServiceInvoice(stateServiceInvoiceModel))
                .map(stateServiceInvoiceModel -> StateServiceInvoiceResponse.from(stateServiceInvoiceModel))
                .toList();
    }

    private boolean belongsStateBookingToTheConfiguration(StateBookingEnum.StateBookingModel model, String generalConfigurationType){
        if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            if (
                    model.name().equals(StateBookingEnum.DRAFT.getName()) ||
                            model.name().equals(StateBookingEnum.CANCELLED.getName()) ||
                            model.name().equals(StateBookingEnum.TO_RESCHEDULE.getName()) ||
                            model.name().equals(StateBookingEnum.SCHEDULED.getName()) ||
                            model.name().equals(StateBookingEnum.FINISHED.getName()) ||
                            model.name().equals(StateBookingEnum.NO_SHOW.getName())
            ){
                return true;
            }else{
                return false;
            }
        }else if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            if (
                    model.name().equals(StateBookingEnum.DRAFT.getName()) ||
                            model.name().equals(StateBookingEnum.REQUEST.getName()) ||
                            model.name().equals(StateBookingEnum.CONFIRMED.getName()) ||
                            model.name().equals(StateBookingEnum.RECONFIRMED.getName()) ||
                            model.name().equals(StateBookingEnum.MODIFIED.getName()) ||
                            model.name().equals(StateBookingEnum.ANNULLED.getName()) ||
                            model.name().equals(StateBookingEnum.CANCELLED.getName()) ||
                            model.name().equals(StateBookingEnum.SENT_TO_OPERATIONS.getName())
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    private boolean belongsStateRegisterGroupToTheConfiguration(StateRegisterGroupEnum.StateRegisterGroupModel model, String generalConfigurationType){
        if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            if (
                    model.name().equals(StateRegisterGroupEnum.GENERATED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.OPENED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.ANNULLED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.REVIEW.getName())
            ){
                return true;
            }else{
                return false;
            }
        }else if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            if (
                    model.name().equals(StateRegisterGroupEnum.GENERATED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.OPENED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.COMPLETED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.INITIALIZED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.FINISHED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.CLOSED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.ANNULLED.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.DRAFT.getName()) ||
                            model.name().equals(StateRegisterGroupEnum.REVIEW.getName())
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    private boolean belongsStateServiceInvoice(StateServiceInvoiceEnum.StateServiceInvoiceModel model){
        if (
                model.name().equals(StateServiceInvoiceEnum.PENDING_PAYMENT.getName()) ||
                        model.name().equals(StateServiceInvoiceEnum.DOCUMENT_RECEIVED.getName())
        ){
            return true;
        }else{
            return false;
        }
    }

}
