package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.restauranttype.RestaurantTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultRestaurantType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.restauranttype.PersistRestaurantTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.restauranttype.RestaurantTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.restauranttype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.RestaurantTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.RestaurantTypeUseCase;
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
public class RestaurantTypeUseCaseImpl implements RestaurantTypeUseCase {

    @Autowired
    private RestaurantTypeRepo repo;

    @Autowired
    private RestaurantTypeConverter converter;

    @Autowired
    private SetFieldDefaultRestaurantType setFieldDefault;

    @Autowired
    PersistRestaurantTypeNumberCallbackImpl persistRestaurantTypeNumberCallbackImpl;

    @Override
    @Transactional
    public RestaurantTypeResponse create(RestaurantTypeCreateRequest request) {
        RestaurantTypeDomain restaurantTypeDomainExist = repo.findByName(request.getName());
        if (restaurantTypeDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        RestaurantTypeDomain newDomain = request.to();
        newDomain.setIdentify(persistRestaurantTypeNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RestaurantTypeDomain createdDomain = repo.create(newDomain);
        return RestaurantTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RestaurantTypeResponse edit(String id, RestaurantTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RestaurantTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No restaurant type found with id '%s'.".formatted(id));
        }
        RestaurantTypeDomain restaurantTypeDomainExist = repo.findByName(request.getName());
        if (restaurantTypeDomainExist != null && !restaurantTypeDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        RestaurantTypeDomain restaurantTypeDomain = request.to();
        restaurantTypeDomain.setIdentify(old.getIdentify());
        RestaurantTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(restaurantTypeDomain, old));
        return RestaurantTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RestaurantTypeResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestaurantTypeResponse> findAll() {
        return repo.findAll(false).stream().map(restaurantTypeDomain -> RestaurantTypeResponse.from(restaurantTypeDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RestaurantTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No restaurant type found with id '%s'.".formatted(id));
        }
        return RestaurantTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantTypeSearchResponse searchAll(RestaurantTypeSearchRequest restaurantTypeSearchRequest) {
       PageDTO<RestaurantTypeDomain> pageDTO = repo.searchAll(restaurantTypeSearchRequest);
       RestaurantTypeSearchResponse restaurantTypeSearchRespose = new RestaurantTypeSearchResponse();
       restaurantTypeSearchRespose.setRestaurantTypes(pageDTO.getContent().stream().map(restaurantTypeDomain ->  RestaurantTypeResponse.from(restaurantTypeDomain)).toList());
       restaurantTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       restaurantTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return restaurantTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantTypeSearchResponse searchFilterAll(RestaurantTypeSearchFilterRequest restaurantTypeSearchFilterRequest) {
        PageDTO<RestaurantTypeDomain> pageDTO = repo.searchAllFilter(restaurantTypeSearchFilterRequest);
        RestaurantTypeSearchResponse restaurantTypeSearchRespose = new RestaurantTypeSearchResponse();
        restaurantTypeSearchRespose.setRestaurantTypes(pageDTO.getContent().stream().map(restaurantTypeDomain ->  RestaurantTypeResponse.from(restaurantTypeDomain)).toList());
        restaurantTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        restaurantTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return restaurantTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantTypeResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RestaurantTypeDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No restaurant type found with name '%s'.".formatted(name));
        }
        return RestaurantTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RestaurantTypeResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RestaurantTypeDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No restaurant type found with identify '%s'.".formatted(identify));
        }
        return RestaurantTypeResponse.from(old);
    }
}
