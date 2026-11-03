package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.users.user.*;

import java.util.List;

public interface UserUseCase {

    public UserResponse create(UserCreateRequest request);

    public UserResponse edit(String id, UserUpdateRequest request);

    public void deleteById(String id);

    public List<UserResponse> findAll();

    public UserResponse findById(String id);

    public UserSearchResponse searchAll(UserSearchRequest userSearchRequest);

    public UserSearchResponse searchFilterAll(UserSearchFilterRequest userSearchFilterRequest);

    public UserResponse findByUserName(String userName);

    public UserResponse findByEmail(String email);

    public UserResponse findByUserNameOrEmail(String usermail);

    public List<SimpleBaseResponse> findAllSimple();
}
