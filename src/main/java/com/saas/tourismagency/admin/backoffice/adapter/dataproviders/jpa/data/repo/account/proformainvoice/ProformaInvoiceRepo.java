package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.proformainvoice;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.proformainvoice.ProformaInvoiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultProformaInvoice;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaInvoiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account.ProformaInvoiceMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.ProformaInvoiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.TypeInheritanceProformaEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.repo.DelegatedSpringJpaRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProformaInvoiceRepo extends DelegatedSpringJpaRepo<ProformaInvoiceDomain, ProformaInvoiceEntity, String, ProformaInvoiceConverter, SetFieldDefaultProformaInvoice, ProformaInvoiceMongoRepo> {

    @Autowired
    private IProformaInvoiceRepoTemp repositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public ProformaInvoiceRepo(ProformaInvoiceMongoRepo springRepo, ProformaInvoiceConverter converter, SetFieldDefaultProformaInvoice setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> PROFORMA_INVOICE_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("identify", "identify"),
            Map.entry("idCurrency", "currency.id"),
            Map.entry("idCustomer", "customer.id"),
            Map.entry("idRegisterGroup", "registerGroup.id"),
            Map.entry("proformaState", "proformaState"),
            Map.entry("dateStart", "date"),
            Map.entry("dateEnd", "date"),
            Map.entry("dateCancelStart", "dateCancel"),
            Map.entry("dateCancelEnd", "dateCancel")
    );

    public PageDTO<ProformaInvoiceDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (PROFORMA_INVOICE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = PROFORMA_INVOICE_ENTITY_FIELDS.get(key);
                        order.add(
                                SortType.ASC.test(entry.getValue())
                                        ? Sort.Order.asc(fieldKey)
                                        : Sort.Order.desc(fieldKey)
                        );
                    } else {
                        sortErrorMessages.add(
                                new ValidationErrorMessage(
                                        "sort.key",
                                        key,
                                        "Unknown property to sort for"
                                )
                        );
                    }
                }
            }
        }else{
            order.add(
                    Sort.Order.desc("createdAt")
            );
        }
        if (!sortErrorMessages.isEmpty()) {
            throw new ValidationException(sortErrorMessages);
        }
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String identify = null;
        String idCurrency = null;
        String idCustomer = null;
        String idRegisterGroup = null;
        String dateStart = null;
        String dateEnd = null;
        String proformaState = null;
        String dateCancelStart = null;
        String dateCancelEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "identify" -> {
                        identify = entry.getValue();
                    }
                    case "idCurrency" -> {
                        idCurrency = entry.getValue();
                    }
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idRegisterGroup" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "proformaState" -> {
                        proformaState = entry.getValue();
                    }
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
                    }
                    case "dateCancelStart" -> {
                        dateCancelStart = entry.getValue();
                    }
                    case "dateCancelEnd" -> {
                        dateCancelEnd = entry.getValue();
                    }
                    default ->
                            filterErrorMessages.add(
                                    new ValidationErrorMessage(
                                            "filter.key",
                                            entry.getKey(),
                                            "Unknown property to filter for"
                                    )
                            );
                }
            }
        }

        filterErrorMessages.addAll(sortErrorMessages);

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }

        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        // Document
        query.addCriteria(Criteria.where("typeInheritanceProforma").is(TypeInheritanceProformaEnum.PROFORMA_INVOICE.getName()));

        if (StringUtils.isNotEmpty(identify)) {
            query.addCriteria(Criteria.where("identify").regex(String.format(".*%s.*", identify),"i"));
        }

        if (StringUtils.isNotEmpty(idCurrency)) {
            query.addCriteria(Criteria.where("currency.id").is(idCurrency));
        }

        if (StringUtils.isNotEmpty(idCustomer)) {
            query.addCriteria(Criteria.where("customer.id").is(idCustomer));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(proformaState)) {
            query.addCriteria(Criteria.where("proformaState").is(proformaState));
        }

        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateEnd);
            query.addCriteria(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateStart);
            query.addCriteria(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateEnd);
            query.addCriteria(Criteria.where("date").lte(dtEnd));
        }

        if (StringUtils.isNotEmpty(dateCancelStart) && StringUtils.isNotEmpty(dateCancelEnd)) {
            LocalDate dtCancelStart = dateUtil.formatIso8601ToLocalDate(dateCancelStart);
            LocalDate dtCancelEnd = dateUtil.formatIso8601ToLocalDate(dateCancelEnd);
            query.addCriteria(Criteria.where("dateCancel").gte(dtCancelStart).lte(dtCancelEnd));
        } else if (StringUtils.isNotEmpty(dateCancelStart) && (dateCancelEnd == null || StringUtils.isEmpty(dateCancelEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtCancelStart = dateUtil.formatIso8601ToLocalDate(dateCancelStart);
            query.addCriteria(Criteria.where("dateCancel").gte(dtCancelStart));
        } else if (StringUtils.isNotEmpty(dateCancelEnd) && (dateCancelStart == null || StringUtils.isEmpty(dateCancelStart) || StringUtils.isBlank(dateCancelStart))) {
            LocalDate dtCancelEnd = dateUtil.formatIso8601ToLocalDate(dateCancelEnd);
            query.addCriteria(Criteria.where("dateCancel").lte(dtCancelEnd));
        }

        query.fields().include("id", "identify", "date", "proformaState", "dateInit", "dateEnd", "importTotalUsd", "importTotalSoles", "dateCancel", "currency", "employee", "customer", "registerGroup");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.queryAllSimpleDomainCaseListProformaInvoice(pageConfig, query);
    }

    public PageDTO<ProformaInvoiceDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (PROFORMA_INVOICE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = PROFORMA_INVOICE_ENTITY_FIELDS.get(key);
                        order.add(
                                SortType.ASC.test(entry.getValue())
                                        ? Sort.Order.asc(fieldKey)
                                        : Sort.Order.desc(fieldKey)
                        );
                    } else {
                        sortErrorMessages.add(
                                new ValidationErrorMessage(
                                        "sort.key",
                                        key,
                                        "Unknown property to sort for"
                                )
                        );
                    }
                }
            }
        }
        if (!sortErrorMessages.isEmpty()) {
            throw new ValidationException(sortErrorMessages);
        }

        Pageable pageConfig = PageRequest.of(
                searchDomain.getPage(),
                searchDomain.getSize(),
                Sort.by(order)
        );

        return this.findAll(pageConfig);
    }

    public List<ProformaInvoiceDomain> findAll(Boolean deleted){
        ProformaInvoiceEntity example = new ProformaInvoiceEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<ProformaInvoiceDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<ProformaInvoiceDomain> searchAllByRegisterGroup(String idRegisterGroup) {
        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        // Document
        query.addCriteria(Criteria.where("typeInheritanceProforma").is(TypeInheritanceProformaEnum.PROFORMA_INVOICE.getName()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        return repositoryTemplate.query(query);
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("id").is(id));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id");
        return repositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "name");
        return repositoryTemplate.queryAllSimple(query);
    }

    public IdBaseDomain findByIdentifyEntity(String identify){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("identify").is(identify));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id");
        return repositoryTemplate.queryOneId(query);
    }
}
