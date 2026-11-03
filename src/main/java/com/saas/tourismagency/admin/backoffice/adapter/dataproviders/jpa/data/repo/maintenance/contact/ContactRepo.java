package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.contact;

import com.saas.tourismagency.admin.backoffice.core.utils.NameNormalizer;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.contact.ContactConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultContact;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ContactEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.maintenance.ContactMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ContactDomain;
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
public class ContactRepo extends DelegatedSpringJpaRepo<ContactDomain, ContactEntity, String, ContactConverter, SetFieldDefaultContact, ContactMongoRepo> {

    @Autowired
    private IContactRepositoryTemplate contactRepositoryTemplate;

    @Autowired
    public ContactRepo(ContactMongoRepo springRepo, ContactConverter converter, SetFieldDefaultContact setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> CONTACT_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("email", "email"),
            Map.entry("mobile", "mobile"),
            Map.entry("contactType", "contactType")
    );

    public PageDTO<ContactDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (CONTACT_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = CONTACT_ENTITY_FIELDS.get(key);
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
        String email = null;
        String mobile = null;
        String contactType = null;

        if (searchFilterDomain.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "email" -> {
                        email = entry.getValue();
                    }
                    case "mobile" -> {
                        mobile = entry.getValue();
                    }
                    case "contactType" -> {
                        contactType = entry.getValue();
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

        if (StringUtils.isNotEmpty(email)) {
            query.addCriteria(Criteria.where("contactInformation.email").regex(String.format(".*%s.*", email),"i"));
        }

        if (StringUtils.isNotEmpty(mobile)) {
            query.addCriteria(Criteria.where("contactInformation.mobile").regex(String.format(".*%s.*", mobile),"i"));
        }

        if (StringUtils.isNotEmpty(contactType)) {
            query.addCriteria(Criteria.where("contactType").is(contactType));
        }

        query.fields().include("id", "person", "contactInformation", "supplier", "supplierType", "customer");

        Pageable pageConfig = PageRequest.of(
                searchFilterDomain.getPage(),
                searchFilterDomain.getSize(),
                Sort.by(order)
        );

        return contactRepositoryTemplate.queryAllSimpleDomainCaseListContact(pageConfig, query);
    }

    public PageDTO<ContactDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (CONTACT_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = CONTACT_ENTITY_FIELDS.get(key);
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

        return contactRepositoryTemplate.query(pageConfig, query);
    }

    public List<ContactDomain> findAll(Boolean deleted){
        ContactEntity example = new ContactEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<ContactDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<ContactDomain> findAll(String contactType, Boolean deleted){
        ContactEntity example = new ContactEntity();
        example.setContactType(contactType);
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<ContactDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public ContactDomain findByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("deleted").is(false));
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));
        query.addCriteria(Criteria.where("name").regex(NameNormalizer.exactLikePattern(name), "i"));
        PageDTO<ContactDomain> page = contactRepositoryTemplate.query(PageRequest.of(0, 1), query);
        if (page.getContent().isEmpty()) {
            return null;
        }
        return page.getContent().get(0);
    }

}
