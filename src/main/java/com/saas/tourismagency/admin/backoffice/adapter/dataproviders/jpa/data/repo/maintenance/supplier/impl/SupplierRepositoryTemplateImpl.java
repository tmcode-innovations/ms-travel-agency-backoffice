package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.ISupplierRepositoryTemplate;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.statistics.GroupedDataDomain;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplierRepositoryTemplateImpl implements ISupplierRepositoryTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private SupplierConverter converter;

    @Override
    public PageDTO<SupplierDomain> query(Pageable pageable, Query query) {
        query.limit(Integer.MAX_VALUE);
        Long totalCount = mongoTemplate.count(query, SupplierEntity.class);
        query.with(pageable);
        query.limit(pageable.getPageSize());
        List<SupplierEntity> results = mongoTemplate.find(query, SupplierEntity.class);
        Page<SupplierEntity> resultsWithPage  = PageableExecutionUtils.getPage(results, pageable, () -> totalCount);
        return converter.toDomainPage(resultsWithPage);
    }

    @Override
    public List<SupplierDomain> query(Query query) {
        List<SupplierEntity> items = mongoTemplate.find(query, SupplierEntity.class);
        return converter.toDomainAll(items);
    }

    @Override
    public IdBaseDomain queryOneId(Query query) {
        SupplierEntity entity = mongoTemplate.findOne(query, SupplierEntity.class);
        return converter.toDomainIdBase(entity);
    }

    @Override
    public List<SimpleBaseDomain> queryAllSimple(Query query) {
        List<SupplierEntity> items = mongoTemplate.find(query, SupplierEntity.class);
        return converter.toDomainAllSimple(items);
    }

    // Statistics

    @Override
    public Long countSupplier(Query query) {
        Long result = mongoTemplate.count(query, SupplierEntity.class);
        return result;
    }

    public List<GroupedDataDomain> aggregateAndFilter(String filterField, Object filterValue, String groupByField) {
        Criteria criteria = new Criteria()
                .andOperator(
                        Criteria.where(filterField).is(filterValue),
                        Criteria.where("deleted").is(false),
                        Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()),
                        Criteria.where("typeInheritance").in(
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
                        )
                );

        MatchOperation matchOperation = Aggregation.match(criteria);

        // Create a group operation to group documents
        GroupOperation groupOperation = Aggregation.group(groupByField).count().as("count");

        // Create an aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);

        // Execute the aggregation
        AggregationResults<GroupedDataDomain> results = mongoTemplate.aggregate(aggregation, "base_entities", GroupedDataDomain.class);

        return results.getMappedResults();
    }

    @Override
    public List<GroupedDataDomain> aggregateSupplierByType(String groupByField) {
        Criteria criteria = new Criteria()
                .andOperator(
                        Criteria.where("deleted").is(false),
                        Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()),
                        Criteria.where("typeInheritance").in(
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
                        )
                );

        MatchOperation matchOperation = Aggregation.match(criteria);

        // Create a group operation to group documents
        GroupOperation groupOperation = Aggregation.group(groupByField).count().as("count");

        // Create an aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);

        // Execute the aggregation
        AggregationResults<GroupedDataDomain> results = mongoTemplate.aggregate(aggregation, "base_entities", GroupedDataDomain.class);

        return results.getMappedResults();
    }

    @Override
    public List<SupplierDomain> queryAllSimpleDomainCaseToolUpdateSummary(Query query) {
        List<SupplierEntity> items = mongoTemplate.find(query, SupplierEntity.class);
        return converter.toDomainAllSimpleToDomainCaseToolUpdateSummary(items);
    }

    @Override
    public void updateIdSimpleDomainCaseUpdateToolSummary(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, SupplierEntity.class);
    }

    @Override
    public SupplierDomain queryOneIdWithDepartmentRegion(Query query) {
        SupplierEntity entity = mongoTemplate.findOne(query, SupplierEntity.class);
        return converter.toDomainWithDepartmentRegion(entity);
    }

    @Override
    public List<SupplierDomain> queryAllSimpleForServiceOperation(Query query) {
        List<SupplierEntity> items = mongoTemplate.find(query, SupplierEntity.class);
        return converter.toDomainAllSimpleForServiceOperation(items);
    }
}
