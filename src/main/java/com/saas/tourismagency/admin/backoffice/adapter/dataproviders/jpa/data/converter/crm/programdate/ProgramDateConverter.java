package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programdate;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program.ProgramConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProgramDateConverter implements Converter<ProgramDateDomain, ProgramDateEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ProgramConverter programConverter;

    @Autowired
    private TourConverter tourConverter;

    @Override
    public ProgramDateDomain toDomain(ProgramDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramDateDomain programDateDomain = new ProgramDateDomain();

        programDateDomain.setId( source.getId() );
        programDateDomain.setDeleted( source.getDeleted() );
        programDateDomain.setEnabled( source.getEnabled() );
        programDateDomain.setMigrated( source.getMigrated() );
        programDateDomain.setCreatedAt( source.getCreatedAt() );
        programDateDomain.setUpdatedAt( source.getUpdatedAt() );
        programDateDomain.setDeletedAt( source.getDeletedAt() );
        programDateDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        programDateDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        programDateDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        programDateDomain.setIdentifierName( source.getIdentifierName() );
        programDateDomain.setTenant( source.getTenant() );
        programDateDomain.setName( source.getName() );
        programDateDomain.setDescription( source.getDescription() );
        programDateDomain.setIdentify( source.getIdentify() );
        programDateDomain.setDay( source.getDay() );
        programDateDomain.setProgram( programConverter.toDomain( source.getProgram() ) );
        programDateDomain.setTours( tourEntitySetToTourDomainSet( source.getTours() ) );

        return programDateDomain;
    }

    @Override
    public ProgramDateEntity toEntity(ProgramDateDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramDateEntity programDateEntity = new ProgramDateEntity();

        programDateEntity.setId( source.getId() );
        programDateEntity.setDeleted( source.getDeleted() );
        programDateEntity.setEnabled( source.getEnabled() );
        programDateEntity.setMigrated( source.getMigrated() );
        programDateEntity.setCreatedAt( source.getCreatedAt() );
        programDateEntity.setUpdatedAt( source.getUpdatedAt() );
        programDateEntity.setDeletedAt( source.getDeletedAt() );
        programDateEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        programDateEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        programDateEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        programDateEntity.setIdentifierName( source.getIdentifierName() );
        programDateEntity.setTenant( source.getTenant() );
        programDateEntity.setName( source.getName() );
        programDateEntity.setDescription( source.getDescription() );
        programDateEntity.setIdentify( source.getIdentify() );
        programDateEntity.setDay( source.getDay() );
        programDateEntity.setProgram( programConverter.toEntity( source.getProgram() ) );
        programDateEntity.setTours( tourDomainSetToTourEntitySet( source.getTours() ) );

        return programDateEntity;
    }

    public Set<TourDomain> tourEntitySetToTourDomainSet(Set<TourEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<TourDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TourEntity tourEntity : set ) {
            set1.add( tourConverter.toDomain( tourEntity ) );
        }
        return set1;
    }

    protected Set<TourEntity> tourDomainSetToTourEntitySet(Set<TourDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<TourEntity> set1 = new LinkedHashSet<TourEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TourDomain tourDomain : set ) {
            set1.add( tourConverter.toEntity( tourDomain ) );
        }
        return set1;
    }

    public IdBaseDomain toDomainIdBase(ProgramDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProgramDateEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProgramDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProgramDateDomain> toDomainAllSimpleToDomain(List<ProgramDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramDateDomain programDateDomain = new ProgramDateDomain();
            programDateDomain.setId(entity.getId());
            programDateDomain.setName(entity.getName());
            programDateDomain.setDay(entity.getDay());
            if (entity.getTours() != null && !entity.getTours().isEmpty()){
                programDateDomain.setTours(new HashSet<>());
                for ( TourEntity tourEntity : entity.getTours() ) {
                    TourDomain tourDomain = new TourDomain();
                    tourDomain.setId(tourEntity.getId());
                    tourDomain.setName(tourEntity.getName());
                    programDateDomain.getTours().add(tourDomain);
                }
            }
            return programDateDomain;
        }).toList();
    }

    public List<ProgramDateDomain> toDomainAllSimpleToDomainCaseDeleteTour(List<ProgramDateEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramDateDomain programDateDomain = new ProgramDateDomain();
            programDateDomain.setId( entity.getId() );
            programDateDomain.setName(entity.getName());
            programDateDomain.setDay(entity.getDay());
            if (entity.getTours() != null && !entity.getTours().isEmpty()){
                programDateDomain.setTours(new HashSet<>());
                for ( TourEntity tourEntity : entity.getTours() ) {
                    TourDomain tourDomain = new TourDomain();
                    tourDomain.setId(tourEntity.getId());
                    tourDomain.setName(tourEntity.getName());
                    programDateDomain.getTours().add(tourDomain);
                }
            }
            return programDateDomain;
        }).toList();
    }
}
