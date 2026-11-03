package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.collection;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.collection.CollectionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultCollection;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CollectionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account.CollectionMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.CollectionDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.TypeInheritanceDocumentFinanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CollectionRepo extends DelegatedSpringJpaRepo<CollectionDomain, CollectionEntity, String, CollectionConverter, SetFieldDefaultCollection, CollectionMongoRepo> {

    @Autowired
    private ICollectionRepoTemp repositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public CollectionRepo(CollectionMongoRepo springRepo, CollectionConverter converter, SetFieldDefaultCollection setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> COLLECTION_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("identify", "identify"),
            Map.entry("idCurrency", "currency.id"),
            Map.entry("idCustomer", "customer.id"),
            Map.entry("idRegisterGroup", "registerGroup.id"),
            Map.entry("documentAccountState", "documentAccountState"),
            Map.entry("dateStart", "date"),
            Map.entry("dateEnd", "date")
    );

    public PageDTO<CollectionDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COLLECTION_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COLLECTION_ENTITY_FIELDS.get(key);
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
        String documentAccountState = null;

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
                    case "documentAccountState" -> {
                        documentAccountState = entry.getValue();
                    }
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
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
        query.addCriteria(Criteria.where("typeInheritanceDocumentFinance").is(TypeInheritanceDocumentFinanceEnum.DOCUMENT_FINANCE_COLLECTION.getName()));

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

        if (StringUtils.isNotEmpty(documentAccountState)) {
            query.addCriteria(Criteria.where("documentAccountState").is(documentAccountState));
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

        query.fields().include("id", "identify", "date", "documentAccountState", "importTotalUsd", "importTotalSoles", "currency", "customer", "registerGroup");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.queryAllSimpleDomainCaseListCollection(pageConfig, query);
    }

    public PageDTO<CollectionDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COLLECTION_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COLLECTION_ENTITY_FIELDS.get(key);
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

    public List<CollectionDomain> findAll(Boolean deleted){
        CollectionEntity example = new CollectionEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<CollectionDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
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

        query.fields().include("id", "name");

        return repositoryTemplate.queryAllSimple(query);
    }

    public void updateIndexByIdSimpleDomainCaseUpdateDocumentFinanceBasic(String id,
                                                                          Double importTotalSoles,
                                                                          Double importTotalUsd,
                                                                          Double importSubTotalUsd,
                                                                          Double importSubTotalSoles,
                                                                          Double importIgvUsd,
                                                                          Double importIgvSoles,
                                                                          Double subTotalPrice){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("importSubTotalUsd", importSubTotalUsd);
        update.set("importSubTotalSoles", importSubTotalSoles);

        update.set("importIgvUsd", importIgvUsd);
        update.set("importIgvSoles", importIgvSoles);

        update.set("importTotalUsd", importTotalUsd);
        update.set("importTotalSoles", importTotalSoles);

        update.set("subTotalPrice", subTotalPrice);

        repositoryTemplate.updateIdSimpleDomainCaseUpdateDocumentFinanceBasic(query, update);
    }

    public CollectionDomain findByIdCollectionCaseGetModificationData(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "importTotalSoles", "importTotalUsd", "importSubTotalUsd", "importSubTotalSoles", "importIgvUsd", "importIgvSoles");

        return repositoryTemplate.queryOneIdSimpleDomainCaseGetModificationData(query);
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
