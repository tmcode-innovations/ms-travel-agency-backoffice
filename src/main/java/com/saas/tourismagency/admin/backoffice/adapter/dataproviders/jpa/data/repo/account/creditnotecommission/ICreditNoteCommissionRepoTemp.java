package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.creditnotecommission;

import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface ICreditNoteCommissionRepoTemp {

    public PageDTO<CreditNoteCommissionDomain> query(Pageable pageable, Query query);

    public List<CreditNoteCommissionDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update);

    public CreditNoteCommissionDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public PageDTO<CreditNoteCommissionDomain> queryAllSimpleDomainCaseListCreditNoteCommission(Pageable pageable, Query query);
}
