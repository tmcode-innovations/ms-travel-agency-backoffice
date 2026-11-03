package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.servicecategory;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceCategoryEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceCategoryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryConverter implements Converter<ServiceCategoryDomain, ServiceCategoryEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public ServiceCategoryDomain toDomain(ServiceCategoryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ServiceCategoryDomain serviceCategoryDomain = new ServiceCategoryDomain();

        serviceCategoryDomain.setId( source.getId() );
        serviceCategoryDomain.setDeleted( source.getDeleted() );
        serviceCategoryDomain.setEnabled( source.getEnabled() );
        serviceCategoryDomain.setMigrated( source.getMigrated() );
        serviceCategoryDomain.setCreatedAt( source.getCreatedAt() );
        serviceCategoryDomain.setUpdatedAt( source.getUpdatedAt() );
        serviceCategoryDomain.setDeletedAt( source.getDeletedAt() );
        serviceCategoryDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        serviceCategoryDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        serviceCategoryDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        serviceCategoryDomain.setIdentifierName( source.getIdentifierName() );
        serviceCategoryDomain.setTenant( source.getTenant() );
        serviceCategoryDomain.setName( source.getName() );
        serviceCategoryDomain.setDescription( source.getDescription() );
        serviceCategoryDomain.setIdentify( source.getIdentify() );
        serviceCategoryDomain.setParent( toDomain( source.getParent() ) );

        return serviceCategoryDomain;
    }

    @Override
    public ServiceCategoryEntity toEntity(ServiceCategoryDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ServiceCategoryEntity serviceCategoryEntity = new ServiceCategoryEntity();

        serviceCategoryEntity.setId( source.getId() );
        serviceCategoryEntity.setDeleted( source.getDeleted() );
        serviceCategoryEntity.setEnabled( source.getEnabled() );
        serviceCategoryEntity.setMigrated( source.getMigrated() );
        serviceCategoryEntity.setCreatedAt( source.getCreatedAt() );
        serviceCategoryEntity.setUpdatedAt( source.getUpdatedAt() );
        serviceCategoryEntity.setDeletedAt( source.getDeletedAt() );
        serviceCategoryEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        serviceCategoryEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        serviceCategoryEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        serviceCategoryEntity.setIdentifierName( source.getIdentifierName() );
        serviceCategoryEntity.setTenant( source.getTenant() );
        serviceCategoryEntity.setName( source.getName() );
        serviceCategoryEntity.setDescription( source.getDescription() );
        serviceCategoryEntity.setIdentify( source.getIdentify() );
        serviceCategoryEntity.setParent( toEntity( source.getParent() ) );

        return serviceCategoryEntity;
    }

    public IdBaseDomain toDomainIdBase(ServiceCategoryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ServiceCategoryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ServiceCategoryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ServiceCategoryDomain> toDomainAllSimpleToDomainCaseListServiceCategory(List<ServiceCategoryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ServiceCategoryDomain serviceCategoryDomain = new ServiceCategoryDomain();
            serviceCategoryDomain.setId(entity.getId());
            serviceCategoryDomain.setIdentify(entity.getIdentify());
            serviceCategoryDomain.setName(entity.getName());
            return serviceCategoryDomain;
        }).toList();
    }

    public PageDTO<ServiceCategoryDomain> toDomainPageCaseListServiceCategory(Page<ServiceCategoryEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ServiceCategoryDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListServiceCategory(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
