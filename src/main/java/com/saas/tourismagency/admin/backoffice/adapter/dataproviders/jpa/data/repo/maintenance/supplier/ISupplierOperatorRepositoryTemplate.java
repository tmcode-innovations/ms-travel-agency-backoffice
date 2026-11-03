package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ISupplierOperatorRepositoryTemplate {

    public PageDTO<SupplierOperatorDomain> query(Pageable pageable, Query query);

    public List<SupplierOperatorDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SupplierOperatorDomain> queryAllSimpleWithType(Query query);

    public List<SupplierOperatorDomain> queryAllSimpleWithDepartmentRegion(Query query);

    public PageDTO<SupplierOperatorDomain> queryAllSimpleDomainCaseListSupplierOperator(Pageable pageable, Query query);
}
