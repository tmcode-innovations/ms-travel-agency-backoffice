package com.saas.tourismagency.admin.backoffice.usecase.base.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultState;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.StateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.state.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.StateDomain;
import com.saas.tourismagency.admin.backoffice.usecase.base.StateUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class StateUseCaseImpl implements StateUseCase {

    @Autowired
    private StateRepo repo;

    @Autowired
    private CountryRepo repoCountry;

    @Autowired
    private StateConverter converter;

    @Autowired
    private SetFieldDefaultState setFieldDefault;

    @Override
    @Transactional
    public StateResponse create(StateCreateRequest request) {
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        StateDomain newDomain = request.to(countryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        StateDomain createdDomain = repo.create(newDomain);
        return StateResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public StateResponse edit(String id, StateUpdateRequest request) {
        if (id == null) {
            return null;
        }
        StateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No state found with id '%s'.".formatted(id));
        }
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = repoCountry.findById(request.getCountry());
        }
        StateDomain stateDomain = request.to(countryDomain);
        StateDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(stateDomain, old));
        return StateResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        StateResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateResponse> findAll() {
        return repo.findAll(false).stream().map(stateDomain -> StateResponse.from(stateDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StateResponse findById(String id) {
        if (id == null) {
            return null;
        }
        StateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No state found with id '%s'.".formatted(id));
        }
        return StateResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public StateSearchResponse searchAll(StateSearchRequest stateSearchRequest) {
       PageDTO<StateDomain> pageDTO = repo.searchAll(stateSearchRequest);
       StateSearchResponse stateSearchRespose = new StateSearchResponse();
       stateSearchRespose.setStates(pageDTO.getContent().stream().map(stateDomain ->  StateResponse.from(stateDomain)).toList());
       stateSearchRespose.setTotalElements(pageDTO.getTotalElements());
       stateSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return stateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public StateSearchResponse searchFilterAll(StateSearchFilterRequest stateSearchFilterRequest) {
        PageDTO<StateDomain> pageDTO = repo.searchAllFilter(stateSearchFilterRequest);
        StateSearchResponse stateSearchRespose = new StateSearchResponse();
        stateSearchRespose.setStates(pageDTO.getContent().stream().map(stateDomain ->  StateResponse.from(stateDomain)).toList());
        stateSearchRespose.setTotalElements(pageDTO.getTotalElements());
        stateSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return stateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public StateWithoutPageSearchResponse searchAllByCountry(BaseSearchRequestById baseSearchRequestById) {
        List<StateDomain> list = repo.searchAllByCountry(baseSearchRequestById.getIdEntity());
        StateWithoutPageSearchResponse stateWithoutPageSearchResponse = new StateWithoutPageSearchResponse();
        stateWithoutPageSearchResponse.setEntities(list.stream().map(stateDomain ->  StateResponse.from(stateDomain)).toList());
        return stateWithoutPageSearchResponse;
    }
}
