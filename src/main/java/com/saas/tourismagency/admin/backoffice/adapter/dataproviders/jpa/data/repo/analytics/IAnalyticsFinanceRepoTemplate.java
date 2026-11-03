package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaEntity;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAnalyticsFinanceRepoTemplate {

    public Page<ProformaEntity> aggregatePageableProformaByTypeAndDate(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String proformaType, boolean useCancelDate);

    public Page<DocumentFinanceEntity> aggregatePageableDocumentFinanceByTypeAndDate(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String typeInheritance, boolean useCancelDate);

    public Page<DocumentFinanceEntity> aggregatePageableDocumentFinanceByTypesAndDate(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, List<String> typeInheritances, boolean useCancelDate);

    public Page<ProformaEntity> aggregatePageableProformaByFilters(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    public Page<DocumentFinanceEntity> aggregatePageableRegisterSaleOperative(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    public Page<DocumentFinanceEntity> aggregatePageableRegisterSaleOperativePassenger(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    public Page<ProformaEntity> aggregatePageableGroupPendingInvoice(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

    public Page<ProformaEntity> aggregatePageableProformCtrl(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

}
