package com.saas.tourismagency.admin.backoffice.usecase.base;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.nomenclature.*;

import java.util.List;

public interface BaseNomenclatureUseCase {

    public List<PartnerCategoryResponse> searchAllPartnerCategory();

    public List<TitlePersonResponse> searchAllTitlePerson();

    public List<GenderResponse> searchAllGender();

    public List<ChangeRequestTypeResponse> searchAllChangeRequestType();

    public List<ChangeRequestStateResponse> searchAllChangeRequestStatus();

    public List<ChangeRequestCategoryModelResponse> searchAllChangeRequestCategory(String changeRequestType);
}
