package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtouritem;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CostTourItemRepositoryTemplate {

    public PageDTO<CostTourItemDomain> query(Pageable pageable, Query query);

    public List<CostTourItemDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<CostTourItemDomain> queryAllSimpleDomainCaseShowCostTourItem(Query query);

    public List<CostTourItemDomain> queryAllSimpleDomainCaseDeleteByQuotation(Query query);

    public List<CostTourItemDomain> queryAllSimpleDomainCaseResendQuotationToBookingByCostTourAndQuotation(Query query);

    public List<CostTourItemDomain> queryAllSimpleDomainCaseChangeStateOpenedByCostTourAndQuotation(Query query);
}
