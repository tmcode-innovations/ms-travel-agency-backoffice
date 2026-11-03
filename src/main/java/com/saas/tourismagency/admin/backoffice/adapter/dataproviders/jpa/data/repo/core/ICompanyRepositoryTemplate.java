package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core;

import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface ICompanyRepositoryTemplate {

    public PageDTO<CompanyDomain> query(Pageable pageable, Query query);

    public PageDTO<CompanyDomain> queryAllSimpleDomainCaseListCompany(Pageable pageable, Query query);

}
