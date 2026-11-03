package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IQuotationRepositoryTemplate {

    public PageDTO<QuotationDomain> query(Pageable pageable, Query query);

    public List<QuotationDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public QuotationDomain queryOneIdSimpleDomainCaseCreateItinerary(Query query);

    public PageDTO<QuotationDomain> queryAllSimpleDomainCaseListQuotation(Pageable pageable, Query query);

    public void updateIdSimpleDomainCaseUpdateAmount(Query query, Update update);

    public void updateIdSimpleDomainCaseDeleteItineraryDate(Query query, Update update);

    public QuotationDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public QuotationDomain queryOneIdSimpleDomainCaseGeneric(Query query);

    // Statistics
    public Long countQuotation(Query query);

    public void updateIdSimpleDomainCaseUserDataModification(Query query, Update update);

    public QuotationDomain queryOneIdSimpleDomainCaseGetModificationUserData(Query query);

    public void updateIdSimpleDomainCaseCustomerModification(Query query, Update update);

    public List<QuotationDomain> queryAllSimpleDomainCaseToolUpdateEmployee(Query query);

    public void updateIdSimpleDomainCaseUpdateToolEmployee(Query query, Update update);
}
