package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemRestaurant;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant.RegisterBookingRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemRestaurantUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegisterBookingItemRestaurantUseCaseImpl implements RegisterBookingItemRestaurantUseCase {

    @Autowired
    private RegisterBookingItemRestaurantRepo repo;

    @Autowired
    private RegisterBookingItemRestaurantConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private RegisterBookingRestaurantRepo registerBookingRestaurantRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemRestaurant setFieldDefault;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Override
    @Transactional
    public RegisterBookingItemRestaurantResponse create(RegisterBookingItemRestaurantCreateRequest request) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = null;
        if (request.getRegisterBookingRestaurant() != null && !request.getRegisterBookingRestaurant().isEmpty()){
            registerBookingRestaurantDomain = registerBookingRestaurantRepo.findById(request.getRegisterBookingRestaurant());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemRestaurantDomain newDomain = request.to(serviceDomain, registerBookingRestaurantDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemRestaurantDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemRestaurantResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemRestaurantResponse create(RegisterBookingItemRestaurantCreateRequest request, RegisterBookingRestaurantDomain registerBookingRestaurantDomain) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingItemRestaurantDomain newDomain = request.to(serviceDomain, registerBookingRestaurantDomain, registerBookingRestaurantDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemRestaurantDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemRestaurantResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemRestaurantResponse edit(String id, RegisterBookingItemRestaurantUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemRestaurantDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = null;
        if (request.getRegisterBookingRestaurant() != null && !request.getRegisterBookingRestaurant().isEmpty()){
            registerBookingRestaurantDomain = registerBookingRestaurantRepo.findById(request.getRegisterBookingRestaurant());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain = request.to(serviceDomain, registerBookingRestaurantDomain, registerGroupDomain);
        RegisterBookingItemRestaurantDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemRestaurantDomain, old));
        return RegisterBookingItemRestaurantResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemRestaurantResponse edit(String id, RegisterBookingItemRestaurantUpdateRequest request, RegisterBookingRestaurantDomain registerBookingRestaurantDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemRestaurantDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        LocalDateTime localDateTime = LocalDateTime.of(
                old.getDate().getYear(),
                old.getDate().getMonth(),
                old.getDate().getDayOfMonth(),
                request.getDateTime().getHour(),
                request.getDateTime().getMinute(),
                request.getDateTime().getSecond()
        );

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            if (serviceIdBaseDomain != null){
                serviceDomain = new ServiceDomain();
                serviceDomain.setId(serviceIdBaseDomain.getId());
            }
        }
        old.setService(serviceDomain);
        old.setDateTime(request.getDateTime());
        old.setObservation(request.getObservation());
        old.setCantPax((request.getCantPaxAdult() != null ?  request.getCantPaxAdult() : 0)
                + (request.getCantPaxChildren() != null ?  request.getCantPaxChildren() : 0));
        old.setCantPaxAdult(request.getCantPaxAdult());
        old.setCantPaxChildren(request.getCantPaxChildren());
        old.setTc(request.getTc());
        old.setTcLocal(request.getTcLocal());
        old.setGuide(request.getGuide());

        RegisterBookingItemRestaurantDomain editedDomain = repo.edit(old);

        ItineraryItemDomain itineraryItemDomainOld = itineraryItemRepo.findById(old.getIdItineraryItemIdentify());

        itineraryItemDomainOld.setService(serviceDomain);
        itineraryItemDomainOld.setItineraryDateTime(localDateTime);
        itineraryItemDomainOld.setObservation(request.getObservation());
        itineraryItemDomainOld.setPaxAdultTotal(request.getCantPaxAdult());
        itineraryItemDomainOld.setPaxChildrenTotal(request.getCantPaxChildren());
        itineraryItemDomainOld.setPaxTcTotal(request.getTc());
        itineraryItemDomainOld.setPaxTcLocalTotal(request.getTcLocal());
        itineraryItemDomainOld.setPaxGuideTotal(request.getGuide());

        itineraryItemRepo.edit(itineraryItemDomainOld);

        return RegisterBookingItemRestaurantResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemRestaurantResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemRestaurantResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemRestaurantResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemRestaurantResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemRestaurantDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemRestaurantSearchResponse searchAll(RegisterBookingItemRestaurantSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingItemRestaurantDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingItemRestaurantSearchResponse bankSearchRespose = new RegisterBookingItemRestaurantSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemRestaurantResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemRestaurantSearchResponse searchFilterAll(RegisterBookingItemRestaurantSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingItemRestaurantDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingItemRestaurantSearchResponse bankSearchRespose = new RegisterBookingItemRestaurantSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemRestaurantResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemRestaurantResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemRestaurantDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item restaurant found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemRestaurantResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemRestaurantDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item restaurant found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemRestaurantResponse> searchAllByBooking(String idBooking) {
        List<RegisterBookingItemRestaurantDomain> list = repo.searchAllByBooking(idBooking);
        list = list.stream().collect(Collectors.toList());
        Collections.sort(list);
        return list.stream().map(registerBookingItemRestaurantDomain ->  RegisterBookingItemRestaurantResponse.from(registerBookingItemRestaurantDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemRestaurantResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain = repo.searchBookingAndRegisterBookingItem(idBooking, idRegisterBookingItem);
        return RegisterBookingItemRestaurantResponse.from(registerBookingItemRestaurantDomain);
    }
}
