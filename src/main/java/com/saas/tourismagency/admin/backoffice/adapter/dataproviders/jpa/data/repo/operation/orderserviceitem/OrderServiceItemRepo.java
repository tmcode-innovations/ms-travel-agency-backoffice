package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.operation.orderserviceitem.OrderServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.operation.SetFieldDefaultOrderServiceItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.operation.OrderServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.operation.OrderServiceItemMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
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

import java.time.LocalDateTime;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceItemRepo extends DelegatedSpringJpaRepo<OrderServiceItemDomain, OrderServiceItemEntity, String, OrderServiceItemConverter, SetFieldDefaultOrderServiceItem, OrderServiceItemMongoRepo> {

    @Autowired
    private IOrderServiceItemRepositoryTemplate repositoryTemplate;

    @Autowired
    public OrderServiceItemRepo(OrderServiceItemMongoRepo springRepo, OrderServiceItemConverter converter, SetFieldDefaultOrderServiceItem setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> ORDER_SERVICE_ITEM_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<OrderServiceItemDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ORDER_SERVICE_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ORDER_SERVICE_ITEM_ENTITY_FIELDS.get(key);
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
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String name = null;
        String description = null;
        String identify = null;

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

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<OrderServiceItemDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ORDER_SERVICE_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ORDER_SERVICE_ITEM_ENTITY_FIELDS.get(key);
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

    public List<OrderServiceItemDomain> findAll(Boolean deleted){
        OrderServiceItemEntity example = new OrderServiceItemEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<OrderServiceItemDomain> entities = this.findAllWithExample(
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

    public List<OrderServiceItemDomain> searchAllByOrderService(String idOrderService) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idOrderService)) {
            query.addCriteria(Criteria.where("orderService.id").is(idOrderService));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public OrderServiceItemDomain searchOrderServiceAndOrderServiceItem(String idOrderService, String idOrderServiceItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idOrderService)) {
            query.addCriteria(Criteria.where("orderService.id").is(idOrderService));
        }

        if (StringUtils.isNotEmpty(idOrderServiceItem)) {
            query.addCriteria(Criteria.where("id").is(idOrderServiceItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query).getFirst();
    }

    public List<OrderServiceItemDomain> searchAllByOrderServiceSimpleDomainCaseListOrderServiceItems(String idOrderService) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idOrderService)) {
            query.addCriteria(Criteria.where("orderService.id").is(idOrderService));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "dateService", "dateTimeService", "service", "observation", "guideTransfer", "drive", "registerBookingItemOperator");

        return repositoryTemplate.queryAllSimpleDomainCaseListOrderServiceItems(query);
    }

    public List<OrderServiceItemDomain> searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItems(String idOrderService) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idOrderService)) {
            query.addCriteria(Criteria.where("orderService.id").is(idOrderService));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "registerBookingItemOperator");

        return repositoryTemplate.queryAllSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItems(query);
    }

    public List<OrderServiceItemDomain> searchAllByOrderServiceSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(String idRequestOrder) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRequestOrder)) {
            query.addCriteria(Criteria.where("requestOrderService.id").is(idRequestOrder));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "registerBookingItemOperator", "cantPax", "cantPaxAdult", "cantPaxChildren", "tc", "tcLocal", "guide");

        return repositoryTemplate.queryAllSimpleDomainCaseListOrderServiceItemByVerifyRegisterBookingItemsButRequestOrder(query);
    }

    public List<OrderServiceItemDomain> searchAllByOrderServiceCaseListSimpleByDelete(String idOrderService) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idOrderService)) {
            query.addCriteria(Criteria.where("orderService.id").is(idOrderService));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryAllSimpleDomainCaseShowOrderServiceItemByDelete(query);
    }

    public List<OrderServiceItemDomain> searchAllByRegisterGroup(String registerGroupId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(registerGroupId)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(registerGroupId));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        return repositoryTemplate.query(query);
    }

    public List<OrderServiceItemDomain> searchAllByRegisterGroupAndGuideTransfer(String registerGroupId, String guideTransferId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(registerGroupId)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(registerGroupId));
        }
        if (StringUtils.isNotEmpty(guideTransferId)) {
            query.addCriteria(Criteria.where("guideTransfer.id").is(guideTransferId));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        return repositoryTemplate.queryAllSimpleDomainCaseListOrderServiceItemsExpenseGuide(query);
    }

    public OrderServiceItemDomain searchRegisterBookingItemOperator(String idRegisterBookingItemOperator) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterBookingItemOperator)) {
            query.addCriteria(Criteria.where("registerBookingItemOperator.id").is(idRegisterBookingItemOperator));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query).getFirst();
    }

    public List<OrderServiceItemDomain> searchAllByRegisterGroupCaseChangeDate(String registerGroupId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(registerGroupId)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(registerGroupId));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "dateService", "dateTimeService", "dateTimePax", "dateTimeRecj");
        return repositoryTemplate.query(query);
    }

    public void updateChangeDateItineraryDate(String id, LocalDate dateService, LocalDateTime dateTimeService, LocalDateTime dateTimePax, LocalDateTime dateTimeRecj) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(id)) {
            query.addCriteria(Criteria.where("id").is(id));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();
        if (dateService != null) {
            update.set("dateService", dateService);
        }
        if (dateTimeService != null) {
            update.set("dateTimeService", dateTimeService);
        }
        if (dateTimePax != null) {
            update.set("dateTimePax", dateTimePax);
        }
        if (dateTimeRecj != null) {
            update.set("dateTimeRecj", dateTimeRecj);
        }
        update.set("updatedBy", new ObjectId(UserResolver.resolveUser().getId()));
        update.set("updatedAt", LocalDateTime.now());

        repositoryTemplate.updateChangeDateItineraryDate(query, update);
    }
}
