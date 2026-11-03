package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpassword;

import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface IUserPasswordRepositoryTemplate {

    public PageDTO<UserPasswordDomain> query(Pageable pageable, Query query);

    public List<UserPasswordDomain> queryBy(Query query);

}
