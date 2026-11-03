package com.saas.tourismagency.admin.backoffice.usecase.hr.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employeeuser.EmployeeUserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.hr.SetFieldDefaultEmployeeUser;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.hr.EmployeeUserUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EmployeeUserUseCaseImpl implements EmployeeUserUseCase {

    @Autowired
    private EmployeeUserRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeUserConverter converter;

    @Autowired
    private SetFieldDefaultEmployeeUser setFieldDefault;

    @Override
    @Transactional
    public EmployeeUserResponse create(EmployeeUserCreateRequest request) {
        UserDomain userDomain = null;
        EmployeeDomain employeeDomain = null;

        if (request.getUser() != null && !request.getUser().isEmpty()){
            userDomain = userRepo.findById(request.getUser());
        }
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            employeeDomain = employeeRepo.findById(request.getEmployee());
        }

        EmployeeUserDomain newDomain = request.to(
                employeeDomain,
                userDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        EmployeeUserDomain createdDomain = repo.create(newDomain);
        return EmployeeUserResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public EmployeeUserResponse edit(String id, EmployeeUserUpdateRequest request) {
        if (id == null) {
            return null;
        }
        EmployeeUserDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No employee user found with id '%s'.".formatted(id));
        }
        EmployeeDomain employeeDomain = null;
        UserDomain userDomain = null;
        if (request.getUser() != null && !request.getUser().isEmpty()){
            userDomain = userRepo.findById(request.getUser());
        }
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            employeeDomain = employeeRepo.findById(request.getEmployee());
        }

        EmployeeUserDomain employeeUserDomain = request.to(
                employeeDomain,
                userDomain
        );
        employeeDomain.setIdentify(old.getIdentify());
        EmployeeUserDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(employeeUserDomain, old));
        return EmployeeUserResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        EmployeeUserResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeUserResponse> findAll() {
        return repo.findAll(false).stream().map(employeeUserDomain -> EmployeeUserResponse.from(employeeUserDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeUserResponse findById(String id) {
        if (id == null) {
            return null;
        }
        EmployeeUserDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No employee user found with id '%s'.".formatted(id));
        }
        return EmployeeUserResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeUserSearchResponse searchAll(EmployeeUserSearchRequest employeeUserSearchRequest) {
       PageDTO<EmployeeUserDomain> pageDTO = repo.searchAll(employeeUserSearchRequest);
       EmployeeUserSearchResponse employeeUserSearchRespose = new EmployeeUserSearchResponse();
       employeeUserSearchRespose.setEmployeeUsers(pageDTO.getContent().stream().map(employeeUserDomain ->  EmployeeUserResponse.from(employeeUserDomain)).toList());
       employeeUserSearchRespose.setTotalElements(pageDTO.getTotalElements());
       employeeUserSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return employeeUserSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeUserSearchResponse searchFilterAll(EmployeeUserSearchFilterRequest employeeUserSearchFilterRequest) {
        PageDTO<EmployeeUserDomain> pageDTO = repo.searchAllFilter(employeeUserSearchFilterRequest);
        EmployeeUserSearchResponse employeeUserSearchRespose = new EmployeeUserSearchResponse();
        employeeUserSearchRespose.setEmployeeUsers(pageDTO.getContent().stream().map(employeeUserDomain ->  EmployeeUserResponse.from(employeeUserDomain)).toList());
        employeeUserSearchRespose.setTotalElements(pageDTO.getTotalElements());
        employeeUserSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return employeeUserSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeUserResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        EmployeeUserDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No employee user found with name '%s'.".formatted(name));
        }
        return EmployeeUserResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeUserResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        EmployeeUserDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No employee user found with identify '%s'.".formatted(identify));
        }
        return EmployeeUserResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeUserResponse findByEmployee(String idEmployee) {
        if (idEmployee == null) {
            return null;
        }
        EmployeeUserDomain old = repo.getByEmployee(idEmployee);
        if (old == null) {
            throw new NotFoundException("No employee user found with id employee '%s'.".formatted(idEmployee));
        }
        return EmployeeUserResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeUserResponse findByUser(String idUser) {
        if (idUser == null) {
            return null;
        }
        EmployeeUserDomain old = repo.getByEmployee(idUser);
        if (old == null) {
            throw new NotFoundException("No employee user found with id user '%s'.".formatted(idUser));
        }
        return EmployeeUserResponse.from(old);
    }
}
