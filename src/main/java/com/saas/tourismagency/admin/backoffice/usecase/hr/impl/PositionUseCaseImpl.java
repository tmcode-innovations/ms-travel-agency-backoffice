package com.saas.tourismagency.admin.backoffice.usecase.hr.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.position.PositionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.hr.SetFieldDefaultPosition;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.position.PersistPositionNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.position.PositionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.position.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import com.saas.tourismagency.admin.backoffice.usecase.hr.PositionUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PositionUseCaseImpl implements PositionUseCase {

    @Autowired
    private PositionRepo repo;

    @Autowired
    private PositionConverter converter;

    @Autowired
    private SetFieldDefaultPosition setFieldDefault;

    @Autowired
    PersistPositionNumberCallbackImpl persistPositionNumberCallbackImpl;

    @Override
    @Transactional
    public PositionResponse create(PositionCreateRequest request) {
        PositionDomain positionDomainExist = repo.findByName(request.getName());
        if (positionDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PositionDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistPositionNumberCallbackImpl.generateNumber());
        PositionDomain createdDomain = repo.create(newDomain);
        return PositionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PositionResponse edit(String id, PositionUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PositionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No position found with id '%s'.".formatted(id));
        }
        PositionDomain positionDomainExist = repo.findByName(request.getName());
        if (positionDomainExist != null && !positionDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PositionDomain positionDomain = request.to();
        positionDomain.setIdentify(old.getIdentify());
        PositionDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(positionDomain, old));
        return PositionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PositionResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionResponse> findAll() {
        return repo.findAll(false).stream().map(positionDomain -> PositionResponse.from(positionDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PositionResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PositionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No position found with id '%s'.".formatted(id));
        }
        return PositionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PositionSearchResponse searchAll(PositionSearchRequest positionSearchRequest) {
       PageDTO<PositionDomain> pageDTO = repo.searchAll(positionSearchRequest);
       PositionSearchResponse positionSearchRespose = new PositionSearchResponse();
       positionSearchRespose.setPositions(pageDTO.getContent().stream().map(positionDomain ->  PositionResponse.from(positionDomain)).toList());
       positionSearchRespose.setTotalElements(pageDTO.getTotalElements());
       positionSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return positionSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PositionSearchResponse searchFilterAll(PositionSearchFilterRequest positionSearchFilterRequest) {
        PageDTO<PositionDomain> pageDTO = repo.searchAllFilter(positionSearchFilterRequest);
        PositionSearchResponse positionSearchRespose = new PositionSearchResponse();
        positionSearchRespose.setPositions(pageDTO.getContent().stream().map(positionDomain ->  PositionResponse.from(positionDomain)).toList());
        positionSearchRespose.setTotalElements(pageDTO.getTotalElements());
        positionSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return positionSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PositionResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        PositionDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No position found with name '%s'.".formatted(name));
        }
        return PositionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PositionResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        PositionDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No position found with identify '%s'.".formatted(identify));
        }
        return PositionResponse.from(old);
    }
}
