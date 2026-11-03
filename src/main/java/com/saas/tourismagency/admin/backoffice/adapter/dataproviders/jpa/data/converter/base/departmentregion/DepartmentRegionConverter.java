package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentRegionConverter implements Converter<DepartmentRegionDomain, DepartmentRegionEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Override
    public DepartmentRegionDomain toDomain(DepartmentRegionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DepartmentRegionDomain departmentRegionDomain = new DepartmentRegionDomain();

        departmentRegionDomain.setId( source.getId() );
        departmentRegionDomain.setDeleted( source.getDeleted() );
        departmentRegionDomain.setEnabled( source.getEnabled() );
        departmentRegionDomain.setMigrated( source.getMigrated() );
        departmentRegionDomain.setCreatedAt( source.getCreatedAt() );
        departmentRegionDomain.setUpdatedAt( source.getUpdatedAt() );
        departmentRegionDomain.setDeletedAt( source.getDeletedAt() );
        departmentRegionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        departmentRegionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        departmentRegionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        departmentRegionDomain.setIdentifierName( source.getIdentifierName() );
        departmentRegionDomain.setTenant( source.getTenant() );
        departmentRegionDomain.setName( source.getName() );
        departmentRegionDomain.setDescription( source.getDescription() );
        departmentRegionDomain.setIdentify( source.getIdentify() );
        departmentRegionDomain.setCapital( source.getCapital() );
        departmentRegionDomain.setCountry( countryConverter.toDomain( source.getCountry() ) );

        return departmentRegionDomain;
    }

    @Override
    public DepartmentRegionEntity toEntity(DepartmentRegionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DepartmentRegionEntity departmentRegionEntity = new DepartmentRegionEntity();

        departmentRegionEntity.setId( source.getId() );
        departmentRegionEntity.setDeleted( source.getDeleted() );
        departmentRegionEntity.setEnabled( source.getEnabled() );
        departmentRegionEntity.setMigrated( source.getMigrated() );
        departmentRegionEntity.setCreatedAt( source.getCreatedAt() );
        departmentRegionEntity.setUpdatedAt( source.getUpdatedAt() );
        departmentRegionEntity.setDeletedAt( source.getDeletedAt() );
        departmentRegionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        departmentRegionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        departmentRegionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        departmentRegionEntity.setIdentifierName( source.getIdentifierName() );
        departmentRegionEntity.setTenant( source.getTenant() );
        departmentRegionEntity.setName( source.getName() );
        departmentRegionEntity.setDescription( source.getDescription() );
        departmentRegionEntity.setIdentify( source.getIdentify() );
        departmentRegionEntity.setCapital( source.getCapital() );
        departmentRegionEntity.setCountry( countryConverter.toEntity( source.getCountry() ) );

        return departmentRegionEntity;
    }

    public IdBaseDomain toDomainIdBase(DepartmentRegionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DepartmentRegionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DepartmentRegionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<DepartmentRegionDomain> toDomainAllSimpleToDomainCaseListDepartmentRegion(List<DepartmentRegionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DepartmentRegionDomain departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(entity.getId());
            departmentRegionDomain.setIdentify(entity.getIdentify());
            departmentRegionDomain.setName(entity.getName());
            if (entity.getCountry() != null) {
                CountryDomain countryDomain = new CountryDomain();
                countryDomain.setId(entity.getCountry().getId());
                countryDomain.setName(entity.getCountry().getName());
                departmentRegionDomain.setCountry(countryDomain);
            }
            return departmentRegionDomain;
        }).toList();
    }

    public PageDTO<DepartmentRegionDomain> toDomainPageCaseListDepartmentRegion(Page<DepartmentRegionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<DepartmentRegionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListDepartmentRegion(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
