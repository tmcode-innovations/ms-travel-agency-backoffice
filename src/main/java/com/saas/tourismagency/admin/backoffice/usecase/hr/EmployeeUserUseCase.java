package com.saas.tourismagency.admin.backoffice.usecase.hr;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.*;

import java.util.List;

public interface EmployeeUserUseCase {

    public EmployeeUserResponse create(EmployeeUserCreateRequest request);

    public EmployeeUserResponse edit(String id, EmployeeUserUpdateRequest request);

    public void deleteById(String id);

    public List<EmployeeUserResponse> findAll();

    public EmployeeUserResponse findById(String id);

    public EmployeeUserSearchResponse searchAll(EmployeeUserSearchRequest aeaSearchRequest);

    public EmployeeUserSearchResponse searchFilterAll(EmployeeUserSearchFilterRequest aeaSearchFilterRequest);

    public EmployeeUserResponse findByName(String name);

    public EmployeeUserResponse findByIdentify(String identify);

    public EmployeeUserResponse findByEmployee(String idEmployee);

    public EmployeeUserResponse findByUser(String idUser);
}
