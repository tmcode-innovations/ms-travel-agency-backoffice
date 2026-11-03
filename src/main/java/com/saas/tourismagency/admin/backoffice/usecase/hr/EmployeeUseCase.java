package com.saas.tourismagency.admin.backoffice.usecase.hr;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeUseCase {

    public EmployeeResponse create(EmployeeCreateRequest request);

    public EmployeeResponse edit(String id, EmployeeUpdateRequest request);

    public void deleteById(String id);

    public List<EmployeeResponse> findAll();

    public EmployeeResponse findById(String id);

    public EmployeeSearchResponse searchAll(EmployeeSearchRequest aeaSearchRequest);

    public EmployeeSearchResponse searchFilterAll(EmployeeSearchFilterRequest aeaSearchFilterRequest);

    public EmployeeResponse findByName(String name);

    public EmployeeResponse findByIdentify(String identify);

    public List<SimpleBaseResponse> findAllSimple();

    public List<SimpleBaseResponse> findAllSimpleWithConditionalArea();

    public EmployeeResponse uploadSign(String id, MultipartFile[] multipartFile);

    public ByteArrayResource downloadSign(String idEmployee);

    public EmployeeResponse findByActiveUser();
}
