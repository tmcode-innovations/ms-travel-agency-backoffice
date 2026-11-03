package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemInstitution;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinginstitution.RegisterBookingInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemInstitutionUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RegisterBookingItemInstitutionUseCaseImpl implements RegisterBookingItemInstitutionUseCase {

    @Autowired
    private RegisterBookingItemInstitutionRepo repo;

    @Autowired
    private RegisterBookingItemInstitutionConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private RegisterBookingInstitutionRepo registerBookingInstitutionRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemInstitution setFieldDefault;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Override
    @Transactional
    public RegisterBookingItemInstitutionResponse create(RegisterBookingItemInstitutionCreateRequest request) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = null;
        if (request.getRegisterBookingInstitution() != null && !request.getRegisterBookingInstitution().isEmpty()){
            registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(request.getRegisterBookingInstitution());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemInstitutionDomain newDomain = request.to(serviceDomain, registerBookingInstitutionDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemInstitutionDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemInstitutionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemInstitutionResponse create(RegisterBookingItemInstitutionCreateRequest request, RegisterBookingInstitutionDomain registerBookingInstitutionDomain) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingItemInstitutionDomain newDomain = request.to(serviceDomain, registerBookingInstitutionDomain, registerBookingInstitutionDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemInstitutionDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemInstitutionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemInstitutionResponse edit(String id, RegisterBookingItemInstitutionUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = null;
        if (request.getRegisterBookingInstitution() != null && !request.getRegisterBookingInstitution().isEmpty()){
            registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(request.getRegisterBookingInstitution());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain = request.to(serviceDomain, registerBookingInstitutionDomain, registerGroupDomain);
        RegisterBookingItemInstitutionDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemInstitutionDomain, old));
        return RegisterBookingItemInstitutionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemInstitutionResponse edit(String id, RegisterBookingItemInstitutionUpdateRequest request, RegisterBookingInstitutionDomain registerBookingInstitutionDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        old.setObservation(request.getObservation());

        RegisterBookingItemInstitutionDomain editedDomain = repo.edit(old);

        ItineraryItemDomain itineraryItemDomainOld = itineraryItemRepo.findById(old.getIdItineraryItemIdentify());
        itineraryItemDomainOld.setObservation(request.getObservation());

        itineraryItemRepo.edit(itineraryItemDomainOld);

        return RegisterBookingItemInstitutionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemInstitutionResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemInstitutionResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemInstitutionResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemInstitutionResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemInstitutionSearchResponse searchAll(RegisterBookingItemInstitutionSearchRequest searchRequest) {
       PageDTO<RegisterBookingItemInstitutionDomain> pageDTO = repo.searchAll(searchRequest);
       RegisterBookingItemInstitutionSearchResponse searchRespose = new RegisterBookingItemInstitutionSearchResponse();
       searchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemInstitutionResponse.from(bankDomain)).toList());
       searchRespose.setTotalElements(pageDTO.getTotalElements());
       searchRespose.setTotalPages(pageDTO.getTotalPages());
       return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemInstitutionSearchResponse searchFilterAll(RegisterBookingItemInstitutionSearchFilterRequest searchFilterRequest) {
        PageDTO<RegisterBookingItemInstitutionDomain> pageDTO = repo.searchAllFilter(searchFilterRequest);
        RegisterBookingItemInstitutionSearchResponse searchRespose = new RegisterBookingItemInstitutionSearchResponse();
        searchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemInstitutionResponse.from(bankDomain)).toList());
        searchRespose.setTotalElements(pageDTO.getTotalElements());
        searchRespose.setTotalPages(pageDTO.getTotalPages());
        return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemInstitutionResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemInstitutionDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item operator found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemInstitutionResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemInstitutionDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item operator found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemInstitutionResponse> searchAllByBooking(String idBooking) {
        List<RegisterBookingItemInstitutionDomain> list = repo.searchAllByBooking(idBooking);
        return list.stream().map(registerBookingItemInstitutionDomain ->  RegisterBookingItemInstitutionResponse.from(registerBookingItemInstitutionDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemInstitutionResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain = repo.searchBookingAndRegisterBookingItem(idBooking, idRegisterBookingItem);
        return RegisterBookingItemInstitutionResponse.from(registerBookingItemInstitutionDomain);
    }
}
