package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderserviceitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itinerarydate.ItineraryDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemoperator.RegisterBookingItemOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderservice.OrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.requestorderservice.RequestOrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceItemConverter implements Converter<OrderServiceItemDomain, OrderServiceItemEntity> {

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
    private RegisterBookingItemOperatorConverter registerBookingItemOperatorConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private OrderServiceConverter orderServiceConverter;

    @Autowired
    private GuideConverter guideConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private ItineraryDateConverter itineraryDateConverter;

    @Override
    public OrderServiceItemDomain toDomain(OrderServiceItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        OrderServiceItemDomain orderServiceItemDomain = new OrderServiceItemDomain();

        orderServiceItemDomain.setId( source.getId() );
        orderServiceItemDomain.setDeleted( source.getDeleted() );
        orderServiceItemDomain.setEnabled( source.getEnabled() );
        orderServiceItemDomain.setMigrated( source.getMigrated() );
        orderServiceItemDomain.setCreatedAt( source.getCreatedAt() );
        orderServiceItemDomain.setUpdatedAt( source.getUpdatedAt() );
        orderServiceItemDomain.setDeletedAt( source.getDeletedAt() );
        orderServiceItemDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        orderServiceItemDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        orderServiceItemDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        orderServiceItemDomain.setIdentifierName( source.getIdentifierName() );
        orderServiceItemDomain.setTenant( source.getTenant() );
        orderServiceItemDomain.setName( source.getName() );
        orderServiceItemDomain.setDescription( source.getDescription() );
        orderServiceItemDomain.setIdentify( source.getIdentify() );

        orderServiceItemDomain.setDateService( source.getDateService() );
        orderServiceItemDomain.setDateTimeService( source.getDateTimeService() );
        orderServiceItemDomain.setDateTimePax( source.getDateTimePax() );
        orderServiceItemDomain.setDateTimeRecj( source.getDateTimeRecj() );

        orderServiceItemDomain.setObservation( source.getObservation() );
        orderServiceItemDomain.setObservationAditional( source.getObservationAditional() );

        orderServiceItemDomain.setCantPax( source.getCantPax() );
        orderServiceItemDomain.setCantPaxAdult( source.getCantPaxAdult() );
        orderServiceItemDomain.setCantPaxChildren( source.getCantPaxChildren() );
        orderServiceItemDomain.setTc( source.getTc() );
        orderServiceItemDomain.setTcLocal( source.getTcLocal() );
        orderServiceItemDomain.setGuide( source.getGuide() );

        orderServiceItemDomain.setOperatorPvt( source.getOperatorPvt() );
        orderServiceItemDomain.setOperatorSic( source.getOperatorSic() );

        orderServiceItemDomain.setLodgePvt( source.getLodgePvt() );
        orderServiceItemDomain.setLodgeSic( source.getLodgeSic() );

        orderServiceItemDomain.setOrderServiceType( source.getOrderServiceType() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            orderServiceItemDomain.setSupplierOperator( supplierOperatorConverter.toDomain( source.getSupplierOperator() )  );
        }else{
            orderServiceItemDomain.setSupplierOperator( null );
        }

        if (source.getOrderService() != null &&
                source.getOrderService().getId() != null &&
                !source.getOrderService().getId().isEmpty()) {
            orderServiceItemDomain.setOrderService( orderServiceConverter.toDomain( source.getOrderService() )  );
        }else{
            orderServiceItemDomain.setOrderService( null );
        }

        if (source.getRequestOrderService() != null &&
                source.getRequestOrderService().getId() != null &&
                !source.getRequestOrderService().getId().isEmpty()) {
            orderServiceItemDomain.setRequestOrderService( requestOrderServiceConverter.toDomain( source.getRequestOrderService() )  );
        }else{
            orderServiceItemDomain.setRequestOrderService( null );
        }

        if (source.getRegisterBookingOperator() != null &&
                source.getRegisterBookingOperator().getId() != null &&
                !source.getRegisterBookingOperator().getId().isEmpty()) {
            orderServiceItemDomain.setRegisterBookingOperator( registerBookingOperatorConverter.toDomain( source.getRegisterBookingOperator() )  );
        }else{
            orderServiceItemDomain.setRegisterBookingOperator( null );
        }

        if (source.getRegisterBookingItemOperator() != null &&
                source.getRegisterBookingItemOperator().getId() != null &&
                !source.getRegisterBookingItemOperator().getId().isEmpty()) {
            orderServiceItemDomain.setRegisterBookingItemOperator( registerBookingItemOperatorConverter.toDomain( source.getRegisterBookingItemOperator() )  );
        }else{
            orderServiceItemDomain.setRegisterBookingItemOperator( null );
        }

        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            orderServiceItemDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() )  );
        }else{
            orderServiceItemDomain.setRegisterGroup( null );
        }

        if (source.getSupplierGuide() != null &&
                source.getSupplierGuide().getId() != null &&
                !source.getSupplierGuide().getId().isEmpty()) {
            orderServiceItemDomain.setSupplierGuide( supplierGuideConverter.toDomain( source.getSupplierGuide() )  );
        }else{
            orderServiceItemDomain.setSupplierGuide( null );
        }

        if (source.getSupplierTransport() != null &&
                source.getSupplierTransport().getId() != null &&
                !source.getSupplierTransport().getId().isEmpty()) {
            orderServiceItemDomain.setSupplierTransport( supplierTransportConverter.toDomain( source.getSupplierTransport() )  );
        }else{
            orderServiceItemDomain.setSupplierTransport( null );
        }

        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()) {
            orderServiceItemDomain.setSupplierLodging( supplierLodgingConverter.toDomain( source.getSupplierLodging() )  );
        }else{
            orderServiceItemDomain.setSupplierLodging( null );
        }

        if (source.getSupplierLodger() != null &&
                source.getSupplierLodger().getId() != null &&
                !source.getSupplierLodger().getId().isEmpty()) {
            orderServiceItemDomain.setSupplierLodger( supplierLodgerConverter.toDomain( source.getSupplierLodger() )  );
        }else{
            orderServiceItemDomain.setSupplierLodger( null );
        }

        if (source.getSupplierGlamping() != null &&
                source.getSupplierGlamping().getId() != null &&
                !source.getSupplierGlamping().getId().isEmpty()) {
            orderServiceItemDomain.setSupplierGlamping( supplierGlampingConverter.toDomain( source.getSupplierGlamping() )  );
        }else{
            orderServiceItemDomain.setSupplierGlamping( null );
        }

        if (source.getDrive() != null &&
                source.getDrive().getId() != null &&
                !source.getDrive().getId().isEmpty()) {
            orderServiceItemDomain.setDrive( guideConverter.toDomain( source.getDrive() )  );
        }else{
            orderServiceItemDomain.setDrive( null );
        }

        if (source.getGuideTransfer() != null &&
                source.getGuideTransfer().getId() != null &&
                !source.getGuideTransfer().getId().isEmpty()) {
            orderServiceItemDomain.setGuideTransfer( guideConverter.toDomain( source.getGuideTransfer() )  );
        }else{
            orderServiceItemDomain.setGuideTransfer( null );
        }

        if (source.getService() != null &&
                source.getService().getId() != null &&
                !source.getService().getId().isEmpty()) {
            orderServiceItemDomain.setService( serviceConverter.toDomain( source.getService() )  );
        }else{
            orderServiceItemDomain.setService( null );
        }

        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            orderServiceItemDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() )  );
        }else{
            orderServiceItemDomain.setSupplier( null );
        }

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            orderServiceItemDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() )  );
        }else{
            orderServiceItemDomain.setSupplierType( null );
        }

        if (source.getItineraryDate() != null &&
                source.getItineraryDate().getId() != null &&
                !source.getItineraryDate().getId().isEmpty()) {
            orderServiceItemDomain.setItineraryDate( itineraryDateConverter.toDomain( source.getItineraryDate() )  );
        }else{
            orderServiceItemDomain.setItineraryDate( null );
        }

        return orderServiceItemDomain;
    }

    @Override
    public OrderServiceItemEntity toEntity(OrderServiceItemDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        OrderServiceItemEntity orderServiceItemEntity = new OrderServiceItemEntity();

        orderServiceItemEntity.setId( source.getId() );
        orderServiceItemEntity.setDeleted( source.getDeleted() );
        orderServiceItemEntity.setEnabled( source.getEnabled() );
        orderServiceItemEntity.setMigrated( source.getMigrated() );
        orderServiceItemEntity.setCreatedAt( source.getCreatedAt() );
        orderServiceItemEntity.setUpdatedAt( source.getUpdatedAt() );
        orderServiceItemEntity.setDeletedAt( source.getDeletedAt() );
        orderServiceItemEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        orderServiceItemEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        orderServiceItemEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        orderServiceItemEntity.setIdentifierName( source.getIdentifierName() );
        orderServiceItemEntity.setTenant( source.getTenant() );
        orderServiceItemEntity.setName( source.getName() );
        orderServiceItemEntity.setDescription( source.getDescription() );
        orderServiceItemEntity.setIdentify( source.getIdentify() );

        orderServiceItemEntity.setDateService( source.getDateService() );
        orderServiceItemEntity.setDateTimeService( source.getDateTimeService() );
        orderServiceItemEntity.setDateTimePax( source.getDateTimePax() );
        orderServiceItemEntity.setDateTimeRecj( source.getDateTimeRecj() );

        orderServiceItemEntity.setObservation( source.getObservation() );
        orderServiceItemEntity.setObservationAditional( source.getObservationAditional() );

        orderServiceItemEntity.setCantPax( source.getCantPax() );
        orderServiceItemEntity.setCantPaxAdult( source.getCantPaxAdult() );
        orderServiceItemEntity.setCantPaxChildren( source.getCantPaxChildren() );
        orderServiceItemEntity.setTc( source.getTc() );
        orderServiceItemEntity.setTcLocal( source.getTcLocal() );
        orderServiceItemEntity.setGuide( source.getGuide() );

        orderServiceItemEntity.setOperatorPvt( source.getOperatorPvt() );
        orderServiceItemEntity.setOperatorSic( source.getOperatorSic() );

        orderServiceItemEntity.setLodgePvt( source.getLodgePvt() );
        orderServiceItemEntity.setLodgeSic( source.getLodgeSic() );

        orderServiceItemEntity.setOrderServiceType( source.getOrderServiceType() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            orderServiceItemEntity.setSupplierOperator( supplierOperatorConverter.toEntity( source.getSupplierOperator() )  );
        }else{
            orderServiceItemEntity.setSupplierOperator( null );
        }

        if (source.getOrderService() != null &&
                source.getOrderService().getId() != null &&
                !source.getOrderService().getId().isEmpty()) {
            orderServiceItemEntity.setOrderService( orderServiceConverter.toEntity( source.getOrderService() )  );
        }else{
            orderServiceItemEntity.setOrderService( null );
        }

        if (source.getRequestOrderService() != null &&
                source.getRequestOrderService().getId() != null &&
                !source.getRequestOrderService().getId().isEmpty()) {
            orderServiceItemEntity.setRequestOrderService( requestOrderServiceConverter.toEntity( source.getRequestOrderService() )  );
        }else{
            orderServiceItemEntity.setRequestOrderService( null );
        }

        if (source.getRegisterBookingOperator() != null &&
                source.getRegisterBookingOperator().getId() != null &&
                !source.getRegisterBookingOperator().getId().isEmpty()) {
            orderServiceItemEntity.setRegisterBookingOperator( registerBookingOperatorConverter.toEntity( source.getRegisterBookingOperator() )  );
        }else{
            orderServiceItemEntity.setRegisterBookingOperator( null );
        }

        if (source.getRegisterBookingItemOperator() != null &&
                source.getRegisterBookingItemOperator().getId() != null &&
                !source.getRegisterBookingItemOperator().getId().isEmpty()) {
            orderServiceItemEntity.setRegisterBookingItemOperator( registerBookingItemOperatorConverter.toEntity( source.getRegisterBookingItemOperator() )  );
        }else{
            orderServiceItemEntity.setRegisterBookingItemOperator( null );
        }

        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            orderServiceItemEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() )  );
        }else{
            orderServiceItemEntity.setRegisterGroup( null );
        }

        if (source.getSupplierGuide() != null &&
                source.getSupplierGuide().getId() != null &&
                !source.getSupplierGuide().getId().isEmpty()) {
            orderServiceItemEntity.setSupplierGuide( supplierGuideConverter.toEntity( source.getSupplierGuide() )  );
        }else{
            orderServiceItemEntity.setSupplierGuide( null );
        }

        if (source.getSupplierTransport() != null &&
                source.getSupplierTransport().getId() != null &&
                !source.getSupplierTransport().getId().isEmpty()) {
            orderServiceItemEntity.setSupplierTransport( supplierTransportConverter.toEntity( source.getSupplierTransport() )  );
        }else{
            orderServiceItemEntity.setSupplierTransport( null );
        }

        if (source.getSupplierLodging() != null &&
                source.getSupplierLodging().getId() != null &&
                !source.getSupplierLodging().getId().isEmpty()) {
            orderServiceItemEntity.setSupplierLodging( supplierLodgingConverter.toEntity( source.getSupplierLodging() )  );
        }else{
            orderServiceItemEntity.setSupplierLodging( null );
        }

        if (source.getSupplierLodger() != null &&
                source.getSupplierLodger().getId() != null &&
                !source.getSupplierLodger().getId().isEmpty()) {
            orderServiceItemEntity.setSupplierLodger( supplierLodgerConverter.toEntity( source.getSupplierLodger() )  );
        }else{
            orderServiceItemEntity.setSupplierLodger( null );
        }

        if (source.getSupplierGlamping() != null &&
                source.getSupplierGlamping().getId() != null &&
                !source.getSupplierGlamping().getId().isEmpty()) {
            orderServiceItemEntity.setSupplierGlamping( supplierGlampingConverter.toEntity( source.getSupplierGlamping() )  );
        }else{
            orderServiceItemEntity.setSupplierGlamping( null );
        }

        if (source.getDrive() != null &&
                source.getDrive().getId() != null &&
                !source.getDrive().getId().isEmpty()) {
            orderServiceItemEntity.setDrive( guideConverter.toEntity( source.getDrive() )  );
        }else{
            orderServiceItemEntity.setDrive( null );
        }

        if (source.getGuideTransfer() != null &&
                source.getGuideTransfer().getId() != null &&
                !source.getGuideTransfer().getId().isEmpty()) {
            orderServiceItemEntity.setGuideTransfer( guideConverter.toEntity( source.getGuideTransfer() )  );
        }else{
            orderServiceItemEntity.setGuideTransfer( null );
        }

        if (source.getService() != null &&
                source.getService().getId() != null &&
                !source.getService().getId().isEmpty()) {
            orderServiceItemEntity.setService( serviceConverter.toEntity( source.getService() )  );
        }else{
            orderServiceItemEntity.setService( null );
        }

        if (source.getSupplier() != null &&
                source.getSupplier().getId() != null &&
                !source.getSupplier().getId().isEmpty()) {
            orderServiceItemEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() )  );
        }else{
            orderServiceItemEntity.setSupplier( null );
        }

        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            orderServiceItemEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() )  );
        }else{
            orderServiceItemEntity.setSupplierType( null );
        }

        if (source.getItineraryDate() != null &&
                source.getItineraryDate().getId() != null &&
                !source.getItineraryDate().getId().isEmpty()) {
            orderServiceItemEntity.setItineraryDate( itineraryDateConverter.toEntity( source.getItineraryDate() )  );
        }else{
            orderServiceItemEntity.setItineraryDate( null );
        }

        return orderServiceItemEntity;
    }

    public IdBaseDomain toDomainIdBase(OrderServiceItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(OrderServiceItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<OrderServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<OrderServiceItemDomain> toDomainAllSimpleToDomain(List<OrderServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceItemDomain orderServiceItemDomain = new OrderServiceItemDomain();
            orderServiceItemDomain.setId(entity.getId());
            return orderServiceItemDomain;
        }).toList();
    }

    public List<OrderServiceItemDomain> toDomainAllSimpleToDomainCaseListOrderServiceItems(List<OrderServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceItemDomain orderServiceItemDomain = new OrderServiceItemDomain();
            orderServiceItemDomain.setId( entity.getId() );
            orderServiceItemDomain.setDateService( entity.getDateService() );
            orderServiceItemDomain.setDateTimeService( entity.getDateTimeService() );
            orderServiceItemDomain.setObservation( entity.getObservation() );
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                orderServiceItemDomain.setService( new ServiceDomain() );
                orderServiceItemDomain.getService().setId( entity.getService().getId() );
                orderServiceItemDomain.getService().setName( entity.getService().getName() );
            }
            if (entity.getRegisterBookingItemOperator() != null &&
                    entity.getRegisterBookingItemOperator().getId() != null &&
                    !entity.getRegisterBookingItemOperator().getId().isEmpty()) {
                orderServiceItemDomain.setRegisterBookingItemOperator( new RegisterBookingItemOperatorDomain() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setId( entity.getRegisterBookingItemOperator().getId() );
            }
            if (entity.getGuideTransfer() != null &&
                    entity.getGuideTransfer().getId() != null &&
                    !entity.getGuideTransfer().getId().isEmpty()){
                orderServiceItemDomain.setGuideTransfer(new GuideDomain());
                orderServiceItemDomain.getGuideTransfer().setId(entity.getGuideTransfer().getId());
                if (entity.getGuideTransfer().getPerson() != null &&
                        entity.getGuideTransfer().getPerson().getId() != null &&
                        !entity.getGuideTransfer().getPerson().getId().isEmpty()){
                    orderServiceItemDomain.getGuideTransfer().setPerson(new PersonDomain());
                    orderServiceItemDomain.getGuideTransfer().getPerson().setId(entity.getGuideTransfer().getPerson().getId());
                    orderServiceItemDomain.getGuideTransfer().getPerson().setFirstName(entity.getGuideTransfer().getPerson().getFirstName());
                    orderServiceItemDomain.getGuideTransfer().getPerson().setLastName(entity.getGuideTransfer().getPerson().getLastName());
                }
            }
            if (entity.getDrive() != null &&
                    entity.getDrive().getId() != null &&
                    !entity.getDrive().getId().isEmpty()){
                orderServiceItemDomain.setDrive(new GuideDomain());
                orderServiceItemDomain.getDrive().setId(entity.getDrive().getId());
                if (entity.getDrive().getPerson() != null &&
                        entity.getDrive().getPerson().getId() != null &&
                        !entity.getDrive().getPerson().getId().isEmpty()){
                    orderServiceItemDomain.getDrive().setPerson(new PersonDomain());
                    orderServiceItemDomain.getDrive().getPerson().setId(entity.getDrive().getPerson().getId());
                    orderServiceItemDomain.getDrive().getPerson().setFirstName(entity.getDrive().getPerson().getFirstName());
                    orderServiceItemDomain.getDrive().getPerson().setLastName(entity.getDrive().getPerson().getLastName());
                }
            }
            return orderServiceItemDomain;
        }).toList();
    }

    public List<OrderServiceItemDomain> toDomainAllSimpleToDomainCaseListOrderServiceItemByVerifyRegisterBookingItems(List<OrderServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceItemDomain orderServiceItemDomain = new OrderServiceItemDomain();
            orderServiceItemDomain.setId( entity.getId() );
            if (entity.getRegisterBookingItemOperator() != null &&
                    entity.getRegisterBookingItemOperator().getId() != null &&
                    !entity.getRegisterBookingItemOperator().getId().isEmpty()) {
                orderServiceItemDomain.setRegisterBookingItemOperator( new RegisterBookingItemOperatorDomain() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setId( entity.getRegisterBookingItemOperator().getId() );
            }
            return orderServiceItemDomain;
        }).toList();
    }

    public List<OrderServiceItemDomain> toDomainAllSimpleToDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(List<OrderServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceItemDomain orderServiceItemDomain = new OrderServiceItemDomain();
            orderServiceItemDomain.setId( entity.getId() );
            orderServiceItemDomain.setCantPax( entity.getCantPax() );
            orderServiceItemDomain.setCantPaxAdult( entity.getCantPaxAdult() );
            orderServiceItemDomain.setCantPaxChildren( entity.getCantPaxChildren() );
            orderServiceItemDomain.setTc( entity.getTc() );
            orderServiceItemDomain.setTcLocal( entity.getTcLocal() );
            orderServiceItemDomain.setGuide( entity.getGuide() );
            if (entity.getRegisterBookingItemOperator() != null &&
                    entity.getRegisterBookingItemOperator().getId() != null &&
                    !entity.getRegisterBookingItemOperator().getId().isEmpty()) {
                orderServiceItemDomain.setRegisterBookingItemOperator( new RegisterBookingItemOperatorDomain() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setId( entity.getRegisterBookingItemOperator().getId() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setCantPax( entity.getRegisterBookingItemOperator().getCantPax() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setCantPaxAdult( entity.getRegisterBookingItemOperator().getCantPaxAdult() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setCantPaxChildren( entity.getRegisterBookingItemOperator().getCantPaxChildren() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setTc( entity.getRegisterBookingItemOperator().getTc() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setTcLocal( entity.getRegisterBookingItemOperator().getTcLocal() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setGuide( entity.getRegisterBookingItemOperator().getGuide() );
            }
            return orderServiceItemDomain;
        }).toList();
    }

    public List<OrderServiceItemDomain> toDomainAllSimpleToDomainCaseShowOrderServiceItemByDelete(List<OrderServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceItemDomain orderServiceItemDomain = new OrderServiceItemDomain();
            orderServiceItemDomain.setId(entity.getId());
            return orderServiceItemDomain;
        }).toList();
    }

    public List<OrderServiceItemDomain> toDomainAllSimpleToDomainCaseListOrderServiceItemsExpenseGuide(List<OrderServiceItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            OrderServiceItemDomain orderServiceItemDomain = new OrderServiceItemDomain();
            orderServiceItemDomain.setId( entity.getId() );
            orderServiceItemDomain.setDateService( entity.getDateService() );
            orderServiceItemDomain.setDateTimeService( entity.getDateTimeService() );
            orderServiceItemDomain.setObservation( entity.getObservation() );
            if (entity.getService() != null &&
                    entity.getService().getId() != null &&
                    !entity.getService().getId().isEmpty()) {
                orderServiceItemDomain.setService( new ServiceDomain() );
                orderServiceItemDomain.getService().setId( entity.getService().getId() );
                orderServiceItemDomain.getService().setName( entity.getService().getName() );
                orderServiceItemDomain.getService().setTypeTour( entity.getService().getTypeTour() );
            }
            if (entity.getRegisterBookingItemOperator() != null &&
                    entity.getRegisterBookingItemOperator().getId() != null &&
                    !entity.getRegisterBookingItemOperator().getId().isEmpty()) {
                orderServiceItemDomain.setRegisterBookingItemOperator( new RegisterBookingItemOperatorDomain() );
                orderServiceItemDomain.getRegisterBookingItemOperator().setId( entity.getRegisterBookingItemOperator().getId() );
            }
            if (entity.getGuideTransfer() != null &&
                    entity.getGuideTransfer().getId() != null &&
                    !entity.getGuideTransfer().getId().isEmpty()){
                orderServiceItemDomain.setGuideTransfer(new GuideDomain());
                orderServiceItemDomain.getGuideTransfer().setId(entity.getGuideTransfer().getId());
                if (entity.getGuideTransfer().getPerson() != null &&
                        entity.getGuideTransfer().getPerson().getId() != null &&
                        !entity.getGuideTransfer().getPerson().getId().isEmpty()){
                    orderServiceItemDomain.getGuideTransfer().setPerson(new PersonDomain());
                    orderServiceItemDomain.getGuideTransfer().getPerson().setId(entity.getGuideTransfer().getPerson().getId());
                    orderServiceItemDomain.getGuideTransfer().getPerson().setFirstName(entity.getGuideTransfer().getPerson().getFirstName());
                    orderServiceItemDomain.getGuideTransfer().getPerson().setLastName(entity.getGuideTransfer().getPerson().getLastName());
                }
            }
            if (entity.getDrive() != null &&
                    entity.getDrive().getId() != null &&
                    !entity.getDrive().getId().isEmpty()){
                orderServiceItemDomain.setDrive(new GuideDomain());
                orderServiceItemDomain.getDrive().setId(entity.getDrive().getId());
                if (entity.getDrive().getPerson() != null &&
                        entity.getDrive().getPerson().getId() != null &&
                        !entity.getDrive().getPerson().getId().isEmpty()){
                    orderServiceItemDomain.getDrive().setPerson(new PersonDomain());
                    orderServiceItemDomain.getDrive().getPerson().setId(entity.getDrive().getPerson().getId());
                    orderServiceItemDomain.getDrive().getPerson().setFirstName(entity.getDrive().getPerson().getFirstName());
                    orderServiceItemDomain.getDrive().getPerson().setLastName(entity.getDrive().getPerson().getLastName());
                }
            }
            return orderServiceItemDomain;
        }).toList();
    }
}
