package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.expenseguide.ExpenseGuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultExpenseGuide;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguide.ExpenseGuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguide.PersistExpenseGuideNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguidedetail.ExpenseGuideDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress.PersistVoucherIngressNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.GuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguide.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.ExpenseGuideDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.expenseguide.JRExpenseGuideRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.ExpenseGuideDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.ExpenseGuideUseCase;
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
public class ExpenseGuideUseCaseImpl implements ExpenseGuideUseCase {

    @Autowired
    private ExpenseGuideRepo repo;

    @Autowired
    private ExpenseGuideConverter converter;

    @Autowired
    private SetFieldDefaultExpenseGuide setFieldDefault;

    @Autowired
    private GuideRepo guideRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private SupplierLodgingRepo supplierLodgingRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private ExpenseGuideDetailUseCase expenseGuideDetailUseCase;

    @Autowired
    private ExpenseGuideDetailRepo expenseGuideDetailRepo;

    @Autowired
    PersistExpenseGuideNumberCallbackImpl persistExpenseGuideNumberCallback;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRExpenseGuideRepository jrExpenseGuideRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public ExpenseGuideResponse create(ExpenseGuideCreateRequest request) {
        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getSupplierLodging());
            supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
            currencyDomain.setName(currencyIdBaseDomain.getName());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DateProcessDomain dateProcessDomain = null;
        if (request.getDateProcess() != null && !request.getDateProcess().isEmpty()){
            IdBaseDomain dateProcessIdBaseDomain = dateProcessRepo.findByIdEntity(request.getDateProcess());
            dateProcessDomain = new DateProcessDomain();
            dateProcessDomain.setId(dateProcessIdBaseDomain.getId());
        }

