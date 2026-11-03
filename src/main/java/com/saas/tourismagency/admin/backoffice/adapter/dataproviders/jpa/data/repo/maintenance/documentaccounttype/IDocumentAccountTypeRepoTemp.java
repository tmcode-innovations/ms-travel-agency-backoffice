package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype;

import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IDocumentAccountTypeRepoTemp {

    public PageDTO<DocumentAccountTypeDomain> query(Pageable pageable, Query query);

    public List<DocumentAccountTypeDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<DocumentAccountTypeDomain> queryAllSimpleDomainCaseListDocumentAccountType(Pageable pageable, Query query);
}
