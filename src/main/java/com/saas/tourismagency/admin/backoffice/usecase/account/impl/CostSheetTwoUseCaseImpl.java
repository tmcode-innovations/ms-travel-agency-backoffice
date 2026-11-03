package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwo.CostSheetTwoConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultCostSheetTwo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwo.CostSheetTwoRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwo.PersistCostSheetTwoNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwodetail.CostSheetTwoDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformacreditnote.ProformaCreditNoteRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice.ProformaInvoiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem.VoucherEgressDetailItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringressdetail.VoucherIngressDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.costsheettwopercent.CostSheetTwoPercentRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwo.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.CostSheetTwoDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.costsheettwo.JRCostSheetTwoRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.voucheregress.JRVoucherEgressRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.CostSheetTwoStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CostSheetTwoPercentDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.CostSheetTwoDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.CostSheetTwoUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CostSheetTwoUseCaseImpl implements CostSheetTwoUseCase {

    @Autowired
    private CostSheetTwoRepo repo;

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CostSheetTwoPercentRepo costSheetTwoPercentRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private CostSheetTwoDetailRepo costSheetTwoDetailRepo;

    @Autowired
    private VoucherEgressDetailItemRepo voucherEgressDetailItemRepo;

    @Autowired
    private VoucherIngressDetailRepo voucherIngressDetailRepo;

    @Autowired
    private ProformaInvoiceRepo proformaInvoiceRepo;

    @Autowired
    private ProformaCreditNoteRepo proformaCreditNoteRepo;

    @Autowired
    private CostSheetTwoConverter converter;

    @Autowired
    PersistCostSheetTwoNumberCallbackImpl persistCostSheetTwoNumberCallback;

    @Autowired
    private SetFieldDefaultCostSheetTwo setFieldDefault;

    @Autowired
    private CostSheetTwoDetailUseCase costSheetTwoDetailUseCase;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRCostSheetTwoRepository jrCostSheetTwoRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public CostSheetTwoResponse create(CostSheetTwoCreateRequest request) {

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        DateProcessDomain dateProcessDomain = null;
        if (request.getDateProcess() != null && !request.getDateProcess().isEmpty()){
            IdBaseDomain dateProcessIdBaseDomain = dateProcessRepo.findByIdEntity(request.getDateProcess());
            dateProcessDomain = new DateProcessDomain();
            dateProcessDomain.setId(dateProcessIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            IdBaseDomain countryIdBaseDomain = countryRepo.findByIdEntity(request.getCountry());
            countryDomain = new CountryDomain();
            countryDomain.setId(countryIdBaseDomain.getId());
        }

        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }

        CostSheetTwoPercentDomain costSheetTwoPercentDomain = null;
        if (request.getCostSheetTwoPercent() != null && !request.getCostSheetTwoPercent().isEmpty()){
            costSheetTwoPercentDomain = costSheetTwoPercentRepo.findByIdDomain(request.getCostSheetTwoPercent());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        CostSheetTwoDomain newDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                costSheetTwoPercentDomain,
                registerGroupDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistCostSheetTwoNumberCallback.generateNumber());
        newDomain.setCostSheetTwoState(CostSheetTwoStateEnum.PENDING.getName());

        CostSheetTwoDomain createdDomain = repo.create(newDomain);

        Double netoGeneralUsd = (double)0;
        Double igvTotalGeneral = (double)0;
        Double invoiceSaleGeneral = (double)0;
        Double boletaSaleGeneral = (double)0;
        Double documentCobranzaGeneral = (double)0;

        Integer intItem = 1;

        List<VoucherEgressDetailItemDomain> listVoucherEgressDetailItem = voucherEgressDetailItemRepo.searchAllByRegisterGroup(registerGroupDomain.getId());
        listVoucherEgressDetailItem = listVoucherEgressDetailItem.stream().collect(Collectors.toList());

        for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomain : listVoucherEgressDetailItem){

            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setNoItem(intItem);
            intItem = intItem + 1;
            costSheetTwoDetailDomain.setVoucherEgressDetailItem(voucherEgressDetailItemDomain);

            costSheetTwoDetailDomain.setCurrency(voucherEgressDetailItemDomain.getCurrency());
            costSheetTwoDetailDomain.setNetoImporteUsd(voucherEgressDetailItemDomain.getNetImportUsd());
            costSheetTwoDetailDomain.setNetoImporteSoles(voucherEgressDetailItemDomain.getNetImportSoles());
            costSheetTwoDetailDomain.setTotalImporteUsd(voucherEgressDetailItemDomain.getTotalImportUsd());
            costSheetTwoDetailDomain.setTotalImporteSoles(voucherEgressDetailItemDomain.getTotalImportSoles());
            costSheetTwoDetailDomain.setServicePqtTourist(voucherEgressDetailItemDomain.getServicePqtTourist());
            costSheetTwoDetailDomain.setObservation(voucherEgressDetailItemDomain.getObservation());
            costSheetTwoDetailDomain.setNetoGeneralUsd(voucherEgressDetailItemDomain.getNetGeneralUsd());
            costSheetTwoDetailDomain.setIgvSfGeneral(voucherEgressDetailItemDomain.getIgvSfGeneral());
            costSheetTwoDetailDomain.setIgvCosteGeneral(voucherEgressDetailItemDomain.getIgvCostGeneral());
            costSheetTwoDetailDomain.setIgvTotalGeneral(voucherEgressDetailItemDomain.getIgvTotalGeneral());
            costSheetTwoDetailDomain.setInvoiceSaleGeneral(voucherEgressDetailItemDomain.getInvoiceSaleGeneral());
            costSheetTwoDetailDomain.setBoletaSaleGeneral(voucherEgressDetailItemDomain.getTicketSaleGeneral());
            costSheetTwoDetailDomain.setDocumentCobranzaGeneral(voucherEgressDetailItemDomain.getDocumentCollectionGeneral());
            costSheetTwoDetailDomain.setIgv(voucherEgressDetailItemDomain.getIgv());

            costSheetTwoDetailDomain.setIdentify(voucherEgressDetailItemDomain.getVoucherEgressDetail().getVoucherEgress().getIdentify());
            costSheetTwoDetailDomain.setSupplier(voucherEgressDetailItemDomain.getVoucherEgressDetail().getSupplier());
            costSheetTwoDetailDomain.setSupplierType(voucherEgressDetailItemDomain.getVoucherEgressDetail().getSupplierType());
            costSheetTwoDetailDomain.setGuideDrive(voucherEgressDetailItemDomain.getVoucherEgressDetail().getGuideDrive());
            costSheetTwoDetailDomain.setGuideTransfer(voucherEgressDetailItemDomain.getVoucherEgressDetail().getGuideTransfer());
            costSheetTwoDetailDomain.setTouristDestine(voucherEgressDetailItemDomain.getVoucherEgressDetail().getTouristDestine());
            costSheetTwoDetailDomain.setDepartmentRegion(voucherEgressDetailItemDomain.getVoucherEgressDetail().getDepartmentRegion());
            costSheetTwoDetailDomain.setDocumentAccountType(voucherEgressDetailItemDomain.getVoucherEgressDetail().getDocumentAccountType());
            costSheetTwoDetailDomain.setNumberDoc(voucherEgressDetailItemDomain.getVoucherEgressDetail().getNumberDoc());
            costSheetTwoDetailDomain.setDate(voucherEgressDetailItemDomain.getVoucherEgressDetail().getDate());
            costSheetTwoDetailDomain.setChangeType(voucherEgressDetailItemDomain.getVoucherEgressDetail().getChangeType());

            costSheetTwoDetailDomain.setCostSheetTwo(createdDomain);

            costSheetTwoDetailDomain.setTenant(UserResolver.resolveUser().getTenant());

            costSheetTwoDetailRepo.create(costSheetTwoDetailDomain);

            netoGeneralUsd = netoGeneralUsd + costSheetTwoDetailDomain.getNetoGeneralUsd();
            igvTotalGeneral = igvTotalGeneral + costSheetTwoDetailDomain.getIgvTotalGeneral();
            invoiceSaleGeneral = invoiceSaleGeneral + costSheetTwoDetailDomain.getInvoiceSaleGeneral();
            boletaSaleGeneral = boletaSaleGeneral + costSheetTwoDetailDomain.getBoletaSaleGeneral();
            documentCobranzaGeneral = documentCobranzaGeneral + costSheetTwoDetailDomain.getDocumentCobranzaGeneral();

        }

        createdDomain.setTotalValueWithoutIgv(netoGeneralUsd);
        createdDomain.setTotalValueWithIgv(igvTotalGeneral);
        createdDomain.setTotalValueInvoiceExporter(invoiceSaleGeneral);
        createdDomain.setTotalValueBoletaInafect(boletaSaleGeneral);
        createdDomain.setTotalValueDocCobranza(documentCobranzaGeneral);

        List<VoucherIngressDetailDomain> listVoucherIngressDetail = voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimpleByRegisterGroup(registerGroupDomain.getId());
        listVoucherIngressDetail = listVoucherIngressDetail.stream().collect(Collectors.toList());

        Double voucherIngressTotalUsd = (double)0;

        for (VoucherIngressDetailDomain voucherIngressDetailDomain : listVoucherIngressDetail) {
            voucherIngressTotalUsd = voucherIngressTotalUsd + voucherIngressDetailDomain.getImportUsd();
        }

        createdDomain.setTotalProform((double)0);
        createdDomain.setTotalProfNCred((double)0);

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){

            if (registerGroupDomain.getQuotation().getAmount() != null && registerGroupDomain.getQuotation().getAmount() > 0){
                createdDomain.setTotalProform(registerGroupDomain.getQuotation().getAmount());
            }else{
                createdDomain.setTotalProform((double)0);
            }

            createdDomain.setTotalProfNCred((double)0);

            createdDomain.setTotalVoucherIngress(voucherIngressTotalUsd);

        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){

            createdDomain.setTotalProform(0.00);

            List<ProformaInvoiceDomain> listProformaInvoice = proformaInvoiceRepo.searchAllByRegisterGroup(registerGroupDomain.getId());
            if (listProformaInvoice != null && !listProformaInvoice.isEmpty()) {
                for (ProformaInvoiceDomain proformaInvoice : listProformaInvoice) {
                    createdDomain.setTotalProform(createdDomain.getTotalProform() + proformaInvoice.getImportTotalUsd());
                }
            }else{
                createdDomain.setTotalProform(0.00);
            }

            createdDomain.setTotalProfNCred(0.00);

            List<ProformaCreditNoteDomain> listProformaCreditNote = proformaCreditNoteRepo.searchAllByRegisterGroup(registerGroupDomain.getId());
            if (listProformaCreditNote != null && !listProformaCreditNote.isEmpty()) {
                for (ProformaCreditNoteDomain proformaCreditNote : listProformaCreditNote) {
                    createdDomain.setTotalProfNCred(createdDomain.getTotalProfNCred() + proformaCreditNote.getImportTotalUsd());
                }
            }else{
                createdDomain.setTotalProfNCred(0.00);
            }

        }

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            createdDomain.setTotalDataProform(createdDomain.getTotalVoucherIngress() - createdDomain.getTotalProfNCred());
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            createdDomain.setTotalDataProform(createdDomain.getTotalProform() - createdDomain.getTotalProfNCred());
        }

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            if (createdDomain.getAirValue() == null){
                createdDomain.setAirValue((double)0);
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            createdDomain.setAirValue(calculateAirValueFromVoucherEgressDetails(listVoucherEgressDetailItem));
        }
        createdDomain.setTotalAirValue(createdDomain.getTotalDataProform() - createdDomain.getAirValue() );

        createdDomain.setDcInvoice(createdDomain.getTotalValueInvoiceExporter());
        createdDomain.setDcBoleta(createdDomain.getTotalValueBoletaInafect());
        createdDomain.setDcCobranza(createdDomain.getTotalValueDocCobranza());

        createdDomain.setDcVtaPqt(createdDomain.getDcInvoice() + createdDomain.getDcBoleta() + createdDomain.getDcCobranza());

        createdDomain.setDuUtility( createdDomain.getTotalDataProform() - createdDomain.getDcVtaPqt() );

        Double factorUtility = (double)0;
        if ( createdDomain.getDcVtaPqt() != null && !createdDomain.getDcVtaPqt().equals((double)0) ){
            factorUtility = createdDomain.getTotalDataProform() / createdDomain.getDcVtaPqt();
        }

        Double percentInvoice = (double)30;
        Double percentBoleta = (double)70;
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName()) && costSheetTwoPercentDomain != null){
            if (costSheetTwoPercentDomain.getPercentMin() != null){
                percentInvoice = costSheetTwoPercentDomain.getPercentMin();
            }
            if (costSheetTwoPercentDomain.getPercentMax() != null){
                percentBoleta = costSheetTwoPercentDomain.getPercentMax();
            }
        }

        createdDomain.setDistrUtiliInvoiceGastos( createdDomain.getDcInvoice() );
        createdDomain.setDistrUtiliInvoiceTotalReal( createdDomain.getDcInvoice() * factorUtility );
        createdDomain.setDistrUtiliInvoiceUtilidad( createdDomain.getDistrUtiliInvoiceTotalReal() - createdDomain.getDcInvoice() );
        if ( createdDomain.getTotalDataProform() != null && createdDomain.getTotalDataProform() != 0 ){
            createdDomain.setDistrUtiliInvoicePercentReal( createdDomain.getDistrUtiliInvoiceTotalReal() * 100 / createdDomain.getTotalDataProform() );
        }else{
            createdDomain.setDistrUtiliInvoicePercentReal((double)0);
        }

        if ( companyDomain.getSetting().getDistributionInvoiceExpenses() != null && companyDomain.getSetting().getDistributionInvoiceExpenses() > 0 ){
            createdDomain.setDistrUtiliInvoiceVal(createdDomain.getTotalAirValue() * companyDomain.getSetting().getDistributionInvoiceExpenses() / 100);
        }else{
            createdDomain.setDistrUtiliInvoiceVal(createdDomain.getTotalAirValue() * percentInvoice / 100);
        }

        createdDomain.setDistrUtiliInvoiceDiff(createdDomain.getDistrUtiliInvoiceTotalReal() - createdDomain.getDistrUtiliInvoiceVal());

        createdDomain.setDistrUtiliBoletaGastos( createdDomain.getDcBoleta() );
        createdDomain.setDistrUtiliBoletaTotalReal( createdDomain.getDcBoleta() * factorUtility );
        createdDomain.setDistrUtiliBoletaUtilidad( createdDomain.getDistrUtiliBoletaTotalReal() - createdDomain.getDcBoleta() );
        if ( createdDomain.getTotalDataProform() != null && createdDomain.getTotalDataProform() != 0 ){
            createdDomain.setDistrUtiliBoletaPercentReal( createdDomain.getDistrUtiliBoletaTotalReal() * 100 / createdDomain.getTotalDataProform() );
        }else{
            createdDomain.setDistrUtiliBoletaPercentReal((double)0);
        }

        if ( companyDomain.getSetting().getDistributionTicketExpenses() != null && companyDomain.getSetting().getDistributionTicketExpenses() > 0 ){
            createdDomain.setDistrUtiliBoletaVal(createdDomain.getTotalAirValue() * companyDomain.getSetting().getDistributionTicketExpenses() / 100);
        }else{
            createdDomain.setDistrUtiliBoletaVal(createdDomain.getTotalAirValue() * percentBoleta / 100);
        }

        createdDomain.setDistrUtiliBoletaDiff(createdDomain.getDistrUtiliBoletaTotalReal() - createdDomain.getDistrUtiliBoletaVal());

        createdDomain.setDistrUtiliCobranzaGastos( createdDomain.getDistrUtiliCobranzaGastos() );
        createdDomain.setDistrUtiliCobranzaTotalReal( createdDomain.getDistrUtiliCobranzaTotalReal() );

        createdDomain.setDistrUtiliTotalGastos( createdDomain.getDistrUtiliInvoiceGastos() + createdDomain.getDistrUtiliBoletaGastos() + createdDomain.getDistrUtiliCobranzaGastos() );
        createdDomain.setDistrUtiliTotalUtilidad( createdDomain.getDistrUtiliInvoiceUtilidad() + createdDomain.getDistrUtiliBoletaUtilidad() );
        createdDomain.setDistrUtiliTotalTotalReal( createdDomain.getDistrUtiliInvoiceTotalReal() + createdDomain.getDistrUtiliBoletaTotalReal() + createdDomain.getDistrUtiliCobranzaTotalReal() );
        createdDomain.setDistrUtiliTotalPercentReal( createdDomain.getDistrUtiliInvoicePercentReal() + createdDomain.getDistrUtiliBoletaPercentReal() );
        createdDomain.setDistrUtiliTotalVal( createdDomain.getDistrUtiliInvoiceVal() + createdDomain.getDistrUtiliBoletaVal() );

        CostSheetTwoDomain costSheetTwoDomainEdit = repo.edit(createdDomain);

        return CostSheetTwoResponse.from(costSheetTwoDomainEdit);
    }

    @Override
    @Transactional
    public CostSheetTwoResponse edit(String id, CostSheetTwoUpdateRequest request) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        if (id == null) {
            return null;
        }
        CostSheetTwoDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        DateProcessDomain dateProcessDomain = null;
        if (request.getDateProcess() != null && !request.getDateProcess().isEmpty()){
            IdBaseDomain dateProcessIdBaseDomain = dateProcessRepo.findByIdEntity(request.getDateProcess());
            dateProcessDomain = new DateProcessDomain();
            dateProcessDomain.setId(dateProcessIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            IdBaseDomain countryIdBaseDomain = countryRepo.findByIdEntity(request.getCountry());
            countryDomain = new CountryDomain();
            countryDomain.setId(countryIdBaseDomain.getId());
        }

        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }

        CostSheetTwoPercentDomain costSheetTwoPercentDomain = null;
        if (request.getCostSheetTwoPercent() != null && !request.getCostSheetTwoPercent().isEmpty()){
            IdBaseDomain costSheetTwoPercentIdBaseDomain = costSheetTwoPercentRepo.findByIdEntity(request.getCostSheetTwoPercent());
            costSheetTwoPercentDomain = new CostSheetTwoPercentDomain();
            costSheetTwoPercentDomain.setId(costSheetTwoPercentIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        CostSheetTwoDomain costSheetTwoDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                costSheetTwoPercentDomain,
                registerGroupDomain
        );
        costSheetTwoDomain.setIdentify(old.getIdentify());
        costSheetTwoDomain.setCostSheetTwoState(old.getCostSheetTwoState());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName()) && registerGroupDomain != null){
            costSheetTwoDomain.setAirValue(calculateAirValueFromVoucherEgressDetails(voucherEgressDetailItemRepo.searchAllByRegisterGroup(registerGroupDomain.getId())));
        }

        CostSheetTwoDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costSheetTwoDomain, old));
        return CostSheetTwoResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CostSheetTwoResponse old = findById(id);
        List<CostSheetTwoDetailDomain> detailsByDelete = costSheetTwoDetailRepo.searchAllByCostSheetTwoCaseListSimpleByDelete(id);
        if (detailsByDelete != null && !detailsByDelete.isEmpty()){
            for (CostSheetTwoDetailDomain costSheetTwoDetailDomain : detailsByDelete){
                costSheetTwoDetailRepo.deleteById(costSheetTwoDetailDomain.getId());
            }
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostSheetTwoResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> CostSheetTwoResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CostSheetTwoDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return CostSheetTwoResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoSearchResponse searchAll(CostSheetTwoSearchRequest bankSearchRequest) {
       PageDTO<CostSheetTwoDomain> pageDTO = repo.searchAll(bankSearchRequest);
       CostSheetTwoSearchResponse bankSearchRespose = new CostSheetTwoSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CostSheetTwoResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoSearchResponse searchFilterAll(CostSheetTwoSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<CostSheetTwoDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        CostSheetTwoSearchResponse bankSearchRespose = new CostSheetTwoSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CostSheetTwoResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional
    public CostSheetTwoResponse changeState(String id, CostSheetTwoChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        CostSheetTwoDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No cost sheet two found with id '%s'.".formatted(id));
        }
        old.setCostSheetTwoState(request.getCostSheetTwoState());
        CostSheetTwoDomain editedDomain = repo.edit(old);
        switch (request.getCostSheetTwoState()) {
            case "Pending" -> {
                _changeStatePending(old, request);
            }
            case "Cancelled" -> {
                _changeStateCancelled(old, request);
            }
            case "Annulled" -> {
                _changeStateAnnulled(old, request);
            }
            default -> {

            }
        }
        return CostSheetTwoResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public CostSheetTwoDetailResponse addCostSheetTwoDetail(CostSheetTwoDetailCreateRequest request, String idCostSheetTwo) {
        if (idCostSheetTwo == null) {
            return null;
        }
        CostSheetTwoDomain costSheetTwoDomain = repo.findById(idCostSheetTwo);
        if (costSheetTwoDomain == null) {
            throw new NotFoundException("No cost sheet two found with id '%s'.".formatted(idCostSheetTwo));
        }
        CostSheetTwoDetailResponse result = costSheetTwoDetailUseCase.create(request, costSheetTwoDomain);
        return result;
    }

    @Override
    @Transactional
    public CostSheetTwoDetailResponse editCostSheetTwoDetail(String id, CostSheetTwoDetailUpdateRequest request, String idCostSheetTwo) {
        if (idCostSheetTwo == null) {
            return null;
        }
        CostSheetTwoDomain costSheetTwoDomain = repo.findById(idCostSheetTwo);
        if (costSheetTwoDomain == null) {
            throw new NotFoundException("No cost sheet two found with id '%s'.".formatted(idCostSheetTwo));
        }
        CostSheetTwoDetailResponse result = costSheetTwoDetailUseCase.edit(id, request, costSheetTwoDomain);

        return result;
    }

    @Override
    @Transactional
    public void deleteCostSheetTwoDetail(String id, String idCostSheetTwo) {
        CostSheetTwoResponse old = findById(idCostSheetTwo);
        costSheetTwoDetailUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostSheetTwoDetailResponse> findAllCostSheetTwoDetailByCostSheetTwo(String idCostSheetTwo) {
        if (idCostSheetTwo == null) {
            return null;
        }
        CostSheetTwoDomain costSheetTwoDomain = repo.findById(idCostSheetTwo);
        if (costSheetTwoDomain == null) {
            throw new NotFoundException("No cost sheet two found with id '%s'.".formatted(idCostSheetTwo));
        }
        return costSheetTwoDetailRepo.searchAllByCostSheetTwoCaseListSimple(idCostSheetTwo).stream().map(costSheetTwoDetailDomain -> CostSheetTwoDetailResponse.from(costSheetTwoDetailDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoDetailResponse findByIdAndCostSheetTwo(String id, String idCostSheetTwo) {
        if (idCostSheetTwo == null) {
            return null;
        }
        CostSheetTwoDomain costSheetTwoDomain = repo.findById(idCostSheetTwo);
        if (costSheetTwoDomain == null) {
            throw new NotFoundException("No cost sheet two found with id '%s'.".formatted(idCostSheetTwo));
        }
        return costSheetTwoDetailUseCase.searchByCostSheetTwoAndCostSheetTwoDetail(idCostSheetTwo, id);
    }

    @Override
    @Transactional
    public Boolean existRegisterGroupInCostSheetTwo(String idRegisterGroup) {
        Boolean result = false;
        List<SimpleBaseDomain> list = repo.findAllSimpleByRegisterGroup(idRegisterGroup);
        if (list != null && !list.isEmpty()){
            result = true;
        }
        return result;
    }

    @Override
    @Transactional
    public CostSheetTwoResponse updateCalculate(String id, CostSheetTwoUpdateRequest request) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        if (id == null) {
            return null;
        }
        CostSheetTwoDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        List<CostSheetTwoDetailDomain> listCostSheetTwoDetails = costSheetTwoDetailRepo.searchAllByCostSheetTwoCaseListSimpleUpdateCalculate(id);
        if (listCostSheetTwoDetails != null && !listCostSheetTwoDetails.isEmpty()){
            listCostSheetTwoDetails = listCostSheetTwoDetails.stream().collect(Collectors.toList());
            for (CostSheetTwoDetailDomain costSheetTwoDetailDomain : listCostSheetTwoDetails){
                if (costSheetTwoDetailDomain.getVoucherEgressDetailItem() != null){
                    costSheetTwoDetailRepo.deleteById(costSheetTwoDetailDomain.getId());
                }
            }
        }

        Double netoGeneralUsd = (double)0;
        Double igvTotalGeneral = (double)0;
        Double invoiceSaleGeneral = (double)0;
        Double boletaSaleGeneral = (double)0;
        Double documentCobranzaGeneral = (double)0;

        Integer intItem = 1;

        List<VoucherEgressDetailItemDomain> listVoucherEgressDetailItem = voucherEgressDetailItemRepo.searchAllByRegisterGroupCaseUpdateCalculate(old.getRegisterGroup().getId());
        listVoucherEgressDetailItem = listVoucherEgressDetailItem.stream().collect(Collectors.toList());

        for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomain : listVoucherEgressDetailItem){

            CostSheetTwoDetailDomain costSheetTwoDetailDomain = new CostSheetTwoDetailDomain();
            costSheetTwoDetailDomain.setNoItem(intItem);
            intItem = intItem + 1;
            costSheetTwoDetailDomain.setVoucherEgressDetailItem(voucherEgressDetailItemDomain);

            costSheetTwoDetailDomain.setCurrency(voucherEgressDetailItemDomain.getCurrency());
            costSheetTwoDetailDomain.setNetoImporteUsd(voucherEgressDetailItemDomain.getNetImportUsd());
            costSheetTwoDetailDomain.setNetoImporteSoles(voucherEgressDetailItemDomain.getNetImportSoles());
            costSheetTwoDetailDomain.setTotalImporteUsd(voucherEgressDetailItemDomain.getTotalImportUsd());
            costSheetTwoDetailDomain.setTotalImporteSoles(voucherEgressDetailItemDomain.getTotalImportSoles());
            costSheetTwoDetailDomain.setServicePqtTourist(voucherEgressDetailItemDomain.getServicePqtTourist());
            costSheetTwoDetailDomain.setObservation(voucherEgressDetailItemDomain.getObservation());
            costSheetTwoDetailDomain.setNetoGeneralUsd(voucherEgressDetailItemDomain.getNetGeneralUsd());
            costSheetTwoDetailDomain.setIgvSfGeneral(voucherEgressDetailItemDomain.getIgvSfGeneral());
            costSheetTwoDetailDomain.setIgvCosteGeneral(voucherEgressDetailItemDomain.getIgvCostGeneral());
            costSheetTwoDetailDomain.setIgvTotalGeneral(voucherEgressDetailItemDomain.getIgvTotalGeneral());
            costSheetTwoDetailDomain.setInvoiceSaleGeneral(voucherEgressDetailItemDomain.getInvoiceSaleGeneral());
            costSheetTwoDetailDomain.setBoletaSaleGeneral(voucherEgressDetailItemDomain.getTicketSaleGeneral());
            costSheetTwoDetailDomain.setDocumentCobranzaGeneral(voucherEgressDetailItemDomain.getDocumentCollectionGeneral());
            costSheetTwoDetailDomain.setIgv(voucherEgressDetailItemDomain.getIgv());

            costSheetTwoDetailDomain.setIdentify(voucherEgressDetailItemDomain.getVoucherEgressDetail().getVoucherEgress().getIdentify());
            costSheetTwoDetailDomain.setSupplier(voucherEgressDetailItemDomain.getVoucherEgressDetail().getSupplier());
            costSheetTwoDetailDomain.setSupplierType(voucherEgressDetailItemDomain.getVoucherEgressDetail().getSupplierType());
            costSheetTwoDetailDomain.setGuideDrive(voucherEgressDetailItemDomain.getVoucherEgressDetail().getGuideDrive());
            costSheetTwoDetailDomain.setGuideTransfer(voucherEgressDetailItemDomain.getVoucherEgressDetail().getGuideTransfer());
            costSheetTwoDetailDomain.setTouristDestine(voucherEgressDetailItemDomain.getVoucherEgressDetail().getTouristDestine());
            costSheetTwoDetailDomain.setDepartmentRegion(voucherEgressDetailItemDomain.getVoucherEgressDetail().getDepartmentRegion());
            costSheetTwoDetailDomain.setDocumentAccountType(voucherEgressDetailItemDomain.getVoucherEgressDetail().getDocumentAccountType());
            costSheetTwoDetailDomain.setNumberDoc(voucherEgressDetailItemDomain.getVoucherEgressDetail().getNumberDoc());
            costSheetTwoDetailDomain.setDate(voucherEgressDetailItemDomain.getVoucherEgressDetail().getDate());
            costSheetTwoDetailDomain.setChangeType(voucherEgressDetailItemDomain.getVoucherEgressDetail().getChangeType());

            costSheetTwoDetailDomain.setCostSheetTwo(old);

            costSheetTwoDetailDomain.setTenant(UserResolver.resolveUser().getTenant());

            costSheetTwoDetailRepo.create(costSheetTwoDetailDomain);

            netoGeneralUsd = netoGeneralUsd + costSheetTwoDetailDomain.getNetoGeneralUsd();
            igvTotalGeneral = igvTotalGeneral + costSheetTwoDetailDomain.getIgvTotalGeneral();
            invoiceSaleGeneral = invoiceSaleGeneral + costSheetTwoDetailDomain.getInvoiceSaleGeneral();
            boletaSaleGeneral = boletaSaleGeneral + costSheetTwoDetailDomain.getBoletaSaleGeneral();
            documentCobranzaGeneral = documentCobranzaGeneral + costSheetTwoDetailDomain.getDocumentCobranzaGeneral();

        }

        old.setTotalValueWithoutIgv(netoGeneralUsd);
        old.setTotalValueWithIgv(igvTotalGeneral);
        old.setTotalValueInvoiceExporter(invoiceSaleGeneral);
        old.setTotalValueBoletaInafect(boletaSaleGeneral);
        old.setTotalValueDocCobranza(documentCobranzaGeneral);

        List<VoucherIngressDetailDomain> listVoucherIngressDetail = voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimpleByRegisterGroup(old.getRegisterGroup().getId());
        listVoucherIngressDetail = listVoucherIngressDetail.stream().collect(Collectors.toList());

        Double voucherIngressTotalUsd = (double)0;

        for (VoucherIngressDetailDomain voucherIngressDetailDomain : listVoucherIngressDetail) {
            voucherIngressTotalUsd = voucherIngressTotalUsd + voucherIngressDetailDomain.getImportUsd();
        }

        old.setTotalProform((double)0);
        old.setTotalProfNCred((double)0);

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){

            if (old.getRegisterGroup().getQuotation().getAmount() != null && old.getRegisterGroup().getQuotation().getAmount() > 0){
                old.setTotalProform(old.getRegisterGroup().getQuotation().getAmount());
            }else{
                old.setTotalProform((double)0);
            }

            old.setTotalProfNCred((double)0);

            old.setTotalVoucherIngress(voucherIngressTotalUsd);

        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){

            List<ProformaInvoiceDomain> listProformaInvoice = proformaInvoiceRepo.searchAllByRegisterGroup(old.getRegisterGroup().getId());
            if (listProformaInvoice != null && !listProformaInvoice.isEmpty()) {
                for (ProformaInvoiceDomain proformaInvoice : listProformaInvoice) {
                    old.setTotalProform(old.getTotalProform() + proformaInvoice.getImportTotalUsd());
                }
            }else{
                old.setTotalProform(0.00);
            }

            old.setTotalProfNCred(0.00);

            List<ProformaCreditNoteDomain> listProformaCreditNote = proformaCreditNoteRepo.searchAllByRegisterGroup(old.getRegisterGroup().getId());
            if (listProformaCreditNote != null && !listProformaCreditNote.isEmpty()) {
                for (ProformaCreditNoteDomain proformaCreditNote : listProformaCreditNote) {
                    old.setTotalProfNCred(old.getTotalProfNCred() + proformaCreditNote.getImportTotalUsd());
                }
            }else{
                old.setTotalProfNCred(0.00);
            }

        }

        old.setTotalDataProform(old.getTotalProform() - old.getTotalProfNCred());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            old.setTotalDataProform(old.getTotalVoucherIngress() - old.getTotalProfNCred());
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            old.setTotalDataProform(old.getTotalProform() - old.getTotalProfNCred());
        }

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            if (request.getAirValue() == null){
                old.setAirValue((double)0);
            }else{
                old.setAirValue(request.getAirValue());
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            old.setAirValue(calculateAirValueFromVoucherEgressDetails(listVoucherEgressDetailItem));
        }

        old.setTotalAirValue(old.getTotalDataProform() - old.getAirValue() );

        old.setDcInvoice(old.getTotalValueInvoiceExporter());
        old.setDcBoleta(old.getTotalValueBoletaInafect());
        old.setDcCobranza(old.getTotalValueDocCobranza());

        old.setDcVtaPqt(old.getDcInvoice() + old.getDcBoleta() + old.getDcCobranza());

        old.setDuUtility( old.getTotalDataProform() - old.getDcVtaPqt() );

        Double factorUtility = (double)0;
        if ( old.getDcVtaPqt() != null && !old.getDcVtaPqt().equals((double)0) ){
            factorUtility = old.getTotalDataProform() / old.getDcVtaPqt();
        }

        old.setDistrUtiliInvoiceGastos( old.getDcInvoice() );
        old.setDistrUtiliInvoiceTotalReal( old.getDcInvoice() * factorUtility );
        old.setDistrUtiliInvoiceUtilidad( old.getDistrUtiliInvoiceTotalReal() - old.getDcInvoice() );
        if ( old.getTotalDataProform() != null && old.getTotalDataProform() != 0 ){
            old.setDistrUtiliInvoicePercentReal( old.getDistrUtiliInvoiceTotalReal() * 100 / old.getTotalDataProform() );
        }else{
            old.setDistrUtiliInvoicePercentReal((double)0);
        }

        Double percentInvoice = (double)30;
        Double percentBoleta = (double)70;
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName()) && old.getCostSheetTwoPercent() != null){
            if (old.getCostSheetTwoPercent().getPercentMin() != null){
                percentInvoice = old.getCostSheetTwoPercent().getPercentMin();
            }
            if (old.getCostSheetTwoPercent().getPercentMax() != null){
                percentBoleta = old.getCostSheetTwoPercent().getPercentMax();
            }
        }

        if ( companyDomain.getSetting().getDistributionInvoiceExpenses() != null && companyDomain.getSetting().getDistributionInvoiceExpenses() > 0 ){
            old.setDistrUtiliInvoiceVal(old.getTotalAirValue() * companyDomain.getSetting().getDistributionInvoiceExpenses() / 100);
        }else{
            old.setDistrUtiliInvoiceVal(old.getTotalAirValue() * percentInvoice / 100);
        }

        old.setDistrUtiliInvoiceDiff(old.getDistrUtiliInvoiceTotalReal() - old.getDistrUtiliInvoiceVal());

        old.setDistrUtiliBoletaGastos( old.getDcBoleta() );
        old.setDistrUtiliBoletaTotalReal( old.getDcBoleta() * factorUtility );
        old.setDistrUtiliBoletaUtilidad( old.getDistrUtiliBoletaTotalReal() - old.getDcBoleta() );
        if ( old.getTotalDataProform() != null && old.getTotalDataProform() != 0 ){
            old.setDistrUtiliBoletaPercentReal( old.getDistrUtiliBoletaTotalReal() * 100 / old.getTotalDataProform() );
        }else{
            old.setDistrUtiliBoletaPercentReal((double)0);
        }

        if ( companyDomain.getSetting().getDistributionTicketExpenses() != null && companyDomain.getSetting().getDistributionTicketExpenses() > 0 ){
            old.setDistrUtiliBoletaVal(old.getTotalAirValue() * companyDomain.getSetting().getDistributionTicketExpenses() / 100);
        }else{
            old.setDistrUtiliBoletaVal(old.getTotalAirValue() * percentBoleta / 100);
        }

        old.setDistrUtiliBoletaDiff(old.getDistrUtiliBoletaTotalReal() - old.getDistrUtiliBoletaVal());

        old.setDistrUtiliCobranzaGastos( old.getDistrUtiliCobranzaGastos() );
        old.setDistrUtiliCobranzaTotalReal( old.getDistrUtiliCobranzaTotalReal() );

        old.setDistrUtiliTotalGastos( old.getDistrUtiliInvoiceGastos() + old.getDistrUtiliBoletaGastos() + old.getDistrUtiliCobranzaGastos() );
        old.setDistrUtiliTotalUtilidad( old.getDistrUtiliInvoiceUtilidad() + old.getDistrUtiliBoletaUtilidad() );
        old.setDistrUtiliTotalTotalReal( old.getDistrUtiliInvoiceTotalReal() + old.getDistrUtiliBoletaTotalReal() + old.getDistrUtiliCobranzaTotalReal() );
        old.setDistrUtiliTotalPercentReal( old.getDistrUtiliInvoicePercentReal() + old.getDistrUtiliBoletaPercentReal() );
        old.setDistrUtiliTotalVal( old.getDistrUtiliInvoiceVal() + old.getDistrUtiliBoletaVal() );

        CostSheetTwoDomain costSheetTwoDomainEdit = repo.edit(old);

        return CostSheetTwoResponse.from(costSheetTwoDomainEdit);
    }

    private Double calculateAirValueFromVoucherEgressDetails(List<VoucherEgressDetailItemDomain> listVoucherEgressDetailItem){
        Double airValue = (double)0;
        if (listVoucherEgressDetailItem != null && !listVoucherEgressDetailItem.isEmpty()){
            for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomain : listVoucherEgressDetailItem){
                if (voucherEgressDetailItemDomain.getServicePqtTourist() != null
                        && "VUELOS".equalsIgnoreCase(voucherEgressDetailItemDomain.getServicePqtTourist().getName())
                        && voucherEgressDetailItemDomain.getVoucherEgressDetail() != null
                        && voucherEgressDetailItemDomain.getVoucherEgressDetail().getDocumentAccountType() != null
                        && DocumentAccountTypeEnum.COLLECTION_DOCUMENT.getName().equalsIgnoreCase(voucherEgressDetailItemDomain.getVoucherEgressDetail().getDocumentAccountType().getDocumentAccountType())
                        && voucherEgressDetailItemDomain.getDocumentCollectionGeneral() != null){
                    airValue = airValue + voucherEgressDetailItemDomain.getDocumentCollectionGeneral();
                }
            }
        }
        return airValue;
    }

    @Override
    @Transactional
    public byte[] exportCostSheetTwoPdf(String idCostSheetTwo) {
        if (idCostSheetTwo == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        CostSheetTwoDomain costSheetTwoDomain = repo.findById(idCostSheetTwo);
        if (costSheetTwoDomain == null) {
            throw new NotFoundException("No cost sheet two found with id '%s'.".formatted(idCostSheetTwo));
        }

        List<CostSheetTwoDomain> list = new ArrayList<CostSheetTwoDomain>();
        list.add(costSheetTwoDomain);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<CostSheetTwoDetailDomain> costSheetTwoDetailDomains = costSheetTwoDetailRepo.searchAllByCostSheetTwo(idCostSheetTwo);
        if (costSheetTwoDetailDomains != null && !costSheetTwoDetailDomains.isEmpty()) {
            costSheetTwoDetailDomains = costSheetTwoDetailDomains.stream().collect(Collectors.toList());
        }

        byte[] result = new byte[0];
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            result = jrCostSheetTwoRepository.exportLegendary(list, costSheetTwoDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            result = jrCostSheetTwoRepository.exportGuru(list, costSheetTwoDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
        }
        return result;
    }

    @Override
    @Transactional
    public byte[] exportCostSheetTwoExcel(String idCostSheetTwo) {
        if (idCostSheetTwo == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        CostSheetTwoDomain costSheetTwoDomain = repo.findById(idCostSheetTwo);
        if (costSheetTwoDomain == null) {
            throw new NotFoundException("No cost sheet two found with id '%s'.".formatted(idCostSheetTwo));
        }

        List<CostSheetTwoDomain> list = new ArrayList<CostSheetTwoDomain>();
        list.add(costSheetTwoDomain);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<CostSheetTwoDetailDomain> costSheetTwoDetailDomains = costSheetTwoDetailRepo.searchAllByCostSheetTwoCaseListSimpleByExport(idCostSheetTwo);
        if (costSheetTwoDetailDomains != null && !costSheetTwoDetailDomains.isEmpty()) {
            costSheetTwoDetailDomains = costSheetTwoDetailDomains.stream().collect(Collectors.toList());
        }

        byte[] result = new byte[0];
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            result = jrCostSheetTwoRepository.exportLegendary(list, costSheetTwoDetailDomains, ExportFormatEnum.XLSX.getName(), parameters);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            result = jrCostSheetTwoRepository.exportGuru(list, costSheetTwoDetailDomains, ExportFormatEnum.XLSX.getName(), parameters);
        }
        return result;
    }

    private void _changeStatePending(CostSheetTwoDomain costSheetTwoDomain, CostSheetTwoChangeStateRequest request){

    }

    private void _changeStateCancelled(CostSheetTwoDomain costSheetTwoDomain, CostSheetTwoChangeStateRequest request){

    }

    private void _changeStateAnnulled(CostSheetTwoDomain costSheetTwoDomain, CostSheetTwoChangeStateRequest request){

    }
}
