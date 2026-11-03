package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtourserviceitem.CostTourServiceItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultCostTourServiceItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourServiceItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm.CostTourServiceItemMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain;
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
public class CostTourServiceItemRepo extends DelegatedSpringJpaRepo<CostTourServiceItemDomain, CostTourServiceItemEntity, String, CostTourServiceItemConverter, SetFieldDefaultCostTourServiceItem, CostTourServiceItemMongoRepo> {

    @Autowired
    private CostTourServiceItemRepositoryTemplate costTourServiceItemRepositoryTemplate;

    @Autowired
    public CostTourServiceItemRepo(CostTourServiceItemMongoRepo springRepo, CostTourServiceItemConverter converter, SetFieldDefaultCostTourServiceItem setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> COST_TOUR_SERVICE_ITEM_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<CostTourServiceItemDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COST_TOUR_SERVICE_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COST_TOUR_SERVICE_ITEM_ENTITY_FIELDS.get(key);
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

        return costTourServiceItemRepositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<CostTourServiceItemDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COST_TOUR_SERVICE_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COST_TOUR_SERVICE_ITEM_ENTITY_FIELDS.get(key);
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

        return costTourServiceItemRepositoryTemplate.query(pageConfig, query);
    }

    public List<CostTourServiceItemDomain> findAll(Boolean deleted){
        CostTourServiceItemEntity example = new CostTourServiceItemEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<CostTourServiceItemDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<CostTourServiceItemDomain> searchAllByCostTour(String idCostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idCostTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return costTourServiceItemRepositoryTemplate.query(query);
    }

    public List<CostTourServiceItemDomain> searchAllByQuotation(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return costTourServiceItemRepositoryTemplate.query(query);
    }

    public CostTourServiceItemDomain searchAllByQuotationAndCostTourServiceItem(String idQuotation, String idCostTourServiceItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idCostTourServiceItem)) {
            query.addCriteria(Criteria.where("id").is(idCostTourServiceItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<CostTourServiceItemDomain> result = costTourServiceItemRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public CostTourServiceItemDomain searchAllByQuotationAndCostTourAndCostTourServiceItem(String idQuotation, String idCostTour, String idCostTourServiceItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idCostTour));
        }

        if (StringUtils.isNotEmpty(idCostTourServiceItem)) {
            query.addCriteria(Criteria.where("id").is(idCostTourServiceItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<CostTourServiceItemDomain> result = costTourServiceItemRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public List<CostTourServiceItemDomain> searchAllByCostTourAndQuotation(String idCostTour, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idCostTour));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return costTourServiceItemRepositoryTemplate.query(query);
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return costTourServiceItemRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return costTourServiceItemRepositoryTemplate.queryAllSimple(query);
    }

    public List<CostTourServiceItemDomain> searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourServiceItem(String idQuotation, String idCostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idCostTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "observation", "service", "supplier", "itineraryDate");

        return costTourServiceItemRepositoryTemplate.queryAllSimpleDomainCaseShowCostTourServiceItem(query);
    }

    public List<CostTourServiceItemDomain> searchAllByQuotationSimpleDomainCaseDelete(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return costTourServiceItemRepositoryTemplate.queryAllSimpleDomainCaseDeleteByQuotation(query);
    }

    public List<CostTourServiceItemDomain> searchAllByCostTourAndQuotationCaseResendQuotationToBooking(String idCostTour, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idCostTour));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "name",
                "description",
                "identify",
                "itineraryDate",
                "supplierType",
                "supplier",
                "service",
                "observation"
        );

        return costTourServiceItemRepositoryTemplate.queryAllSimpleDomainCaseResendQuotationToBookingByCostTourAndQuotation(query);
    }

    public List<CostTourServiceItemDomain> searchAllByCostTourAndQuotationCaseChangeStateOpened(String idCostTour, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idCostTour));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "name",
                "description",
                "identify",
                "itineraryDate",
                "supplierType",
                "supplier",
                "service",
                "observation"
        );

        return costTourServiceItemRepositoryTemplate.queryAllSimpleDomainCaseChangeStateOpenedByCostTourAndQuotation(query);
    }
}
