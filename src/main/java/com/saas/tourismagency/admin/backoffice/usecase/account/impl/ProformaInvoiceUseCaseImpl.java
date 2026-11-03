package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformainvoice.ProformaInvoiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultProformaInvoice;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformadetail.ProformaDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice.PersistProfInvoiceNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice.ProformaInvoiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress.PersistVoucherIngressNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.proforminvoice.JRProformInvoiceRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformadetail.ProformaDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proformainvoice.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.*;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.ProformaInvoiceUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
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
public class ProformaInvoiceUseCaseImpl implements ProformaInvoiceUseCase {

    @Autowired
    private ProformaInvoiceRepo repo;

    @Autowired
    private ProformaInvoiceConverter converter;

    @Autowired
    private SetFieldDefaultProformaInvoice setFieldDefault;

    @Autowired
    private ProformaDetailUseCase proformaDetailUseCase;

    @Autowired
    private ProformaDetailRepo proformaDetailRepo;

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    PersistProfInvoiceNumberCallbackImpl persistProfInvoiceNumberCallback;

    @Autowired
    private JRProformInvoiceRepository jrProformInvoiceRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Override
    @Transactional
    public ProformaInvoiceResponse create(ProformaInvoiceCreateRequest request) {
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

        EmployeeDomain employeeDomain = null;
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getEmployee());
            employeeDomain = new EmployeeDomain();
            employeeDomain.setId(employeeIdBaseDomain.getId());
        }

        ProformaInvoiceDomain newDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                registerGroupDomain,
                employeeDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistProfInvoiceNumberCallback.generateNumber(LocalDate.now().getYear()));

        if (newDomain.getDateCancel() != null) {
            newDomain.setProformaState(ProformaStateEnum.CANCELLED.getName());
        } else {
            newDomain.setProformaState(ProformaStateEnum.PENDING.getName());
        }
        newDomain.setProformaType(ProformaTypeEnum.INVOICE.getName());

        newDomain.setImportTotalSoles(0.0);
        newDomain.setImportTotalUsd(0.0);
        newDomain.setImportAmortizationUsd(0.0);
        newDomain.setImportAmortizationSoles(0.0);
        newDomain.setImportSaldxAmortizarUsd(0.0);
        newDomain.setImportSaldxAmortizarSoles(0.0);
        newDomain.setPriceUnit(0.0);

        newDomain.setTypeInheritanceProforma(TypeInheritanceProformaEnum.PROFORMA_INVOICE.getName());

        ProformaInvoiceDomain createdDomain = repo.create(newDomain);
        return ProformaInvoiceResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProformaInvoiceResponse edit(String id, ProformaInvoiceUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProformaInvoiceDomain old = repo.findById(id);
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

        EmployeeDomain employeeDomain = null;
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getEmployee());
            employeeDomain = new EmployeeDomain();
            employeeDomain.setId(employeeIdBaseDomain.getId());
        }

