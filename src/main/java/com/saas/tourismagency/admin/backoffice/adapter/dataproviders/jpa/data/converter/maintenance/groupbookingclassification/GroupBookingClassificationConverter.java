package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.groupbookingclassification;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GroupBookingClassificationEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GroupBookingClassificationDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupBookingClassificationConverter implements Converter<GroupBookingClassificationDomain, GroupBookingClassificationEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public GroupBookingClassificationDomain toDomain(GroupBookingClassificationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        GroupBookingClassificationDomain groupBookingClassificationDomain = new GroupBookingClassificationDomain();

        groupBookingClassificationDomain.setId( source.getId() );
        groupBookingClassificationDomain.setDeleted( source.getDeleted() );
        groupBookingClassificationDomain.setEnabled( source.getEnabled() );
        groupBookingClassificationDomain.setMigrated( source.getMigrated() );
        groupBookingClassificationDomain.setCreatedAt( source.getCreatedAt() );
        groupBookingClassificationDomain.setUpdatedAt( source.getUpdatedAt() );
        groupBookingClassificationDomain.setDeletedAt( source.getDeletedAt() );
        groupBookingClassificationDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        groupBookingClassificationDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        groupBookingClassificationDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        groupBookingClassificationDomain.setIdentifierName( source.getIdentifierName() );
        groupBookingClassificationDomain.setTenant( source.getTenant() );
        groupBookingClassificationDomain.setName( source.getName() );
        groupBookingClassificationDomain.setDescription( source.getDescription() );
        groupBookingClassificationDomain.setIdentify( source.getIdentify() );
        groupBookingClassificationDomain.setBookingClassification( source.getBookingClassification() );

        return groupBookingClassificationDomain;
    }

    @Override
    public GroupBookingClassificationEntity toEntity(GroupBookingClassificationDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        GroupBookingClassificationEntity groupBookingClassificationEntity = new GroupBookingClassificationEntity();

        groupBookingClassificationEntity.setId( source.getId() );
        groupBookingClassificationEntity.setDeleted( source.getDeleted() );
        groupBookingClassificationEntity.setEnabled( source.getEnabled() );
        groupBookingClassificationEntity.setMigrated( source.getMigrated() );
        groupBookingClassificationEntity.setCreatedAt( source.getCreatedAt() );
        groupBookingClassificationEntity.setUpdatedAt( source.getUpdatedAt() );
        groupBookingClassificationEntity.setDeletedAt( source.getDeletedAt() );
        groupBookingClassificationEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        groupBookingClassificationEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        groupBookingClassificationEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        groupBookingClassificationEntity.setIdentifierName( source.getIdentifierName() );
        groupBookingClassificationEntity.setTenant( source.getTenant() );
        groupBookingClassificationEntity.setName( source.getName() );
        groupBookingClassificationEntity.setDescription( source.getDescription() );
        groupBookingClassificationEntity.setIdentify( source.getIdentify() );
        groupBookingClassificationEntity.setBookingClassification( source.getBookingClassification() );

        return groupBookingClassificationEntity;
    }

    public IdBaseDomain toDomainIdBase(GroupBookingClassificationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(GroupBookingClassificationEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<GroupBookingClassificationEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }
}
