package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programitem.ProgramItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultProgramItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.ProgramItemEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.springdata.crm.ProgramItemMongoRepo;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
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
public class ProgramItemRepo extends DelegatedSpringJpaRepo<ProgramItemDomain, ProgramItemEntity, String, ProgramItemConverter, SetFieldDefaultProgramItem, ProgramItemMongoRepo> {

    @Autowired
    private IProgramItemRepositoryTemplate programItemRepositoryTemplate;

    @Autowired
    public ProgramItemRepo(ProgramItemMongoRepo springRepo, ProgramItemConverter converter, SetFieldDefaultProgramItem setFieldDefault) {
        super(
                springRepo,
                converter,
                setFieldDefault
        );
    }

    final Map<String, String> PROGRAM_ITEM_ENTITY_FIELDS = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("description", "description"),
            Map.entry("identify", "identify")
    );

    public PageDTO<ProgramItemDomain> searchAllFilter(ISearchFilterPageDomain searchFilterDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchFilterDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchFilterDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (PROGRAM_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = PROGRAM_ITEM_ENTITY_FIELDS.get(key);
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

        return programItemRepositoryTemplate.query(pageConfig, query);
    }

    public PageDTO<ProgramItemDomain> searchAll(ISearchPageDomain searchDomain) {
        List<Sort.Order> order = new ArrayList<>();
        List<ValidationErrorMessage> sortErrorMessages = new ArrayList<>();
        if (searchDomain.getSort() != null) {
            for (Map.Entry<String, String> entry : searchDomain.getSort().entrySet()) {
                String value = entry.getValue();
                if (!SortType.NONE.test(value)) {
                    String key = entry.getKey();
                    if (PROGRAM_ITEM_ENTITY_FIELDS.containsKey(key)) {
                        String fieldKey = PROGRAM_ITEM_ENTITY_FIELDS.get(key);
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

        return programItemRepositoryTemplate.query(pageConfig, query);
    }

    public List<ProgramItemDomain> findAll(Boolean deleted){
        ProgramItemEntity example = new ProgramItemEntity();
        example.setDeleted(deleted);
        example.setTenant(UserResolver.resolveUser().getTenant());
        List<ProgramItemDomain> entities = this.findAllWithExample(
                Example.of(example)
        );
        return entities;
    }

    public List<ProgramItemDomain> searchAllByProgramDate(String idProgramDate) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgramDate)) {
            query.addCriteria(Criteria.where("programDate.id").is(idProgramDate));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return programItemRepositoryTemplate.query(query);
    }

    public List<ProgramItemDomain> searchAllByProgram(String idProgram) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return programItemRepositoryTemplate.query(query);
    }

    public ProgramItemDomain searchAllByProgramAndProgramItem(String idProgram, String idProgramItem) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        if (StringUtils.isNotEmpty(idProgramItem)) {
            query.addCriteria(Criteria.where("id").is(idProgramItem));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        List<ProgramItemDomain> result = programItemRepositoryTemplate.query(query);
        if (result != null && !result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public List<ProgramItemDomain> searchAllByProgramDateAndProgram(String idProgramDate, String idProgram) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgramDate)) {
            query.addCriteria(Criteria.where("programDate.id").is(idProgramDate));
        }

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        return programItemRepositoryTemplate.query(query);
    }

    public IdBaseDomain findByIdEntity(String id){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        query.addCriteria(Criteria.where("id").is(id));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id");

        return programItemRepositoryTemplate.queryOneId(query);
    }

    public List<SimpleBaseDomain> findAllSimple(){
        Query query = new Query();
        // Default Query
        query.addCriteria(Criteria.where("deleted").is(false));

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "name");

        return programItemRepositoryTemplate.queryAllSimple(query);
    }

    public List<ProgramItemDomain> searchAllByProgramSimpleDomain(String idProgram) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "programDateTime");

        return programItemRepositoryTemplate.queryAllSimpleDomain(query);
    }

    public List<ProgramItemDomain> searchAllByProgramDateAndProgramSimpleDomainCaseItinerary(String idProgramDate, String idProgram) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgramDate)) {
            query.addCriteria(Criteria.where("programDate.id").is(idProgramDate));
        }

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include(
        "id",
                "index",
                "supplierType",
                "supplier",
                "service",
                "observation",
                "pvt",
                "sic",
                "programDateTime",
                "tour",
                "serviceGroup",
                "lodgingCantSwb",
                "lodgingCantMat",
                "lodgingCantTwin",
                "lodgingCantTrp",
                "lodgingCategoryStr",
                "lodgingCantBreakfast",
                "lodgingCantLunch",
                "lodgingCantDinner",
                "lodgingCantBoxLunch",
                "lodgingCantSwbTcGuide",
                "lodgingCantTwinTcGuide",
                "lodgingCantBreakfastTcGuide",
                "lodgingCantLunchTcGuide",
                "lodgingCantDinnerTcGuide",
                "lodgingCantBoxLunchTcGuide",
                "lodgingCantSwbTcLocalGuide",
                "lodgingCantTwinTcLocalGuide",
                "lodgingCantBreakfastTcLocalGuide",
                "lodgingCantLunchTcLocalGuide",
                "lodgingCantDinnerTcLocalGuide",
                "lodgingCantBoxLunchTcLocalGuide",
                "lodgingCantSwbGuide",
                "lodgingCantTwinGuide",
                "lodgingCantBreakfastGuide",
                "lodgingCantLunchGuide",
                "lodgingCantDinnerGuide",
                "lodgingCantBoxLunchGuide",
                "lodgeCantSwb",
                "lodgeCantMat",
                "lodgeCantTwin",
                "lodgeCantTrp",
                "lodgeCategoryStr",
                "lodgeCantBreakfast",
                "lodgeCantLunch",
                "lodgeCantDinner",
                "lodgeCantBoxLunch",
                "lodgeCantSwbTcGuide",
                "lodgeCantTwinTcGuide",
                "lodgeCantBreakfastTcGuide",
                "lodgeCantLunchTcGuide",
                "lodgeCantDinnerTcGuide",
                "lodgeCantBoxLunchTcGuide",
                "lodgeCantSwbTcLocalGuide",
                "lodgeCantTwinTcLocalGuide",
                "lodgeCantBreakfastTcLocalGuide",
                "lodgeCantLunchTcLocalGuide",
                "lodgeCantDinnerTcLocalGuide",
                "lodgeCantBoxLunchTcLocalGuide",
                "lodgeCantSwbGuide",
                "lodgeCantTwinGuide",
                "lodgeCantBreakfastGuide",
                "lodgeCantLunchGuide",
                "lodgeCantDinnerGuide",
                "lodgeCantBoxLunchGuide",
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
                "restaurantCantBoxLunchGuide"
                );

        return programItemRepositoryTemplate.queryAllSimpleDomainCaseCreateItinerary(query);
    }

    public List<ProgramItemDomain> searchAllByProgramAndProgramDateSimpleDomainCaseDeleteTours(String idProgram, String idProgramDate, String idTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        if (StringUtils.isNotEmpty(idProgramDate)) {
            query.addCriteria(Criteria.where("programDate.id").is(idProgramDate));
        }

        if (StringUtils.isNotEmpty(idTour)) {
            query.addCriteria(Criteria.where("tour.id").is(idTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "programDate");

        return programItemRepositoryTemplate.queryAllSimpleDomainCaseDeleteTours(query);
    }

    public List<ProgramItemDomain> searchAllByProgramAndProgramDateAndProgramCostTourSimpleDomainCaseDeleteTours(String idProgram, String idProgramDate, String idTour, String idProgramCostTour) {
        Query query = new Query();

        query.addCriteria(Criteria.where("deleted").is(false));

        if (StringUtils.isNotEmpty(idProgram)) {
            query.addCriteria(Criteria.where("program.id").is(idProgram));
        }

        if (StringUtils.isNotEmpty(idProgramDate)) {
            query.addCriteria(Criteria.where("programDate.id").is(idProgramDate));
        }

        if (StringUtils.isNotEmpty(idTour)) {
            query.addCriteria(Criteria.where("tour.id").is(idTour));
        }

        if (StringUtils.isNotEmpty(idProgramCostTour)) {
            query.addCriteria(Criteria.where("programCostTour.id").is(idProgramCostTour));
        }

        // Filter Tenant
        query.addCriteria(Criteria.where("tenant").is(UserResolver.resolveUser().getTenant()));

        query.fields().include("id", "index", "programDate");

        return programItemRepositoryTemplate.queryAllSimpleDomainCaseDeleteTours(query);
    }
}
