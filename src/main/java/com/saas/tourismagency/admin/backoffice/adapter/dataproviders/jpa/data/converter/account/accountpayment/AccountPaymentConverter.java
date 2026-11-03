package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.accountpayment;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proforma.ProformaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.bank.BankConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.AccountPaymentEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.AccountPaymentDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountPaymentConverter implements Converter<AccountPaymentDomain, AccountPaymentEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ProformaConverter proformaConverter;

    @Autowired
    private BankConverter bankConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Override
    public AccountPaymentDomain toDomain(AccountPaymentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AccountPaymentDomain accountPaymentDomain = new AccountPaymentDomain();

        accountPaymentDomain.setId( source.getId() );
        accountPaymentDomain.setDeleted( source.getDeleted() );
        accountPaymentDomain.setEnabled( source.getEnabled() );
        accountPaymentDomain.setMigrated( source.getMigrated() );
        accountPaymentDomain.setCreatedAt( source.getCreatedAt() );
        accountPaymentDomain.setUpdatedAt( source.getUpdatedAt() );
        accountPaymentDomain.setDeletedAt( source.getDeletedAt() );
        accountPaymentDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        accountPaymentDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        accountPaymentDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        accountPaymentDomain.setIdentifierName( source.getIdentifierName() );
        accountPaymentDomain.setTenant( source.getTenant() );
        accountPaymentDomain.setName( source.getName() );
        accountPaymentDomain.setDescription( source.getDescription() );
        accountPaymentDomain.setIdentify( source.getIdentify() );

        accountPaymentDomain.setNoItem(source.getNoItem() );
        accountPaymentDomain.setPaymentDate( source.getPaymentDate() );
        accountPaymentDomain.setNroDocument( source.getNroDocument() );
        accountPaymentDomain.setObservation( source.getObservation() );
        accountPaymentDomain.setImportMontSoles( source.getImportMontSoles() );
        accountPaymentDomain.setImportMontUsd( source.getImportMontUsd() );

        accountPaymentDomain.setPaymentType( source.getPaymentType() );

        accountPaymentDomain.setProforma( proformaConverter.toDomain( source.getProforma() ) );
        accountPaymentDomain.setBank( bankConverter.toDomain( source.getBank() ) );
        accountPaymentDomain.setChangeType( changeTypeConverter.toDomain( source.getChangeType() ) );

        return accountPaymentDomain;
    }

    @Override
    public AccountPaymentEntity toEntity(AccountPaymentDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        AccountPaymentEntity accountPaymentEntity = new AccountPaymentEntity();

        accountPaymentEntity.setId( source.getId() );
        accountPaymentEntity.setDeleted( source.getDeleted() );
        accountPaymentEntity.setEnabled( source.getEnabled() );
        accountPaymentEntity.setMigrated( source.getMigrated() );
        accountPaymentEntity.setCreatedAt( source.getCreatedAt() );
        accountPaymentEntity.setUpdatedAt( source.getUpdatedAt() );
        accountPaymentEntity.setDeletedAt( source.getDeletedAt() );
        accountPaymentEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        accountPaymentEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        accountPaymentEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        accountPaymentEntity.setIdentifierName( source.getIdentifierName() );
        accountPaymentEntity.setTenant( source.getTenant() );
        accountPaymentEntity.setName( source.getName() );
        accountPaymentEntity.setDescription( source.getDescription() );
        accountPaymentEntity.setIdentify( source.getIdentify() );

        accountPaymentEntity.setNoItem(source.getNoItem() );
        accountPaymentEntity.setPaymentDate( source.getPaymentDate() );
        accountPaymentEntity.setNroDocument( source.getNroDocument() );
        accountPaymentEntity.setObservation( source.getObservation() );
        accountPaymentEntity.setImportMontSoles( source.getImportMontSoles() );
        accountPaymentEntity.setImportMontUsd( source.getImportMontUsd() );

        accountPaymentEntity.setPaymentType( source.getPaymentType() );

        accountPaymentEntity.setProforma( proformaConverter.toEntity( source.getProforma() ) );
        accountPaymentEntity.setBank( bankConverter.toEntity( source.getBank() ) );
        accountPaymentEntity.setChangeType( changeTypeConverter.toEntity( source.getChangeType() ) );

        return accountPaymentEntity;
    }

    public IdBaseDomain toDomainIdBase(AccountPaymentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(AccountPaymentEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<AccountPaymentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<AccountPaymentDomain> toDomainAllSimpleToDomainCaseListEntity(List<AccountPaymentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            AccountPaymentDomain accountPaymentDomain = new AccountPaymentDomain();

            return accountPaymentDomain;
        }).toList();
    }

    public List<AccountPaymentDomain> toDomainAllSimpleToDomainCaseShowAccountPayment(List<AccountPaymentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            AccountPaymentDomain accountPaymentDomain = new AccountPaymentDomain();
            accountPaymentDomain.setId(entity.getId());
            accountPaymentDomain.setName(entity.getName());
            accountPaymentDomain.setDescription(entity.getDescription());
            return accountPaymentDomain;
        }).toList();
    }

    public List<AccountPaymentDomain> toDomainAllSimpleToDomainCaseListAccountPayment(List<AccountPaymentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            AccountPaymentDomain accountPaymentDomain = new AccountPaymentDomain();
            accountPaymentDomain.setId(entity.getId());
            accountPaymentDomain.setIdentify(entity.getIdentify());
            accountPaymentDomain.setNoItem(entity.getNoItem());
            accountPaymentDomain.setPaymentDate(entity.getPaymentDate());
            accountPaymentDomain.setNroDocument(entity.getNroDocument());
            accountPaymentDomain.setObservation(entity.getObservation());
            accountPaymentDomain.setImportMontUsd(entity.getImportMontUsd());
            accountPaymentDomain.setImportMontSoles(entity.getImportMontSoles());
            accountPaymentDomain.setPaymentType(entity.getPaymentType());
            if (entity.getProforma() != null && entity.getProforma().getId() != null && !entity.getProforma().getId().isEmpty()){
                accountPaymentDomain.setProforma(new ProformaDomain());
                accountPaymentDomain.getProforma().setId(entity.getProforma().getId());
                accountPaymentDomain.getProforma().setIdentify(entity.getProforma().getIdentify());
                accountPaymentDomain.getProforma().setProformaType(entity.getProforma().getProformaType());
                accountPaymentDomain.getProforma().setProformaState(entity.getProforma().getProformaState());
                accountPaymentDomain.getProforma().setImportTotalSoles(entity.getProforma().getImportTotalSoles());
                accountPaymentDomain.getProforma().setImportTotalUsd(entity.getProforma().getImportTotalUsd());
            }
            if (entity.getBank() != null && entity.getBank().getId() != null && !entity.getBank().getId().isEmpty()){
                accountPaymentDomain.setBank(new BankDomain());
                accountPaymentDomain.getBank().setId(entity.getBank().getId());
                accountPaymentDomain.getBank().setName(entity.getBank().getName());
            }
            return accountPaymentDomain;
        }).toList();
    }

    public PageDTO<AccountPaymentDomain> toDomainPageCaseListAccountPayment(Page<AccountPaymentEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<AccountPaymentDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListAccountPayment(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<AccountPaymentDomain> toDomainAllByProformaIdFullData(List<AccountPaymentEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            AccountPaymentDomain domain = new AccountPaymentDomain();
            domain.setId(entity.getId());
            domain.setIdentify(entity.getIdentify());
            domain.setNoItem(entity.getNoItem());
            domain.setPaymentDate(entity.getPaymentDate());
            domain.setNroDocument(entity.getNroDocument());
            domain.setObservation(entity.getObservation());
            domain.setImportMontUsd(entity.getImportMontUsd());
            domain.setImportMontSoles(entity.getImportMontSoles());
            domain.setPaymentType(entity.getPaymentType());

            if (entity.getProforma() != null) {
                ProformaDomain proformaDomain = new ProformaDomain();
                proformaDomain.setId(entity.getProforma().getId());
                proformaDomain.setIdentify(entity.getProforma().getIdentify());
                domain.setProforma(proformaDomain);
            }

            if (entity.getBank() != null) {
                BankDomain bankDomain = new BankDomain();
                bankDomain.setId(entity.getBank().getId());
                bankDomain.setName(entity.getBank().getName());
                domain.setBank(bankDomain);
            }

            if (entity.getChangeType() != null) {
                ChangeTypeDomain changeTypeDomain = new ChangeTypeDomain();
                changeTypeDomain.setId(entity.getChangeType().getId());
                domain.setChangeType(changeTypeDomain);
            }

            return domain;
        }).toList();
    }
}
