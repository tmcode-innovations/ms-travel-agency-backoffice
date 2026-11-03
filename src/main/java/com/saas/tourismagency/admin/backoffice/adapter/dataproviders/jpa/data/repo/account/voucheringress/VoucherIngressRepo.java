package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringress.VoucherIngressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherIngress;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account.VoucherIngressMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
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
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VoucherIngressRepo extends DelegatedSpringJpaRepo<VoucherIngressDomain, VoucherIngressEntity, String, VoucherIngressConverter, SetFieldDefaultVoucherIngress, VoucherIngressMongoRepo> {

    @Autowired
    private IVoucherIngressRepoTemp repositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public VoucherIngressRepo(VoucherIngressMongoRepo springRepo, VoucherIngressConverter converter, SetFieldDefaultVoucherIngress setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> VOUCHER_INGRESS_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify"),
            Map.entry("idCurrency", "currency.id"),
            Map.entry("dateStart", "date"),
            Map.entry("dateEnd", "date")
    );

    public PageDTO<VoucherIngressDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (VOUCHER_INGRESS_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = VOUCHER_INGRESS_ENTITY_FIELDS.get(key);
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
        }else{
            order.add(
                    Sort.Order.desc("createdAt")
            );
        }
        if (!sortErrorMessages.isEmpty()) {
            throw new ValidationException(sortErrorMessages);
        }
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();
        String name = null;
        String description = null;
        String identify = null;
        String idCurrency = null;
        String dateStart = null;
        String dateEnd = null;

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
                    case "idCurrency" -> {
                        idCurrency = entry.getValue();
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

        if (StringUtils.isNotEmpty(idCurrency)) {
            query.addCriteria(Criteria.where("currency.id").is(idCurrency));
        }

        if (StringUtils.isNotEmpty(dateStart) && StringUtils.isNotEmpty(dateEnd)) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateStart);
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateEnd);
            query.addCriteria(Criteria.where("date").gte(dtStart).lte(dtEnd));
        } else if (StringUtils.isNotEmpty(dateStart) && (dateEnd == null || StringUtils.isEmpty(dateEnd) || StringUtils.isBlank(dateEnd))) {
            LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(dateStart);
            query.addCriteria(Criteria.where("date").gte(dtStart));
        } else if (StringUtils.isNotEmpty(dateEnd) && (dateStart == null || StringUtils.isEmpty(dateStart) || StringUtils.isBlank(dateStart))) {
            LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(dateEnd);
            query.addCriteria(Criteria.where("date").lte(dtEnd));
        }

        query.fields().include("id", "identify", "date", "description", "currency", "importTotalSoles", "importTotalUsd");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.queryAllSimpleDomainCaseListVoucherIngress(pageConfig, query);
    }

    public PageDTO<VoucherIngressDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (VOUCHER_INGRESS_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = VOUCHER_INGRESS_ENTITY_FIELDS.get(key);
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

    public List<VoucherIngressDomain> findAll(Boolean deleted){
        VoucherIngressEntity example = new VoucherIngressEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<VoucherIngressDomain> entities = this.findAllWithExample(
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

    public VoucherIngressDomain findByIdVoucherIngressCaseGetModificationData(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "importTotalSoles", "importTotalUsd");

        return repositoryTemplate.queryOneIdSimpleDomainCaseGetModificationData(query);
    }

    public void updateIndexByIdSimpleDomainCaseUpdateVoucherIngressBasic(String id, Double importTotalSoles, Double importTotalUsd){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("importTotalSoles", importTotalSoles);
        update.set("importTotalUsd", importTotalUsd);

        repositoryTemplate.updateIdSimpleDomainCaseUpdateVoucherIngressBasic(query, update);
    }
}
