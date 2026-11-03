package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itinerarydate.ItineraryDateConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultItineraryDate;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.ProgramDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem.ProgramItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.*;
import com.saas.tourismagency.admin.backoffice.adapter.events.booking.event.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.GeneratedRegisterBookingOperatorItemEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.ItineraryDateUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ItineraryDateUseCaseImpl implements ItineraryDateUseCase {

    @Autowired
    private ItineraryDateRepo repo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ItineraryDateConverter converter;

    @Autowired
    private SetFieldDefaultItineraryDate setFieldDefault;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private ProgramDateRepo programDateRepo;

    @Autowired
    private ProgramItemRepo programItemRepo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Override
    @Transactional
    public ItineraryDateResponse create(ItineraryDateCreateRequest request) {
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }
        List<ItineraryDateDomain> listItineraryDateByRegisterGroup = repo.searchAllByRegisterGroup(registerGroupDomain.getId());
        Collections.sort(listItineraryDateByRegisterGroup);
        Integer index = listItineraryDateByRegisterGroup.getFirst().getIndex();
        ItineraryDateDomain newDomain = request.to(registerGroupDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(index + 1);
        newDomain.setItemItinerary(Integer.toString(index + 1));
        ItineraryDateDomain createdDomain = repo.create(newDomain);
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new ItineraryDateCreateEvent(this, createdDomain));
        }*/
        return ItineraryDateResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ItineraryDateResponse edit(String id, ItineraryDateUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryDateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }
        ItineraryDateDomain itineraryDateDomain = request.to(registerGroupDomain);
        itineraryDateDomain.setIndex(old.getIndex());
        itineraryDateDomain.setItemItinerary(old.getItemItinerary());
        itineraryDateDomain.setTours(old.getTours());
        itineraryDateDomain.setIdItineraryDateQuotation(old.getIdItineraryDateQuotation());
        ItineraryDateDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(itineraryDateDomain, old));
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, editedDomain));
        }*/
        return ItineraryDateResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ItineraryDateResponse old = findById(id);
        repo.deleteById(old.getId());
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            this.eventPublisher.publishEvent(new ItineraryDateDeleteEvent(this, repo.findById(id)));
        }*/
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryDateResponse> findAll() {
        return repo.findAll(false).stream().map(itineraryDateDomain -> ItineraryDateResponse.from(itineraryDateDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ItineraryDateDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary date found with id '%s'.".formatted(id));
        }
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        String generalConfiguration = "";
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            generalConfiguration = GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName();
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            generalConfiguration = GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName();
        }
        return ItineraryDateResponse.from(old, generalConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateDomain findByIdDomain(String id) {
        if (id == null) {
            return null;
        }
        ItineraryDateDomain entityDomain = repo.findById(id);
        if (entityDomain == null) {
            throw new NotFoundException("No itinerary date found with id '%s'.".formatted(id));
        }
        return entityDomain;
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateSearchResponse searchAll(ItineraryDateSearchRequest bankSearchRequest) {
       PageDTO<ItineraryDateDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ItineraryDateSearchResponse bankSearchRespose = new ItineraryDateSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ItineraryDateResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateSearchResponse searchFilterAll(ItineraryDateSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ItineraryDateDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ItineraryDateSearchResponse bankSearchRespose = new ItineraryDateSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ItineraryDateResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryDateResponse> searchAllByRegisterGroup(String idRegisterGroup) {
        List<ItineraryDateDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        list = list.stream().collect(Collectors.toList());
        Collections.sort(list);
        return list.stream().map(itineraryDateDomain ->  ItineraryDateResponse.from(itineraryDateDomain)).toList();
    }

    @Override
    @Transactional
    public void generateDateItinerary(RegisterGroupDomain registerGroup, Integer days, LocalDate dateInit) {
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> createDateItinerary(registerGroup, item, dateInit.plusDays(item)));
    }

    @Override
    @Transactional
    public void generateDateItineraryByProgram(RegisterGroupDomain registerGroup, ProgramDomain program, LocalDate dateInit) {
        List<ProgramDateDomain> programDateDomainList = programDateRepo.searchAllByProgramSimpleDomain(program.getId());
        SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        if (programDateDomainList != null && !programDateDomainList.isEmpty()) {
            List<ProgramDateDomain> listProgramDateResult = programDateDomainList.stream().collect(Collectors.toList());
            Collections.sort(listProgramDateResult);
            Integer item = 0;
            Integer indexItem = 1;
            for (ProgramDateDomain programDateDomain: listProgramDateResult
            ) {
                ItineraryDateDomain itineraryDateDomain = createDateItineraryFromProgramDate(registerGroup, programDateDomain, dateInit.plusDays(item));
                List<ProgramItemDomain> listProgramItem = programItemRepo.searchAllByProgramDateAndProgramSimpleDomainCaseItinerary(programDateDomain.getId(), program.getId());
                List<ProgramItemDomain> listProgramItemResult;
                List<ItineraryItemDomain> saveItineraryItems = new ArrayList<>();
                if (listProgramItem != null && !listProgramItem.isEmpty()){
                    listProgramItemResult = listProgramItem.stream().collect(Collectors.toList());
                    Collections.sort(listProgramItemResult);
                    for (ProgramItemDomain programItemDomain: listProgramItemResult
                    ) {
                        ItineraryItemDomain newDomain = new ItineraryItemDomain();
                        newDomain.setRegisterGroup(registerGroup);
                        newDomain.setItineraryDate(itineraryDateDomain);
                        newDomain.setSupplierType(programItemDomain.getSupplierType());
                        newDomain.setSupplier(programItemDomain.getSupplier());
                        newDomain.setService(programItemDomain.getService());
                        newDomain.setObservation(programItemDomain.getObservation());
                        newDomain.setPrice(0.0);
                        newDomain.setPaxTotal(registerGroup.getCantPaxAdult());
                        newDomain.setPaxAdultTotal(registerGroup.getCantPaxAdult());
                        newDomain.setPaxChildrenTotal(registerGroup.getCantPaxChildren());
                        newDomain.setPaxTcTotal(registerGroup.getTC());
                        newDomain.setPaxTcLocalTotal(registerGroup.getTCLocal());
                        newDomain.setPaxGuideTotal(registerGroup.getGuide());
                        newDomain.setTenant(UserResolver.resolveUser().getTenant());
                        // newDomain.setIndex(this.getLastIndexItineraryItem(registerGroup.getId()));
                        newDomain.setIndex(indexItem);
                        LocalDateTime localDateTime = LocalDateTime.of(
                                itineraryDateDomain.getItineraryDate().getYear(),
                                itineraryDateDomain.getItineraryDate().getMonth(),
                                itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                                programItemDomain.getProgramDateTime().getHour(),
                                programItemDomain.getProgramDateTime().getMinute(),
                                programItemDomain.getProgramDateTime().getSecond()
                        );
                        newDomain.setItineraryDateTime(localDateTime);
                        newDomain.setServiceGroup(programItemDomain.getServiceGroup());
                        if (supplierTypeDomainLodging.getId().equals(programItemDomain.getSupplierType().getId())){
                            if (
                                    (newDomain.getPaxAdultTotal() != null && newDomain.getPaxAdultTotal() > 0) ||
                                            (newDomain.getPaxChildrenTotal() != null && newDomain.getPaxChildrenTotal() > 0) ||
                                            (newDomain.getPaxTcTotal() != null && newDomain.getPaxTcTotal() > 0) ||
                                            (newDomain.getPaxGuideTotal() != null && newDomain.getPaxGuideTotal() > 0)
                            ){
                                newDomain.setLodgingCantNight(1);
                            }else if (newDomain.getPaxTcLocalTotal() != null && newDomain.getPaxTcLocalTotal() > 0){
                                newDomain.setLodgingTcLocalCantNight(1);
                            } else{
                                newDomain.setLodgingCantNight(1);
                            }

                            newDomain.setLodgingCantSwb(programItemDomain.getLodgingCantSwb());
                            newDomain.setLodgingCantMat(programItemDomain.getLodgingCantMat());
                            newDomain.setLodgingCantTwin(programItemDomain.getLodgingCantTwin());
                            newDomain.setLodgingCantTrp(programItemDomain.getLodgingCantTrp());
                            newDomain.setLodgingCategoryStr(programItemDomain.getLodgingCategoryStr());
                            newDomain.setLodgingCantBreakfast(programItemDomain.getLodgingCantBreakfast());
                            newDomain.setLodgingCantLunch(programItemDomain.getLodgingCantLunch());
                            newDomain.setLodgingCantDinner(programItemDomain.getLodgingCantDinner());
                            newDomain.setLodgingCantBoxLunch(programItemDomain.getLodgingCantBoxLunch());
                            newDomain.setLodgingCantSwbTcGuide(programItemDomain.getLodgingCantSwbTcGuide());
                            newDomain.setLodgingCantTwinTcGuide(programItemDomain.getLodgingCantTwinTcGuide());
                            newDomain.setLodgingCantBreakfastTcGuide(programItemDomain.getLodgingCantBreakfastTcGuide());
                            newDomain.setLodgingCantLunchTcGuide(programItemDomain.getLodgingCantLunchTcGuide());
                            newDomain.setLodgingCantDinnerTcGuide(programItemDomain.getLodgingCantDinnerTcGuide());
                            newDomain.setLodgingCantBoxLunchTcGuide(programItemDomain.getLodgingCantBoxLunchTcGuide());
                            newDomain.setLodgingCantSwbTcLocalGuide(programItemDomain.getLodgingCantSwbTcLocalGuide());
                            newDomain.setLodgingCantTwinTcLocalGuide(programItemDomain.getLodgingCantTwinTcLocalGuide());
                            newDomain.setLodgingCantBreakfastTcLocalGuide(programItemDomain.getLodgingCantBreakfastTcLocalGuide());
                            newDomain.setLodgingCantLunchTcLocalGuide(programItemDomain.getLodgingCantLunchTcLocalGuide());
                            newDomain.setLodgingCantDinnerTcLocalGuide(programItemDomain.getLodgingCantDinnerTcLocalGuide());
                            newDomain.setLodgingCantBoxLunchTcLocalGuide(programItemDomain.getLodgingCantBoxLunchTcLocalGuide());
                            newDomain.setLodgingCantSwbGuide(programItemDomain.getLodgingCantSwbGuide());
                            newDomain.setLodgingCantTwinGuide(programItemDomain.getLodgingCantTwinGuide());
                            newDomain.setLodgingCantBreakfastGuide(programItemDomain.getLodgingCantBreakfastGuide());
                            newDomain.setLodgingCantLunchGuide(programItemDomain.getLodgingCantLunchGuide());
                            newDomain.setLodgingCantDinnerGuide(programItemDomain.getLodgingCantDinnerGuide());
                            newDomain.setLodgingCantBoxLunchGuide(programItemDomain.getLodgingCantBoxLunchGuide());

                        }else if (supplierTypeDomainLodge.getId().equals(programItemDomain.getSupplierType().getId())){
                            if (newDomain.getServiceGroup() != null && !newDomain.getServiceGroup().isEmpty()){
                                if (newDomain.getServiceGroup().equals(ServiceGroupEnum.LODGING.getName())){
                                    if (
                                            (newDomain.getPaxAdultTotal() != null && newDomain.getPaxAdultTotal() > 0) ||
                                                    (newDomain.getPaxChildrenTotal() != null && newDomain.getPaxChildrenTotal() > 0) ||
                                                    (newDomain.getPaxTcTotal() != null && newDomain.getPaxTcTotal() > 0) ||
                                                    (newDomain.getPaxGuideTotal() != null && newDomain.getPaxGuideTotal() > 0)
                                    ){
                                        newDomain.setLodgeCantNight(1);
                                    }else if (newDomain.getPaxTcLocalTotal() != null && newDomain.getPaxTcLocalTotal() > 0){
                                        newDomain.setLodgeTcLocalCantNight(1);
                                    } else{
                                        newDomain.setLodgeCantNight(1);
                                    }
                                    newDomain.setServiceForOperation(false);

                                    newDomain.setLodgeCantSwb(programItemDomain.getLodgeCantSwb());
                                    newDomain.setLodgeCantMat(programItemDomain.getLodgeCantMat());
                                    newDomain.setLodgeCantTwin(programItemDomain.getLodgeCantTwin());
                                    newDomain.setLodgeCantTrp(programItemDomain.getLodgeCantTrp());
                                    newDomain.setLodgeCategoryStr(programItemDomain.getLodgeCategoryStr());
                                    newDomain.setLodgeCantBreakfast(programItemDomain.getLodgeCantBreakfast());
                                    newDomain.setLodgeCantLunch(programItemDomain.getLodgeCantLunch());
                                    newDomain.setLodgeCantDinner(programItemDomain.getLodgeCantDinner());
                                    newDomain.setLodgeCantBoxLunch(programItemDomain.getLodgeCantBoxLunch());
                                    newDomain.setLodgeCantSwbTcGuide(programItemDomain.getLodgeCantSwbTcGuide());
                                    newDomain.setLodgeCantTwinTcGuide(programItemDomain.getLodgeCantTwinTcGuide());
                                    newDomain.setLodgeCantBreakfastTcGuide(programItemDomain.getLodgeCantBreakfastTcGuide());
                                    newDomain.setLodgeCantLunchTcGuide(programItemDomain.getLodgeCantLunchTcGuide());
                                    newDomain.setLodgeCantDinnerTcGuide(programItemDomain.getLodgeCantDinnerTcGuide());
                                    newDomain.setLodgeCantBoxLunchTcGuide(programItemDomain.getLodgeCantBoxLunchTcGuide());
                                    newDomain.setLodgeCantSwbTcLocalGuide(programItemDomain.getLodgeCantSwbTcLocalGuide());
                                    newDomain.setLodgeCantTwinTcLocalGuide(programItemDomain.getLodgeCantTwinTcLocalGuide());
                                    newDomain.setLodgeCantBreakfastTcLocalGuide(programItemDomain.getLodgeCantBreakfastTcLocalGuide());
                                    newDomain.setLodgeCantLunchTcLocalGuide(programItemDomain.getLodgeCantLunchTcLocalGuide());
                                    newDomain.setLodgeCantDinnerTcLocalGuide(programItemDomain.getLodgeCantDinnerTcLocalGuide());
                                    newDomain.setLodgeCantBoxLunchTcLocalGuide(programItemDomain.getLodgeCantBoxLunchTcLocalGuide());
                                    newDomain.setLodgeCantSwbGuide(programItemDomain.getLodgeCantSwbGuide());
                                    newDomain.setLodgeCantTwinGuide(programItemDomain.getLodgeCantTwinGuide());
                                    newDomain.setLodgeCantBreakfastGuide(programItemDomain.getLodgeCantBreakfastGuide());
                                    newDomain.setLodgeCantLunchGuide(programItemDomain.getLodgeCantLunchGuide());
                                    newDomain.setLodgeCantDinnerGuide(programItemDomain.getLodgeCantDinnerGuide());
                                    newDomain.setLodgeCantBoxLunchGuide(programItemDomain.getLodgeCantBoxLunchGuide());
                                }else if (newDomain.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())){
                                    newDomain.setLodgePvt(programItemDomain.getPvt());
                                    newDomain.setLodgeSic(programItemDomain.getSic());
                                    newDomain.setServiceForOperation(true);
                                }
                            }
                        }else if (supplierTypeDomainGlamping.getId().equals(programItemDomain.getSupplierType().getId())){
                            if (
                                    (newDomain.getPaxAdultTotal() != null && newDomain.getPaxAdultTotal() > 0) ||
                                            (newDomain.getPaxChildrenTotal() != null && newDomain.getPaxChildrenTotal() > 0) ||
                                            (newDomain.getPaxTcTotal() != null && newDomain.getPaxTcTotal() > 0) ||
                                            (newDomain.getPaxGuideTotal() != null && newDomain.getPaxGuideTotal() > 0)
                            ){
                                newDomain.setLodgingCantNight(1);
                            }else if (newDomain.getPaxTcLocalTotal() != null && newDomain.getPaxTcLocalTotal() > 0){
                                newDomain.setLodgingTcLocalCantNight(1);
                            } else{
                                newDomain.setLodgingCantNight(1);
                            }

                            newDomain.setLodgingCantSwb(programItemDomain.getLodgingCantSwb());
                            newDomain.setLodgingCantMat(programItemDomain.getLodgingCantMat());
                            newDomain.setLodgingCantTwin(programItemDomain.getLodgingCantTwin());
                            newDomain.setLodgingCantTrp(programItemDomain.getLodgingCantTrp());
                            newDomain.setLodgingCategoryStr(programItemDomain.getLodgingCategoryStr());
                            newDomain.setLodgingCantBreakfast(programItemDomain.getLodgingCantBreakfast());
                            newDomain.setLodgingCantLunch(programItemDomain.getLodgingCantLunch());
                            newDomain.setLodgingCantDinner(programItemDomain.getLodgingCantDinner());
                            newDomain.setLodgingCantBoxLunch(programItemDomain.getLodgingCantBoxLunch());
                            newDomain.setLodgingCantSwbTcGuide(programItemDomain.getLodgingCantSwbTcGuide());
                            newDomain.setLodgingCantTwinTcGuide(programItemDomain.getLodgingCantTwinTcGuide());
                            newDomain.setLodgingCantBreakfastTcGuide(programItemDomain.getLodgingCantBreakfastTcGuide());
                            newDomain.setLodgingCantLunchTcGuide(programItemDomain.getLodgingCantLunchTcGuide());
                            newDomain.setLodgingCantDinnerTcGuide(programItemDomain.getLodgingCantDinnerTcGuide());
                            newDomain.setLodgingCantBoxLunchTcGuide(programItemDomain.getLodgingCantBoxLunchTcGuide());
                            newDomain.setLodgingCantSwbTcLocalGuide(programItemDomain.getLodgingCantSwbTcLocalGuide());
                            newDomain.setLodgingCantTwinTcLocalGuide(programItemDomain.getLodgingCantTwinTcLocalGuide());
                            newDomain.setLodgingCantBreakfastTcLocalGuide(programItemDomain.getLodgingCantBreakfastTcLocalGuide());
                            newDomain.setLodgingCantLunchTcLocalGuide(programItemDomain.getLodgingCantLunchTcLocalGuide());
                            newDomain.setLodgingCantDinnerTcLocalGuide(programItemDomain.getLodgingCantDinnerTcLocalGuide());
                            newDomain.setLodgingCantBoxLunchTcLocalGuide(programItemDomain.getLodgingCantBoxLunchTcLocalGuide());
                            newDomain.setLodgingCantSwbGuide(programItemDomain.getLodgingCantSwbGuide());
                            newDomain.setLodgingCantTwinGuide(programItemDomain.getLodgingCantTwinGuide());
                            newDomain.setLodgingCantBreakfastGuide(programItemDomain.getLodgingCantBreakfastGuide());
                            newDomain.setLodgingCantLunchGuide(programItemDomain.getLodgingCantLunchGuide());
                            newDomain.setLodgingCantDinnerGuide(programItemDomain.getLodgingCantDinnerGuide());
                            newDomain.setLodgingCantBoxLunchGuide(programItemDomain.getLodgingCantBoxLunchGuide());

                            newDomain.setServiceForOperation(false);
                        }else if (supplierTypeDomainOperator.getId().equals(programItemDomain.getSupplierType().getId())){
                            newDomain.setOperatorPvt(programItemDomain.getPvt());
                            newDomain.setOperatorSic(programItemDomain.getSic());
                            newDomain.setServiceForOperation(true);
                        }else if (supplierTypeDomainTransport.getId().equals(programItemDomain.getSupplierType().getId())){
                            newDomain.setServiceForOperation(true);
                        }else if (supplierTypeDomainRestaurant.getId().equals(programItemDomain.getSupplierType().getId())){
                            newDomain.setRestaurantCantBreakfast(programItemDomain.getRestaurantCantBreakfast());
                            newDomain.setRestaurantCantLunch(programItemDomain.getRestaurantCantLunch());
                            newDomain.setRestaurantCantDinner(programItemDomain.getRestaurantCantDinner());
                            newDomain.setRestaurantCantBoxLunch(programItemDomain.getRestaurantCantBoxLunch());
                            newDomain.setRestaurantCantBreakfastTcGuide(programItemDomain.getRestaurantCantBreakfastTcGuide());
                            newDomain.setRestaurantCantLunchTcGuide(programItemDomain.getRestaurantCantLunchTcGuide());
                            newDomain.setRestaurantCantDinnerTcGuide(programItemDomain.getRestaurantCantDinnerTcGuide());
                            newDomain.setRestaurantCantBoxLunchTcGuide(programItemDomain.getRestaurantCantBoxLunchTcGuide());
                            newDomain.setRestaurantCantBreakfastTcLocalGuide(programItemDomain.getRestaurantCantBreakfastTcLocalGuide());
                            newDomain.setRestaurantCantLunchTcLocalGuide(programItemDomain.getRestaurantCantLunchTcLocalGuide());
                            newDomain.setRestaurantCantDinnerTcLocalGuide(programItemDomain.getRestaurantCantDinnerTcLocalGuide());
                            newDomain.setRestaurantCantBoxLunchTcLocalGuide(programItemDomain.getRestaurantCantBoxLunchTcLocalGuide());
                            newDomain.setRestaurantCantBreakfastGuide(programItemDomain.getRestaurantCantBreakfastGuide());
                            newDomain.setRestaurantCantLunchGuide(programItemDomain.getRestaurantCantLunchGuide());
                            newDomain.setRestaurantCantDinnerGuide(programItemDomain.getRestaurantCantDinnerGuide());
                            newDomain.setRestaurantCantBoxLunchGuide(programItemDomain.getRestaurantCantBoxLunchGuide());
                            newDomain.setServiceForOperation(false);
                        }else{
                            newDomain.setServiceForOperation(false);
                        }
                        newDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName());
                        saveItineraryItems.add(newDomain);
                        indexItem = indexItem + 1;
                    }
                }
                itineraryItemRepo.createAll(saveItineraryItems);
                item = item + 1;
            }

            List<ItineraryDateDomain> listItineraryDateUpdateLodging = repo.searchAllByRegisterGroupSimpleDomainCaseProgram(registerGroup.getId());
            listItineraryDateUpdateLodging = listItineraryDateUpdateLodging.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateUpdateLodging);

            List<ItineraryItemDomain> listItineraryItemUpdateLodging = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseProgram(registerGroup.getId());
            listItineraryItemUpdateLodging = listItineraryItemUpdateLodging.stream().collect(Collectors.toList());
            Collections.sort(listItineraryItemUpdateLodging);

            if (listItineraryItemUpdateLodging != null && !listItineraryItemUpdateLodging.isEmpty()){
                for (ItineraryItemDomain itineraryItemDomain : listItineraryItemUpdateLodging){
                    if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){

                        int index = listItineraryDateUpdateLodging.indexOf(itineraryItemDomain.getItineraryDate());
                        index = index + 1;

                        if (index <= listItineraryDateUpdateLodging.size() - 1){

                            ItineraryDateDomain itineraryDateDomain = listItineraryDateUpdateLodging.get(index);

                            if (itineraryItemDomain.getPaxAdultTotal() != null || itineraryItemDomain.getPaxChildrenTotal() != null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxAdultTotal() != null ? itineraryItemDomain.getPaxAdultTotal() : 0)
                                                + (itineraryItemDomain.getPaxChildrenTotal() != null ? itineraryItemDomain.getPaxChildrenTotal() : 0));
                            }

                            if (itineraryItemDomain.getPaxTcTotal()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTc(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxTcTotal() != null ? itineraryItemDomain.getPaxTcTotal() : 0));
                            }

                            if (itineraryItemDomain.getPaxTcLocalTotal()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocal(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxTcLocalTotal() != null ? itineraryItemDomain.getPaxTcLocalTotal() : 0));
                            }

                            if (itineraryItemDomain.getPaxGuideTotal()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuide(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxGuideTotal() != null ? itineraryItemDomain.getPaxGuideTotal() : 0));
                            }

                        }

                    }else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId()) && itineraryItemDomain.getServiceGroup() != null && !itineraryItemDomain.getServiceGroup().isEmpty() && itineraryItemDomain.getServiceGroup().equals(ServiceGroupEnum.LODGING.getName())){
                        int index = listItineraryDateUpdateLodging.indexOf(itineraryItemDomain.getItineraryDate());
                        index = index + 1;

                        if (index <= listItineraryDateUpdateLodging.size() - 1){

                            ItineraryDateDomain itineraryDateDomain = listItineraryDateUpdateLodging.get(index);

                            if (itineraryItemDomain.getPaxAdultTotal() != null || itineraryItemDomain.getPaxChildrenTotal() != null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryLodge(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxAdultTotal() != null ? itineraryItemDomain.getPaxAdultTotal() : 0)
                                                + (itineraryItemDomain.getPaxChildrenTotal() != null ? itineraryItemDomain.getPaxChildrenTotal() : 0));
                            }

                            if (itineraryItemDomain.getPaxTcTotal()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLodge(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxTcTotal() != null ? itineraryItemDomain.getPaxTcTotal() : 0));
                            }

                            if (itineraryItemDomain.getPaxTcLocalTotal()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocalLodge(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxTcLocalTotal() != null ? itineraryItemDomain.getPaxTcLocalTotal() : 0));
                            }

                            if (itineraryItemDomain.getPaxGuideTotal()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuideLodge(
                                        itineraryItemDomain.getId(),
                                        itineraryDateDomain.getId(),
                                        (itineraryItemDomain.getPaxGuideTotal() != null ? itineraryItemDomain.getPaxGuideTotal() : 0));
                            }

                        }

                    }
                }
            }
        }
    }

    private void createDateItinerary(RegisterGroupDomain registerGroup, Integer day, LocalDate date){
        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
        Integer indexDay = day + 1;
        itineraryDateDomain.setName("");
        itineraryDateDomain.setItineraryDate(date);
        itineraryDateDomain.setRegisterGroup(registerGroup);
        itineraryDateDomain.setIndex(indexDay);
        itineraryDateDomain.setItemItinerary(indexDay.toString());
        itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
        repo.create(itineraryDateDomain);
    }

    private ItineraryDateDomain createDateItineraryFromProgramDate(RegisterGroupDomain registerGroup, ProgramDateDomain programDate, LocalDate date){
        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
        itineraryDateDomain.setName(programDate.getName());
        itineraryDateDomain.setItineraryDate(date);
        itineraryDateDomain.setRegisterGroup(registerGroup);
        itineraryDateDomain.setIndex(programDate.getDay());
        itineraryDateDomain.setItemItinerary(programDate.getDay().toString());
        itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
        return repo.create(itineraryDateDomain);
    }

    private Integer getLastIndexItineraryItem(String idRegisterGroup) {
        Integer index = 0;
        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByRegisterGroup(idRegisterGroup).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryItemDomain::getIndex));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryDateDomain> searchAllDomainByRegisterGroup(String idRegisterGroup) {
        List<ItineraryDateDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateResponse searchByRegisterGroupAndItineraryDate(String idRegisterGroup, String idItineraryDate) {
        ItineraryDateDomain itineraryDateDomain = repo.searchAllByRegisterGroupAndItineraryDate(idRegisterGroup, idItineraryDate);
        return ItineraryDateResponse.from(itineraryDateDomain);
    }
}
