package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinancedetail;

import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IDocumentFinanceDetailRepoTemp {

    public PageDTO<DocumentFinanceDetailDomain> query(Pageable pageable, Query query);

    public List<DocumentFinanceDetailDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<DocumentFinanceDetailDomain> queryAllSimpleDomainCaseShowDocumentFinanceDetail(Query query);

    public List<DocumentFinanceDetailDomain> queryAllSimpleDomainCaseShowNoItem(Query query);
}
