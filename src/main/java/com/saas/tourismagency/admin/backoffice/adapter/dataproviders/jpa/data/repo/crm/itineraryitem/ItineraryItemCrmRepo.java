package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itineraryitem.ItineraryItemCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultItineraryItemCrm;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm.ItineraryItemCrmMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ItineraryItemCrmRepo extends DelegatedSpringJpaRepo<ItineraryItemDomain, ItineraryItemEntity, String, ItineraryItemCrmConverter, SetFieldDefaultItineraryItemCrm, ItineraryItemCrmMongoRepo> {

    @Autowired
    private IItineraryItemCrmRepositoryTemplate itineraryItemRepositoryTemplate;

    @Autowired
    public ItineraryItemCrmRepo(ItineraryItemCrmMongoRepo springRepo, ItineraryItemCrmConverter converter, SetFieldDefaultItineraryItemCrm setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> ITINERARY_ITEM_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<ItineraryItemDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ITINERARY_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ITINERARY_ITEM_ENTITY_FIELDS.get(key);
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

        return itineraryItemRepositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<ItineraryItemDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ITINERARY_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ITINERARY_ITEM_ENTITY_FIELDS.get(key);
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

        return itineraryItemRepositoryTemplate.query(pageConfig, query);
    }

    public List<ItineraryItemDomain> findAll(Boolean deleted){
        ItineraryItemEntity example = new ItineraryItemEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<ItineraryItemDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<ItineraryItemDomain> searchAllByItineraryDate(String idItineraryDate) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return itineraryItemRepositoryTemplate.query(query);
    }

    public List<ItineraryItemDomain> searchAllByQuotation(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return itineraryItemRepositoryTemplate.query(query);
    }

    public ItineraryItemDomain searchAllByQuotationAndItineraryItem(String idQuotation, String idItineraryItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idItineraryItem)) {
            query.addCriteria(Criteria.where("id").is(idItineraryItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> result = itineraryItemRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndQuotation(String idItineraryDate, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return itineraryItemRepositoryTemplate.query(query);
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return itineraryItemRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return itineraryItemRepositoryTemplate.queryAllSimple(query);
    }

    public List<SimpleBaseDomain> searchAllByQuotationSimple(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryAllSimple(query);
    }

    public List<ItineraryItemDomain> searchAllByQuotationSimpleDomain(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomain(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndQuotationSimpleDomainCaseShowItinerary(String idItineraryDate, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDateTime", "supplierType", "supplier", "service", "paxTotal", "paxAdultTotal", "paxChildrenTotal", "paxTcTotal", "paxTcLocalTotal", "paxGuideTotal", "price", "priceAdult", "priceChildren", "priceTc", "priceTcLocal", "priceGuide", "observation", "tour");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseShowItinerary(query);
    }

    public ItineraryItemDomain findByIdSimpleDomainCaseUpdateOrderItinerary(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryOneIdSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public void updateIndexByIdSimpleDomainCaseUpdateOrderItinerary(String id, Integer index){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("index", index);

        itineraryItemRepositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItinerary(query, update);
    }

    public void updateIndexItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(String id, Integer index, String itineraryDate, LocalDateTime itineraryDateTime){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("index", index);
        update.set("itineraryDateTime", itineraryDateTime);
        ObjectId objectIdItineraryDate = new ObjectId(itineraryDate);
        update.set("itineraryDate", objectIdItineraryDate);

        itineraryItemRepositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItinerary(query, update);
    }

    public void updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(String id, LocalDateTime itineraryDateTime){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("itineraryDateTime", itineraryDateTime);

        itineraryItemRepositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItinerary(query, update);
    }

    public List<ItineraryItemDomain> searchAllByQuotationSimpleDomainCaseUpdateOrderItinerary(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(String idItineraryDate, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public List<ItineraryItemDomain> searchAllByQuotationSimpleDomainCaseGetCalculateCost(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "price", "priceAdult", "priceChildren", "priceTc", "priceTcLocal", "priceGuide", "paxTotal", "paxAdultTotal", "paxChildrenTotal", "paxTcTotal", "paxTcLocalTotal", "paxGuideTotal", "service");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseGetCalculateCost(query);
    }

    public List<ItineraryItemDomain> searchAllByQuotationSimpleDomainCaseGetCollectionForLastIndex(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseGetCollectionForLastIndex(query);
    }

    public ItineraryItemDomain findByIdSimpleDomainCaseGeneric(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryOneIdSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public List<ItineraryItemDomain> searchAllByQuotationAndItineraryDateSimpleDomainCaseDeleteTours(String idQuotation, String idItineraryDate, String idTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idTour)) {
            query.addCriteria(Criteria.where("tour.id").is(idTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseDeleteTours(query);
    }

    public List<ItineraryItemDomain> searchAllByQuotationAndItineraryDateCostTourSimpleDomainCaseDeleteTours(String idQuotation, String idItineraryDate, String idTour, String idCostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idTour)) {
            query.addCriteria(Criteria.where("tour.id").is(idTour));
        }

        if (StringUtils.isNotEmpty(idCostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idCostTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseDeleteTours(query);
    }

    public ItineraryItemDomain searchAllByQuotationAndCostTour(String idQuotation, String idICostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idICostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idICostTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> result = itineraryItemRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public ItineraryItemDomain searchAllByQuotationAndCostTourAndCostTourServiceItem(String idQuotation, String idICostTour, String idICostTourServiceItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        if (StringUtils.isNotEmpty(idICostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idICostTour));
        }

        if (StringUtils.isNotEmpty(idICostTourServiceItem)) {
            query.addCriteria(Criteria.where("costTourServiceItem.id").is(idICostTourServiceItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> result = itineraryItemRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public List<ItineraryItemDomain> searchAllByQuotationSimpleDomainCaseDelete(String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseDeleteByQuotation(query);
    }

    public List<ItineraryItemDomain> searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(String idItineraryDate, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "name",
                "description",
                "identify",
                "serviceGroup",
                "observation",
                "itineraryDateTime",
                "paxTotal",
                "price",
                "paxAdultTotal",
                "priceAdult",
                "paxChildrenTotal",
                "priceChildren",
                "paxTcTotal",
                "priceTc",
                "paxTcLocalTotal",
                "priceTcLocal",
                "paxGuideTotal",
                "priceGuide",
                "itineraryDate",
                "index",
                "supplierType",
                "supplier",
                "service",
                "departmentRegion",
                "tour",
                "lodgingCantSwb",
                "lodgingCantMat",
                "lodgingCantTwin",
                "lodgingCantTrp",
                "lodgingCategory",
                "lodgingCantBreakfast",
                "lodgingCantLunch",
                "lodgingCantDinner",
                "lodgingCantBoxLunch",
                "lodgingCantNight",
                "lodgingCantBreakfastTcGuide",
                "lodgingCantLunchTcGuide",
                "lodgingCantDinnerTcGuide",
                "lodgingCantBoxLunchTcGuide",
                "lodgingCantSwbTcGuide",
                "lodgingCantTwinTcGuide",
                "lodgingTcCantNight",
                "lodgingCantBreakfastTcLocalGuide",
                "lodgingCantLunchTcLocalGuide",
                "lodgingCantDinnerTcLocalGuide",
                "lodgingCantBoxLunchTcLocalGuide",
                "lodgingCantSwbTcLocalGuide",
                "lodgingCantTwinTcLocalGuide",
                "lodgingTcLocalCantNight",
                "lodgingCantBreakfastGuide",
                "lodgingCantLunchGuide",
                "lodgingCantDinnerGuide",
                "lodgingCantBoxLunchGuide",
                "lodgingCantSwbGuide",
                "lodgingCantTwinGuide",
                "lodgingGuideCantNight",
                "lodgeCantSwb",
                "lodgeCantMat",
                "lodgeCantTwin",
                "lodgeCantTrp",
                "lodgeCategory",
                "lodgeCantBreakfast",
                "lodgeCantLunch",
                "lodgeCantDinner",
                "lodgeCantBoxLunch",
                "lodgeCantNight",
                "lodgeCantBreakfastTcGuide",
                "lodgeCantLunchTcGuide",
                "lodgeCantDinnerTcGuide",
                "lodgeCantBoxLunchTcGuide",
                "lodgeCantSwbTcGuide",
                "lodgeCantTwinTcGuide",
                "lodgeTcCantNight",
                "lodgeCantBreakfastTcLocalGuide",
                "lodgeCantLunchTcLocalGuide",
                "lodgeCantDinnerTcLocalGuide",
                "lodgeCantBoxLunchTcLocalGuide",
                "lodgeCantSwbTcLocalGuide",
                "lodgeCantTwinTcLocalGuide",
                "lodgeTcLocalCantNight",
                "lodgeCantBreakfastGuide",
                "lodgeCantLunchGuide",
                "lodgeCantDinnerGuide",
                "lodgeCantBoxLunchGuide",
                "lodgeCantSwbGuide",
                "lodgeCantTwinGuide",
                "lodgeGuideCantNight",
                "lodgePvt",
                "lodgeSic",
                "restaurantCantBreakfast",
                "restaurantCantLunch",
                "restaurantCantDinner",
                "restaurantCantBoxLunch",
                "restaurantCantBreakfastTcGuide",
                "restaurantCantLunchTcGuide",
                "restaurantCantDinnerTcGuide",
                "restaurantCantBoxLunchTcGuide",
                "restaurantCantBreakfastTcLocalGuide",
                "restaurantCantLunchTcLocalGuide",
                "restaurantCantDinnerTcLocalGuide",
                "restaurantCantBoxLunchTcLocalGuide",
                "restaurantCantBreakfastGuide",
                "restaurantCantLunchGuide",
                "restaurantCantDinnerGuide",
                "restaurantCantBoxLunchGuide",
                "restaurantCantBreakfastDrive",
                "restaurantCantLunchDrive",
                "restaurantCantDinnerDrive",
                "restaurantCantBoxLunchDrive",
                "operatorPvt",
                "operatorSic",
                "lodgingForOperator",
                "lodgingForOperatorStr"
        );

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseResendQuotationToBooking(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndQuotationSimpleDomainCaseChangeStateOpened(String idItineraryDate, String idQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idQuotation)) {
            query.addCriteria(Criteria.where("quotation.id").is(idQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "name",
                "description",
                "identify",
                "serviceGroup",
                "observation",
                "itineraryDateTime",
                "paxTotal",
                "price",
                "paxAdultTotal",
                "priceAdult",
                "paxChildrenTotal",
                "priceChildren",
                "paxTcTotal",
                "priceTc",
                "paxTcLocalTotal",
                "priceTcLocal",
                "paxGuideTotal",
                "priceGuide",
                "itineraryDate",
                "index",
                "supplierType",
                "supplier",
                "service",
                "departmentRegion",
                "tour",
                "lodgingCantSwb",
                "lodgingCantMat",
                "lodgingCantTwin",
                "lodgingCantTrp",
                "lodgingCategory",
                "lodgingCantBreakfast",
                "lodgingCantLunch",
                "lodgingCantDinner",
                "lodgingCantBoxLunch",
                "lodgingCantNight",
                "lodgingCantBreakfastTcGuide",
                "lodgingCantLunchTcGuide",
                "lodgingCantDinnerTcGuide",
                "lodgingCantBoxLunchTcGuide",
                "lodgingCantSwbTcGuide",
                "lodgingCantTwinTcGuide",
                "lodgingTcCantNight",
                "lodgingCantBreakfastTcLocalGuide",
                "lodgingCantLunchTcLocalGuide",
                "lodgingCantDinnerTcLocalGuide",
                "lodgingCantBoxLunchTcLocalGuide",
                "lodgingCantSwbTcLocalGuide",
                "lodgingCantTwinTcLocalGuide",
                "lodgingTcLocalCantNight",
                "lodgingCantBreakfastGuide",
                "lodgingCantLunchGuide",
                "lodgingCantDinnerGuide",
                "lodgingCantBoxLunchGuide",
                "lodgingCantSwbGuide",
                "lodgingCantTwinGuide",
                "lodgingGuideCantNight",
                "lodgeCantSwb",
                "lodgeCantMat",
                "lodgeCantTwin",
                "lodgeCantTrp",
                "lodgeCategory",
                "lodgeCantBreakfast",
                "lodgeCantLunch",
                "lodgeCantDinner",
                "lodgeCantBoxLunch",
                "lodgeCantNight",
                "lodgeCantBreakfastTcGuide",
                "lodgeCantLunchTcGuide",
                "lodgeCantDinnerTcGuide",
                "lodgeCantBoxLunchTcGuide",
                "lodgeCantSwbTcGuide",
                "lodgeCantTwinTcGuide",
                "lodgeTcCantNight",
                "lodgeCantBreakfastTcLocalGuide",
                "lodgeCantLunchTcLocalGuide",
                "lodgeCantDinnerTcLocalGuide",
                "lodgeCantBoxLunchTcLocalGuide",
                "lodgeCantSwbTcLocalGuide",
                "lodgeCantTwinTcLocalGuide",
                "lodgeTcLocalCantNight",
                "lodgeCantBreakfastGuide",
                "lodgeCantLunchGuide",
                "lodgeCantDinnerGuide",
                "lodgeCantBoxLunchGuide",
                "lodgeCantSwbGuide",
                "lodgeCantTwinGuide",
                "lodgeGuideCantNight",
                "lodgePvt",
                "lodgeSic",
                "restaurantCantBreakfast",
                "restaurantCantLunch",
                "restaurantCantDinner",
                "restaurantCantBoxLunch",
                "restaurantCantBreakfastTcGuide",
                "restaurantCantLunchTcGuide",
                "restaurantCantDinnerTcGuide",
                "restaurantCantBoxLunchTcGuide",
                "restaurantCantBreakfastTcLocalGuide",
                "restaurantCantLunchTcLocalGuide",
                "restaurantCantDinnerTcLocalGuide",
                "restaurantCantBoxLunchTcLocalGuide",
                "restaurantCantBreakfastGuide",
                "restaurantCantLunchGuide",
                "restaurantCantDinnerGuide",
                "restaurantCantBoxLunchGuide",
                "restaurantCantBreakfastDrive",
                "restaurantCantLunchDrive",
                "restaurantCantDinnerDrive",
                "restaurantCantBoxLunchDrive",
                "operatorPvt",
                "operatorSic",
                "lodgingForOperator",
                "lodgingForOperatorStr"
        );

        return itineraryItemRepositoryTemplate.queryAllSimpleDomainCaseChangeStateOpened(query);
    }
}
