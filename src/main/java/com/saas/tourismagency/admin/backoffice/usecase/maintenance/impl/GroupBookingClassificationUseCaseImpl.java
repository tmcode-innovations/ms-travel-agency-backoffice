package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;

import dev.mm.javaee.core.exceptions.BadRequestException;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.groupbookingclassification.GroupBookingClassificationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultGroupBookingClassification;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.groupbookingclassification.GroupBookingClassificationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.groupbookingclassification.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GroupBookingClassificationDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.GroupBookingClassificationUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GroupBookingClassificationUseCaseImpl implements GroupBookingClassificationUseCase {

    @Autowired
    private GroupBookingClassificationRepo repo;

    @Autowired
    private GroupBookingClassificationConverter converter;

    @Autowired
    private SetFieldDefaultGroupBookingClassification setFieldDefault;

    @Override
    @Transactional
    public GroupBookingClassificationResponse create(GroupBookingClassificationCreateRequest request) {
        if (repo.findByName(request.getName()) != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        GroupBookingClassificationDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        GroupBookingClassificationDomain createdDomain = repo.create(newDomain);
        return GroupBookingClassificationResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public GroupBookingClassificationResponse edit(String id, GroupBookingClassificationUpdateRequest request) {
        if (id == null) {
            return null;
        }
        GroupBookingClassificationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No group booking classification found with id '%s'.".formatted(id));
        }
        if (repo.findByName(request.getName()) != null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        GroupBookingClassificationDomain groupBookingClassificationDomain = request.to();
        GroupBookingClassificationDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(groupBookingClassificationDomain, old));
        return GroupBookingClassificationResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        GroupBookingClassificationResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupBookingClassificationResponse> findAll() {
        return repo.findAll(false).stream().map(groupBookingClassificationDomain -> GroupBookingClassificationResponse.from(groupBookingClassificationDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GroupBookingClassificationResponse findById(String id) {
        if (id == null) {
            return null;
        }
        GroupBookingClassificationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No group booking classification found with id '%s'.".formatted(id));
        }
        return GroupBookingClassificationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public GroupBookingClassificationSearchResponse searchAll(GroupBookingClassificationSearchRequest groupBookingClassificationSearchRequest) {
       PageDTO<GroupBookingClassificationDomain> pageDTO = repo.searchAll(groupBookingClassificationSearchRequest);
       GroupBookingClassificationSearchResponse groupBookingClassificationSearchRespose = new GroupBookingClassificationSearchResponse();
       groupBookingClassificationSearchRespose.setGroupBookingClassifications(pageDTO.getContent().stream().map(groupBookingClassificationDomain ->  GroupBookingClassificationResponse.from(groupBookingClassificationDomain)).toList());
       groupBookingClassificationSearchRespose.setTotalElements(pageDTO.getTotalElements());
       groupBookingClassificationSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return groupBookingClassificationSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupBookingClassificationSearchResponse searchFilterAll(GroupBookingClassificationSearchFilterRequest groupBookingClassificationSearchFilterRequest) {
        PageDTO<GroupBookingClassificationDomain> pageDTO = repo.searchAllFilter(groupBookingClassificationSearchFilterRequest);
        GroupBookingClassificationSearchResponse groupBookingClassificationSearchRespose = new GroupBookingClassificationSearchResponse();
        groupBookingClassificationSearchRespose.setGroupBookingClassifications(pageDTO.getContent().stream().map(groupBookingClassificationDomain ->  GroupBookingClassificationResponse.from(groupBookingClassificationDomain)).toList());
        groupBookingClassificationSearchRespose.setTotalElements(pageDTO.getTotalElements());
        groupBookingClassificationSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return groupBookingClassificationSearchRespose;
    }
}
