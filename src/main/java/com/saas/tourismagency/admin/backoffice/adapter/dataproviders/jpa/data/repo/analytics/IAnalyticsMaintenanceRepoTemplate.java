package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAnalyticsMaintenanceRepoTemplate {

    Page<SupplierEntity> aggregatePageableSupplier(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

}
