package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice;

import com.saas.tourismagency.admin.backoffice.domain.account.ProformaInvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IProformaInvoiceRepoTemp {

    public PageDTO<ProformaInvoiceDomain> query(Pageable pageable, Query query);

    public List<ProformaInvoiceDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<ProformaInvoiceDomain> queryAllSimpleDomainCaseListProformaInvoice(Pageable pageable, Query query);
}
