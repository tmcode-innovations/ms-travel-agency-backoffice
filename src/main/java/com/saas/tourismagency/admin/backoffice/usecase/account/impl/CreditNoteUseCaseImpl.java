package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.creditnote.CreditNoteConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultCreditNote;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.creditnote.CreditNoteRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinance.DocumentFinanceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinancedetail.DocumentFinanceDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.creditnote.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinancedetail.DocumentFinanceDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.creditnote.JRCreditNoteRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.creditnote.JRCreditNoteSolesRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.creditnote.JRCreditNoteUsdRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.TypeInheritanceDocumentFinanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.CreditNoteUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.DocumentFinanceDetailUseCase;
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
public class CreditNoteUseCaseImpl implements CreditNoteUseCase {

    @Autowired
    private CreditNoteRepo repo;

    @Autowired
    private CreditNoteConverter converter;

    @Autowired
    private SetFieldDefaultCreditNote setFieldDefault;

    @Autowired
    private DocumentFinanceDetailUseCase documentFinanceDetailUseCase;

    @Autowired
    private DocumentFinanceDetailRepo documentFinanceDetailRepo;

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private DocumentFinanceRepo documentFinanceRepo;

    @Autowired
    private JRCreditNoteRepository jrCreditNoteRepository;

    @Autowired
    private JRCreditNoteUsdRepository jrCreditNoteUsdRepository;

    @Autowired
    private JRCreditNoteSolesRepository jrCreditNoteSolesRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private DocumentFinanceDetailConverter documentFinanceDetailConverter;

    @Override
    @Transactional
    public CreditNoteResponse create(CreditNoteCreateRequest request) {
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

        EmployeeDomain employeeDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            employeeDomain = new EmployeeDomain();
            employeeDomain.setId(employeeIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
            currencyDomain.setName(currencyIdBaseDomain.getName());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }

        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            IdBaseDomain countryIdBaseDomain = countryRepo.findByIdEntity(request.getCountry());
            countryDomain = new CountryDomain();
            countryDomain.setId(countryIdBaseDomain.getId());
        }

        DocumentFinanceDomain documentFinanceDomain = null;
        if (request.getDocumentFinanceAssociate() != null && !request.getDocumentFinanceAssociate().isEmpty()){
            IdBaseDomain documentIdBaseDomain = documentFinanceRepo.findByIdEntity(request.getDocumentFinanceAssociate());
            documentFinanceDomain = new DocumentFinanceDomain();
            documentFinanceDomain.setId(documentIdBaseDomain.getId());
        }

        CreditNoteDomain newDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                registerGroupDomain,
                employeeDomain,
                documentFinanceDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setDocumentAccountState(DocumentAccountStateEnum.PENDING.getName());
        newDomain.setDocumentAccountType(DocumentAccountTypeEnum.CREDIT_NOTE.getName());

        newDomain.setImportTotalSoles(0.0);
        newDomain.setImportTotalUsd(0.0);
        newDomain.setImportSubTotalSoles(0.0);
        newDomain.setImportSubTotalUsd(0.0);
        newDomain.setImportIgvSoles(0.0);
        newDomain.setImportIgvUsd(0.0);

        newDomain.setTypeInheritanceDocumentFinance(TypeInheritanceDocumentFinanceEnum.DOCUMENT_FINANCE_CREDIT_NOTE.getName());

