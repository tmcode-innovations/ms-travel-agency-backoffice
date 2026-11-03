package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programcosttour;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program.ProgramConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programdate.ProgramDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramCostTourEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ProgramCostTourConverter implements Converter<ProgramCostTourDomain, ProgramCostTourEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ProgramConverter programConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private ProgramDateConverter programDateConverter;

    @Override
    public ProgramCostTourDomain toDomain(ProgramCostTourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramCostTourDomain programCostTourDomain = new ProgramCostTourDomain();

        programCostTourDomain.setId( source.getId() );
        programCostTourDomain.setDeleted( source.getDeleted() );
        programCostTourDomain.setEnabled( source.getEnabled() );
        programCostTourDomain.setMigrated( source.getMigrated() );
        programCostTourDomain.setCreatedAt( source.getCreatedAt() );
        programCostTourDomain.setUpdatedAt( source.getUpdatedAt() );
        programCostTourDomain.setDeletedAt( source.getDeletedAt() );
        programCostTourDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        programCostTourDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        programCostTourDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        programCostTourDomain.setIdentifierName( source.getIdentifierName() );
        programCostTourDomain.setTenant( source.getTenant() );
        programCostTourDomain.setName( source.getName() );
        programCostTourDomain.setDescription( source.getDescription() );
        programCostTourDomain.setIdentify( source.getIdentify() );
        programCostTourDomain.setDay( source.getDay() );
        programCostTourDomain.setProgram( programConverter.toDomain( source.getProgram() ) );
        programCostTourDomain.setTour( tourConverter.toDomain( source.getTour() ) );
        programCostTourDomain.setProgramDate( programDateConverter.toDomain( source.getProgramDate() ) );
        return programCostTourDomain;
    }

    @Override
    public ProgramCostTourEntity toEntity(ProgramCostTourDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramCostTourEntity programCostTourEntity = new ProgramCostTourEntity();

        programCostTourEntity.setId( source.getId() );
        programCostTourEntity.setDeleted( source.getDeleted() );
        programCostTourEntity.setEnabled( source.getEnabled() );
        programCostTourEntity.setMigrated( source.getMigrated() );
        programCostTourEntity.setCreatedAt( source.getCreatedAt() );
        programCostTourEntity.setUpdatedAt( source.getUpdatedAt() );
        programCostTourEntity.setDeletedAt( source.getDeletedAt() );
        programCostTourEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        programCostTourEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        programCostTourEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        programCostTourEntity.setIdentifierName( source.getIdentifierName() );
        programCostTourEntity.setTenant( source.getTenant() );
        programCostTourEntity.setName( source.getName() );
        programCostTourEntity.setDescription( source.getDescription() );
        programCostTourEntity.setIdentify( source.getIdentify() );
        programCostTourEntity.setDay( source.getDay() );
        programCostTourEntity.setProgram( programConverter.toEntity( source.getProgram() ) );
        programCostTourEntity.setTour( tourConverter.toEntity( source.getTour() ) );
        programCostTourEntity.setProgramDate( programDateConverter.toEntity( source.getProgramDate() ) );
        return programCostTourEntity;
    }

    public IdBaseDomain toDomainIdBase(ProgramCostTourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProgramCostTourEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProgramCostTourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProgramCostTourDomain> toDomainAllSimpleToDomainCaseShowProgramCostTour(List<ProgramCostTourEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramCostTourDomain programCostTourDomain = new ProgramCostTourDomain();
            programCostTourDomain.setId(entity.getId());
            programCostTourDomain.setDay(entity.getDay());
            if (entity.getProgramDate() != null &&
                    entity.getProgramDate().getId() != null &&
                    !entity.getProgramDate().getId().isEmpty()) {
                programCostTourDomain.setProgramDate(new ProgramDateDomain());
                programCostTourDomain.getProgramDate().setId(entity.getProgramDate().getId());
                programCostTourDomain.getProgramDate().setName(entity.getProgramDate().getName());
                programCostTourDomain.getProgramDate().setDay(entity.getProgramDate().getDay());
            }

            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                programCostTourDomain.setTour(new TourDomain());
                programCostTourDomain.getTour().setId(entity.getTour().getId());
                programCostTourDomain.getTour().setName(entity.getTour().getName());
                programCostTourDomain.getTour().setCantDay(entity.getTour().getCantDay());
                programCostTourDomain.getTour().setWithoutCost(entity.getTour().getWithoutCost());
            }

            if (entity.getProgram() != null &&
                    entity.getProgram().getId() != null &&
                    !entity.getProgram().getId().isEmpty()) {
                programCostTourDomain.setProgram(new ProgramDomain());
                programCostTourDomain.getProgram().setId(entity.getProgram().getId());
                programCostTourDomain.getProgram().setName(entity.getProgram().getName());
                programCostTourDomain.getProgram().setDay(entity.getProgram().getDay());
            }

            return programCostTourDomain;
        }).toList();
    }

}
