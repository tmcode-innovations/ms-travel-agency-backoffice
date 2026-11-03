package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IRegisterGroupRepositoryTemplate {

    public PageDTO<RegisterGroupDomain> query(Pageable pageable, Query query);

    public List<RegisterGroupDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public RegisterGroupDomain queryOneIdSimpleDomainCaseCreateItinerary(Query query);

    public PageDTO<RegisterGroupDomain> queryAllSimpleDomainCaseListRegisterGroup(Pageable pageable, Query query);

    public List<RegisterGroupDomain> queryAllSimpleDomainCaseListRegisterGroup(Query query);

    public void updateIdSimpleDomainCaseDeleteItineraryDate(Query query, Update update);

    // Statistics
    public Long countRegisterGroup(Query query);

    //
    public List<RegisterGroupEntity> aggregateFindRegisterGroupNotInCostSheetTwo();

    public List<RegisterGroupDomain> findAllRegisterGroupForCostSheetTwo(Query query);

    public List<RegisterGroupDomain> queryAllAperture(Query query);

    public List<RegisterGroupDomain> queryAllApertureDocumentFinance(Query query);

    public List<RegisterGroupDomain> queryAllApertureVoucherIngress(Query query);

    public List<RegisterGroupDomain> queryAllApertureVoucherEgress(Query query);

    public List<RegisterGroupDomain> queryAllApertureReport(Query query);

    public void updateIdSimpleDomainCaseUserDataModification(Query query, Update update);

    public RegisterGroupDomain queryOneIdSimpleDomainCaseGetModificationUserData(Query query);

    public void updateIdSimpleDomainCaseCustomerModification(Query query, Update update);

    public RegisterGroupDomain queryOneIdSimpleDomainCaseGetModificationCustomerData(Query query);
}
