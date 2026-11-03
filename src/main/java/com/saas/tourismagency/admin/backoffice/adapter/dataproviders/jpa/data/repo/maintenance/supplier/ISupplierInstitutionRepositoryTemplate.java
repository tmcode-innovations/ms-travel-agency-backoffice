package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierInstitutionDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ISupplierInstitutionRepositoryTemplate {

    public PageDTO<SupplierInstitutionDomain> query(Pageable pageable, Query query);

    public List<SupplierInstitutionDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SupplierInstitutionDomain> queryAllSimpleWithType(Query query);

    public List<SupplierInstitutionDomain> queryAllSimpleWithDepartmentRegion(Query query);

    public PageDTO<SupplierInstitutionDomain> queryAllSimpleDomainCaseListSupplierInstitution(Pageable pageable, Query query);
}
