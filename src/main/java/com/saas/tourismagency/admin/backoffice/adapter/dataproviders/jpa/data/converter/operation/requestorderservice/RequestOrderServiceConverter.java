package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.requestorderservice;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.RequestOrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestOrderServiceConverter implements Converter<RequestOrderServiceDomain, RequestOrderServiceEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SupplierOperatorConverter supplierOperatorConverter;

    @Autowired
    private RegisterBookingOperatorConverter registerBookingOperatorConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Override
    public RequestOrderServiceDomain toDomain(RequestOrderServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RequestOrderServiceDomain requestOrderServiceDomain = new RequestOrderServiceDomain();

        requestOrderServiceDomain.setId( source.getId() );
        requestOrderServiceDomain.setDeleted( source.getDeleted() );
        requestOrderServiceDomain.setEnabled( source.getEnabled() );
        requestOrderServiceDomain.setMigrated( source.getMigrated() );
        requestOrderServiceDomain.setCreatedAt( source.getCreatedAt() );
        requestOrderServiceDomain.setUpdatedAt( source.getUpdatedAt() );
        requestOrderServiceDomain.setDeletedAt( source.getDeletedAt() );
        requestOrderServiceDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        requestOrderServiceDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        requestOrderServiceDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        requestOrderServiceDomain.setIdentifierName( source.getIdentifierName() );
        requestOrderServiceDomain.setTenant( source.getTenant() );
        requestOrderServiceDomain.setName( source.getName() );
        requestOrderServiceDomain.setDescription( source.getDescription() );
        requestOrderServiceDomain.setIdentify( source.getIdentify() );

        requestOrderServiceDomain.setRequestOrderServiceState( source.getRequestOrderServiceState() );
        requestOrderServiceDomain.setActiveRequestOrderService( source.getActiveRequestOrderService() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            requestOrderServiceDomain.setSupplierOperator( supplierOperatorConverter.toDomain( source.getSupplierOperator() )  );
        }else{
            requestOrderServiceDomain.setSupplierOperator( null );
        }

        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            requestOrderServiceDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() )  );
        }else{
            requestOrderServiceDomain.setRegisterGroup( null );
        }

        if (source.getRegisterBookingOperator() != null &&
                source.getRegisterBookingOperator().getId() != null &&
                !source.getRegisterBookingOperator().getId().isEmpty()) {
            requestOrderServiceDomain.setRegisterBookingOperator( registerBookingOperatorConverter.toDomain( source.getRegisterBookingOperator() )  );
        }else{
            requestOrderServiceDomain.setRegisterBookingOperator( null );
        }

        return requestOrderServiceDomain;
    }

    @Override
    public RequestOrderServiceEntity toEntity(RequestOrderServiceDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RequestOrderServiceEntity requestOrderServiceEntity = new RequestOrderServiceEntity();

        requestOrderServiceEntity.setId( source.getId() );
        requestOrderServiceEntity.setDeleted( source.getDeleted() );
        requestOrderServiceEntity.setEnabled( source.getEnabled() );
        requestOrderServiceEntity.setMigrated( source.getMigrated() );
        requestOrderServiceEntity.setCreatedAt( source.getCreatedAt() );
        requestOrderServiceEntity.setUpdatedAt( source.getUpdatedAt() );
        requestOrderServiceEntity.setDeletedAt( source.getDeletedAt() );
        requestOrderServiceEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        requestOrderServiceEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        requestOrderServiceEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        requestOrderServiceEntity.setIdentifierName( source.getIdentifierName() );
        requestOrderServiceEntity.setTenant( source.getTenant() );
        requestOrderServiceEntity.setName( source.getName() );
        requestOrderServiceEntity.setDescription( source.getDescription() );
        requestOrderServiceEntity.setIdentify( source.getIdentify() );

        requestOrderServiceEntity.setRequestOrderServiceState( source.getRequestOrderServiceState() );
        requestOrderServiceEntity.setActiveRequestOrderService( source.getActiveRequestOrderService() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            requestOrderServiceEntity.setSupplierOperator( supplierOperatorConverter.toEntity( source.getSupplierOperator() )  );
        }else{
            requestOrderServiceEntity.setSupplierOperator( null );
        }

        if (source.getRegisterGroup() != null &&
                source.getRegisterGroup().getId() != null &&
                !source.getRegisterGroup().getId().isEmpty()) {
            requestOrderServiceEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() )  );
        }else{
            requestOrderServiceEntity.setRegisterGroup( null );
        }

        if (source.getRegisterBookingOperator() != null &&
                source.getRegisterBookingOperator().getId() != null &&
                !source.getRegisterBookingOperator().getId().isEmpty()) {
            requestOrderServiceEntity.setRegisterBookingOperator( registerBookingOperatorConverter.toEntity( source.getRegisterBookingOperator() )  );
        }else{
            requestOrderServiceEntity.setRegisterBookingOperator( null );
        }

        return requestOrderServiceEntity;
    }

    public IdBaseDomain toDomainIdBase(RequestOrderServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(RequestOrderServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<RequestOrderServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<RequestOrderServiceDomain> toDomainAllSimpleToDomain(List<RequestOrderServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RequestOrderServiceDomain requestOrderServiceDomain = new RequestOrderServiceDomain();
            requestOrderServiceDomain.setId(entity.getId());
            return requestOrderServiceDomain;
        }).toList();
    }

    public RequestOrderServiceDomain toDomainIdSimpleDomainCaseGenerateRequestOrderService(RequestOrderServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        RequestOrderServiceDomain requestOrderServiceDomain = new RequestOrderServiceDomain();

        requestOrderServiceDomain.setId( source.getId() );
        requestOrderServiceDomain.setRequestOrderServiceState( source.getRequestOrderServiceState() );
        return requestOrderServiceDomain;
    }

    public List<RequestOrderServiceDomain> toDomainAllSimpleToDomainCaseListRequestOrderService(List<RequestOrderServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            RequestOrderServiceDomain requestOrderServiceDomain = new RequestOrderServiceDomain();
            requestOrderServiceDomain.setId(entity.getId());
            requestOrderServiceDomain.setIdentify(entity.getIdentify());
            requestOrderServiceDomain.setCreatedAt(entity.getCreatedAt());
            requestOrderServiceDomain.setActiveRequestOrderService(entity.getActiveRequestOrderService());
            requestOrderServiceDomain.setRequestOrderServiceState(entity.getRequestOrderServiceState());
            if (entity.getRegisterBookingOperator() != null &&
                    entity.getRegisterBookingOperator().getId() != null &&
                    !entity.getRegisterBookingOperator().getId().isEmpty()){
                requestOrderServiceDomain.setRegisterBookingOperator(new RegisterBookingOperatorDomain());
                requestOrderServiceDomain.getRegisterBookingOperator().setId(entity.getRegisterBookingOperator().getId());
                requestOrderServiceDomain.getRegisterBookingOperator().setIdentify(entity.getRegisterBookingOperator().getIdentify());
                requestOrderServiceDomain.getRegisterBookingOperator().setTotalPax(entity.getRegisterBookingOperator().getTotalPax());
            }
            if (entity.getSupplierOperator() != null &&
                    entity.getSupplierOperator().getId() != null &&
                    !entity.getSupplierOperator().getId().isEmpty()){
                requestOrderServiceDomain.setSupplierOperator(new SupplierOperatorDomain());
                requestOrderServiceDomain.getSupplierOperator().setId(entity.getSupplierOperator().getId());
                requestOrderServiceDomain.getSupplierOperator().setName(entity.getSupplierOperator().getName());
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                requestOrderServiceDomain.setRegisterGroup(new RegisterGroupDomain());
                requestOrderServiceDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                requestOrderServiceDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
                requestOrderServiceDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
                if (entity.getRegisterGroup().getCustomer() != null &&
                        entity.getRegisterGroup().getCustomer().getId() != null &&
                        !entity.getRegisterGroup().getCustomer().getId().isEmpty()){
                    requestOrderServiceDomain.getRegisterGroup().setCustomer(new CustomerDomain());
                    requestOrderServiceDomain.getRegisterGroup().getCustomer().setId(entity.getRegisterGroup().getCustomer().getId());
                    requestOrderServiceDomain.getRegisterGroup().getCustomer().setIdentify(entity.getRegisterGroup().getCustomer().getIdentify());
                    requestOrderServiceDomain.getRegisterGroup().getCustomer().setName(entity.getRegisterGroup().getCustomer().getName());
                }
            }
            return requestOrderServiceDomain;
        }).toList();
    }

    public PageDTO<RequestOrderServiceDomain> toDomainPageCaseListRequestOrderService(Page<RequestOrderServiceEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<RequestOrderServiceDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListRequestOrderService(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
