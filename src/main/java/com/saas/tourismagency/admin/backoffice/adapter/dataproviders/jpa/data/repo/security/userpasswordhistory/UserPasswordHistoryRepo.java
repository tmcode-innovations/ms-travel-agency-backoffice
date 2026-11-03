package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.userpasswordhistory;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.userpasswordhistory.UserPasswordHistoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultUserPasswordHistory;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserPasswordHistoryEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security.UserPasswordHistoryMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserPasswordHistoryDomain;
import dev.mm.javaee.core.domain.ISearchFilterPageDomain;
import dev.mm.javaee.core.domain.ISearchPageDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.repo.DelegatedSpringJpaRepo;
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
public class UserPasswordHistoryRepo extends DelegatedSpringJpaRepo<UserPasswordHistoryDomain, UserPasswordHistoryEntity, String, UserPasswordHistoryConverter, SetFieldDefaultUserPasswordHistory, UserPasswordHistoryMongoRepo> {

    @Autowired
    private IUserPasswordHistoryRepositoryTemplate userPasswordHistoryRepositoryTemplate;

    @Autowired
    public UserPasswordHistoryRepo(UserPasswordHistoryMongoRepo springRepo, UserPasswordHistoryConverter converter, SetFieldDefaultUserPasswordHistory setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    public UserPasswordHistoryDomain findByUser(UserDomain user){
        Query query = new Query();
        query.addCriteria(Criteria.where("user.id").is(user.getId()));

        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<UserPasswordHistoryDomain> list = userPasswordHistoryRepositoryTemplate.queryBy(query);
        if (list != null && !list.isEmpty()){
            return list.iterator().next();
        }
        return null;
    }

    final Map<String, String> USER_PASSWORD_HISTORY_ENTITY_FIELDS = Map.ofEntries(

    );

    public PageDTO<UserPasswordHistoryDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (USER_PASSWORD_HISTORY_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = USER_PASSWORD_HISTORY_ENTITY_FIELDS.get(key);
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

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {

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

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return userPasswordHistoryRepositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<UserPasswordHistoryDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (USER_PASSWORD_HISTORY_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = USER_PASSWORD_HISTORY_ENTITY_FIELDS.get(key);
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

        Pageable pageConfig = PageRequest.of(
                searchDomain.getPage(),
                searchDomain.getSize(),
                Sort.by(order)
        );

        return this.findAll(pageConfig);
    }

    public List<UserPasswordHistoryDomain> findByUser(String userId) {
        List<UserPasswordHistoryEntity> userPasswordHistoricals = repo().findByUserIdAndTenant(userId, UserResolver.resolveUser().getTenant());
        return userPasswordHistoricals != null ? converter.toDomainAll(userPasswordHistoricals) : null;
    }

    public UserPasswordHistoryDomain findByPasswordHash(String passwordHash, String userId) {
        UserPasswordHistoryEntity userPasswordHistoricals = repo().findByPasswordHashAndUserIdAndTenant(passwordHash, userId, UserResolver.resolveUser().getTenant());
        return userPasswordHistoricals != null ? converter.toDomain(userPasswordHistoricals) : null;
    }

}
