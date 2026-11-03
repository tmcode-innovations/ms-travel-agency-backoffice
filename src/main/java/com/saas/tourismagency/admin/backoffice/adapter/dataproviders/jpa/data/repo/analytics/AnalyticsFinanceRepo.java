package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.documentfinance.DocumentFinanceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proforma.ProformaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.account.AnalyticsFinanceSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ProformaTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.DocumentFinanceDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsFinanceRepo {

    @Autowired
    private IAnalyticsFinanceRepoTemplate repositoryTemplate;

    @Autowired
    private ProformaConverter proformaConverter;

    @Autowired
    private DocumentFinanceConverter documentFinanceConverter;

    // ---- PROFORMA INVOICE ----

    public PageDTO<ProformaDomain> searchPageableProformInvoiceByIssueDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<ProformaEntity> result = repositoryTemplate.aggregatePageableProformaByTypeAndDate(searchFilterDomain, pageConfig, ProformaTypeEnum.INVOICE.getName(), false);
        return toProformaDomainPage(result);
    }

    public PageDTO<ProformaDomain> searchPageableProformInvoiceByCancelationDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<ProformaEntity> result = repositoryTemplate.aggregatePageableProformaByTypeAndDate(searchFilterDomain, pageConfig, ProformaTypeEnum.INVOICE.getName(), true);
        return toProformaDomainPage(result);
    }

    // ---- PROFORMA NOTE CRED ----

    public PageDTO<ProformaDomain> searchPageableProformNoteCredByIssueDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<ProformaEntity> result = repositoryTemplate.aggregatePageableProformaByTypeAndDate(searchFilterDomain, pageConfig, ProformaTypeEnum.CREDIT_NOTE.getName(), false);
        return toProformaDomainPage(result);
    }

    public PageDTO<ProformaDomain> searchPageableProformNoteCredByCancelationDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<ProformaEntity> result = repositoryTemplate.aggregatePageableProformaByTypeAndDate(searchFilterDomain, pageConfig, ProformaTypeEnum.CREDIT_NOTE.getName(), true);
        return toProformaDomainPage(result);
    }

    // ---- DOCUMENT FINANCE: INVOICE ----

    public PageDTO<DocumentFinanceDomain> searchPageableInvoiceByIssueDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceInvoiceEntityData", false);
        return toDocumentFinanceDomainPage(result);
    }

    public PageDTO<DocumentFinanceDomain> searchPageableInvoiceByCancelationDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceInvoiceEntityData", true);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- DOCUMENT FINANCE: BOLETA ----

    public PageDTO<DocumentFinanceDomain> searchPageableBoletaByIssueDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceTicketEntityData", false);
        return toDocumentFinanceDomainPage(result);
    }

    public PageDTO<DocumentFinanceDomain> searchPageableBoletaByCancelationDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceTicketEntityData", true);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- DOCUMENT FINANCE: NOTE DEB ----

    public PageDTO<DocumentFinanceDomain> searchPageableNoteDebByIssueDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceDebitNoteEntityData", false);
        return toDocumentFinanceDomainPage(result);
    }

    public PageDTO<DocumentFinanceDomain> searchPageableNoteDebByCancelationDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceDebitNoteEntityData", true);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- DOCUMENT FINANCE: NOTE CRED ----

    public PageDTO<DocumentFinanceDomain> searchPageableNoteCredByIssueDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceCreditNoteEntityData", false);
        return toDocumentFinanceDomainPage(result);
    }

    public PageDTO<DocumentFinanceDomain> searchPageableNoteCredByCancelationDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceCreditNoteEntityData", true);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- DOCUMENT FINANCE: DOCUMENT COBRANZA ----

    public PageDTO<DocumentFinanceDomain> searchPageableDocumentCobranzaByIssueDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceCollectionEntityData", false);
        return toDocumentFinanceDomainPage(result);
    }

    public PageDTO<DocumentFinanceDomain> searchPageableDocumentCobranzaByCancelationDate(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypeAndDate(searchFilterDomain, pageConfig, "DocumentFinanceCollectionEntityData", true);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- REGISTER SALE ----

    public PageDTO<DocumentFinanceDomain> searchPageableRegisterSale(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        List<String> types = List.of("DocumentFinanceInvoiceEntityData", "DocumentFinanceTicketEntityData");
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableDocumentFinanceByTypesAndDate(searchFilterDomain, pageConfig, types, false);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- REGISTER SALE OPERATIVE ----

    public PageDTO<DocumentFinanceDomain> searchPageableRegisterSaleOperative(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableRegisterSaleOperative(searchFilterDomain, pageConfig);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- REGISTER SALE OPERATIVE PASSENGER ----

    public PageDTO<DocumentFinanceDomain> searchPageableRegisterSaleOperativePassenger(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<DocumentFinanceEntity> result = repositoryTemplate.aggregatePageableRegisterSaleOperativePassenger(searchFilterDomain, pageConfig);
        return toDocumentFinanceDomainPage(result);
    }

    // ---- STATE ACCOUNT CUSTOMER ----

    public PageDTO<ProformaDomain> searchPageableStateAccountCustomer(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<ProformaEntity> result = repositoryTemplate.aggregatePageableProformaByFilters(searchFilterDomain, pageConfig);
        return toProformaDomainPage(result);
    }

    // ---- GROUP PENDING INVOICE ----

    public PageDTO<ProformaDomain> searchPageableGroupPendingInvoice(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<ProformaEntity> result = repositoryTemplate.aggregatePageableGroupPendingInvoice(searchFilterDomain, pageConfig);
        return toProformaDomainPage(result);
    }

    // ---- PROFORM CTRL ----

    public PageDTO<ProformaDomain> searchPageableProformCtrl(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );
        Page<ProformaEntity> result = repositoryTemplate.aggregatePageableProformCtrl(searchFilterDomain, pageConfig);
        return toProformaDomainPage(result);
    }

    // ---- SEARCH ALL FOR EXPORTS ----

    public List<ProformaDomain> searchAllProformInvoiceByIssueDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ProformaDomain> result = searchPageableProformInvoiceByIssueDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ProformaDomain> searchAllProformInvoiceByCancelationDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ProformaDomain> result = searchPageableProformInvoiceByCancelationDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ProformaDomain> searchAllProformNoteCredByIssueDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ProformaDomain> result = searchPageableProformNoteCredByIssueDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ProformaDomain> searchAllProformNoteCredByCancelationDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ProformaDomain> result = searchPageableProformNoteCredByCancelationDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllInvoiceByIssueDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableInvoiceByIssueDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllInvoiceByCancelationDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableInvoiceByCancelationDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllBoletaByIssueDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableBoletaByIssueDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllBoletaByCancelationDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableBoletaByCancelationDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllNoteDebByIssueDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableNoteDebByIssueDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllNoteDebByCancelationDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableNoteDebByCancelationDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllNoteCredByIssueDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableNoteCredByIssueDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllNoteCredByCancelationDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableNoteCredByCancelationDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllDocumentCobranzaByIssueDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableDocumentCobranzaByIssueDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllDocumentCobranzaByCancelationDate(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableDocumentCobranzaByCancelationDate(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllRegisterSale(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableRegisterSale(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllRegisterSaleOperative(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableRegisterSaleOperative(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<DocumentFinanceDomain> searchAllRegisterSaleOperativePassenger(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<DocumentFinanceDomain> result = searchPageableRegisterSaleOperativePassenger(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ProformaDomain> searchAllStateAccountCustomer(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ProformaDomain> result = searchPageableStateAccountCustomer(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ProformaDomain> searchAllGroupPendingInvoice(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ProformaDomain> result = searchPageableGroupPendingInvoice(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    public List<ProformaDomain> searchAllProformCtrl(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = toFilterRequest(request);
        PageDTO<ProformaDomain> result = searchPageableProformCtrl(filterRequest);
        return result.getContent() != null ? result.getContent() : new ArrayList<>();
    }

    private AnalyticsFinanceSearchFilterRequest toFilterRequest(AnalyticsFinanceSearchReportFilterRequest request) {
        AnalyticsFinanceSearchFilterRequest filterRequest = new AnalyticsFinanceSearchFilterRequest();
        filterRequest.setPage(0);
        filterRequest.setSize(Integer.MAX_VALUE);
        filterRequest.setFilter(request.getFilter());
        filterRequest.setSort(request.getSort());
        return filterRequest;
    }

    // ---- CONVERTERS ----

    private PageDTO<ProformaDomain> toProformaDomainPage(Page<ProformaEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<ProformaDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(page.getContent().stream().map(proformaConverter::toDomain).toList());
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

    private PageDTO<DocumentFinanceDomain> toDocumentFinanceDomainPage(Page<DocumentFinanceEntity> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        PageDTO<DocumentFinanceDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(page.getContent().stream().map(documentFinanceConverter::toDomain).toList());
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
