package com.saas.tourismagency.admin.backoffice.usecase.analytics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.AnalyticsFinanceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.proforma.ProformaResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.account.JRReportDocumentFinanceRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.account.JRReportProformaFinanceRepository;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.DocumentAccountTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsFinanceUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AnalyticsFinanceUseCaseImpl implements AnalyticsFinanceUseCase {

    @Autowired
    private AnalyticsFinanceRepo analyticsFinanceRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRReportProformaFinanceRepository jrReportProformaFinanceRepository;

    @Autowired
    private JRReportDocumentFinanceRepository jrReportDocumentFinanceRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    private Map<String, Object> buildBaseParameters(AnalyticsFinanceSearchReportFilterRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("userActive", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        Map<String, String> filter = request != null && request.getFilter() != null ? request.getFilter() : Map.of();
        Map<String, String> filterReport = request != null && request.getFilterReport() != null ? request.getFilterReport() : Map.of();

        parameters.put("date_init", LocalDateTimeUtil.toShortDateFromFilter(filterReport.getOrDefault("date_init", filter.get("dateStart"))));
        parameters.put("date_end", LocalDateTimeUtil.toShortDateFromFilter(filterReport.getOrDefault("date_end", filter.get("dateEnd"))));
        parameters.put("states", filterReport.getOrDefault("states", filter.get("state")));
        parameters.put("currencys", filterReport.getOrDefault("currencys", filter.get("currency")));
        parameters.put("types", filterReport.getOrDefault("types", filter.get("documentType")));
        parameters.put("type_dates", filterReport.getOrDefault("type_dates", filter.get("typeDate")));
        parameters.put("proform_types", filterReport.getOrDefault("proform_types", filter.get("proformaType")));

        Map<String, String> docTypeAbbrevs = new HashMap<>();
        for (DocumentAccountTypeEnum typeEnum : DocumentAccountTypeEnum.values()) {
            docTypeAbbrevs.put(typeEnum.getName(), typeEnum.getAbbrev());
        }
        parameters.put("docTypeAbbrevs", docTypeAbbrevs);

        Map<String, String> proformaStateLabels = new HashMap<>();
        for (ProformaStateEnum stateEnum : ProformaStateEnum.values()) {
            proformaStateLabels.put(stateEnum.getName(), stateEnum.getDescription());
        }
        parameters.put("proformaStateLabels", proformaStateLabels);

        Map<String, String> proformaTypeLabels = new HashMap<>();
        for (ProformaTypeEnum typeEnum : ProformaTypeEnum.values()) {
            proformaTypeLabels.put(typeEnum.getName(), typeEnum.getDescription());
        }
        parameters.put("proformaTypeLabels", proformaTypeLabels);

        Map<String, String> proformaTypeAbbrevs = new HashMap<>();
        proformaTypeAbbrevs.put(ProformaTypeEnum.INVOICE.getName(), DocumentAccountTypeEnum.BILL.getAbbrev());
        proformaTypeAbbrevs.put(ProformaTypeEnum.CREDIT_NOTE.getName(), DocumentAccountTypeEnum.CREDIT_NOTE.getAbbrev());
        parameters.put("proformaTypeAbbrevs", proformaTypeAbbrevs);

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                log.error("Logo not found", e);
            } catch (IOException e) {
                log.error("Error loading logo", e);
            }
        } else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                log.error("Logo not found", e);
            } catch (IOException e) {
                log.error("Error loading logo", e);
            }
        }

        return parameters;
    }

    // ---- ISSUE DATE: PROFORMA INVOICE ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateProformInvoice(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<ProformaDomain> pageDTO = analyticsFinanceRepo.searchPageableProformInvoiceByIssueDate(request);
        AnalyticsFinanceSearchResponse<ProformaResponse> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ProformaResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- ISSUE DATE: PROFORMA NOTE CRED ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateProformNoteCred(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<ProformaDomain> pageDTO = analyticsFinanceRepo.searchPageableProformNoteCredByIssueDate(request);
        AnalyticsFinanceSearchResponse<ProformaResponse> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ProformaResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- ISSUE DATE: INVOICE ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateInvoice(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableInvoiceByIssueDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- ISSUE DATE: BOLETA ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateBoleta(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableBoletaByIssueDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- ISSUE DATE: NOTE DEB ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateNoteDeb(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableNoteDebByIssueDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- ISSUE DATE: NOTE CRED ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateNoteCred(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableNoteCredByIssueDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- ISSUE DATE: DOCUMENT COBRANZA ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateIssueDateDocumentCobranza(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableDocumentCobranzaByIssueDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- CANCELATION DATE: PROFORMA INVOICE ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateProformInvoice(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<ProformaDomain> pageDTO = analyticsFinanceRepo.searchPageableProformInvoiceByCancelationDate(request);
        AnalyticsFinanceSearchResponse<ProformaResponse> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ProformaResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- CANCELATION DATE: PROFORMA NOTE CRED ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateProformNoteCred(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<ProformaDomain> pageDTO = analyticsFinanceRepo.searchPageableProformNoteCredByCancelationDate(request);
        AnalyticsFinanceSearchResponse<ProformaResponse> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ProformaResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- CANCELATION DATE: INVOICE ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateInvoice(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableInvoiceByCancelationDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- CANCELATION DATE: BOLETA ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateBoleta(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableBoletaByCancelationDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- CANCELATION DATE: NOTE DEB ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateNoteDeb(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableNoteDebByCancelationDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- CANCELATION DATE: NOTE CRED ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateNoteCred(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableNoteCredByCancelationDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- CANCELATION DATE: DOCUMENT COBRANZA ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateCancelationDateDocumentCobranza(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableDocumentCobranzaByCancelationDate(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- REGISTER SALE ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateRegisterSale(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableRegisterSale(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- REGISTER SALE OPERATIVE ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateRegisterSaleOperative(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableRegisterSaleOperative(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- REGISTER SALE OPERATIVE PASSENGER ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateRegisterSaleOperativePassenger(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<DocumentFinanceDomain> pageDTO = analyticsFinanceRepo.searchPageableRegisterSaleOperativePassenger(request);
        AnalyticsFinanceSearchResponse<Map<String, Object>> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(this::toDocumentFinanceMap).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- STATE ACCOUNT CUSTOMER ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateStateAccountCustomer(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<ProformaDomain> pageDTO = analyticsFinanceRepo.searchPageableStateAccountCustomer(request);
        AnalyticsFinanceSearchResponse<ProformaResponse> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ProformaResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- GROUP PENDING INVOICE ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateGroupPendingInvoice(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<ProformaDomain> pageDTO = analyticsFinanceRepo.searchPageableGroupPendingInvoice(request);
        AnalyticsFinanceSearchResponse<ProformaResponse> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ProformaResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- PROFORM CTRL ----

    @Override
    @Transactional(readOnly = true)
    public AnalyticsFinanceSearchResponse<?> searchFilterPaginateProformCtrl(AnalyticsFinanceSearchFilterRequest request) {
        PageDTO<ProformaDomain> pageDTO = analyticsFinanceRepo.searchPageableProformCtrl(request);
        AnalyticsFinanceSearchResponse<ProformaResponse> response = new AnalyticsFinanceSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ProformaResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ---- EXPORT: ISSUE DATE ----

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformInvoiceByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformInvoiceByIssueDate(request);
        return jrReportProformaFinanceRepository.exportProformInvoiceByIssueDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformInvoiceByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformInvoiceByIssueDate(request);
        return jrReportProformaFinanceRepository.exportProformInvoiceByIssueDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformNoteCredByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformNoteCredByIssueDate(request);
        return jrReportProformaFinanceRepository.exportProformNoteCredByIssueDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformNoteCredByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformNoteCredByIssueDate(request);
        return jrReportProformaFinanceRepository.exportProformNoteCredByIssueDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportInvoiceByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllInvoiceByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportInvoiceByIssueDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportInvoiceByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllInvoiceByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportInvoiceByIssueDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportBoletaByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllBoletaByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportBoletaByIssueDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportBoletaByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllBoletaByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportBoletaByIssueDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteDebByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteDebByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportNoteDebByIssueDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteDebByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteDebByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportNoteDebByIssueDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteCredByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteCredByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportNoteCredByIssueDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteCredByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteCredByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportNoteCredByIssueDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportDocumentCobranzaByIssueDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllDocumentCobranzaByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportDocumentCobranzaByIssueDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportDocumentCobranzaByIssueDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllDocumentCobranzaByIssueDate(request);
        return jrReportDocumentFinanceRepository.exportDocumentCobranzaByIssueDateExcel(list, buildBaseParameters(request));
    }

    // ---- EXPORT: CANCELATION DATE ----

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformInvoiceByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformInvoiceByCancelationDate(request);
        return jrReportProformaFinanceRepository.exportProformInvoiceByCancelationDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformInvoiceByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformInvoiceByCancelationDate(request);
        return jrReportProformaFinanceRepository.exportProformInvoiceByCancelationDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformNoteCredByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformNoteCredByCancelationDate(request);
        return jrReportProformaFinanceRepository.exportProformNoteCredByCancelationDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformNoteCredByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformNoteCredByCancelationDate(request);
        return jrReportProformaFinanceRepository.exportProformNoteCredByCancelationDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportInvoiceByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllInvoiceByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportInvoiceByCancelationDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportInvoiceByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllInvoiceByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportInvoiceByCancelationDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportBoletaByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllBoletaByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportBoletaByCancelationDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportBoletaByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllBoletaByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportBoletaByCancelationDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteDebByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteDebByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportNoteDebByCancelationDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteDebByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteDebByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportNoteDebByCancelationDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteCredByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteCredByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportNoteCredByCancelationDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportNoteCredByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllNoteCredByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportNoteCredByCancelationDateExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportDocumentCobranzaByCancelationDatePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllDocumentCobranzaByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportDocumentCobranzaByCancelationDatePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportDocumentCobranzaByCancelationDateExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllDocumentCobranzaByCancelationDate(request);
        return jrReportDocumentFinanceRepository.exportDocumentCobranzaByCancelationDateExcel(list, buildBaseParameters(request));
    }

    // ---- EXPORT: PHASE 3 REPORTS ----

    @Override
    @Transactional(readOnly = true)
    public byte[] exportRegisterSalePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllRegisterSale(request);
        return jrReportDocumentFinanceRepository.exportRegisterSalePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportRegisterSaleExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllRegisterSale(request);
        return jrReportDocumentFinanceRepository.exportRegisterSaleExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportRegisterSaleOperativePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllRegisterSaleOperative(request);
        return jrReportDocumentFinanceRepository.exportRegisterSaleOperativePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportRegisterSaleOperativeExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllRegisterSaleOperative(request);
        return jrReportDocumentFinanceRepository.exportRegisterSaleOperativeExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportRegisterSaleOperativePassengerPdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllRegisterSaleOperativePassenger(request);
        return jrReportDocumentFinanceRepository.exportRegisterSaleOperativePassengerPdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportRegisterSaleOperativePassengerExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<DocumentFinanceDomain> list = analyticsFinanceRepo.searchAllRegisterSaleOperativePassenger(request);
        return jrReportDocumentFinanceRepository.exportRegisterSaleOperativePassengerExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportStateAccountCustomerPdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllStateAccountCustomer(request);
        return jrReportProformaFinanceRepository.exportStateAccountCustomerPdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportStateAccountCustomerExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllStateAccountCustomer(request);
        return jrReportProformaFinanceRepository.exportStateAccountCustomerExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportGroupPendingInvoicePdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllGroupPendingInvoice(request);
        return jrReportProformaFinanceRepository.exportGroupPendingInvoicePdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportGroupPendingInvoiceExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllGroupPendingInvoice(request);
        return jrReportProformaFinanceRepository.exportGroupPendingInvoiceExcel(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformCtrlPdf(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformCtrl(request);
        return jrReportProformaFinanceRepository.exportProformCtrlPdf(list, buildBaseParameters(request));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportProformCtrlExcel(AnalyticsFinanceSearchReportFilterRequest request) {
        List<ProformaDomain> list = analyticsFinanceRepo.searchAllProformCtrl(request);
        return jrReportProformaFinanceRepository.exportProformCtrlExcel(list, buildBaseParameters(request));
    }

    // ---- HELPER ----

    private Map<String, Object> toDocumentFinanceMap(DocumentFinanceDomain domain) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", domain.getId());
        map.put("name", domain.getName());
        map.put("description", domain.getDescription());
        map.put("identify", domain.getIdentify());
        map.put("identifyPrefix", domain.getIdentifyPrefix());
        map.put("identifyConsecutive", domain.getIdentifyConsecutive());
        map.put("typeInheritanceDocumentFinance", domain.getTypeInheritanceDocumentFinance());
        map.put("date", domain.getDate());
        map.put("dateCancel", domain.getDateCancel());
        map.put("dateAnnulled", domain.getDateAnnulled());
        map.put("address", domain.getAddress());
        map.put("referenceDocument", domain.getReferenceDocument());
        map.put("dateInit", domain.getDateInit());
        map.put("dateEnd", domain.getDateEnd());
        map.put("noPax", domain.getNoPax());
        map.put("subTotalPrice", domain.getSubTotalPrice());
        map.put("importSubTotalUsd", domain.getImportSubTotalUsd());
        map.put("importSubTotalSoles", domain.getImportSubTotalSoles());
        map.put("importIgvUsd", domain.getImportIgvUsd());
        map.put("importIgvSoles", domain.getImportIgvSoles());
        map.put("importTotalUsd", domain.getImportTotalUsd());
        map.put("importTotalSoles", domain.getImportTotalSoles());
        map.put("importTotalUsdFormat", domain.getImportTotalUsdFormat());
        map.put("importTotalSolesFormat", domain.getImportTotalSolesFormat());
        map.put("documentAccountType", domain.getDocumentAccountType());
        map.put("documentAccountState", domain.getDocumentAccountState());
        if (domain.getRegisterGroup() != null) {
            Map<String, Object> registerGroupMap = new LinkedHashMap<>();
            registerGroupMap.put("id", domain.getRegisterGroup().getId());
            registerGroupMap.put("name", domain.getRegisterGroup().getName());
            map.put("registerGroup", registerGroupMap);
        }
        if (domain.getCustomer() != null) {
            Map<String, Object> customerMap = new LinkedHashMap<>();
            customerMap.put("id", domain.getCustomer().getId());
            customerMap.put("name", domain.getCustomer().getName());
            map.put("customer", customerMap);
        }
        if (domain.getCurrency() != null) {
            Map<String, Object> currencyMap = new LinkedHashMap<>();
            currencyMap.put("id", domain.getCurrency().getId());
            currencyMap.put("name", domain.getCurrency().getName());
            map.put("currency", currencyMap);
        }
        if (domain.getChangeType() != null) {
            Map<String, Object> changeTypeMap = new LinkedHashMap<>();
            changeTypeMap.put("id", domain.getChangeType().getId());
            changeTypeMap.put("priceSale", domain.getChangeType().getPriceSale());
            map.put("changeType", changeTypeMap);
        }
        return map;
    }
}
