package com.saas.tourismagency.admin.backoffice.usecase.operation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderservice.OrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation.SetFieldDefaultOrderService;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger.RegisterBookingItemLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.RegisterBookingItemOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.GuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice.OrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderservice.PersistOrderServiceNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.OrderServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.RequestOrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderservice.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.orderserviceitem.OrderServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.operation.orderservice.JROrderServiceOperationRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.operation.orderservice.JROrderServiceRepository;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.GeneratedRegisterBookingOperatorItemEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.operation.OrderServiceItemUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.operation.OrderServiceUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceUseCaseImpl implements OrderServiceUseCase {

    @Autowired
    private OrderServiceRepo repo;

    @Autowired
    private RequestOrderServiceRepo requestOrderServiceRepo;

    @Autowired
    private RegisterBookingOperatorRepo registerBookingOperatorRepo;

    @Autowired
    private RegisterBookingItemOperatorRepo registerBookingItemOperatorRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

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
    private EmployeeRepo employeeRepo;

    @Autowired
    private GuideRepo guideRepo;

    @Autowired
    private OrderServiceItemRepo orderServiceItemRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private OrderServiceItemUseCase orderServiceItemUseCase;

    @Autowired
    private OrderServiceConverter converter;

    @Autowired
    private SetFieldDefaultOrderService setFieldDefault;

    @Autowired
    PersistOrderServiceNumberCallbackImpl persistOrderServiceNumberCallback;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JROrderServiceRepository jrOrderServiceRepository;

    @Autowired
    private JROrderServiceOperationRepository jrOrderServiceOperationRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public OrderServiceResponse create(OrderServiceCreateRequest request) {

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

        EmployeeDomain responsibleDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain responsibleIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            responsibleDomain = new EmployeeDomain();
            responsibleDomain.setId(responsibleIdBaseDomain.getId());
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

        OrderServiceDomain newDomain = request.to(
                    requestOrderServiceDomain,
                    registerBookingOperatorDomain,
                    supplierOperatorDomain,
                    registerGroupDomain,
                    supplierGuideDomain,
                    supplierTransportDomain,
                    supplierLodgingDomain,
                    supplierLodgerDomain,
                    supplierGlampingDomain,
                    responsibleDomain,
                    driveDomain,
                    guideTransferDomain,
                    supplier,
                    supplierType
                );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistOrderServiceNumberCallback.generateNumber());
        newDomain.setOrderServiceState(OrderServiceStateEnum.DRAFT.getName());
        OrderServiceDomain createdDomain = repo.create(newDomain);
        return OrderServiceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public OrderServiceResponse edit(String id, OrderServiceUpdateRequest request) {
        if (id == null) {
            return null;
        }
        OrderServiceDomain old = repo.findById(id);
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

        EmployeeDomain responsibleDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain responsibleIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            responsibleDomain = new EmployeeDomain();
            responsibleDomain.setId(responsibleIdBaseDomain.getId());
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

        if (old.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){

            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            supplierType = new SupplierTypeDomain();
            supplierType.setId(supplierTypeDomainOperator.getId());

            if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
                IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
                supplier = new SupplierDomain();
                supplier.setId(supplierIdBaseDomain.getId());
            }

        }else if (old.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){

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

        OrderServiceDomain orderServiceDomain = request.to(
                requestOrderServiceDomain,
                registerBookingOperatorDomain,
                supplierOperatorDomain,
                registerGroupDomain,
                supplierGuideDomain,
                supplierTransportDomain,
                supplierLodgingDomain,
                supplierLodgerDomain,
                supplierGlampingDomain,
                responsibleDomain,
                driveDomain,
                guideTransferDomain,
                supplier,
                supplierType
        );
        orderServiceDomain.setIdentify(old.getIdentify());
        orderServiceDomain.setOrderServiceState(old.getOrderServiceState());
        orderServiceDomain.setOrderServiceType(old.getOrderServiceType());
        OrderServiceDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(orderServiceDomain, old));

        if (old.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderService(id);
            listOrderServiceItems = listOrderServiceItems.stream().collect(Collectors.toList());
            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for(OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems ){

                    orderServiceItemDomain.setSupplierLodging(supplierLodgingDomain);
                    orderServiceItemDomain.setGuideTransfer(guideTransferDomain);
                    orderServiceItemDomain.setDrive(driveDomain);

                    orderServiceItemRepo.edit(orderServiceItemDomain);

                    ItineraryItemDomain editedItineraryItemDomain = itineraryItemRepo.searchRegisterBookingItemOperator(orderServiceItemDomain.getRegisterBookingItemOperator().getId());
                    if (editedItineraryItemDomain != null){
                        editedItineraryItemDomain.setOrderServiceItem(orderServiceItemDomain);
                        editedItineraryItemDomain.setGuideTransport(orderServiceItemDomain.getDrive());
                        editedItineraryItemDomain.setGuideTransferDrive(orderServiceItemDomain.getGuideTransfer());
                        itineraryItemRepo.edit(editedItineraryItemDomain);
                    }
                }
            }
        }

        return OrderServiceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        OrderServiceResponse old = findById(id);

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);

        List<OrderServiceItemDomain> detailsByDelete = orderServiceItemRepo.searchAllByOrderServiceCaseListSimpleByDelete(id);
        if (detailsByDelete != null && !detailsByDelete.isEmpty()){
            for (OrderServiceItemDomain orderServiceItemDomain : detailsByDelete){
                if (old.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
                    ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchOrderServiceItem(orderServiceItemDomain.getId());
                    if (itineraryItemDomain != null){
                        itineraryItemDomain.setGuideTransport(null);
                        itineraryItemDomain.setGuideTransferDrive(null);
                        itineraryItemDomain.setOrderServiceItem(null);
                        itineraryItemRepo.edit(itineraryItemDomain);
                    }
                }else if(old.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
                    ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.searchOrderServiceItem(orderServiceItemDomain.getId());
                    if (itineraryItemDomain != null){
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
                orderServiceItemRepo.deleteById(orderServiceItemDomain.getId());
            }
        }
        repo.deleteById(old.getId());
        if (old.getRequestOrderService() != null &&
                old.getRequestOrderService().getId() != null &&
                !old.getRequestOrderService().getId().isEmpty()){

            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(old.getRequestOrderService().getId());
            RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findById(old.getRequestOrderService().getId());
            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                requestOrderServiceDomain.setRequestOrderServiceState(RequestOrderServiceStateEnum.IN_PROGRESS.getName());
            }else{
                requestOrderServiceDomain.setRequestOrderServiceState(RequestOrderServiceStateEnum.DRAFT.getName());
            }
            requestOrderServiceRepo.edit(requestOrderServiceDomain);

        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceResponse> findAll() {
        return repo.findAll(false).stream().map(orderServiceDomain -> OrderServiceResponse.from(orderServiceDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceResponse findById(String id) {
        if (id == null) {
            return null;
        }
        OrderServiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return OrderServiceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        OrderServiceDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No entity with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceSearchResponse searchAll(OrderServiceSearchRequest bankSearchRequest) {
       PageDTO<OrderServiceDomain> pageDTO = repo.searchAll(bankSearchRequest);
       OrderServiceSearchResponse bankSearchRespose = new OrderServiceSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  OrderServiceResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceSearchResponse searchFilterAll(OrderServiceSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<OrderServiceDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        OrderServiceSearchResponse bankSearchRespose = new OrderServiceSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  OrderServiceResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceItemResponse> findAllOrderServiceItemByOrderService(String idOrderService) {
        if (idOrderService == null) {
            return null;
        }
        IdBaseDomain orderServiceIdBaseDomain = repo.findByIdEntity(idOrderService);
        if (orderServiceIdBaseDomain == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(idOrderService));
        }
        return orderServiceItemUseCase.searchAllByOrderService(idOrderService);
    }

    @Override
    @Transactional
    public OrderServiceItemResponse addOrderServiceItem(OrderServiceItemCreateRequest request, String idOrderService) {
        if (idOrderService == null) {
            return null;
        }
        IdBaseDomain orderServiceIdBaseDomain = repo.findByIdEntity(idOrderService);
        if (orderServiceIdBaseDomain == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(idOrderService));
        }
        OrderServiceDomain orderServiceDomain = new OrderServiceDomain();
        orderServiceDomain.setId(orderServiceIdBaseDomain.getId());

        OrderServiceItemResponse result = orderServiceItemUseCase.create(request, orderServiceDomain);

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            // Verify by Change Status

            List<RegisterBookingItemOperatorDomain> listRegisterBookingItems = registerBookingItemOperatorRepo.searchAllByBookingSimpleDomainCaseOperationAvailable(request.getRegisterBookingOperator());
            listRegisterBookingItems = listRegisterBookingItems.stream().
                    filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getGeneratedBy().equals(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName())).
                    filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getService().getWithoutCost() == null || !registerBookingItemOperatorDomain.getService().getWithoutCost()).collect(Collectors.toList());
            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(request.getRequestOrderService());
            listOrderServiceItems = listOrderServiceItems.stream().collect(Collectors.toList());
            List<RegisterBookingItemOperatorDomain> listReferenceDelete = new ArrayList<>();

            Integer cantPaxTotal = 0;

            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for (OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems){
                    Integer cantPaxTotalInter = (orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxAdult() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxAdult() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxChildren() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxChildren() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getTc() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getTc() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getTcLocal() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getTcLocal() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getGuide() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getGuide() : 0 );
                    if (cantPaxTotalInter > cantPaxTotal){
                        cantPaxTotal = cantPaxTotalInter;
                    }
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
                requestOrderServiceRepo.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(request.getRequestOrderService(), RequestOrderServiceStateEnum.IN_PROGRESS.getName());
            }else{
                requestOrderServiceRepo.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(request.getRequestOrderService(), RequestOrderServiceStateEnum.FINISHED.getName());
            }

            repo.updateOrderServiceCantPaxByIdCaseUpdateItem(idOrderService, cantPaxTotal);

        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){

            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(request.getRequestOrderService());

            Integer cantPaxTotal = 0;

            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for (OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems){
                    Integer cantPaxTotalInter = (orderServiceItemDomain.getCantPaxAdult() != null ? orderServiceItemDomain.getCantPaxAdult() : 0 )
                            +  (orderServiceItemDomain.getCantPaxChildren() != null ? orderServiceItemDomain.getCantPaxChildren() : 0 )
                            +  (orderServiceItemDomain.getTc() != null ? orderServiceItemDomain.getTc() : 0 )
                            +  (orderServiceItemDomain.getTcLocal() != null ? orderServiceItemDomain.getTcLocal() : 0 )
                            +  (orderServiceItemDomain.getGuide() != null ? orderServiceItemDomain.getGuide() : 0 );
                    if (cantPaxTotalInter > cantPaxTotal){
                        cantPaxTotal = cantPaxTotalInter;
                    }
                }
            }

            repo.updateOrderServiceCantPaxByIdCaseUpdateItem(idOrderService, cantPaxTotal);
        }
        return result;
    }

    @Override
    @Transactional
    public OrderServiceItemResponse editOrderServiceItem(String id, OrderServiceItemUpdateRequest request, String idOrderService) {
        if (idOrderService == null) {
            return null;
        }
        IdBaseDomain orderServiceIdBaseDomain = repo.findByIdEntity(idOrderService);
        if (orderServiceIdBaseDomain == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(idOrderService));
        }
        OrderServiceDomain orderServiceDomain = new OrderServiceDomain();
        orderServiceDomain.setId(orderServiceIdBaseDomain.getId());

        OrderServiceItemResponse result = orderServiceItemUseCase.edit(id, request, orderServiceDomain);

        List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(request.getRequestOrderService());

        Integer cantPaxTotal = 0;

        if (request.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for (OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems){
                    Integer cantPaxTotalInter = (orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxAdult() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxAdult() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxChildren() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxChildren() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getTc() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getTc() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getTcLocal() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getTcLocal() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getGuide() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getGuide() : 0 );
                    if (cantPaxTotalInter > cantPaxTotal){
                        cantPaxTotal = cantPaxTotalInter;
                    }
                }
            }
        }else if (request.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){
            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for (OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems){
                    Integer cantPaxTotalInter = (orderServiceItemDomain.getCantPaxAdult() != null ? orderServiceItemDomain.getCantPaxAdult() : 0 )
                            +  (orderServiceItemDomain.getCantPaxChildren() != null ? orderServiceItemDomain.getCantPaxChildren() : 0 )
                            +  (orderServiceItemDomain.getTc() != null ? orderServiceItemDomain.getTc() : 0 )
                            +  (orderServiceItemDomain.getTcLocal() != null ? orderServiceItemDomain.getTcLocal() : 0 )
                            +  (orderServiceItemDomain.getGuide() != null ? orderServiceItemDomain.getGuide() : 0 );
                    if (cantPaxTotalInter > cantPaxTotal){
                        cantPaxTotal = cantPaxTotalInter;
                    }
                }
            }
        }

        repo.updateOrderServiceCantPaxByIdCaseUpdateItem(idOrderService, cantPaxTotal);

        return result;
    }

    @Override
    @Transactional
    public void deleteOrderServiceItem(String id, String idOrderService) {
        OrderServiceResponse old = findById(idOrderService);
        orderServiceItemUseCase.deleteById(id);

        Integer cantPaxTotal = 0;

        if (old.getOrderServiceType().equals(OrderServiceTypeEnum.BOOKING.getName())){
            // Verify by Change Status

            List<RegisterBookingItemOperatorDomain> listRegisterBookingItems = registerBookingItemOperatorRepo.searchAllByBookingSimpleDomainCaseOperationAvailable(old.getRegisterBookingOperator().getId());
            listRegisterBookingItems = listRegisterBookingItems.stream().
                    filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getGeneratedBy().equals(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName())).
                    filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getService().getWithoutCost() == null || !registerBookingItemOperatorDomain.getService().getWithoutCost()).collect(Collectors.toList());
            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(old.getRequestOrderService().getId());
            listOrderServiceItems = listOrderServiceItems.stream().collect(Collectors.toList());
            List<RegisterBookingItemOperatorDomain> listReferenceDelete = new ArrayList<>();

            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for (OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems){
                    Integer cantPaxTotalInter = (orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxAdult() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxAdult() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxChildren() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getCantPaxChildren() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getTc() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getTc() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getTcLocal() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getTcLocal() : 0 )
                            +  (orderServiceItemDomain.getRegisterBookingItemOperator().getGuide() != null ? orderServiceItemDomain.getRegisterBookingItemOperator().getGuide() : 0 );
                    if (cantPaxTotalInter > cantPaxTotal){
                        cantPaxTotal = cantPaxTotalInter;
                    }
                    if (listRegisterBookingItems != null && !listRegisterBookingItems.isEmpty()){
                        for (RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain : listRegisterBookingItems){
                            if (orderServiceItemDomain.getRegisterBookingItemOperator().getId().equals(registerBookingItemOperatorDomain.getId())){
                                listReferenceDelete.add(registerBookingItemOperatorDomain);
                                break;
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
                    requestOrderServiceRepo.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(old.getRequestOrderService().getId(), RequestOrderServiceStateEnum.IN_PROGRESS.getName());
                }else{
                    requestOrderServiceRepo.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(old.getRequestOrderService().getId(), RequestOrderServiceStateEnum.FINISHED.getName());
                }

            }else{
                requestOrderServiceRepo.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(old.getRequestOrderService().getId(), RequestOrderServiceStateEnum.DRAFT.getName());
            }

            repo.updateOrderServiceCantPaxByIdCaseUpdateItem(idOrderService, cantPaxTotal);

        }else if (old.getOrderServiceType().equals(OrderServiceTypeEnum.OPERATION.getName())){

            List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderService(old.getId());

            if (listOrderServiceItems != null && !listOrderServiceItems.isEmpty()){
                for (OrderServiceItemDomain orderServiceItemDomain : listOrderServiceItems){
                    Integer cantPaxTotalInter = (orderServiceItemDomain.getCantPaxAdult() != null ? orderServiceItemDomain.getCantPaxAdult() : 0 )
                            +  (orderServiceItemDomain.getCantPaxChildren() != null ? orderServiceItemDomain.getCantPaxChildren() : 0 )
                            +  (orderServiceItemDomain.getTc() != null ? orderServiceItemDomain.getTc() : 0 )
                            +  (orderServiceItemDomain.getTcLocal() != null ? orderServiceItemDomain.getTcLocal() : 0 )
                            +  (orderServiceItemDomain.getGuide() != null ? orderServiceItemDomain.getGuide() : 0 );
                    if (cantPaxTotalInter > cantPaxTotal){
                        cantPaxTotal = cantPaxTotalInter;
                    }
                }
            }else{
                cantPaxTotal = old.getRegisterGroup().getTotalPax();
            }
        }

        repo.updateOrderServiceCantPaxByIdCaseUpdateItem(idOrderService, cantPaxTotal);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderServiceItemResponse findByIdAndOrderService(String id, String idOrderService) {
        if (idOrderService == null) {
            return null;
        }
        OrderServiceDomain orderServiceDomain = repo.findById(idOrderService);
        if (orderServiceDomain == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(idOrderService));
        }
        return orderServiceItemUseCase.searchByOrderServiceAndOrderServiceItem(idOrderService, id);
    }

    @Override
    @Transactional
    public OrderServiceResponse changeState(String id, OrderServiceChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        OrderServiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(id));
        }
        old.setOrderServiceState(request.getOrderServiceState());
        OrderServiceDomain editedDomain = repo.edit(old);
        switch (request.getOrderServiceState()) {
            case "Draft" -> {
                _changeStateDraft(old, request);
            }
            case "Confirmed" -> {
                _changeStateConfirmed(old, request);
            }
            case "Cancelled" -> {
                _changeStateCancelled(old, request);
            }
            default -> {

            }
        }
        return OrderServiceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public OrderServiceResponse generate(String idRequestOrderService) {
        if (idRequestOrderService == null) {
            return null;
        }
        RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findById(idRequestOrderService);
        if (requestOrderServiceDomain == null) {
            throw new NotFoundException("No request order service found with id '%s'.".formatted(idRequestOrderService));
        }

        OrderServiceDomain newDomain = new OrderServiceDomain();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistOrderServiceNumberCallback.generateNumber());
        newDomain.setOrderServiceState(OrderServiceStateEnum.DRAFT.getName());
        newDomain.setSupplierOperator(requestOrderServiceDomain.getSupplierOperator());
        newDomain.setRegisterBookingOperator(requestOrderServiceDomain.getRegisterBookingOperator());
        newDomain.setRegisterGroup(requestOrderServiceDomain.getRegisterGroup());
        newDomain.setRequestOrderService(requestOrderServiceDomain);
        OrderServiceDomain createdDomain = repo.create(newDomain);
        return OrderServiceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public List<RegisterBookingItemOperatorResponse> findAllRegisterBookingItemOperatorAvailable(String idBookingOperator, String idOrderService) {
        if (idOrderService == null) {
            return null;
        }
        IdBaseDomain orderServiceIdBaseDomain = repo.findByIdEntity(idOrderService);
        if (orderServiceIdBaseDomain == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(idOrderService));
        }
        if (idBookingOperator == null) {
            return null;
        }
        IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(idBookingOperator);
        if (registerBookingOperatorIdBaseDomain == null) {
            throw new NotFoundException("No register booking operator found with id '%s'.".formatted(idBookingOperator));
        }
        List<RegisterBookingItemOperatorDomain> listRegisterBookingItems = registerBookingItemOperatorRepo.searchAllByBookingSimpleDomainCaseOperationAvailable(idBookingOperator);
        listRegisterBookingItems = listRegisterBookingItems.stream().
                filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getGeneratedBy().equals(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName())).
                filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getService().getWithoutCost() == null || !registerBookingItemOperatorDomain.getService().getWithoutCost()).collect(Collectors.toList());
        List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItems(idOrderService);
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

        return listRegisterBookingItems.stream().map(registerBookingItemOperatorDomain -> RegisterBookingItemOperatorResponse.from(registerBookingItemOperatorDomain)).toList();
    }

    @Override
    @Transactional
    public List<RegisterBookingItemOperatorResponse> findAllRegisterBookingItemOperatorAvailableByRequestOrderService(String idBookingOperator, String idRequestOrderService) {
        if (idRequestOrderService == null) {
            return null;
        }
        IdBaseDomain requestOrderServiceIdBaseDomain = requestOrderServiceRepo.findByIdEntity(idRequestOrderService);
        if (requestOrderServiceIdBaseDomain == null) {
            throw new NotFoundException("No request order service found with id '%s'.".formatted(idRequestOrderService));
        }
        if (idBookingOperator == null) {
            return null;
        }
        IdBaseDomain registerBookingOperatorIdBaseDomain = registerBookingOperatorRepo.findByIdEntity(idBookingOperator);
        if (registerBookingOperatorIdBaseDomain == null) {
            throw new NotFoundException("No register booking operator found with id '%s'.".formatted(idBookingOperator));
        }
        List<RegisterBookingItemOperatorDomain> listRegisterBookingItems = registerBookingItemOperatorRepo.searchAllByBookingSimpleDomainCaseOperationAvailable(idBookingOperator);
        listRegisterBookingItems = listRegisterBookingItems.stream().
                filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getGeneratedBy().equals(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName())).
                filter(registerBookingItemOperatorDomain -> registerBookingItemOperatorDomain.getService().getWithoutCost() == null || !registerBookingItemOperatorDomain.getService().getWithoutCost()).collect(Collectors.toList());
        List<OrderServiceItemDomain> listOrderServiceItems = orderServiceItemRepo.searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(idRequestOrderService);
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

        if (listRegisterBookingItems != null && !listRegisterBookingItems.isEmpty()){
            Collections.sort(listRegisterBookingItems);
        }
        return listRegisterBookingItems.stream().map(registerBookingItemOperatorDomain -> RegisterBookingItemOperatorResponse.from(registerBookingItemOperatorDomain)).toList();
    }

    @Override
    @Transactional
    public byte[] exportOrderServicePdf(String idOrderService) {
        if (idOrderService == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByUser(userDomain.getId());
        if (employeeUserDomain != null){
            parameters.put("activeEmployee", employeeUserDomain.getEmployee());
        }else{
            throw new BadRequestException(MessagesValidation.VALIDATION_EMPLOYEE_USER_NOT_ASSOCIATE);
        }
        OrderServiceDomain orderServiceDomain = repo.findById(idOrderService);
        if (orderServiceDomain == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(idOrderService));
        }

        List<OrderServiceDomain> list = new ArrayList<OrderServiceDomain>();
        list.add(orderServiceDomain);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<OrderServiceItemDomain> orderServiceItemDomains = orderServiceItemRepo.searchAllByOrderService(idOrderService);
        if (orderServiceItemDomains != null && !orderServiceItemDomains.isEmpty()) {
            orderServiceItemDomains = orderServiceItemDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantService", orderServiceItemDomains.size());

        return jrOrderServiceRepository.export(list, orderServiceItemDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportOrderServiceOperationPdf(String idOrderService) {
        if (idOrderService == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByUser(userDomain.getId());
        if (employeeUserDomain != null){
            parameters.put("activeEmployee", employeeUserDomain.getEmployee());
        }else{
            throw new BadRequestException(MessagesValidation.VALIDATION_EMPLOYEE_USER_NOT_ASSOCIATE);
        }
        OrderServiceDomain orderServiceDomain = repo.findById(idOrderService);
        if (orderServiceDomain == null) {
            throw new NotFoundException("No order service found with id '%s'.".formatted(idOrderService));
        }

        List<OrderServiceDomain> list = new ArrayList<OrderServiceDomain>();
        list.add(orderServiceDomain);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<OrderServiceItemDomain> orderServiceItemDomains = orderServiceItemRepo.searchAllByOrderService(idOrderService);
        if (orderServiceItemDomains != null && !orderServiceItemDomains.isEmpty()) {
            orderServiceItemDomains = orderServiceItemDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantService", orderServiceItemDomains.size());

        return jrOrderServiceOperationRepository.export(list, orderServiceItemDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDomain> findAllServiceByRegisterGroup(String registerGroupId) {
        if (registerGroupId == null || registerGroupId.isEmpty()) {
            return List.of();
        }
        List<OrderServiceItemDomain> items = orderServiceItemRepo.searchAllByRegisterGroup(registerGroupId);
        Map<String, ServiceDomain> distinctServices = new LinkedHashMap<>();
        for (OrderServiceItemDomain item : items) {
            if (item.getService() != null && item.getService().getId() != null) {
                distinctServices.putIfAbsent(item.getService().getId(), item.getService());
            }
        }
        return new ArrayList<>(distinctServices.values());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderServiceItemResponse> findAllOrderServiceItemByRegisterGroupAndGuideTransfer(String registerGroupId, String guideTransferId) {
        if (registerGroupId == null || registerGroupId.isEmpty() || guideTransferId == null || guideTransferId.isEmpty()) {
            return List.of();
        }
        List<OrderServiceItemDomain> items = orderServiceItemRepo.searchAllByRegisterGroupAndGuideTransfer(registerGroupId, guideTransferId);
        items = items.stream().collect(Collectors.toList());
        ArrayList<OrderServiceItemDomain> distinctServices = new ArrayList<>();
        for (OrderServiceItemDomain item : items) {
            if (item.getService() != null && item.getService().getTypeTour() != null && item.getService().getTypeTour()){
                distinctServices.add(item);
            }
        }
        return distinctServices.stream().map(OrderServiceItemResponse::from).collect(Collectors.toList());
    }

    private void _changeStateDraft(OrderServiceDomain orderServiceDomain, OrderServiceChangeStateRequest request){

    }

    private void _changeStateConfirmed(OrderServiceDomain orderServiceDomain, OrderServiceChangeStateRequest request){

    }

    private void _changeStateCancelled(OrderServiceDomain orderServiceDomain, OrderServiceChangeStateRequest request){

    }
}
