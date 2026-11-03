package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.ticket.TicketConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultTicket;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.documentfinancedetail.DocumentFinanceDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformacreditnote.ProformaCreditNoteRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice.ProformaInvoiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.ticket.TicketRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.igv.IgvRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinancedetail.DocumentFinanceDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.ticket.JRTicketRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.ticket.JRTicketSolesRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.ticket.JRTicketUsdRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.documentfinancedetail.DocumentFinanceDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.invoice.InvoiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.ticket.*;
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
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.DocumentFinanceDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.TicketUseCase;
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
public class TicketUseCaseImpl implements TicketUseCase {

    @Autowired
    private TicketRepo repo;

    @Autowired
    private TicketConverter converter;

    @Autowired
    private SetFieldDefaultTicket setFieldDefault;

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
    private IgvRepo igvRepo;

    @Autowired
    private ProformaInvoiceRepo proformaInvoiceRepo;

    @Autowired
    private ProformaCreditNoteRepo proformaCreditNoteRepo;

    @Autowired
    private JRTicketRepository jrTicketRepository;

    @Autowired
    private JRTicketUsdRepository jrTicketUsdRepository;

    @Autowired
    private JRTicketSolesRepository jrTicketSolesRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private DocumentFinanceDetailConverter documentFinanceDetailConverter;

