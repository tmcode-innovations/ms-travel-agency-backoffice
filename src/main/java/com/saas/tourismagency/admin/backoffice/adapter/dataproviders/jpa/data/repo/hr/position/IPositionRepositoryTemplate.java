package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.position;

import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface IPositionRepositoryTemplate {

    public PageDTO<PositionDomain> query(Pageable pageable, Query query);

    public PageDTO<PositionDomain> queryAllSimpleDomainCaseListPosition(Pageable pageable, Query query);

}
