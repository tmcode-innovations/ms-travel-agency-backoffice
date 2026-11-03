package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping.RegisterBookingGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingGlamping;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingGlampingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking.RegisterBookingGlampingMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeInheritanceBookingEnum;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.repo.DelegatedSpringJpaRepo;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegisterBookingGlampingRepo extends DelegatedSpringJpaRepo<RegisterBookingGlampingDomain, RegisterBookingGlampingEntity, String, RegisterBookingGlampingConverter, SetFieldDefaultRegisterBookingGlamping, RegisterBookingGlampingMongoRepo> {

    @Autowired
    private IRegisterBookingGlamRepositoryTemplate repositoryTemplate;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public RegisterBookingGlampingRepo(RegisterBookingGlampingMongoRepo springRepo, RegisterBookingGlampingConverter converter, SetFieldDefaultRegisterBookingGlamping setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> REGISTER_BOOKING_GLAMPING_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify"),
            Map.entry("idSupplier", "supplier.id"),
            Map.entry("idRegisterGroup", "supplier.id"),
            Map.entry("createdAtStart", "createdAt"),
            Map.entry("createdAtEnd", "createdAt")
    );

    public PageDTO<RegisterBookingGlampingDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_BOOKING_GLAMPING_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_BOOKING_GLAMPING_ENTITY_FIELDS.get(key);
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
        String idSupplier = null;
        String idRegisterGroup = null;
        String createdAtStart = null;
        String createdAtEnd = null;

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
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "idRegisterGroup" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "createdAtStart" -> {
                        createdAtStart = entry.getValue();
                    }
                    case "createdAtEnd" -> {
                        createdAtEnd = entry.getValue();
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

        // Booking
        query.addCriteria(Criteria.where("typeInheritanceBooking").is(TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName()));

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

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        if (StringUtils.isNotEmpty(createdAtStart) && StringUtils.isNotEmpty(createdAtEnd)) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(createdAtStart));
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(createdAtEnd));
            query.addCriteria(Criteria.where("createdAt").gt(dtStart).lt(dtEnd));
        } else if (StringUtils.isNotEmpty(createdAtStart) && (createdAtEnd == null || StringUtils.isEmpty(createdAtEnd) || StringUtils.isBlank(createdAtEnd))) {
            LocalDateTime dtStart = dateUtil.toStartOfDay(dateUtil.formatIso8601ToLocalDate(createdAtStart));
            query.addCriteria(Criteria.where("createdAt").gte(dtStart));
        } else if (StringUtils.isNotEmpty(createdAtEnd) && (createdAtStart == null || StringUtils.isEmpty(createdAtStart) || StringUtils.isBlank(createdAtStart))) {
            LocalDateTime dtEnd = dateUtil.toEndOfDay(dateUtil.formatIso8601ToLocalDate(createdAtEnd));
            query.addCriteria(Criteria.where("createdAt").lte(dtEnd));
        }

        query.fields().include("id", "identify", "supplierType", "supplier", "createdAt", "stateBooking", "registerGroup");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.queryAllSimpleDomainCaseListRegisterBookingGlamping(pageConfig, query);
    }

    public PageDTO<RegisterBookingGlampingDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_BOOKING_GLAMPING_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_BOOKING_GLAMPING_ENTITY_FIELDS.get(key);
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

        // Booking
        query.addCriteria(Criteria.where("typeInheritanceBooking").is(TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName()));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Pageable pageConfig = PageRequest.of(
                searchDomain.getPage(),
                searchDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.query(pageConfig, query);
    }

    public List<RegisterBookingGlampingDomain> findAll(String typeInheritanceBooking){
        RegisterBookingGlampingEntity example = new RegisterBookingGlampingEntity();
        example.setTypeInheritanceBooking(typeInheritanceBooking);
        example.setDeleted(false);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<RegisterBookingGlampingDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public RegisterBookingGlampingDomain findByNameAndTypeInheritanceBooking(String name, String typeInheritanceBooking){
        RegisterBookingGlampingEntity entity = repo().findByNameAndTypeInheritanceBookingAndTenantAndDeleted(name, typeInheritanceBooking, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public RegisterBookingGlampingDomain findByIdentifyAndTypeInheritance(String identify, String typeInheritanceBooking) {
        RegisterBookingGlampingEntity entity = repo().findByIdentifyAndTypeInheritanceBookingAndTenantAndDeleted(identify, typeInheritanceBooking, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public RegisterBookingGlampingDomain searchByRegisterGroupAndSupplierAndActive(String idRegisterGroup, String idSupplier) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        query.addCriteria(Criteria.where("activeRegisterBooking").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<RegisterBookingGlampingDomain> list = repositoryTemplate.query(query);
        if (list != null && !list.isEmpty()){
            return list.getFirst();
        }
        return null;
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

    public RegisterBookingGlampingDomain findByIdRegisterGroupCaseValidationExistBooking(String idRegisterGroup, String idSupplier){
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            query.addCriteria(Criteria.where("supplier.id").is(idSupplier));
        }

        query.addCriteria(Criteria.where("activeRegisterBooking").is(true));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return repositoryTemplate.queryOneIdSimpleDomainCaseValidationExistBooking(query);
    }

    public List<RegisterBookingGlampingDomain> searchAllByRegisterGroupCaseChangeDate(String idRegisterGroup) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "initTour", "endTour", "initHourTour", "endHourTour");
        return repositoryTemplate.query(query);
    }

    public void updateChangeDateItineraryDate(String id, LocalDate initTour, LocalDate endTour, LocalDateTime initHourTour, LocalDateTime endHourTour) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(id)) {
            query.addCriteria(Criteria.where("id").is(id));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();
        if (initTour != null) {
            update.set("initTour", initTour);
        }
        if (endTour != null) {
            update.set("endTour", endTour);
        }
        if (initHourTour != null) {
            update.set("initHourTour", initHourTour);
        }
        if (endHourTour != null) {
            update.set("endHourTour", endHourTour);
        }
        update.set("updatedBy", new ObjectId(UserResolver.resolveUser().getId()));
        update.set("updatedAt", LocalDateTime.now());

        repositoryTemplate.updateChangeDateItineraryDate(query, update);
    }
}
