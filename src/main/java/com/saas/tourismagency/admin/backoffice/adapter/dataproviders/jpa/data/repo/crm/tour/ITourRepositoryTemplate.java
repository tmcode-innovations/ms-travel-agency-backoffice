package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ITourRepositoryTemplate {

    public PageDTO<TourDomain> query(Pageable pageable, Query query);

    public List<TourDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<TourDomain> queryAllSimpleDomainCaseListTour(Pageable pageable, Query query);

    // Statistics
    public Long countTour(Query query);
}
