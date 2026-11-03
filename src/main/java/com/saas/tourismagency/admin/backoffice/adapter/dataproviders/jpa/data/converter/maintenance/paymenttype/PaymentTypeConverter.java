package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.paymenttype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PaymentTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PaymentTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeConverter implements Converter<PaymentTypeDomain, PaymentTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public PaymentTypeDomain toDomain(PaymentTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PaymentTypeDomain paymentTypeDomain = new PaymentTypeDomain();

        paymentTypeDomain.setId( source.getId() );
        paymentTypeDomain.setDeleted( source.getDeleted() );
        paymentTypeDomain.setEnabled( source.getEnabled() );
        paymentTypeDomain.setMigrated( source.getMigrated() );
        paymentTypeDomain.setCreatedAt( source.getCreatedAt() );
        paymentTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        paymentTypeDomain.setDeletedAt( source.getDeletedAt() );
        paymentTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        paymentTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        paymentTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        paymentTypeDomain.setIdentifierName( source.getIdentifierName() );
        paymentTypeDomain.setTenant( source.getTenant() );
        paymentTypeDomain.setName( source.getName() );
        paymentTypeDomain.setDescription( source.getDescription() );
        paymentTypeDomain.setIdentify( source.getIdentify() );

        return paymentTypeDomain;
    }

    @Override
    public PaymentTypeEntity toEntity(PaymentTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PaymentTypeEntity paymentTypeEntity = new PaymentTypeEntity();

        paymentTypeEntity.setId( source.getId() );
        paymentTypeEntity.setDeleted( source.getDeleted() );
        paymentTypeEntity.setEnabled( source.getEnabled() );
        paymentTypeEntity.setMigrated( source.getMigrated() );
        paymentTypeEntity.setCreatedAt( source.getCreatedAt() );
        paymentTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        paymentTypeEntity.setDeletedAt( source.getDeletedAt() );
        paymentTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        paymentTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        paymentTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        paymentTypeEntity.setIdentifierName( source.getIdentifierName() );
        paymentTypeEntity.setTenant( source.getTenant() );
        paymentTypeEntity.setName( source.getName() );
        paymentTypeEntity.setDescription( source.getDescription() );
        paymentTypeEntity.setIdentify( source.getIdentify() );

        return paymentTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(PaymentTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PaymentTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PaymentTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<PaymentTypeDomain> toDomainAllSimpleToDomainCaseListPaymentType(List<PaymentTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PaymentTypeDomain paymentTypeDomain = new PaymentTypeDomain();
            paymentTypeDomain.setId(entity.getId());
            paymentTypeDomain.setIdentify(entity.getIdentify());
            paymentTypeDomain.setName(entity.getName());
            return paymentTypeDomain;
        }).toList();
    }

    public PageDTO<PaymentTypeDomain> toDomainPageCaseListPaymentType(Page<PaymentTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<PaymentTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListPaymentType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
