package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengerbooking;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.passengerbooking.PassengerBookingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultPassengerBooking;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.booking.PassengerBookingEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.booking.PassengerBookingMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerBookingDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.repo.DelegatedSpringJpaRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PassengerBookingRepo extends DelegatedSpringJpaRepo<PassengerBookingDomain, PassengerBookingEntity, String, PassengerBookingConverter, SetFieldDefaultPassengerBooking, PassengerBookingMongoRepo> {

    @Autowired
    private IPassengerBookingRepositoryTemplate repositoryTemplate;

    @Autowired
    public PassengerBookingRepo(PassengerBookingMongoRepo springRepo, PassengerBookingConverter converter, SetFieldDefaultPassengerBooking setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> PASSENGER_BOOKING_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<PassengerBookingDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (PASSENGER_BOOKING_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = PASSENGER_BOOKING_ENTITY_FIELDS.get(key);
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
            query.addCriteria(Criteria.where("name").is(name));
        }

        if (StringUtils.isNotEmpty(description)) {
            query.addCriteria(Criteria.where("description").is(description));
        }

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return repositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<PassengerBookingDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (PASSENGER_BOOKING_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = PASSENGER_BOOKING_ENTITY_FIELDS.get(key);
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

    public List<PassengerBookingDomain> searchAllByRegisterBookingOperatorAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingOperator.id").is(idRegisterBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingRestaurantAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingRestaurant.id").is(idRegisterBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgingAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodging.id").is(idRegisterBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingGlampingAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingGlamping.id").is(idRegisterBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingInstitution.id").is(idRegisterBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroup(String idRegisterBooking, String idRegisterGroup) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idRegisterBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingOperator.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typePassengerTcBooking)) {
            query.addCriteria(Criteria.where("typePassengerTcBooking").is(typePassengerTcBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingInstitution.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typePassengerTcBooking)) {
            query.addCriteria(Criteria.where("typePassengerTcBooking").is(typePassengerTcBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingRestaurant.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typePassengerTcBooking)) {
            query.addCriteria(Criteria.where("typePassengerTcBooking").is(typePassengerTcBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodging.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typeRoomBooking)) {
            query.addCriteria(Criteria.where("typeRoomBooking").is(typeRoomBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingGlamping.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typeRoomBooking)) {
            query.addCriteria(Criteria.where("typeRoomBooking").is(typeRoomBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingInstitution.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typeRoomBooking)) {
            query.addCriteria(Criteria.where("typeRoomBooking").is(typeRoomBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBooking(String idRegisterBooking, String idRegisterGroup, String typePassengerTcBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typePassengerTcBooking)) {
            query.addCriteria(Criteria.where("typePassengerTcBooking").is(typePassengerTcBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }

    public List<PassengerBookingDomain> searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBooking(String idRegisterBooking, String idRegisterGroup, String typeRoomBooking) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            query.addCriteria(Criteria.where("registerGroup.id").is(idRegisterGroup));
        }

        if (StringUtils.isNotEmpty(idRegisterBooking)) {
            query.addCriteria(Criteria.where("registerBookingLodger.id").is(idRegisterBooking));
        }

        if (StringUtils.isNotEmpty(typeRoomBooking)) {
            query.addCriteria(Criteria.where("typeRoomBooking").is(typeRoomBooking));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return repositoryTemplate.query(query);
    }
}
