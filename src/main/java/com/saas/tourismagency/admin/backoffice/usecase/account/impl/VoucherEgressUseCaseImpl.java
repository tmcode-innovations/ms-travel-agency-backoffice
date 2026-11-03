package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregress.VoucherEgressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherEgress;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress.PersistVoucherEgressNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress.VoucherEgressRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail.PersistVoucherEgressDetailNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail.VoucherEgressDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem.VoucherEgressDetailItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.BankRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.detraction.DetractionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregress.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.VoucherEgressDetailItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.voucheregress.JRVoucherEgressRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateServiceInvoiceEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.BankDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherEgressDetailItemUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherEgressDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherEgressUseCase;
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
public class VoucherEgressUseCaseImpl implements VoucherEgressUseCase {

    @Autowired
    private VoucherEgressRepo repo;

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private DetractionRepo detractionRepo;

    @Autowired
    private VoucherEgressDetailRepo voucherEgressDetailRepo;

    @Autowired
    private VoucherEgressDetailItemRepo voucherEgressDetailItemRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private DepartmentRegionRepo departmentRegionRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private VoucherEgressConverter converter;

    @Autowired
    PersistVoucherEgressNumberCallbackImpl persistVoucherEgressNumberCallback;

    @Autowired
    PersistVoucherEgressDetailNumberCallbackImpl persistVoucherEgressDetailNumberCallback;

    @Autowired
    private SetFieldDefaultVoucherEgress setFieldDefault;

    @Autowired
    private VoucherEgressDetailUseCase voucherEgressDetailUseCase;

    @Autowired
    private VoucherEgressDetailItemUseCase voucherEgressDetailItemUseCase;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRVoucherEgressRepository jrVoucherEgressRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public VoucherEgressResponse create(VoucherEgressCreateRequest request) {
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

        EmployeeDomain employeeDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            employeeDomain = new EmployeeDomain();
            employeeDomain.setId(employeeIdBaseDomain.getId());
        }

        BankDomain bankDomain = null;
        if (request.getBank() != null && !request.getBank().isEmpty()){
            IdBaseDomain bankIdBaseDomain = bankRepo.findByIdEntity(request.getBank());
            bankDomain = new BankDomain();
            bankDomain.setId(bankIdBaseDomain.getId());
        }

