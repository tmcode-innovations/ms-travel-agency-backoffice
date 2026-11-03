package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature.*;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.*;
import com.saas.tourismagency.admin.backoffice.usecase.base.BaseNomenclatureUseCase;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BaseNomenclatureUseCaseImpl implements BaseNomenclatureUseCase {

    @Override
    @Transactional(readOnly = true)
    public List<PartnerCategoryResponse> searchAllPartnerCategory() {
        return EnumMappeableService
                .map(PartnerCategoryEnum.class)
                .stream()
                .map(partnerCategoryModel -> PartnerCategoryResponse.from(partnerCategoryModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TitlePersonResponse> searchAllTitlePerson() {
        return EnumMappeableService
                .map(TitlePersonEnum.class)
                .stream()
                .map(titlePersonModel -> TitlePersonResponse.from(titlePersonModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenderResponse> searchAllGender() {
        return EnumMappeableService
                .map(GenderEnum.class)
                .stream()
                .map(genderModel -> GenderResponse.from(genderModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestTypeResponse> searchAllChangeRequestType() {
        return EnumMappeableService
                .map(ChangeRequestTypeEnum.class)
                .stream()
                .map(changeRequestTypeModel -> ChangeRequestTypeResponse.from(changeRequestTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestStateResponse> searchAllChangeRequestStatus() {
        return EnumMappeableService
                .map(ChangeRequestStatusEnum.class)
                .stream()
                .map(changeRequestStatusModel -> ChangeRequestStateResponse.from(changeRequestStatusModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestCategoryModelResponse> searchAllChangeRequestCategory(String changeRequestType) {
        return EnumMappeableService
                .map(ChangeRequestCategoryModelEnum.class)
                .stream()
                .filter(changeRequestCategoryModel -> belongsChangeRequestCategoryToChangeRequestType(changeRequestCategoryModel, changeRequestType))
                .map(changeRequestCategoryModel -> ChangeRequestCategoryModelResponse.from(changeRequestCategoryModel))
                .toList();
    }

    private boolean belongsChangeRequestCategoryToChangeRequestType(ChangeRequestCategoryModelEnum.ChangeRequestCategoryModel model, String changeRequestType){
        if (changeRequestType.equals(ChangeRequestTypeEnum.ADD.getName())){
            if (
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_DAYS_TO_INIT.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_DAYS_TO_END.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_DAYS_MIDDLE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_PASSENGER_TYPE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_PASSENGER.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_SERVICE_TOUR.getName())
            ){
                return true;
            }else{
                return false;
            }
        }else if (changeRequestType.equals(ChangeRequestTypeEnum.CHANGE.getName())){
            if (
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_PASSENGER.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_SERVICE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_PASSENGER_TYPE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_SERVICE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_SERVICE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_PASSENGER.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_PASSENGER_TYPE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_SERVICE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.CHANGE_DATE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.CHANGE_QUOTATION_INFO.getName())
            ){
                return true;
            }else{
                return false;
            }
        }else if (changeRequestType.equals(ChangeRequestTypeEnum.ADD_CHANGE.getName())){
            if (
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_DAYS_TO_INIT.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_DAYS_TO_END.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_DAYS_MIDDLE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_PASSENGER_TYPE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_PASSENGER.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.ADD_SERVICE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_PASSENGER.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_SERVICE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_PASSENGER_TYPE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.EDIT_SERVICE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_SERVICE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_PASSENGER.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_PASSENGER_TYPE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.DELETE_SERVICE_TOUR.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.CHANGE_DATE.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.CHANGE_QUOTATION_INFO.getName()) ||
                    model.name().equals(ChangeRequestCategoryModelEnum.APPLY_ALL_CHANGE_TYPE.getName())
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
