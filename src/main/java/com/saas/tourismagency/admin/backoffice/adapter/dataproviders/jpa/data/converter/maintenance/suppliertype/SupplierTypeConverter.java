package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTypeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierTypeConverter implements Converter<SupplierTypeDomain, SupplierTypeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public SupplierTypeDomain toDomain(SupplierTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierTypeDomain supplierTypeDomain = new SupplierTypeDomain();

        supplierTypeDomain.setId( source.getId() );
        supplierTypeDomain.setDeleted( source.getDeleted() );
        supplierTypeDomain.setEnabled( source.getEnabled() );
        supplierTypeDomain.setMigrated( source.getMigrated() );
        supplierTypeDomain.setCreatedAt( source.getCreatedAt() );
        supplierTypeDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierTypeDomain.setDeletedAt( source.getDeletedAt() );
        supplierTypeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierTypeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierTypeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierTypeDomain.setIdentifierName( source.getIdentifierName() );
        supplierTypeDomain.setTenant( source.getTenant() );
        supplierTypeDomain.setName( source.getName() );
        supplierTypeDomain.setIdentify( source.getIdentify() );
        supplierTypeDomain.setDescription( source.getDescription() );

        return supplierTypeDomain;
    }

    @Override
    public SupplierTypeEntity toEntity(SupplierTypeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierTypeEntity supplierTypeEntity = new SupplierTypeEntity();

        supplierTypeEntity.setId( source.getId() );
        supplierTypeEntity.setDeleted( source.getDeleted() );
        supplierTypeEntity.setEnabled( source.getEnabled() );
        supplierTypeEntity.setMigrated( source.getMigrated() );
        supplierTypeEntity.setCreatedAt( source.getCreatedAt() );
        supplierTypeEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierTypeEntity.setDeletedAt( source.getDeletedAt() );
        supplierTypeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierTypeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierTypeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierTypeEntity.setIdentifierName( source.getIdentifierName() );
        supplierTypeEntity.setTenant( source.getTenant() );
        supplierTypeEntity.setName( source.getName() );
        supplierTypeEntity.setIdentify( source.getIdentify() );
        supplierTypeEntity.setDescription( source.getDescription() );

        return supplierTypeEntity;
    }

    public IdBaseDomain toDomainIdBase(SupplierTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierTypeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<SupplierTypeDomain> toDomainAllSimpleToDomainCaseByIdentify(List<SupplierTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierTypeDomain supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId( entity.getId() );
            return supplierTypeDomain;
        }).toList();
    }

    public List<SupplierTypeDomain> toDomainAllSimpleToDomainCaseListSupplierType(List<SupplierTypeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierTypeDomain supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(entity.getId());
            supplierTypeDomain.setIdentify(entity.getIdentify());
            supplierTypeDomain.setName(entity.getName());
            return supplierTypeDomain;
        }).toList();
    }

    public PageDTO<SupplierTypeDomain> toDomainPageCaseListSupplierType(Page<SupplierTypeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierTypeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierType(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
