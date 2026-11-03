package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.servicepqttourist;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.grouppqttourist.GroupPqtTouristConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documentaccounttype.DocumentAccountTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.AccountPaymentEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ServicePqtTouristEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ServicePqtTouristDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePqtTouristConverter implements Converter<ServicePqtTouristDomain, ServicePqtTouristEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DocumentAccountTypeConverter documentAccountTypeConverter;

    @Autowired
    private GroupPqtTouristConverter groupPqtTouristConverter;

    @Override
    public ServicePqtTouristDomain toDomain(ServicePqtTouristEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ServicePqtTouristDomain servicePqtTouristDomain = new ServicePqtTouristDomain();

        servicePqtTouristDomain.setId( source.getId() );
        servicePqtTouristDomain.setDeleted( source.getDeleted() );
        servicePqtTouristDomain.setEnabled( source.getEnabled() );
        servicePqtTouristDomain.setMigrated( source.getMigrated() );
        servicePqtTouristDomain.setCreatedAt( source.getCreatedAt() );
        servicePqtTouristDomain.setUpdatedAt( source.getUpdatedAt() );
        servicePqtTouristDomain.setDeletedAt( source.getDeletedAt() );
        servicePqtTouristDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        servicePqtTouristDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        servicePqtTouristDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        servicePqtTouristDomain.setIdentifierName( source.getIdentifierName() );
        servicePqtTouristDomain.setTenant( source.getTenant() );
        servicePqtTouristDomain.setName( source.getName() );
        servicePqtTouristDomain.setDescription( source.getDescription() );
        servicePqtTouristDomain.setIdentify( source.getIdentify() );
        servicePqtTouristDomain.setAffectServiceIgv( source.getAffectServiceIgv() );
        servicePqtTouristDomain.setCostServiceIgv( source.getCostServiceIgv() );
        servicePqtTouristDomain.setDocumentAccountType( documentAccountTypeConverter.toDomain( source.getDocumentAccountType() ) );
        servicePqtTouristDomain.setGroupPqtTourist( groupPqtTouristConverter.toDomain( source.getGroupPqtTourist() ) );
        servicePqtTouristDomain.setAffectIgv( source.getAffectIgv() );

        return servicePqtTouristDomain;
    }

    @Override
    public ServicePqtTouristEntity toEntity(ServicePqtTouristDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ServicePqtTouristEntity servicePqtTouristEntity = new ServicePqtTouristEntity();

        servicePqtTouristEntity.setId( source.getId() );
        servicePqtTouristEntity.setDeleted( source.getDeleted() );
        servicePqtTouristEntity.setEnabled( source.getEnabled() );
        servicePqtTouristEntity.setMigrated( source.getMigrated() );
        servicePqtTouristEntity.setCreatedAt( source.getCreatedAt() );
        servicePqtTouristEntity.setUpdatedAt( source.getUpdatedAt() );
        servicePqtTouristEntity.setDeletedAt( source.getDeletedAt() );
        servicePqtTouristEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        servicePqtTouristEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        servicePqtTouristEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        servicePqtTouristEntity.setIdentifierName( source.getIdentifierName() );
        servicePqtTouristEntity.setTenant( source.getTenant() );
        servicePqtTouristEntity.setName( source.getName() );
        servicePqtTouristEntity.setDescription( source.getDescription() );
        servicePqtTouristEntity.setIdentify( source.getIdentify() );
        servicePqtTouristEntity.setAffectServiceIgv( source.getAffectServiceIgv() );
        servicePqtTouristEntity.setCostServiceIgv( source.getCostServiceIgv() );
        servicePqtTouristEntity.setDocumentAccountType( documentAccountTypeConverter.toEntity( source.getDocumentAccountType() ) );
        servicePqtTouristEntity.setGroupPqtTourist( groupPqtTouristConverter.toEntity( source.getGroupPqtTourist() ) );
        servicePqtTouristEntity.setAffectIgv( source.getAffectIgv() );

        return servicePqtTouristEntity;
    }

    public IdBaseDomain toDomainIdBase(ServicePqtTouristEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ServicePqtTouristEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ServicePqtTouristEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ServicePqtTouristDomain> toDomainAllSimpleToDomainCaseShowServicePqtTourist(List<ServicePqtTouristEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ServicePqtTouristDomain servicePqtTouristDomain = new ServicePqtTouristDomain();
            servicePqtTouristDomain.setId(entity.getId());
            servicePqtTouristDomain.setName(entity.getName());
            servicePqtTouristDomain.setDescription(entity.getDescription());
            return servicePqtTouristDomain;
        }).toList();
    }

    public List<ServicePqtTouristDomain> toDomainAllSimpleToDomainCaseListServicePqtTourist(List<ServicePqtTouristEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ServicePqtTouristDomain servicePqtTouristDomain = new ServicePqtTouristDomain();
            servicePqtTouristDomain.setId(entity.getId());
            servicePqtTouristDomain.setIdentify(entity.getIdentify());
            servicePqtTouristDomain.setName(entity.getName());
            if (entity.getDocumentAccountType() != null) {
                com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain documentAccountTypeDomain = new com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain();
                documentAccountTypeDomain.setId(entity.getDocumentAccountType().getId());
                documentAccountTypeDomain.setName(entity.getDocumentAccountType().getName());
                servicePqtTouristDomain.setDocumentAccountType(documentAccountTypeDomain);
            }
            if (entity.getGroupPqtTourist() != null) {
                com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain groupPqtTouristDomain = new com.saas.tourismagency.admin.backoffice.domain.account.GroupPqtTouristDomain();
                groupPqtTouristDomain.setId(entity.getGroupPqtTourist().getId());
                groupPqtTouristDomain.setName(entity.getGroupPqtTourist().getName());
                servicePqtTouristDomain.setGroupPqtTourist(groupPqtTouristDomain);
            }
            servicePqtTouristDomain.setAffectServiceIgv(entity.getAffectServiceIgv());
            servicePqtTouristDomain.setCostServiceIgv(entity.getCostServiceIgv());
            return servicePqtTouristDomain;
        }).toList();
    }

    public PageDTO<ServicePqtTouristDomain> toDomainPageCaseListServicePqtTourist(Page<ServicePqtTouristEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ServicePqtTouristDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListServicePqtTourist(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
