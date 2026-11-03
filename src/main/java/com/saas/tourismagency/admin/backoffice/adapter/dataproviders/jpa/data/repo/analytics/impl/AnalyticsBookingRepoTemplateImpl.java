package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.impl;

import org.bson.types.ObjectId;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.DepartmentRegionEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.EntityBaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingLodgingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterGroupEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.IAnalyticsBookingRepoTemplate;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.ItineraryItemReportItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.RegisterGroupReportItem;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.AnalyticsFilterUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeInheritanceBookingEnum;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class AnalyticsBookingRepoTemplateImpl implements IAnalyticsBookingRepoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DateUtil dateUtil;

    // ===================== ITINERARY =====================

    @Override
    public Page<ItineraryItemEntity> aggregatePageableItinerary(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String idSupplierType = null;
        String idSupplier = null;
        String customerId = null;
        String localidad = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "idSupplierType" -> idSupplierType = entry.getValue();
                    case "idSupplier" -> idSupplier = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "localidad" -> localidad = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idSupplierType)) {
            listCriteria.add(Criteria.where("joinedDataSupplierType._id").is(AnalyticsFilterUtil.refId(idSupplierType)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(localidad)) {
            listCriteria.add(Criteria.where("departmentRegion").is(departmentRegionValue(localidad)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemAggregationResult.class
        );

        ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== GROUP =====================

    @Override
    public Page<RegisterGroupEntity> aggregatePageableGroup(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idProgram = null;
        String idCustomer = null;
        String idRegisterGroup = null;
        String stateRegisterGroup = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idProgram" -> idProgram = entry.getValue();
                    case "idCustomer" -> idCustomer = entry.getValue();
                    case "customerId" -> idCustomer = entry.getValue();
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "stateRegisterGroup" -> stateRegisterGroup = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationProgram = Aggregation.lookup("crm_programs", "program", "_id", "joinedDataProgram");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("_id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idProgram)) {
            listCriteria.add(Criteria.where("joinedDataProgram._id").is(AnalyticsFilterUtil.refId(idProgram)));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(AnalyticsFilterUtil.refId(idCustomer)));
        }
        if (StringUtils.isNotEmpty(stateRegisterGroup)) {
            listCriteria.add(Criteria.where("stateRegisterGroup").is(AnalyticsFilterUtil.stateRegisterGroup(stateRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationProgram,
                lookupOperationCustomer,
                matchOperation,
                facetOperation
        );

        AggregationResults<RegisterGroupAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_register_groups", RegisterGroupAggregationResult.class
        );

        RegisterGroupAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<RegisterGroupEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== BIBLE ACTIVITY =====================

    @Override
    public Page<ItineraryItemEntity> aggregatePageableBibleActivity(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String serviceGroup = null;
        String idSupplierType = null;
        String customerId = null;
        String localidad = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "serviceGroup" -> serviceGroup = entry.getValue();
                    case "idSupplierType" -> idSupplierType = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "localidad" -> localidad = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(serviceGroup)) {
            listCriteria.add(Criteria.where("serviceGroup").is(serviceGroup));
        }
        if (StringUtils.isNotEmpty(idSupplierType)) {
            listCriteria.add(Criteria.where("joinedDataSupplierType._id").is(AnalyticsFilterUtil.refId(idSupplierType)));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(localidad)) {
            listCriteria.add(Criteria.where("departmentRegion").is(departmentRegionValue(localidad)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemAggregationResult.class
        );

        ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== PASSENGER =====================

    @Override
    public Page<PassengerGroupEntity> aggregatePageablePassenger(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String typePassengerTcBooking = null;
        String customerId = null;
        String name = null;
        String document = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "typePassengerTcBooking" -> typePassengerTcBooking = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "name" -> name = entry.getValue();
                    case "document" -> document = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(typePassengerTcBooking)) {
            listCriteria.add(Criteria.where("typePassengerTcBooking").is(typePassengerTcBooking));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(name)) {
            listCriteria.add(Criteria.where("name").regex(String.format(".*%s.*", name), "i"));
        }
        if (StringUtils.isNotEmpty(document)) {
            listCriteria.add(Criteria.where("document").regex(String.format(".*%s.*", document), "i"));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateInit").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("dateInit").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateInit").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                matchOperation,
                facetOperation
        );

        AggregationResults<PassengerGroupAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_passenger_groups", PassengerGroupAggregationResult.class
        );

        PassengerGroupAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<PassengerGroupEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== SUPPLIER (Hotel, Operator, Restaurant, Transport, AirLine) =====================

    @Override
    public Page<EntityBaseEntity> aggregatePageableSupplier(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String supplierTypeInheritance) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idSupplierType = null;
        String idSupplier = null;
        String name = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idSupplierType" -> idSupplierType = entry.getValue();
                    case "supplierTypeId" -> idSupplierType = entry.getValue();
                    case "idSupplier" -> idSupplier = entry.getValue();
                    case "supplierId" -> idSupplier = entry.getValue();
                    case "name" -> name = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("typeInheritance").is(supplierTypeInheritance));

        if (StringUtils.isNotEmpty(idSupplierType)) {
            listCriteria.add(Criteria.where("joinedDataSupplierType._id").is(AnalyticsFilterUtil.refId(idSupplierType)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("_id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(name)) {
            listCriteria.add(Criteria.where("name").regex(String.format(".*%s.*", name), "i"));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationSupplierType,
                matchOperation,
                facetOperation
        );

        AggregationResults<EntityBaseAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "base_entities", EntityBaseAggregationResult.class
        );

        EntityBaseAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<EntityBaseEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== GROUP BY PROGRAM =====================

    @Override
    public Page<RegisterGroupEntity> aggregatePageableGroupByProgram(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idProgram = null;
        String idRegisterGroup = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idProgram" -> idProgram = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationProgram = Aggregation.lookup("crm_programs", "program", "_id", "joinedDataProgram");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("_id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idProgram)) {
            listCriteria.add(Criteria.where("joinedDataProgram._id").is(AnalyticsFilterUtil.refId(idProgram)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationProgram,
                matchOperation,
                facetOperation
        );

        AggregationResults<RegisterGroupAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_register_groups", RegisterGroupAggregationResult.class
        );

        RegisterGroupAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<RegisterGroupEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== GROUP BY ACTIVITY =====================

    @Override
    public Page<ItineraryItemEntity> aggregatePageableGroupByActivity(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String idSupplier = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "idSupplier" -> idSupplier = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemAggregationResult.class
        );

        ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== HOTEL EXPIRATION =====================

    @Override
    public Page<RegisterBookingLodgingEntity> aggregatePageableHotelExpiration(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String idSupplier = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "idSupplier" -> idSupplier = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("initTour").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("initTour").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("initTour").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<RegisterBookingLodgingAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_register_booking_bases", RegisterBookingLodgingAggregationResult.class
        );

        RegisterBookingLodgingAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<RegisterBookingLodgingEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== TRANSPORT =====================

    @Override
    public Page<ItineraryItemEntity> aggregatePageableTransport(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String nameSupplier = null;
        String responsible = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "nameSupplier" -> nameSupplier = entry.getValue();
                    case "transportName" -> nameSupplier = entry.getValue();
                    case "responsible" -> responsible = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("joinedDataSupplierType.name").is("TRANSPORTES"));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier.name").regex(String.format(".*%s.*", nameSupplier), "i"));
        }
        if (StringUtils.isNotEmpty(responsible)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.responsible").is(AnalyticsFilterUtil.refId(responsible)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemAggregationResult.class
        );

        ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== AIRLINE =====================

    @Override
    public Page<ItineraryItemEntity> aggregatePageableAirLine(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String nameSupplier = null;
        String responsible = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "nameSupplier" -> nameSupplier = entry.getValue();
                    case "airlineName" -> nameSupplier = entry.getValue();
                    case "responsible" -> responsible = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("joinedDataSupplierType.name").is("LINEAS AEREAS"));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier.name").regex(String.format(".*%s.*", nameSupplier), "i"));
        }
        if (StringUtils.isNotEmpty(responsible)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.responsible").is(AnalyticsFilterUtil.refId(responsible)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemAggregationResult.class
        );

        ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== HOTEL =====================

    @Override
    public Page<RegisterBookingLodgingEntity> aggregatePageableHotel(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String nameSupplier = null;
        String responsible = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "nameSupplier" -> nameSupplier = entry.getValue();
                    case "responsible" -> responsible = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("typeInheritanceBooking").is(TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGING.getName()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier.name").regex(String.format(".*%s.*", nameSupplier), "i"));
        }
        if (StringUtils.isNotEmpty(responsible)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.responsible").is(AnalyticsFilterUtil.refId(responsible)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("initTour").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("initTour").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("initTour").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<RegisterBookingLodgingAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_register_booking_bases", RegisterBookingLodgingAggregationResult.class
        );

        RegisterBookingLodgingAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<RegisterBookingLodgingEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== OPERATOR =====================

    @Override
    public Page<RegisterBookingOperatorEntity> aggregatePageableOperator(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String nameSupplier = null;
        String responsible = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "nameSupplier" -> nameSupplier = entry.getValue();
                    case "responsible" -> responsible = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("typeInheritanceBooking").is(TypeInheritanceBookingEnum.REGISTER_BOOKING_OPERATOR.getName()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier.name").regex(String.format(".*%s.*", nameSupplier), "i"));
        }
        if (StringUtils.isNotEmpty(responsible)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.responsible").is(AnalyticsFilterUtil.refId(responsible)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<RegisterBookingOperatorAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_register_booking_bases", RegisterBookingOperatorAggregationResult.class
        );

        RegisterBookingOperatorAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<RegisterBookingOperatorEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== RESTAURANT =====================

    @Override
    public Page<RegisterBookingRestaurantEntity> aggregatePageableRestaurant(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String nameSupplier = null;
        String responsible = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "nameSupplier" -> nameSupplier = entry.getValue();
                    case "responsible" -> responsible = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        listCriteria.add(Criteria.where("typeInheritanceBooking").is(TypeInheritanceBookingEnum.REGISTER_BOOKING_RESTAURANT.getName()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier.name").regex(String.format(".*%s.*", nameSupplier), "i"));
        }
        if (StringUtils.isNotEmpty(responsible)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.responsible").is(AnalyticsFilterUtil.refId(responsible)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<RegisterBookingRestaurantAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_register_booking_bases", RegisterBookingRestaurantAggregationResult.class
        );

        RegisterBookingRestaurantAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<RegisterBookingRestaurantEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== STATISTICS =====================
    @Override
    @SuppressWarnings("unchecked")
    public Page<Map> aggregatePageableStatistics(ISearchFilterPageDomain searchFilterDomain, Pageable pageable, String groupField) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        LookupOperation lookupRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedRegisterGroup");
        LookupOperation lookupSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedSupplier");

        String groupKey = switch (groupField) {
            case "operator" -> "$joinedRegisterGroup.customer";
            case "country" -> "$joinedRegisterGroup.country";
            case "responsible" -> "$joinedRegisterGroup.responsible";
            case "hotel" -> "$joinedSupplier._id";
            default -> "$" + groupField;
        };

        GroupOperation groupOperation = Aggregation.group(groupKey)
                .count().as("totalGroups")
                .sum("paxTotal").as("totalPax")
                .sum("importTotalUsd").as("totalUsd");
        if ("hotel".equals(groupField)) {
            groupOperation = groupOperation.sum("lodgingCantNight").as("totalNights");
        }
        if ("operator".equals(groupField)) {
            groupOperation = groupOperation.first("$joinedRegisterGroup.country").as("countryKey");
        }

        String labelCollection = switch (groupField) {
            case "country" -> "base_countries";
            case "responsible" -> "hr_employees";
            default -> "base_entities";
        };

        Document labelMatch = new Document("$match", new Document("$expr",
                new Document("$eq", java.util.List.of(toStringConversion("$_id"), toStringConversion(firstOrSelf("$$labelId"))))));
        List<Object> labelPipeline = new ArrayList<>();
        labelPipeline.add(labelMatch);
        if ("responsible".equals(groupField)) {
            labelPipeline.add(new Document("$lookup", new Document("from", "base_persons")
                    .append("let", new Document("personId", "$person"))
                    .append("pipeline", java.util.List.of(
                            new Document("$match", new Document("$expr", new Document("$eq",
                                    java.util.List.of(toStringConversion("$_id"), toStringConversion("$$personId"))))),
                            new Document("$project", new Document("_id", 0).append("firstName", 1).append("lastName", 1))))
                    .append("as", "personInfo")));
            labelPipeline.add(new Document("$project", new Document("_id", 0).append("name",
                    new Document("$concat", java.util.List.of(
                            new Document("$ifNull", java.util.List.of(new Document("$first", "$personInfo.firstName"), "")),
                            " ",
                            new Document("$ifNull", java.util.List.of(new Document("$first", "$personInfo.lastName"), "")))))));
        } else {
            labelPipeline.add(new Document("$project", new Document("_id", 0).append("name", 1)));
        }
        AggregationOperation labelLookup = context -> context.getMappedObject(new Document("$lookup",
                new Document("from", labelCollection)
                        .append("let", new Document("labelId", "$_id"))
                        .append("pipeline", labelPipeline)
                        .append("as", "labelInfo")));
        AggregationOperation addLabel = context -> context.getMappedObject(
                new Document("$addFields", new Document("label", new Document("$first", "$labelInfo.name"))));
        AggregationOperation dropLabelInfo = context -> context.getMappedObject(
                new Document("$addFields", new Document("labelInfo", "$$REMOVE")));
        List<AggregationOperation> statisticsOps = new ArrayList<>(java.util.List.of(labelLookup, addLabel, dropLabelInfo));
        if ("operator".equals(groupField)) {
            Document countryMatch = new Document("$match", new Document("$expr",
                    new Document("$eq", java.util.List.of(toStringConversion("$_id"), toStringConversion(firstOrSelf("$$countryId"))))));
            Document countryProject = new Document("$project", new Document("_id", 0).append("name", 1));
            AggregationOperation countryLookup = context -> context.getMappedObject(new Document("$lookup",
                    new Document("from", "base_countries")
                            .append("let", new Document("countryId", "$countryKey"))
                            .append("pipeline", java.util.List.of(countryMatch, countryProject))
                            .append("as", "countryInfo")));
            AggregationOperation addCountry = context -> context.getMappedObject(
                    new Document("$addFields", new Document("country", new Document("$first", "$countryInfo.name"))));
            AggregationOperation dropCountryInfo = context -> context.getMappedObject(
                    new Document("$addFields", new Document("countryInfo", "$$REMOVE")));
            statisticsOps.add(countryLookup);
            statisticsOps.add(addCountry);
            statisticsOps.add(dropCountryInfo);
        }

        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "totalGroups");

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        List<AggregationOperation> aggregationOps = new ArrayList<>();
        aggregationOps.add(lookupRegisterGroup);
        aggregationOps.add(lookupSupplier);
        aggregationOps.add(matchOperation);
        aggregationOps.add(groupOperation);
        aggregationOps.addAll(statisticsOps);
        aggregationOps.add(sortOperation);
        aggregationOps.add(facetOperation);

        Aggregation aggregation = Aggregation.newAggregation(aggregationOps);

        AggregationResults<Map> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", Map.class
        );

        Map aggregationResult = results.getUniqueMappedResult();
        List<Map> data = aggregationResult != null ? (List<Map>) aggregationResult.get("data") : List.of();
        decorateStatistics(data, groupField);
        long totalCount = 0;
        if (aggregationResult != null && aggregationResult.get("totalCount") != null) {
            List<Map> totalCountList = (List<Map>) aggregationResult.get("totalCount");
            if (!totalCountList.isEmpty()) {
                Object totalObj = totalCountList.get(0).get("total");
                if (totalObj instanceof Number) {
                    totalCount = ((Number) totalObj).longValue();
                }
            }
        }

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== HELPERS =====================

    private Object departmentRegionValue(String localidad) {
        if (ObjectId.isValid(localidad)) {
            return new ObjectId(localidad);
        }
        DepartmentRegionEntity departmentRegion = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(localidad)),
                DepartmentRegionEntity.class
        );
        return departmentRegion != null ? departmentRegion.getId() : "__no_region__";
    }

    private void decorateStatistics(List<Map> rows, String groupField) {
        if (rows == null || rows.isEmpty()) {
            return;
        }
        List<Object> keys = rows.stream().map(row -> statisticKey(row.get("_id"))).filter(Objects::nonNull).toList();
        List<Object> countryKeys = "operator".equals(groupField)
                ? rows.stream().map(row -> statisticKey(row.get("countryKey"))).filter(Objects::nonNull).toList()
                : List.of();
        String labelKey = switch (groupField) {
            case "operator" -> "name";
            case "country" -> "country";
            case "responsible" -> "responsible";
            case "hotel" -> "hotel";
            default -> groupField;
        };
        String collection = switch (groupField) {
            case "responsible" -> "hr_employees";
            case "country" -> "base_countries";
            default -> "base_entities";
        };
        Map<String, String> names = resolveStatisticNames(collection, keys);
        Map<String, String> countryNames = "operator".equals(groupField)
                ? resolveStatisticNames("base_countries", countryKeys)
                : Collections.emptyMap();
        for (Map row : rows) {
            Object key = statisticKey(row.get("_id"));
            String hex = key != null ? statisticKeyHex(key) : null;
            Object resolved = hex != null ? names.get(hex) : null;
            Object aggLabel = row.get("label");
            String label = (aggLabel != null && !aggLabel.toString().isBlank()) ? aggLabel.toString()
                    : (resolved != null ? resolved.toString() : "-");
            row.put("label", label);
            row.putIfAbsent("id", hex != null ? hex : "-");
            row.putIfAbsent(labelKey, label);
            if ("operator".equals(groupField)) {
                Object countryKey = row.get("countryKey");
                Object aggCountry = row.get("country");
                String country;
                if (aggCountry != null && !aggCountry.toString().isBlank()) {
                    country = aggCountry.toString();
                } else {
                    Object countryResolved = countryKey != null ? countryNames.get(statisticKeyHex(statisticKey(countryKey))) : null;
                    country = countryResolved != null ? countryResolved.toString() : "-";
                }
                row.put("country", country);
            } else {
                row.putIfAbsent("country", "-");
            }
            row.putIfAbsent("totalNights", 0);
        }
    }

    private String statisticKeyHex(Object key) {
        if (key instanceof ObjectId id) {
            return id.toHexString();
        }
        return String.valueOf(key);
    }

    private static Document toStringConversion(Object input) {
        return new Document("$convert",
                new Document("input", input)
                        .append("to", "string")
                        .append("onError", "")
                        .append("onNull", ""));
    }

    private static Document firstOrSelf(Object input) {
        return new Document("$cond", java.util.List.of(
                new Document("$isArray", input),
                new Document("$arrayElemAt", java.util.List.of(input, 0)),
                input));
    }

    private Object statisticKey(Object key) {
        if (key instanceof java.util.Collection<?> collection && !collection.isEmpty()) {
            return collection.iterator().next();
        }
        return key;
    }

    private Map<String, String> resolveStatisticNames(String collection, List<?> keys) {
        Map<String, String> names = new HashMap<>();
        if (keys == null || keys.isEmpty()) {
            return names;
        }
        List<Object> queryIds = new ArrayList<>();
        for (Object key : keys) {
            String hex = statisticKeyHex(key);
            if (ObjectId.isValid(hex)) {
                queryIds.add(hex);
                queryIds.add(new ObjectId(hex));
            }
        }
        if (queryIds.isEmpty()) {
            return names;
        }
        Query idQuery = Query.query(Criteria.where("_id").in(queryIds));
        idQuery.fields().include("name");
        List<Document> docs = mongoTemplate.find(idQuery, Document.class, collection);
        for (Document doc : docs) {
            names.put(statisticKeyHex(doc.get("_id")), doc.getString("name"));
        }
        return names;
    }

    // ===================== GUIDE =====================

    @Override
    public Page<ItineraryItemEntity> aggregatePageableGuide(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String idSupplierType = null;
        String idSupplier = null;
        String customerId = null;
        String localidad = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "idSupplierType" -> idSupplierType = entry.getValue();
                    case "idSupplier" -> idSupplier = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "localidad" -> localidad = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idSupplierType)) {
            listCriteria.add(Criteria.where("joinedDataSupplierType._id").is(AnalyticsFilterUtil.refId(idSupplierType)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(localidad)) {
            listCriteria.add(Criteria.where("departmentRegion").is(departmentRegionValue(localidad)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                lookupOperationSupplier,
                matchOperation,
                facetOperation
        );

        AggregationResults<ItineraryItemAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemAggregationResult.class
        );

        ItineraryItemAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<ItineraryItemReportItem> aggregatePageableGuideReport(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String idSupplierType = null;
        String idSupplier = null;
        String customerId = null;
        String localidad = null;
        String dateStart = null;
        String dateEnd = null;
        String serviceGroup = null;
        String nameSupplier = null;
        String responsibleFilter = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "idSupplierType" -> idSupplierType = entry.getValue();
                    case "idSupplier" -> idSupplier = entry.getValue();
                    case "customerId" -> customerId = entry.getValue();
                    case "localidad" -> localidad = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    case "serviceGroup" -> serviceGroup = entry.getValue();
                    case "nameSupplier" -> nameSupplier = entry.getValue();
                    case "transportName" -> nameSupplier = entry.getValue();
                    case "airlineName" -> nameSupplier = entry.getValue();
                    case "responsible" -> responsibleFilter = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationService = Aggregation.lookup("maintenance_services", "service", "_id", "joinedDataService");
        LookupOperation lookupOperationDepartmentRegion = Aggregation.lookup("base_department_regions", "departmentRegion", "_id", "joinedDataDepartmentRegion");
        LookupOperation lookupOperationGuideTransferDrive = Aggregation.lookup("maintenance_guides", "guideTransferDrive", "_id", "joinedDataGuideTransferDrive");
        LookupOperation lookupOperationGuideTransport = Aggregation.lookup("maintenance_guides", "guideTransport", "_id", "joinedDataGuideTransport");
        LookupOperation lookupOperationOrderServiceItem = Aggregation.lookup("operation_order_service_items", "orderServiceItem", "_id", "joinedDataOrderServiceItem");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idSupplierType)) {
            listCriteria.add(Criteria.where("joinedDataSupplierType._id").is(AnalyticsFilterUtil.refId(idSupplierType)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(customerId)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup.customer").is(AnalyticsFilterUtil.refId(customerId)));
        }
        if (StringUtils.isNotEmpty(localidad)) {
            listCriteria.add(Criteria.where("departmentRegion").is(departmentRegionValue(localidad)));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)).lte(dateUtil.toEndOfDay(dtEnd)));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("itineraryDateTime").gte(dateUtil.toStartOfDay(dtStart)));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("itineraryDateTime").lte(dateUtil.toEndOfDay(dtEnd)));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        AggregationOperation addRgField = Aggregation.addFields().addField("rg")
                .withValue(new Document("$arrayElemAt", List.of("$joinedDataRegisterGroup", 0))).build();
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "rg.customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationResponsible = Aggregation.lookup("hr_employees", "rg.responsible", "_id", "joinedDataResponsible");

        List<Criteria> listCriteriaExtra = new ArrayList<>();
        if (StringUtils.isNotEmpty(serviceGroup)) {
            listCriteriaExtra.add(Criteria.where("serviceGroup").is(serviceGroup));
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            listCriteriaExtra.add(Criteria.where("joinedDataSupplier.name").regex(String.format(".*%s.*", nameSupplier), "i"));
        }
        if (StringUtils.isNotEmpty(responsibleFilter)) {
            listCriteriaExtra.add(Criteria.where("joinedDataResponsible.name").regex(String.format(".*%s.*", responsibleFilter), "i"));
        }
        MatchOperation matchOperationExtra = null;
        if (!listCriteriaExtra.isEmpty()) {
            matchOperationExtra = Aggregation.match(new Criteria().andOperator(listCriteriaExtra));
        }

        Document projectDoc = new Document("$project", new Document()
                .append("_id", 0)
                .append("observation", 1)
                .append("itineraryDateTime", 1)
                .append("identify", 1)
                .append("paxAdultTotal", 1)
                .append("paxChildrenTotal", 1)
                .append("paxTcTotal", 1)
                .append("paxTcLocalTotal", 1)
                .append("paxGuideTotal", 1)
                .append("operatorPvt", 1)
                .append("operatorSic", 1)
                .append("serviceName", new Document("$arrayElemAt", List.of("$joinedDataService.name", 0)))
                .append("serviceAbbrev", new Document("$arrayElemAt", List.of("$joinedDataService.abbrev", 0)))
                .append("supplierName", new Document("$arrayElemAt", List.of("$joinedDataSupplier.name", 0)))
                .append("departmentRegionName", new Document("$arrayElemAt", List.of("$joinedDataDepartmentRegion.name", 0)))
                .append("registerGroupName", new Document("$arrayElemAt", List.of("$joinedDataRegisterGroup.name", 0)))
                .append("registerGroupIdentify", new Document("$arrayElemAt", List.of("$joinedDataRegisterGroup.identify", 0)))
                .append("customerName", new Document("$arrayElemAt", List.of("$joinedDataCustomer.name", 0)))
                .append("responsibleName", new Document("$arrayElemAt", List.of("$joinedDataResponsible.name", 0)))
                .append("guideTransferDriveName", new Document("$arrayElemAt", List.of("$joinedDataGuideTransferDrive.name", 0)))
                .append("guideTransportName", new Document("$arrayElemAt", List.of("$joinedDataGuideTransport.name", 0)))
                .append("orderServiceDateTimeService", new Document("$arrayElemAt", List.of("$joinedDataOrderServiceItem.dateTimeService", 0)))
                .append("orderServiceDateTimePax", new Document("$arrayElemAt", List.of("$joinedDataOrderServiceItem.dateTimePax", 0))));
        AggregationOperation projectOperation = context -> projectDoc;

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        List<AggregationOperation> pipelineOperations = new ArrayList<>(List.of(
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                lookupOperationSupplier,
                lookupOperationService,
                lookupOperationDepartmentRegion,
                lookupOperationGuideTransferDrive,
                lookupOperationGuideTransport,
                lookupOperationOrderServiceItem,
                matchOperation,
                addRgField,
                lookupOperationCustomer,
                lookupOperationResponsible));
        if (matchOperationExtra != null) {
            pipelineOperations.add(matchOperationExtra);
        }
        pipelineOperations.add(projectOperation);
        pipelineOperations.add(facetOperation);

        Aggregation aggregation = Aggregation.newAggregation(pipelineOperations);

        AggregationResults<ItineraryItemReportAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_itinerary_items", ItineraryItemReportAggregationResult.class
        );

        ItineraryItemReportAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<ItineraryItemReportItem> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    @Override
    public Page<RegisterGroupReportItem> aggregateAllRegisterGroupReport(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String idRegisterGroup = null;
        String idProgram = null;
        String idCustomer = null;
        String stateRegisterGroup = null;
        String name = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idRegisterGroup" -> idRegisterGroup = entry.getValue();
                    case "registerGroupId" -> idRegisterGroup = entry.getValue();
                    case "idProgram" -> idProgram = entry.getValue();
                    case "idCustomer" -> idCustomer = entry.getValue();
                    case "customerId" -> idCustomer = entry.getValue();
                    case "stateRegisterGroup" -> stateRegisterGroup = entry.getValue();
                    case "name" -> name = entry.getValue();
                    case "dateStart" -> dateStart = entry.getValue();
                    case "dateEnd" -> dateEnd = entry.getValue();
                    default -> filterErrorMessages.add(
                            new ValidationErrorMessage("filter.key", entry.getKey(), "Unknown property to filter for")
                    );
                }
            }
        }

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationProgram = Aggregation.lookup("crm_programs", "program", "_id", "joinedDataProgram");
        LookupOperation lookupOperationCustomer = Aggregation.lookup("base_entities", "customer", "_id", "joinedDataCustomer");
        LookupOperation lookupOperationResponsible = Aggregation.lookup("hr_employees", "responsible", "_id", "joinedDataResponsible");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("_id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idProgram)) {
            listCriteria.add(Criteria.where("joinedDataProgram._id").is(AnalyticsFilterUtil.refId(idProgram)));
        }
        if (StringUtils.isNotEmpty(idCustomer)) {
            listCriteria.add(Criteria.where("joinedDataCustomer._id").is(AnalyticsFilterUtil.refId(idCustomer)));
        }
        if (StringUtils.isNotEmpty(stateRegisterGroup)) {
            listCriteria.add(Criteria.where("stateRegisterGroup").is(AnalyticsFilterUtil.stateRegisterGroup(stateRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(name)) {
            listCriteria.add(Criteria.where("name").regex(String.format(".*%s.*", name), "i"));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("dateAperture").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("dateAperture").lte(dtEnd));
        }

        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        Document projectDoc = new Document("$project", new Document()
                .append("_id", 0)
                .append("name", 1)
                .append("identify", 1)
                .append("cantPax", 1)
                .append("cantPaxAdult", 1)
                .append("cantPaxChildren", 1)
                .append("tC", 1)
                .append("tCLocal", 1)
                .append("initTour", 1)
                .append("endTour", 1)
                .append("arriveAir", 1)
                .append("departureAir", 1)
                .append("customerName", new Document("$arrayElemAt", List.of("$joinedDataCustomer.name", 0)))
                .append("responsibleName", new Document("$arrayElemAt", List.of("$joinedDataResponsible.name", 0)))
                .append("programName", new Document("$arrayElemAt", List.of("$joinedDataProgram.name", 0))));
        AggregationOperation projectOperation = context -> projectDoc;

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();

        SkipOperation skipOperation = Aggregation.skip((long) pageNumber * pageSize);
        LimitOperation limitOperation = Aggregation.limit(pageSize);
        CountOperation countOperation = Aggregation.count().as("total");

        FacetOperation facetOperation = Aggregation.facet(
                skipOperation,
                limitOperation
        ).as("data").and(countOperation).as("totalCount");

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationProgram,
                lookupOperationCustomer,
                lookupOperationResponsible,
                matchOperation,
                projectOperation,
                facetOperation
        );

        AggregationResults<RegisterGroupReportAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "booking_register_groups", RegisterGroupReportAggregationResult.class
        );

        RegisterGroupReportAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<RegisterGroupReportItem> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // ===================== AGGREGATION RESULT CLASSES =====================

    public static class ItineraryItemAggregationResult {
        private List<ItineraryItemEntity> data;
        private List<TotalCount> totalCount;

        public List<ItineraryItemEntity> getData() { return data; }
        public void setData(List<ItineraryItemEntity> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class ItineraryItemReportAggregationResult {
        private List<ItineraryItemReportItem> data;
        private List<TotalCount> totalCount;

        public List<ItineraryItemReportItem> getData() { return data; }
        public void setData(List<ItineraryItemReportItem> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class RegisterGroupAggregationResult {
        private List<RegisterGroupEntity> data;
        private List<TotalCount> totalCount;

        public List<RegisterGroupEntity> getData() { return data; }
        public void setData(List<RegisterGroupEntity> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class RegisterGroupReportAggregationResult {
        private List<RegisterGroupReportItem> data;
        private List<TotalCount> totalCount;

        public List<RegisterGroupReportItem> getData() { return data; }
        public void setData(List<RegisterGroupReportItem> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class PassengerGroupAggregationResult {
        private List<PassengerGroupEntity> data;
        private List<TotalCount> totalCount;

        public List<PassengerGroupEntity> getData() { return data; }
        public void setData(List<PassengerGroupEntity> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class EntityBaseAggregationResult {
        private List<EntityBaseEntity> data;
        private List<TotalCount> totalCount;

        public List<EntityBaseEntity> getData() { return data; }
        public void setData(List<EntityBaseEntity> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class RegisterBookingLodgingAggregationResult {
        private List<RegisterBookingLodgingEntity> data;
        private List<TotalCount> totalCount;

        public List<RegisterBookingLodgingEntity> getData() { return data; }
        public void setData(List<RegisterBookingLodgingEntity> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class RegisterBookingOperatorAggregationResult {
        private List<RegisterBookingOperatorEntity> data;
        private List<TotalCount> totalCount;

        public List<RegisterBookingOperatorEntity> getData() { return data; }
        public void setData(List<RegisterBookingOperatorEntity> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class RegisterBookingRestaurantAggregationResult {
        private List<RegisterBookingRestaurantEntity> data;
        private List<TotalCount> totalCount;

        public List<RegisterBookingRestaurantEntity> getData() { return data; }
        public void setData(List<RegisterBookingRestaurantEntity> data) { this.data = data; }
        public List<TotalCount> getTotalCount() { return totalCount; }
        public void setTotalCount(List<TotalCount> totalCount) { this.totalCount = totalCount; }
    }

    public static class TotalCount {
        private long total;

        public long getTotal() { return total; }
        public void setTotal(long total) { this.total = total; }
    }

}
