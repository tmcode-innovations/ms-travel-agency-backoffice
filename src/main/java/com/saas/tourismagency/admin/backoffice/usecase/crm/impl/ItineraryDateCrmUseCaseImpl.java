package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itinerarydate.ItineraryDateCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultItineraryDateCrm;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.CostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.ItineraryItemCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programcosttour.ProgramCostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programdate.ProgramDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programitem.ProgramItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.*;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.ItineraryDateCrmCreateEvent;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.ItineraryDateCrmDeleteEvent;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.ItineraryDateCrmEditEvent;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ItineraryDateCrmUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ItineraryDateCrmUseCaseImpl implements ItineraryDateCrmUseCase {

    @Autowired
    private ItineraryDateCrmRepo repo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private ItineraryDateCrmConverter converter;

    @Autowired
    private SetFieldDefaultItineraryDateCrm setFieldDefault;

    @Autowired
    private ItineraryItemCrmRepo itineraryItemCrmRepo;

    @Autowired
    private ProgramItemRepo programItemRepo;

    @Autowired
    private ProgramDateRepo programDateRepo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private ItineraryItemCrmRepo itineraryItemRepo;

    @Autowired
    private ProgramCostTourRepo programCostTourRepo;

    @Autowired
    private CostTourRepo costTourRepo;

    @Override
    @Transactional
    public ItineraryDateResponse create(ItineraryDateCreateRequest request) {
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        List<ItineraryDateDomain> listItineraryDateByQuotation = repo.searchAllByQuotation(quotationDomain.getId());
        Collections.sort(listItineraryDateByQuotation);
        Integer index = listItineraryDateByQuotation.getFirst().getIndex();
        ItineraryDateDomain newDomain = request.to(quotationDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(index + 1);
        newDomain.setItemItinerary(Integer.toString(index + 1));
        ItineraryDateDomain createdDomain = repo.create(newDomain);
        /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryDateCrmCreateEvent(this, createdDomain));
            }
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
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        ItineraryDateDomain itineraryDateDomain = request.to(quotationDomain);
        itineraryDateDomain.setIndex(old.getIndex());
        itineraryDateDomain.setItemItinerary(old.getItemItinerary());
        itineraryDateDomain.setTours(old.getTours());
        ItineraryDateDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(itineraryDateDomain, old));
        /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, editedDomain));
            }
        }*/
        return ItineraryDateResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ItineraryDateResponse old = findById(id);
        repo.deleteById(old.getId());
        /*if (old.getQuotation().getIdentifyFile() != null && !old.getQuotation().getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                this.eventPublisher.publishEvent(new ItineraryDateCrmDeleteEvent(this, repo.findById(id)));
            }
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
    public ItineraryDateSearchResponse searchAll(ItineraryDateSearchRequest itineraryDateSearchRequest) {
       PageDTO<ItineraryDateDomain> pageDTO = repo.searchAll(itineraryDateSearchRequest);
       ItineraryDateSearchResponse itineraryDateSearchRespose = new ItineraryDateSearchResponse();
       itineraryDateSearchRespose.setItineraryDates(pageDTO.getContent().stream().map(itineraryDateDomain ->  ItineraryDateResponse.from(itineraryDateDomain)).toList());
       itineraryDateSearchRespose.setTotalElements(pageDTO.getTotalElements());
       itineraryDateSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return itineraryDateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateSearchResponse searchFilterAll(ItineraryDateSearchFilterRequest itineraryDateSearchFilterRequest) {
        PageDTO<ItineraryDateDomain> pageDTO = repo.searchAllFilter(itineraryDateSearchFilterRequest);
        ItineraryDateSearchResponse itineraryDateSearchRespose = new ItineraryDateSearchResponse();
        itineraryDateSearchRespose.setItineraryDates(pageDTO.getContent().stream().map(itineraryDateDomain ->  ItineraryDateResponse.from(itineraryDateDomain)).toList());
        itineraryDateSearchRespose.setTotalElements(pageDTO.getTotalElements());
        itineraryDateSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return itineraryDateSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryDateResponse> searchAllByQuotation(String idQuotation) {
        List<ItineraryDateDomain> list = repo.searchAllByQuotation(idQuotation);
        list = list.stream().collect(Collectors.toList());
        Collections.sort(list);
        return list.stream().map(itineraryDateDomain ->  ItineraryDateResponse.from(itineraryDateDomain)).toList();
    }

    @Override
    @Transactional
    public void generateDateItinerary(QuotationDomain quotation, Integer days, LocalDate dateInit) {
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> createDateItinerary(quotation, item, dateInit.plusDays(item)));
    }

    @Override
    @Transactional
    public void generateDateItineraryByProgram(QuotationDomain quotation, ProgramDomain program, LocalDate dateInit) {
        List<ProgramDateDomain> programDateDomainList = programDateRepo.searchAllByProgramSimpleDomain(program.getId());
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        List<ItineraryItemDomain> saveItineraryItemsForUpdateCostTours = new ArrayList<>();
        if (programDateDomainList != null && !programDateDomainList.isEmpty()) {
            List<ProgramDateDomain> listProgramDateResult = programDateDomainList.stream().collect(Collectors.toList());
            Collections.sort(listProgramDateResult);
            Integer item = 0;
            Integer indexItem = 1;
            for (ProgramDateDomain programDateDomain: listProgramDateResult
            ) {
                ItineraryDateDomain itineraryDateDomain = createDateItineraryFromProgramDate(quotation, programDateDomain, dateInit.plusDays(item));
                List<ProgramItemDomain> listProgramItem = programItemRepo.searchAllByProgramDateAndProgramSimpleDomainCaseItinerary(programDateDomain.getId(), program.getId());
                List<ProgramItemDomain> listProgramItemResult;
                List<ItineraryItemDomain> saveItineraryItems = new ArrayList<>();
                if (listProgramItem != null && !listProgramItem.isEmpty()){
                    listProgramItemResult = listProgramItem.stream().collect(Collectors.toList());
                    Collections.sort(listProgramItemResult);
                    for (ProgramItemDomain programItemDomain: listProgramItemResult
                    ) {
                        ItineraryItemDomain newDomain = new ItineraryItemDomain();
                        newDomain.setQuotation(quotation);
                        newDomain.setItineraryDate(itineraryDateDomain);
                        newDomain.setSupplierType(programItemDomain.getSupplierType());
                        newDomain.setSupplier(programItemDomain.getSupplier());
                        newDomain.setService(programItemDomain.getService());
                        newDomain.setObservation(programItemDomain.getObservation());
                        newDomain.setPrice(0.0);
                        newDomain.setPaxAdultTotal(quotation.getPaxAdult());
                        newDomain.setPaxChildrenTotal(quotation.getPaxChild());
                        newDomain.setPaxTotal(quotation.getPaxAdult() + quotation.getPaxChild());
                        newDomain.setPaxTcTotal(quotation.getPaxTc());
                        newDomain.setPaxTcLocalTotal(quotation.getPaxTcLocal());
                        newDomain.setTenant(UserResolver.resolveUser().getTenant());
                        newDomain.setIndex(indexItem);
                        if (supplierTypeDomainLodging.getId().equals(programItemDomain.getSupplierType().getId())){
                            newDomain.setLodgingCantNight(1);
                        }else if (supplierTypeDomainLodge.getId().equals(programItemDomain.getSupplierType().getId())){
                            newDomain.setLodgeCantNight(1);
                            newDomain.setLodgePvt(programItemDomain.getPvt());
                            newDomain.setLodgeSic(programItemDomain.getSic());
                        }else if (supplierTypeDomainGlamping.getId().equals(programItemDomain.getSupplierType().getId())){
                            newDomain.setLodgingCantNight(1);
                        }else if (supplierTypeDomainOperator.getId().equals(programItemDomain.getSupplierType().getId())){
                            newDomain.setOperatorPvt(programItemDomain.getPvt());
                            newDomain.setOperatorSic(programItemDomain.getSic());
                        }
                        LocalDateTime localDateTimeTemp = LocalDateTime.now();
                        LocalDateTime localDateTime = LocalDateTime.of(
                                itineraryDateDomain.getItineraryDate().getYear(),
                                itineraryDateDomain.getItineraryDate().getMonth(),
                                itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                                localDateTimeTemp.getHour(),
                                localDateTimeTemp.getMinute(),
                                localDateTimeTemp.getSecond()
                        );
                        newDomain.setItineraryDateTime(localDateTime);
                        newDomain.setTour(programItemDomain.getTour());
                        saveItineraryItems.add(newDomain);
                        indexItem = indexItem + 1;
                    }
                }
                saveItineraryItemsForUpdateCostTours.addAll(itineraryItemRepo.createAll(saveItineraryItems));
                item = item + 1;
            }
        }
        List<ProgramCostTourDomain> listProgramCostTourDomains = programCostTourRepo.searchAllByProgramSimpleDomainCaseShowProgramCostTour(program.getId());
        if (listProgramCostTourDomains != null && !listProgramCostTourDomains.isEmpty()){
            listProgramCostTourDomains = listProgramCostTourDomains.stream().collect(Collectors.toList());
            Collections.sort(listProgramCostTourDomains);
            for (ProgramCostTourDomain programCostTourDomain: listProgramCostTourDomains){
                CostTourDomain costTourDomain = new CostTourDomain();
                costTourDomain.setTour(programCostTourDomain.getTour());
                costTourDomain.setQuotation(quotation);
                costTourDomain.setSubTotalAmount(0.00);
                costTourDomain.setNumMaxPax(quotation.getPaxTotal());
                ItineraryDateDomain itineraryDateDomain = repo.getByQuotationAndItemItinerary(quotation.getId(), programCostTourDomain.getDay().toString());
                costTourDomain.setItineraryDate(itineraryDateDomain);
                costTourDomain.setTenant(UserResolver.resolveUser().getTenant());
                CostTourDomain createCostTourDomain = costTourRepo.create(costTourDomain);

                List<ItineraryItemDomain>  tempForUpdates = saveItineraryItemsForUpdateCostTours.stream().filter(
                        itineraryItemDomain -> _matchesCostTourAndItineraryDate(itineraryItemDomain, costTourDomain))
                        .collect(Collectors.toList());
                if (tempForUpdates != null && !tempForUpdates.isEmpty()){
                    for (ItineraryItemDomain itemTempForUpdate: tempForUpdates){
                        itemTempForUpdate.setCostTour(createCostTourDomain);
                        itineraryItemRepo.edit(itemTempForUpdate);
                    }
                }
            }
        }
    }

    private void createDateItinerary(QuotationDomain quotation, Integer day, LocalDate date){
        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
        Integer indexDay = day + 1;
        // itineraryDateDomain.setName("Dia " + indexDay);
        itineraryDateDomain.setName("");
        itineraryDateDomain.setItineraryDate(date);
        itineraryDateDomain.setQuotation(quotation);
        itineraryDateDomain.setIndex(indexDay);
        itineraryDateDomain.setItemItinerary(indexDay.toString());
        itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
        repo.create(itineraryDateDomain);
    }

    private ItineraryDateDomain createDateItineraryFromProgramDate(QuotationDomain quotation, ProgramDateDomain programDate, LocalDate date){
        ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
        itineraryDateDomain.setName(programDate.getName());
        itineraryDateDomain.setItineraryDate(date);
        itineraryDateDomain.setQuotation(quotation);
        itineraryDateDomain.setIndex(programDate.getDay());
        itineraryDateDomain.setItemItinerary(programDate.getDay().toString());
        itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
        if (programDate.getTours() != null && !programDate.getTours().isEmpty()){
            itineraryDateDomain.setTours(programDate.getTours());
        }
        return repo.create(itineraryDateDomain);
    }

    private Integer getLastIndexItineraryItem(String idQuotation) {
        Integer index = 0;
        List<ItineraryItemDomain> list = itineraryItemCrmRepo.searchAllByQuotation(idQuotation).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryItemDomain::getIndex));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }

    private boolean _matchesCostTourAndItineraryDate(ItineraryItemDomain itineraryItemDomain, CostTourDomain costTourDomain) {
        if (itineraryItemDomain == null || costTourDomain == null) {
            return false;
        }
        if (itineraryItemDomain.getTour() == null
                || itineraryItemDomain.getItineraryDate() == null
                || costTourDomain.getTour() == null
                || costTourDomain.getItineraryDate() == null) {
            return false;
        }
        return Objects.equals(itineraryItemDomain.getTour().getId(), costTourDomain.getTour().getId())
                && Objects.equals(itineraryItemDomain.getItineraryDate().getId(), costTourDomain.getItineraryDate().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryDateDomain> searchAllDomainByQuotation(String idQuotation) {
        List<ItineraryDateDomain> list = repo.searchAllByQuotation(idQuotation);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateResponse searchByQuotationAndItineraryDate(String idQuotation, String idItineraryDate) {
        ItineraryDateDomain itineraryDateDomain = repo.searchAllByQuotationAndItineraryDate(idQuotation, idItineraryDate);
        return ItineraryDateResponse.from(itineraryDateDomain);
    }

}
