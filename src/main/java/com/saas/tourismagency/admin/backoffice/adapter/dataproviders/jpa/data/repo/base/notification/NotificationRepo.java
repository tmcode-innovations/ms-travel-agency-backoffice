package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.notification.NotificationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultNotification;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.NotificationEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.base.NotificationMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.NotificationDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationStatusEnum;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NotificationRepo extends DelegatedSpringJpaRepo<NotificationDomain, NotificationEntity, String, NotificationConverter, SetFieldDefaultNotification, NotificationMongoRepo> {

    @Autowired
    private INotificationRepositoryTemplate notificationRepositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public NotificationRepo(NotificationMongoRepo springRepo, NotificationConverter converter, SetFieldDefaultNotification setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> NOTIFICATION_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("createdAtStart", "createdAt"),
            Map.entry("createdAtEnd", "createdAt")
    );

    public PageDTO<NotificationDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null && !searchFilterDomain.getSort().isEmpty()) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (NOTIFICATION_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = NOTIFICATION_ENTITY_FIELDS.get(key);
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
        String createdAtStart = null;
        String createdAtEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
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

        query.addCriteria(Criteria.where("userDestine.id").is(UserResolver.resolveUser().getId()));

        query.fields().include("id", "type", "subject", "dateTime", "userSource");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return notificationRepositoryTemplate.queryAllSimpleDomainCaseListNotification(pageConfig, query);
    }

    public PageDTO<NotificationDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (NOTIFICATION_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = NOTIFICATION_ENTITY_FIELDS.get(key);
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

        return notificationRepositoryTemplate.query(pageConfig, query);
    }

    public List<NotificationDomain> findAll(Boolean deleted){
        NotificationEntity example = new NotificationEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<NotificationDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public NotificationDomain findByName(String name){

        NotificationEntity entity = repo().findByNameAndTenantAndDeleted(name, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public NotificationDomain findByIdentify(String identify){
        NotificationEntity entity = repo().findByIdentifyAndTenantAndDeleted(identify, UserResolver.resolveUser().getTenant(), false);
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

        return notificationRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return notificationRepositoryTemplate.queryAllSimple(query);
    }

    public List<NotificationDomain> searchAllNotificationsByUserSimpleDomainCaseGeneric(String idUser) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idUser)) {
            query.addCriteria(Criteria.where("userDestine.id").is(idUser));
        }

        // Status
        query.addCriteria(Criteria.where("status").in(
                List.of(
                        NotificationStatusEnum.DRAFT.getName(),
                        NotificationStatusEnum.WITHOUT_VIEW.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "subject",
                "dateTime",
                "name",
                "read",
                "type",
                "status",
                "nameObjectSource",
                "identifyObjectSource",
                "idObjectSource",
                "nameObjectDestine",
                "identifyObjectDestine",
                "idObjectDestine",
                "objectEntityDestine",
                "objectEntitySource",
                "employeeDestine",
                "employeeSource",
                "userDestine",
                "userSource"
        );

        return notificationRepositoryTemplate.queryAllSimpleDomainCaseGeneric(query);
    }

    public List<NotificationDomain> searchAllNotificationsNewByUserSimpleDomainCaseGeneric(String idUser) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idUser)) {
            query.addCriteria(Criteria.where("userDestine.id").is(idUser));
        }

        // Status
        query.addCriteria(Criteria.where("status").in(
                List.of(
                        NotificationStatusEnum.DRAFT.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "subject",
                "dateTime",
                "name",
                "read",
                "type",
                "status",
                "nameObjectSource",
                "identifyObjectSource",
                "idObjectSource",
                "nameObjectDestine",
                "identifyObjectDestine",
                "idObjectDestine",
                "objectEntityDestine",
                "objectEntitySource",
                "employeeDestine",
                "employeeSource",
                "userDestine",
                "userSource"
        );

        return notificationRepositoryTemplate.queryAllSimpleDomainCaseGeneric(query);
    }
}
