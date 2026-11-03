package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;

import java.util.List;

public interface UserPasswordHistoryUseCase {

    public UserPasswordHistoryDomain findByUser(UserDomain user);

    public UserPasswordHistoryDomain create(UserPasswordDomain userPasswordDomain, UserDomain user);

    public List<UserPasswordHistoryDomain> findByUserDomain(UserDomain user);

    public UserPasswordHistoryDomain findByPasswordHash(String passwordHash, UserDomain user);
}
