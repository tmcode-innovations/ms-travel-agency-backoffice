package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodger.RegisterBookingItemLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemLodger;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger.RegisterBookingItemLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgerUseCase;
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
public class RegisterBookingItemLodgerUseCaseImpl implements RegisterBookingItemLodgerUseCase {

    @Autowired
    private RegisterBookingItemLodgerRepo repo;

    @Autowired
    private RegisterBookingItemLodgerConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemLodger setFieldDefault;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Override
    @Transactional
    public RegisterBookingItemLodgerResponse create(RegisterBookingItemLodgerCreateRequest request) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
            registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemLodgerDomain newDomain = request.to(serviceDomain, registerBookingLodgerDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemLodgerDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemLodgerResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerResponse create(RegisterBookingItemLodgerCreateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingItemLodgerDomain newDomain = request.to(serviceDomain, registerBookingLodgerDomain, registerBookingLodgerDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemLodgerDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemLodgerResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerResponse edit(String id, RegisterBookingItemLodgerUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
            registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = request.to(serviceDomain, registerBookingLodgerDomain, registerGroupDomain);
        RegisterBookingItemLodgerDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemLodgerDomain, old));
        return RegisterBookingItemLodgerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerResponse edit(String id, RegisterBookingItemLodgerUpdateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgerDomain old = repo.findById(id);
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
        old.setPvt(request.getPvt());
        old.setSic(request.getSic());
        old.setCantPax((request.getCantPaxAdult() != null ?  request.getCantPaxAdult() : 0)
                + (request.getCantPaxChildren() != null ?  request.getCantPaxChildren() : 0));
        old.setCantPaxAdult(request.getCantPaxAdult());
        old.setCantPaxChildren(request.getCantPaxChildren());
        old.setTc(request.getTc());
        old.setTcLocal(request.getTcLocal());
        old.setGuide(request.getGuide());

        RegisterBookingItemLodgerDomain editedDomain = repo.edit(old);

        ItineraryItemDomain itineraryItemDomainOld = itineraryItemRepo.findById(old.getIdItineraryItemIdentify());

        itineraryItemDomainOld.setService(serviceDomain);
        itineraryItemDomainOld.setItineraryDateTime(localDateTime);
        itineraryItemDomainOld.setObservation(request.getObservation());
        itineraryItemDomainOld.setPaxAdultTotal(request.getCantPaxAdult());
        itineraryItemDomainOld.setPaxChildrenTotal(request.getCantPaxChildren());
        itineraryItemDomainOld.setPaxTcTotal(request.getTc());
        itineraryItemDomainOld.setPaxTcLocalTotal(request.getTcLocal());
        itineraryItemDomainOld.setPaxGuideTotal(request.getGuide());
        itineraryItemDomainOld.setLodgePvt(request.getPvt());
        itineraryItemDomainOld.setLodgeSic(request.getSic());

        itineraryItemRepo.edit(itineraryItemDomainOld);

        return RegisterBookingItemLodgerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemLodgerResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgerResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemLodgerResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemLodgerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerSearchResponse searchAll(RegisterBookingItemLodgerSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingItemLodgerDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingItemLodgerSearchResponse bankSearchRespose = new RegisterBookingItemLodgerSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgerResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerSearchResponse searchFilterAll(RegisterBookingItemLodgerSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingItemLodgerDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingItemLodgerSearchResponse bankSearchRespose = new RegisterBookingItemLodgerSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgerResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemLodgerDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item lodger found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemLodgerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemLodgerDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item lodger found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemLodgerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgerResponse> searchAllByBooking(String idBooking) {
        List<RegisterBookingItemLodgerDomain> list = repo.searchAllByBooking(idBooking);
        list = list.stream().collect(Collectors.toList());
        Collections.sort(list);
        return list.stream().map(registerBookingItemLodgerDomain ->  RegisterBookingItemLodgerResponse.from(registerBookingItemLodgerDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = repo.searchBookingAndRegisterBookingItem(idBooking, idRegisterBookingItem);
        return RegisterBookingItemLodgerResponse.from(registerBookingItemLodgerDomain);
    }
}
