package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programtype.ProgramTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTransportEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTransportDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramConverter implements Converter<ProgramDomain, ProgramEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ProgramTypeConverter programTypeConverter;

    @Override
    public ProgramDomain toDomain(ProgramEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramDomain programDomain = new ProgramDomain();

        programDomain.setId( source.getId() );
        programDomain.setDeleted( source.getDeleted() );
        programDomain.setEnabled( source.getEnabled() );
        programDomain.setMigrated( source.getMigrated() );
        programDomain.setCreatedAt( source.getCreatedAt() );
        programDomain.setUpdatedAt( source.getUpdatedAt() );
        programDomain.setDeletedAt( source.getDeletedAt() );
        programDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        programDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        programDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        programDomain.setIdentifierName( source.getIdentifierName() );
        programDomain.setTenant( source.getTenant() );
        programDomain.setName( source.getName() );
        programDomain.setDescription( source.getDescription() );
        programDomain.setIdentify( source.getIdentify() );
        programDomain.setDay( source.getDay() );
        programDomain.setStateProgram( source.getStateProgram() );
        programDomain.setProgramType( programTypeConverter.toDomain( source.getProgramType() ) );

        return programDomain;
    }

    @Override
    public ProgramEntity toEntity(ProgramDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramEntity programEntity = new ProgramEntity();

        programEntity.setId( source.getId() );
        programEntity.setDeleted( source.getDeleted() );
        programEntity.setEnabled( source.getEnabled() );
        programEntity.setMigrated( source.getMigrated() );
        programEntity.setCreatedAt( source.getCreatedAt() );
        programEntity.setUpdatedAt( source.getUpdatedAt() );
        programEntity.setDeletedAt( source.getDeletedAt() );
        programEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        programEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        programEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        programEntity.setIdentifierName( source.getIdentifierName() );
        programEntity.setTenant( source.getTenant() );
        programEntity.setName( source.getName() );
        programEntity.setDescription( source.getDescription() );
        programEntity.setIdentify( source.getIdentify() );
        programEntity.setDay( source.getDay() );
        programEntity.setStateProgram( source.getStateProgram() );
        programEntity.setProgramType( programTypeConverter.toEntity( source.getProgramType() ) );

        return programEntity;
    }

    public IdBaseDomain toDomainIdBase(ProgramEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProgramEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProgramEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProgramDomain> toDomainAllSimpleToDomainCaseListProgram(List<ProgramEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramDomain programDomain = new ProgramDomain();
            programDomain.setId(entity.getId());
            programDomain.setIdentify(entity.getIdentify());
            programDomain.setName(entity.getName());
            programDomain.setDay(entity.getDay());
            if (entity.getProgramType() != null &&
                    entity.getProgramType().getId() != null &&
                    !entity.getProgramType().getId().isEmpty()){
                programDomain.setProgramType(new ProgramTypeDomain());
                programDomain.getProgramType().setId(entity.getProgramType().getId());
                programDomain.getProgramType().setName(entity.getProgramType().getName());
            }
            return programDomain;
        }).toList();
    }

    public PageDTO<ProgramDomain> toDomainPageCaseListProgram(Page<ProgramEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ProgramDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListProgram(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public ProgramDomain toDomainSimpleBaseWithDay(ProgramEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramDomain programDomain = new ProgramDomain();

        programDomain.setId( source.getId() );
        programDomain.setName( source.getName() );
        programDomain.setDescription( source.getDescription() );
        programDomain.setDay( source.getDay() );
        return programDomain;
    }

    public List<ProgramDomain> toDomainAllSimpleWithDay(List<ProgramEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDay(entity)).toList();
    }
}
