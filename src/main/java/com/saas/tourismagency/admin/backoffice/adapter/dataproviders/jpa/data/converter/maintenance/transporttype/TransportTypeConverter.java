package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.transporttype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TransportTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TransportTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportTypeConverter implements Converter<TransportTypeDomain, TransportTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public TransportTypeDomain toDomain(TransportTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TransportTypeDomain transportTypeDomain = new TransportTypeDomain();

        transportTypeDomain.setId( source.getId() );
        transportTypeDomain.setDeleted( source.getDeleted() );
        transportTypeDomain.setEnabled( source.getEnabled() );
        transportTypeDomain.setMigrated( source.getMigrated() );
        transportTypeDomain.setCreatedAt( source.getCreatedAt() );
        transportTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        transportTypeDomain.setDeletedAt( source.getDeletedAt() );
        transportTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        transportTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        transportTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        transportTypeDomain.setIdentifierName( source.getIdentifierName() );
        transportTypeDomain.setTenant( source.getTenant() );
        transportTypeDomain.setName( source.getName() );
        transportTypeDomain.setDescription( source.getDescription() );
        transportTypeDomain.setIdentify( source.getIdentify() );

        return transportTypeDomain;
    }

    @Override
    public TransportTypeEntity toEntity(TransportTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TransportTypeEntity transportTypeEntity = new TransportTypeEntity();

        transportTypeEntity.setId( source.getId() );
        transportTypeEntity.setDeleted( source.getDeleted() );
        transportTypeEntity.setEnabled( source.getEnabled() );
        transportTypeEntity.setMigrated( source.getMigrated() );
        transportTypeEntity.setCreatedAt( source.getCreatedAt() );
        transportTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        transportTypeEntity.setDeletedAt( source.getDeletedAt() );
        transportTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        transportTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        transportTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        transportTypeEntity.setIdentifierName( source.getIdentifierName() );
        transportTypeEntity.setTenant( source.getTenant() );
        transportTypeEntity.setName( source.getName() );
        transportTypeEntity.setDescription( source.getDescription() );
        transportTypeEntity.setIdentify( source.getIdentify() );

        return transportTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(TransportTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(TransportTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<TransportTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<TransportTypeDomain> toDomainAllSimpleToDomainCaseListTransportType(List<TransportTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            TransportTypeDomain transportTypeDomain = new TransportTypeDomain();
            transportTypeDomain.setId(entity.getId());
            transportTypeDomain.setIdentify(entity.getIdentify());
            transportTypeDomain.setName(entity.getName());
            return transportTypeDomain;
        }).toList();
    }

    public PageDTO<TransportTypeDomain> toDomainPageCaseListTransportType(Page<TransportTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<TransportTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListTransportType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
