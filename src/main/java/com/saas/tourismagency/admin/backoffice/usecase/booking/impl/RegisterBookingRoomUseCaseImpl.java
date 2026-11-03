package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingroom.RegisterBookingRoomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingRoom;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.RegisterBookingGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.RegisterBookingLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom.RegisterBookingRoomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingroom.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingRoomUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RegisterBookingRoomUseCaseImpl implements RegisterBookingRoomUseCase {

    @Autowired
    private RegisterBookingRoomRepo repo;

    @Autowired
    private RegisterBookingRoomConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private RegisterBookingLodgingRepo registerBookingLodgingRepo;

    @Autowired
    private RegisterBookingGlampingRepo registerBookingGlampingRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingRoom setFieldDefault;

    @Override
    @Transactional
    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request) {

        RegisterBookingLodgingDomain registerBookingLodgingDomain = null;
        if (request.getRegisterBookingLodging() != null && !request.getRegisterBookingLodging().isEmpty()){
            registerBookingLodgingDomain = registerBookingLodgingRepo.findById(request.getRegisterBookingLodging());
        }

        RegisterBookingGlampingDomain registerBookingGlampingDomain = null;
        if (request.getRegisterBookingGlamping() != null && !request.getRegisterBookingGlamping().isEmpty()){
            registerBookingGlampingDomain = registerBookingGlampingRepo.findById(request.getRegisterBookingGlamping());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
            registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingRoomDomain newDomain = request.to(registerBookingLodgingDomain, registerBookingLodgerDomain, registerBookingGlampingDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingRoomDomain createdDomain = repo.create(newDomain);
        return RegisterBookingRoomResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain) {
        RegisterBookingRoomDomain newDomain = request.to(registerBookingLodgingDomain, null, null, registerBookingLodgingDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingRoomDomain createdDomain = repo.create(newDomain);
        return RegisterBookingRoomResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain) {
        RegisterBookingRoomDomain newDomain = request.to(null, registerBookingLodgerDomain, null, registerBookingLodgerDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingRoomDomain createdDomain = repo.create(newDomain);
        return RegisterBookingRoomResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRoomResponse create(RegisterBookingRoomCreateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain) {
        RegisterBookingRoomDomain newDomain = request.to(null, null, registerBookingGlampingDomain, registerBookingGlampingDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingRoomDomain createdDomain = repo.create(newDomain);
        return RegisterBookingRoomResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingRoomDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = null;
        if (request.getRegisterBookingLodging() != null && !request.getRegisterBookingLodging().isEmpty()){
            registerBookingLodgingDomain = registerBookingLodgingRepo.findById(request.getRegisterBookingLodging());
        }

        RegisterBookingGlampingDomain registerBookingGlampingDomain = null;
        if (request.getRegisterBookingGlamping() != null && !request.getRegisterBookingGlamping().isEmpty()){
            registerBookingGlampingDomain = registerBookingGlampingRepo.findById(request.getRegisterBookingGlamping());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
            registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingRoomDomain registerBookingRoomDomain = request.to(registerBookingLodgingDomain, registerBookingLodgerDomain, registerBookingGlampingDomain, registerGroupDomain);
        RegisterBookingRoomDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingRoomDomain, old));
        return RegisterBookingRoomResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingRoomDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        RegisterBookingRoomDomain registerBookingRoomDomain = request.to(registerBookingLodgingDomain, null, null, registerBookingLodgingDomain.getRegisterGroup());
        RegisterBookingRoomDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingRoomDomain, old));
        return RegisterBookingRoomResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingRoomDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        RegisterBookingRoomDomain registerBookingRoomDomain = request.to(null, registerBookingLodgerDomain, null, registerBookingLodgerDomain.getRegisterGroup());
        RegisterBookingRoomDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingRoomDomain, old));
        return RegisterBookingRoomResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRoomResponse edit(String id, RegisterBookingRoomUpdateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingRoomDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        RegisterBookingRoomDomain registerBookingRoomDomain = request.to(null, null, null, registerBookingGlampingDomain.getRegisterGroup());
        RegisterBookingRoomDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingRoomDomain, old));
        return RegisterBookingRoomResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingRoomResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRoomResponse> findAll() {
        return repo.findAll(false).stream().map(registerBookingRoomDomain -> RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRoomResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingRoomDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingRoomResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRoomSearchResponse searchAll(RegisterBookingRoomSearchRequest searchRequest) {
       PageDTO<RegisterBookingRoomDomain> pageDTO = repo.searchAll(searchRequest);
       RegisterBookingRoomSearchResponse searchResponse = new RegisterBookingRoomSearchResponse();
       searchResponse.setItems(pageDTO.getContent().stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList());
       searchResponse.setTotalElements(pageDTO.getTotalElements());
       searchResponse.setTotalPages(pageDTO.getTotalPages());
       return searchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRoomSearchResponse searchFilterAll(RegisterBookingRoomSearchFilterRequest searchFilterRequest) {
        PageDTO<RegisterBookingRoomDomain> pageDTO = repo.searchAllFilter(searchFilterRequest);
        RegisterBookingRoomSearchResponse searchResponse = new RegisterBookingRoomSearchResponse();
        searchResponse.setItems(pageDTO.getContent().stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList());
        searchResponse.setTotalElements(pageDTO.getTotalElements());
        searchResponse.setTotalPages(pageDTO.getTotalPages());
        return searchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRoomResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingRoomDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking room found with name '%s'.".formatted(name));
        }
        return RegisterBookingRoomResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRoomResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingRoomDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item lodging found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingRoomResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRoomResponse> searchAllByBookingLodging(String idBooking) {
        List<RegisterBookingRoomDomain> list = repo.searchAllByBookingLodging(idBooking);
        return list.stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRoomResponse> searchAllByBookingLodger(String idBooking) {
        List<RegisterBookingRoomDomain> list = repo.searchAllByBookingLodger(idBooking);
        return list.stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRoomResponse> searchAllByBookingGlamping(String idBooking) {
        List<RegisterBookingRoomDomain> list = repo.searchAllByBookingGlamping(idBooking);
        return list.stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRoomResponse> searchAllByBookingLodgingAndTypeRoomBooking(String idBooking, String typeRoomBooking) {
        List<RegisterBookingRoomDomain> list = repo.searchAllByBookingLodgingAndTypeRoomBooking(idBooking, typeRoomBooking);
        return list.stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRoomResponse> searchAllByBookingLodgerAndTypeRoomBooking(String idBooking, String typeRoomBooking) {
        List<RegisterBookingRoomDomain> list = repo.searchAllByBookingLodgerAndTypeRoomBooking(idBooking, typeRoomBooking);
        return list.stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRoomResponse> searchAllByBookingGlampingAndTypeRoomBooking(String idBooking, String typeRoomBooking) {
        List<RegisterBookingRoomDomain> list = repo.searchAllByBookingGlampingAndTypeRoomBooking(idBooking, typeRoomBooking);
        return list.stream().map(registerBookingRoomDomain ->  RegisterBookingRoomResponse.from(registerBookingRoomDomain)).toList();
    }
}
