package com.saas.tourismagency.admin.backoffice.usecase.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.*;

import java.util.List;

public interface GuideUseCase {

    public GuideResponse create(GuideCreateRequest request);

    public GuideResponse edit(String id, GuideUpdateRequest request);

    public void deleteById(String id);

    public List<GuideResponse> findAll();

    public GuideResponse findById(String id);

    public GuideSearchResponse searchAll(GuideSearchRequest guideSearchRequest);

    public GuideSearchResponse searchFilterAll(GuideSearchFilterRequest guideSearchFilterRequest);

    public GuideResponse findByName(String name);

    public GuideResponse findByIdentify(String identify);

    public GuideWithoutPageSearchResponse searchAllByGuideAndTransferDrive(BaseSearchRequestById baseSearchRequestById);

    public GuideWithoutPageSearchResponse searchAllByDrive(BaseSearchRequestById baseSearchRequestById);

    public List<GuideResponse> findAllSimple();
}
