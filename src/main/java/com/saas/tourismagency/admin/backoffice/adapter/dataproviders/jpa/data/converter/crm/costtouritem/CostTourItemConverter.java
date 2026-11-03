package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtouritem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtour.CostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtourserviceitem.CostTourServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itinerarydate.ItineraryDateCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.tour.TourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.passengertype.PassengerTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PassengerTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CostTourItemConverter implements Converter<CostTourItemDomain, CostTourItemEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CostTourConverter costTourConverter;

    @Autowired
    private QuotationConverter quotationConverter;

    @Autowired
    private TourConverter tourConverter;

    @Autowired
    private ItineraryDateCrmConverter itineraryDateConverter;

    @Autowired
    private PassengerTypeConverter passengerTypeConverter;

    @Autowired
    private CostTourServiceItemConverter costTourServiceItemConverter;

    @Override
    public CostTourItemDomain toDomain(CostTourItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostTourItemDomain costTourItemDomain = new CostTourItemDomain();

        costTourItemDomain.setId( source.getId() );
        costTourItemDomain.setDeleted( source.getDeleted() );
        costTourItemDomain.setEnabled( source.getEnabled() );
        costTourItemDomain.setMigrated( source.getMigrated() );
        costTourItemDomain.setCreatedAt( source.getCreatedAt() );
        costTourItemDomain.setUpdatedAt( source.getUpdatedAt() );
        costTourItemDomain.setDeletedAt( source.getDeletedAt() );
        costTourItemDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        costTourItemDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        costTourItemDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        costTourItemDomain.setIdentifierName( source.getIdentifierName() );
        costTourItemDomain.setTenant( source.getTenant() );
        costTourItemDomain.setName( source.getName() );
        costTourItemDomain.setDescription( source.getDescription() );
        costTourItemDomain.setIdentify( source.getIdentify() );
        costTourItemDomain.setCantPax( source.getCantPax() );
        costTourItemDomain.setPricePax( source.getPricePax() );
        costTourItemDomain.setAmount( source.getAmount() );
        costTourItemDomain.setQuotation( quotationConverter.toDomain( source.getQuotation() ) );
        costTourItemDomain.setTour( tourConverter.toDomain( source.getTour() ) );
        costTourItemDomain.setItineraryDate( itineraryDateConverter.toDomain( source.getItineraryDate() ) );
        costTourItemDomain.setCostTour( costTourConverter.toDomain( source.getCostTour() ) );
        costTourItemDomain.setPassengerType( passengerTypeConverter.toDomain( source.getPassengerType() ) );
        costTourItemDomain.setCostTourServiceItem( costTourServiceItemConverter.toDomain( source.getCostTourServiceItem() ) );
        return costTourItemDomain;
    }

    @Override
    public CostTourItemEntity toEntity(CostTourItemDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostTourItemEntity costTourItemEntity = new CostTourItemEntity();

        costTourItemEntity.setId( source.getId() );
        costTourItemEntity.setDeleted( source.getDeleted() );
        costTourItemEntity.setEnabled( source.getEnabled() );
        costTourItemEntity.setMigrated( source.getMigrated() );
        costTourItemEntity.setCreatedAt( source.getCreatedAt() );
        costTourItemEntity.setUpdatedAt( source.getUpdatedAt() );
        costTourItemEntity.setDeletedAt( source.getDeletedAt() );
        costTourItemEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        costTourItemEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        costTourItemEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        costTourItemEntity.setIdentifierName( source.getIdentifierName() );
        costTourItemEntity.setTenant( source.getTenant() );
        costTourItemEntity.setName( source.getName() );
        costTourItemEntity.setDescription( source.getDescription() );
        costTourItemEntity.setIdentify( source.getIdentify() );
        costTourItemEntity.setCantPax(source.getCantPax() );
        costTourItemEntity.setPricePax(source.getPricePax() );
        costTourItemEntity.setAmount( source.getAmount() );
        costTourItemEntity.setQuotation( quotationConverter.toEntity( source.getQuotation() ) );
        costTourItemEntity.setTour( tourConverter.toEntity( source.getTour() ) );
        costTourItemEntity.setItineraryDate( itineraryDateConverter.toEntity( source.getItineraryDate() ) );
        costTourItemEntity.setCostTour( costTourConverter.toEntity( source.getCostTour() ) );
        costTourItemEntity.setPassengerType( passengerTypeConverter.toEntity( source.getPassengerType() ) );
        costTourItemEntity.setCostTourServiceItem( costTourServiceItemConverter.toEntity( source.getCostTourServiceItem() ) );
        return costTourItemEntity;
    }

    public IdBaseDomain toDomainIdBase(CostTourItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CostTourItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CostTourItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CostTourItemDomain> toDomainAllSimpleToDomainCaseShowCostTourItem(List<CostTourItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourItemDomain costTourItemDomain = new CostTourItemDomain();
            costTourItemDomain.setId(entity.getId());
            costTourItemDomain.setPricePax(entity.getPricePax());
            costTourItemDomain.setCantPax(entity.getCantPax());
            costTourItemDomain.setAmount(entity.getAmount());
            if (entity.getPassengerType() != null &&
                    entity.getPassengerType().getId() != null &&
                    !entity.getPassengerType().getId().isEmpty()) {
                costTourItemDomain.setPassengerType(new PassengerTypeDomain());
                costTourItemDomain.getPassengerType().setId(entity.getPassengerType().getId());
                costTourItemDomain.getPassengerType().setName(entity.getPassengerType().getName());
            }
            if (entity.getCostTourServiceItem() != null &&
                    entity.getCostTourServiceItem().getId() != null &&
                    !entity.getCostTourServiceItem().getId().isEmpty()) {
                costTourItemDomain.setCostTourServiceItem(new CostTourServiceItemDomain());
                costTourItemDomain.getCostTourServiceItem().setId(entity.getCostTourServiceItem().getId());
                costTourItemDomain.getCostTourServiceItem().setName(entity.getCostTourServiceItem().getName());
                if (entity.getCostTourServiceItem().getService() != null &&
                        entity.getCostTourServiceItem().getService().getId() != null &&
                        !entity.getCostTourServiceItem().getService().getId().isEmpty()) {
                    costTourItemDomain.getCostTourServiceItem().setService(new ServiceDomain());
                    costTourItemDomain.getCostTourServiceItem().getService().setId(entity.getCostTourServiceItem().getService().getId());
                    costTourItemDomain.getCostTourServiceItem().getService().setName(entity.getCostTourServiceItem().getService().getName());
                }
            }
            return costTourItemDomain;
        }).toList();
    }

    public List<CostTourItemDomain> toDomainAllSimpleToDomainCaseDeleteByQuotation(List<CostTourItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourItemDomain costTourItemDomain = new CostTourItemDomain();
            costTourItemDomain.setId( entity.getId() );
            return costTourItemDomain;
        }).toList();
    }

    public List<CostTourItemDomain> toDomainAllSimpleToDomainCaseResendQuotationToBookingByCostTourAndQuotation(List<CostTourItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourItemDomain costTourItemDomain = new CostTourItemDomain();
            costTourItemDomain.setId( entity.getId() );
            costTourItemDomain.setName( entity.getName() );
            costTourItemDomain.setDescription( entity.getDescription() );
            costTourItemDomain.setIdentify( entity.getIdentify() );
            costTourItemDomain.setCantPax( entity.getCantPax() );
            costTourItemDomain.setPricePax( entity.getPricePax() );
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                costTourItemDomain.setTour(new TourDomain());
                costTourItemDomain.getTour().setId(entity.getTour().getId());
                costTourItemDomain.getTour().setName(entity.getTour().getName());
            }
            if (entity.getPassengerType() != null &&
                    entity.getPassengerType().getId() != null &&
                    !entity.getPassengerType().getId().isEmpty()) {
                costTourItemDomain.setPassengerType(new PassengerTypeDomain());
                costTourItemDomain.getPassengerType().setId(entity.getPassengerType().getId());
                costTourItemDomain.getPassengerType().setName(entity.getPassengerType().getName());
            }
            if (entity.getCostTourServiceItem() != null &&
                    entity.getCostTourServiceItem().getId() != null &&
                    !entity.getCostTourServiceItem().getId().isEmpty()) {
                costTourItemDomain.setCostTourServiceItem(new CostTourServiceItemDomain());
                costTourItemDomain.getCostTourServiceItem().setId(entity.getCostTourServiceItem().getId());
                costTourItemDomain.getCostTourServiceItem().setName(entity.getCostTourServiceItem().getName());
            }
            return costTourItemDomain;
        }).toList();
    }

    public List<CostTourItemDomain> toDomainAllSimpleToDomainCaseChangeStateOpenedByCostTourAndQuotation(List<CostTourItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostTourItemDomain costTourItemDomain = new CostTourItemDomain();
            costTourItemDomain.setId( entity.getId() );
            costTourItemDomain.setName( entity.getName() );
            costTourItemDomain.setDescription( entity.getDescription() );
            costTourItemDomain.setIdentify( entity.getIdentify() );
            costTourItemDomain.setCantPax( entity.getCantPax() );
            costTourItemDomain.setPricePax( entity.getPricePax() );
            if (entity.getTour() != null &&
                    entity.getTour().getId() != null &&
                    !entity.getTour().getId().isEmpty()) {
                costTourItemDomain.setTour(new TourDomain());
                costTourItemDomain.getTour().setId(entity.getTour().getId());
                costTourItemDomain.getTour().setName(entity.getTour().getName());
            }
            if (entity.getPassengerType() != null &&
                    entity.getPassengerType().getId() != null &&
                    !entity.getPassengerType().getId().isEmpty()) {
                costTourItemDomain.setPassengerType(new PassengerTypeDomain());
                costTourItemDomain.getPassengerType().setId(entity.getPassengerType().getId());
                costTourItemDomain.getPassengerType().setName(entity.getPassengerType().getName());
            }
            if (entity.getCostTourServiceItem() != null &&
                    entity.getCostTourServiceItem().getId() != null &&
                    !entity.getCostTourServiceItem().getId().isEmpty()) {
                costTourItemDomain.setCostTourServiceItem(new CostTourServiceItemDomain());
                costTourItemDomain.getCostTourServiceItem().setId(entity.getCostTourServiceItem().getId());
                costTourItemDomain.getCostTourServiceItem().setName(entity.getCostTourServiceItem().getName());
            }
            return costTourItemDomain;
        }).toList();
    }
}
