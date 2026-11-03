package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CostTourServiceItemRepositoryTemplate {

    public PageDTO<CostTourServiceItemDomain> query(Pageable pageable, Query query);

    public List<CostTourServiceItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseShowCostTourServiceItem(Query query);

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query);

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseResendQuotationToBookingByCostTourAndQuotation(Query query);

    public List<CostTourServiceItemDomain> queryAllSimpleDomainCaseChangeStateOpenedByCostTourAndQuotation(Query query);
}
