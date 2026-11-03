package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.creditnote;

import com.saas.tourismagency.admin.backoffice.domain.account.CreditNoteDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface ICreditNoteRepoTemp {

    public PageDTO<CreditNoteDomain> query(Pageable pageable, Query query);

    public List<CreditNoteDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update);

    public CreditNoteDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public PageDTO<CreditNoteDomain> queryAllSimpleDomainCaseListCreditNote(Pageable pageable, Query query);
}