        CreditNoteDomain createdDomain = repo.create(newDomain);
        return CreditNoteResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CreditNoteResponse edit(String id, CreditNoteUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CreditNoteDomain old = repo.findById(id);
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

        EmployeeDomain employeeDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            employeeDomain = new EmployeeDomain();
            employeeDomain.setId(employeeIdBaseDomain.getId());
        }

        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            currencyDomain = new CurrencyDomain();
            currencyDomain.setId(currencyIdBaseDomain.getId());
            currencyDomain.setName(currencyIdBaseDomain.getName());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }

        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }

        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            IdBaseDomain countryIdBaseDomain = countryRepo.findByIdEntity(request.getCountry());
            countryDomain = new CountryDomain();
            countryDomain.setId(countryIdBaseDomain.getId());
        }

        DocumentFinanceDomain documentFinanceDomain = null;
        if (request.getDocumentFinanceAssociate() != null && !request.getDocumentFinanceAssociate().isEmpty()){
            IdBaseDomain documentIdBaseDomain = documentFinanceRepo.findByIdEntity(request.getDocumentFinanceAssociate());
            documentFinanceDomain = new DocumentFinanceDomain();
            documentFinanceDomain.setId(documentIdBaseDomain.getId());
        }

        CreditNoteDomain creditNoteDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                registerGroupDomain,
                employeeDomain,
                documentFinanceDomain
        );
        creditNoteDomain.setDocumentAccountState(old.getDocumentAccountState());
        creditNoteDomain.setDocumentAccountType(old.getDocumentAccountType());
        creditNoteDomain.setTypeInheritanceDocumentFinance(old.getTypeInheritanceDocumentFinance());
        creditNoteDomain.setImportTotalSoles(old.getImportTotalSoles());
        creditNoteDomain.setImportTotalUsd(old.getImportTotalUsd());
        creditNoteDomain.setImportSubTotalSoles(old.getImportSubTotalSoles());
        creditNoteDomain.setImportSubTotalUsd(old.getImportSubTotalUsd());
        creditNoteDomain.setImportIgvSoles(old.getImportIgvSoles());
        creditNoteDomain.setImportIgvUsd(old.getImportIgvUsd());
        CreditNoteDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(creditNoteDomain, old));
        return CreditNoteResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CreditNoteResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditNoteResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> CreditNoteResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CreditNoteResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CreditNoteDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return CreditNoteResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditNoteSearchResponse searchAll(CreditNoteSearchRequest bankSearchRequest) {
       PageDTO<CreditNoteDomain> pageDTO = repo.searchAll(bankSearchRequest);
       CreditNoteSearchResponse bankSearchRespose = new CreditNoteSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CreditNoteResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CreditNoteSearchResponse searchFilterAll(CreditNoteSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<CreditNoteDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        CreditNoteSearchResponse bankSearchRespose = new CreditNoteSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  CreditNoteResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional
    public DocumentFinanceDetailResponse addDocumentFinanceDetail(DocumentFinanceDetailCreateRequest request, String idDocumentFinance) {
        if (idDocumentFinance == null) {
            return null;
        }
        CreditNoteDomain creditNoteDomain = repo.findById(idDocumentFinance);
        if (creditNoteDomain == null) {
            throw new NotFoundException("No credit note found with id '%s'.".formatted(idDocumentFinance));
        }
        DocumentFinanceDetailResponse result = documentFinanceDetailUseCase.create(request, creditNoteDomain);

        double subTotalSoles = 0.00;
        double subTotalUsd = 0.00;

        double subTotalPrice = 0.00;

        double igvSoles = 0.00;
        double igvUsd = 0.00;

        List<DocumentFinanceDetailDomain> listDocumentFinanceDetail = documentFinanceDetailRepo.searchAllByDocumentFinanceCaseListSimple(idDocumentFinance);

        if (listDocumentFinanceDetail != null && !listDocumentFinanceDetail.isEmpty()){
            for (DocumentFinanceDetailDomain documentFinanceDetailDomain: listDocumentFinanceDetail){
                subTotalSoles = subTotalSoles + (documentFinanceDetailDomain.getImportSoles() != null ? documentFinanceDetailDomain.getImportSoles() : 0.00);
                subTotalUsd = subTotalUsd + (documentFinanceDetailDomain.getImportUsd() != null ? documentFinanceDetailDomain.getImportUsd() : 0.00);

                subTotalPrice = subTotalPrice + (documentFinanceDetailDomain.getPriceUnit() != null ? documentFinanceDetailDomain.getPriceUnit() : 0.00);

                igvSoles = igvSoles + (documentFinanceDetailDomain.getImportIgvSoles() != null ? documentFinanceDetailDomain.getImportIgvSoles() : 0.00);
                igvUsd = igvUsd + (documentFinanceDetailDomain.getImportIgvUsd() != null ? documentFinanceDetailDomain.getImportIgvUsd() : 0.00);
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateDocumentFinanceBasic(
                idDocumentFinance,
                subTotalSoles + igvSoles,
                subTotalUsd + igvUsd,
                subTotalUsd,
                subTotalSoles,
                igvUsd,
                igvSoles,
                subTotalPrice);

        return result;
    }

    @Override
    @Transactional
    public DocumentFinanceDetailResponse editDocumentFinanceDetail(String id, DocumentFinanceDetailUpdateRequest request, String idDocumentFinance) {
        if (idDocumentFinance == null) {
            return null;
        }
        CreditNoteDomain creditNoteDomain = repo.findById(idDocumentFinance);
        if (creditNoteDomain == null) {
            throw new NotFoundException("No credit note found with id '%s'.".formatted(idDocumentFinance));
        }
        DocumentFinanceDetailResponse result = documentFinanceDetailUseCase.edit(id, request, creditNoteDomain);

        double subTotalSoles = 0.00;
        double subTotalUsd = 0.00;

        double subTotalPrice = 0.00;

        double igvSoles = 0.00;
        double igvUsd = 0.00;

        List<DocumentFinanceDetailDomain> listDocumentFinanceDetail = documentFinanceDetailRepo.searchAllByDocumentFinanceCaseListSimple(idDocumentFinance);

        if (listDocumentFinanceDetail != null && !listDocumentFinanceDetail.isEmpty()){
            for (DocumentFinanceDetailDomain documentFinanceDetailDomain: listDocumentFinanceDetail){
                subTotalSoles = subTotalSoles + (documentFinanceDetailDomain.getImportSoles() != null ? documentFinanceDetailDomain.getImportSoles() : 0.00);
                subTotalUsd = subTotalUsd + (documentFinanceDetailDomain.getImportUsd() != null ? documentFinanceDetailDomain.getImportUsd() : 0.00);

                subTotalPrice = subTotalPrice + (documentFinanceDetailDomain.getPriceUnit() != null ? documentFinanceDetailDomain.getPriceUnit() : 0.00);

                igvSoles = igvSoles + (documentFinanceDetailDomain.getImportIgvSoles() != null ? documentFinanceDetailDomain.getImportIgvSoles() : 0.00);
                igvUsd = igvUsd + (documentFinanceDetailDomain.getImportIgvUsd() != null ? documentFinanceDetailDomain.getImportIgvUsd() : 0.00);
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateDocumentFinanceBasic(
                idDocumentFinance,
                subTotalSoles + igvSoles,
                subTotalUsd + igvUsd,
                subTotalUsd,
                subTotalSoles,
                igvUsd,
                igvSoles,
                subTotalPrice);

        return result;
    }

    @Override
    @Transactional
    public void deleteDocumentFinanceDetail(String id, String idDocumentFinance) {
        CreditNoteResponse old = findById(idDocumentFinance);
        documentFinanceDetailUseCase.deleteById(id);

        double subTotalSoles = 0.00;
        double subTotalUsd = 0.00;

        double subTotalPrice = 0.00;

        double igvSoles = 0.00;
        double igvUsd = 0.00;

        List<DocumentFinanceDetailDomain> listDocumentFinanceDetail = documentFinanceDetailRepo.searchAllByDocumentFinanceCaseListSimple(idDocumentFinance);

        if (listDocumentFinanceDetail != null && !listDocumentFinanceDetail.isEmpty()){
            for (DocumentFinanceDetailDomain documentFinanceDetailDomain: listDocumentFinanceDetail){
                subTotalSoles = subTotalSoles + (documentFinanceDetailDomain.getImportSoles() != null ? documentFinanceDetailDomain.getImportSoles() : 0.00);
                subTotalUsd = subTotalUsd + (documentFinanceDetailDomain.getImportUsd() != null ? documentFinanceDetailDomain.getImportUsd() : 0.00);

                subTotalPrice = subTotalPrice + (documentFinanceDetailDomain.getPriceUnit() != null ? documentFinanceDetailDomain.getPriceUnit() : 0.00);

                igvSoles = igvSoles + (documentFinanceDetailDomain.getImportIgvSoles() != null ? documentFinanceDetailDomain.getImportIgvSoles() : 0.00);
                igvUsd = igvUsd + (documentFinanceDetailDomain.getImportIgvUsd() != null ? documentFinanceDetailDomain.getImportIgvUsd() : 0.00);
            }
        }

        repo.updateIndexByIdSimpleDomainCaseUpdateDocumentFinanceBasic(
                idDocumentFinance,
                subTotalSoles + igvSoles,
                subTotalUsd + igvUsd,
                subTotalUsd,
                subTotalSoles,
                igvUsd,
                igvSoles,
                subTotalPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentFinanceDetailResponse> findAllDocumentFinanceDetailByDocumentFinance(String idDocumentFinance) {
        if (idDocumentFinance == null) {
            return null;
        }
        CreditNoteDomain creditNoteDomain = repo.findById(idDocumentFinance);
        if (creditNoteDomain == null) {
            throw new NotFoundException("No credit note found with id '%s'.".formatted(idDocumentFinance));
        }
        return documentFinanceDetailRepo.searchAllByDocumentFinance(idDocumentFinance).stream().map(DocumentFinanceDetailResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance) {
        if (idDocumentFinance == null) {
            return null;
        }
        CreditNoteDomain creditNoteDomain = repo.findById(idDocumentFinance);
        if (creditNoteDomain == null) {
            throw new NotFoundException("No credit note found with id '%s'.".formatted(idDocumentFinance));
        }
        return documentFinanceDetailUseCase.searchByDocumentFinanceAndDocumentFinanceDetail(idDocumentFinance, id);
    }

    @Override
    @Transactional(readOnly = true)
    public CreditNoteResponse getCreditNoteModificationData(String idCreditNote) {
        if (idCreditNote == null) {
            return null;
        }
        IdBaseDomain creditNoteIdBaseDomain = repo.findByIdEntity(idCreditNote);
        if (creditNoteIdBaseDomain == null){
            throw new NotFoundException("No credit note found with id '%s'.".formatted(idCreditNote));
        }

        return CreditNoteResponse.from(repo.findByIdCreditNoteCaseGetModificationData(idCreditNote));
    }

    @Override
    @Transactional
    public byte[] exportCreditNotePdf(String idCreditNote) {
        if (idCreditNote == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        CreditNoteDomain creditNoteDomain = repo.findById(idCreditNote);
        if (creditNoteDomain == null) {
            throw new NotFoundException("No credit note found with id '%s'.".formatted(idCreditNote));
        }

        List<CreditNoteDomain> list = new ArrayList<CreditNoteDomain>();
        list.add(creditNoteDomain);

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

        List<DocumentFinanceDetailDomain> documentFinanceDetailDomains = documentFinanceDetailRepo.searchAllByDocumentFinance(idCreditNote);
        if (documentFinanceDetailDomains != null && !documentFinanceDetailDomains.isEmpty()) {
            documentFinanceDetailDomains = documentFinanceDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", documentFinanceDetailDomains.size());

        byte[] result = new byte[0];
        if (creditNoteDomain.getCurrency() == null) {
            return result;
        }
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(creditNoteDomain.getCurrency().getId())){
                result = jrCreditNoteUsdRepository.exportUsd(converter.toRptDocumentAccountUsdList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailUsdList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(creditNoteDomain.getCurrency().getId())){
                result = jrCreditNoteSolesRepository.exportSoles(converter.toRptDocumentAccountSolesList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailSolesList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(creditNoteDomain.getCurrency().getId())){
                result = jrCreditNoteUsdRepository.exportUsd(converter.toRptDocumentAccountUsdList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailUsdList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(creditNoteDomain.getCurrency().getId())){
                result = jrCreditNoteSolesRepository.exportSoles(converter.toRptDocumentAccountSolesList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailSolesList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }
        }
        return result;
    }
}
