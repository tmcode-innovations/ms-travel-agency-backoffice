package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.passengertype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.PassengerTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerTypeConverter implements Converter<PassengerTypeDomain, PassengerTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public PassengerTypeDomain toDomain(PassengerTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerTypeDomain passengerTypeDomain = new PassengerTypeDomain();

        passengerTypeDomain.setId( source.getId() );
        passengerTypeDomain.setDeleted( source.getDeleted() );
        passengerTypeDomain.setEnabled( source.getEnabled() );
        passengerTypeDomain.setMigrated( source.getMigrated() );
        passengerTypeDomain.setCreatedAt( source.getCreatedAt() );
        passengerTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        passengerTypeDomain.setDeletedAt( source.getDeletedAt() );
        passengerTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        passengerTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        passengerTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        passengerTypeDomain.setIdentifierName( source.getIdentifierName() );
        passengerTypeDomain.setTenant( source.getTenant() );
        passengerTypeDomain.setName( source.getName() );
        passengerTypeDomain.setDescription( source.getDescription() );
        passengerTypeDomain.setIdentify( source.getIdentify() );
        passengerTypeDomain.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );

        return passengerTypeDomain;
    }

    @Override
    public PassengerTypeEntity toEntity(PassengerTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        PassengerTypeEntity passengerTypeEntity = new PassengerTypeEntity();

        passengerTypeEntity.setId( source.getId() );
        passengerTypeEntity.setDeleted( source.getDeleted() );
        passengerTypeEntity.setEnabled( source.getEnabled() );
        passengerTypeEntity.setMigrated( source.getMigrated() );
        passengerTypeEntity.setCreatedAt( source.getCreatedAt() );
        passengerTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        passengerTypeEntity.setDeletedAt( source.getDeletedAt() );
        passengerTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        passengerTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        passengerTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        passengerTypeEntity.setIdentifierName( source.getIdentifierName() );
        passengerTypeEntity.setTenant( source.getTenant() );
        passengerTypeEntity.setName( source.getName() );
        passengerTypeEntity.setDescription( source.getDescription() );
        passengerTypeEntity.setIdentify( source.getIdentify() );
        passengerTypeEntity.setTypePassengerTcBooking( source.getTypePassengerTcBooking() );

        return passengerTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(PassengerTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(PassengerTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<PassengerTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<PassengerTypeDomain> toDomainAllSimpleToDomainCaseListPassengerType(List<PassengerTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            PassengerTypeDomain passengerTypeDomain = new PassengerTypeDomain();
            passengerTypeDomain.setId(entity.getId());
            passengerTypeDomain.setIdentify(entity.getIdentify());
            passengerTypeDomain.setName(entity.getName());
            return passengerTypeDomain;
        }).toList();
    }

    public PageDTO<PassengerTypeDomain> toDomainPageCaseListPassengerType(Page<PassengerTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<PassengerTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListPassengerType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
