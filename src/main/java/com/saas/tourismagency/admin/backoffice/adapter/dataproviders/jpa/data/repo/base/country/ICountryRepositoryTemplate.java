package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country;

import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ICountryRepositoryTemplate {

    public PageDTO<CountryDomain> query(Pageable pageable, Query query);

    public List<CountryDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public PageDTO<CountryDomain> queryAllSimpleDomainCaseListCountry(Pageable pageable, Query query);
}
