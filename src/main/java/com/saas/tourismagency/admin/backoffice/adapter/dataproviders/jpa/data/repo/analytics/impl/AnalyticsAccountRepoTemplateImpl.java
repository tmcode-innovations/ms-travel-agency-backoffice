package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetail.VoucherEgressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.IAnalyticsAccountRepoTemplate;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.AnalyticsFilterUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchFilterReportDomain;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AnalyticsAccountRepoTemplateImpl implements IAnalyticsAccountRepoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private VoucherEgressDetailConverter converter;

    final Map<String, String> VOUCHER_PURCHASE_SUPPLIER_FIELDS = Map.ofEntries(
            Map.entry("valueTypeShowSupplier", "valueTypeShowSupplier"),
            Map.entry("idRegisterGroup", "idRegisterGroup"),
            Map.entry("idSupplierType", "idSupplierType"),
            Map.entry("idSupplier", "idSupplier"),
            Map.entry("idDocumentAccountType", "idDocumentAccountType"),
            Map.entry("numberDoc", "numberDoc"),
            Map.entry("noCheck", "noCheck"),
            Map.entry("dateStart", "date"),
            Map.entry("dateEnd", "date")
    );

    @Override
    public List<VoucherEgressDetailEntity> aggregateVoucherPurchaseBySupplier(ISearchFilterReportDomain searchFilterDomain) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String valueTypeShowSupplier = null;
        String idRegisterGroup = null;
        String idSupplierType = null;
        String idSupplier = null;
        String idDocumentAccountType = null;
        String numberDoc = null;
        String noCheck = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "valueTypeShowSupplier" -> {
                        valueTypeShowSupplier = entry.getValue();
                    }
                    case "valueTypeShowSupplierId" -> {
                        valueTypeShowSupplier = entry.getValue();
                    }
                    case "idRegisterGroup" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "registerGroupId" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "idSupplierType" -> {
                        idSupplierType = entry.getValue();
                    }
                    case "supplierTypeId" -> {
                        idSupplierType = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "supplierId" -> {
                        idSupplier = entry.getValue();
                    }
                    case "idDocumentAccountType" -> {
                        idDocumentAccountType = entry.getValue();
                    }
                    case "numberDoc" -> {
                        numberDoc = entry.getValue();
                    }
                    case "noCheck" -> {
                        noCheck = entry.getValue();
                    }
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
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
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationDocumentAccountType = Aggregation.lookup("maintenance_document_account_types", "documentAccountType", "_id", "joinedDataDocumentAccountType");
        LookupOperation lookupOperationVoucherEgress = Aggregation.lookup("account_voucher_egress", "voucherEgress", "_id", "joinedDataVoucherEgress");

        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(valueTypeShowSupplier)) {
            listCriteria.add(Criteria.where("valueTypeShowSupplier").is(AnalyticsFilterUtil.valueTypeShowSupplier(valueTypeShowSupplier)));
        }
        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idSupplierType)) {
            listCriteria.add(Criteria.where("joinedDataSupplierType._id").is(AnalyticsFilterUtil.refId(idSupplierType)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(idDocumentAccountType)) {
            listCriteria.add(Criteria.where("joinedDataDocumentAccountType._id").is(AnalyticsFilterUtil.refId(idDocumentAccountType)));
        }
        if (StringUtils.isNotEmpty(noCheck)) {
            listCriteria.add(Criteria.where("joinedDataVoucherEgress.noCheck").regex(String.format(".*%s.*", noCheck), "i"));
        }
        if (StringUtils.isNotEmpty(numberDoc)) {
            listCriteria.add(Criteria.where("numberDoc").regex(String.format(".*%s.*", numberDoc), "i"));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").lte(dtEnd));
        }
        Criteria criteria = new Criteria().andOperator(listCriteria);
        MatchOperation matchOperation = Aggregation.match(criteria);

        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                lookupOperationSupplier,
                lookupOperationDocumentAccountType,
                lookupOperationVoucherEgress,
                matchOperation
        );
        List<VoucherEgressDetailEntity> result = mongoTemplate.aggregate(aggregation, "account_voucher_egress_details", VoucherEgressDetailEntity.class).getMappedResults();
        return result;
    }

    @Override
    public Page<VoucherEgressDetailEntity> aggregatePageableVoucherPurchaseBySupplier(ISearchFilterPageDomain searchFilterDomain, Pageable pageable) {
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String valueTypeShowSupplier = null;
        String idRegisterGroup = null;
        String idSupplierType = null;
        String idSupplier = null;
        String idDocumentAccountType = null;
        String numberDoc = null;
        String noCheck = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "valueTypeShowSupplier" -> {
                        valueTypeShowSupplier = entry.getValue();
                    }
                    case "valueTypeShowSupplierId" -> {
                        valueTypeShowSupplier = entry.getValue();
                    }
                    case "idRegisterGroup" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "registerGroupId" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "idSupplierType" -> {
                        idSupplierType = entry.getValue();
                    }
                    case "supplierTypeId" -> {
                        idSupplierType = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "supplierId" -> {
                        idSupplier = entry.getValue();
                    }
                    case "idDocumentAccountType" -> {
                        idDocumentAccountType = entry.getValue();
                    }
                    case "numberDoc" -> {
                        numberDoc = entry.getValue();
                    }
                    case "noCheck" -> {
                        noCheck = entry.getValue();
                    }
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
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
        LookupOperation lookupOperationRegisterGroup = Aggregation.lookup("booking_register_groups", "registerGroup", "_id", "joinedDataRegisterGroup");
        LookupOperation lookupOperationSupplierType = Aggregation.lookup("maintenance_supplier_types", "supplierType", "_id", "joinedDataSupplierType");
        LookupOperation lookupOperationSupplier = Aggregation.lookup("base_entities", "supplier", "_id", "joinedDataSupplier");
        LookupOperation lookupOperationDocumentAccountType = Aggregation.lookup("maintenance_document_account_types", "documentAccountType", "_id", "joinedDataDocumentAccountType");
        LookupOperation lookupOperationVoucherEgress = Aggregation.lookup("account_voucher_egress", "voucherEgress", "_id", "joinedDataVoucherEgress");
        List<Criteria> listCriteria = new ArrayList<>();
        listCriteria.add(Criteria.where("deleted").is(false));
        listCriteria.add(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        if (StringUtils.isNotEmpty(valueTypeShowSupplier)) {
            listCriteria.add(Criteria.where("valueTypeShowSupplier").is(AnalyticsFilterUtil.valueTypeShowSupplier(valueTypeShowSupplier)));
        }
        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            listCriteria.add(Criteria.where("joinedDataRegisterGroup._id").is(AnalyticsFilterUtil.refId(idRegisterGroup)));
        }
        if (StringUtils.isNotEmpty(idSupplierType)) {
            listCriteria.add(Criteria.where("joinedDataSupplierType._id").is(AnalyticsFilterUtil.refId(idSupplierType)));
        }
        if (StringUtils.isNotEmpty(idSupplier)) {
            listCriteria.add(Criteria.where("joinedDataSupplier._id").is(AnalyticsFilterUtil.refId(idSupplier)));
        }
        if (StringUtils.isNotEmpty(idDocumentAccountType)) {
            listCriteria.add(Criteria.where("joinedDataDocumentAccountType._id").is(AnalyticsFilterUtil.refId(idDocumentAccountType)));
        }
        if (StringUtils.isNotEmpty(noCheck)) {
            listCriteria.add(Criteria.where("joinedDataVoucherEgress.noCheck").regex(String.format(".*%s.*", noCheck),"i"));
        }
        if (StringUtils.isNotEmpty(numberDoc)) {
            listCriteria.add(Criteria.where("numberDoc").regex(String.format(".*%s.*", numberDoc),"i"));
        }
        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatSimpleDateToLocalDate(dateStart);
            listCriteria.add(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatSimpleDateToLocalDate(dateEnd);
            listCriteria.add(Criteria.where("date").lte(dtEnd));
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
                lookupOperationRegisterGroup,
                lookupOperationSupplierType,
                lookupOperationSupplier,
                lookupOperationDocumentAccountType,
                lookupOperationVoucherEgress,
                matchOperation,
                facetOperation
        );

        AggregationResults<VoucherEgressDetailAggregationResult> results = mongoTemplate.aggregate(
                aggregation, "account_voucher_egress_details", VoucherEgressDetailAggregationResult.class
        );

        VoucherEgressDetailAggregationResult aggregationResult = results.getUniqueMappedResult();
        List<VoucherEgressDetailEntity> data = aggregationResult != null ? aggregationResult.getData() : List.of();
        long totalCount = aggregationResult != null && !aggregationResult.getTotalCount().isEmpty() ? aggregationResult.getTotalCount().get(0).getTotal() : 0;

        return new PageImpl<>(data, pageable, totalCount);
    }

    // Class to map the aggregation result
    public static class VoucherEgressDetailAggregationResult {
        private List<VoucherEgressDetailEntity> data;
        private List<TotalCount> totalCount;

        public List<VoucherEgressDetailEntity> getData() {
            return data;
        }

        public void setData(List<VoucherEgressDetailEntity> data) {
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
