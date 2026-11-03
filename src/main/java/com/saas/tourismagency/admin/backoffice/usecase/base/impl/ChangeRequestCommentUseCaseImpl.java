package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.changerequest.ChangeRequestCommentConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultChangeRequestComment;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.ChangeRequestCommentRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.ChangeRequestModelRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification.NotificationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestCommentDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestCreatedEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationStatusEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.ChangeRequestCommentUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChangeRequestCommentUseCaseImpl implements ChangeRequestCommentUseCase {

    @Autowired
    private ChangeRequestCommentRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private ChangeRequestModelRepo changeRequestRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private ObjectRepo objectRepo;

    @Autowired
    private ChangeRequestCommentConverter converter;

    @Autowired
    private SetFieldDefaultChangeRequestComment setFieldDefault;

    @Override
    @Transactional
    public ChangeRequestCommentResponse create(ChangeRequestCommentCreateRequest request) {
        UserDomain userDomain = null;
        EmployeeDomain employeeDomain = null;
        EmployeeUserDomain employeeUserDomain = null;
        RegisterGroupDomain registerGroupDomain = null;
        QuotationDomain quotationDomain = null;
        if (request.getCreated().equals(ChangeRequestCreatedEnum.QUOTATION.getName())){
            if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
                quotationDomain = quotationRepo.findById(request.getQuotation());
            }
            employeeDomain = quotationDomain.getEmployee();
            if (employeeDomain != null){
                employeeUserDomain = employeeUserRepo.getByEmployee(employeeDomain.getId());
                if (employeeUserDomain != null){
                    userDomain = employeeUserDomain.getUser();
                }
            }
        }else if (request.getCreated().equals(ChangeRequestCreatedEnum.REGISTER_GROUP.getName())){
            if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
                registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
            }
            employeeDomain = registerGroupDomain.getResponsible();
            if (employeeDomain != null){
                employeeUserDomain = employeeUserRepo.getByEmployee(employeeDomain.getId());
                if (employeeUserDomain != null){
                    userDomain = employeeUserDomain.getUser();
                }
            }
        }
        ChangeRequestDomain changeRequestDomain = null;
        if (request.getChangeRequest() != null && !request.getChangeRequest().isEmpty()){
            changeRequestDomain = changeRequestRepo.findById(request.getChangeRequest());
        }

        request.setDateTime(LocalDateTime.now());

        ChangeRequestCommentDomain newDomain = request.to(userDomain, employeeDomain, changeRequestDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ChangeRequestCommentDomain createdDomain = repo.create(newDomain);
        return ChangeRequestCommentResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ChangeRequestCommentResponse create(ChangeRequestCommentCreateRequest request, ChangeRequestDomain changeRequestDomain) {
        UserDomain userDomain = null;
        EmployeeDomain employeeDomain = null;
        if (request.getCreated().equals(ChangeRequestCreatedEnum.QUOTATION.getName())){
            userDomain = UserResolver.resolveUser();
            EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(userDomain.getId());
            if (employeeUserSourceDomain != null){
                employeeDomain = employeeUserSourceDomain.getEmployee();
            }

            NotificationDomain notificationDomain = new NotificationDomain();
            notificationDomain.setType(NotificationTypeEnum.SEND_COMMENT.getName());
            notificationDomain.setRead(false);
            notificationDomain.setDateTime(LocalDateTime.now());
            notificationDomain.setSubject("Se ha enviado un comentario por el usuario " + UserResolver.resolveUser().getFullName());
            notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

            notificationDomain.setUserSource(userDomain);
            notificationDomain.setEmployeeSource(employeeDomain);

            notificationDomain.setUserDestine(changeRequestDomain.getUserReceive());
            notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeReceive());

            notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

            ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
            ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

            notificationDomain.setIdObjectSource(changeRequestDomain.getRegisterGroup().getId());
            notificationDomain.setNameObjectSource(changeRequestDomain.getRegisterGroup().getName());
            notificationDomain.setIdentifyObjectSource(changeRequestDomain.getRegisterGroup().getIdentify());
            notificationDomain.setObjectEntitySource(objectDomainSource);

            notificationDomain.setIdObjectDestine(changeRequestDomain.getQuotation().getId());
            notificationDomain.setNameObjectDestine(changeRequestDomain.getQuotation().getName());
            notificationDomain.setIdentifyObjectDestine(changeRequestDomain.getQuotation().getIdentify());
            notificationDomain.setObjectEntityDestine(objectDomainDestine);

            notificationRepo.create(notificationDomain);


        }else if (request.getCreated().equals(ChangeRequestCreatedEnum.REGISTER_GROUP.getName())){
            userDomain = UserResolver.resolveUser();
            EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(userDomain.getId());
            if (employeeUserSourceDomain != null){
                employeeDomain = employeeUserSourceDomain.getEmployee();
            }

            NotificationDomain notificationDomain = new NotificationDomain();
            notificationDomain.setType(NotificationTypeEnum.SEND_COMMENT.getName());
            notificationDomain.setRead(false);
            notificationDomain.setDateTime(LocalDateTime.now());
            notificationDomain.setSubject("Se ha enviado un comentario por el usuario " + UserResolver.resolveUser().getFullName());
            notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

            notificationDomain.setUserSource(userDomain);
            notificationDomain.setEmployeeSource(employeeDomain);

            notificationDomain.setUserDestine(changeRequestDomain.getUserReceive());
            notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeReceive());

            notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

            ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");
            ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_QUOTATION");

            notificationDomain.setIdObjectSource(changeRequestDomain.getRegisterGroup().getId());
            notificationDomain.setNameObjectSource(changeRequestDomain.getRegisterGroup().getName());
            notificationDomain.setIdentifyObjectSource(changeRequestDomain.getRegisterGroup().getIdentify());
            notificationDomain.setObjectEntitySource(objectDomainSource);

            notificationDomain.setIdObjectDestine(changeRequestDomain.getQuotation().getId());
            notificationDomain.setNameObjectDestine(changeRequestDomain.getQuotation().getName());
            notificationDomain.setIdentifyObjectDestine(changeRequestDomain.getQuotation().getIdentify());
            notificationDomain.setObjectEntityDestine(objectDomainDestine);

            notificationRepo.create(notificationDomain);

        }
        request.setDateTime(LocalDateTime.now());

        ChangeRequestCommentDomain newDomain = request.to(userDomain, employeeDomain, changeRequestDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ChangeRequestCommentDomain createdDomain = repo.create(newDomain);

        return ChangeRequestCommentResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ChangeRequestCommentResponse edit(String id, ChangeRequestCommentUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ChangeRequestCommentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No change request comment found with id '%s'.".formatted(id));
        }

        ChangeRequestDomain changeRequestDomain = null;
        if (request.getChangeRequest() != null && !request.getChangeRequest().isEmpty()){
            changeRequestDomain = changeRequestRepo.findById(request.getChangeRequest());
        }

        ChangeRequestCommentDomain changeRequestCommentDomain = request.to(old.getUser(), old.getEmployee(), changeRequestDomain);
        changeRequestCommentDomain.setDateTime(old.getDateTime());
        ChangeRequestCommentDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(changeRequestCommentDomain, old));
        return ChangeRequestCommentResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ChangeRequestCommentResponse edit(String id, ChangeRequestCommentUpdateRequest request, ChangeRequestDomain changeRequestDomain) {
        if (id == null) {
            return null;
        }
        ChangeRequestCommentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No change request comment found with id '%s'.".formatted(id));
        }

        ChangeRequestCommentDomain changeRequestCommentDomain = request.to(old.getUser(), old.getEmployee(), changeRequestDomain);
        changeRequestCommentDomain.setDateTime(old.getDateTime());
        ChangeRequestCommentDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(changeRequestCommentDomain, old));
        return ChangeRequestCommentResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ChangeRequestCommentResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestCommentResponse> findAll() {
        return repo.findAll(false).stream().map(changeRequestCommentDomain -> ChangeRequestCommentResponse.from(changeRequestCommentDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestCommentResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ChangeRequestCommentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No chane request comment found with id '%s'.".formatted(id));
        }
        return ChangeRequestCommentResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestCommentSearchResponse searchAll(ChangeRequestCommentSearchRequest changeRequestCommentSearchRequest) {
       PageDTO<ChangeRequestCommentDomain> pageDTO = repo.searchAll(changeRequestCommentSearchRequest);
       ChangeRequestCommentSearchResponse changeRequestCommentSearchRespose = new ChangeRequestCommentSearchResponse();
       changeRequestCommentSearchRespose.setChangeRequestComments(pageDTO.getContent().stream().map(changeRequestCommentDomain ->  ChangeRequestCommentResponse.from(changeRequestCommentDomain)).toList());
       changeRequestCommentSearchRespose.setTotalElements(pageDTO.getTotalElements());
       changeRequestCommentSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return changeRequestCommentSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestCommentSearchResponse searchFilterAll(ChangeRequestCommentSearchFilterRequest changeRequestCommentSearchFilterRequest) {
        PageDTO<ChangeRequestCommentDomain> pageDTO = repo.searchAllFilter(changeRequestCommentSearchFilterRequest);
        ChangeRequestCommentSearchResponse changeRequestCommentSearchRespose = new ChangeRequestCommentSearchResponse();
        changeRequestCommentSearchRespose.setChangeRequestComments(pageDTO.getContent().stream().map(changeRequestCommentDomain ->  ChangeRequestCommentResponse.from(changeRequestCommentDomain)).toList());
        changeRequestCommentSearchRespose.setTotalElements(pageDTO.getTotalElements());
        changeRequestCommentSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return changeRequestCommentSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestCommentResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ChangeRequestCommentDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No change request comment found with name '%s'.".formatted(name));
        }
        return ChangeRequestCommentResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestCommentResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ChangeRequestCommentDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No change request comment found with identify '%s'.".formatted(identify));
        }
        return ChangeRequestCommentResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestCommentResponse searchByChangeRequestAndChangeRequestComment(String idChangeRequest, String idChangeRequestComment) {
        ChangeRequestCommentDomain changeRequestCommentDomain = repo.searchAllByChangeRequestAndChangeRequestComment(idChangeRequest, idChangeRequestComment);
        return ChangeRequestCommentResponse.from(changeRequestCommentDomain);
    }
}