        ExpenseGuideDomain newDomain = request.to(
                guideTransferDomain,
                registerGroupDomain,
                supplierLodgingDomain,
                currencyDomain,
                changeTypeDomain,
                dateProcessDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistExpenseGuideNumberCallback.generateNumber());
        ExpenseGuideDomain createdDomain = repo.create(newDomain);
        return ExpenseGuideResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ExpenseGuideResponse edit(String id, ExpenseGuideUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ExpenseGuideDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        GuideDomain guideTransferDomain = null;
        if (request.getGuideTransfer() != null && !request.getGuideTransfer().isEmpty()){
            IdBaseDomain guideTransferIdBaseDomain = guideRepo.findByIdEntity(request.getGuideTransfer());
            guideTransferDomain = new GuideDomain();
            guideTransferDomain.setId(guideTransferIdBaseDomain.getId());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getSupplierLodging());
            supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
        }

        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }

        DateProcessDomain dateProcessDomain = null;
        if (request.getDateProcess() != null && !request.getDateProcess().isEmpty()){
            IdBaseDomain dateProcessIdBaseDomain = dateProcessRepo.findByIdEntity(request.getDateProcess());
            dateProcessDomain = new DateProcessDomain();
            dateProcessDomain.setId(dateProcessIdBaseDomain.getId());
        }

        ExpenseGuideDomain expenseGuideDomain = request.to(
                guideTransferDomain,
                registerGroupDomain,
                supplierLodgingDomain,
                currencyDomain,
                changeTypeDomain,
                dateProcessDomain
        );
        expenseGuideDomain.setIdentify(old.getIdentify());
        ExpenseGuideDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(expenseGuideDomain, old));

        return ExpenseGuideResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ExpenseGuideResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseGuideResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> ExpenseGuideResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ExpenseGuideDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ExpenseGuideResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideSearchResponse searchAll(ExpenseGuideSearchRequest bankSearchRequest) {
       PageDTO<ExpenseGuideDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ExpenseGuideSearchResponse bankSearchRespose = new ExpenseGuideSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ExpenseGuideResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideSearchResponse searchFilterAll(ExpenseGuideSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ExpenseGuideDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ExpenseGuideSearchResponse bankSearchRespose = new ExpenseGuideSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ExpenseGuideResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional
    public ExpenseGuideDetailResponse addExpenseGuideDetail(ExpenseGuideDetailCreateRequest request, String idExpenseGuide) {
        if (idExpenseGuide == null) {
            return null;
        }
        ExpenseGuideDomain expenseGuideDomain = repo.findById(idExpenseGuide);
        if (expenseGuideDomain == null) {
            throw new NotFoundException("No expense guide found with id '%s'.".formatted(idExpenseGuide));
        }
        ExpenseGuideDetailResponse result = expenseGuideDetailUseCase.create(request, expenseGuideDomain);

        double subTotalSoles = 0.00;
        double subTotalUsd = 0.00;
        double subTotalPrice = 0.00;

        List<ExpenseGuideDetailDomain> listDetail = expenseGuideDetailRepo.searchAllByExpenseGuide(idExpenseGuide);
        if (listDetail != null && !listDetail.isEmpty()){
            for (ExpenseGuideDetailDomain detail : listDetail){
                subTotalUsd = subTotalUsd + (detail.getImportUsd() != null ? detail.getImportUsd() : 0.00);
                subTotalSoles = subTotalSoles + (detail.getImportSoles() != null ? detail.getImportSoles() : 0.00);
                subTotalPrice = subTotalPrice + (detail.getPriceUnit() != null ? detail.getPriceUnit() : 0.00);
            }
        }

        expenseGuideDomain.setImportSubTotalUsd(subTotalUsd);
        expenseGuideDomain.setImportSubTotalSoles(subTotalSoles);
        expenseGuideDomain.setImportTotalUsd(subTotalUsd);
        expenseGuideDomain.setImportTotalSoles(subTotalSoles);
        expenseGuideDomain.setSubTotalPrice(subTotalPrice);

        repo.edit(expenseGuideDomain);

        return result;
    }

    @Override
    @Transactional
    public ExpenseGuideDetailResponse editExpenseGuideDetail(String id, ExpenseGuideDetailUpdateRequest request, String idExpenseGuide) {
        if (idExpenseGuide == null) {
            return null;
        }
        ExpenseGuideDomain expenseGuideDomain = repo.findById(idExpenseGuide);
        if (expenseGuideDomain == null) {
            throw new NotFoundException("No expense guide found with id '%s'.".formatted(idExpenseGuide));
        }
        ExpenseGuideDetailResponse result = expenseGuideDetailUseCase.edit(id, request, expenseGuideDomain);

        double subTotalSoles = 0.00;
        double subTotalUsd = 0.00;
        double subTotalPrice = 0.00;

        List<ExpenseGuideDetailDomain> listDetail = expenseGuideDetailRepo.searchAllByExpenseGuide(idExpenseGuide);
        if (listDetail != null && !listDetail.isEmpty()){
            for (ExpenseGuideDetailDomain detail : listDetail){
                subTotalUsd = subTotalUsd + (detail.getImportUsd() != null ? detail.getImportUsd() : 0.00);
                subTotalSoles = subTotalSoles + (detail.getImportSoles() != null ? detail.getImportSoles() : 0.00);
                subTotalPrice = subTotalPrice + (detail.getPriceUnit() != null ? detail.getPriceUnit() : 0.00);
            }
        }

        expenseGuideDomain.setImportSubTotalUsd(subTotalUsd);
        expenseGuideDomain.setImportSubTotalSoles(subTotalSoles);
        expenseGuideDomain.setImportTotalUsd(subTotalUsd);
        expenseGuideDomain.setImportTotalSoles(subTotalSoles);
        expenseGuideDomain.setSubTotalPrice(subTotalPrice);

        repo.edit(expenseGuideDomain);

        return result;
    }

    @Override
    @Transactional
    public void deleteExpenseGuideDetail(String id, String idExpenseGuide) {
        ExpenseGuideResponse old = findById(idExpenseGuide);
        expenseGuideDetailUseCase.deleteById(id);

        double subTotalSoles = 0.00;
        double subTotalUsd = 0.00;
        double subTotalPrice = 0.00;

        List<ExpenseGuideDetailDomain> listDetail = expenseGuideDetailRepo.searchAllByExpenseGuide(idExpenseGuide);
        if (listDetail != null && !listDetail.isEmpty()){
            for (ExpenseGuideDetailDomain detail : listDetail){
                subTotalUsd = subTotalUsd + (detail.getImportUsd() != null ? detail.getImportUsd() : 0.00);
                subTotalSoles = subTotalSoles + (detail.getImportSoles() != null ? detail.getImportSoles() : 0.00);
                subTotalPrice = subTotalPrice + (detail.getPriceUnit() != null ? detail.getPriceUnit() : 0.00);
            }
        }

        ExpenseGuideDomain expenseGuideDomain = repo.findById(idExpenseGuide);
        if (expenseGuideDomain != null) {
            expenseGuideDomain.setImportSubTotalUsd(subTotalUsd);
            expenseGuideDomain.setImportSubTotalSoles(subTotalSoles);
            expenseGuideDomain.setImportTotalUsd(subTotalUsd);
            expenseGuideDomain.setImportTotalSoles(subTotalSoles);
            expenseGuideDomain.setSubTotalPrice(subTotalPrice);

            repo.edit(expenseGuideDomain);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseGuideDetailResponse> findAllExpenseGuideDetailByExpenseGuide(String idExpenseGuide) {
        if (idExpenseGuide == null) {
            return null;
        }
        ExpenseGuideDomain expenseGuideDomain = repo.findById(idExpenseGuide);
        if (expenseGuideDomain == null) {
            throw new NotFoundException("No expense guide found with id '%s'.".formatted(idExpenseGuide));
        }
        return expenseGuideDetailUseCase.findAllByExpenseGuide(idExpenseGuide);
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideDetailResponse findByIdAndExpenseGuide(String id, String idExpenseGuide) {
        if (idExpenseGuide == null) {
            return null;
        }
        ExpenseGuideDomain expenseGuideDomain = repo.findById(idExpenseGuide);
        if (expenseGuideDomain == null) {
            throw new NotFoundException("No expense guide found with id '%s'.".formatted(idExpenseGuide));
        }
        ExpenseGuideDetailResponse detail = expenseGuideDetailUseCase.findById(id);
        if (detail == null) {
            throw new NotFoundException("No expense guide detail found with id '%s'.".formatted(id));
        }
        return detail;
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideResponse getExpenseGuideModificationData(String idExpenseGuide) {
        if (idExpenseGuide == null) {
            return null;
        }
        IdBaseDomain expenseGuideIdBaseDomain = repo.findByIdEntity(idExpenseGuide);
        if (expenseGuideIdBaseDomain == null) {
            throw new NotFoundException("No expense guide found with id '%s'.".formatted(idExpenseGuide));
        }
        return ExpenseGuideResponse.from(repo.findById(idExpenseGuide));
    }

    @Override
    @Transactional
    public byte[] exportExpenseGuidePdf(String idExpenseGuide) {
        if (idExpenseGuide == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        ExpenseGuideDomain expenseGuideDomain = repo.findById(idExpenseGuide);
        if (expenseGuideDomain == null) {
            throw new NotFoundException("No expense guide found with id '%s'.".formatted(idExpenseGuide));
        }

        List<ExpenseGuideDomain> list = new ArrayList<ExpenseGuideDomain>();
        list.add(expenseGuideDomain);

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

        List<ExpenseGuideDetailDomain> expenseGuideDetailDomains = expenseGuideDetailRepo.searchAllByExpenseGuide(idExpenseGuide);
        if (expenseGuideDetailDomains != null && !expenseGuideDetailDomains.isEmpty()) {
            expenseGuideDetailDomains = expenseGuideDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", expenseGuideDetailDomains.size());

        return jrExpenseGuideRepository.export(list, expenseGuideDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
    }
}
