package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.BaseEntityClassAliases;

import com.saas.tourismagency.admin.backoffice.core.utils.NameNormalizer;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSupplier;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance.SupplierMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.statistics.GroupedDataDomain;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SupplierRepo extends DelegatedSpringJpaRepo<SupplierDomain, SupplierEntity, String, SupplierConverter, SetFieldDefaultSupplier, SupplierMongoRepo> {

    @Autowired
    private ISupplierRepositoryTemplate supplierRepositoryTemplate;

    @Autowired
    public SupplierRepo(SupplierMongoRepo springRepo, SupplierConverter converter, SetFieldDefaultSupplier setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> SUPPLIER_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<SupplierDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (SUPPLIER_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = SUPPLIER_ENTITY_FIELDS.get(key);
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
        query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_ADMINISTRATIVE.getName(),
                        TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName(),
                        TypeInheritanceEnum.SUPPLIER_GUIDE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGING.getName(),
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName(),
                        TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName(),
                        TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName(),
                        TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName(),
                        TypeInheritanceEnum.SUPPLIER_GLAMPING.getName()
                )
        ));

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

        return supplierRepositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<SupplierDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (SUPPLIER_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = SUPPLIER_ENTITY_FIELDS.get(key);
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
        query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_ADMINISTRATIVE.getName(),
                        TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName(),
                        TypeInheritanceEnum.SUPPLIER_GUIDE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGING.getName(),
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName(),
                        TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName(),
                        TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName(),
                        TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName(),
                        TypeInheritanceEnum.SUPPLIER_GLAMPING.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Pageable pageConfig = PageRequest.of(
                searchDomain.getPage(),
                searchDomain.getSize(),
                Sort.by(order)
        );

        return supplierRepositoryTemplate.query(pageConfig, query);
    }

    public List<SupplierDomain> findAll(Boolean deleted){
        SupplierEntity example = new SupplierEntity();
        example.setDeleted(false);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<SupplierDomain> suppliers = this.findAllWithExample(
                Example.of(example)
        );
        return suppliers;
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_ADMINISTRATIVE.getName(),
                        TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName(),
                        TypeInheritanceEnum.SUPPLIER_GUIDE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGING.getName(),
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName(),
                        TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName(),
                        TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName(),
                        TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName(),
                        TypeInheritanceEnum.SUPPLIER_GLAMPING.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return supplierRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_ADMINISTRATIVE.getName(),
                        TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName(),
                        TypeInheritanceEnum.SUPPLIER_GUIDE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGING.getName(),
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName(),
                        TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName(),
                        TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName(),
                        TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName(),
                        TypeInheritanceEnum.SUPPLIER_GLAMPING.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return supplierRepositoryTemplate.queryAllSimple(query);
    }

    // Statistics

    public Long countSupplier(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_ADMINISTRATIVE.getName(),
                        TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName(),
                        TypeInheritanceEnum.SUPPLIER_GUIDE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGING.getName(),
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName(),
                        TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName(),
                        TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName(),
                        TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName(),
                        TypeInheritanceEnum.SUPPLIER_GLAMPING.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return supplierRepositoryTemplate.countSupplier(query);
    }

    public List<GroupedDataDomain> aggregateSupplierByType(){
        return supplierRepositoryTemplate.aggregateSupplierByType("supplierType");
    }

    public List<SupplierDomain> searchAllSimpleDomainCaseToolUpdateSummary() {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "summary");

        return supplierRepositoryTemplate.queryAllSimpleDomainCaseToolUpdateSummary(query);
    }

    public void updateSummaryByIdSimpleDomainCaseUpdateToolSummary(String id, String summary){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("summary", summary);

        supplierRepositoryTemplate.updateIdSimpleDomainCaseUpdateToolSummary(query, update);
    }

    public SupplierDomain findByIdEntityWithDepartmentRegion(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Supplier
        query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_ADMINISTRATIVE.getName(),
                        TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName(),
                        TypeInheritanceEnum.SUPPLIER_GUIDE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGE.getName(),
                        TypeInheritanceEnum.SUPPLIER_LODGING.getName(),
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName(),
                        TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName(),
                        TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName(),
                        TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName(),
                        TypeInheritanceEnum.SUPPLIER_GLAMPING.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "partner");

        return supplierRepositoryTemplate.queryOneIdWithDepartmentRegion(query);
    }

    public List<SupplierDomain> findAllSimpleForServiceOperation(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Supplier
        /*query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_LODGE.getName(),
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName(),
                        TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName()
                )
        ));*/

        query.addCriteria(Criteria.where("typeInheritance").in(
                List.of(
                        TypeInheritanceEnum.SUPPLIER_OPERATOR.getName()
                )
        ));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "supplierType");

        return supplierRepositoryTemplate.queryAllSimpleForServiceOperation(query);
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
        return supplierRepositoryTemplate.queryOneId(query) != null;
    }

}
