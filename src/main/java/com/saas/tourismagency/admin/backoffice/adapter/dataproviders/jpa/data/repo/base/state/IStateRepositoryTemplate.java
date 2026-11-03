package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state;

import com.saas.tourismagency.admin.backoffice.domain.base.StateDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IStateRepositoryTemplate {

    public PageDTO<StateDomain> query(Pageable pageable, Query query);

    public List<StateDomain> query(Query query);

    public PageDTO<StateDomain> queryAllSimpleDomainCaseListState(Pageable pageable, Query query);
}
