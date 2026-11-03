package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringressdetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringress.VoucherIngressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressDetailEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherIngressDetailConverter implements Converter<VoucherIngressDetailDomain, VoucherIngressDetailEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private VoucherIngressConverter voucherIngressConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Override
    public VoucherIngressDetailDomain toDomain(VoucherIngressDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherIngressDetailDomain voucherIngressDetailDomain = new VoucherIngressDetailDomain();

        voucherIngressDetailDomain.setId( source.getId() );
        voucherIngressDetailDomain.setDeleted( source.getDeleted() );
        voucherIngressDetailDomain.setEnabled( source.getEnabled() );
        voucherIngressDetailDomain.setMigrated( source.getMigrated() );
        voucherIngressDetailDomain.setCreatedAt( source.getCreatedAt() );
        voucherIngressDetailDomain.setUpdatedAt( source.getUpdatedAt() );
        voucherIngressDetailDomain.setDeletedAt( source.getDeletedAt() );
        voucherIngressDetailDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        voucherIngressDetailDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        voucherIngressDetailDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        voucherIngressDetailDomain.setIdentifierName( source.getIdentifierName() );
        voucherIngressDetailDomain.setTenant( source.getTenant() );
        voucherIngressDetailDomain.setName( source.getName() );
        voucherIngressDetailDomain.setDescription( source.getDescription() );
        voucherIngressDetailDomain.setIdentify( source.getIdentify() );
        voucherIngressDetailDomain.setObservation( source.getObservation() );
        voucherIngressDetailDomain.setImportSoles( source.getImportSoles() );
        voucherIngressDetailDomain.setImportUsd( source.getImportUsd() );

        voucherIngressDetailDomain.setChangeType( changeTypeConverter.toDomain( source.getChangeType() ) );
        voucherIngressDetailDomain.setVoucherIngress( voucherIngressConverter.toDomain( source.getVoucherIngress() ) );
        voucherIngressDetailDomain.setCustomer( customerConverter.toDomain( source.getCustomer() ) );
        voucherIngressDetailDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        voucherIngressDetailDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );

        return voucherIngressDetailDomain;
    }

    @Override
    public VoucherIngressDetailEntity toEntity(VoucherIngressDetailDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherIngressDetailEntity voucherIngressDetailEntity = new VoucherIngressDetailEntity();

        voucherIngressDetailEntity.setId( source.getId() );
        voucherIngressDetailEntity.setDeleted( source.getDeleted() );
        voucherIngressDetailEntity.setEnabled( source.getEnabled() );
        voucherIngressDetailEntity.setMigrated( source.getMigrated() );
        voucherIngressDetailEntity.setCreatedAt( source.getCreatedAt() );
        voucherIngressDetailEntity.setUpdatedAt( source.getUpdatedAt() );
        voucherIngressDetailEntity.setDeletedAt( source.getDeletedAt() );
        voucherIngressDetailEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        voucherIngressDetailEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        voucherIngressDetailEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        voucherIngressDetailEntity.setIdentifierName( source.getIdentifierName() );
        voucherIngressDetailEntity.setTenant( source.getTenant() );
        voucherIngressDetailEntity.setName( source.getName() );
        voucherIngressDetailEntity.setDescription( source.getDescription() );
        voucherIngressDetailEntity.setIdentify( source.getIdentify() );

        voucherIngressDetailEntity.setObservation( source.getObservation() );
        voucherIngressDetailEntity.setImportSoles( source.getImportSoles() );
        voucherIngressDetailEntity.setImportUsd( source.getImportUsd() );

        voucherIngressDetailEntity.setChangeType( changeTypeConverter.toEntity( source.getChangeType() ) );
        voucherIngressDetailEntity.setVoucherIngress( voucherIngressConverter.toEntity( source.getVoucherIngress() ) );
        voucherIngressDetailEntity.setCustomer( customerConverter.toEntity( source.getCustomer() ) );
        voucherIngressDetailEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        voucherIngressDetailEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );

        return voucherIngressDetailEntity;
    }

    public IdBaseDomain toDomainIdBase(VoucherIngressDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(VoucherIngressDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<VoucherIngressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<VoucherIngressDetailDomain> toDomainAllSimpleToDomainCaseShowVoucherIngressDetail(List<VoucherIngressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherIngressDetailDomain voucherIngressDetailDomain = new VoucherIngressDetailDomain();
            voucherIngressDetailDomain.setId(entity.getId());
            voucherIngressDetailDomain.setName(entity.getName());
            voucherIngressDetailDomain.setDescription(entity.getDescription());
            voucherIngressDetailDomain.setImportUsd(entity.getImportUsd());
            voucherIngressDetailDomain.setImportSoles(entity.getImportSoles());
            voucherIngressDetailDomain.setObservation(entity.getObservation());
            if (entity.getSupplier() != null){
                voucherIngressDetailDomain.setSupplier( new SupplierDomain() );
                voucherIngressDetailDomain.getSupplier().setId( entity.getSupplier().getId() );
                voucherIngressDetailDomain.getSupplier().setName( entity.getSupplier().getName() );
            }
            if (entity.getCustomer() != null){
                voucherIngressDetailDomain.setCustomer( new CustomerDomain() );
                voucherIngressDetailDomain.getCustomer().setId( entity.getCustomer().getId() );
                voucherIngressDetailDomain.getCustomer().setName( entity.getCustomer().getName() );
            }
            if (entity.getRegisterGroup() != null){
                voucherIngressDetailDomain.setRegisterGroup( new RegisterGroupDomain() );
                voucherIngressDetailDomain.getRegisterGroup().setId( entity.getRegisterGroup().getId() );
                voucherIngressDetailDomain.getRegisterGroup().setName( entity.getRegisterGroup().getName() );
            }
            return voucherIngressDetailDomain;
        }).toList();
    }

    public List<VoucherIngressDetailDomain> toDomainAllSimpleToDomainCaseListVoucherIngressDetail(List<VoucherIngressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherIngressDetailDomain voucherIngressDetailDomain = new VoucherIngressDetailDomain();
            voucherIngressDetailDomain.setId(entity.getId());
            voucherIngressDetailDomain.setName(entity.getName());
            voucherIngressDetailDomain.setDescription(entity.getDescription());
            return voucherIngressDetailDomain;
        }).toList();
    }

    public PageDTO<VoucherIngressDetailDomain> toDomainPageCaseListVoucherIngressDetail(Page<VoucherIngressDetailEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<VoucherIngressDetailDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListVoucherIngressDetail(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<VoucherIngressDetailDomain> toDomainAllSimpleToDomainCaseShowVoucherIngressDetailByRegisterGroup(List<VoucherIngressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherIngressDetailDomain voucherIngressDetailDomain = new VoucherIngressDetailDomain();
            voucherIngressDetailDomain.setId(entity.getId());
            voucherIngressDetailDomain.setName(entity.getName());
            voucherIngressDetailDomain.setDescription(entity.getDescription());
            voucherIngressDetailDomain.setImportUsd(entity.getImportUsd());
            voucherIngressDetailDomain.setImportSoles(entity.getImportSoles());
            return voucherIngressDetailDomain;
        }).toList();
    }

    public List<VoucherIngressDetailDomain> toDomainAllSimpleToDomainCaseShowVoucherIngressDetailByDelete(List<VoucherIngressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherIngressDetailDomain voucherIngressDetailDomain = new VoucherIngressDetailDomain();
            voucherIngressDetailDomain.setId(entity.getId());
            return voucherIngressDetailDomain;
        }).toList();
    }
}
