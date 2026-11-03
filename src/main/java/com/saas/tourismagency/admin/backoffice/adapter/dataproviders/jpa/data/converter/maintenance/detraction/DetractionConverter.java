package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.detraction;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.DetractionEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetractionConverter implements Converter<DetractionDomain, DetractionEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public DetractionDomain toDomain(DetractionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DetractionDomain detractionDomain = new DetractionDomain();

        detractionDomain.setId( source.getId() );
        detractionDomain.setDeleted( source.getDeleted() );
        detractionDomain.setEnabled( source.getEnabled() );
        detractionDomain.setMigrated( source.getMigrated() );
        detractionDomain.setCreatedAt( source.getCreatedAt() );
        detractionDomain.setUpdatedAt( source.getUpdatedAt() );
        detractionDomain.setDeletedAt( source.getDeletedAt() );
        detractionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        detractionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        detractionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        detractionDomain.setIdentifierName( source.getIdentifierName() );
        detractionDomain.setTenant( source.getTenant() );
        detractionDomain.setName( source.getName() );
        detractionDomain.setDescription( source.getDescription() );
        detractionDomain.setAvailable( source.getAvailable() );
        detractionDomain.setValueDefault( source.getValueDefault() );
        detractionDomain.setPercent( source.getPercent() );
        detractionDomain.setAmount( source.getAmount() );
        detractionDomain.setDate( source.getDate() );
        detractionDomain.setObservation( source.getObservation() );

        return detractionDomain;
    }

    @Override
    public DetractionEntity toEntity(DetractionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DetractionEntity detractionEntity = new DetractionEntity();

        detractionEntity.setId( source.getId() );
        detractionEntity.setDeleted( source.getDeleted() );
        detractionEntity.setEnabled( source.getEnabled() );
        detractionEntity.setMigrated( source.getMigrated() );
        detractionEntity.setCreatedAt( source.getCreatedAt() );
        detractionEntity.setUpdatedAt( source.getUpdatedAt() );
        detractionEntity.setDeletedAt( source.getDeletedAt() );
        detractionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        detractionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        detractionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        detractionEntity.setIdentifierName( source.getIdentifierName() );
        detractionEntity.setTenant( source.getTenant() );
        detractionEntity.setName( source.getName() );
        detractionEntity.setDescription( source.getDescription() );
        detractionEntity.setAvailable( source.getAvailable() );
        detractionEntity.setValueDefault( source.getValueDefault() );
        detractionEntity.setPercent( source.getPercent() );
        detractionEntity.setAmount( source.getAmount() );
        detractionEntity.setDate( source.getDate() );
        detractionEntity.setObservation( source.getObservation() );

        return detractionEntity;
    }

    public IdBaseDomain toDomainIdBase(DetractionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DetractionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DetractionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<DetractionDomain> toDomainAllSimpleToDomainCaseListDetraction(List<DetractionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DetractionDomain detractionDomain = new DetractionDomain();
            detractionDomain.setId(entity.getId());
            detractionDomain.setName(entity.getName());
            detractionDomain.setPercent(entity.getPercent());
            detractionDomain.setAmount(entity.getAmount());
            detractionDomain.setValueDefault(entity.getValueDefault());
            detractionDomain.setAvailable(entity.getAvailable());
            return detractionDomain;
        }).toList();
    }

    public PageDTO<DetractionDomain> toDomainPageCaseListDetraction(Page<DetractionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<DetractionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListDetraction(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
