package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province;

import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IProvinceRepositoryTemplate {

    public PageDTO<ProvinceDomain> query(Pageable pageable, Query query);

    public List<ProvinceDomain> query(Query query);

    public PageDTO<ProvinceDomain> queryAllSimpleDomainCaseListProvince(Pageable pageable, Query query);
}
