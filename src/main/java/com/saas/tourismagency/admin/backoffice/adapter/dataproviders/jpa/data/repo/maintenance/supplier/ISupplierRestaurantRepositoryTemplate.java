package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierRestaurantDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ISupplierRestaurantRepositoryTemplate {

    public PageDTO<SupplierRestaurantDomain> query(Pageable pageable, Query query);

    public List<SupplierRestaurantDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SupplierRestaurantDomain> queryAllSimpleWithType(Query query);

    public List<SupplierRestaurantDomain> queryAllSimpleWithDepartmentRegion(Query query);

    public PageDTO<SupplierRestaurantDomain> queryAllSimpleDomainCaseListSupplierRestaurant(Pageable pageable, Query query);
}
