package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.BaseEntityClassAliases;

import com.saas.tourismagency.admin.backoffice.core.utils.NameNormalizer;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSupplierRestaurant;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance.SupplierRestaurantMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierRestaurantDomain;
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
public class SupplierRestaurantRepo extends DelegatedSpringJpaRepo<SupplierRestaurantDomain, SupplierRestaurantEntity, String, SupplierRestaurantConverter, SetFieldDefaultSupplierRestaurant, SupplierRestaurantMongoRepo> {

    @Autowired
    private ISupplierRestaurantRepositoryTemplate supplierRestaurantRepositoryTemplate;

    @Autowired
    public SupplierRestaurantRepo(SupplierRestaurantMongoRepo springRepo, SupplierRestaurantConverter converter, SetFieldDefaultSupplierRestaurant setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> SUPPLIER_RESTAURANT_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<SupplierRestaurantDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (SUPPLIER_RESTAURANT_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = SUPPLIER_RESTAURANT_ENTITY_FIELDS.get(key);
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

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").is(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()));

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


        query.fields().include("id", "identify", "name");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return supplierRestaurantRepositoryTemplate.queryAllSimpleDomainCaseListSupplierRestaurant(pageConfig, query);
    }

    public PageDTO<SupplierRestaurantDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (SUPPLIER_RESTAURANT_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = SUPPLIER_RESTAURANT_ENTITY_FIELDS.get(key);
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

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").is(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Pageable pageConfig = PageRequest.of(
                searchDomain.getPage(),
                searchDomain.getSize(),
                Sort.by(order)
        );

        return supplierRestaurantRepositoryTemplate.query(pageConfig, query);
    }

    public List<SupplierRestaurantDomain> findAll(String typeInheritance){
        SupplierRestaurantEntity example = new SupplierRestaurantEntity();
        example.setTypeInheritance(typeInheritance);
        example.setDeleted(false);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<SupplierRestaurantDomain> suppliers = this.findAllWithExample(
                Example.of(example)
        );
        return suppliers;
    }

    public SupplierRestaurantDomain findByNameAndTypeInheritance(String name, String typeInheritance){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.addCriteria(Criteria.where("name").regex(NameNormalizer.exactLikePattern(name), "i"));
        query.addCriteria(Criteria.where("typeInheritance").is(typeInheritance));
        query.fields().include("id");
        IdBaseDomain found = supplierRestaurantRepositoryTemplate.queryOneId(query);
        if (found == null) {
            return null;
        }
        return findById(found.getId());
    }

    public boolean existsByNameGlobal(String name, String excludeId){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.addCriteria(Criteria.where("name").regex(NameNormalizer.exactLikePattern(name), "i"));
        query.addCriteria(Criteria.where("_class").in(BaseEntityClassAliases.SUPPLIER));
        if (excludeId != null) {
            query.addCriteria(Criteria.where("id").ne(excludeId));
        }
        query.fields().include("id");
        return supplierRestaurantRepositoryTemplate.queryOneId(query) != null;
    }

    public SupplierRestaurantDomain findByIdentifyAndTypeInheritance(String identify, String typeInheritance) {
        SupplierRestaurantEntity entity = repo().findByIdentifyAndTypeInheritanceAndTenantAndDeleted(identify, typeInheritance, UserResolver.resolveUser().getTenant(), false);
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

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").is(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return supplierRestaurantRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").is(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return supplierRestaurantRepositoryTemplate.queryAllSimple(query);
    }

    public List<SupplierRestaurantDomain> findAllSimpleWithType(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").is(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "supplierType");

        return supplierRestaurantRepositoryTemplate.queryAllSimpleWithType(query);
    }

    public List<SupplierRestaurantDomain> findAllSimpleWithDepartmentRegion(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").is(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "supplierType", "partner");

        return supplierRestaurantRepositoryTemplate.queryAllSimpleWithDepartmentRegion(query);
    }
}
