package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.itineraryitem.ItineraryItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultItineraryItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtour.CostTourBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtourserviceitem.CostTourServiceItemBookRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.RegisterBookingGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinginstitution.RegisterBookingInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.RegisterBookingLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant.RegisterBookingRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tourdate.TourDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.TourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.lodgingcategory.LodgingCategoryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.EntityExistBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.enums.ModeUpdateServiceAdvancedEnum;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.RegisterBookingOperatorResponse;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.GeneratedRegisterBookingOperatorItemEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateServiceInvoiceEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.usecase.booking.ItineraryItemUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingRestaurantUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
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

@Slf4j
@Service
public class ItineraryItemUseCaseImpl implements ItineraryItemUseCase {

    @Autowired
    private ItineraryItemRepo repo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

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
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ItineraryItemConverter converter;

    @Autowired
    private SetFieldDefaultItineraryItem setFieldDefault;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private TourDateRepo tourDateRepo;

    @Autowired
    private TourItemRepo tourItemRepo;

    @Autowired
    private CostTourBookingRepo costTourRepo;

    @Autowired
    private CostTourServiceItemBookRepo costTourServiceItemRepo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

    @Autowired
    private LodgingCategoryRepo lodgingCategoryRepo;

    @Autowired
    private RegisterBookingOperatorRepo registerBookingOperatorRepo;

    @Autowired
    private RegisterBookingRestaurantUseCase registerBookingRestaurantUseCase;

    @Autowired
    private RegisterBookingRestaurantRepo registerBookingRestaurantRepo;

    @Autowired
    private RegisterBookingLodgingRepo registerBookingLodgingRepo;

    @Autowired
    private RegisterBookingGlampingRepo registerBookingGlampingRepo;

    @Autowired
    private RegisterBookingInstitutionRepo registerBookingInstitutionRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;


