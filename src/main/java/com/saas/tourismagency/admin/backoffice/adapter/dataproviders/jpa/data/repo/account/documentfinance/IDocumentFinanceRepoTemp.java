package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinance;

import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IDocumentFinanceRepoTemp {

    public PageDTO<DocumentFinanceDomain> query(Pageable pageable, Query query);

    public List<DocumentFinanceDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);
}
