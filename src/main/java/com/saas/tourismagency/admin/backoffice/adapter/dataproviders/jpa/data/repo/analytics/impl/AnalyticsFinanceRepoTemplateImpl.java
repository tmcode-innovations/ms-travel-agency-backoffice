package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.DocumentFinanceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.ProformaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.IAnalyticsFinanceRepoTemplate;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.AnalyticsFilterUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AnalyticsFinanceRepoTemplateImpl implements IAnalyticsFinanceRepoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Override
    public Page<ProformaEntity> aggregatePageableProformaByTypeAndDate(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String proformaType, boolean useCancelDate) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
                    }
                    case "customerId" -> {
                        customerId = entry.getValue();
                    }
                    case "registerGroupId" -> {
                        registerGroupId = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(proformaType)) {
            listCriteria.add(Criteria.where("proformaType").is(AnalyticsFilterUtil.proformaType(proformaType)));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(useCancelDate ? "dateCancel" : "date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where(useCancelDate ? "dateCancel" : "date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(useCancelDate ? "dateCancel" : "date").lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                matchOperation,
                facetOperation
        );

        AggregationResults<ProformaAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_proformas", ProformaAggregationResult.class
        );

        ProformaAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ProformaEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<DocumentFinanceEntity> aggregatePageableDocumentFinanceByTypeAndDate(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String typeInheritance, boolean useCancelDate) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
                    }
                    case "customerId" -> {
                        customerId = entry.getValue();
                    }
                    case "registerGroupId" -> {
                        registerGroupId = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(typeInheritance)) {
            listCriteria.add(Criteria.where("typeInheritanceDocumentFinance").is(typeInheritance));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        String dateField = useCancelDate ? "dateCancel" : "date";
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(dateField).gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where(dateField).gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(dateField).lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                matchOperation,
                facetOperation
        );

        AggregationResults<DocumentFinanceAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_document_finances", DocumentFinanceAggregationResult.class
        );

        DocumentFinanceAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<DocumentFinanceEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<DocumentFinanceEntity> aggregatePageableDocumentFinanceByTypesAndDate(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, List<String> typeInheritances, boolean useCancelDate) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;
        String state = null;
        String documentType = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "registerGroupId" -> registerGroupId = entry.getValue();
                    case "state" -> state = entry.getValue();
                    case "documentType" -> documentType = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (typeInheritances != null && !typeInheritances.isEmpty()) {
            listCriteria.add(Criteria.where("typeInheritanceDocumentFinance").in(typeInheritances));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        if (StringUtils.isNotEmpty(state)) {
            listCriteria.add(Criteria.where("documentAccountState").is(AnalyticsFilterUtil.documentAccountState(state)));
        }
        if (StringUtils.isNotEmpty(documentType)) {
            listCriteria.add(Criteria.where("documentAccountType").is(AnalyticsFilterUtil.refId(documentType)));
        }
        String dateField = useCancelDate ? "dateCancel" : "date";
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(dateField).gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where(dateField).gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(dateField).lte(dtEnd));
        }
        Criteria criteria = new Criteria().andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(skipOperation, limitOperation).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                matchOperation,
                facetOperation
        );

        AggregationResults<DocumentFinanceAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_document_finances", DocumentFinanceAggregationResult.class
        );

        DocumentFinanceAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<DocumentFinanceEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<ProformaEntity> aggregatePageableProformaByFilters(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;
        String stateProform = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "registerGroupId" -> registerGroupId = entry.getValue();
                    case "stateProform" -> stateProform = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        if (StringUtils.isNotEmpty(stateProform)) {
            listCriteria.add(Criteria.where("proformaState").is(AnalyticsFilterUtil.proformaState(stateProform)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").lte(dtEnd));
        }
        Criteria criteria = new Criteria().andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(skipOperation, limitOperation).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                matchOperation,
                facetOperation
        );

        AggregationResults<ProformaAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_proformas", ProformaAggregationResult.class
        );

        ProformaAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ProformaEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<DocumentFinanceEntity> aggregatePageableRegisterSaleOperative(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;
        String supplierId = null;
        String state = null;
        String documentType = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "registerGroupId" -> registerGroupId = entry.getValue();
                    case "supplierId" -> supplierId = entry.getValue();
                    case "state" -> state = entry.getValue();
                    case "documentType" -> documentType = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("typeInheritanceDocumentFinance").is("DocumentFinanceInvoiceCommissionEntityData"));
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        if (StringUtils.isNotEmpty(supplierId)) {
            listCriteria.add(Criteria.where("supplier").is(AnalyticsFilterUtil.refId(supplierId)));
        }
        if (StringUtils.isNotEmpty(state)) {
            listCriteria.add(Criteria.where("documentAccountState").is(AnalyticsFilterUtil.documentAccountState(state)));
        }
        if (StringUtils.isNotEmpty(documentType)) {
            listCriteria.add(Criteria.where("documentAccountType").is(AnalyticsFilterUtil.refId(documentType)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").lte(dtEnd));
        }
        Criteria criteria = new Criteria().andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(skipOperation, limitOperation).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                matchOperation,
                facetOperation
        );

        AggregationResults<DocumentFinanceAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_document_finances", DocumentFinanceAggregationResult.class
        );

        DocumentFinanceAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<DocumentFinanceEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<DocumentFinanceEntity> aggregatePageableRegisterSaleOperativePassenger(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;
        String supplierId = null;
        String employeeId = null;
        String state = null;
        String documentType = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "registerGroupId" -> registerGroupId = entry.getValue();
                    case "supplierId" -> supplierId = entry.getValue();
                    case "employeeId" -> employeeId = entry.getValue();
                    case "state" -> state = entry.getValue();
                    case "documentType" -> documentType = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");
        LookupOperation lookupOperationPassengerGroup = Aggregation.lookup("booking_passenger_groups", "registerGroup", "registerGroup", "joinedDataPassengerGroup");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
listCriteria.add(Criteria.where("typeInheritanceDocumentFinance").is("DocumentFinanceInvoiceCommissionEntityData"));
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        if (StringUtils.isNotEmpty(supplierId)) {
            listCriteria.add(Criteria.where("supplier").is(AnalyticsFilterUtil.refId(supplierId)));
        }
        if (StringUtils.isNotEmpty(employeeId)) {
            listCriteria.add(Criteria.where("responsible").is(AnalyticsFilterUtil.refId(employeeId)));
        }
        if (StringUtils.isNotEmpty(state)) {
            listCriteria.add(Criteria.where("documentAccountState").is(AnalyticsFilterUtil.documentAccountState(state)));
        }
        if (StringUtils.isNotEmpty(documentType)) {
            listCriteria.add(Criteria.where("documentAccountType").is(AnalyticsFilterUtil.refId(documentType)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").lte(dtEnd));
        }
        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");
        FacetOperation facetOperation = Aggregation.facet(skipOperation, limitOperation).as("data").and(countOperation).as("totalCount");
        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                lookupOperationPassengerGroup,
                matchOperation,
                facetOperation
        );

        AggregationResults<DocumentFinanceAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_document_finances", DocumentFinanceAggregationResult.class
        );

        DocumentFinanceAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<DocumentFinanceEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<ProformaEntity> aggregatePageableGroupPendingInvoice(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;
        String typeProformDocument = null;
        String typeDate = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "registerGroupId" -> registerGroupId = entry.getValue();
                    case "typeProformDocument" -> typeProformDocument = entry.getValue();
                    case "typeDate" -> typeDate = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");
        LookupOperation lookupOperationInvoices = Aggregation.lookup("account_document_finances", "registerGroup", "registerGroup", "linkedInvoices");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("linkedInvoices").size(0));
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        if (StringUtils.isNotEmpty(typeProformDocument)) {
            listCriteria.add(Criteria.where("proformaType").is(AnalyticsFilterUtil.proformaType(typeProformDocument)));
        }
        String dateField = "date";
        if (StringUtils.isNotEmpty(typeDate)) {
            dateField = switch (typeDate) {
                case "1", "FECHA_INICIO" -> "dateInit";
                case "2", "FECHA_FIN" -> "dateEnd";
                case "FECHA_CANCELACION" -> "dateCancel";
                default -> "date";
            };
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(dateField).gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where(dateField).gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where(dateField).lte(dtEnd));
        }
        Criteria criteria = new Criteria().andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(skipOperation, limitOperation).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                lookupOperationInvoices,
                matchOperation,
                facetOperation
        );

        AggregationResults<ProformaAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_proformas", ProformaAggregationResult.class
        );

        ProformaAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ProformaEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<ProformaEntity> aggregatePageableProformCtrl(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;
        String customerId = null;
        String registerGroupId = null;
        String proformaType = null;
        String proformaState = null;
        String currency = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "registerGroupId" -> registerGroupId = entry.getValue();
                    case "proformaType" -> proformaType = entry.getValue();
                    case "proformaState" -> proformaState = entry.getValue();
                    case "currency" -> currency = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationCurrency = Aggregation.lookup("maintenance_currencies", "currency", "_id", "joinedDataCurrency");
        LookupOperation lookupOperationChangeType = Aggregation.lookup("account_change_types", "changeType", "_id", "joinedDataChangeType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(proformaType)) {
            listCriteria.add(Criteria.where("proformaType").is(AnalyticsFilterUtil.proformaType(proformaType)));
        }
        if (StringUtils.isNotEmpty(proformaState)) {
            listCriteria.add(Criteria.where("proformaState").is(AnalyticsFilterUtil.proformaState(proformaState)));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(registerGroupId)) {
            listCriteria.add(Criteria.where("registerGroup").is(AnalyticsFilterUtil.refId(registerGroupId)));
        }
        if (StringUtils.isNotEmpty(currency)) {
            listCriteria.add(Criteria.where("currency").is(AnalyticsFilterUtil.refId(currency)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").lte(dtEnd));
        }
        Criteria criteria = new Criteria().andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(skipOperation, limitOperation).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationCustomer,
                lookupOperationCurrency,
                lookupOperationChangeType,
                matchOperation,
                facetOperation
        );

        AggregationResults<ProformaAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_proformas", ProformaAggregationResult.class
        );

        ProformaAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ProformaEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    public static class ProformaAggregationResult {
        private List<ProformaEntity> data;
        private List<TotalCount> totalCount;

        public List<ProformaEntity> getData() {
            return data;
        }

        public void setData(List<ProformaEntity> data) {
            this.data = data;
        }

        public List<TotalCount> getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(List<TotalCount> totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class DocumentFinanceAggregationResult {
        private List<DocumentFinanceEntity> data;
        private List<TotalCount> totalCount;

        public List<DocumentFinanceEntity> getData() {
            return data;
        }

        public void setData(List<DocumentFinanceEntity> data) {
            this.data = data;
        }

        public List<TotalCount> getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(List<TotalCount> totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class TotalCount {
        private long total;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }
    }
}
