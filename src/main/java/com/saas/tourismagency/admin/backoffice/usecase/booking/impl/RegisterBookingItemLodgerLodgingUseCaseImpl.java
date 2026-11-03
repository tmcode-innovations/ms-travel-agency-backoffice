package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemLodgerLodging;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.LodgingCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgerLodgingUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RegisterBookingItemLodgerLodgingUseCaseImpl implements RegisterBookingItemLodgerLodgingUseCase {

    @Autowired
    private RegisterBookingItemLodgerLodgingRepo repo;

    @Autowired
    private RegisterBookingItemLodgerLodgingConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private LodgingCategoryRepo lodgingCategoryRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemLodgerLodging setFieldDefault;

    @Override
    @Transactional
    public RegisterBookingItemLodgerLodgingResponse create(RegisterBookingItemLodgerLodgingCreateRequest request) {
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
            registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemLodgerLodgingDomain newDomain = request.to(lodgingCategoryDomain, registerBookingLodgerDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemLodgerLodgingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemLodgerLodgingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerLodgingResponse create(RegisterBookingItemLodgerLodgingCreateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain) {
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingItemLodgerLodgingDomain newDomain = request.to(lodgingCategoryDomain, registerBookingLodgerDomain, registerBookingLodgerDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemLodgerLodgingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemLodgerLodgingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerLodgingResponse edit(String id, RegisterBookingItemLodgerLodgingUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgerLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = null;
        if (request.getRegisterBookingLodger() != null && !request.getRegisterBookingLodger().isEmpty()){
            registerBookingLodgerDomain = registerBookingLodgerRepo.findById(request.getRegisterBookingLodger());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = request.to(lodgingCategoryDomain, registerBookingLodgerDomain, registerGroupDomain);
        registerBookingItemLodgerLodgingDomain.setDateTimeInitService(old.getDateTimeInitService());
        RegisterBookingItemLodgerLodgingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemLodgerLodgingDomain, old));
        return RegisterBookingItemLodgerLodgingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerLodgingResponse edit(String id, RegisterBookingItemLodgerLodgingUpdateRequest request, RegisterBookingLodgerDomain registerBookingLodgerDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgerLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        old.setObservation(request.getObservation());

        RegisterBookingItemLodgerLodgingDomain editedDomain = repo.edit(old);

        return RegisterBookingItemLodgerLodgingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemLodgerLodgingResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgerLodgingResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemLodgerLodgingResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerLodgingResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgerLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemLodgerLodgingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerLodgingSearchResponse searchAll(RegisterBookingItemLodgerLodgingSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingItemLodgerLodgingDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingItemLodgerLodgingSearchResponse bankSearchRespose = new RegisterBookingItemLodgerLodgingSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgerLodgingResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerLodgingSearchResponse searchFilterAll(RegisterBookingItemLodgerLodSearchFilterReq bankSearchFilterRequest) {
        PageDTO<RegisterBookingItemLodgerLodgingDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingItemLodgerLodgingSearchResponse bankSearchRespose = new RegisterBookingItemLodgerLodgingSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgerLodgingResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerLodgingResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemLodgerLodgingDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item lodger lodging found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemLodgerLodgingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerLodgingResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemLodgerLodgingDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item lodger lodging found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemLodgerLodgingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgerLodgingResponse> searchAllByBooking(String idBooking) {
        List<RegisterBookingItemLodgerLodgingDomain> list = repo.searchAllByBooking(idBooking);
        return list.stream().map(registerBookingItemLodgerLodgingDomain ->  RegisterBookingItemLodgerLodgingResponse.from(registerBookingItemLodgerLodgingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerLodgingResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = repo.searchBookingAndRegisterBookingItem(idBooking, idRegisterBookingItem);
        return RegisterBookingItemLodgerLodgingResponse.from(registerBookingItemLodgerLodgingDomain);
    }
}
