package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwo;

import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface ICostSheetTwoRepoTemp {

    public PageDTO<CostSheetTwoDomain> query(Pageable pageable, Query query);

    public List<CostSheetTwoDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostSheetTwoDomain> queryAllSimpleDomainCaseShowCostSheetTwo(Query query);

    public PageDTO<CostSheetTwoDomain> queryAllSimpleDomainCaseListCostSheetTwo(Pageable pageable, Query query);

    public CostSheetTwoDomain queryOneIdSimpleDomainCaseGetModificationData(Query query);

    public void updateIdSimpleDomainCaseUpdateCostSheetTwoBasic(Query query, Update update);
}
