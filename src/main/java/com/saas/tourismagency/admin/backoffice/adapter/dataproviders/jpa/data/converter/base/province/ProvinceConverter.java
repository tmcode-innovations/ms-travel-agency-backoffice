package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.ProvinceEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceConverter implements Converter<ProvinceDomain, ProvinceEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Override
    public ProvinceDomain toDomain(ProvinceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProvinceDomain provinceDomain = new ProvinceDomain();

        provinceDomain.setId( source.getId() );
        provinceDomain.setDeleted( source.getDeleted() );
        provinceDomain.setEnabled( source.getEnabled() );
        provinceDomain.setMigrated( source.getMigrated() );
        provinceDomain.setCreatedAt( source.getCreatedAt() );
        provinceDomain.setUpdatedAt( source.getUpdatedAt() );
        provinceDomain.setDeletedAt( source.getDeletedAt() );
        provinceDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        provinceDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        provinceDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        provinceDomain.setIdentifierName( source.getIdentifierName() );
        provinceDomain.setTenant( source.getTenant() );
        provinceDomain.setName( source.getName() );
        provinceDomain.setDescription( source.getDescription() );
        provinceDomain.setIdentify( source.getIdentify() );
        provinceDomain.setCountry( countryConverter.toDomain( source.getCountry() ) );
        provinceDomain.setDepartmentRegion( departmentRegionConverter.toDomain( source.getDepartmentRegion() ) );

        return provinceDomain;
    }

    @Override
    public ProvinceEntity toEntity(ProvinceDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        ProvinceEntity provinceEntity = new ProvinceEntity();

        provinceEntity.setId( source.getId() );
        provinceEntity.setDeleted( source.getDeleted() );
        provinceEntity.setEnabled( source.getEnabled() );
        provinceEntity.setMigrated( source.getMigrated() );
        provinceEntity.setCreatedAt( source.getCreatedAt() );
        provinceEntity.setUpdatedAt( source.getUpdatedAt() );
        provinceEntity.setDeletedAt( source.getDeletedAt() );
        provinceEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        provinceEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        provinceEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        provinceEntity.setIdentifierName( source.getIdentifierName() );
        provinceEntity.setTenant( source.getTenant() );
        provinceEntity.setName( source.getName() );
        provinceEntity.setDescription( source.getDescription() );
        provinceEntity.setIdentify( source.getIdentify() );
        provinceEntity.setCountry( countryConverter.toEntity( source.getCountry() ) );
        provinceEntity.setDepartmentRegion( departmentRegionConverter.toEntity( source.getDepartmentRegion() ) );

        return provinceEntity;
    }

    public IdBaseDomain toDomainIdBase(ProvinceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(ProvinceEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<ProvinceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<ProvinceDomain> toDomainAllSimpleToDomainCaseListProvince(List<ProvinceEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            ProvinceDomain provinceDomain = new ProvinceDomain();
            provinceDomain.setId(entity.getId());
            provinceDomain.setIdentify(entity.getIdentify());
            provinceDomain.setName(entity.getName());
            if (entity.getDepartmentRegion() != null) {
                DepartmentRegionDomain departmentRegionDomain = new DepartmentRegionDomain();
                departmentRegionDomain.setId(entity.getDepartmentRegion().getId());
                departmentRegionDomain.setName(entity.getDepartmentRegion().getName());
                provinceDomain.setDepartmentRegion(departmentRegionDomain);
            }
            if (entity.getCountry() != null) {
                CountryDomain countryDomain = new CountryDomain();
                countryDomain.setId(entity.getCountry().getId());
                countryDomain.setName(entity.getCountry().getName());
                provinceDomain.setCountry(countryDomain);
            }
            return provinceDomain;
        }).toList();
    }

    public PageDTO<ProvinceDomain> toDomainPageCaseListProvince(Page<ProvinceEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<ProvinceDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListProvince(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
