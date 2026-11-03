package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderservice;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.requestorderservice.RequestOrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceConverter implements Converter<OrderServiceDomain, OrderServiceEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SupplierOperatorConverter supplierOperatorConverter;

    @Autowired
    private SupplierGuideConverter supplierGuideConverter;

    @Autowired
    private SupplierTransportConverter supplierTransportConverter;

    @Autowired
    private SupplierLodgerConverter supplierLodgerConverter;

    @Autowired
    private SupplierLodgingConverter supplierLodgingConverter;

    @Autowired
    private SupplierGlampingConverter supplierGlampingConverter;

    @Autowired
    private RequestOrderServiceConverter requestOrderServiceConverter;

    @Autowired
    private RegisterBookingOperatorConverter registerBookingOperatorConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private GuideConverter guideConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Override
    public OrderServiceDomain toDomain(OrderServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        OrderServiceDomain orderServiceDomain = new OrderServiceDomain();

        orderServiceDomain.setId( source.getId() );
        orderServiceDomain.setDeleted( source.getDeleted() );
        orderServiceDomain.setEnabled( source.getEnabled() );
        orderServiceDomain.setMigrated( source.getMigrated() );
        orderServiceDomain.setCreatedAt( source.getCreatedAt() );
        orderServiceDomain.setUpdatedAt( source.getUpdatedAt() );
        orderServiceDomain.setDeletedAt( source.getDeletedAt() );
        orderServiceDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        orderServiceDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        orderServiceDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        orderServiceDomain.setIdentifierName( source.getIdentifierName() );
        orderServiceDomain.setTenant( source.getTenant() );
        orderServiceDomain.setName( source.getName() );
        orderServiceDomain.setDescription( source.getDescription() );
        orderServiceDomain.setIdentify( source.getIdentify() );

        orderServiceDomain.setArriveAir( source.getArriveAir() );
        orderServiceDomain.setDepartureAir( source.getDepartureAir() );
        orderServiceDomain.setLodging( source.getLodging() );

        orderServiceDomain.setCantPax( source.getCantPax() );
        orderServiceDomain.setObservation( source.getObservation() );
        orderServiceDomain.setDateOrderService( source.getDateOrderService() );
        orderServiceDomain.setDateService( source.getDateService() );
        orderServiceDomain.setDateTimeService( source.getDateTimeService() );
        orderServiceDomain.setActiveOrderService( source.getActiveOrderService() );

        orderServiceDomain.setOrderServiceState(source.getOrderServiceState() );
        orderServiceDomain.setOrderServiceType(source.getOrderServiceType() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            orderServiceDomain.setSupplierOperator( supplierOperatorConverter.toDomain( source.getSupplierOperator() )  );
        }else{
            orderServiceDomain.setSupplierOperator( null );
        }

        if (source.getRequestOrderService() != null &&
                source.getRequestOrderService().getId() != null &&
                !source.getRequestOrderService().getId().isEmpty()) {
            orderServiceDomain.setRequestOrderService( requestOrderServiceConverter.toDomain( source.getRequestOrderService() )  );
        }else{
            orderServiceDomain.setRequestOrderService( null );
        }

        if (source.getRegisterBookingOperator() != null &&
                source.getRegisterBookingOperator().getId() != null &&
                !source.getRegisterBookingOperator().getId().isEmpty()) {
            orderServiceDomain.setRegisterBookingOperator( registerBookingOperatorConverter.toDomain( source.getRegisterBookingOperator() )  );
        }else{
            orderServiceDomain.setRegisterBookingOperator( null );
        }

        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            orderServiceDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() )  );
        }else{
            orderServiceDomain.setRegisterGroup( null );
        }

        if (source.getSupplierGuide() != null &&
                source.getSupplierGuide().getId() != null &&
                !source.getSupplierGuide().getId().isEmpty()) {
            orderServiceDomain.setSupplierGuide( supplierGuideConverter.toDomain( source.getSupplierGuide() )  );
        }else{
            orderServiceDomain.setSupplierGuide( null );
        }

        if (source.getSupplierTransport() != null &&
                source.getSupplierTransport().getId() != null &&
                !source.getSupplierTransport().getId().isEmpty()) {
            orderServiceDomain.setSupplierTransport( supplierTransportConverter.toDomain( source.getSupplierTransport() )  );
        }else{
            orderServiceDomain.setSupplierTransport( null );
        }

        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()) {
            orderServiceDomain.setSupplierLodging( supplierLodgingConverter.toDomain( source.getSupplierLodging() )  );
        }else{
            orderServiceDomain.setSupplierLodging( null );
        }

        if (source.getSupplierLodger() != null &&
                source.getSupplierLodger().getId() != null &&
                !source.getSupplierLodger().getId().isEmpty()) {
            orderServiceDomain.setSupplierLodger( supplierLodgerConverter.toDomain( source.getSupplierLodger() )  );
        }else{
            orderServiceDomain.setSupplierLodger( null );
        }

        if (source.getSupplierGlamping() != null &&
                source.getSupplierGlamping().getId() != null &&
                !source.getSupplierGlamping().getId().isEmpty()) {
            orderServiceDomain.setSupplierGlamping( supplierGlampingConverter.toDomain( source.getSupplierGlamping() )  );
        }else{
            orderServiceDomain.setSupplierGlamping( null );
        }

        if (source.getResponsible() != null &&
                source.getResponsible().getId() != null &&
                !source.getResponsible().getId().isEmpty()) {
            orderServiceDomain.setResponsible( employeeConverter.toDomain( source.getResponsible() )  );
        }else{
            orderServiceDomain.setResponsible( null );
        }

        if (source.getDrive() != null &&
                source.getDrive().getId() != null &&
                !source.getDrive().getId().isEmpty()) {
            orderServiceDomain.setDrive( guideConverter.toDomain( source.getDrive() )  );
        }else{
            orderServiceDomain.setDrive( null );
        }

        if (source.getGuideTransfer() != null &&
                source.getGuideTransfer().getId() != null &&
                !source.getGuideTransfer().getId().isEmpty()) {
            orderServiceDomain.setGuideTransfer( guideConverter.toDomain( source.getGuideTransfer() )  );
        }else{
            orderServiceDomain.setGuideTransfer( null );
        }

        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            orderServiceDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() )  );
        }else{
            orderServiceDomain.setSupplier( null );
        }

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            orderServiceDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() )  );
        }else{
            orderServiceDomain.setSupplierType( null );
        }

        return orderServiceDomain;
    }

    @Override
    public OrderServiceEntity toEntity(OrderServiceDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        OrderServiceEntity orderServiceEntity = new OrderServiceEntity();

        orderServiceEntity.setId( source.getId() );
        orderServiceEntity.setDeleted( source.getDeleted() );
        orderServiceEntity.setEnabled( source.getEnabled() );
        orderServiceEntity.setMigrated( source.getMigrated() );
        orderServiceEntity.setCreatedAt( source.getCreatedAt() );
        orderServiceEntity.setUpdatedAt( source.getUpdatedAt() );
        orderServiceEntity.setDeletedAt( source.getDeletedAt() );
        orderServiceEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        orderServiceEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        orderServiceEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        orderServiceEntity.setIdentifierName( source.getIdentifierName() );
        orderServiceEntity.setTenant( source.getTenant() );
        orderServiceEntity.setName( source.getName() );
        orderServiceEntity.setDescription( source.getDescription() );
        orderServiceEntity.setIdentify( source.getIdentify() );

        orderServiceEntity.setArriveAir( source.getArriveAir() );
        orderServiceEntity.setDepartureAir( source.getDepartureAir() );
        orderServiceEntity.setLodging( source.getLodging() );

        orderServiceEntity.setCantPax( source.getCantPax() );
        orderServiceEntity.setObservation( source.getObservation() );
        orderServiceEntity.setDateOrderService( source.getDateOrderService() );
        orderServiceEntity.setDateService( source.getDateService() );
        orderServiceEntity.setDateTimeService( source.getDateTimeService() );
        orderServiceEntity.setActiveOrderService( source.getActiveOrderService() );

        orderServiceEntity.setOrderServiceState(source.getOrderServiceState() );
        orderServiceEntity.setOrderServiceType(source.getOrderServiceType() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            orderServiceEntity.setSupplierOperator( supplierOperatorConverter.toEntity( source.getSupplierOperator() )  );
        }else{
            orderServiceEntity.setSupplierOperator( null );
        }

        if (source.getRequestOrderService() != null &&
                source.getRequestOrderService().getId() != null &&
                !source.getRequestOrderService().getId().isEmpty()) {
            orderServiceEntity.setRequestOrderService( requestOrderServiceConverter.toEntity( source.getRequestOrderService() )  );
        }else{
            orderServiceEntity.setRequestOrderService( null );
        }

        if (source.getRegisterBookingOperator() != null &&
                source.getRegisterBookingOperator().getId() != null &&
                !source.getRegisterBookingOperator().getId().isEmpty()) {
            orderServiceEntity.setRegisterBookingOperator( registerBookingOperatorConverter.toEntity( source.getRegisterBookingOperator() )  );
        }else{
            orderServiceEntity.setRegisterBookingOperator( null );
        }

        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            orderServiceEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() )  );
        }else{
            orderServiceEntity.setRegisterGroup( null );
        }

        if (source.getSupplierGuide() != null &&
                source.getSupplierGuide().getId() != null &&
                !source.getSupplierGuide().getId().isEmpty()) {
            orderServiceEntity.setSupplierGuide( supplierGuideConverter.toEntity( source.getSupplierGuide() )  );
        }else{
            orderServiceEntity.setSupplierGuide( null );
        }

        if (source.getSupplierTransport() != null &&
                source.getSupplierTransport().getId() != null &&
                !source.getSupplierTransport().getId().isEmpty()) {
            orderServiceEntity.setSupplierTransport( supplierTransportConverter.toEntity( source.getSupplierTransport() )  );
        }else{
            orderServiceEntity.setSupplierTransport( null );
        }

        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()) {
            orderServiceEntity.setSupplierLodging( supplierLodgingConverter.toEntity( source.getSupplierLodging() )  );
        }else{
            orderServiceEntity.setSupplierLodging( null );
        }

        if (source.getSupplierLodger() != null &&
                source.getSupplierLodger().getId() != null &&
                !source.getSupplierLodger().getId().isEmpty()) {
            orderServiceEntity.setSupplierLodger( supplierLodgerConverter.toEntity( source.getSupplierLodger() )  );
        }else{
            orderServiceEntity.setSupplierLodger( null );
        }

        if (source.getSupplierGlamping() != null &&
                source.getSupplierGlamping().getId() != null &&
                !source.getSupplierGlamping().getId().isEmpty()) {
            orderServiceEntity.setSupplierGlamping( supplierGlampingConverter.toEntity( source.getSupplierGlamping() )  );
        }else{
            orderServiceEntity.setSupplierGlamping( null );
        }

        if (source.getResponsible() != null &&
                source.getResponsible().getId() != null &&
                !source.getResponsible().getId().isEmpty()) {
            orderServiceEntity.setResponsible( employeeConverter.toEntity( source.getResponsible() )  );
        }else{
            orderServiceEntity.setResponsible( null );
        }

        if (source.getDrive() != null &&
                source.getDrive().getId() != null &&
                !source.getDrive().getId().isEmpty()) {
            orderServiceEntity.setDrive( guideConverter.toEntity( source.getDrive() )  );
        }else{
            orderServiceEntity.setDrive( null );
        }

        if (source.getGuideTransfer() != null &&
                source.getGuideTransfer().getId() != null &&
                !source.getGuideTransfer().getId().isEmpty()) {
            orderServiceEntity.setGuideTransfer( guideConverter.toEntity( source.getGuideTransfer() )  );
        }else{
            orderServiceEntity.setGuideTransfer( null );
        }

        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            orderServiceEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() )  );
        }else{
            orderServiceEntity.setSupplier( null );
        }

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            orderServiceEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() )  );
        }else{
            orderServiceEntity.setSupplierType( null );
        }

        return orderServiceEntity;
    }

    public IdBaseDomain toDomainIdBase(OrderServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(OrderServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<OrderServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<OrderServiceDomain> toDomainAllSimpleToDomain(List<OrderServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceDomain orderServiceDomain = new OrderServiceDomain();
            orderServiceDomain.setId(entity.getId());
            return orderServiceDomain;
        }).toList();
    }

    public List<OrderServiceDomain> toDomainAllSimpleToDomainCaseListOrderService(List<OrderServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceDomain orderServiceDomain = new OrderServiceDomain();
            orderServiceDomain.setId(entity.getId());
            orderServiceDomain.setIdentify(entity.getIdentify());
            orderServiceDomain.setName(entity.getName());
            orderServiceDomain.setDateOrderService(entity.getDateOrderService());
            orderServiceDomain.setObservation(entity.getObservation());
            orderServiceDomain.setOrderServiceType(entity.getOrderServiceType());
            orderServiceDomain.setOrderServiceState(entity.getOrderServiceState());
            orderServiceDomain.setCantPax(entity.getCantPax());
            if (entity.getGuideTransfer() != null &&
                    entity.getGuideTransfer().getId() != null &&
                    !entity.getGuideTransfer().getId().isEmpty()){
                orderServiceDomain.setGuideTransfer(new GuideDomain());
                orderServiceDomain.getGuideTransfer().setId(entity.getGuideTransfer().getId());
                if (entity.getGuideTransfer().getPerson() != null &&
                        entity.getGuideTransfer().getPerson().getId() != null &&
                        !entity.getGuideTransfer().getPerson().getId().isEmpty()){
                    orderServiceDomain.getGuideTransfer().setPerson(new PersonDomain());
                    orderServiceDomain.getGuideTransfer().getPerson().setId(entity.getGuideTransfer().getPerson().getId());
                    orderServiceDomain.getGuideTransfer().getPerson().setFirstName(entity.getGuideTransfer().getPerson().getFirstName());
                    orderServiceDomain.getGuideTransfer().getPerson().setLastName(entity.getGuideTransfer().getPerson().getLastName());
                }
            }
            if (entity.getDrive() != null &&
                    entity.getDrive().getId() != null &&
                    !entity.getDrive().getId().isEmpty()){
                orderServiceDomain.setDrive(new GuideDomain());
                orderServiceDomain.getDrive().setId(entity.getDrive().getId());
                if (entity.getDrive().getPerson() != null &&
                        entity.getDrive().getPerson().getId() != null &&
                        !entity.getDrive().getPerson().getId().isEmpty()){
                    orderServiceDomain.getDrive().setPerson(new PersonDomain());
                    orderServiceDomain.getDrive().getPerson().setId(entity.getDrive().getPerson().getId());
                    orderServiceDomain.getDrive().getPerson().setFirstName(entity.getDrive().getPerson().getFirstName());
                    orderServiceDomain.getDrive().getPerson().setLastName(entity.getDrive().getPerson().getLastName());
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                orderServiceDomain.setRegisterGroup(new RegisterGroupDomain());
                orderServiceDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                orderServiceDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                orderServiceDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            if (entity.getRequestOrderService() != null &&
                    entity.getRequestOrderService().getId() != null &&
                    !entity.getRequestOrderService().getId().isEmpty()){
                orderServiceDomain.setRequestOrderService(new RequestOrderServiceDomain());
                orderServiceDomain.getRequestOrderService().setId(entity.getRequestOrderService().getId());
                orderServiceDomain.getRequestOrderService().setIdentify(entity.getRequestOrderService().getIdentify());
            }
            return orderServiceDomain;
        }).toList();
    }

    public PageDTO<OrderServiceDomain> toDomainPageCaseListOrderService(Page<OrderServiceEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<OrderServiceDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListOrderService(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<OrderServiceDomain> toDomainAllSimpleToDomainCaseShowOrderServiceByDelete(List<OrderServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceDomain orderServiceDomain = new OrderServiceDomain();
            orderServiceDomain.setId(entity.getId());
            orderServiceDomain.setOrderServiceType(entity.getOrderServiceType());
            return orderServiceDomain;
        }).toList();
    }
}
