package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterGroup;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking.RegisterGroupMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
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
public class RegisterGroupRepo extends DelegatedSpringJpaRepo<RegisterGroupDomain, RegisterGroupEntity, String, RegisterGroupConverter, SetFieldDefaultRegisterGroup, RegisterGroupMongoRepo> {

    @Autowired
    private IRegisterGroupRepositoryTemplate repositoryTemplate;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public RegisterGroupRepo(RegisterGroupMongoRepo springRepo, RegisterGroupConverter converter, SetFieldDefaultRegisterGroup setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> REGISTER_GROUP_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify"),
            Map.entry("identifyQuotation", "quotation.identify"),
            Map.entry("cantPax", "cantPax"),
            Map.entry("idCustomer", "customer.id"),
            Map.entry("idProgram", "program.id"),
            Map.entry("idIdiom", "idiom.id"),
            Map.entry("idResponsible", "responsible.id"),
            Map.entry("stateRegisterGroup", "stateRegisterGroup"),
            Map.entry("initTourStart", "initTour"),
            Map.entry("initTourEnd", "initTour"),
            Map.entry("endTourStart", "endTour"),
            Map.entry("endTourEnd", "endTour"),
            Map.entry("dateApertureStart", "dateAperture"),
            Map.entry("dateApertureEnd", "dateAperture"),
            Map.entry("createdAtStart", "createdAt"),
            Map.entry("createdAtEnd", "createdAt")
    );

