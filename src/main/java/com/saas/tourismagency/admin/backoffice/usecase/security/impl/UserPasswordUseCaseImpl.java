package com.saas.tourismagency.admin.backoffice.usecase.security.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.userpassword.UserPasswordConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultUserPassword;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpassword.UserPasswordRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.password.PasswordService;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordDomain;
import com.saas.tourismagency.admin.backoffice.usecase.security.UserPasswordUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class UserPasswordUseCaseImpl implements UserPasswordUseCase {

    @Autowired
    private UserPasswordRepo repo;

    @Autowired
    private UserPasswordConverter converter;

    @Autowired
    private SetFieldDefaultUserPassword setFieldDefault;

    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public UserPasswordDomain findByUser(UserDomain user) {
        return repo.findByUser(user);
    }

    @Override
    @Transactional
    public UserPasswordDomain create(String plainPassword, UserDomain user) {
        UserPasswordDomain newDomain = _convertPass(plainPassword, user);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        UserPasswordDomain createdDomain = repo.create(newDomain);
        return createdDomain;
    }

    @Override
    @Transactional
    public UserPasswordDomain edit(String plainPassword, UserDomain user) {
        UserPasswordDomain oldPass = findByUser(user);
        UserPasswordDomain convertedPass = _convertPass(
                plainPassword,
                user
        );
        convertedPass.setId(oldPass.getId());
        return repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(convertedPass, oldPass));
    }

    private UserPasswordDomain _convertPass(String plainPassword, UserDomain user) {
        String salt = UUID.randomUUID().toString();
        String passHash = passwordService.hash(plainPassword, salt);
        return new UserPasswordDomain(
                salt,
                passHash,
                user
        );
    }

}
