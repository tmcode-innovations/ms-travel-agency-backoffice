package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CurrencyEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyConverter implements Converter<CurrencyDomain, CurrencyEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public CurrencyDomain toDomain(CurrencyEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CurrencyDomain currencyDomain = new CurrencyDomain();

        currencyDomain.setId( source.getId() );
        currencyDomain.setDeleted( source.getDeleted() );
        currencyDomain.setEnabled( source.getEnabled() );
        currencyDomain.setMigrated( source.getMigrated() );
        currencyDomain.setCreatedAt( source.getCreatedAt() );
        currencyDomain.setUpdatedAt( source.getUpdatedAt() );
        currencyDomain.setDeletedAt( source.getDeletedAt() );
        currencyDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        currencyDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        currencyDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        currencyDomain.setIdentifierName( source.getIdentifierName() );
        currencyDomain.setTenant( source.getTenant() );
        currencyDomain.setName( source.getName() );
        currencyDomain.setDescription( source.getDescription() );
        currencyDomain.setIdentify( source.getIdentify() );
        currencyDomain.setAbbrev( source.getAbbrev() );

        return currencyDomain;
    }

    @Override
    public CurrencyEntity toEntity(CurrencyDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CurrencyEntity currencyEntity = new CurrencyEntity();

        currencyEntity.setId( source.getId() );
        currencyEntity.setDeleted( source.getDeleted() );
        currencyEntity.setEnabled( source.getEnabled() );
        currencyEntity.setMigrated( source.getMigrated() );
        currencyEntity.setCreatedAt( source.getCreatedAt() );
        currencyEntity.setUpdatedAt( source.getUpdatedAt() );
        currencyEntity.setDeletedAt( source.getDeletedAt() );
        currencyEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        currencyEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        currencyEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        currencyEntity.setIdentifierName( source.getIdentifierName() );
        currencyEntity.setTenant( source.getTenant() );
        currencyEntity.setName( source.getName() );
        currencyEntity.setDescription( source.getDescription() );
        currencyEntity.setIdentify( source.getIdentify() );
        currencyEntity.setAbbrev( source.getAbbrev() );

        return currencyEntity;
    }

    public IdBaseDomain toDomainIdBase(CurrencyEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        idBaseDomain.setName( source.getName() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CurrencyEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CurrencyEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CurrencyDomain> toDomainAllSimpleToDomainCaseListCurrency(List<CurrencyEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CurrencyDomain currencyDomain = new CurrencyDomain();
            currencyDomain.setId(entity.getId());
            currencyDomain.setIdentify(entity.getIdentify());
            currencyDomain.setName(entity.getName());
            currencyDomain.setAbbrev(entity.getAbbrev());
            return currencyDomain;
        }).toList();
    }

    public PageDTO<CurrencyDomain> toDomainPageCaseListCurrency(Page<CurrencyEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CurrencyDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCurrency(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
