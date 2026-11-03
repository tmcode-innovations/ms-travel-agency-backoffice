package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemoperator.RegisterBookingItemOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemOperator;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.RegisterBookingItemOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice.OrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.OrderServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.RequestOrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.GeneratedRegisterBookingOperatorItemEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemOperatorUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegisterBookingItemOperatorUseCaseImpl implements RegisterBookingItemOperatorUseCase {

    @Autowired
    private RegisterBookingItemOperatorRepo repo;

    @Autowired
    private RegisterBookingItemOperatorConverter converter;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private RegisterBookingOperatorRepo registerBookingOperatorRepo;

    @Autowired
    private SetFieldDefaultRegisterBookingItemOperator setFieldDefault;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private RequestOrderServiceRepo requestOrderServiceRepo;

    @Autowired
    private OrderServiceItemRepo orderServiceItemRepo;

    @Autowired
    private OrderServiceRepo orderServiceRepo;

    @Override
    @Transactional
    public RegisterBookingItemOperatorResponse create(RegisterBookingItemOperatorCreateRequest request) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            registerBookingOperatorDomain = registerBookingOperatorRepo.findById(request.getRegisterBookingOperator());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemOperatorDomain newDomain = request.to(serviceDomain, registerBookingOperatorDomain, registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName());
        newDomain.setSequence(this.getLastIndexBookingOperatorItem(registerBookingOperatorDomain.getId()));
        RegisterBookingItemOperatorDomain createdDomain = repo.create(newDomain);
        return RegisterBookingItemOperatorResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemOperatorResponse create(RegisterBookingItemOperatorCreateRequest request, RegisterBookingOperatorDomain registerBookingOperatorDomain) {
        RegisterBookingItemOperatorResponse result = null;
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            if (serviceIdBaseDomain != null){
                serviceDomain = new ServiceDomain();
                serviceDomain.setId(serviceIdBaseDomain.getId());
            }
        }

        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findByIdSimpleDomainCaseCommonBasicData(request.getItineraryDate());
        }
        LocalDateTime localDateTime = null;
        if (itineraryDateDomain != null){
            localDateTime = LocalDateTime.of(
                    itineraryDateDomain.getItineraryDate().getYear(),
                    itineraryDateDomain.getItineraryDate().getMonth(),
                    itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                    request.getDateTime().getHour(),
                    request.getDateTime().getMinute(),
                    request.getDateTime().getSecond()
            );
        }else{
            localDateTime = LocalDateTime.of(
                    LocalDate.now().getYear(),
                    LocalDate.now().getMonth(),
                    LocalDate.now().getDayOfMonth(),
                    request.getDateTime().getHour(),
                    request.getDateTime().getMinute(),
                    request.getDateTime().getSecond()
            );
        }

        RegisterBookingItemOperatorDomain newDomain = request.to(serviceDomain, registerBookingOperatorDomain, registerBookingOperatorDomain.getRegisterGroup());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName());
        newDomain.setSequence(this.getLastIndexBookingOperatorItem(registerBookingOperatorDomain.getId()));
        if (itineraryDateDomain != null){
            newDomain.setDate(itineraryDateDomain.getItineraryDate());
            newDomain.setDateTime(localDateTime);
            newDomain.setDateService(itineraryDateDomain.getItineraryDate());
        }

        newDomain.setCantPax(
                (request.getCantPaxAdult() != null ?  request.getCantPaxAdult() : 0)
                        + (request.getCantPaxChildren() != null ?  request.getCantPaxChildren() : 0)
        );
        newDomain.setCantPaxAdult(request.getCantPaxAdult() != null ?  request.getCantPaxAdult() : 0);
        newDomain.setCantPaxChildren(request.getCantPaxChildren() != null ?  request.getCantPaxChildren() : 0);
        newDomain.setTc(request.getTc() != null ?  request.getTc() : 0);
        newDomain.setTcLocal(request.getTcLocal() != null ?  request.getTcLocal() : 0);
        newDomain.setGuide(request.getGuide() != null ?  request.getGuide() : 0);

        RegisterBookingItemOperatorDomain createdDomain = repo.create(newDomain);

        ItineraryItemDomain newItineraryItemDomain = new ItineraryItemDomain();
        newItineraryItemDomain.setRegisterGroup(registerBookingOperatorDomain.getRegisterGroup());
        newItineraryItemDomain.setServiceGroup(ServiceGroupEnum.TRADITIONAL_GROUP.getName());
        newItineraryItemDomain.setServiceForOperation(true);
        newItineraryItemDomain.setService(serviceDomain);
        newItineraryItemDomain.setItineraryDateTime(localDateTime);
        newItineraryItemDomain.setObservation(request.getObservation());
        newItineraryItemDomain.setPaxAdultTotal(request.getCantPaxAdult());
        newItineraryItemDomain.setPaxChildrenTotal(request.getCantPaxChildren());
        newItineraryItemDomain.setPaxTcTotal(request.getTc());
        newItineraryItemDomain.setPaxTcLocalTotal(request.getTcLocal());
        newItineraryItemDomain.setPaxGuideTotal(request.getGuide());
        newItineraryItemDomain.setOperatorPvt(request.getPvt());
        newItineraryItemDomain.setOperatorSic(request.getSic());
        newItineraryItemDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName());

        newItineraryItemDomain.setItineraryDate(itineraryDateDomain);
        newItineraryItemDomain.setSupplierType(registerBookingOperatorDomain.getSupplierType());
        newItineraryItemDomain.setSupplier(registerBookingOperatorDomain.getSupplier());
        newItineraryItemDomain.setIndex(this.getLastIndexItineraryItem(registerBookingOperatorDomain.getRegisterGroup().getId()));

        if (registerBookingOperatorDomain.getSupplier() != null && registerBookingOperatorDomain.getSupplier().getPartner() != null && registerBookingOperatorDomain.getSupplier().getPartner().getAddress() != null && registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null  ){
            newItineraryItemDomain.setDepartmentRegion(registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion());
        }

        newItineraryItemDomain.setRegisterBookingItemOperator(createdDomain);
        newItineraryItemDomain.setPaxTotal((newItineraryItemDomain.getPaxAdultTotal() != null ? newItineraryItemDomain.getPaxAdultTotal() : 0)  + (newItineraryItemDomain.getPaxChildrenTotal() != null ? newItineraryItemDomain.getPaxChildrenTotal()  : 0));
        newItineraryItemDomain.setCustomer(registerBookingOperatorDomain.getRegisterGroup().getCustomer());

        newItineraryItemDomain.setTenant(UserResolver.resolveUser().getTenant());
        ItineraryItemDomain createdItineraryItemDomain = itineraryItemRepo.create(newItineraryItemDomain);

        createdDomain.setIdItineraryItemIdentify(createdItineraryItemDomain.getId());

        RegisterBookingItemOperatorDomain editedDomain = repo.edit(createdDomain);

        RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findByIdSimpleDomainCaseGenerateRequestOrderService(registerBookingOperatorDomain.getRegisterGroup().getId(), registerBookingOperatorDomain.getSupplier().getId(), registerBookingOperatorDomain.getId());
        if (requestOrderServiceDomain != null){
            if (requestOrderServiceDomain.getRequestOrderServiceState().equals(RequestOrderServiceStateEnum.FINISHED.getName())){
                requestOrderServiceRepo.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(requestOrderServiceDomain.getId(), RequestOrderServiceStateEnum.IN_PROGRESS.getName());
            }
        }
        result = RegisterBookingItemOperatorResponse.from(editedDomain);
        return result;
    }

    @Override
    @Transactional
    public RegisterBookingItemOperatorResponse edit(String id, RegisterBookingItemOperatorUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemOperatorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            registerBookingOperatorDomain = registerBookingOperatorRepo.findById(request.getRegisterBookingOperator());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = request.to(serviceDomain, registerBookingOperatorDomain, registerGroupDomain);
        registerBookingItemOperatorDomain.setGeneratedBy(old.getGeneratedBy());
        registerBookingItemOperatorDomain.setSequence(old.getSequence());
        RegisterBookingItemOperatorDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingItemOperatorDomain, old));
        return RegisterBookingItemOperatorResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemOperatorResponse edit(String id, RegisterBookingItemOperatorUpdateRequest request, RegisterBookingOperatorDomain registerBookingOperatorDomain) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemOperatorDomain old = repo.findById(id);
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

        RegisterBookingItemOperatorDomain editedDomain = repo.edit(old);

        ItineraryItemDomain itineraryItemDomainOld = itineraryItemRepo.findById(old.getIdItineraryItemIdentify());

        itineraryItemDomainOld.setService(serviceDomain);
        itineraryItemDomainOld.setItineraryDateTime(localDateTime);
        itineraryItemDomainOld.setObservation(request.getObservation());
        itineraryItemDomainOld.setPaxAdultTotal(request.getCantPaxAdult());
        itineraryItemDomainOld.setPaxChildrenTotal(request.getCantPaxChildren());
        itineraryItemDomainOld.setPaxTcTotal(request.getTc());
        itineraryItemDomainOld.setPaxTcLocalTotal(request.getTcLocal());
        itineraryItemDomainOld.setPaxGuideTotal(request.getGuide());
        itineraryItemDomainOld.setOperatorPvt(request.getPvt());
        itineraryItemDomainOld.setOperatorSic(request.getSic());
        itineraryItemDomainOld.setRegisterBookingItemOperator(editedDomain);

        itineraryItemRepo.edit(itineraryItemDomainOld);

        if (itineraryItemDomainOld.getOrderServiceItem() != null){
            OrderServiceItemDomain  orderServiceItemDomain = itineraryItemDomainOld.getOrderServiceItem();
            orderServiceItemDomain.setService(serviceDomain);
            orderServiceItemDomain.setDateTimeService(localDateTime);
            orderServiceItemDomain.setDateTimeService(localDateTime);
            orderServiceItemDomain.setDateTimeRecj(localDateTime);
            orderServiceItemDomain.setDateTimePax(localDateTime);
            orderServiceItemDomain.setObservation(request.getObservation());

            orderServiceItemRepo.edit(orderServiceItemDomain);

            if (orderServiceItemDomain.getOrderService() != null){
                OrderServiceDomain orderServiceDomain = orderServiceItemDomain.getOrderService();
                orderServiceDomain.setCantPax(
                        (request.getCantPaxAdult() != null ?  request.getCantPaxAdult() : 0)
                                + (request.getCantPaxChildren() != null ?  request.getCantPaxChildren() : 0)
                                + (request.getTc() != null ?  request.getTc() : 0)
                                + (request.getTcLocal() != null ?  request.getTcLocal() : 0)
                                + (request.getGuide() != null ?  request.getGuide() : 0)
                );

                orderServiceRepo.edit(orderServiceDomain);
            }
        }

        return RegisterBookingItemOperatorResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingItemOperatorResponse old = findById(id);
        ItineraryItemDomain itineraryItemDomainDel = itineraryItemRepo.findById(old.getIdItineraryItemIdentify());
        if (itineraryItemDomainDel != null){
            itineraryItemRepo.deleteById(itineraryItemDomainDel.getId());
        }
        OrderServiceItemDomain orderServiceItemDomain = orderServiceItemRepo.searchRegisterBookingItemOperator(id);
        if (orderServiceItemDomain != null){
            List<OrderServiceItemDomain> listOrderServiceItemTemp = orderServiceItemRepo.searchAllByOrderServiceCaseListSimpleByDelete(orderServiceItemDomain.getOrderService().getId());
            if (listOrderServiceItemTemp != null && !listOrderServiceItemTemp.isEmpty() && listOrderServiceItemTemp.size() == 1){
                orderServiceRepo.deleteById(orderServiceItemDomain.getOrderService().getId());
            }
            orderServiceItemRepo.deleteById(orderServiceItemDomain.getId());
            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(orderServiceItemDomain.getRequestOrderService().getId());
            RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findById(orderServiceItemDomain.getRequestOrderService().getId());
            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                requestOrderServiceDomain.setRequestOrderServiceState(RequestOrderServiceStateEnum.IN_PROGRESS.getName());
            }else{
                requestOrderServiceDomain.setRequestOrderServiceState(RequestOrderServiceStateEnum.DRAFT.getName());
            }
            requestOrderServiceRepo.edit(requestOrderServiceDomain);

        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemOperatorResponse> findAll() {
        return repo.findAll(false).stream().map(bankDomain -> RegisterBookingItemOperatorResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemOperatorResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingItemOperatorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingItemOperatorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemOperatorSearchResponse searchAll(RegisterBookingItemOperatorSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingItemOperatorDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingItemOperatorSearchResponse bankSearchRespose = new RegisterBookingItemOperatorSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemOperatorResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemOperatorSearchResponse searchFilterAll(RegisterBookingItemOperatorSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingItemOperatorDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingItemOperatorSearchResponse bankSearchRespose = new RegisterBookingItemOperatorSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingItemOperatorResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemOperatorResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingItemOperatorDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register booking item operator found with name '%s'.".formatted(name));
        }
        return RegisterBookingItemOperatorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemOperatorResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingItemOperatorDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register booking item operator found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingItemOperatorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemOperatorResponse> searchAllByBooking(String idBooking) {
        List<RegisterBookingItemOperatorDomain> list = repo.searchAllByBooking(idBooking);
        list = list.stream().collect(Collectors.toList());
        Collections.sort(list);
        return list.stream().map(registerBookingItemOperatorDomain ->  RegisterBookingItemOperatorResponse.from(registerBookingItemOperatorDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemOperatorResponse searchByBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = repo.searchBookingAndRegisterBookingItem(idBooking, idRegisterBookingItem);
        return RegisterBookingItemOperatorResponse.from(registerBookingItemOperatorDomain);
    }

    private Integer getLastIndexItineraryItem(String idRegisterGroup) {
        Integer index = 0;
        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByRegisterGroupSimpleDomain(idRegisterGroup).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryItemDomain::getIndex));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }

    private Integer getLastIndexBookingOperatorItem(String idRegisterBooking) {
        Integer index = 0;
        List<RegisterBookingItemOperatorDomain> list = repo.searchAllByRegisterBookingSimpleDomain(idRegisterBooking).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(RegisterBookingItemOperatorDomain::getSequence));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getSequence() + 1;
        }else{
            index = 1;
        }
        return index;
    }
}
