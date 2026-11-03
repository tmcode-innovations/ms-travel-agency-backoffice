package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem.ItineraryItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.IItineraryItemRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupGuideTransferDriveDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ItineraryItemRepositoryTemplateImpl implements IItineraryItemRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private ItineraryItemConverter converter;

    @Override
    public PageDTO<ItineraryItemDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ItineraryItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ItineraryItemEntity> results = mongoTemplate.find(query, ItineraryItemEntity.class);
        Page<ItineraryItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<ItineraryItemDomain> query(Query query) {
        List<ItineraryItemEntity> itineraryItems = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAll(itineraryItems);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        ItineraryItemEntity entity = mongoTemplate.findOne(query, ItineraryItemEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimple(items);
    }

    @Override
    public List<RegisterGroupGuideTransferDriveDomain> queryAllGuideTransferDriveRegisterGroup(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return items.stream()
                .filter(item -> item.getGuideTransferDrive() != null
                        && item.getGuideTransferDrive().getId() != null
                        && !item.getGuideTransferDrive().getId().isEmpty()
                        && item.getRegisterGroup() != null
                        && item.getRegisterGroup().getId() != null
                        && !item.getRegisterGroup().getId().isEmpty())
                .map(item -> new RegisterGroupGuideTransferDriveDomain(
                        item.getGuideTransferDrive().getId(),
                        item.getRegisterGroup().getId()))
                .toList();
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomain(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomain(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseShowItinerary(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseShowItinerary(items);
    }

    @Override
    public ItineraryItemDomain queryOneIdSimpleDomainCaseUpdateOrderItinerary(Query query) {
        ItineraryItemEntity entity = mongoTemplate.findOne(query, ItineraryItemEntity.class);
        return converter.toDomainIdSimpleDomainCaseUpdateOrderItinerary(entity);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateOrderItinerary(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseUpdateOrderItinerary(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseUpdateOrderItinerary(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGenerateBooking(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGenerateBooking(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGenerateBookingAction(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGenerateBookingAction(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseGenerateBookingActionHotel(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGenerateBookingActionHotel(items);
    }

    @Override
    public PageDTO<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControl(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ItineraryItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ItineraryItemEntity> results = mongoTemplate.find(query, ItineraryItemEntity.class);
        Page<ItineraryItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseTransferControl(resultsWithPage);
    }

    @Override
    public PageDTO<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControlByTourBible(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, ItineraryItemEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<ItineraryItemEntity> results = mongoTemplate.find(query, ItineraryItemEntity.class);
        Page<ItineraryItemEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPageCaseTransferControlByTourBible(resultsWithPage);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControlExport(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseTransferControlExport(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseValidationExistBooking(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseValidationExistBooking(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteTours(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteTours(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseTransferControlByTourBibleExport(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainPageCaseTransferControlByTourBibleExport(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseToolUpdateCustomer(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseToolUpdateCustomer(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateToolCustomer(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseServiceRequest(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseServiceRequest(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateCantPax(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseVoucherEgressSupplier(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseVoucherEgressSupplier(items);
    }

    final Map<String, String> ITINERARY_ITEM_TRANSFER_CONTROL_BY_TOUR_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("idSupplier", "idSupplier"),
            Map.entry("idCustomer", "idCustomer"),
            Map.entry("nameTour", "nameTour"),
            Map.entry("itineraryDateTimeStart", "itineraryDateTime"),
            Map.entry("itineraryDateTimeEnd", "itineraryDateTime")
    );

    @Override
    public Page<ItineraryItemEntity> aggregatePageableTransferControlByTourBible(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idCustomer = null;
        String idSupplier = null;
        String nameTour = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "nameTour" -> {
                        nameTour = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationTour = Aggregation.lookup("crm_tours", "tour", "_id", "joinedDataTour");
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationItineraryDate = Aggregation.lookup("booking_itinerary_dates", "itineraryDate", "_id", "joinedDataItineraryDate");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(idSupplier)) {
            ObjectId objectIdSupplier = new ObjectId(idSupplier);
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(objectIdSupplier));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            ObjectId objectIdCustomer = new ObjectId(idCustomer);
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(objectIdCustomer));
        }
        if (StringUtils.isNotEmpty(nameTour)) {
            listCriteria.add(Criteria.where("joinedDataTour.name").regex(String.format(".*%s.*", nameTour),"i"));
        }

        listCriteria.add(Criteria.where("joinedDataRegisterGroup.stateRegisterGroup").ne(StateRegisterGroupEnum.ANNULLED.getName()));

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");
        SortOperation sortOperation = Aggregation.sort(pageable.getSort());

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                sortOperation,
                lookupOperationSupplier,
                lookupOperationCustomer,
                lookupOperationTour,
                lookupOperationRegisterGroup,
                lookupOperationItineraryDate,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult.class
        );

        ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public List<ItineraryItemEntity> aggregateTransferControlByTourBible(ISearchFilterPageDomain searchFilterDomain) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idCustomer = null;
        String idSupplier = null;
        String nameTour = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "nameTour" -> {
                        nameTour = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationTour = Aggregation.lookup("crm_tours", "tour", "_id", "joinedDataTour");
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationItineraryDate = Aggregation.lookup("booking_itinerary_dates", "itineraryDate", "_id", "joinedDataItineraryDate");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(idSupplier)) {
            ObjectId objectIdSupplier = new ObjectId(idSupplier);
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(objectIdSupplier));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            ObjectId objectIdCustomer = new ObjectId(idCustomer);
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(objectIdCustomer));
        }
        if (StringUtils.isNotEmpty(nameTour)) {
            listCriteria.add(Criteria.where("joinedDataTour.name").regex(String.format(".*%s.*", nameTour),"i"));
        }

        listCriteria.add(Criteria.where("joinedDataRegisterGroup.stateRegisterGroup").ne(StateRegisterGroupEnum.ANNULLED.getName()));

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationSupplier,
                lookupOperationCustomer,
                lookupOperationTour,
                lookupOperationRegisterGroup,
                lookupOperationItineraryDate,
                matchOperation
        );
        List<ItineraryItemEntity> result = mongoTemplate.aggregate(aggregation, "booking_itinerary_items", ItineraryItemEntity.class).getMappedResults();
        return result;
    }

    @Override
    public Page<ItineraryItemEntity> aggregatePageableTransferControlByServiceBible(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idCustomer = null;
        String idSupplier = null;
        String nameTour = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;
        String stateServiceInvoice = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "nameTour" -> {
                        nameTour = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
                    }
                    case "stateServiceInvoice" -> {
                        stateServiceInvoice = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationTour = Aggregation.lookup("crm_tours", "tour", "_id", "joinedDataTour");
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationItineraryDate = Aggregation.lookup("booking_itinerary_dates", "itineraryDate", "_id", "joinedDataItineraryDate");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(idSupplier)) {
            ObjectId objectIdSupplier = new ObjectId(idSupplier);
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(objectIdSupplier));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            ObjectId objectIdCustomer = new ObjectId(idCustomer);
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(objectIdCustomer));
        }
        if (StringUtils.isNotEmpty(nameTour)) {
            listCriteria.add(Criteria.where("joinedDataTour.name").regex(String.format(".*%s.*", nameTour),"i"));
        }
        if (StringUtils.isNotEmpty(stateServiceInvoice)) {
            listCriteria.add(Criteria.where("stateServiceInvoice").is(stateServiceInvoice));
        }

        listCriteria.add(Criteria.where("joinedDataRegisterGroup.stateRegisterGroup").ne(StateRegisterGroupEnum.ANNULLED.getName()));

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");
        SortOperation sortOperation = Aggregation.sort(pageable.getSort());

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                sortOperation,
                lookupOperationSupplier,
                lookupOperationCustomer,
                lookupOperationTour,
                lookupOperationRegisterGroup,
                lookupOperationItineraryDate,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult.class
        );

        ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public List<ItineraryItemEntity> aggregateTransferControlByServiceBible(ISearchFilterPageDomain searchFilterDomain) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idCustomer = null;
        String idSupplier = null;
        String nameTour = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;
        String stateServiceInvoice = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "nameTour" -> {
                        nameTour = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
                    }
                    case "stateServiceInvoice" -> {
                        stateServiceInvoice = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationTour = Aggregation.lookup("crm_tours", "tour", "_id", "joinedDataTour");
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationItineraryDate = Aggregation.lookup("booking_itinerary_dates", "itineraryDate", "_id", "joinedDataItineraryDate");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(idSupplier)) {
            ObjectId objectIdSupplier = new ObjectId(idSupplier);
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(objectIdSupplier));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            ObjectId objectIdCustomer = new ObjectId(idCustomer);
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(objectIdCustomer));
        }
        if (StringUtils.isNotEmpty(nameTour)) {
            listCriteria.add(Criteria.where("joinedDataTour.name").regex(String.format(".*%s.*", nameTour),"i"));
        }
        if (StringUtils.isNotEmpty(stateServiceInvoice)) {
            listCriteria.add(Criteria.where("stateServiceInvoice").is(stateServiceInvoice));
        }

        listCriteria.add(Criteria.where("joinedDataRegisterGroup.stateRegisterGroup").ne(StateRegisterGroupEnum.ANNULLED.getName()));

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationSupplier,
                lookupOperationCustomer,
                lookupOperationTour,
                lookupOperationRegisterGroup,
                lookupOperationItineraryDate,
                matchOperation
        );
        List<ItineraryItemEntity> result = mongoTemplate.aggregate(aggregation, "booking_itinerary_items", ItineraryItemEntity.class).getMappedResults();
        return result;
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateRegisterBookingItemOperator(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateRegisterBookingItemLodger(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseDeleteByRegisterGroup(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseDeleteByRegisterGroup(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseAdvancedByRegisterGroup(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseAdvancedByRegisterGroup(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllByRegisterGroupAndSupplierSimpleDomainCaseAnnulled(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseAnnulledByRegisterGroupAndSupplier(items);
    }

    @Override
    public void updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryAnnulled(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseExportItineraryByRegisterGroup(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseExportItineraryByRegisterGroup(items);
    }

    @Override
    public List<ItineraryItemDomain> queryAllByRegisterGroupSimpleDomainCaseProgram(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseProgramByRegisterGroup(items);
    }

    @Override
    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public Page<ItineraryItemEntity> aggregatePageableTransferControlBible(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, List<ObjectId> listSupplierType) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idSupplier = null;
        String idRegisterGroup = null;
        String idDepartmentRegion = null;
        String idCustomer = null;
        String idGuideTransport = null;
        String idGuideTransferDrive = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "idRegisterGroup" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "idDepartmentRegion" -> {
                        idDepartmentRegion = entry.getValue();
                    }
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idGuideTransport" -> {
                        idGuideTransport = entry.getValue();
                    }
                    case "idGuideTransferDrive" -> {
                        idGuideTransferDrive = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationItineraryDate = Aggregation.lookup("booking_itinerary_dates", "itineraryDate", "_id", "joinedDataItineraryDate");
        LookupOperation lookupOperationDepartmentRegion = Aggregation.lookup("base_department_regions", "departmentRegion", "_id", "joinedDataDepartmentRegion");
        LookupOperation lookupOperationGuideTransport = Aggregation.lookup("maintenance_guides", "guideTransport", "_id", "joinedDataGuideTransport");
        LookupOperation lookupOperationGuideTransferDrive = Aggregation.lookup("maintenance_guides", "guideTransferDrive", "_id", "joinedDataGuideTransferDrive");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        listCriteria.add(Criteria.where("serviceForOperation").is(true));

        if (StringUtils.isNotEmpty(idDepartmentRegion)) {
            ObjectId objectIdDepartmentRegion = new ObjectId(idDepartmentRegion);
            listCriteria.add(Criteria.where("joinedDataDepartmentRegion._id").is(objectIdDepartmentRegion));
        }
        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            ObjectId objectIdRegisterGroup = new ObjectId(idRegisterGroup);
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(objectIdRegisterGroup));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            ObjectId objectIdSupplier = new ObjectId(idSupplier);
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(objectIdSupplier));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            ObjectId objectIdCustomer = new ObjectId(idCustomer);
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(objectIdCustomer));
        }
        if (StringUtils.isNotEmpty(idGuideTransport)) {
            ObjectId objectIdGuideTransport = new ObjectId(idGuideTransport);
            listCriteria.add(Criteria.where("joinedDataGuideTransport._id").is(objectIdGuideTransport));
        }
        if (StringUtils.isNotEmpty(idGuideTransferDrive)) {
            ObjectId objectIdGuideTransferDrive = new ObjectId(idGuideTransferDrive);
            listCriteria.add(Criteria.where("joinedDataGuideTransferDrive._id").is(objectIdGuideTransferDrive));
        }

        listCriteria.add(Criteria.where("joinedDataSupplierType._id").in(listSupplierType));

        listCriteria.add(Criteria.where("joinedDataRegisterGroup.stateRegisterGroup").ne(StateRegisterGroupEnum.ANNULLED.getName()));

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");
        SortOperation sortOperation = Aggregation.sort(pageable.getSort());

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                sortOperation,
                lookupOperationSupplier,
                lookupOperationCustomer,
                lookupOperationRegisterGroup,
                lookupOperationItineraryDate,
                lookupOperationDepartmentRegion,
                lookupOperationGuideTransferDrive,
                lookupOperationGuideTransport,
                lookupOperationSupplierType,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult.class
        );

        ItineraryItemRepositoryTemplateImpl.ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public List<ItineraryItemEntity> aggregateTransferControlBible(ISearchFilterPageDomain searchFilterDomain, List<ObjectId> listSupplierType) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idSupplier = null;
        String idRegisterGroup = null;
        String idDepartmentRegion = null;
        String idCustomer = null;
        String idGuideTransport = null;
        String idGuideTransferDrive = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "idRegisterGroup" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "idDepartmentRegion" -> {
                        idDepartmentRegion = entry.getValue();
                    }
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idGuideTransport" -> {
                        idGuideTransport = entry.getValue();
                    }
                    case "idGuideTransferDrive" -> {
                        idGuideTransferDrive = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationItineraryDate = Aggregation.lookup("booking_itinerary_dates", "itineraryDate", "_id", "joinedDataItineraryDate");
        LookupOperation lookupOperationDepartmentRegion = Aggregation.lookup("base_department_regions", "departmentRegion", "_id", "joinedDataDepartmentRegion");
        LookupOperation lookupOperationGuideTransport = Aggregation.lookup("maintenance_guides", "guideTransport", "_id", "joinedDataGuideTransport");
        LookupOperation lookupOperationGuideTransferDrive = Aggregation.lookup("maintenance_guides", "guideTransferDrive", "_id", "joinedDataGuideTransferDrive");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        listCriteria.add(Criteria.where("serviceForOperation").is(true));

        if (StringUtils.isNotEmpty(idDepartmentRegion)) {
            ObjectId objectIdDepartmentRegion = new ObjectId(idDepartmentRegion);
            listCriteria.add(Criteria.where("joinedDataDepartmentRegion._id").is(objectIdDepartmentRegion));
        }
        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            ObjectId objectIdRegisterGroup = new ObjectId(idRegisterGroup);
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(objectIdRegisterGroup));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            ObjectId objectIdSupplier = new ObjectId(idSupplier);
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(objectIdSupplier));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            ObjectId objectIdCustomer = new ObjectId(idCustomer);
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(objectIdCustomer));
        }
        if (StringUtils.isNotEmpty(idGuideTransport)) {
            ObjectId objectIdGuideTransport = new ObjectId(idGuideTransport);
            listCriteria.add(Criteria.where("joinedDataGuideTransport._id").is(objectIdGuideTransport));
        }
        if (StringUtils.isNotEmpty(idGuideTransferDrive)) {
            ObjectId objectIdGuideTransferDrive = new ObjectId(idGuideTransferDrive);
            listCriteria.add(Criteria.where("joinedDataGuideTransferDrive._id").is(objectIdGuideTransferDrive));
        }

        listCriteria.add(Criteria.where("joinedDataSupplierType._id").in(listSupplierType));

        listCriteria.add(Criteria.where("joinedDataRegisterGroup.stateRegisterGroup").ne(StateRegisterGroupEnum.ANNULLED.getName()));

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.formatIso8601ToLocalDateTimeInit(itineraryDateTimeStart);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.formatIso8601ToLocalDateTimeEnd(itineraryDateTimeEnd);
            listCriteria.add(Criteria.where("joinedDataItineraryDate.itineraryDate").lte(dtEnd));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationSupplier,
                lookupOperationCustomer,
                lookupOperationRegisterGroup,
                lookupOperationItineraryDate,
                lookupOperationDepartmentRegion,
                lookupOperationGuideTransferDrive,
                lookupOperationGuideTransport,
                lookupOperationSupplierType,
                matchOperation
        );
        List<ItineraryItemEntity> result = mongoTemplate.aggregate(aggregation, "booking_itinerary_items", ItineraryItemEntity.class).getMappedResults();
        return result;
    }

    @Override
    public List<ItineraryItemDomain> queryAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseGenerateAllBookingByRegisterGroupAndSupplier(items);
    }

    @Override
    public void updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public void updateIdSimpleDomainCaseCustomerModification(Query query, Update update) {
        mongoTemplate.updateFirst(query, update,ItineraryItemEntity.class);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseVoucherEgress(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseVoucherEgress(items);
    }

    @Override
    public ItineraryItemDomain queryOneIdSimpleDomainCaseUpdateOrderItineraryVoucherEgress(Query query) {
        ItineraryItemEntity entity = mongoTemplate.findOne(query, ItineraryItemEntity.class);
        return converter.toDomainIdSimpleDomainCaseUpdateOrderItineraryVoucherEgress(entity);
    }

    @Override
    public List<ItineraryItemDomain> queryAllSimpleDomainCaseResendFileToSale(Query query) {
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return converter.toDomainAllSimpleToDomainCaseRsendFileToSaleByRegisterGroup(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateIdItineraryItemQuotation(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, ItineraryItemEntity.class);
    }

    // Class to map the aggregation result
    public static class ItineraryItemAggregationResult {
        private List<ItineraryItemEntity> data;
        private List<ItineraryItemRepositoryTemplateImpl.TotalCount> totalCount;

        public List<ItineraryItemEntity> getData() {
            return data;
        }

        public void setData(List<ItineraryItemEntity> data) {
            this.data = data;
        }

        public List<ItineraryItemRepositoryTemplateImpl.TotalCount> getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(List<ItineraryItemRepositoryTemplateImpl.TotalCount> totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class TotalCount {
        private long total;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }
    }
}
