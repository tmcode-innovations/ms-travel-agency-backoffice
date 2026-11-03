package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregress;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.bank.BankConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.detraction.DetractionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import dev.mm.javaee.core.domain.UserBaseDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherEgressConverter implements Converter<VoucherEgressDomain, VoucherEgressEntity> {

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

    @Autowired
    private BankConverter bankConverter;

    @Autowired
    private DetractionConverter detractionConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Override
    public VoucherEgressDomain toDomain(VoucherEgressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressDomain voucherEgressDomain = new VoucherEgressDomain();

        voucherEgressDomain.setId( source.getId() );
        voucherEgressDomain.setDeleted( source.getDeleted() );
        voucherEgressDomain.setEnabled( source.getEnabled() );
        voucherEgressDomain.setMigrated( source.getMigrated() );
        voucherEgressDomain.setCreatedAt( source.getCreatedAt() );
        voucherEgressDomain.setUpdatedAt( source.getUpdatedAt() );
        voucherEgressDomain.setDeletedAt( source.getDeletedAt() );
        voucherEgressDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        voucherEgressDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        voucherEgressDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        voucherEgressDomain.setIdentifierName( source.getIdentifierName() );
        voucherEgressDomain.setTenant( source.getTenant() );
        voucherEgressDomain.setName( source.getName() );
        voucherEgressDomain.setDescription( source.getDescription() );
        voucherEgressDomain.setIdentify( source.getIdentify() );

        voucherEgressDomain.setYear( source.getYear() );
        voucherEgressDomain.setCantRegister( source.getCantRegister() );
        voucherEgressDomain.setNoCheck( source.getNoCheck() );
        voucherEgressDomain.setVoucherState( source.getVoucherState() );
        voucherEgressDomain.setVoucherType( source.getVoucherType() );
        voucherEgressDomain.setImportTotalSoles( source.getImportTotalSoles() );
        voucherEgressDomain.setImportTotalUsd( source.getImportTotalUsd() );
        voucherEgressDomain.setImportSoles( source.getImportSoles() );
        voucherEgressDomain.setImportUsd( source.getImportUsd() );
        voucherEgressDomain.setDetractionSoles( source.getDetractionSoles() );
        voucherEgressDomain.setDetractionUsd( source.getDetractionUsd() );
        voucherEgressDomain.setDate( source.getDate() );
        voucherEgressDomain.setDateCancel( source.getDateCancel() );
        voucherEgressDomain.setVoucherDetraction( source.getVoucherDetraction() );

        voucherEgressDomain.setDateProcess( dateProcessConverter.toDomain( source.getDateProcess() ) );
        voucherEgressDomain.setChangeType( changeTypeConverter.toDomain( source.getChangeType() ) );
        voucherEgressDomain.setCurrency( currencyConverter.toDomain( source.getCurrency() ) );
        voucherEgressDomain.setResponsible( employeeConverter.toDomain( source.getResponsible() ) );
        voucherEgressDomain.setBank( bankConverter.toDomain( source.getBank() ) );
        voucherEgressDomain.setParent( toDomain( source.getParent() ) );
        voucherEgressDomain.setDetraction( detractionConverter.toDomain( source.getDetraction() ) );

        voucherEgressDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        voucherEgressDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        voucherEgressDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        voucherEgressDomain.setDepartmentRegion( departmentRegionConverter.toDomain( source.getDepartmentRegion() ) );

        voucherEgressDomain.setValueTypeShowSupplier( source.getValueTypeShowSupplier() );
        return voucherEgressDomain;
    }

    @Override
    public VoucherEgressEntity toEntity(VoucherEgressDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressEntity voucherEgressEntity = new VoucherEgressEntity();

        voucherEgressEntity.setId( source.getId() );
        voucherEgressEntity.setDeleted( source.getDeleted() );
        voucherEgressEntity.setEnabled( source.getEnabled() );
        voucherEgressEntity.setMigrated( source.getMigrated() );
        voucherEgressEntity.setCreatedAt( source.getCreatedAt() );
        voucherEgressEntity.setUpdatedAt( source.getUpdatedAt() );
        voucherEgressEntity.setDeletedAt( source.getDeletedAt() );
        voucherEgressEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        voucherEgressEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        voucherEgressEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        voucherEgressEntity.setIdentifierName( source.getIdentifierName() );
        voucherEgressEntity.setTenant( source.getTenant() );
        voucherEgressEntity.setName( source.getName() );
        voucherEgressEntity.setDescription( source.getDescription() );
        voucherEgressEntity.setIdentify( source.getIdentify() );

        voucherEgressEntity.setYear( source.getYear() );
        voucherEgressEntity.setCantRegister( source.getCantRegister() );
        voucherEgressEntity.setNoCheck( source.getNoCheck() );
        voucherEgressEntity.setVoucherState( source.getVoucherState() );
        voucherEgressEntity.setVoucherType( source.getVoucherType() );
        voucherEgressEntity.setImportTotalSoles( source.getImportTotalSoles() );
        voucherEgressEntity.setImportTotalUsd( source.getImportTotalUsd() );
        voucherEgressEntity.setImportSoles( source.getImportSoles() );
        voucherEgressEntity.setImportUsd( source.getImportUsd() );
        voucherEgressEntity.setDetractionSoles( source.getDetractionSoles() );
        voucherEgressEntity.setDetractionUsd( source.getDetractionUsd() );
        voucherEgressEntity.setDate( source.getDate() );
        voucherEgressEntity.setDateCancel( source.getDateCancel() );
        voucherEgressEntity.setVoucherDetraction( source.getVoucherDetraction() );

        voucherEgressEntity.setDateProcess( dateProcessConverter.toEntity( source.getDateProcess() ) );
        voucherEgressEntity.setChangeType( changeTypeConverter.toEntity( source.getChangeType() ) );
        voucherEgressEntity.setCurrency( currencyConverter.toEntity( source.getCurrency() ) );
        voucherEgressEntity.setResponsible( employeeConverter.toEntity( source.getResponsible() ) );
        voucherEgressEntity.setBank( bankConverter.toEntity( source.getBank() ) );
        voucherEgressEntity.setParent( toEntity( source.getParent() ) );
        voucherEgressEntity.setDetraction( detractionConverter.toEntity( source.getDetraction() ) );

        voucherEgressEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        voucherEgressEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        voucherEgressEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        voucherEgressEntity.setDepartmentRegion( departmentRegionConverter.toEntity( source.getDepartmentRegion() ) );

        voucherEgressEntity.setValueTypeShowSupplier( source.getValueTypeShowSupplier() );
        return voucherEgressEntity;
    }

    public IdBaseDomain toDomainIdBase(VoucherEgressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(VoucherEgressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<VoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<VoucherEgressDomain> toDomainAllSimpleToDomainCaseShowVoucherEgress(List<VoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDomain voucherEgressDomain = new VoucherEgressDomain();
            voucherEgressDomain.setId(entity.getId());
            voucherEgressDomain.setName(entity.getName());
            voucherEgressDomain.setDescription(entity.getDescription());
            return voucherEgressDomain;
        }).toList();
    }

    public List<VoucherEgressDomain> toDomainAllSimpleToDomainCaseListVoucherEgress(List<VoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDomain voucherEgressDomain = new VoucherEgressDomain();
            voucherEgressDomain.setId(entity.getId());
            voucherEgressDomain.setName(entity.getName());
            voucherEgressDomain.setDescription(entity.getDescription());
            voucherEgressDomain.setIdentify(entity.getIdentify());
            voucherEgressDomain.setDate(entity.getDate());
            voucherEgressDomain.setVoucherDetraction(entity.getVoucherDetraction());
            voucherEgressDomain.setVoucherState(entity.getVoucherState());
            voucherEgressDomain.setVoucherType(entity.getVoucherType());
            voucherEgressDomain.setNoCheck(entity.getNoCheck());
            voucherEgressDomain.setImportTotalSoles(entity.getImportTotalSoles());
            voucherEgressDomain.setImportTotalUsd(entity.getImportTotalUsd());
            voucherEgressDomain.setImportSoles(entity.getImportTotalSoles());
            voucherEgressDomain.setImportUsd(entity.getImportTotalUsd());
            voucherEgressDomain.setDetractionSoles(entity.getDetractionSoles());
            voucherEgressDomain.setDetractionUsd(entity.getDetractionUsd());
            voucherEgressDomain.setValueTypeShowSupplier(entity.getValueTypeShowSupplier());
            if (entity.getCurrency() != null &&
                    entity.getCurrency().getId() != null &&
                    !entity.getCurrency().getId().isEmpty()){
                voucherEgressDomain.setCurrency(new CurrencyDomain());
                voucherEgressDomain.getCurrency().setId(entity.getCurrency().getId());
                voucherEgressDomain.getCurrency().setName(entity.getCurrency().getName());
                voucherEgressDomain.getCurrency().setAbbrev(entity.getCurrency().getAbbrev());
            }
            if (entity.getBank() != null &&
                    entity.getBank().getId() != null &&
                    !entity.getBank().getId().isEmpty()){
                voucherEgressDomain.setBank(new BankDomain());
                voucherEgressDomain.getBank().setId(entity.getBank().getId());
                voucherEgressDomain.getBank().setName(entity.getBank().getName());
            }
            if (entity.getParent() != null &&
                    entity.getParent().getId() != null &&
                    !entity.getParent().getId().isEmpty()){
                voucherEgressDomain.setParent(new VoucherEgressDomain());
                voucherEgressDomain.getParent().setId(entity.getParent().getId());
                voucherEgressDomain.getParent().setName(entity.getParent().getName());
                voucherEgressDomain.getParent().setIdentify(entity.getParent().getIdentify());
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                voucherEgressDomain.setRegisterGroup(new RegisterGroupDomain());
                voucherEgressDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                voucherEgressDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                voucherEgressDomain.setSupplier(new SupplierDomain());
                voucherEgressDomain.getSupplier().setId(entity.getSupplier().getId());
                voucherEgressDomain.getSupplier().setName(entity.getSupplier().getName());
            }
            if (entity.getSupplierType() != null &&
                    entity.getSupplierType().getId() != null &&
                    !entity.getSupplierType().getId().isEmpty()){
                voucherEgressDomain.setSupplierType(new SupplierTypeDomain());
                voucherEgressDomain.getSupplierType().setId(entity.getSupplierType().getId());
                voucherEgressDomain.getSupplierType().setName(entity.getSupplierType().getName());
            }
            if (entity.getDepartmentRegion() != null &&
                    entity.getDepartmentRegion().getId() != null &&
                    !entity.getDepartmentRegion().getId().isEmpty()){
                voucherEgressDomain.setDepartmentRegion(new DepartmentRegionDomain());
                voucherEgressDomain.getDepartmentRegion().setId(entity.getDepartmentRegion().getId());
                voucherEgressDomain.getDepartmentRegion().setName(entity.getDepartmentRegion().getName());
            }
            if (entity.getCreatedBy() != null &&
                    entity.getCreatedBy().getId() != null &&
                    !entity.getCreatedBy().getId().isEmpty()){
                voucherEgressDomain.setCreatedBy(new UserBaseDomain());
                voucherEgressDomain.getCreatedBy().setId(entity.getCreatedBy().getId());
            }
            return voucherEgressDomain;
        }).toList();
    }

    public PageDTO<VoucherEgressDomain> toDomainPageCaseListVoucherEgress(Page<VoucherEgressEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<VoucherEgressDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListVoucherEgress(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public VoucherEgressDomain toDomainIdSimpleDomainCaseGetModificationData(VoucherEgressEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressDomain voucherEgressDomain = new VoucherEgressDomain();
        voucherEgressDomain.setId( source.getId() );
        voucherEgressDomain.setImportTotalUsd( source.getImportTotalUsd() );
        voucherEgressDomain.setImportTotalSoles( source.getImportTotalSoles() );
        voucherEgressDomain.setImportSoles( source.getImportSoles() );
        voucherEgressDomain.setImportUsd( source.getImportUsd() );
        voucherEgressDomain.setDetractionSoles( source.getDetractionSoles() );
        voucherEgressDomain.setDetractionUsd( source.getDetractionUsd() );
        return voucherEgressDomain;
    }

    public List<VoucherEgressDomain> toDomainAllSimpleToDomainCaseVoucherEgressByParent(List<VoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDomain voucherEgressDomain = new VoucherEgressDomain();
            voucherEgressDomain.setId(entity.getId());
            voucherEgressDomain.setName(entity.getName());
            voucherEgressDomain.setDescription(entity.getDescription());
            return voucherEgressDomain;
        }).toList();
    }

    public List<VoucherEgressDomain> toDomainAllCasePending(List<VoucherEgressEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDomain voucherEgressDomain = new VoucherEgressDomain();
            voucherEgressDomain.setId(entity.getId());
            voucherEgressDomain.setIdentify(entity.getIdentify());
            voucherEgressDomain.setName(entity.getName());
            voucherEgressDomain.setDescription(entity.getDescription());
            return voucherEgressDomain;
        }).toList();
    }
}
