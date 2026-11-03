package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem.ItineraryItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultItineraryItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.ItineraryItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking.ItineraryItemMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupGuideTransferDriveDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.repo.DelegatedSpringJpaRepo;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItineraryItemRepo extends DelegatedSpringJpaRepo<ItineraryItemDomain, ItineraryItemEntity, String, ItineraryItemConverter, SetFieldDefaultItineraryItem, ItineraryItemMongoRepo> {

    @Autowired
    private IItineraryItemRepositoryTemplate repositoryTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public ItineraryItemRepo(ItineraryItemMongoRepo springRepo, ItineraryItemConverter converter, SetFieldDefaultItineraryItem setFieldDefault) {
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

        return repositoryTemplate.query(pageConfig, query);
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

        return repositoryTemplate.query(pageConfig, query);
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

        return repositoryTemplate.query(query);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroup(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<ItineraryItemDomain> searchAllByGuideTransferDrive(String idGuideTransferDrive) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idGuideTransferDrive)) {
            query.addCriteria(Criteria.where("guideTransferDrive.id").is(idGuideTransferDrive));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<RegisterGroupGuideTransferDriveDomain> searchAllGuideTransferDriveRegisterGroup() {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("guideTransferDrive.id", "registerGroup.id");

        return repositoryTemplate.queryAllGuideTransferDriveRegisterGroup(query);
    }

    public ItineraryItemDomain searchAllByRegisterGroupAndItineraryItem(String idRegisterGroup, String idItineraryItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idItineraryItem)) {
            query.addCriteria(Criteria.where("id").is(idItineraryItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query).getFirst();
    }

    public ItineraryItemDomain searchAllByRegisterGroupAndItineraryItemCaseVoucherEgress(String idRegisterGroup, String idItineraryItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idItineraryItem)) {
            query.addCriteria(Criteria.where("id").is(idItineraryItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "itineraryDate", "service", "tour");

        return repositoryTemplate.queryOneIdSimpleDomainCaseUpdateOrderItineraryVoucherEgress(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroup(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupAndSupplierType(String idItineraryDate, String idRegisterGroup, String idSupplierType) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplierType)) {
            query.addCriteria(Criteria.where("supplierType.id").is(idSupplierType));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public ItineraryItemDomain findByIdItineraryItemQuotation(String idItineraryItemQuotation) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryItemQuotation)) {
            query.addCriteria(Criteria.where("idItineraryItemQuotation").is(idItineraryItemQuotation));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> list = repositoryTemplate.query(query);
        if (list != null && !list.isEmpty()){
            return list.getFirst();
        }
        return null;
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupAndSupplier(String idRegisterGroup, String idSupplier) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<String> findDistinctRegisterGroupIdsBySupplier(String idSupplier) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        query.addCriteria(Criteria.where("registerGroup").ne(null));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("registerGroup.id");
        List<ItineraryItemEntity> items = mongoTemplate.find(query, ItineraryItemEntity.class);
        return items.stream()
                .filter(i -> i.getRegisterGroup() != null)
                .map(i -> i.getRegisterGroup().getId())
                .distinct()
                .collect(Collectors.toList());
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

    public List<SimpleBaseDomain> searchAllByRegisterGroupSimple(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDateTime");

        return repositoryTemplate.queryAllSimple(query);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomain(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDateTime");

        return repositoryTemplate.queryAllSimpleDomain(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseShowItinerary(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDateTime", "supplierType", "supplier", "service", "paxTotal", "paxAdultTotal", "paxChildrenTotal", "paxTcTotal", "paxTcLocalTotal", "paxGuideTotal", "price", "priceAdult", "priceChildren", "priceTc", "priceTcLocal", "priceGuide", "observation", "tour");

        return repositoryTemplate.queryAllSimpleDomainCaseShowItinerary(query);
    }

    public ItineraryItemDomain findByIdSimpleDomainCaseUpdateOrderItinerary(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return repositoryTemplate.queryOneIdSimpleDomainCaseUpdateOrderItinerary(query);
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

        repositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItinerary(query, update);
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

        repositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItinerary(query, update);
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

        repositoryTemplate.updateIdSimpleDomainCaseUpdateOrderItinerary(query, update);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime");

        return repositoryTemplate.queryAllSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "itineraryDate", "itineraryDateTime", "supplierType", "serviceGroup", "lodgingCantBreakfastItineraryDate", "lodgingCantBreakfastTcGuideItineraryDate", "lodgingCantBreakfastTcLocalGuideItineraryDate", "lodgingCantBreakfastGuideItineraryDate", "lodgeCantBreakfastItineraryDate", "lodgeCantBreakfastTcGuideItineraryDate", "lodgeCantBreakfastTcLocalGuideItineraryDate", "lodgeCantBreakfastGuideItineraryDate");

        return repositoryTemplate.queryAllSimpleDomainCaseUpdateOrderItinerary(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "supplier", "supplierType", "itineraryDateTime");

        return repositoryTemplate.queryAllSimpleDomainCaseGenerateBooking(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseVoucherEgressSupplier(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "supplier", "supplierType", "itineraryDateTime");

        return repositoryTemplate.queryAllSimpleDomainCaseVoucherEgressSupplier(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBookingAction(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "supplier", "supplierType", "itineraryDate", "service", "observation", "operatorPvt", "operatorSic", "lodgePvt", "lodgeSic", "itineraryDateTime", "paxTotal", "paxAdultTotal", "paxChildrenTotal", "paxTcTotal", "paxTcLocalTotal", "paxGuideTotal", "generatedBy");

        return repositoryTemplate.queryAllSimpleDomainCaseGenerateBookingAction(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBookingActionHotel(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "index",
                "supplier",
                "supplierType",
                "itineraryDate",
                "service",
                "serviceGroup",
                "observation",
                "itineraryDateTime",
                "paxTotal",
                "paxAdultTotal",
                "paxChildrenTotal",
                "paxTcTotal",
                "paxTcLocalTotal",
                "paxGuideTotal",
                "lodgingCantSwb",
                "lodgingCantMat",
                "lodgingCantTwin",
                "lodgingCantTrp",
                "lodgingCategory",
                "lodgingCategoryStr",
                "lodgingCantBreakfast",
                "lodgingCantLunch",
                "lodgingCantDinner",
                "lodgingCantBoxLunch",
                "lodgingCantBreakfastDateTime",
                "lodgingCantLunchDateTime",
                "lodgingCantDinnerDateTime",
                "lodgingCantBoxLunchDateTime",
                "lodgingCantBreakfastDate",
                "lodgingCantLunchDate",
                "lodgingCantDinnerDate",
                "lodgingCantBoxLunchDate",
                "lodgingCantBreakfastItineraryDate",
                "lodgingCantLunchItineraryDate",
                "lodgingCantDinnerItineraryDate",
                "lodgingCantBoxLunchItineraryDate",
                "lodgingCantNight",
                "lodgingCantBreakfastTcGuide",
                "lodgingCantLunchTcGuide",
                "lodgingCantDinnerTcGuide",
                "lodgingCantBoxLunchTcGuide",
                "lodgingCantBreakfastTcGuideDateTime",
                "lodgingCantLunchTcGuideDateTime",
                "lodgingCantDinnerTcGuideDateTime",
                "lodgingCantBoxLunchTcGuideDateTime",
                "lodgingCantBreakfastTcGuideDate",
                "lodgingCantLunchTcGuideDate",
                "lodgingCantDinnerTcGuideDate",
                "lodgingCantBoxLunchTcGuideDate",
                "lodgingCantBreakfastTcGuideItineraryDate",
                "lodgingCantLunchTcGuideItineraryDate",
                "lodgingCantDinnerTcGuideItineraryDate",
                "lodgingCantBoxLunchTcGuideItineraryDate",
                "lodgingCantSwbTcGuide",
                "lodgingCantTwinTcGuide",
                "lodgingCantBreakfastTcLocalGuide",
                "lodgingCantLunchTcLocalGuide",
                "lodgingCantDinnerTcLocalGuide",
                "lodgingCantBoxLunchTcLocalGuide",
                "lodgingCantBreakfastTcLocalGuideDateTime",
                "lodgingCantLunchTcLocalGuideDateTime",
                "lodgingCantDinnerTcLocalGuideDateTime",
                "lodgingCantBoxLunchTcLocalGuideDateTime",
                "lodgingCantBreakfastTcLocalGuideDate",
                "lodgingCantLunchTcLocalGuideDate",
                "lodgingCantDinnerTcLocalGuideDate",
                "lodgingCantBoxLunchTcLocalGuideDate",
                "lodgingCantBreakfastTcLocalGuideItineraryDate",
                "lodgingCantLunchTcLocalGuideItineraryDate",
                "lodgingCantDinnerTcLocalGuideItineraryDate",
                "lodgingCantBoxLunchTcLocalGuideItineraryDate",
                "lodgingCantSwbTcLocalGuide",
                "lodgingCantTwinTcLocalGuide",
                "lodgingCantBreakfastGuide",
                "lodgingCantLunchGuide",
                "lodgingCantDinnerGuide",
                "lodgingCantBoxLunchGuide",
                "lodgingCantBreakfastGuideDateTime",
                "lodgingCantLunchGuideDateTime",
                "lodgingCantDinnerGuideDateTime",
                "lodgingCantBoxLunchGuideDateTime",
                "lodgingCantBreakfastGuideDate",
                "lodgingCantLunchGuideDate",
                "lodgingCantDinnerGuideDate",
                "lodgingCantBoxLunchGuideDate",
                "lodgingCantBreakfastGuideItineraryDate",
                "lodgingCantLunchGuideItineraryDate",
                "lodgingCantDinnerGuideItineraryDate",
                "lodgingCantBoxLunchGuideItineraryDate",
                "lodgingCantSwbGuide",
                "lodgingCantTwinGuide",
                "lodgingGuideCantNight",
                "lodgeCantSwb",
                "lodgeCantMat",
                "lodgeCantTwin",
                "lodgeCantTrp",
                "lodgeCategory",
                "lodgeCategoryStr",
                "lodgeCantBreakfast",
                "lodgeCantLunch",
                "lodgeCantDinner",
                "lodgeCantBoxLunch",
                "lodgeCantBreakfastDateTime",
                "lodgeCantLunchDateTime",
                "lodgeCantDinnerDateTime",
                "lodgeCantBoxLunchDateTime",
                "lodgeCantBreakfastDate",
                "lodgeCantLunchDate",
                "lodgeCantDinnerDate",
                "lodgeCantBoxLunchDate",
                "lodgeCantBreakfastItineraryDate",
                "lodgeCantLunchItineraryDate",
                "lodgeCantDinnerItineraryDate",
                "lodgeCantBoxLunchItineraryDate",
                "lodgeCantNight",
                "lodgeCantBreakfastTcGuide",
                "lodgeCantLunchTcGuide",
                "lodgeCantDinnerTcGuide",
                "lodgeCantBoxLunchTcGuide",
                "lodgeCantBreakfastTcGuideDateTime",
                "lodgeCantLunchTcGuideDateTime",
                "lodgeCantDinnerTcGuideDateTime",
                "lodgeCantBoxLunchTcGuideDateTime",
                "lodgeCantBreakfastTcGuideDate",
                "lodgeCantLunchTcGuideDate",
                "lodgeCantDinnerTcGuideDate",
                "lodgeCantBoxLunchTcGuideDate",
                "lodgeCantBreakfastTcGuideItineraryDate",
                "lodgeCantLunchTcGuideItineraryDate",
                "lodgeCantDinnerTcGuideItineraryDate",
                "lodgeCantBoxLunchTcGuideItineraryDate",
                "lodgeCantSwbTcGuide",
                "lodgeCantTwinTcGuide",
                "lodgeTcCantNight",
                "lodgeCantBreakfastTcLocalGuide",
                "lodgeCantLunchTcLocalGuide",
                "lodgeCantDinnerTcLocalGuide",
                "lodgeCantBoxLunchTcLocalGuide",
                "lodgeCantBreakfastTcLocalGuideDateTime",
                "lodgeCantLunchTcLocalGuideDateTime",
                "lodgeCantDinnerTcLocalGuideDateTime",
                "lodgeCantBoxLunchTcLocalGuideDateTime",
                "lodgeCantBreakfastTcLocalGuideDate",
                "lodgeCantLunchTcLocalGuideDate",
                "lodgeCantDinnerTcLocalGuideDate",
                "lodgeCantBoxLunchTcLocalGuideDate",
                "lodgeCantBreakfastTcLocalGuideItineraryDate",
                "lodgeCantLunchTcLocalGuideItineraryDate",
                "lodgeCantDinnerTcLocalGuideItineraryDate",
                "lodgeCantBoxLunchTcLocalGuideItineraryDate",
                "lodgeCantSwbTcLocalGuide",
                "lodgeCantTwinTcLocalGuide",
                "lodgeTcLocalCantNight",
                "lodgeCantBreakfastGuide",
                "lodgeCantLunchGuide",
                "lodgeCantDinnerGuide",
                "lodgeCantBoxLunchGuide",
                "lodgeCantBreakfastGuideDateTime",
                "lodgeCantLunchGuideDateTime",
                "lodgeCantDinnerGuideDateTime",
                "lodgeCantBoxLunchGuideDateTime",
                "lodgeCantBreakfastGuideDate",
                "lodgeCantLunchGuideDate",
                "lodgeCantDinnerGuideDate",
                "lodgeCantBoxLunchGuideDate",
                "lodgeCantBreakfastGuideItineraryDate",
                "lodgeCantLunchGuideItineraryDate",
                "lodgeCantDinnerGuideItineraryDate",
                "lodgeCantBoxLunchGuideItineraryDate",
                "lodgeCantSwbGuide",
                "lodgeCantTwinGuide",
                "lodgeGuideCantNight",
                "lodgePvt",
                "lodgeSic"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseGenerateBookingActionHotel(query);
    }

    final Map<String, String> ITINERARY_ITEM_TRANSFER_CONTROL_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("idSupplier", "supplier.id"),
            Map.entry("idRegisterGroup", "registerGroup.id"),
            Map.entry("itineraryDateTimeStart", "itineraryDateTime"),
            Map.entry("itineraryDateTimeEnd", "itineraryDateTime")
    );

    public PageDTO<ItineraryItemDomain> searchAllFilterCaseTransferControlPage(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ITINERARY_ITEM_TRANSFER_CONTROL_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ITINERARY_ITEM_TRANSFER_CONTROL_ENTITY_FIELDS.get(key);
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

        String idSupplier = null;
        String idRegisterGroup = null;
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

        filterErrorMessages.addAll(sortErrorMessages);

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }

        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            query.addCriteria(Criteria.where("itineraryDateTime").gt(dtStart).lt(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            query.addCriteria(Criteria.where("itineraryDateTime").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            query.addCriteria(Criteria.where("itineraryDateTime").lte(dtEnd));
        }

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        query.addCriteria(Criteria.where("supplierType.id").is(supplierTypeDomainOperator.getId()));

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        query.fields().include(
                "id",
                "index",
                "supplier",
                "supplierType",
                "service",
                "registerGroup",
                "itineraryDate",
                "observation",
                "itineraryDateTime",
                "tour",
                "orderServiceItem",
                "paxTotal",
                "paxAdultTotal",
                "paxChildrenTotal",
                "paxTcTotal",
                "paxTcLocalTotal",
                "paxGuideTotal"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseTransferControl(pageConfig, query);
    }

    final Map<String, String> ITINERARY_ITEM_TRANSFER_CONTROL_BY_TOUR_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("idSupplier", "supplier.id"),
            Map.entry("idCustomer", "customer.id"),
            Map.entry("itineraryDateTimeStart", "itineraryDateTime"),
            Map.entry("itineraryDateTimeEnd", "itineraryDateTime")
    );

    public PageDTO<ItineraryItemDomain> searchAllFilterCaseTransferControlByTourBiblePage(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ITINERARY_ITEM_TRANSFER_CONTROL_BY_TOUR_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ITINERARY_ITEM_TRANSFER_CONTROL_BY_TOUR_ENTITY_FIELDS.get(key);
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
            order.add(Sort.Order.asc("itineraryDateTime"));
        }
        if (!sortErrorMessages.isEmpty()) {
            throw new ValidationException(sortErrorMessages);
        }
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();

        String idCustomer = null;
        String idSupplier = null;
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

        filterErrorMessages.addAll(sortErrorMessages);

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }

        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idCustomer)) {
            query.addCriteria(Criteria.where("customer.id").is(idCustomer));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            query.addCriteria(Criteria.where("itineraryDateTime").gt(dtStart).lt(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            query.addCriteria(Criteria.where("itineraryDateTime").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            query.addCriteria(Criteria.where("itineraryDateTime").lte(dtEnd));
        }

        /*SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        query.addCriteria(Criteria.where("supplierType.id").is(supplierTypeDomainOperator.getId()));*/

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        query.fields().include(
                "id",
                "index",
                "customer",
                "supplier",
                "supplierType",
                "service",
                "registerGroup",
                "itineraryDate",
                "observation",
                "itineraryDateTime",
                "tour",
                "lodgingForOperatorStr",
                "paxTotal",
                "paxAdultTotal",
                "stateItineraryService"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseTransferControlByTourBible(pageConfig, query);
    }

    public List<ItineraryItemDomain> searchAllFilterCaseTransferControlByTourBibleExport(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (ITINERARY_ITEM_TRANSFER_CONTROL_BY_TOUR_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = ITINERARY_ITEM_TRANSFER_CONTROL_BY_TOUR_ENTITY_FIELDS.get(key);
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
            order.add(Sort.Order.asc("itineraryDateTime"));
        }
        if (!sortErrorMessages.isEmpty()) {
            throw new ValidationException(sortErrorMessages);
        }
        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();

        String idCustomer = null;
        String idSupplier = null;
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

        filterErrorMessages.addAll(sortErrorMessages);

        if (!filterErrorMessages.isEmpty()) {//si hay, lanzo 422
            throw new ValidationException(filterErrorMessages);
        }

        Query query = new Query();

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        if (StringUtils.isNotEmpty(idCustomer)) {
            query.addCriteria(Criteria.where("customer.id").is(idCustomer));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            query.addCriteria(Criteria.where("itineraryDateTime").gt(dtStart).lt(dtEnd));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            query.addCriteria(Criteria.where("itineraryDateTime").gte(dtStart));
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            query.addCriteria(Criteria.where("itineraryDateTime").lte(dtEnd));
        }

        /*SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        query.addCriteria(Criteria.where("supplierType.id").is(supplierTypeDomainOperator.getId()));*/

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        query.fields().include(
                "id",
                "index",
                "customer",
                "supplier",
                "supplierType",
                "service",
                "registerGroup",
                "itineraryDate",
                "observation",
                "itineraryDateTime",
                "tour",
                "lodgingForOperatorStr",
                "paxTotal",
                "stateItineraryService"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseTransferControlByTourBibleExport(query);
    }

    public List<ItineraryItemDomain> searchAllFilterCaseTransferControl(ISearchFilterPageDomain searchFilterDomain) {
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

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        query.addCriteria(Criteria.where("supplierType.id").is(supplierTypeDomainOperator.getId()));

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        query.fields().include(
                "id",
                "index",
                "supplier",
                "supplierType",
                "service",
                "registerGroup",
                "itineraryDate",
                "observation",
                "itineraryDateTime",
                "tour"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseTransferControlExport(query);
    }

    public List<ItineraryItemDomain> searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseValidationExistBooking(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "supplier", "supplierType");

        return repositoryTemplate.queryAllSimpleDomainCaseValidationExistBooking(query);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "supplier", "supplierType");

        return repositoryTemplate.queryAllSimpleDomainCaseValidationExistBooking(query);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupAndItineraryDateSimpleDomainCaseDeleteTours(String idRegisterGroup, String idItineraryDate, String idTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
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

        return repositoryTemplate.queryAllSimpleDomainCaseDeleteTours(query);
    }

    public List<ItineraryItemDomain> searchAllSimpleDomainCaseToolUpdateCustomer() {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "registerGroup");

        return repositoryTemplate.queryAllSimpleDomainCaseToolUpdateCustomer(query);
    }

    public void updateCustomerByIdSimpleDomainCaseUpdateToolCustomer(String id, String idCustomer){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("customer", new ObjectId(idCustomer));

        repositoryTemplate.updateIdSimpleDomainCaseUpdateToolCustomer(query, update);
    }

    public List<ItineraryItemDomain> searchAllRegisterGroupAndSupplierSimpleDomainCaseServiceRequest(String idRegisterGroup, String idSupplier) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "service", "customer", "registerGroup", "lodgingForOperatorStr", "paxAdultTotal", "observation", "itineraryDate");

        return repositoryTemplate.queryAllSimpleDomainCaseServiceRequest(query);
    }

    public void updateCantByIdSimpleDomainCaseUpdateCantPax(String id, Integer paxTotal, Integer paxAdultTotal, Integer paxChildrenTotal, Integer paxTcTotal, Integer paxTcLocalTotal, Integer paxGuideTotal){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("paxTotal", paxTotal);
        update.set("paxAdultTotal", paxAdultTotal);
        update.set("paxChildrenTotal", paxChildrenTotal);
        update.set("paxTcTotal", paxTcTotal);
        update.set("paxTcLocalTotal", paxTcLocalTotal);
        update.set("paxGuideTotal", paxGuideTotal);

        repositoryTemplate.updateIdSimpleDomainCaseUpdateCantPax(query, update);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupAndItineraryDateAndCostTourSimpleDomainCaseDeleteTours(String idRegisterGroup, String idItineraryDate, String idTour, String idCostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
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

        return repositoryTemplate.queryAllSimpleDomainCaseDeleteTours(query);
    }

    public PageDTO<ItineraryItemDomain> searchAggregatePageableTransferControlByTourBible(ISearchFilterPageDomain searchFilterDomain) {
        // List<Sort.Order> order = new ArrayList<>();
        // order.add(Sort.Order.desc("itineraryDateTime"));

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(Sort.Direction.ASC, "itineraryDateTime")
        );
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableTransferControlByTourBible(searchFilterDomain, pageConfig);
        return converter.toDomainPageCaseTransferControlByTourBible(result);
    }

    public PageDTO<ItineraryItemDomain> searchAggregatePageableTransferControlBible(ISearchFilterPageDomain searchFilterDomain) {
        // List<Sort.Order> order = new ArrayList<>();
        // order.add(Sort.Order.desc("itineraryDateTime"));

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(Sort.Direction.ASC, "itineraryDateTime")
        );
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        ObjectId objectIdSupplierTypeOperator = new ObjectId(supplierTypeDomainOperator.getId());

        /*SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        ObjectId objectIdSupplierTypeTransport = new ObjectId(supplierTypeDomainTransport.getId());

        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        ObjectId objectIdSupplierTypeLodge = new ObjectId(supplierTypeDomainLodge.getId());*/

        List<ObjectId> objectIdList = List.of(objectIdSupplierTypeOperator);
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableTransferControlBible(searchFilterDomain, pageConfig, objectIdList);
        return converter.toDomainPageCaseTransferControlBible(result);
    }

    public List<ItineraryItemDomain> searchAggregateTransferControlByTourBible(ISearchFilterPageDomain searchFilterDomain) {
        List<ItineraryItemEntity> result = repositoryTemplate.aggregateTransferControlByTourBible(searchFilterDomain);
        return converter.toDomainPageCaseTransferControlByTourBibleExport(result);
    }

    public List<ItineraryItemDomain> searchAggregateTransferControlBible(ISearchFilterPageDomain searchFilterDomain) {
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        ObjectId objectIdSupplierTypeOperator = new ObjectId(supplierTypeDomainOperator.getId());

        /*SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        ObjectId objectIdSupplierTypeTransport = new ObjectId(supplierTypeDomainTransport.getId());

        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        ObjectId objectIdSupplierTypeLodge = new ObjectId(supplierTypeDomainLodge.getId());
*/
        List<ObjectId> objectIdList = List.of(objectIdSupplierTypeOperator);
        List<ItineraryItemEntity> result = repositoryTemplate.aggregateTransferControlBible(searchFilterDomain, objectIdList);
        return converter.toDomainPageCaseTransferControlBibleExport(result);
    }

    public PageDTO<ItineraryItemDomain> searchAggregatePageableTransferControlByServiceBible(ISearchFilterPageDomain searchFilterDomain) {
        // List<Sort.Order> order = new ArrayList<>();
        // order.add(Sort.Order.desc("itineraryDateTime"));

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(Sort.Direction.ASC, "itineraryDateTime")
        );
        Page<ItineraryItemEntity> result = repositoryTemplate.aggregatePageableTransferControlByServiceBible(searchFilterDomain, pageConfig);
        return converter.toDomainPageCaseTransferControlByServiceBible(result);
    }

    public List<ItineraryItemDomain> searchAggregateTransferControlByServiceBible(ISearchFilterPageDomain searchFilterDomain) {
        List<ItineraryItemEntity> result = repositoryTemplate.aggregateTransferControlByServiceBible(searchFilterDomain);
        return converter.toDomainPageCaseTransferControlByServiceBibleExport(result);
    }

    public ItineraryItemDomain searchOrderServiceItem(String idOrderServiceItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idOrderServiceItem)) {
            query.addCriteria(Criteria.where("orderServiceItem.id").is(idOrderServiceItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> result = repositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public ItineraryItemDomain searchRegisterBookingItemOperator(String idRegisterBookingItemOperator) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterBookingItemOperator)) {
            query.addCriteria(Criteria.where("registerBookingItemOperator.id").is(idRegisterBookingItemOperator));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> result = repositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public void updateCustomerByIdSimpleDomainCaseUpdateRegisterBookingItemOperator(String id, String idRegisterBookingItemOperator){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("registerBookingItemOperator", new ObjectId(idRegisterBookingItemOperator));

        repositoryTemplate.updateIdSimpleDomainCaseUpdateRegisterBookingItemOperator(query, update);
    }

    public void updateCustomerByIdSimpleDomainCaseUpdateRegisterBookingItemLodger(String id, String idRegisterBookingItemLodger){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("registerBookingItemLodger", new ObjectId(idRegisterBookingItemLodger));

        repositoryTemplate.updateIdSimpleDomainCaseUpdateRegisterBookingItemLodger(query, update);
    }

    public ItineraryItemDomain searchAllByRegisterGroupAndCostTour(String idRegisterGroup, String idICostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idICostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idICostTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> result = repositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public ItineraryItemDomain searchAllByRegisterGroupAndCostTourAndCostTourServiceItem(String idRegisterGroup, String idICostTour, String idICostTourServiceItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idICostTour)) {
            query.addCriteria(Criteria.where("costTour.id").is(idICostTour));
        }

        if (StringUtils.isNotEmpty(idICostTourServiceItem)) {
            query.addCriteria(Criteria.where("costTourServiceItem.id").is(idICostTourServiceItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ItineraryItemDomain> result = repositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomainCaseDelete(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryAllSimpleDomainCaseDeleteByRegisterGroup(query);
    }

    public ItineraryItemDomain searchAllRegisterGroupAndSupplierSimpleDomainCaseServiceRequestById(String idItineraryItem, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryItem)) {
            query.addCriteria(Criteria.where("id").is(idItineraryItem));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "service", "customer", "registerGroup", "lodgingForOperatorStr", "paxAdultTotal", "observation", "itineraryDate");

        List<ItineraryItemDomain> list = repositoryTemplate.queryAllSimpleDomainCaseServiceRequest(query);
        if (list != null && !list.isEmpty()){
            return list.getFirst();
        }
        return null;
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomainCaseAdvanced(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "index",
                "supplier",
                "supplierType",
                "itineraryDate",
                "service",
                "serviceGroup",
                "observation",
                "itineraryDateTime",
                "paxTotal",
                "paxAdultTotal",
                "paxChildrenTotal",
                "paxTcTotal",
                "paxTcLocalTotal",
                "paxGuideTotal",
                "lodgingCantSwb",
                "lodgingCantMat",
                "lodgingCantTwin",
                "lodgingCantTrp",
                "lodgingCategory",
                "lodgingCategoryStr",
                "lodgingCantBreakfast",
                "lodgingCantLunch",
                "lodgingCantDinner",
                "lodgingCantBoxLunch",
                "lodgingCantBreakfastDateTime",
                "lodgingCantLunchDateTime",
                "lodgingCantDinnerDateTime",
                "lodgingCantBoxLunchDateTime",
                "lodgingCantBreakfastDate",
                "lodgingCantLunchDate",
                "lodgingCantDinnerDate",
                "lodgingCantBoxLunchDate",
                "lodgingCantBreakfastItineraryDate",
                "lodgingCantLunchItineraryDate",
                "lodgingCantDinnerItineraryDate",
                "lodgingCantBoxLunchItineraryDate",
                "lodgingCantNight",
                "lodgingCantBreakfastTcGuide",
                "lodgingCantLunchTcGuide",
                "lodgingCantDinnerTcGuide",
                "lodgingCantBoxLunchTcGuide",
                "lodgingCantBreakfastTcGuideDateTime",
                "lodgingCantLunchTcGuideDateTime",
                "lodgingCantDinnerTcGuideDateTime",
                "lodgingCantBoxLunchTcGuideDateTime",
                "lodgingCantBreakfastTcGuideDate",
                "lodgingCantLunchTcGuideDate",
                "lodgingCantDinnerTcGuideDate",
                "lodgingCantBoxLunchTcGuideDate",
                "lodgingCantBreakfastTcGuideItineraryDate",
                "lodgingCantLunchTcGuideItineraryDate",
                "lodgingCantDinnerTcGuideItineraryDate",
                "lodgingCantBoxLunchTcGuideItineraryDate",
                "lodgingCantSwbTcGuide",
                "lodgingCantTwinTcGuide",
                "lodgingCantBreakfastTcLocalGuide",
                "lodgingCantLunchTcLocalGuide",
                "lodgingCantDinnerTcLocalGuide",
                "lodgingCantBoxLunchTcLocalGuide",
                "lodgingCantBreakfastTcLocalGuideDateTime",
                "lodgingCantLunchTcLocalGuideDateTime",
                "lodgingCantDinnerTcLocalGuideDateTime",
                "lodgingCantBoxLunchTcLocalGuideDateTime",
                "lodgingCantBreakfastTcLocalGuideDate",
                "lodgingCantLunchTcLocalGuideDate",
                "lodgingCantDinnerTcLocalGuideDate",
                "lodgingCantBoxLunchTcLocalGuideDate",
                "lodgingCantBreakfastTcLocalGuideItineraryDate",
                "lodgingCantLunchTcLocalGuideItineraryDate",
                "lodgingCantDinnerTcLocalGuideItineraryDate",
                "lodgingCantBoxLunchTcLocalGuideItineraryDate",
                "lodgingCantSwbTcLocalGuide",
                "lodgingCantTwinTcLocalGuide",
                "lodgingCantBreakfastGuide",
                "lodgingCantLunchGuide",
                "lodgingCantDinnerGuide",
                "lodgingCantBoxLunchGuide",
                "lodgingCantBreakfastGuideDateTime",
                "lodgingCantLunchGuideDateTime",
                "lodgingCantDinnerGuideDateTime",
                "lodgingCantBoxLunchGuideDateTime",
                "lodgingCantBreakfastGuideDate",
                "lodgingCantLunchGuideDate",
                "lodgingCantDinnerGuideDate",
                "lodgingCantBoxLunchGuideDate",
                "lodgingCantBreakfastGuideItineraryDate",
                "lodgingCantLunchGuideItineraryDate",
                "lodgingCantDinnerGuideItineraryDate",
                "lodgingCantBoxLunchGuideItineraryDate",
                "lodgingCantSwbGuide",
                "lodgingCantTwinGuide",
                "lodgingGuideCantNight",
                "lodgeCantSwb",
                "lodgeCantMat",
                "lodgeCantTwin",
                "lodgeCantTrp",
                "lodgeCategory",
                "lodgeCategoryStr",
                "lodgeCantBreakfast",
                "lodgeCantLunch",
                "lodgeCantDinner",
                "lodgeCantBoxLunch",
                "lodgeCantBreakfastDateTime",
                "lodgeCantLunchDateTime",
                "lodgeCantDinnerDateTime",
                "lodgeCantBoxLunchDateTime",
                "lodgeCantBreakfastDate",
                "lodgeCantLunchDate",
                "lodgeCantDinnerDate",
                "lodgeCantBoxLunchDate",
                "lodgeCantBreakfastItineraryDate",
                "lodgeCantLunchItineraryDate",
                "lodgeCantDinnerItineraryDate",
                "lodgeCantBoxLunchItineraryDate",
                "lodgeCantNight",
                "lodgeCantBreakfastTcGuide",
                "lodgeCantLunchTcGuide",
                "lodgeCantDinnerTcGuide",
                "lodgeCantBoxLunchTcGuide",
                "lodgeCantBreakfastTcGuideDateTime",
                "lodgeCantLunchTcGuideDateTime",
                "lodgeCantDinnerTcGuideDateTime",
                "lodgeCantBoxLunchTcGuideDateTime",
                "lodgeCantBreakfastTcGuideDate",
                "lodgeCantLunchTcGuideDate",
                "lodgeCantDinnerTcGuideDate",
                "lodgeCantBoxLunchTcGuideDate",
                "lodgeCantBreakfastTcGuideItineraryDate",
                "lodgeCantLunchTcGuideItineraryDate",
                "lodgeCantDinnerTcGuideItineraryDate",
                "lodgeCantBoxLunchTcGuideItineraryDate",
                "lodgeCantSwbTcGuide",
                "lodgeCantTwinTcGuide",
                "lodgeTcCantNight",
                "lodgeCantBreakfastTcLocalGuide",
                "lodgeCantLunchTcLocalGuide",
                "lodgeCantDinnerTcLocalGuide",
                "lodgeCantBoxLunchTcLocalGuide",
                "lodgeCantBreakfastTcLocalGuideDateTime",
                "lodgeCantLunchTcLocalGuideDateTime",
                "lodgeCantDinnerTcLocalGuideDateTime",
                "lodgeCantBoxLunchTcLocalGuideDateTime",
                "lodgeCantBreakfastTcLocalGuideDate",
                "lodgeCantLunchTcLocalGuideDate",
                "lodgeCantDinnerTcLocalGuideDate",
                "lodgeCantBoxLunchTcLocalGuideDate",
                "lodgeCantBreakfastTcLocalGuideItineraryDate",
                "lodgeCantLunchTcLocalGuideItineraryDate",
                "lodgeCantDinnerTcLocalGuideItineraryDate",
                "lodgeCantBoxLunchTcLocalGuideItineraryDate",
                "lodgeCantSwbTcLocalGuide",
                "lodgeCantTwinTcLocalGuide",
                "lodgeTcLocalCantNight",
                "lodgeCantBreakfastGuide",
                "lodgeCantLunchGuide",
                "lodgeCantDinnerGuide",
                "lodgeCantBoxLunchGuide",
                "lodgeCantBreakfastGuideDateTime",
                "lodgeCantLunchGuideDateTime",
                "lodgeCantDinnerGuideDateTime",
                "lodgeCantBoxLunchGuideDateTime",
                "lodgeCantBreakfastGuideDate",
                "lodgeCantLunchGuideDate",
                "lodgeCantDinnerGuideDate",
                "lodgeCantBoxLunchGuideDate",
                "lodgeCantBreakfastGuideItineraryDate",
                "lodgeCantLunchGuideItineraryDate",
                "lodgeCantDinnerGuideItineraryDate",
                "lodgeCantBoxLunchGuideItineraryDate",
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
                "operatorSic"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseAdvancedByRegisterGroup(query);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupAndSupplierSimpleDomainCaseAnnulled(String idRegisterGroup, String idSupplier) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryAllByRegisterGroupAndSupplierSimpleDomainCaseAnnulled(query);
    }

    public void updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryAnnulled(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        LocalDateTime updatedAt = LocalDateTime.now();

        Update update = new Update();

        update.set("registerBookingBase", null);

        update.set("updatedBy", new ObjectId(UserResolver.resolveUser().getId()));
        update.set("updatedAt", updatedAt);

        repositoryTemplate.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryAnnulled(query, update);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomainCaseExportItinerary(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "index",
                "serviceGroup",
                "supplier",
                "itineraryDate",
                "service",
                "guideTransport",
                "guideTransferDrive",
                "orderServiceItem",
                "observation",
                "itineraryDateTime",
                "paxTotal",
                "paxAdultTotal",
                "paxChildrenTotal",
                "paxTcTotal",
                "paxTcLocalTotal",
                "paxGuideTotal",
                "lodgingCantSwb",
                "lodgingCantMat",
                "lodgingCantTwin",
                "lodgingCantTrp",
                "lodgingCategory",
                "lodgingCategoryStr",
                "lodgingCantBreakfast",
                "lodgingCantLunch",
                "lodgingCantDinner",
                "lodgingCantBoxLunch",
                "lodgingCantBreakfastDateTime",
                "lodgingCantLunchDateTime",
                "lodgingCantDinnerDateTime",
                "lodgingCantBoxLunchDateTime",
                "lodgingCantBreakfastDate",
                "lodgingCantLunchDate",
                "lodgingCantDinnerDate",
                "lodgingCantBoxLunchDate",
                "lodgingCantBreakfastItineraryDate",
                "lodgingCantLunchItineraryDate",
                "lodgingCantDinnerItineraryDate",
                "lodgingCantBoxLunchItineraryDate",
                "lodgingCantNight",
                "lodgingCantBreakfastTcGuide",
                "lodgingCantLunchTcGuide",
                "lodgingCantDinnerTcGuide",
                "lodgingCantBoxLunchTcGuide",
                "lodgingCantBreakfastTcGuideDateTime",
                "lodgingCantLunchTcGuideDateTime",
                "lodgingCantDinnerTcGuideDateTime",
                "lodgingCantBoxLunchTcGuideDateTime",
                "lodgingCantBreakfastTcGuideDate",
                "lodgingCantLunchTcGuideDate",
                "lodgingCantDinnerTcGuideDate",
                "lodgingCantBoxLunchTcGuideDate",
                "lodgingCantBreakfastTcGuideItineraryDate",
                "lodgingCantLunchTcGuideItineraryDate",
                "lodgingCantDinnerTcGuideItineraryDate",
                "lodgingCantBoxLunchTcGuideItineraryDate",
                "lodgingCantSwbTcGuide",
                "lodgingCantTwinTcGuide",
                "lodgingCantBreakfastTcLocalGuide",
                "lodgingCantLunchTcLocalGuide",
                "lodgingCantDinnerTcLocalGuide",
                "lodgingCantBoxLunchTcLocalGuide",
                "lodgingCantBreakfastTcLocalGuideDateTime",
                "lodgingCantLunchTcLocalGuideDateTime",
                "lodgingCantDinnerTcLocalGuideDateTime",
                "lodgingCantBoxLunchTcLocalGuideDateTime",
                "lodgingCantBreakfastTcLocalGuideDate",
                "lodgingCantLunchTcLocalGuideDate",
                "lodgingCantDinnerTcLocalGuideDate",
                "lodgingCantBoxLunchTcLocalGuideDate",
                "lodgingCantBreakfastTcLocalGuideItineraryDate",
                "lodgingCantLunchTcLocalGuideItineraryDate",
                "lodgingCantDinnerTcLocalGuideItineraryDate",
                "lodgingCantBoxLunchTcLocalGuideItineraryDate",
                "lodgingCantSwbTcLocalGuide",
                "lodgingCantTwinTcLocalGuide",
                "lodgingCantBreakfastGuide",
                "lodgingCantLunchGuide",
                "lodgingCantDinnerGuide",
                "lodgingCantBoxLunchGuide",
                "lodgingCantBreakfastGuideDateTime",
                "lodgingCantLunchGuideDateTime",
                "lodgingCantDinnerGuideDateTime",
                "lodgingCantBoxLunchGuideDateTime",
                "lodgingCantBreakfastGuideDate",
                "lodgingCantLunchGuideDate",
                "lodgingCantDinnerGuideDate",
                "lodgingCantBoxLunchGuideDate",
                "lodgingCantBreakfastGuideItineraryDate",
                "lodgingCantLunchGuideItineraryDate",
                "lodgingCantDinnerGuideItineraryDate",
                "lodgingCantBoxLunchGuideItineraryDate",
                "lodgingCantSwbGuide",
                "lodgingCantTwinGuide",
                "lodgingGuideCantNight",
                "lodgeCantSwb",
                "lodgeCantMat",
                "lodgeCantTwin",
                "lodgeCantTrp",
                "lodgeCategory",
                "lodgeCategoryStr",
                "lodgeCantBreakfast",
                "lodgeCantLunch",
                "lodgeCantDinner",
                "lodgeCantBoxLunch",
                "lodgeCantBreakfastDateTime",
                "lodgeCantLunchDateTime",
                "lodgeCantDinnerDateTime",
                "lodgeCantBoxLunchDateTime",
                "lodgeCantBreakfastDate",
                "lodgeCantLunchDate",
                "lodgeCantDinnerDate",
                "lodgeCantBoxLunchDate",
                "lodgeCantBreakfastItineraryDate",
                "lodgeCantLunchItineraryDate",
                "lodgeCantDinnerItineraryDate",
                "lodgeCantBoxLunchItineraryDate",
                "lodgeCantNight",
                "lodgeCantBreakfastTcGuide",
                "lodgeCantLunchTcGuide",
                "lodgeCantDinnerTcGuide",
                "lodgeCantBoxLunchTcGuide",
                "lodgeCantBreakfastTcGuideDateTime",
                "lodgeCantLunchTcGuideDateTime",
                "lodgeCantDinnerTcGuideDateTime",
                "lodgeCantBoxLunchTcGuideDateTime",
                "lodgeCantBreakfastTcGuideDate",
                "lodgeCantLunchTcGuideDate",
                "lodgeCantDinnerTcGuideDate",
                "lodgeCantBoxLunchTcGuideDate",
                "lodgeCantBreakfastTcGuideItineraryDate",
                "lodgeCantLunchTcGuideItineraryDate",
                "lodgeCantDinnerTcGuideItineraryDate",
                "lodgeCantBoxLunchTcGuideItineraryDate",
                "lodgeCantSwbTcGuide",
                "lodgeCantTwinTcGuide",
                "lodgeTcCantNight",
                "lodgeCantBreakfastTcLocalGuide",
                "lodgeCantLunchTcLocalGuide",
                "lodgeCantDinnerTcLocalGuide",
                "lodgeCantBoxLunchTcLocalGuide",
                "lodgeCantBreakfastTcLocalGuideDateTime",
                "lodgeCantLunchTcLocalGuideDateTime",
                "lodgeCantDinnerTcLocalGuideDateTime",
                "lodgeCantBoxLunchTcLocalGuideDateTime",
                "lodgeCantBreakfastTcLocalGuideDate",
                "lodgeCantLunchTcLocalGuideDate",
                "lodgeCantDinnerTcLocalGuideDate",
                "lodgeCantBoxLunchTcLocalGuideDate",
                "lodgeCantBreakfastTcLocalGuideItineraryDate",
                "lodgeCantLunchTcLocalGuideItineraryDate",
                "lodgeCantDinnerTcLocalGuideItineraryDate",
                "lodgeCantBoxLunchTcLocalGuideItineraryDate",
                "lodgeCantSwbTcLocalGuide",
                "lodgeCantTwinTcLocalGuide",
                "lodgeTcLocalCantNight",
                "lodgeCantBreakfastGuide",
                "lodgeCantLunchGuide",
                "lodgeCantDinnerGuide",
                "lodgeCantBoxLunchGuide",
                "lodgeCantBreakfastGuideDateTime",
                "lodgeCantLunchGuideDateTime",
                "lodgeCantDinnerGuideDateTime",
                "lodgeCantBoxLunchGuideDateTime",
                "lodgeCantBreakfastGuideDate",
                "lodgeCantLunchGuideDate",
                "lodgeCantDinnerGuideDate",
                "lodgeCantBoxLunchGuideDate",
                "lodgeCantBreakfastGuideItineraryDate",
                "lodgeCantLunchGuideItineraryDate",
                "lodgeCantDinnerGuideItineraryDate",
                "lodgeCantBoxLunchGuideItineraryDate",
                "lodgeCantSwbGuide",
                "lodgeCantTwinGuide",
                "lodgeGuideCantNight",
                "lodgePvt",
                "lodgeSic",
                "operatorPvt",
                "operatorSic",
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
                "restaurantCantBoxLunchDrive"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseExportItineraryByRegisterGroup(query);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomainCaseProgram(String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "supplierType", "itineraryDate", "paxAdultTotal", "paxTotal", "paxChildrenTotal", "paxTcTotal", "paxTcLocalTotal", "paxGuideTotal", "serviceGroup");

        return repositoryTemplate.queryAllByRegisterGroupSimpleDomainCaseProgram(query);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastItineraryDate", null);
        }

        update.set("lodgingCantBreakfast", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTc(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastTcGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastTcGuideItineraryDate", null);
        }
        update.set("lodgingCantBreakfastTcGuide", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocal(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastTcLocalGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastTcLocalGuideItineraryDate", null);
        }
        update.set("lodgingCantBreakfastTcLocalGuide", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuide(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastGuideItineraryDate", null);
        }
        update.set("lodgingCantBreakfastGuide", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryLodge(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastItineraryDate", null);
        }
        update.set("lodgeCantBreakfast", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLodge(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastTcGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastTcGuideItineraryDate", null);
        }
        update.set("lodgeCantBreakfastTcGuide", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocalLodge(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastTcLocalGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastTcLocalGuideItineraryDate", null);
        }
        update.set("lodgeCantBreakfastTcLocalGuide", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuideLodge(String id, String idItineraryDate, Integer cantBreakfast){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastGuideItineraryDate", null);
        }
        update.set("lodgeCantBreakfastGuide", cantBreakfast);

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastTcGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastTcGuideItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocalOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastTcLocalGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastTcLocalGuideItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuideOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgingCantBreakfastGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgingCantBreakfastGuideItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryLodgeOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLodgeOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastTcGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastTcGuideItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocalLodgeOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastTcLocalGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastTcLocalGuideItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public void updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuideLodgeOnlyItineraryDate(String id, String idItineraryDate){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idItineraryDate != null && !idItineraryDate.isEmpty()){
            update.set("lodgeCantBreakfastGuideItineraryDate", new ObjectId(idItineraryDate));
        }else{
            update.set("lodgeCantBreakfastGuideItineraryDate", null);
        }

        repositoryTemplate.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(query, update);
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(String idRegisterGroup, String idSupplier) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(query);
    }

    public void updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(String id, String idRegisterBookingBase){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        if (idRegisterBookingBase != null && !idRegisterBookingBase.isEmpty()){
            update.set("registerBookingBase", new ObjectId(idRegisterBookingBase));
        }else{
            update.set("registerBookingBase", null);
        }

        repositoryTemplate.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(query, update);
    }

    public void updateIdSimpleDomainCaseCustomerModification(String id, String idCustomer){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("customer", new ObjectId(idCustomer));

        repositoryTemplate.updateIdSimpleDomainCaseCustomerModification(query, update);
    }

    public ItineraryItemDomain findSimpleDomainCaseVoucherEgress(String idItineraryItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryItem)) {
            query.addCriteria(Criteria.where("id").is(idItineraryItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "paxTotal");

        List<ItineraryItemDomain> list = repositoryTemplate.queryAllSimpleDomainCaseVoucherEgress(query);
        if (list != null && !list.isEmpty()){
            return list.getFirst();
        }
        return null;
    }

    public List<ItineraryItemDomain> searchAllByRegisterGroupSimpleDomainCaseResendFileToSale(String idItineraryDate, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idItineraryDate)) {
            query.addCriteria(Criteria.where("itineraryDate.id").is(idItineraryDate));
        }

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
                "id",
                "index",
                "supplier",
                "supplierType",
                "itineraryDate",
                "service",
                "serviceGroup",
                "observation",
                "itineraryDateTime",
                "paxTotal",
                "paxAdultTotal",
                "paxChildrenTotal",
                "paxTcTotal",
                "paxTcLocalTotal",
                "paxGuideTotal",
                "departmentRegion",
                "tour",
                "lodgingCantSwb",
                "lodgingCantMat",
                "lodgingCantTwin",
                "lodgingCantTrp",
                "lodgingCategory",
                "lodgingCategoryStr",
                "lodgingCantBreakfast",
                "lodgingCantLunch",
                "lodgingCantDinner",
                "lodgingCantBoxLunch",
                "lodgingCantBreakfastDateTime",
                "lodgingCantLunchDateTime",
                "lodgingCantDinnerDateTime",
                "lodgingCantBoxLunchDateTime",
                "lodgingCantBreakfastDate",
                "lodgingCantLunchDate",
                "lodgingCantDinnerDate",
                "lodgingCantBoxLunchDate",
                "lodgingCantBreakfastItineraryDate",
                "lodgingCantLunchItineraryDate",
                "lodgingCantDinnerItineraryDate",
                "lodgingCantBoxLunchItineraryDate",
                "lodgingCantNight",
                "lodgingCantBreakfastTcGuide",
                "lodgingCantLunchTcGuide",
                "lodgingCantDinnerTcGuide",
                "lodgingCantBoxLunchTcGuide",
                "lodgingCantBreakfastTcGuideDateTime",
                "lodgingCantLunchTcGuideDateTime",
                "lodgingCantDinnerTcGuideDateTime",
                "lodgingCantBoxLunchTcGuideDateTime",
                "lodgingCantBreakfastTcGuideDate",
                "lodgingCantLunchTcGuideDate",
                "lodgingCantDinnerTcGuideDate",
                "lodgingCantBoxLunchTcGuideDate",
                "lodgingCantBreakfastTcGuideItineraryDate",
                "lodgingCantLunchTcGuideItineraryDate",
                "lodgingCantDinnerTcGuideItineraryDate",
                "lodgingCantBoxLunchTcGuideItineraryDate",
                "lodgingCantSwbTcGuide",
                "lodgingCantTwinTcGuide",
                "lodgingCantBreakfastTcLocalGuide",
                "lodgingCantLunchTcLocalGuide",
                "lodgingCantDinnerTcLocalGuide",
                "lodgingCantBoxLunchTcLocalGuide",
                "lodgingCantBreakfastTcLocalGuideDateTime",
                "lodgingCantLunchTcLocalGuideDateTime",
                "lodgingCantDinnerTcLocalGuideDateTime",
                "lodgingCantBoxLunchTcLocalGuideDateTime",
                "lodgingCantBreakfastTcLocalGuideDate",
                "lodgingCantLunchTcLocalGuideDate",
                "lodgingCantDinnerTcLocalGuideDate",
                "lodgingCantBoxLunchTcLocalGuideDate",
                "lodgingCantBreakfastTcLocalGuideItineraryDate",
                "lodgingCantLunchTcLocalGuideItineraryDate",
                "lodgingCantDinnerTcLocalGuideItineraryDate",
                "lodgingCantBoxLunchTcLocalGuideItineraryDate",
                "lodgingCantSwbTcLocalGuide",
                "lodgingCantTwinTcLocalGuide",
                "lodgingCantBreakfastGuide",
                "lodgingCantLunchGuide",
                "lodgingCantDinnerGuide",
                "lodgingCantBoxLunchGuide",
                "lodgingCantBreakfastGuideDateTime",
                "lodgingCantLunchGuideDateTime",
                "lodgingCantDinnerGuideDateTime",
                "lodgingCantBoxLunchGuideDateTime",
                "lodgingCantBreakfastGuideDate",
                "lodgingCantLunchGuideDate",
                "lodgingCantDinnerGuideDate",
                "lodgingCantBoxLunchGuideDate",
                "lodgingCantBreakfastGuideItineraryDate",
                "lodgingCantLunchGuideItineraryDate",
                "lodgingCantDinnerGuideItineraryDate",
                "lodgingCantBoxLunchGuideItineraryDate",
                "lodgingCantSwbGuide",
                "lodgingCantTwinGuide",
                "lodgingGuideCantNight",
                "lodgeCantSwb",
                "lodgeCantMat",
                "lodgeCantTwin",
                "lodgeCantTrp",
                "lodgeCategory",
                "lodgeCategoryStr",
                "lodgeCantBreakfast",
                "lodgeCantLunch",
                "lodgeCantDinner",
                "lodgeCantBoxLunch",
                "lodgeCantBreakfastDateTime",
                "lodgeCantLunchDateTime",
                "lodgeCantDinnerDateTime",
                "lodgeCantBoxLunchDateTime",
                "lodgeCantBreakfastDate",
                "lodgeCantLunchDate",
                "lodgeCantDinnerDate",
                "lodgeCantBoxLunchDate",
                "lodgeCantBreakfastItineraryDate",
                "lodgeCantLunchItineraryDate",
                "lodgeCantDinnerItineraryDate",
                "lodgeCantBoxLunchItineraryDate",
                "lodgeCantNight",
                "lodgeCantBreakfastTcGuide",
                "lodgeCantLunchTcGuide",
                "lodgeCantDinnerTcGuide",
                "lodgeCantBoxLunchTcGuide",
                "lodgeCantBreakfastTcGuideDateTime",
                "lodgeCantLunchTcGuideDateTime",
                "lodgeCantDinnerTcGuideDateTime",
                "lodgeCantBoxLunchTcGuideDateTime",
                "lodgeCantBreakfastTcGuideDate",
                "lodgeCantLunchTcGuideDate",
                "lodgeCantDinnerTcGuideDate",
                "lodgeCantBoxLunchTcGuideDate",
                "lodgeCantBreakfastTcGuideItineraryDate",
                "lodgeCantLunchTcGuideItineraryDate",
                "lodgeCantDinnerTcGuideItineraryDate",
                "lodgeCantBoxLunchTcGuideItineraryDate",
                "lodgeCantSwbTcGuide",
                "lodgeCantTwinTcGuide",
                "lodgeTcCantNight",
                "lodgeCantBreakfastTcLocalGuide",
                "lodgeCantLunchTcLocalGuide",
                "lodgeCantDinnerTcLocalGuide",
                "lodgeCantBoxLunchTcLocalGuide",
                "lodgeCantBreakfastTcLocalGuideDateTime",
                "lodgeCantLunchTcLocalGuideDateTime",
                "lodgeCantDinnerTcLocalGuideDateTime",
                "lodgeCantBoxLunchTcLocalGuideDateTime",
                "lodgeCantBreakfastTcLocalGuideDate",
                "lodgeCantLunchTcLocalGuideDate",
                "lodgeCantDinnerTcLocalGuideDate",
                "lodgeCantBoxLunchTcLocalGuideDate",
                "lodgeCantBreakfastTcLocalGuideItineraryDate",
                "lodgeCantLunchTcLocalGuideItineraryDate",
                "lodgeCantDinnerTcLocalGuideItineraryDate",
                "lodgeCantBoxLunchTcLocalGuideItineraryDate",
                "lodgeCantSwbTcLocalGuide",
                "lodgeCantTwinTcLocalGuide",
                "lodgeTcLocalCantNight",
                "lodgeCantBreakfastGuide",
                "lodgeCantLunchGuide",
                "lodgeCantDinnerGuide",
                "lodgeCantBoxLunchGuide",
                "lodgeCantBreakfastGuideDateTime",
                "lodgeCantLunchGuideDateTime",
                "lodgeCantDinnerGuideDateTime",
                "lodgeCantBoxLunchGuideDateTime",
                "lodgeCantBreakfastGuideDate",
                "lodgeCantLunchGuideDate",
                "lodgeCantDinnerGuideDate",
                "lodgeCantBoxLunchGuideDate",
                "lodgeCantBreakfastGuideItineraryDate",
                "lodgeCantLunchGuideItineraryDate",
                "lodgeCantDinnerGuideItineraryDate",
                "lodgeCantBoxLunchGuideItineraryDate",
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
                "operatorSic"
        );

        return repositoryTemplate.queryAllSimpleDomainCaseResendFileToSale(query);
    }

    public void updateIdItineraryItemQuotationSimpleDomainCaseResend(String id, String idItineraryItemQuotation){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();

        update.set("idItineraryItemQuotation", idItineraryItemQuotation);

        repositoryTemplate.updateIdSimpleDomainCaseUpdateIdItineraryItemQuotation(query, update);
    }
}
