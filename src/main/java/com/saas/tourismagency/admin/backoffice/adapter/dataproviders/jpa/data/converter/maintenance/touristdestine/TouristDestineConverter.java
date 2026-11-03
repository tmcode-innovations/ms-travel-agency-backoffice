package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.district.DistrictConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.province.ProvinceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.state.StateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TouristDestineEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristDestineConverter implements Converter<TouristDestineDomain, TouristDestineEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Autowired
    private ProvinceConverter provinceConverter;

    @Autowired
    private DistrictConverter districtConverter;

    @Autowired
    private StateConverter stateConverter;

    @Override
    public TouristDestineDomain toDomain(TouristDestineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TouristDestineDomain touristDestineDomain = new TouristDestineDomain();

        touristDestineDomain.setId( source.getId() );
        touristDestineDomain.setDeleted( source.getDeleted() );
        touristDestineDomain.setEnabled( source.getEnabled() );
        touristDestineDomain.setMigrated( source.getMigrated() );
        touristDestineDomain.setCreatedAt( source.getCreatedAt() );
        touristDestineDomain.setUpdatedAt( source.getUpdatedAt() );
        touristDestineDomain.setDeletedAt( source.getDeletedAt() );
        touristDestineDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        touristDestineDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        touristDestineDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        touristDestineDomain.setIdentifierName( source.getIdentifierName() );
        touristDestineDomain.setTenant( source.getTenant() );
        touristDestineDomain.setName( source.getName() );
        touristDestineDomain.setDescription( source.getDescription() );
        touristDestineDomain.setIdentify( source.getIdentify() );
        touristDestineDomain.setCountry( countryConverter.toDomain( source.getCountry() ) );
        touristDestineDomain.setDepartmentRegion( departmentRegionConverter.toDomain( source.getDepartmentRegion() ) );
        touristDestineDomain.setProvince( provinceConverter.toDomain( source.getProvince() ) );
        touristDestineDomain.setDistrict( districtConverter.toDomain( source.getDistrict() ) );
        touristDestineDomain.setState( stateConverter.toDomain( source.getState() ) );

        return touristDestineDomain;
    }

    @Override
    public TouristDestineEntity toEntity(TouristDestineDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        TouristDestineEntity touristDestineEntity = new TouristDestineEntity();

        touristDestineEntity.setId( source.getId() );
        touristDestineEntity.setDeleted( source.getDeleted() );
        touristDestineEntity.setEnabled( source.getEnabled() );
        touristDestineEntity.setMigrated( source.getMigrated() );
        touristDestineEntity.setCreatedAt( source.getCreatedAt() );
        touristDestineEntity.setUpdatedAt( source.getUpdatedAt() );
        touristDestineEntity.setDeletedAt( source.getDeletedAt() );
        touristDestineEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        touristDestineEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        touristDestineEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        touristDestineEntity.setIdentifierName( source.getIdentifierName() );
        touristDestineEntity.setTenant( source.getTenant() );
        touristDestineEntity.setName( source.getName() );
        touristDestineEntity.setDescription( source.getDescription() );
        touristDestineEntity.setIdentify( source.getIdentify() );
        touristDestineEntity.setCountry( countryConverter.toEntity( source.getCountry() ) );
        touristDestineEntity.setDepartmentRegion( departmentRegionConverter.toEntity( source.getDepartmentRegion() ) );
        touristDestineEntity.setProvince( provinceConverter.toEntity( source.getProvince() ) );
        touristDestineEntity.setDistrict( districtConverter.toEntity( source.getDistrict() ) );
        touristDestineEntity.setState( stateConverter.toEntity( source.getState() ) );

        return touristDestineEntity;
    }

    public IdBaseDomain toDomainIdBase(TouristDestineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(TouristDestineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<TouristDestineEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<TouristDestineDomain> toDomainAllSimpleToDomainCaseListTouristDestine(List<TouristDestineEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            TouristDestineDomain touristDestineDomain = new TouristDestineDomain();
            touristDestineDomain.setId(entity.getId());
            touristDestineDomain.setIdentify(entity.getIdentify());
            touristDestineDomain.setName(entity.getName());
            if (entity.getCountry() != null) {
                com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain countryDomain = new com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain();
                countryDomain.setId(entity.getCountry().getId());
                countryDomain.setName(entity.getCountry().getName());
                touristDestineDomain.setCountry(countryDomain);
            }
            if (entity.getDepartmentRegion() != null) {
                com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain departmentRegionDomain = new com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain();
                departmentRegionDomain.setId(entity.getDepartmentRegion().getId());
                departmentRegionDomain.setName(entity.getDepartmentRegion().getName());
                touristDestineDomain.setDepartmentRegion(departmentRegionDomain);
            }
            if (entity.getProvince() != null) {
                com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain provinceDomain = new com.saas.tourismagency.admin.backoffice.domain.base.ProvinceDomain();
                provinceDomain.setId(entity.getProvince().getId());
                provinceDomain.setName(entity.getProvince().getName());
                touristDestineDomain.setProvince(provinceDomain);
            }
            if (entity.getDistrict() != null) {
                com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain districtDomain = new com.saas.tourismagency.admin.backoffice.domain.base.DistrictDomain();
                districtDomain.setId(entity.getDistrict().getId());
                districtDomain.setName(entity.getDistrict().getName());
                touristDestineDomain.setDistrict(districtDomain);
            }
            if (entity.getState() != null) {
                com.saas.tourismagency.admin.backoffice.domain.base.StateDomain stateDomain = new com.saas.tourismagency.admin.backoffice.domain.base.StateDomain();
                stateDomain.setId(entity.getState().getId());
                stateDomain.setName(entity.getState().getName());
                touristDestineDomain.setState(stateDomain);
            }
            return touristDestineDomain;
        }).toList();
    }

    public PageDTO<TouristDestineDomain> toDomainPageCaseListTouristDestine(Page<TouristDestineEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<TouristDestineDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListTouristDestine(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