ProformaInvoiceDomain proformaDomain = request.to(
                dateProcessDomain,
                countryDomain,
                currencyDomain,
                changeTypeDomain,
                customerDomain,
                registerGroupDomain,
                employeeDomain
        );

        if (proformaDomain.getCurrency() == null && request.getCurrency() == null) {
            proformaDomain.setCurrency(old.getCurrency());
        }
        if (proformaDomain.getCountry() == null && request.getCountry() == null) {
            proformaDomain.setCountry(old.getCountry());
        }
        if (proformaDomain.getEmployee() == null && request.getEmployee() == null) {
            proformaDomain.setEmployee(old.getEmployee());
        }

        proformaDomain.setIdentify(old.getIdentify());
        proformaDomain.setTypeInheritanceProforma(old.getTypeInheritanceProforma());
        if (ProformaStateEnum.ANNULLED.getName().equals(old.getProformaState())) {
            proformaDomain.setProformaState(old.getProformaState());
        } else if (proformaDomain.getDateCancel() != null) {
            proformaDomain.setProformaState(ProformaStateEnum.CANCELLED.getName());
        } else {
            proformaDomain.setProformaState(ProformaStateEnum.PENDING.getName());
        }
        proformaDomain.setProformaType(old.getProformaType());

        proformaDomain.setImportTotalSoles(old.getImportTotalSoles());
        proformaDomain.setImportTotalUsd(old.getImportTotalUsd());
        proformaDomain.setImportAmortizationUsd(old.getImportAmortizationUsd());
        proformaDomain.setImportAmortizationSoles(old.getImportAmortizationSoles());
        proformaDomain.setImportSaldxAmortizarUsd(old.getImportSaldxAmortizarUsd());
        proformaDomain.setImportSaldxAmortizarSoles(old.getImportSaldxAmortizarSoles());
        proformaDomain.setPriceUnit(old.getPriceUnit());

        ProformaInvoiceDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(proformaDomain, old));
        return ProformaInvoiceResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProformaInvoiceResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaInvoiceResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> ProformaInvoiceResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaInvoiceResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProformaInvoiceDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ProformaInvoiceResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaInvoiceSearchResponse searchAll(ProformaInvoiceSearchRequest bankSearchRequest) {
       PageDTO<ProformaInvoiceDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ProformaInvoiceSearchResponse bankSearchRespose = new ProformaInvoiceSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ProformaInvoiceResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaInvoiceSearchResponse searchFilterAll(ProformaInvoiceSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ProformaInvoiceDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ProformaInvoiceSearchResponse bankSearchRespose = new ProformaInvoiceSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ProformaInvoiceResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional
    public ProformaDetailResponse addProformaDetail(ProformaDetailCreateRequest request, String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaInvoiceDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma invoice found with id '%s'.".formatted(idProforma));
        }
        ProformaDetailResponse result = proformaDetailUseCase.create(request, proformaDomain);
        proformaDetailUseCase.recalculateProformaTotals(idProforma);
        return result;
    }

    @Override
    @Transactional
    public ProformaDetailResponse editProformaDetail(String id, ProformaDetailUpdateRequest request, String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaInvoiceDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma invoice found with id '%s'.".formatted(idProforma));
        }
        ProformaDetailResponse result = proformaDetailUseCase.edit(id, request, proformaDomain);
        proformaDetailUseCase.recalculateProformaTotals(idProforma);
        return result;
    }

    @Override
    @Transactional
    public void deleteProformaDetail(String id, String idProforma) {
        ProformaInvoiceResponse old = findById(idProforma);
        proformaDetailUseCase.deleteById(id);
        proformaDetailUseCase.recalculateProformaTotals(idProforma);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaDetailResponse> findAllProformaDetailByProforma(String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaInvoiceDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma invoice found with id '%s'.".formatted(idProforma));
        }
        return proformaDetailRepo.searchAllByProforma(idProforma).stream().map(ProformaDetailResponse::from).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaDetailResponse findByIdAndProforma(String id, String idProforma) {
        if (idProforma == null) {
            return null;
        }
        ProformaInvoiceDomain proformaDomain = repo.findById(idProforma);
        if (proformaDomain == null) {
            throw new NotFoundException("No proforma invoice found with id '%s'.".formatted(idProforma));
        }
        return proformaDetailUseCase.searchByProformaAndProformaDetail(idProforma, id);
    }

    @Override
    @Transactional
    public byte[] exportProformaInvoicePdf(String idProformaInvoice) {
        if (idProformaInvoice == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByUser(userDomain.getId());
        if (employeeUserDomain != null){
            parameters.put("activeEmployee", employeeUserDomain.getEmployee());
        }else{
            throw new BadRequestException(MessagesValidation.VALIDATION_EMPLOYEE_USER_NOT_ASSOCIATE);
        }

        ProformaInvoiceDomain proformaInvoiceDomain = repo.findById(idProformaInvoice);
        if (proformaInvoiceDomain == null) {
            throw new NotFoundException("No proforma invoice found with id '%s'.".formatted(idProformaInvoice));
        }

        List<ProformaInvoiceDomain> list = new ArrayList<ProformaInvoiceDomain>();
        list.add(proformaInvoiceDomain);

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

        List<ProformaDetailDomain> proformaDetailDomains = proformaDetailRepo.searchAllByProforma(idProformaInvoice);
        if (proformaDetailDomains != null && !proformaDetailDomains.isEmpty()) {
            proformaDetailDomains = proformaDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", proformaDetailDomains.size());

        byte[] result = new byte[0];
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(proformaInvoiceDomain.getCurrency().getId())){
                result = jrProformInvoiceRepository.exportUsd(list, proformaDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(proformaInvoiceDomain.getCurrency().getId())){
                result = jrProformInvoiceRepository.exportSoles(list, proformaDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            if (companyDomain.getSetting().getCurrencyUsdDefault().getId().equals(proformaInvoiceDomain.getCurrency().getId())){
                result = jrProformInvoiceRepository.exportUsd(list, proformaDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }else if (companyDomain.getSetting().getCurrencySolesDefault().getId().equals(proformaInvoiceDomain.getCurrency().getId())){
                result = jrProformInvoiceRepository.exportSoles(list, proformaDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
            }
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProformaInvoiceResponse getProformaInvoiceModificationData(String idProformaInvoice) {
        if (idProformaInvoice == null) {
            return null;
        }
        IdBaseDomain proformaIdBaseDomain = repo.findByIdEntity(idProformaInvoice);
        if (proformaIdBaseDomain == null) {
            throw new NotFoundException("No proforma invoice found with id '%s'.".formatted(idProformaInvoice));
        }
        ProformaInvoiceDomain proformaInvoiceDomain = repo.findById(idProformaInvoice);
        return ProformaInvoiceResponse.from(proformaInvoiceDomain);
    }
}
