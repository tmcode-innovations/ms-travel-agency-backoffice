package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;

public interface UserPasswordUseCase {

    public UserPasswordDomain findByUser(UserDomain user);

    public UserPasswordDomain create(String plainPassword, UserDomain user);

    public UserPasswordDomain edit(String plainPassword, UserDomain user);

}
