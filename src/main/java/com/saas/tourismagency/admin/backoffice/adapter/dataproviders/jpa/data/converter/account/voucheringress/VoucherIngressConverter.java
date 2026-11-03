package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringress;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherIngressConverter implements Converter<VoucherIngressDomain, VoucherIngressEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DateProcessConverter dateProcessConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public VoucherIngressDomain toDomain(VoucherIngressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherIngressDomain voucherIngressDomain = new VoucherIngressDomain();

        voucherIngressDomain.setId( source.getId() );
        voucherIngressDomain.setDeleted( source.getDeleted() );
        voucherIngressDomain.setEnabled( source.getEnabled() );
        voucherIngressDomain.setMigrated( source.getMigrated() );
        voucherIngressDomain.setCreatedAt( source.getCreatedAt() );
        voucherIngressDomain.setUpdatedAt( source.getUpdatedAt() );
        voucherIngressDomain.setDeletedAt( source.getDeletedAt() );
        voucherIngressDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        voucherIngressDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        voucherIngressDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        voucherIngressDomain.setIdentifierName( source.getIdentifierName() );
        voucherIngressDomain.setTenant( source.getTenant() );
        voucherIngressDomain.setName( source.getName() );
        voucherIngressDomain.setDescription( source.getDescription() );
        voucherIngressDomain.setIdentify( source.getIdentify() );
        voucherIngressDomain.setYear( source.getYear() );
        voucherIngressDomain.setIdentifyOther( source.getIdentifyOther() );
        voucherIngressDomain.setCantRegister( source.getCantRegister() );
        voucherIngressDomain.setImportTotalSoles( source.getImportTotalSoles() );
        voucherIngressDomain.setImportTotalUsd( source.getImportTotalUsd() );
        voucherIngressDomain.setDate( source.getDate() );
        voucherIngressDomain.setVoucherState( source.getVoucherState() );
        voucherIngressDomain.setDateProcess( dateProcessConverter.toDomain( source.getDateProcess() ) );
        voucherIngressDomain.setChangeType( changeTypeConverter.toDomain( source.getChangeType() ) );
        voucherIngressDomain.setCurrency( currencyConverter.toDomain( source.getCurrency() ) );
        voucherIngressDomain.setResponsible( employeeConverter.toDomain( source.getResponsible() ) );

        return voucherIngressDomain;
    }

    @Override
    public VoucherIngressEntity toEntity(VoucherIngressDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherIngressEntity voucherIngressEntity = new VoucherIngressEntity();

        voucherIngressEntity.setId( source.getId() );
        voucherIngressEntity.setDeleted( source.getDeleted() );
        voucherIngressEntity.setEnabled( source.getEnabled() );
        voucherIngressEntity.setMigrated( source.getMigrated() );
        voucherIngressEntity.setCreatedAt( source.getCreatedAt() );
        voucherIngressEntity.setUpdatedAt( source.getUpdatedAt() );
        voucherIngressEntity.setDeletedAt( source.getDeletedAt() );
        voucherIngressEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        voucherIngressEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        voucherIngressEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        voucherIngressEntity.setIdentifierName( source.getIdentifierName() );
        voucherIngressEntity.setTenant( source.getTenant() );
        voucherIngressEntity.setName( source.getName() );
        voucherIngressEntity.setDescription( source.getDescription() );
        voucherIngressEntity.setIdentify( source.getIdentify() );
        voucherIngressEntity.setYear( source.getYear() );
        voucherIngressEntity.setIdentifyOther( source.getIdentifyOther() );
        voucherIngressEntity.setCantRegister( source.getCantRegister() );
        voucherIngressEntity.setImportTotalSoles( source.getImportTotalSoles() );
        voucherIngressEntity.setImportTotalUsd( source.getImportTotalUsd() );
        voucherIngressEntity.setDate( source.getDate() );
        voucherIngressEntity.setVoucherState( source.getVoucherState() );
        voucherIngressEntity.setDateProcess( dateProcessConverter.toEntity( source.getDateProcess() ) );
        voucherIngressEntity.setChangeType( changeTypeConverter.toEntity( source.getChangeType() ) );
        voucherIngressEntity.setCurrency( currencyConverter.toEntity( source.getCurrency() ) );
        voucherIngressEntity.setResponsible( employeeConverter.toEntity( source.getResponsible() ) );

        return voucherIngressEntity;
    }

    public IdBaseDomain toDomainIdBase(VoucherIngressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(VoucherIngressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<VoucherIngressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<VoucherIngressDomain> toDomainAllSimpleToDomainCaseShowVoucherIngress(List<VoucherIngressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherIngressDomain voucherIngressDomain = new VoucherIngressDomain();
            voucherIngressDomain.setId(entity.getId());
            voucherIngressDomain.setName(entity.getName());
            voucherIngressDomain.setDescription(entity.getDescription());
            return voucherIngressDomain;
        }).toList();
    }

    public List<VoucherIngressDomain> toDomainAllSimpleToDomainCaseListVoucherIngress(List<VoucherIngressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherIngressDomain voucherIngressDomain = new VoucherIngressDomain();
            voucherIngressDomain.setId(entity.getId());
            voucherIngressDomain.setDescription(entity.getDescription());
            voucherIngressDomain.setIdentify(entity.getIdentify());
            voucherIngressDomain.setDate(entity.getDate());
            voucherIngressDomain.setImportTotalSoles(entity.getImportTotalSoles());
            voucherIngressDomain.setImportTotalUsd(entity.getImportTotalUsd());
            if (entity.getCurrency() != null &&
                    entity.getCurrency().getId() != null &&
                    !entity.getCurrency().getId().isEmpty()){
                voucherIngressDomain.setCurrency(new CurrencyDomain());
                voucherIngressDomain.getCurrency().setId(entity.getCurrency().getId());
                voucherIngressDomain.getCurrency().setName(entity.getCurrency().getName());
                voucherIngressDomain.getCurrency().setAbbrev(entity.getCurrency().getAbbrev());
            }
            return voucherIngressDomain;
        }).toList();
    }

    public PageDTO<VoucherIngressDomain> toDomainPageCaseListVoucherIngress(Page<VoucherIngressEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<VoucherIngressDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListVoucherIngress(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public VoucherIngressDomain toDomainIdSimpleDomainCaseGetModificationData(VoucherIngressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherIngressDomain voucherIngressDomain = new VoucherIngressDomain();
        voucherIngressDomain.setId( source.getId() );
        voucherIngressDomain.setImportTotalUsd( source.getImportTotalUsd() );
        voucherIngressDomain.setImportTotalSoles( source.getImportTotalSoles() );
        return voucherIngressDomain;
    }
}
