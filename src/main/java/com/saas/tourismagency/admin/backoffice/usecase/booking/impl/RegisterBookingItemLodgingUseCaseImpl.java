package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodging.RegisterBookingItemLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemLodging;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodging.RegisterBookingItemLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.RegisterBookingLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.LodgingCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgingUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RegisterBookingItemLodgingUseCaseImpl implements RegisterBookingItemLodgingUseCase {

    @Autowired
    private RegisterBookingItemLodgingRepo repo;

    @Autowired
    private RegisterBookingItemLodgingConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private LodgingCategoryRepo lodgingCategoryRepo;

    @Autowired
    private RegisterBookingLodgingRepo registerBookingLodgingRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemLodging setFieldDefault;

    @Override
    @Transactional
    public RegisterBookingItemLodgingResponse create(RegisterBookingItemLodgingCreateRequest request) {
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = null;
        if (request.getRegisterBookingLodging() != null && !request.getRegisterBookingLodging().isEmpty()){
            registerBookingLodgingDomain = registerBookingLodgingRepo.findById(request.getRegisterBookingLodging());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemLodgingDomain newDomain = request.to(lodgingCategoryDomain, registerBookingLodgingDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemLodgingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemLodgingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgingResponse create(RegisterBookingItemLodgingCreateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain) {
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingItemLodgingDomain newDomain = request.to(lodgingCategoryDomain, registerBookingLodgingDomain, registerBookingLodgingDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemLodgingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemLodgingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgingResponse edit(String id, RegisterBookingItemLodgingUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = null;
        if (request.getRegisterBookingLodging() != null && !request.getRegisterBookingLodging().isEmpty()){
            registerBookingLodgingDomain = registerBookingLodgingRepo.findById(request.getRegisterBookingLodging());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = request.to(lodgingCategoryDomain, registerBookingLodgingDomain, registerGroupDomain);
        registerBookingItemLodgingDomain.setDateTimeInitService(old.getDateTimeInitService());
        RegisterBookingItemLodgingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemLodgingDomain, old));
        return RegisterBookingItemLodgingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgingResponse edit(String id, RegisterBookingItemLodgingUpdateRequest request, RegisterBookingLodgingDomain registerBookingLodgingDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        old.setObservation(request.getObservation());

        RegisterBookingItemLodgingDomain editedDomain = repo.edit(old);

        return RegisterBookingItemLodgingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemLodgingResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgingResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemLodgingResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemLodgingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingSearchResponse searchAll(RegisterBookingItemLodgingSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingItemLodgingDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingItemLodgingSearchResponse bankSearchRespose = new RegisterBookingItemLodgingSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgingResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingSearchResponse searchFilterAll(RegisterBookingItemLodgingSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingItemLodgingDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingItemLodgingSearchResponse bankSearchRespose = new RegisterBookingItemLodgingSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemLodgingResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemLodgingDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item lodging found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemLodgingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemLodgingDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item lodging found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemLodgingResponse.from(old);
    }



    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgingResponse> searchAllByBooking(String idBooking) {
        List<RegisterBookingItemLodgingDomain> list = repo.searchAllByBooking(idBooking);
        return list.stream().map(registerBookingItemLodgingDomain ->  RegisterBookingItemLodgingResponse.from(registerBookingItemLodgingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = repo.searchBookingAndRegisterBookingItem(idBooking, idRegisterBookingItem);
        return RegisterBookingItemLodgingResponse.from(registerBookingItemLodgingDomain);
    }
}
