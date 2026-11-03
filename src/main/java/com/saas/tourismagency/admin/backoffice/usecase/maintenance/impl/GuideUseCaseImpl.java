package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.guide.GuideConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPerson;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultGuide;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.idiom.IdiomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.GuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.PersistGuideNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.BaseSearchRequestById;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.guide.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.GuideTypeEnum;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.GuideUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GuideUseCaseImpl implements GuideUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private GuideRepo repo;

    @Autowired
    private GuideConverter converter;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private IdiomRepo idiomRepo;

    @Autowired
    private SetFieldDefaultGuide setFieldDefault;

    @Autowired
    private SetFieldDefaultPerson setFieldDefaultPerson;

    @Autowired
    PersistGuideNumberCallbackImpl persistGuideNumberCallbackImpl;

    @Override
    @Transactional
    public GuideResponse create(GuideCreateRequest request) {
        PersonDomain newPersonDomain = request.getPerson().to();
        SupplierDomain supplierDomain = null;

        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }

        newPersonDomain.setTenant(UserResolver.resolveUser().getTenant());
        PersonDomain createdPersonDomain = personRepo.create(newPersonDomain);

        Set<IdiomDomain> idioms = new HashSet<>();
        if (request.getIdioms() != null && !request.getIdioms().isEmpty()){
            idioms = request.getIdioms().stream().map(s ->
                 idiomRepo.findById(s)
            ).filter(Objects::nonNull).collect(Collectors.toSet());
        }
        Set<String> guideTypes = new HashSet<>();
        if (request.getGuideTypes() != null && !request.getGuideTypes().isEmpty()){
            guideTypes = request.getGuideTypes().stream().collect(Collectors.toSet());
        }

        GuideDomain newDomain = request.to(
                createdPersonDomain,
                supplierDomain,
                idioms,
                guideTypes
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistGuideNumberCallbackImpl.generateNumber());
        GuideDomain createdDomain = repo.create(newDomain);
        return GuideResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public GuideResponse edit(String id, GuideUpdateRequest request) {
        if (id == null) {
            return null;
        }
        GuideDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No guide found with id '%s'.".formatted(id));
        }
        GuideDomain guideDomainExist = null;
        if (StringUtils.isNotEmpty(request.getName())) {
            guideDomainExist = repo.findByName(request.getName());
            if (guideDomainExist != null && !guideDomainExist.getId().equals(old.getId())){
                throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
            }
        }

        PersonDomain oldPerson = personRepo.findById(request.getPerson().getId());
        if (oldPerson == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(request.getPerson().getId()));
        }
        PersonDomain updatePersonDomain = request.getPerson().to();

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }

        PersonDomain editedDomainPerson = personRepo.edit(setFieldDefaultPerson.setDefaultFieldBaseUpdated(updatePersonDomain, oldPerson));

        Set<IdiomDomain> idioms = new HashSet<>();
        if (request.getIdioms() != null && !request.getIdioms().isEmpty()){
            idioms = request.getIdioms().stream().map(s ->
                    idiomRepo.findById(s)
            ).filter(Objects::nonNull).collect(Collectors.toSet());
        }
        Set<String> guideTypes = new HashSet<>();
        if (request.getGuideTypes() != null && !request.getGuideTypes().isEmpty()){
            guideTypes = request.getGuideTypes().stream().collect(Collectors.toSet());
        }

        GuideDomain guideDomain = request.to(
                editedDomainPerson,
                supplierDomain,
                idioms,
                guideTypes
        );
        guideDomain.setIdentify(old.getIdentify());
        guideDomain.setName(StringUtils.isNotEmpty(request.getName()) ? request.getName() : old.getName());
        GuideDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(guideDomain, old));
        return GuideResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        GuideResponse old = findById(id);
        if (referenceGuard.isReferenced("Guide", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GuideResponse> findAll() {
        return repo.findAll(false).stream().map(guideDomain -> GuideResponse.from(guideDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GuideResponse findById(String id) {
        if (id == null) {
            return null;
        }
        GuideDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No guide found with id '%s'.".formatted(id));
        }
        return GuideResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public GuideSearchResponse searchAll(GuideSearchRequest guideSearchRequest) {
       PageDTO<GuideDomain> pageDTO = repo.searchAll(guideSearchRequest);
       GuideSearchResponse guideSearchRespose = new GuideSearchResponse();
       guideSearchRespose.setGuides(pageDTO.getContent().stream().map(guideDomain ->  GuideResponse.from(guideDomain)).toList());
       guideSearchRespose.setTotalElements(pageDTO.getTotalElements());
       guideSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return guideSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public GuideSearchResponse searchFilterAll(GuideSearchFilterRequest guideSearchFilterRequest) {
        PageDTO<GuideDomain> pageDTO = repo.searchAllFilter(guideSearchFilterRequest);
        GuideSearchResponse guideSearchRespose = new GuideSearchResponse();
        guideSearchRespose.setGuides(pageDTO.getContent().stream().map(guideDomain ->  GuideResponse.from(guideDomain)).toList());
        guideSearchRespose.setTotalElements(pageDTO.getTotalElements());
        guideSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return guideSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public GuideResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        GuideDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No guide found with name '%s'.".formatted(name));
        }
        return GuideResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public GuideResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        GuideDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No guide found with identify '%s'.".formatted(identify));
        }
        return GuideResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public GuideWithoutPageSearchResponse searchAllByGuideAndTransferDrive(BaseSearchRequestById baseSearchRequestById) {
        List<GuideDomain> list = repo.searchAllBySupplierAndGuideTypes(baseSearchRequestById.getIdEntity(), List.of(
                GuideTypeEnum.GUIDE.getName(),
                GuideTypeEnum.TRANSFER_DRIVER.getName()
        ));
        GuideWithoutPageSearchResponse guideWithoutPageSearchResponse = new GuideWithoutPageSearchResponse();
        guideWithoutPageSearchResponse.setEntities(list.stream().map(guideDomain ->  GuideResponse.from(guideDomain)).toList());
        return guideWithoutPageSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public GuideWithoutPageSearchResponse searchAllByDrive(BaseSearchRequestById baseSearchRequestById) {
        List<GuideDomain> list = repo.searchAllBySupplierAndGuideTypes(baseSearchRequestById.getIdEntity(), List.of(
                GuideTypeEnum.DRIVER.getName()
        ));
        GuideWithoutPageSearchResponse guideWithoutPageSearchResponse = new GuideWithoutPageSearchResponse();
        guideWithoutPageSearchResponse.setEntities(list.stream().map(guideDomain ->  GuideResponse.from(guideDomain)).toList());
        return guideWithoutPageSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GuideResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> GuideResponse.from(simpleBaseDomain)).toList();
    }

}
