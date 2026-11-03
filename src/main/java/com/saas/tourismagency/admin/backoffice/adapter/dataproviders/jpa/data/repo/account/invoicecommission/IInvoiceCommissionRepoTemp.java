package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.invoicecommission;

import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceCommissionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IInvoiceCommissionRepoTemp {

    public PageDTO<InvoiceCommissionDomain> query(Pageable pageable, Query query);

    public List<InvoiceCommissionDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update);

    public InvoiceCommissionDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public PageDTO<InvoiceCommissionDomain> queryAllSimpleDomainCaseListInvoiceCommission(Pageable pageable, Query query);
}
