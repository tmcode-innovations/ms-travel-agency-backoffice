package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.area;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.company.CompanyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaConverter implements Converter<AreaDomain, AreaEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CompanyConverter companyConverter;

    @Override
    public AreaDomain toDomain(AreaEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AreaDomain areaDomain = new AreaDomain();

        areaDomain.setId( source.getId() );
        areaDomain.setDeleted( source.getDeleted() );
        areaDomain.setEnabled( source.getEnabled() );
        areaDomain.setMigrated( source.getMigrated() );
        areaDomain.setCreatedAt( source.getCreatedAt() );
        areaDomain.setUpdatedAt( source.getUpdatedAt() );
        areaDomain.setDeletedAt( source.getDeletedAt() );
        areaDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        areaDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        areaDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        areaDomain.setIdentifierName( source.getIdentifierName() );
        areaDomain.setTenant( source.getTenant() );
        areaDomain.setName( source.getName() );
        areaDomain.setDescription( source.getDescription() );
        areaDomain.setIdentify( source.getIdentify() );
        areaDomain.setCompany( companyConverter.toDomain( source.getCompany() ) );

        return areaDomain;
    }

    @Override
    public AreaEntity toEntity(AreaDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AreaEntity areaEntity = new AreaEntity();

        areaEntity.setId( source.getId() );
        areaEntity.setDeleted( source.getDeleted() );
        areaEntity.setEnabled( source.getEnabled() );
        areaEntity.setMigrated( source.getMigrated() );
        areaEntity.setCreatedAt( source.getCreatedAt() );
        areaEntity.setUpdatedAt( source.getUpdatedAt() );
        areaEntity.setDeletedAt( source.getDeletedAt() );
        areaEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        areaEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        areaEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        areaEntity.setIdentifierName( source.getIdentifierName() );
        areaEntity.setTenant( source.getTenant() );
        areaEntity.setName( source.getName() );
        areaEntity.setDescription( source.getDescription() );
        areaEntity.setIdentify( source.getIdentify() );
        areaEntity.setCompany( companyConverter.toEntity( source.getCompany() ) );

        return areaEntity;
    }

    public AreaDomain toDomainCollection(AreaEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AreaDomain areaDomain = new AreaDomain();

        areaDomain.setId( source.getId() );
        areaDomain.setIdentifierName( source.getIdentifierName() );
        areaDomain.setTenant( source.getTenant() );
        areaDomain.setName( source.getName() );
        areaDomain.setDescription( source.getDescription() );
        areaDomain.setIdentify( source.getIdentify() );

        return areaDomain;
    }

    public AreaEntity toEntityCollection(AreaDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AreaEntity areaEntity = new AreaEntity();

        areaEntity.setId( source.getId() );
        areaEntity.setIdentifierName( source.getIdentifierName() );
        areaEntity.setTenant( source.getTenant() );
        areaEntity.setName( source.getName() );
        areaEntity.setDescription( source.getDescription() );
        areaEntity.setIdentify( source.getIdentify() );

        return areaEntity;
    }

    public IdBaseDomain toDomainIdBase(AreaEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(AreaEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<AreaEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<AreaDomain> toDomainAllSimpleToDomainCaseListArea(List<AreaEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            AreaDomain areaDomain = new AreaDomain();
            areaDomain.setId(entity.getId());
            areaDomain.setIdentify(entity.getIdentify());
            areaDomain.setName(entity.getName());
            if (entity.getCompany() != null) {
                CompanyDomain companyDomain = new CompanyDomain();
                companyDomain.setId(entity.getCompany().getId());
                companyDomain.setName(entity.getCompany().getName());
                areaDomain.setCompany(companyDomain);
            }
            return areaDomain;
        }).toList();
    }

    public PageDTO<AreaDomain> toDomainPageCaseListArea(Page<AreaEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<AreaDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListArea(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
