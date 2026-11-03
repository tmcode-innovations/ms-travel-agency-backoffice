package com.saas.tourismagency.admin.backoffice.usecase.security;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.security.changepassword.ChangePasswordRequest;

public interface ChangePasswordUseCase {

    public void changePassword(ChangePasswordRequest changePasswordRequest);

}
