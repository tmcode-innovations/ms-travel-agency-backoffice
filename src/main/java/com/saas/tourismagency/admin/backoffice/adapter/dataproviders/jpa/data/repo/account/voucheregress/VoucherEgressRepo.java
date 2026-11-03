package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregress.VoucherEgressConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherEgress;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.account.VoucherEgressEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.account.VoucherEgressMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.ValueTypeShowSupplierEnum;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.VoucherStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
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
public class VoucherEgressRepo extends DelegatedSpringJpaRepo<VoucherEgressDomain, VoucherEgressEntity, String, VoucherEgressConverter, SetFieldDefaultVoucherEgress, VoucherEgressMongoRepo> {

    @Autowired
    private IVoucherEgressRepoTemp repositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public VoucherEgressRepo(VoucherEgressMongoRepo springRepo, VoucherEgressConverter converter, SetFieldDefaultVoucherEgress setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> VOUCHER_EGRESS_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify"),
            Map.entry("idCurrency", "currency.id"),
            Map.entry("idBank", "bank.id"),
            Map.entry("voucherState", "voucherState"),
            Map.entry("voucherType", "voucherType"),
            Map.entry("dateStart", "date"),
            Map.entry("dateEnd", "date")
    );

    public PageDTO<VoucherEgressDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (VOUCHER_EGRESS_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = VOUCHER_EGRESS_ENTITY_FIELDS.get(key);
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
        String idBank = null;
        String voucherState = null;
        String voucherType = null;
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
                    case "idBank" -> {
                        idBank = entry.getValue();
                    }
                    case "voucherState" -> {
                        voucherState = entry.getValue();
                    }
                    case "voucherType" -> {
                        voucherType = entry.getValue();
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

        if (StringUtils.isNotEmpty(idBank)) {
            query.addCriteria(Criteria.where("bank.id").is(idBank));
        }

        if (StringUtils.isNotEmpty(voucherState)) {
            query.addCriteria(Criteria.where("voucherState").is(voucherState));
        }

        if (StringUtils.isNotEmpty(voucherType)) {
            query.addCriteria(Criteria.where("voucherType").is(voucherType));
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

        query.fields().include("id", "identify", "date", "description", "currency", "bank", "parent", "voucherDetraction", "voucherState", "voucherType", "noCheck", "importSoles", "importUsd", "detractionSoles", "detractionUsd", "importTotalSoles", "importTotalUsd", "registerGroup", "supplier", "supplierType", "departmentRegion", "valueTypeShowSupplier", "createdBy");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.queryAllSimpleDomainCaseListVoucherEgress(pageConfig, query);
    }

    public PageDTO<VoucherEgressDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (VOUCHER_EGRESS_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = VOUCHER_EGRESS_ENTITY_FIELDS.get(key);
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

    public List<VoucherEgressDomain> findAll(Boolean deleted){
        VoucherEgressEntity example = new VoucherEgressEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<VoucherEgressDomain> entities = this.findAllWithExample(
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

        query.fields().include("id", "name", "identify", "description");

        return repositoryTemplate.queryAllSimple(query);
    }

    public List<VoucherEgressDomain> findAllSimplePending(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("voucherState").is(VoucherStateEnum.PENDING.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "identify", "description");

        return repositoryTemplate.queryAllPending(query);
    }

    public List<VoucherEgressDomain> findAllSimplePendingBooking(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("voucherState").is(VoucherStateEnum.PENDING.getName()));

        query.addCriteria(Criteria.where("valueTypeShowSupplier").is(ValueTypeShowSupplierEnum.BOOKIG.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "identify", "description");

        return repositoryTemplate.queryAllPending(query);
    }

    public VoucherEgressDomain findByIdVoucherEgressCaseGetModificationData(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "importTotalSoles", "importTotalUsd", "importSoles", "importUsd", "detractionSoles", "detractionUsd");

        return repositoryTemplate.queryOneIdSimpleDomainCaseGetModificationData(query);
    }

    public void updateIndexByIdSimpleDomainCaseUpdateVoucherEgressBasic(String id, Double importTotalSoles, Double importTotalUsd, Double importSoles, Double importUsd, Double detractionSoles, Double detractionUsd){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("importTotalSoles", importTotalSoles);
        update.set("importTotalUsd", importTotalUsd);

        update.set("importSoles", importSoles);
        update.set("importUsd", importUsd);

        update.set("detractionSoles", detractionSoles);
        update.set("detractionUsd", detractionUsd);

        repositoryTemplate.updateIdSimpleDomainCaseUpdateVoucherEgressBasic(query, update);
    }

    public List<VoucherEgressDomain> searchAllByVoucherEgressCaseListSimpleByParent(String idVoucherEgressParent) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idVoucherEgressParent)) {
            query.addCriteria(Criteria.where("parent.id").is(idVoucherEgressParent));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name", "description");

        return repositoryTemplate.queryAllSimpleDomainCaseVoucherEgressByParent(query);
    }
}
