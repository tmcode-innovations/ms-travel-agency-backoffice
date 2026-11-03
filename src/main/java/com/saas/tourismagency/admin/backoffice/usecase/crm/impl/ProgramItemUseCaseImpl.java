package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.programdate.ProgramDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultProgramItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programcosttour.ProgramCostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.ProgramDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem.ProgramItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.TourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.ItineraryItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.programitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.ItineraryItemCrmCreateEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ProgramItemUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ProgramItemUseCaseImpl implements ProgramItemUseCase {

    @Autowired
    private ProgramItemRepo repo;

    @Autowired
    private ProgramDateRepo programDateRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private ProgramDateConverter converter;

    @Autowired
    private SetFieldDefaultProgramItem setFieldDefault;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private TourItemRepo tourItemRepo;

    @Autowired
    private ProgramCostTourRepo programCostTourRepo;

    @Override
    @Transactional
    public ProgramItemResponse create(ProgramItemCreateRequest request) {
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            IdBaseDomain programIdBaseDomain = programRepo.findByIdEntity(request.getProgram());
            if (programIdBaseDomain != null){
                programDomain = new ProgramDomain();
                programDomain.setId(programIdBaseDomain.getId());
            }
        }

        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getSupplierType());
            if (programDateIdBaseDomain != null){
                programDateDomain = new ProgramDateDomain();
                programDateDomain.setId(programDateIdBaseDomain.getId());
            }
        }

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            if (supplierTypeIdBaseDomain != null){
                supplierTypeDomain = new SupplierTypeDomain();
                supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
            }
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            if (supplierIdBaseDomain != null){
                supplierDomain = new SupplierDomain();
                supplierDomain.setId(supplierIdBaseDomain.getId());
            }
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            if (serviceIdBaseDomain != null){
                serviceDomain = new ServiceDomain();
                serviceDomain.setId(serviceIdBaseDomain.getId());
            }

        }
        ProgramItemDomain newDomain = request.to(programDomain, programDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProgramItemDomain createdDomain = repo.create(newDomain);
        return ProgramItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ProgramItemResponse create(ProgramItemCreateRequest request, ProgramDomain programDomain) {
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getSupplierType());
            if (programDateIdBaseDomain != null){
                programDateDomain = new ProgramDateDomain();
                programDateDomain.setId(programDateIdBaseDomain.getId());
            }
        }

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            if (supplierTypeIdBaseDomain != null){
                supplierTypeDomain = new SupplierTypeDomain();
                supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
            }
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            if (supplierIdBaseDomain != null){
                supplierDomain = new SupplierDomain();
                supplierDomain.setId(supplierIdBaseDomain.getId());
            }
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            if (serviceIdBaseDomain != null){
                serviceDomain = new ServiceDomain();
                serviceDomain.setId(serviceIdBaseDomain.getId());
            }

        }
        ProgramItemDomain newDomain = request.to(programDomain, programDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexProgramItem(programDomain.getId()));
        ProgramItemDomain createdDomain = repo.create(newDomain);
        return ProgramItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public void createMultiple(ProgramItemCreateRequest request, ProgramDomain programDomain) {
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            if (supplierTypeIdBaseDomain != null){
                supplierTypeDomain = new SupplierTypeDomain();
                supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
            }
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            if (supplierIdBaseDomain != null){
                supplierDomain = new SupplierDomain();
                supplierDomain.setId(supplierIdBaseDomain.getId());
            }
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            if (serviceIdBaseDomain != null){
                serviceDomain = new ServiceDomain();
                serviceDomain.setId(serviceIdBaseDomain.getId());
            }

        }

        if (request.getProgramDates() != null && !request.getProgramDates().isEmpty()){
            for(String item: request.getProgramDates()){
                ProgramDateDomain programDateDomain = null;
                if (request.getProgramDates() != null && !request.getProgramDates().isEmpty()){
                    IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(item);
                    if (programDateIdBaseDomain != null){
                        programDateDomain = new ProgramDateDomain();
                        programDateDomain.setId(programDateIdBaseDomain.getId());
                    }
                }
                ProgramItemDomain newDomain = request.to(programDomain, programDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                newDomain.setIndex(this.getLastIndexProgramItem(programDomain.getId()));
                repo.create(newDomain);
            }
        }
    }

    @Override
    @Transactional
    public void createTour(ProgramTourItemCreateRequest request, ProgramDomain programDomain){
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            tourDomain = tourRepo.findById(request.getTour());
        }
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(request.getTour()));
        }
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            programDateDomain = programDateRepo.findById(request.getProgramDate());
        }
        if (programDateDomain == null) {
            throw new NotFoundException("No program date found with id '%s'.".formatted(request.getProgramDate()));
        }
        if (tourDomain.getCantDay() + programDateDomain.getDay() - 1 > programDomain.getDay()){
            throw new BadRequestException(MessagesValidation.VALIDATION_TOUR_NOT_ASSIGNED_ITINERARY);
        }

        ProgramCostTourDomain newProgramCostTourDomain = new ProgramCostTourDomain();
        newProgramCostTourDomain.setProgramDate(programDateDomain);
        newProgramCostTourDomain.setProgram(programDomain);
        newProgramCostTourDomain.setTour(tourDomain);
        newProgramCostTourDomain.setDay(programDateDomain.getDay());
        newProgramCostTourDomain.setTenant(UserResolver.resolveUser().getTenant());
        ProgramCostTourDomain createProgramCostTourDomain = programCostTourRepo.create(newProgramCostTourDomain);

        List<TourItemDomain> tourItemDomainList = tourItemRepo.searchAllByTour(tourDomain.getId());
        if (tourItemDomainList != null && !tourItemDomainList.isEmpty()){
            List<TourItemDomain> tourItemDomainListResult = tourItemDomainList.stream().collect(Collectors.toList());
            for(TourItemDomain item: tourItemDomainListResult){
                ProgramDateDomain programDateDomainDb = programDateRepo.getByProgramAndDay(programDomain.getId(), programDateDomain.getDay() + item.getTourDate().getDay() - 1);
                ProgramItemDomain newDomain = new ProgramItemDomain();
                newDomain.setProgramDate(programDateDomainDb);
                newDomain.setProgram(programDomain);
                newDomain.setSupplierType(item.getSupplierType());
                newDomain.setSupplier(item.getSupplier());
                newDomain.setService(item.getService());
                newDomain.setObservation(item.getObservation());
                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                newDomain.setIndex(this.getLastIndexProgramItem(programDomain.getId()));
                newDomain.setTour(tourDomain);
                newDomain.setProgramCostTour(createProgramCostTourDomain);
                repo.create(newDomain);

                if (programDateDomainDb.getTours() == null){
                    programDateDomainDb.setTours(new HashSet<TourDomain>());
                }
                programDateDomainDb.getTours().add(tourDomain);
                programDateRepo.edit(programDateDomainDb);
            }
        }


    }

    @Override
    @Transactional
    public ProgramItemResponse edit(String id, ProgramItemUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ProgramItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            IdBaseDomain programIdBaseDomain = programRepo.findByIdEntity(request.getProgram());
            if (programIdBaseDomain != null){
                programDomain = new ProgramDomain();
                programDomain.setId(programIdBaseDomain.getId());
            }
        }
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getProgramDate());
            if (programDateIdBaseDomain != null){
                programDateDomain = new ProgramDateDomain();
                programDateDomain.setId(programDateIdBaseDomain.getId());
            }
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            if (supplierTypeIdBaseDomain != null){
                supplierTypeDomain = new SupplierTypeDomain();
                supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
            }
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            if (supplierIdBaseDomain != null){
                supplierDomain = new SupplierDomain();
                supplierDomain.setId(supplierIdBaseDomain.getId());
            }
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            if (serviceIdBaseDomain != null){
                serviceDomain = new ServiceDomain();
                serviceDomain.setId(serviceIdBaseDomain.getId());
            }

        }
        ProgramItemDomain programItemDomain = request.to(programDomain, programDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        programItemDomain.setIndex(old.getIndex());
        programItemDomain.setTour(old.getTour());
        programItemDomain.setProgramCostTour(old.getProgramCostTour());
        ProgramItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(programItemDomain, old));
        return ProgramItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ProgramItemResponse edit(String id, ProgramItemUpdateRequest request, ProgramDomain programDomain) {
        if (id == null) {
            return null;
        }
        ProgramItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        ProgramDateDomain programDateDomain = null;
        if (request.getProgramDate() != null && !request.getProgramDate().isEmpty()){
            IdBaseDomain programDateIdBaseDomain = programDateRepo.findByIdEntity(request.getProgramDate());
            if (programDateIdBaseDomain != null){
                programDateDomain = new ProgramDateDomain();
                programDateDomain.setId(programDateIdBaseDomain.getId());
            }
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            IdBaseDomain supplierTypeIdBaseDomain = supplierTypeRepo.findByIdEntity(request.getSupplierType());
            if (supplierTypeIdBaseDomain != null){
                supplierTypeDomain = new SupplierTypeDomain();
                supplierTypeDomain.setId(supplierTypeIdBaseDomain.getId());
            }
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            if (supplierIdBaseDomain != null){
                supplierDomain = new SupplierDomain();
                supplierDomain.setId(supplierIdBaseDomain.getId());
            }
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            if (serviceIdBaseDomain != null){
                serviceDomain = new ServiceDomain();
                serviceDomain.setId(serviceIdBaseDomain.getId());
            }

        }
        ProgramItemDomain programItemDomain = request.to(programDomain, programDateDomain, supplierTypeDomain, supplierDomain, serviceDomain);
        programItemDomain.setIndex(old.getIndex());
        programItemDomain.setTour(old.getTour());
        programItemDomain.setProgramCostTour(old.getProgramCostTour());
        ProgramItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(programItemDomain, old));
        return ProgramItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ProgramItemResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramItemResponse> findAll() {
        return repo.findAll(false).stream().map(programItemDomain -> ProgramItemResponse.from(programItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramItemResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ProgramItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No tour item found with id '%s'.".formatted(id));
        }
        return ProgramItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramItemSearchResponse searchAll(ProgramItemSearchRequest programItemSearchRequest) {
        PageDTO<ProgramItemDomain> pageDTO = repo.searchAll(programItemSearchRequest);
        ProgramItemSearchResponse programItemSearchRespose = new ProgramItemSearchResponse();
        programItemSearchRespose.setProgramItems(pageDTO.getContent().stream().map(programItemDomain ->  ProgramItemResponse.from(programItemDomain)).toList());
        programItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
        programItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return programItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramItemSearchResponse searchFilterAll(ProgramItemSearchFilterRequest programItemSearchFilterRequest) {
        PageDTO<ProgramItemDomain> pageDTO = repo.searchAllFilter(programItemSearchFilterRequest);
        ProgramItemSearchResponse programItemSearchRespose = new ProgramItemSearchResponse();
        programItemSearchRespose.setProgramItems(pageDTO.getContent().stream().map(programItemDomain ->  ProgramItemResponse.from(programItemDomain)).toList());
        programItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
        programItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return programItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramItemResponse> searchAllByTour(String idTour) {
        List<ProgramItemDomain> list = repo.searchAllByProgram(idTour);
        return list.stream().map(programItemDomain ->  ProgramItemResponse.from(programItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramItemResponse searchByProgramAndProgramItem(String idProgram, String idProgramItem) {
        ProgramItemDomain programItemDomain = repo.searchAllByProgramAndProgramItem(idProgram, idProgramItem);
        return ProgramItemResponse.from(programItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramItemDomain> searchAllDomainByProgram(String idProgram) {
        List<ProgramItemDomain> list = repo.searchAllByProgram(idProgram);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgramItemDomain> searchAllDomainByProgramAndProgramDate(String idProgramDate, String idProgram) {
        List<ProgramItemDomain> list = repo.searchAllByProgramDateAndProgram(idProgramDate, idProgram);
        return list;
    }

    private Integer getLastIndexProgramItem(String idProgram) {
        Integer index = 0;
        List<ProgramItemDomain> list = repo.searchAllByProgramSimpleDomain(idProgram).stream().collect(Collectors.toList());
        Collections.sort(list);
        long count = list.stream().count();
        if (count != 0){
            Stream<ProgramItemDomain> stream = list.stream();
            ProgramItemDomain programItemLast = stream.skip(count - 1).findFirst().get();
            index = programItemLast.getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }
}
