package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.costtour.CostTourConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultCostTour;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.CostTourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm.CostTourMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
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
public class CostTourRepo extends DelegatedSpringJpaRepo<CostTourDomain, CostTourEntity, String, CostTourConverter, SetFieldDefaultCostTour, CostTourMongoRepo> {

    @Autowired
    private CostTourRepositoryTemplate costTourRepositoryTemplate;

    @Autowired
    public CostTourRepo(CostTourMongoRepo springRepo, CostTourConverter converter, SetFieldDefaultCostTour setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> COST_TOUR_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<CostTourDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COST_TOUR_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COST_TOUR_ENTITY_FIELDS.get(key);
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

        return costTourRepositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<CostTourDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COST_TOUR_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COST_TOUR_ENTITY_FIELDS.get(key);
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

        return costTourRepositoryTemplate.query(pageConfig, query);
    }

    public List<CostTourDomain> findAll(Boolean deleted){
        CostTourEntity example = new CostTourEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<CostTourDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<CostTourDomain> searchAllByQuotation(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return costTourRepositoryTemplate.query(query);
    }

    public CostTourDomain searchAllByQuotationAndCostTour(String idQuotation, String idCostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("id").is(idCostTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<CostTourDomain> result = costTourRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
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

        return costTourRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return costTourRepositoryTemplate.queryAllSimple(query);
    }

    public List<CostTourDomain> searchAllByQuotationSimpleDomainCaseShowCostTour(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "tour", "itineraryDate", "numMaxPax", "subTotalAmount");

        return costTourRepositoryTemplate.queryAllSimpleDomainCaseShowCostTour(query);
    }

    public void updateIdSimpleDomainCaseUpdateSubTotalAmount(String id, Double subTotalAmount){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("subTotalAmount", subTotalAmount);

        costTourRepositoryTemplate.updateIdSimpleDomainCaseUpdateSubTotalAmount(query, update);
    }

    public List<CostTourDomain> searchAllByQuotationAndTourSimpleDomainCaseShowCostTour(String idQuotation, String idTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idTour)) {
            query.addCriteria(Criteria.where("tour.id").is(idTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "tour", "itineraryDate", "numMaxPax", "subTotalAmount");

        return costTourRepositoryTemplate.queryAllSimpleDomainCaseShowCostTour(query);
    }

    public List<CostTourDomain> searchAllByQuotationAndItineraryDateSimpleDomainCaseShowCostTour(String idQuotation, String idItineraryDate) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "tour", "itineraryDate", "numMaxPax", "subTotalAmount");

        return costTourRepositoryTemplate.queryAllSimpleDomainCaseShowCostTour(query);
    }

    public List<CostTourDomain> searchAllByQuotationSimpleDomainCaseDelete(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return costTourRepositoryTemplate.queryAllSimpleDomainCaseDeleteByQuotation(query);
    }

    public List<CostTourDomain> searchAllByQuotationAndItineraryDateSimpleDomainCaseDelete(String idQuotation, String idItineraryDate) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return costTourRepositoryTemplate.queryAllSimpleDomainCaseDeleteByQuotationAndItineraryDate(query);
    }

    public List<CostTourDomain> searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

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
                "numMaxPax",
                "subTotalAmount",
                "tour"
        );

        return costTourRepositoryTemplate.queryAllSimpleDomainCaseResendQuotationToBooking(query);
    }

    public List<CostTourDomain> searchAllByQuotationSimpleDomainCaseChangeStateOpened(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

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
                "numMaxPax",
                "subTotalAmount",
                "tour"
        );

        return costTourRepositoryTemplate.queryAllSimpleDomainCaseChangeStateOpened(query);
    }
}
