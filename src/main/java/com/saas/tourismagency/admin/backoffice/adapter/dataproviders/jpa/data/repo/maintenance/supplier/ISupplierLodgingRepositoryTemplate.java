package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ISupplierLodgingRepositoryTemplate {

    public PageDTO<SupplierLodgingDomain> query(Pageable pageable, Query query);

    public List<SupplierLodgingDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SupplierLodgingDomain> queryAllSimpleWithType(Query query);

    public List<SupplierLodgingDomain> queryAllSimpleWithDepartmentRegion(Query query);

    public PageDTO<SupplierLodgingDomain> queryAllSimpleDomainCaseListSupplierLodging(Pageable pageable, Query query);
}
