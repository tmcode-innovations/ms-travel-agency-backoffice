package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.security.SetFieldDefaultUser;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.AreaEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.security.UserEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.security.UserMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
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
public class UserRepo extends DelegatedSpringJpaRepo<UserDomain, UserEntity, String, UserConverter, SetFieldDefaultUser, UserMongoRepo> {

    @Autowired
    private IUserRepositoryTemplate userRepositoryTemplate;

    @Autowired
    public UserRepo(UserMongoRepo springRepo, UserConverter converter, SetFieldDefaultUser setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> USER_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("userName", "userName"),
            Map.entry("email", "email"),
            Map.entry("fullName", "fullName")
    );

    public PageDTO<UserDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (USER_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = USER_ENTITY_FIELDS.get(key);
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
        String userName = null;
        String email = null;
        String fullName = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "userName" -> {
                        userName = entry.getValue();
                    }
                    case "email" -> {
                        email = entry.getValue();
                    }
                    case "fullName" -> {
                        fullName = entry.getValue();
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

        if (StringUtils.isNotEmpty(userName)) {
            query.addCriteria(Criteria.where("userName").is(userName));
        }

        if (StringUtils.isNotEmpty(email)) {
            query.addCriteria(Criteria.where("email").is(email));
        }

        if (StringUtils.isNotEmpty(fullName)) {
            query.addCriteria(Criteria.where("fullName").is(fullName));
        }

        query.fields().include("id", "userName", "email", "fullName", "description");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return userRepositoryTemplate.queryAllSimpleDomainCaseListUser(pageConfig, query);
    }

    public PageDTO<UserDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (USER_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = USER_ENTITY_FIELDS.get(key);
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

        return userRepositoryTemplate.query(pageConfig, query);
    }

    public UserDomain findByUserName(String userName) {
        UserEntity current = repo().findByUserName(userName);
        return current != null ? converter.toDomain(current) : null;
    }

    public UserDomain findByEmail(String email) {
        UserEntity current = repo().findByEmail(email);
        return current != null ? converter.toDomain(current) : null;
    }

    public UserDomain findByUserNameOrEmail(String usermail) {
        UserEntity current = repo().findByUserNameOrEmail(usermail, usermail);
        return current != null ? converter.toDomain(current) : null;
    }

    public UserDomain findByUserNameAndTenant(String userName) {
        UserEntity current = repo().findByUserNameAndTenant(userName, UserResolver.resolveUser().getTenant());
        return current != null ? converter.toDomain(current) : null;
    }

    public UserDomain findByEmailAndTenant(String email) {
        UserEntity current = repo().findByEmailAndTenant(email, UserResolver.resolveUser().getTenant());
        return current != null ? converter.toDomain(current) : null;
    }

    public UserDomain findByTenantAndUserNameOrEmail(String usermail) {
        UserEntity current = repo().findByTenantAndUserNameOrEmail(UserResolver.resolveUser().getTenant(), usermail, usermail);
        return current != null ? converter.toDomain(current) : null;
    }

    public List<UserDomain> findAll(Boolean deleted){
        UserEntity example = new UserEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<UserDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "fullName");

        return userRepositoryTemplate.queryAllSimple(query);
    }
}
