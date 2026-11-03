package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringress.VoucherIngressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherIngress;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress.PersistVoucherIngressNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice.ProformaInvoiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress.VoucherIngressRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringressdetail.VoucherIngressDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringress.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.VoucherIngressDetailUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.voucheringress.JRVoucherIngressRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherIngressDetailUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherIngressUseCase;
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
public class VoucherIngressUseCaseImpl implements VoucherIngressUseCase {

    @Autowired
    private VoucherIngressRepo repo;

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private VoucherIngressDetailRepo voucherIngressDetailRepo;

    @Autowired
    private ProformaInvoiceRepo proformaInvoiceRepo;

    @Autowired
    private VoucherIngressDetailUseCase voucherIngressDetailUseCase;

    @Autowired
    private VoucherIngressConverter converter;

    @Autowired
    private SetFieldDefaultVoucherIngress setFieldDefault;

    @Autowired
    PersistVoucherIngressNumberCallbackImpl persistVoucherIngressNumberCallback;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRVoucherIngressRepository jrVoucherIngressRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public VoucherIngressResponse create(VoucherIngressCreateRequest request) {
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
        }

        VoucherIngressDomain newDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistVoucherIngressNumberCallback.generateNumber());
        newDomain.setVoucherState(VoucherStateEnum.PENDING.getName());
        newDomain.setImportTotalSoles(0.0);
        newDomain.setImportTotalUsd(0.0);
        VoucherIngressDomain createdDomain = repo.create(newDomain);
        return VoucherIngressResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherIngressResponse edit(String id, VoucherIngressUpdateRequest request) {
        if (id == null) {
            return null;
        }
        VoucherIngressDomain old = repo.findById(id);
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
        }
        VoucherIngressDomain voucherIngressDomain = request.to(dateProcessDomain, changeTypeDomain, currencyDomain, employeeDomain);
        voucherIngressDomain.setIdentify(old.getIdentify());
        voucherIngressDomain.setVoucherState(old.getVoucherState());
        voucherIngressDomain.setImportTotalUsd(old.getImportTotalUsd());
        voucherIngressDomain.setImportTotalSoles(old.getImportTotalSoles());
        VoucherIngressDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherIngressDomain, old));
        return VoucherIngressResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        VoucherIngressResponse old = findById(id);
        List<VoucherIngressDetailDomain> detailsByDelete = voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimpleByDelete(id);
        if (detailsByDelete != null && !detailsByDelete.isEmpty()){
            for (VoucherIngressDetailDomain voucherIngressDetailDomain : detailsByDelete){
                voucherIngressDetailRepo.deleteById(voucherIngressDetailDomain.getId());
            }
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherIngressResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> VoucherIngressResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressResponse findById(String id) {
        if (id == null) {
            return null;
        }
        VoucherIngressDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return VoucherIngressResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressSearchResponse searchAll(VoucherIngressSearchRequest voucherIngressSearchRequest) {
       PageDTO<VoucherIngressDomain> pageDTO = repo.searchAll(voucherIngressSearchRequest);
       VoucherIngressSearchResponse voucherIngressSearchResponse = new VoucherIngressSearchResponse();
       voucherIngressSearchResponse.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherIngressResponse.from(bankDomain)).toList());
       voucherIngressSearchResponse.setTotalElements(pageDTO.getTotalElements());
       voucherIngressSearchResponse.setTotalPages(pageDTO.getTotalPages());
       return voucherIngressSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressSearchResponse searchFilterAll(VoucherIngressSearchFilterRequest voucherIngressSearchFilterRequest) {
        PageDTO<VoucherIngressDomain> pageDTO = repo.searchAllFilter(voucherIngressSearchFilterRequest);
        VoucherIngressSearchResponse voucherIngressSearchResponse = new VoucherIngressSearchResponse();
        voucherIngressSearchResponse.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherIngressResponse.from(bankDomain)).toList());
        voucherIngressSearchResponse.setTotalElements(pageDTO.getTotalElements());
        voucherIngressSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return voucherIngressSearchResponse;
    }

    @Override
    @Transactional
    public VoucherIngressDetailResponse addVoucherIngressDetail(VoucherIngressDetailCreateRequest request, String idVoucherIngress) {
        if (idVoucherIngress == null) {
            return null;
        }
        VoucherIngressDomain voucherIngressDomain = repo.findById(idVoucherIngress);
        if (voucherIngressDomain == null) {
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(idVoucherIngress));
        }
        VoucherIngressDetailResponse result = voucherIngressDetailUseCase.create(request, voucherIngressDomain);

        List<VoucherIngressDetailDomain> listVoucherIngressDetail = voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimple(idVoucherIngress);
        Double importTotalSoles = 0.0;
        Double importTotalUsd = 0.0;
        if (listVoucherIngressDetail != null && !listVoucherIngressDetail.isEmpty()){
            for (VoucherIngressDetailDomain voucherIngressDetailDomain: listVoucherIngressDetail){
                importTotalSoles = importTotalSoles + voucherIngressDetailDomain.getImportSoles();
                importTotalUsd = importTotalUsd + voucherIngressDetailDomain.getImportUsd();
            }
        }
        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherIngressBasic(idVoucherIngress, importTotalSoles, importTotalUsd);

        return result;
    }

    @Override
    @Transactional
    public VoucherIngressDetailResponse editVoucherIngressDetail(String id, VoucherIngressDetailUpdateRequest request, String idVoucherIngress) {
        if (idVoucherIngress == null) {
            return null;
        }
        VoucherIngressDomain voucherIngressDomain = repo.findById(idVoucherIngress);
        if (voucherIngressDomain == null) {
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(idVoucherIngress));
        }
        VoucherIngressDetailResponse result = voucherIngressDetailUseCase.edit(id, request, voucherIngressDomain);

        List<VoucherIngressDetailDomain> listVoucherIngressDetail = voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimple(idVoucherIngress);
        Double importTotalSoles = 0.0;
        Double importTotalUsd = 0.0;
        if (listVoucherIngressDetail != null && !listVoucherIngressDetail.isEmpty()){
            for (VoucherIngressDetailDomain voucherIngressDetailDomain: listVoucherIngressDetail){
                importTotalSoles = importTotalSoles + voucherIngressDetailDomain.getImportSoles();
                importTotalUsd = importTotalUsd + voucherIngressDetailDomain.getImportUsd();
            }
        }
        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherIngressBasic(idVoucherIngress, importTotalSoles, importTotalUsd);

        return result;
    }

    @Override
    @Transactional
    public void deleteVoucherIngressDetail(String id, String idVoucherIngress) {
        VoucherIngressResponse old = findById(idVoucherIngress);
        voucherIngressDetailUseCase.deleteById(id);

        List<VoucherIngressDetailDomain> listVoucherIngressDetail = voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimple(idVoucherIngress);
        Double importTotalSoles = 0.0;
        Double importTotalUsd = 0.0;
        if (listVoucherIngressDetail != null && !listVoucherIngressDetail.isEmpty()){
            for (VoucherIngressDetailDomain voucherIngressDetailDomain: listVoucherIngressDetail){
                importTotalSoles = importTotalSoles + voucherIngressDetailDomain.getImportSoles();
                importTotalUsd = importTotalUsd + voucherIngressDetailDomain.getImportUsd();
            }
        }
        repo.updateIndexByIdSimpleDomainCaseUpdateVoucherIngressBasic(idVoucherIngress, importTotalSoles, importTotalUsd);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherIngressDetailResponse> findAllVoucherIngressDetailByVoucherIngress(String idVoucherIngress) {
        if (idVoucherIngress == null) {
            return null;
        }
        VoucherIngressDomain voucherIngressDomain = repo.findById(idVoucherIngress);
        if (voucherIngressDomain == null) {
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(idVoucherIngress));
        }
        return voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimple(idVoucherIngress).stream().map(voucherIngressDetailDomain -> VoucherIngressDetailResponse.from(voucherIngressDetailDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressDetailResponse findByIdAndVoucherIngress(String id, String idVoucherIngress) {
        if (idVoucherIngress == null) {
            return null;
        }
        VoucherIngressDomain voucherIngressDomain = repo.findById(idVoucherIngress);
        if (voucherIngressDomain == null) {
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(idVoucherIngress));
        }
        return voucherIngressDetailUseCase.searchByVoucherIngressAndVoucherIngressDetail(idVoucherIngress, id);
    }

    @Override
    @Transactional
    public VoucherIngressResponse changeState(String id, VoucherIngressChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        VoucherIngressDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(id));
        }
        old.setVoucherState(request.getVoucherState());
        VoucherIngressDomain editedDomain = repo.edit(old);
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
        return VoucherIngressResponse.from(editedDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressResponse getVoucherIngressModificationData(String idVoucherIngress) {
        if (idVoucherIngress == null) {
            return null;
        }
        IdBaseDomain voucherIngressIdBaseDomain = repo.findByIdEntity(idVoucherIngress);
        if (voucherIngressIdBaseDomain == null){
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(idVoucherIngress));
        }

        return VoucherIngressResponse.from(repo.findByIdVoucherIngressCaseGetModificationData(idVoucherIngress));
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressVerificationImportResponse verificationImport(String idRegisterGroup) {
        Double cancelImportTotalUsd = 0.00;
        Double cancelImportTotalSoles = 0.00;

        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()) {
            List<VoucherIngressDetailDomain> listVoucherIngressDetail = voucherIngressDetailRepo.searchAllByVoucherIngressCaseListSimpleByRegisterGroup(idRegisterGroup);
            if (listVoucherIngressDetail != null && !listVoucherIngressDetail.isEmpty()) {
                for (VoucherIngressDetailDomain voucherIngressDetail : listVoucherIngressDetail) {
                    if (voucherIngressDetail.getImportUsd() != null) {
                        cancelImportTotalUsd += voucherIngressDetail.getImportUsd();
                    }
                    if (voucherIngressDetail.getImportSoles() != null) {
                        cancelImportTotalSoles += voucherIngressDetail.getImportSoles();
                    }
                }
            }
        }

        VoucherIngressVerificationImportResponse response = new VoucherIngressVerificationImportResponse();
        response.setCancelImportTotalUsd(cancelImportTotalUsd);
        response.setCancelImportTotalSoles(cancelImportTotalSoles);

        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()) {
            List<ProformaInvoiceDomain> listProformaInvoice = proformaInvoiceRepo.searchAllByRegisterGroup(idRegisterGroup);
            if (listProformaInvoice != null && !listProformaInvoice.isEmpty()) {
                ProformaInvoiceDomain proformaInvoice = listProformaInvoice.get(0);
                response.setProformImportTotalUsd(proformaInvoice.getImportTotalUsd());
                response.setProformImportTotalSoles(proformaInvoice.getImportTotalSoles());

                if (proformaInvoice.getImportTotalUsd() != null) {
                    response.setPendientImportTotalUsd(proformaInvoice.getImportTotalUsd() - cancelImportTotalUsd);
                }
                if (proformaInvoice.getImportTotalSoles() != null) {
                    response.setPendientImportTotalSoles(proformaInvoice.getImportTotalSoles() - cancelImportTotalSoles);
                }
            }
        }

        return response;
    }

    @Override
    @Transactional
    public byte[] exportVoucherIngressPdf(String idVoucherIngress) {
        if (idVoucherIngress == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        VoucherIngressDomain voucherIngressDomain = repo.findById(idVoucherIngress);
        if (voucherIngressDomain == null) {
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(idVoucherIngress));
        }

        List<VoucherIngressDomain> list = new ArrayList<VoucherIngressDomain>();
        list.add(voucherIngressDomain);

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

        List<VoucherIngressDetailDomain> voucherIngressDetailDomains = voucherIngressDetailRepo.searchAllByVoucherIngress(idVoucherIngress);
        if (voucherIngressDetailDomains != null && !voucherIngressDetailDomains.isEmpty()) {
            voucherIngressDetailDomains = voucherIngressDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", voucherIngressDetailDomains.size());

        return jrVoucherIngressRepository.export(list, voucherIngressDetailDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportVoucherIngressExcel(String idVoucherIngress) {
        if (idVoucherIngress == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        VoucherIngressDomain voucherIngressDomain = repo.findById(idVoucherIngress);
        if (voucherIngressDomain == null) {
            throw new NotFoundException("No voucher ingress found with id '%s'.".formatted(idVoucherIngress));
        }

        List<VoucherIngressDomain> list = new ArrayList<VoucherIngressDomain>();
        list.add(voucherIngressDomain);

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

        List<VoucherIngressDetailDomain> voucherIngressDetailDomains = voucherIngressDetailRepo.searchAllByVoucherIngress(idVoucherIngress);
        if (voucherIngressDetailDomains != null && !voucherIngressDetailDomains.isEmpty()) {
            voucherIngressDetailDomains = voucherIngressDetailDomains.stream().collect(Collectors.toList());
        }

        parameters.put("cantRegister", voucherIngressDetailDomains.size());

        return jrVoucherIngressRepository.export(list, voucherIngressDetailDomains, ExportFormatEnum.XLSX.getName(), parameters);
    }

    private void _changeStatePending(VoucherIngressDomain voucherIngressDomain, VoucherIngressChangeStateRequest request){

    }

    private void _changeStateCancelled(VoucherIngressDomain voucherIngressDomain, VoucherIngressChangeStateRequest request){

    }

    private void _changeStateAnnulled(VoucherIngressDomain voucherIngressDomain, VoucherIngressChangeStateRequest request){

    }
}
