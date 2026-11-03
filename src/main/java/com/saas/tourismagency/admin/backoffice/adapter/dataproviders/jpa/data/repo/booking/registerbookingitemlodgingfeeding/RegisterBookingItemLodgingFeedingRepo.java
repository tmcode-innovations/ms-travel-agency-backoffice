package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingItemLodgingFeeding;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.RegisterBookingItemLodgingFeedingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking.RegisterBookingItemLodgingFeedingMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingItemLodgingFeedingDomain;
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

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegisterBookingItemLodgingFeedingRepo extends DelegatedSpringJpaRepo<RegisterBookingItemLodgingFeedingDomain, RegisterBookingItemLodgingFeedingEntity, String, RegisterBookingItemLodgingFeedingConverter, SetFieldDefaultRegisterBookingItemLodgingFeeding, RegisterBookingItemLodgingFeedingMongoRepo> {

    @Autowired
    private IRegisterBookingItemLodgingFeedingRepTemp repositoryTemplate;

    @Autowired
    public RegisterBookingItemLodgingFeedingRepo(RegisterBookingItemLodgingFeedingMongoRepo springRepo, RegisterBookingItemLodgingFeedingConverter converter, SetFieldDefaultRegisterBookingItemLodgingFeeding setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> REGISTER_BOOKING_ITEM_LODGING_FEEDING_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<RegisterBookingItemLodgingFeedingDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_BOOKING_ITEM_LODGING_FEEDING_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_BOOKING_ITEM_LODGING_FEEDING_ENTITY_FIELDS.get(key);
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

    public PageDTO<RegisterBookingItemLodgingFeedingDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (REGISTER_BOOKING_ITEM_LODGING_FEEDING_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = REGISTER_BOOKING_ITEM_LODGING_FEEDING_ENTITY_FIELDS.get(key);
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

    public List<RegisterBookingItemLodgingFeedingDomain> findAll(Boolean deleted){
        RegisterBookingItemLodgingFeedingEntity example = new RegisterBookingItemLodgingFeedingEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<RegisterBookingItemLodgingFeedingDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public RegisterBookingItemLodgingFeedingDomain findByName(String name){
        RegisterBookingItemLodgingFeedingEntity entity = repo().findByNameAndTenantAndDeleted(name, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public RegisterBookingItemLodgingFeedingDomain findByIdentify(String identify){
        RegisterBookingItemLodgingFeedingEntity entity = repo().findByIdentifyAndTenantAndDeleted(identify, UserResolver.resolveUser().getTenant(), false);
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    public List<RegisterBookingItemLodgingFeedingDomain> searchAllByRegisterBookingItemLodging(String idRegisterBookingItemLodging) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterBookingItemLodging)) {
            query.addCriteria(Criteria.where("registerBookingItemLodging.id").is(idRegisterBookingItemLodging));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<RegisterBookingItemLodgingFeedingDomain> searchAllByRegisterBookingItemLodgerLodging(String idRegisterBookingItemLodgerLodging) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterBookingItemLodgerLodging)) {
            query.addCriteria(Criteria.where("registerBookingItemLodgerLodging.id").is(idRegisterBookingItemLodgerLodging));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<RegisterBookingItemLodgingFeedingDomain> searchAllByRegisterBookingItemLodgingSimpleDomainCaseAllCupon(String idRegisterBookingItemLodging) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterBookingItemLodging)) {
            query.addCriteria(Criteria.where("registerBookingItemLodging.id").is(idRegisterBookingItemLodging));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "dateFeeding", "dateTimeFeeding", "cant", "typeFeedingBooking", "typePassengerBooking", "typeBooking");

        return repositoryTemplate.queryAllRegisterBookingItemLodgingSimpleDomainCaseAllCupon(query);
    }

    public List<RegisterBookingItemLodgingFeedingDomain> searchAllByRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(String idRegisterBookingItemLodgerLodging) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterBookingItemLodgerLodging)) {
            query.addCriteria(Criteria.where("registerBookingItemLodgerLodging.id").is(idRegisterBookingItemLodgerLodging));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "dateFeeding", "dateTimeFeeding", "cant", "typeFeedingBooking", "typePassengerBooking", "typeBooking");

        return repositoryTemplate.queryAllRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(query);
    }

    public List<RegisterBookingItemLodgingFeedingDomain> searchAllByRegisterBookingItemLodgingCaseChangeDate(String idRegisterBookingItemLodging) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(idRegisterBookingItemLodging)) {
            query.addCriteria(Criteria.where("registerBookingItemLodging.id").is(idRegisterBookingItemLodging));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "dateFeeding", "dateTimeFeeding");
        return repositoryTemplate.query(query);
    }

    public List<RegisterBookingItemLodgingFeedingDomain> searchAllByRegisterBookingItemLodgerLodgingCaseChangeDate(String idRegisterBookingItemLodgerLodging) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(idRegisterBookingItemLodgerLodging)) {
            query.addCriteria(Criteria.where("registerBookingItemLodgerLodging.id").is(idRegisterBookingItemLodgerLodging));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "dateFeeding", "dateTimeFeeding");
        return repositoryTemplate.query(query);
    }

    public List<RegisterBookingItemLodgingFeedingDomain> searchAllByRegisterBookingItemGlampingCaseChangeDate(String idRegisterBookingItemGlamping) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(idRegisterBookingItemGlamping)) {
            query.addCriteria(Criteria.where("registerBookingItemGlamping.id").is(idRegisterBookingItemGlamping));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.fields().include("id", "dateFeeding", "dateTimeFeeding");
        return repositoryTemplate.query(query);
    }

    public void updateChangeDateItineraryDate(String id, LocalDate dateFeeding, LocalDateTime dateTimeFeeding) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        if (StringUtils.isNotEmpty(id)) {
            query.addCriteria(Criteria.where("id").is(id));
        }
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        Update update = new Update();
        if (dateFeeding != null) {
            update.set("dateFeeding", dateFeeding);
        }
        if (dateTimeFeeding != null) {
            update.set("dateTimeFeeding", dateTimeFeeding);
        }
        update.set("updatedBy", new ObjectId(UserResolver.resolveUser().getId()));
        update.set("updatedAt", LocalDateTime.now());

        repositoryTemplate.updateChangeDateItineraryDate(query, update);
    }
}