    @Override
    @Transactional
    public ItineraryItemResponse create(ItineraryItemCreateRequest request) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
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
        ItineraryItemDomain newDomain = request.to(registerGroupDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            newDomain.setStateItineraryService(StateBookingEnum.DRAFT.getName());
            newDomain.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());
        }
        newDomain.setCustomer(registerGroupDomain.getCustomer());
        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        }
        ItineraryItemDomain createdDomain = repo.create(newDomain);

        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new ItineraryItemCreateEvent(this, createdDomain));
        }*/
        return ItineraryItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse create(ItineraryItemCreateRequest request, RegisterGroupDomain registerGroupDomain) {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
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
        ItineraryItemDomain newDomain = request.to(registerGroupDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            newDomain.setStateItineraryService(StateBookingEnum.DRAFT.getName());
            newDomain.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());
        }
        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));
        newDomain.setCustomer(registerGroupDomain.getCustomer());
        ItineraryItemDomain createdDomain = repo.create(newDomain);
        return ItineraryItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public void createMultiple(ItineraryItemCreateRequest request, RegisterGroupDomain registerGroupDomain) {
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
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        if (request.getItineraryDates() != null && !request.getItineraryDates().isEmpty()){
            for(String item: request.getItineraryDates()){
                ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.findById(item);
                ItineraryItemDomain newDomain = request.to(registerGroupDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));
                if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                    newDomain.setStateItineraryService(StateBookingEnum.DRAFT.getName());
                    newDomain.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());
                }
                newDomain.setCustomer(registerGroupDomain.getCustomer());
                ItineraryItemDomain createdDomain = repo.create(newDomain);
            }
        }
    }

    @Override
    @Transactional
    public void createAdvanced(ItineraryServiceAdvancedCreateRequest request, RegisterGroupDomain registerGroupDomain) {

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        if (request.getSuppliers() != null && !request.getSuppliers().isEmpty()){
            for (ContainerSupplierAdvancedCreateRequest containerSupplierAdvancedCreateRequest: request.getSuppliers()){
                SupplierTypeDomain supplierTypeDomain = null;
                if (containerSupplierAdvancedCreateRequest.getSupplierType() != null && containerSupplierAdvancedCreateRequest.getSupplierType().getId() != null && !containerSupplierAdvancedCreateRequest.getSupplierType().getId().isEmpty()){
                    supplierTypeDomain = supplierTypeRepo.findById(containerSupplierAdvancedCreateRequest.getSupplierType().getId());
                }

                SupplierDomain supplierDomain = null;
                if (containerSupplierAdvancedCreateRequest.getSupplier() != null && containerSupplierAdvancedCreateRequest.getSupplier().getId() != null && !containerSupplierAdvancedCreateRequest.getSupplier().getId().isEmpty()){
                    supplierDomain = supplierRepo.findByIdEntityWithDepartmentRegion(containerSupplierAdvancedCreateRequest.getSupplier().getId());
                }

                DepartmentRegionDomain departmentRegionDomain = null;
                if (supplierDomain != null && supplierDomain.getPartner() != null && supplierDomain.getPartner().getAddress() != null && supplierDomain.getPartner().getAddress().getDepartmentRegion() != null  ){
                    departmentRegionDomain = supplierDomain.getPartner().getAddress().getDepartmentRegion();
                }

                if (containerSupplierAdvancedCreateRequest.getServices() != null && !containerSupplierAdvancedCreateRequest.getServices().isEmpty()){
                    for (ContainerServiceAdvancedCreateRequest containerServiceAdvancedCreateRequest: containerSupplierAdvancedCreateRequest.getServices()){
                        ServiceDomain serviceDomain = null;
                        if (containerServiceAdvancedCreateRequest.getService() != null && containerServiceAdvancedCreateRequest.getService().getId() != null && !containerServiceAdvancedCreateRequest.getService().getId().isEmpty()){
                            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getService().getId());
                            if (serviceIdBaseDomain != null){
                                serviceDomain = new ServiceDomain();
                                serviceDomain.setId(serviceIdBaseDomain.getId());
                            }
                        }

                        if (containerServiceAdvancedCreateRequest.getItineraryDate() != null){
                            ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.findById(containerServiceAdvancedCreateRequest.getItineraryDate().getId());
                            ItineraryItemDomain newDomain = new ItineraryItemDomain();
                            newDomain.setRegisterGroup(registerGroupDomain);
                            newDomain.setItineraryDate(itineraryDateDomain);
                            newDomain.setSupplierType(supplierTypeDomain);
                            newDomain.setSupplier(supplierDomain);
                            newDomain.setService(serviceDomain);
                            newDomain.setDepartmentRegion(departmentRegionDomain);
                            newDomain.setTenant(UserResolver.resolveUser().getTenant());
                            newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));

                            newDomain.setPaxAdultTotal(containerServiceAdvancedCreateRequest.getPaxAdultTotal());
                            newDomain.setPaxChildrenTotal(containerServiceAdvancedCreateRequest.getPaxChildrenTotal());
                            newDomain.setPaxTcTotal(containerServiceAdvancedCreateRequest.getPaxTcTotal());
                            newDomain.setPaxTcLocalTotal(containerServiceAdvancedCreateRequest.getPaxTcLocalTotal());
                            newDomain.setPaxGuideTotal(containerServiceAdvancedCreateRequest.getPaxGuideTotal());

                            newDomain.setObservation(containerServiceAdvancedCreateRequest.getObservation());

                            if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                            }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                            }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                            newDomain.setPaxTotal((newDomain.getPaxAdultTotal() != null ? newDomain.getPaxAdultTotal() : 0)  + (newDomain.getPaxChildrenTotal() != null ? newDomain.getPaxChildrenTotal()  : 0));
                            newDomain.setCustomer(registerGroupDomain.getCustomer());

                            newDomain.setServiceGroup(containerServiceAdvancedCreateRequest.getServiceGroup());

                            if (
                                    supplierTypeDomainOperator.getIdentify().equals(supplierTypeDomain.getIdentify()) ||
                                    supplierTypeDomainTransport.getIdentify().equals(supplierTypeDomain.getIdentify())
                            ){
                                newDomain.setServiceForOperation(true);
                            }else if (
                                    supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify()) &&
                                    containerServiceAdvancedCreateRequest.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())
                            ){
                                newDomain.setServiceForOperation(true);
                            } else {
                                newDomain.setServiceForOperation(false);
                            }

                            newDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName());

                            newDomain.setPrice(containerServiceAdvancedCreateRequest.getPrice());
                            newDomain.setPriceAdult(containerServiceAdvancedCreateRequest.getPriceAdult());
                            newDomain.setPriceChildren(containerServiceAdvancedCreateRequest.getPriceChildren());
                            newDomain.setPriceTc(containerServiceAdvancedCreateRequest.getPriceTc());
                            newDomain.setPriceTcLocal(containerServiceAdvancedCreateRequest.getPriceTcLocal());
                            newDomain.setPriceGuide(containerServiceAdvancedCreateRequest.getPriceGuide());

                            // Supplier Type Lodging

                            newDomain.setLodgingCantSwb(containerServiceAdvancedCreateRequest.getLodgingCantSwb());
                            newDomain.setLodgingCantMat(containerServiceAdvancedCreateRequest.getLodgingCantMat());
                            newDomain.setLodgingCantTwin(containerServiceAdvancedCreateRequest.getLodgingCantTwin());
                            newDomain.setLodgingCantTrp(containerServiceAdvancedCreateRequest.getLodgingCantTrp());
                            newDomain.setLodgingCategoryStr(containerServiceAdvancedCreateRequest.getLodgingCategoryStr());
                            newDomain.setLodgingCantBreakfast(containerServiceAdvancedCreateRequest.getLodgingCantBreakfast());
                            newDomain.setLodgingCantLunch(containerServiceAdvancedCreateRequest.getLodgingCantLunch());
                            newDomain.setLodgingCantDinner(containerServiceAdvancedCreateRequest.getLodgingCantDinner());
                            newDomain.setLodgingCantBoxLunch(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunch());
                            newDomain.setLodgingCantBreakfastDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastDateTime());
                            newDomain.setLodgingCantLunchDateTime(containerServiceAdvancedCreateRequest.getLodgingCantLunchDateTime());
                            newDomain.setLodgingCantDinnerDateTime(containerServiceAdvancedCreateRequest.getLodgingCantDinnerDateTime());
                            newDomain.setLodgingCantBoxLunchDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchDateTime());
                            newDomain.setLodgingCantBreakfastDate(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastDate());
                            newDomain.setLodgingCantLunchDate(containerServiceAdvancedCreateRequest.getLodgingCantLunchDate());
                            newDomain.setLodgingCantDinnerDate(containerServiceAdvancedCreateRequest.getLodgingCantDinnerDate());
                            newDomain.setLodgingCantBoxLunchDate(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchDate());

                            if (containerServiceAdvancedCreateRequest.getLodgingCantBreakfastItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBreakfastItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBreakfastItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBreakfastItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantLunchItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantLunchItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantLunchItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantLunchItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantLunchItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantDinnerItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantDinnerItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantDinnerItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantDinnerItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantDinnerItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBoxLunchItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgingCantBreakfastTcGuide(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcGuide());
                            newDomain.setLodgingCantLunchTcGuide(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcGuide());
                            newDomain.setLodgingCantDinnerTcGuide(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcGuide());
                            newDomain.setLodgingCantBoxLunchTcGuide(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcGuide());
                            newDomain.setLodgingCantBreakfastTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcGuideDateTime());
                            newDomain.setLodgingCantLunchTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcGuideDateTime());
                            newDomain.setLodgingCantDinnerTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcGuideDateTime());
                            newDomain.setLodgingCantBoxLunchTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcGuideDateTime());
                            newDomain.setLodgingCantBreakfastTcGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcGuideDate());
                            newDomain.setLodgingCantLunchTcGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcGuideDate());
                            newDomain.setLodgingCantDinnerTcGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcGuideDate());
                            newDomain.setLodgingCantBoxLunchTcGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcGuideDate());

                            if (containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantLunchTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantLunchTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantLunchTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgingCantSwbTcGuide(containerServiceAdvancedCreateRequest.getLodgingCantSwbTcGuide());
                            newDomain.setLodgingCantTwinTcGuide(containerServiceAdvancedCreateRequest.getLodgingCantTwinTcGuide());


                            newDomain.setLodgingCantBreakfastTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcLocalGuide());
                            newDomain.setLodgingCantLunchTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcLocalGuide());
                            newDomain.setLodgingCantDinnerTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcLocalGuide());
                            newDomain.setLodgingCantBoxLunchTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcLocalGuide());

                            newDomain.setLodgingCantBreakfastTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcLocalGuideDateTime());
                            newDomain.setLodgingCantLunchTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcLocalGuideDateTime());
                            newDomain.setLodgingCantDinnerTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcLocalGuideDateTime());
                            newDomain.setLodgingCantBoxLunchTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcLocalGuideDateTime());
                            newDomain.setLodgingCantBreakfastTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcLocalGuideDate());
                            newDomain.setLodgingCantLunchTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcLocalGuideDate());
                            newDomain.setLodgingCantDinnerTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcLocalGuideDate());
                            newDomain.setLodgingCantBoxLunchTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcLocalGuideDate());

                            if (containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantLunchTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantLunchTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgingCantSwbTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgingCantSwbTcLocalGuide());
                            newDomain.setLodgingCantTwinTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgingCantTwinTcLocalGuide());

                            newDomain.setLodgingCantBreakfastGuide(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastGuide());
                            newDomain.setLodgingCantLunchGuide(containerServiceAdvancedCreateRequest.getLodgingCantLunchGuide());
                            newDomain.setLodgingCantDinnerGuide(containerServiceAdvancedCreateRequest.getLodgingCantDinnerGuide());
                            newDomain.setLodgingCantBoxLunchGuide(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchGuide());
                            newDomain.setLodgingCantBreakfastGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastGuideDateTime());
                            newDomain.setLodgingCantLunchGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantLunchGuideDateTime());
                            newDomain.setLodgingCantDinnerGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantDinnerGuideDateTime());
                            newDomain.setLodgingCantBoxLunchGuideDateTime(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchGuideDateTime());
                            newDomain.setLodgingCantBreakfastGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastGuideDate());
                            newDomain.setLodgingCantLunchGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantLunchGuideDate());
                            newDomain.setLodgingCantDinnerGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantDinnerGuideDate());
                            newDomain.setLodgingCantBoxLunchGuideDate(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchGuideDate());

                            if (containerServiceAdvancedCreateRequest.getLodgingCantBreakfastGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBreakfastGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBreakfastGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBreakfastGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantLunchGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantLunchGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantLunchGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantLunchGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantLunchGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantDinnerGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantDinnerGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantDinnerGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantDinnerGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantDinnerGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgingCantBoxLunchGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgingCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgingCantSwbGuide(containerServiceAdvancedCreateRequest.getLodgingCantSwbGuide());
                            newDomain.setLodgingCantTwinGuide(containerServiceAdvancedCreateRequest.getLodgingCantTwinGuide());

                            // Supplier Type Lodge

                            newDomain.setLodgeCantSwb(containerServiceAdvancedCreateRequest.getLodgeCantSwb());
                            newDomain.setLodgeCantMat(containerServiceAdvancedCreateRequest.getLodgeCantMat());
                            newDomain.setLodgeCantTwin(containerServiceAdvancedCreateRequest.getLodgeCantTwin());
                            newDomain.setLodgeCantTrp(containerServiceAdvancedCreateRequest.getLodgeCantTrp());

                            newDomain.setLodgeCategoryStr(containerServiceAdvancedCreateRequest.getLodgeCategoryStr());
                            newDomain.setLodgeCantBreakfast(containerServiceAdvancedCreateRequest.getLodgeCantBreakfast());
                            newDomain.setLodgeCantLunch(containerServiceAdvancedCreateRequest.getLodgeCantLunch());
                            newDomain.setLodgeCantDinner(containerServiceAdvancedCreateRequest.getLodgeCantDinner());
                            newDomain.setLodgeCantBoxLunch(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunch());
                            newDomain.setLodgeCantBreakfastDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastDateTime());
                            newDomain.setLodgeCantLunchDateTime(containerServiceAdvancedCreateRequest.getLodgeCantLunchDateTime());
                            newDomain.setLodgeCantDinnerDateTime(containerServiceAdvancedCreateRequest.getLodgeCantDinnerDateTime());
                            newDomain.setLodgeCantBoxLunchDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchDateTime());
                            newDomain.setLodgeCantBreakfastDate(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastDate());
                            newDomain.setLodgeCantLunchDate(containerServiceAdvancedCreateRequest.getLodgeCantLunchDate());
                            newDomain.setLodgeCantDinnerDate(containerServiceAdvancedCreateRequest.getLodgeCantDinnerDate());
                            newDomain.setLodgeCantBoxLunchDate(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchDate());

                            if (containerServiceAdvancedCreateRequest.getLodgeCantBreakfastItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBreakfastItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBreakfastItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBreakfastItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantLunchItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantLunchItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantLunchItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantLunchItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantLunchItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantDinnerItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantDinnerItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantDinnerItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantDinnerItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantDinnerItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBoxLunchItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgeCantBreakfastTcGuide(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcGuide());
                            newDomain.setLodgeCantLunchTcGuide(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcGuide());
                            newDomain.setLodgeCantDinnerTcGuide(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcGuide());

                            newDomain.setLodgeCantBoxLunchTcGuide(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcGuide());
                            newDomain.setLodgeCantBreakfastTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcGuideDateTime());
                            newDomain.setLodgeCantLunchTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcGuideDateTime());
                            newDomain.setLodgeCantDinnerTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcGuideDateTime());
                            newDomain.setLodgeCantBoxLunchTcGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcGuideDateTime());
                            newDomain.setLodgeCantBreakfastTcGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcGuideDate());
                            newDomain.setLodgeCantLunchTcGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcGuideDate());
                            newDomain.setLodgeCantDinnerTcGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcGuideDate());
                            newDomain.setLodgeCantBoxLunchTcGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcGuideDate());

                            if (containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantLunchTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantLunchTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantLunchTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgeCantSwbTcGuide(containerServiceAdvancedCreateRequest.getLodgeCantSwbTcGuide());
                            newDomain.setLodgeCantTwinTcGuide(containerServiceAdvancedCreateRequest.getLodgeCantTwinTcGuide());

                            newDomain.setLodgeCantBreakfastTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcLocalGuide());
                            newDomain.setLodgeCantLunchTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcLocalGuide());
                            newDomain.setLodgeCantDinnerTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcLocalGuide());
                            newDomain.setLodgeCantBoxLunchTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcLocalGuide());
                            newDomain.setLodgeCantBreakfastTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcLocalGuideDateTime());
                            newDomain.setLodgeCantLunchTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcLocalGuideDateTime());
                            newDomain.setLodgeCantDinnerTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcLocalGuideDateTime());
                            newDomain.setLodgeCantBoxLunchTcLocalGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcLocalGuideDateTime());
                            newDomain.setLodgeCantBreakfastTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcLocalGuideDate());
                            newDomain.setLodgeCantLunchTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcLocalGuideDate());
                            newDomain.setLodgeCantDinnerTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcLocalGuideDate());
                            newDomain.setLodgeCantBoxLunchTcLocalGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcLocalGuideDate());

                            if (containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantLunchTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantLunchTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgeCantSwbTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgeCantSwbTcLocalGuide());
                            newDomain.setLodgeCantTwinTcLocalGuide(containerServiceAdvancedCreateRequest.getLodgeCantTwinTcLocalGuide());

                            newDomain.setLodgeCantBreakfastGuide(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastGuide());
                            newDomain.setLodgeCantLunchGuide(containerServiceAdvancedCreateRequest.getLodgeCantLunchGuide());
                            newDomain.setLodgeCantDinnerGuide(containerServiceAdvancedCreateRequest.getLodgeCantDinnerGuide());
                            newDomain.setLodgeCantBoxLunchGuide(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchGuide());
                            newDomain.setLodgeCantBreakfastGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastGuideDateTime());
                            newDomain.setLodgeCantLunchGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantLunchGuideDateTime());
                            newDomain.setLodgeCantDinnerGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantDinnerGuideDateTime());
                            newDomain.setLodgeCantBoxLunchGuideDateTime(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchGuideDateTime());
                            newDomain.setLodgeCantBreakfastGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastGuideDate());
                            newDomain.setLodgeCantLunchGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantLunchGuideDate());
                            newDomain.setLodgeCantDinnerGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantDinnerGuideDate());
                            newDomain.setLodgeCantBoxLunchGuideDate(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchGuideDate());

                            if (containerServiceAdvancedCreateRequest.getLodgeCantBreakfastGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBreakfastGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBreakfastGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBreakfastGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantLunchGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantLunchGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantLunchGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantLunchGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantLunchGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantDinnerGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantDinnerGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantDinnerGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantDinnerGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantDinnerGuideItineraryDate(itineraryDateDomainDate);
                            }
                            if (containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                                    containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                                    !containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchGuideItineraryDate().isEmpty()){
                                ItineraryDateDomain itineraryDateDomainDate = null;
                                IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedCreateRequest.getLodgeCantBoxLunchGuideItineraryDate());
                                if (itineraryDateIdBaseDomain != null){
                                    itineraryDateDomainDate = new ItineraryDateDomain();
                                    itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                }
                                newDomain.setLodgeCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
                            }

                            newDomain.setLodgeCantSwbGuide(containerServiceAdvancedCreateRequest.getLodgeCantSwbGuide());
                            newDomain.setLodgeCantTwinGuide(containerServiceAdvancedCreateRequest.getLodgeCantTwinGuide());

                            newDomain.setLodgePvt(containerServiceAdvancedCreateRequest.getLodgePvt());
                            newDomain.setLodgeSic(containerServiceAdvancedCreateRequest.getLodgeSic());

                            // Supplier Type Restaurant

                            newDomain.setRestaurantCantBreakfast(containerServiceAdvancedCreateRequest.getRestaurantCantBreakfast());
                            newDomain.setRestaurantCantLunch(containerServiceAdvancedCreateRequest.getRestaurantCantLunch());
                            newDomain.setRestaurantCantDinner(containerServiceAdvancedCreateRequest.getRestaurantCantDinner());
                            newDomain.setRestaurantCantBoxLunch(containerServiceAdvancedCreateRequest.getRestaurantCantBoxLunch());
                            newDomain.setRestaurantCantBreakfastTcGuide(containerServiceAdvancedCreateRequest.getRestaurantCantBreakfastTcGuide());
                            newDomain.setRestaurantCantLunchTcGuide(containerServiceAdvancedCreateRequest.getRestaurantCantLunchTcGuide());
                            newDomain.setRestaurantCantDinnerTcGuide(containerServiceAdvancedCreateRequest.getRestaurantCantDinnerTcGuide());
                            newDomain.setRestaurantCantBoxLunchTcGuide(containerServiceAdvancedCreateRequest.getRestaurantCantBoxLunchTcGuide());
                            newDomain.setRestaurantCantBreakfastTcLocalGuide(containerServiceAdvancedCreateRequest.getRestaurantCantBreakfastTcLocalGuide());
                            newDomain.setRestaurantCantLunchTcLocalGuide(containerServiceAdvancedCreateRequest.getRestaurantCantLunchTcLocalGuide());
                            newDomain.setRestaurantCantDinnerTcLocalGuide(containerServiceAdvancedCreateRequest.getRestaurantCantDinnerTcLocalGuide());
                            newDomain.setRestaurantCantBoxLunchTcLocalGuide(containerServiceAdvancedCreateRequest.getRestaurantCantBoxLunchTcLocalGuide());

                            newDomain.setRestaurantCantBreakfastGuide(containerServiceAdvancedCreateRequest.getRestaurantCantBreakfastGuide());
                            newDomain.setRestaurantCantLunchGuide(containerServiceAdvancedCreateRequest.getRestaurantCantLunchGuide());
                            newDomain.setRestaurantCantDinnerGuide(containerServiceAdvancedCreateRequest.getRestaurantCantDinnerGuide());
                            newDomain.setRestaurantCantBoxLunchGuide(containerServiceAdvancedCreateRequest.getRestaurantCantBoxLunchGuide());

                            newDomain.setRestaurantCantBreakfastDrive(containerServiceAdvancedCreateRequest.getRestaurantCantBreakfastDrive());
                            newDomain.setRestaurantCantLunchDrive(containerServiceAdvancedCreateRequest.getRestaurantCantLunchDrive());
                            newDomain.setRestaurantCantDinnerDrive(containerServiceAdvancedCreateRequest.getRestaurantCantDinnerDrive());

                            newDomain.setRestaurantCantBoxLunchDrive(containerServiceAdvancedCreateRequest.getRestaurantCantBoxLunchDrive());

                            // Supplier Type Operator

                            newDomain.setOperatorPvt(containerServiceAdvancedCreateRequest.getOperatorPvt());
                            newDomain.setOperatorSic(containerServiceAdvancedCreateRequest.getOperatorSic());

                            repo.create(newDomain);
                        }

                    }
                }
            }
        }

    }

    @Override
    @Transactional
    public void editAdvanced(ItineraryServiceAdvancedUpdateRequest request, RegisterGroupDomain registerGroupDomain) {

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        if (request.getSuppliers() != null && !request.getSuppliers().isEmpty()){
            for (ContainerSupplierAdvancedUpdateRequest containerSupplierAdvancedUpdateRequest: request.getSuppliers()){
                if ( containerSupplierAdvancedUpdateRequest.getModeUpdateServiceAdvancedFlag().equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName()) ||
                        containerSupplierAdvancedUpdateRequest.getModeUpdateServiceAdvancedFlag().equals(ModeUpdateServiceAdvancedEnum.NEW_SUPPLIER.getName()) ) {
                    SupplierTypeDomain supplierTypeDomain = null;
                    if (containerSupplierAdvancedUpdateRequest.getSupplierType() != null && containerSupplierAdvancedUpdateRequest.getSupplierType().getId() != null && !containerSupplierAdvancedUpdateRequest.getSupplierType().getId().isEmpty()){
                        supplierTypeDomain = supplierTypeRepo.findById(containerSupplierAdvancedUpdateRequest.getSupplierType().getId());
                    }

                    SupplierDomain supplierDomain = null;
                    if (containerSupplierAdvancedUpdateRequest.getSupplier() != null && containerSupplierAdvancedUpdateRequest.getSupplier().getId() != null && !containerSupplierAdvancedUpdateRequest.getSupplier().getId().isEmpty()){
                        supplierDomain = supplierRepo.findByIdEntityWithDepartmentRegion(containerSupplierAdvancedUpdateRequest.getSupplier().getId());
                    }

                    DepartmentRegionDomain departmentRegionDomain = null;
                    if (supplierDomain != null && supplierDomain.getPartner() != null && supplierDomain.getPartner().getAddress() != null && supplierDomain.getPartner().getAddress().getDepartmentRegion() != null  ){
                        departmentRegionDomain = supplierDomain.getPartner().getAddress().getDepartmentRegion();
                    }

                    if (containerSupplierAdvancedUpdateRequest.getServices() != null && !containerSupplierAdvancedUpdateRequest.getServices().isEmpty()){
                        for (ContainerServiceAdvancedUpdateRequest containerServiceAdvancedUpdateRequest: containerSupplierAdvancedUpdateRequest.getServices()){
                            ServiceDomain serviceDomain = null;
                            if (containerServiceAdvancedUpdateRequest.getService() != null && containerServiceAdvancedUpdateRequest.getService().getId() != null && !containerServiceAdvancedUpdateRequest.getService().getId().isEmpty()){
                                IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getService().getId());
                                if (serviceIdBaseDomain != null){
                                    serviceDomain = new ServiceDomain();
                                    serviceDomain.setId(serviceIdBaseDomain.getId());
                                }
                            }

                            if (containerServiceAdvancedUpdateRequest.getItineraryDate() != null){
                                ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.findById(containerServiceAdvancedUpdateRequest.getItineraryDate().getId());
                                ItineraryItemDomain newDomain = new ItineraryItemDomain();
                                newDomain.setRegisterGroup(registerGroupDomain);
                                newDomain.setItineraryDate(itineraryDateDomain);
                                newDomain.setSupplierType(supplierTypeDomain);
                                newDomain.setSupplier(supplierDomain);
                                newDomain.setService(serviceDomain);
                                newDomain.setDepartmentRegion(departmentRegionDomain);
                                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                                newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));

                                newDomain.setPaxAdultTotal(containerServiceAdvancedUpdateRequest.getPaxAdultTotal());
                                newDomain.setPaxChildrenTotal(containerServiceAdvancedUpdateRequest.getPaxChildrenTotal());
                                newDomain.setPaxTcTotal(containerServiceAdvancedUpdateRequest.getPaxTcTotal());
                                newDomain.setPaxTcLocalTotal(containerServiceAdvancedUpdateRequest.getPaxTcLocalTotal());
                                newDomain.setPaxGuideTotal(containerServiceAdvancedUpdateRequest.getPaxGuideTotal());

                                newDomain.setObservation(containerServiceAdvancedUpdateRequest.getObservation());

                                if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                                }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                                }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
                                }
                                LocalDateTime localDateTime = LocalDateTime.of(
                                        itineraryDateDomain.getItineraryDate().getYear(),
                                        itineraryDateDomain.getItineraryDate().getMonth(),
                                        itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                                        containerServiceAdvancedUpdateRequest.getItineraryDateTime().getHour(),
                                        containerServiceAdvancedUpdateRequest.getItineraryDateTime().getMinute(),
                                        containerServiceAdvancedUpdateRequest.getItineraryDateTime().getSecond()
                                );
                                newDomain.setItineraryDateTime(localDateTime);
                                newDomain.setPaxTotal((newDomain.getPaxAdultTotal() != null ? newDomain.getPaxAdultTotal() : 0)  + (newDomain.getPaxChildrenTotal() != null ? newDomain.getPaxChildrenTotal()  : 0));
                                newDomain.setCustomer(registerGroupDomain.getCustomer());


                                newDomain.setServiceGroup(containerServiceAdvancedUpdateRequest.getServiceGroup());

                                if (
                                        supplierTypeDomainOperator.getIdentify().equals(supplierTypeDomain.getIdentify()) ||
                                                supplierTypeDomainTransport.getIdentify().equals(supplierTypeDomain.getIdentify())
                                ){
                                    newDomain.setServiceForOperation(true);
                                }else if (
                                        supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify()) &&
                                                containerServiceAdvancedUpdateRequest.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())
                                ){
                                    newDomain.setServiceForOperation(true);
                                } else {
                                    newDomain.setServiceForOperation(false);
                                }

                                newDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName());

                                newDomain.setPrice(containerServiceAdvancedUpdateRequest.getPrice());
                                newDomain.setPriceAdult(containerServiceAdvancedUpdateRequest.getPriceAdult());
                                newDomain.setPriceChildren(containerServiceAdvancedUpdateRequest.getPriceChildren());
                                newDomain.setPriceTc(containerServiceAdvancedUpdateRequest.getPriceTc());
                                newDomain.setPriceTcLocal(containerServiceAdvancedUpdateRequest.getPriceTcLocal());
                                newDomain.setPriceGuide(containerServiceAdvancedUpdateRequest.getPriceGuide());

                                // Supplier Type Lodging

                                newDomain.setLodgingCantSwb(containerServiceAdvancedUpdateRequest.getLodgingCantSwb());
                                newDomain.setLodgingCantMat(containerServiceAdvancedUpdateRequest.getLodgingCantMat());
                                newDomain.setLodgingCantTwin(containerServiceAdvancedUpdateRequest.getLodgingCantTwin());
                                newDomain.setLodgingCantTrp(containerServiceAdvancedUpdateRequest.getLodgingCantTrp());
                                newDomain.setLodgingCategoryStr(containerServiceAdvancedUpdateRequest.getLodgingCategoryStr());
                                newDomain.setLodgingCantBreakfast(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfast());
                                newDomain.setLodgingCantLunch(containerServiceAdvancedUpdateRequest.getLodgingCantLunch());
                                newDomain.setLodgingCantDinner(containerServiceAdvancedUpdateRequest.getLodgingCantDinner());
                                newDomain.setLodgingCantBoxLunch(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunch());
                                newDomain.setLodgingCantBreakfastDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastDateTime());
                                newDomain.setLodgingCantLunchDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantLunchDateTime());
                                newDomain.setLodgingCantDinnerDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerDateTime());
                                newDomain.setLodgingCantBoxLunchDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchDateTime());
                                newDomain.setLodgingCantBreakfastDate(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastDate());
                                newDomain.setLodgingCantLunchDate(containerServiceAdvancedUpdateRequest.getLodgingCantLunchDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBreakfastItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantLunchItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantLunchItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantLunchItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantLunchItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantLunchItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantDinnerItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantDinnerItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantDinnerItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantDinnerItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBoxLunchItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgingCantDinnerDate(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerDate());
                                newDomain.setLodgingCantBoxLunchDate(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchDate());

                                newDomain.setLodgingCantBreakfastTcGuide(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcGuide());
                                newDomain.setLodgingCantLunchTcGuide(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcGuide());
                                newDomain.setLodgingCantDinnerTcGuide(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcGuide());
                                newDomain.setLodgingCantBoxLunchTcGuide(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcGuide());
                                newDomain.setLodgingCantBreakfastTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcGuideDateTime());
                                newDomain.setLodgingCantLunchTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcGuideDateTime());
                                newDomain.setLodgingCantDinnerTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcGuideDateTime());
                                newDomain.setLodgingCantBoxLunchTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcGuideDateTime());
                                newDomain.setLodgingCantBreakfastTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcGuideDate());
                                newDomain.setLodgingCantLunchTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcGuideDate());
                                newDomain.setLodgingCantDinnerTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcGuideDate());
                                newDomain.setLodgingCantBoxLunchTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcGuideDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgingCantSwbTcGuide(containerServiceAdvancedUpdateRequest.getLodgingCantSwbTcGuide());
                                newDomain.setLodgingCantTwinTcGuide(containerServiceAdvancedUpdateRequest.getLodgingCantTwinTcGuide());

                                newDomain.setLodgingCantBreakfastTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcLocalGuide());
                                newDomain.setLodgingCantLunchTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcLocalGuide());
                                newDomain.setLodgingCantDinnerTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcLocalGuide());
                                newDomain.setLodgingCantBoxLunchTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcLocalGuide());

                                newDomain.setLodgingCantBreakfastTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcLocalGuideDateTime());
                                newDomain.setLodgingCantLunchTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcLocalGuideDateTime());
                                newDomain.setLodgingCantDinnerTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcLocalGuideDateTime());
                                newDomain.setLodgingCantBoxLunchTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcLocalGuideDateTime());
                                newDomain.setLodgingCantBreakfastTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcLocalGuideDate());
                                newDomain.setLodgingCantLunchTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcLocalGuideDate());
                                newDomain.setLodgingCantDinnerTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcLocalGuideDate());
                                newDomain.setLodgingCantBoxLunchTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcLocalGuideDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantLunchTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgingCantSwbTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgingCantSwbTcLocalGuide());
                                newDomain.setLodgingCantTwinTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgingCantTwinTcLocalGuide());

                                newDomain.setLodgingCantBreakfastGuide(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastGuide());
                                newDomain.setLodgingCantLunchGuide(containerServiceAdvancedUpdateRequest.getLodgingCantLunchGuide());
                                newDomain.setLodgingCantDinnerGuide(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerGuide());
                                newDomain.setLodgingCantBoxLunchGuide(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchGuide());
                                newDomain.setLodgingCantBreakfastGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastGuideDateTime());
                                newDomain.setLodgingCantLunchGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantLunchGuideDateTime());
                                newDomain.setLodgingCantDinnerGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerGuideDateTime());
                                newDomain.setLodgingCantBoxLunchGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchGuideDateTime());
                                newDomain.setLodgingCantBreakfastGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastGuideDate());
                                newDomain.setLodgingCantLunchGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantLunchGuideDate());
                                newDomain.setLodgingCantDinnerGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerGuideDate());
                                newDomain.setLodgingCantBoxLunchGuideDate(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchGuideDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBreakfastGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantLunchGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantLunchGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantLunchGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantLunchGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantLunchGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantDinnerGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantDinnerGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantDinnerGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantDinnerGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantDinnerGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgingCantBoxLunchGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgingCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgingCantSwbGuide(containerServiceAdvancedUpdateRequest.getLodgingCantSwbGuide());
                                newDomain.setLodgingCantTwinGuide(containerServiceAdvancedUpdateRequest.getLodgingCantTwinGuide());

                                // Supplier Type Lodge

                                newDomain.setLodgeCantSwb(containerServiceAdvancedUpdateRequest.getLodgeCantSwb());
                                newDomain.setLodgeCantMat(containerServiceAdvancedUpdateRequest.getLodgeCantMat());
                                newDomain.setLodgeCantTwin(containerServiceAdvancedUpdateRequest.getLodgeCantTwin());
                                newDomain.setLodgeCantTrp(containerServiceAdvancedUpdateRequest.getLodgeCantTrp());

                                newDomain.setLodgeCategoryStr(containerServiceAdvancedUpdateRequest.getLodgeCategoryStr());
                                newDomain.setLodgeCantBreakfast(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfast());
                                newDomain.setLodgeCantLunch(containerServiceAdvancedUpdateRequest.getLodgeCantLunch());
                                newDomain.setLodgeCantDinner(containerServiceAdvancedUpdateRequest.getLodgeCantDinner());
                                newDomain.setLodgeCantBoxLunch(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunch());
                                newDomain.setLodgeCantBreakfastDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastDateTime());
                                newDomain.setLodgeCantLunchDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantLunchDateTime());
                                newDomain.setLodgeCantDinnerDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerDateTime());
                                newDomain.setLodgeCantBoxLunchDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchDateTime());
                                newDomain.setLodgeCantBreakfastDate(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastDate());
                                newDomain.setLodgeCantLunchDate(containerServiceAdvancedUpdateRequest.getLodgeCantLunchDate());
                                newDomain.setLodgeCantDinnerDate(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerDate());
                                newDomain.setLodgeCantBoxLunchDate(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBreakfastItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantLunchItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantLunchItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantLunchItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantLunchItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantLunchItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantDinnerItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantDinnerItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantDinnerItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantDinnerItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBoxLunchItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgeCantBreakfastTcGuide(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcGuide());
                                newDomain.setLodgeCantLunchTcGuide(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcGuide());
                                newDomain.setLodgeCantDinnerTcGuide(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcGuide());

                                newDomain.setLodgeCantBoxLunchTcGuide(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcGuide());
                                newDomain.setLodgeCantBreakfastTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcGuideDateTime());
                                newDomain.setLodgeCantLunchTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcGuideDateTime());
                                newDomain.setLodgeCantDinnerTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcGuideDateTime());
                                newDomain.setLodgeCantBoxLunchTcGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcGuideDateTime());
                                newDomain.setLodgeCantBreakfastTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcGuideDate());
                                newDomain.setLodgeCantLunchTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcGuideDate());
                                newDomain.setLodgeCantDinnerTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcGuideDate());
                                newDomain.setLodgeCantBoxLunchTcGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcGuideDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgeCantSwbTcGuide(containerServiceAdvancedUpdateRequest.getLodgeCantSwbTcGuide());
                                newDomain.setLodgeCantTwinTcGuide(containerServiceAdvancedUpdateRequest.getLodgeCantTwinTcGuide());

                                newDomain.setLodgeCantBreakfastTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcLocalGuide());
                                newDomain.setLodgeCantLunchTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcLocalGuide());
                                newDomain.setLodgeCantDinnerTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcLocalGuide());
                                newDomain.setLodgeCantBoxLunchTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcLocalGuide());
                                newDomain.setLodgeCantBreakfastTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcLocalGuideDateTime());
                                newDomain.setLodgeCantLunchTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcLocalGuideDateTime());
                                newDomain.setLodgeCantDinnerTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcLocalGuideDateTime());
                                newDomain.setLodgeCantBoxLunchTcLocalGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcLocalGuideDateTime());
                                newDomain.setLodgeCantBreakfastTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcLocalGuideDate());
                                newDomain.setLodgeCantLunchTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcLocalGuideDate());
                                newDomain.setLodgeCantDinnerTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcLocalGuideDate());
                                newDomain.setLodgeCantBoxLunchTcLocalGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcLocalGuideDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantLunchTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchTcLocalGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgeCantSwbTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgeCantSwbTcLocalGuide());
                                newDomain.setLodgeCantTwinTcLocalGuide(containerServiceAdvancedUpdateRequest.getLodgeCantTwinTcLocalGuide());

                                newDomain.setLodgeCantBreakfastGuide(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastGuide());
                                newDomain.setLodgeCantLunchGuide(containerServiceAdvancedUpdateRequest.getLodgeCantLunchGuide());
                                newDomain.setLodgeCantDinnerGuide(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerGuide());
                                newDomain.setLodgeCantBoxLunchGuide(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchGuide());
                                newDomain.setLodgeCantBreakfastGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastGuideDateTime());
                                newDomain.setLodgeCantLunchGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantLunchGuideDateTime());
                                newDomain.setLodgeCantDinnerGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerGuideDateTime());
                                newDomain.setLodgeCantBoxLunchGuideDateTime(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchGuideDateTime());
                                newDomain.setLodgeCantBreakfastGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastGuideDate());
                                newDomain.setLodgeCantLunchGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantLunchGuideDate());
                                newDomain.setLodgeCantDinnerGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerGuideDate());
                                newDomain.setLodgeCantBoxLunchGuideDate(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchGuideDate());

                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBreakfastGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantLunchGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantLunchGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantLunchGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantLunchGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantLunchGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantDinnerGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantDinnerGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantDinnerGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantDinnerGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantDinnerGuideItineraryDate(itineraryDateDomainDate);
                                }
                                if (containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                                        containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                                        !containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchGuideItineraryDate().isEmpty()){
                                    ItineraryDateDomain itineraryDateDomainDate = null;
                                    IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(containerServiceAdvancedUpdateRequest.getLodgeCantBoxLunchGuideItineraryDate());
                                    if (itineraryDateIdBaseDomain != null){
                                        itineraryDateDomainDate = new ItineraryDateDomain();
                                        itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
                                    }
                                    newDomain.setLodgeCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
                                }

                                newDomain.setLodgeCantSwbGuide(containerServiceAdvancedUpdateRequest.getLodgeCantSwbGuide());
                                newDomain.setLodgeCantTwinGuide(containerServiceAdvancedUpdateRequest.getLodgeCantTwinGuide());

                                newDomain.setLodgePvt(containerServiceAdvancedUpdateRequest.getLodgePvt());
                                newDomain.setLodgeSic(containerServiceAdvancedUpdateRequest.getLodgeSic());

                                // Supplier Type Restaurant

                                newDomain.setRestaurantCantBreakfast(containerServiceAdvancedUpdateRequest.getRestaurantCantBreakfast());
                                newDomain.setRestaurantCantLunch(containerServiceAdvancedUpdateRequest.getRestaurantCantLunch());
                                newDomain.setRestaurantCantDinner(containerServiceAdvancedUpdateRequest.getRestaurantCantDinner());
                                newDomain.setRestaurantCantBoxLunch(containerServiceAdvancedUpdateRequest.getRestaurantCantBoxLunch());
                                newDomain.setRestaurantCantBreakfastTcGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantBreakfastTcGuide());
                                newDomain.setRestaurantCantLunchTcGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantLunchTcGuide());
                                newDomain.setRestaurantCantDinnerTcGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantDinnerTcGuide());
                                newDomain.setRestaurantCantBoxLunchTcGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantBoxLunchTcGuide());
                                newDomain.setRestaurantCantBreakfastTcLocalGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantBreakfastTcLocalGuide());
                                newDomain.setRestaurantCantLunchTcLocalGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantLunchTcLocalGuide());
                                newDomain.setRestaurantCantDinnerTcLocalGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantDinnerTcLocalGuide());
                                newDomain.setRestaurantCantBoxLunchTcLocalGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantBoxLunchTcLocalGuide());

                                newDomain.setRestaurantCantBreakfastGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantBreakfastGuide());
                                newDomain.setRestaurantCantLunchGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantLunchGuide());
                                newDomain.setRestaurantCantDinnerGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantDinnerGuide());
                                newDomain.setRestaurantCantBoxLunchGuide(containerServiceAdvancedUpdateRequest.getRestaurantCantBoxLunchGuide());

                                newDomain.setRestaurantCantBreakfastDrive(containerServiceAdvancedUpdateRequest.getRestaurantCantBreakfastDrive());
                                newDomain.setRestaurantCantLunchDrive(containerServiceAdvancedUpdateRequest.getRestaurantCantLunchDrive());
                                newDomain.setRestaurantCantDinnerDrive(containerServiceAdvancedUpdateRequest.getRestaurantCantDinnerDrive());

                                newDomain.setRestaurantCantBoxLunchDrive(containerServiceAdvancedUpdateRequest.getRestaurantCantBoxLunchDrive());

                                // Supplier Type Operator

                                newDomain.setOperatorPvt(containerServiceAdvancedUpdateRequest.getOperatorPvt());
                                newDomain.setOperatorSic(containerServiceAdvancedUpdateRequest.getOperatorSic());

                                repo.create(newDomain);
                            }

                        }
                    }
                }
            }
        }

    }

    @Override
    @Transactional
    public void createTour(ItineraryTourItemCreateRequest request, RegisterGroupDomain registerGroupDomain) {
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

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
        if (tourDomain.getCantDay() + Integer.parseInt(itineraryDateDomain.getItemItinerary()) - 1 > registerGroupDomain.getDays()){
            throw new BadRequestException(MessagesValidation.VALIDATION_TOUR_NOT_ASSIGNED_ITINERARY);
        }

        List<CostTourDomain> costTourDomainList = costTourRepo.searchAllByRegisterGroupAndItineraryDateSimpleDomainCaseShowCostTour(registerGroupDomain.getId(), request.getItineraryDate());
        List<CostTourDomain> existCostTourUnique = costTourDomainList.stream().filter(costTourDomain -> costTourDomain.getTour().getUniqueDay() != null && costTourDomain.getTour().getUniqueDay() ).collect(Collectors.toList());
        if (existCostTourUnique != null && !existCostTourUnique.isEmpty()){
            throw new BadRequestException(MessagesValidation.VALIDATION_TOUR_UNIQUE_DAY);
        }

        CostTourDomain newCostTourDomain = new CostTourDomain();
        newCostTourDomain.setNumMaxPax(request.getNumMaxPax());
        newCostTourDomain.setTour(tourDomain);
        newCostTourDomain.setRegisterGroup(registerGroupDomain);
        newCostTourDomain.setItineraryDate(itineraryDateDomain);
        newCostTourDomain.setTenant(UserResolver.resolveUser().getTenant());
        newCostTourDomain.setSubTotalAmount(0.00);
        CostTourDomain createCostTourDomain = costTourRepo.create(newCostTourDomain);

        List<TourItemDomain> tourItemDomainList = tourItemRepo.searchAllByTour(tourDomain.getId());
        if (tourItemDomainList != null && !tourItemDomainList.isEmpty()){
            List<TourItemDomain> tourItemDomainListResult = tourItemDomainList.stream().collect(Collectors.toList());
            for(TourItemDomain item: tourItemDomainListResult){
                ItineraryDateDomain itineraryDateDomainDb = itineraryDateRepo.getByRegisterGroupAndItemItinerary(registerGroupDomain.getId(), Integer.toString(Integer.parseInt(itineraryDateDomain.getItemItinerary()) + item.getTourDate().getDay() - 1));
                ItineraryItemDomain newDomain = new ItineraryItemDomain();
                newDomain.setRegisterGroup(registerGroupDomain);
                newDomain.setItineraryDate(itineraryDateDomainDb);
                newDomain.setSupplierType(item.getSupplierType());
                newDomain.setSupplier(item.getSupplier());
                newDomain.setService(item.getService());
                newDomain.setObservation(item.getObservation());
                newDomain.setPrice(0.0);
                newDomain.setPriceAdult(0.0);
                newDomain.setPriceChildren(0.0);
                newDomain.setPriceTc(0.0);
                newDomain.setPriceTcLocal(0.0);
                newDomain.setPriceGuide(0.0);

                /*newDomain.setPaxTotal(registerGroupDomain.getCantPaxAdult() + registerGroupDomain.getCantPaxChildren());
                newDomain.setPaxChildrenTotal(registerGroupDomain.getCantPaxChildren());
                newDomain.setPaxTcTotal(registerGroupDomain.getTC());
                newDomain.setPaxTcLocalTotal(registerGroupDomain.getTCLocal());*/

                newDomain.setPaxTotal(request.getNumMaxPax());
                newDomain.setPaxAdultTotal(request.getNumMaxPax());
                newDomain.setPaxChildrenTotal(0);
                newDomain.setPaxTcTotal(0);
                newDomain.setPaxTcLocalTotal(0);
                newDomain.setPaxGuideTotal(0);

                if (item.getSupplier() != null && item.getSupplier().getPartner() != null && item.getSupplier().getPartner().getAddress() != null && item.getSupplier().getPartner().getAddress().getDepartmentRegion() != null  ){
                    newDomain.setDepartmentRegion(item.getSupplier().getPartner().getAddress().getDepartmentRegion());
                }else{
                    newDomain.setDepartmentRegion(null);
                }

                newDomain.setTenant(UserResolver.resolveUser().getTenant());
                newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));
                newDomain.setTour(tourDomain);
                newDomain.setCostTour(createCostTourDomain);
                if (supplierTypeDomainLodging.getIdentify().equals(item.getSupplierType().getIdentify())){
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
                }else if (supplierTypeDomainLodge.getIdentify().equals(item.getSupplierType().getIdentify())){
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
                }else if (supplierTypeDomainGlamping.getIdentify().equals(item.getSupplierType().getIdentify())){
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
                newDomain.setStateItineraryService(StateBookingEnum.DRAFT.getName());
                newDomain.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());

                newDomain.setCustomer(registerGroupDomain.getCustomer());
                ItineraryItemDomain createdDomain = repo.create(newDomain);

                if (itineraryDateDomainDb.getTours() == null){
                    itineraryDateDomainDb.setTours(new HashSet<>());
                }
                itineraryDateDomainDb.getTours().add(tourDomain);
                itineraryDateRepo.edit(itineraryDateDomainDb);
            }
        }
    }

    @Override
    @Transactional
    public void createTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, RegisterGroupDomain registerGroupDomain) {
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
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
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            IdBaseDomain serviceIdBaseDomain = serviceRepo.findByIdEntity(request.getService());
            serviceDomain = new ServiceDomain();
            serviceDomain.setId(serviceIdBaseDomain.getId());
        }

        CostTourServiceItemDomain newCostTourServiceItemDomain = new CostTourServiceItemDomain();
        newCostTourServiceItemDomain.setTour(tourDomain);
        newCostTourServiceItemDomain.setRegisterGroup(registerGroupDomain);
        newCostTourServiceItemDomain.setItineraryDate(itineraryDateDomain);
        newCostTourServiceItemDomain.setCostTour(costTourDomain);
        newCostTourServiceItemDomain.setService(serviceDomain);
        newCostTourServiceItemDomain.setSupplierType(supplierTypeDomain);
        newCostTourServiceItemDomain.setSupplier(supplierDomain);
        newCostTourServiceItemDomain.setObservation(request.getObservation());
        newCostTourServiceItemDomain.setTenant(UserResolver.resolveUser().getTenant());

        newCostTourServiceItemDomain = costTourServiceItemRepo.create(newCostTourServiceItemDomain);

        ItineraryItemDomain newDomain = new ItineraryItemDomain();
        newDomain.setRegisterGroup(registerGroupDomain);
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

        if (supplierDomain != null && supplierDomain.getPartner() != null && supplierDomain.getPartner().getAddress() != null && supplierDomain.getPartner().getAddress().getDepartmentRegion() != null  ){
            newDomain.setDepartmentRegion(supplierDomain.getPartner().getAddress().getDepartmentRegion());
        }else{
            newDomain.setDepartmentRegion(null);
        }

        newDomain.setPaxTotal(costTourDomain.getNumMaxPax());
        newDomain.setPaxAdultTotal(costTourDomain.getNumMaxPax());
        newDomain.setPaxChildrenTotal(0);
        newDomain.setPaxTcTotal(0);
        newDomain.setPaxTcLocalTotal(0);
        newDomain.setPaxGuideTotal(0);

        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIndex(this.getLastIndexItineraryItem(registerGroupDomain.getId()));
        newDomain.setTour(tourDomain);
        newDomain.setCostTour(costTourDomain);
        newDomain.setCostTourServiceItem(newCostTourServiceItemDomain);

        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
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
        newDomain.setStateItineraryService(StateBookingEnum.DRAFT.getName());
        newDomain.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());
        newDomain.setCustomer(registerGroupDomain.getCustomer());
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
        SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
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
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        ItineraryItemDomain itineraryItemDomain = request.to(registerGroupDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);
        itineraryItemDomain.setRegisterBookingBase(old.getRegisterBookingBase());
        itineraryItemDomain.setGuideTransport(old.getGuideTransport());
        itineraryItemDomain.setGuideTransferDrive(old.getGuideTransferDrive());
        itineraryItemDomain.setOrderServiceItem(old.getOrderServiceItem());
        itineraryItemDomain.setIndex(old.getIndex());
        itineraryItemDomain.setIdItineraryItemQuotation(old.getIdItineraryItemQuotation());
        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
            if (
                    (itineraryItemDomain.getPaxAdultTotal() != null && itineraryItemDomain.getPaxAdultTotal() > 0) ||
                            (itineraryItemDomain.getPaxChildrenTotal() != null && itineraryItemDomain.getPaxChildrenTotal() > 0) ||
                            (itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal() > 0) ||
                            (itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal() > 0)
            ){
                itineraryItemDomain.setLodgingCantNight(1);
            }else if (itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal() > 0){
                itineraryItemDomain.setLodgingTcLocalCantNight(1);
            } else{
                itineraryItemDomain.setLodgingCantNight(1);
            }
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            if (
                    (itineraryItemDomain.getPaxAdultTotal() != null && itineraryItemDomain.getPaxAdultTotal() > 0) ||
                            (itineraryItemDomain.getPaxChildrenTotal() != null && itineraryItemDomain.getPaxChildrenTotal() > 0) ||
                            (itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal() > 0) ||
                            (itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal() > 0)
            ){
                itineraryItemDomain.setLodgeCantNight(1);
            }else if (itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal() > 0){
                itineraryItemDomain.setLodgeTcLocalCantNight(1);
            } else{
                itineraryItemDomain.setLodgeCantNight(1);
            }
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
            if (
                    (itineraryItemDomain.getPaxAdultTotal() != null && itineraryItemDomain.getPaxAdultTotal() > 0) ||
                            (itineraryItemDomain.getPaxChildrenTotal() != null && itineraryItemDomain.getPaxChildrenTotal() > 0) ||
                            (itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal() > 0) ||
                            (itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal() > 0)
            ){
                itineraryItemDomain.setLodgingCantNight(1);
            }else if (itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal() > 0){
                itineraryItemDomain.setLodgingTcLocalCantNight(1);
            } else{
                itineraryItemDomain.setLodgingCantNight(1);
            }
        }

        if (
                supplierTypeDomainOperator.getIdentify().equals(supplierTypeDomain.getIdentify()) ||
                        supplierTypeDomainTransport.getIdentify().equals(supplierTypeDomain.getIdentify())
        ){
            itineraryItemDomain.setServiceForOperation(true);
        }else if (
                supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify()) &&
                        request.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())
        ){
            itineraryItemDomain.setServiceForOperation(true);
        } else {
            itineraryItemDomain.setServiceForOperation(false);
        }

        LocalDateTime localDateTime = LocalDateTime.of(
                itineraryDateDomain.getItineraryDate().getYear(),
                itineraryDateDomain.getItineraryDate().getMonth(),
                itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                request.getItineraryDateTime().getHour(),
                request.getItineraryDateTime().getMinute(),
                request.getItineraryDateTime().getSecond()
        );
        itineraryItemDomain.setItineraryDateTime(localDateTime);
        itineraryItemDomain.setLodgingTcCantNight(old.getLodgingTcCantNight());
        itineraryItemDomain.setLodgingTcLocalCantNight(old.getLodgingTcLocalCantNight());
        itineraryItemDomain.setLodgingGuideCantNight(old.getLodgingGuideCantNight());
        itineraryItemDomain.setTour(old.getTour());
        itineraryItemDomain.setCostTour(old.getCostTour());
        itineraryItemDomain.setCostTourServiceItem(old.getCostTourServiceItem());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            itineraryItemDomain.setStateItineraryService(old.getStateItineraryService());
            itineraryItemDomain.setStateServiceInvoice(old.getStateServiceInvoice());
        }
        itineraryItemDomain.setCustomer(old.getCustomer());
        itineraryItemDomain.setGeneratedBy(old.getGeneratedBy());
        if (request.getLodgingCantBreakfastItineraryDate() != null &&
                request.getLodgingCantBreakfastItineraryDate() != null &&
                !request.getLodgingCantBreakfastItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgingCantLunchItineraryDate() != null &&
                request.getLodgingCantLunchItineraryDate() != null &&
                !request.getLodgingCantLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerItineraryDate() != null &&
                request.getLodgingCantDinnerItineraryDate() != null &&
                !request.getLodgingCantDinnerItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchItineraryDate() != null &&
                request.getLodgingCantBoxLunchItineraryDate() != null &&
                !request.getLodgingCantBoxLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                request.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                !request.getLodgingCantBreakfastTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantLunchTcGuideItineraryDate() != null &&
                request.getLodgingCantLunchTcGuideItineraryDate() != null &&
                !request.getLodgingCantLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                request.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                !request.getLodgingCantDinnerTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                request.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                !request.getLodgingCantBoxLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantDinnerTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgingCantBreakfastGuideItineraryDate() != null &&
                request.getLodgingCantBreakfastGuideItineraryDate() != null &&
                !request.getLodgingCantBreakfastGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantLunchGuideItineraryDate() != null &&
                request.getLodgingCantLunchGuideItineraryDate() != null &&
                !request.getLodgingCantLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerGuideItineraryDate() != null &&
                request.getLodgingCantDinnerGuideItineraryDate() != null &&
                !request.getLodgingCantDinnerGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                request.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                !request.getLodgingCantBoxLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgeCantBreakfastItineraryDate() != null &&
                request.getLodgeCantBreakfastItineraryDate() != null &&
                !request.getLodgeCantBreakfastItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchItineraryDate() != null &&
                request.getLodgeCantLunchItineraryDate() != null &&
                !request.getLodgeCantLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerItineraryDate() != null &&
                request.getLodgeCantDinnerItineraryDate() != null &&
                !request.getLodgeCantDinnerGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchItineraryDate() != null &&
                request.getLodgeCantBoxLunchItineraryDate() != null &&
                !request.getLodgeCantBoxLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                request.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                !request.getLodgeCantBreakfastTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchTcGuideItineraryDate() != null &&
                request.getLodgeCantLunchTcGuideItineraryDate() != null &&
                !request.getLodgeCantLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                request.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                !request.getLodgeCantDinnerTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                request.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                !request.getLodgeCantBoxLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantDinnerTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgeCantBreakfastGuideItineraryDate() != null &&
                request.getLodgeCantBreakfastGuideItineraryDate() != null &&
                !request.getLodgeCantBreakfastGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchGuideItineraryDate() != null &&
                request.getLodgeCantLunchGuideItineraryDate() != null &&
                !request.getLodgeCantLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerGuideItineraryDate() != null &&
                request.getLodgeCantDinnerGuideItineraryDate() != null &&
                !request.getLodgeCantDinnerGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                request.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                !request.getLodgeCantBoxLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
        }

        ItineraryItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(itineraryItemDomain, old));
        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse edit(String id, ItineraryItemUpdateRequest request, RegisterGroupDomain registerGroupDomain) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        SupplierTypeDomain supplierTypeDomainTransport = supplierTypeRepo.findOne("4", null);
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRepo.findById(request.getSupplier());
        }
        if (supplierDomain != null){
            if (!old.getSupplier().getId().equals(supplierDomain.getId())){
                if (request.getSupplierType().equals(supplierTypeDomainLodging.getId())){
                    RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), old.getSupplier().getId());
                    if (registerBookingLodgingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_OF_SUPPLIER_TO_REMOVE);
                    }
                } else if (request.getSupplierType().equals(supplierTypeDomainOperator.getId())){
                    RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), old.getSupplier().getId());
                    if (registerBookingOperatorDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_OF_SUPPLIER_TO_REMOVE);
                    }
                } else if (request.getSupplierType().equals(supplierTypeDomainRestaurant.getId())){
                    RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), old.getSupplier().getId());
                    if (registerBookingRestaurantDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_OF_SUPPLIER_TO_REMOVE);
                    }
                } else if (request.getSupplierType().equals(supplierTypeDomainLodge.getId())){
                    RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), old.getSupplier().getId());
                    if (registerBookingLodgerDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_OF_SUPPLIER_TO_REMOVE);
                    }
                }else if (request.getSupplierType().equals(supplierTypeDomainGlamping.getId())){
                    RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), old.getSupplier().getId());
                    if (registerBookingGlampingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_OF_SUPPLIER_TO_REMOVE);
                    }
                }else if (request.getSupplierType().equals(supplierTypeDomainInstitution.getId())){
                    RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), old.getSupplier().getId());
                    if (registerBookingInstitutionDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_OF_SUPPLIER_TO_REMOVE);
                    }
                }
            }
        }


        ItineraryDateDomain itineraryDateDomain = null;
        if (request.getItineraryDate() != null && !request.getItineraryDate().isEmpty()){
            itineraryDateDomain = itineraryDateRepo.findById(request.getItineraryDate());
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }
        DepartmentRegionDomain departmentRegionDomain = null;
        if (supplierDomain != null && supplierDomain.getPartner() != null && supplierDomain.getPartner().getAddress() != null && supplierDomain.getPartner().getAddress().getDepartmentRegion() != null  ){
            departmentRegionDomain = supplierDomain.getPartner().getAddress().getDepartmentRegion();
        }
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
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        ItineraryItemDomain itineraryItemDomain = request.to(registerGroupDomain, itineraryDateDomain, supplierTypeDomain, supplierDomain, serviceDomain, departmentRegionDomain, supplierLodgingDomain);

        if (lodgingCategory != null){
            itineraryItemDomain.setLodgingCategory(lodgingCategory);
        }
        if (lodgeCategory != null){
            itineraryItemDomain.setLodgeCategory(lodgeCategory);
        }

        itineraryItemDomain.setRegisterBookingBase(old.getRegisterBookingBase());
        itineraryItemDomain.setGuideTransport(old.getGuideTransport());
        itineraryItemDomain.setGuideTransferDrive(old.getGuideTransferDrive());
        itineraryItemDomain.setOrderServiceItem(old.getOrderServiceItem());
        itineraryItemDomain.setIndex(old.getIndex());
        itineraryItemDomain.setIdItineraryItemQuotation(old.getIdItineraryItemQuotation());
        itineraryItemDomain.setGeneratedBy(old.getGeneratedBy());
        if (supplierTypeDomainLodging.getIdentify().equals(supplierTypeDomain.getIdentify())){
            if (
                    (itineraryItemDomain.getPaxAdultTotal() != null && itineraryItemDomain.getPaxAdultTotal() > 0) ||
                            (itineraryItemDomain.getPaxChildrenTotal() != null && itineraryItemDomain.getPaxChildrenTotal() > 0) ||
                            (itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal() > 0) ||
                            (itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal() > 0)
            ){
                itineraryItemDomain.setLodgingCantNight(1);
            }else if (itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal() > 0){
                itineraryItemDomain.setLodgingTcLocalCantNight(1);
            } else{
                itineraryItemDomain.setLodgingCantNight(1);
            }
        }else if (supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify())){
            if (
                    (itineraryItemDomain.getPaxAdultTotal() != null && itineraryItemDomain.getPaxAdultTotal() > 0) ||
                            (itineraryItemDomain.getPaxChildrenTotal() != null && itineraryItemDomain.getPaxChildrenTotal() > 0) ||
                            (itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal() > 0) ||
                            (itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal() > 0)
            ){
                itineraryItemDomain.setLodgeCantNight(1);
            }else if (itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal() > 0){
                itineraryItemDomain.setLodgeTcLocalCantNight(1);
            } else{
                itineraryItemDomain.setLodgeCantNight(1);
            }
        }else if (supplierTypeDomainGlamping.getIdentify().equals(supplierTypeDomain.getIdentify())){
            if (
                    (itineraryItemDomain.getPaxAdultTotal() != null && itineraryItemDomain.getPaxAdultTotal() > 0) ||
                            (itineraryItemDomain.getPaxChildrenTotal() != null && itineraryItemDomain.getPaxChildrenTotal() > 0) ||
                            (itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal() > 0) ||
                            (itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal() > 0)
            ){
                itineraryItemDomain.setLodgingCantNight(1);
            }else if (itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal() > 0){
                itineraryItemDomain.setLodgingTcLocalCantNight(1);
            } else{
                itineraryItemDomain.setLodgingCantNight(1);
            }
        }

        if (
                supplierTypeDomainOperator.getIdentify().equals(supplierTypeDomain.getIdentify()) ||
                        supplierTypeDomainTransport.getIdentify().equals(supplierTypeDomain.getIdentify())
        ){
            itineraryItemDomain.setServiceForOperation(true);
        }else if (
                supplierTypeDomainLodge.getIdentify().equals(supplierTypeDomain.getIdentify()) &&
                        request.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())
        ){
            itineraryItemDomain.setServiceForOperation(true);
        } else {
            itineraryItemDomain.setServiceForOperation(false);
        }

        LocalDateTime localDateTime = LocalDateTime.of(
                itineraryDateDomain.getItineraryDate().getYear(),
                itineraryDateDomain.getItineraryDate().getMonth(),
                itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                request.getItineraryDateTime().getHour(),
                request.getItineraryDateTime().getMinute(),
                request.getItineraryDateTime().getSecond()
        );
        itineraryItemDomain.setItineraryDateTime(localDateTime);
        itineraryItemDomain.setLodgingTcCantNight(old.getLodgingTcCantNight());
        itineraryItemDomain.setLodgingTcLocalCantNight(old.getLodgingTcLocalCantNight());
        itineraryItemDomain.setLodgingGuideCantNight(old.getLodgingGuideCantNight());
        itineraryItemDomain.setTour(old.getTour());
        itineraryItemDomain.setCostTour(old.getCostTour());
        itineraryItemDomain.setCostTourServiceItem(old.getCostTourServiceItem());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            itineraryItemDomain.setStateItineraryService(old.getStateItineraryService());
            itineraryItemDomain.setStateServiceInvoice(old.getStateServiceInvoice());
        }

        itineraryItemDomain.setCustomer(old.getCustomer());

        if (request.getLodgingCantBreakfastItineraryDate() != null &&
                request.getLodgingCantBreakfastItineraryDate() != null &&
                !request.getLodgingCantBreakfastItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgingCantLunchItineraryDate() != null &&
                request.getLodgingCantLunchItineraryDate() != null &&
                !request.getLodgingCantLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerItineraryDate() != null &&
                request.getLodgingCantDinnerItineraryDate() != null &&
                !request.getLodgingCantDinnerItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchItineraryDate() != null &&
                request.getLodgingCantBoxLunchItineraryDate() != null &&
                !request.getLodgingCantBoxLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                request.getLodgingCantBreakfastTcGuideItineraryDate() != null &&
                !request.getLodgingCantBreakfastTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantLunchTcGuideItineraryDate() != null &&
                request.getLodgingCantLunchTcGuideItineraryDate() != null &&
                !request.getLodgingCantLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                request.getLodgingCantDinnerTcGuideItineraryDate() != null &&
                !request.getLodgingCantDinnerTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                request.getLodgingCantBoxLunchTcGuideItineraryDate() != null &&
                !request.getLodgingCantBoxLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantDinnerTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantDinnerTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgingCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgingCantBreakfastGuideItineraryDate() != null &&
                request.getLodgingCantBreakfastGuideItineraryDate() != null &&
                !request.getLodgingCantBreakfastGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBreakfastGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantLunchGuideItineraryDate() != null &&
                request.getLodgingCantLunchGuideItineraryDate() != null &&
                !request.getLodgingCantLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantLunchGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantDinnerGuideItineraryDate() != null &&
                request.getLodgingCantDinnerGuideItineraryDate() != null &&
                !request.getLodgingCantDinnerGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantDinnerGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantDinnerGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                request.getLodgingCantBoxLunchGuideItineraryDate() != null &&
                !request.getLodgingCantBoxLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgingCantBoxLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgingCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
        }

        if (request.getLodgeCantBreakfastItineraryDate() != null &&
                request.getLodgeCantBreakfastItineraryDate() != null &&
                !request.getLodgeCantBreakfastItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchItineraryDate() != null &&
                request.getLodgeCantLunchItineraryDate() != null &&
                !request.getLodgeCantLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerItineraryDate() != null &&
                request.getLodgeCantDinnerItineraryDate() != null &&
                !request.getLodgeCantDinnerItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchItineraryDate() != null &&
                request.getLodgeCantBoxLunchItineraryDate() != null &&
                !request.getLodgeCantBoxLunchItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                request.getLodgeCantBreakfastTcGuideItineraryDate() != null &&
                !request.getLodgeCantBreakfastTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchTcGuideItineraryDate() != null &&
                request.getLodgeCantLunchTcGuideItineraryDate() != null &&
                !request.getLodgeCantLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                request.getLodgeCantDinnerTcGuideItineraryDate() != null &&
                !request.getLodgeCantDinnerTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerTcGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                request.getLodgeCantBoxLunchTcGuideItineraryDate() != null &&
                !request.getLodgeCantBoxLunchTcGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchTcGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchTcGuideItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantBreakfastTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantDinnerTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantDinnerTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                request.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null &&
                !request.getLodgeCantBoxLunchTcLocalGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchTcLocalGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchTcLocalGuideItineraryDate(itineraryDateDomainDate);
        }


        if (request.getLodgeCantBreakfastGuideItineraryDate() != null &&
                request.getLodgeCantBreakfastGuideItineraryDate() != null &&
                !request.getLodgeCantBreakfastGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBreakfastGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBreakfastGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantLunchGuideItineraryDate() != null &&
                request.getLodgeCantLunchGuideItineraryDate() != null &&
                !request.getLodgeCantLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantLunchGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantDinnerGuideItineraryDate() != null &&
                request.getLodgeCantDinnerGuideItineraryDate() != null &&
                !request.getLodgeCantDinnerGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantDinnerGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantDinnerGuideItineraryDate(itineraryDateDomainDate);
        }
        if (request.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                request.getLodgeCantBoxLunchGuideItineraryDate() != null &&
                !request.getLodgeCantBoxLunchGuideItineraryDate().isEmpty()){
            ItineraryDateDomain itineraryDateDomainDate = null;
            IdBaseDomain itineraryDateIdBaseDomain = itineraryDateRepo.findByIdEntity(request.getLodgeCantBoxLunchGuideItineraryDate());
            if (itineraryDateIdBaseDomain != null){
                itineraryDateDomainDate = new ItineraryDateDomain();
                itineraryDateDomainDate.setId(itineraryDateIdBaseDomain.getId());
            }
            itineraryItemDomain.setLodgeCantBoxLunchGuideItineraryDate(itineraryDateDomainDate);
        }

        ItineraryItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(itineraryItemDomain, old));
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, editedDomain));
        }*/
        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ItineraryItemResponse old = findById(id);
        repo.deleteById(old.getId());
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            this.eventPublisher.publishEvent(new ItineraryItemDeleteEvent(this, repo.findById(id)));
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
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ItineraryItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemSearchResponse searchAll(ItineraryItemSearchRequest bankSearchRequest) {
       PageDTO<ItineraryItemDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ItineraryItemSearchResponse bankSearchRespose = new ItineraryItemSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ItineraryItemResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemSearchResponse searchFilterAll(ItineraryItemSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ItineraryItemDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ItineraryItemSearchResponse bankSearchRespose = new ItineraryItemSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ItineraryItemResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemResponse> searchAllByRegisterGroup(String idRegisterGroup) {
        List<ItineraryItemDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list.stream().map(itineraryItemDomain ->  ItineraryItemResponse.from(itineraryItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemResponse searchByRegisterGroupAndItineraryItem(String idRegisterGroup, String idItineraryItem) {
        ItineraryItemDomain itineraryItemDomain = repo.searchAllByRegisterGroupAndItineraryItem(idRegisterGroup, idItineraryItem);
        return ItineraryItemResponse.from(itineraryItemDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemResponse searchByRegisterGroupAndItineraryItemVoucherEgress(String idRegisterGroup, String idItineraryItem) {
        ItineraryItemDomain itineraryItemDomain = repo.searchAllByRegisterGroupAndItineraryItemCaseVoucherEgress(idRegisterGroup, idItineraryItem);
        return ItineraryItemResponse.from(itineraryItemDomain);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemDomain> searchAllDomainByRegisterGroup(String idRegisterGroup) {
        List<ItineraryItemDomain> list = repo.searchAllByRegisterGroup(idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemDomain> searchAllDomainByRegisterGroupAndItineraryDate(String idItineraryDate, String idRegisterGroup) {
        List<ItineraryItemDomain> list = repo.searchAllByItineraryDateAndRegisterGroup(idItineraryDate, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemDomain> searchAllDomainSimpleDomainCaseGenerateBookingByRegisterGroupAndItineraryDate(String idItineraryDate, String idRegisterGroup) {
        List<ItineraryItemDomain> list = repo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(idItineraryDate, idRegisterGroup);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemDomain> searchAllDomainByRegisterGroupAndItineraryDateAndSupplierType(String idSupplierType, String idItineraryDate, String idRegisterGroup) {
        List<ItineraryItemDomain> list = repo.searchAllByItineraryDateAndRegisterGroupAndSupplierType(idItineraryDate, idRegisterGroup, idSupplierType);
        return list;
    }

    private Integer getLastIndexItineraryItem(String idRegisterGroup) {
        Integer index = 0;
        List<ItineraryItemDomain> list = repo.searchAllByRegisterGroupSimpleDomain(idRegisterGroup).stream().collect(Collectors.toList());
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
    public List<ItineraryItemDomain> searchAllDomainByRegisterGroupAndSupplier(String idSupplier, String idRegisterGroup) {
        List<ItineraryItemDomain> list = repo.searchAllByRegisterGroupAndSupplier(idRegisterGroup, idSupplier);
        return list;
    }

    @Override
    @Transactional
    public ItineraryItemResponse changeState(String id, ItineraryItemChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }
        old.setStateItineraryService(request.getStateItineraryItem());
        ItineraryItemDomain editedDomain = repo.edit(old);
        switch (request.getStateItineraryItem()) {
            case "Draft" -> {
                _changeStateDraft(old, request);
            }
            case "Request" -> {
                _changeStateRequest(old, request);
            }
            case "Confirmed" -> {
                _changeStateConfirmed(old, request);
            }
            case "Reconfirmed" -> {
                _changeStateReconfirmed(old, request);
            }
            case "Modified" -> {
                _changeStateModified(old, request);
            }
            case "Annulled" -> {
                _changeStateAnnulled(old, request);
            }
            case "Cancelled" -> {
                _changeStateCancelled(old, request);
            }
            case "Booking" -> {
                _changeStateBooking(old, request);
            }
            case "To Reschedule" -> {
                _changeStateToReschedule(old, request);
            }
            case "Rescheduled" -> {
                _changeStateRescheduled(old, request);
            }
            case "Finished" -> {
                _changeStateFinished(old, request);
            }
            case "Generated Pending MP" -> {
                _changeStateGeneratedPendingMp(old, request);
            }
            case "Generated Pending ABC" -> {
                _changeStateGeneratedPendingAbc(old, request);
            }
            case "Generated Pending BCD" -> {
                _changeStateGeneratedPendingBcd(old, request);
            }
            case "Generated Confirm Peru Rail" -> {
                _changeStateGeneratedConfirmPeruRail(old, request);
            }
            default -> {

            }
        }
        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse changeSupplier(String id, ItineraryItemChangeSupplierRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }
        SupplierOperatorDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierOperatorRepo.findById(request.getSupplier());
        }

        old.setSupplier(supplierDomain);
        ItineraryItemDomain editedDomain = repo.edit(old);

        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse changeNote(String id, ItineraryItemChangeNoteRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }

        old.setNote(request.getNote());
        ItineraryItemDomain editedDomain = repo.edit(old);

        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public EntityExistBaseResponse existItineraryItemById(String id) {
        if (id == null) {
            return null;
        }
        EntityExistBaseResponse entityExistBaseResponse = new EntityExistBaseResponse();
        IdBaseDomain itineraryItemIdBaseDomainOld = repo.findByIdEntity(id);
        if (itineraryItemIdBaseDomainOld != null){
            entityExistBaseResponse.setFlag(true);
        }else{
            entityExistBaseResponse.setFlag(false);
        }
        return entityExistBaseResponse;
    }

    @Override
    @Transactional
    public ItineraryItemResponse changeStateInvoice(String id, ItineraryItemChangeStateInvoiceRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }
        old.setStateServiceInvoice(request.getStateItineraryItem());
        ItineraryItemDomain editedDomain = repo.edit(old);
        switch (request.getStateItineraryItem()) {
            case "Pending Payment" -> {
                _changeStateInvoicePendingPayment(old, request);
            }
            case "Document Received" -> {
                _changeStateInvoiceDocumentReceived(old, request);
            }
            case "Generated" -> {
                _changeStateInvoiceGenerated(old, request);
            }
            default -> {

            }
        }
        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse changeNoteInvoice(String id, ItineraryItemChangeNoteInvoiceRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }

        old.setNoteInvoice(request.getNote());
        ItineraryItemDomain editedDomain = repo.edit(old);

        return ItineraryItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ItineraryItemResponse changeDateInvoice(String id, ItineraryItemChangeDateInvoiceRequest request) {
        if (id == null) {
            return null;
        }
        ItineraryItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }

        old.setDocumentDateInvoice(request.getDate());
        ItineraryItemDomain editedDomain = repo.edit(old);

        return ItineraryItemResponse.from(editedDomain);
    }

    private void _changeStateDraft(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateRequest(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateConfirmed(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateReconfirmed(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateModified(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateAnnulled(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateCancelled(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateBooking(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateToReschedule(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateRescheduled(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateFinished(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingMp(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingAbc(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingBcd(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }

    private void _changeStateGeneratedConfirmPeruRail(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateRequest request){

    }


    private void _changeStateInvoicePendingPayment(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateInvoiceRequest request){

    }

    private void _changeStateInvoiceDocumentReceived(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateInvoiceRequest request){

    }

    private void _changeStateInvoiceGenerated(ItineraryItemDomain itineraryItemDomain, ItineraryItemChangeStateInvoiceRequest request){

    }

}
