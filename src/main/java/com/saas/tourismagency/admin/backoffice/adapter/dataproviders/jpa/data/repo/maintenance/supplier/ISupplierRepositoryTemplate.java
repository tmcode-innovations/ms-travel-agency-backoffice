package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.statistics.GroupedDataDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface ISupplierRepositoryTemplate {

    public PageDTO<SupplierDomain> query(Pageable pageable, Query query);

    public List<SupplierDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    // Statistics
    public Long countSupplier(Query query);

    public List<GroupedDataDomain> aggregateAndFilter(String filterField, Object filterValue, String groupByField);

    public List<GroupedDataDomain> aggregateSupplierByType(String groupByField);

    public List<SupplierDomain> queryAllSimpleDomainCaseToolUpdateSummary(Query query);

    public void updateIdSimpleDomainCaseUpdateToolSummary(Query query, Update update);

    public SupplierDomain queryOneIdWithDepartmentRegion(Query query);

    public List<SupplierDomain> queryAllSimpleForServiceOperation(Query query);
}
