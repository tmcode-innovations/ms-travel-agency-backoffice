package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.program.ProgramConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programcosttour.ProgramCostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programdate.ProgramDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramItemConverter implements Converter<ProgramItemDomain, ProgramItemEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ProgramConverter programConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Autowired
    private ProgramDateConverter programDateConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private ProgramCostTourConverter programCostTourConverter;

    @Override
    public ProgramItemDomain toDomain(ProgramItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramItemDomain programItemDomain = new ProgramItemDomain();

        programItemDomain.setId( source.getId() );
        programItemDomain.setDeleted( source.getDeleted() );
        programItemDomain.setEnabled( source.getEnabled() );
        programItemDomain.setMigrated( source.getMigrated() );
        programItemDomain.setCreatedAt( source.getCreatedAt() );
        programItemDomain.setUpdatedAt( source.getUpdatedAt() );
        programItemDomain.setDeletedAt( source.getDeletedAt() );
        programItemDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        programItemDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        programItemDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        programItemDomain.setIdentifierName( source.getIdentifierName() );
        programItemDomain.setTenant( source.getTenant() );
        programItemDomain.setName( source.getName() );
        programItemDomain.setDescription( source.getDescription() );
        programItemDomain.setIdentify( source.getIdentify() );
        programItemDomain.setObservation( source.getObservation() );
        programItemDomain.setIndex( source.getIndex() );
        programItemDomain.setProgramDate( programDateConverter.toDomain( source.getProgramDate() ) );
        programItemDomain.setProgram( programConverter.toDomain( source.getProgram() ) );
        programItemDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        programItemDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        programItemDomain.setService( serviceConverter.toDomain( source.getService() ) );
        if (source.getTour() != null &&
                source.getTour().getId() != null &&
                !source.getTour().getId().isEmpty()) {
            programItemDomain.setTour( tourConverter.toDomain( source.getTour() ) );
        }else{
            programItemDomain.setTour( null );
        }
        if (source.getProgramCostTour() != null &&
                source.getProgramCostTour().getId() != null &&
                !source.getProgramCostTour().getId().isEmpty()) {
            programItemDomain.setProgramCostTour( programCostTourConverter.toDomain( source.getProgramCostTour() ) );
        }else{
            programItemDomain.setProgramCostTour( null );
        }
        programItemDomain.setPvt( source.getPvt() );
        programItemDomain.setSic( source.getSic() );
        programItemDomain.setProgramDateTime( source.getProgramDateTime() );
        programItemDomain.setServiceGroup( source.getServiceGroup() );

        // Supplier Type Lodging

        programItemDomain.setLodgingCantSwb(source.getLodgingCantSwb());
        programItemDomain.setLodgingCantMat(source.getLodgingCantMat());
        programItemDomain.setLodgingCantTwin(source.getLodgingCantTwin());
        programItemDomain.setLodgingCantTrp(source.getLodgingCantTrp());

        programItemDomain.setLodgingCategoryStr(source.getLodgingCategoryStr());

        programItemDomain.setLodgingCantBreakfast(source.getLodgingCantBreakfast());
        programItemDomain.setLodgingCantLunch(source.getLodgingCantLunch());
        programItemDomain.setLodgingCantDinner(source.getLodgingCantDinner());
        programItemDomain.setLodgingCantBoxLunch(source.getLodgingCantBoxLunch());

        programItemDomain.setLodgingCantSwbTcGuide(source.getLodgingCantSwbTcGuide());
        programItemDomain.setLodgingCantTwinTcGuide(source.getLodgingCantTwinTcGuide());

        programItemDomain.setLodgingCantBreakfastTcGuide(source.getLodgingCantBreakfastTcGuide());
        programItemDomain.setLodgingCantLunchTcGuide(source.getLodgingCantLunchTcGuide());
        programItemDomain.setLodgingCantDinnerTcGuide(source.getLodgingCantDinnerTcGuide());
        programItemDomain.setLodgingCantBoxLunchTcGuide(source.getLodgingCantBoxLunchTcGuide());

        programItemDomain.setLodgingCantSwbTcLocalGuide(source.getLodgingCantSwbTcLocalGuide());
        programItemDomain.setLodgingCantTwinTcLocalGuide(source.getLodgingCantTwinTcLocalGuide());

        programItemDomain.setLodgingCantBreakfastTcLocalGuide(source.getLodgingCantBreakfastTcLocalGuide());
        programItemDomain.setLodgingCantLunchTcLocalGuide(source.getLodgingCantLunchTcLocalGuide());
        programItemDomain.setLodgingCantDinnerTcLocalGuide(source.getLodgingCantDinnerTcLocalGuide());
        programItemDomain.setLodgingCantBoxLunchTcLocalGuide(source.getLodgingCantBoxLunchTcLocalGuide());

        programItemDomain.setLodgingCantSwbGuide(source.getLodgingCantSwbGuide());
        programItemDomain.setLodgingCantTwinGuide(source.getLodgingCantTwinGuide());

        programItemDomain.setLodgingCantBreakfastGuide(source.getLodgingCantBreakfastGuide());
        programItemDomain.setLodgingCantLunchGuide(source.getLodgingCantLunchGuide());
        programItemDomain.setLodgingCantDinnerGuide(source.getLodgingCantDinnerGuide());
        programItemDomain.setLodgingCantBoxLunchGuide(source.getLodgingCantBoxLunchGuide());

        // Supplier Type Lodge

        programItemDomain.setLodgeCantSwb(source.getLodgeCantSwb());
        programItemDomain.setLodgeCantMat(source.getLodgeCantMat());
        programItemDomain.setLodgeCantTwin(source.getLodgeCantTwin());
        programItemDomain.setLodgeCantTrp(source.getLodgeCantTrp());

        programItemDomain.setLodgeCategoryStr(source.getLodgeCategoryStr());

        programItemDomain.setLodgeCantBreakfast(source.getLodgeCantBreakfast());
        programItemDomain.setLodgeCantLunch(source.getLodgeCantLunch());
        programItemDomain.setLodgeCantDinner(source.getLodgeCantDinner());
        programItemDomain.setLodgeCantBoxLunch(source.getLodgeCantBoxLunch());

        programItemDomain.setLodgeCantSwbTcGuide(source.getLodgeCantSwbTcGuide());
        programItemDomain.setLodgeCantTwinTcGuide(source.getLodgeCantTwinTcGuide());

        programItemDomain.setLodgeCantBreakfastTcGuide(source.getLodgeCantBreakfastTcGuide());
        programItemDomain.setLodgeCantLunchTcGuide(source.getLodgeCantLunchTcGuide());
        programItemDomain.setLodgeCantDinnerTcGuide(source.getLodgeCantDinnerTcGuide());
        programItemDomain.setLodgeCantBoxLunchTcGuide(source.getLodgeCantBoxLunchTcGuide());

        programItemDomain.setLodgeCantSwbTcLocalGuide(source.getLodgeCantSwbTcLocalGuide());
        programItemDomain.setLodgeCantTwinTcLocalGuide(source.getLodgeCantTwinTcLocalGuide());

        programItemDomain.setLodgeCantBreakfastTcLocalGuide(source.getLodgeCantBreakfastTcLocalGuide());
        programItemDomain.setLodgeCantLunchTcLocalGuide(source.getLodgeCantLunchTcLocalGuide());
        programItemDomain.setLodgeCantDinnerTcLocalGuide(source.getLodgeCantDinnerTcLocalGuide());
        programItemDomain.setLodgeCantBoxLunchTcLocalGuide(source.getLodgeCantBoxLunchTcLocalGuide());

        programItemDomain.setLodgeCantSwbGuide(source.getLodgeCantSwbGuide());
        programItemDomain.setLodgeCantTwinGuide(source.getLodgeCantTwinGuide());

        programItemDomain.setLodgeCantBreakfastGuide(source.getLodgeCantBreakfastGuide());
        programItemDomain.setLodgeCantLunchGuide(source.getLodgeCantLunchGuide());
        programItemDomain.setLodgeCantDinnerGuide(source.getLodgeCantDinnerGuide());
        programItemDomain.setLodgeCantBoxLunchGuide(source.getLodgeCantBoxLunchGuide());

        // Supplier Type Restaurant

        programItemDomain.setRestaurantCantBreakfast(source.getRestaurantCantBreakfast());
        programItemDomain.setRestaurantCantLunch(source.getRestaurantCantLunch());
        programItemDomain.setRestaurantCantDinner(source.getRestaurantCantDinner());
        programItemDomain.setRestaurantCantBoxLunch(source.getRestaurantCantBoxLunch());

        programItemDomain.setRestaurantCantBreakfastTcGuide(source.getRestaurantCantBreakfastTcGuide());
        programItemDomain.setRestaurantCantLunchTcGuide(source.getRestaurantCantLunchTcGuide());
        programItemDomain.setRestaurantCantDinnerTcGuide(source.getRestaurantCantDinnerTcGuide());
        programItemDomain.setRestaurantCantBoxLunchTcGuide(source.getRestaurantCantBoxLunchTcGuide());

        programItemDomain.setRestaurantCantBreakfastTcLocalGuide(source.getRestaurantCantBreakfastTcLocalGuide());
        programItemDomain.setRestaurantCantLunchTcLocalGuide(source.getRestaurantCantLunchTcLocalGuide());
        programItemDomain.setRestaurantCantDinnerTcLocalGuide(source.getRestaurantCantDinnerTcLocalGuide());
        programItemDomain.setRestaurantCantBoxLunchTcLocalGuide(source.getRestaurantCantBoxLunchTcLocalGuide());

        programItemDomain.setRestaurantCantBreakfastGuide(source.getRestaurantCantBreakfastGuide());
        programItemDomain.setRestaurantCantLunchGuide(source.getRestaurantCantLunchGuide());
        programItemDomain.setRestaurantCantDinnerGuide(source.getRestaurantCantDinnerGuide());
        programItemDomain.setRestaurantCantBoxLunchGuide(source.getRestaurantCantBoxLunchGuide());


        return programItemDomain;
    }

    @Override
    public ProgramItemEntity toEntity(ProgramItemDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProgramItemEntity programItemEntity = new ProgramItemEntity();

        programItemEntity.setId( source.getId() );
        programItemEntity.setDeleted( source.getDeleted() );
        programItemEntity.setEnabled( source.getEnabled() );
        programItemEntity.setMigrated( source.getMigrated() );
        programItemEntity.setCreatedAt( source.getCreatedAt() );
        programItemEntity.setUpdatedAt( source.getUpdatedAt() );
        programItemEntity.setDeletedAt( source.getDeletedAt() );
        programItemEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        programItemEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        programItemEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        programItemEntity.setIdentifierName( source.getIdentifierName() );
        programItemEntity.setTenant( source.getTenant() );
        programItemEntity.setName( source.getName() );
        programItemEntity.setDescription( source.getDescription() );
        programItemEntity.setIdentify( source.getIdentify() );
        programItemEntity.setObservation( source.getObservation() );
        programItemEntity.setIndex( source.getIndex() );
        programItemEntity.setProgram( programConverter.toEntity( source.getProgram() ) );
        programItemEntity.setProgramDate( programDateConverter.toEntity( source.getProgramDate() ) );
        programItemEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        programItemEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        programItemEntity.setService( serviceConverter.toEntity( source.getService() ) );
        if (source.getTour() != null &&
                source.getTour().getId() != null &&
                !source.getTour().getId().isEmpty()) {
            programItemEntity.setTour( tourConverter.toEntity( source.getTour() ) );
        }else{
            programItemEntity.setTour( null );
        }
        if (source.getProgramCostTour() != null &&
                source.getProgramCostTour().getId() != null &&
                !source.getProgramCostTour().getId().isEmpty()) {
            programItemEntity.setProgramCostTour( programCostTourConverter.toEntity( source.getProgramCostTour() ) );
        }else{
            programItemEntity.setProgramCostTour( null );
        }
        programItemEntity.setPvt( source.getPvt() );
        programItemEntity.setSic( source.getSic() );
        programItemEntity.setProgramDateTime( source.getProgramDateTime() );

        programItemEntity.setServiceGroup( source.getServiceGroup() );

        // Supplier Type Lodging

        programItemEntity.setLodgingCantSwb(source.getLodgingCantSwb());
        programItemEntity.setLodgingCantMat(source.getLodgingCantMat());
        programItemEntity.setLodgingCantTwin(source.getLodgingCantTwin());
        programItemEntity.setLodgingCantTrp(source.getLodgingCantTrp());

        programItemEntity.setLodgingCategoryStr(source.getLodgingCategoryStr());

        programItemEntity.setLodgingCantBreakfast(source.getLodgingCantBreakfast());
        programItemEntity.setLodgingCantLunch(source.getLodgingCantLunch());
        programItemEntity.setLodgingCantDinner(source.getLodgingCantDinner());
        programItemEntity.setLodgingCantBoxLunch(source.getLodgingCantBoxLunch());

        programItemEntity.setLodgingCantSwbTcGuide(source.getLodgingCantSwbTcGuide());
        programItemEntity.setLodgingCantTwinTcGuide(source.getLodgingCantTwinTcGuide());

        programItemEntity.setLodgingCantBreakfastTcGuide(source.getLodgingCantBreakfastTcGuide());
        programItemEntity.setLodgingCantLunchTcGuide(source.getLodgingCantLunchTcGuide());
        programItemEntity.setLodgingCantDinnerTcGuide(source.getLodgingCantDinnerTcGuide());
        programItemEntity.setLodgingCantBoxLunchTcGuide(source.getLodgingCantBoxLunchTcGuide());

        programItemEntity.setLodgingCantSwbTcLocalGuide(source.getLodgingCantSwbTcLocalGuide());
        programItemEntity.setLodgingCantTwinTcLocalGuide(source.getLodgingCantTwinTcLocalGuide());

        programItemEntity.setLodgingCantBreakfastTcLocalGuide(source.getLodgingCantBreakfastTcLocalGuide());
        programItemEntity.setLodgingCantLunchTcLocalGuide(source.getLodgingCantLunchTcLocalGuide());
        programItemEntity.setLodgingCantDinnerTcLocalGuide(source.getLodgingCantDinnerTcLocalGuide());
        programItemEntity.setLodgingCantBoxLunchTcLocalGuide(source.getLodgingCantBoxLunchTcLocalGuide());

        programItemEntity.setLodgingCantSwbGuide(source.getLodgingCantSwbGuide());
        programItemEntity.setLodgingCantTwinGuide(source.getLodgingCantTwinGuide());

        programItemEntity.setLodgingCantBreakfastGuide(source.getLodgingCantBreakfastGuide());
        programItemEntity.setLodgingCantLunchGuide(source.getLodgingCantLunchGuide());
        programItemEntity.setLodgingCantDinnerGuide(source.getLodgingCantDinnerGuide());
        programItemEntity.setLodgingCantBoxLunchGuide(source.getLodgingCantBoxLunchGuide());

        // Supplier Type Lodge

        programItemEntity.setLodgeCantSwb(source.getLodgeCantSwb());
        programItemEntity.setLodgeCantMat(source.getLodgeCantMat());
        programItemEntity.setLodgeCantTwin(source.getLodgeCantTwin());
        programItemEntity.setLodgeCantTrp(source.getLodgeCantTrp());

        programItemEntity.setLodgeCategoryStr(source.getLodgeCategoryStr());

        programItemEntity.setLodgeCantBreakfast(source.getLodgeCantBreakfast());
        programItemEntity.setLodgeCantLunch(source.getLodgeCantLunch());
        programItemEntity.setLodgeCantDinner(source.getLodgeCantDinner());
        programItemEntity.setLodgeCantBoxLunch(source.getLodgeCantBoxLunch());

        programItemEntity.setLodgeCantSwbTcGuide(source.getLodgeCantSwbTcGuide());
        programItemEntity.setLodgeCantTwinTcGuide(source.getLodgeCantTwinTcGuide());

        programItemEntity.setLodgeCantBreakfastTcGuide(source.getLodgeCantBreakfastTcGuide());
        programItemEntity.setLodgeCantLunchTcGuide(source.getLodgeCantLunchTcGuide());
        programItemEntity.setLodgeCantDinnerTcGuide(source.getLodgeCantDinnerTcGuide());
        programItemEntity.setLodgeCantBoxLunchTcGuide(source.getLodgeCantBoxLunchTcGuide());

        programItemEntity.setLodgeCantSwbTcLocalGuide(source.getLodgeCantSwbTcLocalGuide());
        programItemEntity.setLodgeCantTwinTcLocalGuide(source.getLodgeCantTwinTcLocalGuide());

        programItemEntity.setLodgeCantBreakfastTcLocalGuide(source.getLodgeCantBreakfastTcLocalGuide());
        programItemEntity.setLodgeCantLunchTcLocalGuide(source.getLodgeCantLunchTcLocalGuide());
        programItemEntity.setLodgeCantDinnerTcLocalGuide(source.getLodgeCantDinnerTcLocalGuide());
        programItemEntity.setLodgeCantBoxLunchTcLocalGuide(source.getLodgeCantBoxLunchTcLocalGuide());

        programItemEntity.setLodgeCantSwbGuide(source.getLodgeCantSwbGuide());
        programItemEntity.setLodgeCantTwinGuide(source.getLodgeCantTwinGuide());

        programItemEntity.setLodgeCantBreakfastGuide(source.getLodgeCantBreakfastGuide());
        programItemEntity.setLodgeCantLunchGuide(source.getLodgeCantLunchGuide());
        programItemEntity.setLodgeCantDinnerGuide(source.getLodgeCantDinnerGuide());
        programItemEntity.setLodgeCantBoxLunchGuide(source.getLodgeCantBoxLunchGuide());

        // Supplier Type Restaurant

        programItemEntity.setRestaurantCantBreakfast(source.getRestaurantCantBreakfast());
        programItemEntity.setRestaurantCantLunch(source.getRestaurantCantLunch());
        programItemEntity.setRestaurantCantDinner(source.getRestaurantCantDinner());
        programItemEntity.setRestaurantCantBoxLunch(source.getRestaurantCantBoxLunch());

        programItemEntity.setRestaurantCantBreakfastTcGuide(source.getRestaurantCantBreakfastTcGuide());
        programItemEntity.setRestaurantCantLunchTcGuide(source.getRestaurantCantLunchTcGuide());
        programItemEntity.setRestaurantCantDinnerTcGuide(source.getRestaurantCantDinnerTcGuide());
        programItemEntity.setRestaurantCantBoxLunchTcGuide(source.getRestaurantCantBoxLunchTcGuide());

        programItemEntity.setRestaurantCantBreakfastTcLocalGuide(source.getRestaurantCantBreakfastTcLocalGuide());
        programItemEntity.setRestaurantCantLunchTcLocalGuide(source.getRestaurantCantLunchTcLocalGuide());
        programItemEntity.setRestaurantCantDinnerTcLocalGuide(source.getRestaurantCantDinnerTcLocalGuide());
        programItemEntity.setRestaurantCantBoxLunchTcLocalGuide(source.getRestaurantCantBoxLunchTcLocalGuide());

        programItemEntity.setRestaurantCantBreakfastGuide(source.getRestaurantCantBreakfastGuide());
        programItemEntity.setRestaurantCantLunchGuide(source.getRestaurantCantLunchGuide());
        programItemEntity.setRestaurantCantDinnerGuide(source.getRestaurantCantDinnerGuide());
        programItemEntity.setRestaurantCantBoxLunchGuide(source.getRestaurantCantBoxLunchGuide());


        return programItemEntity;
    }

    public IdBaseDomain toDomainIdBase(ProgramItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProgramItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProgramItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProgramItemDomain> toDomainAllSimpleToDomain(List<ProgramItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramItemDomain programItemDomain = new ProgramItemDomain();
            programItemDomain.setId(entity.getId());
            programItemDomain.setIndex(entity.getIndex());
            programItemDomain.setProgramDateTime(entity.getProgramDateTime());
            return programItemDomain;
        }).toList();
    }

    public List<ProgramItemDomain> toDomainAllSimpleToDomainCaseCreateItinerary(List<ProgramItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramItemDomain programItemDomain = new ProgramItemDomain();
            programItemDomain.setId(entity.getId());
            programItemDomain.setIndex(entity.getIndex());

            programItemDomain.setSupplierType(new SupplierTypeDomain());
            programItemDomain.getSupplierType().setId(entity.getSupplierType().getId());

            programItemDomain.setSupplier(new SupplierDomain());
            programItemDomain.getSupplier().setId(entity.getSupplier().getId());

            programItemDomain.setService(new ServiceDomain());
            programItemDomain.getService().setId(entity.getService().getId());

            programItemDomain.setTour(new TourDomain());
            programItemDomain.getTour().setId(entity.getTour().getId());

            programItemDomain.setObservation(entity.getObservation());
            programItemDomain.setPvt(entity.getPvt());
            programItemDomain.setSic(entity.getSic());
            programItemDomain.setProgramDateTime(entity.getProgramDateTime());
            programItemDomain.setServiceGroup(entity.getServiceGroup());

            // Supplier Type Lodging

            programItemDomain.setLodgingCantSwb(entity.getLodgingCantSwb());
            programItemDomain.setLodgingCantMat(entity.getLodgingCantMat());
            programItemDomain.setLodgingCantTwin(entity.getLodgingCantTwin());
            programItemDomain.setLodgingCantTrp(entity.getLodgingCantTrp());

            programItemDomain.setLodgingCategoryStr(entity.getLodgingCategoryStr());

            programItemDomain.setLodgingCantBreakfast(entity.getLodgingCantBreakfast());
            programItemDomain.setLodgingCantLunch(entity.getLodgingCantLunch());
            programItemDomain.setLodgingCantDinner(entity.getLodgingCantDinner());
            programItemDomain.setLodgingCantBoxLunch(entity.getLodgingCantBoxLunch());

            programItemDomain.setLodgingCantSwbTcGuide(entity.getLodgingCantSwbTcGuide());
            programItemDomain.setLodgingCantTwinTcGuide(entity.getLodgingCantTwinTcGuide());

            programItemDomain.setLodgingCantBreakfastTcGuide(entity.getLodgingCantBreakfastTcGuide());
            programItemDomain.setLodgingCantLunchTcGuide(entity.getLodgingCantLunchTcGuide());
            programItemDomain.setLodgingCantDinnerTcGuide(entity.getLodgingCantDinnerTcGuide());
            programItemDomain.setLodgingCantBoxLunchTcGuide(entity.getLodgingCantBoxLunchTcGuide());

            programItemDomain.setLodgingCantSwbTcLocalGuide(entity.getLodgingCantSwbTcLocalGuide());
            programItemDomain.setLodgingCantTwinTcLocalGuide(entity.getLodgingCantTwinTcLocalGuide());

            programItemDomain.setLodgingCantBreakfastTcLocalGuide(entity.getLodgingCantBreakfastTcLocalGuide());
            programItemDomain.setLodgingCantLunchTcLocalGuide(entity.getLodgingCantLunchTcLocalGuide());
            programItemDomain.setLodgingCantDinnerTcLocalGuide(entity.getLodgingCantDinnerTcLocalGuide());
            programItemDomain.setLodgingCantBoxLunchTcLocalGuide(entity.getLodgingCantBoxLunchTcLocalGuide());

            programItemDomain.setLodgingCantSwbGuide(entity.getLodgingCantSwbGuide());
            programItemDomain.setLodgingCantTwinGuide(entity.getLodgingCantTwinGuide());

            programItemDomain.setLodgingCantBreakfastGuide(entity.getLodgingCantBreakfastGuide());
            programItemDomain.setLodgingCantLunchGuide(entity.getLodgingCantLunchGuide());
            programItemDomain.setLodgingCantDinnerGuide(entity.getLodgingCantDinnerGuide());
            programItemDomain.setLodgingCantBoxLunchGuide(entity.getLodgingCantBoxLunchGuide());

            // Supplier Type Lodge

            programItemDomain.setLodgeCantSwb(entity.getLodgeCantSwb());
            programItemDomain.setLodgeCantMat(entity.getLodgeCantMat());
            programItemDomain.setLodgeCantTwin(entity.getLodgeCantTwin());
            programItemDomain.setLodgeCantTrp(entity.getLodgeCantTrp());

            programItemDomain.setLodgeCategoryStr(entity.getLodgeCategoryStr());

            programItemDomain.setLodgeCantBreakfast(entity.getLodgeCantBreakfast());
            programItemDomain.setLodgeCantLunch(entity.getLodgeCantLunch());
            programItemDomain.setLodgeCantDinner(entity.getLodgeCantDinner());
            programItemDomain.setLodgeCantBoxLunch(entity.getLodgeCantBoxLunch());

            programItemDomain.setLodgeCantSwbTcGuide(entity.getLodgeCantSwbTcGuide());
            programItemDomain.setLodgeCantTwinTcGuide(entity.getLodgeCantTwinTcGuide());

            programItemDomain.setLodgeCantBreakfastTcGuide(entity.getLodgeCantBreakfastTcGuide());
            programItemDomain.setLodgeCantLunchTcGuide(entity.getLodgeCantLunchTcGuide());
            programItemDomain.setLodgeCantDinnerTcGuide(entity.getLodgeCantDinnerTcGuide());
            programItemDomain.setLodgeCantBoxLunchTcGuide(entity.getLodgeCantBoxLunchTcGuide());

            programItemDomain.setLodgeCantSwbTcLocalGuide(entity.getLodgeCantSwbTcLocalGuide());
            programItemDomain.setLodgeCantTwinTcLocalGuide(entity.getLodgeCantTwinTcLocalGuide());

            programItemDomain.setLodgeCantBreakfastTcLocalGuide(entity.getLodgeCantBreakfastTcLocalGuide());
            programItemDomain.setLodgeCantLunchTcLocalGuide(entity.getLodgeCantLunchTcLocalGuide());
            programItemDomain.setLodgeCantDinnerTcLocalGuide(entity.getLodgeCantDinnerTcLocalGuide());
            programItemDomain.setLodgeCantBoxLunchTcLocalGuide(entity.getLodgeCantBoxLunchTcLocalGuide());

            programItemDomain.setLodgeCantSwbGuide(entity.getLodgeCantSwbGuide());
            programItemDomain.setLodgeCantTwinGuide(entity.getLodgeCantTwinGuide());

            programItemDomain.setLodgeCantBreakfastGuide(entity.getLodgeCantBreakfastGuide());
            programItemDomain.setLodgeCantLunchGuide(entity.getLodgeCantLunchGuide());
            programItemDomain.setLodgeCantDinnerGuide(entity.getLodgeCantDinnerGuide());
            programItemDomain.setLodgeCantBoxLunchGuide(entity.getLodgeCantBoxLunchGuide());

            // Supplier Type Restaurant

            programItemDomain.setRestaurantCantBreakfast(entity.getRestaurantCantBreakfast());
            programItemDomain.setRestaurantCantLunch(entity.getRestaurantCantLunch());
            programItemDomain.setRestaurantCantDinner(entity.getRestaurantCantDinner());
            programItemDomain.setRestaurantCantBoxLunch(entity.getRestaurantCantBoxLunch());

            programItemDomain.setRestaurantCantBreakfastTcGuide(entity.getRestaurantCantBreakfastTcGuide());
            programItemDomain.setRestaurantCantLunchTcGuide(entity.getRestaurantCantLunchTcGuide());
            programItemDomain.setRestaurantCantDinnerTcGuide(entity.getRestaurantCantDinnerTcGuide());
            programItemDomain.setRestaurantCantBoxLunchTcGuide(entity.getRestaurantCantBoxLunchTcGuide());

            programItemDomain.setRestaurantCantBreakfastTcLocalGuide(entity.getRestaurantCantBreakfastTcLocalGuide());
            programItemDomain.setRestaurantCantLunchTcLocalGuide(entity.getRestaurantCantLunchTcLocalGuide());
            programItemDomain.setRestaurantCantDinnerTcLocalGuide(entity.getRestaurantCantDinnerTcLocalGuide());
            programItemDomain.setRestaurantCantBoxLunchTcLocalGuide(entity.getRestaurantCantBoxLunchTcLocalGuide());

            programItemDomain.setRestaurantCantBreakfastGuide(entity.getRestaurantCantBreakfastGuide());
            programItemDomain.setRestaurantCantLunchGuide(entity.getRestaurantCantLunchGuide());
            programItemDomain.setRestaurantCantDinnerGuide(entity.getRestaurantCantDinnerGuide());
            programItemDomain.setRestaurantCantBoxLunchGuide(entity.getRestaurantCantBoxLunchGuide());

            return programItemDomain;
        }).toList();
    }

    public List<ProgramItemDomain> toDomainAllSimpleToDomainCaseDeleteTours(List<ProgramItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProgramItemDomain programItemDomain = new ProgramItemDomain();
            programItemDomain.setId( entity.getId() );
            programItemDomain.setIndex( entity.getIndex() );
            programItemDomain.setProgramDate( new ProgramDateDomain() );
            programItemDomain.getProgramDate().setId( entity.getProgramDate().getId() );
            return programItemDomain;
        }).toList();
    }
}
