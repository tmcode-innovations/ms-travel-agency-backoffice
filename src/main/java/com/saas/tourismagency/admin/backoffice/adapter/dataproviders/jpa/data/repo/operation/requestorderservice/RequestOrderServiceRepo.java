package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.requestorderservice.RequestOrderServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation.SetFieldDefaultRequestOrderService;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.ConfigurationSupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.RequestOrderServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.operation.RequestOrderServiceMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
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
import java.util.Set;

@Service
public class RequestOrderServiceRepo extends DelegatedSpringJpaRepo<RequestOrderServiceDomain, RequestOrderServiceEntity, String, RequestOrderServiceConverter, SetFieldDefaultRequestOrderService, RequestOrderServiceMongoRepo> {

    @Autowired
    private IRequestOrderServiceRepositoryTemplate repositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public RequestOrderServiceRepo(RequestOrderServiceMongoRepo springRepo, RequestOrderServiceConverter converter, SetFieldDefaultRequestOrderService setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> REQUEST_ORDER_SERVICE_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify"),
            Map.entry("dateRequestOrderServiceCreateStart", "createdAt"),
            Map.entry("dateRequestOrderServiceCreateEnd", "createdAt")
    );

    public PageDTO<RequestOrderServiceDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REQUEST_ORDER_SERVICE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REQUEST_ORDER_SERVICE_ENTITY_FIELDS.get(key);
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
        String dateRequestOrderServiceCreateStart = null;
        String dateRequestOrderServiceCreateEnd = null;

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
                    case "dateRequestOrderServiceCreateStart" -> {
                        dateRequestOrderServiceCreateStart = entry.getValue();
                    }
                    case "dateRequestOrderServiceCreateEnd" -> {
                        dateRequestOrderServiceCreateEnd = entry.getValue();
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

        if (StringUtils.isNotEmpty(dateRequestOrderServiceCreateStart) && StringUtils.isNotEmpty(dateRequestOrderServiceCreateEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateRequestOrderServiceCreateStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateRequestOrderServiceCreateEnd);
            query.addCriteria(Criteria.where("createdAt").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateRequestOrderServiceCreateStart) && (dateRequestOrderServiceCreateEnd == null || StringUtils.isEmpty(dateRequestOrderServiceCreateEnd) || StringUtils.isBlank(dateRequestOrderServiceCreateEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateRequestOrderServiceCreateStart);
            query.addCriteria(Criteria.where("createdAt").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateRequestOrderServiceCreateEnd) && (dateRequestOrderServiceCreateStart == null || StringUtils.isEmpty(dateRequestOrderServiceCreateStart) || StringUtils.isBlank(dateRequestOrderServiceCreateStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateRequestOrderServiceCreateEnd);
            query.addCriteria(Criteria.where("createdAt").lte(dtEnd));
        }

        query.fields().include("id", "identify", "createdAt", "registerBookingOperator", "registerGroup", "supplierOperator", "activeRequestOrderService", "requestOrderServiceState");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.queryAllSimpleDomainCaseListRequestOrderService(pageConfig, query);
    }

    public PageDTO<RequestOrderServiceDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REQUEST_ORDER_SERVICE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REQUEST_ORDER_SERVICE_ENTITY_FIELDS.get(key);
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

    public List<RequestOrderServiceDomain> findAll(Boolean deleted){
        RequestOrderServiceEntity example = new RequestOrderServiceEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<RequestOrderServiceDomain> entities = this.findAllWithExample(
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

    public RequestOrderServiceDomain findByIdSimpleDomainCaseGenerateRequestOrderService(String idRegisterGroup, String idSupplierOperator, String idRegisterBookingOperator){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("supplierOperator.id").is(idSupplierOperator));

        query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));

        query.addCriteria(Criteria.where("registerBookingOperator.id").is(idRegisterBookingOperator));

        query.addCriteria(Criteria.where("activeRequestOrderService").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "requestOrderServiceState");

        return repositoryTemplate.queryOneIdSimpleDomainCaseGenerateRequestOrderService(query);
    }

    public void updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(String id, String requestOrderServiceState){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("requestOrderServiceState", requestOrderServiceState);

        repositoryTemplate.updateRequestOrderServiceStateByIdSimpleDomainCaseAddItem(query, update);
    }
}
