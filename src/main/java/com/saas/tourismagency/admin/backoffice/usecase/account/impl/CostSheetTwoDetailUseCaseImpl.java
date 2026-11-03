package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwodetail.CostSheetTwoDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultCostSheetTwoDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwo.CostSheetTwoRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwodetail.CostSheetTwoDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.ServicePqtTouristRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem.VoucherEgressDetailItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documentaccounttype.DocumentAccountTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.GuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.igv.IgvRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.TouristDestineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.costsheettwodetail.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.usecase.account.CostSheetTwoDetailUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CostSheetTwoDetailUseCaseImpl implements CostSheetTwoDetailUseCase {

    @Autowired
    private CostSheetTwoDetailRepo repo;

    @Autowired
    private CostSheetTwoDetailConverter converter;

    @Autowired
    private SetFieldDefaultCostSheetTwoDetail setFieldDefault;

    @Autowired
    private CostSheetTwoRepo costSheetTwoRepo;

    @Autowired
    private VoucherEgressDetailItemRepo voucherEgressDetailItemRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ServicePqtTouristRepo servicePqtTouristRepo;

    @Autowired
    private IgvRepo igvRepo;

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
    private DocumentAccountTypeRepo documentAccountTypeRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Override
    @Transactional
    public CostSheetTwoDetailResponse create(CostSheetTwoDetailCreateRequest request) {

        CostSheetTwoDomain costSheetTwoDomain = null;
        if (request.getCostSheetTwo() != null && !request.getCostSheetTwo().isEmpty()){
            IdBaseDomain costSheetTwoIdBaseDomain = costSheetTwoRepo.findByIdEntity(request.getCostSheetTwo());
            costSheetTwoDomain = new CostSheetTwoDomain();
            costSheetTwoDomain.setId(costSheetTwoIdBaseDomain.getId());
        }

        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = null;
        if (request.getVoucherEgressDetailItem() != null && !request.getVoucherEgressDetailItem().isEmpty()){
            IdBaseDomain voucherEgressDetailItemIdBaseDomain = voucherEgressDetailItemRepo.findByIdEntity(request.getVoucherEgressDetailItem());
            voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(voucherEgressDetailItemIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            servicePqtTouristDomain = new ServicePqtTouristDomain();
            servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
        }

        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            igvDomain = new IgvDomain();
            igvDomain.setId(igvIdBaseDomain.getId());
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

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        CostSheetTwoDetailDomain newDomain = request.to(
                costSheetTwoDomain,
                voucherEgressDetailItemDomain,
                currencyDomain,
                servicePqtTouristDomain,
                igvDomain,
                supplierDomain,
                guideDriveDomain,
                guideTransferDomain,
                supplierTypeDomain,
                touristDestineDomain,
                departmentRegionDomain,
                documentAccountTypeDomain,
                changeTypeDomain
                );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        List<CostSheetTwoDetailDomain> listTemp = repo.searchAllByCostSheetTwoCaseListSimpleOnlyNoItem(costSheetTwoDomain.getId());
        if (listTemp != null && !listTemp.isEmpty()){
            listTemp = listTemp.stream().collect(Collectors.toList());
            Collections.sort(listTemp);
            newDomain.setNoItem(listTemp.getLast().getNoItem() + 1);
        }else{
            newDomain.setNoItem(1);
        }

        CostSheetTwoDetailDomain createdDomain = repo.create(newDomain);
        return CostSheetTwoDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostSheetTwoDetailResponse create(CostSheetTwoDetailCreateRequest request, CostSheetTwoDomain costSheetTwoDomain) {

        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = null;
        if (request.getVoucherEgressDetailItem() != null && !request.getVoucherEgressDetailItem().isEmpty()){
            IdBaseDomain voucherEgressDetailItemIdBaseDomain = voucherEgressDetailItemRepo.findByIdEntity(request.getVoucherEgressDetailItem());
            voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(voucherEgressDetailItemIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            servicePqtTouristDomain = new ServicePqtTouristDomain();
            servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
        }

        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            igvDomain = new IgvDomain();
            igvDomain.setId(igvIdBaseDomain.getId());
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

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        CostSheetTwoDetailDomain newDomain = request.to(
                costSheetTwoDomain,
                voucherEgressDetailItemDomain,
                currencyDomain,
                servicePqtTouristDomain,
                igvDomain,
                supplierDomain,
                guideDriveDomain,
                guideTransferDomain,
                supplierTypeDomain,
                touristDestineDomain,
                departmentRegionDomain,
                documentAccountTypeDomain,
                changeTypeDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        List<CostSheetTwoDetailDomain> listTemp = repo.searchAllByCostSheetTwoCaseListSimpleOnlyNoItem(costSheetTwoDomain.getId());
        if (listTemp != null && !listTemp.isEmpty()){
            listTemp = listTemp.stream().collect(Collectors.toList());
            Collections.sort(listTemp);
            newDomain.setNoItem(listTemp.getLast().getNoItem() + 1);
        }else{
            newDomain.setNoItem(1);
        }

        CostSheetTwoDetailDomain createdDomain = repo.create(newDomain);
        return CostSheetTwoDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CostSheetTwoDetailResponse edit(String id, CostSheetTwoDetailUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CostSheetTwoDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        CostSheetTwoDomain costSheetTwoDomain = null;
        if (request.getCostSheetTwo() != null && !request.getCostSheetTwo().isEmpty()){
            IdBaseDomain costSheetTwoIdBaseDomain = costSheetTwoRepo.findByIdEntity(request.getCostSheetTwo());
            costSheetTwoDomain = new CostSheetTwoDomain();
            costSheetTwoDomain.setId(costSheetTwoIdBaseDomain.getId());
        }

        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = null;
        if (request.getVoucherEgressDetailItem() != null && !request.getVoucherEgressDetailItem().isEmpty()){
            IdBaseDomain voucherEgressDetailItemIdBaseDomain = voucherEgressDetailItemRepo.findByIdEntity(request.getVoucherEgressDetailItem());
            voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(voucherEgressDetailItemIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            servicePqtTouristDomain = new ServicePqtTouristDomain();
            servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
        }

        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            igvDomain = new IgvDomain();
            igvDomain.setId(igvIdBaseDomain.getId());
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

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        CostSheetTwoDetailDomain costSheetTwoDetailDomain = request.to(
                costSheetTwoDomain,
                voucherEgressDetailItemDomain,
                currencyDomain,
                servicePqtTouristDomain,
                igvDomain,
                supplierDomain,
                guideDriveDomain,
                guideTransferDomain,
                supplierTypeDomain,
                touristDestineDomain,
                departmentRegionDomain,
                documentAccountTypeDomain,
                changeTypeDomain
        );
        costSheetTwoDetailDomain.setNoItem(old.getNoItem());
        CostSheetTwoDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costSheetTwoDetailDomain, old));
        return CostSheetTwoDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public CostSheetTwoDetailResponse edit(String id, CostSheetTwoDetailUpdateRequest request, CostSheetTwoDomain costSheetTwoDomain) {
        if (id == null) {
            return null;
        }
        CostSheetTwoDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = null;
        if (request.getVoucherEgressDetailItem() != null && !request.getVoucherEgressDetailItem().isEmpty()){
            IdBaseDomain voucherEgressDetailItemIdBaseDomain = voucherEgressDetailItemRepo.findByIdEntity(request.getVoucherEgressDetailItem());
            voucherEgressDetailItemDomain = new VoucherEgressDetailItemDomain();
            voucherEgressDetailItemDomain.setId(voucherEgressDetailItemIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            servicePqtTouristDomain = new ServicePqtTouristDomain();
            servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
        }

        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            igvDomain = new IgvDomain();
            igvDomain.setId(igvIdBaseDomain.getId());
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

        DocumentAccountTypeDomain documentAccountTypeDomain = null;
        if (request.getDocumentAccountType() != null && !request.getDocumentAccountType().isEmpty()){
            IdBaseDomain documentAccountTypeIdBaseDomain = documentAccountTypeRepo.findByIdEntity(request.getDocumentAccountType());
            documentAccountTypeDomain = new DocumentAccountTypeDomain();
            documentAccountTypeDomain.setId(documentAccountTypeIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        CostSheetTwoDetailDomain costSheetTwoDetailDomain = request.to(
                costSheetTwoDomain,
                voucherEgressDetailItemDomain,
                currencyDomain,
                servicePqtTouristDomain,
                igvDomain,
                supplierDomain,
                guideDriveDomain,
                guideTransferDomain,
                supplierTypeDomain,
                touristDestineDomain,
                departmentRegionDomain,
                documentAccountTypeDomain,
                changeTypeDomain
        );
        costSheetTwoDetailDomain.setNoItem(old.getNoItem());
        CostSheetTwoDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(costSheetTwoDetailDomain, old));
        return CostSheetTwoDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CostSheetTwoDetailResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostSheetTwoDetailResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> CostSheetTwoDetailResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoDetailResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CostSheetTwoDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return CostSheetTwoDetailResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoDetailSearchResponse searchAll(CostSheetTwoDetailSearchRequest bankSearchRequest) {
       PageDTO<CostSheetTwoDetailDomain> pageDTO = repo.searchAll(bankSearchRequest);
       CostSheetTwoDetailSearchResponse bankSearchRespose = new CostSheetTwoDetailSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CostSheetTwoDetailResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoDetailSearchResponse searchFilterAll(CostSheetTwoDetailSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<CostSheetTwoDetailDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        CostSheetTwoDetailSearchResponse bankSearchRespose = new CostSheetTwoDetailSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CostSheetTwoDetailResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostSheetTwoDetailResponse> searchAllByCostSheetTwo(String idCostSheetTwo) {
        List<CostSheetTwoDetailDomain> list = repo.searchAllByCostSheetTwo(idCostSheetTwo);
        return list.stream().map(costSheetTwoDetailDomain ->  CostSheetTwoDetailResponse.from(costSheetTwoDetailDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CostSheetTwoDetailResponse searchByCostSheetTwoAndCostSheetTwoDetail(String idCostSheetTwo, String idCostSheetTwoDetail) {
        CostSheetTwoDetailDomain costSheetTwoDetailDomain = repo.searchAllByCostSheetTwoAndCostSheetTwoDetail(idCostSheetTwo, idCostSheetTwoDetail);
        return CostSheetTwoDetailResponse.from(costSheetTwoDetailDomain);
    }
}
