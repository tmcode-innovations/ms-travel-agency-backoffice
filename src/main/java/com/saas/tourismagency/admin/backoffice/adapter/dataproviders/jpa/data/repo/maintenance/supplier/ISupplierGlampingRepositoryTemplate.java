package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGlampingDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ISupplierGlampingRepositoryTemplate {

    public PageDTO<SupplierGlampingDomain> query(Pageable pageable, Query query);

    public List<SupplierGlampingDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SupplierGlampingDomain> queryAllSimpleWithType(Query query);

    public List<SupplierGlampingDomain> queryAllSimpleWithDepartmentRegion(Query query);

    public PageDTO<SupplierGlampingDomain> queryAllSimpleDomainCaseListSupplierGlamping(Pageable pageable, Query query);
}
