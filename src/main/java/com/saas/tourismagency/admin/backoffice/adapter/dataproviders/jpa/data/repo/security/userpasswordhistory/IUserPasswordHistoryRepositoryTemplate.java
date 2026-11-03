package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpasswordhistory;

import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IUserPasswordHistoryRepositoryTemplate {

    public PageDTO<UserPasswordHistoryDomain> query(Pageable pageable, Query query);

    public List<UserPasswordHistoryDomain> queryBy(Query query);

}
