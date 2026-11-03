package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwo;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.changetype.ChangeTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.currency.CurrencyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.dateprocess.DateProcessConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.country.CountryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.costsheettwopercent.CostSheetTwoPercentConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.customer.CustomerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CostSheetTwoEntity;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostSheetTwoConverter implements Converter<CostSheetTwoDomain, CostSheetTwoEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private DateProcessConverter dateProcessConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private ChangeTypeConverter changeTypeConverter;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CostSheetTwoPercentConverter costSheetTwoPercentConverter;

    @Autowired
    private RegisterGroupConverter registerGroupConverter;

    @Override
    public CostSheetTwoDomain toDomain(CostSheetTwoEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostSheetTwoDomain costSheetTwoDomain = new CostSheetTwoDomain();

        costSheetTwoDomain.setId( source.getId() );
        costSheetTwoDomain.setDeleted( source.getDeleted() );
        costSheetTwoDomain.setEnabled( source.getEnabled() );
        costSheetTwoDomain.setMigrated( source.getMigrated() );
        costSheetTwoDomain.setCreatedAt( source.getCreatedAt() );
        costSheetTwoDomain.setUpdatedAt( source.getUpdatedAt() );
        costSheetTwoDomain.setDeletedAt( source.getDeletedAt() );
        costSheetTwoDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        costSheetTwoDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        costSheetTwoDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        costSheetTwoDomain.setIdentifierName( source.getIdentifierName() );
        costSheetTwoDomain.setTenant( source.getTenant() );
        costSheetTwoDomain.setName( source.getName() );
        costSheetTwoDomain.setDescription( source.getDescription() );
        costSheetTwoDomain.setIdentify( source.getIdentify() );

        costSheetTwoDomain.setDate( source.getDate() );
        costSheetTwoDomain.setDateInit( source.getDateInit() );
        costSheetTwoDomain.setDateEnd( source.getDateEnd() );
        costSheetTwoDomain.setNoPax( source.getNoPax() );

        costSheetTwoDomain.setTotalProform(source.getTotalProform());
        costSheetTwoDomain.setTotalProfNCred(source.getTotalProfNCred());
        costSheetTwoDomain.setTotalDataProform(source.getTotalDataProform());
        costSheetTwoDomain.setTotalVoucherIngress(source.getTotalVoucherIngress());
        costSheetTwoDomain.setAirValue(source.getAirValue());
        costSheetTwoDomain.setTotalAirValue(source.getTotalAirValue());

        costSheetTwoDomain.setDcInvoice(source.getDcInvoice());
        costSheetTwoDomain.setDcBoleta(source.getDcBoleta());
        costSheetTwoDomain.setDcCobranza(source.getDcCobranza());
        costSheetTwoDomain.setDcVtaPqt(source.getDcVtaPqt());

        costSheetTwoDomain.setDuUtility(source.getDuUtility());

        costSheetTwoDomain.setDistrUtiliInvoiceGastos(source.getDistrUtiliInvoiceGastos());
        costSheetTwoDomain.setDistrUtiliInvoiceUtilidad(source.getDistrUtiliInvoiceUtilidad());
        costSheetTwoDomain.setDistrUtiliInvoiceTotalReal(source.getDistrUtiliInvoiceTotalReal());
        costSheetTwoDomain.setDistrUtiliInvoicePercentReal(source.getDistrUtiliInvoicePercentReal());
        costSheetTwoDomain.setDistrUtiliInvoiceVal(source.getDistrUtiliInvoiceVal());
        costSheetTwoDomain.setDistrUtiliInvoiceDiff(source.getDistrUtiliInvoiceDiff());

        costSheetTwoDomain.setDistrUtiliBoletaGastos(source.getDistrUtiliBoletaGastos());
        costSheetTwoDomain.setDistrUtiliBoletaUtilidad(source.getDistrUtiliBoletaUtilidad());
        costSheetTwoDomain.setDistrUtiliBoletaTotalReal(source.getDistrUtiliBoletaTotalReal());
        costSheetTwoDomain.setDistrUtiliBoletaPercentReal(source.getDistrUtiliBoletaPercentReal());
        costSheetTwoDomain.setDistrUtiliBoletaVal(source.getDistrUtiliBoletaVal());
        costSheetTwoDomain.setDistrUtiliBoletaDiff(source.getDistrUtiliBoletaDiff());

        costSheetTwoDomain.setDistrUtiliCobranzaGastos(source.getDistrUtiliCobranzaGastos());
        costSheetTwoDomain.setDistrUtiliCobranzaTotalReal(source.getDistrUtiliCobranzaTotalReal());

        costSheetTwoDomain.setDistrUtiliTotalGastos(source.getDistrUtiliTotalGastos());
        costSheetTwoDomain.setDistrUtiliTotalUtilidad(source.getDistrUtiliTotalUtilidad());
        costSheetTwoDomain.setDistrUtiliTotalTotalReal(source.getDistrUtiliTotalTotalReal());
        costSheetTwoDomain.setDistrUtiliTotalPercentReal(source.getDistrUtiliTotalPercentReal());
        costSheetTwoDomain.setDistrUtiliTotalVal(source.getDistrUtiliTotalVal());

        costSheetTwoDomain.setTotalValueWithoutIgv(source.getTotalValueWithoutIgv());
        costSheetTwoDomain.setTotalValueWithIgv(source.getTotalValueWithIgv());
        costSheetTwoDomain.setTotalValueInvoiceExporter(source.getTotalValueInvoiceExporter());
        costSheetTwoDomain.setTotalValueBoletaInafect(source.getTotalValueBoletaInafect());
        costSheetTwoDomain.setTotalValueDocCobranza(source.getTotalValueDocCobranza());

        costSheetTwoDomain.setCostSheetTwoState(source.getCostSheetTwoState());

        costSheetTwoDomain.setDateProcess(dateProcessConverter.toDomain(source.getDateProcess()));
        costSheetTwoDomain.setCountry(countryConverter.toDomain(source.getCountry()));
        costSheetTwoDomain.setCurrency(currencyConverter.toDomain(source.getCurrency()));
        costSheetTwoDomain.setChangeType(changeTypeConverter.toDomain(source.getChangeType()));
        costSheetTwoDomain.setCustomer(customerConverter.toDomain(source.getCustomer()));
        costSheetTwoDomain.setCostSheetTwoPercent(costSheetTwoPercentConverter.toDomain(source.getCostSheetTwoPercent()));
        costSheetTwoDomain.setRegisterGroup(registerGroupConverter.toDomain(source.getRegisterGroup()));

        return costSheetTwoDomain;
    }

    @Override
    public CostSheetTwoEntity toEntity(CostSheetTwoDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostSheetTwoEntity costSheetTwoEntity = new CostSheetTwoEntity();

        costSheetTwoEntity.setId( source.getId() );
        costSheetTwoEntity.setDeleted( source.getDeleted() );
        costSheetTwoEntity.setEnabled( source.getEnabled() );
        costSheetTwoEntity.setMigrated( source.getMigrated() );
        costSheetTwoEntity.setCreatedAt( source.getCreatedAt() );
        costSheetTwoEntity.setUpdatedAt( source.getUpdatedAt() );
        costSheetTwoEntity.setDeletedAt( source.getDeletedAt() );
        costSheetTwoEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        costSheetTwoEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        costSheetTwoEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        costSheetTwoEntity.setIdentifierName( source.getIdentifierName() );
        costSheetTwoEntity.setTenant( source.getTenant() );
        costSheetTwoEntity.setName( source.getName() );
        costSheetTwoEntity.setDescription( source.getDescription() );
        costSheetTwoEntity.setIdentify( source.getIdentify() );

        costSheetTwoEntity.setDate( source.getDate() );
        costSheetTwoEntity.setDateInit( source.getDateInit() );
        costSheetTwoEntity.setDateEnd( source.getDateEnd() );
        costSheetTwoEntity.setNoPax( source.getNoPax() );

        costSheetTwoEntity.setTotalProform(source.getTotalProform());
        costSheetTwoEntity.setTotalProfNCred(source.getTotalProfNCred());
        costSheetTwoEntity.setTotalDataProform(source.getTotalDataProform());
        costSheetTwoEntity.setTotalVoucherIngress(source.getTotalVoucherIngress());
        costSheetTwoEntity.setAirValue(source.getAirValue());
        costSheetTwoEntity.setTotalAirValue(source.getTotalAirValue());

        costSheetTwoEntity.setDcInvoice(source.getDcInvoice());
        costSheetTwoEntity.setDcBoleta(source.getDcBoleta());
        costSheetTwoEntity.setDcCobranza(source.getDcCobranza());
        costSheetTwoEntity.setDcVtaPqt(source.getDcVtaPqt());

        costSheetTwoEntity.setDuUtility(source.getDuUtility());

        costSheetTwoEntity.setDistrUtiliInvoiceGastos(source.getDistrUtiliInvoiceGastos());
        costSheetTwoEntity.setDistrUtiliInvoiceUtilidad(source.getDistrUtiliInvoiceUtilidad());
        costSheetTwoEntity.setDistrUtiliInvoiceTotalReal(source.getDistrUtiliInvoiceTotalReal());
        costSheetTwoEntity.setDistrUtiliInvoicePercentReal(source.getDistrUtiliInvoicePercentReal());
        costSheetTwoEntity.setDistrUtiliInvoiceVal(source.getDistrUtiliInvoiceVal());
        costSheetTwoEntity.setDistrUtiliInvoiceDiff(source.getDistrUtiliInvoiceDiff());

        costSheetTwoEntity.setDistrUtiliBoletaGastos(source.getDistrUtiliBoletaGastos());
        costSheetTwoEntity.setDistrUtiliBoletaUtilidad(source.getDistrUtiliBoletaUtilidad());
        costSheetTwoEntity.setDistrUtiliBoletaTotalReal(source.getDistrUtiliBoletaTotalReal());
        costSheetTwoEntity.setDistrUtiliBoletaPercentReal(source.getDistrUtiliBoletaPercentReal());
        costSheetTwoEntity.setDistrUtiliBoletaVal(source.getDistrUtiliBoletaVal());
        costSheetTwoEntity.setDistrUtiliBoletaDiff(source.getDistrUtiliBoletaDiff());

        costSheetTwoEntity.setDistrUtiliCobranzaGastos(source.getDistrUtiliCobranzaGastos());
        costSheetTwoEntity.setDistrUtiliCobranzaTotalReal(source.getDistrUtiliCobranzaTotalReal());

        costSheetTwoEntity.setDistrUtiliTotalGastos(source.getDistrUtiliTotalGastos());
        costSheetTwoEntity.setDistrUtiliTotalUtilidad(source.getDistrUtiliTotalUtilidad());
        costSheetTwoEntity.setDistrUtiliTotalTotalReal(source.getDistrUtiliTotalTotalReal());
        costSheetTwoEntity.setDistrUtiliTotalPercentReal(source.getDistrUtiliTotalPercentReal());
        costSheetTwoEntity.setDistrUtiliTotalVal(source.getDistrUtiliTotalVal());

        costSheetTwoEntity.setTotalValueWithoutIgv(source.getTotalValueWithoutIgv());
        costSheetTwoEntity.setTotalValueWithIgv(source.getTotalValueWithIgv());
        costSheetTwoEntity.setTotalValueInvoiceExporter(source.getTotalValueInvoiceExporter());
        costSheetTwoEntity.setTotalValueBoletaInafect(source.getTotalValueBoletaInafect());
        costSheetTwoEntity.setTotalValueDocCobranza(source.getTotalValueDocCobranza());

        costSheetTwoEntity.setCostSheetTwoState(source.getCostSheetTwoState());

        costSheetTwoEntity.setDateProcess(dateProcessConverter.toEntity(source.getDateProcess()));
        costSheetTwoEntity.setCountry(countryConverter.toEntity(source.getCountry()));
        costSheetTwoEntity.setCurrency(currencyConverter.toEntity(source.getCurrency()));
        costSheetTwoEntity.setChangeType(changeTypeConverter.toEntity(source.getChangeType()));
        costSheetTwoEntity.setCustomer(customerConverter.toEntity(source.getCustomer()));
        costSheetTwoEntity.setCostSheetTwoPercent(costSheetTwoPercentConverter.toEntity(source.getCostSheetTwoPercent()));
        costSheetTwoEntity.setRegisterGroup(registerGroupConverter.toEntity(source.getRegisterGroup()));

        return costSheetTwoEntity;
    }

    public IdBaseDomain toDomainIdBase(CostSheetTwoEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(CostSheetTwoEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<CostSheetTwoEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<CostSheetTwoDomain> toDomainAllSimpleToDomainCaseShowCostSheetTwo(List<CostSheetTwoEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDomain costSheetTwoDomain = new CostSheetTwoDomain();
            costSheetTwoDomain.setId(entity.getId());
            costSheetTwoDomain.setName(entity.getName());
            costSheetTwoDomain.setDescription(entity.getDescription());
            return costSheetTwoDomain;
        }).toList();
    }

    public List<CostSheetTwoDomain> toDomainAllSimpleToDomainCaseListCostSheetTwo(List<CostSheetTwoEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            CostSheetTwoDomain costSheetTwoDomain = new CostSheetTwoDomain();
            costSheetTwoDomain.setId(entity.getId());
            costSheetTwoDomain.setName(entity.getName());
            costSheetTwoDomain.setDescription(entity.getDescription());
            costSheetTwoDomain.setIdentify(entity.getIdentify());
            costSheetTwoDomain.setDate(entity.getDate());
            costSheetTwoDomain.setCostSheetTwoState(entity.getCostSheetTwoState());

            costSheetTwoDomain.setTotalValueWithoutIgv(entity.getTotalValueWithoutIgv());
            costSheetTwoDomain.setTotalValueWithIgv(entity.getTotalValueWithIgv());

            if (entity.getRegisterGroup() != null &&
                    entity.getRegisterGroup().getId() != null &&
                    !entity.getRegisterGroup().getId().isEmpty()){
                costSheetTwoDomain.setRegisterGroup(new RegisterGroupDomain());
                costSheetTwoDomain.getRegisterGroup().setId(entity.getRegisterGroup().getId());
                costSheetTwoDomain.getRegisterGroup().setName(entity.getRegisterGroup().getName());
                costSheetTwoDomain.getRegisterGroup().setIdentify(entity.getRegisterGroup().getIdentify());
            }

            return costSheetTwoDomain;
        }).toList();
    }

    public PageDTO<CostSheetTwoDomain> toDomainPageCaseListCostSheetTwo(Page<CostSheetTwoEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<CostSheetTwoDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListCostSheetTwo(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    public CostSheetTwoDomain toDomainIdSimpleDomainCaseGetModificationData(CostSheetTwoEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        CostSheetTwoDomain costSheetTwoDomain = new CostSheetTwoDomain();
        costSheetTwoDomain.setId( source.getId() );
        costSheetTwoDomain.setTotalValueWithoutIgv(source.getTotalValueWithoutIgv());
        costSheetTwoDomain.setTotalValueWithIgv(source.getTotalValueWithIgv());
        return costSheetTwoDomain;
    }

}
