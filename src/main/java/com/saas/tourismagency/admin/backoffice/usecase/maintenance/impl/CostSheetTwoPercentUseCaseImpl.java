package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.costsheettwopercent.CostSheetTwoPercentConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultCostSheetTwoPercent;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.costsheettwopercent.CostSheetTwoPercentRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.costsheettwopercent.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.CostSheetTwoPercentUseCase;
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
public class CostSheetTwoPercentUseCaseImpl implements CostSheetTwoPercentUseCase {

    @Autowired
    private CostSheetTwoPercentRepo repo;

    @Autowired
    private CostSheetTwoPercentConverter converter;

    @Autowired
    private SetFieldDefaultCostSheetTwoPercent setFieldDefault;

    @Override
    @Transactional
    public CostSheetTwoPercentResponse create(CostSheetTwoPercentCreateRequest request) {
        CostSheetTwoPercentDomain costSheetTwoPercentDomainExist = repo.findByName(request.getName());
        if (costSheetTwoPercentDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        CostSheetTwoPercentDomain newDomain = request.to(false);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CostSheetTwoPercentDomain createdDomain = repo.create(newDomain);
        return CostSheetTwoPercentResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostSheetTwoPercentResponse edit(String id, CostSheetTwoPercentUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CostSheetTwoPercentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No cost sheet two percent found with id '%s'.".formatted(id));
        }
        CostSheetTwoPercentDomain costSheetTwoPercentDomainExist = repo.findByName(request.getName());
        if (costSheetTwoPercentDomainExist != null && !costSheetTwoPercentDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        CostSheetTwoPercentDomain costSheetTwoPercentDomain = request.to();
        costSheetTwoPercentDomain.setAvailable(old.getAvailable());
        CostSheetTwoPercentDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costSheetTwoPercentDomain, old));
        return CostSheetTwoPercentResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CostSheetTwoPercentResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostSheetTwoPercentResponse> findAll() {
        return repo.findAll(false).stream().map(costSheetTwoPercentDomain -> CostSheetTwoPercentResponse.from(costSheetTwoPercentDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoPercentResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CostSheetTwoPercentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No cost sheet two percent found with id '%s'.".formatted(id));
        }
        return CostSheetTwoPercentResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoPercentSearchResponse searchAll(CostSheetTwoPercentSearchRequest costSheetTwoPercentSearchRequest) {
       PageDTO<CostSheetTwoPercentDomain> pageDTO = repo.searchAll(costSheetTwoPercentSearchRequest);
       CostSheetTwoPercentSearchResponse costSheetTwoPercentSearchRespose = new CostSheetTwoPercentSearchResponse();
       costSheetTwoPercentSearchRespose.setCostSheetTwoPercents(pageDTO.getContent().stream().map(costSheetTwoPercentDomain ->  CostSheetTwoPercentResponse.from(costSheetTwoPercentDomain)).toList());
       costSheetTwoPercentSearchRespose.setTotalElements(pageDTO.getTotalElements());
       costSheetTwoPercentSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return costSheetTwoPercentSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoPercentSearchResponse searchFilterAll(CostSheetTwoPercentSearchFilterRequest costSheetTwoPercentSearchFilterRequest) {
        PageDTO<CostSheetTwoPercentDomain> pageDTO = repo.searchAllFilter(costSheetTwoPercentSearchFilterRequest);
        CostSheetTwoPercentSearchResponse costSheetTwoPercentSearchRespose = new CostSheetTwoPercentSearchResponse();
        costSheetTwoPercentSearchRespose.setCostSheetTwoPercents(pageDTO.getContent().stream().map(costSheetTwoPercentDomain ->  CostSheetTwoPercentResponse.from(costSheetTwoPercentDomain)).toList());
        costSheetTwoPercentSearchRespose.setTotalElements(pageDTO.getTotalElements());
        costSheetTwoPercentSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return costSheetTwoPercentSearchRespose;
    }

    @Override
    @Transactional
    public CostSheetTwoPercentResponse changeAvailable(String id, Boolean available) {
        if (id == null) {
            return null;
        }
        CostSheetTwoPercentDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No cost sheet two percent found with id '%s'.".formatted(id));
        }
        if (available && old.getAvailable()){
            throw new BadRequestException("Cost sheet two percent is for default");
        }
        if (!available && !old.getAvailable()){
            throw new BadRequestException("Cost sheet two percent is not for default");
        }
        old.setAvailable(available);
        CostSheetTwoPercentDomain editedDomain = repo.edit(old);
        return CostSheetTwoPercentResponse.from(editedDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoPercentResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        CostSheetTwoPercentDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No cost sheet two percent found with name '%s'.".formatted(name));
        }
        return CostSheetTwoPercentResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoPercentResponse findAvailable() {
        CostSheetTwoPercentDomain old = repo.findAvailable();
        if (old == null) {
            throw new NotFoundException("No cost sheet two percent available found.");
        }
        return CostSheetTwoPercentResponse.from(old);
    }
}
