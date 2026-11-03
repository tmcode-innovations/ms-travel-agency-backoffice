package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringressdetail;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringressdetail.VoucherIngressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherIngressDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherIngressDetailEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account.VoucherIngressDetailMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VoucherIngressDetailRepo extends DelegatedSpringJpaRepo<VoucherIngressDetailDomain, VoucherIngressDetailEntity, String, VoucherIngressDetailConverter, SetFieldDefaultVoucherIngressDetail, VoucherIngressDetailMongoRepo> {

    @Autowired
    private IVoucherIngressDetailRepoTemp repositoryTemplate;

    @Autowired
    public VoucherIngressDetailRepo(VoucherIngressDetailMongoRepo springRepo, VoucherIngressDetailConverter converter, SetFieldDefaultVoucherIngressDetail setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> VOUCHER_INGRESS_DETAIL_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<VoucherIngressDetailDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (VOUCHER_INGRESS_DETAIL_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = VOUCHER_INGRESS_DETAIL_ENTITY_FIELDS.get(key);
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

    public PageDTO<VoucherIngressDetailDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (VOUCHER_INGRESS_DETAIL_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = VOUCHER_INGRESS_DETAIL_ENTITY_FIELDS.get(key);
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

    public List<VoucherIngressDetailDomain> findAll(Boolean deleted){
        VoucherIngressDetailEntity example = new VoucherIngressDetailEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<VoucherIngressDetailDomain> entities = this.findAllWithExample(
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

    public List<VoucherIngressDetailDomain> searchAllByVoucherIngress(String idVoucherIngress) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idVoucherIngress)) {
            query.addCriteria(Criteria.where("voucherIngress.id").is(idVoucherIngress));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public VoucherIngressDetailDomain searchAllByVoucherIngressAndVoucherIngressDetail(String idVoucherIngress, String idVoucherIngressDetail) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idVoucherIngress)) {
            query.addCriteria(Criteria.where("voucherIngress.id").is(idVoucherIngress));
        }

        if (StringUtils.isNotEmpty(idVoucherIngressDetail)) {
            query.addCriteria(Criteria.where("id").is(idVoucherIngressDetail));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query).getFirst();
    }

    public List<VoucherIngressDetailDomain> searchAllByVoucherIngressCaseListSimple(String idVoucherIngress) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idVoucherIngress)) {
            query.addCriteria(Criteria.where("voucherIngress.id").is(idVoucherIngress));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "description", "observation", "importSoles", "importUsd", "customer", "supplier", "registerGroup");

        return repositoryTemplate.queryAllSimpleDomainCaseShowVoucherIngressDetail(query);
    }

    public List<VoucherIngressDetailDomain> searchAllByVoucherIngressCaseListSimpleByRegisterGroup(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "description", "importSoles", "importUsd");

        return repositoryTemplate.queryAllSimpleDomainCaseShowVoucherIngressDetailByRegisterGroup(query);
    }

    public List<VoucherIngressDetailDomain> searchAllByVoucherIngressCaseListSimpleByDelete(String idVoucherIngress) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idVoucherIngress)) {
            query.addCriteria(Criteria.where("voucherIngress.id").is(idVoucherIngress));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryAllSimpleDomainCaseShowVoucherIngressDetailByDelete(query);
    }
}
