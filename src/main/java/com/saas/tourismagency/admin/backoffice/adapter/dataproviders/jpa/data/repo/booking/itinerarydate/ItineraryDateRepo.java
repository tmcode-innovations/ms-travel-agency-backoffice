package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itinerarydate.ItineraryDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultItineraryDate;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryDateEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking.ItineraryDateMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ItineraryDateRepo extends DelegatedSpringJpaRepo<ItineraryDateDomain, ItineraryDateEntity, String, ItineraryDateConverter, SetFieldDefaultItineraryDate, ItineraryDateMongoRepo> {

    @Autowired
    private IItineraryDateRepositoryTemplate repositoryTemplate;

    @Autowired
    public ItineraryDateRepo(ItineraryDateMongoRepo springRepo, ItineraryDateConverter converter, SetFieldDefaultItineraryDate setFieldDefault) {
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

        return repositoryTemplate.query(pageConfig, query);
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

        return repositoryTemplate.query(pageConfig, query);
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

    public List<ItineraryDateDomain> searchAllByRegisterGroup(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public ItineraryDateDomain searchAllByRegisterGroupAndItineraryDate(String idRegisterGroup, String idItineraryDate) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("id").is(idItineraryDate));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryDateDomain> list = repositoryTemplate.query(query);
        if (list != null && !list.isEmpty()){
            return list.getFirst();
        }
        return null;
    }

    public ItineraryDateDomain findByIdItineraryDateQuotation(String idItineraryDateQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDateQuotation)) {
            query.addCriteria(Criteria.where("idItineraryDateQuotation").is(idItineraryDateQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryDateDomain> list = repositoryTemplate.query(query);
        if (list != null && !list.isEmpty()){
            return list.getFirst();
        }
        return null;
    }

    public ItineraryDateDomain getByRegisterGroupAndItemItinerary(String idRegisterGroup, String itemItinerary) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(itemItinerary)) {
            query.addCriteria(Criteria.where("itemItinerary").is(itemItinerary));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryDateDomain> list = repositoryTemplate.query(query);
        if (list != null && !list.isEmpty()){
            return list.getFirst();
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

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseShowItinerary(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itemItinerary", "itineraryDate", "tours");

        return repositoryTemplate.queryAllSimpleDomainCaseShowItinerary(query);
    }

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return repositoryTemplate.queryAllSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseUpdateOrderItinerary(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return repositoryTemplate.queryOneIdSimpleDomainCaseUpdateOrderItinerary(query);
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

        repositoryTemplate.updateByIdSimpleDomainCaseUpdateOrderItinerary(query, update);
    }

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index");

        return repositoryTemplate.queryAllSimpleDomainCaseGenerateBooking(query);
    }

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseGenerateBookingAction(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itemItinerary");

        return repositoryTemplate.queryAllSimpleDomainCaseGenerateBookingAction(query);
    }

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseDeleteItineraryDate(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return repositoryTemplate.queryAllSimpleDomainCaseDeleteItineraryDate(query);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseDeleteItineraryDate(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return repositoryTemplate.queryOneIdSimpleDomainCaseDeleteItineraryDate(query);
    }

    public ItineraryDateDomain findByItemItinerarySimpleDomainCaseGeneric(String itemItinerary){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("itemItinerary").is(itemItinerary));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return repositoryTemplate.queryOneIdSimpleDomainCaseDeleteItineraryDate(query);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseDeleteTour(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary", "tours");

        return repositoryTemplate.queryOneIdSimpleDomainCaseDeleteTour(query);
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

        repositoryTemplate.updateToursByIdSimpleDomainCaseDeleteTour(query, update);
    }

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseDeleteTour(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary", "tours");

        return repositoryTemplate.queryAllSimpleDomainCaseDeleteTour(query);
    }

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseDelete(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryAllSimpleDomainCaseDeleteByRegisterGroup(query);
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

        repositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItineraryDate(query, update);
    }

    public List<ItineraryDateDomain> searchAllByRegisterGroupSimpleDomainCaseProgram(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate");

        return repositoryTemplate.queryAllSimpleDomainCaseProgramByRegisterGroup(query);
    }

    public ItineraryDateDomain findByIdSimpleDomainCaseCommonBasicData(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "index", "itineraryDate", "itemItinerary");

        return repositoryTemplate.queryOneIdSimpleDomainCaseCommonBasicData(query);
    }

    public void updateIdItineraryDateQuotationSimpleDomainCaseResend(String id, String idItineraryDateQuotation){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("idItineraryDateQuotation", idItineraryDateQuotation);

        repositoryTemplate.updateIdSimpleDomainCaseUpdateIdItineraryDateQuotation(query, update);
    }

}
