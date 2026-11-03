package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetailitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.servicepqttourist.ServicePqtTouristConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregress.VoucherEgressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetail.VoucherEgressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.detraction.DetractionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.igv.IgvConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailItemEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherEgressDetailItemConverter implements Converter<VoucherEgressDetailItemDomain, VoucherEgressDetailItemEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private VoucherEgressConverter voucherEgressConverter;

    @Autowired
    private VoucherEgressDetailConverter voucherEgressDetailConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private ServicePqtTouristConverter servicePqtTouristConverter;

    @Autowired
    private IgvConverter igvConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Autowired
    private DetractionConverter detractionConverter;

    @Override
    public VoucherEgressDetailItemDomain toDomain(VoucherEgressDetailItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();

        voucherEgressDetailItemDomain.setId( source.getId() );
        voucherEgressDetailItemDomain.setDeleted( source.getDeleted() );
        voucherEgressDetailItemDomain.setEnabled( source.getEnabled() );
        voucherEgressDetailItemDomain.setMigrated( source.getMigrated() );
        voucherEgressDetailItemDomain.setCreatedAt( source.getCreatedAt() );
        voucherEgressDetailItemDomain.setUpdatedAt( source.getUpdatedAt() );
        voucherEgressDetailItemDomain.setDeletedAt( source.getDeletedAt() );
        voucherEgressDetailItemDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        voucherEgressDetailItemDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        voucherEgressDetailItemDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        voucherEgressDetailItemDomain.setIdentifierName( source.getIdentifierName() );
        voucherEgressDetailItemDomain.setTenant( source.getTenant() );
        voucherEgressDetailItemDomain.setName( source.getName() );
        voucherEgressDetailItemDomain.setDescription( source.getDescription() );
        voucherEgressDetailItemDomain.setIdentify( source.getIdentify() );

        voucherEgressDetailItemDomain.setNoItem( source.getNoItem() );
        voucherEgressDetailItemDomain.setObservation( source.getObservation() );
        voucherEgressDetailItemDomain.setNetImportSoles( source.getNetImportSoles() );
        voucherEgressDetailItemDomain.setNetImportUsd( source.getNetImportUsd() );
        voucherEgressDetailItemDomain.setTotalImportSoles( source.getTotalImportSoles() );
        voucherEgressDetailItemDomain.setTotalImportUsd( source.getTotalImportUsd() );

        voucherEgressDetailItemDomain.setNetGeneralUsd( source.getNetGeneralUsd() );
        voucherEgressDetailItemDomain.setIgvSfGeneral( source.getIgvSfGeneral() );
        voucherEgressDetailItemDomain.setIgvCostGeneral( source.getIgvCostGeneral() );
        voucherEgressDetailItemDomain.setIgvTotalGeneral( source.getIgvTotalGeneral() );
        voucherEgressDetailItemDomain.setInvoiceSaleGeneral( source.getInvoiceSaleGeneral() );
        voucherEgressDetailItemDomain.setTicketSaleGeneral( source.getTicketSaleGeneral() );
        voucherEgressDetailItemDomain.setDocumentCollectionGeneral( source.getDocumentCollectionGeneral() );

        voucherEgressDetailItemDomain.setVoucherEgress( voucherEgressConverter.toDomain( source.getVoucherEgress() ) );
        voucherEgressDetailItemDomain.setVoucherEgressDetail( voucherEgressDetailConverter.toDomain( source.getVoucherEgressDetail() ) );
        voucherEgressDetailItemDomain.setCurrency( currencyConverter.toDomain( source.getCurrency() ) );
        voucherEgressDetailItemDomain.setServicePqtTourist( servicePqtTouristConverter.toDomain( source.getServicePqtTourist() ) );
        voucherEgressDetailItemDomain.setIgv( igvConverter.toDomain(source.getIgv() ) );
        voucherEgressDetailItemDomain.setRegisterGroup( registerGroupConverter.toDomain(source.getRegisterGroup() ) );
        voucherEgressDetailItemDomain.setDetraction( detractionConverter.toDomain( source.getDetraction() ) );

        voucherEgressDetailItemDomain.setPaxTotal( source.getPaxTotal() );

        voucherEgressDetailItemDomain.setNetImportSolesTotal( source.getNetImportSolesTotal() );
        voucherEgressDetailItemDomain.setNetImportUsdTotal( source.getNetImportUsdTotal() );
        voucherEgressDetailItemDomain.setTotalImportSolesTotal( source.getTotalImportSolesTotal() );
        voucherEgressDetailItemDomain.setTotalImportUsdTotal( source.getTotalImportUsdTotal() );

        return voucherEgressDetailItemDomain;
    }

    @Override
    public VoucherEgressDetailItemEntity toEntity(VoucherEgressDetailItemDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        VoucherEgressDetailItemEntity voucherEgressDetailItemEntity = new VoucherEgressDetailItemEntity();

        voucherEgressDetailItemEntity.setId( source.getId() );
        voucherEgressDetailItemEntity.setDeleted( source.getDeleted() );
        voucherEgressDetailItemEntity.setEnabled( source.getEnabled() );
        voucherEgressDetailItemEntity.setMigrated( source.getMigrated() );
        voucherEgressDetailItemEntity.setCreatedAt( source.getCreatedAt() );
        voucherEgressDetailItemEntity.setUpdatedAt( source.getUpdatedAt() );
        voucherEgressDetailItemEntity.setDeletedAt( source.getDeletedAt() );
        voucherEgressDetailItemEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        voucherEgressDetailItemEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        voucherEgressDetailItemEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        voucherEgressDetailItemEntity.setIdentifierName( source.getIdentifierName() );
        voucherEgressDetailItemEntity.setTenant( source.getTenant() );
        voucherEgressDetailItemEntity.setName( source.getName() );
        voucherEgressDetailItemEntity.setDescription( source.getDescription() );
        voucherEgressDetailItemEntity.setIdentify( source.getIdentify() );

        voucherEgressDetailItemEntity.setNoItem( source.getNoItem() );
        voucherEgressDetailItemEntity.setObservation( source.getObservation() );
        voucherEgressDetailItemEntity.setNetImportSoles( source.getNetImportSoles() );
        voucherEgressDetailItemEntity.setNetImportUsd( source.getNetImportUsd() );
        voucherEgressDetailItemEntity.setTotalImportSoles( source.getTotalImportSoles() );
        voucherEgressDetailItemEntity.setTotalImportUsd( source.getTotalImportUsd() );

        voucherEgressDetailItemEntity.setNetGeneralUsd( source.getNetGeneralUsd() );
        voucherEgressDetailItemEntity.setIgvSfGeneral( source.getIgvSfGeneral() );
        voucherEgressDetailItemEntity.setIgvCostGeneral( source.getIgvCostGeneral() );
        voucherEgressDetailItemEntity.setIgvTotalGeneral( source.getIgvTotalGeneral() );
        voucherEgressDetailItemEntity.setInvoiceSaleGeneral( source.getInvoiceSaleGeneral() );
        voucherEgressDetailItemEntity.setTicketSaleGeneral( source.getTicketSaleGeneral() );
        voucherEgressDetailItemEntity.setDocumentCollectionGeneral( source.getDocumentCollectionGeneral() );

        voucherEgressDetailItemEntity.setVoucherEgress( voucherEgressConverter.toEntity( source.getVoucherEgress() ) );
        voucherEgressDetailItemEntity.setVoucherEgressDetail( voucherEgressDetailConverter.toEntity( source.getVoucherEgressDetail() ) );
        voucherEgressDetailItemEntity.setCurrency( currencyConverter.toEntity( source.getCurrency() ) );
        voucherEgressDetailItemEntity.setServicePqtTourist( servicePqtTouristConverter.toEntity( source.getServicePqtTourist() ) );
        voucherEgressDetailItemEntity.setIgv( igvConverter.toEntity(source.getIgv() ) );
        voucherEgressDetailItemEntity.setRegisterGroup( registerGroupConverter.toEntity(source.getRegisterGroup() ) );
        voucherEgressDetailItemEntity.setDetraction( detractionConverter.toEntity( source.getDetraction() ) );

        voucherEgressDetailItemEntity.setPaxTotal( source.getPaxTotal() );

        voucherEgressDetailItemEntity.setNetImportSolesTotal( source.getNetImportSolesTotal() );
        voucherEgressDetailItemEntity.setNetImportUsdTotal( source.getNetImportUsdTotal() );
        voucherEgressDetailItemEntity.setTotalImportSolesTotal( source.getTotalImportSolesTotal() );
        voucherEgressDetailItemEntity.setTotalImportUsdTotal( source.getTotalImportUsdTotal() );

        return voucherEgressDetailItemEntity;
    }

    public IdBaseDomain toDomainIdBase(VoucherEgressDetailItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(VoucherEgressDetailItemEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<VoucherEgressDetailItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<VoucherEgressDetailItemDomain> toDomainAllSimpleToDomainCaseShowVoucherEgressDetailItem(List<VoucherEgressDetailItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(entity.getId());
            voucherEgressDetailItemDomain.setName(entity.getName());
            voucherEgressDetailItemDomain.setDescription(entity.getDescription());
            voucherEgressDetailItemDomain.setTotalImportSoles(entity.getTotalImportSoles());
            voucherEgressDetailItemDomain.setTotalImportUsd(entity.getTotalImportUsd());
            voucherEgressDetailItemDomain.setTotalImportSolesTotal(entity.getTotalImportSolesTotal());
            voucherEgressDetailItemDomain.setTotalImportUsdTotal(entity.getTotalImportUsdTotal());
            voucherEgressDetailItemDomain.setObservation(entity.getObservation());
            voucherEgressDetailItemDomain.setNoItem(entity.getNoItem());
            if (entity.getServicePqtTourist() != null){
                voucherEgressDetailItemDomain.setServicePqtTourist( new ServicePqtTouristDomain() );
                voucherEgressDetailItemDomain.getServicePqtTourist().setId( entity.getServicePqtTourist().getId() );
                voucherEgressDetailItemDomain.getServicePqtTourist().setName( entity.getServicePqtTourist().getName() );
            }
            if (entity.getDetraction() != null){
                voucherEgressDetailItemDomain.setDetraction( new DetractionDomain() );
                voucherEgressDetailItemDomain.getDetraction().setId( entity.getDetraction().getId() );
                voucherEgressDetailItemDomain.getDetraction().setName( entity.getDetraction().getName() );
                voucherEgressDetailItemDomain.getDetraction().setAmount( entity.getDetraction().getAmount() );
                voucherEgressDetailItemDomain.getDetraction().setPercent( entity.getDetraction().getPercent() );
            }
            return voucherEgressDetailItemDomain;
        }).toList();
    }

    public List<VoucherEgressDetailItemDomain> toDomainAllSimpleToDomainCaseListVoucherEgressDetailItem(List<VoucherEgressDetailItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(entity.getId());
            voucherEgressDetailItemDomain.setName(entity.getName());
            voucherEgressDetailItemDomain.setDescription(entity.getDescription());
            return voucherEgressDetailItemDomain;
        }).toList();
    }

    public PageDTO<VoucherEgressDetailItemDomain> toDomainPageCaseListVoucherEgressDetailItem(Page<VoucherEgressDetailItemEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<VoucherEgressDetailItemDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListVoucherEgressDetailItem(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<VoucherEgressDetailItemDomain> toDomainAllSimpleToDomainCaseNoItemVoucherEgressDetailItem(List<VoucherEgressDetailItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(entity.getId());
            voucherEgressDetailItemDomain.setNoItem(entity.getNoItem());
            return voucherEgressDetailItemDomain;
        }).toList();
    }

    public List<VoucherEgressDetailItemDomain> toDomainAllSimpleToDomainCaseShowVoucherEgressDetailItemByDelete(List<VoucherEgressDetailItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(entity.getId());
            return voucherEgressDetailItemDomain;
        }).toList();
    }

    public List<VoucherEgressDetailItemDomain> toDomainAllSimpleToDomainCaseUpdateCalculateByRegisterGroup(List<VoucherEgressDetailItemEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(entity.getId());
            voucherEgressDetailItemDomain.setName(entity.getName());
            voucherEgressDetailItemDomain.setDescription(entity.getDescription());

            if (entity.getCurrency() != null &&
                    entity.getCurrency().getId() != null &&
                    !entity.getCurrency().getId().isEmpty()){
                voucherEgressDetailItemDomain.setCurrency(new CurrencyDomain());
                voucherEgressDetailItemDomain.getCurrency().setId(entity.getCurrency().getId());
            }

            voucherEgressDetailItemDomain.setNetImportUsd(entity.getNetImportUsd());
            voucherEgressDetailItemDomain.setNetImportSoles(entity.getNetImportSoles());
            voucherEgressDetailItemDomain.setTotalImportUsd(entity.getTotalImportUsd());
            voucherEgressDetailItemDomain.setTotalImportSoles(entity.getTotalImportSoles());

            if (entity.getServicePqtTourist() != null &&
                    entity.getServicePqtTourist().getId() != null &&
                    !entity.getServicePqtTourist().getId().isEmpty()){
                voucherEgressDetailItemDomain.setServicePqtTourist(new ServicePqtTouristDomain());
                voucherEgressDetailItemDomain.getServicePqtTourist().setId(entity.getServicePqtTourist().getId());
            }

            voucherEgressDetailItemDomain.setObservation(entity.getObservation());
            voucherEgressDetailItemDomain.setNetGeneralUsd(entity.getNetGeneralUsd());
            voucherEgressDetailItemDomain.setIgvSfGeneral(entity.getIgvSfGeneral());
            voucherEgressDetailItemDomain.setIgvCostGeneral(entity.getIgvCostGeneral());
            voucherEgressDetailItemDomain.setIgvTotalGeneral(entity.getIgvTotalGeneral());
            voucherEgressDetailItemDomain.setInvoiceSaleGeneral(entity.getInvoiceSaleGeneral());
            voucherEgressDetailItemDomain.setTicketSaleGeneral(entity.getTicketSaleGeneral());
            voucherEgressDetailItemDomain.setDocumentCollectionGeneral(entity.getDocumentCollectionGeneral());

            if (entity.getIgv() != null &&
                    entity.getIgv().getId() != null &&
                    !entity.getIgv().getId().isEmpty()){
                voucherEgressDetailItemDomain.setIgv(new IgvDomain());
                voucherEgressDetailItemDomain.getIgv().setId(entity.getIgv().getId());
            }

            if (entity.getVoucherEgressDetail() != null &&
                    entity.getVoucherEgressDetail().getId() != null &&
                    !entity.getVoucherEgressDetail().getId().isEmpty()){
                voucherEgressDetailItemDomain.setVoucherEgressDetail(new VoucherEgressDetailDomain());
                voucherEgressDetailItemDomain.getVoucherEgressDetail().setId(entity.getVoucherEgressDetail().getId());

                voucherEgressDetailItemDomain.getVoucherEgressDetail().setNumberDoc(entity.getVoucherEgressDetail().getNumberDoc());
                voucherEgressDetailItemDomain.getVoucherEgressDetail().setDate(entity.getVoucherEgressDetail().getDate());

                if (entity.getVoucherEgressDetail().getVoucherEgress() != null &&
                        entity.getVoucherEgressDetail().getVoucherEgress().getId() != null &&
                        !entity.getVoucherEgressDetail().getVoucherEgress().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setVoucherEgress(new VoucherEgressDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getVoucherEgress().setId(entity.getVoucherEgressDetail().getVoucherEgress().getId());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getVoucherEgress().setIdentify(entity.getVoucherEgressDetail().getVoucherEgress().getIdentify());
                }

                if (entity.getVoucherEgressDetail().getSupplier() != null &&
                        entity.getVoucherEgressDetail().getSupplier().getId() != null &&
                        !entity.getVoucherEgressDetail().getSupplier().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setSupplier(new SupplierDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getSupplier().setId(entity.getVoucherEgressDetail().getSupplier().getId());
                }

                if (entity.getVoucherEgressDetail().getSupplierType() != null &&
                        entity.getVoucherEgressDetail().getSupplierType().getId() != null &&
                        !entity.getVoucherEgressDetail().getSupplierType().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setSupplierType(new SupplierTypeDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getSupplierType().setId(entity.getVoucherEgressDetail().getSupplierType().getId());
                }

                if (entity.getVoucherEgressDetail().getGuideDrive() != null &&
                        entity.getVoucherEgressDetail().getGuideDrive().getId() != null &&
                        !entity.getVoucherEgressDetail().getGuideDrive().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setGuideDrive(new GuideDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getGuideDrive().setId(entity.getVoucherEgressDetail().getGuideDrive().getId());
                }

                if (entity.getVoucherEgressDetail().getGuideTransfer() != null &&
                        entity.getVoucherEgressDetail().getGuideTransfer().getId() != null &&
                        !entity.getVoucherEgressDetail().getGuideTransfer().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setGuideTransfer(new GuideDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getGuideTransfer().setId(entity.getVoucherEgressDetail().getGuideTransfer().getId());
                }

                if (entity.getVoucherEgressDetail().getTouristDestine() != null &&
                        entity.getVoucherEgressDetail().getTouristDestine().getId() != null &&
                        !entity.getVoucherEgressDetail().getTouristDestine().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setTouristDestine(new TouristDestineDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getTouristDestine().setId(entity.getVoucherEgressDetail().getTouristDestine().getId());
                }

                if (entity.getVoucherEgressDetail().getDepartmentRegion() != null &&
                        entity.getVoucherEgressDetail().getDepartmentRegion().getId() != null &&
                        !entity.getVoucherEgressDetail().getDepartmentRegion().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setDepartmentRegion(new DepartmentRegionDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getDepartmentRegion().setId(entity.getVoucherEgressDetail().getDepartmentRegion().getId());
                }

                if (entity.getVoucherEgressDetail().getDepartmentRegion() != null &&
                        entity.getVoucherEgressDetail().getDepartmentRegion().getId() != null &&
                        !entity.getVoucherEgressDetail().getDepartmentRegion().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setDepartmentRegion(new DepartmentRegionDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getDepartmentRegion().setId(entity.getVoucherEgressDetail().getDepartmentRegion().getId());
                }

                if (entity.getVoucherEgressDetail().getDocumentAccountType() != null &&
                        entity.getVoucherEgressDetail().getDocumentAccountType().getId() != null &&
                        !entity.getVoucherEgressDetail().getDocumentAccountType().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setDocumentAccountType(new DocumentAccountTypeDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getDocumentAccountType().setId(entity.getVoucherEgressDetail().getDocumentAccountType().getId());
                }

                if (entity.getVoucherEgressDetail().getChangeType() != null &&
                        entity.getVoucherEgressDetail().getChangeType().getId() != null &&
                        !entity.getVoucherEgressDetail().getChangeType().getId().isEmpty()){
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().setChangeType(new ChangeTypeDomain());
                    voucherEgressDetailItemDomain.getVoucherEgressDetail().getChangeType().setId(entity.getVoucherEgressDetail().getChangeType().getId());
                }
            }

            return voucherEgressDetailItemDomain;
        }).toList();
    }

}
