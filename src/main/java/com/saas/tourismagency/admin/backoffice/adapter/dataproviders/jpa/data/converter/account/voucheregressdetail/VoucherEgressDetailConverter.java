package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregress.VoucherEgressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem.ItineraryItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.detraction.DetractionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documentaccounttype.DocumentAccountTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DocumentAccountTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherEgressDetailConverter implements Converter<VoucherEgressDetailDomain, VoucherEgressDetailEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private VoucherEgressConverter voucherEgressConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private SupplierConverter supplierConverter;

    @Autowired
    private GuideConverter guideConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private TouristDestineConverter touristDestineConverter;

    @Autowired
    private DepartmentRegionConverter departmentRegionConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private DocumentAccountTypeConverter documentAccountTypeConverter;

    @Autowired
    private DetractionConverter detractionConverter;

    @Autowired
    private ItineraryItemConverter itineraryItemConverter;

    @Override
    public VoucherEgressDetailDomain toDomain(VoucherEgressDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();

        voucherEgressDetailDomain.setId( source.getId() );
        voucherEgressDetailDomain.setDeleted( source.getDeleted() );
        voucherEgressDetailDomain.setEnabled( source.getEnabled() );
        voucherEgressDetailDomain.setMigrated( source.getMigrated() );
        voucherEgressDetailDomain.setCreatedAt( source.getCreatedAt() );
        voucherEgressDetailDomain.setUpdatedAt( source.getUpdatedAt() );
        voucherEgressDetailDomain.setDeletedAt( source.getDeletedAt() );
        voucherEgressDetailDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        voucherEgressDetailDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        voucherEgressDetailDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        voucherEgressDetailDomain.setIdentifierName( source.getIdentifierName() );
        voucherEgressDetailDomain.setTenant( source.getTenant() );
        voucherEgressDetailDomain.setName( source.getName() );
        voucherEgressDetailDomain.setDescription( source.getDescription() );
        voucherEgressDetailDomain.setIdentify( source.getIdentify() );

        voucherEgressDetailDomain.setNoItem( source.getNoItem() );
        voucherEgressDetailDomain.setYear( source.getYear() );
        voucherEgressDetailDomain.setValueTypeShowSupplier( source.getValueTypeShowSupplier() );
        voucherEgressDetailDomain.setNumberDoc( source.getNumberDoc() );
        voucherEgressDetailDomain.setObservation( source.getDescription() );
        voucherEgressDetailDomain.setDate( source.getDate() );

        voucherEgressDetailDomain.setMontSoles( source.getMontSoles() );
        voucherEgressDetailDomain.setMontUsd( source.getMontUsd() );
        voucherEgressDetailDomain.setDetractionSoles( source.getDetractionSoles() );
        voucherEgressDetailDomain.setDetractionUsd( source.getDetractionUsd() );
        voucherEgressDetailDomain.setBalanceSoles( source.getBalanceSoles() );
        voucherEgressDetailDomain.setBalanceUsd( source.getBalanceUsd() );

        voucherEgressDetailDomain.setVoucherEgress( voucherEgressConverter.toDomain( source.getVoucherEgress() ) );
        voucherEgressDetailDomain.setCurrency( currencyConverter.toDomain( source.getCurrency() ) );
        voucherEgressDetailDomain.setRegisterGroup( registerGroupConverter.toDomain( source.getRegisterGroup() ) );
        voucherEgressDetailDomain.setSupplier( supplierConverter.toDomain( source.getSupplier() ) );
        voucherEgressDetailDomain.setGuideDrive( guideConverter.toDomain( source.getGuideDrive() ) );
        voucherEgressDetailDomain.setGuideTransfer( guideConverter.toDomain( source.getGuideTransfer() ) );
        voucherEgressDetailDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        voucherEgressDetailDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        voucherEgressDetailDomain.setDepartmentRegion( departmentRegionConverter.toDomain( source.getDepartmentRegion() ) );
        voucherEgressDetailDomain.setChangeType( changeTypeConverter.toDomain( source.getChangeType() ) );
        voucherEgressDetailDomain.setDocumentAccountType( documentAccountTypeConverter.toDomain( source.getDocumentAccountType() ) );
        voucherEgressDetailDomain.setDetraction( detractionConverter.toDomain( source.getDetraction() ) );
        voucherEgressDetailDomain.setItineraryItem( itineraryItemConverter.toDomain( source.getItineraryItem() ) );

        voucherEgressDetailDomain.setPaxTotal( source.getPaxTotal() );

        return voucherEgressDetailDomain;
    }

    @Override
    public VoucherEgressDetailEntity toEntity(VoucherEgressDetailDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressDetailEntity voucherEgressDetailEntity = new VoucherEgressDetailEntity();

        voucherEgressDetailEntity.setId( source.getId() );
        voucherEgressDetailEntity.setDeleted( source.getDeleted() );
        voucherEgressDetailEntity.setEnabled( source.getEnabled() );
        voucherEgressDetailEntity.setMigrated( source.getMigrated() );
        voucherEgressDetailEntity.setCreatedAt( source.getCreatedAt() );
        voucherEgressDetailEntity.setUpdatedAt( source.getUpdatedAt() );
        voucherEgressDetailEntity.setDeletedAt( source.getDeletedAt() );
        voucherEgressDetailEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        voucherEgressDetailEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        voucherEgressDetailEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        voucherEgressDetailEntity.setIdentifierName( source.getIdentifierName() );
        voucherEgressDetailEntity.setTenant( source.getTenant() );
        voucherEgressDetailEntity.setName( source.getName() );
        voucherEgressDetailEntity.setDescription( source.getDescription() );
        voucherEgressDetailEntity.setIdentify( source.getIdentify() );

        voucherEgressDetailEntity.setNoItem( source.getNoItem() );
        voucherEgressDetailEntity.setYear( source.getYear() );
        voucherEgressDetailEntity.setValueTypeShowSupplier( source.getValueTypeShowSupplier() );
        voucherEgressDetailEntity.setNumberDoc( source.getNumberDoc() );
        voucherEgressDetailEntity.setObservation( source.getDescription() );
        voucherEgressDetailEntity.setDate( source.getDate() );

        voucherEgressDetailEntity.setMontSoles( source.getMontSoles() );
        voucherEgressDetailEntity.setMontUsd( source.getMontUsd() );
        voucherEgressDetailEntity.setDetractionSoles( source.getDetractionSoles() );
        voucherEgressDetailEntity.setDetractionUsd( source.getDetractionUsd() );
        voucherEgressDetailEntity.setBalanceSoles( source.getBalanceSoles() );
        voucherEgressDetailEntity.setBalanceUsd( source.getBalanceUsd() );

        voucherEgressDetailEntity.setVoucherEgress( voucherEgressConverter.toEntity( source.getVoucherEgress() ) );
        voucherEgressDetailEntity.setCurrency( currencyConverter.toEntity( source.getCurrency() ) );
        voucherEgressDetailEntity.setRegisterGroup( registerGroupConverter.toEntity( source.getRegisterGroup() ) );
        voucherEgressDetailEntity.setSupplier( supplierConverter.toEntity( source.getSupplier() ) );
        voucherEgressDetailEntity.setGuideDrive( guideConverter.toEntity( source.getGuideDrive() ) );
        voucherEgressDetailEntity.setGuideTransfer( guideConverter.toEntity( source.getGuideTransfer() ) );
        voucherEgressDetailEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        voucherEgressDetailEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        voucherEgressDetailEntity.setDepartmentRegion( departmentRegionConverter.toEntity( source.getDepartmentRegion() ) );
        voucherEgressDetailEntity.setChangeType( changeTypeConverter.toEntity( source.getChangeType() ) );
        voucherEgressDetailEntity.setDocumentAccountType( documentAccountTypeConverter.toEntity( source.getDocumentAccountType() ) );
        voucherEgressDetailEntity.setDetraction( detractionConverter.toEntity( source.getDetraction() ) );
        voucherEgressDetailEntity.setItineraryItem( itineraryItemConverter.toEntity( source.getItineraryItem() ) );

        voucherEgressDetailEntity.setPaxTotal( source.getPaxTotal() );

        return voucherEgressDetailEntity;
    }

    public IdBaseDomain toDomainIdBase(VoucherEgressDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(VoucherEgressDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<VoucherEgressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<VoucherEgressDetailDomain> toDomainAllSimpleToDomainCaseShowVoucherEgressDetail(List<VoucherEgressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();
            voucherEgressDetailDomain.setId(entity.getId());
            voucherEgressDetailDomain.setName(entity.getName());
            voucherEgressDetailDomain.setDescription(entity.getDescription());
            voucherEgressDetailDomain.setIdentify(entity.getIdentify());
            voucherEgressDetailDomain.setMontUsd(entity.getMontUsd());
            voucherEgressDetailDomain.setMontSoles(entity.getMontSoles());
            voucherEgressDetailDomain.setBalanceSoles(entity.getBalanceSoles());
            voucherEgressDetailDomain.setBalanceUsd(entity.getBalanceUsd());
            voucherEgressDetailDomain.setDetractionSoles(entity.getDetractionSoles());
            voucherEgressDetailDomain.setDetractionUsd(entity.getDetractionUsd());
            voucherEgressDetailDomain.setObservation(entity.getObservation());
            voucherEgressDetailDomain.setDate(entity.getDate());
            voucherEgressDetailDomain.setNumberDoc(entity.getNumberDoc());
            if (entity.getSupplier() != null){
                voucherEgressDetailDomain.setSupplier( new SupplierDomain() );
                voucherEgressDetailDomain.getSupplier().setId( entity.getSupplier().getId() );
                voucherEgressDetailDomain.getSupplier().setName( entity.getSupplier().getName() );
            }
            if (entity.getRegisterGroup() != null){
                voucherEgressDetailDomain.setRegisterGroup( new RegisterGroupDomain() );
                voucherEgressDetailDomain.getRegisterGroup().setId( entity.getRegisterGroup().getId() );
                voucherEgressDetailDomain.getRegisterGroup().setName( entity.getRegisterGroup().getName() );
            }
            return voucherEgressDetailDomain;
        }).toList();
    }

    public List<VoucherEgressDetailDomain> toDomainAllSimpleToDomainCaseListVoucherEgressDetail(List<VoucherEgressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();
            voucherEgressDetailDomain.setId(entity.getId());
            voucherEgressDetailDomain.setName(entity.getName());
            voucherEgressDetailDomain.setDescription(entity.getDescription());
            return voucherEgressDetailDomain;
        }).toList();
    }

    public PageDTO<VoucherEgressDetailDomain> toDomainPageCaseListVoucherEgressDetail(Page<VoucherEgressDetailEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<VoucherEgressDetailDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListVoucherEgressDetail(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public VoucherEgressDetailDomain toDomainIdSimpleDomainCaseGetModificationData(VoucherEgressDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();
        voucherEgressDetailDomain.setId( source.getId() );
        voucherEgressDetailDomain.setBalanceUsd( source.getBalanceUsd() );
        voucherEgressDetailDomain.setBalanceSoles( source.getBalanceSoles() );
        voucherEgressDetailDomain.setMontUsd( source.getMontUsd() );
        voucherEgressDetailDomain.setMontSoles( source.getMontSoles() );
        voucherEgressDetailDomain.setDetractionSoles( source.getDetractionSoles() );
        voucherEgressDetailDomain.setDetractionUsd( source.getDetractionUsd() );
        return voucherEgressDetailDomain;
    }

    public List<VoucherEgressDetailDomain> toDomainAnalyticsAggregateVoucherPurchaseBySupplier(List<VoucherEgressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();
            voucherEgressDetailDomain.setId(entity.getId());
            voucherEgressDetailDomain.setValueTypeShowSupplier(entity.getValueTypeShowSupplier());
            if (entity.getVoucherEgress() != null){
                voucherEgressDetailDomain.setVoucherEgress( new VoucherEgressDomain() );
                voucherEgressDetailDomain.getVoucherEgress().setId( entity.getVoucherEgress().getId() );
                voucherEgressDetailDomain.getVoucherEgress().setIdentify(entity.getVoucherEgress().getIdentify());
                voucherEgressDetailDomain.getVoucherEgress().setNoCheck(entity.getVoucherEgress().getNoCheck());
                voucherEgressDetailDomain.getVoucherEgress().setDescription(entity.getVoucherEgress().getDescription());
                voucherEgressDetailDomain.getVoucherEgress().setDateCancel(entity.getVoucherEgress().getDateCancel());
                if (entity.getVoucherEgress().getCurrency() != null){
                    voucherEgressDetailDomain.getVoucherEgress().setCurrency(new CurrencyDomain());
                    voucherEgressDetailDomain.getVoucherEgress().getCurrency().setId(entity.getVoucherEgress().getCurrency().getId());
                    voucherEgressDetailDomain.getVoucherEgress().getCurrency().setName(entity.getVoucherEgress().getCurrency().getName());
                }
            }
            if (entity.getDocumentAccountType() != null) {
                voucherEgressDetailDomain.setDocumentAccountType(new DocumentAccountTypeDomain());
                voucherEgressDetailDomain.getDocumentAccountType().setId(entity.getDocumentAccountType().getId());
                voucherEgressDetailDomain.getDocumentAccountType().setName(entity.getDocumentAccountType().getName());
            }
            voucherEgressDetailDomain.setNumberDoc(entity.getNumberDoc());
            voucherEgressDetailDomain.setDate(entity.getDate());
            if (entity.getSupplier() != null) {
                voucherEgressDetailDomain.setSupplier(new SupplierDomain());
                voucherEgressDetailDomain.getSupplier().setId(entity.getSupplier().getId());
                voucherEgressDetailDomain.getSupplier().setName(entity.getSupplier().getName());
            }
            if (entity.getDepartmentRegion() != null) {
                voucherEgressDetailDomain.setDepartmentRegion(new DepartmentRegionDomain());
                voucherEgressDetailDomain.getDepartmentRegion().setId(entity.getDepartmentRegion().getId());
                voucherEgressDetailDomain.getDepartmentRegion().setName(entity.getDepartmentRegion().getName());
            }
            if (entity.getChangeType() != null) {
                voucherEgressDetailDomain.setChangeType(new ChangeTypeDomain());
                voucherEgressDetailDomain.getChangeType().setId(entity.getChangeType().getId());
                voucherEgressDetailDomain.getChangeType().setPriceSale(entity.getChangeType().getPriceSale());
            }
            voucherEgressDetailDomain.setMontSoles(entity.getMontSoles());
            voucherEgressDetailDomain.setMontUsd(entity.getMontUsd());
            voucherEgressDetailDomain.setDetractionSoles(entity.getDetractionSoles());
            voucherEgressDetailDomain.setDetractionUsd(entity.getDetractionUsd());
            voucherEgressDetailDomain.setBalanceSoles(entity.getBalanceSoles());
            voucherEgressDetailDomain.setBalanceUsd(entity.getBalanceUsd());
            return voucherEgressDetailDomain;
        }).toList();
    }

    public PageDTO<VoucherEgressDetailDomain> toDomainPageAnalyticsAggregateVoucherPurchaseBySupplier(Page<VoucherEgressDetailEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<VoucherEgressDetailDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAnalyticsAggregateVoucherPurchaseBySupplier(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<VoucherEgressDetailDomain> toDomainAllSimpleToDomainCaseNoItemVoucherEgressDetail(List<VoucherEgressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();
            voucherEgressDetailDomain.setId(entity.getId());
            voucherEgressDetailDomain.setNoItem(entity.getNoItem());
            return voucherEgressDetailDomain;
        }).toList();
    }

    public List<VoucherEgressDetailDomain> toDomainAllSimpleToDomainCaseShowVoucherEgressDetailByDelete(List<VoucherEgressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();
            voucherEgressDetailDomain.setId(entity.getId());
            if (entity.getItineraryItem() != null &&
                    entity.getItineraryItem().getId() != null &&
                    !entity.getItineraryItem().getId().isEmpty()){
                voucherEgressDetailDomain.setItineraryItem(new ItineraryItemDomain());
                voucherEgressDetailDomain.getItineraryItem().setId(entity.getItineraryItem().getId());
            }
            return voucherEgressDetailDomain;
        }).toList();
    }

    public List<VoucherEgressDetailDomain> toDomainAllSimpleDomainCaseExportPdfByVoucherEgress(List<VoucherEgressDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailDomain voucherEgressDetailDomain = new VoucherEgressDetailDomain();
            voucherEgressDetailDomain.setId(entity.getId());
            voucherEgressDetailDomain.setNumberDoc(entity.getNumberDoc());
            voucherEgressDetailDomain.setDate(entity.getDate());

            voucherEgressDetailDomain.setMontUsd(entity.getMontUsd());
            voucherEgressDetailDomain.setMontSoles(entity.getMontSoles());
            voucherEgressDetailDomain.setDetractionUsd(entity.getDetractionUsd());
            voucherEgressDetailDomain.setDetractionSoles(entity.getDetractionSoles());
            voucherEgressDetailDomain.setBalanceUsd(entity.getBalanceUsd());
            voucherEgressDetailDomain.setBalanceSoles(entity.getBalanceSoles());

            if (entity.getItineraryItem() != null &&
                    entity.getItineraryItem().getId() != null &&
                    !entity.getItineraryItem().getId().isEmpty()){
                voucherEgressDetailDomain.setItineraryItem(new ItineraryItemDomain());
                voucherEgressDetailDomain.getItineraryItem().setId(entity.getItineraryItem().getId());
                if (entity.getItineraryItem().getService() != null &&
                        entity.getItineraryItem().getService().getId() != null &&
                        !entity.getItineraryItem().getService().getId().isEmpty()){
                    voucherEgressDetailDomain.getItineraryItem().setService(new ServiceDomain());
                    voucherEgressDetailDomain.getItineraryItem().getService().setId(entity.getItineraryItem().getService().getId());
                    voucherEgressDetailDomain.getItineraryItem().getService().setName(entity.getItineraryItem().getService().getName());
                }
            }
            if (entity.getDepartmentRegion() != null &&
                    entity.getDepartmentRegion().getId() != null &&
                    !entity.getDepartmentRegion().getId().isEmpty()){
                voucherEgressDetailDomain.setDepartmentRegion(new DepartmentRegionDomain());
                voucherEgressDetailDomain.getDepartmentRegion().setId(entity.getDepartmentRegion().getId());
                voucherEgressDetailDomain.getDepartmentRegion().setName(entity.getDepartmentRegion().getName());
            }
            if (entity.getSupplier() != null &&
                    entity.getSupplier().getId() != null &&
                    !entity.getSupplier().getId().isEmpty()){
                voucherEgressDetailDomain.setSupplier(new SupplierDomain());
                voucherEgressDetailDomain.getSupplier().setId(entity.getSupplier().getId());
                voucherEgressDetailDomain.getSupplier().setName(entity.getSupplier().getName());
            }
            if (entity.getGuideTransfer() != null &&
                    entity.getGuideTransfer().getId() != null &&
                    !entity.getGuideTransfer().getId().isEmpty()){
                voucherEgressDetailDomain.setGuideTransfer(new GuideDomain());
                voucherEgressDetailDomain.getGuideTransfer().setId(entity.getGuideTransfer().getId());
                if (entity.getGuideTransfer().getPerson() != null &&
                        entity.getGuideTransfer().getPerson().getId() != null &&
                        !entity.getGuideTransfer().getPerson().getId().isEmpty()){
                    voucherEgressDetailDomain.getGuideTransfer().setPerson(new PersonDomain());
                    voucherEgressDetailDomain.getGuideTransfer().getPerson().setId(entity.getGuideTransfer().getPerson().getId());
                    voucherEgressDetailDomain.getGuideTransfer().getPerson().setFirstName(entity.getGuideTransfer().getPerson().getFirstName());
                    voucherEgressDetailDomain.getGuideTransfer().getPerson().setLastName(entity.getGuideTransfer().getPerson().getLastName());
                }
            }
            if (entity.getGuideDrive() != null &&
                    entity.getGuideDrive().getId() != null &&
                    !entity.getGuideDrive().getId().isEmpty()){
                voucherEgressDetailDomain.setGuideDrive(new GuideDomain());
                voucherEgressDetailDomain.getGuideDrive().setId(entity.getGuideDrive().getId());
                if (entity.getGuideDrive().getPerson() != null &&
                        entity.getGuideDrive().getPerson().getId() != null &&
                        !entity.getGuideDrive().getPerson().getId().isEmpty()){
                    voucherEgressDetailDomain.getGuideDrive().setPerson(new PersonDomain());
                    voucherEgressDetailDomain.getGuideDrive().getPerson().setId(entity.getGuideDrive().getPerson().getId());
                    voucherEgressDetailDomain.getGuideDrive().getPerson().setFirstName(entity.getGuideDrive().getPerson().getFirstName());
                    voucherEgressDetailDomain.getGuideDrive().getPerson().setLastName(entity.getGuideDrive().getPerson().getLastName());
                }
            }
            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                voucherEgressDetailDomain.setRegisterGroup(new RegisterGroupDomain());
                voucherEgressDetailDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                voucherEgressDetailDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
            }
            return voucherEgressDetailDomain;
        }).toList();
    }
}
