package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetail.VoucherEgressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherEgressDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress.VoucherEgressRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail.PersistVoucherEgressDetailNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail.VoucherEgressDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem.VoucherEgressDetailItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.detraction.DetractionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.DocumentAccountTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.GuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.TouristDestineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateServiceInvoiceEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherEgressDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VoucherEgressDetailUseCaseImpl implements VoucherEgressDetailUseCase {

    @Autowired
    private VoucherEgressDetailRepo repo;

    @Autowired
    private VoucherEgressRepo voucherEgressRepo;

    @Autowired
    private VoucherEgressDetailItemRepo voucherEgressDetailItemRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private GuideRepo guideRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private TouristDestineRepo touristDestineRepo;

    @Autowired
    private DepartmentRegionRepo departmentRegionRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private DocumentAccountTypeRepo documentAccountTypeRepo;

    @Autowired
    private DetractionRepo detractionRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private VoucherEgressDetailConverter converter;

    @Autowired
    PersistVoucherEgressDetailNumberCallbackImpl persistVoucherEgressDetailNumberCallback;

    @Autowired
    private SetFieldDefaultVoucherEgressDetail setFieldDefault;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional
    public VoucherEgressDetailResponse create(VoucherEgressDetailCreateRequest request) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        VoucherEgressDomain voucherEgressDomain = null;
        if (request.getVoucherEgress() != null && !request.getVoucherEgress().isEmpty()){
            IdBaseDomain voucherEgressIdBaseDomain = voucherEgressRepo.findByIdEntity(request.getVoucherEgress());
            voucherEgressDomain = new VoucherEgressDomain();
            voucherEgressDomain.setId(voucherEgressIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            IdBaseDomain guideDriveIdBaseDomain = guideRepo.findByIdEntity(request.getGuideDrive());
            guideDriveDomain = new GuideDomain();
            guideDriveDomain.setId(guideDriveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }

        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            IdBaseDomain touristDestineIdBaseDomain = touristDestineRepo.findByIdEntity(request.getTouristDestine());
            touristDestineDomain = new TouristDestineDomain();
            touristDestineDomain.setId(touristDestineIdBaseDomain.getId());
        }

        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            detractionDomain = new DetractionDomain();
            detractionDomain.setId(detractionIdBaseDomain.getId());
        }

        ItineraryItemDomain itineraryItemDomain = null;
        if (request.getItineraryItem() != null && !request.getItineraryItem().isEmpty()){
            itineraryItemDomain = itineraryItemRepo.findSimpleDomainCaseVoucherEgress(request.getItineraryItem());
        }

        VoucherEgressDetailDomain newDomain = null;

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            newDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain, itineraryItemDomain);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            newDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }else{
            newDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }

        if (itineraryItemDomain != null) {
            newDomain.setPaxTotal(itineraryItemDomain.getPaxTotal());
        } else {
            newDomain.setPaxTotal(0);
        }

        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistVoucherEgressDetailNumberCallback.generateNumber());
        newDomain.setMontUsd(0.0);
        newDomain.setMontSoles(0.0);
        newDomain.setBalanceUsd(0.0);
        newDomain.setBalanceSoles(0.0);
        newDomain.setDetractionUsd(0.0);
        newDomain.setDetractionSoles(0.0);

        List<VoucherEgressDetailDomain> listTemp = repo.searchAllByVoucherEgressCaseListSimpleNoItem(voucherEgressDomain.getId());
        if (listTemp != null && !listTemp.isEmpty()){
            listTemp = listTemp.stream().collect(Collectors.toList());
            Collections.sort(listTemp);
            newDomain.setNoItem(listTemp.getLast().getNoItem() + 1);
        }else{
            newDomain.setNoItem(1);
        }

        VoucherEgressDetailDomain createdDomain = repo.create(newDomain);

        // Update Status Service Invoice

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            if (itineraryItemDomain != null){
                itineraryItemDomain = itineraryItemRepo.findById(itineraryItemDomain.getId());
                itineraryItemDomain.setStateServiceInvoice(StateServiceInvoiceEnum.GENERATED.getName());
                itineraryItemRepo.edit(itineraryItemDomain);
            }
        }

        return VoucherEgressDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherEgressDetailResponse create(VoucherEgressDetailCreateRequest request, VoucherEgressDomain voucherEgressDomain) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            IdBaseDomain guideDriveIdBaseDomain = guideRepo.findByIdEntity(request.getGuideDrive());
            guideDriveDomain = new GuideDomain();
            guideDriveDomain.setId(guideDriveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }

        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            IdBaseDomain touristDestineIdBaseDomain = touristDestineRepo.findByIdEntity(request.getTouristDestine());
            touristDestineDomain = new TouristDestineDomain();
            touristDestineDomain.setId(touristDestineIdBaseDomain.getId());
        }

        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            detractionDomain = new DetractionDomain();
            detractionDomain.setId(detractionIdBaseDomain.getId());
        }

        ItineraryItemDomain itineraryItemDomain = null;
        if (request.getItineraryItem() != null && !request.getItineraryItem().isEmpty()){
            itineraryItemDomain = itineraryItemRepo.findSimpleDomainCaseVoucherEgress(request.getItineraryItem());
        }

        VoucherEgressDetailDomain newDomain = null;

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            newDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain, itineraryItemDomain);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            newDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }else{
            newDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }

        if (itineraryItemDomain != null) {
            newDomain.setPaxTotal(itineraryItemDomain.getPaxTotal());
        } else {
            newDomain.setPaxTotal(0);
        }

        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistVoucherEgressDetailNumberCallback.generateNumber());
        newDomain.setMontUsd(0.0);
        newDomain.setMontSoles(0.0);
        newDomain.setBalanceUsd(0.0);
        newDomain.setBalanceSoles(0.0);
        newDomain.setDetractionUsd(0.0);
        newDomain.setDetractionSoles(0.0);

        List<VoucherEgressDetailDomain> listTemp = repo.searchAllByVoucherEgressCaseListSimpleNoItem(voucherEgressDomain.getId());
        if (listTemp != null && !listTemp.isEmpty()){
            listTemp = listTemp.stream().collect(Collectors.toList());
            Collections.sort(listTemp);
            newDomain.setNoItem(listTemp.getLast().getNoItem() + 1);
        }else{
            newDomain.setNoItem(1);
        }

        VoucherEgressDetailDomain createdDomain = repo.create(newDomain);

        // Update Status Service Invoice

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            if (itineraryItemDomain != null){
                itineraryItemDomain = itineraryItemRepo.findById(itineraryItemDomain.getId());
                itineraryItemDomain.setStateServiceInvoice(StateServiceInvoiceEnum.GENERATED.getName());
                itineraryItemRepo.edit(itineraryItemDomain);
            }
        }

        return VoucherEgressDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherEgressDetailResponse edit(String id, VoucherEgressDetailUpdateRequest request) {
        if (id == null) {
            return null;
        }
        VoucherEgressDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        VoucherEgressDomain voucherEgressDomain = null;
        if (request.getVoucherEgress() != null && !request.getVoucherEgress().isEmpty()){
            IdBaseDomain voucherEgressIdBaseDomain = voucherEgressRepo.findByIdEntity(request.getVoucherEgress());
            voucherEgressDomain = new VoucherEgressDomain();
            voucherEgressDomain.setId(voucherEgressIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            IdBaseDomain guideDriveIdBaseDomain = guideRepo.findByIdEntity(request.getGuideDrive());
            guideDriveDomain = new GuideDomain();
            guideDriveDomain.setId(guideDriveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }

        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            IdBaseDomain touristDestineIdBaseDomain = touristDestineRepo.findByIdEntity(request.getTouristDestine());
            touristDestineDomain = new TouristDestineDomain();
            touristDestineDomain.setId(touristDestineIdBaseDomain.getId());
        }

        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            detractionDomain = new DetractionDomain();
            detractionDomain.setId(detractionIdBaseDomain.getId());
        }

        ItineraryItemDomain itineraryItemDomain = null;
        if (request.getItineraryItem() != null && !request.getItineraryItem().isEmpty()){
            IdBaseDomain itineraryItemIdBaseDomain = itineraryItemRepo.findByIdEntity(request.getItineraryItem());
            itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId(itineraryItemIdBaseDomain.getId());
        }

        VoucherEgressDetailDomain voucherEgressDetailDomain = null;

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            voucherEgressDetailDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain, itineraryItemDomain);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            voucherEgressDetailDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }else{
            voucherEgressDetailDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }

        voucherEgressDetailDomain.setNoItem(old.getNoItem());
        voucherEgressDetailDomain.setIdentify(old.getIdentify());
        voucherEgressDetailDomain.setMontUsd(old.getMontUsd());
        voucherEgressDetailDomain.setMontSoles(old.getMontSoles());
        voucherEgressDetailDomain.setBalanceUsd(old.getBalanceUsd());
        voucherEgressDetailDomain.setBalanceSoles(old.getBalanceSoles());
        voucherEgressDetailDomain.setDetractionUsd(old.getDetractionUsd());
        voucherEgressDetailDomain.setDetractionSoles(old.getDetractionSoles());

        voucherEgressDetailDomain.setItineraryItem(old.getItineraryItem());

        voucherEgressDetailDomain.setPaxTotal(old.getPaxTotal());

        VoucherEgressDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherEgressDetailDomain, old));
        return VoucherEgressDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public VoucherEgressDetailResponse edit(String id, VoucherEgressDetailUpdateRequest request, VoucherEgressDomain voucherEgressDomain) {
        if (id == null) {
            return null;
        }
        VoucherEgressDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            IdBaseDomain guideDriveIdBaseDomain = guideRepo.findByIdEntity(request.getGuideDrive());
            guideDriveDomain = new GuideDomain();
            guideDriveDomain.setId(guideDriveIdBaseDomain.getId());
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }

        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            IdBaseDomain touristDestineIdBaseDomain = touristDestineRepo.findByIdEntity(request.getTouristDestine());
            touristDestineDomain = new TouristDestineDomain();
            touristDestineDomain.setId(touristDestineIdBaseDomain.getId());
        }

        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            detractionDomain = new DetractionDomain();
            detractionDomain.setId(detractionIdBaseDomain.getId());
        }

        ItineraryItemDomain itineraryItemDomain = null;
        if (request.getItineraryItem() != null && !request.getItineraryItem().isEmpty()){
            IdBaseDomain itineraryItemIdBaseDomain = itineraryItemRepo.findByIdEntity(request.getItineraryItem());
            itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId(itineraryItemIdBaseDomain.getId());
        }

        VoucherEgressDetailDomain voucherEgressDetailDomain = null;

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            voucherEgressDetailDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain, itineraryItemDomain);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            voucherEgressDetailDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }else{
            voucherEgressDetailDomain = request.to(voucherEgressDomain, currencyDomain, registerGroupDomain, supplierDomain, guideDriveDomain, guideTransferDomain, supplierTypeDomain, touristDestineDomain, departmentRegionDomain, changeTypeDomain, documentAccountTypeDomain, detractionDomain);
        }

        voucherEgressDetailDomain.setIdentify(old.getIdentify());
        voucherEgressDetailDomain.setNoItem(old.getNoItem());
        voucherEgressDetailDomain.setMontUsd(old.getMontUsd());
        voucherEgressDetailDomain.setMontSoles(old.getMontSoles());
        voucherEgressDetailDomain.setBalanceUsd(old.getBalanceUsd());
        voucherEgressDetailDomain.setBalanceSoles(old.getBalanceSoles());
        voucherEgressDetailDomain.setDetractionUsd(old.getDetractionUsd());
        voucherEgressDetailDomain.setDetractionSoles(old.getDetractionSoles());

        voucherEgressDetailDomain.setItineraryItem(old.getItineraryItem());

        voucherEgressDetailDomain.setPaxTotal(old.getPaxTotal());

        VoucherEgressDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherEgressDetailDomain, old));
        return VoucherEgressDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        VoucherEgressDetailResponse old = findById(id);
        List<VoucherEgressDetailItemDomain> detailsByDelete = voucherEgressDetailItemRepo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListByDelete(id);
        if (detailsByDelete != null && !detailsByDelete.isEmpty()){
            for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomain : detailsByDelete){
                voucherEgressDetailItemRepo.deleteById(voucherEgressDetailItemDomain.getId());
            }
        }
        if (old.getItineraryItem() != null) {
            ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.findById(old.getItineraryItem().getId());
            if (itineraryItemDomain != null){
                itineraryItemDomain.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());
                itineraryItemRepo.edit(itineraryItemDomain);
            }
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressDetailResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> VoucherEgressDetailResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailResponse findById(String id) {
        if (id == null) {
            return null;
        }
        VoucherEgressDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return VoucherEgressDetailResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailSearchResponse searchAll(VoucherEgressDetailSearchRequest bankSearchRequest) {
       PageDTO<VoucherEgressDetailDomain> pageDTO = repo.searchAll(bankSearchRequest);
       VoucherEgressDetailSearchResponse bankSearchRespose = new VoucherEgressDetailSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherEgressDetailResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailSearchResponse searchFilterAll(VoucherEgressDetailSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<VoucherEgressDetailDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        VoucherEgressDetailSearchResponse bankSearchRespose = new VoucherEgressDetailSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherEgressDetailResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressDetailResponse> searchAllByVoucherEgress(String idVoucherEgress) {
        List<VoucherEgressDetailDomain> list = repo.searchAllByVoucherEgress(idVoucherEgress);
        return list.stream().map(voucherEgressDetailDomain ->  VoucherEgressDetailResponse.from(voucherEgressDetailDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailResponse searchByVoucherEgressAndVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail) {
        VoucherEgressDetailDomain voucherEgressDetailDomain = repo.searchAllByVoucherEgressAndVoucherEgressDetail(idVoucherEgress, idVoucherEgressDetail);
        return VoucherEgressDetailResponse.from(voucherEgressDetailDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailResponse getVoucherEgressDetailModificationData(String idVoucherEgress, String idVoucherEgressDetail) {
        if (idVoucherEgressDetail == null) {
            return null;
        }
        IdBaseDomain voucherEgressDetailIdBaseDomain = repo.findByIdEntity(idVoucherEgressDetail);
        if (voucherEgressDetailIdBaseDomain == null){
            throw new NotFoundException("No voucher egress detail found with id '%s'.".formatted(idVoucherEgressDetail));
        }

        return VoucherEgressDetailResponse.from(repo.findByIdVoucherEgressDetailCaseGetModificationData(idVoucherEgressDetail ,idVoucherEgress));
    }

    @Override
    public VoucherEgressDetailResponse changeCantPax(String idItineraryItem, VoucherEgressDetailChangeCantPaxRequest request) {
        if (idItineraryItem == null) {
            return null;
        }
        VoucherEgressDetailDomain voucherEgressDetailDomain = repo.searchAllByItineraryItem(idItineraryItem);
        if (voucherEgressDetailDomain == null){
            throw new NotFoundException("No voucher egress detail found with id itinerary item '%s'.".formatted(idItineraryItem));
        }

        voucherEgressDetailDomain.setPaxTotal(request.getPaxTotal());

        voucherEgressDetailDomain = repo.edit(voucherEgressDetailDomain);

        return VoucherEgressDetailResponse.from(voucherEgressDetailDomain);
    }

    @Override
    public void duplicate(String id) {
        VoucherEgressDetailDomain voucherEgressDetailDomain = repo.findById(id);
        if (voucherEgressDetailDomain == null){
            throw new NotFoundException("No voucher egress detail found with id '%s'.".formatted(id));
        }

        VoucherEgressDetailDomain voucherEgressDetailDomainCopy = new VoucherEgressDetailDomain();

        voucherEgressDetailDomainCopy.setTenant(UserResolver.resolveUser().getTenant());
        voucherEgressDetailDomainCopy.setIdentify(persistVoucherEgressDetailNumberCallback.generateNumber());
        voucherEgressDetailDomainCopy.setMontUsd(0.0);
        voucherEgressDetailDomainCopy.setMontSoles(0.0);
        voucherEgressDetailDomainCopy.setBalanceUsd(0.0);
        voucherEgressDetailDomainCopy.setBalanceSoles(0.0);
        voucherEgressDetailDomainCopy.setDetractionUsd(0.0);
        voucherEgressDetailDomainCopy.setDetractionSoles(0.0);

        voucherEgressDetailDomainCopy.setName(voucherEgressDetailDomain.getName());
        voucherEgressDetailDomainCopy.setDescription(voucherEgressDetailDomain.getDescription());

        voucherEgressDetailDomainCopy.setYear(voucherEgressDetailDomain.getYear());

        voucherEgressDetailDomainCopy.setValueTypeShowSupplier(voucherEgressDetailDomain.getValueTypeShowSupplier());
        voucherEgressDetailDomainCopy.setNumberDoc("");
        voucherEgressDetailDomainCopy.setObservation("");

        voucherEgressDetailDomainCopy.setDate(voucherEgressDetailDomain.getDate());
        voucherEgressDetailDomainCopy.setVoucherEgress(voucherEgressDetailDomain.getVoucherEgress());
        voucherEgressDetailDomainCopy.setCurrency(voucherEgressDetailDomain.getCurrency());
        voucherEgressDetailDomainCopy.setRegisterGroup(voucherEgressDetailDomain.getRegisterGroup());
        voucherEgressDetailDomainCopy.setSupplier(voucherEgressDetailDomain.getSupplier());
        voucherEgressDetailDomainCopy.setGuideTransfer(voucherEgressDetailDomain.getGuideTransfer());
        voucherEgressDetailDomainCopy.setGuideDrive(voucherEgressDetailDomain.getGuideDrive());
        voucherEgressDetailDomainCopy.setSupplierType(voucherEgressDetailDomain.getSupplierType());
        voucherEgressDetailDomainCopy.setTouristDestine(voucherEgressDetailDomain.getTouristDestine());
        voucherEgressDetailDomainCopy.setDepartmentRegion(voucherEgressDetailDomain.getDepartmentRegion());
        voucherEgressDetailDomainCopy.setChangeType(voucherEgressDetailDomain.getChangeType());
        voucherEgressDetailDomainCopy.setDocumentAccountType(voucherEgressDetailDomain.getDocumentAccountType());
        voucherEgressDetailDomainCopy.setDetraction(voucherEgressDetailDomain.getDetraction());
        voucherEgressDetailDomainCopy.setItineraryItem(voucherEgressDetailDomain.getItineraryItem());
        voucherEgressDetailDomainCopy.setPaxTotal(voucherEgressDetailDomain.getPaxTotal());

        List<VoucherEgressDetailDomain> listTemp = repo.searchAllByVoucherEgressCaseListSimpleNoItem(id);
        if (listTemp != null && !listTemp.isEmpty()){
            listTemp = listTemp.stream().collect(Collectors.toList());
            Collections.sort(listTemp);
            voucherEgressDetailDomainCopy.setNoItem(listTemp.getLast().getNoItem() + 1);
        }else{
            voucherEgressDetailDomainCopy.setNoItem(1);
        }

        repo.create(voucherEgressDetailDomainCopy);

    }

}
