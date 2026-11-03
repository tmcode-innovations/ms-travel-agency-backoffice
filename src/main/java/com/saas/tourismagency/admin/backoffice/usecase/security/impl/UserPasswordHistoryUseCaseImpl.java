package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.userpasswordhistory.UserPasswordHistoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultUserPassword;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpasswordhistory.UserPasswordHistoryRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.password.PasswordService;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.UserPasswordHistoryUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserPasswordHistoryUseCaseImpl implements UserPasswordHistoryUseCase {

    @Autowired
    private UserPasswordHistoryRepo repo;

    @Autowired
    private UserPasswordHistoryConverter converter;

    @Autowired
    private SetFieldDefaultUserPassword setFieldDefault;

    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public UserPasswordHistoryDomain findByUser(UserDomain user) {
        return repo.findByUser(user);
    }

    @Override
    @Transactional
    public UserPasswordHistoryDomain create(UserPasswordDomain userPasswordDomain, UserDomain user) {
        UserPasswordHistoryDomain newDomain = new UserPasswordHistoryDomain();
        newDomain.setUser(user);
        newDomain.setPasswordHash(userPasswordDomain.getPasswordHash());
        newDomain.setSalt(userPasswordDomain.getSalt());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        UserPasswordHistoryDomain createdDomain = repo.create(newDomain);
        return createdDomain;
    }

    @Override
    @Transactional
    public List<UserPasswordHistoryDomain> findByUserDomain(UserDomain user) {
        return repo.findByUser(user.getId());
    }

    @Override
    @Transactional
    public UserPasswordHistoryDomain findByPasswordHash(String passwordHash, UserDomain user) {
        return repo.findByPasswordHash(passwordHash, user.getId());
    }

}
