package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtourserviceitem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.CostTourServiceItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CostTourServiceItemBookRepositoryTemplate {

    public PageDTO<CostTourServiceItemDomain> query(Pageable pageable, Query query);

    public List<CostTourServiceItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseShowCostTourServiceItem(Query query);

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query);

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseResendFileToSaleByCostTourAndRegisterGroup(Query query);
}
