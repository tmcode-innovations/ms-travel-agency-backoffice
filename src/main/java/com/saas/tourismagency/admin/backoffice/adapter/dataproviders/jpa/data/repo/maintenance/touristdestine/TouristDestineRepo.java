package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine;

import com.saas.tourismagency.admin.backoffice.core.utils.NameNormalizer;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultTouristDestine;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.TouristDestineEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance.TouristDestineMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.TouristDestineDomain;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TouristDestineRepo extends DelegatedSpringJpaRepo<TouristDestineDomain, TouristDestineEntity, String, TouristDestineConverter, SetFieldDefaultTouristDestine, TouristDestineMongoRepo> {

    @Autowired
    private ITouristDestineRepositoryTemplate touristDestineRepositoryTemplate;

    @Autowired
    public TouristDestineRepo(TouristDestineMongoRepo springRepo, TouristDestineConverter converter, SetFieldDefaultTouristDestine setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> TOURIST_DESTINE_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<TouristDestineDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (TOURIST_DESTINE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = TOURIST_DESTINE_ENTITY_FIELDS.get(key);
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

        query.fields().include("id", "identify", "name", "country", "departmentRegion", "province", "district", "state");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return touristDestineRepositoryTemplate.queryAllSimpleDomainCaseListTouristDestine(pageConfig, query);
    }

    public PageDTO<TouristDestineDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (TOURIST_DESTINE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = TOURIST_DESTINE_ENTITY_FIELDS.get(key);
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

        return touristDestineRepositoryTemplate.query(pageConfig, query);
    }

    public List<TouristDestineDomain> findAll(Boolean deleted){
        TouristDestineEntity example = new TouristDestineEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<TouristDestineDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public TouristDestineDomain findByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.addCriteria(Criteria.where("name").regex(NameNormalizer.exactLikePattern(name), "i"));
        query.fields().include("id");
        IdBaseDomain found = touristDestineRepositoryTemplate.queryOneId(query);
        if (found == null) {
            return null;
        }
        return findById(found.getId());
    }

    public TouristDestineDomain findByIdentify(String identify){
        TouristDestineEntity entity = repo().findByIdentifyAndTenantAndDeleted(identify, UserResolver.resolveUser().getTenant(), false);
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

        return touristDestineRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return touristDestineRepositoryTemplate.queryAllSimple(query);
    }
}
