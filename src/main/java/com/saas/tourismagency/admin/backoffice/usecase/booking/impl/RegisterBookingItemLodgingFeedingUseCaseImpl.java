package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemLodgingFeeding;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.RegisterBookingGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemglamping.RegisterBookingItemGlamRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodging.RegisterBookingItemLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.RegisterBookingLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeBookingEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgingFeedingUseCase;
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
public class RegisterBookingItemLodgingFeedingUseCaseImpl implements RegisterBookingItemLodgingFeedingUseCase {

    @Autowired
    private RegisterBookingItemLodgingFeedingRepo repo;

    @Autowired
    private RegisterBookingItemLodgingFeedingConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

    @Autowired
    private RegisterBookingItemLodgerLodgingRepo registerBookingItemLodgerLodgingRepo;


    @Autowired
    private RegisterBookingLodgingRepo registerBookingLodgingRepo;

    @Autowired
    private RegisterBookingItemLodgingRepo registerBookingItemLodgingRepo;

    @Autowired
    private RegisterBookingGlampingRepo registerBookingGlampingRepo;

    @Autowired
    private RegisterBookingItemGlamRepo registerBookingItemGlampingRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemLodgingFeeding setFieldDefault;

    @Override
    @Transactional
    public RegisterBookingItemLodgingFeedingResponse create(RegisterBookingItemLodgingFeedingCreateRequest request) {
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = null;

        RegisterBookingLodgingDomain registerBookingLodgingDomain = null;
        RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = null;

        RegisterBookingGlampingDomain registerBookingGlampingDomain = null;
        RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain = null;

        RegisterBookingItemLodgingFeedingDomain newDomain = null;

        if (request.getTypeBooking().equals(TypeBookingEnum.BOOKING_LODGING.getName())){
            if (request.getRegisterBookingLodging() != null && !request.getRegisterBookingLodging().isEmpty()){
                registerBookingLodgingDomain = registerBookingLodgingRepo.findById(request.getRegisterBookingLodging());
            }
            if (request.getRegisterBookingItemLodging() != null && !request.getRegisterBookingItemLodging().isEmpty()){
                registerBookingItemLodgingDomain = registerBookingItemLodgingRepo.findById(request.getRegisterBookingItemLodging());
            }
            newDomain = request.toLodging(registerBookingItemLodgingDomain, registerBookingLodgingDomain, registerGroupDomain);
        }else if (request.getTypeBooking().equals(TypeBookingEnum.BOOKING_LODGE.getName())){
            if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
                registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
            }
            if (request.getRegisterBookingItemLodgerLodging() != null && !request.getRegisterBookingItemLodgerLodging().isEmpty()){
                registerBookingItemLodgerLodgingDomain = registerBookingItemLodgerLodgingRepo.findById(request.getRegisterBookingItemLodgerLodging());
            }
            newDomain = request.toLodger(registerBookingItemLodgerLodgingDomain, registerBookingLodgerDomain, registerGroupDomain);
        }else if (request.getTypeBooking().equals(TypeBookingEnum.BOOKING_GLAMPING.getName())){
            if (request.getRegisterBookingGlamping() != null && !request.getRegisterBookingGlamping().isEmpty()){
                registerBookingGlampingDomain = registerBookingGlampingRepo.findById(request.getRegisterBookingGlamping());
            }
            if (request.getRegisterBookingItemGlamping() != null && !request.getRegisterBookingItemGlamping().isEmpty()){
                registerBookingItemGlampingDomain = registerBookingItemGlampingRepo.findById(request.getRegisterBookingItemGlamping());
            }
            newDomain = request.toGlamping(registerBookingItemGlampingDomain, registerBookingGlampingDomain, registerGroupDomain);
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemLodgingFeedingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemLodgingFeedingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgingFeedingResponse edit(String id, RegisterBookingItemLodgingFeedingUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgingFeedingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = null;

        RegisterBookingLodgingDomain registerBookingLodgingDomain = null;
        RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = null;

        RegisterBookingGlampingDomain registerBookingGlampingDomain = null;
        RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain = null;

        RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = null;

        if (request.getTypeBooking().equals(TypeBookingEnum.BOOKING_LODGING.getName())){
            if (request.getRegisterBookingLodging() != null && !request.getRegisterBookingLodging().isEmpty()){
                registerBookingLodgingDomain = registerBookingLodgingRepo.findById(request.getRegisterBookingLodging());
            }
            if (request.getRegisterBookingItemLodging() != null && !request.getRegisterBookingItemLodging().isEmpty()){
                registerBookingItemLodgingDomain = registerBookingItemLodgingRepo.findById(request.getRegisterBookingItemLodging());
            }
            registerBookingItemLodgingFeedingDomain = request.toLodging(registerBookingItemLodgingDomain, registerBookingLodgingDomain, registerGroupDomain);
        }else if (request.getTypeBooking().equals(TypeBookingEnum.BOOKING_LODGE.getName())){
            if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
                registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
            }
            if (request.getRegisterBookingItemLodgerLodging() != null && !request.getRegisterBookingItemLodgerLodging().isEmpty()){
                registerBookingItemLodgerLodgingDomain = registerBookingItemLodgerLodgingRepo.findById(request.getRegisterBookingItemLodgerLodging());
            }
            registerBookingItemLodgingFeedingDomain = request.toLodger(registerBookingItemLodgerLodgingDomain, registerBookingLodgerDomain, registerGroupDomain);
        }else if (request.getTypeBooking().equals(TypeBookingEnum.BOOKING_GLAMPING.getName())){
            if (request.getRegisterBookingGlamping() != null && !request.getRegisterBookingGlamping().isEmpty()){
                registerBookingGlampingDomain = registerBookingGlampingRepo.findById(request.getRegisterBookingGlamping());
            }
            if (request.getRegisterBookingItemGlamping() != null && !request.getRegisterBookingItemGlamping().isEmpty()){
                registerBookingItemGlampingDomain = registerBookingItemGlampingRepo.findById(request.getRegisterBookingItemGlamping());
            }
            registerBookingItemLodgingFeedingDomain = request.toGlamping(registerBookingItemGlampingDomain, registerBookingGlampingDomain, registerGroupDomain);
        }

        RegisterBookingItemLodgingFeedingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemLodgingFeedingDomain, old));
        return RegisterBookingItemLodgingFeedingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemLodgingFeedingResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgingFeedingResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemLodgingFeedingResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingFeedingResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgingFeedingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemLodgingFeedingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingFeedingSearchResponse searchAll(RegisterBookingItemLodgingFeedingSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingItemLodgingFeedingDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingItemLodgingFeedingSearchResponse bankSearchRespose = new RegisterBookingItemLodgingFeedingSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgingFeedingResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingFeedingSearchResponse searchFilterAll(RegisterBookingItemLodgingFeedSearchFilterReq bankSearchFilterRequest) {
        PageDTO<RegisterBookingItemLodgingFeedingDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingItemLodgingFeedingSearchResponse bankSearchRespose = new RegisterBookingItemLodgingFeedingSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgingFeedingResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingFeedingResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemLodgingFeedingDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item lodging feeding found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemLodgingFeedingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingFeedingResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemLodgingFeedingDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item lodger found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemLodgingFeedingResponse.from(old);
    }
}
