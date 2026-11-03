package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district;

import com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IDistrictRepositoryTemplate {

    public PageDTO<DistrictDomain> query(Pageable pageable, Query query);

    public List<DistrictDomain> query(Query query);

    public PageDTO<DistrictDomain> queryAllSimpleDomainCaseListDistrict(Pageable pageable, Query query);
}
