package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.IAnalyticsMaintenanceRepoTemplate;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.AnalyticsFilterUtil;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AnalyticsMaintenanceRepoTemplateImpl implements IAnalyticsMaintenanceRepoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<SupplierEntity> aggregatePageableSupplier(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String name = null;
        String ruc = null;
        String identify = null;
        String supplierTypeId = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "name" -> {
                        name = entry.getValue();
                    }
                    case "ruc" -> {
                        ruc = entry.getValue();
                    }
                    case "identify" -> {
                        identify = entry.getValue();
                    }
                    case "supplierTypeId" -> {
                        supplierTypeId = entry.getValue();
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

        if (!filterErrorMessages.isEmpty()) {
            throw new ValidationException(filterErrorMessages);
        }

        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationPartner = Aggregation.lookup("base_entities", "partner", "_id", "joinedDataPartner");
        LookupOperation lookupOperationSector = Aggregation.lookup("maintenance_sectors", "sector", "_id", "joinedDataSector");
        LookupOperation lookupOperationDocumentType = Aggregation.lookup("base_entities", "documentType", "_id", "joinedDataDocumentType");
        LookupOperation lookupOperationTouristDestine = Aggregation.lookup("maintenance_tourist_destines", "touristDestine", "_id", "joinedDataTouristDestine");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("_class").is("SupplierEntityData"));
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(name)) {
            listCriteria.add(Criteria.where("name").regex(name, "i"));
        }
        if (StringUtils.isNotEmpty(ruc)) {
            listCriteria.add(Criteria.where("ruc").regex(ruc, "i"));
        }
        if (StringUtils.isNotEmpty(identify)) {
            listCriteria.add(Criteria.where("identify").regex(identify, "i"));
        }
        if (StringUtils.isNotEmpty(supplierTypeId)) {
            listCriteria.add(Criteria.where("supplierType").is(AnalyticsFilterUtil.refId(supplierTypeId)));
        }
        Criteria criteria = new Criteria()
                .andOperator(listCriteria);

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
                lookupOperationPartner,
                lookupOperationSector,
                lookupOperationDocumentType,
                lookupOperationTouristDestine,
                matchOperation,
                facetOperation
        );

        AggregationResults<SupplierAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "base_entities", SupplierAggregationResult.class
        );

        SupplierAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<SupplierEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    public static class SupplierAggregationResult {
        private List<SupplierEntity> data;
        private List<TotalCount> totalCount;

        public List<SupplierEntity> getData() {
            return data;
        }

        public void setData(List<SupplierEntity> data) {
            this.data = data;
        }

        public List<TotalCount> getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(List<TotalCount> totalCount) {
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
