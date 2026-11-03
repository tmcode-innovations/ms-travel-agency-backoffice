package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultQuotation;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.QuotationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm.QuotationMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.repo.DelegatedSpringJpaRepo;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuotationRepo extends DelegatedSpringJpaRepo<QuotationDomain, QuotationEntity, String, QuotationConverter, SetFieldDefaultQuotation, QuotationMongoRepo> {

    @Autowired
    private IQuotationRepositoryTemplate quotationRepositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public QuotationRepo(QuotationMongoRepo springRepo, QuotationConverter converter, SetFieldDefaultQuotation setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> QUOTATION_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify"),
            Map.entry("quotationType", "quotationType"),
            Map.entry("idCustomer", "customer.id"),
            Map.entry("nameCustomer", "customer.name"),
            Map.entry("stateQuotation", "stateQuotation"),
            Map.entry("dateInitTourStart", "dateInitTour"),
            Map.entry("dateInitTourEnd", "dateInitTour"),
            Map.entry("dateEndTourStart", "dateEndTour"),
            Map.entry("dateEndTourEnd", "dateEndTour"),
            Map.entry("createdAtStart", "createdAt"),
            Map.entry("createdAtEnd", "createdAt"),
            Map.entry("idEmployee", "employee.id")
    );

    public PageDTO<QuotationDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (QUOTATION_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = QUOTATION_ENTITY_FIELDS.get(key);
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
        String name = null;
        String description = null;
        String identify = null;
        String quotationType = null;
        String idCustomer = null;
        String nameCustomer = null;
        String stateQuotation = null;
        String dateInitTourStart = null;
        String dateInitTourEnd = null;
        String dateEndTourStart = null;
        String dateEndTourEnd = null;
        String createdAtStart = null;
        String createdAtEnd = null;
        String idEmployee = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "name" -> {
                        name = entry.getValue();
                    }
                    case "description" -> {
                        description = entry.getValue();
                    }
                    case "identify" -> {
                        identify = entry.getValue();
                    }
                    case "quotationType" -> {
                        quotationType = entry.getValue();
                    }
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "nameCustomer" -> {
                        nameCustomer = entry.getValue();
                    }
                    case "stateQuotation" -> {
                        stateQuotation = entry.getValue();
                    }
                    case "dateInitTourStart" -> {
                        dateInitTourStart = entry.getValue();
                    }
                    case "dateInitTourEnd" -> {
                        dateInitTourEnd = entry.getValue();
                    }
                    case "dateEndTourStart" -> {
                        dateEndTourStart = entry.getValue();
                    }
                    case "dateEndTourEnd" -> {
                        dateEndTourEnd = entry.getValue();
                    }
                    case "createdAtStart" -> {
                        createdAtStart = entry.getValue();
                    }
                    case "createdAtEnd" -> {
                        createdAtEnd = entry.getValue();
                    }
                    case "idEmployee" -> {
                        idEmployee = entry.getValue();
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

        if (StringUtils.isNotEmpty(name)) {
            query.addCriteria(Criteria.where("name").regex(String.format(".*%s.*", name),"i"));
        }

        if (StringUtils.isNotEmpty(description)) {
            query.addCriteria(Criteria.where("description").regex(String.format(".*%s.*", description),"i"));
        }

        if (StringUtils.isNotEmpty(identify)) {
            query.addCriteria(Criteria.where("identify").regex(String.format(".*%s.*", identify),"i"));
        }

        if (StringUtils.isNotEmpty(quotationType)) {
            query.addCriteria(Criteria.where("quotationType").is(quotationType));
        }

        if (StringUtils.isNotEmpty(idCustomer)) {
            query.addCriteria(Criteria.where("customer.id").is(idCustomer));
        }

        if (StringUtils.isNotEmpty(nameCustomer)) {
            query.addCriteria(Criteria.where("customer.name").is(nameCustomer));
        }

        if (StringUtils.isNotEmpty(stateQuotation)) {
            query.addCriteria(Criteria.where("stateQuotation").is(stateQuotation));
        }

        if (StringUtils.isNotEmpty(dateInitTourStart) && StringUtils.isNotEmpty(dateInitTourEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateInitTourStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateInitTourEnd);
            query.addCriteria(Criteria.where("dateInitTour").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateInitTourStart) && (dateInitTourEnd == null || StringUtils.isEmpty(dateInitTourEnd) || StringUtils.isBlank(dateInitTourEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateInitTourStart);
            query.addCriteria(Criteria.where("dateInitTour").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateInitTourEnd) && (dateInitTourStart == null || StringUtils.isEmpty(dateInitTourStart) || StringUtils.isBlank(dateInitTourStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateInitTourEnd);
            query.addCriteria(Criteria.where("dateInitTour").lte(dtEnd));
        }


        if (StringUtils.isNotEmpty(dateEndTourStart) && StringUtils.isNotEmpty(dateEndTourEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateEndTourStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateEndTourEnd);
            query.addCriteria(Criteria.where("dateEndTour").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateEndTourStart) && (dateEndTourEnd == null || StringUtils.isEmpty(dateEndTourEnd) || StringUtils.isBlank(dateEndTourEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateEndTourStart);
            query.addCriteria(Criteria.where("dateEndTour").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEndTourEnd) && (dateEndTourStart == null || StringUtils.isEmpty(dateEndTourStart) || StringUtils.isBlank(dateEndTourStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateEndTourEnd);
            query.addCriteria(Criteria.where("dateEndTour").lte(dtEnd));
        }

        if (StringUtils.isNotEmpty(createdAtStart) && StringUtils.isNotEmpty(createdAtEnd)) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(createdAtStart));
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(createdAtEnd));
            query.addCriteria(Criteria.where("createdAt").gt(dtStart).lt(dtEnd));
        } else if (StringUtils.isNotEmpty(createdAtStart) && (createdAtEnd == null || StringUtils.isEmpty(createdAtEnd) || StringUtils.isBlank(createdAtEnd))) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(createdAtStart));
            query.addCriteria(Criteria.where("createdAt").gte(dtStart));
        } else if (StringUtils.isNotEmpty(createdAtEnd) && (createdAtStart == null || StringUtils.isEmpty(createdAtStart) || StringUtils.isBlank(createdAtStart))) {
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(createdAtEnd));
            query.addCriteria(Criteria.where("createdAt").lte(dtEnd));
        }

        if (StringUtils.isNotEmpty(idEmployee)) {
            query.addCriteria(Criteria.where("employee.id").is(idEmployee));
        }

        query.fields().include("id", "stateQuotation", "fileNameQuotation", "fileNameItinerary", "identify", "name", "customer", "responsibleFile.person", "createdAt", "identifyFile", "employee.person", "amount");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return quotationRepositoryTemplate.queryAllSimpleDomainCaseListQuotation(pageConfig, query);
    }

    public PageDTO<QuotationDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (QUOTATION_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = QUOTATION_ENTITY_FIELDS.get(key);
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

        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Pageable pageConfig = PageRequest.of(
                searchDomain.getPage(),
                searchDomain.getSize(),
                Sort.by(order)
        );

        return quotationRepositoryTemplate.query(pageConfig, query);
    }

    public List<QuotationDomain> findAll(Boolean deleted){
        QuotationEntity example = new QuotationEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<QuotationDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public QuotationDomain findByName(String name){
        QuotationEntity entity = repo().findByNameAndTenantAndDeleted(name, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public QuotationDomain findByIdentify(String identify){
        QuotationEntity entity = repo().findByIdentifyAndTenantAndDeleted(identify, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return quotationRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return quotationRepositoryTemplate.queryAllSimple(query);
    }

    public QuotationDomain findByIdQuotationCaseCreateItinerary(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return quotationRepositoryTemplate.queryOneIdSimpleDomainCaseCreateItinerary(query);
    }

    public void updateIdSimpleDomainCaseUpdateAmount(String id, Double amount){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("amount", amount);

        quotationRepositoryTemplate.updateIdSimpleDomainCaseUpdateAmount(query, update);
    }

    public void updateIdSimpleDomainCaseDeleteItineraryDate(String id, LocalDate dateInitTour, LocalDate dateEndTour, Integer cantDayTour){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("dateInitTour", dateInitTour);
        update.set("dateEndTour", dateEndTour);
        update.set("cantDayTour", cantDayTour);

        quotationRepositoryTemplate.updateIdSimpleDomainCaseDeleteItineraryDate(query, update);
    }

    public QuotationDomain findByIdQuotationCaseGetModificationData(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "cantDayTour", "dateInitTour", "dateEndTour", "amount", "stateQuotation");

        return quotationRepositoryTemplate.queryOneIdSimpleDomainCaseGetModificationData(query);
    }

    public QuotationDomain findByIdQuotationCaseGeneric(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "identify", "identifyFile");

        return quotationRepositoryTemplate.queryOneIdSimpleDomainCaseGeneric(query);
    }

    // Statistics

    public Long countQuotation(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return quotationRepositoryTemplate.countQuotation(query);
    }

    public void updateIdSimpleDomainCaseUserDataModification(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        LocalDateTime updatedAt = LocalDateTime.now();

        Update update = new Update();

        update.set("updatedAt", updatedAt);
        update.set("updatedBy", new ObjectId(UserResolver.resolveUser().getId()));

        quotationRepositoryTemplate.updateIdSimpleDomainCaseUserDataModification(query, update);
    }

    public QuotationDomain findByIdQuotationCaseGetModificationUserData(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "updatedAt", "updatedBy");

        return quotationRepositoryTemplate.queryOneIdSimpleDomainCaseGetModificationUserData(query);
    }

    public void updateIdSimpleDomainCaseCustomerModification(String id, String idCustomer){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("customer", new ObjectId(idCustomer));

        quotationRepositoryTemplate.updateIdSimpleDomainCaseCustomerModification(query, update);
    }

    public List<QuotationDomain> searchAllSimpleDomainCaseToolUpdateEmployee() {
        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        // Employee null
        query.addCriteria(Criteria.where("employee").is(null));

        query.fields().include("id", "createdBy");

        return quotationRepositoryTemplate.queryAllSimpleDomainCaseToolUpdateEmployee(query);
    }

    public void updateEmployeeByIdSimpleDomainCaseUpdateToolEmployee(String id, String idEmployee) {
        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("employee", new ObjectId(idEmployee));

        quotationRepositoryTemplate.updateIdSimpleDomainCaseUpdateToolEmployee(query, update);
    }

    public IdBaseDomain findByIdentifyEntity(String identify){        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("identify").is(identify));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return quotationRepositoryTemplate.queryOneId(query);
    }
}
