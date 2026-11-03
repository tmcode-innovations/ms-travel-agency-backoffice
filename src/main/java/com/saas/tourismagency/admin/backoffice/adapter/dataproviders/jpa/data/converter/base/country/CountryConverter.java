package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.CountryEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryConverter implements Converter<CountryDomain, CountryEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public CountryDomain toDomain(CountryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CountryDomain countryDomain = new CountryDomain();

        countryDomain.setId( source.getId() );
        countryDomain.setDeleted( source.getDeleted() );
        countryDomain.setEnabled( source.getEnabled() );
        countryDomain.setMigrated( source.getMigrated() );
        countryDomain.setCreatedAt( source.getCreatedAt() );
        countryDomain.setUpdatedAt( source.getUpdatedAt() );
        countryDomain.setDeletedAt( source.getDeletedAt() );
        countryDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        countryDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        countryDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        countryDomain.setIdentifierName( source.getIdentifierName() );
        countryDomain.setTenant( source.getTenant() );
        countryDomain.setName( source.getName() );
        countryDomain.setDescription( source.getDescription() );
        countryDomain.setIdentify( source.getIdentify() );
        countryDomain.setCountryIso( source.getCountryIso() );
        countryDomain.setIdentifyAlfa3( source.getIdentifyAlfa3() );
        countryDomain.setIdentifyNumeric( source.getIdentifyNumeric() );

        return countryDomain;
    }

    @Override
    public CountryEntity toEntity(CountryDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setId( source.getId() );
        countryEntity.setDeleted( source.getDeleted() );
        countryEntity.setEnabled( source.getEnabled() );
        countryEntity.setMigrated( source.getMigrated() );
        countryEntity.setCreatedAt( source.getCreatedAt() );
        countryEntity.setUpdatedAt( source.getUpdatedAt() );
        countryEntity.setDeletedAt( source.getDeletedAt() );
        countryEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        countryEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        countryEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        countryEntity.setIdentifierName( source.getIdentifierName() );
        countryEntity.setTenant( source.getTenant() );
        countryEntity.setName( source.getName() );
        countryEntity.setDescription( source.getDescription() );
        countryEntity.setIdentify( source.getIdentify() );
        countryEntity.setCountryIso( source.getCountryIso() );
        countryEntity.setIdentifyAlfa3( source.getIdentifyAlfa3() );
        countryEntity.setIdentifyNumeric( source.getIdentifyNumeric() );

        return countryEntity;
    }

    public IdBaseDomain toDomainIdBase(CountryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CountryEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CountryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CountryDomain> toDomainAllSimpleToDomainCaseListCountry(List<CountryEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CountryDomain countryDomain = new CountryDomain();
            countryDomain.setId(entity.getId());
            countryDomain.setIdentify(entity.getIdentify());
            countryDomain.setName(entity.getName());
            return countryDomain;
        }).toList();
    }

    public PageDTO<CountryDomain> toDomainPageCaseListCountry(Page<CountryEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CountryDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCountry(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
