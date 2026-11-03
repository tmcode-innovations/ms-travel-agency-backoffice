package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DistrictEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictConverter implements Converter<DistrictDomain, DistrictEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Autowired
    private ProvinceConverter provinceConverter;

    @Override
    public DistrictDomain toDomain(DistrictEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DistrictDomain districtDomain = new DistrictDomain();

        districtDomain.setId( source.getId() );
        districtDomain.setDeleted( source.getDeleted() );
        districtDomain.setEnabled( source.getEnabled() );
        districtDomain.setMigrated( source.getMigrated() );
        districtDomain.setCreatedAt( source.getCreatedAt() );
        districtDomain.setUpdatedAt( source.getUpdatedAt() );
        districtDomain.setDeletedAt( source.getDeletedAt() );
        districtDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        districtDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        districtDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        districtDomain.setIdentifierName( source.getIdentifierName() );
        districtDomain.setTenant( source.getTenant() );
        districtDomain.setName( source.getName() );
        districtDomain.setDescription( source.getDescription() );
        districtDomain.setIdentify( source.getIdentify() );
        districtDomain.setCountry( countryConverter.toDomain( source.getCountry() ) );
        districtDomain.setDepartmentRegion( departmentRegionConverter.toDomain( source.getDepartmentRegion() ) );
        districtDomain.setProvince( provinceConverter.toDomain( source.getProvince() ) );

        return districtDomain;
    }

    @Override
    public DistrictEntity toEntity(DistrictDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DistrictEntity districtEntity = new DistrictEntity();

        districtEntity.setId( source.getId() );
        districtEntity.setDeleted( source.getDeleted() );
        districtEntity.setEnabled( source.getEnabled() );
        districtEntity.setMigrated( source.getMigrated() );
        districtEntity.setCreatedAt( source.getCreatedAt() );
        districtEntity.setUpdatedAt( source.getUpdatedAt() );
        districtEntity.setDeletedAt( source.getDeletedAt() );
        districtEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        districtEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        districtEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        districtEntity.setIdentifierName( source.getIdentifierName() );
        districtEntity.setTenant( source.getTenant() );
        districtEntity.setName( source.getName() );
        districtEntity.setDescription( source.getDescription() );
        districtEntity.setIdentify( source.getIdentify() );
        districtEntity.setCountry( countryConverter.toEntity( source.getCountry() ) );
        districtEntity.setDepartmentRegion( departmentRegionConverter.toEntity( source.getDepartmentRegion() ) );
        districtEntity.setProvince( provinceConverter.toEntity( source.getProvince() ) );

        return districtEntity;
    }

    public IdBaseDomain toDomainIdBase(DistrictEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DistrictEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DistrictEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<DistrictDomain> toDomainAllSimpleToDomainCaseListDistrict(List<DistrictEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DistrictDomain districtDomain = new DistrictDomain();
            districtDomain.setId(entity.getId());
            districtDomain.setIdentify(entity.getIdentify());
            districtDomain.setName(entity.getName());
            if (entity.getProvince() != null) {
                ProvinceDomain provinceDomain = new ProvinceDomain();
                provinceDomain.setId(entity.getProvince().getId());
                provinceDomain.setName(entity.getProvince().getName());
                districtDomain.setProvince(provinceDomain);
            }
            if (entity.getDepartmentRegion() != null) {
                DepartmentRegionDomain departmentRegionDomain = new DepartmentRegionDomain();
                departmentRegionDomain.setId(entity.getDepartmentRegion().getId());
                departmentRegionDomain.setName(entity.getDepartmentRegion().getName());
                districtDomain.setDepartmentRegion(departmentRegionDomain);
            }
            if (entity.getCountry() != null) {
                CountryDomain countryDomain = new CountryDomain();
                countryDomain.setId(entity.getCountry().getId());
                countryDomain.setName(entity.getCountry().getName());
                districtDomain.setCountry(countryDomain);
            }
            return districtDomain;
        }).toList();
    }

    public PageDTO<DistrictDomain> toDomainPageCaseListDistrict(Page<DistrictEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<DistrictDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListDistrict(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
