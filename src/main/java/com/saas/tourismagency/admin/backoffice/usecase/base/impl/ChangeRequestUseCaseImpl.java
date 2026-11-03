package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.changerequest.ChangeRequestConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultChangeRequest;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.ChangeRequestCommentRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.ChangeRequestModelRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification.NotificationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequest.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.changerequestcomment.ChangeRequestCommentUpdateRequest;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestCommentDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ChangeRequestDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestCreatedEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.ChangeRequestStatusEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationStatusEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateQuotationEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.ChangeRequestCommentUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.base.ChangeRequestUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChangeRequestUseCaseImpl implements ChangeRequestUseCase {

    @Autowired
    private ChangeRequestModelRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private ObjectRepo objectRepo;

    @Autowired
    private ChangeRequestCommentRepo changeRequestCommentRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private ChangeRequestCommentUseCase changeRequestCommentUseCase;

    @Autowired
    private ChangeRequestConverter converter;

    @Autowired
    private SetFieldDefaultChangeRequest setFieldDefault;

    @Override
    @Transactional
    public ChangeRequestResponse create(ChangeRequestCreateRequest request) {
        RegisterGroupDomain registerGroupDomain = null;
        QuotationDomain quotationDomain = null;
        EmployeeDomain employeeEmitDomain = null;
        EmployeeUserDomain employeeUserEmitDomain = null;
        EmployeeDomain employeeReceiveDomain = null;
        EmployeeUserDomain employeeUserReceiveDomain = null;
        UserDomain userEmitDomain = null;
        UserDomain userReceiveDomain = null;
        ObjectDomain objectEntityEmitDomain = null;
        ObjectDomain objectEntityReceiveDomain = null;

        if (request.getCreated().equals(ChangeRequestCreatedEnum.QUOTATION.getName())){
            if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
                quotationDomain = quotationRepo.findById(request.getQuotation());
            }
            if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
                registerGroupDomain = registerGroupRepo.findByIdentify(request.getRegisterGroup());
            }

            List<ChangeRequestDomain> listChangeRequestActive = repo.searchAllByQuotationAndRegisterGroupAndActive(quotationDomain.getId(), registerGroupDomain.getId());
            if (listChangeRequestActive != null && !listChangeRequestActive.isEmpty()){
                throw new BadRequestException(MessagesValidation.VALIDATION_CHANGE_REQUEST_ACTIVE);
            }

            employeeEmitDomain = quotationDomain.getEmployee();
            if (employeeEmitDomain != null){
                employeeUserEmitDomain = employeeUserRepo.getByEmployee(employeeEmitDomain.getId());
                if (employeeUserEmitDomain != null){
                    userEmitDomain = employeeUserEmitDomain.getUser();
                }
            }
            employeeReceiveDomain = registerGroupDomain.getResponsible();
            if (employeeReceiveDomain != null){
                employeeUserReceiveDomain = employeeUserRepo.getByEmployee(employeeReceiveDomain.getId());
                if (employeeUserReceiveDomain != null){
                    userReceiveDomain = employeeUserReceiveDomain.getUser();
                }
            }
            objectEntityEmitDomain = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
            objectEntityReceiveDomain = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

            request.setIdObjectEmit(quotationDomain.getId());
            request.setIdentifyObjectEmit(quotationDomain.getIdentify());
            request.setNameObjectEmit(quotationDomain.getName());

            request.setIdObjectReceive(registerGroupDomain.getId());
            request.setIdentifyObjectReceive(registerGroupDomain.getIdentify());
            request.setNameObjectReceive(registerGroupDomain.getName());
        }else if (request.getCreated().equals(ChangeRequestCreatedEnum.REGISTER_GROUP.getName())){
            //
        }

        Set<String> categories = new HashSet<>();
        if (request.getCategories() != null && !request.getCategories().isEmpty()){
            categories = request.getCategories().stream().collect(Collectors.toSet());
        }

        request.setDateTime(LocalDateTime.now());
        ChangeRequestDomain newDomain = request.to(userEmitDomain, employeeEmitDomain, objectEntityEmitDomain, userReceiveDomain, employeeReceiveDomain, objectEntityReceiveDomain, registerGroupDomain, quotationDomain, categories);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setStatus(ChangeRequestStatusEnum.DRAFT.getName());
        newDomain.setActiveChangeRequest(true);
        ChangeRequestDomain createdDomain = repo.create(newDomain);
        return ChangeRequestResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ChangeRequestResponse createAndSent(ChangeRequestCreateRequest request) {
        RegisterGroupDomain registerGroupDomain = null;
        QuotationDomain quotationDomain = null;
        EmployeeDomain employeeEmitDomain = null;
        EmployeeUserDomain employeeUserEmitDomain = null;
        EmployeeDomain employeeReceiveDomain = null;
        EmployeeUserDomain employeeUserReceiveDomain = null;
        UserDomain userEmitDomain = null;
        UserDomain userReceiveDomain = null;
        ObjectDomain objectEntityEmitDomain = null;
        ObjectDomain objectEntityReceiveDomain = null;

        if (request.getCreated().equals(ChangeRequestCreatedEnum.QUOTATION.getName())){
            if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
                quotationDomain = quotationRepo.findById(request.getQuotation());
            }
            if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
                registerGroupDomain = registerGroupRepo.findByIdentify(request.getRegisterGroup());
            }

            List<ChangeRequestDomain> listChangeRequestActive = repo.searchAllByQuotationAndRegisterGroupAndActive(quotationDomain.getId(), registerGroupDomain.getId());
            if (listChangeRequestActive != null && !listChangeRequestActive.isEmpty()){
                throw new BadRequestException(MessagesValidation.VALIDATION_CHANGE_REQUEST_ACTIVE);
            }

            employeeEmitDomain = quotationDomain.getEmployee();
            if (employeeEmitDomain != null){
                employeeUserEmitDomain = employeeUserRepo.getByEmployee(employeeEmitDomain.getId());
                if (employeeUserEmitDomain != null){
                    userEmitDomain = employeeUserEmitDomain.getUser();
                }
            }
            employeeReceiveDomain = registerGroupDomain.getResponsible();
            if (employeeReceiveDomain != null){
                employeeUserReceiveDomain = employeeUserRepo.getByEmployee(employeeReceiveDomain.getId());
                if (employeeUserReceiveDomain != null){
                    userReceiveDomain = employeeUserReceiveDomain.getUser();
                }
            }
            objectEntityEmitDomain = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
            objectEntityReceiveDomain = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

            request.setIdObjectEmit(quotationDomain.getId());
            request.setIdentifyObjectEmit(quotationDomain.getIdentify());
            request.setNameObjectEmit(quotationDomain.getName());

            request.setIdObjectReceive(registerGroupDomain.getId());
            request.setIdentifyObjectReceive(registerGroupDomain.getIdentify());
            request.setNameObjectReceive(registerGroupDomain.getName());
        }else if (request.getCreated().equals(ChangeRequestCreatedEnum.REGISTER_GROUP.getName())){
            //
        }

        Set<String> categories = new HashSet<>();
        if (request.getCategories() != null && !request.getCategories().isEmpty()){
            categories = request.getCategories().stream().collect(Collectors.toSet());
        }

        UserDomain userSentByDomain = null;
        EmployeeDomain employeeSentByDomain = null;

        userSentByDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(userSentByDomain.getId());
        if (employeeUserSourceDomain != null){
            employeeSentByDomain = employeeUserSourceDomain.getEmployee();
        }

        request.setDateTime(LocalDateTime.now());
        ChangeRequestDomain newDomain = request.to(userEmitDomain, employeeEmitDomain, objectEntityEmitDomain, userReceiveDomain, employeeReceiveDomain, objectEntityReceiveDomain, registerGroupDomain, quotationDomain, categories);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setStatus(ChangeRequestStatusEnum.SENT.getName());
        newDomain.setActiveChangeRequest(true);

        newDomain.setUserSentBy(userSentByDomain);
        newDomain.setEmployeeSentBy(employeeSentByDomain);

        ChangeRequestDomain createdDomain = repo.create(newDomain);

        if (request.getEmployees() != null && !request.getEmployees().isEmpty()) {
            for (String item : request.getEmployees()) {
                NotificationDomain notificationDomain = new NotificationDomain();
                notificationDomain.setType(NotificationTypeEnum.REQUEST_SENT.getName());
                notificationDomain.setRead(false);
                notificationDomain.setDateTime(LocalDateTime.now());
                notificationDomain.setSubject("Has recibido una nueva solicitud de cambio de la cotización (" + createdDomain.getQuotation().getName() + ")");
                notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

                notificationDomain.setUserSource(createdDomain.getUserEmit());
                notificationDomain.setEmployeeSource(createdDomain.getEmployeeEmit());

                EmployeeDomain employeeDomain = null;
                if (item != null && !item.isEmpty()){
                    employeeDomain = employeeRepo.findById(item);
                }

                if (employeeDomain != null){
                    notificationDomain.setEmployeeDestine(employeeDomain);
                    EmployeeUserDomain employeeUserDestineDomain = employeeUserRepo.getByEmployee(notificationDomain.getEmployeeDestine().getId());
                    if (employeeUserDestineDomain != null){
                        notificationDomain.setUserDestine(employeeUserDestineDomain.getUser());
                    }
                }else{
                    notificationDomain.setUserDestine(createdDomain.getUserReceive());
                    notificationDomain.setEmployeeDestine(createdDomain.getEmployeeReceive());
                }

                notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

                ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
                ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

                notificationDomain.setIdObjectSource(createdDomain.getQuotation().getId());
                notificationDomain.setNameObjectSource(createdDomain.getQuotation().getName());
                notificationDomain.setIdentifyObjectSource(createdDomain.getQuotation().getIdentify());
                notificationDomain.setObjectEntitySource(objectDomainSource);

                notificationDomain.setIdObjectDestine(createdDomain.getRegisterGroup().getId());
                notificationDomain.setNameObjectDestine(createdDomain.getRegisterGroup().getName());
                notificationDomain.setIdentifyObjectDestine(createdDomain.getRegisterGroup().getIdentify());
                notificationDomain.setObjectEntityDestine(objectDomainDestine);

                notificationRepo.create(notificationDomain);
            }
        }
        return ChangeRequestResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ChangeRequestResponse edit(String id, ChangeRequestUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ChangeRequestDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(id));
        }

        RegisterGroupDomain registerGroupDomain = old.getRegisterGroup();
        QuotationDomain quotationDomain = old.getQuotation();
        EmployeeDomain employeeEmitDomain = old.getEmployeeEmit();
        EmployeeDomain employeeReceiveDomain = old.getEmployeeReceive();
        UserDomain userEmitDomain = old.getUserEmit();
        UserDomain userReceiveDomain = old.getUserReceive();
        ObjectDomain objectEntityEmitDomain = old.getObjectEntityEmit();
        ObjectDomain objectEntityReceiveDomain = old.getObjectEntityReceive();

        Set<String> categories = new HashSet<>();
        if (request.getCategories() != null && !request.getCategories().isEmpty()){
            categories = request.getCategories().stream().collect(Collectors.toSet());
        }

        ChangeRequestDomain changeRequestDomain = request.to(userEmitDomain, employeeEmitDomain, objectEntityEmitDomain, userReceiveDomain, employeeReceiveDomain, objectEntityReceiveDomain, registerGroupDomain, quotationDomain, categories);
        changeRequestDomain.setDateTime(old.getDateTime());
        changeRequestDomain.setStatus(old.getStatus());
        changeRequestDomain.setActiveChangeRequest(old.getActiveChangeRequest());

        changeRequestDomain.setUserSentBy(old.getUserSentBy());
        changeRequestDomain.setEmployeeSentBy(old.getEmployeeSentBy());

        changeRequestDomain.setUserAttendedBy(old.getUserAttendedBy());
        changeRequestDomain.setEmployeeAttendedBy(old.getEmployeeAttendedBy());

        changeRequestDomain.setUserRefusedBy(old.getUserRefusedBy());
        changeRequestDomain.setEmployeeRefusedBy(old.getEmployeeRefusedBy());

        changeRequestDomain.setUserCompletedBy(old.getUserCompletedBy());
        changeRequestDomain.setEmployeeCompletedBy(old.getEmployeeCompletedBy());

        changeRequestDomain.setUserAcceptedBy(old.getUserAcceptedBy());
        changeRequestDomain.setEmployeeAcceptedBy(old.getEmployeeAcceptedBy());

        ChangeRequestDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(changeRequestDomain, old));
        return ChangeRequestResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ChangeRequestResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestResponse> findAll() {
        return repo.findAll(false).stream().map(changeRequestDomain -> ChangeRequestResponse.from(changeRequestDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ChangeRequestDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(id));
        }
        return ChangeRequestResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestSearchResponse searchAll(ChangeRequestSearchRequest changeRequestSearchRequest) {
       PageDTO<ChangeRequestDomain> pageDTO = repo.searchAll(changeRequestSearchRequest);
       ChangeRequestSearchResponse changeRequestSearchRespose = new ChangeRequestSearchResponse();
       changeRequestSearchRespose.setChangeRequests(pageDTO.getContent().stream().map(changeRequestDomain ->  ChangeRequestResponse.from(changeRequestDomain)).toList());
       changeRequestSearchRespose.setTotalElements(pageDTO.getTotalElements());
       changeRequestSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return changeRequestSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestSearchResponse searchFilterAll(ChangeRequestSearchFilterRequest changeRequestSearchFilterRequest) {
        PageDTO<ChangeRequestDomain> pageDTO = repo.searchAllFilter(changeRequestSearchFilterRequest);
        ChangeRequestSearchResponse changeRequestSearchRespose = new ChangeRequestSearchResponse();
        changeRequestSearchRespose.setChangeRequests(pageDTO.getContent().stream().map(changeRequestDomain ->  ChangeRequestResponse.from(changeRequestDomain)).toList());
        changeRequestSearchRespose.setTotalElements(pageDTO.getTotalElements());
        changeRequestSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return changeRequestSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        ChangeRequestDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No change request found with name '%s'.".formatted(name));
        }
        return ChangeRequestResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        ChangeRequestDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No change request found with identify '%s'.".formatted(identify));
        }
        return ChangeRequestResponse.from(old);
    }

    @Override
    @Transactional
    public ChangeRequestResponse changeState(String id, ChangeRequestChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        ChangeRequestDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(id));
        }
        switch (request.getStateChangeRequest()) {
            case "Sent" -> {
                _changeStateSentPre(old, request);
            }
            case "Accepted" -> {
                _changeStateAcceptedPre(old, request);
            }
            case "Attended" -> {
                _changeStateAttendedPre(old, request);
            }
            case "Refused" -> {
                _changeStateRefusedPre(old, request);
            }
            case "Completed" -> {
                _changeStateCompletedPre(old, request);
            }
            case "Review" -> {
                _changeStateReviewPre(old, request);
            }
            default -> {

            }
        }

        old.setStatus(request.getStateChangeRequest());
        if (request.getStateChangeRequest().equals(ChangeRequestStatusEnum.COMPLETED.getName())){
            old.setActiveChangeRequest(false);
        }else if (request.getStateChangeRequest().equals(ChangeRequestStatusEnum.REFUSED.getName())){
            old.setActiveChangeRequest(false);
        }
        ChangeRequestDomain editedDomain = repo.edit(old);

        switch (request.getStateChangeRequest()) {
            case "Sent" -> {
                _changeStateSentPost(old, request);
            }
            case "Accepted" -> {
                _changeStateAcceptedPost(old, request);
            }
            case "Attended" -> {
                _changeStateAttendedPost(old, request);
            }
            case "Refused" -> {
                _changeStateRefusedPost(old, request);
            }
            case "Completed" -> {
                _changeStateCompletedPost(old, request);
            }
            case "Review" -> {
                _changeStateReviewPost(old, request);
            }
            default -> {

            }
        }

        return ChangeRequestResponse.from(editedDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestCommentResponse> findAllChangeRequestCommentByChangeRequest(String idChangeRequest) {
        List<ChangeRequestCommentResponse> result = new ArrayList<>();
        if (idChangeRequest == null) {
            return null;
        }
        IdBaseDomain changeRequestIdBaseDomain = repo.findByIdEntity(idChangeRequest);
        if (changeRequestIdBaseDomain == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(idChangeRequest));
        }
        List<ChangeRequestCommentDomain> listChangeRequestComment = changeRequestCommentRepo.searchAllByChangeRequest(idChangeRequest);
        if (listChangeRequestComment != null && !listChangeRequestComment.isEmpty()){
            listChangeRequestComment = listChangeRequestComment.stream().collect(Collectors.toList());
            result = listChangeRequestComment.stream().map(changeRequestCommentDomain -> ChangeRequestCommentResponse.from(changeRequestCommentDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public ChangeRequestCommentResponse addChangeRequestComment(ChangeRequestCommentCreateRequest request, String idChangeRequest) {
        if (idChangeRequest == null) {
            return null;
        }
        ChangeRequestDomain changeRequestDomain = repo.findById(idChangeRequest);
        if (changeRequestDomain == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(idChangeRequest));
        }
        return changeRequestCommentUseCase.create(request, changeRequestDomain);
    }

    @Override
    @Transactional
    public ChangeRequestCommentResponse editChangeRequestComment(String id, ChangeRequestCommentUpdateRequest request, String idChangeRequest) {
        if (idChangeRequest == null) {
            return null;
        }
        IdBaseDomain changeRequestIdBaseDomain = repo.findByIdEntity(idChangeRequest);
        if (changeRequestIdBaseDomain == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(idChangeRequest));
        }
        ChangeRequestDomain changeRequestDomain = new ChangeRequestDomain();
        changeRequestDomain.setId(changeRequestIdBaseDomain.getId());
        return changeRequestCommentUseCase.edit(id, request, changeRequestDomain);
    }

    @Override
    @Transactional
    public void deleteChangeRequestComment(String id, String idChangeRequest) {
        if (idChangeRequest == null) {
            throw new BadRequestException("The change request id cannot be null");
        }
        IdBaseDomain changeRequestIdBaseDomain = repo.findByIdEntity(idChangeRequest);
        if (changeRequestIdBaseDomain == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(idChangeRequest));
        }
        changeRequestCommentUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestCommentResponse findByChangeRequestCommentAndChangeRequest(String id, String idChangeRequest) {
        if (idChangeRequest == null) {
            return null;
        }
        IdBaseDomain changeRequestIdBaseDomain = repo.findByIdEntity(idChangeRequest);
        if (changeRequestIdBaseDomain == null) {
            throw new NotFoundException("No change request found with id '%s'.".formatted(idChangeRequest));
        }
        return changeRequestCommentUseCase.searchByChangeRequestAndChangeRequestComment(idChangeRequest, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestResponse> findAllByRegisterGroup(String idRegisterGroup) {
        List<ChangeRequestResponse> result = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        List<ChangeRequestDomain> listChangeRequest = repo.searchAllByRegisterGroup(idRegisterGroup);
        if (listChangeRequest != null && !listChangeRequest.isEmpty()){
            listChangeRequest = listChangeRequest.stream().collect(Collectors.toList());
            result = listChangeRequest
                    .stream()
                    .filter(changeRequestDomain -> !changeRequestDomain.getStatus().equals(ChangeRequestStatusEnum.DRAFT.getName()))
                    .map(changeRequestDomain -> ChangeRequestResponse.from(changeRequestDomain))
                    .toList();
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChangeRequestResponse> findAllByQuotation(String idQuotation) {
        List<ChangeRequestResponse> result = new ArrayList<>();
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = quotationRepo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        List<ChangeRequestDomain> listChangeRequest = repo.searchAllByQuotation(idQuotation);
        if (listChangeRequest != null && !listChangeRequest.isEmpty()){
            listChangeRequest = listChangeRequest.stream().collect(Collectors.toList());
            result = listChangeRequest.stream().map(changeRequestDomain -> ChangeRequestResponse.from(changeRequestDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ChangeRequestResponse findChangeRequestActiveByQuotationAndRegisterGroup(String idQuotation, String idRegisterGroup) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = quotationRepo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        ChangeRequestDomain changeRequest = repo.findByQuotationAndRegisterGroupAndActive(idQuotation, registerGroupDomain.getId());
        if (changeRequest != null){
            return ChangeRequestResponse.from(changeRequest);
        }
        return null;
    }

    private void _changeStateSentPre(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){

    }

    private void _changeStateAcceptedPre(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){

    }

    private void _changeStateAttendedPre(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){

    }

    private void _changeStateRefusedPre(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){

    }

    private void _changeStateCompletedPre(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){

    }

    private void _changeStateReviewPre(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){

    }

    private void _changeStateSentPost(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){
        if (request.getEmployees() != null && !request.getEmployees().isEmpty()) {
            for (String item : request.getEmployees()) {
                NotificationDomain notificationDomain = new NotificationDomain();
                notificationDomain.setType(NotificationTypeEnum.REQUEST_SENT.getName());
                notificationDomain.setRead(false);
                notificationDomain.setDateTime(LocalDateTime.now());
                notificationDomain.setSubject("Has recibido una nueva solicitud de cambio de la cotización (" + changeRequestDomain.getQuotation().getName() + ")");
                notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

                // notificationDomain.setUserSource(changeRequestDomain.getUserEmit());
                // notificationDomain.setEmployeeSource(changeRequestDomain.getEmployeeEmit());

                notificationDomain.setUserSource(UserResolver.resolveUser());
                EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
                if (employeeUserSourceDomain != null){
                    notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
                }

                EmployeeDomain employeeDomain = null;
                if (item != null && !item.isEmpty()){
                    employeeDomain = employeeRepo.findById(item);
                }

                if (employeeDomain != null){
                    notificationDomain.setEmployeeDestine(employeeDomain);
                    EmployeeUserDomain employeeUserDestineDomain = employeeUserRepo.getByEmployee(notificationDomain.getEmployeeDestine().getId());
                    if (employeeUserDestineDomain != null){
                        notificationDomain.setUserDestine(employeeUserDestineDomain.getUser());
                    }
                }else{
                    notificationDomain.setUserDestine(changeRequestDomain.getUserReceive());
                    notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeReceive());
                }

                notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

                ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
                ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

                notificationDomain.setIdObjectSource(changeRequestDomain.getQuotation().getId());
                notificationDomain.setNameObjectSource(changeRequestDomain.getQuotation().getName());
                notificationDomain.setIdentifyObjectSource(changeRequestDomain.getQuotation().getIdentify());
                notificationDomain.setObjectEntitySource(objectDomainSource);

                notificationDomain.setIdObjectDestine(changeRequestDomain.getRegisterGroup().getId());
                notificationDomain.setNameObjectDestine(changeRequestDomain.getRegisterGroup().getName());
                notificationDomain.setIdentifyObjectDestine(changeRequestDomain.getRegisterGroup().getIdentify());
                notificationDomain.setObjectEntityDestine(objectDomainDestine);

                notificationRepo.create(notificationDomain);
            }
        }

        UserDomain userSentByDomain = null;
        EmployeeDomain employeeSentByDomain = null;

        userSentByDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserSentByDomain = employeeUserRepo.getByUser(userSentByDomain.getId());
        if (employeeUserSentByDomain != null){
            employeeSentByDomain = employeeUserSentByDomain.getEmployee();
        }

        changeRequestDomain.setUserSentBy(userSentByDomain);
        changeRequestDomain.setEmployeeSentBy(employeeSentByDomain);

        repo.edit(changeRequestDomain);
    }

    private void _changeStateAcceptedPost(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){
        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setType(NotificationTypeEnum.REQUEST_ACCEPTED.getName());
        notificationDomain.setRead(false);
        notificationDomain.setDateTime(LocalDateTime.now());
        notificationDomain.setSubject("Se ha aceptado el cambio, la cotización (" + changeRequestDomain.getQuotation().getName() + ") ha sido habilitada' y se habilita la cotización para que el vendedor pueda hacer el cambio requerido.");
        notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

        notificationDomain.setUserSource(UserResolver.resolveUser());
        EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
        if (employeeUserSourceDomain != null){
            notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
        }

        // notificationDomain.setUserSource(changeRequestDomain.getUserReceive());
        notificationDomain.setUserDestine(changeRequestDomain.getUserEmit());
        // notificationDomain.setEmployeeSource(changeRequestDomain.getEmployeeReceive());
        notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeEmit());

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

        if (request.getFreeQuotation() != null && request.getFreeQuotation()){
            changeRequestDomain.getRegisterGroup().setStateRegisterGroup(StateRegisterGroupEnum.REVIEW.getName());
            registerGroupRepo.edit(changeRequestDomain.getRegisterGroup());

            changeRequestDomain.getQuotation().setStateQuotation(StateQuotationEnum.REVIEW.getName());
            quotationRepo.edit(changeRequestDomain.getQuotation());
        }

        UserDomain userAcceptedByDomain = null;
        EmployeeDomain employeeAcceptedByDomain = null;

        userAcceptedByDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserAcceptedByDomain = employeeUserRepo.getByUser(userAcceptedByDomain.getId());
        if (employeeUserAcceptedByDomain != null){
            employeeAcceptedByDomain = employeeUserAcceptedByDomain.getEmployee();
        }

        changeRequestDomain.setUserAcceptedBy(userAcceptedByDomain);
        changeRequestDomain.setEmployeeAcceptedBy(employeeAcceptedByDomain);

        repo.edit(changeRequestDomain);
    }

    private void _changeStateAttendedPost(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){
        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setType(NotificationTypeEnum.CHANGE_ACCEPTED.getName());
        notificationDomain.setRead(false);
        notificationDomain.setDateTime(LocalDateTime.now());
        notificationDomain.setSubject("Se ha aceptado el cambio para la cotización (" + changeRequestDomain.getQuotation().getName() + ") lo realizará Reservas");
        notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

        notificationDomain.setUserSource(UserResolver.resolveUser());
        EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
        if (employeeUserSourceDomain != null){
            notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
        }

        // notificationDomain.setUserSource(changeRequestDomain.getUserReceive());
        notificationDomain.setUserDestine(changeRequestDomain.getUserEmit());
        // notificationDomain.setEmployeeSource(changeRequestDomain.getEmployeeReceive());
        notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeEmit());

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

        changeRequestDomain.getRegisterGroup().setStateRegisterGroup(StateRegisterGroupEnum.REVIEW.getName());
        registerGroupRepo.edit(changeRequestDomain.getRegisterGroup());

        UserDomain userAttendedByDomain = null;
        EmployeeDomain employeeAttendedByDomain = null;

        userAttendedByDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserAttendedByDomain = employeeUserRepo.getByUser(userAttendedByDomain.getId());
        if (employeeUserAttendedByDomain != null){
            employeeAttendedByDomain = employeeUserAttendedByDomain.getEmployee();
        }

        changeRequestDomain.setUserAttendedBy(userAttendedByDomain);
        changeRequestDomain.setEmployeeAttendedBy(employeeAttendedByDomain);

        repo.edit(changeRequestDomain);

    }

    private void _changeStateRefusedPost(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){
        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setType(NotificationTypeEnum.CHANGE_REFUSED.getName());
        notificationDomain.setRead(false);
        notificationDomain.setDateTime(LocalDateTime.now());
        notificationDomain.setSubject("La solicitud de cambio para la cotización (" + changeRequestDomain.getQuotation().getName() + ") ha sido rechazada");
        notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

        notificationDomain.setUserSource(UserResolver.resolveUser());
        EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
        if (employeeUserSourceDomain != null){
            notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
        }

        // notificationDomain.setUserSource(changeRequestDomain.getUserReceive());
        notificationDomain.setUserDestine(changeRequestDomain.getUserEmit());
        // notificationDomain.setEmployeeSource(changeRequestDomain.getEmployeeReceive());
        notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeEmit());

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

        UserDomain userRefusedByDomain = null;
        EmployeeDomain employeeRefusedByDomain = null;

        userRefusedByDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserRefusedByDomain = employeeUserRepo.getByUser(userRefusedByDomain.getId());
        if (employeeUserRefusedByDomain != null){
            employeeRefusedByDomain = employeeUserRefusedByDomain.getEmployee();
        }

        changeRequestDomain.setUserRefusedBy(userRefusedByDomain);
        changeRequestDomain.setEmployeeRefusedBy(employeeRefusedByDomain);

        repo.edit(changeRequestDomain);
    }

    private void _changeStateCompletedPost(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){
        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setType(NotificationTypeEnum.REQUEST_COMPLETED.getName());
        notificationDomain.setRead(false);
        notificationDomain.setDateTime(LocalDateTime.now());
        notificationDomain.setSubject("Se ha completado una solicitud de cambio");
        notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

        if (request.getCreated().equals(ChangeRequestCreatedEnum.QUOTATION.getName())){
            notificationDomain.setUserSource(UserResolver.resolveUser());
            EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
            if (employeeUserSourceDomain != null){
                notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
            }

            // notificationDomain.setUserSource(changeRequestDomain.getUserEmit());
            notificationDomain.setUserDestine(changeRequestDomain.getUserReceive());
            // notificationDomain.setEmployeeSource(changeRequestDomain.getEmployeeEmit());
            notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeReceive());

            notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

            ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
            ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

            notificationDomain.setIdObjectSource(changeRequestDomain.getQuotation().getId());
            notificationDomain.setNameObjectSource(changeRequestDomain.getQuotation().getName());
            notificationDomain.setIdentifyObjectSource(changeRequestDomain.getQuotation().getIdentify());
            notificationDomain.setObjectEntitySource(objectDomainSource);

            notificationDomain.setIdObjectDestine(changeRequestDomain.getRegisterGroup().getId());
            notificationDomain.setNameObjectDestine(changeRequestDomain.getRegisterGroup().getName());
            notificationDomain.setIdentifyObjectDestine(changeRequestDomain.getRegisterGroup().getIdentify());
            notificationDomain.setObjectEntityDestine(objectDomainDestine);
        }else if (request.getCreated().equals(ChangeRequestCreatedEnum.REGISTER_GROUP.getName())){

            changeRequestDomain.getQuotation().setStateQuotation(StateQuotationEnum.IN_BOOKING.getName());
            quotationRepo.edit(changeRequestDomain.getQuotation());

            changeRequestDomain.getRegisterGroup().setStateRegisterGroup(StateRegisterGroupEnum.OPENED.getName());
            registerGroupRepo.edit(changeRequestDomain.getRegisterGroup());

            notificationDomain.setUserSource(UserResolver.resolveUser());
            EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
            if (employeeUserSourceDomain != null){
                notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
            }

            // notificationDomain.setUserSource(changeRequestDomain.getUserReceive());
            notificationDomain.setUserDestine(changeRequestDomain.getUserEmit());
            // notificationDomain.setEmployeeSource(changeRequestDomain.getEmployeeReceive());
            notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeEmit());

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
        }
        notificationRepo.create(notificationDomain);

        UserDomain userCompletedByDomain = null;
        EmployeeDomain employeeCompletedByDomain = null;

        userCompletedByDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserCompletedByDomain = employeeUserRepo.getByUser(userCompletedByDomain.getId());
        if (employeeUserCompletedByDomain != null){
            employeeCompletedByDomain = employeeUserCompletedByDomain.getEmployee();
        }

        changeRequestDomain.setUserCompletedBy(userCompletedByDomain);
        changeRequestDomain.setEmployeeCompletedBy(employeeCompletedByDomain);

        repo.edit(changeRequestDomain);
    }

    private void _changeStateReviewPost(ChangeRequestDomain changeRequestDomain, ChangeRequestChangeStateRequest request){
        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setType(NotificationTypeEnum.CHANGE_REVIEW.getName());
        notificationDomain.setRead(false);
        notificationDomain.setDateTime(LocalDateTime.now());
        notificationDomain.setSubject("La solicitud de cambio ha pasado a revision por el area de reserva");
        notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

        if (request.getCreated().equals(ChangeRequestCreatedEnum.QUOTATION.getName())){
            notificationDomain.setUserSource(UserResolver.resolveUser());
            EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
            if (employeeUserSourceDomain != null){
                notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
            }

            // notificationDomain.setUserSource(changeRequestDomain.getUserEmit());
            notificationDomain.setUserDestine(changeRequestDomain.getUserReceive());
            // notificationDomain.setEmployeeSource(changeRequestDomain.getEmployeeEmit());
            notificationDomain.setEmployeeDestine(changeRequestDomain.getEmployeeReceive());

            notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

            ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
            ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

            notificationDomain.setIdObjectSource(changeRequestDomain.getQuotation().getId());
            notificationDomain.setNameObjectSource(changeRequestDomain.getQuotation().getName());
            notificationDomain.setIdentifyObjectSource(changeRequestDomain.getQuotation().getIdentify());
            notificationDomain.setObjectEntitySource(objectDomainSource);

            notificationDomain.setIdObjectDestine(changeRequestDomain.getRegisterGroup().getId());
            notificationDomain.setNameObjectDestine(changeRequestDomain.getRegisterGroup().getName());
            notificationDomain.setIdentifyObjectDestine(changeRequestDomain.getRegisterGroup().getIdentify());
            notificationDomain.setObjectEntityDestine(objectDomainDestine);
        }
        notificationRepo.create(notificationDomain);
    }
}
