package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailEntity;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchFilterReportDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAnalyticsAccountRepoTemplate {

    public List<VoucherEgressDetailEntity> aggregateVoucherPurchaseBySupplier(ISearchFilterReportDomain searchFilterDomain);

    public Page<VoucherEgressDetailEntity> aggregatePageableVoucherPurchaseBySupplier(ISearchFilterPageDomain searchFilterDomain, Pageable pageable);

}
