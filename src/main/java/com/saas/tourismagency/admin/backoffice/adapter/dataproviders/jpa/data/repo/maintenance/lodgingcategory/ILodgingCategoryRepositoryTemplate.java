package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ILodgingCategoryRepositoryTemplate {

    public PageDTO<LodgingCategoryDomain> query(Pageable pageable, Query query);

    public List<LodgingCategoryDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<LodgingCategoryDomain> queryAllSimpleDomainCaseListLodgingCategory(Pageable pageable, Query query);
}
