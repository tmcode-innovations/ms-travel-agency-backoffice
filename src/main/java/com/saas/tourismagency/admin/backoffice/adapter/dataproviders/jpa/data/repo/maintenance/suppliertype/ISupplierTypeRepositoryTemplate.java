package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ISupplierTypeRepositoryTemplate {

    public PageDTO<SupplierTypeDomain> query(Pageable pageable, Query query);

    public List<SupplierTypeDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<SupplierTypeDomain> queryAllSimpleByIdentify(Query query);

    public PageDTO<SupplierTypeDomain> queryAllSimpleDomainCaseListSupplierType(Pageable pageable, Query query);
}
