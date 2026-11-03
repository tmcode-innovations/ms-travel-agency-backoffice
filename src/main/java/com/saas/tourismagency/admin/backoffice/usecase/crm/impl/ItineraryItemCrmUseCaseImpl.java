package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.itinerarydate.ItineraryDateCrmConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultItineraryItemCrm;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.CostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem.CostTourServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.ItineraryItemCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tourdate.TourDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.TourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.LodgingCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.events.crm.event.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.crm.ItineraryItemCrmUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ItineraryItemCrmUseCaseImpl implements ItineraryItemCrmUseCase {

    @Autowired
    private ItineraryItemCrmRepo repo;

    @Autowired
    private ItineraryDateCrmRepo itineraryDateRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private SupplierLodgingRepo supplierLodgingRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private DepartmentRegionRepo departmentRegionRepo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private TourDateRepo tourDateRepo;

    @Autowired
    private TourItemRepo tourItemRepo;

    @Autowired
    private CostTourRepo costTourRepo;

    @Autowired
    private CostTourServiceItemRepo costTourServiceItemRepo;

    @Autowired
    private LodgingCategoryRepo lodgingCategoryRepo;

    @Autowired
    private ItineraryDateCrmConverter converter;

    @Autowired
    private SetFieldDefaultItineraryItemCrm setFieldDefault;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional
    public ItineraryItemResponse create(ItineraryItemCreateRequest request) {
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            departmentRegionDomain = departmentRegionRepo.findById(request.getDepartmentRegion());
        }
        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getLodgingForOperator() != null && !request.getLodgingForOperator().isEmpty()){
            supplierLodgingDomain = supplierLodgingRepo.findById(request.getLodgingForOperator());
        }
        ItineraryItemDomain newDomain = request.to(quotationDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));
        if (supplierTypeDomainLodging.equals(supplierTypeDomain)){
            newDomain.setLodgingCantNight(1);
        }else if (supplierTypeDomainLodge.equals(supplierTypeDomain)){
            newDomain.setLodgeCantNight(1);
        }
        ItineraryItemDomain createdDomain = repo.create(newDomain);
        /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryItemCrmCreateEvent(this, createdDomain));
            }
        }*/
        return ItineraryItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse create(ItineraryItemCreateRequest request, QuotationDomain quotationDomain) {
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain idBaseDomainSupplier = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(idBaseDomainSupplier.getId());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain idBaseDomainService = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(idBaseDomainService.getId());
        }
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain idBaseDomainDepartmentRegion = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            departmentRegionDomain = new DepartmentRegionDomain();
            departmentRegionDomain.setId(idBaseDomainDepartmentRegion.getId());
        }
        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getLodgingForOperator() != null && !request.getLodgingForOperator().isEmpty()){
            IdBaseDomain idBaseDomainSupplierLodging = supplierLodgingRepo.findByIdEntity(request.getLodgingForOperator());
            supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(idBaseDomainSupplierLodging.getId());
        }
        ItineraryItemDomain newDomain = request.to(quotationDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));
        if (supplierTypeDomainLodging.equals(supplierTypeDomain)){
            newDomain.setLodgingCantNight(1);
        }else if (supplierTypeDomainLodge.equals(supplierTypeDomain)){
            newDomain.setLodgeCantNight(1);
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
            newDomain.setLodgingCantNight(1);
        }
        LocalDateTime localDateTime = LocalDateTime.of(
                itineraryDateDomain.getItineraryDate().getYear(),
                itineraryDateDomain.getItineraryDate().getMonth(),
                itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                request.getItineraryDateTime().getHour(),
                request.getItineraryDateTime().getMinute(),
                request.getItineraryDateTime().getSecond()
        );
        newDomain.setItineraryDateTime(localDateTime);
        ItineraryItemDomain createdDomain = repo.create(newDomain);
        /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryItemCrmCreateEvent(this, createdDomain));
            }
        }*/
        return ItineraryItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public void createMultiple(ItineraryItemCreateRequest request, QuotationDomain quotationDomain) {

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
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
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            if (departmentRegionIdBaseDomain != null){
                departmentRegionDomain = new DepartmentRegionDomain();
                departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
            }
        }
        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getLodgingForOperator() != null && !request.getLodgingForOperator().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getLodgingForOperator());
            if (supplierLodgingIdBaseDomain != null){
                supplierLodgingDomain = new SupplierLodgingDomain();
                supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
            }
        }
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        if (request.getItineraryDates() != null && !request.getItineraryDates().isEmpty()){
            for(String item: request.getItineraryDates()){
                ItineraryDateDomain itineraryDateDomain;
                itineraryDateDomain = itineraryDateRepo.findByIdSimpleDomainCaseGeneric(item);
                ItineraryItemDomain newDomain = request.to(quotationDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));
                if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
                    newDomain.setLodgingCantNight(1);
                }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
                    newDomain.setLodgeCantNight(1);
                }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
                    newDomain.setLodgingCantNight(1);
                }
                LocalDateTime localDateTime = LocalDateTime.of(
                        itineraryDateDomain.getItineraryDate().getYear(),
                        itineraryDateDomain.getItineraryDate().getMonth(),
                        itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                        request.getItineraryDateTime().getHour(),
                        request.getItineraryDateTime().getMinute(),
                        request.getItineraryDateTime().getSecond()
                );
                newDomain.setItineraryDateTime(localDateTime);

                // To Calculate Total Price
                Double amountAdult = 0.0;
                Double amountChildren = 0.0;
                Double amountTc = 0.0;
                Double amountTcLocal = 0.0;
                Double amountGuide = 0.0;
                if (newDomain.getPriceAdult() != null && newDomain.getPaxAdultTotal()!= null){
                    amountAdult = newDomain.getPriceAdult() * newDomain.getPaxAdultTotal();
                }
                if (newDomain.getPriceChildren() != null && newDomain.getPaxChildrenTotal()!= null){
                    amountChildren = newDomain.getPriceChildren() * newDomain.getPaxChildrenTotal();
                }
                if (newDomain.getPriceTc() != null && newDomain.getPaxTcTotal()!= null){
                    amountTc = newDomain.getPriceTc() * newDomain.getPaxTcTotal();
                }
                if (newDomain.getPriceTcLocal() != null && newDomain.getPaxTcLocalTotal()!= null){
                    amountTcLocal = newDomain.getPriceTcLocal() * newDomain.getPaxTcLocalTotal();
                }
                if (newDomain.getPriceGuide() != null && newDomain.getPaxGuideTotal()!= null){
                    amountGuide = newDomain.getPriceGuide() * newDomain.getPaxGuideTotal();
                }

                if (newDomain.getService().getWithoutCost() != null && !newDomain.getService().getWithoutCost()){
                    newDomain.setPrice(
                                    amountAdult
                                    + amountChildren
                                    + amountTc
                                    + amountTcLocal
                                    + amountGuide
                    );
                }else if (newDomain.getService().getWithoutCost() == null){
                    newDomain.setPrice(
                            amountAdult
                                    + amountChildren
                                    + amountTc
                                    + amountTcLocal
                                    + amountGuide
                    );
                }else{
                    newDomain.setPrice(0.0);
                }

                newDomain.setPaxTotal(newDomain.getPaxAdultTotal() + newDomain.getPaxChildrenTotal());

                ItineraryItemDomain createdDomain = repo.create(newDomain);
                /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
                    if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryItemCrmCreateEvent(this, createdDomain));
                    }
                }*/
            }
        }
    }

    @Override
    @Transactional
    public void createAdvanced(ItineraryServiceAdvancedCreateRequest request, QuotationDomain quotationDomain) {

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        if (request.getSuppliers() != null && !request.getSuppliers().isEmpty()){
            for (ContainerSupplierAdvancedCreateRequest containerSupplierAdvancedCreateRequest: request.getSuppliers()){
                SupplierTypeDomain supplierTypeDomain = null;
                if (containerSupplierAdvancedCreateRequest.getSupplierType().getId() != null && !containerSupplierAdvancedCreateRequest.getSupplierType().getId().isEmpty()){
                    supplierTypeDomain = supplierTypeRepo.findById(containerSupplierAdvancedCreateRequest.getSupplierType().getId());
                }

                SupplierDomain supplierDomain = null;
                if (containerSupplierAdvancedCreateRequest.getSupplier().getId() != null && !containerSupplierAdvancedCreateRequest.getSupplier().getId().isEmpty()){
                    IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(containerSupplierAdvancedCreateRequest.getSupplier().getId());
                    if (supplierIdBaseDomain != null){
                        supplierDomain = new SupplierDomain();
                        supplierDomain.setId(supplierIdBaseDomain.getId());
                    }
                }

                DepartmentRegionDomain departmentRegionDomain = null;
                if (containerSupplierAdvancedCreateRequest.getDepartmentRegion().getId() != null && !containerSupplierAdvancedCreateRequest.getDepartmentRegion().getId().isEmpty()){
                    IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(containerSupplierAdvancedCreateRequest.getDepartmentRegion().getId());
                    if (departmentRegionIdBaseDomain != null){
                        departmentRegionDomain = new DepartmentRegionDomain();
                        departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
                    }
                }

                if (containerSupplierAdvancedCreateRequest.getServices() != null && !containerSupplierAdvancedCreateRequest.getServices().isEmpty()){
                    for (ContainerServiceAdvancedCreateRequest containerServiceAdvancedCreateRequest: containerSupplierAdvancedCreateRequest.getServices()){
                        ServiceDomain serviceDomain = null;
                        if (containerServiceAdvancedCreateRequest.getService().getId() != null && !containerServiceAdvancedCreateRequest.getService().getId().isEmpty()){
                            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getService().getId());
                            if (serviceIdBaseDomain != null){
                                serviceDomain = new ServiceDomain();
                                serviceDomain.setId(serviceIdBaseDomain.getId());
                            }
                        }

                        ItineraryDateDomain itineraryDateDomain;
                        itineraryDateDomain = itineraryDateRepo.findByIdSimpleDomainCaseGeneric(containerServiceAdvancedCreateRequest.getItineraryDate().getId());
                        ItineraryItemDomain newDomain = new ItineraryItemDomain();
                        newDomain.setQuotation(quotationDomain);
                        newDomain.setItineraryDate(itineraryDateDomain);
                        newDomain.setSupplierType(supplierTypeDomain);
                        newDomain.setSupplier(supplierDomain);
                        newDomain.setService(serviceDomain);
                        newDomain.setDepartmentRegion(departmentRegionDomain);
                        newDomain.setTenant(UserResolver.resolveUser().getTenant());
                        newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));

                        newDomain.setPaxAdultTotal(containerServiceAdvancedCreateRequest.getPaxAdultTotal());
                        newDomain.setPaxChildrenTotal(containerServiceAdvancedCreateRequest.getPaxChildrenTotal());
                        newDomain.setPaxTcTotal(containerServiceAdvancedCreateRequest.getPaxTcTotal());
                        newDomain.setPaxTcLocalTotal(containerServiceAdvancedCreateRequest.getPaxTcLocalTotal());
                        newDomain.setPaxGuideTotal(containerServiceAdvancedCreateRequest.getPaxGuideTotal());

                        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
                            newDomain.setLodgingCantNight(1);
                        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
                            newDomain.setLodgeCantNight(1);
                        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
                            newDomain.setLodgingCantNight(1);
                        }
                        LocalDateTime localDateTime = LocalDateTime.of(
                                itineraryDateDomain.getItineraryDate().getYear(),
                                itineraryDateDomain.getItineraryDate().getMonth(),
                                itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                                containerServiceAdvancedCreateRequest.getItineraryDateTime().getHour(),
                                containerServiceAdvancedCreateRequest.getItineraryDateTime().getMinute(),
                                containerServiceAdvancedCreateRequest.getItineraryDateTime().getSecond()
                        );
                        newDomain.setItineraryDateTime(localDateTime);

                        newDomain.setPaxTotal(newDomain.getPaxAdultTotal() + newDomain.getPaxChildrenTotal());

                        repo.create(newDomain);
                    }
                }
            }
        }

    }

    @Override
    @Transactional
    public void createTour(ItineraryTourItemCreateRequest request, QuotationDomain quotationDomain) {
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);

        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            tourDomain = tourRepo.findById(request.getTour());
        }
        if (tourDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(request.getTour()));
        }
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }
        if (itineraryDateDomain == null) {
            throw new NotFoundException("No itinerary date found with id '%s'.".formatted(request.getItineraryDate()));
        }
        if (tourDomain.getCantDay() + Integer.parseInt(itineraryDateDomain.getItemItinerary()) - 1 > quotationDomain.getCantDayTour()){
            throw new BadRequestException(MessagesValidation.VALIDATION_TOUR_NOT_ASSIGNED_ITINERARY);
        }

        List<CostTourDomain> costTourDomainList = costTourRepo.searchAllByQuotationAndItineraryDateSimpleDomainCaseShowCostTour(quotationDomain.getId(), request.getItineraryDate());
        List<CostTourDomain> existCostTourUnique = costTourDomainList.stream().filter(costTourDomain -> costTourDomain.getTour().getUniqueDay() != null && costTourDomain.getTour().getUniqueDay() ).collect(Collectors.toList());
        if (existCostTourUnique != null && !existCostTourUnique.isEmpty()){
            throw new BadRequestException(MessagesValidation.VALIDATION_TOUR_UNIQUE_DAY);
        }


        CostTourDomain newCostTourDomain = new CostTourDomain();
        newCostTourDomain.setNumMaxPax(request.getNumMaxPax());
        newCostTourDomain.setTour(tourDomain);
        newCostTourDomain.setQuotation(quotationDomain);
        newCostTourDomain.setItineraryDate(itineraryDateDomain);
        newCostTourDomain.setTenant(UserResolver.resolveUser().getTenant());
        newCostTourDomain.setSubTotalAmount(0.00);
        CostTourDomain createCostTourDomain = costTourRepo.create(newCostTourDomain);

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        List<TourItemDomain> tourItemDomainList = tourItemRepo.searchAllByTour(tourDomain.getId());
        if (tourItemDomainList != null && !tourItemDomainList.isEmpty()){
            List<TourItemDomain> tourItemDomainListResult = tourItemDomainList.stream().collect(Collectors.toList());
            for(TourItemDomain item: tourItemDomainListResult){
                ItineraryDateDomain itineraryDateDomainDb = itineraryDateRepo.getByQuotationAndItemItinerary(quotationDomain.getId(), Integer.toString(Integer.parseInt(itineraryDateDomain.getItemItinerary()) + item.getTourDate().getDay() - 1));
                ItineraryItemDomain newDomain = new ItineraryItemDomain();
                newDomain.setQuotation(quotationDomain);
                newDomain.setItineraryDate(itineraryDateDomainDb);
                newDomain.setSupplierType(item.getSupplierType());
                newDomain.setSupplier(item.getSupplier());
                newDomain.setService(item.getService());
                newDomain.setObservation(item.getObservation());
                newDomain.setServiceGroup(item.getServiceGroup());
                newDomain.setPrice(0.0);
                newDomain.setPriceAdult(0.0);
                newDomain.setPriceChildren(0.0);
                newDomain.setPriceTc(0.0);
                newDomain.setPriceTcLocal(0.0);
                newDomain.setPriceGuide(0.0);

                if (item.getSupplier() != null && item.getSupplier().getPartner() != null && item.getSupplier().getPartner().getAddress() != null && item.getSupplier().getPartner().getAddress().getDepartmentRegion() != null  ){
                    newDomain.setDepartmentRegion(item.getSupplier().getPartner().getAddress().getDepartmentRegion());
                }else{
                    newDomain.setDepartmentRegion(null);
                }


                /*newDomain.setPaxTotal(quotationDomain.getPaxAdult() + quotationDomain.getPaxChild());
                newDomain.setPaxAdultTotal(quotationDomain.getPaxAdult());
                newDomain.setPaxChildrenTotal(quotationDomain.getPaxChild());
                newDomain.setPaxTcTotal(quotationDomain.getPaxTc());
                newDomain.setPaxTcLocalTotal(quotationDomain.getPaxTcLocal());
                newDomain.setPaxGuideTotal(quotationDomain.getGuide());*/

                newDomain.setPaxTotal(request.getNumMaxPax());
                newDomain.setPaxAdultTotal(request.getNumMaxPax());
                newDomain.setPaxChildrenTotal(0);
                newDomain.setPaxTcTotal(0);
                newDomain.setPaxTcLocalTotal(0);
                newDomain.setPaxGuideTotal(0);

                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));
                newDomain.setTour(tourDomain);
                newDomain.setCostTour(createCostTourDomain);

                if (supplierTypeDomainLodging.getIdentify().equals(item.getSupplierType().getIdentify())){
                    newDomain.setLodgingCantNight(1);
                }else if (supplierTypeDomainLodge.getIdentify().equals(item.getSupplierType().getIdentify())){
                    newDomain.setLodgeCantNight(1);
                    if (newDomain.getServiceGroup() != null){
                        if (newDomain.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())){
                            if (newDomain.getLodgePvt() == null && newDomain.getLodgeSic() == null){
                                newDomain.setLodgeSic(true);
                                newDomain.setLodgePvt(false);
                            }
                        }else{
                            newDomain.setLodgeSic(false);
                            newDomain.setLodgePvt(false);
                        }
                    }else{
                        newDomain.setServiceGroup(ServiceGroupEnum.TRADITIONAL_GROUP.getName());
                        newDomain.setLodgeSic(false);
                        newDomain.setLodgePvt(false);
                    }
                }else if (supplierTypeDomainGlamping.getIdentify().equals(item.getSupplierType().getIdentify())){
                    newDomain.setLodgingCantNight(1);
                }else if (supplierTypeDomainOperator.getIdentify().equals(item.getSupplierType().getIdentify())){
                    /*if (newDomain.getOperatorPvt() == null && newDomain.getOperatorSic() == null){
                        newDomain.setOperatorSic(true);
                    }*/
                }

                LocalDateTime localDateTimeTemp = LocalDateTime.now();

                LocalDateTime localDateTime = LocalDateTime.of(
                        itineraryDateDomainDb.getItineraryDate().getYear(),
                        itineraryDateDomainDb.getItineraryDate().getMonth(),
                        itineraryDateDomainDb.getItineraryDate().getDayOfMonth(),
                        localDateTimeTemp.getHour(),
                        localDateTimeTemp.getMinute(),
                        localDateTimeTemp.getSecond()
                );
                newDomain.setItineraryDateTime(localDateTime);

                ItineraryItemDomain createdDomain = repo.create(newDomain);

                if (itineraryDateDomainDb.getTours() == null){
                    itineraryDateDomainDb.setTours(new HashSet<>());
                }
                itineraryDateDomainDb.getTours().add(tourDomain);
                itineraryDateRepo.edit(itineraryDateDomainDb);

                /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
                    if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryItemCrmCreateEvent(this, createdDomain));
                    }
                }*/
            }
        }
    }

    @Override
    @Transactional
    public void createTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, QuotationDomain quotationDomain) {
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);

        CostTourDomain costTourDomain = null;
        if (request.getCostTour() != null && !request.getCostTour().isEmpty()){
            costTourDomain = costTourRepo.findById(request.getCostTour());
        }
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }
        TourDomain tourDomain = null;
        if (request.getTour() != null && !request.getTour().isEmpty()){
            IdBaseDomain tourIdBaseDomain = tourRepo.findByIdEntity(request.getTour());
            tourDomain = new TourDomain();
            tourDomain.setId(tourIdBaseDomain.getId());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findByIdEntityWithDepartmentRegion(request.getSupplier());
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        CostTourServiceItemDomain newCostTourServiceItemDomain = new CostTourServiceItemDomain();
        newCostTourServiceItemDomain.setTour(tourDomain);
        newCostTourServiceItemDomain.setQuotation(quotationDomain);
        newCostTourServiceItemDomain.setItineraryDate(itineraryDateDomain);
        newCostTourServiceItemDomain.setCostTour(costTourDomain);
        newCostTourServiceItemDomain.setService(serviceDomain);
        newCostTourServiceItemDomain.setSupplierType(supplierTypeDomain);
        newCostTourServiceItemDomain.setSupplier(supplierDomain);
        newCostTourServiceItemDomain.setObservation(request.getObservation());
        newCostTourServiceItemDomain.setTenant(UserResolver.resolveUser().getTenant());

        newCostTourServiceItemDomain = costTourServiceItemRepo.create(newCostTourServiceItemDomain);

        ItineraryItemDomain newDomain = new ItineraryItemDomain();
        newDomain.setQuotation(quotationDomain);
        newDomain.setItineraryDate(itineraryDateDomain);
        newDomain.setSupplierType(supplierTypeDomain);
        newDomain.setSupplier(supplierDomain);
        newDomain.setService(serviceDomain);
        newDomain.setObservation(request.getObservation());
        newDomain.setPrice(0.0);
        newDomain.setPriceAdult(0.0);
        newDomain.setPriceChildren(0.0);
        newDomain.setPriceTc(0.0);
        newDomain.setPriceTcLocal(0.0);
        newDomain.setPriceGuide(0.0);

        newDomain.setPaxTotal(costTourDomain.getNumMaxPax());
        newDomain.setPaxAdultTotal(costTourDomain.getNumMaxPax());
        newDomain.setPaxChildrenTotal(0);
        newDomain.setPaxTcTotal(0);
        newDomain.setPaxTcLocalTotal(0);
        newDomain.setPaxGuideTotal(0);

        if (supplierDomain != null && supplierDomain.getPartner() != null && supplierDomain.getPartner().getAddress() != null && supplierDomain.getPartner().getAddress().getDepartmentRegion() != null  ){
            newDomain.setDepartmentRegion(supplierDomain.getPartner().getAddress().getDepartmentRegion());
        }else{
            newDomain.setDepartmentRegion(null);
        }

        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(quotationDomain.getId()));
        newDomain.setTour(tourDomain);
        newDomain.setCostTour(costTourDomain);
        newDomain.setCostTourServiceItem(newCostTourServiceItemDomain);

        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
            newDomain.setLodgingCantNight(1);
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            newDomain.setLodgeCantNight(1);
            if (newDomain.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())){
                /*if (newDomain.getOperatorSic() == null && newDomain.getOperatorPvt() == null){
                    newDomain.setOperatorSic(true);
                }*/
            }
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
            newDomain.setLodgingCantNight(1);
        }else if (supplierTypeDomainOperator.getIdentify().equals(supplierTypeDomain.getIdentify())){
            /*if (newDomain.getOperatorSic() == null && newDomain.getOperatorPvt() == null){
                newDomain.setOperatorSic(true);
            }*/
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

        newDomain.setItineraryDateTime(localDateTime);
        repo.create(newDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse edit(String id, ItineraryItemUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findByIdQuotationCaseGeneric(request.getQuotation());
        }
        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomain = new ItineraryDateDomain();
                itineraryDateDomain.setId(itineraryDateIdBaseDomain.getId());
            }
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }

        SupplierDomain supplierDomain = null;
        DepartmentRegionDomain departmentRegionDomain = null;

        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findByIdEntityWithDepartmentRegion(request.getSupplier());
            if (supplierDomain != null && supplierDomain.getPartner() != null && supplierDomain.getPartner().getAddress() != null && supplierDomain.getPartner().getAddress().getDepartmentRegion() != null  ){
                departmentRegionDomain = supplierDomain.getPartner().getAddress().getDepartmentRegion();
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

        /*DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            IdBaseDomain departmentRegionIdBaseDomain = departmentRegionRepo.findByIdEntity(request.getDepartmentRegion());
            if (departmentRegionIdBaseDomain != null){
                departmentRegionDomain = new DepartmentRegionDomain();
                departmentRegionDomain.setId(departmentRegionIdBaseDomain.getId());
            }
        }*/

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getLodgingForOperator() != null && !request.getLodgingForOperator().isEmpty()){
            IdBaseDomain supplierLodgingIdBaseDomain = supplierLodgingRepo.findByIdEntity(request.getLodgingForOperator());
            if (supplierLodgingIdBaseDomain != null){
                supplierLodgingDomain = new SupplierLodgingDomain();
                supplierLodgingDomain.setId(supplierLodgingIdBaseDomain.getId());
            }
        }
        ItineraryItemDomain itineraryItemDomain = request.to(quotationDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
            itineraryItemDomain.setLodgingCantNight(1);
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            itineraryItemDomain.setLodgeCantNight(1);
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
            itineraryItemDomain.setLodgingCantNight(1);
        }
        itineraryItemDomain.setLodgingTcCantNight(old.getLodgingTcCantNight());
        itineraryItemDomain.setLodgingTcLocalCantNight(old.getLodgingTcLocalCantNight());
        itineraryItemDomain.setLodgingGuideCantNight(old.getLodgingGuideCantNight());
        itineraryItemDomain.setIndex(old.getIndex());
        itineraryItemDomain.setTour(old.getTour());
        itineraryItemDomain.setCostTour(old.getCostTour());
        itineraryItemDomain.setCostTourServiceItem(old.getCostTourServiceItem());
        ItineraryItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(itineraryItemDomain, old));
        /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, editedDomain));
            }
        }*/
        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse edit(String id, ItineraryItemUpdateRequest request, QuotationDomain quotationDomain) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }

        SupplierDomain supplierDomain = null;
        DepartmentRegionDomain departmentRegionDomain = null;

        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
            if (supplierDomain != null && supplierDomain.getPartner() != null && supplierDomain.getPartner().getAddress() != null && supplierDomain.getPartner().getAddress().getDepartmentRegion() != null  ){
                departmentRegionDomain = supplierDomain.getPartner().getAddress().getDepartmentRegion();
            }
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }
        /*DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getDepartmentRegion() != null && !request.getDepartmentRegion().isEmpty()){
            departmentRegionDomain = departmentRegionRepo.findById(request.getDepartmentRegion());
        }*/
        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getLodgingForOperator() != null && !request.getLodgingForOperator().isEmpty()){
            supplierLodgingDomain = supplierLodgingRepo.findById(request.getLodgingForOperator());
        }
        LodgingCategoryDomain lodgingCategory = null;
        if (request.getLodgingCategory() != null && !request.getLodgingCategory().isEmpty()){
            lodgingCategory = lodgingCategoryRepo.findById(request.getLodgingCategory());
        }
        LodgingCategoryDomain lodgeCategory = null;
        if (request.getLodgeCategory() != null && !request.getLodgeCategory().isEmpty()){
            lodgeCategory = lodgingCategoryRepo.findById(request.getLodgeCategory());
        }
        ItineraryItemDomain itineraryItemDomain = request.to(quotationDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);

        if (lodgingCategory != null){
            itineraryItemDomain.setLodgingCategory(lodgingCategory);
        }
        if (lodgeCategory != null){
            itineraryItemDomain.setLodgeCategory(lodgeCategory);
        }

        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
            itineraryItemDomain.setLodgingCantNight(1);
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            itineraryItemDomain.setLodgeCantNight(1);
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
            itineraryItemDomain.setLodgingCantNight(1);
        }
        itineraryItemDomain.setLodgingTcCantNight(old.getLodgingTcCantNight());
        itineraryItemDomain.setLodgingTcLocalCantNight(old.getLodgingTcLocalCantNight());
        itineraryItemDomain.setLodgingGuideCantNight(old.getLodgingGuideCantNight());
        itineraryItemDomain.setIndex(old.getIndex());
        itineraryItemDomain.setTour(old.getTour());
        itineraryItemDomain.setCostTour(old.getCostTour());
        itineraryItemDomain.setCostTourServiceItem(old.getCostTourServiceItem());
        LocalDateTime localDateTime = LocalDateTime.of(
                itineraryDateDomain.getItineraryDate().getYear(),
                itineraryDateDomain.getItineraryDate().getMonth(),
                itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                request.getItineraryDateTime().getHour(),
                request.getItineraryDateTime().getMinute(),
                request.getItineraryDateTime().getSecond()
        );
        itineraryItemDomain.setItineraryDateTime(localDateTime);

        // To Calculate Total Price
        Double amountAdult = 0.0;
        Double amountChildren = 0.0;
        Double amountTc = 0.0;
        Double amountTcLocal = 0.0;
        Double amountGuide = 0.0;
        if (itineraryItemDomain.getPriceAdult() != null && itineraryItemDomain.getPaxAdultTotal()!= null){
            amountAdult = itineraryItemDomain.getPriceAdult() * itineraryItemDomain.getPaxAdultTotal();
        }
        if (itineraryItemDomain.getPriceChildren() != null && itineraryItemDomain.getPaxChildrenTotal()!= null){
            amountChildren = itineraryItemDomain.getPriceChildren() * itineraryItemDomain.getPaxChildrenTotal();
        }
        if (itineraryItemDomain.getPriceTc() != null && itineraryItemDomain.getPaxTcTotal()!= null){
            amountTc = itineraryItemDomain.getPriceTc() * itineraryItemDomain.getPaxTcTotal();
        }
        if (itineraryItemDomain.getPriceTcLocal() != null && itineraryItemDomain.getPaxTcLocalTotal()!= null){
            amountTcLocal = itineraryItemDomain.getPriceTcLocal() * itineraryItemDomain.getPaxTcLocalTotal();
        }
        if (itineraryItemDomain.getPriceGuide() != null && itineraryItemDomain.getPaxGuideTotal()!= null){
            amountGuide = itineraryItemDomain.getPriceGuide() * itineraryItemDomain.getPaxGuideTotal();
        }

        if (itineraryItemDomain.getService().getWithoutCost() != null && !itineraryItemDomain.getService().getWithoutCost()){
            itineraryItemDomain.setPrice(
                                amountAdult
                            + amountChildren
                            + amountTc
                            + amountTcLocal
                            + amountGuide
            );
        }else if (itineraryItemDomain.getService().getWithoutCost() == null){
            itineraryItemDomain.setPrice(
                    amountAdult
                            + amountChildren
                            + amountTc
                            + amountTcLocal
                            + amountGuide
            );
        }else{
            itineraryItemDomain.setPrice(0.0);
        }

        itineraryItemDomain.setPaxTotal(itineraryItemDomain.getPaxAdultTotal() + itineraryItemDomain.getPaxChildrenTotal());

        ItineraryItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(itineraryItemDomain, old));
        /*if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, editedDomain));
            }
        }*/

        if (old.getCostTourServiceItem() != null){
            old.getCostTourServiceItem().setItineraryDate(itineraryDateDomain);
            old.getCostTourServiceItem().setSupplierType(supplierTypeDomain);
            old.getCostTourServiceItem().setSupplier(supplierDomain);
            old.getCostTourServiceItem().setService(serviceDomain);
            old.getCostTourServiceItem().setObservation(editedDomain.getObservation());
            costTourServiceItemRepo.edit(old.getCostTourServiceItem());
        }
        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ItineraryItemResponse old = findById(id);
        repo.deleteById(old.getId());
        /*if (old.getQuotation().getIdentifyFile() != null && !old.getQuotation().getIdentifyFile().isEmpty()){
            CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
            if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                this.eventPublisher.publishEvent(new ItineraryItemCrmDeleteEvent(this, repo.findById(id)));
            }
        }*/
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemResponse> findAll() {
        return repo.findAll(false).stream().map(itineraryItemDomain -> ItineraryItemResponse.from(itineraryItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }
        return ItineraryItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemSearchResponse searchAll(ItineraryItemSearchRequest itineraryItemSearchRequest) {
       PageDTO<ItineraryItemDomain> pageDTO = repo.searchAll(itineraryItemSearchRequest);
       ItineraryItemSearchResponse itineraryItemSearchRespose = new ItineraryItemSearchResponse();
       itineraryItemSearchRespose.setItineraryItems(pageDTO.getContent().stream().map(itineraryItemDomain ->  ItineraryItemResponse.from(itineraryItemDomain)).toList());
       itineraryItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
       itineraryItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return itineraryItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemSearchResponse searchFilterAll(ItineraryItemSearchFilterRequest itineraryItemSearchFilterRequest) {
        PageDTO<ItineraryItemDomain> pageDTO = repo.searchAllFilter(itineraryItemSearchFilterRequest);
        ItineraryItemSearchResponse itineraryItemSearchRespose = new ItineraryItemSearchResponse();
        itineraryItemSearchRespose.setItineraryItems(pageDTO.getContent().stream().map(itineraryItemDomain ->  ItineraryItemResponse.from(itineraryItemDomain)).toList());
        itineraryItemSearchRespose.setTotalElements(pageDTO.getTotalElements());
        itineraryItemSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return itineraryItemSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemResponse> searchAllByQuotation(String idQuotation) {
        List<ItineraryItemDomain> list = repo.searchAllByQuotation(idQuotation);
        return list.stream().map(itineraryItemDomain ->  ItineraryItemResponse.from(itineraryItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemResponse searchByQuotationAndItineraryItem(String idQuotation, String idItineraryItem) {
        ItineraryItemDomain itineraryItemDomain = repo.searchAllByQuotationAndItineraryItem(idQuotation, idItineraryItem);
        return ItineraryItemResponse.from(itineraryItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemDomain> searchAllDomainByQuotation(String idQuotation) {
        List<ItineraryItemDomain> list = repo.searchAllByQuotation(idQuotation);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemDomain> searchAllDomainByQuotationAndItineraryDate(String idItineraryDate, String idQuotation) {
        List<ItineraryItemDomain> list = repo.searchAllByItineraryDateAndQuotation(idItineraryDate, idQuotation);
        return list;
    }

    private Integer getLastIndexItineraryItem(String idQuotation) {
        Integer index = 0;
        List<ItineraryItemDomain> list = repo.searchAllByQuotationSimpleDomainCaseGetCollectionForLastIndex(idQuotation).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryItemDomain::getIndex));
        long count = list.size();
        if (count != 0){
            index = list.getLast().getIndex() + 1;
        }else{
            index = 1;
        }
        return index;
    }
}
