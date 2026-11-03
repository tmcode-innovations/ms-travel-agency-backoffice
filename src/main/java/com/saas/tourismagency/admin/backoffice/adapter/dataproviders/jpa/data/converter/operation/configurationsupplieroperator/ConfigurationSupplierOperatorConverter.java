package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.configurationsupplieroperator;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.ConfigurationSupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationSupplierOperatorConverter implements Converter<ConfigurationSupplierOperatorDomain, ConfigurationSupplierOperatorEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private SupplierOperatorConverter supplierOperatorConverter;

    @Override
    public ConfigurationSupplierOperatorDomain toDomain(ConfigurationSupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ConfigurationSupplierOperatorDomain configurationSupplierOperatorDomain = new ConfigurationSupplierOperatorDomain();

        configurationSupplierOperatorDomain.setId( source.getId() );
        configurationSupplierOperatorDomain.setDeleted( source.getDeleted() );
        configurationSupplierOperatorDomain.setEnabled( source.getEnabled() );
        configurationSupplierOperatorDomain.setMigrated( source.getMigrated() );
        configurationSupplierOperatorDomain.setCreatedAt( source.getCreatedAt() );
        configurationSupplierOperatorDomain.setUpdatedAt( source.getUpdatedAt() );
        configurationSupplierOperatorDomain.setDeletedAt( source.getDeletedAt() );
        configurationSupplierOperatorDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        configurationSupplierOperatorDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        configurationSupplierOperatorDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        configurationSupplierOperatorDomain.setIdentifierName( source.getIdentifierName() );
        configurationSupplierOperatorDomain.setTenant( source.getTenant() );
        configurationSupplierOperatorDomain.setName( source.getName() );
        configurationSupplierOperatorDomain.setDescription( source.getDescription() );
        configurationSupplierOperatorDomain.setIdentify( source.getIdentify() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            configurationSupplierOperatorDomain.setSupplierOperator( supplierOperatorConverter.toDomain( source.getSupplierOperator() )  );
        }else{
            configurationSupplierOperatorDomain.setSupplierOperator( null );
        }

        configurationSupplierOperatorDomain.setActive( source.getActive() );

        return configurationSupplierOperatorDomain;
    }

    @Override
    public ConfigurationSupplierOperatorEntity toEntity(ConfigurationSupplierOperatorDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ConfigurationSupplierOperatorEntity configurationSupplierOperatorEntity = new ConfigurationSupplierOperatorEntity();

        configurationSupplierOperatorEntity.setId( source.getId() );
        configurationSupplierOperatorEntity.setDeleted( source.getDeleted() );
        configurationSupplierOperatorEntity.setEnabled( source.getEnabled() );
        configurationSupplierOperatorEntity.setMigrated( source.getMigrated() );
        configurationSupplierOperatorEntity.setCreatedAt( source.getCreatedAt() );
        configurationSupplierOperatorEntity.setUpdatedAt( source.getUpdatedAt() );
        configurationSupplierOperatorEntity.setDeletedAt( source.getDeletedAt() );
        configurationSupplierOperatorEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        configurationSupplierOperatorEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        configurationSupplierOperatorEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        configurationSupplierOperatorEntity.setIdentifierName( source.getIdentifierName() );
        configurationSupplierOperatorEntity.setTenant( source.getTenant() );
        configurationSupplierOperatorEntity.setName( source.getName() );
        configurationSupplierOperatorEntity.setDescription( source.getDescription() );
        configurationSupplierOperatorEntity.setIdentify( source.getIdentify() );

        if (source.getSupplierOperator() != null &&
                source.getSupplierOperator().getId() != null &&
                !source.getSupplierOperator().getId().isEmpty()) {
            configurationSupplierOperatorEntity.setSupplierOperator( supplierOperatorConverter.toEntity( source.getSupplierOperator() )  );
        }else{
            configurationSupplierOperatorEntity.setSupplierOperator( null );
        }

        configurationSupplierOperatorEntity.setActive( source.getActive() );

        return configurationSupplierOperatorEntity;
    }

    public IdBaseDomain toDomainIdBase(ConfigurationSupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ConfigurationSupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setId( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ConfigurationSupplierOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ConfigurationSupplierOperatorDomain> toDomainAllSimpleToDomain(List<ConfigurationSupplierOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ConfigurationSupplierOperatorDomain configurationSupplierOperatorDomain = new ConfigurationSupplierOperatorDomain();
            configurationSupplierOperatorDomain.setId(entity.getId());
            configurationSupplierOperatorDomain.setName(entity.getName());
            return configurationSupplierOperatorDomain;
        }).toList();
    }

    public ConfigurationSupplierOperatorDomain toDomainIdSimpleDomainCaseGenerateRequestOrderService(ConfigurationSupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ConfigurationSupplierOperatorDomain configurationSupplierOperatorDomain = new ConfigurationSupplierOperatorDomain();

        configurationSupplierOperatorDomain.setId( source.getId() );
        return configurationSupplierOperatorDomain;
    }

    public List<ConfigurationSupplierOperatorDomain> toDomainAllSimpleToDomainCaseListConfigurationSupplierOperator(List<ConfigurationSupplierOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ConfigurationSupplierOperatorDomain configurationSupplierOperatorDomain = new ConfigurationSupplierOperatorDomain();
            configurationSupplierOperatorDomain.setId(entity.getId());
            configurationSupplierOperatorDomain.setName(entity.getName());
            configurationSupplierOperatorDomain.setActive(entity.getActive());
            configurationSupplierOperatorDomain.setDescription(entity.getDescription());
            if (entity.getSupplierOperator() != null &&
                    entity.getSupplierOperator().getId() != null &&
                    !entity.getSupplierOperator().getId().isEmpty()){
                configurationSupplierOperatorDomain.setSupplierOperator(new SupplierOperatorDomain());
                configurationSupplierOperatorDomain.getSupplierOperator().setId(entity.getSupplierOperator().getId());
                configurationSupplierOperatorDomain.getSupplierOperator().setName(entity.getSupplierOperator().getName());
            }
            return configurationSupplierOperatorDomain;
        }).toList();
    }

    public PageDTO<ConfigurationSupplierOperatorDomain> toDomainPageCaseListConfigurationSupplierOperator(Page<ConfigurationSupplierOperatorEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ConfigurationSupplierOperatorDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListConfigurationSupplierOperator(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
