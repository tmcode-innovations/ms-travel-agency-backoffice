package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTransportDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ISupplierTransportRepositoryTemplate {

    public PageDTO<SupplierTransportDomain> query(Pageable pageable, Query query);

    public List<SupplierTransportDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SupplierTransportDomain> queryAllSimpleWithType(Query query);

    public List<SupplierTransportDomain> queryAllSimpleWithDepartmentRegion(Query query);

    public PageDTO<SupplierTransportDomain> queryAllSimpleDomainCaseListSupplierTransport(Pageable pageable, Query query);
}
