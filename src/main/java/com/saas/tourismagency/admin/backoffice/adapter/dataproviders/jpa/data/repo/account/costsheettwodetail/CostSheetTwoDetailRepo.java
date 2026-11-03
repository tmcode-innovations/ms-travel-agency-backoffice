package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.costsheettwodetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.costsheettwodetail.CostSheetTwoDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultCostSheetTwoDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.CostSheetTwoDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account.CostSheetTwoDetailMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.CostSheetTwoDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
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
public class CostSheetTwoDetailRepo extends DelegatedSpringJpaRepo<CostSheetTwoDetailDomain, CostSheetTwoDetailEntity, String, CostSheetTwoDetailConverter, SetFieldDefaultCostSheetTwoDetail, CostSheetTwoDetailMongoRepo> {

    @Autowired
    private ICostSheetTwoDetailRepoTemp repositoryTemplate;

    @Autowired
    public CostSheetTwoDetailRepo(CostSheetTwoDetailMongoRepo springRepo, CostSheetTwoDetailConverter converter, SetFieldDefaultCostSheetTwoDetail setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> COST_SHEET_TWO_DETAIL_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<CostSheetTwoDetailDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COST_SHEET_TWO_DETAIL_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COST_SHEET_TWO_DETAIL_ENTITY_FIELDS.get(key);
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

    public PageDTO<CostSheetTwoDetailDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (COST_SHEET_TWO_DETAIL_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = COST_SHEET_TWO_DETAIL_ENTITY_FIELDS.get(key);
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

    public List<CostSheetTwoDetailDomain> findAll(Boolean deleted){
        CostSheetTwoDetailEntity example = new CostSheetTwoDetailEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<CostSheetTwoDetailDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
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

    public List<CostSheetTwoDetailDomain> searchAllByCostSheetTwo(String idCostSheetTwo) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostSheetTwo)) {
            query.addCriteria(Criteria.where("costSheetTwo.id").is(idCostSheetTwo));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public CostSheetTwoDetailDomain searchAllByCostSheetTwoAndCostSheetTwoDetail(String idCostSheetTwo, String idCostSheetTwoDetail) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostSheetTwo)) {
            query.addCriteria(Criteria.where("costSheetTwo.id").is(idCostSheetTwo));
        }

        if (StringUtils.isNotEmpty(idCostSheetTwoDetail)) {
            query.addCriteria(Criteria.where("id").is(idCostSheetTwoDetail));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query).getFirst();
    }

    public List<CostSheetTwoDetailDomain> searchAllByCostSheetTwoCaseListSimple(String idCostSheetTwo) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostSheetTwo)) {
            query.addCriteria(Criteria.where("costSheetTwo.id").is(idCostSheetTwo));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "description", "identify", "noItem", "numberDoc", "netoGeneralUsd", "igvTotalGeneral", "invoiceSaleGeneral", "boletaSaleGeneral", "documentCobranzaGeneral", "supplier", "supplierType", "touristDestine", "servicePqtTourist", "documentAccountType", "departmentRegion", "voucherEgressDetailItem");

        return repositoryTemplate.queryAllSimpleDomainCaseShowCostSheetTwoDetail(query);
    }

    public List<CostSheetTwoDetailDomain> searchAllByCostSheetTwoCaseListSimpleOnlyNoItem(String idCostSheetTwo) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostSheetTwo)) {
            query.addCriteria(Criteria.where("costSheetTwo.id").is(idCostSheetTwo));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "description", "identify", "noItem");

        return repositoryTemplate.queryAllSimpleDomainCaseShowCostSheetTwoDetailOnlyNoItem(query);
    }

    public List<CostSheetTwoDetailDomain> searchAllByCostSheetTwoCaseListSimpleUpdateCalculate(String idCostSheetTwo) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostSheetTwo)) {
            query.addCriteria(Criteria.where("costSheetTwo.id").is(idCostSheetTwo));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "description", "identify", "voucherEgressDetailItem");

        return repositoryTemplate.queryAllSimpleDomainCaseShowCostSheetTwoDetailUpdateCalculate(query);
    }

    public List<CostSheetTwoDetailDomain> searchAllByCostSheetTwoCaseListSimpleByDelete(String idCostSheetTwo) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostSheetTwo)) {
            query.addCriteria(Criteria.where("costSheetTwo.id").is(idCostSheetTwo));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryAllSimpleDomainCaseShowCostSheetTwoDetailByDelete(query);
    }

    public List<CostSheetTwoDetailDomain> searchAllByCostSheetTwoCaseListSimpleByExport(String idCostSheetTwo) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idCostSheetTwo)) {
            query.addCriteria(Criteria.where("costSheetTwo.id").is(idCostSheetTwo));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "departmentRegion", "voucherEgressDetailItem", "netoImporteUsd", "netoImporteSoles", "totalImporteUsd", "totalImporteSoles", "link");

        return repositoryTemplate.queryAllSimpleDomainCaseShowCostSheetTwoDetailByExport(query);
    }
}
