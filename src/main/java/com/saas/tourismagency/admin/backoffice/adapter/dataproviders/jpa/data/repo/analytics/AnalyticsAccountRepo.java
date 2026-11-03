package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetail.VoucherEgressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchFilterReportDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsAccountRepo {

    @Autowired
    private IAnalyticsAccountRepoTemplate repositoryTemplate;

    @Autowired
    private VoucherEgressDetailConverter voucherEgressDetailConverter;


    public PageDTO<VoucherEgressDetailDomain> searchPageableVoucherPurchaseBySupplier(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<VoucherEgressDetailEntity> result = repositoryTemplate.aggregatePageableVoucherPurchaseBySupplier(searchFilterDomain, pageConfig);
        return voucherEgressDetailConverter.toDomainPageAnalyticsAggregateVoucherPurchaseBySupplier(result);
    }

    public List<VoucherEgressDetailDomain> searchVoucherPurchaseBySupplier(ISearchFilterReportDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();

        List<VoucherEgressDetailEntity> result = repositoryTemplate.aggregateVoucherPurchaseBySupplier(searchFilterDomain);
        return voucherEgressDetailConverter.toDomainAnalyticsAggregateVoucherPurchaseBySupplier(result);
    }
}
