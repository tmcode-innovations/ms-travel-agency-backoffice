package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.bank;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.BankEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankConverter implements Converter<BankDomain, BankEntity> {

    @Autowired
    private UserConverter userConverter;

    @Override
    public BankDomain toDomain(BankEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        BankDomain bankDomain = new BankDomain();

        bankDomain.setId( source.getId() );
        bankDomain.setDeleted( source.getDeleted() );
        bankDomain.setEnabled( source.getEnabled() );
        bankDomain.setMigrated( source.getMigrated() );
        bankDomain.setCreatedAt( source.getCreatedAt() );
        bankDomain.setUpdatedAt( source.getUpdatedAt() );
        bankDomain.setDeletedAt( source.getDeletedAt() );
        bankDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        bankDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        bankDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        bankDomain.setIdentifierName( source.getIdentifierName() );
        bankDomain.setTenant( source.getTenant() );
        bankDomain.setName( source.getName() );
        bankDomain.setDescription( source.getDescription() );
        bankDomain.setIdentify( source.getIdentify() );

        return bankDomain;
    }

    @Override
    public BankEntity toEntity(BankDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        BankEntity bankEntity = new BankEntity();

        bankEntity.setId( source.getId() );
        bankEntity.setDeleted( source.getDeleted() );
        bankEntity.setEnabled( source.getEnabled() );
        bankEntity.setMigrated( source.getMigrated() );
        bankEntity.setCreatedAt( source.getCreatedAt() );
        bankEntity.setUpdatedAt( source.getUpdatedAt() );
        bankEntity.setDeletedAt( source.getDeletedAt() );
        bankEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        bankEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        bankEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        bankEntity.setIdentifierName( source.getIdentifierName() );
        bankEntity.setTenant( source.getTenant() );
        bankEntity.setName( source.getName() );
        bankEntity.setDescription( source.getDescription() );
        bankEntity.setIdentify( source.getIdentify() );

        return bankEntity;
    }

    public IdBaseDomain toDomainIdBase(BankEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(BankEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<BankEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<BankDomain> toDomainAllSimpleToDomainCaseListBank(List<BankEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            BankDomain bankDomain = new BankDomain();
            bankDomain.setId(entity.getId());
            bankDomain.setIdentify(entity.getIdentify());
            bankDomain.setName(entity.getName());
            return bankDomain;
        }).toList();
    }

    public PageDTO<BankDomain> toDomainPageCaseListBank(Page<BankEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<BankDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListBank(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