    public PageDTO<RegisterGroupDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        Query query = buildFilterQuery(searchFilterDomain);
        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize()
        );
        return repositoryTemplate.queryAllSimpleDomainCaseListRegisterGroup(pageConfig, query);
    }

    public List<RegisterGroupDomain> searchFilterAll(ISearchFilterPageDomain searchFilterDomain) {
        Query query = buildFilterQuery(searchFilterDomain);
        return repositoryTemplate.queryAllSimpleDomainCaseListRegisterGroup(query);
    }

    private Query buildFilterQuery(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_GROUP_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_GROUP_ENTITY_FIELDS.get(key);
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
        String identifyQuotation = null;
        Integer cantPax = null;
        String idQuotation = null;
        String idCustomer = null;
        String idProgram = null;
        String idIdiom = null;
        String idResponsible = null;
        String stateRegisterGroup = null;
        String initTourStart = null;
        String initTourEnd = null;
        String endTourStart = null;
        String endTourEnd = null;
        String dateApertureStart = null;
        String dateApertureEnd = null;
        String createdAtStart = null;
        String createdAtEnd = null;

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
                    case "identifyQuotation" -> {
                        identifyQuotation = entry.getValue();
                    }
                    case "cantPax" ->{
                        if (entry.getValue() != null){
                            try {
                                cantPax = Integer.valueOf(entry.getValue());
                            } catch (Exception e) {
                                throw new BadRequestException("Error parsing 'cantPax' value to integer.");
                            }}
                        }
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idProgram" -> {
                        idProgram = entry.getValue();
                    }
                    case "idIdiom" -> {
                        idIdiom = entry.getValue();
                    }
                    case "idQuotation" -> {
                        idQuotation = entry.getValue();
                    }
                    case "idResponsible" -> {
                        idResponsible = entry.getValue();
                    }
                    case "stateRegisterGroup" -> {
                        stateRegisterGroup = entry.getValue();
                    }
                    case "initTourStart" -> {
                        initTourStart = entry.getValue();
                    }
                    case "initTourEnd" -> {
                        initTourEnd = entry.getValue();
                    }
                    case "endTourStart" -> {
                        endTourStart = entry.getValue();
                    }
                    case "endTourEnd" -> {
                        endTourEnd = entry.getValue();
                    }
                    case "dateApertureStart" -> {
                        dateApertureStart = entry.getValue();
                    }
                    case "dateApertureEnd" -> {
                        dateApertureEnd = entry.getValue();
                    }
                    case "createdAtStart" -> {
                        createdAtStart = entry.getValue();
                    }
                    case "createdAtEnd" -> {
                        createdAtEnd = entry.getValue();
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

        if (StringUtils.isNotEmpty(identifyQuotation)) {
            QuotationDomain quotationDomain = quotationRepo.findByIdentify(identifyQuotation);
            if (quotationDomain != null){
                query.addCriteria(Criteria.where("quotation.id").is(quotationDomain.getId()));
            }
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (cantPax != null) {
            query.addCriteria(Criteria.where("totalPax").is(cantPax));
        }

        if (StringUtils.isNotEmpty(idCustomer)) {
            query.addCriteria(Criteria.where("customer.id").is(idCustomer));
        }

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        if (StringUtils.isNotEmpty(idIdiom)) {
            query.addCriteria(Criteria.where("idiom.id").is(idIdiom));
        }

        if (StringUtils.isNotEmpty(idResponsible)) {
            query.addCriteria(Criteria.where("responsible.id").is(idResponsible));
        }

        if (StringUtils.isNotEmpty(stateRegisterGroup)) {
            query.addCriteria(Criteria.where("stateRegisterGroup").is(stateRegisterGroup));
        }

        if (StringUtils.isNotEmpty(initTourStart) && StringUtils.isNotEmpty(initTourEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(initTourStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(initTourEnd);
            query.addCriteria(Criteria.where("initTour").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(initTourStart) && (initTourEnd == null || StringUtils.isEmpty(initTourEnd) || StringUtils.isBlank(initTourEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(initTourStart);
            query.addCriteria(Criteria.where("initTour").gte(dtStart));
        } else if (StringUtils.isNotEmpty(initTourEnd) && (initTourStart == null || StringUtils.isEmpty(initTourStart) || StringUtils.isBlank(initTourStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(initTourEnd);
            query.addCriteria(Criteria.where("initTour").lte(dtEnd));
        }

        if (StringUtils.isNotEmpty(endTourStart) && StringUtils.isNotEmpty(endTourEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(endTourStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(endTourEnd);
            query.addCriteria(Criteria.where("endTour").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(endTourStart) && (endTourEnd == null || StringUtils.isEmpty(endTourEnd) || StringUtils.isBlank(endTourEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(endTourStart);
            query.addCriteria(Criteria.where("endTour").gte(dtStart));
        } else if (StringUtils.isNotEmpty(endTourEnd) && (endTourStart == null || StringUtils.isEmpty(endTourStart) || StringUtils.isBlank(endTourStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(endTourEnd);
            query.addCriteria(Criteria.where("endTour").lte(dtEnd));
        }

        if (StringUtils.isNotEmpty(dateApertureStart) && StringUtils.isNotEmpty(dateApertureEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateApertureStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateApertureEnd);
            query.addCriteria(Criteria.where("dateAperture").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateApertureStart) && (dateApertureEnd == null || StringUtils.isEmpty(dateApertureEnd) || StringUtils.isBlank(dateApertureEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateApertureStart);
            query.addCriteria(Criteria.where("dateAperture").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateApertureEnd) && (dateApertureStart == null || StringUtils.isEmpty(dateApertureStart) || StringUtils.isBlank(dateApertureStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateApertureEnd);
            query.addCriteria(Criteria.where("dateAperture").lte(dtEnd));
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

        query.fields().include("id", "apertureWrite", "quotation", "stateRegisterGroup", "identify", "dateAperture", "initTour", "endTour", "customer", "name", "totalPax", "responsible.person", "stateRegisterGroup", "link");

        query.with(Sort.by(order));

        return query;
    }

    public PageDTO<RegisterGroupDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_GROUP_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_GROUP_ENTITY_FIELDS.get(key);
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

        return repositoryTemplate.query(pageConfig, query);
    }

    public List<RegisterGroupDomain> findAll(Boolean deleted){
        RegisterGroupEntity example = new RegisterGroupEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<RegisterGroupDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public RegisterGroupDomain findByName(String name){
        RegisterGroupEntity entity = repo().findByNameAndTenantAndDeleted(name, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public RegisterGroupDomain findByIdentify(String identify){
        RegisterGroupEntity entity = repo().findByIdentifyAndTenantAndDeleted(identify, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public RegisterGroupDomain getByQuotation(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        List<RegisterGroupDomain> list = repositoryTemplate.query(query);
        if (list != null && !list.isEmpty()){
            return repositoryTemplate.query(query).getFirst();
        }
        return null;
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "identify");

        return repositoryTemplate.queryAllSimple(query);
    }

    public RegisterGroupDomain findByIdRegisterGroupCaseCreateItinerary(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "quotation");

        return repositoryTemplate.queryOneIdSimpleDomainCaseCreateItinerary(query);
    }

    public void updateIdSimpleDomainCaseDeleteItineraryDate(String id, LocalDate initTour, LocalDate endTour, Integer days){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("initTour", initTour);
        update.set("endTour", endTour);
        update.set("days", days);

        repositoryTemplate.updateIdSimpleDomainCaseDeleteItineraryDate(query, update);
    }

    // Statistics

    public Long countRegisterGroup(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.countRegisterGroup(query);
    }

    public List<RegisterGroupDomain> findAllRegisterGroupNotInCostSheetTwo(){
        return converter.toDomainAllSimpleToDomainCaseListRegisterGroupWithoutCostSheetTwo(repositoryTemplate.aggregateFindRegisterGroupNotInCostSheetTwo());
    }

    public List<RegisterGroupDomain> findAllRegisterGroupForCostSheetTwo(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("apertureWrite").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "identify", "name", "initTour", "endTour", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "customer");

        return repositoryTemplate.findAllRegisterGroupForCostSheetTwo(query);
    }

    public List<RegisterGroupDomain> findAllSimpleAperture(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("apertureWrite").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "identify", "responsible", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "totalPax");

        return repositoryTemplate.queryAllAperture(query);
    }

    public List<RegisterGroupDomain> findAllSimpleApertureDocumentFinance(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("apertureWrite").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "identify", "initTour", "endTour", "responsible","customer", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "totalPax");

        return repositoryTemplate.queryAllApertureDocumentFinance(query);
    }

    public List<RegisterGroupDomain> findAllSimpleApertureVoucherIngress(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("apertureWrite").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "identify", "initTour", "endTour", "responsible","customer", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "totalPax");

        return repositoryTemplate.queryAllApertureVoucherIngress(query);
    }

    public List<RegisterGroupDomain> findAllSimpleApertureVoucherEgress(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("apertureWrite").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "identify", "initTour", "endTour", "responsible","customer", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "totalPax");

        return repositoryTemplate.queryAllApertureVoucherEgress(query);
    }

    public List<RegisterGroupDomain> findAllSimpleApertureReport(){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("apertureWrite").is(true));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "name", "identify", "initTour", "endTour", "responsible","customer", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "totalPax");
        return repositoryTemplate.queryAllApertureReport(query);
    }

    public List<RegisterGroupDomain> findAllSimpleApertureVoucherIngressByCustomer(String idCustomer){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("apertureWrite").is(true));
        query.addCriteria(Criteria.where("customer.id").is(idCustomer));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "name", "identify", "initTour", "endTour", "responsible","customer", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "totalPax");
        return repositoryTemplate.queryAllApertureVoucherIngress(query);
    }

    public List<RegisterGroupDomain> findAllSimpleApertureVoucherIngressBySupplier(List<String> registerGroupIds){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("apertureWrite").is(true));
        query.addCriteria(Criteria.where("id").in(registerGroupIds));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "name", "identify", "initTour", "endTour", "responsible","customer", "cantPaxAdult", "cantPaxChildren", "tC", "tCLocal", "guide", "totalPax");
        return repositoryTemplate.queryAllApertureVoucherIngress(query);
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

        repositoryTemplate.updateIdSimpleDomainCaseUserDataModification(query, update);
    }

    public RegisterGroupDomain findByIdRegisterGroupCaseGetModificationUserData(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "updatedAt", "updatedBy");

        return repositoryTemplate.queryOneIdSimpleDomainCaseGetModificationUserData(query);
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

        repositoryTemplate.updateIdSimpleDomainCaseCustomerModification(query, update);
    }

    public RegisterGroupDomain findByIdQuotationCaseGetModificationCustomerData(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryOneIdSimpleDomainCaseGetModificationCustomerData(query);
    }
}
