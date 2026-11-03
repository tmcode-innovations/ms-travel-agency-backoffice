package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.notification.NotificationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultNotification;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification.NotificationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.notification.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationStatusEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.NotificationUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotificationUseCaseImpl implements NotificationUseCase {

    @Autowired
    private NotificationRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    @Autowired
    private NotificationConverter converter;

    @Autowired
    private SetFieldDefaultNotification setFieldDefault;

    @Override
    @Transactional
    public NotificationResponse create(NotificationCreateRequest request) {
        NotificationDomain notificationDomainExist = repo.findByName(request.getName());
        if (notificationDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }

        UserDomain userSourceDomain = null;
        if (request.getUserSource() != null && !request.getUserSource().isEmpty()){
            userSourceDomain = userRepo.findById(request.getUserSource());
        }
        UserDomain userDestineDomain = null;
        if (request.getUserDestine() != null && !request.getUserDestine().isEmpty()){
            userDestineDomain = userRepo.findById(request.getUserDestine());
        }
        EmployeeDomain employeeSourceDomain = null;
        if (request.getEmployeeSource() != null && !request.getEmployeeSource().isEmpty()){
            employeeSourceDomain = employeeRepo.findById(request.getEmployeeSource());
        }
        EmployeeDomain employeeDestineDomain = null;
        if (request.getEmployeeDestine() != null && !request.getEmployeeDestine().isEmpty()){
            employeeDestineDomain = employeeRepo.findById(request.getEmployeeDestine());
        }

        NotificationDomain newDomain = request.to(userSourceDomain, employeeSourceDomain, userDestineDomain, employeeDestineDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        NotificationDomain createdDomain = repo.create(newDomain);
        return NotificationResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public NotificationResponse edit(String id, NotificationUpdateRequest request) {
        if (id == null) {
            return null;
        }
        NotificationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No notification found with id '%s'.".formatted(id));
        }
        NotificationDomain notificationDomainExist = repo.findByName(request.getName());
        if (notificationDomainExist != null && !notificationDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        UserDomain userSourceDomain = null;
        if (request.getUserSource() != null && !request.getUserSource().isEmpty()){
            userSourceDomain = userRepo.findById(request.getUserSource());
        }
        UserDomain userDestineDomain = null;
        if (request.getUserDestine() != null && !request.getUserDestine().isEmpty()){
            userDestineDomain = userRepo.findById(request.getUserDestine());
        }
        EmployeeDomain employeeSourceDomain = null;
        if (request.getEmployeeSource() != null && !request.getEmployeeSource().isEmpty()){
            employeeSourceDomain = employeeRepo.findById(request.getEmployeeSource());
        }
        EmployeeDomain employeeDestineDomain = null;
        if (request.getEmployeeDestine() != null && !request.getEmployeeDestine().isEmpty()){
            employeeDestineDomain = employeeRepo.findById(request.getEmployeeDestine());
        }

        NotificationDomain notificationDomain = request.to(userSourceDomain, employeeSourceDomain, userDestineDomain, employeeDestineDomain);
        NotificationDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(notificationDomain, old));
        return NotificationResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        NotificationResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> findAll() {
        return repo.findAll(false).stream().map(notificationDomain -> NotificationResponse.from(notificationDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponse findById(String id) {
        if (id == null) {
            return null;
        }
        NotificationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No notification found with id '%s'.".formatted(id));
        }
        return NotificationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationSearchResponse searchAll(NotificationSearchRequest notificationSearchRequest) {
       PageDTO<NotificationDomain> pageDTO = repo.searchAll(notificationSearchRequest);
       NotificationSearchResponse notificationSearchRespose = new NotificationSearchResponse();
       notificationSearchRespose.setNotifications(pageDTO.getContent().stream().map(notificationDomain ->  NotificationResponse.from(notificationDomain)).toList());
       notificationSearchRespose.setTotalElements(pageDTO.getTotalElements());
       notificationSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return notificationSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationSearchResponse searchFilterAll(NotificationSearchFilterRequest notificationSearchFilterRequest) {
        PageDTO<NotificationDomain> pageDTO = repo.searchAllFilter(notificationSearchFilterRequest);
        NotificationSearchResponse notificationSearchRespose = new NotificationSearchResponse();
        notificationSearchRespose.setNotifications(pageDTO.getContent().stream().map(notificationDomain ->  NotificationResponse.from(notificationDomain)).toList());
        notificationSearchRespose.setTotalElements(pageDTO.getTotalElements());
        notificationSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return notificationSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        NotificationDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No notification found with name '%s'.".formatted(name));
        }
        return NotificationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        NotificationDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No notification found with identify '%s'.".formatted(identify));
        }
        return NotificationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationSearchDataResponse searchAllNotificationsByUser(BaseSearchRequestById request) {
        List<NotificationDomain> list = repo.searchAllNotificationsByUserSimpleDomainCaseGeneric(request.getIdEntity());
        return NotificationSearchDataResponse.from(list.stream().map(notificationDomain -> NotificationResponse.from(notificationDomain)).collect(Collectors.toList()), "notification");
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationSearchDataResponse searchAllNotificationsNewByUser(BaseSearchRequestById request) {
        List<NotificationDomain> list = repo.searchAllNotificationsNewByUserSimpleDomainCaseGeneric(request.getIdEntity());
        List<NotificationResponse> result = list.stream().map(notificationDomain -> NotificationResponse.from(notificationDomain)).collect(Collectors.toList());
        for (NotificationDomain notificationDomain: list){
            notificationDomain = repo.findById(notificationDomain.getId());
            notificationDomain.setStatus(NotificationStatusEnum.WITHOUT_VIEW.getName());
            repo.edit(notificationDomain);
        }
        return NotificationSearchDataResponse.from(result, "notification");
    }

    @Override
    @Transactional(readOnly = true)
    public void cleanNotifications(BaseSearchRequestById request) {
        List<NotificationDomain> list = repo.searchAllNotificationsByUserSimpleDomainCaseGeneric(request.getIdEntity());
        List<NotificationResponse> result = list.stream().map(notificationDomain -> NotificationResponse.from(notificationDomain)).collect(Collectors.toList());
        for (NotificationDomain notificationDomain: list){
            notificationDomain = repo.findById(notificationDomain.getId());
            notificationDomain.setStatus(NotificationStatusEnum.DELETED.getName());
            repo.edit(notificationDomain);
        }
    }

    @Override
    @Transactional
    public NotificationResponse changeState(String id, NotificationChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        NotificationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No notification found with id '%s'.".formatted(id));
        }
        switch (request.getStateNotification()) {
            case "Without View" -> {
                _changeStateWithoutViewPre(old, request);
            }
            case "View" -> {
                _changeStateViewPre(old, request);
            }
            case "Deleted" -> {
                _changeStateDeletedPre(old, request);
            }
            default -> {

            }
        }

        old.setStatus(request.getStateNotification());
        NotificationDomain editedDomain = repo.edit(old);

        switch (request.getStateNotification()) {
            case "Without View" -> {
                _changeStateWithoutViewPost(old, request);
            }
            case "View" -> {
                _changeStateViewPost(old, request);
            }
            case "Deleted" -> {
                _changeStateDeletedPost(old, request);
            }
            default -> {

            }
        }
        return NotificationResponse.from(editedDomain);
    }

    private void _changeStateWithoutViewPre(NotificationDomain notificationDomain, NotificationChangeStateRequest request){

    }

    private void _changeStateViewPre(NotificationDomain notificationDomain, NotificationChangeStateRequest request){

    }

    private void _changeStateDeletedPre(NotificationDomain notificationDomain, NotificationChangeStateRequest request){

    }

    private void _changeStateWithoutViewPost(NotificationDomain notificationDomain, NotificationChangeStateRequest request){

    }

    private void _changeStateViewPost(NotificationDomain notificationDomain, NotificationChangeStateRequest request){

    }

    private void _changeStateDeletedPost(NotificationDomain notificationDomain, NotificationChangeStateRequest request){

    }
}
