package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodger.RegisterBookingItemLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemLodger;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgerEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking.RegisterBookingItemLodgerMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgerDomain;
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
public class RegisterBookingItemLodgerRepo extends DelegatedSpringJpaRepo<RegisterBookingItemLodgerDomain, RegisterBookingItemLodgerEntity, String, RegisterBookingItemLodgerConverter, SetFieldDefaultRegisterBookingItemLodger, RegisterBookingItemLodgerMongoRepo> {

    @Autowired
    private IRegisterBookingItemLodgerRepositoryTemplate repositoryTemplate;

    @Autowired
    public RegisterBookingItemLodgerRepo(RegisterBookingItemLodgerMongoRepo springRepo, RegisterBookingItemLodgerConverter converter, SetFieldDefaultRegisterBookingItemLodger setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> REGISTER_BOOKING_ITEM_LODGER_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<RegisterBookingItemLodgerDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_BOOKING_ITEM_LODGER_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_BOOKING_ITEM_LODGER_ENTITY_FIELDS.get(key);
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

    public PageDTO<RegisterBookingItemLodgerDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_BOOKING_ITEM_LODGER_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_BOOKING_ITEM_LODGER_ENTITY_FIELDS.get(key);
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

    public List<RegisterBookingItemLodgerDomain> findAll(Boolean deleted){
        RegisterBookingItemLodgerEntity example = new RegisterBookingItemLodgerEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<RegisterBookingItemLodgerDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public RegisterBookingItemLodgerDomain findByName(String name){
        RegisterBookingItemLodgerEntity entity = repo().findByNameAndTenantAndDeleted(name, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public RegisterBookingItemLodgerDomain findByIdentify(String identify){
        RegisterBookingItemLodgerEntity entity = repo().findByIdentifyAndTenantAndDeleted(identify, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public List<RegisterBookingItemLodgerDomain> searchAllByBooking(String idBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public RegisterBookingItemLodgerDomain searchBookingAndRegisterBookingItem(String idBooking, String idRegisterBookingItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idBooking));
        }

        if (StringUtils.isNotEmpty(idRegisterBookingItem)) {
            query.addCriteria(Criteria.where("id").is(idRegisterBookingItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query).getFirst();
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

    public List<RegisterBookingItemLodgerDomain> searchAllByBookingItemLodgerSimpleDomainCaseAllCupon(String idBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "dateService", "date", "guide", "service", "observation", "pvt", "sic", "sequence");

        return repositoryTemplate.queryAllRegisterBookingItemLodgerSimpleDomainCaseAllCupon(query);
    }

    public List<RegisterBookingItemLodgerDomain> searchAllByRegisterBookingSimpleDomain(String idRegisterBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idRegisterBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "sequence");

        return repositoryTemplate.queryAllSimpleDomain(query);
    }

    public List<RegisterBookingItemLodgerDomain> searchAllByBookingCaseChangeDate(String idBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "dateService", "date", "dateTime");

        return repositoryTemplate.query(query);
    }

    public void updateChangeDateItineraryDate(String id, LocalDate dateService, LocalDate date, LocalDateTime dateTime) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(id)) {
            query.addCriteria(Criteria.where("id").is(id));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();
        if (dateService != null) {
            update.set("dateService", dateService);
        }
        if (date != null) {
            update.set("date", date);
        }
        if (dateTime != null) {
            update.set("dateTime", dateTime);
        }
        update.set("updatedBy", new ObjectId(UserResolver.resolveUser().getId()));
        update.set("updatedAt", LocalDateTime.now());

        repositoryTemplate.updateChangeDateItineraryDate(query, update);
    }
}
