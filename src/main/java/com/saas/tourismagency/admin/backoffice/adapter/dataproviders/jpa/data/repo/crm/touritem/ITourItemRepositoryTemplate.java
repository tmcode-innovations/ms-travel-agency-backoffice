package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ITourItemRepositoryTemplate {

    public PageDTO<TourItemDomain> query(Pageable pageable, Query query);

    public List<TourItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);
}