    @Override
    @Transactional
    public TicketResponse create(TicketCreateRequest request) {
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

        ProformaInvoiceDomain proformaInvoiceDomain = null;
        if (request.getNproformayear() != null && !request.getNproformayear().isEmpty() && request.getNproformanumber() != null && !request.getNproformanumber().isEmpty()){
            IdBaseDomain proformaInvoiceIdBaseDomain = proformaInvoiceRepo.findByIdentifyEntity(request.getNproformayear() + "-" + request.getNproformanumber());
            if (proformaInvoiceIdBaseDomain != null){
                proformaInvoiceDomain = new ProformaInvoiceDomain();
                proformaInvoiceDomain.setId(proformaInvoiceIdBaseDomain.getId());
            }else{
                request.setNproformayear(null);
                request.setNproformanumber(null);
            }
        }

        TicketDomain newDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                registerGroupDomain,
                employeeDomain,
                proformaInvoiceDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setDocumentAccountState(DocumentAccountStateEnum.PENDING.getName());
        newDomain.setDocumentAccountType(DocumentAccountTypeEnum.TICKET.getName());

        newDomain.setImportTotalSoles(0.0);
        newDomain.setImportTotalUsd(0.0);
        newDomain.setImportSubTotalSoles(0.0);
        newDomain.setImportSubTotalUsd(0.0);
        newDomain.setImportIgvSoles(0.0);
        newDomain.setImportIgvUsd(0.0);

        newDomain.setTypeInheritanceDocumentFinance(TypeInheritanceDocumentFinanceEnum.DOCUMENT_FINANCE_TICKET.getName());

        TicketDomain createdDomain = repo.create(newDomain);
        return TicketResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public TicketResponse edit(String id, TicketUpdateRequest request) {
        if (id == null) {
            return null;
        }
        TicketDomain old = repo.findById(id);
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

        ProformaInvoiceDomain proformaInvoiceDomain = null;
        if (request.getNproformayear() != null && !request.getNproformayear().isEmpty() && request.getNproformanumber() != null && !request.getNproformanumber().isEmpty()){
            IdBaseDomain proformaInvoiceIdBaseDomain = proformaInvoiceRepo.findByIdentifyEntity(request.getNproformayear() + "-" + request.getNproformanumber());
            if (proformaInvoiceIdBaseDomain != null){
                proformaInvoiceDomain = new ProformaInvoiceDomain();
                proformaInvoiceDomain.setId(proformaInvoiceIdBaseDomain.getId());
            }else{
                request.setNproformayear(null);
                request.setNproformanumber(null);
            }
        }

        TicketDomain ticketDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                registerGroupDomain,
                employeeDomain,
                proformaInvoiceDomain
        );
        ticketDomain.setDocumentAccountState(old.getDocumentAccountState());
        ticketDomain.setDocumentAccountType(old.getDocumentAccountType());
        ticketDomain.setTypeInheritanceDocumentFinance(old.getTypeInheritanceDocumentFinance());
        ticketDomain.setImportTotalSoles(old.getImportTotalSoles());
        ticketDomain.setImportTotalUsd(old.getImportTotalUsd());
        ticketDomain.setImportSubTotalSoles(old.getImportSubTotalSoles());
        ticketDomain.setImportSubTotalUsd(old.getImportSubTotalUsd());
        ticketDomain.setImportIgvSoles(old.getImportIgvSoles());
        ticketDomain.setImportIgvUsd(old.getImportIgvUsd());
        TicketDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(ticketDomain, old));
        return TicketResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        TicketResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> TicketResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse findById(String id) {
        if (id == null) {
            return null;
        }
        TicketDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return TicketResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketSearchResponse searchAll(TicketSearchRequest bankSearchRequest) {
       PageDTO<TicketDomain> pageDTO = repo.searchAll(bankSearchRequest);
       TicketSearchResponse bankSearchRespose = new TicketSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  TicketResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public TicketSearchResponse searchFilterAll(TicketSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<TicketDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        TicketSearchResponse bankSearchRespose = new TicketSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  TicketResponse.from(bankDomain)).toList());
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
        TicketDomain ticketDomain = repo.findById(idDocumentFinance);
        if (ticketDomain == null) {
            throw new NotFoundException("No ticket found with id '%s'.".formatted(idDocumentFinance));
        }
        DocumentFinanceDetailResponse result = documentFinanceDetailUseCase.create(request, ticketDomain);

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
        TicketDomain ticketDomain = repo.findById(idDocumentFinance);
        if (ticketDomain == null) {
            throw new NotFoundException("No ticket found with id '%s'.".formatted(idDocumentFinance));
        }
        DocumentFinanceDetailResponse result = documentFinanceDetailUseCase.edit(id, request, ticketDomain);

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
        TicketResponse old = findById(idDocumentFinance);
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
        TicketDomain ticketDomain = repo.findById(idDocumentFinance);
        if (ticketDomain == null) {
            throw new NotFoundException("No ticket found with id '%s'.".formatted(idDocumentFinance));
        }
        return documentFinanceDetailRepo.searchAllByDocumentFinance(idDocumentFinance).stream().map(DocumentFinanceDetailResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentFinanceDetailResponse findByIdAndDocumentFinance(String id, String idDocumentFinance) {
        if (idDocumentFinance == null) {
            return null;
        }
        TicketDomain ticketDomain = repo.findById(idDocumentFinance);
        if (ticketDomain == null) {
            throw new NotFoundException("No ticket found with id '%s'.".formatted(idDocumentFinance));
        }
        return documentFinanceDetailUseCase.searchByDocumentFinanceAndDocumentFinanceDetail(idDocumentFinance, id);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse getTicketModificationData(String idTicket) {
        if (idTicket == null) {
            return null;
        }
        IdBaseDomain ticketIdBaseDomain = repo.findByIdEntity(idTicket);
        if (ticketIdBaseDomain == null){
            throw new NotFoundException("No ticket found with id '%s'.".formatted(idTicket));
        }

        return TicketResponse.from(repo.findByIdTicketCaseGetModificationData(idTicket));
    }

    @Override
    @Transactional
    public byte[] exportTicketPdf(String idTicket) {
        if (idTicket == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        TicketDomain ticketDomain = repo.findById(idTicket);
        if (ticketDomain == null) {
            throw new NotFoundException("No ticket found with id '%s'.".formatted(idTicket));
        }

        List<TicketDomain> list = new ArrayList<TicketDomain>();
        list.add(ticketDomain);

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

        List<DocumentFinanceDetailDomain> documentFinanceDetailDomains = documentFinanceDetailRepo.searchAllByDocumentFinance(idTicket);
        if (documentFinanceDetailDomains != null && !documentFinanceDetailDomains.isEmpty()) {
            documentFinanceDetailDomains = documentFinanceDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", documentFinanceDetailDomains.size());

        byte[] result = new byte[0];
        if (ticketDomain.getCurrency() == null) {
            return result;
        }
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(ticketDomain.getCurrency().getId())){
                result = jrTicketUsdRepository.exportUsd(converter.toRptDocumentAccountUsdList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailUsdList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(ticketDomain.getCurrency().getId())){
                result = jrTicketSolesRepository.exportSoles(converter.toRptDocumentAccountSolesList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailSolesList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(ticketDomain.getCurrency().getId())){
                result = jrTicketUsdRepository.exportUsd(converter.toRptDocumentAccountUsdList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailUsdList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(ticketDomain.getCurrency().getId())){
                result = jrTicketSolesRepository.exportSoles(converter.toRptDocumentAccountSolesList(list), documentFinanceDetailConverter.toRptDocumentAccountDetailSolesList(documentFinanceDetailDomains), ExportFormatEnum.PDF.getName(), parameters);
            }
        }

        return result;
    }
}
