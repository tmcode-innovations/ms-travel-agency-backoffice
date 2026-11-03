package com.saas.tourismagency.admin.backoffice.usecase.operation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.requestorderservice.RequestOrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation.SetFieldDefaultRequestOrderService;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.RegisterBookingItemOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice.OrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.OrderServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.PersistRequestOrderServiceNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.RequestOrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.GeneratedRegisterBookingOperatorItemEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.usecase.operation.RequestOrderServiceUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RequestOrderServiceUseCaseImpl implements RequestOrderServiceUseCase {

    @Autowired
    private RequestOrderServiceRepo repo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

    @Autowired
    private RegisterBookingOperatorRepo registerBookingOperatorRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private OrderServiceRepo orderServiceRepo;

    @Autowired
    private OrderServiceItemRepo orderServiceItemRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private RequestOrderServiceConverter converter;

    @Autowired
    private SetFieldDefaultRequestOrderService setFieldDefault;

    @Autowired
    PersistRequestOrderServiceNumberCallbackImpl persistRequestOrderServiceNumberCallback;

    @Autowired
    private RegisterBookingItemOperatorRepo registerBookingItemOperatorRepo;

    @Override
    @Transactional
    public RequestOrderServiceResponse create(RequestOrderServiceCreateRequest request) {
        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            IdBaseDomain supplierOperatorIdBaseDomain = supplierOperatorRepo.findByIdEntity(request.getSupplierOperator());
            supplierOperatorDomain = new SupplierOperatorDomain();
            supplierOperatorDomain.setId(supplierOperatorIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(request.getRegisterBookingOperator());
            registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
            registerBookingOperatorDomain.setId(registerBookingOperatorIdBaseDomain.getId());
        }

        RequestOrderServiceDomain newDomain = request.to(registerBookingOperatorDomain, registerGroupDomain, supplierOperatorDomain);
        newDomain.setRequestOrderServiceState(RequestOrderServiceStateEnum.DRAFT.getName());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRequestOrderServiceNumberCallback.generateNumber());
        RequestOrderServiceDomain createdDomain = repo.create(newDomain);
        return RequestOrderServiceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RequestOrderServiceResponse edit(String id, RequestOrderServiceUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RequestOrderServiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            IdBaseDomain supplierOperatorIdBaseDomain = supplierOperatorRepo.findByIdEntity(request.getSupplierOperator());
            supplierOperatorDomain = new SupplierOperatorDomain();
            supplierOperatorDomain.setId(supplierOperatorIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(request.getRegisterBookingOperator());
            registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
            registerBookingOperatorDomain.setId(registerBookingOperatorIdBaseDomain.getId());
        }

        RequestOrderServiceDomain requestOrderServiceDomain = request.to(registerBookingOperatorDomain, registerGroupDomain, supplierOperatorDomain);
        requestOrderServiceDomain.setRequestOrderServiceState(old.getRequestOrderServiceState());
        requestOrderServiceDomain.setActiveRequestOrderService(old.getActiveRequestOrderService());
        requestOrderServiceDomain.setIdentify(old.getIdentify());
        RequestOrderServiceDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(requestOrderServiceDomain, old));
        return RequestOrderServiceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RequestOrderServiceResponse old = findById(id);
        List<OrderServiceDomain> orderServiceByDelete = orderServiceRepo.searchAllByOrderServiceCaseListSimpleByDelete(id);
        if (orderServiceByDelete != null && !orderServiceByDelete.isEmpty()){
            for (OrderServiceDomain orderServiceDomain : orderServiceByDelete){
                List<OrderServiceItemDomain> detailsByDelete = orderServiceItemRepo.searchAllByOrderServiceCaseListSimpleByDelete(id);
                if (detailsByDelete != null && !detailsByDelete.isEmpty()){
                    for (OrderServiceItemDomain orderServiceItemDomain : detailsByDelete){
                        if (orderServiceDomain.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
                            ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchOrderServiceItem(orderServiceItemDomain.getId());
                            if (itineraryItemDomain != null){
                                itineraryItemDomain.setGuideTransport(null);
                                itineraryItemDomain.setGuideTransferDrive(null);
                                itineraryItemDomain.setOrderServiceItem(null);
                                itineraryItemRepo.edit(itineraryItemDomain);
                            }
                        }
                        orderServiceItemRepo.deleteById(orderServiceItemDomain.getId());
                    }
                }
                orderServiceRepo.deleteById(orderServiceDomain.getId());
            }
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (old.getRegisterBookingOperator() != null && old.getRegisterBookingOperator().getId() != null && !old.getRegisterBookingOperator().getId().isEmpty()){
            registerBookingOperatorDomain = registerBookingOperatorRepo.findById(old.getRegisterBookingOperator().getId());
        }
        registerBookingOperatorDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        // Change Status to booking
        registerBookingOperatorRepo.edit(registerBookingOperatorDomain);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequestOrderServiceResponse> findAll() {
        return repo.findAll(false).stream().map(requestOrderServiceDomain -> RequestOrderServiceResponse.from(requestOrderServiceDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RequestOrderServiceResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RequestOrderServiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RequestOrderServiceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RequestOrderServiceDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        RequestOrderServiceDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No entity with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public RequestOrderServiceSearchResponse searchAll(RequestOrderServiceSearchRequest bankSearchRequest) {
       PageDTO<RequestOrderServiceDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RequestOrderServiceSearchResponse bankSearchRespose = new RequestOrderServiceSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RequestOrderServiceResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RequestOrderServiceSearchResponse searchFilterAll(RequestOrderServiceSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RequestOrderServiceDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RequestOrderServiceSearchResponse bankSearchRespose = new RequestOrderServiceSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RequestOrderServiceResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional
    public RequestOrderServiceResponse changeState(String id, RequestOrderServiceChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RequestOrderServiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No request order service found with id '%s'.".formatted(id));
        }
        old.setRequestOrderServiceState(request.getRequestOrderServiceState());
        RequestOrderServiceDomain editedDomain = repo.edit(old);
        switch (request.getRequestOrderServiceState()) {
            case "Draft" -> {
                _changeStateDraft(old, request);
            }
            case "InProgress" -> {
                _changeStateInProgress(old, request);
            }
            case "Finished" -> {
                _changeStateFinished(old, request);
            }
            case "Cancelled" -> {
                _changeStateCancelled(old, request);
            }
            default -> {

            }
        }
        return RequestOrderServiceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RequestOrderServiceResponse generate(String idRegisterGroup, String idRegisterBookingOperator, String idSupplierOperator) {
        SupplierOperatorDomain supplierOperatorDomain = null;
        if (idSupplierOperator != null && !idSupplierOperator.isEmpty()){
            supplierOperatorDomain = supplierOperatorRepo.findById(idSupplierOperator);
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (idRegisterBookingOperator != null && !idRegisterBookingOperator.isEmpty()){
            registerBookingOperatorDomain = registerBookingOperatorRepo.findById(idRegisterBookingOperator);
        }

        RequestOrderServiceDomain newDomain = new RequestOrderServiceDomain();
        newDomain.setActiveRequestOrderService(true);
        newDomain.setSupplierOperator(supplierOperatorDomain);
        newDomain.setRegisterGroup(registerGroupDomain);
        newDomain.setRegisterBookingOperator(registerBookingOperatorDomain);
        newDomain.setRequestOrderServiceState(RequestOrderServiceStateEnum.DRAFT.getName());

        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRequestOrderServiceNumberCallback.generateNumber());
        RequestOrderServiceDomain createdDomain = repo.create(newDomain);

        registerBookingOperatorDomain.setStateBooking(StateBookingEnum.SENT_TO_OPERATIONS.getName());
        // Change Status to booking
        registerBookingOperatorRepo.edit(registerBookingOperatorDomain);

        return RequestOrderServiceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public void reSendRequestOrderService(String id) {
        RequestOrderServiceDomain entityDomain = this.findByIdDomain(id);
        if (entityDomain != null){

            List<RegisterBookingItemOperatorDomain> listRegisterBookingItems = registerBookingItemOperatorRepo.searchAllByBookingSimpleDomainCaseOperationAvailable(entityDomain.getRegisterBookingOperator().getId());
            listRegisterBookingItems = listRegisterBookingItems.stream().
                    filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getGeneratedBy().equals(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName())).
                    filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getService().getWithoutCost() == null || !registerBookingItemOperatorDomain.getService().getWithoutCost()).collect(Collectors.toList());
            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(id);
            listOrderServiceItems = listOrderServiceItems.stream().collect(Collectors.toList());
            List<RegisterBookingItemOperatorDomain> listReferenceDelete = new ArrayList<>();

            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for (OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems){
                    if (listRegisterBookingItems != null && !listRegisterBookingItems.isEmpty()){
                        for (RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain : listRegisterBookingItems){
                            if (orderServiceItemDomain.getRegisterBookingItemOperator().getId().equals(registerBookingItemOperatorDomain.getId())){
                                listReferenceDelete.add(registerBookingItemOperatorDomain);
                                break;
                            }
                        }
                    }
                }
            }

            if (listReferenceDelete != null && !listReferenceDelete.isEmpty()){
                for (RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain : listReferenceDelete){
                    listRegisterBookingItems.remove(registerBookingItemOperatorDomain);
                }
            }

            if (!listRegisterBookingItems.isEmpty()){
                repo.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(id, RequestOrderServiceStateEnum.IN_PROGRESS.getName());
            }
        }
    }

    private void _changeStateDraft(RequestOrderServiceDomain requestOrderServiceDomain, RequestOrderServiceChangeStateRequest request){

    }

    private void _changeStateInProgress(RequestOrderServiceDomain requestOrderServiceDomain, RequestOrderServiceChangeStateRequest request){

    }

    private void _changeStateFinished(RequestOrderServiceDomain requestOrderServiceDomain, RequestOrderServiceChangeStateRequest request){

    }

    private void _changeStateCancelled(RequestOrderServiceDomain requestOrderServiceDomain, RequestOrderServiceChangeStateRequest request){

    }

}
