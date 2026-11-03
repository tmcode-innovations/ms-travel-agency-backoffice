package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengerbooking.PassengerBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultPassengerBooking;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengerbooking.PassengerBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom.RegisterBookingRoomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.ItineraryItemResponse;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerBookingUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PassengerBookingUseCaseImpl implements PassengerBookingUseCase {

    @Autowired
    private PassengerBookingRepo repo;

    @Autowired
    private PassengerBookingConverter converter;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private RegisterBookingRoomRepo registerBookingRoomRepo;

    @Autowired
    private SetFieldDefaultPassengerBooking setFieldDefault;

    @Override
    @Transactional
    public PassengerBookingResponse create(PassengerBookingCreateRequest request) {
        PassengerBookingDomain newDomain = request.to();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        PassengerBookingDomain createdDomain = repo.create(newDomain);
        return PassengerBookingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PassengerBookingResponse create(
            PassengerBookingCreateRequest request,
            PassengerGroupDomain passengerGroupDomain,
            RegisterBookingOperatorDomain registerBookingOperatorDomain,
            RegisterBookingRestaurantDomain registerBookingRestaurantDomain,
            RegisterBookingLodgingDomain registerBookingLodgingDomain,
            RegisterBookingLodgerDomain registerBookingLodgerDomain,
            RegisterBookingGlampingDomain registerBookingGlampingDomain,
            RegisterBookingInstitutionDomain registerBookingInstitutionDomain,
            RegisterGroupDomain registerGroupDomain,
            RegisterBookingRoomDomain registerBookingRoomDomain
    ) {
        PassengerBookingDomain newDomain = request.to(
                passengerGroupDomain,
                registerBookingOperatorDomain,
                registerBookingRestaurantDomain,
                registerBookingLodgingDomain,
                registerBookingLodgerDomain,
                registerBookingGlampingDomain,
                registerBookingInstitutionDomain,
                registerGroupDomain,
                registerBookingRoomDomain
                );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        PassengerBookingDomain createdDomain = repo.create(newDomain);
        return PassengerBookingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public List<PassengerBookingResponse> createMultiple(
            PassengerBookingCreateRequest request,
            RegisterBookingOperatorDomain registerBookingOperatorDomain,
            RegisterBookingRestaurantDomain registerBookingRestaurantDomain,
            RegisterBookingLodgingDomain registerBookingLodgingDomain,
            RegisterBookingLodgerDomain registerBookingLodgerDomain,
            RegisterBookingGlampingDomain registerBookingGlampingDomain,
            RegisterBookingInstitutionDomain registerBookingInstitutionDomain,
            RegisterGroupDomain registerGroupDomain,
            RegisterBookingRoomDomain registerBookingRoomDomain
    ) {
        List<PassengerBookingResponse> result = new ArrayList<>();
        if (request.getPassengerGroups() != null && !request.getPassengerGroups().isEmpty()){
            for(String item: request.getPassengerGroups()){
                PassengerGroupDomain passengerGroupDomain = null;
                if (item != null && !item.isEmpty()){
                    passengerGroupDomain = passengerGroupRepo.findById(item);
                    PassengerBookingDomain newDomain = request.to(
                            passengerGroupDomain,
                            registerBookingOperatorDomain,
                            registerBookingRestaurantDomain,
                            registerBookingLodgingDomain,
                            registerBookingLodgerDomain,
                            registerBookingGlampingDomain,
                            registerBookingInstitutionDomain,
                            registerGroupDomain,
                            registerBookingRoomDomain
                    );
                    newDomain.setTenant(UserResolver.resolveUser().getTenant());
                    PassengerBookingDomain createdDomain = repo.create(newDomain);
                    result.add(PassengerBookingResponse.from(createdDomain));
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public PassengerBookingResponse edit(String id, PassengerBookingUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PassengerBookingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        PassengerBookingDomain bankDomain = request.to();
        PassengerBookingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(bankDomain, old));
        return PassengerBookingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PassengerBookingResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> PassengerBookingResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerBookingResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PassengerBookingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return PassengerBookingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerBookingSearchResponse searchAll(PassengerBookingSearchRequest bankSearchRequest) {
       PageDTO<PassengerBookingDomain> pageDTO = repo.searchAll(bankSearchRequest);
       PassengerBookingSearchResponse bankSearchRespose = new PassengerBookingSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  PassengerBookingResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerBookingSearchResponse searchFilterAll(PassengerBookingSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<PassengerBookingDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        PassengerBookingSearchResponse bankSearchRespose = new PassengerBookingSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  PassengerBookingResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingOperatorAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingOperatorAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingOperatorAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingOperatorAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingRestaurantAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingRestaurantAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingRestaurantAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingRestaurantAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgingAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgingAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgingAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgingAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingGlampingAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingGlampingAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingGlampingAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingGlampingAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingInstitutionAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingInstitutionAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingInstitutionAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupDomain(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgerAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgerAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgerAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBookingDomain(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, idRegisterGroup, typePassengerTcBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBookingDomain(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        List<PassengerBookingDomain> list = repo.searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, idRegisterGroup, typeRoomBooking);
        return list.stream().map(passengerBookingDomain ->  PassengerBookingResponse.from(passengerBookingDomain)).toList();
    }
}
