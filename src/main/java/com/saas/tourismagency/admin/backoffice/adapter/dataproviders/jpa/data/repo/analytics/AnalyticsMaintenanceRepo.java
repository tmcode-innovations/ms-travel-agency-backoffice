package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
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
public class AnalyticsMaintenanceRepo {

    @Autowired
    private IAnalyticsMaintenanceRepoTemplate repositoryTemplate;

    @Autowired
    private SupplierConverter supplierConverter;

    public PageDTO<SupplierDomain> searchPageableSupplier(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<SupplierEntity> result = repositoryTemplate.aggregatePageableSupplier(searchFilterDomain, pageConfig);
        return toSupplierDomainPage(result);
    }

    private PageDTO<SupplierDomain> toSupplierDomainPage(Page<SupplierEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<SupplierDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(page.getContent().stream().map(supplierConverter::toDomain).toList());
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
