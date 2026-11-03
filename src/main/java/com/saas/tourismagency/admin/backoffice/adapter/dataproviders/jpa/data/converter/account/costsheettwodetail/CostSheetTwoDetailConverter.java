package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwodetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwo.CostSheetTwoConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.servicepqttourist.ServicePqtTouristConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetailitem.VoucherEgressDetailItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.departmentregion.DepartmentRegionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documentaccounttype.DocumentAccountTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.igv.IgvConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CostSheetTwoDetailEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostSheetTwoDetailConverter implements Converter<CostSheetTwoDetailDomain, CostSheetTwoDetailEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CostSheetTwoConverter costSheetTwoConverter;

    @Autowired
    private VoucherEgressDetailItemConverter voucherEgressDetailItemConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private ServicePqtTouristConverter servicePqtTouristConverter;

    @Autowired
    private IgvConverter igvConverter;

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
    private DocumentAccountTypeConverter documentAccountTypeConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Override
    public CostSheetTwoDetailDomain toDomain(CostSheetTwoDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();

        costSheetTwoDetailDomain.setId( source.getId() );
        costSheetTwoDetailDomain.setDeleted( source.getDeleted() );
        costSheetTwoDetailDomain.setEnabled( source.getEnabled() );
        costSheetTwoDetailDomain.setMigrated( source.getMigrated() );
        costSheetTwoDetailDomain.setCreatedAt( source.getCreatedAt() );
        costSheetTwoDetailDomain.setUpdatedAt( source.getUpdatedAt() );
        costSheetTwoDetailDomain.setDeletedAt( source.getDeletedAt() );
        costSheetTwoDetailDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        costSheetTwoDetailDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        costSheetTwoDetailDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        costSheetTwoDetailDomain.setIdentifierName( source.getIdentifierName() );
        costSheetTwoDetailDomain.setTenant( source.getTenant() );
        costSheetTwoDetailDomain.setName( source.getName() );
        costSheetTwoDetailDomain.setDescription( source.getDescription() );
        costSheetTwoDetailDomain.setIdentify( source.getIdentify() );

        costSheetTwoDetailDomain.setNoItem(source.getNoItem());
        costSheetTwoDetailDomain.setNetoImporteUsd(source.getNetoImporteUsd());
        costSheetTwoDetailDomain.setNetoImporteSoles(source.getNetoImporteSoles());
        costSheetTwoDetailDomain.setTotalImporteUsd(source.getTotalImporteUsd());
        costSheetTwoDetailDomain.setTotalImporteSoles(source.getTotalImporteSoles());
        costSheetTwoDetailDomain.setObservation(source.getObservation());
        costSheetTwoDetailDomain.setNetoGeneralUsd(source.getNetoGeneralUsd());
        costSheetTwoDetailDomain.setIgvSfGeneral(source.getIgvSfGeneral());
        costSheetTwoDetailDomain.setIgvCosteGeneral(source.getIgvCosteGeneral());
        costSheetTwoDetailDomain.setIgvTotalGeneral(source.getIgvTotalGeneral());
        costSheetTwoDetailDomain.setInvoiceSaleGeneral(source.getInvoiceSaleGeneral());
        costSheetTwoDetailDomain.setBoletaSaleGeneral(source.getBoletaSaleGeneral());
        costSheetTwoDetailDomain.setDocumentCobranzaGeneral(source.getDocumentCobranzaGeneral());
        costSheetTwoDetailDomain.setNumberDoc(source.getNumberDoc());
        costSheetTwoDetailDomain.setDate(source.getDate());

        costSheetTwoDetailDomain.setCostSheetTwo(costSheetTwoConverter.toDomain(source.getCostSheetTwo()));
        costSheetTwoDetailDomain.setVoucherEgressDetailItem(voucherEgressDetailItemConverter.toDomain(source.getVoucherEgressDetailItem()));
        costSheetTwoDetailDomain.setCurrency(currencyConverter.toDomain(source.getCurrency()));
        costSheetTwoDetailDomain.setServicePqtTourist(servicePqtTouristConverter.toDomain(source.getServicePqtTourist()));
        costSheetTwoDetailDomain.setIgv(igvConverter.toDomain(source.getIgv()));
        costSheetTwoDetailDomain.setSupplier(supplierConverter.toDomain(source.getSupplier()));
        costSheetTwoDetailDomain.setGuideDrive(guideConverter.toDomain(source.getGuideDrive()));
        costSheetTwoDetailDomain.setGuideTransfer(guideConverter.toDomain(source.getGuideTransfer()));
        costSheetTwoDetailDomain.setSupplierType(supplierTypeConverter.toDomain(source.getSupplierType()));
        costSheetTwoDetailDomain.setTouristDestine(touristDestineConverter.toDomain(source.getTouristDestine()));
        costSheetTwoDetailDomain.setDepartmentRegion(departmentRegionConverter.toDomain(source.getDepartmentRegion()));
        costSheetTwoDetailDomain.setDocumentAccountType(documentAccountTypeConverter.toDomain(source.getDocumentAccountType()));
        costSheetTwoDetailDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));

        costSheetTwoDetailDomain.setLink(source.getLink());

        return costSheetTwoDetailDomain;
    }

    @Override
    public CostSheetTwoDetailEntity toEntity(CostSheetTwoDetailDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostSheetTwoDetailEntity costSheetTwoDetailEntity = new CostSheetTwoDetailEntity();

        costSheetTwoDetailEntity.setId( source.getId() );
        costSheetTwoDetailEntity.setDeleted( source.getDeleted() );
        costSheetTwoDetailEntity.setEnabled( source.getEnabled() );
        costSheetTwoDetailEntity.setMigrated( source.getMigrated() );
        costSheetTwoDetailEntity.setCreatedAt( source.getCreatedAt() );
        costSheetTwoDetailEntity.setUpdatedAt( source.getUpdatedAt() );
        costSheetTwoDetailEntity.setDeletedAt( source.getDeletedAt() );
        costSheetTwoDetailEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        costSheetTwoDetailEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        costSheetTwoDetailEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        costSheetTwoDetailEntity.setIdentifierName( source.getIdentifierName() );
        costSheetTwoDetailEntity.setTenant( source.getTenant() );
        costSheetTwoDetailEntity.setName( source.getName() );
        costSheetTwoDetailEntity.setDescription( source.getDescription() );
        costSheetTwoDetailEntity.setIdentify( source.getIdentify() );

        costSheetTwoDetailEntity.setNoItem(source.getNoItem());
        costSheetTwoDetailEntity.setNetoImporteUsd(source.getNetoImporteUsd());
        costSheetTwoDetailEntity.setNetoImporteSoles(source.getNetoImporteSoles());
        costSheetTwoDetailEntity.setTotalImporteUsd(source.getTotalImporteUsd());
        costSheetTwoDetailEntity.setTotalImporteSoles(source.getTotalImporteSoles());
        costSheetTwoDetailEntity.setObservation(source.getObservation());
        costSheetTwoDetailEntity.setNetoGeneralUsd(source.getNetoGeneralUsd());
        costSheetTwoDetailEntity.setIgvSfGeneral(source.getIgvSfGeneral());
        costSheetTwoDetailEntity.setIgvCosteGeneral(source.getIgvCosteGeneral());
        costSheetTwoDetailEntity.setIgvTotalGeneral(source.getIgvTotalGeneral());
        costSheetTwoDetailEntity.setInvoiceSaleGeneral(source.getInvoiceSaleGeneral());
        costSheetTwoDetailEntity.setBoletaSaleGeneral(source.getBoletaSaleGeneral());
        costSheetTwoDetailEntity.setDocumentCobranzaGeneral(source.getDocumentCobranzaGeneral());
        costSheetTwoDetailEntity.setNumberDoc(source.getNumberDoc());
        costSheetTwoDetailEntity.setDate(source.getDate());

        costSheetTwoDetailEntity.setCostSheetTwo(costSheetTwoConverter.toEntity(source.getCostSheetTwo()));
        costSheetTwoDetailEntity.setVoucherEgressDetailItem(voucherEgressDetailItemConverter.toEntity(source.getVoucherEgressDetailItem()));
        costSheetTwoDetailEntity.setCurrency(currencyConverter.toEntity(source.getCurrency()));
        costSheetTwoDetailEntity.setServicePqtTourist(servicePqtTouristConverter.toEntity(source.getServicePqtTourist()));
        costSheetTwoDetailEntity.setIgv(igvConverter.toEntity(source.getIgv()));
        costSheetTwoDetailEntity.setSupplier(supplierConverter.toEntity(source.getSupplier()));
        costSheetTwoDetailEntity.setGuideDrive(guideConverter.toEntity(source.getGuideDrive()));
        costSheetTwoDetailEntity.setGuideTransfer(guideConverter.toEntity(source.getGuideTransfer()));
        costSheetTwoDetailEntity.setSupplierType(supplierTypeConverter.toEntity(source.getSupplierType()));
        costSheetTwoDetailEntity.setTouristDestine(touristDestineConverter.toEntity(source.getTouristDestine()));
        costSheetTwoDetailEntity.setDepartmentRegion(departmentRegionConverter.toEntity(source.getDepartmentRegion()));
        costSheetTwoDetailEntity.setDocumentAccountType(documentAccountTypeConverter.toEntity(source.getDocumentAccountType()));
        costSheetTwoDetailEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));

        costSheetTwoDetailEntity.setLink(source.getLink());
        return costSheetTwoDetailEntity;
    }

    public IdBaseDomain toDomainIdBase(CostSheetTwoDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CostSheetTwoDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CostSheetTwoDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CostSheetTwoDetailDomain> toDomainAllSimpleToDomainCaseShowCostSheetTwoDetail(List<CostSheetTwoDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setId(entity.getId());
            costSheetTwoDetailDomain.setName(entity.getName());
            costSheetTwoDetailDomain.setDescription(entity.getDescription());
            costSheetTwoDetailDomain.setIdentify(entity.getIdentify());
            costSheetTwoDetailDomain.setNoItem(entity.getNoItem());
            costSheetTwoDetailDomain.setNumberDoc(entity.getNumberDoc());
            costSheetTwoDetailDomain.setNetoGeneralUsd(entity.getNetoGeneralUsd());
            costSheetTwoDetailDomain.setIgvTotalGeneral(entity.getIgvTotalGeneral());
            costSheetTwoDetailDomain.setInvoiceSaleGeneral(entity.getInvoiceSaleGeneral());
            costSheetTwoDetailDomain.setBoletaSaleGeneral(entity.getBoletaSaleGeneral());
            costSheetTwoDetailDomain.setDocumentCobranzaGeneral(entity.getDocumentCobranzaGeneral());
            if (entity.getSupplier() != null){
                costSheetTwoDetailDomain.setSupplier( new SupplierDomain() );
                costSheetTwoDetailDomain.getSupplier().setId( entity.getSupplier().getId() );
                costSheetTwoDetailDomain.getSupplier().setName( entity.getSupplier().getName() );
                costSheetTwoDetailDomain.getSupplier().setTypeInheritance( entity.getSupplier().getTypeInheritance() );
            }
            if (entity.getSupplierType() != null){
                costSheetTwoDetailDomain.setSupplierType( new SupplierTypeDomain() );
                costSheetTwoDetailDomain.getSupplierType().setId( entity.getSupplierType().getId() );
                costSheetTwoDetailDomain.getSupplierType().setName( entity.getSupplierType().getName() );
            }
            if (entity.getTouristDestine() != null){
                costSheetTwoDetailDomain.setTouristDestine( new TouristDestineDomain() );
                costSheetTwoDetailDomain.getTouristDestine().setId( entity.getTouristDestine().getId() );
                costSheetTwoDetailDomain.getTouristDestine().setName( entity.getTouristDestine().getName() );
            }
            if (entity.getDepartmentRegion() != null){
                costSheetTwoDetailDomain.setDepartmentRegion( new DepartmentRegionDomain() );
                costSheetTwoDetailDomain.getDepartmentRegion().setId( entity.getDepartmentRegion().getId() );
                costSheetTwoDetailDomain.getDepartmentRegion().setName( entity.getDepartmentRegion().getName() );
            }
            if (entity.getServicePqtTourist() != null){
                costSheetTwoDetailDomain.setServicePqtTourist( new ServicePqtTouristDomain() );
                costSheetTwoDetailDomain.getServicePqtTourist().setId( entity.getServicePqtTourist().getId() );
                costSheetTwoDetailDomain.getServicePqtTourist().setName( entity.getServicePqtTourist().getName() );
            }
            if (entity.getDocumentAccountType() != null){
                costSheetTwoDetailDomain.setDocumentAccountType( new DocumentAccountTypeDomain() );
                costSheetTwoDetailDomain.getDocumentAccountType().setId( entity.getDocumentAccountType().getId() );
                costSheetTwoDetailDomain.getDocumentAccountType().setName( entity.getDocumentAccountType().getName() );
            }
            if (entity.getVoucherEgressDetailItem() != null){
                costSheetTwoDetailDomain.setVoucherEgressDetailItem( new VoucherEgressDetailItemDomain() );
                costSheetTwoDetailDomain.getVoucherEgressDetailItem().setId( entity.getVoucherEgressDetailItem().getId() );
                costSheetTwoDetailDomain.getVoucherEgressDetailItem().setName( entity.getVoucherEgressDetailItem().getName() );
            }
            return costSheetTwoDetailDomain;
        }).toList();
    }

    public List<CostSheetTwoDetailDomain> toDomainAllSimpleToDomainCaseListCostSheetTwoDetail(List<CostSheetTwoDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setId(entity.getId());
            costSheetTwoDetailDomain.setName(entity.getName());
            costSheetTwoDetailDomain.setDescription(entity.getDescription());
            return costSheetTwoDetailDomain;
        }).toList();
    }

    public PageDTO<CostSheetTwoDetailDomain> toDomainPageCaseListCostSheetTwoDetail(Page<CostSheetTwoDetailEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CostSheetTwoDetailDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCostSheetTwoDetail(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public List<CostSheetTwoDetailDomain> toDomainAllSimpleToDomainCaseShowCostSheetTwoDetailOnlyNoItem(List<CostSheetTwoDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setId(entity.getId());
            costSheetTwoDetailDomain.setName(entity.getName());
            costSheetTwoDetailDomain.setDescription(entity.getDescription());
            costSheetTwoDetailDomain.setIdentify(entity.getIdentify());
            costSheetTwoDetailDomain.setNoItem(entity.getNoItem());
            return costSheetTwoDetailDomain;
        }).toList();
    }

    public List<CostSheetTwoDetailDomain> toDomainAllSimpleToDomainCaseShowCostSheetTwoDetailUpdateCalculate(List<CostSheetTwoDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setId(entity.getId());
            costSheetTwoDetailDomain.setName(entity.getName());
            costSheetTwoDetailDomain.setDescription(entity.getDescription());
            costSheetTwoDetailDomain.setIdentify(entity.getIdentify());
            if (entity.getVoucherEgressDetailItem() != null){
                costSheetTwoDetailDomain.setVoucherEgressDetailItem( new VoucherEgressDetailItemDomain() );
                costSheetTwoDetailDomain.getVoucherEgressDetailItem().setId( entity.getVoucherEgressDetailItem().getId() );
                costSheetTwoDetailDomain.getVoucherEgressDetailItem().setName( entity.getVoucherEgressDetailItem().getName() );
            }
            return costSheetTwoDetailDomain;
        }).toList();
    }

    public List<CostSheetTwoDetailDomain> toDomainAllSimpleToDomainCaseShowCostSheetTwoDetailByDelete(List<CostSheetTwoDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setId(entity.getId());
            return costSheetTwoDetailDomain;
        }).toList();
    }

    public List<CostSheetTwoDetailDomain> toDomainAllSimpleToDomainCaseShowCostSheetTwoDetailByExport(List<CostSheetTwoDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setId(entity.getId());
            costSheetTwoDetailDomain.setNetoImporteSoles(entity.getNetoImporteSoles());
            costSheetTwoDetailDomain.setNetoImporteUsd(entity.getNetoImporteUsd());
            costSheetTwoDetailDomain.setTotalImporteSoles(entity.getTotalImporteSoles());
            costSheetTwoDetailDomain.setTotalImporteUsd(entity.getTotalImporteUsd());
            costSheetTwoDetailDomain.setLink(entity.getLink());
            if (entity.getDepartmentRegion() != null &&
                    entity.getDepartmentRegion().getId() != null &&
                    !entity.getDepartmentRegion().getId().isEmpty()) {
                costSheetTwoDetailDomain.setDepartmentRegion( new DepartmentRegionDomain() );
                costSheetTwoDetailDomain.getDepartmentRegion().setId( entity.getDepartmentRegion().getId() );
                costSheetTwoDetailDomain.getDepartmentRegion().setName( entity.getDepartmentRegion().getName() );
            }
            if (entity.getVoucherEgressDetailItem() != null &&
                    entity.getVoucherEgressDetailItem().getId() != null &&
                    !entity.getVoucherEgressDetailItem().getId().isEmpty()) {
                costSheetTwoDetailDomain.setVoucherEgressDetailItem( new VoucherEgressDetailItemDomain() );
                costSheetTwoDetailDomain.getVoucherEgressDetailItem().setId( entity.getVoucherEgressDetailItem().getId() );
                costSheetTwoDetailDomain.getVoucherEgressDetailItem().setName( entity.getVoucherEgressDetailItem().getName() );
                if (entity.getVoucherEgressDetailItem().getVoucherEgressDetail() != null &&
                        entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getId() != null &&
                        !entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getId().isEmpty()) {
                    costSheetTwoDetailDomain.getVoucherEgressDetailItem().setVoucherEgressDetail( new VoucherEgressDetailDomain() );
                    costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setId( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getId() );
                    costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setName( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getName() );
                    costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setDate( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getDate() );
                    if (entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier() != null &&
                            entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().getId() != null &&
                            !entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().getId().isEmpty()) {
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setSupplier( new SupplierDomain() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().setId( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().getId() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().setName( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().getName() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().setSummary( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().getSummary() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().setRuc( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getSupplier().getRuc() );
                    }
                    if (entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getDocumentAccountType() != null &&
                            entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getDocumentAccountType().getId() != null &&
                            !entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getDocumentAccountType().getId().isEmpty()) {
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setDocumentAccountType( new DocumentAccountTypeDomain() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getDocumentAccountType().setId( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getDocumentAccountType().getId() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getDocumentAccountType().setName( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getDocumentAccountType().getName() );
                    }
                    if (entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getCurrency() != null &&
                            entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getCurrency().getId() != null &&
                            !entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getCurrency().getId().isEmpty()) {
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setCurrency( new CurrencyDomain() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getCurrency().setId( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getCurrency().getId() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getCurrency().setName( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getCurrency().getName() );
                    }
                    if (entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getChangeType() != null &&
                            entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getChangeType().getId() != null &&
                            !entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getChangeType().getId().isEmpty()) {
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setChangeType( new ChangeTypeDomain() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getChangeType().setId( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getChangeType().getId() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getChangeType().setPriceSale( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getChangeType().getPriceSale() );
                    }
                    if (entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem() != null &&
                            entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getId() != null &&
                            !entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getId().isEmpty()) {
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().setItineraryItem( new ItineraryItemDomain() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().setId( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getId() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().setName( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getName() );
                        costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().setItineraryDateTime( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getItineraryDateTime() );
                        if (entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getService() != null &&
                                entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getService().getId() != null &&
                                !entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getService().getId().isEmpty()) {
                            costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().setService( new ServiceDomain() );
                            costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getService().setId( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getService().getId() );
                            costSheetTwoDetailDomain.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getService().setName( entity.getVoucherEgressDetailItem().getVoucherEgressDetail().getItineraryItem().getService().getName() );
                        }
                    }
                }
            }
            return costSheetTwoDetailDomain;
        }).toList();
    }
}