        VoucherEgressDomain voucherEgressParentDomain = null;
        if (request.getParent() != null && !request.getParent().isEmpty()){
            IdBaseDomain voucherEgressParentIdBaseDomain = repo.findByIdEntity(request.getParent());
            voucherEgressParentDomain = new VoucherEgressDomain();
            bankDomain.setId(voucherEgressParentDomain.getId());
        }

        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            detractionDomain = new DetractionDomain();
            detractionDomain.setId(detractionIdBaseDomain.getId());
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

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }

        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
        }

        VoucherEgressDomain newDomain = null;
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            newDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain, bankDomain, voucherEgressParentDomain, detractionDomain, registerGroupDomain, supplierDomain, supplierTypeDomain, departmentRegionDomain);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            newDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain, bankDomain, voucherEgressParentDomain, detractionDomain);
        }else{
            newDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain, bankDomain, voucherEgressParentDomain, detractionDomain);
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistVoucherEgressNumberCallback.generateNumber());

        if (newDomain.getDateCancel() != null){
            newDomain.setVoucherState(VoucherStateEnum.CANCELLED.getName());
        }else{
            newDomain.setVoucherState(VoucherStateEnum.PENDING.getName());
        }

        newDomain.setImportUsd(0.0);
        newDomain.setImportSoles(0.0);
        newDomain.setDetractionUsd(0.0);
        newDomain.setDetractionSoles(0.0);
        newDomain.setImportTotalSoles(0.0);
        newDomain.setImportTotalUsd(0.0);
        VoucherEgressDomain createdDomain = repo.create(newDomain);
        return VoucherEgressResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherEgressResponse edit(String id, VoucherEgressUpdateRequest request) {
        if (id == null) {
            return null;
        }
        VoucherEgressDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
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

        EmployeeDomain employeeDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            employeeDomain = new EmployeeDomain();
            employeeDomain.setId(employeeIdBaseDomain.getId());
        }

        BankDomain bankDomain = null;
        if (request.getBank() != null && !request.getBank().isEmpty()){
            IdBaseDomain bankIdBaseDomain = bankRepo.findByIdEntity(request.getBank());
            bankDomain = new BankDomain();
            bankDomain.setId(bankIdBaseDomain.getId());
        }

        VoucherEgressDomain voucherEgressParentDomain = null;
        if (request.getParent() != null && !request.getParent().isEmpty()){
            IdBaseDomain voucherEgressParentIdBaseDomain = repo.findByIdEntity(request.getParent());
            voucherEgressParentDomain = new VoucherEgressDomain();
            bankDomain.setId(voucherEgressParentDomain.getId());
        }

        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            detractionDomain = new DetractionDomain();
            detractionDomain.setId(detractionIdBaseDomain.getId());
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

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            supplierTypeDomain = new SupplierTypeDomain();
            supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
        }

        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
        }

        VoucherEgressDomain voucherEgressDomain = null;

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            voucherEgressDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain, bankDomain, voucherEgressParentDomain, detractionDomain, registerGroupDomain, supplierDomain, supplierTypeDomain, departmentRegionDomain);
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            voucherEgressDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain, bankDomain, voucherEgressParentDomain, detractionDomain);
        }else{
            voucherEgressDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain, bankDomain, voucherEgressParentDomain, detractionDomain);
        }

        voucherEgressDomain.setIdentify(old.getIdentify());
        voucherEgressDomain.setImportUsd(old.getImportUsd());
        voucherEgressDomain.setImportSoles(old.getImportSoles());
        voucherEgressDomain.setDetractionUsd(old.getDetractionUsd());
        voucherEgressDomain.setDetractionSoles(old.getDetractionSoles());
        voucherEgressDomain.setImportTotalSoles(old.getImportTotalSoles());
        voucherEgressDomain.setImportTotalUsd(old.getImportTotalUsd());

        if (voucherEgressDomain.getDateCancel() != null){
            voucherEgressDomain.setVoucherState(VoucherStateEnum.CANCELLED.getName());
        }else{
            if (old.getVoucherState().equals(VoucherStateEnum.CANCELLED.getName())){
                voucherEgressDomain.setVoucherState(VoucherStateEnum.PENDING.getName());
            }else{
                voucherEgressDomain.setVoucherState(old.getVoucherState());
            }
        }

        voucherEgressDomain.setValueTypeShowSupplier(old.getValueTypeShowSupplier());

        VoucherEgressDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherEgressDomain, old));

        if(request.getGenerateDetraction() != null && request.getGenerateDetraction()){

            List<VoucherEgressDomain> listVoucherEgressExist = repo.searchAllByVoucherEgressCaseListSimpleByParent(editedDomain.getId());
            if (listVoucherEgressExist != null && !listVoucherEgressExist.isEmpty()){
                for (VoucherEgressDomain voucherEgressExistDomain : listVoucherEgressExist){
                    deleteById(voucherEgressExistDomain.getId());
                }
            }

            VoucherEgressDomain voucherEgressDetractionDomain = new VoucherEgressDomain();
            voucherEgressDetractionDomain.setImportUsd(0.0);
            voucherEgressDetractionDomain.setImportSoles(0.0);
            voucherEgressDetractionDomain.setDetractionUsd(0.0);
            voucherEgressDetractionDomain.setDetractionSoles(0.0);
            voucherEgressDetractionDomain.setImportTotalSoles(0.0);
            voucherEgressDetractionDomain.setImportTotalUsd(0.0);

            voucherEgressDetractionDomain.setName( "DETRACCION APLICADO AL VOUCHER: " + editedDomain.getIdentify() + " - " + LocalDateTimeUtil.toLocalDateFormatShort(editedDomain.getDate()) );

            voucherEgressDetractionDomain.setDate(editedDomain.getDate());
            voucherEgressDetractionDomain.setBank(editedDomain.getBank());
            voucherEgressDetractionDomain.setCurrency(editedDomain.getCurrency());
            voucherEgressDetractionDomain.setVoucherState(editedDomain.getVoucherState());
            voucherEgressDetractionDomain.setNoCheck(editedDomain.getNoCheck());
            voucherEgressDetractionDomain.setVoucherType(editedDomain.getVoucherType());
            voucherEgressDetractionDomain.setChangeType(editedDomain.getChangeType());
            voucherEgressDetractionDomain.setVoucherDetraction(true);
            voucherEgressDetractionDomain.setCantRegister(editedDomain.getCantRegister());

            voucherEgressDetractionDomain.setRegisterGroup(editedDomain.getRegisterGroup());
            voucherEgressDetractionDomain.setSupplier(editedDomain.getSupplier());
            voucherEgressDetractionDomain.setSupplierType(editedDomain.getSupplierType());
            voucherEgressDetractionDomain.setDepartmentRegion(editedDomain.getDepartmentRegion());

            voucherEgressDetractionDomain.setParent(editedDomain);

            voucherEgressDetractionDomain.setTenant(UserResolver.resolveUser().getTenant());
            voucherEgressDetractionDomain.setIdentify(persistVoucherEgressNumberCallback.generateNumber());

            VoucherEgressDomain createdDetractionDomain = repo.create(voucherEgressDetractionDomain);

            Double montSolesDetraction = 0.0;
            Double montUsdDetraction = 0.0;
            Double detractionSolesDetraction = 0.0;
            Double detractionUsdDetraction = 0.0;
            Double balanceSolesDetraction = 0.0;
            Double balanceUsdDetraction = 0.0;

            Integer noItem = 1;

            List<VoucherEgressDetailDomain> listVoucherEgressDetail = voucherEgressDetailRepo.searchAllByVoucherEgress(editedDomain.getId());
            if (listVoucherEgressDetail !=  null && !listVoucherEgressDetail.isEmpty()){
                for (VoucherEgressDetailDomain voucherEgressDetailDomain : listVoucherEgressDetail){
                    VoucherEgressDetailDomain voucherEgressDetailDetractionDomain = new VoucherEgressDetailDomain();

                    voucherEgressDetailDetractionDomain.setNoItem(noItem);
                    noItem = noItem + 1;
                    voucherEgressDetailDetractionDomain.setVoucherEgress(createdDetractionDomain);
                    voucherEgressDetailDetractionDomain.setCurrency(voucherEgressDetailDomain.getCurrency());
                    voucherEgressDetailDetractionDomain.setValueTypeShowSupplier(voucherEgressDetailDomain.getValueTypeShowSupplier());
                    voucherEgressDetailDetractionDomain.setRegisterGroup(voucherEgressDetailDomain.getRegisterGroup());
                    voucherEgressDetailDetractionDomain.setDocumentAccountType(voucherEgressDetailDomain.getDocumentAccountType());
                    voucherEgressDetailDetractionDomain.setDepartmentRegion(voucherEgressDetailDomain.getDepartmentRegion());
                    voucherEgressDetailDetractionDomain.setSupplier(voucherEgressDetailDomain.getSupplier());
                    voucherEgressDetailDetractionDomain.setSupplierType(voucherEgressDetailDomain.getSupplierType());
                    voucherEgressDetailDetractionDomain.setNumberDoc(voucherEgressDetailDomain.getNumberDoc());
                    voucherEgressDetailDetractionDomain.setDate(voucherEgressDetailDomain.getDate());
                    voucherEgressDetailDetractionDomain.setChangeType(voucherEgressDetailDomain.getChangeType());
                    voucherEgressDetailDetractionDomain.setObservation(voucherEgressDetailDomain.getObservation());

                    voucherEgressDetailDetractionDomain.setItineraryItem(voucherEgressDetailDomain.getItineraryItem());

                    voucherEgressDetailDetractionDomain.setMontSoles(voucherEgressDetailDomain.getDetractionSoles());
                    voucherEgressDetailDetractionDomain.setMontUsd(voucherEgressDetailDomain.getDetractionUsd());

                    voucherEgressDetailDetractionDomain.setBalanceSoles(voucherEgressDetailDomain.getDetractionSoles());
                    voucherEgressDetailDetractionDomain.setBalanceUsd(voucherEgressDetailDomain.getDetractionUsd());

                    voucherEgressDetailDetractionDomain.setDetractionSoles(0.00);
                    voucherEgressDetailDetractionDomain.setDetractionUsd(0.00);

                    voucherEgressDetailDetractionDomain.setTenant(UserResolver.resolveUser().getTenant());
                    voucherEgressDetailDetractionDomain.setIdentify(persistVoucherEgressDetailNumberCallback.generateNumber());

                    VoucherEgressDetailDomain createdDetailDetractionDomain = voucherEgressDetailRepo.create(voucherEgressDetailDetractionDomain);

                    montSolesDetraction = montSolesDetraction + createdDetailDetractionDomain.getMontSoles();
                    montUsdDetraction = montUsdDetraction + createdDetailDetractionDomain.getMontUsd();
                    detractionSolesDetraction = detractionSolesDetraction + createdDetailDetractionDomain.getDetractionSoles();
                    detractionUsdDetraction = detractionUsdDetraction + createdDetailDetractionDomain.getDetractionUsd();
                    balanceSolesDetraction = balanceSolesDetraction + createdDetailDetractionDomain.getBalanceSoles();
                    balanceUsdDetraction = balanceUsdDetraction + createdDetailDetractionDomain.getBalanceUsd();

                }
            }

            repo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(createdDetractionDomain.getId(), balanceSolesDetraction, balanceUsdDetraction, montSolesDetraction, montUsdDetraction, detractionSolesDetraction, detractionUsdDetraction);

        }

        return VoucherEgressResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        VoucherEgressResponse old = findById(id);
        List<VoucherEgressDetailDomain> detailsByDelete = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimpleByDelete(id);
        if (detailsByDelete != null && !detailsByDelete.isEmpty()){
            for (VoucherEgressDetailDomain voucherEgressDetailDomain : detailsByDelete){
                List<VoucherEgressDetailItemDomain> detailsByDeleteItems = voucherEgressDetailItemRepo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListByDelete(voucherEgressDetailDomain.getId());
                if (detailsByDeleteItems != null && !detailsByDeleteItems.isEmpty()){
                    for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomain : detailsByDeleteItems){
                        voucherEgressDetailItemRepo.deleteById(voucherEgressDetailItemDomain.getId());
                    }
                }
                if (voucherEgressDetailDomain.getItineraryItem() != null){
                    ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.findById(voucherEgressDetailDomain.getItineraryItem().getId());
                    if (itineraryItemDomain != null){
                        itineraryItemDomain.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());
                        itineraryItemRepo.edit(itineraryItemDomain);
                    }
                }
                voucherEgressDetailRepo.deleteById(voucherEgressDetailDomain.getId());
            }
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> VoucherEgressResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressResponse findById(String id) {
        if (id == null) {
            return null;
        }
        VoucherEgressDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return VoucherEgressResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressSearchResponse searchAll(VoucherEgressSearchRequest bankSearchRequest) {
       CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
       PageDTO<VoucherEgressDomain> pageDTO = repo.searchAll(bankSearchRequest);
       VoucherEgressSearchResponse voucherEgressSearchRespose = new VoucherEgressSearchResponse();

       if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
           voucherEgressSearchRespose.setItems(pageDTO.getContent().stream().map(voucherEgressDomain ->  VoucherEgressResponse.from(voucherEgressDomain)).toList());
       }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
           voucherEgressSearchRespose.setItems(pageDTO.getContent().stream().map(voucherEgressDomain ->  VoucherEgressResponse.fromConfiguration(voucherEgressDomain)).toList());
       }
        voucherEgressSearchRespose.setTotalElements(pageDTO.getTotalElements());
        voucherEgressSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return voucherEgressSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressSearchResponse searchFilterAll(VoucherEgressSearchFilterRequest bankSearchFilterRequest) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        PageDTO<VoucherEgressDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        VoucherEgressSearchResponse voucherEgressSearchRespose = new VoucherEgressSearchResponse();
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            voucherEgressSearchRespose.setItems(pageDTO.getContent().stream().map(voucherEgressDomain ->  VoucherEgressResponse.from(voucherEgressDomain)).toList());
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            voucherEgressSearchRespose.setItems(pageDTO.getContent().stream().map(voucherEgressDomain ->  VoucherEgressResponse.fromConfiguration(voucherEgressDomain)).toList());
        }
        voucherEgressSearchRespose.setTotalElements(pageDTO.getTotalElements());
        voucherEgressSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return voucherEgressSearchRespose;
    }

    @Override
    @Transactional
    public VoucherEgressResponse changeState(String id, VoucherEgressChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        VoucherEgressDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(id));
        }
        old.setVoucherState(request.getVoucherState());
        VoucherEgressDomain editedDomain = repo.edit(old);
        switch (request.getVoucherState()) {
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
        return VoucherEgressResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public VoucherEgressDetailResponse addVoucherEgressDetail(VoucherEgressDetailCreateRequest request, String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        VoucherEgressDetailResponse result = voucherEgressDetailUseCase.create(request, voucherEgressDomain);

        List<VoucherEgressDetailDomain> listVoucherEgressDetail = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimple(idVoucherEgress);
        Double montSoles = 0.0;
        Double montUsd = 0.0;
        Double detractionSoles = 0.0;
        Double detractionUsd = 0.0;
        Double balanceSoles = 0.0;
        Double balanceUsd = 0.0;
        if (listVoucherEgressDetail != null && !listVoucherEgressDetail.isEmpty()){
            for (VoucherEgressDetailDomain voucherEgressDetailDomainCalculate: listVoucherEgressDetail){
                montSoles = montSoles + voucherEgressDetailDomainCalculate.getMontSoles();
                montUsd = montUsd + voucherEgressDetailDomainCalculate.getMontUsd();
                detractionSoles = detractionSoles + voucherEgressDetailDomainCalculate.getDetractionSoles();
                detractionUsd = detractionUsd + voucherEgressDetailDomainCalculate.getDetractionUsd();
                balanceSoles = balanceSoles + voucherEgressDetailDomainCalculate.getBalanceSoles();
                balanceUsd = balanceUsd + voucherEgressDetailDomainCalculate.getBalanceUsd();
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(idVoucherEgress, balanceSoles, balanceUsd, montSoles, montUsd, detractionSoles, detractionUsd);

        return result;
    }

    @Override
    @Transactional
    public VoucherEgressDetailResponse editVoucherEgressDetail(String id, VoucherEgressDetailUpdateRequest request, String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        VoucherEgressDetailResponse result = voucherEgressDetailUseCase.edit(id, request, voucherEgressDomain);

        List<VoucherEgressDetailDomain> listVoucherEgressDetail = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimple(idVoucherEgress);
        Double montSoles = 0.0;
        Double montUsd = 0.0;
        Double detractionSoles = 0.0;
        Double detractionUsd = 0.0;
        Double balanceSoles = 0.0;
        Double balanceUsd = 0.0;
        if (listVoucherEgressDetail != null && !listVoucherEgressDetail.isEmpty()){
            for (VoucherEgressDetailDomain voucherEgressDetailDomainCalculate: listVoucherEgressDetail){
                montSoles = montSoles + voucherEgressDetailDomainCalculate.getMontSoles();
                montUsd = montUsd + voucherEgressDetailDomainCalculate.getMontUsd();
                detractionSoles = detractionSoles + voucherEgressDetailDomainCalculate.getDetractionSoles();
                detractionUsd = detractionUsd + voucherEgressDetailDomainCalculate.getDetractionUsd();
                balanceSoles = balanceSoles + voucherEgressDetailDomainCalculate.getBalanceSoles();
                balanceUsd = balanceUsd + voucherEgressDetailDomainCalculate.getBalanceUsd();
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(idVoucherEgress, balanceSoles, balanceUsd, montSoles, montUsd, detractionSoles, detractionUsd);

        return result;
    }

    @Override
    @Transactional
    public void deleteVoucherEgressDetail(String id, String idVoucherEgress) {
        VoucherEgressResponse old = findById(idVoucherEgress);
        voucherEgressDetailUseCase.deleteById(id);

        List<VoucherEgressDetailDomain> listVoucherEgressDetail = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimple(idVoucherEgress);
        Double montSoles = 0.0;
        Double montUsd = 0.0;
        Double detractionSoles = 0.0;
        Double detractionUsd = 0.0;
        Double balanceSoles = 0.0;
        Double balanceUsd = 0.0;
        if (listVoucherEgressDetail != null && !listVoucherEgressDetail.isEmpty()){
            for (VoucherEgressDetailDomain voucherEgressDetailDomainCalculate: listVoucherEgressDetail){
                montSoles = montSoles + voucherEgressDetailDomainCalculate.getMontSoles();
                montUsd = montUsd + voucherEgressDetailDomainCalculate.getMontUsd();
                detractionSoles = detractionSoles + voucherEgressDetailDomainCalculate.getDetractionSoles();
                detractionUsd = detractionUsd + voucherEgressDetailDomainCalculate.getDetractionUsd();
                balanceSoles = balanceSoles + voucherEgressDetailDomainCalculate.getBalanceSoles();
                balanceUsd = balanceUsd + voucherEgressDetailDomainCalculate.getBalanceUsd();
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(idVoucherEgress, balanceSoles, balanceUsd, montSoles, montUsd, detractionSoles, detractionUsd);

    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressDetailResponse> findAllVoucherEgressDetailByVoucherEgress(String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        return voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimple(idVoucherEgress).stream().map(voucherEgressDetailDomain -> VoucherEgressDetailResponse.from(voucherEgressDetailDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailResponse findByIdAndVoucherEgress(String id, String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        IdBaseDomain voucherEgressDomainBase = repo.findByIdEntity(idVoucherEgress);
        if (voucherEgressDomainBase == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        return voucherEgressDetailUseCase.searchByVoucherEgressAndVoucherEgressDetail(idVoucherEgress, id);
    }

    @Override
    @Transactional
    public VoucherEgressDetailItemResponse addVoucherEgressDetailItem(VoucherEgressDetailItemCreateRequest request, String idVoucherEgress, String idVoucherEgressDetail) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        if (idVoucherEgress == null) {
            return null;
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        VoucherEgressDetailDomain voucherEgressDetailDomain = voucherEgressDetailRepo.findById(idVoucherEgressDetail);
        if (voucherEgressDetailDomain == null) {
            throw new NotFoundException("No voucher egress detail found with id '%s'.".formatted(idVoucherEgressDetail));
        }
        DetractionDomain detractionDomain = detractionRepo.findById(request.getDetraction());

        VoucherEgressDetailItemResponse result = voucherEgressDetailItemUseCase.create(request, voucherEgressDomain, voucherEgressDetailDomain);

        List<VoucherEgressDetailItemDomain> listVoucherEgressDetailItem = voucherEgressDetailItemRepo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListSimple(idVoucherEgress, idVoucherEgressDetail);
        Double montSoles = 0.0;
        Double montUsd = 0.0;
        Double detractionSoles = 0.0;
        Double detractionUsd = 0.0;
        Double balanceSoles = 0.0;
        Double balanceUsd = 0.0;
        if (listVoucherEgressDetailItem != null && !listVoucherEgressDetailItem.isEmpty()){
            for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomainCalculate: listVoucherEgressDetailItem){
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    montSoles = montSoles + voucherEgressDetailItemDomainCalculate.getTotalImportSoles();
                    montUsd = montUsd + voucherEgressDetailItemDomainCalculate.getTotalImportUsd();
                }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    montSoles = montSoles + voucherEgressDetailItemDomainCalculate.getTotalImportSolesTotal();
                    montUsd = montUsd + voucherEgressDetailItemDomainCalculate.getTotalImportUsdTotal();
                }
            }
        }
        if (detractionDomain != null){
            if (montSoles > detractionDomain.getAmount()){
                detractionUsd = montUsd * detractionDomain.getPercent() / 100;
                detractionSoles = montSoles * detractionDomain.getPercent() / 100;
            }
        }
        balanceSoles = montSoles - detractionSoles;
        balanceUsd = montUsd - detractionUsd;

        voucherEgressDetailRepo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressDetailBasic(idVoucherEgressDetail, balanceUsd, balanceSoles, montUsd, montSoles, detractionUsd, detractionSoles);

        List<VoucherEgressDetailDomain> listVoucherEgressDetailRecalculate = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimple(idVoucherEgress);
        Double montSolesVoucher = 0.0;
        Double montUsdVoucher = 0.0;
        Double detractionSolesVoucher = 0.0;
        Double detractionUsdVoucher = 0.0;
        Double balanceSolesVoucher = 0.0;
        Double balanceUsdVoucher = 0.0;
        if (listVoucherEgressDetailRecalculate != null && !listVoucherEgressDetailRecalculate.isEmpty()){
            for (VoucherEgressDetailDomain voucherEgressDetailDomainRecalculate: listVoucherEgressDetailRecalculate){
                montSolesVoucher = montSolesVoucher + voucherEgressDetailDomainRecalculate.getMontSoles();
                montUsdVoucher = montUsdVoucher + voucherEgressDetailDomainRecalculate.getMontUsd();
                detractionSolesVoucher = detractionSolesVoucher + voucherEgressDetailDomainRecalculate.getDetractionSoles();
                detractionUsdVoucher = detractionUsdVoucher + voucherEgressDetailDomainRecalculate.getDetractionUsd();
                balanceSolesVoucher = balanceSolesVoucher + voucherEgressDetailDomainRecalculate.getBalanceSoles();
                balanceUsdVoucher = balanceUsdVoucher + voucherEgressDetailDomainRecalculate.getBalanceUsd();
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(idVoucherEgress, balanceSolesVoucher, balanceUsdVoucher, montSolesVoucher, montUsdVoucher, detractionSolesVoucher, detractionUsdVoucher);

        return result;
    }

    @Override
    @Transactional
    public VoucherEgressDetailItemResponse editVoucherEgressDetailItem(String id, VoucherEgressDetailItemUpdateRequest request, String idVoucherEgress, String idVoucherEgressDetail) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        if (idVoucherEgress == null) {
            return null;
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        VoucherEgressDetailDomain voucherEgressDetailDomain = voucherEgressDetailRepo.findById(idVoucherEgressDetail);
        if (voucherEgressDetailDomain == null) {
            throw new NotFoundException("No voucher egress detail found with id '%s'.".formatted(idVoucherEgressDetail));
        }
        DetractionDomain detractionDomain = detractionRepo.findById(request.getDetraction());

        VoucherEgressDetailItemResponse result = voucherEgressDetailItemUseCase.edit(id, request, voucherEgressDomain, voucherEgressDetailDomain);
        List<VoucherEgressDetailItemDomain> listVoucherEgressDetailItem = voucherEgressDetailItemRepo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListSimple(idVoucherEgress, idVoucherEgressDetail);
        Double montSoles = 0.0;
        Double montUsd = 0.0;
        Double detractionSoles = 0.0;
        Double detractionUsd = 0.0;
        Double balanceSoles = 0.0;
        Double balanceUsd = 0.0;
        if (listVoucherEgressDetailItem != null && !listVoucherEgressDetailItem.isEmpty()){
            for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomainCalculate: listVoucherEgressDetailItem){
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    montSoles = montSoles + voucherEgressDetailItemDomainCalculate.getTotalImportSoles();
                    montUsd = montUsd + voucherEgressDetailItemDomainCalculate.getTotalImportUsd();
                }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    montSoles = montSoles + voucherEgressDetailItemDomainCalculate.getTotalImportSolesTotal();
                    montUsd = montUsd + voucherEgressDetailItemDomainCalculate.getTotalImportUsdTotal();
                }
            }
        }
        if (detractionDomain != null){
            if (montSoles > detractionDomain.getAmount()){
                detractionUsd = montUsd * detractionDomain.getPercent() / 100;
                detractionSoles = montSoles * detractionDomain.getPercent() / 100;
            }
        }
        balanceSoles = montSoles - detractionSoles;
        balanceUsd = montUsd - detractionUsd;

        voucherEgressDetailRepo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressDetailBasic(idVoucherEgressDetail, balanceUsd, balanceSoles, montUsd, montSoles, detractionUsd, detractionSoles);

        List<VoucherEgressDetailDomain> listVoucherEgressDetailRecalculate = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimple(idVoucherEgress);
        Double montSolesVoucher = 0.0;
        Double montUsdVoucher = 0.0;
        Double detractionSolesVoucher = 0.0;
        Double detractionUsdVoucher = 0.0;
        Double balanceSolesVoucher = 0.0;
        Double balanceUsdVoucher = 0.0;
        if (listVoucherEgressDetailRecalculate != null && !listVoucherEgressDetailRecalculate.isEmpty()){
            for (VoucherEgressDetailDomain voucherEgressDetailDomainRecalculate: listVoucherEgressDetailRecalculate){
                montSolesVoucher = montSolesVoucher + voucherEgressDetailDomainRecalculate.getMontSoles();
                montUsdVoucher = montUsdVoucher + voucherEgressDetailDomainRecalculate.getMontUsd();
                detractionSolesVoucher = detractionSolesVoucher + voucherEgressDetailDomainRecalculate.getDetractionSoles();
                detractionUsdVoucher = detractionUsdVoucher + voucherEgressDetailDomainRecalculate.getDetractionUsd();
                balanceSolesVoucher = balanceSolesVoucher + voucherEgressDetailDomainRecalculate.getBalanceSoles();
                balanceUsdVoucher = balanceUsdVoucher + voucherEgressDetailDomainRecalculate.getBalanceUsd();
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(idVoucherEgress, balanceSolesVoucher, balanceUsdVoucher, montSolesVoucher, montUsdVoucher, detractionSolesVoucher, detractionUsdVoucher);

        return result;
    }

    @Override
    @Transactional
    public void deleteVoucherEgressDetailItem(String id, String idVoucherEgress, String idVoucherEgressDetail) {
        VoucherEgressResponse old = findById(idVoucherEgress);
        VoucherEgressDetailResponse oldDetail = voucherEgressDetailUseCase.findById(idVoucherEgressDetail);
        voucherEgressDetailItemUseCase.deleteById(id);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<VoucherEgressDetailItemDomain> listVoucherEgressDetailItem = voucherEgressDetailItemRepo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListSimple(idVoucherEgress, idVoucherEgressDetail);
        Double montSoles = 0.0;
        Double montUsd = 0.0;
        Double detractionSoles = 0.0;
        Double detractionUsd = 0.0;
        Double balanceSoles = 0.0;
        Double balanceUsd = 0.0;
        if (listVoucherEgressDetailItem != null && !listVoucherEgressDetailItem.isEmpty()){
            for (VoucherEgressDetailItemDomain voucherEgressDetailItemDomainCalculate: listVoucherEgressDetailItem){
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    montSoles = montSoles + voucherEgressDetailItemDomainCalculate.getTotalImportSoles();
                    montUsd = montUsd + voucherEgressDetailItemDomainCalculate.getTotalImportUsd();
                }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    montSoles = montSoles + voucherEgressDetailItemDomainCalculate.getTotalImportSolesTotal();
                    montUsd = montUsd + voucherEgressDetailItemDomainCalculate.getTotalImportUsdTotal();
                }
                if (voucherEgressDetailItemDomainCalculate.getDetraction() != null){
                    if (voucherEgressDetailItemDomainCalculate.getTotalImportSoles() > voucherEgressDetailItemDomainCalculate.getDetraction().getAmount()){
                        detractionUsd = detractionUsd + (voucherEgressDetailItemDomainCalculate.getTotalImportUsd() * voucherEgressDetailItemDomainCalculate.getDetraction().getPercent() / 100);
                        detractionSoles = detractionSoles + (voucherEgressDetailItemDomainCalculate.getTotalImportSoles() * voucherEgressDetailItemDomainCalculate.getDetraction().getPercent() / 100);
                    }
                }
            }
        }
        balanceSoles = montSoles - detractionSoles;
        balanceUsd = montUsd - detractionUsd;

        voucherEgressDetailRepo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressDetailBasic(idVoucherEgressDetail, balanceUsd, balanceSoles, montUsd, montSoles, detractionUsd, detractionSoles);

        List<VoucherEgressDetailDomain> listVoucherEgressDetailRecalculate = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimple(idVoucherEgress);
        Double montSolesVoucher = 0.0;
        Double montUsdVoucher = 0.0;
        Double detractionSolesVoucher = 0.0;
        Double detractionUsdVoucher = 0.0;
        Double balanceSolesVoucher = 0.0;
        Double balanceUsdVoucher = 0.0;
        if (listVoucherEgressDetailRecalculate != null && !listVoucherEgressDetailRecalculate.isEmpty()){
            for (VoucherEgressDetailDomain voucherEgressDetailDomainRecalculate: listVoucherEgressDetailRecalculate){
                montSolesVoucher = montSolesVoucher + voucherEgressDetailDomainRecalculate.getMontSoles();
                montUsdVoucher = montUsdVoucher + voucherEgressDetailDomainRecalculate.getMontUsd();
                detractionSolesVoucher = detractionSolesVoucher + voucherEgressDetailDomainRecalculate.getDetractionSoles();
                detractionUsdVoucher = detractionUsdVoucher + voucherEgressDetailDomainRecalculate.getDetractionUsd();
                balanceSolesVoucher = balanceSolesVoucher + voucherEgressDetailDomainRecalculate.getBalanceSoles();
                balanceUsdVoucher = balanceUsdVoucher + voucherEgressDetailDomainRecalculate.getBalanceUsd();
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(idVoucherEgress, balanceSolesVoucher, balanceUsdVoucher, montSolesVoucher, montUsdVoucher, detractionSolesVoucher, detractionUsdVoucher);

    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressDetailItemResponse> findAllVoucherEgressDetailItemByVoucherEgressAndVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDetailDomain voucherEgressDetailDomain = voucherEgressDetailRepo.findById(idVoucherEgressDetail);
        if (voucherEgressDetailDomain == null) {
            throw new NotFoundException("No voucher egress detail found with id '%s'.".formatted(idVoucherEgressDetail));
        }
        return voucherEgressDetailItemRepo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListSimple(idVoucherEgress, idVoucherEgressDetail).stream().map(voucherEgressDetailItemDomain -> VoucherEgressDetailItemResponse.from(voucherEgressDetailItemDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailItemResponse findByIdAndVoucherEgressAndVoucherEgressDetail(String id, String idVoucherEgress, String idVoucherEgressDetail) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDetailDomain voucherEgressDetailDomain = voucherEgressDetailRepo.findById(idVoucherEgressDetail);
        if (voucherEgressDetailDomain == null) {
            throw new NotFoundException("No voucher egress detail found with id '%s'.".formatted(idVoucherEgressDetail));
        }
        return voucherEgressDetailItemUseCase.searchByVoucherEgressAndVoucherEgressDetailAndVoucherEgressDetailItem(idVoucherEgress, idVoucherEgressDetail, id);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressResponse getVoucherEgressModificationData(String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        IdBaseDomain voucherEgressIdBaseDomain = repo.findByIdEntity(idVoucherEgress);
        if (voucherEgressIdBaseDomain == null){
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }

        return VoucherEgressResponse.from(repo.findByIdVoucherEgressCaseGetModificationData(idVoucherEgress));
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailResponse getVoucherEgressDetailModificationData(String id, String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }
        return voucherEgressDetailUseCase.getVoucherEgressDetailModificationData(idVoucherEgress, id);
    }

    @Override
    @Transactional
    public byte[] exportVoucherEgressPdf(String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }

        List<VoucherEgressDomain> list = new ArrayList<VoucherEgressDomain>();
        list.add(voucherEgressDomain);

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

        List<VoucherEgressDetailDomain> voucherEgressDetailDomains = voucherEgressDetailRepo.searchAllByVoucherEgressCaseListSimpleByExportPdf(idVoucherEgress);
        if (voucherEgressDetailDomains != null && !voucherEgressDetailDomains.isEmpty()) {
            voucherEgressDetailDomains = voucherEgressDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", voucherEgressDetailDomains.size());

        byte[] result = new byte[0];
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportUsd(list, voucherEgressDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportSoles(list, voucherEgressDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportUsdGuru(list, voucherEgressDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportSolesGuru(list, voucherEgressDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public byte[] exportVoucherEgressExcel(String idVoucherEgress) {
        if (idVoucherEgress == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        VoucherEgressDomain voucherEgressDomain = repo.findById(idVoucherEgress);
        if (voucherEgressDomain == null) {
            throw new NotFoundException("No voucher egress found with id '%s'.".formatted(idVoucherEgress));
        }

        List<VoucherEgressDomain> list = new ArrayList<VoucherEgressDomain>();
        list.add(voucherEgressDomain);

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

        List<VoucherEgressDetailDomain> voucherEgressDetailDomains = voucherEgressDetailRepo.searchAllByVoucherEgress(idVoucherEgress);
        if (voucherEgressDetailDomains != null && !voucherEgressDetailDomains.isEmpty()) {
            voucherEgressDetailDomains = voucherEgressDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", voucherEgressDetailDomains.size());

        byte[] result = new byte[0];
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportUsd(list, voucherEgressDetailDomains, ExportFormatEnum.XLSX.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportSoles(list, voucherEgressDetailDomains, ExportFormatEnum.XLSX.getName(), parameters);
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportUsdGuru(list, voucherEgressDetailDomains, ExportFormatEnum.XLSX.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(voucherEgressDomain.getCurrency().getId())){
                result = jrVoucherEgressRepository.exportSolesGuru(list, voucherEgressDetailDomains, ExportFormatEnum.XLSX.getName(), parameters);
            }
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressResponse> findAllSimplePending() {
        return repo.findAllSimplePending().stream().map(voucherEgressDomain -> VoucherEgressResponse.from(voucherEgressDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressResponse> findAllSimplePendingBooking() {
        return repo.findAllSimplePendingBooking().stream().map(voucherEgressDomain -> VoucherEgressResponse.from(voucherEgressDomain)).toList();
    }

    @Override
    public void duplicateVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail) {
        VoucherEgressResponse old = findById(idVoucherEgress);
        voucherEgressDetailUseCase.duplicate(idVoucherEgressDetail);
    }

    private void _changeStatePending(VoucherEgressDomain voucherEgressDomain, VoucherEgressChangeStateRequest request){

    }

    private void _changeStateCancelled(VoucherEgressDomain voucherEgressDomain, VoucherEgressChangeStateRequest request){

    }

    private void _changeStateAnnulled(VoucherEgressDomain voucherEgressDomain, VoucherEgressChangeStateRequest request){

    }
}
