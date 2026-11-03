package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwodetail;

import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ICostSheetTwoDetailRepoTemp {

    public PageDTO<CostSheetTwoDetailDomain> query(Pageable pageable, Query query);

    public List<CostSheetTwoDetailDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetail(Query query);

    public PageDTO<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseListCostSheetTwoDetail(Pageable pageable, Query query);

    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailOnlyNoItem(Query query);

    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailUpdateCalculate(Query query);

    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailByDelete(Query query);

    public List<CostSheetTwoDetailDomain> queryAllSimpleDomainCaseShowCostSheetTwoDetailByExport(Query query);
}
