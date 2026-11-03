package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinancedetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance.DocumentFinanceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.entry.EntryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceDetailEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDetailDomain;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailSoles;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.account.RptDocumentAccountDetailUsd;
import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentFinanceDetailConverter implements Converter<DocumentFinanceDetailDomain, DocumentFinanceDetailEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DocumentFinanceConverter documentFinanceConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private EntryConverter entryConverter;

    @Override
    public DocumentFinanceDetailDomain toDomain(DocumentFinanceDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentFinanceDetailDomain documentFinanceDetailDomain = new DocumentFinanceDetailDomain();

        documentFinanceDetailDomain.setId( source.getId() );
        documentFinanceDetailDomain.setDeleted( source.getDeleted() );
        documentFinanceDetailDomain.setEnabled( source.getEnabled() );
        documentFinanceDetailDomain.setMigrated( source.getMigrated() );
        documentFinanceDetailDomain.setCreatedAt( source.getCreatedAt() );
        documentFinanceDetailDomain.setUpdatedAt( source.getUpdatedAt() );
        documentFinanceDetailDomain.setDeletedAt( source.getDeletedAt() );
        documentFinanceDetailDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        documentFinanceDetailDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        documentFinanceDetailDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        documentFinanceDetailDomain.setIdentifierName( source.getIdentifierName() );
        documentFinanceDetailDomain.setTenant( source.getTenant() );
        documentFinanceDetailDomain.setName( source.getName() );
        documentFinanceDetailDomain.setDescription( source.getDescription() );
        documentFinanceDetailDomain.setIdentify( source.getIdentify() );

        documentFinanceDetailDomain.setPriceUnit(source.getPriceUnit());
        documentFinanceDetailDomain.setImportUsd(source.getImportUsd());
        documentFinanceDetailDomain.setImportSoles(source.getImportSoles());
        documentFinanceDetailDomain.setImportIgvUsd(source.getImportIgvUsd());
        documentFinanceDetailDomain.setImportIgvSoles(source.getImportIgvSoles());
        documentFinanceDetailDomain.setImportTotalUsd(source.getImportTotalUsd());
        documentFinanceDetailDomain.setImportTotalSoles(source.getImportTotalSoles());
        documentFinanceDetailDomain.setCant(source.getCant());
        documentFinanceDetailDomain.setServiceFinance(source.getServiceFinance());
        documentFinanceDetailDomain.setNoItem(source.getNoItem());

        documentFinanceDetailDomain.setDocumentFinance(documentFinanceConverter.toDomain(source.getDocumentFinance()));
        documentFinanceDetailDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        // Rubro
        documentFinanceDetailDomain.setEntry(entryConverter.toDomain(source.getEntry()));

        documentFinanceDetailDomain.setAffectIgv(source.getAffectIgv());

        return documentFinanceDetailDomain;
    }

    @Override
    public DocumentFinanceDetailEntity toEntity(DocumentFinanceDetailDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        DocumentFinanceDetailEntity documentFinanceDetailEntity = new DocumentFinanceDetailEntity();

        documentFinanceDetailEntity.setId( source.getId() );
        documentFinanceDetailEntity.setDeleted( source.getDeleted() );
        documentFinanceDetailEntity.setEnabled( source.getEnabled() );
        documentFinanceDetailEntity.setMigrated( source.getMigrated() );
        documentFinanceDetailEntity.setCreatedAt( source.getCreatedAt() );
        documentFinanceDetailEntity.setUpdatedAt( source.getUpdatedAt() );
        documentFinanceDetailEntity.setDeletedAt( source.getDeletedAt() );
        documentFinanceDetailEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        documentFinanceDetailEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        documentFinanceDetailEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        documentFinanceDetailEntity.setIdentifierName( source.getIdentifierName() );
        documentFinanceDetailEntity.setTenant( source.getTenant() );
        documentFinanceDetailEntity.setName( source.getName() );
        documentFinanceDetailEntity.setDescription( source.getDescription() );
        documentFinanceDetailEntity.setIdentify( source.getIdentify() );

        documentFinanceDetailEntity.setPriceUnit(source.getPriceUnit());
        documentFinanceDetailEntity.setImportUsd(source.getImportUsd());
        documentFinanceDetailEntity.setImportSoles(source.getImportSoles());
        documentFinanceDetailEntity.setImportIgvUsd(source.getImportIgvUsd());
        documentFinanceDetailEntity.setImportIgvSoles(source.getImportIgvSoles());
        documentFinanceDetailEntity.setImportTotalUsd(source.getImportTotalUsd());
        documentFinanceDetailEntity.setImportTotalSoles(source.getImportTotalSoles());
        documentFinanceDetailEntity.setCant(source.getCant());
        documentFinanceDetailEntity.setServiceFinance(source.getServiceFinance());
        documentFinanceDetailEntity.setNoItem(source.getNoItem());

        documentFinanceDetailEntity.setDocumentFinance(documentFinanceConverter.toEntity(source.getDocumentFinance()));
        documentFinanceDetailEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        // Rubro
        documentFinanceDetailEntity.setEntry(entryConverter.toEntity(source.getEntry()));

        documentFinanceDetailEntity.setAffectIgv(source.getAffectIgv());

        return documentFinanceDetailEntity;
    }

    public IdBaseDomain toDomainIdBase(DocumentFinanceDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(DocumentFinanceDetailEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<DocumentFinanceDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<DocumentFinanceDetailDomain> toDomainAllSimpleToDomainCaseShowDocumentFinanceDetail(List<DocumentFinanceDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DocumentFinanceDetailDomain documentFinanceDetailDomain = new DocumentFinanceDetailDomain();
            documentFinanceDetailDomain.setId(entity.getId());

            documentFinanceDetailDomain.setImportUsd(entity.getImportUsd());
            documentFinanceDetailDomain.setImportSoles(entity.getImportSoles());

            documentFinanceDetailDomain.setImportIgvSoles(entity.getImportIgvSoles());
            documentFinanceDetailDomain.setImportIgvUsd(entity.getImportIgvUsd());

            documentFinanceDetailDomain.setImportTotalSoles(entity.getImportTotalSoles());
            documentFinanceDetailDomain.setImportTotalUsd(entity.getImportTotalUsd());

            documentFinanceDetailDomain.setCant(entity.getCant());

            return documentFinanceDetailDomain;
        }).toList();
    }

    public List<DocumentFinanceDetailDomain> toDomainAllSimpleToDomainCaseShowNoItem(List<DocumentFinanceDetailEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            DocumentFinanceDetailDomain documentFinanceDetailDomain = new DocumentFinanceDetailDomain();
            documentFinanceDetailDomain.setId(entity.getId());
            documentFinanceDetailDomain.setNoItem(entity.getNoItem());
            return documentFinanceDetailDomain;
        }).toList();
    }

    public List<RptDocumentAccountDetailUsd> toRptDocumentAccountDetailUsdList(List<DocumentFinanceDetailDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountDetailUsd).toList();
    }

    private RptDocumentAccountDetailUsd toRptDocumentAccountDetailUsd(DocumentFinanceDetailDomain source) {
        if (source == null) {
            return null;
        }
        RptDocumentAccountDetailUsd target = new RptDocumentAccountDetailUsd();
        target.setDescription(source.getName());
        target.setValueusd(NumberUtil.getImportFormat(source.getImportUsd()));
        target.setItemstr(source.getServiceFinance());
        return target;
    }

    public List<RptDocumentAccountDetailSoles> toRptDocumentAccountDetailSolesList(List<DocumentFinanceDetailDomain> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::toRptDocumentAccountDetailSoles).toList();
    }

    private RptDocumentAccountDetailSoles toRptDocumentAccountDetailSoles(DocumentFinanceDetailDomain source) {
        if (source == null) {
            return null;
        }
        RptDocumentAccountDetailSoles target = new RptDocumentAccountDetailSoles();
        target.setDescription(source.getName());
        target.setValuesoles(NumberUtil.getImportFormat(source.getImportSoles()));
        target.setItemstr(source.getServiceFinance());
        return target;
    }
}
