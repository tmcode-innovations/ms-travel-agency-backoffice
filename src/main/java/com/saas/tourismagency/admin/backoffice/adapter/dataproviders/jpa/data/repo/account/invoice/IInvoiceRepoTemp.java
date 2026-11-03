package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.invoice;

import com.saas.tourismagency.admin.backoffice.domain.account.InvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IInvoiceRepoTemp {

    public PageDTO<InvoiceDomain> query(Pageable pageable, Query query);

    public List<InvoiceDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public void updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(Query query, Update update);

    public InvoiceDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public PageDTO<InvoiceDomain> queryAllSimpleDomainCaseListInvoice(Pageable pageable, Query query);
}
