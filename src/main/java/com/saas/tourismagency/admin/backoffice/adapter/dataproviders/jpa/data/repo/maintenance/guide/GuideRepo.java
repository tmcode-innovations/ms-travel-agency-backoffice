package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide;

import com.saas.tourismagency.admin.backoffice.core.utils.NameNormalizer;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultGuide;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.GuideEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance.GuideMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GuideRepo extends DelegatedSpringJpaRepo<GuideDomain, GuideEntity, String, GuideConverter, SetFieldDefaultGuide, GuideMongoRepo> {

    @Autowired
    private IGuideRepositoryTemplate guideRepositoryTemplate;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    public GuideRepo(GuideMongoRepo springRepo, GuideConverter converter, SetFieldDefaultGuide setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> GUIDE_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<GuideDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (GUIDE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = GUIDE_ENTITY_FIELDS.get(key);
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
        String firstName = null;
        String lastName = null;
        String supplier = null;

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
                    case "firstName" -> {
                        firstName = entry.getValue();
                    }
                    case "lastName" -> {
                        lastName = entry.getValue();
                    }
                    case "supplier" -> {
                        supplier = entry.getValue();
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

        if (StringUtils.isNotEmpty(firstName) || StringUtils.isNotEmpty(lastName)) {
            List<IdBaseDomain> personIdList = personRepo.findIdsByFirstNameAndLastName(firstName, lastName);
            if (personIdList.isEmpty()) {
                query.addCriteria(Criteria.where("id").is("__none__"));
            } else {
                List<Object> personValues = personIdList.stream()
                        .map(IdBaseDomain::getId)
                        .filter(StringUtils::isNotEmpty)
                        .map(this::toReferenceValue)
                        .toList();
                query.addCriteria(new Criteria().orOperator(
                        Criteria.where("person").in(personValues),
                        Criteria.where("person.$id").in(personValues)
                ));
            }
        }

        if (StringUtils.isNotEmpty(supplier)) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("supplier").is(toReferenceValue(supplier)),
                    Criteria.where("supplier.$id").is(toReferenceValue(supplier))
            ));
        }

        query.fields().include("id", "identify", "ruc", "person", "contactInformation", "supplier", "guideTypes", "idioms");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return guideRepositoryTemplate.queryAllSimpleDomainCaseListGuide(pageConfig, query);
    }

    public PageDTO<GuideDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (GUIDE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = GUIDE_ENTITY_FIELDS.get(key);
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

        return guideRepositoryTemplate.query(pageConfig, query);
    }

    public List<GuideDomain> findAll(Boolean deleted){
        GuideEntity example = new GuideEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<GuideDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public GuideDomain findByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.addCriteria(Criteria.where("name").regex(NameNormalizer.exactLikePattern(name), "i"));
        query.fields().include("id");
        IdBaseDomain found = guideRepositoryTemplate.queryOneId(query);
        if (found == null) {
            return null;
        }
        return findById(found.getId());
    }

    public GuideDomain findByIdentify(String identify){
        GuideEntity entity = repo().findByIdentifyAndTenantAndDeleted(identify, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public List<GuideDomain> searchAllBySupplierAndGuideTypes(String idSupplier, List<String> guideTypes) {
        Query query = new Query();
        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("supplier").is(toReferenceValue(idSupplier)),
                    Criteria.where("supplier.$id").is(toReferenceValue(idSupplier))
            ));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        // Supplier
        query.addCriteria(Criteria.where("guideTypes").in(guideTypes));

        return guideRepositoryTemplate.query(query);
    }

    private Object toReferenceValue(String id) {
        if (id == null || id.isEmpty()) {
            return id;
        }
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return id;
        }
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return guideRepositoryTemplate.queryOneId(query);
    }

    public List<GuideDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "person");

        return guideRepositoryTemplate.queryAllSimple(query);
    }
}
