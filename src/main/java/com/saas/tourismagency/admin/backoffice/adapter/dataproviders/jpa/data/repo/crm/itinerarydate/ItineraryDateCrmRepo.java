package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itinerarydate.ItineraryDateCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultItineraryDateCrm;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm.ItineraryDateCrmMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
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
public class ItineraryDateCrmRepo extends DelegatedSpringJpaRepo<ItineraryDateDomain, ItineraryDateEntity, String, ItineraryDateCrmConverter, SetFieldDefaultItineraryDateCrm, ItineraryDateCrmMongoRepo> {

    @Autowired
    private IItineraryDateCrmRepositoryTemplate itineraryDateRepositoryTemplate;

    @Autowired
    public ItineraryDateCrmRepo(ItineraryDateCrmMongoRepo springRepo, ItineraryDateCrmConverter converter, SetFieldDefaultItineraryDateCrm setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> ITINERARY_DATE_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<ItineraryDateDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ITINERARY_DATE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ITINERARY_DATE_ENTITY_FIELDS.get(key);
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

        return itineraryDateRepositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<ItineraryDateDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ITINERARY_DATE_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ITINERARY_DATE_ENTITY_FIELDS.get(key);
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

        return itineraryDateRepositoryTemplate.query(pageConfig, query);
    }

    public List<ItineraryDateDomain> findAll(Boolean deleted){
        ItineraryDateEntity example = new ItineraryDateEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<ItineraryDateDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<ItineraryDateDomain> searchAllByQuotation(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return itineraryDateRepositoryTemplate.query(query);
    }

    public ItineraryDateDomain searchAllByQuotationAndItineraryDate(String idQuotation, String idItineraryDate) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("id").is(idItineraryDate));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryDateDomain> result = itineraryDateRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public ItineraryDateDomain getByQuotationAndItemItinerary(String idQuotation, String itemItinerary) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(itemItinerary)) {
            query.addCriteria(Criteria.where("itemItinerary").is(itemItinerary));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryDateDomain> result = itineraryDateRepositoryTemplate.query(query);
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

        return itineraryDateRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return itineraryDateRepositoryTemplate.queryAllSimple(query);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseShowItinerary(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itemItinerary", "itineraryDate", "tours");

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseShowItinerary(query);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseUpdateOrderItinerary(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseUpdateOrderItinerary(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return itineraryDateRepositoryTemplate.queryOneIdSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public void updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(String id, Integer index, String itemItinerary, LocalDate itineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("index", index);
        update.set("itineraryDate", itineraryDate);
        update.set("itemItinerary", itemItinerary);

        itineraryDateRepositoryTemplate.updateByIdSimpleDomainCaseUpdateOrderItinerary(query, update);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseDeleteItineraryDate(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return itineraryDateRepositoryTemplate.queryOneIdSimpleDomainCaseDeleteItineraryDate(query);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseDeleteItineraryDate(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseDeleteItineraryDate(query);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseGeneric(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return itineraryDateRepositoryTemplate.queryOneIdSimpleDomainCaseGeneric(query);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseGeneric(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseGeneric(query);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseDeleteTour(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary", "tours");

        return itineraryDateRepositoryTemplate.queryOneIdSimpleDomainCaseDeleteTour(query);
    }

    public void updateToursByIdSimpleDomainCaseDeleteTour(String id, Set<TourEntity> tours){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("tours", tours);

        itineraryDateRepositoryTemplate.updateToursByIdSimpleDomainCaseDeleteTour(query, update);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseDeleteTour(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary", "tours");

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseDeleteTour(query);
    }

    public void updateIndexByIdSimpleDomainCaseUpdateOrderItineraryDate(String id, Integer index){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("index", index);
        update.set("itemItinerary", index.toString());

        itineraryDateRepositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItineraryDate(query, update);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseDelete(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseDeleteByQuotation(query);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(String idQuotation) {
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
                "index",
                "itemItinerary",
                "tours"
        );

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseResendQuotationToBooking(query);
    }

    public List<ItineraryDateDomain> searchAllByQuotationSimpleDomainCaseChangeStateOpened(String idQuotation) {
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
                "index",
                "itemItinerary",
                "tours"
        );

        return itineraryDateRepositoryTemplate.queryAllSimpleDomainCaseChangeStateOpened(query);
    }
}
