package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemglamping.RegisterBookingItemGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemGlamping;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.RegisterBookingGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemglamping.RegisterBookingItemGlamRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.LodgingCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemGlampingUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RegisterBookingItemGlampingUseCaseImpl implements RegisterBookingItemGlampingUseCase {

    @Autowired
    private RegisterBookingItemGlamRepo repo;

    @Autowired
    private RegisterBookingItemGlampingConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private LodgingCategoryRepo lodgingCategoryRepo;

    @Autowired
    private RegisterBookingGlampingRepo registerBookingGlampingRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemGlamping setFieldDefault;

    @Override
    @Transactional
    public RegisterBookingItemGlampingResponse create(RegisterBookingItemGlampingCreateRequest request) {
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingGlampingDomain registerBookingGlampingDomain = null;
        if (request.getRegisterBookingGlamping() != null && !request.getRegisterBookingGlamping().isEmpty()){
            registerBookingGlampingDomain = registerBookingGlampingRepo.findById(request.getRegisterBookingGlamping());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemGlampingDomain newDomain = request.to(lodgingCategoryDomain, registerBookingGlampingDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemGlampingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemGlampingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemGlampingResponse create(RegisterBookingItemGlampingCreateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain) {
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingItemGlampingDomain newDomain = request.to(lodgingCategoryDomain, registerBookingGlampingDomain, registerBookingGlampingDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterBookingItemGlampingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemGlampingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemGlampingResponse edit(String id, RegisterBookingItemGlampingUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemGlampingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingGlampingDomain registerBookingGlampingDomain = null;
        if (request.getRegisterBookingGlamping() != null && !request.getRegisterBookingGlamping().isEmpty()){
            registerBookingGlampingDomain = registerBookingGlampingRepo.findById(request.getRegisterBookingGlamping());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain = request.to(lodgingCategoryDomain, registerBookingGlampingDomain, registerGroupDomain);
        registerBookingItemGlampingDomain.setDateTimeInitService(old.getDateTimeInitService());
        RegisterBookingItemGlampingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemGlampingDomain, old));
        return RegisterBookingItemGlampingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemGlampingResponse edit(String id, RegisterBookingItemGlampingUpdateRequest request, RegisterBookingGlampingDomain registerBookingGlampingDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemGlampingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        LodgingCategoryDomain lodgingCategoryDomain = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategoryDomain = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }

        RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain = request.to(lodgingCategoryDomain, registerBookingGlampingDomain, registerBookingGlampingDomain.getRegisterGroup());
        registerBookingItemGlampingDomain.setDateTimeInitService(old.getDateTimeInitService());
        RegisterBookingItemGlampingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemGlampingDomain, old));
        return RegisterBookingItemGlampingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemGlampingResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemGlampingResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemGlampingResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemGlampingResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemGlampingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemGlampingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemGlampingSearchResponse searchAll(RegisterBookingItemGlampingSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingItemGlampingDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingItemGlampingSearchResponse bankSearchRespose = new RegisterBookingItemGlampingSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemGlampingResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemGlampingSearchResponse searchFilterAll(RegisterBookingItemGlampingSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingItemGlampingDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingItemGlampingSearchResponse bankSearchRespose = new RegisterBookingItemGlampingSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemGlampingResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemGlampingResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemGlampingDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item lodging found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemGlampingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemGlampingResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemGlampingDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item lodging found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemGlampingResponse.from(old);
    }



    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemGlampingResponse> searchAllByBooking(String idBooking) {
        List<RegisterBookingItemGlampingDomain> list = repo.searchAllByBooking(idBooking);
        return list.stream().map(registerBookingItemLodgingDomain ->  RegisterBookingItemGlampingResponse.from(registerBookingItemLodgingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemGlampingResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        RegisterBookingItemGlampingDomain registerBookingItemLodgingDomain = repo.searchBookingAndRegisterBookingItem(idBooking, idRegisterBookingItem);
        return RegisterBookingItemGlampingResponse.from(registerBookingItemLodgingDomain);
    }
}
