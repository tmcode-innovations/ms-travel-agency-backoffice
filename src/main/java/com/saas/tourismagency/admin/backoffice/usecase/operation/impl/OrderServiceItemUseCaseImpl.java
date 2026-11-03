package com.saas.tourismagency.admin.backoffice.usecase.operation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderserviceitem.OrderServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation.SetFieldDefaultOrderServiceItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.RegisterBookingBaseRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger.RegisterBookingItemLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.RegisterBookingItemOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.GuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice.OrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.OrderServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.RequestOrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice.OrderServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.GeneratedRegisterBookingOperatorItemEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceTypeEnum;
import com.saas.tourismagency.admin.backoffice.usecase.operation.OrderServiceItemUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceItemUseCaseImpl implements OrderServiceItemUseCase {

    @Autowired
    private OrderServiceItemRepo repo;

    @Autowired
    private RequestOrderServiceRepo requestOrderServiceRepo;

    @Autowired
    private RegisterBookingOperatorRepo registerBookingOperatorRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

    @Autowired
    private RegisterBookingItemOperatorRepo registerBookingItemOperatorRepo;

    @Autowired
    private RegisterBookingItemLodgerRepo registerBookingItemLodgerRepo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private SupplierGuideRepo supplierGuideRepo;

    @Autowired
    private SupplierTransportRepo supplierTransportRepo;

    @Autowired
    private SupplierLodgingRepo supplierLodgingRepo;

    @Autowired
    private SupplierLodgerRepo supplierLodgerRepo;

    @Autowired
    private SupplierGlampingRepo supplierGlampingRepo;

    @Autowired
    private GuideRepo guideRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private OrderServiceRepo orderServiceRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private RegisterBookingBaseRepo registerBookingBaseRepo;

    @Autowired
    private OrderServiceItemConverter converter;

    @Autowired
    private SetFieldDefaultOrderServiceItem setFieldDefault;

    @Override
    @Transactional
    public OrderServiceItemResponse create(OrderServiceItemCreateRequest request) {

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            IdBaseDomain supplierOperatorIdBaseDomain = supplierOperatorRepo.findByIdEntity(request.getSupplierOperator());
            supplierOperatorDomain = new SupplierOperatorDomain();
            supplierOperatorDomain.setId(supplierOperatorIdBaseDomain.getId());
        }

        RequestOrderServiceDomain requestOrderServiceDomain = null;
        if (request.getRequestOrderService() != null && !request.getRequestOrderService().isEmpty()){
            IdBaseDomain requestOrderServiceIdBaseDomain = requestOrderServiceRepo.findByIdEntity(request.getRequestOrderService());
            requestOrderServiceDomain = new RequestOrderServiceDomain();
            requestOrderServiceDomain.setId(requestOrderServiceIdBaseDomain.getId());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(request.getRegisterBookingOperator());
            registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
            registerBookingOperatorDomain.setId(registerBookingOperatorIdBaseDomain.getId());
        }

        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = null;
        if (request.getRegisterBookingItemOperator() != null && !request.getRegisterBookingItemOperator().isEmpty()){
            IdBaseDomain registerBookingItemOperatorIdBaseDomain = registerBookingItemOperatorRepo.findByIdEntity(request.getRegisterBookingItemOperator());
            registerBookingItemOperatorDomain = new RegisterBookingItemOperatorDomain();
            registerBookingItemOperatorDomain.setId(registerBookingItemOperatorIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierGuideDomain supplierGuideDomain = null;
        if (request.getSupplierGuide() != null && !request.getSupplierGuide().isEmpty()){
            IdBaseDomain supplierGuideIdBaseDomain = supplierGuideRepo.findByIdEntity(request.getSupplierGuide());
            supplierGuideDomain = new SupplierGuideDomain();
            supplierGuideDomain.setId(supplierGuideIdBaseDomain.getId());
        }

        SupplierTransportDomain supplierTransportDomain = null;
        if (request.getSupplierTransport() != null && !request.getSupplierTransport().isEmpty()){
            IdBaseDomain supplierTransportIdBaseDomain = supplierTransportRepo.findByIdEntity(request.getSupplierTransport());
            supplierTransportDomain = new SupplierTransportDomain();
            supplierTransportDomain.setId(supplierTransportIdBaseDomain.getId());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getSupplierLodging());
            supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
        }

        SupplierLodgerDomain supplierLodgerDomain = null;
        if (request.getSupplierLodger() != null && !request.getSupplierLodger().isEmpty()){
            IdBaseDomain supplierLodgerIdBaseDomain = supplierLodgerRepo.findByIdEntity(request.getSupplierLodger());
            supplierLodgerDomain = new SupplierLodgerDomain();
            supplierLodgerDomain.setId(supplierLodgerIdBaseDomain.getId());
        }

        SupplierGlampingDomain supplierGlampingDomain = null;
        if (request.getSupplierGlamping() != null && !request.getSupplierGlamping().isEmpty()){
            IdBaseDomain supplierGlampingIdBaseDomain = supplierGlampingRepo.findByIdEntity(request.getSupplierGlamping());
            supplierGlampingDomain = new SupplierGlampingDomain();
            supplierGlampingDomain.setId(supplierGlampingIdBaseDomain.getId());
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        GuideDomain driveDomain = null;
        if (request.getDrive() != null && !request.getDrive().isEmpty()){
            IdBaseDomain driveIdBaseDomain = guideRepo.findByIdEntity(request.getDrive());
            driveDomain = new GuideDomain();
            driveDomain.setId(driveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        OrderServiceDomain orderServiceDomain = null;
        if (request.getOrderService() != null && !request.getOrderService().isEmpty()){
            IdBaseDomain orderServiceIdBaseDomain = orderServiceRepo.findByIdEntity(request.getOrderService());
            orderServiceDomain = new OrderServiceDomain();
            orderServiceDomain.setId(orderServiceIdBaseDomain.getId());
        }

        SupplierDomain supplier = null;
        SupplierTypeDomain supplierType = null;

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){

            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            supplierType = new SupplierTypeDomain();
            supplierType.setId(supplierTypeDomainOperator.getId());

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }

        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){

            if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
                IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
                supplierType = new SupplierTypeDomain();
                supplierType.setId(supplierTypeIdBaseDomain.getId());
            }

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }
        }

        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }

        OrderServiceItemDomain newDomain = request.to(
                orderServiceDomain,
                requestOrderServiceDomain,
                registerBookingOperatorDomain,
                registerBookingItemOperatorDomain,
                serviceDomain,
                supplierOperatorDomain,
                registerGroupDomain,
                supplierGuideDomain,
                supplierTransportDomain,
                supplierLodgingDomain,
                supplierLodgerDomain,
                supplierGlampingDomain,
                driveDomain,
                guideTransferDomain,
                supplier,
                supplierType,
                itineraryDateDomain
        );
        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            if (itineraryDateDomain != null){
                newDomain.setDateService(itineraryDateDomain.getItineraryDate());
            }
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setDateTimePax(newDomain.getDateTimeService());
        OrderServiceItemDomain createdDomain = repo.create(newDomain);

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            createItineraryCaseOrderServiceTypeOperation(createdDomain);
        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            updateItineraryCaseOrderServiceTypeBooking(createdDomain);
        }

        return OrderServiceItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public OrderServiceItemResponse create(OrderServiceItemCreateRequest request, OrderServiceDomain orderServiceDomain) {

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            IdBaseDomain supplierOperatorIdBaseDomain = supplierOperatorRepo.findByIdEntity(request.getSupplierOperator());
            supplierOperatorDomain = new SupplierOperatorDomain();
            supplierOperatorDomain.setId(supplierOperatorIdBaseDomain.getId());
        }

        RequestOrderServiceDomain requestOrderServiceDomain = null;
        if (request.getRequestOrderService() != null && !request.getRequestOrderService().isEmpty()){
            IdBaseDomain requestOrderServiceIdBaseDomain = requestOrderServiceRepo.findByIdEntity(request.getRequestOrderService());
            requestOrderServiceDomain = new RequestOrderServiceDomain();
            requestOrderServiceDomain.setId(requestOrderServiceIdBaseDomain.getId());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(request.getRegisterBookingOperator());
            registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
            registerBookingOperatorDomain.setId(registerBookingOperatorIdBaseDomain.getId());
        }

        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = null;
        if (request.getRegisterBookingItemOperator() != null && !request.getRegisterBookingItemOperator().isEmpty()){
            registerBookingItemOperatorDomain = registerBookingItemOperatorRepo.findById(request.getRegisterBookingItemOperator());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierGuideDomain supplierGuideDomain = null;
        if (request.getSupplierGuide() != null && !request.getSupplierGuide().isEmpty()){
            IdBaseDomain supplierGuideIdBaseDomain = supplierGuideRepo.findByIdEntity(request.getSupplierGuide());
            supplierGuideDomain = new SupplierGuideDomain();
            supplierGuideDomain.setId(supplierGuideIdBaseDomain.getId());
        }

        SupplierTransportDomain supplierTransportDomain = null;
        if (request.getSupplierTransport() != null && !request.getSupplierTransport().isEmpty()){
            IdBaseDomain supplierTransportIdBaseDomain = supplierTransportRepo.findByIdEntity(request.getSupplierTransport());
            supplierTransportDomain = new SupplierTransportDomain();
            supplierTransportDomain.setId(supplierTransportIdBaseDomain.getId());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getSupplierLodging());
            supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
        }

        SupplierLodgerDomain supplierLodgerDomain = null;
        if (request.getSupplierLodger() != null && !request.getSupplierLodger().isEmpty()){
            IdBaseDomain supplierLodgerIdBaseDomain = supplierLodgerRepo.findByIdEntity(request.getSupplierLodger());
            supplierLodgerDomain = new SupplierLodgerDomain();
            supplierLodgerDomain.setId(supplierLodgerIdBaseDomain.getId());
        }

        SupplierGlampingDomain supplierGlampingDomain = null;
        if (request.getSupplierGlamping() != null && !request.getSupplierGlamping().isEmpty()){
            IdBaseDomain supplierGlampingIdBaseDomain = supplierGlampingRepo.findByIdEntity(request.getSupplierGlamping());
            supplierGlampingDomain = new SupplierGlampingDomain();
            supplierGlampingDomain.setId(supplierGlampingIdBaseDomain.getId());
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        GuideDomain driveDomain = null;
        if (request.getDrive() != null && !request.getDrive().isEmpty()){
            IdBaseDomain driveIdBaseDomain = guideRepo.findByIdEntity(request.getDrive());
            driveDomain = new GuideDomain();
            driveDomain.setId(driveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        SupplierDomain supplier = null;
        SupplierTypeDomain supplierType = null;

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){

            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            supplierType = new SupplierTypeDomain();
            supplierType.setId(supplierTypeDomainOperator.getId());

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }

        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){

            if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
                IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
                supplierType = new SupplierTypeDomain();
                supplierType.setId(supplierTypeIdBaseDomain.getId());
            }

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }
        }

        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }

        OrderServiceItemDomain newDomain = request.to(
                orderServiceDomain,
                requestOrderServiceDomain,
                registerBookingOperatorDomain,
                registerBookingItemOperatorDomain,
                serviceDomain,
                supplierOperatorDomain,
                registerGroupDomain,
                supplierGuideDomain,
                supplierTransportDomain,
                supplierLodgingDomain,
                supplierLodgerDomain,
                supplierGlampingDomain,
                driveDomain,
                guideTransferDomain,
                supplier,
                supplierType,
                itineraryDateDomain
        );
        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            if (itineraryDateDomain != null){
                newDomain.setDateService(itineraryDateDomain.getItineraryDate());
            }
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        OrderServiceItemDomain createdDomain = repo.create(newDomain);

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            createItineraryCaseOrderServiceTypeOperation(createdDomain);
        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            updateItineraryCaseOrderServiceTypeBooking(createdDomain);
        }

        return OrderServiceItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public OrderServiceItemResponse edit(String id, OrderServiceItemUpdateRequest request) {
        if (id == null) {
            return null;
        }
        OrderServiceItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            IdBaseDomain supplierOperatorIdBaseDomain = supplierOperatorRepo.findByIdEntity(request.getSupplierOperator());
            supplierOperatorDomain = new SupplierOperatorDomain();
            supplierOperatorDomain.setId(supplierOperatorIdBaseDomain.getId());
        }

        RequestOrderServiceDomain requestOrderServiceDomain = null;
        if (request.getRequestOrderService() != null && !request.getRequestOrderService().isEmpty()){
            IdBaseDomain requestOrderServiceIdBaseDomain = requestOrderServiceRepo.findByIdEntity(request.getRequestOrderService());
            requestOrderServiceDomain = new RequestOrderServiceDomain();
            requestOrderServiceDomain.setId(requestOrderServiceIdBaseDomain.getId());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(request.getRegisterBookingOperator());
            registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
            registerBookingOperatorDomain.setId(registerBookingOperatorIdBaseDomain.getId());
        }

        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = null;
        if (request.getRegisterBookingItemOperator() != null && !request.getRegisterBookingItemOperator().isEmpty()){
            registerBookingItemOperatorDomain = registerBookingItemOperatorRepo.findById(request.getRegisterBookingItemOperator());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierGuideDomain supplierGuideDomain = null;
        if (request.getSupplierGuide() != null && !request.getSupplierGuide().isEmpty()){
            IdBaseDomain supplierGuideIdBaseDomain = supplierGuideRepo.findByIdEntity(request.getSupplierGuide());
            supplierGuideDomain = new SupplierGuideDomain();
            supplierGuideDomain.setId(supplierGuideIdBaseDomain.getId());
        }

        SupplierTransportDomain supplierTransportDomain = null;
        if (request.getSupplierTransport() != null && !request.getSupplierTransport().isEmpty()){
            IdBaseDomain supplierTransportIdBaseDomain = supplierTransportRepo.findByIdEntity(request.getSupplierTransport());
            supplierTransportDomain = new SupplierTransportDomain();
            supplierTransportDomain.setId(supplierTransportIdBaseDomain.getId());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getSupplierLodging());
            supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
        }

        SupplierLodgerDomain supplierLodgerDomain = null;
        if (request.getSupplierLodger() != null && !request.getSupplierLodger().isEmpty()){
            IdBaseDomain supplierLodgerIdBaseDomain = supplierLodgerRepo.findByIdEntity(request.getSupplierLodger());
            supplierLodgerDomain = new SupplierLodgerDomain();
            supplierLodgerDomain.setId(supplierLodgerIdBaseDomain.getId());
        }

        SupplierGlampingDomain supplierGlampingDomain = null;
        if (request.getSupplierGlamping() != null && !request.getSupplierGlamping().isEmpty()){
            IdBaseDomain supplierGlampingIdBaseDomain = supplierGlampingRepo.findByIdEntity(request.getSupplierGlamping());
            supplierGlampingDomain = new SupplierGlampingDomain();
            supplierGlampingDomain.setId(supplierGlampingIdBaseDomain.getId());
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        GuideDomain driveDomain = null;
        if (request.getDrive() != null && !request.getDrive().isEmpty()){
            IdBaseDomain driveIdBaseDomain = guideRepo.findByIdEntity(request.getDrive());
            driveDomain = new GuideDomain();
            driveDomain.setId(driveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        OrderServiceDomain orderServiceDomain = null;
        if (request.getOrderService() != null && !request.getOrderService().isEmpty()){
            IdBaseDomain orderServiceIdBaseDomain = orderServiceRepo.findByIdEntity(request.getOrderService());
            orderServiceDomain = new OrderServiceDomain();
            orderServiceDomain.setId(orderServiceIdBaseDomain.getId());
        }

        SupplierDomain supplier = null;
        SupplierTypeDomain supplierType = null;

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){

            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            supplierType = new SupplierTypeDomain();
            supplierType.setId(supplierTypeDomainOperator.getId());

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }

        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){

            if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
                IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
                supplierType = new SupplierTypeDomain();
                supplierType.setId(supplierTypeIdBaseDomain.getId());
            }

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }
        }

        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }

        OrderServiceItemDomain orderServiceItemDomain = request.to(
                orderServiceDomain,
                requestOrderServiceDomain,
                registerBookingOperatorDomain,
                registerBookingItemOperatorDomain,
                serviceDomain,
                supplierOperatorDomain,
                registerGroupDomain,
                supplierGuideDomain,
                supplierTransportDomain,
                supplierLodgingDomain,
                supplierLodgerDomain,
                supplierGlampingDomain,
                driveDomain,
                guideTransferDomain,
                supplier,
                supplierType,
                itineraryDateDomain
        );

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            if (itineraryDateDomain != null){
                orderServiceItemDomain.setDateService(itineraryDateDomain.getItineraryDate());
            }
        }
        OrderServiceItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(orderServiceItemDomain, old));

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            updateItineraryCaseOrderServiceTypeOperation(editedDomain);
        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            updateItineraryCaseOrderServiceTypeBooking(editedDomain);
        }

        return OrderServiceItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public OrderServiceItemResponse edit(String id, OrderServiceItemUpdateRequest request, OrderServiceDomain orderServiceDomain) {
        if (id == null) {
            return null;
        }
        OrderServiceItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSupplierOperator() != null && !request.getSupplierOperator().isEmpty()){
            IdBaseDomain supplierOperatorIdBaseDomain = supplierOperatorRepo.findByIdEntity(request.getSupplierOperator());
            supplierOperatorDomain = new SupplierOperatorDomain();
            supplierOperatorDomain.setId(supplierOperatorIdBaseDomain.getId());
        }

        RequestOrderServiceDomain requestOrderServiceDomain = null;
        if (request.getRequestOrderService() != null && !request.getRequestOrderService().isEmpty()){
            IdBaseDomain requestOrderServiceIdBaseDomain = requestOrderServiceRepo.findByIdEntity(request.getRequestOrderService());
            requestOrderServiceDomain = new RequestOrderServiceDomain();
            requestOrderServiceDomain.setId(requestOrderServiceIdBaseDomain.getId());
        }

        RegisterBookingOperatorDomain registerBookingOperatorDomain = null;
        if (request.getRegisterBookingOperator() != null && !request.getRegisterBookingOperator().isEmpty()){
            IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(request.getRegisterBookingOperator());
            registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
            registerBookingOperatorDomain.setId(registerBookingOperatorIdBaseDomain.getId());
        }

        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = null;
        if (request.getRegisterBookingItemOperator() != null && !request.getRegisterBookingItemOperator().isEmpty()){
            registerBookingItemOperatorDomain = registerBookingItemOperatorRepo.findById(request.getRegisterBookingItemOperator());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierGuideDomain supplierGuideDomain = null;
        if (request.getSupplierGuide() != null && !request.getSupplierGuide().isEmpty()){
            IdBaseDomain supplierGuideIdBaseDomain = supplierGuideRepo.findByIdEntity(request.getSupplierGuide());
            supplierGuideDomain = new SupplierGuideDomain();
            supplierGuideDomain.setId(supplierGuideIdBaseDomain.getId());
        }

        SupplierTransportDomain supplierTransportDomain = null;
        if (request.getSupplierTransport() != null && !request.getSupplierTransport().isEmpty()){
            IdBaseDomain supplierTransportIdBaseDomain = supplierTransportRepo.findByIdEntity(request.getSupplierTransport());
            supplierTransportDomain = new SupplierTransportDomain();
            supplierTransportDomain.setId(supplierTransportIdBaseDomain.getId());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getSupplierLodging());
            supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
        }

        SupplierLodgerDomain supplierLodgerDomain = null;
        if (request.getSupplierLodger() != null && !request.getSupplierLodger().isEmpty()){
            IdBaseDomain supplierLodgerIdBaseDomain = supplierLodgerRepo.findByIdEntity(request.getSupplierLodger());
            supplierLodgerDomain = new SupplierLodgerDomain();
            supplierLodgerDomain.setId(supplierLodgerIdBaseDomain.getId());
        }

        SupplierGlampingDomain supplierGlampingDomain = null;
        if (request.getSupplierGlamping() != null && !request.getSupplierGlamping().isEmpty()){
            IdBaseDomain supplierGlampingIdBaseDomain = supplierGlampingRepo.findByIdEntity(request.getSupplierGlamping());
            supplierGlampingDomain = new SupplierGlampingDomain();
            supplierGlampingDomain.setId(supplierGlampingIdBaseDomain.getId());
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        GuideDomain driveDomain = null;
        if (request.getDrive() != null && !request.getDrive().isEmpty()){
            IdBaseDomain driveIdBaseDomain = guideRepo.findByIdEntity(request.getDrive());
            driveDomain = new GuideDomain();
            driveDomain.setId(driveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        SupplierDomain supplier = null;
        SupplierTypeDomain supplierType = null;

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){

            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            supplierType = new SupplierTypeDomain();
            supplierType.setId(supplierTypeDomainOperator.getId());

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }

        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){

            if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
                IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
                supplierType = new SupplierTypeDomain();
                supplierType.setId(supplierTypeIdBaseDomain.getId());
            }

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }
        }

        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }

        OrderServiceItemDomain orderServiceItemDomain = request.to(
                orderServiceDomain,
                requestOrderServiceDomain,
                registerBookingOperatorDomain,
                registerBookingItemOperatorDomain,
                serviceDomain,
                supplierOperatorDomain,
                registerGroupDomain,
                supplierGuideDomain,
                supplierTransportDomain,
                supplierLodgingDomain,
                supplierLodgerDomain,
                supplierGlampingDomain,
                driveDomain,
                guideTransferDomain,
                supplier,
                supplierType,
                itineraryDateDomain
        );
        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            if (itineraryDateDomain != null){
                orderServiceItemDomain.setDateService(itineraryDateDomain.getItineraryDate());
            }
        }
        OrderServiceItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(orderServiceItemDomain, old));

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            updateItineraryCaseOrderServiceTypeOperation(editedDomain);
        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            updateItineraryCaseOrderServiceTypeBooking(editedDomain);
        }

        return OrderServiceItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        OrderServiceItemResponse old = findById(id);

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);

        if (old.getOrderService().getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchOrderServiceItem(old.getId());
            if (itineraryItemDomain != null){
                itineraryItemDomain.setOrderServiceItem(null);
                itineraryItemDomain.setGuideTransport(null);
                itineraryItemDomain.setGuideTransferDrive(null);
                itineraryItemDomain.setOrderServiceItem(null);
                itineraryItemRepo.edit(itineraryItemDomain);
            }
        }else if(old.getOrderService().getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchOrderServiceItem(old.getId());
            if (itineraryItemDomain != null){
                itineraryItemDomain.setOrderServiceItem(null);
                itineraryItemDomain.setGuideTransport(null);
                itineraryItemDomain.setGuideTransferDrive(null);
                itineraryItemDomain.setOrderServiceItem(null);
                itineraryItemRepo.edit(itineraryItemDomain);
                itineraryItemRepo.deleteById(itineraryItemDomain.getId());

                if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){

                    RegisterBookingOperatorDomain registerBookingOperatorDomain =
                            registerBookingOperatorRepo.getOneRegisterBookingByRegisterGroupAndSupplierAndActive(itineraryItemDomain.getRegisterGroup().getId(), itineraryItemDomain.getSupplier().getId());

                    if (registerBookingOperatorDomain != null) {
                        if (
                                itineraryItemDomain.getRegisterBookingItemOperator() != null &&
                                        itineraryItemDomain.getRegisterBookingItemOperator().getId() != null &&
                                        !itineraryItemDomain.getRegisterBookingItemOperator().getId().isEmpty()
                        ) {

                            RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = registerBookingItemOperatorRepo.findById(itineraryItemDomain.getRegisterBookingItemOperator().getId());
                            if (registerBookingItemOperatorDomain != null){
                                registerBookingItemOperatorRepo.deleteById(registerBookingItemOperatorDomain.getId());
                            }
                        }
                    }
                }else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){

                    RegisterBookingLodgerDomain registerBookingLodgerDomain =
                            registerBookingLodgerRepo.getOneRegisterBookingByRegisterGroupAndSupplierAndActive(itineraryItemDomain.getRegisterGroup().getId(), itineraryItemDomain.getSupplier().getId());

                    if (registerBookingLodgerDomain != null) {
                        if (
                                itineraryItemDomain.getRegisterBookingItemLodger() != null &&
                                        itineraryItemDomain.getRegisterBookingItemLodger().getId() != null &&
                                        !itineraryItemDomain.getRegisterBookingItemLodger().getId().isEmpty()
                        ) {

                            RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = registerBookingItemLodgerRepo.findById(itineraryItemDomain.getRegisterBookingItemLodger().getId());
                            if (registerBookingItemLodgerDomain != null){
                                registerBookingItemLodgerRepo.deleteById(registerBookingItemLodgerDomain.getId());
                            }
                        }
                    }
                }
            }
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceItemResponse> findAll() {
        return repo.findAll(false).stream().map(orderServiceItemDomain -> OrderServiceItemResponse.from(orderServiceItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceItemResponse findById(String id) {
        if (id == null) {
            return null;
        }
        OrderServiceItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return OrderServiceItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceItemDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        OrderServiceItemDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No entity with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceItemSearchResponse searchAll(OrderServiceItemSearchRequest bankSearchRequest) {
       PageDTO<OrderServiceItemDomain> pageDTO = repo.searchAll(bankSearchRequest);
       OrderServiceItemSearchResponse bankSearchRespose = new OrderServiceItemSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  OrderServiceItemResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceItemSearchResponse searchFilterAll(OrderServiceItemSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<OrderServiceItemDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        OrderServiceItemSearchResponse bankSearchRespose = new OrderServiceItemSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  OrderServiceItemResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceItemResponse> searchAllByOrderService(String idOrderService) {
        List<OrderServiceItemDomain> list = repo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItems(idOrderService);
        return list.stream().map(orderServiceItemDomain ->  OrderServiceItemResponse.from(orderServiceItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceItemResponse searchByOrderServiceAndOrderServiceItem(String idOrderService, String idOrderServiceItem) {
        OrderServiceItemDomain orderServiceItemDomain = repo.searchOrderServiceAndOrderServiceItem(idOrderService, idOrderServiceItem);
        return OrderServiceItemResponse.from(orderServiceItemDomain);
    }

    private void createItineraryCaseOrderServiceTypeOperation(OrderServiceItemDomain orderServiceItemDomain){
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);

        ItineraryItemDomain newDomain = new ItineraryItemDomain();
        newDomain.setRegisterGroup(orderServiceItemDomain.getRegisterGroup());
        newDomain.setItineraryDate(orderServiceItemDomain.getItineraryDate());
        newDomain.setSupplierType(orderServiceItemDomain.getSupplierType());
        newDomain.setSupplier(orderServiceItemDomain.getSupplier());
        newDomain.setService(orderServiceItemDomain.getService());
        // newDomain.setDepartmentRegion();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(orderServiceItemDomain.getRegisterGroup().getId()));

        newDomain.setPaxAdultTotal(orderServiceItemDomain.getCantPaxAdult());
        newDomain.setPaxChildrenTotal(orderServiceItemDomain.getCantPaxChildren());
        newDomain.setPaxTcTotal(orderServiceItemDomain.getTc());
        newDomain.setPaxTcLocalTotal(orderServiceItemDomain.getTcLocal());
        newDomain.setPaxGuideTotal(orderServiceItemDomain.getGuide());

        newDomain.setServiceForOperation(true);
        newDomain.setObservation(orderServiceItemDomain.getObservation());

        newDomain.setOperatorPvt(orderServiceItemDomain.getOperatorPvt() != null ? orderServiceItemDomain.getOperatorPvt() : false);
        newDomain.setOperatorSic(orderServiceItemDomain.getOperatorSic() != null ? orderServiceItemDomain.getOperatorSic() : false);

        newDomain.setLodgePvt(orderServiceItemDomain.getLodgePvt() != null ? orderServiceItemDomain.getLodgePvt() : false);
        newDomain.setLodgeSic(orderServiceItemDomain.getLodgeSic() != null ? orderServiceItemDomain.getLodgeSic() : false);

        newDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.OPERATION.getName());

        LocalDateTime localDateTime = LocalDateTime.of(
                orderServiceItemDomain.getItineraryDate().getItineraryDate().getYear(),
                orderServiceItemDomain.getItineraryDate().getItineraryDate().getMonth(),
                orderServiceItemDomain.getItineraryDate().getItineraryDate().getDayOfMonth(),
                orderServiceItemDomain.getDateTimeService().getHour(),
                orderServiceItemDomain.getDateTimeService().getMinute(),
                orderServiceItemDomain.getDateTimeService().getSecond()
        );
        newDomain.setItineraryDateTime(localDateTime);
        newDomain.setPaxTotal((newDomain.getPaxAdultTotal() != null ? newDomain.getPaxAdultTotal() : 0)  + (newDomain.getPaxChildrenTotal() != null ? newDomain.getPaxChildrenTotal()  : 0));
        // newDomain.setCustomer(orderServiceItemDomain.getRegisterGroup().getCustomer());
        newDomain.setOrderServiceItem(orderServiceItemDomain);
        newDomain.setGuideTransport(orderServiceItemDomain.getDrive());
        newDomain.setGuideTransferDrive(orderServiceItemDomain.getGuideTransfer());
        ItineraryItemDomain itineraryItemResult = itineraryItemRepo.create(newDomain);

        if ( itineraryItemResult.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
            RegisterBookingOperatorDomain registerBookingOperatorDomain =
                    registerBookingOperatorRepo.getOneRegisterBookingByRegisterGroupAndSupplierAndActive(itineraryItemResult.getRegisterGroup().getId(), itineraryItemResult.getSupplier().getId());

            if (registerBookingOperatorDomain != null){

                RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = new RegisterBookingItemOperatorDomain();
                registerBookingItemOperatorDomain.setRegisterBookingOperator(registerBookingOperatorDomain);
                registerBookingItemOperatorDomain.setRegisterGroup(itineraryItemResult.getRegisterGroup());
                registerBookingItemOperatorDomain.setService(itineraryItemResult.getService());
                registerBookingItemOperatorDomain.setObservation(itineraryItemResult.getObservation());
                registerBookingItemOperatorDomain.setPvt(itineraryItemResult.getOperatorPvt());
                registerBookingItemOperatorDomain.setSic(itineraryItemResult.getOperatorSic());
                registerBookingItemOperatorDomain.setDate(itineraryItemResult.getItineraryDate().getItineraryDate());
                registerBookingItemOperatorDomain.setDateTime(itineraryItemResult.getItineraryDateTime());
                registerBookingItemOperatorDomain.setIdItineraryItemIdentify(itineraryItemResult.getId());
                registerBookingItemOperatorDomain.setSequence(this.getLastIndexBookingOperatorItem(registerBookingOperatorDomain.getId()));
                registerBookingItemOperatorDomain.setDateService(itineraryItemResult.getItineraryDate().getItineraryDate());

                registerBookingItemOperatorDomain.setCantPax(
                        (itineraryItemResult.getPaxAdultTotal() != null ?  itineraryItemResult.getPaxAdultTotal() : 0)
                                + (itineraryItemResult.getPaxChildrenTotal() != null ?  itineraryItemResult.getPaxChildrenTotal() : 0)
                );
                registerBookingItemOperatorDomain.setCantPaxAdult(itineraryItemResult.getPaxAdultTotal() != null ?  itineraryItemResult.getPaxAdultTotal() : 0);
                registerBookingItemOperatorDomain.setCantPaxChildren(itineraryItemResult.getPaxChildrenTotal() != null ?  itineraryItemResult.getPaxChildrenTotal() : 0);
                registerBookingItemOperatorDomain.setTc(itineraryItemResult.getPaxTcTotal() != null ?  itineraryItemResult.getPaxTcTotal() : 0);
                registerBookingItemOperatorDomain.setTcLocal(itineraryItemResult.getPaxTcLocalTotal() != null ?  itineraryItemResult.getPaxTcLocalTotal() : 0);
                registerBookingItemOperatorDomain.setGuide(itineraryItemResult.getPaxGuideTotal() != null ?  itineraryItemResult.getPaxGuideTotal() : 0);

                registerBookingItemOperatorDomain.setTenant(UserResolver.resolveUser().getTenant());
                registerBookingItemOperatorDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.OPERATION.getName());
                RegisterBookingItemOperatorDomain createdDomainRegisterBookingItemOperator = registerBookingItemOperatorRepo.create(registerBookingItemOperatorDomain);
                itineraryItemRepo.updateCustomerByIdSimpleDomainCaseUpdateRegisterBookingItemOperator(itineraryItemResult.getId(), createdDomainRegisterBookingItemOperator.getId());

                registerBookingOperatorDomain.setNumberService(registerBookingOperatorDomain.getNumberService() + 1);
                RegisterBookingOperatorDomain editedRegisterBookingOperatorDomain = registerBookingOperatorRepo.edit(registerBookingOperatorDomain);
            }

        }else if ( itineraryItemResult.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){

            RegisterBookingLodgerDomain registerBookingLodgerDomain =
                    registerBookingLodgerRepo.getOneRegisterBookingByRegisterGroupAndSupplierAndActive(itineraryItemResult.getRegisterGroup().getId(), itineraryItemResult.getSupplier().getId());

            if (registerBookingLodgerDomain != null){

                RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = new RegisterBookingItemLodgerDomain();
                registerBookingItemLodgerDomain.setRegisterBookingLodger(registerBookingLodgerDomain);
                registerBookingItemLodgerDomain.setRegisterGroup(itineraryItemResult.getRegisterGroup());
                registerBookingItemLodgerDomain.setService(itineraryItemResult.getService());
                registerBookingItemLodgerDomain.setObservation(itineraryItemResult.getObservation());
                registerBookingItemLodgerDomain.setPvt(itineraryItemResult.getOperatorPvt());
                registerBookingItemLodgerDomain.setSic(itineraryItemResult.getOperatorSic());
                registerBookingItemLodgerDomain.setDate(itineraryItemResult.getItineraryDate().getItineraryDate());
                registerBookingItemLodgerDomain.setDateTime(itineraryItemResult.getItineraryDateTime());
                registerBookingItemLodgerDomain.setIdItineraryItemIdentify(itineraryItemResult.getId());
                registerBookingItemLodgerDomain.setSequence(this.getLastIndexBookingLodgerItem(registerBookingLodgerDomain.getId()));
                registerBookingItemLodgerDomain.setDateService(itineraryItemResult.getItineraryDate().getItineraryDate());

                registerBookingItemLodgerDomain.setCantPax(
                        (itineraryItemResult.getPaxAdultTotal() != null ?  itineraryItemResult.getPaxAdultTotal() : 0)
                                + (itineraryItemResult.getPaxChildrenTotal() != null ?  itineraryItemResult.getPaxChildrenTotal() : 0)
                );
                registerBookingItemLodgerDomain.setCantPaxAdult(itineraryItemResult.getPaxAdultTotal() != null ?  itineraryItemResult.getPaxAdultTotal() : 0);
                registerBookingItemLodgerDomain.setCantPaxChildren(itineraryItemResult.getPaxChildrenTotal() != null ?  itineraryItemResult.getPaxChildrenTotal() : 0);
                registerBookingItemLodgerDomain.setTc(itineraryItemResult.getPaxTcTotal() != null ?  itineraryItemResult.getPaxTcTotal() : 0);
                registerBookingItemLodgerDomain.setTcLocal(itineraryItemResult.getPaxTcLocalTotal() != null ?  itineraryItemResult.getPaxTcLocalTotal() : 0);
                registerBookingItemLodgerDomain.setGuide(itineraryItemResult.getPaxGuideTotal() != null ?  itineraryItemResult.getPaxGuideTotal() : 0);

                registerBookingItemLodgerDomain.setTenant(UserResolver.resolveUser().getTenant());
                RegisterBookingItemLodgerDomain createdDomainRegisterBookingItemLodger = registerBookingItemLodgerRepo.create(registerBookingItemLodgerDomain);
                itineraryItemRepo.updateCustomerByIdSimpleDomainCaseUpdateRegisterBookingItemLodger(itineraryItemResult.getId(), createdDomainRegisterBookingItemLodger.getId());

                registerBookingLodgerDomain.setNumberService(registerBookingLodgerDomain.getNumberService() + 1);
                RegisterBookingLodgerDomain editedRegisterBookingLodgerDomain = registerBookingLodgerRepo.edit(registerBookingLodgerDomain);

            }

        }
    }

    private void updateItineraryCaseOrderServiceTypeOperation(OrderServiceItemDomain orderServiceItemDomain){
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);

        ItineraryItemDomain editedDomain = itineraryItemRepo.searchOrderServiceItem(orderServiceItemDomain.getId());
        if (editedDomain != null){
            editedDomain.setRegisterGroup(orderServiceItemDomain.getRegisterGroup());
            editedDomain.setItineraryDate(orderServiceItemDomain.getItineraryDate());
            editedDomain.setSupplierType(orderServiceItemDomain.getSupplierType());
            editedDomain.setSupplier(orderServiceItemDomain.getSupplier());
            editedDomain.setService(orderServiceItemDomain.getService());
            // newDomain.setDepartmentRegion();
            editedDomain.setPaxAdultTotal(orderServiceItemDomain.getCantPaxAdult());
            editedDomain.setPaxChildrenTotal(orderServiceItemDomain.getCantPaxChildren());
            editedDomain.setPaxTcTotal(orderServiceItemDomain.getTc());
            editedDomain.setPaxTcLocalTotal(orderServiceItemDomain.getTcLocal());
            editedDomain.setPaxGuideTotal(orderServiceItemDomain.getGuide());

            editedDomain.setOperatorPvt(orderServiceItemDomain.getOperatorPvt() != null ? orderServiceItemDomain.getOperatorPvt() : false);
            editedDomain.setOperatorSic(orderServiceItemDomain.getOperatorSic() != null ? orderServiceItemDomain.getOperatorSic() : false);

            editedDomain.setLodgePvt(orderServiceItemDomain.getLodgePvt() != null ? orderServiceItemDomain.getLodgePvt() : false);
            editedDomain.setLodgeSic(orderServiceItemDomain.getLodgeSic() != null ? orderServiceItemDomain.getLodgeSic() : false);

            editedDomain.setObservation(orderServiceItemDomain.getObservation());

            LocalDateTime localDateTime = LocalDateTime.of(
                    orderServiceItemDomain.getItineraryDate().getItineraryDate().getYear(),
                    orderServiceItemDomain.getItineraryDate().getItineraryDate().getMonth(),
                    orderServiceItemDomain.getItineraryDate().getItineraryDate().getDayOfMonth(),
                    orderServiceItemDomain.getDateTimeService().getHour(),
                    orderServiceItemDomain.getDateTimeService().getMinute(),
                    orderServiceItemDomain.getDateTimeService().getSecond()
            );
            editedDomain.setItineraryDateTime(localDateTime);
            editedDomain.setOrderServiceItem(orderServiceItemDomain);
            editedDomain.setPaxTotal((editedDomain.getPaxAdultTotal() != null ? editedDomain.getPaxAdultTotal() : 0)  + (editedDomain.getPaxChildrenTotal() != null ? editedDomain.getPaxChildrenTotal()  : 0));

            editedDomain.setOrderServiceItem(orderServiceItemDomain);
            editedDomain.setGuideTransport(orderServiceItemDomain.getDrive());
            editedDomain.setGuideTransferDrive(orderServiceItemDomain.getGuideTransfer());

            ItineraryItemDomain itineraryItemEditResult = itineraryItemRepo.edit(editedDomain);

            if ( itineraryItemEditResult.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){

                RegisterBookingOperatorDomain registerBookingOperatorDomain =
                        registerBookingOperatorRepo.getOneRegisterBookingByRegisterGroupAndSupplierAndActive(itineraryItemEditResult.getRegisterGroup().getId(), itineraryItemEditResult.getSupplier().getId());

                if (registerBookingOperatorDomain != null) {

                    if (
                            itineraryItemEditResult.getRegisterBookingItemOperator() != null &&
                            itineraryItemEditResult.getRegisterBookingItemOperator().getId() != null &&
                            !itineraryItemEditResult.getRegisterBookingItemOperator().getId().isEmpty()
                    ){

                        RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = registerBookingItemOperatorRepo.findById(itineraryItemEditResult.getRegisterBookingItemOperator().getId());

                        if (registerBookingItemOperatorDomain != null){
                            registerBookingItemOperatorDomain.setRegisterBookingOperator(registerBookingOperatorDomain);
                            registerBookingItemOperatorDomain.setRegisterGroup(itineraryItemEditResult.getRegisterGroup());
                            registerBookingItemOperatorDomain.setService(itineraryItemEditResult.getService());
                            registerBookingItemOperatorDomain.setObservation(itineraryItemEditResult.getObservation());
                            registerBookingItemOperatorDomain.setPvt(itineraryItemEditResult.getOperatorPvt());
                            registerBookingItemOperatorDomain.setSic(itineraryItemEditResult.getOperatorSic());
                            registerBookingItemOperatorDomain.setDate(itineraryItemEditResult.getItineraryDate().getItineraryDate());
                            registerBookingItemOperatorDomain.setDateTime(itineraryItemEditResult.getItineraryDateTime());
                            registerBookingItemOperatorDomain.setIdItineraryItemIdentify(itineraryItemEditResult.getId());
                            registerBookingItemOperatorDomain.setDateService(itineraryItemEditResult.getItineraryDate().getItineraryDate());

                            registerBookingItemOperatorDomain.setCantPax(
                                    (itineraryItemEditResult.getPaxAdultTotal() != null ?  itineraryItemEditResult.getPaxAdultTotal() : 0)
                                            + (itineraryItemEditResult.getPaxChildrenTotal() != null ?  itineraryItemEditResult.getPaxChildrenTotal() : 0)
                            );
                            registerBookingItemOperatorDomain.setCantPaxAdult(itineraryItemEditResult.getPaxAdultTotal() != null ?  itineraryItemEditResult.getPaxAdultTotal() : 0);
                            registerBookingItemOperatorDomain.setCantPaxChildren(itineraryItemEditResult.getPaxChildrenTotal() != null ?  itineraryItemEditResult.getPaxChildrenTotal() : 0);
                            registerBookingItemOperatorDomain.setTc(itineraryItemEditResult.getPaxTcTotal() != null ?  itineraryItemEditResult.getPaxTcTotal() : 0);
                            registerBookingItemOperatorDomain.setTcLocal(itineraryItemEditResult.getPaxTcLocalTotal() != null ?  itineraryItemEditResult.getPaxTcLocalTotal() : 0);
                            registerBookingItemOperatorDomain.setGuide(itineraryItemEditResult.getPaxGuideTotal() != null ?  itineraryItemEditResult.getPaxGuideTotal() : 0);
                            registerBookingItemOperatorDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.OPERATION.getName());
                            registerBookingItemOperatorDomain.setTenant(UserResolver.resolveUser().getTenant());
                            RegisterBookingItemOperatorDomain editDomainRegisterBookingItemOperator = registerBookingItemOperatorRepo.edit(registerBookingItemOperatorDomain);
                            itineraryItemRepo.updateCustomerByIdSimpleDomainCaseUpdateRegisterBookingItemOperator(itineraryItemEditResult.getId(), editDomainRegisterBookingItemOperator.getId());

                        }
                    }

                }

            }else if ( itineraryItemEditResult.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){

                RegisterBookingLodgerDomain registerBookingLodgerDomain =
                        registerBookingLodgerRepo.getOneRegisterBookingByRegisterGroupAndSupplierAndActive(itineraryItemEditResult.getRegisterGroup().getId(), itineraryItemEditResult.getSupplier().getId());

                if (registerBookingLodgerDomain != null){

                    if (
                            itineraryItemEditResult.getRegisterBookingItemOperator() != null &&
                                    itineraryItemEditResult.getRegisterBookingItemOperator().getId() != null &&
                                    !itineraryItemEditResult.getRegisterBookingItemOperator().getId().isEmpty()
                    ){

                        RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = registerBookingItemLodgerRepo.findById(itineraryItemEditResult.getRegisterBookingItemLodger().getId());

                        if (registerBookingItemLodgerDomain != null){
                            registerBookingItemLodgerDomain.setRegisterBookingLodger(registerBookingLodgerDomain);
                            registerBookingItemLodgerDomain.setRegisterGroup(itineraryItemEditResult.getRegisterGroup());
                            registerBookingItemLodgerDomain.setService(itineraryItemEditResult.getService());
                            registerBookingItemLodgerDomain.setObservation(itineraryItemEditResult.getObservation());
                            registerBookingItemLodgerDomain.setPvt(itineraryItemEditResult.getOperatorPvt());
                            registerBookingItemLodgerDomain.setSic(itineraryItemEditResult.getOperatorSic());
                            registerBookingItemLodgerDomain.setDate(itineraryItemEditResult.getItineraryDate().getItineraryDate());
                            registerBookingItemLodgerDomain.setDateTime(itineraryItemEditResult.getItineraryDateTime());
                            registerBookingItemLodgerDomain.setIdItineraryItemIdentify(itineraryItemEditResult.getId());
                            registerBookingItemLodgerDomain.setDateService(itineraryItemEditResult.getItineraryDate().getItineraryDate());

                            registerBookingItemLodgerDomain.setCantPax(
                                    (itineraryItemEditResult.getPaxAdultTotal() != null ?  itineraryItemEditResult.getPaxAdultTotal() : 0)
                                            + (itineraryItemEditResult.getPaxChildrenTotal() != null ?  itineraryItemEditResult.getPaxChildrenTotal() : 0)
                            );
                            registerBookingItemLodgerDomain.setCantPaxAdult(itineraryItemEditResult.getPaxAdultTotal() != null ?  itineraryItemEditResult.getPaxAdultTotal() : 0);
                            registerBookingItemLodgerDomain.setCantPaxChildren(itineraryItemEditResult.getPaxChildrenTotal() != null ?  itineraryItemEditResult.getPaxChildrenTotal() : 0);
                            registerBookingItemLodgerDomain.setTc(itineraryItemEditResult.getPaxTcTotal() != null ?  itineraryItemEditResult.getPaxTcTotal() : 0);
                            registerBookingItemLodgerDomain.setTcLocal(itineraryItemEditResult.getPaxTcLocalTotal() != null ?  itineraryItemEditResult.getPaxTcLocalTotal() : 0);
                            registerBookingItemLodgerDomain.setGuide(itineraryItemEditResult.getPaxGuideTotal() != null ?  itineraryItemEditResult.getPaxGuideTotal() : 0);

                            registerBookingItemLodgerDomain.setTenant(UserResolver.resolveUser().getTenant());
                            RegisterBookingItemLodgerDomain editDomainRegisterBookingItemOperator = registerBookingItemLodgerRepo.edit(registerBookingItemLodgerDomain);
                            itineraryItemRepo.updateCustomerByIdSimpleDomainCaseUpdateRegisterBookingItemOperator(itineraryItemEditResult.getId(), editDomainRegisterBookingItemOperator.getId());

                        }
                    }

                }

            }
        }
    }

    private void updateItineraryCaseOrderServiceTypeBooking(OrderServiceItemDomain orderServiceItemDomain){
        ItineraryItemDomain editedDomain = itineraryItemRepo.searchRegisterBookingItemOperator(orderServiceItemDomain.getRegisterBookingItemOperator().getId());
        if (editedDomain != null){
            LocalDateTime localDateTime = LocalDateTime.of(
                    orderServiceItemDomain.getRegisterBookingItemOperator().getDateService().getYear(),
                    orderServiceItemDomain.getRegisterBookingItemOperator().getDateService().getMonth(),
                    orderServiceItemDomain.getRegisterBookingItemOperator().getDateService().getDayOfMonth(),
                    orderServiceItemDomain.getDateTimeService().getHour(),
                    orderServiceItemDomain.getDateTimeService().getMinute(),
                    orderServiceItemDomain.getDateTimeService().getSecond()
            );
            editedDomain.setItineraryDateTime(localDateTime);
            editedDomain.setOrderServiceItem(orderServiceItemDomain);
            editedDomain.setGuideTransport(orderServiceItemDomain.getDrive());
            editedDomain.setGuideTransferDrive(orderServiceItemDomain.getGuideTransfer());

            editedDomain.setObservation(orderServiceItemDomain.getObservation());
            itineraryItemRepo.edit(editedDomain);

            RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = registerBookingItemOperatorRepo.findById(orderServiceItemDomain.getRegisterBookingItemOperator().getId());

            if (registerBookingItemOperatorDomain != null){
                registerBookingItemOperatorDomain.setDateTime(localDateTime);
                registerBookingItemOperatorDomain.setObservation(orderServiceItemDomain.getObservation());

                registerBookingItemOperatorRepo.edit(registerBookingItemOperatorDomain);
            }
        }
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
        List<RegisterBookingItemOperatorDomain> list = registerBookingItemOperatorRepo.searchAllByRegisterBookingSimpleDomain(idRegisterBooking).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(RegisterBookingItemOperatorDomain::getSequence));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getSequence() + 1;
        }else{
            index = 1;
        }
        return index;
    }

    private Integer getLastIndexBookingLodgerItem(String idRegisterBooking) {
        Integer index = 0;
        List<RegisterBookingItemLodgerDomain> list = registerBookingItemLodgerRepo.searchAllByRegisterBookingSimpleDomain(idRegisterBooking).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(RegisterBookingItemLodgerDomain::getSequence));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getSequence() + 1;
        }else{
            index = 1;
        }
        return index;
    }
}
