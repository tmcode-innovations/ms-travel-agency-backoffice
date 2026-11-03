package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.costsheettwopercent;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ICostSheetTwoPercentRepositoryTemplate {

    public PageDTO<CostSheetTwoPercentDomain> query(Pageable pageable, Query query);

    public List<CostSheetTwoPercentDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<CostSheetTwoPercentDomain> queryAllSimpleDomainCaseListCostSheetTwoPercent(Pageable pageable, Query query);
}
