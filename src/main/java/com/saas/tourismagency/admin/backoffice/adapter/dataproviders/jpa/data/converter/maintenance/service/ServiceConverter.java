package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.servicecategory.ServiceCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceConverter implements Converter<ServiceDomain, ServiceEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private TouristDestineConverter touristDestineConverter;

    @Autowired
    private ServiceCategoryConverter serviceCategoryConverter;

    @Override
    public ServiceDomain toDomain(ServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ServiceDomain serviceDomain = new ServiceDomain();

        serviceDomain.setId( source.getId() );
        serviceDomain.setDeleted( source.getDeleted() );
        serviceDomain.setEnabled( source.getEnabled() );
        serviceDomain.setMigrated( source.getMigrated() );
        serviceDomain.setCreatedAt( source.getCreatedAt() );
        serviceDomain.setUpdatedAt( source.getUpdatedAt() );
        serviceDomain.setDeletedAt( source.getDeletedAt() );
        serviceDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        serviceDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        serviceDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        serviceDomain.setIdentifierName( source.getIdentifierName() );
        serviceDomain.setTenant( source.getTenant() );
        serviceDomain.setName( source.getName() );
        serviceDomain.setDescription( source.getDescription() );
        serviceDomain.setIdentify( source.getIdentify() );
        serviceDomain.setAbbrev( source.getAbbrev() );
        serviceDomain.setObservation( source.getObservation() );
        serviceDomain.setNoApplyRate( source.getNoApplyRate() );
        serviceDomain.setDay( source.getDay() );
        serviceDomain.setWithoutCost( source.getWithoutCost() );
        serviceDomain.setTypeTour( source.getTypeTour() );
        serviceDomain.setCost( source.getCost() );
        serviceDomain.setServiceType( source.getServiceType() );
        serviceDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        serviceDomain.setServiceCategory( serviceCategoryConverter.toDomain( source.getServiceCategory() ) );

        return serviceDomain;
    }

    @Override
    public ServiceEntity toEntity(ServiceDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ServiceEntity serviceEntity = new ServiceEntity();

        serviceEntity.setId( source.getId() );
        serviceEntity.setDeleted( source.getDeleted() );
        serviceEntity.setEnabled( source.getEnabled() );
        serviceEntity.setMigrated( source.getMigrated() );
        serviceEntity.setCreatedAt( source.getCreatedAt() );
        serviceEntity.setUpdatedAt( source.getUpdatedAt() );
        serviceEntity.setDeletedAt( source.getDeletedAt() );
        serviceEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        serviceEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        serviceEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        serviceEntity.setIdentifierName( source.getIdentifierName() );
        serviceEntity.setTenant( source.getTenant() );
        serviceEntity.setName( source.getName() );
        serviceEntity.setDescription( source.getDescription() );
        serviceEntity.setIdentify( source.getIdentify() );
        serviceEntity.setAbbrev( source.getAbbrev() );
        serviceEntity.setObservation( source.getObservation() );
        serviceEntity.setNoApplyRate( source.getNoApplyRate() );
        serviceEntity.setDay( source.getDay() );
        serviceEntity.setWithoutCost( source.getWithoutCost() );
        serviceEntity.setTypeTour( source.getTypeTour() );
        serviceEntity.setCost( source.getCost() );
        serviceEntity.setServiceType( source.getServiceType() );
        serviceEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        serviceEntity.setServiceCategory( serviceCategoryConverter.toEntity( source.getServiceCategory() ) );

        return serviceEntity;
    }

    public IdBaseDomain toDomainIdBase(ServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ServiceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ServiceDomain> toDomainAllSimpleToDomainCaseToolUpdateAbbrev(List<ServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ServiceDomain serviceDomain = new ServiceDomain();
            serviceDomain.setId( entity.getId() );
            serviceDomain.setAbbrev( entity.getAbbrev() );
            serviceDomain.setName( entity.getName() );
            return serviceDomain;
        }).toList();
    }

    public List<ServiceDomain> toDomainAllSimpleToDomainCaseListService(List<ServiceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ServiceDomain serviceDomain = new ServiceDomain();
            serviceDomain.setId(entity.getId());
            serviceDomain.setIdentify(entity.getIdentify());
            serviceDomain.setAbbrev(entity.getAbbrev());
            serviceDomain.setName(entity.getName());
            return serviceDomain;
        }).toList();
    }

    public PageDTO<ServiceDomain> toDomainPageCaseListService(Page<ServiceEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ServiceDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListService(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
