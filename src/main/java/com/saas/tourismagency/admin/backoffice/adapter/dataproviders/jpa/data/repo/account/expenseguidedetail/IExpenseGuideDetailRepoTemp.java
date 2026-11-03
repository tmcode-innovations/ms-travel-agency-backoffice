package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguidedetail;

import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IExpenseGuideDetailRepoTemp {

    public PageDTO<ExpenseGuideDetailDomain> query(Pageable pageable, Query query);

    public List<ExpenseGuideDetailDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

}
