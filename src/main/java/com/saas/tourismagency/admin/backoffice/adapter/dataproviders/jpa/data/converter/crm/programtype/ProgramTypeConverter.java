package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programtype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramTypeConverter implements Converter<ProgramTypeDomain, ProgramTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public ProgramTypeDomain toDomain(ProgramTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramTypeDomain programTypeDomain = new ProgramTypeDomain();

        programTypeDomain.setId( source.getId() );
        programTypeDomain.setDeleted( source.getDeleted() );
        programTypeDomain.setEnabled( source.getEnabled() );
        programTypeDomain.setMigrated( source.getMigrated() );
        programTypeDomain.setCreatedAt( source.getCreatedAt() );
        programTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        programTypeDomain.setDeletedAt( source.getDeletedAt() );
        programTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        programTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        programTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        programTypeDomain.setIdentifierName( source.getIdentifierName() );
        programTypeDomain.setTenant( source.getTenant() );
        programTypeDomain.setName( source.getName() );
        programTypeDomain.setDescription( source.getDescription() );
        programTypeDomain.setIdentify( source.getIdentify() );

        return programTypeDomain;
    }

    @Override
    public ProgramTypeEntity toEntity(ProgramTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramTypeEntity programTypeEntity = new ProgramTypeEntity();

        programTypeEntity.setId( source.getId() );
        programTypeEntity.setDeleted( source.getDeleted() );
        programTypeEntity.setEnabled( source.getEnabled() );
        programTypeEntity.setMigrated( source.getMigrated() );
        programTypeEntity.setCreatedAt( source.getCreatedAt() );
        programTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        programTypeEntity.setDeletedAt( source.getDeletedAt() );
        programTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        programTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        programTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        programTypeEntity.setIdentifierName( source.getIdentifierName() );
        programTypeEntity.setTenant( source.getTenant() );
        programTypeEntity.setName( source.getName() );
        programTypeEntity.setDescription( source.getDescription() );
        programTypeEntity.setIdentify( source.getIdentify() );

        return programTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(ProgramTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProgramTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProgramTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProgramTypeDomain> toDomainAllSimpleToDomainCaseListProgramType(List<ProgramTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramTypeDomain programTypeDomain = new ProgramTypeDomain();
            programTypeDomain.setId(entity.getId());
            programTypeDomain.setIdentify(entity.getIdentify());
            programTypeDomain.setName(entity.getName());
            return programTypeDomain;
        }).toList();
    }

    public PageDTO<ProgramTypeDomain> toDomainPageCaseListProgramType(Page<ProgramTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ProgramTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListProgramType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
