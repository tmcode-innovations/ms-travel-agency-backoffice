package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registergroup.RegisterGroupConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterGroup;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.changerequest.ChangeRequestModelRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.idiom.IdiomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification.NotificationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtour.CostTourBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtouritem.CostTourItemBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtourserviceitem.CostTourServiceItemBookRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengerbooking.PassengerBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.RegisterBookingBaseRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.RegisterBookingGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinginstitution.RegisterBookingInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemglamping.RegisterBookingItemGlamRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger.RegisterBookingItemLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodging.RegisterBookingItemLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.RegisterBookingItemOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.RegisterBookingLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant.RegisterBookingRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.PersistRegisterGroupNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.PersistRegisterGroupNumberTypeGuruCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.enums.SortType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.CostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtouritem.CostTourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem.CostTourServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.ItineraryItemCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger.PassengerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.configurationsupplieroperator.ConfigurationSupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.RequestOrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.OrderServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SortRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.departmentregion.DepartmentRegionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtourserviceitem.CostTourServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itinerarydate.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.enums.ModeUpdateServiceAdvancedEnum;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.RegisterBookingGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinginstitution.RegisterBookingInstitutionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.WrapFeedingItem;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.RegisterBookingLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.RegisterBookingLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.RegisterBookingOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtour.CostTourUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.costtouritem.CostTourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.ChangeItineraryDayCrmRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.EmployeeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.suppliertype.SupplierTypeResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.operation.requestorderservice.RequestOrderServiceResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.passengergroup.JRPassengerGroupRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.passengergroup.JRPassengerGroupXlsRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registergroup.*;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.StringUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.*;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.PassengerDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.TourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateQuotationEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.ConfigurationSupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.OrderServiceTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.operation.enums.RequestOrderServiceStateEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.*;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.crm.QuotationUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.hr.EmployeeUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.exceptions.ValidationException;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class RegisterGroupUseCaseImpl implements RegisterGroupUseCase {

    @Autowired
    private RegisterGroupRepo repo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private IdiomRepo idiomRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private QuotationRepo quotationRepo;

    @Autowired
    private ItineraryDateCrmRepo itineraryDateCrmRepo;

    @Autowired
    private ItineraryItemCrmRepo itineraryItemCrmRepo;

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private RegisterBookingBaseRepo registerBookingBaseRepo;

    @Autowired
    private ChangeRequestModelRepo changeRequestModelRepo;

    @Autowired
    private RegisterGroupConverter converter;

    @Autowired
    private SetFieldDefaultRegisterGroup setFieldDefault;

    @Autowired
    PersistRegisterGroupNumberCallbackImpl persistRegisterGroupNumberCallbackImpl;

    @Autowired
    PersistRegisterGroupNumberTypeGuruCallbackImpl persistRegisterGroupNumberTypeGuruCallback;

    @Autowired
    private PassengerGroupUseCase passengerGroupUseCase;

    @Autowired
    private ItineraryDateUseCase itineraryDateUseCase;

    @Autowired
    private ItineraryItemUseCase itineraryItemUseCase;

    @Autowired
    private RegisterBookingLodgingUseCase registerBookingLodgingUseCase;

    @Autowired
    private RegisterBookingGlampingUseCase registerBookingGlampingUseCase;

    @Autowired
    private RegisterBookingInstitutionUseCase registerBookingInstitutionUseCase;

    @Autowired
    private RegisterBookingLodgerUseCase registerBookingLodgerUseCase;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

    @Autowired
    private RegisterBookingItemLodgingRepo registerBookingItemLodgingRepo;

    @Autowired
    private RegisterBookingItemGlamRepo registerBookingItemGlamRepo;

    @Autowired
    private RegisterBookingItemRestaurantRepo registerBookingItemRestaurantRepo;

    @Autowired
    private RegisterBookingItemInstitutionRepo registerBookingItemInstitutionRepo;

    @Autowired
    private RegisterBookingItemOperatorRepo registerBookingItemOperatorRepo;

    @Autowired
    private RegisterBookingItemLodgerRepo registerBookingItemLodgerRepo;

    @Autowired
    private RegisterBookingItemLodgerLodgingRepo registerBookingItemLodgerLodgingRepo;

    @Autowired
    private RegisterBookingOperatorUseCase registerBookingOperatorUseCase;

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
    private PassengerBookingRepo passengerBookingRepo;

    @Autowired
    private RegisterBookingItemLodgingFeedingRepo registerBookingItemLodgingFeedingRepo;

    @Autowired
    private ObjectRepo objectRepo;

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private QuotationUseCase quotationUseCase;

    @Autowired
    private EmployeeUseCase employeeUseCase;

    @Autowired
    private JRPassengerGroupRepository jrPassengerGroupRepository;

    @Autowired
    private JRPassengerGroupXlsRepository jrPassengerGroupXlsRepository;

    @Autowired
    private JRRegisterBookingLodgerCuponAllRepository jrRegisterBookingLodgerCuponAllRepository;

    @Autowired
    private JRRegisterBookingLodgingCuponAllRepository jrRegisterBookingLodgingCuponAllRepository;

    @Autowired
    private JRRegisterBookingOperatorCuponAllRepository jrRegisterBookingOperatorCuponAllRepository;

    @Autowired
    private JRRegisterBookingRestaurantCuponAllRepository jrRegisterBookingRestaurantCuponAllRepository;

    @Autowired
    private JRRegisterBookingCuponAllRepository jrRegisterBookingCuponAllRepository;

    @Autowired
    private JRRegisterGroupItineraryRepository jrRegisterGroupItineraryRepository;

    @Autowired
    private JRRegisterGroupCantByResponsibleRepository jrRegisterGroupCantByResponsibleRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ConfigurationSupplierOperatorRepo configurationSupplierOperatorRepo;

    @Autowired
    private RequestOrderServiceRepo requestOrderServiceRepo;

    @Autowired
    private OrderServiceItemRepo orderServiceItemRepo;

    public static final String GURU_SEDE_CUSCO_01 = "01";

    @Autowired
    private CostTourBookingRepo costTourRepo;

    @Autowired
    private CostTourItemBookingRepo costTourItemRepo;

    @Autowired
    private CostTourBookingUseCase costTourUseCase;

    @Autowired
    private CostTourItemBookingUseCase costTourItemUseCase;

    @Autowired
    private CostTourRepo costTourCrmRepo;

    @Autowired
    private CostTourItemRepo costTourItemCrmRepo;

    @Autowired
    private CostTourServiceItemRepo costTourServiceItemCrmRepo;

    @Autowired
    private CostTourServiceItemBookUseCase costTourServiceItemUseCase;

    @Autowired
    private CostTourServiceItemBookRepo costTourServiceItemRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Override
    @Transactional
    public RegisterGroupResponse create(RegisterGroupCreateRequest request) {
        RegisterGroupDomain registerGroupDomainExist = repo.findByName(request.getName());
        if (registerGroupDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            quotationDomain = quotationRepo.findById(request.getQuotation());
        }
        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            customerDomain = customerRepo.findById(request.getCustomer());
        }
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            programDomain = programRepo.findById(request.getProgram());
        }
        EmployeeDomain employeeDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            employeeDomain = employeeRepo.findById(request.getResponsible());
        }
        IdiomDomain idiomDomain = null;
        if (request.getIdiom() != null && !request.getIdiom().isEmpty()){
            idiomDomain = idiomRepo.findById(request.getIdiom());
        }
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = countryRepo.findById(request.getCountry());
        }
        RegisterGroupDomain newDomain = request.to(programDomain, quotationDomain, customerDomain, idiomDomain, employeeDomain, countryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRegisterGroupNumberCallbackImpl.generateNumber(
                LocalDate.now().getYear(),
                LocalDate.now().getMonthValue(),
                customerDomain.getPartner().getAddress().getCountry().getIdentify(),
                true)
        );
        newDomain.setStateRegisterGroup(StateRegisterGroupEnum.DRAFT.getName());
        RegisterGroupDomain createdDomain = repo.create(newDomain);
        itineraryDateUseCase.generateDateItinerary(createdDomain, createdDomain.getDays(), createdDomain.getInitTour());
        return RegisterGroupResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterGroupResponse opened_v0(String id, RegisterGroupUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterGroupDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        RegisterGroupDomain registerGroupDomainExist = repo.findByName(request.getName());
        if (registerGroupDomainExist != null && !registerGroupDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            IdBaseDomain quotationIdBaseDomain = quotationRepo.findByIdEntity(request.getQuotation());
            if (quotationIdBaseDomain != null){
                quotationDomain = new QuotationDomain();
                quotationDomain.setId(quotationIdBaseDomain.getId());
            }
        }
        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            customerDomain = customerRepo.findById(request.getCustomer());
        }
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            IdBaseDomain programIdBaseDomain = programRepo.findByIdEntity(request.getProgram());
            if (programIdBaseDomain != null){
                programDomain = new ProgramDomain();
                programDomain.setId(programIdBaseDomain.getId());
            }
        }
        EmployeeDomain employeeDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            if (employeeIdBaseDomain != null){
                employeeDomain = new EmployeeDomain();
                employeeDomain.setId(employeeIdBaseDomain.getId());
            }
        }

        IdiomDomain idiomDomain = null;
        if (request.getIdiom() != null && !request.getIdiom().isEmpty()){
            IdBaseDomain idiomIdBaseDomain = idiomRepo.findByIdEntity(request.getIdiom());
            if (idiomIdBaseDomain != null){
                idiomDomain = new IdiomDomain();
                idiomDomain.setId(idiomIdBaseDomain.getId());
            }
        }

        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            IdBaseDomain countryIdBaseDomain = countryRepo.findByIdEntity(request.getCountry());
            if (countryIdBaseDomain != null){
                countryDomain = new CountryDomain();
                countryDomain.setId(countryIdBaseDomain.getId());
            }
        }

        RegisterGroupDomain registerGroupDomain = request.to(programDomain, quotationDomain, customerDomain, idiomDomain, employeeDomain, countryDomain);
        registerGroupDomain.setIdentify(persistRegisterGroupNumberCallbackImpl.generateNumber(
                LocalDate.now().getYear(),
                LocalDate.now().getMonthValue(),
                registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().getIdentify(),
                true)
        );
        registerGroupDomain.setTenant(UserResolver.resolveUser().getTenant());
        registerGroupDomain.setApertureWrite(true);
        registerGroupDomain.setDateAperture(LocalDate.now());
        registerGroupDomain.setStateRegisterGroup(registerGroupDomain.getStateRegisterGroup());
        registerGroupDomain.setEndTour(registerGroupDomain.getInitTour().plusDays(registerGroupDomain.getDays()-1));
        if (programDomain != null){
            itineraryDateUseCase.generateDateItineraryByProgram(registerGroupDomain, programDomain, registerGroupDomain.getInitTour());
        }else{
            itineraryDateUseCase.generateDateItinerary(registerGroupDomain, registerGroupDomain.getDays(), registerGroupDomain.getInitTour());
        }
        RegisterGroupDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerGroupDomain, old));

        // Update Quotation
        QuotationDomain quotationDomainDbEdit = quotationRepo.findById(registerGroupDomain.getQuotation().getId());
        quotationDomainDbEdit.setResponsibleFile(registerGroupDomain.getResponsible());
        quotationDomainDbEdit.setIdentifyFile(registerGroupDomain.getIdentify());
        quotationDomainDbEdit.setStateQuotation(StateQuotationEnum.IN_BOOKING.getName());
        quotationRepo.edit(quotationDomainDbEdit);

        return RegisterGroupResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterGroupResponse edit(String id, RegisterGroupUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterGroupDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        RegisterGroupDomain registerGroupDomainExist = repo.findByName(request.getName());
        if (registerGroupDomainExist != null && !registerGroupDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        QuotationDomain quotationDomain = null;
        if (request.getQuotation() != null && !request.getQuotation().isEmpty()){
            IdBaseDomain quotationIdBaseDomain = quotationRepo.findByIdEntity(request.getQuotation());
            if (quotationIdBaseDomain != null){
                quotationDomain = new QuotationDomain();
                quotationDomain.setId(quotationIdBaseDomain.getId());
            }
        }
        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            customerDomain = customerRepo.findById(request.getCustomer());
        }
        ProgramDomain programDomain = null;
        if (request.getProgram() != null && !request.getProgram().isEmpty()){
            IdBaseDomain programIdBaseDomain = programRepo.findByIdEntity(request.getProgram());
            if (programIdBaseDomain != null){
                programDomain = new ProgramDomain();
                programDomain.setId(programIdBaseDomain.getId());
            }
        }
        EmployeeDomain employeeDomain = null;
        if (request.getResponsible() != null && !request.getResponsible().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getResponsible());
            if (employeeIdBaseDomain != null){
                employeeDomain = new EmployeeDomain();
                employeeDomain.setId(employeeIdBaseDomain.getId());
            }
        }
        IdiomDomain idiomDomain = null;
        if (request.getIdiom() != null && !request.getIdiom().isEmpty()){
            IdBaseDomain idiomIdBaseDomain = idiomRepo.findByIdEntity(request.getIdiom());
            if (idiomIdBaseDomain != null){
                idiomDomain = new IdiomDomain();
                idiomDomain.setId(idiomIdBaseDomain.getId());
            }
        }

        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            IdBaseDomain countryIdBaseDomain = countryRepo.findByIdEntity(request.getCountry());
            if (countryIdBaseDomain != null){
                countryDomain = new CountryDomain();
                countryDomain.setId(countryIdBaseDomain.getId());
            }
        }
        RegisterGroupDomain registerGroupDomain = request.to(programDomain, quotationDomain, customerDomain, idiomDomain, employeeDomain, countryDomain);
        registerGroupDomain.setIdentify(old.getIdentify());
        registerGroupDomain.setStateRegisterGroup(old.getStateRegisterGroup());
        registerGroupDomain.setDateAperture(old.getDateAperture());
        registerGroupDomain.setInitTour(old.getInitTour());
        registerGroupDomain.setEndTour(old.getEndTour());
        registerGroupDomain.setQuotation(old.getQuotation());
        registerGroupDomain.setApertureWrite(old.getApertureWrite());
        registerGroupDomain.setVersionQuotation(old.getVersionQuotation());
        registerGroupDomain.setEmailPassengerPrincipal(old.getEmailPassengerPrincipal());
        registerGroupDomain.setMobilePassengerPrincipal(old.getMobilePassengerPrincipal());
        registerGroupDomain.setWhatsappPassengerPrincipal(old.getWhatsappPassengerPrincipal());
        RegisterGroupDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerGroupDomain, old));
        return RegisterGroupResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterGroupResponse old = findById(id);
        if (old.getQuotation() != null && old.getQuotation().getId() != null && !old.getQuotation().getId().isEmpty()){
            quotationRepo.deleteById(old.getQuotation().getId());
        }
        repo.deleteById(old.getId());

        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setType(NotificationTypeEnum.QUOTATION_DELETED_BY_REGISTER_GROUP.getName());
        notificationDomain.setRead(false);
        notificationDomain.setDateTime(LocalDateTime.now());
        notificationDomain.setSubject("Se ha eliminado una cotización, ya que el registro de grupo " + old.getIdentify() + " asociado se elimino.");
        notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

        notificationDomain.setUserSource(UserResolver.resolveUser());
        EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
        if (employeeUserSourceDomain != null){
            notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
        }

        EmployeeDomain employeeDomain = null;
        if (old.getQuotation().getEmployee() != null && old.getQuotation().getEmployee().getId() != null && !old.getQuotation().getEmployee().getId().isEmpty()){
            employeeDomain = employeeRepo.findById(old.getQuotation().getEmployee().getId());
        }

        notificationDomain.setEmployeeDestine(employeeDomain);
        EmployeeUserDomain employeeUserDestineDomain = employeeUserRepo.getByEmployee(notificationDomain.getEmployeeDestine().getId());
        if (employeeUserDestineDomain != null){
            notificationDomain.setUserDestine(employeeUserDestineDomain.getUser());
        }
        notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

        ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
        ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

        notificationDomain.setIdObjectSource(old.getQuotation().getId());
        notificationDomain.setNameObjectSource(old.getQuotation().getName());
        notificationDomain.setIdentifyObjectSource(old.getQuotation().getIdentify());
        notificationDomain.setObjectEntitySource(objectDomainSource);

        notificationDomain.setIdObjectDestine(old.getId());
        notificationDomain.setNameObjectDestine(old.getName());
        notificationDomain.setObjectEntityDestine(objectDomainDestine);

        notificationRepo.create(notificationDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAll() {
        return repo.findAll(false).stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterGroupResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterGroupDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        RegisterGroupResponse result = RegisterGroupResponse.from(old);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            List<ItineraryItemDomain> listItems = itineraryItemRepo.searchAllByRegisterGroupSimpleDomain(id);
            if (listItems != null && !listItems.isEmpty()){
                result.setEditedAdvanced(true);
            }else{
                result.setEditedAdvanced(false);
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterGroupSearchResponse searchAll(RegisterGroupSearchRequest registerGroupSearchRequest) {
       PageDTO<RegisterGroupDomain> pageDTO = repo.searchAll(registerGroupSearchRequest);
       RegisterGroupSearchResponse bankSearchRespose = new RegisterGroupSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterGroupResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterGroupSearchResponse searchFilterAll(RegisterGroupSearchFilterRequest registerGroupSearchFilterRequest) {
        PageDTO<RegisterGroupDomain> pageDTO = repo.searchAllFilter(registerGroupSearchFilterRequest);
        RegisterGroupSearchResponse bankSearchRespose = new RegisterGroupSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterGroupResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterGroupResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterGroupDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No register group found with name '%s'.".formatted(name));
        }
        return RegisterGroupResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterGroupResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterGroupDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No register group found with identify '%s'.".formatted(identify));
        }
        return RegisterGroupResponse.from(old);
    }

    @Override
    @Transactional
    public PassengerGroupResponse addPassengerGroup(PassengerGroupCreateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        PassengerGroupResponse result = passengerGroupUseCase.create(request, registerGroupDomain);
        if (request.getPrincipal()){
            registerGroupDomain.setMobilePassengerPrincipal(request.getContactInformation().getMobile());
            registerGroupDomain.setEmailPassengerPrincipal(request.getContactInformation().getEmail());
            registerGroupDomain.setWhatsappPassengerPrincipal(request.getSocialMediaInformation().getWhatsApp());
            repo.edit(registerGroupDomain);
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return result;
    }

    @Override
    @Transactional
    public PassengerGroupResponse editPassengerGroup(String id, PassengerGroupUpdateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        PassengerGroupResponse result = passengerGroupUseCase.edit(id, request, registerGroupDomain);
        if (request.getPrincipal()){
            registerGroupDomain.setMobilePassengerPrincipal(request.getContactInformation().getMobile());
            registerGroupDomain.setEmailPassengerPrincipal(request.getContactInformation().getEmail());
            registerGroupDomain.setWhatsappPassengerPrincipal(request.getSocialMediaInformation().getWhatsApp());
            repo.edit(registerGroupDomain);
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return result;
    }

    @Override
    @Transactional
    public void deletePassengerGroup(String id, String idRegisterGroup) {
        RegisterGroupResponse old = findById(idRegisterGroup);
        passengerGroupUseCase.deleteById(id);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerGroupResponse> findAllPassengerGroupByRegisterGroup(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return passengerGroupUseCase.searchAllByRegisterGroup(idRegisterGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public PassengerGroupResponse findByIdAndRegisterGroup(String id, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return passengerGroupUseCase.searchByRegisterGroupAndPassengerGroup(idRegisterGroup, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryItemResponse> findAllItineraryItemByRegisterGroup(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return itineraryItemUseCase.searchAllByRegisterGroup(idRegisterGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItineraryDateResponse> findAllItineraryDateByRegisterGroup(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return itineraryDateUseCase.searchAllByRegisterGroup(idRegisterGroup);
    }

    @Override
    @Transactional
    public ItineraryItemResponse addItinerary(ItineraryItemCreateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        if (request.getSupplierType().equals(supplierTypeDomainLodging.getId())){
            RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingLodgingDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (request.getSupplierType().equals(supplierTypeDomainOperator.getId())){
            RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingOperatorDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (request.getSupplierType().equals(supplierTypeDomainRestaurant.getId())){
            RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingRestaurantDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (request.getSupplierType().equals(supplierTypeDomainLodge.getId())){
            RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingLodgerDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        }else if (request.getSupplierType().equals(supplierTypeDomainGlamping.getId())){
            RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingGlampingDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        }
        ItineraryItemResponse resultCreate = itineraryItemUseCase.create(request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return resultCreate;
    }

    @Override
    @Transactional
    public void addItineraryMultiple(ItineraryItemCreateRequest request, String idRegisterGroup) {
        RegisterGroupDomain registerGroupDomain = repo.findByIdRegisterGroupCaseCreateItinerary(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        itineraryItemUseCase.createMultiple(request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void addItineraryAdvanced(ItineraryServiceAdvancedCreateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        itineraryItemUseCase.createAdvanced(request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void editItineraryAdvanced(ItineraryServiceAdvancedUpdateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        Map<String, String> mapSupplier = new HashMap<>();
        if (request.getSuppliers() != null && !request.getSuppliers().isEmpty()){
            for (ContainerSupplierAdvancedUpdateRequest containerSupplierAdvancedUpdateRequest : request.getSuppliers()){
                if (!mapSupplier.containsKey(containerSupplierAdvancedUpdateRequest.getSupplier().getId())){
                    mapSupplier.put(containerSupplierAdvancedUpdateRequest.getSupplier().getId(), containerSupplierAdvancedUpdateRequest.getModeUpdateServiceAdvancedFlag());
                }
            }
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryItemDomain> listValidation = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
        listValidation = listValidation.stream().collect(Collectors.toList());
        for(ItineraryItemDomain itemDomain : listValidation){
            if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                if (mapSupplier.containsKey(itemDomain.getSupplier().getId())){
                    String mode = mapSupplier.get(itemDomain.getSupplier().getId());
                    if (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName())) {
                        RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                        if (registerBookingLodgingDomainExist != null){
                            throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                        }
                    }
                }else{
                    RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                    if (registerBookingLodgingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                    }
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                if (mapSupplier.containsKey(itemDomain.getSupplier().getId())) {
                    String mode = mapSupplier.get(itemDomain.getSupplier().getId());
                    if (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName())) {
                        RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                        if (registerBookingOperatorDomainExist != null){
                            throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                        }
                    }
                }else{
                    RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                    if (registerBookingOperatorDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                    }
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                if (mapSupplier.containsKey(itemDomain.getSupplier().getId())) {
                    String mode = mapSupplier.get(itemDomain.getSupplier().getId());
                    if (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName())) {
                        RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                        if (registerBookingRestaurantDomainExist != null){
                            throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                        }
                    }
                }else{
                    RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                    if (registerBookingRestaurantDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                    }
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                if (mapSupplier.containsKey(itemDomain.getSupplier().getId())) {
                    String mode = mapSupplier.get(itemDomain.getSupplier().getId());
                    if (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName())) {
                        RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                        if (registerBookingLodgerDomainExist != null){
                            throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                        }
                    }
                }else{
                    RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                    if (registerBookingLodgerDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                    }
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                if (mapSupplier.containsKey(itemDomain.getSupplier().getId())) {
                    String mode = mapSupplier.get(itemDomain.getSupplier().getId());
                    if (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName())) {
                        RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                        if (registerBookingGlampingDomainExist != null){
                            throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                        }
                    }
                }else{
                    RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                    if (registerBookingGlampingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                    }
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                if (mapSupplier.containsKey(itemDomain.getSupplier().getId())) {
                    String mode = mapSupplier.get(itemDomain.getSupplier().getId());
                    if (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName())) {
                        RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                        if (registerBookingInstitutionDomainExist != null){
                            throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                        }
                    }
                }else{
                    RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                    if (registerBookingInstitutionDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_ITINERARY_ADVANCED);
                    }
                }
            }
        }

        // Remove Supplier Deleted in Advanced Itinerary

        for (ItineraryItemDomain itineraryItemDomain : listValidation){
            if (!mapSupplier.containsKey(itineraryItemDomain.getSupplier().getId())){
                itineraryItemRepo.deleteById(itineraryItemDomain.getId());
            }
        }

        // Remove Itinerary

        for (ItineraryItemDomain itineraryItemDomain : listValidation){
            if (mapSupplier.containsKey(itineraryItemDomain.getSupplier().getId())){
                String mode = mapSupplier.get(itineraryItemDomain.getSupplier().getId());
                if (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName())) {
                    itineraryItemRepo.deleteById(itineraryItemDomain.getId());
                }
            }
        }

        // Remove Itinerary Empty suppliers
        if (request.getSuppliers() != null && !request.getSuppliers().isEmpty()){
            for (ContainerSupplierAdvancedUpdateRequest containerSupplierAdvancedUpdateRequest : request.getSuppliers()){
                if (containerSupplierAdvancedUpdateRequest.getServices() == null || (containerSupplierAdvancedUpdateRequest.getServices() != null && containerSupplierAdvancedUpdateRequest.getServices().isEmpty())){
                    for (ItineraryItemDomain itineraryItemDomain : listValidation){
                        String mode = mapSupplier.get(itineraryItemDomain.getSupplier().getId());
                        if ( mapSupplier.containsKey(itineraryItemDomain.getSupplier().getId())
                                && containerSupplierAdvancedUpdateRequest.getSupplier().getId().equals(itineraryItemDomain.getSupplier().getId())
                                && (mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITHOUT_CHANGE.getName()) || mode.equals(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITH_CHANGE.getName()))
                        ){
                            itineraryItemRepo.deleteById(itineraryItemDomain.getId());
                        }
                    }
                }
            }
        }

        // Create Itinerary
        itineraryItemUseCase.editAdvanced(request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void addItineraryWithTour(ItineraryTourItemCreateRequest request, String idRegisterGroup) {
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        itineraryItemUseCase.createTour(request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void addItineraryWithTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, String idRegisterGroup) {
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        itineraryItemUseCase.createTourServiceAdditional(request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public ItineraryItemResponse editItinerary(String id, ItineraryItemUpdateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.findById(id);
        if (itineraryItemDomain == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        if (request.getSupplierType().equals(supplierTypeDomainLodging.getId())){
            RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingLodgingDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (request.getSupplierType().equals(supplierTypeDomainOperator.getId())){
            RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingOperatorDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (request.getSupplierType().equals(supplierTypeDomainRestaurant.getId())){
            RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingRestaurantDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (request.getSupplierType().equals(supplierTypeDomainLodge.getId())){
            RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingLodgerDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        }else if (request.getSupplierType().equals(supplierTypeDomainGlamping.getId())){
            RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingGlampingDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        }else if (request.getSupplierType().equals(supplierTypeDomainInstitution.getId())){
            RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, request.getSupplier());
            if (registerBookingInstitutionDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        }
        ItineraryItemResponse resultEdit = itineraryItemUseCase.edit(id, request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return resultEdit;
    }

    @Override
    @Transactional
    public void deleteItinerary(String id, String idRegisterGroup) {
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.findById(id);
        if (itineraryItemDomain == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
            RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.searchByRegisterGroupAndSupplierAndActive(idRegisterGroup, itineraryItemDomain.getSupplier().getId());
            if (registerBookingLodgingDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
            RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.searchByRegisterGroupAndSupplierAndActive(idRegisterGroup, itineraryItemDomain.getSupplier().getId());
            if (registerBookingOperatorDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
            RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.searchByRegisterGroupAndSupplierAndActive(idRegisterGroup, itineraryItemDomain.getSupplier().getId());
            if (registerBookingRestaurantDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
            RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.searchByRegisterGroupAndSupplierAndActive(idRegisterGroup, itineraryItemDomain.getSupplier().getId());
            if (registerBookingLodgerDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (itineraryItemDomain.getSupplierType().equals(supplierTypeDomainGlamping.getId())){
            RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.searchByRegisterGroupAndSupplierAndActive(idRegisterGroup, itineraryItemDomain.getSupplier().getId());
            if (registerBookingGlampingDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        } else if (itineraryItemDomain.getSupplierType().equals(supplierTypeDomainInstitution.getId())){
            RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.searchByRegisterGroupAndSupplierAndActive(idRegisterGroup, itineraryItemDomain.getSupplier().getId());
            if (registerBookingInstitutionDomainExist != null){
                throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
            }
        }
        itineraryItemUseCase.deleteById(id);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemResponse findByItineraryItemIdAndRegisterGroup(String id, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return itineraryItemUseCase.searchByRegisterGroupAndItineraryItem(idRegisterGroup, id);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemResponse findByItineraryItemIdAndRegisterGroupVoucherEgress(String id, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupDomainBase = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupDomainBase == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return itineraryItemUseCase.searchByRegisterGroupAndItineraryItemVoucherEgress(idRegisterGroup, id);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateResponse findByItineraryDateIdAndRegisterGroup(String id, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return itineraryDateUseCase.searchByRegisterGroupAndItineraryDate(idRegisterGroup, id);
    }

    @Override
    @Transactional
    public List<ItineraryDateAllItemResponse> findAllItineraryDateWithItineraryItemByRegisterGroup(String idRegisterGroup) {
        List<ItineraryDateAllItemResponse> result = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseShowItinerary(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseShowItinerary(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                result.add(ItineraryDateAllItemResponse.from(itineraryDateDomain, listItineraryItemResult));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<ItineraryModelViewResponse> findAllItineraryDateWithItineraryItemByRegisterGroupModelView(String idRegisterGroup) {
        List<ItineraryDateAllItemResponse> list = this.findAllItineraryDateWithItineraryItemByRegisterGroup(idRegisterGroup);
        List<ItineraryModelViewResponse> result = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            for (ItineraryDateAllItemResponse itineraryDateAllItemResponse: list
            ) {
                List<ItineraryModelViewResponse> details = new ArrayList<>();
                if (itineraryDateAllItemResponse.getItineraryItems() != null && !itineraryDateAllItemResponse.getItineraryItems().isEmpty()){
                    for (ItineraryItemResponse itineraryItemResponse: itineraryDateAllItemResponse.getItineraryItems()
                    ) {
                        ItineraryModelViewResponse itineraryModelViewResponseChild = ItineraryModelViewResponse.from(
                                itineraryItemResponse.getId(),
                                null,
                                null,
                                null,
                                null,
                                null,
                                itineraryItemResponse.getItineraryDateTime(),
                                itineraryItemResponse.getItineraryDateTimeStr(),
                                itineraryItemResponse.getSupplierType(),
                                itineraryItemResponse.getSupplier(),
                                itineraryItemResponse.getService(),
                                itineraryItemResponse.getPaxTotal(),
                                itineraryItemResponse.getPaxAdultTotal(),
                                itineraryItemResponse.getPaxChildrenTotal(),
                                itineraryItemResponse.getPaxTcTotal(),
                                itineraryItemResponse.getPaxTcLocalTotal(),
                                itineraryItemResponse.getPaxGuideTotal(),
                                itineraryItemResponse.getPrice(),
                                itineraryItemResponse.getPriceAdult(),
                                itineraryItemResponse.getPriceChildren(),
                                itineraryItemResponse.getPriceTc(),
                                itineraryItemResponse.getPriceTcLocal(),
                                itineraryItemResponse.getPriceGuide(),
                                itineraryItemResponse.getObservation(),
                                false,
                                null
                        );
                        details.add(itineraryModelViewResponseChild);
                    }
                }
                ItineraryModelViewResponse itineraryModelViewResponseParent = ItineraryModelViewResponse.from(
                        itineraryDateAllItemResponse.getId(),
                        itineraryDateAllItemResponse.getName(),
                        itineraryDateAllItemResponse.getHasTours(),
                        itineraryDateAllItemResponse.getItemItinerary(),
                        itineraryDateAllItemResponse.getItineraryDate(),
                        itineraryDateAllItemResponse.getItineraryDateStr(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        true,
                        details
                );
                result.add(itineraryModelViewResponseParent);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public RegisterGroupResponse changeState(String id, RegisterGroupChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterGroupDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(id));
        }
        old.setStateRegisterGroup(request.getStateRegisterGroup());
        RegisterGroupDomain editedDomain = repo.edit(old);
        switch (request.getStateRegisterGroup()) {
            case "Opened" -> {
                _changeStateOpened(old, request);
            }
            case "Completed" -> {
                _changeStateCompleted(old, request);
            }
            case "Initialized" -> {
                _changeStateInitialized(old, request);
            }
            case "Finished" -> {
                _changeStateFinished(old, request);
            }
            case "Closed" -> {
                _changeStateClosed(old, request);
            }
            case "Annulled" -> {
                _changeStateAnnulled(old, request);
            }
            case "Review" -> {
                _changeStateReview(old, request);
            }
            default -> {

            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(id);

        return RegisterGroupResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterGroupResponse resendQuotationToBooking(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findByIdentify(identify);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with identify '%s'.".formatted(identify));
        }

        Integer cantDaysCompleted = 0;
        List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDateValidation = itineraryDateCrmRepo.searchAllByQuotationSimpleDomainCaseShowItinerary(registerGroupDomain.getQuotation().getId());
        if (listItineraryDateValidation != null && !listItineraryDateValidation.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDateResult = listItineraryDateValidation.stream().collect(Collectors.toList());
            for (com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain> listItineraryItem = itineraryItemCrmRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseShowItinerary(itineraryDateDomain.getId(), registerGroupDomain.getQuotation().getId());
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    cantDaysCompleted = cantDaysCompleted + 1;
                }
            }
        }
        if (!registerGroupDomain.getQuotation().getCantDayTour().equals(cantDaysCompleted)){
            throw new BadRequestException(MessagesValidation.VALIDATION_QUOTATION_NOT_COMPLETED);
        }
        List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTourValidation = costTourCrmRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(registerGroupDomain.getQuotation().getId());
        if (listCostTourValidation != null && !listCostTourValidation.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTourResult = listCostTourValidation.stream().collect(Collectors.toList());
            for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain costTourDomain: listCostTourResult
            ) {
                if (costTourDomain.getSubTotalAmount() == 0 && (costTourDomain.getTour().getWithoutCost() == null || !costTourDomain.getTour().getWithoutCost() )){
                    throw new BadRequestException(MessagesValidation.VALIDATION_QUOTATION_NOT_COST_TOUR_REGISTERED);
                }
            }
        }
        // Delete All By Register Group

        List<CostTourDomain> listCostTourDelete = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<CostTourServiceItemDomain> listCostTourServiceItemDelete = costTourServiceItemRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<ItineraryDateDomain> listItineraryDateDelete = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<ItineraryItemDomain> listItineraryItemDelete = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<PassengerGroupDomain> listPassengerGroupDelete = passengerGroupRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());

        if (listCostTourDelete != null && !listCostTourDelete.isEmpty()){
            for (CostTourDomain costTourDomain: listCostTourDelete){
                costTourRepo.deleteById(costTourDomain.getId());
            }
        }

        if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItemDelete){
                costTourItemRepo.deleteById(costTourItemDomain.getId());
            }
        }

        if (listCostTourServiceItemDelete != null && !listCostTourServiceItemDelete.isEmpty()){
            for (CostTourServiceItemDomain costTourServiceItemDomain: listCostTourServiceItemDelete){
                costTourServiceItemRepo.deleteById(costTourServiceItemDomain.getId());
            }
        }

        if (listItineraryDateDelete != null && !listItineraryDateDelete.isEmpty()){
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateDelete){
                itineraryDateRepo.deleteById(itineraryDateDomain.getId());
            }
        }

        if (listItineraryItemDelete != null && !listItineraryItemDelete.isEmpty()){
            for (ItineraryItemDomain itineraryItemDomain: listItineraryItemDelete){
                itineraryItemRepo.deleteById(itineraryItemDomain.getId());
            }
        }

        if (listPassengerGroupDelete != null && !listPassengerGroupDelete.isEmpty()){
            for (PassengerGroupDomain passengerGroupDomain: listPassengerGroupDelete){
                passengerGroupRepo.deleteById(passengerGroupDomain.getId());
            }
        }

        registerGroupDomain.setInitTour(registerGroupDomain.getQuotation().getDateInitTour());
        registerGroupDomain.setEndTour(registerGroupDomain.getQuotation().getDateEndTour());
        registerGroupDomain.setProgram(registerGroupDomain.getQuotation().getProgram());
        registerGroupDomain.setIdiom(registerGroupDomain.getQuotation().getIdiom());
        registerGroupDomain.setCantPaxAdult(registerGroupDomain.getQuotation().getPaxAdult());
        registerGroupDomain.setCantPaxChildren(registerGroupDomain.getQuotation().getPaxChild());
        registerGroupDomain.setTC(registerGroupDomain.getQuotation().getPaxTc());
        registerGroupDomain.setTCLocal(registerGroupDomain.getQuotation().getPaxTcLocal());
        registerGroupDomain.setTotalPax(registerGroupDomain.getQuotation().getPaxTotal());
        registerGroupDomain.setDays(registerGroupDomain.getQuotation().getCantDayTour());
        // registerGroupDomain.setApertureWrite(true);
        // registerGroupDomain.setDateAperture(LocalDate.now());
        registerGroupDomain.setCountry(registerGroupDomain.getQuotation().getCountry());
        registerGroupDomain.setLink(registerGroupDomain.getQuotation().getLinkGDrive());
        registerGroupDomain.setLinkBitrix(registerGroupDomain.getQuotation().getLinkBitrix());
        registerGroupDomain.setLinkWetravel(registerGroupDomain.getQuotation().getLinkWetravel());
        registerGroupDomain.setEmailPassengerPrincipal(registerGroupDomain.getQuotation().getEmailPassengerPrincipal());
        registerGroupDomain.setWhatsappPassengerPrincipal(registerGroupDomain.getQuotation().getWhatsappPassengerPrincipal());
        registerGroupDomain.setMobilePassengerPrincipal(registerGroupDomain.getQuotation().getMobilePassengerPrincipal());
        registerGroupDomain = repo.edit(registerGroupDomain);

        List<ItineraryItemDomain> listItineraryItemUpdateCostTour = new ArrayList<>();

        // Generate Itinerary Date and Item
        List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDate = itineraryDateCrmRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(registerGroupDomain.getQuotation().getId());
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain itineraryDateDomainSource: listItineraryDateResult
            ) {
                ItineraryDateDomain itineraryDateDomainDestination = new ItineraryDateDomain();
                itineraryDateDomainDestination.setName(itineraryDateDomainSource.getName());
                itineraryDateDomainDestination.setDescription(itineraryDateDomainSource.getDescription());
                itineraryDateDomainDestination.setIdentify(itineraryDateDomainSource.getIdentify());
                itineraryDateDomainDestination.setItineraryDate(itineraryDateDomainSource.getItineraryDate());
                itineraryDateDomainDestination.setIndex(itineraryDateDomainSource.getIndex());
                itineraryDateDomainDestination.setItemItinerary(itineraryDateDomainSource.getItemItinerary());
                itineraryDateDomainDestination.setRegisterGroup(registerGroupDomain);
                itineraryDateDomainDestination.setTours(itineraryDateDomainSource.getTours());
                itineraryDateDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                itineraryDateDomainDestination.setIdItineraryDateQuotation(itineraryDateDomainSource.getId());

                ItineraryDateDomain resultItineraryDateDomainDestination = itineraryDateRepo.create(itineraryDateDomainDestination);

                List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain> listItineraryItem = itineraryItemCrmRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(itineraryDateDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();

                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain itineraryItemDomainSource: listItineraryItemResult
                    ) {
                        ItineraryItemDomain itineraryItemDomainDestination = new ItineraryItemDomain();
                        itineraryItemDomainDestination.setName(itineraryItemDomainSource.getName());
                        itineraryItemDomainDestination.setDescription(itineraryItemDomainSource.getDescription());
                        itineraryItemDomainDestination.setIdentify(itineraryItemDomainSource.getIdentify());
                        itineraryItemDomainDestination.setServiceGroup(itineraryItemDomainSource.getServiceGroup());
                        itineraryItemDomainDestination.setObservation(itineraryItemDomainSource.getObservation());
                        itineraryItemDomainDestination.setItineraryDateTime(itineraryItemDomainSource.getItineraryDateTime());
                        itineraryItemDomainDestination.setPaxTotal(itineraryItemDomainSource.getPaxTotal());
                        itineraryItemDomainDestination.setPrice(itineraryItemDomainSource.getPrice());
                        itineraryItemDomainDestination.setPaxAdultTotal(itineraryItemDomainSource.getPaxAdultTotal());
                        itineraryItemDomainDestination.setPriceAdult(itineraryItemDomainSource.getPriceAdult());
                        itineraryItemDomainDestination.setPaxChildrenTotal(itineraryItemDomainSource.getPaxChildrenTotal());
                        itineraryItemDomainDestination.setPriceChildren(itineraryItemDomainSource.getPriceChildren());
                        itineraryItemDomainDestination.setPaxTcTotal(itineraryItemDomainSource.getPaxTcTotal());
                        itineraryItemDomainDestination.setPriceTc(itineraryItemDomainSource.getPriceTc());
                        itineraryItemDomainDestination.setPaxTcLocalTotal(itineraryItemDomainSource.getPaxTcLocalTotal());
                        itineraryItemDomainDestination.setPriceTcLocal(itineraryItemDomainSource.getPriceTcLocal());
                        itineraryItemDomainDestination.setPaxGuideTotal(itineraryItemDomainSource.getPaxGuideTotal());
                        itineraryItemDomainDestination.setPriceGuide(itineraryItemDomainSource.getPriceGuide());

                        itineraryItemDomainDestination.setItineraryDate(resultItineraryDateDomainDestination);
                        itineraryItemDomainDestination.setIndex(itineraryItemDomainSource.getIndex());
                        itineraryItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        itineraryItemDomainDestination.setSupplierType(itineraryItemDomainSource.getSupplierType());
                        itineraryItemDomainDestination.setSupplier(itineraryItemDomainSource.getSupplier());
                        itineraryItemDomainDestination.setService(itineraryItemDomainSource.getService());
                        itineraryItemDomainDestination.setDepartmentRegion(itineraryItemDomainSource.getDepartmentRegion());
                        itineraryItemDomainDestination.setTour(itineraryItemDomainSource.getTour());
                        itineraryItemDomainDestination.setCustomer(registerGroupDomain.getCustomer());
                        // Supplier Type Lodging

                        itineraryItemDomainDestination.setLodgingCantSwb(itineraryItemDomainSource.getLodgingCantSwb());
                        itineraryItemDomainDestination.setLodgingCantMat(itineraryItemDomainSource.getLodgingCantMat());
                        itineraryItemDomainDestination.setLodgingCantTwin(itineraryItemDomainSource.getLodgingCantTwin());
                        itineraryItemDomainDestination.setLodgingCantTrp(itineraryItemDomainSource.getLodgingCantTrp());

                        itineraryItemDomainDestination.setLodgingCantBreakfast(itineraryItemDomainSource.getLodgingCantBreakfast());
                        itineraryItemDomainDestination.setLodgingCantLunch(itineraryItemDomainSource.getLodgingCantLunch());
                        itineraryItemDomainDestination.setLodgingCantDinner(itineraryItemDomainSource.getLodgingCantDinner());
                        itineraryItemDomainDestination.setLodgingCantBoxLunch(itineraryItemDomainSource.getLodgingCantBoxLunch());

                        itineraryItemDomainDestination.setLodgingCantNight(itineraryItemDomainSource.getLodgingCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastTcGuide(itineraryItemDomainSource.getLodgingCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setLodgingCantLunchTcGuide(itineraryItemDomainSource.getLodgingCantLunchTcGuide());
                        itineraryItemDomainDestination.setLodgingCantDinnerTcGuide(itineraryItemDomainSource.getLodgingCantDinnerTcGuide());
                        itineraryItemDomainDestination.setLodgingCantBoxLunchTcGuide(itineraryItemDomainSource.getLodgingCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setLodgingCantSwbTcGuide(itineraryItemDomainSource.getLodgingCantSwbTcGuide());
                        itineraryItemDomainDestination.setLodgingCantTwinTcGuide(itineraryItemDomainSource.getLodgingCantTwinTcGuide());

                        itineraryItemDomainDestination.setLodgingTcCantNight(itineraryItemDomainSource.getLodgingTcCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastTcLocalGuide(itineraryItemDomainSource.getLodgingCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantLunchTcLocalGuide(itineraryItemDomainSource.getLodgingCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantDinnerTcLocalGuide(itineraryItemDomainSource.getLodgingCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getLodgingCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setLodgingCantSwbTcLocalGuide(itineraryItemDomainSource.getLodgingCantSwbTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantTwinTcLocalGuide(itineraryItemDomainSource.getLodgingCantTwinTcLocalGuide());

                        itineraryItemDomainDestination.setLodgingTcLocalCantNight(itineraryItemDomainSource.getLodgingTcLocalCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastGuide(0);
                        itineraryItemDomainDestination.setLodgingCantLunchGuide(0);
                        itineraryItemDomainDestination.setLodgingCantDinnerGuide(0);

                        itineraryItemDomainDestination.setLodgingCantSwbGuide(0);
                        itineraryItemDomainDestination.setLodgingCantTwinGuide(0);

                        itineraryItemDomainDestination.setLodgingGuideCantNight(0);

                        itineraryItemDomainDestination.setLodgingForOperator(itineraryItemDomainSource.getLodgingForOperator());
                        // Supplier Type Lodge

                        itineraryItemDomainDestination.setLodgeCantSwb(itineraryItemDomainSource.getLodgeCantSwb());
                        itineraryItemDomainDestination.setLodgeCantMat(itineraryItemDomainSource.getLodgeCantMat());
                        itineraryItemDomainDestination.setLodgeCantTwin(itineraryItemDomainSource.getLodgeCantTwin());
                        itineraryItemDomainDestination.setLodgeCantTrp(itineraryItemDomainSource.getLodgeCantTrp());

                        itineraryItemDomainDestination.setLodgeCantBreakfast(itineraryItemDomainSource.getLodgeCantBreakfast());
                        itineraryItemDomainDestination.setLodgeCantLunch(itineraryItemDomainSource.getLodgeCantLunch());
                        itineraryItemDomainDestination.setLodgeCantDinner(itineraryItemDomainSource.getLodgeCantDinner());
                        itineraryItemDomainDestination.setLodgeCantBoxLunch(itineraryItemDomainSource.getLodgeCantBoxLunch());

                        itineraryItemDomainDestination.setLodgeCantNight(itineraryItemDomainSource.getLodgeCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastTcGuide(itineraryItemDomainSource.getLodgeCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setLodgeCantLunchTcGuide(itineraryItemDomainSource.getLodgeCantLunchTcGuide());
                        itineraryItemDomainDestination.setLodgeCantDinnerTcGuide(itineraryItemDomainSource.getLodgeCantDinnerTcGuide());
                        itineraryItemDomainDestination.setLodgeCantBoxLunchTcGuide(itineraryItemDomainSource.getLodgeCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setLodgeCantSwbTcGuide(itineraryItemDomainSource.getLodgeCantSwbTcGuide());
                        itineraryItemDomainDestination.setLodgeCantTwinTcGuide(itineraryItemDomainSource.getLodgeCantTwinTcGuide());

                        itineraryItemDomainDestination.setLodgeTcCantNight(itineraryItemDomainSource.getLodgeTcCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastTcLocalGuide(itineraryItemDomainSource.getLodgeCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantLunchTcLocalGuide(itineraryItemDomainSource.getLodgeCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantDinnerTcLocalGuide(itineraryItemDomainSource.getLodgeCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getLodgeCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setLodgeCantSwbTcLocalGuide(itineraryItemDomainSource.getLodgeCantSwbTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantTwinTcLocalGuide(itineraryItemDomainSource.getLodgeCantTwinTcLocalGuide());

                        itineraryItemDomainDestination.setLodgeTcLocalCantNight(itineraryItemDomainSource.getLodgeTcLocalCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastGuide(0);
                        itineraryItemDomainDestination.setLodgeCantLunchGuide(0);
                        itineraryItemDomainDestination.setLodgeCantDinnerGuide(0);

                        itineraryItemDomainDestination.setLodgeCantSwbGuide(0);
                        itineraryItemDomainDestination.setLodgeCantTwinGuide(0);

                        itineraryItemDomainDestination.setLodgeGuideCantNight(0);

                        itineraryItemDomainDestination.setLodgePvt(itineraryItemDomainSource.getLodgePvt());
                        itineraryItemDomainDestination.setLodgeSic(itineraryItemDomainSource.getLodgeSic());

                        // Supplier Type Restaurant

                        itineraryItemDomainDestination.setRestaurantCantBreakfast(itineraryItemDomainSource.getRestaurantCantBreakfast());
                        itineraryItemDomainDestination.setRestaurantCantLunch(itineraryItemDomainSource.getRestaurantCantLunch());
                        itineraryItemDomainDestination.setRestaurantCantDinner(itineraryItemDomainSource.getRestaurantCantDinner());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunch(itineraryItemDomainSource.getRestaurantCantBoxLunch());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastTcGuide(itineraryItemDomainSource.getRestaurantCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchTcGuide(itineraryItemDomainSource.getRestaurantCantLunchTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerTcGuide(itineraryItemDomainSource.getRestaurantCantDinnerTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchTcGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastTcLocalGuide(itineraryItemDomainSource.getRestaurantCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchTcLocalGuide(itineraryItemDomainSource.getRestaurantCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerTcLocalGuide(itineraryItemDomainSource.getRestaurantCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastGuide(itineraryItemDomainSource.getRestaurantCantBreakfastGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchGuide(itineraryItemDomainSource.getRestaurantCantLunchGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerGuide(itineraryItemDomainSource.getRestaurantCantDinnerGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastDrive(itineraryItemDomainSource.getRestaurantCantBreakfastDrive());
                        itineraryItemDomainDestination.setRestaurantCantLunchDrive(itineraryItemDomainSource.getRestaurantCantLunchDrive());
                        itineraryItemDomainDestination.setRestaurantCantDinnerDrive(itineraryItemDomainSource.getRestaurantCantDinnerDrive());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchDrive(itineraryItemDomainSource.getRestaurantCantBoxLunchDrive());

                        // Supplier Type Operator

                        itineraryItemDomainDestination.setOperatorPvt(itineraryItemDomainSource.getOperatorPvt());
                        itineraryItemDomainDestination.setOperatorSic(itineraryItemDomainSource.getOperatorSic());

                        itineraryItemDomainDestination.setLodgingForOperator(itineraryItemDomainSource.getLodgingForOperator());

                        itineraryItemDomainDestination.setLodgingForOperatorStr(itineraryItemDomainSource.getLodgingForOperatorStr());

                        itineraryItemDomainDestination.setIdItineraryItemQuotation(itineraryItemDomainSource.getId());

                        itineraryItemDomainDestination.setStateItineraryService(StateBookingEnum.DRAFT.getName());
                        itineraryItemDomainDestination.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());

                        itineraryItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        listItineraryItemUpdateCostTour.add(itineraryItemRepo.create(itineraryItemDomainDestination));
                    }
                }
            }
        }
        // Generate Passenger Group
        List<PassengerDomain> listPassenger = passengerRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(registerGroupDomain.getQuotation().getId());
        if (listPassenger != null && !listPassenger.isEmpty()) {
            List<PassengerDomain> listPassengerResult = listPassenger.stream().collect(Collectors.toList());
            for (PassengerDomain passengerDomainSource: listPassengerResult
            ) {
                PassengerGroupDomain passengerGroupDomainDestination = new PassengerGroupDomain();
                passengerGroupDomainDestination.setName(passengerDomainSource.getName());
                passengerGroupDomainDestination.setDescription(passengerDomainSource.getDescription());
                passengerGroupDomainDestination.setIdentify(passengerDomainSource.getIdentify());
                passengerGroupDomainDestination.setSocialMediaInformation(passengerDomainSource.getSocialMediaInformation());
                passengerGroupDomainDestination.setWebSiteInformation(passengerDomainSource.getWebSiteInformation());
                passengerGroupDomainDestination.setContactInformation(passengerDomainSource.getContactInformation());
                passengerGroupDomainDestination.setAddress(passengerDomainSource.getAddress());
                passengerGroupDomainDestination.setDateInit(passengerDomainSource.getDateInit());
                passengerGroupDomainDestination.setDateEnd(passengerDomainSource.getDateEnd());
                passengerGroupDomainDestination.setDocument(passengerDomainSource.getDocument());
                passengerGroupDomainDestination.setObservation(passengerDomainSource.getObservation());
                passengerGroupDomainDestination.setPrincipal(passengerDomainSource.getPrincipal());
                passengerGroupDomainDestination.setImageUrl(passengerDomainSource.getImageUrl());
                passengerGroupDomainDestination.setTitlePerson(passengerDomainSource.getTitlePerson());
                passengerGroupDomainDestination.setTypePassengerTcBooking(passengerDomainSource.getTypePassengerTcBooking());
                passengerGroupDomainDestination.setPerson(passengerDomainSource.getPerson());
                passengerGroupDomainDestination.setNationality(passengerDomainSource.getNationality());
                passengerGroupDomainDestination.setDocumentType(passengerDomainSource.getDocumentType());
                passengerGroupDomainDestination.setPassengerType(passengerDomainSource.getPassengerType());
                passengerGroupDomainDestination.setRegisterGroup(registerGroupDomain);
                passengerGroupDomainDestination.setIndex(passengerDomainSource.getIndex());
                passengerGroupDomainDestination.setIdPassengerQuotation(passengerDomainSource.getId());

                passengerGroupDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                passengerGroupRepo.create(passengerGroupDomainDestination);
            }
        }
        // Generate Cost Tour
        List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTour = costTourCrmRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(registerGroupDomain.getQuotation().getId());
        if (listCostTour != null && !listCostTour.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTourResult = listCostTour.stream().collect(Collectors.toList());
            for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain costTourDomainSource: listCostTourResult
            ) {
                Map<String, CostTourItemDomain> mapCostTourItemService = new HashMap<>();

                CostTourDomain costTourDomainDestination = new CostTourDomain();
                costTourDomainDestination.setName(costTourDomainSource.getName());
                costTourDomainDestination.setDescription(costTourDomainSource.getDescription());
                costTourDomainDestination.setIdentify(costTourDomainSource.getIdentify());
                ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.getByRegisterGroupAndItemItinerary(registerGroupDomain.getId(), costTourDomainSource.getItineraryDate().getItemItinerary());
                costTourDomainDestination.setItineraryDate(itineraryDateDomain);
                costTourDomainDestination.setNumMaxPax(costTourDomainSource.getNumMaxPax());
                costTourDomainDestination.setRegisterGroup(registerGroupDomain);
                costTourDomainDestination.setSubTotalAmount(costTourDomainSource.getSubTotalAmount());
                costTourDomainDestination.setTour(costTourDomainSource.getTour());
                costTourDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                CostTourDomain costTourDomainDestinationResult = costTourRepo.create(costTourDomainDestination);

                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain> listCostTourItem = costTourItemCrmRepo.searchAllByCostTourAndQuotationSimpleDomainCaseResendQuotationToBooking(costTourDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain> listCostTourItemResult = new ArrayList<>();
                if (listCostTourItem != null && !listCostTourItem.isEmpty()) {
                    listCostTourItemResult = listCostTourItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain costTourItemDomainSource : listCostTourItemResult
                    ) {
                        CostTourItemDomain costTourItemDomainDestination = new CostTourItemDomain();
                        costTourItemDomainDestination.setName(costTourItemDomainSource.getName());
                        costTourItemDomainDestination.setDescription(costTourItemDomainSource.getDescription());
                        costTourItemDomainDestination.setIdentify(costTourItemDomainSource.getIdentify());
                        costTourItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        costTourItemDomainDestination.setItineraryDate(itineraryDateDomain);
                        costTourItemDomainDestination.setTour(costTourItemDomainSource.getTour());
                        costTourItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        costTourItemDomainDestination.setPassengerType(costTourItemDomainSource.getPassengerType());
                        costTourItemDomainDestination.setCantPax(costTourItemDomainSource.getCantPax());
                        costTourItemDomainDestination.setPricePax(costTourItemDomainSource.getPricePax());

                        costTourItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        costTourItemDomainDestination = costTourItemRepo.create(costTourItemDomainDestination);
                        if (costTourItemDomainSource.getCostTourServiceItem() != null){
                            mapCostTourItemService.put(costTourItemDomainSource.getCostTourServiceItem().getId(), costTourItemDomainDestination);
                        }
                    }
                }

                List<ItineraryItemDomain>  tempForUpdates = listItineraryItemUpdateCostTour.stream()
                        .filter(itineraryItemDomain -> _matchesCostTourAndItineraryDate(itineraryItemDomain, costTourDomainDestinationResult))
                        .collect(Collectors.toList());
                if (tempForUpdates != null && !tempForUpdates.isEmpty()){
                    for (ItineraryItemDomain itemTempForUpdate: tempForUpdates){
                        itemTempForUpdate.setCostTour(costTourDomainDestinationResult);
                        itineraryItemRepo.edit(itemTempForUpdate);
                    }
                }

                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain> listCostTourServiceItem = costTourServiceItemCrmRepo.searchAllByCostTourAndQuotationCaseResendQuotationToBooking(costTourDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain> listCostTourServiceItemResult = new ArrayList<>();
                if (listCostTourServiceItem != null && !listCostTourServiceItem.isEmpty()) {
                    listCostTourServiceItemResult = listCostTourServiceItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain costTourServiceItemDomainSource : listCostTourServiceItemResult
                    ) {
                        CostTourServiceItemDomain costTourServiceItemDomainDestination = new CostTourServiceItemDomain();
                        costTourServiceItemDomainDestination.setName(costTourServiceItemDomainSource.getName());
                        costTourServiceItemDomainDestination.setDescription(costTourServiceItemDomainSource.getDescription());
                        costTourServiceItemDomainDestination.setIdentify(costTourServiceItemDomainSource.getIdentify());
                        costTourServiceItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        ItineraryDateDomain itineraryDateDomain1 = itineraryDateRepo.getByRegisterGroupAndItemItinerary(registerGroupDomain.getId(), costTourServiceItemDomainSource.getItineraryDate().getItemItinerary());
                        costTourServiceItemDomainDestination.setItineraryDate(itineraryDateDomain1);
                        costTourServiceItemDomainDestination.setTour(costTourDomainDestinationResult.getTour());
                        costTourServiceItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        costTourServiceItemDomainDestination.setSupplierType(costTourServiceItemDomainSource.getSupplierType());
                        costTourServiceItemDomainDestination.setSupplier(costTourServiceItemDomainSource.getSupplier());
                        costTourServiceItemDomainDestination.setService(costTourServiceItemDomainSource.getService());
                        costTourServiceItemDomainDestination.setObservation(costTourServiceItemDomainSource.getObservation());

                        costTourServiceItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        costTourServiceItemDomainDestination = costTourServiceItemRepo.create(costTourServiceItemDomainDestination);

                        if (mapCostTourItemService.containsKey(costTourServiceItemDomainSource.getId())){
                            mapCostTourItemService.get(costTourServiceItemDomainSource.getId()).setCostTourServiceItem(costTourServiceItemDomainDestination);
                            costTourItemRepo.edit(mapCostTourItemService.get(costTourServiceItemDomainSource.getId()));
                        }
                    }
                }
            }
        }

        ChangeRequestDomain changeRequestActive = changeRequestModelRepo.findByQuotationAndRegisterGroupAndActive(registerGroupDomain.getQuotation().getId(), registerGroupDomain.getId());
        if (changeRequestActive != null){
            if (changeRequestActive.getStatus().equals(ChangeRequestStatusEnum.ACCEPTED.getName())){
                changeRequestActive.setStatus(ChangeRequestStatusEnum.REVIEW.getName());
                NotificationDomain notificationDomain = new NotificationDomain();
                notificationDomain.setType(NotificationTypeEnum.CHANGE_REVIEW.getName());
                notificationDomain.setRead(false);
                notificationDomain.setDateTime(LocalDateTime.now());
                notificationDomain.setSubject("La solicitud de cambio ha pasado a revision por el area de reserva");
                notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

                notificationDomain.setUserSource(changeRequestActive.getUserEmit());
                notificationDomain.setUserDestine(changeRequestActive.getUserReceive());
                notificationDomain.setEmployeeSource(changeRequestActive.getEmployeeEmit());
                notificationDomain.setEmployeeDestine(changeRequestActive.getEmployeeReceive());

                notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

                ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
                ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

                notificationDomain.setIdObjectSource(changeRequestActive.getQuotation().getId());
                notificationDomain.setNameObjectSource(changeRequestActive.getQuotation().getName());
                notificationDomain.setIdentifyObjectSource(changeRequestActive.getQuotation().getIdentify());
                notificationDomain.setObjectEntitySource(objectDomainSource);

                notificationDomain.setIdObjectDestine(changeRequestActive.getRegisterGroup().getId());
                notificationDomain.setNameObjectDestine(changeRequestActive.getRegisterGroup().getName());
                notificationDomain.setIdentifyObjectDestine(changeRequestActive.getRegisterGroup().getIdentify());
                notificationDomain.setObjectEntityDestine(objectDomainDestine);

                notificationRepo.create(notificationDomain);

                changeRequestModelRepo.edit(changeRequestActive);
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(registerGroupDomain.getId());

        return RegisterGroupResponse.from(registerGroupDomain);
    }

    @Override
    @Transactional
    public RegisterGroupResponse resendQuotationToBookingUpdateAdministration(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findByIdentify(identify);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with identify '%s'.".formatted(identify));
        }

        // Delete All By Register Group

        List<CostTourDomain> listCostTourDelete = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<CostTourServiceItemDomain> listCostTourServiceItemDelete = costTourServiceItemRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<ItineraryDateDomain> listItineraryDateDelete = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<ItineraryItemDomain> listItineraryItemDelete = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());
        List<PassengerGroupDomain> listPassengerGroupDelete = passengerGroupRepo.searchAllByRegisterGroupSimpleDomainCaseDelete(registerGroupDomain.getId());

        if (listCostTourDelete != null && !listCostTourDelete.isEmpty()){
            for (CostTourDomain costTourDomain: listCostTourDelete){
                costTourRepo.deleteById(costTourDomain.getId());
            }
        }

        if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItemDelete){
                costTourItemRepo.deleteById(costTourItemDomain.getId());
            }
        }

        if (listCostTourServiceItemDelete != null && !listCostTourServiceItemDelete.isEmpty()){
            for (CostTourServiceItemDomain costTourServiceItemDomain: listCostTourServiceItemDelete){
                costTourServiceItemRepo.deleteById(costTourServiceItemDomain.getId());
            }
        }

        if (listItineraryDateDelete != null && !listItineraryDateDelete.isEmpty()){
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateDelete){
                itineraryDateRepo.deleteById(itineraryDateDomain.getId());
            }
        }

        if (listItineraryItemDelete != null && !listItineraryItemDelete.isEmpty()){
            for (ItineraryItemDomain itineraryItemDomain: listItineraryItemDelete){
                itineraryItemRepo.deleteById(itineraryItemDomain.getId());
            }
        }

        if (listPassengerGroupDelete != null && !listPassengerGroupDelete.isEmpty()){
            for (PassengerGroupDomain passengerGroupDomain: listPassengerGroupDelete){
                passengerGroupRepo.deleteById(passengerGroupDomain.getId());
            }
        }

        registerGroupDomain.setInitTour(registerGroupDomain.getQuotation().getDateInitTour());
        registerGroupDomain.setEndTour(registerGroupDomain.getQuotation().getDateEndTour());
        registerGroupDomain.setProgram(registerGroupDomain.getQuotation().getProgram());
        registerGroupDomain.setIdiom(registerGroupDomain.getQuotation().getIdiom());
        registerGroupDomain.setCantPaxAdult(registerGroupDomain.getQuotation().getPaxAdult());
        registerGroupDomain.setCantPaxChildren(registerGroupDomain.getQuotation().getPaxChild());
        registerGroupDomain.setTC(registerGroupDomain.getQuotation().getPaxTc());
        registerGroupDomain.setTCLocal(registerGroupDomain.getQuotation().getPaxTcLocal());
        registerGroupDomain.setTotalPax(registerGroupDomain.getQuotation().getPaxTotal());
        registerGroupDomain.setDays(registerGroupDomain.getQuotation().getCantDayTour());
        // registerGroupDomain.setApertureWrite(true);
        // registerGroupDomain.setDateAperture(LocalDate.now());
        registerGroupDomain.setCountry(registerGroupDomain.getQuotation().getCountry());
        registerGroupDomain.setLink(registerGroupDomain.getQuotation().getLinkGDrive());
        registerGroupDomain.setLinkBitrix(registerGroupDomain.getQuotation().getLinkBitrix());
        registerGroupDomain.setLinkWetravel(registerGroupDomain.getQuotation().getLinkWetravel());
        registerGroupDomain.setEmailPassengerPrincipal(registerGroupDomain.getQuotation().getEmailPassengerPrincipal());
        registerGroupDomain.setWhatsappPassengerPrincipal(registerGroupDomain.getQuotation().getWhatsappPassengerPrincipal());
        registerGroupDomain.setMobilePassengerPrincipal(registerGroupDomain.getQuotation().getMobilePassengerPrincipal());
        registerGroupDomain = repo.edit(registerGroupDomain);

        List<ItineraryItemDomain> listItineraryItemUpdateCostTour = new ArrayList<>();

        // Generate Itinerary Date and Item
        List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDate = itineraryDateCrmRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(registerGroupDomain.getQuotation().getId());
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain itineraryDateDomainSource: listItineraryDateResult
            ) {
                ItineraryDateDomain itineraryDateDomainDestination = new ItineraryDateDomain();
                itineraryDateDomainDestination.setName(itineraryDateDomainSource.getName());
                itineraryDateDomainDestination.setDescription(itineraryDateDomainSource.getDescription());
                itineraryDateDomainDestination.setIdentify(itineraryDateDomainSource.getIdentify());
                itineraryDateDomainDestination.setItineraryDate(itineraryDateDomainSource.getItineraryDate());
                itineraryDateDomainDestination.setIndex(itineraryDateDomainSource.getIndex());
                itineraryDateDomainDestination.setItemItinerary(itineraryDateDomainSource.getItemItinerary());
                itineraryDateDomainDestination.setRegisterGroup(registerGroupDomain);
                itineraryDateDomainDestination.setTours(itineraryDateDomainSource.getTours());
                itineraryDateDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                itineraryDateDomainDestination.setIdItineraryDateQuotation(itineraryDateDomainSource.getId());

                ItineraryDateDomain resultItineraryDateDomainDestination = itineraryDateRepo.create(itineraryDateDomainDestination);

                List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain> listItineraryItem = itineraryItemCrmRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(itineraryDateDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();

                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain itineraryItemDomainSource: listItineraryItemResult
                    ) {
                        ItineraryItemDomain itineraryItemDomainDestination = new ItineraryItemDomain();
                        itineraryItemDomainDestination.setName(itineraryItemDomainSource.getName());
                        itineraryItemDomainDestination.setDescription(itineraryItemDomainSource.getDescription());
                        itineraryItemDomainDestination.setIdentify(itineraryItemDomainSource.getIdentify());
                        itineraryItemDomainDestination.setServiceGroup(itineraryItemDomainSource.getServiceGroup());
                        itineraryItemDomainDestination.setObservation(itineraryItemDomainSource.getObservation());
                        itineraryItemDomainDestination.setItineraryDateTime(itineraryItemDomainSource.getItineraryDateTime());
                        itineraryItemDomainDestination.setPaxTotal(itineraryItemDomainSource.getPaxTotal());
                        itineraryItemDomainDestination.setPrice(itineraryItemDomainSource.getPrice());
                        itineraryItemDomainDestination.setPaxAdultTotal(itineraryItemDomainSource.getPaxAdultTotal());
                        itineraryItemDomainDestination.setPriceAdult(itineraryItemDomainSource.getPriceAdult());
                        itineraryItemDomainDestination.setPaxChildrenTotal(itineraryItemDomainSource.getPaxChildrenTotal());
                        itineraryItemDomainDestination.setPriceChildren(itineraryItemDomainSource.getPriceChildren());
                        itineraryItemDomainDestination.setPaxTcTotal(itineraryItemDomainSource.getPaxTcTotal());
                        itineraryItemDomainDestination.setPriceTc(itineraryItemDomainSource.getPriceTc());
                        itineraryItemDomainDestination.setPaxTcLocalTotal(itineraryItemDomainSource.getPaxTcLocalTotal());
                        itineraryItemDomainDestination.setPriceTcLocal(itineraryItemDomainSource.getPriceTcLocal());
                        itineraryItemDomainDestination.setPaxGuideTotal(itineraryItemDomainSource.getPaxGuideTotal());
                        itineraryItemDomainDestination.setPriceGuide(itineraryItemDomainSource.getPriceGuide());

                        itineraryItemDomainDestination.setItineraryDate(resultItineraryDateDomainDestination);
                        itineraryItemDomainDestination.setIndex(itineraryItemDomainSource.getIndex());
                        itineraryItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        itineraryItemDomainDestination.setSupplierType(itineraryItemDomainSource.getSupplierType());
                        itineraryItemDomainDestination.setSupplier(itineraryItemDomainSource.getSupplier());
                        itineraryItemDomainDestination.setService(itineraryItemDomainSource.getService());
                        itineraryItemDomainDestination.setDepartmentRegion(itineraryItemDomainSource.getDepartmentRegion());
                        itineraryItemDomainDestination.setTour(itineraryItemDomainSource.getTour());
                        itineraryItemDomainDestination.setCustomer(registerGroupDomain.getCustomer());
                        // Supplier Type Lodging

                        itineraryItemDomainDestination.setLodgingCantSwb(itineraryItemDomainSource.getLodgingCantSwb());
                        itineraryItemDomainDestination.setLodgingCantMat(itineraryItemDomainSource.getLodgingCantMat());
                        itineraryItemDomainDestination.setLodgingCantTwin(itineraryItemDomainSource.getLodgingCantTwin());
                        itineraryItemDomainDestination.setLodgingCantTrp(itineraryItemDomainSource.getLodgingCantTrp());

                        itineraryItemDomainDestination.setLodgingCantBreakfast(itineraryItemDomainSource.getLodgingCantBreakfast());
                        itineraryItemDomainDestination.setLodgingCantLunch(itineraryItemDomainSource.getLodgingCantLunch());
                        itineraryItemDomainDestination.setLodgingCantDinner(itineraryItemDomainSource.getLodgingCantDinner());
                        itineraryItemDomainDestination.setLodgingCantBoxLunch(itineraryItemDomainSource.getLodgingCantBoxLunch());

                        itineraryItemDomainDestination.setLodgingCantNight(itineraryItemDomainSource.getLodgingCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastTcGuide(itineraryItemDomainSource.getLodgingCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setLodgingCantLunchTcGuide(itineraryItemDomainSource.getLodgingCantLunchTcGuide());
                        itineraryItemDomainDestination.setLodgingCantDinnerTcGuide(itineraryItemDomainSource.getLodgingCantDinnerTcGuide());
                        itineraryItemDomainDestination.setLodgingCantBoxLunchTcGuide(itineraryItemDomainSource.getLodgingCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setLodgingCantSwbTcGuide(itineraryItemDomainSource.getLodgingCantSwbTcGuide());
                        itineraryItemDomainDestination.setLodgingCantTwinTcGuide(itineraryItemDomainSource.getLodgingCantTwinTcGuide());

                        itineraryItemDomainDestination.setLodgingTcCantNight(itineraryItemDomainSource.getLodgingTcCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastTcLocalGuide(itineraryItemDomainSource.getLodgingCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantLunchTcLocalGuide(itineraryItemDomainSource.getLodgingCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantDinnerTcLocalGuide(itineraryItemDomainSource.getLodgingCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getLodgingCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setLodgingCantSwbTcLocalGuide(itineraryItemDomainSource.getLodgingCantSwbTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantTwinTcLocalGuide(itineraryItemDomainSource.getLodgingCantTwinTcLocalGuide());

                        itineraryItemDomainDestination.setLodgingTcLocalCantNight(itineraryItemDomainSource.getLodgingTcLocalCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastGuide(0);
                        itineraryItemDomainDestination.setLodgingCantLunchGuide(0);
                        itineraryItemDomainDestination.setLodgingCantDinnerGuide(0);

                        itineraryItemDomainDestination.setLodgingCantSwbGuide(0);
                        itineraryItemDomainDestination.setLodgingCantTwinGuide(0);

                        itineraryItemDomainDestination.setLodgingGuideCantNight(0);

                        itineraryItemDomainDestination.setLodgingForOperator(itineraryItemDomainSource.getLodgingForOperator());
                        // Supplier Type Lodge

                        itineraryItemDomainDestination.setLodgeCantSwb(itineraryItemDomainSource.getLodgeCantSwb());
                        itineraryItemDomainDestination.setLodgeCantMat(itineraryItemDomainSource.getLodgeCantMat());
                        itineraryItemDomainDestination.setLodgeCantTwin(itineraryItemDomainSource.getLodgeCantTwin());
                        itineraryItemDomainDestination.setLodgeCantTrp(itineraryItemDomainSource.getLodgeCantTrp());

                        itineraryItemDomainDestination.setLodgeCantBreakfast(itineraryItemDomainSource.getLodgeCantBreakfast());
                        itineraryItemDomainDestination.setLodgeCantLunch(itineraryItemDomainSource.getLodgeCantLunch());
                        itineraryItemDomainDestination.setLodgeCantDinner(itineraryItemDomainSource.getLodgeCantDinner());
                        itineraryItemDomainDestination.setLodgeCantBoxLunch(itineraryItemDomainSource.getLodgeCantBoxLunch());

                        itineraryItemDomainDestination.setLodgeCantNight(itineraryItemDomainSource.getLodgeCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastTcGuide(itineraryItemDomainSource.getLodgeCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setLodgeCantLunchTcGuide(itineraryItemDomainSource.getLodgeCantLunchTcGuide());
                        itineraryItemDomainDestination.setLodgeCantDinnerTcGuide(itineraryItemDomainSource.getLodgeCantDinnerTcGuide());
                        itineraryItemDomainDestination.setLodgeCantBoxLunchTcGuide(itineraryItemDomainSource.getLodgeCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setLodgeCantSwbTcGuide(itineraryItemDomainSource.getLodgeCantSwbTcGuide());
                        itineraryItemDomainDestination.setLodgeCantTwinTcGuide(itineraryItemDomainSource.getLodgeCantTwinTcGuide());

                        itineraryItemDomainDestination.setLodgeTcCantNight(itineraryItemDomainSource.getLodgeTcCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastTcLocalGuide(itineraryItemDomainSource.getLodgeCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantLunchTcLocalGuide(itineraryItemDomainSource.getLodgeCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantDinnerTcLocalGuide(itineraryItemDomainSource.getLodgeCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getLodgeCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setLodgeCantSwbTcLocalGuide(itineraryItemDomainSource.getLodgeCantSwbTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantTwinTcLocalGuide(itineraryItemDomainSource.getLodgeCantTwinTcLocalGuide());

                        itineraryItemDomainDestination.setLodgeTcLocalCantNight(itineraryItemDomainSource.getLodgeTcLocalCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastGuide(0);
                        itineraryItemDomainDestination.setLodgeCantLunchGuide(0);
                        itineraryItemDomainDestination.setLodgeCantDinnerGuide(0);

                        itineraryItemDomainDestination.setLodgeCantSwbGuide(0);
                        itineraryItemDomainDestination.setLodgeCantTwinGuide(0);

                        itineraryItemDomainDestination.setLodgeGuideCantNight(0);

                        itineraryItemDomainDestination.setLodgePvt(itineraryItemDomainSource.getLodgePvt());
                        itineraryItemDomainDestination.setLodgeSic(itineraryItemDomainSource.getLodgeSic());

                        // Supplier Type Restaurant

                        itineraryItemDomainDestination.setRestaurantCantBreakfast(itineraryItemDomainSource.getRestaurantCantBreakfast());
                        itineraryItemDomainDestination.setRestaurantCantLunch(itineraryItemDomainSource.getRestaurantCantLunch());
                        itineraryItemDomainDestination.setRestaurantCantDinner(itineraryItemDomainSource.getRestaurantCantDinner());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunch(itineraryItemDomainSource.getRestaurantCantBoxLunch());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastTcGuide(itineraryItemDomainSource.getRestaurantCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchTcGuide(itineraryItemDomainSource.getRestaurantCantLunchTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerTcGuide(itineraryItemDomainSource.getRestaurantCantDinnerTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchTcGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastTcLocalGuide(itineraryItemDomainSource.getRestaurantCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchTcLocalGuide(itineraryItemDomainSource.getRestaurantCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerTcLocalGuide(itineraryItemDomainSource.getRestaurantCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastGuide(itineraryItemDomainSource.getRestaurantCantBreakfastGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchGuide(itineraryItemDomainSource.getRestaurantCantLunchGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerGuide(itineraryItemDomainSource.getRestaurantCantDinnerGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastDrive(itineraryItemDomainSource.getRestaurantCantBreakfastDrive());
                        itineraryItemDomainDestination.setRestaurantCantLunchDrive(itineraryItemDomainSource.getRestaurantCantLunchDrive());
                        itineraryItemDomainDestination.setRestaurantCantDinnerDrive(itineraryItemDomainSource.getRestaurantCantDinnerDrive());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchDrive(itineraryItemDomainSource.getRestaurantCantBoxLunchDrive());

                        // Supplier Type Operator

                        itineraryItemDomainDestination.setOperatorPvt(itineraryItemDomainSource.getOperatorPvt());
                        itineraryItemDomainDestination.setOperatorSic(itineraryItemDomainSource.getOperatorSic());

                        itineraryItemDomainDestination.setLodgingForOperator(itineraryItemDomainSource.getLodgingForOperator());

                        itineraryItemDomainDestination.setLodgingForOperatorStr(itineraryItemDomainSource.getLodgingForOperatorStr());

                        itineraryItemDomainDestination.setIdItineraryItemQuotation(itineraryItemDomainSource.getId());

                        itineraryItemDomainDestination.setStateItineraryService(StateBookingEnum.DRAFT.getName());
                        itineraryItemDomainDestination.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());

                        itineraryItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        listItineraryItemUpdateCostTour.add(itineraryItemRepo.create(itineraryItemDomainDestination));
                    }
                }
            }
        }
        // Generate Passenger Group
        List<PassengerDomain> listPassenger = passengerRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(registerGroupDomain.getQuotation().getId());
        if (listPassenger != null && !listPassenger.isEmpty()) {
            List<PassengerDomain> listPassengerResult = listPassenger.stream().collect(Collectors.toList());
            for (PassengerDomain passengerDomainSource: listPassengerResult
            ) {
                PassengerGroupDomain passengerGroupDomainDestination = new PassengerGroupDomain();
                passengerGroupDomainDestination.setName(passengerDomainSource.getName());
                passengerGroupDomainDestination.setDescription(passengerDomainSource.getDescription());
                passengerGroupDomainDestination.setIdentify(passengerDomainSource.getIdentify());
                passengerGroupDomainDestination.setSocialMediaInformation(passengerDomainSource.getSocialMediaInformation());
                passengerGroupDomainDestination.setWebSiteInformation(passengerDomainSource.getWebSiteInformation());
                passengerGroupDomainDestination.setContactInformation(passengerDomainSource.getContactInformation());
                passengerGroupDomainDestination.setAddress(passengerDomainSource.getAddress());
                passengerGroupDomainDestination.setDateInit(passengerDomainSource.getDateInit());
                passengerGroupDomainDestination.setDateEnd(passengerDomainSource.getDateEnd());
                passengerGroupDomainDestination.setDocument(passengerDomainSource.getDocument());
                passengerGroupDomainDestination.setObservation(passengerDomainSource.getObservation());
                passengerGroupDomainDestination.setPrincipal(passengerDomainSource.getPrincipal());
                passengerGroupDomainDestination.setImageUrl(passengerDomainSource.getImageUrl());
                passengerGroupDomainDestination.setTitlePerson(passengerDomainSource.getTitlePerson());
                passengerGroupDomainDestination.setTypePassengerTcBooking(passengerDomainSource.getTypePassengerTcBooking());
                passengerGroupDomainDestination.setPerson(passengerDomainSource.getPerson());
                passengerGroupDomainDestination.setNationality(passengerDomainSource.getNationality());
                passengerGroupDomainDestination.setDocumentType(passengerDomainSource.getDocumentType());
                passengerGroupDomainDestination.setPassengerType(passengerDomainSource.getPassengerType());
                passengerGroupDomainDestination.setRegisterGroup(registerGroupDomain);
                passengerGroupDomainDestination.setIndex(passengerDomainSource.getIndex());
                passengerGroupDomainDestination.setIdPassengerQuotation(passengerDomainSource.getId());

                passengerGroupDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                passengerGroupRepo.create(passengerGroupDomainDestination);
            }
        }
        // Generate Cost Tour
        List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTour = costTourCrmRepo.searchAllByQuotationSimpleDomainCaseResendQuotationToBooking(registerGroupDomain.getQuotation().getId());
        if (listCostTour != null && !listCostTour.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTourResult = listCostTour.stream().collect(Collectors.toList());
            for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain costTourDomainSource: listCostTourResult
            ) {
                Map<String, CostTourItemDomain> mapCostTourItemService = new HashMap<>();

                CostTourDomain costTourDomainDestination = new CostTourDomain();
                costTourDomainDestination.setName(costTourDomainSource.getName());
                costTourDomainDestination.setDescription(costTourDomainSource.getDescription());
                costTourDomainDestination.setIdentify(costTourDomainSource.getIdentify());
                ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.getByRegisterGroupAndItemItinerary(registerGroupDomain.getId(), costTourDomainSource.getItineraryDate().getItemItinerary());
                costTourDomainDestination.setItineraryDate(itineraryDateDomain);
                costTourDomainDestination.setNumMaxPax(costTourDomainSource.getNumMaxPax());
                costTourDomainDestination.setRegisterGroup(registerGroupDomain);
                costTourDomainDestination.setSubTotalAmount(costTourDomainSource.getSubTotalAmount());
                costTourDomainDestination.setTour(costTourDomainSource.getTour());
                costTourDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                CostTourDomain costTourDomainDestinationResult = costTourRepo.create(costTourDomainDestination);

                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain> listCostTourItem = costTourItemCrmRepo.searchAllByCostTourAndQuotationSimpleDomainCaseResendQuotationToBooking(costTourDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain> listCostTourItemResult = new ArrayList<>();
                if (listCostTourItem != null && !listCostTourItem.isEmpty()) {
                    listCostTourItemResult = listCostTourItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain costTourItemDomainSource : listCostTourItemResult
                    ) {
                        CostTourItemDomain costTourItemDomainDestination = new CostTourItemDomain();
                        costTourItemDomainDestination.setName(costTourItemDomainSource.getName());
                        costTourItemDomainDestination.setDescription(costTourItemDomainSource.getDescription());
                        costTourItemDomainDestination.setIdentify(costTourItemDomainSource.getIdentify());
                        costTourItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        costTourItemDomainDestination.setItineraryDate(itineraryDateDomain);
                        costTourItemDomainDestination.setTour(costTourItemDomainSource.getTour());
                        costTourItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        costTourItemDomainDestination.setPassengerType(costTourItemDomainSource.getPassengerType());
                        costTourItemDomainDestination.setCantPax(costTourItemDomainSource.getCantPax());
                        costTourItemDomainDestination.setPricePax(costTourItemDomainSource.getPricePax());

                        costTourItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        costTourItemDomainDestination = costTourItemRepo.create(costTourItemDomainDestination);
                        if (costTourItemDomainSource.getCostTourServiceItem() != null){
                            mapCostTourItemService.put(costTourItemDomainSource.getCostTourServiceItem().getId(), costTourItemDomainDestination);
                        }
                    }
                }

                List<ItineraryItemDomain>  tempForUpdates = listItineraryItemUpdateCostTour.stream()
                        .filter(itineraryItemDomain -> _matchesCostTourAndItineraryDate(itineraryItemDomain, costTourDomainDestinationResult))
                        .collect(Collectors.toList());
                if (tempForUpdates != null && !tempForUpdates.isEmpty()){
                    for (ItineraryItemDomain itemTempForUpdate: tempForUpdates){
                        itemTempForUpdate.setCostTour(costTourDomainDestinationResult);
                        itineraryItemRepo.edit(itemTempForUpdate);
                    }
                }

                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain> listCostTourServiceItem = costTourServiceItemCrmRepo.searchAllByCostTourAndQuotationCaseResendQuotationToBooking(costTourDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain> listCostTourServiceItemResult = new ArrayList<>();
                if (listCostTourServiceItem != null && !listCostTourServiceItem.isEmpty()) {
                    listCostTourServiceItemResult = listCostTourServiceItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain costTourServiceItemDomainSource : listCostTourServiceItemResult
                    ) {
                        CostTourServiceItemDomain costTourServiceItemDomainDestination = new CostTourServiceItemDomain();
                        costTourServiceItemDomainDestination.setName(costTourServiceItemDomainSource.getName());
                        costTourServiceItemDomainDestination.setDescription(costTourServiceItemDomainSource.getDescription());
                        costTourServiceItemDomainDestination.setIdentify(costTourServiceItemDomainSource.getIdentify());
                        costTourServiceItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        ItineraryDateDomain itineraryDateDomain1 = itineraryDateRepo.getByRegisterGroupAndItemItinerary(registerGroupDomain.getId(), costTourServiceItemDomainSource.getItineraryDate().getItemItinerary());
                        costTourServiceItemDomainDestination.setItineraryDate(itineraryDateDomain1);
                        costTourServiceItemDomainDestination.setTour(costTourDomainDestinationResult.getTour());
                        costTourServiceItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        costTourServiceItemDomainDestination.setSupplierType(costTourServiceItemDomainSource.getSupplierType());
                        costTourServiceItemDomainDestination.setSupplier(costTourServiceItemDomainSource.getSupplier());
                        costTourServiceItemDomainDestination.setService(costTourServiceItemDomainSource.getService());
                        costTourServiceItemDomainDestination.setObservation(costTourServiceItemDomainSource.getObservation());

                        costTourServiceItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        costTourServiceItemDomainDestination = costTourServiceItemRepo.create(costTourServiceItemDomainDestination);

                        if (mapCostTourItemService.containsKey(costTourServiceItemDomainSource.getId())){
                            mapCostTourItemService.get(costTourServiceItemDomainSource.getId()).setCostTourServiceItem(costTourServiceItemDomainDestination);
                            costTourItemRepo.edit(mapCostTourItemService.get(costTourServiceItemDomainSource.getId()));
                        }
                    }
                }
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(registerGroupDomain.getId());

        return RegisterGroupResponse.from(registerGroupDomain);
    }

    @Override
    @Transactional
    public ItineraryDateResponse editItineraryDate(String id, ItineraryDateUpdateOnlyNameRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        ItineraryDateDomain itineraryDateDomain = itineraryDateUseCase.findByIdDomain(id);

        ItineraryDateUpdateRequest itineraryDateUpdateRequest = new ItineraryDateUpdateRequest();
        itineraryDateUpdateRequest.setId(request.getId());
        itineraryDateUpdateRequest.setName(request.getName());
        itineraryDateUpdateRequest.setDescription(itineraryDateDomain.getDescription());
        itineraryDateUpdateRequest.setIdentify(itineraryDateDomain.getIdentify());
        itineraryDateUpdateRequest.setItineraryDate(itineraryDateDomain.getItineraryDate());
        itineraryDateUpdateRequest.setRegisterGroup(request.getRegisterGroup());

        ItineraryDateResponse resultEdit = itineraryDateUseCase.edit(id, itineraryDateUpdateRequest);

        return resultEdit;
    }

    @Override
    @Transactional
    public void deleteItineraryDate(String id, String idRegisterGroup) {
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryItemDomain> listValidation = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseValidationExistBooking(id, idRegisterGroup);
        listValidation = listValidation.stream().collect(Collectors.toList());
        for(ItineraryItemDomain itemDomain : listValidation){
            if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingInstitutionDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                }
            }
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            List<CostTourDomain> listCostTour = costTourRepo.searchAllByRegisterGroupAndItineraryDateSimpleDomainCaseDelete(idRegisterGroup, id);
            if (listCostTour != null && !listCostTour.isEmpty()){
                throw new BadRequestException(MessagesValidation.VALIDATION_TOUR_EXIST_IN_DAY);
            }
        }

        Integer indexCurrent = 0;
        Integer indexCurrentPass = 0;

        List<ItineraryDateDomain> list = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(idRegisterGroup).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryDateDomain::getIndex));

        ItineraryDateDomain source = itineraryDateRepo.findByIdSimpleDomainCaseDeleteItineraryDate(id);
        ItineraryDateDomain target = list.get(list.size() - 1);

        Integer indexListSource = list.indexOf(source);
        Integer indexListTarget = list.indexOf(target);
        ItineraryDateDomain itineraryIndexSource = list.get(indexListSource);
        ItineraryDateDomain itineraryIndexTarget = list.get(indexListTarget);

        if (indexListSource < indexListTarget){
            indexCurrent = indexListSource;
            indexCurrentPass = indexCurrent + 1;
            while (indexCurrent < indexListTarget){
                ItineraryDateDomain itineraryDateCurrent = list.get(indexCurrent);
                ItineraryDateDomain itineraryDatePassCurrent = list.get(indexCurrentPass);

                ItineraryDateDomain dbItineraryDate = itineraryDateRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId());
                itineraryDateRepo.updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                        dbItineraryDate.getId(), itineraryDateCurrent.getIndex(), itineraryDateCurrent.getItemItinerary(), itineraryDateCurrent.getItineraryDate());

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    ItineraryDateDomain itineraryDateEditDb = itineraryDateRepo.findById(itineraryDatePassCurrent.getId());
                    this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, itineraryDateEditDb));
                }*/

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult;
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    for(ItineraryItemDomain itemDomain : listItineraryItemResult){
                        LocalDateTime localDateTime = LocalDateTime.of(
                                itineraryDateCurrent.getItineraryDate().getYear(),
                                itineraryDateCurrent.getItineraryDate().getMonth(),
                                itineraryDateCurrent.getItineraryDate().getDayOfMonth(),
                                itemDomain.getItineraryDateTime().getHour(),
                                itemDomain.getItineraryDateTime().getMinute(),
                                itemDomain.getItineraryDateTime().getSecond());

                        itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                            this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                        }*/
                    }
                }

                indexCurrent++;
                indexCurrentPass++;
            }

            itineraryDateRepo.updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                    itineraryIndexSource.getId(), itineraryIndexTarget.getIndex(), itineraryIndexTarget.getItemItinerary(), itineraryIndexTarget.getItineraryDate());

            /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                ItineraryDateDomain itineraryDateEditDb = itineraryDateRepo.findById(itineraryIndexSource.getId());
                this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, itineraryDateEditDb));
            }*/

            List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itineraryIndexSource.getId(), idRegisterGroup);
            List<ItineraryItemDomain> listItineraryItemResult;
            if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                for(ItineraryItemDomain itemDomain : listItineraryItemResult){
                    LocalDateTime localDateTime = LocalDateTime.of(
                            itineraryIndexTarget.getItineraryDate().getYear(),
                            itineraryIndexTarget.getItineraryDate().getMonth(),
                            itineraryIndexTarget.getItineraryDate().getDayOfMonth(),
                            itemDomain.getItineraryDateTime().getHour(),
                            itemDomain.getItineraryDateTime().getMinute(),
                            itemDomain.getItineraryDateTime().getSecond());

                    itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                        this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                    }*/
                }
            }
        }

        itineraryDateUseCase.deleteById(id);

        if (listValidation != null && !listValidation.isEmpty()){
            for (ItineraryItemDomain itineraryItemDomain: listValidation){
                itineraryItemRepo.deleteById(itineraryItemDomain.getId());
            }
        }

        repo.updateIdSimpleDomainCaseDeleteItineraryDate(idRegisterGroup, registerGroupDomain.getInitTour(), registerGroupDomain.getEndTour().minusDays(1), registerGroupDomain.getDays() - 1);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public WrapSupplierRegisterBookingAllResponse findAllRegisterBookingGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request) {
        WrapSupplierRegisterBookingAllResponse result = new WrapSupplierRegisterBookingAllResponse();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setOperators(new ArrayList<>());
        result.setLodges(new ArrayList<>());
        result.setRestaurants(new ArrayList<>());
        result.setLodgings(new ArrayList<>());
        result.setGlampings(new ArrayList<>());
        result.setInstitutions(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingResponse> mapResultLodging = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingResponse> mapResultLodge = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingResponse> mapResultOperator = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingResponse> mapResultRestaurant = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingResponse> mapResultGlamping = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingResponse> mapResultInstitutions = new HashMap<>();

        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId()) ){
                        if (!mapResultLodging.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultLodging.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId()) ){
                        if (!mapResultOperator.containsKey(itineraryItemDomain.getSupplier().getId())){
                            ConfigurationSupplierOperatorDomain configurationSupplierOperatorDomain = configurationSupplierOperatorRepo.findByIdSimpleDomainCaseGenerateRequestOrderService(itineraryItemDomain.getSupplier().getId());
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            wrapSupplierRegisterBookingResponse.setAnnulledBookingOperator(false);
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            if (configurationSupplierOperatorDomain != null){
                                wrapSupplierRegisterBookingResponse.setGenerateRequestOrderService(true);
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderService("Without Generate");
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderServiceDescription("Sin Generar");
                            }else{
                                wrapSupplierRegisterBookingResponse.setGenerateRequestOrderService(false);
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderService("Without Generate");
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderServiceDescription("Sin Generar");
                            }
                            mapResultOperator.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId()) ){
                        if (!mapResultRestaurant.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultRestaurant.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId()) ){
                        if (!mapResultLodge.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultLodge.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId()) ){
                        if (!mapResultLodging.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultGlamping.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId()) ){
                        if (!mapResultInstitutions.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultInstitutions.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }
                }
            }
            result.getLodgings().addAll(mapResultLodging.values());
            result.getLodges().addAll(mapResultLodge.values());
            result.getRestaurants().addAll(mapResultRestaurant.values());
            result.getOperators().addAll(mapResultOperator.values());
            result.getGlampings().addAll(mapResultGlamping.values());
            result.getInstitutions().addAll(mapResultInstitutions.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getLodgings()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            } else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findByIdSimpleDomainCaseGenerateRequestOrderService(registerGroupDomain.getId(), registerBookingBaseDomain.getSupplier().getId(), registerBookingBaseDomain.getId());
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getOperators()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
                if (requestOrderServiceDomain != null){
                    wrapSupplierRegisterBookingResponseData.setRequestOrderServiceResponse(RequestOrderServiceResponse.from(requestOrderServiceDomain));
                    wrapSupplierRegisterBookingResponseData.setStateRequestOrderService(requestOrderServiceDomain.getRequestOrderServiceState());
                    RequestOrderServiceStateEnum.RequestOrderServiceStateModel requestOrderServiceStateModelData = EnumMappeableService
                            .map(RequestOrderServiceStateEnum.class)
                            .stream().filter(
                                    requestOrderServiceStateModel -> requestOrderServiceStateModel.name().equals(requestOrderServiceDomain.getRequestOrderServiceState()))
                            .findFirst().get();
                    wrapSupplierRegisterBookingResponseData.setStateRequestOrderServiceDescription(requestOrderServiceStateModelData.description());
                }else{
                    wrapSupplierRegisterBookingResponseData.setRequestOrderServiceResponse(null);
                }

                if (wrapSupplierRegisterBookingResponseData.getGenerateRequestOrderService() != null && wrapSupplierRegisterBookingResponseData.getGenerateRequestOrderService()){
                    if (wrapSupplierRegisterBookingResponseData.getRequestOrderServiceResponse() != null){
                        wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(false);
                    }else{
                        if (wrapSupplierRegisterBookingResponseData.getStateBooking().equals("Without Generate")){
                            wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(false);
                        }else{
                            wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(true);
                        }
                    }
                }else{
                    if (wrapSupplierRegisterBookingResponseData.getStateBooking().equals("Without Generate")){
                        wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(false);
                    }else{
                        wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(true);
                    }
                }
            } else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getRestaurants()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            } else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getLodges()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            } else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getGlampings()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            } else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getInstitutions()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public WrapSupplierRegisterBookingOpeAllResponse findAllRegisterBookingOperatorGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request) {
        WrapSupplierRegisterBookingOpeAllResponse result = new WrapSupplierRegisterBookingOpeAllResponse();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setOperators(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingResponse> mapResultOperator = new HashMap<>();

        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);

        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId()) ){
                        if (!mapResultOperator.containsKey(itineraryItemDomain.getSupplier().getId())){
                            ConfigurationSupplierOperatorDomain configurationSupplierOperatorDomain = configurationSupplierOperatorRepo.findByIdSimpleDomainCaseGenerateRequestOrderService(itineraryItemDomain.getSupplier().getId());
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            if (configurationSupplierOperatorDomain != null){
                                wrapSupplierRegisterBookingResponse.setGenerateRequestOrderService(true);
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderService("Without Generate");
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderServiceDescription("Sin Generar");
                            }else{
                                wrapSupplierRegisterBookingResponse.setGenerateRequestOrderService(false);
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderService("Without Generate");
                                wrapSupplierRegisterBookingResponse.setStateRequestOrderServiceDescription("Sin Generar");
                            }
                            mapResultOperator.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }
                }
            }
            result.getOperators().addAll(mapResultOperator.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findByIdSimpleDomainCaseGenerateRequestOrderService(registerGroupDomain.getId(), registerBookingBaseDomain.getSupplier().getId(), registerBookingBaseDomain.getId());
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getOperators()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
                if (requestOrderServiceDomain != null){
                    wrapSupplierRegisterBookingResponseData.setRequestOrderServiceResponse(RequestOrderServiceResponse.from(requestOrderServiceDomain));
                    wrapSupplierRegisterBookingResponseData.setStateRequestOrderService(requestOrderServiceDomain.getRequestOrderServiceState());
                    RequestOrderServiceStateEnum.RequestOrderServiceStateModel requestOrderServiceStateModelData = EnumMappeableService
                            .map(RequestOrderServiceStateEnum.class)
                            .stream().filter(
                                    requestOrderServiceStateModel -> requestOrderServiceStateModel.name().equals(requestOrderServiceDomain.getRequestOrderServiceState()))
                            .findFirst().get();
                    wrapSupplierRegisterBookingResponseData.setStateRequestOrderServiceDescription(requestOrderServiceStateModelData.description());
                }else{
                    wrapSupplierRegisterBookingResponseData.setRequestOrderServiceResponse(null);
                }
                if (wrapSupplierRegisterBookingResponseData.getGenerateRequestOrderService() != null && wrapSupplierRegisterBookingResponseData.getGenerateRequestOrderService()){
                    if (wrapSupplierRegisterBookingResponseData.getRequestOrderServiceResponse() != null){
                        wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(false);
                    }else{
                        if (wrapSupplierRegisterBookingResponseData.getStateBooking().equals("Without Generate")){
                            wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(false);
                        }else{
                            wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(true);
                        }
                    }
                }else{
                    if (wrapSupplierRegisterBookingResponseData.getStateBooking().equals("Without Generate")){
                        wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(false);
                    }else{
                        wrapSupplierRegisterBookingResponseData.setAnnulledBookingOperator(true);
                    }
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public WrapSupplierRegisterBookingRestAllResponse findAllRegisterBookingRestaurantGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request) {
        WrapSupplierRegisterBookingRestAllResponse result = new WrapSupplierRegisterBookingRestAllResponse();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setRestaurants(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingResponse> mapResultRestaurant = new HashMap<>();

        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);


        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId()) ){
                        if (!mapResultRestaurant.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultRestaurant.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }
                }
            }
            result.getRestaurants().addAll(mapResultRestaurant.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getRestaurants()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public WrapSupplierRegisterBookingInstAllResponse findAllRegisterBookingInstitutionGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request) {
        WrapSupplierRegisterBookingInstAllResponse result = new WrapSupplierRegisterBookingInstAllResponse();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setInstitutions(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingResponse> mapResultInstitution = new HashMap<>();

        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);


        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId()) ){
                        if (!mapResultInstitution.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultInstitution.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }
                }
            }
            result.getInstitutions().addAll(mapResultInstitution.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getInstitutions()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public WrapSupplierRegisterBookingLodgingAllResponse findAllRegisterBookingLodgingGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request) {
        WrapSupplierRegisterBookingLodgingAllResponse result = new WrapSupplierRegisterBookingLodgingAllResponse();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setLodgings(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingResponse> mapResultLodging = new HashMap<>();

        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);

        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId()) ){
                        if (!mapResultLodging.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultLodging.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }
                }
            }
            result.getLodgings().addAll(mapResultLodging.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getLodgings()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public WrapSupplierRegisterBookingLodgerAllResponse findAllRegisterBookingLodgerGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request) {
        WrapSupplierRegisterBookingLodgerAllResponse result = new WrapSupplierRegisterBookingLodgerAllResponse();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setLodges(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingResponse> mapResultLodge = new HashMap<>();

        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);

        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId()) ){
                        if (!mapResultLodge.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultLodge.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }
                }
            }
            result.getLodges().addAll(mapResultLodge.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getLodges()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public WrapSupplierRegisterBookingGlampingAllResponse findAllRegisterBookingGlampingGenerate(String idRegisterGroup, WrapSearchRegisterBookingRequest request) {
        WrapSupplierRegisterBookingGlampingAllResponse result = new WrapSupplierRegisterBookingGlampingAllResponse();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setGlampings(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingResponse> mapResultGlamping = new HashMap<>();

        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId()) ){
                        if (!mapResultGlamping.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponse = new WrapSupplierRegisterBookingResponse();
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setId(itineraryItemDomain.getSupplier().getId());
                                wrapSupplierRegisterBookingResponse.setSupplier(SupplierResponse.from(itineraryItemDomain.getSupplier()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setId(null);
                                wrapSupplierRegisterBookingResponse.setSupplier(null);
                            }
                            if (itineraryItemDomain.getSupplierType() != null &&
                                    itineraryItemDomain.getSupplierType().getId() != null &&
                                    !itineraryItemDomain.getSupplierType().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setSupplierType(SupplierTypeResponse.from(itineraryItemDomain.getSupplierType()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setSupplierType(null);
                            }
                            if (itineraryItemDomain.getSupplier() != null &&
                                    itineraryItemDomain.getSupplier().getPartner() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null &&
                                    itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId() != null &&
                                    !itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getId().isEmpty()){
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(DepartmentRegionResponse.from(itineraryItemDomain.getSupplier().getPartner().getAddress().getDepartmentRegion()));
                            }else{
                                wrapSupplierRegisterBookingResponse.setDepartmentRegion(null);
                            }
                            wrapSupplierRegisterBookingResponse.setStateBooking("Without Generate");
                            wrapSupplierRegisterBookingResponse.setStateBookingDescription("Sin Generar");
                            mapResultGlamping.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingResponse);
                        }
                    }
                }
            }
            result.getGlampings().addAll(mapResultGlamping.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                WrapSupplierRegisterBookingResponse wrapSupplierRegisterBookingResponseData = result.getGlampings()
                        .stream()
                        .filter(
                                wrapSupplierRegisterBookingResponse ->
                                        wrapSupplierRegisterBookingResponse.getSupplier().getId().equals(registerBookingBaseDomain.getSupplier().getId())
                        )
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBooking(registerBookingBaseDomain.getStateBooking());
                StateBookingEnum.StateBookingModel stateBookingModelData = EnumMappeableService
                        .map(StateBookingEnum.class)
                        .stream().filter(
                                stateBookingModel -> stateBookingModel.name().equals(registerBookingBaseDomain.getStateBooking()))
                        .findFirst().get();
                wrapSupplierRegisterBookingResponseData.setStateBookingDescription(stateBookingModelData.description());
                wrapSupplierRegisterBookingResponseData.setRegisterBookingBase(RegisterBookingBaseResponse.from(registerBookingBaseDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public RegisterBookingLodgingResponse generateRegisterBookingLodging(String idRegisterGroup, String idSupplier) {
        return registerBookingLodgingUseCase.generate(idRegisterGroup, idSupplier);
    }

    @Override
    @Transactional
    public RegisterBookingLodgerResponse generateRegisterBookingLodger(String idRegisterGroup, String idSupplier) {
        return registerBookingLodgerUseCase.generate(idRegisterGroup, idSupplier);
    }

    @Override
    @Transactional
    public RegisterBookingOperatorResponse generateRegisterBookingOperator(String idRegisterGroup, String idSupplier) {
        return registerBookingOperatorUseCase.generate(idRegisterGroup, idSupplier);
    }

    @Override
    @Transactional
    public RegisterBookingRestaurantResponse generateRegisterBookingRestaurant(String idRegisterGroup, String idSupplier) {
        return registerBookingRestaurantUseCase.generate(idRegisterGroup, idSupplier);
    }

    @Override
    @Transactional
    public RegisterBookingGlampingResponse generateRegisterBookingGlamping(String idRegisterGroup, String idSupplier) {
        return registerBookingGlampingUseCase.generate(idRegisterGroup, idSupplier);
    }

    @Override
    @Transactional
    public RegisterBookingInstitutionResponse generateRegisterBookingInstitution(String idRegisterGroup, String idSupplier) {
        return registerBookingInstitutionUseCase.generate(idRegisterGroup, idSupplier);
    }

    @Override
    @Transactional
    public List<WrapNoCuponCheckEntityResponse> findAllCuponByRegisterGroup(String idRegisterGroup) {
        List<WrapNoCuponCheckEntityResponse> result = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<RegisterBookingBaseDomain> registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);
        if (registerBookingBaseDomains != null && !registerBookingBaseDomains.isEmpty()) {
            registerBookingBaseDomains = registerBookingBaseDomains.stream().collect(Collectors.toList());
            for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
            ) {
                if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                    List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBooking(registerBookingBaseDomain.getId());
                    if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
                        registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
                        for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain: registerBookingItemLodgingDomains
                        ) {
                            if ( registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgingDomain.getNoCupon(), registerBookingItemLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgingDomain.getDateFrom(), registerBookingItemLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGING, false));
                            }
                        }
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                    RegisterBookingLodgerDomain registerBookingLodgerDomain = registerBookingLodgerRepo.findById(registerBookingBaseDomain.getId());
                    if ( registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingLodgerDomain.getNoCupon(), registerBookingLodgerDomain.getNoCuponPreImpress(),false, registerBookingLodgerDomain.getDateInitActivity(), registerBookingLodgerDomain.getDateTimeInitService(), SupplierTypeDomain.LODGE, false));
                    }
                    List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBooking(registerBookingBaseDomain.getId());
                    if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                        registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                        for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain: registerBookingItemLodgerLodgingDomains
                        ) {
                            if ( registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgerLodgingDomain.getNoCupon(), registerBookingItemLodgerLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgerLodgingDomain.getDateFrom(), registerBookingItemLodgerLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGE, true));
                            }
                        }
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                    RegisterBookingOperatorDomain registerBookingOperatorDomain = registerBookingOperatorRepo.findById(registerBookingBaseDomain.getId());
                    if ( registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingOperatorDomain.getNoCupon(), registerBookingOperatorDomain.getNoCuponPreImpress(),false, registerBookingOperatorDomain.getDateInitActivity(), registerBookingOperatorDomain.getDateTimeInitService(), SupplierTypeDomain.OPERATOR, false));
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                    RegisterBookingRestaurantDomain registerBookingRestaurantDomain = registerBookingRestaurantRepo.findById(registerBookingBaseDomain.getId());
                    if ( registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingRestaurantDomain.getNoCupon(), registerBookingRestaurantDomain.getNoCuponPreImpress(),false, registerBookingRestaurantDomain.getDateInitActivity(), registerBookingRestaurantDomain.getDateTimeInitService(), SupplierTypeDomain.RESTAURANT, false));
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                    List<RegisterBookingItemGlampingDomain> registerBookingItemGlampingDomains = registerBookingItemGlamRepo.searchAllByBooking(registerBookingBaseDomain.getId());
                    if (registerBookingItemGlampingDomains != null && !registerBookingItemGlampingDomains.isEmpty()) {
                        registerBookingItemGlampingDomains = registerBookingItemGlampingDomains.stream().collect(Collectors.toList());
                        for (RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain: registerBookingItemGlampingDomains
                        ) {
                            if ( registerBookingItemGlampingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemGlampingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemGlampingDomain.getNoCupon(), registerBookingItemGlampingDomain.getNoCuponPreImpress(),false, registerBookingItemGlampingDomain.getDateFrom(), registerBookingItemGlampingDomain.getDateTimeInitService(), SupplierTypeDomain.GLAMPING, false));
                            }
                        }
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                    RegisterBookingInstitutionDomain registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(registerBookingBaseDomain.getId());
                    if ( registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingInstitutionDomain.getNoCupon(), registerBookingInstitutionDomain.getNoCuponPreImpress(),false, registerBookingInstitutionDomain.getDateInitActivity(), registerBookingInstitutionDomain.getDateTimeInitService(), SupplierTypeDomain.INSTITUTION, false));
                    }
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    final Map<String, String> FIND_ALL_CUPON_FIELDS_SORT = Map.ofEntries(
            Map.entry("strRegisterBookingBase", "strRegisterBookingBase"),
            Map.entry("dateInitServiceStr", "dateInitServiceStr"),
            Map.entry("noCupon", "noCupon")
    );

    @Override
    @Transactional
    public List<WrapNoCuponCheckEntityResponse> findAllCuponByRegisterGroup(String idRegisterGroup, RegisterGroupAllCuponSearchRequest request) {
        List<WrapNoCuponCheckEntityResponse> result = new ArrayList<>();
        List<WrapNoCuponCheckEntityResponse> resultSort = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOneSimpleByIdentify("1");
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOneSimpleByIdentify("5");
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOneSimpleByIdentify("7");
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOneSimpleByIdentify("2");
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOneSimpleByIdentify("9");
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOneSimpleByIdentify("10");

        List<RegisterBookingBaseDomain> registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterBookingBaseAndActiveSimpleDomainCaseAllCupon(idRegisterGroup);
        if (registerBookingBaseDomains != null && !registerBookingBaseDomains.isEmpty()) {
            registerBookingBaseDomains = registerBookingBaseDomains.stream().collect(Collectors.toList());
            for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
            ) {
                if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                    List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                    if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
                        registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
                        for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain: registerBookingItemLodgingDomains
                        ) {
                            if ( registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgingDomain.getNoCupon(), registerBookingItemLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgingDomain.getDateFrom(), registerBookingItemLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGING, false));
                            }
                        }
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                    RegisterBookingLodgerDomain registerBookingLodgerDomain = registerBookingLodgerRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                    if ( registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingLodgerDomain.getNoCupon(), registerBookingLodgerDomain.getNoCuponPreImpress(),false, registerBookingLodgerDomain.getDateInitActivity(), registerBookingLodgerDomain.getDateTimeInitService(), SupplierTypeDomain.LODGE, false));
                    }
                    List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                    if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                        registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                        for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain: registerBookingItemLodgerLodgingDomains
                        ) {
                            if ( registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgerLodgingDomain.getNoCupon(), registerBookingItemLodgerLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgerLodgingDomain.getDateFrom(), registerBookingItemLodgerLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGE, true));
                            }
                        }
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                    RegisterBookingOperatorDomain registerBookingOperatorDomain = registerBookingOperatorRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                    if ( registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingOperatorDomain.getNoCupon(), registerBookingOperatorDomain.getNoCuponPreImpress(),false, registerBookingOperatorDomain.getDateInitActivity(), registerBookingOperatorDomain.getDateTimeInitService(), SupplierTypeDomain.OPERATOR, false));
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                    RegisterBookingRestaurantDomain registerBookingRestaurantDomain = registerBookingRestaurantRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                    if ( registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingRestaurantDomain.getNoCupon(), registerBookingRestaurantDomain.getNoCuponPreImpress(),false, registerBookingRestaurantDomain.getDateInitActivity(), registerBookingRestaurantDomain.getDateTimeInitService(), SupplierTypeDomain.RESTAURANT, false));
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                    List<RegisterBookingItemGlampingDomain> registerBookingItemGlampingDomains = registerBookingItemGlamRepo.searchAllByBooking(registerBookingBaseDomain.getId());
                    if (registerBookingItemGlampingDomains != null && !registerBookingItemGlampingDomains.isEmpty()) {
                        registerBookingItemGlampingDomains = registerBookingItemGlampingDomains.stream().collect(Collectors.toList());
                        for (RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain: registerBookingItemGlampingDomains
                        ) {
                            if ( registerBookingItemGlampingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemGlampingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemGlampingDomain.getNoCupon(), registerBookingItemGlampingDomain.getNoCuponPreImpress(),false, registerBookingItemGlampingDomain.getDateFrom(), registerBookingItemGlampingDomain.getDateTimeInitService(), SupplierTypeDomain.GLAMPING, false));
                            }
                        }
                    }
                }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                    RegisterBookingInstitutionDomain registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(registerBookingBaseDomain.getId());
                    if ( registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingInstitutionDomain.getNoCupon(), registerBookingInstitutionDomain.getNoCuponPreImpress(),false, registerBookingInstitutionDomain.getDateInitActivity(), registerBookingInstitutionDomain.getDateTimeInitService(), SupplierTypeDomain.INSTITUTION, false));
                    }
                }
            }
        }

        if (request.getSortOtherFormat() != null && request.getSortOtherFormat().length != 0) {
            for (SortRequest sortRequest : request.getSortOtherFormat()) {
                switch (sortRequest.getId()) {
                    case "strRegisterBookingBase" -> {
                        if (!sortRequest.getDesc()){
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getStrRegisterBookingBase));
                        }else{
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getStrRegisterBookingBase).reversed());
                        }
                    }
                    case "dateInitServiceStr" -> {
                        if (!sortRequest.getDesc()){
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getDateTimeInitService));
                            resultSort.addAll(this.transformFindAllCuponByRegisterGroup(result));
                        }else{
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getDateTimeInitService).reversed());
                            resultSort.addAll(this.transformFindAllCuponByRegisterGroup(result));
                        }
                    }
                    case "noCupon" -> {
                        if (!sortRequest.getDesc()){
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getNoCupon));
                        }else{
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getNoCupon).reversed());
                        }
                    }
                    default -> {

                    }
                }
            }
            resultSort.addAll(result);
        }else{
            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getDateTimeInitService));
            resultSort.addAll(this.transformFindAllCuponByRegisterGroup(result));
        }
        return resultSort;
    }

    @Override
    @Transactional
    public List<WrapNoCuponCheckEntityResponse> findAllCuponByRegisterGroup(String idRegisterGroup, RegisterGroupAllCuponSearchRequest request, String exportCuponTypePassengerBooking) {
        List<WrapNoCuponCheckEntityResponse> result = new ArrayList<>();
        List<WrapNoCuponCheckEntityResponse> resultSort = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOneSimpleByIdentify("1");
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOneSimpleByIdentify("5");
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOneSimpleByIdentify("7");
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOneSimpleByIdentify("2");
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOneSimpleByIdentify("9");
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOneSimpleByIdentify("10");

        List<RegisterBookingBaseDomain> registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterBookingBaseAndActiveSimpleDomainCaseAllCupon(idRegisterGroup);
        if (registerBookingBaseDomains != null && !registerBookingBaseDomains.isEmpty()) {
            registerBookingBaseDomains = registerBookingBaseDomains.stream().collect(Collectors.toList());
            for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
            ) {
                if (ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){
                    if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
                            registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain: registerBookingItemLodgingDomains
                            ) {
                                if (registerBookingItemLodgingDomain.getTcLocal() > 0){
                                    if ( registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgingDomain.getNoCupon(), registerBookingItemLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgingDomain.getDateFrom(), registerBookingItemLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGING, false));
                                    }
                                }
                            }
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                            registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain: registerBookingItemLodgerLodgingDomains
                            ) {
                                if (registerBookingItemLodgerLodgingDomain.getTcLocal() > 0){
                                    if ( registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgerLodgingDomain.getNoCupon(), registerBookingItemLodgerLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgerLodgingDomain.getDateFrom(), registerBookingItemLodgerLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGE, true));
                                    }
                                }
                            }
                        }
                    }
                }else if (ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){
                    if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
                            registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain: registerBookingItemLodgingDomains
                            ) {
                                if (
                                        registerBookingItemLodgingDomain.getCantPaxAdult() > 0 ||
                                                registerBookingItemLodgingDomain.getCantPaxChildren() > 0 ||
                                                registerBookingItemLodgingDomain.getTc() > 0 ||
                                                registerBookingItemLodgingDomain.getGuide() > 0
                                ){
                                    if ( registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgingDomain.getNoCupon(), registerBookingItemLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgingDomain.getDateFrom(), registerBookingItemLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGING, false));
                                    }
                                }
                            }
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                        RegisterBookingLodgerDomain registerBookingLodgerDomain = registerBookingLodgerRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if ( registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                            result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingLodgerDomain.getNoCupon(), registerBookingLodgerDomain.getNoCuponPreImpress(),false, registerBookingLodgerDomain.getDateInitActivity(), registerBookingLodgerDomain.getDateTimeInitService(), SupplierTypeDomain.LODGE, false));
                        }
                        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                            registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain: registerBookingItemLodgerLodgingDomains
                            ) {
                                if (
                                        registerBookingItemLodgerLodgingDomain.getCantPaxAdult() > 0 ||
                                                registerBookingItemLodgerLodgingDomain.getCantPaxChildren() > 0 ||
                                                registerBookingItemLodgerLodgingDomain.getTc() > 0 ||
                                                registerBookingItemLodgerLodgingDomain.getGuide() > 0
                                ){
                                    if ( registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                        result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemLodgerLodgingDomain.getNoCupon(), registerBookingItemLodgerLodgingDomain.getNoCuponPreImpress(),false, registerBookingItemLodgerLodgingDomain.getDateFrom(), registerBookingItemLodgerLodgingDomain.getDateTimeInitService(), SupplierTypeDomain.LODGE, true));
                                    }
                                }
                            }
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                        RegisterBookingOperatorDomain registerBookingOperatorDomain = registerBookingOperatorRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if ( registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                            result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingOperatorDomain.getNoCupon(), registerBookingOperatorDomain.getNoCuponPreImpress(),false, registerBookingOperatorDomain.getDateInitActivity(), registerBookingOperatorDomain.getDateTimeInitService(), SupplierTypeDomain.OPERATOR, false));
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = registerBookingRestaurantRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if ( registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                            result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingRestaurantDomain.getNoCupon(), registerBookingRestaurantDomain.getNoCuponPreImpress(),false, registerBookingRestaurantDomain.getDateInitActivity(), registerBookingRestaurantDomain.getDateTimeInitService(), SupplierTypeDomain.RESTAURANT, false));
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                        List<RegisterBookingItemGlampingDomain> registerBookingItemGlampingDomains = registerBookingItemGlamRepo.searchAllByBooking(registerBookingBaseDomain.getId());
                        if (registerBookingItemGlampingDomains != null && !registerBookingItemGlampingDomains.isEmpty()) {
                            registerBookingItemGlampingDomains = registerBookingItemGlampingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain: registerBookingItemGlampingDomains
                            ) {
                                if ( registerBookingItemGlampingDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingItemGlampingDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                                    result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingItemGlampingDomain.getNoCupon(), registerBookingItemGlampingDomain.getNoCuponPreImpress(),false, registerBookingItemGlampingDomain.getDateFrom(), registerBookingItemGlampingDomain.getDateTimeInitService(), SupplierTypeDomain.GLAMPING, false));
                                }
                            }
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(registerBookingBaseDomain.getId());
                        if ( registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress() != null && !registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress().equals("") ){
                            result.add(WrapNoCuponCheckEntityResponse.from(registerBookingBaseDomain, registerBookingInstitutionDomain.getNoCupon(), registerBookingInstitutionDomain.getNoCuponPreImpress(),false, registerBookingInstitutionDomain.getDateInitActivity(), registerBookingInstitutionDomain.getDateTimeInitService(), SupplierTypeDomain.INSTITUTION, false));
                        }
                    }
                }
            }
        }

        if (request.getSortOtherFormat() != null && request.getSortOtherFormat().length != 0) {
            for (SortRequest sortRequest : request.getSortOtherFormat()) {
                switch (sortRequest.getId()) {
                    case "strRegisterBookingBase" -> {
                        if (!sortRequest.getDesc()){
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getStrRegisterBookingBase));
                        }else{
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getStrRegisterBookingBase).reversed());
                        }
                    }
                    case "dateInitServiceStr" -> {
                        if (!sortRequest.getDesc()){
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getDateTimeInitService));
                            resultSort.addAll(this.transformFindAllCuponByRegisterGroup(result));
                        }else{
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getDateTimeInitService).reversed());
                            resultSort.addAll(this.transformFindAllCuponByRegisterGroup(result));
                        }
                    }
                    case "noCupon" -> {
                        if (!sortRequest.getDesc()){
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getNoCupon));
                        }else{
                            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getNoCupon).reversed());
                        }
                    }
                    default -> {

                    }
                }
            }
            resultSort.addAll(result);
        }else{
            result.sort(Comparator.comparing(WrapNoCuponCheckEntityResponse::getDateTimeInitService));
            resultSort.addAll(this.transformFindAllCuponByRegisterGroup(result));
        }
        return resultSort;
    }

    private List<WrapNoCuponCheckEntityResponse> transformFindAllCuponByRegisterGroup(List<WrapNoCuponCheckEntityResponse> result){
        List<WrapNoCuponCheckEntityResponse> resultSort = new ArrayList<>();
        Map<LocalDate, WrapNoCuponCheckEntityListResponse> map = new HashMap<>();
        if (result != null && !result.isEmpty()){
            for (WrapNoCuponCheckEntityResponse wrapNoCuponCheckEntityResponseItem : result){
                if (!map.containsKey(wrapNoCuponCheckEntityResponseItem.getDateInitService())){
                    map.put(wrapNoCuponCheckEntityResponseItem.getDateInitService(), new WrapNoCuponCheckEntityListResponse());
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).setLodges(new ArrayList<>());
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).setLodgesLodging(new ArrayList<>());
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).setLodgings(new ArrayList<>());
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).setOperators(new ArrayList<>());
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).setRestaurants(new ArrayList<>());
                }
                if (wrapNoCuponCheckEntityResponseItem.getSupplierType().equals(SupplierTypeDomain.LODGING)){
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).getLodgings().add(wrapNoCuponCheckEntityResponseItem);
                }else if (wrapNoCuponCheckEntityResponseItem.getSupplierType().equals(SupplierTypeDomain.LODGE)){
                    if (wrapNoCuponCheckEntityResponseItem.getLodgeLodging()){
                        map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).getLodges().add(wrapNoCuponCheckEntityResponseItem);
                    }else{
                        map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).getLodgesLodging().add(wrapNoCuponCheckEntityResponseItem);
                    }
                }else if (wrapNoCuponCheckEntityResponseItem.getSupplierType().equals(SupplierTypeDomain.OPERATOR)){
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).getOperators().add(wrapNoCuponCheckEntityResponseItem);
                }else if (wrapNoCuponCheckEntityResponseItem.getSupplierType().equals(SupplierTypeDomain.RESTAURANT)){
                    map.get(wrapNoCuponCheckEntityResponseItem.getDateInitService()).getRestaurants().add(wrapNoCuponCheckEntityResponseItem);
                }
            }
        }
        List<LocalDate> listDate = map.keySet().stream().collect(Collectors.toList());
        Collections.sort(listDate);
        for(LocalDate localDate : listDate){
            WrapNoCuponCheckEntityListResponse wrapNoCuponCheckEntityListResponse = map.get(localDate);
            if (wrapNoCuponCheckEntityListResponse.getOperators() != null && !wrapNoCuponCheckEntityListResponse.getOperators().isEmpty()){
                for (WrapNoCuponCheckEntityResponse temp : wrapNoCuponCheckEntityListResponse.getOperators()){
                    resultSort.add(temp);
                }
            }
            if (wrapNoCuponCheckEntityListResponse.getRestaurants() != null && !wrapNoCuponCheckEntityListResponse.getRestaurants().isEmpty()){
                for (WrapNoCuponCheckEntityResponse temp : wrapNoCuponCheckEntityListResponse.getRestaurants()){
                    resultSort.add(temp);
                }
            }
            if (wrapNoCuponCheckEntityListResponse.getLodgings() != null && !wrapNoCuponCheckEntityListResponse.getLodgings().isEmpty()){
                for (WrapNoCuponCheckEntityResponse temp : wrapNoCuponCheckEntityListResponse.getLodgings()){
                    resultSort.add(temp);
                }
            }
            if (wrapNoCuponCheckEntityListResponse.getLodges() != null && !wrapNoCuponCheckEntityListResponse.getLodges().isEmpty()){
                for (WrapNoCuponCheckEntityResponse temp : wrapNoCuponCheckEntityListResponse.getLodges()){
                    resultSort.add(temp);
                }
            }
            if (wrapNoCuponCheckEntityListResponse.getLodgesLodging() != null && !wrapNoCuponCheckEntityListResponse.getLodgesLodging().isEmpty()){
                for (WrapNoCuponCheckEntityResponse temp : wrapNoCuponCheckEntityListResponse.getLodgesLodging()){
                    resultSort.add(temp);
                }
            }
        }
        return resultSort;
    }



    @Override
    @Transactional
    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingOperator(String idRegisterGroup, String idRegisterBooking) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idRegisterBooking == null){
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = registerBookingOperatorRepo.findById(idRegisterBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        List<PassengerGroupResponse> result = new ArrayList<>();
        List<PassengerGroupDomain> passengerGroupDomains = passengerGroupRepo.searchAllByRegisterGroup(idRegisterGroup);
        if (passengerGroupDomains != null && !passengerGroupDomains.isEmpty()) {
            passengerGroupDomains = passengerGroupDomains.stream().collect(Collectors.toList());
        }

        List<PassengerBookingDomain> passengerBookingDomains = passengerBookingRepo.searchAllByRegisterBookingOperatorAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        if (passengerBookingDomains != null && !passengerBookingDomains.isEmpty()) {
            passengerBookingDomains = passengerBookingDomains.stream().collect(Collectors.toList());
        }
        for (PassengerGroupDomain itemPassengerGroupDomain: passengerGroupDomains
        ) {
            boolean find = false;
            for (PassengerBookingDomain itemPassengerBookingDomain: passengerBookingDomains
            ) {
                if (itemPassengerBookingDomain.getPassengerGroup().getId().equals(itemPassengerGroupDomain.getId())){
                    find = true;
                    break;
                }
            }
            if (!find){
                result.add(PassengerGroupResponse.from(itemPassengerGroupDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingRestaurant(String idRegisterGroup, String idRegisterBooking) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idRegisterBooking == null){
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = registerBookingRestaurantRepo.findById(idRegisterBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        List<PassengerGroupResponse> result = new ArrayList<>();
        List<PassengerGroupDomain> passengerGroupDomains = passengerGroupRepo.searchAllByRegisterGroup(idRegisterGroup);
        if (passengerGroupDomains != null && !passengerGroupDomains.isEmpty()) {
            passengerGroupDomains = passengerGroupDomains.stream().collect(Collectors.toList());
        }

        List<PassengerBookingDomain> passengerBookingDomains = passengerBookingRepo.searchAllByRegisterBookingRestaurantAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        if (passengerBookingDomains != null && !passengerBookingDomains.isEmpty()) {
            passengerBookingDomains = passengerBookingDomains.stream().collect(Collectors.toList());
        }
        for (PassengerGroupDomain itemPassengerGroupDomain: passengerGroupDomains
        ) {
            boolean find = false;
            for (PassengerBookingDomain itemPassengerBookingDomain: passengerBookingDomains
            ) {
                if (itemPassengerBookingDomain.getPassengerGroup().getId().equals(itemPassengerGroupDomain.getId())){
                    find = true;
                    break;
                }
            }
            if (!find){
                result.add(PassengerGroupResponse.from(itemPassengerGroupDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingLodging(String idRegisterGroup, String idRegisterBooking) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idRegisterBooking == null){
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = registerBookingLodgingRepo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        List<PassengerGroupResponse> result = new ArrayList<>();
        List<PassengerGroupDomain> passengerGroupDomains = passengerGroupRepo.searchAllByRegisterGroup(idRegisterGroup);
        if (passengerGroupDomains != null && !passengerGroupDomains.isEmpty()) {
            passengerGroupDomains = passengerGroupDomains.stream().collect(Collectors.toList());
        }

        List<PassengerBookingDomain> passengerBookingDomains = passengerBookingRepo.searchAllByRegisterBookingLodgingAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        if (passengerBookingDomains != null && !passengerBookingDomains.isEmpty()) {
            passengerBookingDomains = passengerBookingDomains.stream().collect(Collectors.toList());
        }
        for (PassengerGroupDomain itemPassengerGroupDomain: passengerGroupDomains
        ) {
            boolean find = false;
            for (PassengerBookingDomain itemPassengerBookingDomain: passengerBookingDomains
            ) {
                if (itemPassengerBookingDomain.getPassengerGroup().getId().equals(itemPassengerGroupDomain.getId())){
                    find = true;
                    break;
                }
            }
            if (!find){
                result.add(PassengerGroupResponse.from(itemPassengerGroupDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingGlamping(String idRegisterGroup, String idRegisterBooking) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idRegisterBooking == null){
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = registerBookingGlampingRepo.findById(idRegisterBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        List<PassengerGroupResponse> result = new ArrayList<>();
        List<PassengerGroupDomain> passengerGroupDomains = passengerGroupRepo.searchAllByRegisterGroup(idRegisterGroup);
        if (passengerGroupDomains != null && !passengerGroupDomains.isEmpty()) {
            passengerGroupDomains = passengerGroupDomains.stream().collect(Collectors.toList());
        }

        List<PassengerBookingDomain> passengerBookingDomains = passengerBookingRepo.searchAllByRegisterBookingGlampingAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        if (passengerBookingDomains != null && !passengerBookingDomains.isEmpty()) {
            passengerBookingDomains = passengerBookingDomains.stream().collect(Collectors.toList());
        }
        for (PassengerGroupDomain itemPassengerGroupDomain: passengerGroupDomains
        ) {
            boolean find = false;
            for (PassengerBookingDomain itemPassengerBookingDomain: passengerBookingDomains
            ) {
                if (itemPassengerBookingDomain.getPassengerGroup().getId().equals(itemPassengerGroupDomain.getId())){
                    find = true;
                    break;
                }
            }
            if (!find){
                result.add(PassengerGroupResponse.from(itemPassengerGroupDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public List<PassengerGroupResponse> findNotSelectedPassengerGroupByRegisterGroupAndBookingInstitution(String idRegisterGroup, String idRegisterBooking) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idRegisterBooking == null){
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(idRegisterBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        List<PassengerGroupResponse> result = new ArrayList<>();
        List<PassengerGroupDomain> passengerGroupDomains = passengerGroupRepo.searchAllByRegisterGroup(idRegisterGroup);
        if (passengerGroupDomains != null && !passengerGroupDomains.isEmpty()) {
            passengerGroupDomains = passengerGroupDomains.stream().collect(Collectors.toList());
        }

        List<PassengerBookingDomain> passengerBookingDomains = passengerBookingRepo.searchAllByRegisterBookingRestaurantAndRegisterGroup(idRegisterBooking, idRegisterGroup);
        if (passengerBookingDomains != null && !passengerBookingDomains.isEmpty()) {
            passengerBookingDomains = passengerBookingDomains.stream().collect(Collectors.toList());
        }
        for (PassengerGroupDomain itemPassengerGroupDomain: passengerGroupDomains
        ) {
            boolean find = false;
            for (PassengerBookingDomain itemPassengerBookingDomain: passengerBookingDomains
            ) {
                if (itemPassengerBookingDomain.getPassengerGroup().getId().equals(itemPassengerGroupDomain.getId())){
                    find = true;
                    break;
                }
            }
            if (!find){
                result.add(PassengerGroupResponse.from(itemPassengerGroupDomain));
            }
        }
        return result;
    }

    @Override
    @Transactional
    public byte[] exportPassengerGroupPdf(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        List<RegisterGroupDomain> listReport = new ArrayList<>();
        listReport.add(registerGroupDomain);

        List<PassengerGroupDomain> listSubReport = new ArrayList<>();
        listSubReport.addAll(passengerGroupUseCase.searchAllByRegisterGroupDomain(idRegisterGroup));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyUseCase.getMyActiveCompany());

        return jrPassengerGroupRepository.export(listReport, listSubReport, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportPassengerGroupXls(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        List<PassengerGroupDomain> list = new ArrayList<>();
        list.addAll(passengerGroupUseCase.searchAllByRegisterGroupDomain(idRegisterGroup));

        return jrPassengerGroupXlsRepository.export(list, ExportFormatEnum.XLSX.getName());
    }

    @Override
    @Transactional
    public byte[] exportAllCuponPdf(String idRegisterGroup, WrapCuponAllRequest wrapCuponAllRequest) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayList<RptCuponGeneric> list = new ArrayList<RptCuponGeneric>();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterBookingBaseAndActiveSimpleDomainCaseAllCupon(idRegisterGroup);
        if (registerBookingBaseDomains != null && !registerBookingBaseDomains.isEmpty()) {
            registerBookingBaseDomains = registerBookingBaseDomains.stream().collect(Collectors.toList());
            for (String itemNoCupon: wrapCuponAllRequest.getCupons()
            ) {
                for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
                ) {
                    RptCuponGeneric rptCuponGeneric = null;
                    if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())) {
                        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
                            registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain : registerBookingItemLodgingDomains
                            ) {
                                if ( registerBookingItemLodgingDomain.getNoCupon().equals(itemNoCupon) ){

                                    if (registerBookingItemLodgingDomain.getTcLocal() > 0){

                                        rptCuponGeneric = new RptCuponGeneric(  );

                                        rptCuponGeneric.setTypeGeneric("LODGING");

                                        rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                        rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                        rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                        rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                        if (registerBookingBaseDomain.getSupplier() != null){
                                            rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                            if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                    rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                }
                                            }
                                        }
                                        if (registerBookingBaseDomain.getEmployee() != null){
                                            rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                        }

                                        rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                        rptCuponGeneric.setNoCupon(registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress());
                                        RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                        rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgingDomain.getId());

                                        rptCuponGenericDetailSupplierLodging.setNightLodging( registerBookingItemLodgingDomain.getNoNightTcLocalGuide() != null ? registerBookingItemLodgingDomain.getNoNightTcLocalGuide().toString() : "" );
                                        rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgingDomain.getStrDateFrom());
                                        rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgingDomain.getStrDateTo());
                                        rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgingDomain.getStrRoomsTcLocal());

                                        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgingDomain.getId());
                                        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                        if (feedings != null && !feedings.isEmpty()){
                                            for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){

                                                if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC_LOCAL.getName())){
                                                    LocalDateTime localDateTime = null;
                                                    if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                        );
                                                    }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                0,
                                                                0,
                                                                0
                                                        );
                                                    }
                                                    if (!mapFeedings.containsKey(localDateTime)){
                                                        mapFeedings.put(localDateTime, new ArrayList<>());
                                                    }
                                                    mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                }

                                            }
                                        }
                                        List<String> dateTimeBreakFast = new ArrayList<>();
                                        List<String> dateTimeDinner = new ArrayList<>();
                                        List<String> dateTimeLunch = new ArrayList<>();
                                        List<String> dateTimeBoxLunch = new ArrayList<>();

                                        List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                        if (!mapFeedings.isEmpty()){
                                            for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                wrapFeedingItem.setCantBreakfast(0);
                                                wrapFeedingItem.setCantLunch(0);
                                                wrapFeedingItem.setCantDinner(0);
                                                wrapFeedingItem.setCantBoxLunch(0);
                                                List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                if (listFood != null && !listFood.isEmpty()){
                                                    for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                        if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                            wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                            if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                            wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                            if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                            wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                            if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                            wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                            if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }
                                                    }
                                                }
                                                listWrapFeedingItem.add(wrapFeedingItem);
                                            }
                                        }

                                        StringBuffer resultFods = new StringBuffer();
                                        StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                        StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                        StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                        StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                        listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                        for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                            if (wrapFeedingItem.getCantBreakfast() > 0){
                                                // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantLunch() > 0){
                                                // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantDinner() > 0){
                                                // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                            }
                                        }

                                        if ( resultFods.length() > 1 ){
                                            resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                        }

                                        registerBookingItemLodgingDomain.setStrFoods(resultFods.toString());

                                        rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgingDomain.getStrFoods());

                                        StringBuffer resultBreakFastFoods = new StringBuffer();
                                        StringBuffer resultDinnerFoods = new StringBuffer();
                                        StringBuffer resultLunchFoods = new StringBuffer();
                                        StringBuffer resultBoxLunchFoods = new StringBuffer();

                                        for (String localDateTime : dateTimeBreakFast){
                                            resultBreakFastFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBreakFastFoodsV1.length() > 1 ){
                                            resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                        for (String localDateTime : dateTimeDinner){
                                            resultDinnerFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultDinnerFoodsV1.length() > 1 ){
                                            resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                        for (String localDateTime : dateTimeLunch){
                                            resultLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultLunchFoodsV1.length() > 1 ){
                                            resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                        for (String localDateTime : dateTimeBoxLunch){
                                            resultBoxLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBoxLunchFoodsV1.length() > 1 ){
                                            resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                        rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgingDomain.getCategory());
                                        rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgingDomain.getStrTruncateObservation());
                                        rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                        rptCuponGenericDetailSupplierLodging.setShowExportService(false);
                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                        rptCuponGeneric.setDateInitService(registerBookingItemLodgingDomain.getDateFrom());
                                        rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgingDomain.getDateTimeInitService());

                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                        rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                        list.add(rptCuponGeneric);

                                    }


                                    if (
                                            registerBookingItemLodgingDomain.getCantPaxAdult() > 0 ||
                                                    registerBookingItemLodgingDomain.getCantPaxChildren() > 0 ||
                                                    registerBookingItemLodgingDomain.getTc() > 0 ||
                                                    registerBookingItemLodgingDomain.getGuide() > 0
                                    ){

                                        rptCuponGeneric = new RptCuponGeneric(  );

                                        rptCuponGeneric.setTypeGeneric("LODGING");

                                        rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                        rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                        rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                        rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                        if (registerBookingBaseDomain.getSupplier() != null){
                                            rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                            if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                    rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                }
                                            }
                                        }
                                        if (registerBookingBaseDomain.getEmployee() != null){
                                            rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                        }

                                        rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                        rptCuponGeneric.setNoCupon(registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress());
                                        RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                        rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgingDomain.getId());

                                        rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgingDomain.getNoNight() != null ? registerBookingItemLodgingDomain.getNoNight().toString() : "");
                                        rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgingDomain.getStrDateFrom());
                                        rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgingDomain.getStrDateTo());
                                        rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgingDomain.getStrRooms());

                                        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgingDomain.getId());
                                        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                        if (feedings != null && !feedings.isEmpty()){
                                            for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){
                                                if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.PAX.getName()) ||
                                                        registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC.getName()) ||
                                                        registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.GUIDE.getName())
                                                ){
                                                    LocalDateTime localDateTime = null;
                                                    if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                        );
                                                    }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                0,
                                                                0,
                                                                0
                                                        );
                                                    }
                                                    if (!mapFeedings.containsKey(localDateTime)){
                                                        mapFeedings.put(localDateTime, new ArrayList<>());
                                                    }
                                                    mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                }

                                            }
                                        }
                                        List<String> dateTimeBreakFast = new ArrayList<>();
                                        List<String> dateTimeDinner = new ArrayList<>();
                                        List<String> dateTimeLunch = new ArrayList<>();
                                        List<String> dateTimeBoxLunch = new ArrayList<>();

                                        List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                        if (!mapFeedings.isEmpty()){
                                            for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                wrapFeedingItem.setCantBreakfast(0);
                                                wrapFeedingItem.setCantLunch(0);
                                                wrapFeedingItem.setCantDinner(0);
                                                wrapFeedingItem.setCantBoxLunch(0);
                                                List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                if (listFood != null && !listFood.isEmpty()){
                                                    for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                        if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                            wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                            if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                            wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                            if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                            wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                            if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                            wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                            if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }
                                                    }
                                                }
                                                listWrapFeedingItem.add(wrapFeedingItem);
                                            }
                                        }

                                        StringBuffer resultFods = new StringBuffer();
                                        StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                        StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                        StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                        StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                        listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                        for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                            if (wrapFeedingItem.getCantBreakfast() > 0){
                                                // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantLunch() > 0){
                                                // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantDinner() > 0){
                                                // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                            }
                                        }

                                        if ( resultFods.length() > 1 ){
                                            resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                        }

                                        registerBookingItemLodgingDomain.setStrFoods(resultFods.toString());

                                        rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgingDomain.getStrFoods());

                                        StringBuffer resultBreakFastFoods = new StringBuffer();
                                        StringBuffer resultDinnerFoods = new StringBuffer();
                                        StringBuffer resultLunchFoods = new StringBuffer();
                                        StringBuffer resultBoxLunchFoods = new StringBuffer();

                                        for (String localDateTime : dateTimeBreakFast){
                                            resultBreakFastFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBreakFastFoodsV1.length() > 1 ){
                                            resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                        for (String localDateTime : dateTimeDinner){
                                            resultDinnerFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultDinnerFoodsV1.length() > 1 ){
                                            resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                        for (String localDateTime : dateTimeLunch){
                                            resultLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultLunchFoodsV1.length() > 1 ){
                                            resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                        for (String localDateTime : dateTimeBoxLunch){
                                            resultBoxLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBoxLunchFoodsV1.length() > 1 ){
                                            resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                        }
                                        rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                        rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgingDomain.getCategory());
                                        rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgingDomain.getStrTruncateObservation());
                                        rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                        rptCuponGenericDetailSupplierLodging.setShowExportService(true);

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                        rptCuponGeneric.setDateInitService(registerBookingItemLodgingDomain.getDateFrom());
                                        rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgingDomain.getDateTimeInitService());

                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                        rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                        list.add(rptCuponGeneric);

                                    }

                                }
                            }
                        }
                    } else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = registerBookingRestaurantRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if ( registerBookingRestaurantDomain.getNoCupon().equals(itemNoCupon) ){
                            rptCuponGeneric = new RptCuponGeneric(  );
                            rptCuponGeneric.setId(registerBookingRestaurantDomain.getId());
                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                            rptCuponGeneric.setRegisterGroup(registerBookingRestaurantDomain.getRegisterGroup().getName());
                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                            rptCuponGeneric.setPaxNumberPerson(registerBookingRestaurantDomain.getStrPaxCuponNewVersion());

                            if (registerBookingRestaurantDomain.getSupplier() != null){
                                rptCuponGeneric.setSupplierGeneric(registerBookingRestaurantDomain.getSupplier().getStrTruncateName());
                                if (registerBookingRestaurantDomain.getSupplier().getPartner() != null && registerBookingRestaurantDomain.getSupplier().getPartner().getAddress() != null){
                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                    if (registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                    }
                                }
                            }
                            if (registerBookingRestaurantDomain.getEmployee() != null){
                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingRestaurantDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingRestaurantDomain.getEmployee().getPerson().getFirstName());
                            }

                            rptCuponGeneric.setList(new ArrayList<>());

                            rptCuponGeneric.setTypeGeneric("RESTAURANT");
                            rptCuponGeneric.setNoCupon(registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress());

                            Integer cantGuide = 0;

                            List<RegisterBookingItemRestaurantDomain> registerBookingItemRestaurantDomains = registerBookingItemRestaurantRepo.searchAllByBookingItemRestaurantSimpleDomainCaseAllCupon(registerBookingRestaurantDomain.getId());
                            if (registerBookingItemRestaurantDomains != null && !registerBookingItemRestaurantDomains.isEmpty()) {
                                registerBookingItemRestaurantDomains = registerBookingItemRestaurantDomains.stream().collect(Collectors.toList());
                                Collections.sort(registerBookingItemRestaurantDomains);
                                for (RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain : registerBookingItemRestaurantDomains
                                ) {
                                    if (registerBookingItemRestaurantDomain.getGuide() != null && registerBookingItemRestaurantDomain.getGuide() > cantGuide){
                                        cantGuide = registerBookingItemRestaurantDomain.getGuide();
                                    }
                                    RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                    rptCuponGenericDetailSupplierOlr.setId(registerBookingItemRestaurantDomain.getId());
                                    rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemRestaurantDomain.getStrDateService());
                                    rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemRestaurantDomain.getStrTruncateAbbrevName());
                                    rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemRestaurantDomain.getStrTruncateObservation());
                                    rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                }
                            }

                            if ((registerBookingRestaurantDomain.getGuide() == null || registerBookingRestaurantDomain.getGuide() == 0) && cantGuide > 0){
                                rptCuponGeneric.setPaxNumberPerson(rptCuponGeneric.getPaxNumberPerson() +  "+ " + cantGuide + " GUIA ");
                            }

                            rptCuponGeneric.setDateInitService(registerBookingRestaurantDomain.getDateInitActivity());
                            rptCuponGeneric.setDateTimeInitService(registerBookingRestaurantDomain.getDateTimeInitService());
                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingRestaurantDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                            list.add(rptCuponGeneric);
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                        RegisterBookingOperatorDomain registerBookingOperatorDomain = registerBookingOperatorRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if ( registerBookingOperatorDomain.getNoCupon().equals(itemNoCupon) ){
                            rptCuponGeneric = new RptCuponGeneric(  );
                            rptCuponGeneric.setId(registerBookingOperatorDomain.getId());
                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                            rptCuponGeneric.setRegisterGroup(registerBookingOperatorDomain.getRegisterGroup().getName());
                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                            rptCuponGeneric.setPaxNumberPerson(registerBookingOperatorDomain.getStrPaxCuponNewVersion());

                            if (registerBookingOperatorDomain.getSupplier() != null){
                                rptCuponGeneric.setSupplierGeneric(registerBookingOperatorDomain.getSupplier().getStrTruncateName());
                                if (registerBookingOperatorDomain.getSupplier().getPartner() != null && registerBookingOperatorDomain.getSupplier().getPartner().getAddress() != null){
                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                    if (registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                    }
                                }
                            }
                            if (registerBookingOperatorDomain.getEmployee() != null){
                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingOperatorDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingOperatorDomain.getEmployee().getPerson().getFirstName());
                            }

                            rptCuponGeneric.setList(new ArrayList<>());

                            rptCuponGeneric.setTypeGeneric("OPERATOR");
                            rptCuponGeneric.setNoCupon(registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress());

                            Integer cantGuide = 0;

                            List<RegisterBookingItemOperatorDomain> registerBookingItemOperatorDomains = registerBookingItemOperatorRepo.searchAllByBookingItemOperatorSimpleDomainCaseAllCupon(registerBookingOperatorDomain.getId());
                            if (registerBookingItemOperatorDomains != null && !registerBookingItemOperatorDomains.isEmpty()) {
                                registerBookingItemOperatorDomains = registerBookingItemOperatorDomains.stream().collect(Collectors.toList());
                                Collections.sort(registerBookingItemOperatorDomains);
                                for (RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain : registerBookingItemOperatorDomains
                                ) {
                                    if (registerBookingItemOperatorDomain.getGuide() != null && registerBookingItemOperatorDomain.getGuide() > cantGuide){
                                        cantGuide = registerBookingItemOperatorDomain.getGuide();
                                    }
                                    RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                    rptCuponGenericDetailSupplierOlr.setId(registerBookingItemOperatorDomain.getId());
                                    rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemOperatorDomain.getStrDateService());
                                    rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemOperatorDomain.getStrServiceNameVoucher());
                                    rptCuponGenericDetailSupplierOlr.setTypeService(registerBookingItemOperatorDomain.getStrServiceTypeVoucher());
                                    rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemOperatorDomain.getStrTruncateObservation());
                                    rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                }
                            }

                            if ((registerBookingOperatorDomain.getGuide() == null || registerBookingOperatorDomain.getGuide() == 0) && cantGuide > 0){
                                rptCuponGeneric.setPaxNumberPerson(rptCuponGeneric.getPaxNumberPerson() +  "+ " + cantGuide + " GUIA ");
                            }

                            rptCuponGeneric.setDateInitService(registerBookingOperatorDomain.getDateInitActivity());
                            rptCuponGeneric.setDateTimeInitService(registerBookingOperatorDomain.getDateTimeInitService());
                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingOperatorDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                            list.add(rptCuponGeneric);
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                        RegisterBookingLodgerDomain registerBookingLodgerDomain = registerBookingLodgerRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if ( registerBookingLodgerDomain.getNoCupon().equals(itemNoCupon) ){
                            rptCuponGeneric = new RptCuponGeneric(  );
                            rptCuponGeneric.setId(registerBookingLodgerDomain.getId());
                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                            rptCuponGeneric.setRegisterGroup(registerBookingLodgerDomain.getRegisterGroup().getName());
                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                            rptCuponGeneric.setPaxNumberPerson(registerBookingLodgerDomain.getStrPaxCuponNewVersion());

                            if (registerBookingLodgerDomain.getSupplier() != null){
                                rptCuponGeneric.setSupplierGeneric(registerBookingLodgerDomain.getSupplier().getStrTruncateName());
                                if (registerBookingLodgerDomain.getSupplier().getPartner() != null && registerBookingLodgerDomain.getSupplier().getPartner().getAddress() != null){
                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                    if (registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                    }
                                }
                            }
                            if (registerBookingLodgerDomain.getEmployee() != null){
                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingLodgerDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingLodgerDomain.getEmployee().getPerson().getFirstName());
                            }

                            rptCuponGeneric.setList(new ArrayList<>());

                            rptCuponGeneric.setTypeGeneric("LODGE");
                            rptCuponGeneric.setNoCupon(registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress());

                            Integer cantGuide = 0;

                            List<RegisterBookingItemLodgerDomain> registerBookingItemLodgerDomains = registerBookingItemLodgerRepo.searchAllByBookingItemLodgerSimpleDomainCaseAllCupon(registerBookingLodgerDomain.getId());
                            if (registerBookingItemLodgerDomains != null && !registerBookingItemLodgerDomains.isEmpty()) {
                                registerBookingItemLodgerDomains = registerBookingItemLodgerDomains.stream().collect(Collectors.toList());
                                Collections.sort(registerBookingItemLodgerDomains);
                                for (RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain : registerBookingItemLodgerDomains
                                ) {
                                    if (registerBookingItemLodgerDomain.getGuide() != null && registerBookingItemLodgerDomain.getGuide() > cantGuide){
                                        cantGuide = registerBookingItemLodgerDomain.getGuide();
                                    }
                                    RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                    rptCuponGenericDetailSupplierOlr.setId(registerBookingItemLodgerDomain.getId());
                                    rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemLodgerDomain.getStrDateService());
                                    rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemLodgerDomain.getStrServiceNameVoucher());
                                    rptCuponGenericDetailSupplierOlr.setTypeService(registerBookingItemLodgerDomain.getStrServiceTypeVoucher());
                                    rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemLodgerDomain.getStrTruncateObservation());
                                    rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                }
                            }

                            if ((registerBookingLodgerDomain.getGuide() == null || registerBookingLodgerDomain.getGuide() == 0) && cantGuide > 0){
                                rptCuponGeneric.setPaxNumberPerson(rptCuponGeneric.getPaxNumberPerson() +  "+ " + cantGuide + " GUIA ");
                            }

                            rptCuponGeneric.setDateInitService(registerBookingLodgerDomain.getDateInitActivity());
                            rptCuponGeneric.setDateTimeInitService(registerBookingLodgerDomain.getDateTimeInitService());
                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingLodgerDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                            list.add(rptCuponGeneric);
                        }

                        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                        if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                            registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain : registerBookingItemLodgerLodgingDomains
                            ) {
                                if ( registerBookingItemLodgerLodgingDomain.getNoCupon().equals(itemNoCupon) ){

                                    if (registerBookingItemLodgerLodgingDomain.getTcLocal() > 0){

                                        rptCuponGeneric = new RptCuponGeneric(  );

                                        rptCuponGeneric.setTypeGeneric("LODGE_LODGING");

                                        rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                        rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                        rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                        rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                        if (registerBookingBaseDomain.getSupplier() != null){
                                            rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                            if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                    rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                }
                                            }
                                        }
                                        if (registerBookingBaseDomain.getEmployee() != null){
                                            rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                        }

                                        rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                        rptCuponGeneric.setNoCupon(registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress());
                                        RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                        rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgerLodgingDomain.getId());

                                        rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgerLodgingDomain.getNoNightTcLocalGuide() != null ? registerBookingItemLodgerLodgingDomain.getNoNightTcLocalGuide().toString() : "");
                                        rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgerLodgingDomain.getStrDateFrom());
                                        rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgerLodgingDomain.getStrDateTo());
                                        rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgerLodgingDomain.getStrRoomsTcLocal());

                                        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgerLodgingDomain.getId());
                                        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                        if (feedings != null && !feedings.isEmpty()){
                                            for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){
                                                if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC_LOCAL.getName())){
                                                    LocalDateTime localDateTime = null;
                                                    if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                        );
                                                    }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                0,
                                                                0,
                                                                0
                                                        );
                                                    }
                                                    if (!mapFeedings.containsKey(localDateTime)){
                                                        mapFeedings.put(localDateTime, new ArrayList<>());
                                                    }
                                                    mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                }
                                            }
                                        }

                                        List<String> dateTimeBreakFast = new ArrayList<>();
                                        List<String> dateTimeDinner = new ArrayList<>();
                                        List<String> dateTimeLunch = new ArrayList<>();
                                        List<String> dateTimeBoxLunch = new ArrayList<>();

                                        List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                        if (!mapFeedings.isEmpty()){
                                            for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                wrapFeedingItem.setCantBreakfast(0);
                                                wrapFeedingItem.setCantLunch(0);
                                                wrapFeedingItem.setCantDinner(0);
                                                wrapFeedingItem.setCantBoxLunch(0);
                                                List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                if (listFood != null && !listFood.isEmpty()){
                                                    for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                        if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                            wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                            if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                            wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                            if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                            wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                            if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                            wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                            if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }
                                                    }
                                                }
                                                listWrapFeedingItem.add(wrapFeedingItem);
                                            }
                                        }

                                        StringBuffer resultFods = new StringBuffer();
                                        StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                        StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                        StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                        StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                        listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                        for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                            if (wrapFeedingItem.getCantBreakfast() > 0){
                                                // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantLunch() > 0){
                                                // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantDinner() > 0){
                                                // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                            }
                                        }

                                        if ( resultFods.length() > 1 ){
                                            resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                        }

                                        registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());


                                        rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgerLodgingDomain.getStrFoods());

                                        StringBuffer resultBreakFastFoods = new StringBuffer();
                                        StringBuffer resultDinnerFoods = new StringBuffer();
                                        StringBuffer resultLunchFoods = new StringBuffer();
                                        StringBuffer resultBoxLunchFoods = new StringBuffer();

                                        for (String localDateTime : dateTimeBreakFast){
                                            resultBreakFastFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBreakFastFoodsV1.length() > 1 ){
                                            resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                        for (String localDateTime : dateTimeDinner){
                                            resultDinnerFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultDinnerFoodsV1.length() > 1 ){
                                            resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                        for (String localDateTime : dateTimeLunch){
                                            resultLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultLunchFoodsV1.length() > 1 ){
                                            resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                        for (String localDateTime : dateTimeBoxLunch){
                                            resultBoxLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBoxLunchFoodsV1.length() > 1 ){
                                            resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                        rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgerLodgingDomain.getCategory());
                                        rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgerLodgingDomain.getStrTruncateObservation());
                                        rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                        rptCuponGenericDetailSupplierLodging.setShowExportService(false);

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                        rptCuponGeneric.setDateInitService(registerBookingItemLodgerLodgingDomain.getDateFrom());
                                        rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgerLodgingDomain.getDateTimeInitService());

                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                        rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                        list.add(rptCuponGeneric);

                                    }

                                    if (
                                            registerBookingItemLodgerLodgingDomain.getCantPaxAdult() > 0 ||
                                                    registerBookingItemLodgerLodgingDomain.getCantPaxChildren() > 0 ||
                                                    registerBookingItemLodgerLodgingDomain.getTc() > 0 ||
                                                    registerBookingItemLodgerLodgingDomain.getGuide() > 0
                                    ){

                                        rptCuponGeneric = new RptCuponGeneric(  );

                                        rptCuponGeneric.setTypeGeneric("LODGE_LODGING");

                                        rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                        rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                        rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                        rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                        if (registerBookingBaseDomain.getSupplier() != null){
                                            rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                            if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                    rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                }
                                            }
                                        }
                                        if (registerBookingBaseDomain.getEmployee() != null){
                                            rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                        }

                                        rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                        rptCuponGeneric.setNoCupon(registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress());
                                        RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                        rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgerLodgingDomain.getId());

                                        rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgerLodgingDomain.getNoNight() != null ? registerBookingItemLodgerLodgingDomain.getNoNight().toString() : "");
                                        rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgerLodgingDomain.getStrDateFrom());
                                        rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgerLodgingDomain.getStrDateTo());
                                        rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgerLodgingDomain.getStrRooms());

                                        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgerLodgingDomain.getId());
                                        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                        if (feedings != null && !feedings.isEmpty()){
                                            for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){
                                                if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.PAX.getName()) ||
                                                        registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC.getName()) ||
                                                        registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.GUIDE.getName())
                                                ){
                                                    LocalDateTime localDateTime = null;
                                                    if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                        );
                                                    }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                        localDateTime = LocalDateTime.of(
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                0,
                                                                0,
                                                                0
                                                        );
                                                    }
                                                    if (!mapFeedings.containsKey(localDateTime)){
                                                        mapFeedings.put(localDateTime, new ArrayList<>());
                                                    }
                                                    mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                }
                                            }
                                        }

                                        List<String> dateTimeBreakFast = new ArrayList<>();
                                        List<String> dateTimeDinner = new ArrayList<>();
                                        List<String> dateTimeLunch = new ArrayList<>();
                                        List<String> dateTimeBoxLunch = new ArrayList<>();

                                        List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                        if (!mapFeedings.isEmpty()){
                                            for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                wrapFeedingItem.setCantBreakfast(0);
                                                wrapFeedingItem.setCantLunch(0);
                                                wrapFeedingItem.setCantDinner(0);
                                                wrapFeedingItem.setCantBoxLunch(0);
                                                List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                if (listFood != null && !listFood.isEmpty()){
                                                    for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                        if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                            wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                            if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                            wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                            if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                            wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                            if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                            wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                            if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                            }
                                                        }
                                                    }
                                                }
                                                listWrapFeedingItem.add(wrapFeedingItem);
                                            }
                                        }

                                        StringBuffer resultFods = new StringBuffer();
                                        StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                        StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                        StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                        StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                        listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                        for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                            if (wrapFeedingItem.getCantBreakfast() > 0){
                                                // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantLunch() > 0){
                                                // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                            }
                                            if (wrapFeedingItem.getCantDinner() > 0){
                                                // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                            }
                                        }

                                        if ( resultFods.length() > 1 ){
                                            resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                        }

                                        registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());

                                        rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgerLodgingDomain.getStrFoods());

                                        StringBuffer resultBreakFastFoods = new StringBuffer();
                                        StringBuffer resultDinnerFoods = new StringBuffer();
                                        StringBuffer resultLunchFoods = new StringBuffer();
                                        StringBuffer resultBoxLunchFoods = new StringBuffer();

                                        for (String localDateTime : dateTimeBreakFast){
                                            resultBreakFastFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBreakFastFoodsV1.length() > 1 ){
                                            resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                        for (String localDateTime : dateTimeDinner){
                                            resultDinnerFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultDinnerFoodsV1.length() > 1 ){
                                            resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                        for (String localDateTime : dateTimeLunch){
                                            resultLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultLunchFoodsV1.length() > 1 ){
                                            resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                        for (String localDateTime : dateTimeBoxLunch){
                                            resultBoxLunchFoods.append(localDateTime + ", " );
                                        }
                                        if ( resultBoxLunchFoodsV1.length() > 1 ){
                                            resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                        }

                                        rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                        rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgerLodgingDomain.getCategory());
                                        rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgerLodgingDomain.getStrTruncateObservation());
                                        rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                        rptCuponGenericDetailSupplierLodging.setShowExportService(true);

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                        rptCuponGeneric.setDateInitService(registerBookingItemLodgerLodgingDomain.getDateFrom());
                                        rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgerLodgingDomain.getDateTimeInitService());

                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                        rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                        list.add(rptCuponGeneric);

                                    }


                                }
                            }
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())) {
                        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBooking(registerBookingBaseDomain.getId());
                        if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                            registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                            for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain : registerBookingItemLodgerLodgingDomains
                            ) {
                                if ( registerBookingItemLodgerLodgingDomain.getNoCupon().equals(itemNoCupon) ){
                                    rptCuponGeneric = new RptCuponGeneric(  );

                                    rptCuponGeneric.setTypeGeneric("GLAMPING");

                                    rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                    rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                    rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                    rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                    rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCupon());

                                    if (registerBookingBaseDomain.getSupplier() != null){
                                        rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                        if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                            rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                            if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                            }
                                        }
                                    }
                                    if (registerBookingBaseDomain.getEmployee() != null){
                                        rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                    }

                                    rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                    rptCuponGeneric.setNoCupon(registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress());
                                    RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                    rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgerLodgingDomain.getId());

                                    rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgerLodgingDomain.getNoNight().toString());
                                    rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgerLodgingDomain.getStrDateFrom());
                                    rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgerLodgingDomain.getStrDateTo());
                                    rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgerLodgingDomain.getStrRooms());
                                    rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgerLodgingDomain.getStrFoods());
                                    // rptCuponGenericDetailSupplierLodging.setDinner(registerBookingItemLodgingDomain.getStrAlimentacCena());
                                    // rptCuponGenericDetailSupplierLodging.setLunch(registerBookingItemLodgingDomain.getStrAlimentacAlmuerzo());
                                    // rptCuponGenericDetailSupplierLodging.setBreakfast(registerBookingItemLodgingDomain.getStrAlimentacDesayuno());
                                    rptCuponGenericDetailSupplierLodging.setDinner("");
                                    rptCuponGenericDetailSupplierLodging.setLunch("");
                                    rptCuponGenericDetailSupplierLodging.setBreakfast("");
                                    rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgerLodgingDomain.getLodgingCategory().getName());
                                    rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgerLodgingDomain.getStrTruncateObservation());
                                    rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());

                                    rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCupon());

                                    rptCuponGeneric.setDateInitService(registerBookingItemLodgerLodgingDomain.getDateFrom());
                                    rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgerLodgingDomain.getDateTimeInitService());

                                    rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                    rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                    list.add(rptCuponGeneric);
                                }
                            }
                        }
                    }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(registerBookingBaseDomain.getId());
                        if ( registerBookingInstitutionDomain.getNoCupon().equals(itemNoCupon) ){
                            rptCuponGeneric = new RptCuponGeneric(  );
                            rptCuponGeneric.setId(registerBookingInstitutionDomain.getId());
                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                            rptCuponGeneric.setRegisterGroup(registerBookingInstitutionDomain.getRegisterGroup().getName());
                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                            rptCuponGeneric.setPaxNumberPerson(registerBookingInstitutionDomain.getStrPaxCuponNewVersion());

                            if (registerBookingInstitutionDomain.getSupplier() != null){
                                rptCuponGeneric.setSupplierGeneric(registerBookingInstitutionDomain.getSupplier().getStrTruncateName());
                                if (registerBookingInstitutionDomain.getSupplier().getPartner() != null && registerBookingInstitutionDomain.getSupplier().getPartner().getAddress() != null){
                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                    if (registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                    }
                                }
                            }
                            if (registerBookingInstitutionDomain.getEmployee() != null){
                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingInstitutionDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingInstitutionDomain.getEmployee().getPerson().getFirstName());
                            }

                            rptCuponGeneric.setList(new ArrayList<>());

                            rptCuponGeneric.setTypeGeneric("INSTITUTION");
                            rptCuponGeneric.setNoCupon(registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress());
                            List<RegisterBookingItemInstitutionDomain> registerBookingItemInstitutionDomains = registerBookingItemInstitutionRepo.searchAllByBooking(registerBookingInstitutionDomain.getId());
                            if (registerBookingItemInstitutionDomains != null && !registerBookingItemInstitutionDomains.isEmpty()) {
                                registerBookingItemInstitutionDomains = registerBookingItemInstitutionDomains.stream().collect(Collectors.toList());
                                for (RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain : registerBookingItemInstitutionDomains
                                ) {
                                    RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                    rptCuponGenericDetailSupplierOlr.setId(registerBookingItemInstitutionDomain.getId());
                                    rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemInstitutionDomain.getStrDateService());
                                    rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemInstitutionDomain.getStrTruncateAbbrevName());
                                    rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemInstitutionDomain.getStrTruncateObservation());
                                    rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                }
                            }
                            rptCuponGeneric.setDateInitService(registerBookingInstitutionDomain.getDateInitActivity());
                            rptCuponGeneric.setDateTimeInitService(registerBookingInstitutionDomain.getDateTimeInitService());
                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingInstitutionDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                            list.add(rptCuponGeneric);
                        }
                    }
                }
            }
        }

        List<RptCuponGeneric> listSort = new ArrayList<>();

        List<RptExportCuponAllGeneric> listexport = new ArrayList<>();

        Integer id = 1;

        if (wrapCuponAllRequest.getSortOtherFormat() != null && wrapCuponAllRequest.getSortOtherFormat().length != 0) {
            for (SortRequest sortRequest : wrapCuponAllRequest.getSortOtherFormat()) {
                switch (sortRequest.getId()) {
                    case "strRegisterBookingBase" -> {
                        if (!sortRequest.getDesc()){
                            list.sort(Comparator.comparing(RptCuponGeneric::getStrRegisterBookingBase));
                        }else{
                            list.sort(Comparator.comparing(RptCuponGeneric::getStrRegisterBookingBase).reversed());
                        }
                    }
                    case "dateInitServiceStr" -> {
                        if (!sortRequest.getDesc()){
                            list.sort(Comparator.comparing(RptCuponGeneric::getDateTimeInitService));
                            listSort.addAll(this.transformFindAllCuponByRegisterGroumpByExport(list));
                        }else{
                            list.sort(Comparator.comparing(RptCuponGeneric::getDateTimeInitService).reversed());
                            listSort.addAll(this.transformFindAllCuponByRegisterGroumpByExport(list));
                        }
                    }
                    case "noCupon" -> {
                        if (!sortRequest.getDesc()){
                            list.sort(Comparator.comparing(RptCuponGeneric::getNoCupon));
                        }else{
                            list.sort(Comparator.comparing(RptCuponGeneric::getNoCupon).reversed());
                        }
                    }
                    default -> {

                    }
                }
            }
        }else{
            list.sort(Comparator.comparing(RptCuponGeneric::getDateTimeInitService));
            listSort.addAll(this.transformFindAllCuponByRegisterGroumpByExport(list));
        }

        for (RptCuponGeneric rptCuponGeneric: listSort
        ) {
            if ( rptCuponGeneric.getTypeGeneric().equals("LODGING") ){

                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                    rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgingCuponAllRepository.compiler());
                    rptExportCuponAllGeneric.setList(new ArrayList<RptCuponGenericDetail>());
                    rptExportCuponAllGeneric.setId(id);
                    rptExportCuponAllGeneric.setIdentify(identify);
                    rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                    rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    listexport.add(rptExportCuponAllGeneric);
                    identify = identify + 1;
                    id = id + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("RESTAURANT") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("OPERATOR") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingOperatorCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingOperatorCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }
            }else if ( rptCuponGeneric.getTypeGeneric().equals("LODGE") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgerCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgerCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }
            }else if ( rptCuponGeneric.getTypeGeneric().equals("LODGE_LODGING") ){

                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                    rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgingCuponAllRepository.compiler());
                    rptExportCuponAllGeneric.setList(new ArrayList<RptCuponGenericDetail>());
                    rptExportCuponAllGeneric.setId(id);
                    rptExportCuponAllGeneric.setIdentify(identify);
                    rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                    rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    listexport.add(rptExportCuponAllGeneric);
                    identify = identify + 1;
                    id = id + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("GLAMPING") ){

                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                    rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgingCuponAllRepository.compiler());
                    rptExportCuponAllGeneric.setList(new ArrayList<RptCuponGenericDetail>());
                    rptExportCuponAllGeneric.setId(id);
                    rptExportCuponAllGeneric.setIdentify(identify);
                    rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                    rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    listexport.add(rptExportCuponAllGeneric);
                    identify = identify + 1;
                    id = id + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("INSTITUTION") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }

            }

        }
        return jrRegisterBookingCuponAllRepository.export(listexport, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportAllCuponPdf(String idRegisterGroup, WrapCuponAllRequest wrapCuponAllRequest, String exportCuponTypePassengerBooking) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayList<RptCuponGeneric> list = new ArrayList<RptCuponGeneric>();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterBookingBaseAndActiveSimpleDomainCaseAllCupon(idRegisterGroup);
        if (registerBookingBaseDomains != null && !registerBookingBaseDomains.isEmpty()) {
            registerBookingBaseDomains = registerBookingBaseDomains.stream().collect(Collectors.toList());
            for (String itemNoCupon: wrapCuponAllRequest.getCupons()
            ) {
                for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
                ) {
                    RptCuponGeneric rptCuponGeneric = null;

                    if (ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)) {

                        if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())) {
                            List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                            if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
                                registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
                                for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain : registerBookingItemLodgingDomains
                                ) {
                                    if ( registerBookingItemLodgingDomain.getNoCupon().equals(itemNoCupon) ){

                                        if (registerBookingItemLodgingDomain.getTcLocal() > 0){

                                            rptCuponGeneric = new RptCuponGeneric(  );

                                            rptCuponGeneric.setTypeGeneric("LODGING");

                                            rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                            rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                            if (registerBookingBaseDomain.getSupplier() != null){
                                                rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                                if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                    if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                    }
                                                }
                                            }
                                            if (registerBookingBaseDomain.getEmployee() != null){
                                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                            }

                                            rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                            rptCuponGeneric.setNoCupon(registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress());
                                            RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                            rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgingDomain.getId());

                                            rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgingDomain.getNoNightTcLocalGuide() != null ? registerBookingItemLodgingDomain.getNoNightTcLocalGuide().toString() : "");
                                            rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgingDomain.getStrDateFrom());
                                            rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgingDomain.getStrDateTo());
                                            rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgingDomain.getStrRoomsTcLocal());

                                            List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgingDomain.getId());
                                            Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                            if (feedings != null && !feedings.isEmpty()){
                                                for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){

                                                    if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC_LOCAL.getName())){
                                                        LocalDateTime localDateTime = null;
                                                        if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                            );
                                                        }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    0,
                                                                    0,
                                                                    0
                                                            );
                                                        }
                                                        if (!mapFeedings.containsKey(localDateTime)){
                                                            mapFeedings.put(localDateTime, new ArrayList<>());
                                                        }
                                                        mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                    }

                                                }
                                            }
                                            List<String> dateTimeBreakFast = new ArrayList<>();
                                            List<String> dateTimeDinner = new ArrayList<>();
                                            List<String> dateTimeLunch = new ArrayList<>();
                                            List<String> dateTimeBoxLunch = new ArrayList<>();

                                            List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                            if (!mapFeedings.isEmpty()){
                                                for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                    WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                    wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                    wrapFeedingItem.setCantBreakfast(0);
                                                    wrapFeedingItem.setCantLunch(0);
                                                    wrapFeedingItem.setCantDinner(0);
                                                    wrapFeedingItem.setCantBoxLunch(0);
                                                    List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                    if (listFood != null && !listFood.isEmpty()){
                                                        for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                            if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                                wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                                if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                                wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                                if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                                wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                                if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                                wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                                if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }
                                                        }
                                                    }
                                                    listWrapFeedingItem.add(wrapFeedingItem);
                                                }
                                            }

                                            StringBuffer resultFods = new StringBuffer();
                                            StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                            StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                            StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                            StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                            listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                            for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                                if (wrapFeedingItem.getCantBreakfast() > 0){
                                                    // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                    resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantLunch() > 0){
                                                    // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                    resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                    // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                    resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantDinner() > 0){
                                                    // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                    resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                                }
                                            }

                                            if ( resultFods.length() > 1 ){
                                                resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                            }

                                            registerBookingItemLodgingDomain.setStrFoods(resultFods.toString());

                                            rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgingDomain.getStrFoods());

                                            StringBuffer resultBreakFastFoods = new StringBuffer();
                                            StringBuffer resultDinnerFoods = new StringBuffer();
                                            StringBuffer resultLunchFoods = new StringBuffer();
                                            StringBuffer resultBoxLunchFoods = new StringBuffer();

                                            for (String localDateTime : dateTimeBreakFast){
                                                resultBreakFastFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBreakFastFoodsV1.length() > 1 ){
                                                resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                            for (String localDateTime : dateTimeDinner){
                                                resultDinnerFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultDinnerFoodsV1.length() > 1 ){
                                                resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                            for (String localDateTime : dateTimeLunch){
                                                resultLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultLunchFoodsV1.length() > 1 ){
                                                resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                            for (String localDateTime : dateTimeBoxLunch){
                                                resultBoxLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBoxLunchFoodsV1.length() > 1 ){
                                                resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                            rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgingDomain.getCategory());
                                            rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgingDomain.getStrTruncateObservation());
                                            rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                            rptCuponGenericDetailSupplierLodging.setShowExportService(false);
                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                            rptCuponGeneric.setDateInitService(registerBookingItemLodgingDomain.getDateFrom());
                                            rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgingDomain.getDateTimeInitService());

                                            rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                            list.add(rptCuponGeneric);

                                        }

                                    }
                                }
                            }
                        }   else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                            List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                            if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                                registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                                for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain : registerBookingItemLodgerLodgingDomains
                                ) {
                                    if ( registerBookingItemLodgerLodgingDomain.getNoCupon().equals(itemNoCupon) ){

                                        if (registerBookingItemLodgerLodgingDomain.getTcLocal() > 0){

                                            rptCuponGeneric = new RptCuponGeneric(  );

                                            rptCuponGeneric.setTypeGeneric("LODGE_LODGING");

                                            rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                            rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                            if (registerBookingBaseDomain.getSupplier() != null){
                                                rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                                if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                    if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                    }
                                                }
                                            }
                                            if (registerBookingBaseDomain.getEmployee() != null){
                                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                            }

                                            rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                            rptCuponGeneric.setNoCupon(registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress());
                                            RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                            rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgerLodgingDomain.getId());

                                            rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgerLodgingDomain.getNoNightTcLocalGuide() != null ? registerBookingItemLodgerLodgingDomain.getNoNightTcLocalGuide().toString() : "");
                                            rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgerLodgingDomain.getStrDateFrom());
                                            rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgerLodgingDomain.getStrDateTo());
                                            rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgerLodgingDomain.getStrRoomsTcLocal());

                                            List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgerLodgingDomain.getId());
                                            Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                            if (feedings != null && !feedings.isEmpty()){
                                                for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){
                                                    if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC_LOCAL.getName())){
                                                        LocalDateTime localDateTime = null;
                                                        if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                            );
                                                        }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    0,
                                                                    0,
                                                                    0
                                                            );
                                                        }
                                                        if (!mapFeedings.containsKey(localDateTime)){
                                                            mapFeedings.put(localDateTime, new ArrayList<>());
                                                        }
                                                        mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                    }
                                                }
                                            }

                                            List<String> dateTimeBreakFast = new ArrayList<>();
                                            List<String> dateTimeDinner = new ArrayList<>();
                                            List<String> dateTimeLunch = new ArrayList<>();
                                            List<String> dateTimeBoxLunch = new ArrayList<>();

                                            List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                            if (!mapFeedings.isEmpty()){
                                                for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                    WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                    wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                    wrapFeedingItem.setCantBreakfast(0);
                                                    wrapFeedingItem.setCantLunch(0);
                                                    wrapFeedingItem.setCantDinner(0);
                                                    wrapFeedingItem.setCantBoxLunch(0);
                                                    List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                    if (listFood != null && !listFood.isEmpty()){
                                                        for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                            if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                                wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                                if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                                wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                                if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                                wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                                if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                                wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                                if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }
                                                        }
                                                    }
                                                    listWrapFeedingItem.add(wrapFeedingItem);
                                                }
                                            }

                                            StringBuffer resultFods = new StringBuffer();
                                            StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                            StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                            StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                            StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                            listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                            for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                                if (wrapFeedingItem.getCantBreakfast() > 0){
                                                    // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                    resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantLunch() > 0){
                                                    // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                    resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                    // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                    resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantDinner() > 0){
                                                    // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                    resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                                }
                                            }

                                            if ( resultFods.length() > 1 ){
                                                resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                            }

                                            registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());


                                            rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgerLodgingDomain.getStrFoods());

                                            StringBuffer resultBreakFastFoods = new StringBuffer();
                                            StringBuffer resultDinnerFoods = new StringBuffer();
                                            StringBuffer resultLunchFoods = new StringBuffer();
                                            StringBuffer resultBoxLunchFoods = new StringBuffer();

                                            for (String localDateTime : dateTimeBreakFast){
                                                resultBreakFastFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBreakFastFoodsV1.length() > 1 ){
                                                resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                            for (String localDateTime : dateTimeDinner){
                                                resultDinnerFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultDinnerFoodsV1.length() > 1 ){
                                                resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                            for (String localDateTime : dateTimeLunch){
                                                resultLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultLunchFoodsV1.length() > 1 ){
                                                resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                            for (String localDateTime : dateTimeBoxLunch){
                                                resultBoxLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBoxLunchFoodsV1.length() > 1 ){
                                                resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                            rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgerLodgingDomain.getCategory());
                                            rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgerLodgingDomain.getStrTruncateObservation());
                                            rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                            rptCuponGenericDetailSupplierLodging.setShowExportService(false);

                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponOnlyTcLocal());

                                            rptCuponGeneric.setDateInitService(registerBookingItemLodgerLodgingDomain.getDateFrom());
                                            rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgerLodgingDomain.getDateTimeInitService());

                                            rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                            list.add(rptCuponGeneric);

                                        }

                                    }
                                }
                            }
                        }

                    }else if (ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){

                        if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())) {
                            List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                            if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
                                registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
                                for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain : registerBookingItemLodgingDomains
                                ) {
                                    if ( registerBookingItemLodgingDomain.getNoCupon().equals(itemNoCupon) ){

                                        if (
                                                registerBookingItemLodgingDomain.getCantPaxAdult() > 0 ||
                                                        registerBookingItemLodgingDomain.getCantPaxChildren() > 0 ||
                                                        registerBookingItemLodgingDomain.getTc() > 0 ||
                                                        registerBookingItemLodgingDomain.getGuide() > 0
                                        ){

                                            rptCuponGeneric = new RptCuponGeneric(  );

                                            rptCuponGeneric.setTypeGeneric("LODGING");

                                            rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                            rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                            if (registerBookingBaseDomain.getSupplier() != null){
                                                rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                                if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                    if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                    }
                                                }
                                            }
                                            if (registerBookingBaseDomain.getEmployee() != null){
                                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                            }

                                            rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                            rptCuponGeneric.setNoCupon(registerBookingItemLodgingDomain.getStrCuponIsNoInternAndImpress());
                                            RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                            rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgingDomain.getId());

                                            rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgingDomain.getNoNight() != null ? registerBookingItemLodgingDomain.getNoNight().toString() : "");
                                            rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgingDomain.getStrDateFrom());
                                            rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgingDomain.getStrDateTo());
                                            rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgingDomain.getStrRooms());

                                            List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgingDomain.getId());
                                            Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                            if (feedings != null && !feedings.isEmpty()){
                                                for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){
                                                    if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.PAX.getName()) ||
                                                            registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC.getName()) ||
                                                            registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.GUIDE.getName())
                                                    ){
                                                        LocalDateTime localDateTime = null;
                                                        if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                            );
                                                        }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    0,
                                                                    0,
                                                                    0
                                                            );
                                                        }
                                                        if (!mapFeedings.containsKey(localDateTime)){
                                                            mapFeedings.put(localDateTime, new ArrayList<>());
                                                        }
                                                        mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                    }

                                                }
                                            }
                                            List<String> dateTimeBreakFast = new ArrayList<>();
                                            List<String> dateTimeDinner = new ArrayList<>();
                                            List<String> dateTimeLunch = new ArrayList<>();
                                            List<String> dateTimeBoxLunch = new ArrayList<>();

                                            List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                            if (!mapFeedings.isEmpty()){
                                                for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                    WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                    wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                    wrapFeedingItem.setCantBreakfast(0);
                                                    wrapFeedingItem.setCantLunch(0);
                                                    wrapFeedingItem.setCantDinner(0);
                                                    wrapFeedingItem.setCantBoxLunch(0);
                                                    List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                    if (listFood != null && !listFood.isEmpty()){
                                                        for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                            if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                                wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                                if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                                wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                                if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                                wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                                if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                                wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                                if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }
                                                        }
                                                    }
                                                    listWrapFeedingItem.add(wrapFeedingItem);
                                                }
                                            }

                                            StringBuffer resultFods = new StringBuffer();
                                            StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                            StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                            StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                            StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                            listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                            for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                                if (wrapFeedingItem.getCantBreakfast() > 0){
                                                    // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                    resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantLunch() > 0){
                                                    // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                    resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                    // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                    resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantDinner() > 0){
                                                    // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                    resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                                }
                                            }

                                            if ( resultFods.length() > 1 ){
                                                resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                            }

                                            registerBookingItemLodgingDomain.setStrFoods(resultFods.toString());

                                            rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgingDomain.getStrFoods());

                                            StringBuffer resultBreakFastFoods = new StringBuffer();
                                            StringBuffer resultDinnerFoods = new StringBuffer();
                                            StringBuffer resultLunchFoods = new StringBuffer();
                                            StringBuffer resultBoxLunchFoods = new StringBuffer();

                                            for (String localDateTime : dateTimeBreakFast){
                                                resultBreakFastFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBreakFastFoodsV1.length() > 1 ){
                                                resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                            for (String localDateTime : dateTimeDinner){
                                                resultDinnerFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultDinnerFoodsV1.length() > 1 ){
                                                resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                            for (String localDateTime : dateTimeLunch){
                                                resultLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultLunchFoodsV1.length() > 1 ){
                                                resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                            for (String localDateTime : dateTimeBoxLunch){
                                                resultBoxLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBoxLunchFoodsV1.length() > 1 ){
                                                resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                            }
                                            rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                            rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgingDomain.getCategory());
                                            rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgingDomain.getStrTruncateObservation());
                                            rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                            rptCuponGenericDetailSupplierLodging.setShowExportService(true);

                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                            rptCuponGeneric.setDateInitService(registerBookingItemLodgingDomain.getDateFrom());
                                            rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgingDomain.getDateTimeInitService());

                                            rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                            list.add(rptCuponGeneric);

                                        }

                                    }
                                }
                            }
                        } else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                            RegisterBookingRestaurantDomain registerBookingRestaurantDomain = registerBookingRestaurantRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                            if ( registerBookingRestaurantDomain.getNoCupon().equals(itemNoCupon) ){
                                rptCuponGeneric = new RptCuponGeneric(  );
                                rptCuponGeneric.setId(registerBookingRestaurantDomain.getId());
                                rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                rptCuponGeneric.setRegisterGroup(registerBookingRestaurantDomain.getRegisterGroup().getName());
                                rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                rptCuponGeneric.setPaxNumberPerson(registerBookingRestaurantDomain.getStrPaxCuponNewVersion());

                                if (registerBookingRestaurantDomain.getSupplier() != null){
                                    rptCuponGeneric.setSupplierGeneric(registerBookingRestaurantDomain.getSupplier().getStrTruncateName());
                                    if (registerBookingRestaurantDomain.getSupplier().getPartner() != null && registerBookingRestaurantDomain.getSupplier().getPartner().getAddress() != null){
                                        rptCuponGeneric.setSupplierGenericAddress(registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                        if (registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                            rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingRestaurantDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                        }
                                    }
                                }
                                if (registerBookingRestaurantDomain.getEmployee() != null){
                                    rptCuponGeneric.setRegisterBookingResponsible(registerBookingRestaurantDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingRestaurantDomain.getEmployee().getPerson().getFirstName());
                                }

                                rptCuponGeneric.setList(new ArrayList<>());

                                rptCuponGeneric.setTypeGeneric("RESTAURANT");
                                rptCuponGeneric.setNoCupon(registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress());

                                Integer cantGuide = 0;

                                List<RegisterBookingItemRestaurantDomain> registerBookingItemRestaurantDomains = registerBookingItemRestaurantRepo.searchAllByBookingItemRestaurantSimpleDomainCaseAllCupon(registerBookingRestaurantDomain.getId());
                                if (registerBookingItemRestaurantDomains != null && !registerBookingItemRestaurantDomains.isEmpty()) {
                                    registerBookingItemRestaurantDomains = registerBookingItemRestaurantDomains.stream().collect(Collectors.toList());
                                    Collections.sort(registerBookingItemRestaurantDomains);
                                    for (RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain : registerBookingItemRestaurantDomains
                                    ) {
                                        if (registerBookingItemRestaurantDomain.getGuide() != null && registerBookingItemRestaurantDomain.getGuide() > cantGuide){
                                            cantGuide = registerBookingItemRestaurantDomain.getGuide();
                                        }
                                        RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                        rptCuponGenericDetailSupplierOlr.setId(registerBookingItemRestaurantDomain.getId());
                                        rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemRestaurantDomain.getStrDateService());
                                        rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemRestaurantDomain.getStrTruncateAbbrevName());
                                        rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemRestaurantDomain.getStrTruncateObservation());
                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                    }
                                }

                                if ((registerBookingRestaurantDomain.getGuide() == null || registerBookingRestaurantDomain.getGuide() == 0) && cantGuide > 0){
                                    rptCuponGeneric.setPaxNumberPerson(rptCuponGeneric.getPaxNumberPerson() +  "+ " + cantGuide + " GUIA ");
                                }

                                rptCuponGeneric.setDateInitService(registerBookingRestaurantDomain.getDateInitActivity());
                                rptCuponGeneric.setDateTimeInitService(registerBookingRestaurantDomain.getDateTimeInitService());
                                rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingRestaurantDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                list.add(rptCuponGeneric);
                            }
                        }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                            RegisterBookingOperatorDomain registerBookingOperatorDomain = registerBookingOperatorRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                            if ( registerBookingOperatorDomain.getNoCupon().equals(itemNoCupon) ){
                                rptCuponGeneric = new RptCuponGeneric(  );
                                rptCuponGeneric.setId(registerBookingOperatorDomain.getId());
                                rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                rptCuponGeneric.setRegisterGroup(registerBookingOperatorDomain.getRegisterGroup().getName());
                                rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                rptCuponGeneric.setPaxNumberPerson(registerBookingOperatorDomain.getStrPaxCuponNewVersion());

                                if (registerBookingOperatorDomain.getSupplier() != null){
                                    rptCuponGeneric.setSupplierGeneric(registerBookingOperatorDomain.getSupplier().getStrTruncateName());
                                    if (registerBookingOperatorDomain.getSupplier().getPartner() != null && registerBookingOperatorDomain.getSupplier().getPartner().getAddress() != null){
                                        rptCuponGeneric.setSupplierGenericAddress(registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                        if (registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                            rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingOperatorDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                        }
                                    }
                                }
                                if (registerBookingOperatorDomain.getEmployee() != null){
                                    rptCuponGeneric.setRegisterBookingResponsible(registerBookingOperatorDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingOperatorDomain.getEmployee().getPerson().getFirstName());
                                }

                                rptCuponGeneric.setList(new ArrayList<>());

                                rptCuponGeneric.setTypeGeneric("OPERATOR");
                                rptCuponGeneric.setNoCupon(registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress());

                                Integer cantGuide = 0;

                                List<RegisterBookingItemOperatorDomain> registerBookingItemOperatorDomains = registerBookingItemOperatorRepo.searchAllByBookingItemOperatorSimpleDomainCaseAllCupon(registerBookingOperatorDomain.getId());
                                if (registerBookingItemOperatorDomains != null && !registerBookingItemOperatorDomains.isEmpty()) {
                                    registerBookingItemOperatorDomains = registerBookingItemOperatorDomains.stream().collect(Collectors.toList());
                                    Collections.sort(registerBookingItemOperatorDomains);
                                    for (RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain : registerBookingItemOperatorDomains
                                    ) {
                                        if (registerBookingItemOperatorDomain.getGuide() != null && registerBookingItemOperatorDomain.getGuide() > cantGuide){
                                            cantGuide = registerBookingItemOperatorDomain.getGuide();
                                        }
                                        RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                        rptCuponGenericDetailSupplierOlr.setId(registerBookingItemOperatorDomain.getId());
                                        rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemOperatorDomain.getStrDateService());
                                        rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemOperatorDomain.getStrServiceNameVoucher());
                                        rptCuponGenericDetailSupplierOlr.setTypeService(registerBookingItemOperatorDomain.getStrServiceTypeVoucher());
                                        rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemOperatorDomain.getStrTruncateObservation());
                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                    }
                                }

                                if ((registerBookingOperatorDomain.getGuide() == null || registerBookingOperatorDomain.getGuide() == 0) && cantGuide > 0){
                                    rptCuponGeneric.setPaxNumberPerson(rptCuponGeneric.getPaxNumberPerson() +  "+ " + cantGuide + " GUIA ");
                                }

                                rptCuponGeneric.setDateInitService(registerBookingOperatorDomain.getDateInitActivity());
                                rptCuponGeneric.setDateTimeInitService(registerBookingOperatorDomain.getDateTimeInitService());
                                rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingOperatorDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                list.add(rptCuponGeneric);
                            }
                        }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                            RegisterBookingLodgerDomain registerBookingLodgerDomain = registerBookingLodgerRepo.findByIdRegisterSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                            if ( registerBookingLodgerDomain.getNoCupon().equals(itemNoCupon) ){
                                rptCuponGeneric = new RptCuponGeneric(  );
                                rptCuponGeneric.setId(registerBookingLodgerDomain.getId());
                                rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                rptCuponGeneric.setRegisterGroup(registerBookingLodgerDomain.getRegisterGroup().getName());
                                rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                rptCuponGeneric.setPaxNumberPerson(registerBookingLodgerDomain.getStrPaxCuponNewVersion());

                                if (registerBookingLodgerDomain.getSupplier() != null){
                                    rptCuponGeneric.setSupplierGeneric(registerBookingLodgerDomain.getSupplier().getStrTruncateName());
                                    if (registerBookingLodgerDomain.getSupplier().getPartner() != null && registerBookingLodgerDomain.getSupplier().getPartner().getAddress() != null){
                                        rptCuponGeneric.setSupplierGenericAddress(registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                        if (registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                            rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingLodgerDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                        }
                                    }
                                }
                                if (registerBookingLodgerDomain.getEmployee() != null){
                                    rptCuponGeneric.setRegisterBookingResponsible(registerBookingLodgerDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingLodgerDomain.getEmployee().getPerson().getFirstName());
                                }

                                rptCuponGeneric.setList(new ArrayList<>());

                                rptCuponGeneric.setTypeGeneric("LODGE");
                                rptCuponGeneric.setNoCupon(registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress());

                                Integer cantGuide = 0;

                                List<RegisterBookingItemLodgerDomain> registerBookingItemLodgerDomains = registerBookingItemLodgerRepo.searchAllByBookingItemLodgerSimpleDomainCaseAllCupon(registerBookingLodgerDomain.getId());
                                if (registerBookingItemLodgerDomains != null && !registerBookingItemLodgerDomains.isEmpty()) {
                                    registerBookingItemLodgerDomains = registerBookingItemLodgerDomains.stream().collect(Collectors.toList());
                                    Collections.sort(registerBookingItemLodgerDomains);
                                    for (RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain : registerBookingItemLodgerDomains
                                    ) {
                                        if (registerBookingItemLodgerDomain.getGuide() != null && registerBookingItemLodgerDomain.getGuide() > cantGuide){
                                            cantGuide = registerBookingItemLodgerDomain.getGuide();
                                        }
                                        RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                        rptCuponGenericDetailSupplierOlr.setId(registerBookingItemLodgerDomain.getId());
                                        rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemLodgerDomain.getStrDateService());
                                        rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemLodgerDomain.getStrServiceNameVoucher());
                                        rptCuponGenericDetailSupplierOlr.setTypeService(registerBookingItemLodgerDomain.getStrServiceTypeVoucher());
                                        rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemLodgerDomain.getStrTruncateObservation());
                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                    }
                                }

                                if ((registerBookingLodgerDomain.getGuide() == null || registerBookingLodgerDomain.getGuide() == 0) && cantGuide > 0){
                                    rptCuponGeneric.setPaxNumberPerson(rptCuponGeneric.getPaxNumberPerson() +  "+ " + cantGuide + " GUIA ");
                                }

                                rptCuponGeneric.setDateInitService(registerBookingLodgerDomain.getDateInitActivity());
                                rptCuponGeneric.setDateTimeInitService(registerBookingLodgerDomain.getDateTimeInitService());
                                rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingLodgerDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                list.add(rptCuponGeneric);
                            }

                            List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingBaseDomain.getId());
                            if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                                registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                                for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain : registerBookingItemLodgerLodgingDomains
                                ) {
                                    if ( registerBookingItemLodgerLodgingDomain.getNoCupon().equals(itemNoCupon) ){

                                        if (
                                                registerBookingItemLodgerLodgingDomain.getCantPaxAdult() > 0 ||
                                                        registerBookingItemLodgerLodgingDomain.getCantPaxChildren() > 0 ||
                                                        registerBookingItemLodgerLodgingDomain.getTc() > 0 ||
                                                        registerBookingItemLodgerLodgingDomain.getGuide() > 0
                                        ){

                                            rptCuponGeneric = new RptCuponGeneric(  );

                                            rptCuponGeneric.setTypeGeneric("LODGE_LODGING");

                                            rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                            rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                            rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                            rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                            if (registerBookingBaseDomain.getSupplier() != null){
                                                rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                                if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                    rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                    if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                        rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                    }
                                                }
                                            }
                                            if (registerBookingBaseDomain.getEmployee() != null){
                                                rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                            }

                                            rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                            rptCuponGeneric.setNoCupon(registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress());
                                            RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                            rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgerLodgingDomain.getId());

                                            rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgerLodgingDomain.getNoNight() != null ? registerBookingItemLodgerLodgingDomain.getNoNight().toString() : "");
                                            rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgerLodgingDomain.getStrDateFrom());
                                            rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgerLodgingDomain.getStrDateTo());
                                            rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgerLodgingDomain.getStrRooms());

                                            List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodgingSimpleDomainCaseAllCupon(registerBookingItemLodgerLodgingDomain.getId());
                                            Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

                                            if (feedings != null && !feedings.isEmpty()){
                                                for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){
                                                    if (registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.PAX.getName()) ||
                                                            registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.TC.getName()) ||
                                                            registerBookingItemLodgingFeedingDomain.getTypePassengerBooking().equals(TypePassengerBookingEnum.GUIDE.getName())
                                                    ){
                                                        LocalDateTime localDateTime = null;
                                                        if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() != null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getHour(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getMinute(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateTimeFeeding().getSecond()
                                                            );
                                                        }else if (registerBookingItemLodgingFeedingDomain.getDateFeeding() != null && registerBookingItemLodgingFeedingDomain.getDateTimeFeeding() == null){
                                                            localDateTime = LocalDateTime.of(
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getYear(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getMonth(),
                                                                    registerBookingItemLodgingFeedingDomain.getDateFeeding().getDayOfMonth(),
                                                                    0,
                                                                    0,
                                                                    0
                                                            );
                                                        }
                                                        if (!mapFeedings.containsKey(localDateTime)){
                                                            mapFeedings.put(localDateTime, new ArrayList<>());
                                                        }
                                                        mapFeedings.get(localDateTime).add(registerBookingItemLodgingFeedingDomain);
                                                    }
                                                }
                                            }

                                            List<String> dateTimeBreakFast = new ArrayList<>();
                                            List<String> dateTimeDinner = new ArrayList<>();
                                            List<String> dateTimeLunch = new ArrayList<>();
                                            List<String> dateTimeBoxLunch = new ArrayList<>();

                                            List<WrapFeedingItem> listWrapFeedingItem = new ArrayList<>();
                                            if (!mapFeedings.isEmpty()){
                                                for (LocalDateTime itemLocalDateTime : mapFeedings.keySet()){
                                                    WrapFeedingItem wrapFeedingItem = new WrapFeedingItem();
                                                    wrapFeedingItem.setLocalDateTime(itemLocalDateTime);
                                                    wrapFeedingItem.setCantBreakfast(0);
                                                    wrapFeedingItem.setCantLunch(0);
                                                    wrapFeedingItem.setCantDinner(0);
                                                    wrapFeedingItem.setCantBoxLunch(0);
                                                    List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemLocalDateTime);
                                                    if (listFood != null && !listFood.isEmpty()){
                                                        for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                                                            if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                                                                wrapFeedingItem.setCantBreakfast(wrapFeedingItem.getCantBreakfast() + item.getCant());
                                                                if (!dateTimeBreakFast.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBreakFast.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                                                                wrapFeedingItem.setCantLunch(wrapFeedingItem.getCantLunch() + item.getCant());
                                                                if (!dateTimeLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                                                                wrapFeedingItem.setCantDinner(wrapFeedingItem.getCantDinner() + item.getCant());
                                                                if (!dateTimeDinner.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeDinner.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                                                                wrapFeedingItem.setCantBoxLunch(wrapFeedingItem.getCantBoxLunch() + item.getCant());
                                                                if (!dateTimeBoxLunch.contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                                                    dateTimeBoxLunch.add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                                                                }
                                                            }
                                                        }
                                                    }
                                                    listWrapFeedingItem.add(wrapFeedingItem);
                                                }
                                            }

                                            StringBuffer resultFods = new StringBuffer();
                                            StringBuffer resultBreakFastFoodsV1 = new StringBuffer();
                                            StringBuffer resultDinnerFoodsV1 = new StringBuffer();
                                            StringBuffer resultLunchFoodsV1 = new StringBuffer();
                                            StringBuffer resultBoxLunchFoodsV1 = new StringBuffer();
                                            listWrapFeedingItem.sort(Comparator.comparing(WrapFeedingItem::getLocalDateTime));
                                            for (WrapFeedingItem wrapFeedingItem : listWrapFeedingItem){
                                                if (wrapFeedingItem.getCantBreakfast() > 0){
                                                    // resultFods.append(" DESAYUNOS BUFFET " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") + " );
                                                    resultBreakFastFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBreakfast() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantLunch() > 0){
                                                    // resultFods.append(" ALMUERZO " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") + " );
                                                    resultLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantBoxLunch() > 0){
                                                    // resultFods.append(" BOX LUNCH " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") + " );
                                                    resultBoxLunchFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantBoxLunch() + ") , " );
                                                }
                                                if (wrapFeedingItem.getCantDinner() > 0){
                                                    // resultFods.append(" CENA " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") + " );
                                                    resultDinnerFoodsV1.append(" " + LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShort_1(wrapFeedingItem.getLocalDateTime()) + " (" + wrapFeedingItem.getCantDinner() + ") , " );
                                                }
                                            }

                                            if ( resultFods.length() > 1 ){
                                                resultFods.delete(resultFods.length()-2, resultFods.length()-1);
                                            }

                                            registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());

                                            rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgerLodgingDomain.getStrFoods());

                                            StringBuffer resultBreakFastFoods = new StringBuffer();
                                            StringBuffer resultDinnerFoods = new StringBuffer();
                                            StringBuffer resultLunchFoods = new StringBuffer();
                                            StringBuffer resultBoxLunchFoods = new StringBuffer();

                                            for (String localDateTime : dateTimeBreakFast){
                                                resultBreakFastFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBreakFastFoodsV1.length() > 1 ){
                                                resultBreakFastFoodsV1.delete(resultBreakFastFoodsV1.length()-2, resultBreakFastFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setBreakfast(resultBreakFastFoodsV1.toString());

                                            for (String localDateTime : dateTimeDinner){
                                                resultDinnerFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultDinnerFoodsV1.length() > 1 ){
                                                resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setDinner(resultDinnerFoodsV1.toString());

                                            for (String localDateTime : dateTimeLunch){
                                                resultLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultLunchFoodsV1.length() > 1 ){
                                                resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setLunch(resultLunchFoodsV1.toString());

                                            for (String localDateTime : dateTimeBoxLunch){
                                                resultBoxLunchFoods.append(localDateTime + ", " );
                                            }
                                            if ( resultBoxLunchFoodsV1.length() > 1 ){
                                                resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                                            }

                                            rptCuponGenericDetailSupplierLodging.setBoxLunch(resultBoxLunchFoodsV1.toString());

                                            rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgerLodgingDomain.getCategory());
                                            rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgerLodgingDomain.getStrTruncateObservation());
                                            rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());
                                            rptCuponGenericDetailSupplierLodging.setShowExportService(true);

                                            rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCuponWithoutTcLocal());

                                            rptCuponGeneric.setDateInitService(registerBookingItemLodgerLodgingDomain.getDateFrom());
                                            rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgerLodgingDomain.getDateTimeInitService());

                                            rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                            rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                            list.add(rptCuponGeneric);

                                        }


                                    }
                                }
                            }
                        }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())) {
                            List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBooking(registerBookingBaseDomain.getId());
                            if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
                                registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
                                for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain : registerBookingItemLodgerLodgingDomains
                                ) {
                                    if ( registerBookingItemLodgerLodgingDomain.getNoCupon().equals(itemNoCupon) ){
                                        rptCuponGeneric = new RptCuponGeneric(  );

                                        rptCuponGeneric.setTypeGeneric("GLAMPING");

                                        rptCuponGeneric.setId(registerBookingBaseDomain.getId());
                                        rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                        rptCuponGeneric.setRegisterGroup(registerBookingBaseDomain.getRegisterGroup().getName());
                                        rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCupon());

                                        if (registerBookingBaseDomain.getSupplier() != null){
                                            rptCuponGeneric.setSupplierGeneric(registerBookingBaseDomain.getSupplier().getStrTruncateName());
                                            if (registerBookingBaseDomain.getSupplier().getPartner() != null && registerBookingBaseDomain.getSupplier().getPartner().getAddress() != null){
                                                rptCuponGeneric.setSupplierGenericAddress(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                                if (registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                                    rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingBaseDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                                }
                                            }
                                        }
                                        if (registerBookingBaseDomain.getEmployee() != null){
                                            rptCuponGeneric.setRegisterBookingResponsible(registerBookingBaseDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingBaseDomain.getEmployee().getPerson().getFirstName());
                                        }

                                        rptCuponGeneric.setList(new ArrayList<RptCuponGenericDetail>());

                                        rptCuponGeneric.setNoCupon(registerBookingItemLodgerLodgingDomain.getStrCuponIsNoInternAndImpress());
                                        RptCuponGenericDetailSupplierLodging rptCuponGenericDetailSupplierLodging = new RptCuponGenericDetailSupplierLodging();

                                        rptCuponGenericDetailSupplierLodging.setId(registerBookingItemLodgerLodgingDomain.getId());

                                        rptCuponGenericDetailSupplierLodging.setNightLodging(registerBookingItemLodgerLodgingDomain.getNoNight().toString());
                                        rptCuponGenericDetailSupplierLodging.setDateInit(registerBookingItemLodgerLodgingDomain.getStrDateFrom());
                                        rptCuponGenericDetailSupplierLodging.setDateEnd(registerBookingItemLodgerLodgingDomain.getStrDateTo());
                                        rptCuponGenericDetailSupplierLodging.setRooms(registerBookingItemLodgerLodgingDomain.getStrRooms());
                                        rptCuponGenericDetailSupplierLodging.setFoods(registerBookingItemLodgerLodgingDomain.getStrFoods());
                                        // rptCuponGenericDetailSupplierLodging.setDinner(registerBookingItemLodgingDomain.getStrAlimentacCena());
                                        // rptCuponGenericDetailSupplierLodging.setLunch(registerBookingItemLodgingDomain.getStrAlimentacAlmuerzo());
                                        // rptCuponGenericDetailSupplierLodging.setBreakfast(registerBookingItemLodgingDomain.getStrAlimentacDesayuno());
                                        rptCuponGenericDetailSupplierLodging.setDinner("");
                                        rptCuponGenericDetailSupplierLodging.setLunch("");
                                        rptCuponGenericDetailSupplierLodging.setBreakfast("");
                                        rptCuponGenericDetailSupplierLodging.setCategory(registerBookingItemLodgerLodgingDomain.getLodgingCategory().getName());
                                        rptCuponGenericDetailSupplierLodging.setObservation(registerBookingItemLodgerLodgingDomain.getStrTruncateObservation());
                                        rptCuponGenericDetailSupplierLodging.setCompanyName(companyDomain.getPartner().getName());

                                        rptCuponGeneric.setPaxNumberPerson(registerBookingItemLodgerLodgingDomain.getStrPaxCupon());

                                        rptCuponGeneric.setDateInitService(registerBookingItemLodgerLodgingDomain.getDateFrom());
                                        rptCuponGeneric.setDateTimeInitService(registerBookingItemLodgerLodgingDomain.getDateTimeInitService());

                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierLodging);

                                        rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingBaseDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                        list.add(rptCuponGeneric);
                                    }
                                }
                            }
                        }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                            RegisterBookingInstitutionDomain registerBookingInstitutionDomain = registerBookingInstitutionRepo.findById(registerBookingBaseDomain.getId());
                            if ( registerBookingInstitutionDomain.getNoCupon().equals(itemNoCupon) ){
                                rptCuponGeneric = new RptCuponGeneric(  );
                                rptCuponGeneric.setId(registerBookingInstitutionDomain.getId());
                                rptCuponGeneric.setDate(LocalDateTimeUtil.toLocalDateFormatShort(LocalDate.now()));
                                rptCuponGeneric.setRegisterGroup(registerBookingInstitutionDomain.getRegisterGroup().getName());
                                rptCuponGeneric.setStrRegisterBookingBase(registerBookingBaseDomain.getIdentify() + " - " + registerBookingBaseDomain.getSupplier().getStrAbbrevTypeSupplier() + " - " + registerBookingBaseDomain.getSupplier().getName());

                                rptCuponGeneric.setPaxNumberPerson(registerBookingInstitutionDomain.getStrPaxCuponNewVersion());

                                if (registerBookingInstitutionDomain.getSupplier() != null){
                                    rptCuponGeneric.setSupplierGeneric(registerBookingInstitutionDomain.getSupplier().getStrTruncateName());
                                    if (registerBookingInstitutionDomain.getSupplier().getPartner() != null && registerBookingInstitutionDomain.getSupplier().getPartner().getAddress() != null){
                                        rptCuponGeneric.setSupplierGenericAddress(registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getStrTruncateAddress());
                                        if (registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getDepartmentRegion() != null){
                                            rptCuponGeneric.setSupplierGenericDepartmentRegion(registerBookingInstitutionDomain.getSupplier().getPartner().getAddress().getDepartmentRegion().getStrTruncateName());
                                        }
                                    }
                                }
                                if (registerBookingInstitutionDomain.getEmployee() != null){
                                    rptCuponGeneric.setRegisterBookingResponsible(registerBookingInstitutionDomain.getEmployee().getPerson().getLastName() + ", " +registerBookingInstitutionDomain.getEmployee().getPerson().getFirstName());
                                }

                                rptCuponGeneric.setList(new ArrayList<>());

                                rptCuponGeneric.setTypeGeneric("INSTITUTION");
                                rptCuponGeneric.setNoCupon(registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress());
                                List<RegisterBookingItemInstitutionDomain> registerBookingItemInstitutionDomains = registerBookingItemInstitutionRepo.searchAllByBooking(registerBookingInstitutionDomain.getId());
                                if (registerBookingItemInstitutionDomains != null && !registerBookingItemInstitutionDomains.isEmpty()) {
                                    registerBookingItemInstitutionDomains = registerBookingItemInstitutionDomains.stream().collect(Collectors.toList());
                                    for (RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain : registerBookingItemInstitutionDomains
                                    ) {
                                        RptCuponGenericDetailSupplierOlr rptCuponGenericDetailSupplierOlr = new RptCuponGenericDetailSupplierOlr();
                                        rptCuponGenericDetailSupplierOlr.setId(registerBookingItemInstitutionDomain.getId());
                                        rptCuponGenericDetailSupplierOlr.setDateService(registerBookingItemInstitutionDomain.getStrDateService());
                                        rptCuponGenericDetailSupplierOlr.setNameService(registerBookingItemInstitutionDomain.getStrTruncateAbbrevName());
                                        rptCuponGenericDetailSupplierOlr.setObservationService(registerBookingItemInstitutionDomain.getStrTruncateObservation());
                                        rptCuponGeneric.getList().add(rptCuponGenericDetailSupplierOlr);
                                    }
                                }
                                rptCuponGeneric.setDateInitService(registerBookingInstitutionDomain.getDateInitActivity());
                                rptCuponGeneric.setDateTimeInitService(registerBookingInstitutionDomain.getDateTimeInitService());
                                rptCuponGeneric.setGastosExtrasNoIncluidos(registerBookingInstitutionDomain.getGastosExtrasNoIncluidos() ? 1 : 0);

                                list.add(rptCuponGeneric);
                            }
                        }

                    }

                }
            }
        }

        List<RptCuponGeneric> listSort = new ArrayList<>();

        List<RptExportCuponAllGeneric> listexport = new ArrayList<>();

        Integer id = 1;

        if (wrapCuponAllRequest.getSortOtherFormat() != null && wrapCuponAllRequest.getSortOtherFormat().length != 0) {
            for (SortRequest sortRequest : wrapCuponAllRequest.getSortOtherFormat()) {
                switch (sortRequest.getId()) {
                    case "strRegisterBookingBase" -> {
                        if (!sortRequest.getDesc()){
                            list.sort(Comparator.comparing(RptCuponGeneric::getStrRegisterBookingBase));
                        }else{
                            list.sort(Comparator.comparing(RptCuponGeneric::getStrRegisterBookingBase).reversed());
                        }
                    }
                    case "dateInitServiceStr" -> {
                        if (!sortRequest.getDesc()){
                            list.sort(Comparator.comparing(RptCuponGeneric::getDateTimeInitService));
                            listSort.addAll(this.transformFindAllCuponByRegisterGroumpByExport(list));
                        }else{
                            list.sort(Comparator.comparing(RptCuponGeneric::getDateTimeInitService).reversed());
                            listSort.addAll(this.transformFindAllCuponByRegisterGroumpByExport(list));
                        }
                    }
                    case "noCupon" -> {
                        if (!sortRequest.getDesc()){
                            list.sort(Comparator.comparing(RptCuponGeneric::getNoCupon));
                        }else{
                            list.sort(Comparator.comparing(RptCuponGeneric::getNoCupon).reversed());
                        }
                    }
                    default -> {

                    }
                }
            }
        }else{
            list.sort(Comparator.comparing(RptCuponGeneric::getDateTimeInitService));
            listSort.addAll(this.transformFindAllCuponByRegisterGroumpByExport(list));
        }

        for (RptCuponGeneric rptCuponGeneric: listSort
        ) {
            if ( rptCuponGeneric.getTypeGeneric().equals("LODGING") ){

                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                    rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgingCuponAllRepository.compiler());
                    rptExportCuponAllGeneric.setList(new ArrayList<RptCuponGenericDetail>());
                    rptExportCuponAllGeneric.setId(id);
                    rptExportCuponAllGeneric.setIdentify(identify);
                    rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                    rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    listexport.add(rptExportCuponAllGeneric);
                    identify = identify + 1;
                    id = id + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("RESTAURANT") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("OPERATOR") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingOperatorCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingOperatorCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }
            }else if ( rptCuponGeneric.getTypeGeneric().equals("LODGE") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgerCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgerCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }
            }else if ( rptCuponGeneric.getTypeGeneric().equals("LODGE_LODGING") ){

                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                    rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgingCuponAllRepository.compiler());
                    rptExportCuponAllGeneric.setList(new ArrayList<RptCuponGenericDetail>());
                    rptExportCuponAllGeneric.setId(id);
                    rptExportCuponAllGeneric.setIdentify(identify);
                    rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                    rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    listexport.add(rptExportCuponAllGeneric);
                    identify = identify + 1;
                    id = id + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("GLAMPING") ){

                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                    rptExportCuponAllGeneric.setInputStream(jrRegisterBookingLodgingCuponAllRepository.compiler());
                    rptExportCuponAllGeneric.setList(new ArrayList<RptCuponGenericDetail>());
                    rptExportCuponAllGeneric.setId(id);
                    rptExportCuponAllGeneric.setIdentify(identify);
                    rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                    rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    listexport.add(rptExportCuponAllGeneric);
                    identify = identify + 1;
                    id = id + 1;
                }

            }else if ( rptCuponGeneric.getTypeGeneric().equals("INSTITUTION") ){
                Integer count = 0;
                Integer identify = 1;

                for (RptCuponGenericDetail rptCuponGenericDetail: rptCuponGeneric.getList()
                ) {
                    if ( count == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else if ( count % 8 == 0){
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = new RptExportCuponAllGeneric();
                        rptExportCuponAllGeneric.setInputStream(jrRegisterBookingRestaurantCuponAllRepository.compiler());
                        rptExportCuponAllGeneric.setList(new ArrayList<>());
                        rptExportCuponAllGeneric.setId(id);
                        rptExportCuponAllGeneric.setIdentify(identify);
                        rptExportCuponAllGeneric.setRptCuponGeneric(rptCuponGeneric);
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                        listexport.add(rptExportCuponAllGeneric);
                        identify = identify + 1;
                        id = id + 1;
                    }else{
                        RptExportCuponAllGeneric temp = new RptExportCuponAllGeneric();
                        temp.setId(id-1);
                        RptExportCuponAllGeneric rptExportCuponAllGeneric = listexport.get(listexport.indexOf(temp));
                        rptExportCuponAllGeneric.getList().add(rptCuponGenericDetail);
                    }
                    count = count + 1;
                }

            }

        }
        return jrRegisterBookingCuponAllRepository.export(listexport, ExportFormatEnum.PDF.getName(), parameters);
    }

    private List<RptCuponGeneric> transformFindAllCuponByRegisterGroumpByExport(List<RptCuponGeneric> result){
        List<RptCuponGeneric> resultSort = new ArrayList<>();
        Map<LocalDate, RptCuponGenericList> map = new HashMap<>();
        if (result != null && !result.isEmpty()){
            for (RptCuponGeneric rptCuponGeneric : result){
                if (!map.containsKey(rptCuponGeneric.getDateInitService())){
                    map.put(rptCuponGeneric.getDateInitService(), new RptCuponGenericList());
                    map.get(rptCuponGeneric.getDateInitService()).setLodges(new ArrayList<>());
                    map.get(rptCuponGeneric.getDateInitService()).setLodgesLodging(new ArrayList<>());
                    map.get(rptCuponGeneric.getDateInitService()).setLodgings(new ArrayList<>());
                    map.get(rptCuponGeneric.getDateInitService()).setOperators(new ArrayList<>());
                    map.get(rptCuponGeneric.getDateInitService()).setRestaurants(new ArrayList<>());
                }
                if (rptCuponGeneric.getTypeGeneric().equals("LODGING")){
                    map.get(rptCuponGeneric.getDateInitService()).getLodgings().add(rptCuponGeneric);
                }else if (rptCuponGeneric.getTypeGeneric().equals("LODGE")){
                    map.get(rptCuponGeneric.getDateInitService()).getLodges().add(rptCuponGeneric);
                }else if (rptCuponGeneric.getTypeGeneric().equals("LODGE_LODGING")){
                    map.get(rptCuponGeneric.getDateInitService()).getLodgesLodging().add(rptCuponGeneric);
                }else if (rptCuponGeneric.getTypeGeneric().equals("OPERATOR")){
                    map.get(rptCuponGeneric.getDateInitService()).getOperators().add(rptCuponGeneric);
                }else if (rptCuponGeneric.getTypeGeneric().equals("RESTAURANT")){
                    map.get(rptCuponGeneric.getDateInitService()).getRestaurants().add(rptCuponGeneric);
                }
            }
        }
        List<LocalDate> listDate = map.keySet().stream().collect(Collectors.toList());
        Collections.sort(listDate);
        for(LocalDate localDate : listDate){
            RptCuponGenericList rptCuponGenericList = map.get(localDate);
            if (rptCuponGenericList.getOperators() != null && !rptCuponGenericList.getOperators().isEmpty()){
                for (RptCuponGeneric temp : rptCuponGenericList.getOperators()){
                    resultSort.add(temp);
                }
            }
            if (rptCuponGenericList.getRestaurants() != null && !rptCuponGenericList.getRestaurants().isEmpty()){
                for (RptCuponGeneric temp : rptCuponGenericList.getRestaurants()){
                    resultSort.add(temp);
                }
            }
            if (rptCuponGenericList.getLodgings() != null && !rptCuponGenericList.getLodgings().isEmpty()){
                for (RptCuponGeneric temp : rptCuponGenericList.getLodgings()){
                    resultSort.add(temp);
                }
            }
            if (rptCuponGenericList.getLodges() != null && !rptCuponGenericList.getLodges().isEmpty()){
                for (RptCuponGeneric temp : rptCuponGenericList.getLodges()){
                    resultSort.add(temp);
                }
            }
            if (rptCuponGenericList.getLodgesLodging() != null && !rptCuponGenericList.getLodgesLodging().isEmpty()){
                for (RptCuponGeneric temp : rptCuponGenericList.getLodgesLodging()){
                    resultSort.add(temp);
                }
            }
        }
        return resultSort;
    }

    @Override
    @Transactional
    public byte[] exportAllItineraryPdf(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());
        parameters.put("customer", registerGroupDomain.getCustomer().getName());
        parameters.put("idiom", registerGroupDomain.getIdiom().getName());
        parameters.put("registerGroup", registerGroupDomain.getIdentify() + " " + registerGroupDomain.getName());
        parameters.put("dateInitEnd", registerGroupDomain.getInitTourStr() + " - " + registerGroupDomain.getEndTourStr());

        List<PassengerGroupDomain> passengerGroupDomains = passengerGroupRepo.searchAllByRegisterGroup(idRegisterGroup);
        if (passengerGroupDomains != null && !passengerGroupDomains.isEmpty()) {
            passengerGroupDomains = passengerGroupDomains.stream().collect(Collectors.toList());
            StringBuffer result = new StringBuffer();
            for (PassengerGroupDomain passengerGroupDomain: passengerGroupDomains) {
                result.append((passengerGroupDomain.getTitlePersonDescription() != null ? passengerGroupDomain.getTitlePersonDescription() + " " : "") +  passengerGroupDomain.getPerson().getFirstName() + " " + passengerGroupDomain.getPerson().getLastName() + ", ");
            }
            result.delete(result.length()-2, result.length()-1);
            parameters.put("strPassengers", result.toString());
        }

        parameters.put("responsible", registerGroupDomain.getResponsible().getPerson().getFirstName() + " " + registerGroupDomain.getResponsible().getPerson().getLastName());
        parameters.put("strDateAperture", registerGroupDomain.getDateApertureStr());

        if (registerGroupDomain.getProgram() != null){
            parameters.put("program", registerGroupDomain.getProgram().getName());
        }else {
            parameters.put("program", "");
        }

        parameters.put("strPax", registerGroupDomain.getStrPax());

        List<ItineraryItemDomain> itineraryItemDomains = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseExportItinerary(idRegisterGroup);
        if (itineraryItemDomains != null && !itineraryItemDomains.isEmpty()) {
            itineraryItemDomains = itineraryItemDomains.stream().collect(Collectors.toList());
            Collections.sort(itineraryItemDomains);
        }

        return jrRegisterGroupItineraryRepository.export(itineraryItemDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportCantByResponsiblePdf(RegisterGroupSearchFilterRequest registerGroupSearchFilterRequest) {
        List<RegisterGroupDomain> registerGroupDomains = repo.searchFilterAll(registerGroupSearchFilterRequest);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyUseCase.getMyActiveCompany());

        String responsibleLabel = "TODOS LOS RESPONSABLES";
        String dateInitEndLabel = "";
        if (registerGroupSearchFilterRequest != null && registerGroupSearchFilterRequest.getFilter() != null) {
            Map<String, String> filter = registerGroupSearchFilterRequest.getFilter();
            String idResponsible = filter.get("idResponsible");
            if (idResponsible != null && !idResponsible.trim().isEmpty()) {
                EmployeeResponse employeeResponse = employeeUseCase.findById(idResponsible);
                if (employeeResponse != null && employeeResponse.getName() != null && !employeeResponse.getName().trim().isEmpty()) {
                    responsibleLabel = employeeResponse.getName();
                }
            }
            String initTourStart = filter.get("initTourStart");
            String initTourEnd = filter.get("initTourEnd");
            if (initTourStart != null && !initTourStart.trim().isEmpty() && initTourEnd != null && !initTourEnd.trim().isEmpty()) {
                LocalDate dtStart = dateUtil.formatIso8601ToLocalDate(initTourStart);
                LocalDate dtEnd = dateUtil.formatIso8601ToLocalDate(initTourEnd);
                if (dtStart != null && dtEnd != null) {
                    dateInitEndLabel = "Desde " + LocalDateTimeUtil.toLocalDateFormatShort(dtStart) + " Hasta " + LocalDateTimeUtil.toLocalDateFormatShort(dtEnd);
                }
            }
        }
        parameters.put("responsible", responsibleLabel);
        parameters.put("date_init_end", dateInitEndLabel);

        return jrRegisterGroupCantByResponsibleRepository.export(registerGroupDomains, parameters);
    }

    @Override
    @Transactional
    public List<TourResponse> findAllToursByItineraryDate(String idRegisterGroup, String idItineraryDate) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.findByIdSimpleDomainCaseDeleteTour(idItineraryDate);
        if (itineraryDateDomain == null) {
            throw new NotFoundException("No itinerary date found with id '%s'.".formatted(idItineraryDate));
        }
        List<TourResponse> tourResponses = new ArrayList<>();
        if (itineraryDateDomain.getTours() != null && !itineraryDateDomain.getTours().isEmpty()){
            for (TourDomain tourDomain: itineraryDateDomain.getTours()){
                tourResponses.add(TourResponse.from(tourDomain));
            }
        }
        return tourResponses;
    }

    @Override
    @Transactional
    public void removeToursByItineraryDate(String idRegisterGroup, String idItineraryDate, String idTour) {
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.findByIdSimpleDomainCaseDeleteTour(idItineraryDate);
        if (itineraryDateDomain == null) {
            throw new NotFoundException("No itinerary date found with id '%s'.".formatted(idItineraryDate));
        }
        Set<TourEntity> tours = new HashSet<>();
        for (TourDomain tourDomain: itineraryDateDomain.getTours()){
            if (!idTour.equals(tourDomain.getId())){
                TourEntity tourEntity = new TourEntity();
                tourEntity.setId(tourDomain.getId());
                tours.add(tourEntity);
            }
        }
        itineraryDateRepo.updateToursByIdSimpleDomainCaseDeleteTour(idItineraryDate, tours);

        List<ItineraryItemDomain> itineraryItemDomainListToDelete = itineraryItemRepo.searchAllByRegisterGroupAndItineraryDateSimpleDomainCaseDeleteTours(idRegisterGroup, idItineraryDate, idTour);
        if (itineraryItemDomainListToDelete != null && !itineraryItemDomainListToDelete.isEmpty()){
            itineraryItemDomainListToDelete = itineraryItemDomainListToDelete.stream().toList();
            for (ItineraryItemDomain itineraryItemDomain : itineraryItemDomainListToDelete){
                itineraryItemRepo.deleteById(itineraryItemDomain.getId());
                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                    this.eventPublisher.publishEvent(new ItineraryItemDeleteEvent(this, itineraryItemRepo.findById(itineraryItemDomain.getId())));
                }*/
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);
    }

    @Override
    @Transactional
    public void changeDateItineraryDate(String idRegisterGroup, ChangeItineraryDateRequest changeItineraryDateRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        // Se captura el initTour antiguo antes de actualizarlo, para propagar el corrimiento de fechas.
        LocalDate oldInitTour = registerGroupDomain.getInitTour();

        List<ItineraryDateDomain> listItineraryDate = itineraryDateUseCase.searchAllDomainByRegisterGroup(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()) {
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            Integer i = 0;
            for (ItineraryDateDomain itineraryDateDomain : listItineraryDateResult
            ) {
                itineraryDateDomain.setItineraryDate(changeItineraryDateRequest.getDateInit().plusDays(i));
                itineraryDateRepo.edit(itineraryDateDomain);

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, itineraryDateDomain));
                }
*/
                List<ItineraryItemDomain> listItineraryItem = itineraryItemUseCase.searchAllDomainSimpleDomainCaseGenerateBookingByRegisterGroupAndItineraryDate(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    LocalDateTime localDateTime = LocalDateTime.of(
                            itineraryDateDomain.getItineraryDate().getYear(),
                            itineraryDateDomain.getItineraryDate().getMonth(),
                            itineraryDateDomain.getItineraryDate().getDayOfMonth(),
                            itineraryItemDomain.getItineraryDateTime().getHour(),
                            itineraryItemDomain.getItineraryDateTime().getMinute(),
                            itineraryItemDomain.getItineraryDateTime().getSecond());

                    itineraryItemDomain.setItineraryDateTime(localDateTime);
                    itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itineraryItemDomain.getId(), localDateTime);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemDomain));
                    }*/
                }
                i++;
            }
        }
        registerGroupDomain.setInitTour(changeItineraryDateRequest.getDateInit());
        registerGroupDomain.setEndTour(changeItineraryDateRequest.getDateEnd());
        RegisterGroupDomain registerGroupDomainEdit = repo.edit(registerGroupDomain);

        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new RegisterGroupEditEvent(this, registerGroupDomainEdit));
        }*/

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        this.propagateChangeDateItinerary(idRegisterGroup, oldInitTour, changeItineraryDateRequest.getDateInit());

    }

    private void propagateChangeDateItinerary(String idRegisterGroup, LocalDate oldInitTour, LocalDate newDateInit) {
        if (oldInitTour == null || newDateInit == null) {
            return;
        }
        long deltaDays = ChronoUnit.DAYS.between(oldInitTour, newDateInit);
        if (deltaDays == 0) {
            return;
        }

        // Ordenes de servicio (solo items de tipo reserva)
        List<OrderServiceItemDomain> orderServiceItems = orderServiceItemRepo.searchAllByRegisterGroupCaseChangeDate(idRegisterGroup);
        if (orderServiceItems != null) {
            for (OrderServiceItemDomain item : orderServiceItems) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                boolean isBooking = OrderServiceTypeEnum.BOOKING.getName().equals(item.getOrderServiceType());
                if (isBooking) {
                    orderServiceItemRepo.updateChangeDateItineraryDate(
                            item.getId(),
                            shiftDate(item.getDateService(), deltaDays),
                            shiftDateTime(item.getDateTimeService(), deltaDays),
                            shiftDateTime(item.getDateTimePax(), deltaDays),
                            shiftDateTime(item.getDateTimeRecj(), deltaDays));
                }
            }
        }

        // Reservas (cabeceras) y sus items, por tipo de reserva (solo reservas activas)
        List<RegisterBookingBaseDomain> listRegisterBooking = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);
        if (listRegisterBooking == null || listRegisterBooking.isEmpty()) {
            return;
        }
        for (RegisterBookingBaseDomain registerBookingBaseDomain : listRegisterBooking) {
            if (registerBookingBaseDomain == null || registerBookingBaseDomain.getId() == null) {
                continue;
            }
            String idBooking = registerBookingBaseDomain.getId();
            String typeInheritance = registerBookingBaseDomain.getTypeInheritanceBooking();
            if (typeInheritance == null) {
                continue;
            }
            if (TypeInheritanceBookingEnum.REGISTER_BOOKING_OPERATOR.getName().equals(typeInheritance)) {
                this.propagateHeaderService(idBooking, deltaDays, registerBookingOperatorRepo);
                this.propagateItemsOperator(idBooking, deltaDays);
            } else if (TypeInheritanceBookingEnum.REGISTER_BOOKING_RESTAURANT.getName().equals(typeInheritance)) {
                this.propagateHeaderService(idBooking, deltaDays, registerBookingRestaurantRepo);
                this.propagateItemsRestaurant(idBooking, deltaDays);
            } else if (TypeInheritanceBookingEnum.REGISTER_BOOKING_INSTITUTION.getName().equals(typeInheritance)) {
                this.propagateHeaderService(idBooking, deltaDays, registerBookingInstitutionRepo);
                this.propagateItemsInstitution(idBooking, deltaDays);
            } else if (TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGING.getName().equals(typeInheritance)) {
                this.propagateHeaderLodging(idBooking, deltaDays, registerBookingLodgingRepo);
                this.propagateItemsLodging(idBooking, deltaDays);
            } else if (TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName().equals(typeInheritance)) {
                this.propagateHeaderLodging(idBooking, deltaDays, registerBookingGlampingRepo);
                this.propagateItemsGlamping(idBooking, deltaDays);
            } else if (TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGE.getName().equals(typeInheritance)) {
                this.propagateHeaderLodger(idBooking, deltaDays, registerBookingLodgerRepo);
                this.propagateItemsLodger(idBooking, deltaDays);
            }
        }
    }

    // ---------- Helpers de corrimiento ----------

    private LocalDate shiftDate(LocalDate date, long deltaDays) {
        if (date == null) {
            return null;
        }
        return date.plusDays(deltaDays);
    }

    private LocalDateTime shiftDateTime(LocalDateTime dateTime, long deltaDays) {
        if (dateTime == null) {
            return null;
        }
        return LocalDateTime.of(dateTime.toLocalDate().plusDays(deltaDays), dateTime.toLocalTime());
    }

    // ---------- Cabeceras ----------

    private void propagateHeaderService(String idBooking, long deltaDays, RegisterBookingOperatorRepo registerBookingOperatorRepo) {
        RegisterBookingOperatorDomain domain = registerBookingOperatorRepo.findById(idBooking);
        if (domain != null) {
            registerBookingOperatorRepo.updateChangeDateItineraryDate(
                    domain.getId(),
                    shiftDate(domain.getDateInitActivity(), deltaDays),
                    shiftDate(domain.getDateEndActivity(), deltaDays),
                    shiftDateTime(domain.getDateTimeInitService(), deltaDays));
        }
    }

    private void propagateHeaderService(String idBooking, long deltaDays, RegisterBookingRestaurantRepo registerBookingRestaurantRepo) {
        RegisterBookingRestaurantDomain domain = registerBookingRestaurantRepo.findById(idBooking);
        if (domain != null) {
            registerBookingRestaurantRepo.updateChangeDateItineraryDate(
                    domain.getId(),
                    shiftDate(domain.getDateInitActivity(), deltaDays),
                    shiftDate(domain.getDateEndActivity(), deltaDays),
                    shiftDateTime(domain.getDateTimeInitService(), deltaDays));
        }
    }

    private void propagateHeaderService(String idBooking, long deltaDays, RegisterBookingInstitutionRepo registerBookingInstitutionRepo) {
        RegisterBookingInstitutionDomain domain = registerBookingInstitutionRepo.findById(idBooking);
        if (domain != null) {
            registerBookingInstitutionRepo.updateChangeDateItineraryDate(
                    domain.getId(),
                    shiftDate(domain.getDateInitActivity(), deltaDays),
                    shiftDate(domain.getDateEndActivity(), deltaDays),
                    shiftDateTime(domain.getDateTimeInitService(), deltaDays));
        }
    }

    private void propagateHeaderLodging(String idBooking, long deltaDays, RegisterBookingLodgingRepo registerBookingLodgingRepo) {
        RegisterBookingLodgingDomain domain = registerBookingLodgingRepo.findById(idBooking);
        if (domain != null) {
            registerBookingLodgingRepo.updateChangeDateItineraryDate(
                    domain.getId(),
                    shiftDate(domain.getInitTour(), deltaDays),
                    shiftDate(domain.getEndTour(), deltaDays),
                    shiftDateTime(domain.getInitHourTour(), deltaDays),
                    shiftDateTime(domain.getEndHourTour(), deltaDays));
        }
    }

    private void propagateHeaderLodging(String idBooking, long deltaDays, RegisterBookingGlampingRepo registerBookingGlampingRepo) {
        RegisterBookingGlampingDomain domain = registerBookingGlampingRepo.findById(idBooking);
        if (domain != null) {
            registerBookingGlampingRepo.updateChangeDateItineraryDate(
                    domain.getId(),
                    shiftDate(domain.getInitTour(), deltaDays),
                    shiftDate(domain.getEndTour(), deltaDays),
                    shiftDateTime(domain.getInitHourTour(), deltaDays),
                    shiftDateTime(domain.getEndHourTour(), deltaDays));
        }
    }

    private void propagateHeaderLodger(String idBooking, long deltaDays, RegisterBookingLodgerRepo registerBookingLodgerRepo) {
        RegisterBookingLodgerDomain domain = registerBookingLodgerRepo.findById(idBooking);
        if (domain != null) {
            registerBookingLodgerRepo.updateChangeDateItineraryDate(
                    domain.getId(),
                    shiftDate(domain.getDateInitActivity(), deltaDays),
                    shiftDate(domain.getDateEndActivity(), deltaDays),
                    shiftDateTime(domain.getDateTimeInitService(), deltaDays),
                    shiftDate(domain.getInitTour(), deltaDays),
                    shiftDate(domain.getEndTour(), deltaDays),
                    shiftDateTime(domain.getInitHourTour(), deltaDays),
                    shiftDateTime(domain.getEndHourTour(), deltaDays));
        }
    }

    // ---------- Items ----------

    private void propagateItemsOperator(String idBooking, long deltaDays) {
        List<RegisterBookingItemOperatorDomain> items = registerBookingItemOperatorRepo.searchAllByBookingCaseChangeDate(idBooking);
        if (items != null) {
            for (RegisterBookingItemOperatorDomain item : items) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemOperatorRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateService(), deltaDays),
                        shiftDate(item.getDate(), deltaDays),
                        shiftDateTime(item.getDateTime(), deltaDays));
            }
        }
    }

    private void propagateItemsRestaurant(String idBooking, long deltaDays) {
        List<RegisterBookingItemRestaurantDomain> items = registerBookingItemRestaurantRepo.searchAllByBookingCaseChangeDate(idBooking);
        if (items != null) {
            for (RegisterBookingItemRestaurantDomain item : items) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemRestaurantRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateService(), deltaDays),
                        shiftDate(item.getDate(), deltaDays),
                        shiftDateTime(item.getDateTime(), deltaDays));
            }
        }
    }

    private void propagateItemsInstitution(String idBooking, long deltaDays) {
        List<RegisterBookingItemInstitutionDomain> items = registerBookingItemInstitutionRepo.searchAllByBookingCaseChangeDate(idBooking);
        if (items != null) {
            for (RegisterBookingItemInstitutionDomain item : items) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemInstitutionRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateService(), deltaDays),
                        shiftDate(item.getDate(), deltaDays),
                        shiftDateTime(item.getDateTime(), deltaDays));
            }
        }
    }

    private void propagateItemsLodger(String idBooking, long deltaDays) {
        List<RegisterBookingItemLodgerDomain> items = registerBookingItemLodgerRepo.searchAllByBookingCaseChangeDate(idBooking);
        if (items != null) {
            for (RegisterBookingItemLodgerDomain item : items) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemLodgerRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateService(), deltaDays),
                        shiftDate(item.getDate(), deltaDays),
                        shiftDateTime(item.getDateTime(), deltaDays));
            }
        }
        this.propagateItemsLodgerLodging(idBooking, deltaDays);
    }

    private void propagateItemsLodging(String idBooking, long deltaDays) {
        List<RegisterBookingItemLodgingDomain> items = registerBookingItemLodgingRepo.searchAllByBookingCaseChangeDate(idBooking);
        if (items != null) {
            for (RegisterBookingItemLodgingDomain item : items) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemLodgingRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateFrom(), deltaDays),
                        shiftDate(item.getDateTo(), deltaDays),
                        shiftDateTime(item.getDateTimeInitService(), deltaDays));
                this.propagateFeedingLodging(item.getId(), deltaDays);
            }
        }
    }

    private void propagateItemsGlamping(String idBooking, long deltaDays) {
        List<RegisterBookingItemGlampingDomain> items = registerBookingItemGlamRepo.searchAllByBookingCaseChangeDate(idBooking);
        if (items != null) {
            for (RegisterBookingItemGlampingDomain item : items) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemGlamRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateFrom(), deltaDays),
                        shiftDate(item.getDateTo(), deltaDays),
                        shiftDateTime(item.getDateTimeInitService(), deltaDays));
                this.propagateFeedingGlamping(item.getId(), deltaDays);
            }
        }
    }

    private void propagateItemsLodgerLodging(String idLodgerBooking, long deltaDays) {
        List<RegisterBookingItemLodgerLodgingDomain> items = registerBookingItemLodgerLodgingRepo.searchAllByBookingCaseChangeDate(idLodgerBooking);
        if (items != null) {
            for (RegisterBookingItemLodgerLodgingDomain item : items) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemLodgerLodgingRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateFrom(), deltaDays),
                        shiftDate(item.getDateTo(), deltaDays),
                        shiftDateTime(item.getDateTimeInitService(), deltaDays));
                this.propagateFeedingLodgerLodging(item.getId(), deltaDays);
            }
        }
    }

    private void propagateFeedingLodging(String idItemLodging, long deltaDays) {
        List<RegisterBookingItemLodgingFeedingDomain> feeding = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgingCaseChangeDate(idItemLodging);
        if (feeding != null) {
            for (RegisterBookingItemLodgingFeedingDomain item : feeding) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemLodgingFeedingRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateFeeding(), deltaDays),
                        shiftDateTime(item.getDateTimeFeeding(), deltaDays));
            }
        }
    }

    private void propagateFeedingLodgerLodging(String idItemLodgerLodging, long deltaDays) {
        List<RegisterBookingItemLodgingFeedingDomain> feeding = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodgingCaseChangeDate(idItemLodgerLodging);
        if (feeding != null) {
            for (RegisterBookingItemLodgingFeedingDomain item : feeding) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemLodgingFeedingRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateFeeding(), deltaDays),
                        shiftDateTime(item.getDateTimeFeeding(), deltaDays));
            }
        }
    }

    private void propagateFeedingGlamping(String idItemGlamping, long deltaDays) {
        List<RegisterBookingItemLodgingFeedingDomain> feeding = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemGlampingCaseChangeDate(idItemGlamping);
        if (feeding != null) {
            for (RegisterBookingItemLodgingFeedingDomain item : feeding) {
                if (item == null || item.getId() == null) {
                    continue;
                }
                registerBookingItemLodgingFeedingRepo.updateChangeDateItineraryDate(
                        item.getId(),
                        shiftDate(item.getDateFeeding(), deltaDays),
                        shiftDateTime(item.getDateTimeFeeding(), deltaDays));
            }
        }
    }

    @Override
    @Transactional
    public void changeDateItineraryDay(String idRegisterGroup, ChangeItineraryDayRequest changeItineraryDayRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Integer days = changeItineraryDayRequest.getDays();
        LocalDate lastDate = registerGroupDomain.getEndTour().plusDays(days);
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> {
                    ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
                    Integer indexDay = registerGroupDomain.getDays() + item + 1;
                    itineraryDateDomain.setName("");
                    itineraryDateDomain.setItineraryDate(registerGroupDomain.getEndTour().plusDays(item + 1));
                    itineraryDateDomain.setRegisterGroup(registerGroupDomain);
                    itineraryDateDomain.setIndex(indexDay);
                    itineraryDateDomain.setItemItinerary(indexDay.toString());
                    itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                    ItineraryDateDomain itineraryDateDomainCreated = itineraryDateRepo.create(itineraryDateDomain);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryDateCreateEvent(this, itineraryDateDomainCreated));
                    }*/
                });

        registerGroupDomain.setDays(registerGroupDomain.getDays() + changeItineraryDayRequest.getDays());
        registerGroupDomain.setEndTour(lastDate);

        RegisterGroupDomain registerGroupDomainEdit = repo.edit(registerGroupDomain);

        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new RegisterGroupEditEvent(this, registerGroupDomainEdit));
        }*/

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void changeDateItineraryDayInit(String idRegisterGroup, ChangeItineraryDayRequest changeItineraryDayRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<ItineraryDateDomain> listItineraryDateForUpdateOrder = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(idRegisterGroup);

        Integer days = changeItineraryDayRequest.getDays();
        LocalDate initDate = registerGroupDomain.getInitTour().minusDays(days);
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> {
                    ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
                    Integer indexDay = item + 1;
                    itineraryDateDomain.setName("");
                    itineraryDateDomain.setItineraryDate(initDate.plusDays(item));
                    itineraryDateDomain.setRegisterGroup(registerGroupDomain);
                    itineraryDateDomain.setIndex(indexDay);
                    itineraryDateDomain.setItemItinerary(indexDay.toString());
                    itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                    ItineraryDateDomain itineraryDateDomainCreated = itineraryDateRepo.create(itineraryDateDomain);

                });

        for (ItineraryDateDomain itineraryDateDomain : listItineraryDateForUpdateOrder){
            days = days + 1;
            itineraryDateRepo.updateIndexByIdSimpleDomainCaseUpdateOrderItineraryDate(itineraryDateDomain.getId(), days);
        }

        registerGroupDomain.setDays(registerGroupDomain.getDays() + changeItineraryDayRequest.getDays());
        registerGroupDomain.setInitTour(initDate);

        RegisterGroupDomain registerGroupDomainEdit = repo.edit(registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void changeDateItineraryDayMiddle(String idRegisterGroup, ChangeItineraryDayRequest changeItineraryDayRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        List<ItineraryDateDomain> listItineraryDateForUpdateOrder = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(idRegisterGroup).stream().collect(Collectors.toList());
        listItineraryDateForUpdateOrder.sort(Comparator.comparing(ItineraryDateDomain::getIndex));
        LocalDate dateInitPlus = listItineraryDateForUpdateOrder.get(0).getItineraryDate();

        Integer days = changeItineraryDayRequest.getDays();
        ItineraryDateDomain dbItineraryDateTemp = itineraryDateRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(changeItineraryDayRequest.getFrom());
        LocalDate initDate = dbItineraryDateTemp.getItineraryDate();
        AtomicReference<Integer> indexDay = new AtomicReference<>(dbItineraryDateTemp.getIndex());

        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> {
                    ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
                    itineraryDateDomain.setName("");
                    itineraryDateDomain.setItineraryDate(initDate.plusDays(item + 1));
                    itineraryDateDomain.setRegisterGroup(registerGroupDomain);
                    indexDay.set(indexDay.get() + 1);
                    itineraryDateDomain.setIndex(indexDay.get());
                    itineraryDateDomain.setItemItinerary(indexDay.get().toString());
                    itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                    ItineraryDateDomain itineraryDateDomainCreated = itineraryDateRepo.create(itineraryDateDomain);

                });

        Integer indexCurrent = 0;
        Integer indexCurrentPass = 0;

        ItineraryDateDomain source = itineraryDateRepo.findByIdSimpleDomainCaseDeleteItineraryDate(changeItineraryDayRequest.getFrom());
        ItineraryDateDomain target = listItineraryDateForUpdateOrder.get(listItineraryDateForUpdateOrder.size() - 1);

        Integer indexListSource = listItineraryDateForUpdateOrder.indexOf(source);
        Integer indexListTarget = listItineraryDateForUpdateOrder.indexOf(target);

        if (indexListSource < indexListTarget){
            indexCurrent = indexListSource;
            indexCurrentPass = indexCurrent + 1;
            while (indexCurrent < indexListTarget){
                ItineraryDateDomain itineraryDateCurrent = listItineraryDateForUpdateOrder.get(indexCurrent);
                ItineraryDateDomain itineraryDatePassCurrent = listItineraryDateForUpdateOrder.get(indexCurrentPass);

                indexDay.set(indexDay.get() + 1);

                ItineraryDateDomain dbItineraryDate = itineraryDateRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId());
                itineraryDateRepo.updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                        dbItineraryDate.getId(), indexDay.get(), indexDay.get().toString(), dateInitPlus.plusDays(indexDay.get() - 1));

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult;
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    for(ItineraryItemDomain itemDomain : listItineraryItemResult){
                        LocalDateTime localDateTime = LocalDateTime.of(
                                itineraryDatePassCurrent.getItineraryDate().getYear(),
                                itineraryDatePassCurrent.getItineraryDate().getMonth(),
                                itineraryDatePassCurrent.getItineraryDate().getDayOfMonth(),
                                itemDomain.getItineraryDateTime().getHour(),
                                itemDomain.getItineraryDateTime().getMinute(),
                                itemDomain.getItineraryDateTime().getSecond());
                        itemDomain.setItineraryDateTime(localDateTime);

                        itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);
                    }
                }

                indexCurrent++;
                indexCurrentPass++;
            }

        }

        registerGroupDomain.setDays(registerGroupDomain.getDays() + changeItineraryDayRequest.getDays());
        registerGroupDomain.setEndTour(dateInitPlus.plusDays(indexDay.get() - 1));

        RegisterGroupDomain registerGroupDomainEdit = repo.edit(registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);
    }


    @Override
    @Transactional
    public void updateOrderItineraryDate(String idRegisterGroup, UpdateOrderItineraryDateRequest updateOrderItineraryDateRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryItemDomain> listValidation = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseValidationExistBooking(updateOrderItineraryDateRequest.getSourceItineraryDate(), idRegisterGroup);
        listValidation = listValidation.stream().collect(Collectors.toList());
        for(ItineraryItemDomain itemDomain : listValidation){
            if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_MOVE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_MOVE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_MOVE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_MOVE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_MOVE);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingInstitutionDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_MOVE);
                }
            }
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<ItineraryDateDomain> list = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(idRegisterGroup).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryDateDomain::getIndex));

        Integer indexCurrent = 0;
        Integer indexCurrentPass = 0;

        ItineraryDateDomain source = itineraryDateRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(updateOrderItineraryDateRequest.getSourceItineraryDate());
        ItineraryDateDomain target = itineraryDateRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(updateOrderItineraryDateRequest.getTargetItineraryDate());

        Integer indexListSource = list.indexOf(source);
        Integer indexListTarget = list.indexOf(target);
        ItineraryDateDomain itineraryIndexSource = list.get(indexListSource);
        ItineraryDateDomain itineraryIndexTarget = list.get(indexListTarget);

        if (indexListSource > indexListTarget){
            indexCurrent = indexListSource;
            indexCurrentPass = indexCurrent - 1;
            while (indexCurrent > indexListTarget){
                ItineraryDateDomain itineraryDateCurrent = list.get(indexCurrent);
                ItineraryDateDomain itineraryDatePassCurrent = list.get(indexCurrentPass);

                ItineraryDateDomain dbItineraryDate = itineraryDateRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId());
                itineraryDateRepo.updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                        dbItineraryDate.getId(), itineraryDateCurrent.getIndex(), itineraryDateCurrent.getItemItinerary(), itineraryDateCurrent.getItineraryDate());

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    ItineraryDateDomain itineraryDateEditDb = itineraryDateRepo.findById(itineraryDatePassCurrent.getId());
                    this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, itineraryDateEditDb));
                }*/

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult;
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    for(ItineraryItemDomain itemDomain : listItineraryItemResult){
                        LocalDateTime localDateTime = LocalDateTime.of(
                                itineraryDateCurrent.getItineraryDate().getYear(),
                                itineraryDateCurrent.getItineraryDate().getMonth(),
                                itineraryDateCurrent.getItineraryDate().getDayOfMonth(),
                                itemDomain.getItineraryDateTime().getHour(),
                                itemDomain.getItineraryDateTime().getMinute(),
                                itemDomain.getItineraryDateTime().getSecond());

                        itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                            this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                        }*/
                    }
                }

                indexCurrent--;
                indexCurrentPass--;
            }

            itineraryDateRepo.updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                    itineraryIndexSource.getId(), itineraryIndexTarget.getIndex(), itineraryIndexTarget.getItemItinerary(), itineraryIndexTarget.getItineraryDate());

            /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                ItineraryDateDomain itineraryDateEditDb = itineraryDateRepo.findById(itineraryIndexSource.getId());
                this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, itineraryDateEditDb));
            }*/

            List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itineraryIndexSource.getId(), idRegisterGroup);
            List<ItineraryItemDomain> listItineraryItemResult;
            if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                for(ItineraryItemDomain itemDomain : listItineraryItemResult){
                    LocalDateTime localDateTime = LocalDateTime.of(
                            itineraryIndexTarget.getItineraryDate().getYear(),
                            itineraryIndexTarget.getItineraryDate().getMonth(),
                            itineraryIndexTarget.getItineraryDate().getDayOfMonth(),
                            itemDomain.getItineraryDateTime().getHour(),
                            itemDomain.getItineraryDateTime().getMinute(),
                            itemDomain.getItineraryDateTime().getSecond());

                    itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                        this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                    }*/
                }
            }

        }else if (indexListSource < indexListTarget){
            indexCurrent = indexListSource;
            indexCurrentPass = indexCurrent + 1;
            while (indexCurrent < indexListTarget){
                ItineraryDateDomain itineraryDateCurrent = list.get(indexCurrent);
                ItineraryDateDomain itineraryDatePassCurrent = list.get(indexCurrentPass);

                ItineraryDateDomain dbItineraryDate = itineraryDateRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId());
                itineraryDateRepo.updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                        dbItineraryDate.getId(), itineraryDateCurrent.getIndex(), itineraryDateCurrent.getItemItinerary(), itineraryDateCurrent.getItineraryDate());

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    ItineraryDateDomain itineraryDateEditDb = itineraryDateRepo.findById(itineraryDatePassCurrent.getId());
                    this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, itineraryDateEditDb));
                }*/

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult;
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    for(ItineraryItemDomain itemDomain : listItineraryItemResult){
                        LocalDateTime localDateTime = LocalDateTime.of(
                                itineraryDateCurrent.getItineraryDate().getYear(),
                                itineraryDateCurrent.getItineraryDate().getMonth(),
                                itineraryDateCurrent.getItineraryDate().getDayOfMonth(),
                                itemDomain.getItineraryDateTime().getHour(),
                                itemDomain.getItineraryDateTime().getMinute(),
                                itemDomain.getItineraryDateTime().getSecond());

                        itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                            this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                        }*/
                    }
                }

                indexCurrent++;
                indexCurrentPass++;
            }

            itineraryDateRepo.updateItemIndexDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                    itineraryIndexSource.getId(), itineraryIndexTarget.getIndex(), itineraryIndexTarget.getItemItinerary(), itineraryIndexTarget.getItineraryDate());

            /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                ItineraryDateDomain itineraryDateEditDb = itineraryDateRepo.findById(itineraryIndexSource.getId());
                this.eventPublisher.publishEvent(new ItineraryDateEditEvent(this, itineraryDateEditDb));
            }*/

            List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itineraryIndexSource.getId(), idRegisterGroup);
            List<ItineraryItemDomain> listItineraryItemResult;
            if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                for(ItineraryItemDomain itemDomain : listItineraryItemResult){
                    LocalDateTime localDateTime = LocalDateTime.of(
                            itineraryIndexTarget.getItineraryDate().getYear(),
                            itineraryIndexTarget.getItineraryDate().getMonth(),
                            itineraryIndexTarget.getItineraryDate().getDayOfMonth(),
                            itemDomain.getItineraryDateTime().getHour(),
                            itemDomain.getItineraryDateTime().getMinute(),
                            itemDomain.getItineraryDateTime().getSecond());

                    itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                        this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                    }*/
                }
            }
        }

        // Update BreakFast in Lodging

        List<ItineraryDateDomain> listUpdateLodgingBreakfast = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(idRegisterGroup).stream().collect(Collectors.toList());
        listUpdateLodgingBreakfast.sort(Comparator.comparing(ItineraryDateDomain::getIndex));
        for(ItineraryDateDomain itemUpdateBreakFastDomain : listUpdateLodgingBreakfast){
            List<ItineraryItemDomain> listItineraryItemUpdateLodgingBreakfast = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseUpdateOrderItinerary(itemUpdateBreakFastDomain.getId(), idRegisterGroup);
            if (listItineraryItemUpdateLodgingBreakfast != null && !listItineraryItemUpdateLodgingBreakfast.isEmpty()) {
                listItineraryItemUpdateLodgingBreakfast = listItineraryItemUpdateLodgingBreakfast.stream().collect(Collectors.toList());
                for(ItineraryItemDomain itemItineraryUpdateBreakFastDomain : listItineraryItemUpdateLodgingBreakfast){

                    if (
                            (itemItineraryUpdateBreakFastDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId()))
                    ){
                        int index = listUpdateLodgingBreakfast.indexOf(itemItineraryUpdateBreakFastDomain.getItineraryDate());
                        index = index + 1;

                        if (index <= listUpdateLodgingBreakfast.size() - 1) {

                            ItineraryDateDomain itineraryDateUpdateBreakFastDomain = listUpdateLodgingBreakfast.get(index);

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastItineraryDate() != null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocalOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuideOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }
                        }else if (index > listUpdateLodgingBreakfast.size() - 1){

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastItineraryDate() != null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItinerary(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTc(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocal(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuide(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                        }

                    }else if (
                            (itemItineraryUpdateBreakFastDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId()) && itemItineraryUpdateBreakFastDomain.getServiceGroup().equals(ServiceGroupEnum.LODGING))
                    ){
                        int index = listUpdateLodgingBreakfast.indexOf(itemItineraryUpdateBreakFastDomain.getItineraryDate());
                        index = index + 1;

                        if (index <= listUpdateLodgingBreakfast.size() - 1) {

                            ItineraryDateDomain itineraryDateUpdateBreakFastDomain = listUpdateLodgingBreakfast.get(index);

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastItineraryDate() != null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryLodgeOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLodgeOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocalLodgeOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuideLodgeOnlyItineraryDate(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        itineraryDateUpdateBreakFastDomain.getId());
                            }
                        }else if (index > listUpdateLodgingBreakfast.size() - 1){

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastItineraryDate() != null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryLodge(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLodge(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryTcLocalLodge(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                            if (itemItineraryUpdateBreakFastDomain.getLodgingCantBreakfastGuideItineraryDate()!= null){
                                itineraryItemRepo.updateLodgingFoodByIdSimpleDomainCaseUpdateItineraryGuideLodge(
                                        itemItineraryUpdateBreakFastDomain.getId(),
                                        null,
                                        0);
                            }

                        }

                    }
                }
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void updateOrderItineraryItem(String idRegisterGroup, UpdateOrderItineraryItemRequest updateOrderItineraryItemRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseUpdateOrderItinerary(idRegisterGroup).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryItemDomain::getIndex));

        Integer indexCurrent = 0;
        Integer indexCurrentPass = 0;

        ItineraryItemDomain source = itineraryItemRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(updateOrderItineraryItemRequest.getSourceItineraryItem());
        ItineraryItemDomain target = itineraryItemRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(updateOrderItineraryItemRequest.getTargetItineraryItem());

        Integer indexListSource = list.indexOf(source);
        Integer indexListTarget = list.indexOf(target);
        ItineraryItemDomain itineraryIndexSource = list.get(indexListSource);
        ItineraryItemDomain itineraryIndexTarget = list.get(indexListTarget);

        if (indexListSource > indexListTarget){
            indexCurrent = indexListSource;
            indexCurrentPass = indexCurrent - 1;
            while (indexCurrent > indexListTarget){
                ItineraryItemDomain itineraryItemCurrent = list.get(indexCurrent);
                ItineraryItemDomain itineraryItemPassCurrent = list.get(indexCurrentPass);

                ItineraryItemDomain dbItineraryItem = itineraryItemRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(itineraryItemPassCurrent.getId());
                itineraryItemRepo.updateIndexByIdSimpleDomainCaseUpdateOrderItinerary(dbItineraryItem.getId(), itineraryItemCurrent.getIndex());

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itineraryItemPassCurrent.getId());
                    this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                }*/

                indexCurrent--;
                indexCurrentPass--;
            }
            LocalDateTime localDateTime = LocalDateTime.of(
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getYear(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getMonth(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getDayOfMonth(),
                    itineraryIndexSource.getItineraryDateTime().getHour(),
                    itineraryIndexSource.getItineraryDateTime().getMinute(),
                    itineraryIndexSource.getItineraryDateTime().getSecond());
            itineraryItemRepo.updateIndexItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                    itineraryIndexSource.getId(), itineraryIndexTarget.getIndex(), itineraryIndexTarget.getItineraryDate().getId(), localDateTime);

            /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itineraryIndexSource.getId());
                this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
            }*/

        }else if (indexListSource < indexListTarget){
            indexCurrent = indexListSource;
            indexCurrentPass = indexCurrent + 1;
            while (indexCurrent < indexListTarget){
                ItineraryItemDomain itineraryItemCurrent = list.get(indexCurrent);
                ItineraryItemDomain itineraryItemPassCurrent = list.get(indexCurrentPass);

                ItineraryItemDomain dbItineraryItem = itineraryItemRepo.findByIdSimpleDomainCaseUpdateOrderItinerary(itineraryItemPassCurrent.getId());
                itineraryItemRepo.updateIndexByIdSimpleDomainCaseUpdateOrderItinerary(dbItineraryItem.getId(), itineraryItemCurrent.getIndex());

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itineraryItemPassCurrent.getId());
                    this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
                }*/

                indexCurrent++;
                indexCurrentPass++;
            }
            LocalDateTime localDateTime = LocalDateTime.of(
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getYear(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getMonth(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getDayOfMonth(),
                    itineraryIndexSource.getItineraryDateTime().getHour(),
                    itineraryIndexSource.getItineraryDateTime().getMinute(),
                    itineraryIndexSource.getItineraryDateTime().getSecond());
            itineraryItemRepo.updateIndexItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(
                    itineraryIndexSource.getId(), itineraryIndexTarget.getIndex(), itineraryIndexTarget.getItineraryDate().getId(), localDateTime);

            /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itineraryIndexSource.getId());
                this.eventPublisher.publishEvent(new ItineraryItemEditEvent(this, itineraryItemEditDb));
            }*/
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void updateOrderItineraryDateToItem(String idRegisterGroup, UpdateOrderItineraryDateToItemRequest updateOrderItineraryDateToItemRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);
    }

    @Override
    @Transactional
    public void updateOrderItineraryItemToDate(String idRegisterGroup, UpdateOrderItineraryItemToDateRequest updateOrderItineraryItemToDateRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);
    }

    @Override
    @Transactional
    public NewDateRegisterGroupResponse getNewDateRegisterGroup(String idRegisterGroup, NewDateRequest newDateRequest) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (registerGroupDomain.getInitTour().equals(newDateRequest.getNewDateInit())){
            throw new BadRequestException(MessagesValidation.VALIDATION_SAME_DATE_TOUR);
        }
        if (LocalDate.now().isAfter(newDateRequest.getNewDateInit())){
            throw new BadRequestException(MessagesValidation.VALIDATION_DATE_TOUR_IS_LESS_THAN_CURRENT_DATE);
        }
        LocalDate newDateInit = newDateRequest.getNewDateInit();
        LocalDate newDateEnd = newDateInit.plusDays(registerGroupDomain.getDays()-1);
        return NewDateRegisterGroupResponse.from(newDateInit, newDateEnd);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleAperture() {
        return repo.findAllSimpleAperture().stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleApertureDocumentFinance() {
        return repo.findAllSimpleApertureDocumentFinance().stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleApertureDocumentFinanceByGuideTransferDrive(String idGuideTransferDrive) {
        List<RegisterGroupDomain> registerGroups = repo.findAllSimpleApertureDocumentFinance();
        if (registerGroups == null || registerGroups.isEmpty() || idGuideTransferDrive == null || idGuideTransferDrive.isEmpty()) {
            return registerGroups.stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
        }
        Set<String> registerGroupIds = itineraryItemRepo.searchAllByGuideTransferDrive(idGuideTransferDrive).stream()
                .map(itineraryItemDomain -> itineraryItemDomain.getRegisterGroup() != null
                        ? itineraryItemDomain.getRegisterGroup().getId()
                        : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return registerGroups.stream()
                .filter(registerGroupDomain -> registerGroupIds.contains(registerGroupDomain.getId()))
                .map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupGuideTransferDriveResponse> findAllGuideTransferDriveRegisterGroup() {
        Map<String, Set<String>> map = new LinkedHashMap<>();
        itineraryItemRepo.searchAllGuideTransferDriveRegisterGroup().forEach(item -> {
            map.computeIfAbsent(item.getGuideTransferDriveId(), k -> new LinkedHashSet<>())
                    .add(item.getRegisterGroupId());
        });
        return map.entrySet().stream()
                .map(entry -> RegisterGroupGuideTransferDriveResponse.from(
                        entry.getKey(),
                        new ArrayList<>(entry.getValue())))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleApertureVoucherIngress() {
        return repo.findAllSimpleApertureVoucherIngress().stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleApertureVoucherEgress() {
        return repo.findAllSimpleApertureVoucherEgress().stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleApertureReport() {
        return repo.findAllSimpleApertureReport().stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleApertureVoucherIngressByCustomer(String idCustomer) {
        return repo.findAllSimpleApertureVoucherIngressByCustomer(idCustomer).stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllSimpleApertureVoucherIngressBySupplier(String idSupplier) {
        List<String> registerGroupIds = itineraryItemRepo.findDistinctRegisterGroupIdsBySupplier(idSupplier);
        if (registerGroupIds.isEmpty()) {
            return List.of();
        }
        return repo.findAllSimpleApertureVoucherIngressBySupplier(registerGroupIds).stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).toList();
    }

    @Override
    @Transactional
    public List<CostTourResponse> findAllCostTourByQuotation(String idRegisterGroup) {
        List<CostTourResponse> result = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        List<CostTourDomain> listCostTour = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseShowCostTour(idRegisterGroup);
        if (listCostTour != null && !listCostTour.isEmpty()){
            listCostTour = listCostTour.stream().collect(Collectors.toList());
            Collections.sort(listCostTour);
            result = listCostTour.stream().map(costTourDomain -> CostTourResponse.from(costTourDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public CostTourResponse addCostTour(CostTourCreateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        CostTourResponse costTourResponse = costTourUseCase.create(request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return costTourResponse;
    }

    @Override
    @Transactional
    public CostTourResponse editCostTour(String id, CostTourUpdateRequest request, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        CostTourResponse costTourResponse = costTourUseCase.edit(id, request, registerGroupDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return costTourResponse;
    }

    @Override
    @Transactional
    public void deleteCostTour(String id, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("The register group id cannot be null");
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        costTourUseCase.deleteById(id);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional(readOnly = true)
    public CostTourResponse findByCostTourAndRegisterGroup(String id, String idRegisterGroup) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("The register group id cannot be null");
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        return costTourUseCase.searchByRegisterGroupAndCostTour(idRegisterGroup, id);
    }

    @Override
    @Transactional
    public CostTourItemResponse addCostTourItem(CostTourItemCreateRequest request, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("The register group id cannot be null");
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourItemResponse result = costTourItemUseCase.create(request, registerGroupDomain, costTourDomain);

        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourItem(idRegisterGroup, idCostTour);
        Double subTotalAmount = 0.0;
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItem){
                subTotalAmount = subTotalAmount + costTourItemDomain.getAmount();
            }
        }
        costTourRepo.updateIdSimpleDomainCaseUpdateSubTotalAmount(idCostTour, subTotalAmount);

        /*List<CostTourDomain> listCostTour = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseShowCostTour(idRegisterGroup);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idRegisterGroup, amount);*/

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return result;
    }

    @Override
    @Transactional
    public CostTourItemResponse editCostTourItem(String id, CostTourItemUpdateRequest request, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("The register group id cannot be null");
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourItemResponse result = costTourItemUseCase.edit(id, request, registerGroupDomain, costTourDomain);

        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourItem(idRegisterGroup, idCostTour);
        Double subTotalAmount = 0.0;
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItem){
                subTotalAmount = subTotalAmount + costTourItemDomain.getAmount();
            }
        }
        costTourRepo.updateIdSimpleDomainCaseUpdateSubTotalAmount(idCostTour, subTotalAmount);

        /*List<CostTourDomain> listCostTour = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseShowCostTour(idRegisterGroup);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);*/

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return result;
    }

    @Override
    @Transactional
    public void deleteCostTourItem(String id, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("The register group id cannot be null");
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idCostTour == null) {
            throw new BadRequestException("The cost tour id cannot be null");
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        costTourItemUseCase.deleteById(id);

        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourItem(idRegisterGroup, idCostTour);
        Double subTotalAmount = 0.0;
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItem){
                subTotalAmount = subTotalAmount + costTourItemDomain.getAmount();
            }
        }
        costTourRepo.updateIdSimpleDomainCaseUpdateSubTotalAmount(idCostTour, subTotalAmount);

        /*List<CostTourDomain> listCostTour = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseShowCostTour(idRegisterGroup);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);*/

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional(readOnly = true)
    public CostTourItemResponse findByCostTourItemAndRegisterGroupAndCostTour(String id, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        return costTourItemUseCase.searchByRegisterGroupAndCostTourAndCostTourItem(idRegisterGroup, idCostTour, id);
    }

    @Override
    @Transactional
    public List<CostTourItemResponse> findAllCostTourItemByRegisterGroupAndCostTour(String idRegisterGroup, String idCostTour) {
        List<CostTourItemResponse> result = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourItem(idRegisterGroup, idCostTour);
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            result = listCostTourItem.stream().map(costTourItemDomain -> CostTourItemResponse.from(costTourItemDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public void removeToursAndCostTourByRegisterGroup(String idRegisterGroup, String idTour) {
       // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        List<ItineraryDateDomain> listDates = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseDeleteTour(idRegisterGroup);
        if (listDates != null && !listDates.isEmpty()){
            listDates = listDates.stream().collect(Collectors.toList());
            for(ItineraryDateDomain itineraryDateDomain: listDates){
                Set<TourEntity> tours = new HashSet<>();
                if (itineraryDateDomain.getTours() != null && !itineraryDateDomain.getTours().isEmpty()){
                    for (TourDomain tourDomain: itineraryDateDomain.getTours()){
                        if (!idTour.equals(tourDomain.getId())){
                            TourEntity tourEntity = new TourEntity();
                            tourEntity.setId(tourDomain.getId());
                            tours.add(tourEntity);
                        }
                    }
                }
                itineraryDateRepo.updateToursByIdSimpleDomainCaseDeleteTour(itineraryDateDomain.getId(), tours);

                List<ItineraryItemDomain> itineraryItemDomainListToDelete = itineraryItemRepo.searchAllByRegisterGroupAndItineraryDateSimpleDomainCaseDeleteTours(idRegisterGroup, itineraryDateDomain.getId(), idTour);
                if (itineraryItemDomainListToDelete != null && !itineraryItemDomainListToDelete.isEmpty()){
                    itineraryItemDomainListToDelete = itineraryItemDomainListToDelete.stream().toList();
                    for (ItineraryItemDomain itineraryItemDomain : itineraryItemDomainListToDelete){
                        itineraryItemRepo.deleteById(itineraryItemDomain.getId());
                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                            this.eventPublisher.publishEvent(new ItineraryItemCrmDeleteEvent(this, itineraryItemRepo.findById(itineraryItemDomain.getId())));
                        }*/
                    }
                }
            }
        }

        List<CostTourDomain> listCostTourDelete = costTourRepo.searchAllByRegisterGroupAndTourSimpleDomainCaseShowCostTour(idRegisterGroup, idTour);
        if (listCostTourDelete != null && !listCostTourDelete.isEmpty()){
            listCostTourDelete = listCostTourDelete.stream().toList();
            for (CostTourDomain costTourDomain : listCostTourDelete) {
                costTourRepo.deleteById(costTourDomain.getId());
                List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourItem(idRegisterGroup, costTourDomain.getId());
                if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
                    for (CostTourItemDomain costTourItemDomain : listCostTourItemDelete) {
                        costTourItemRepo.deleteById(costTourItemDomain.getId());
                    }
                }
            }
        }

        /*List<CostTourDomain> listCostTour = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseShowCostTour(idRegisterGroup);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);*/

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void removeToursAndCostTourByRegisterGroupAndCostTour(String idRegisterGroup, String idTour, String idCostTour) {
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        TourDomain tourExistDomain = tourRepo.findById(idTour);
        if (tourExistDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        CostTourDomain costTourExistDomain = costTourRepo.findById(idCostTour);
        if (costTourExistDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<ItineraryDateDomain> listDates = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseDeleteTour(idRegisterGroup);
        if (listDates != null && !listDates.isEmpty()){
            listDates = listDates.stream().collect(Collectors.toList());
            for(ItineraryDateDomain itineraryDateDomain: listDates){
                List<ItineraryItemDomain> itineraryItemDomainListToDelete = itineraryItemRepo.searchAllByRegisterGroupAndItineraryDateAndCostTourSimpleDomainCaseDeleteTours(idRegisterGroup, itineraryDateDomain.getId(), idTour, idCostTour);
                if (itineraryItemDomainListToDelete != null && !itineraryItemDomainListToDelete.isEmpty()){

                    Set<TourEntity> tours = new HashSet<>();
                    if (itineraryDateDomain.getTours() != null && !itineraryDateDomain.getTours().isEmpty()){
                        for (TourDomain tourDomain: itineraryDateDomain.getTours()){
                            if (!idTour.equals(tourDomain.getId())){
                                TourEntity tourEntity = new TourEntity();
                                tourEntity.setId(tourDomain.getId());
                                tours.add(tourEntity);
                            }
                        }
                    }
                    itineraryDateRepo.updateToursByIdSimpleDomainCaseDeleteTour(itineraryDateDomain.getId(), tours);

                    itineraryItemDomainListToDelete = itineraryItemDomainListToDelete.stream().toList();
                    for (ItineraryItemDomain itineraryItemDomain : itineraryItemDomainListToDelete){
                        itineraryItemRepo.deleteById(itineraryItemDomain.getId());
                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                            this.eventPublisher.publishEvent(new ItineraryItemCrmDeleteEvent(this, itineraryItemRepo.findById(itineraryItemDomain.getId())));
                        }*/
                    }
                }
            }
        }

        /*List<CostTourDomain> listCostTourDelete = costTourRepo.searchAllByRegisterGroupAndTourSimpleDomainCaseShowCostTour(idRegisterGroup, idTour);
        if (listCostTourDelete != null && !listCostTourDelete.isEmpty()){
            listCostTourDelete = listCostTourDelete.stream().toList();
            for (CostTourDomain costTourDomain : listCostTourDelete) {
                costTourRepo.deleteById(costTourDomain.getId());
                List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourItem(idRegisterGroup, costTourDomain.getId());
                if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
                    for (CostTourItemDomain costTourItemDomain : listCostTourItemDelete) {
                        costTourItemRepo.deleteById(costTourItemDomain.getId());
                    }
                }
            }
        }*/
        costTourRepo.deleteById(costTourExistDomain.getId());
        List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourItem(idRegisterGroup, costTourExistDomain.getId());
        if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
            for (CostTourItemDomain costTourItemDomain : listCostTourItemDelete) {
                costTourItemRepo.deleteById(costTourItemDomain.getId());
            }
        }

        /*List<CostTourDomain> listCostTour = costTourRepo.searchAllByRegisterGroupSimpleDomainCaseShowCostTour(idRegisterGroup);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);*/

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void generateAllRegisterBooking(String idRegisterGroup) {
        WrapSupplierRegisterBookingAllGenerate result = new WrapSupplierRegisterBookingAllGenerate();
        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();
        result.setOperators(new ArrayList<>());
        result.setLodges(new ArrayList<>());
        result.setRestaurants(new ArrayList<>());
        result.setLodgings(new ArrayList<>());
        result.setGlampings(new ArrayList<>());
        result.setInstitutions(new ArrayList<>());

        Map<String, WrapSupplierRegisterBookingGenerate> mapResultLodging = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingGenerate> mapResultLodge = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingGenerate> mapResultOperator = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingGenerate> mapResultRestaurant = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingGenerate> mapResultGlamping = new HashMap<>();
        Map<String, WrapSupplierRegisterBookingGenerate> mapResultInstitutions = new HashMap<>();

        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId()) ){
                        if (!mapResultLodging.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate = new WrapSupplierRegisterBookingGenerate();
                            wrapSupplierRegisterBookingGenerate.setId(itineraryItemDomain.getSupplier().getId());
                            wrapSupplierRegisterBookingGenerate.setSupplier(itineraryItemDomain.getSupplier());
                            wrapSupplierRegisterBookingGenerate.setSupplierType(itineraryItemDomain.getSupplierType());
                            mapResultLodging.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingGenerate);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId()) ){
                        if (!mapResultOperator.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate = new WrapSupplierRegisterBookingGenerate();
                            wrapSupplierRegisterBookingGenerate.setId(itineraryItemDomain.getSupplier().getId());
                            wrapSupplierRegisterBookingGenerate.setSupplier(itineraryItemDomain.getSupplier());
                            wrapSupplierRegisterBookingGenerate.setSupplierType(itineraryItemDomain.getSupplierType());
                            mapResultOperator.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingGenerate);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId()) ){
                        if (!mapResultRestaurant.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate = new WrapSupplierRegisterBookingGenerate();
                            wrapSupplierRegisterBookingGenerate.setId(itineraryItemDomain.getSupplier().getId());
                            wrapSupplierRegisterBookingGenerate.setSupplier(itineraryItemDomain.getSupplier());
                            wrapSupplierRegisterBookingGenerate.setSupplierType(itineraryItemDomain.getSupplierType());
                            mapResultRestaurant.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingGenerate);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId()) ){
                        if (!mapResultLodge.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate = new WrapSupplierRegisterBookingGenerate();
                            wrapSupplierRegisterBookingGenerate.setId(itineraryItemDomain.getSupplier().getId());
                            wrapSupplierRegisterBookingGenerate.setSupplier(itineraryItemDomain.getSupplier());
                            wrapSupplierRegisterBookingGenerate.setSupplierType(itineraryItemDomain.getSupplierType());
                            mapResultLodge.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingGenerate);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId()) ){
                        if (!mapResultLodging.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate = new WrapSupplierRegisterBookingGenerate();
                            wrapSupplierRegisterBookingGenerate.setId(itineraryItemDomain.getSupplier().getId());
                            wrapSupplierRegisterBookingGenerate.setSupplier(itineraryItemDomain.getSupplier());
                            wrapSupplierRegisterBookingGenerate.setSupplierType(itineraryItemDomain.getSupplierType());
                            mapResultGlamping.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingGenerate);
                        }
                    }else if ( itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId()) ){
                        if (!mapResultInstitutions.containsKey(itineraryItemDomain.getSupplier().getId())){
                            WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate = new WrapSupplierRegisterBookingGenerate();
                            wrapSupplierRegisterBookingGenerate.setId(itineraryItemDomain.getSupplier().getId());
                            wrapSupplierRegisterBookingGenerate.setSupplier(itineraryItemDomain.getSupplier());
                            wrapSupplierRegisterBookingGenerate.setSupplierType(itineraryItemDomain.getSupplierType());
                            mapResultInstitutions.put(itineraryItemDomain.getSupplier().getId(), wrapSupplierRegisterBookingGenerate);
                        }
                    }
                }
            }
            result.getLodgings().addAll(mapResultLodging.values());
            result.getLodges().addAll(mapResultLodge.values());
            result.getRestaurants().addAll(mapResultRestaurant.values());
            result.getOperators().addAll(mapResultOperator.values());
            result.getGlampings().addAll(mapResultGlamping.values());
            result.getInstitutions().addAll(mapResultInstitutions.values());
        }

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActive(idRegisterGroup);

        for (WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate: result.getLodgings()
        ) {
            List<RegisterBookingBaseDomain> listRegisterBookingBaseDomain = registerBookingBaseDomains
                    .stream()
                    .filter(
                            registerBookingBaseDomainTemp ->
                                    registerBookingBaseDomainTemp.getSupplier().getId().equals(wrapSupplierRegisterBookingGenerate.getSupplier().getId())
                    ).collect(Collectors.toList());
            if (listRegisterBookingBaseDomain == null || listRegisterBookingBaseDomain.isEmpty()){
                registerBookingLodgingUseCase.generate(idRegisterGroup, wrapSupplierRegisterBookingGenerate.getSupplier().getId());
            }
        }

        for (WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate: result.getLodges()
        ) {
            List<RegisterBookingBaseDomain> listRegisterBookingBaseDomain = registerBookingBaseDomains
                    .stream()
                    .filter(
                            registerBookingBaseDomainTemp ->
                                    registerBookingBaseDomainTemp.getSupplier().getId().equals(wrapSupplierRegisterBookingGenerate.getSupplier().getId())
                    ).collect(Collectors.toList());
            if (listRegisterBookingBaseDomain == null || listRegisterBookingBaseDomain.isEmpty()){
                registerBookingLodgerUseCase.generate(idRegisterGroup, wrapSupplierRegisterBookingGenerate.getSupplier().getId());
            }
        }

        for (WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate: result.getRestaurants()
        ) {
            List<RegisterBookingBaseDomain> listRegisterBookingBaseDomain = registerBookingBaseDomains
                    .stream()
                    .filter(
                            registerBookingBaseDomainTemp ->
                                    registerBookingBaseDomainTemp.getSupplier().getId().equals(wrapSupplierRegisterBookingGenerate.getSupplier().getId())
                    ).collect(Collectors.toList());
            if (listRegisterBookingBaseDomain == null || listRegisterBookingBaseDomain.isEmpty()){
                registerBookingRestaurantUseCase.generate(idRegisterGroup, wrapSupplierRegisterBookingGenerate.getSupplier().getId());
            }
        }

        for (WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate: result.getOperators()
        ) {
            List<RegisterBookingBaseDomain> listRegisterBookingBaseDomain = registerBookingBaseDomains
                    .stream()
                    .filter(
                            registerBookingBaseDomainTemp ->
                                    registerBookingBaseDomainTemp.getSupplier().getId().equals(wrapSupplierRegisterBookingGenerate.getSupplier().getId())
                    ).collect(Collectors.toList());
            if (listRegisterBookingBaseDomain == null || listRegisterBookingBaseDomain.isEmpty()){
                registerBookingOperatorUseCase.generate(idRegisterGroup, wrapSupplierRegisterBookingGenerate.getSupplier().getId());
            }
        }

        for (WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate: result.getGlampings()
        ) {
            List<RegisterBookingBaseDomain> listRegisterBookingBaseDomain = registerBookingBaseDomains
                    .stream()
                    .filter(
                            registerBookingBaseDomainTemp ->
                                    registerBookingBaseDomainTemp.getSupplier().getId().equals(wrapSupplierRegisterBookingGenerate.getSupplier().getId())
                    ).collect(Collectors.toList());
            if (listRegisterBookingBaseDomain == null || listRegisterBookingBaseDomain.isEmpty()){
                registerBookingGlampingUseCase.generate(idRegisterGroup, wrapSupplierRegisterBookingGenerate.getSupplier().getId());
            }
        }

        for (WrapSupplierRegisterBookingGenerate wrapSupplierRegisterBookingGenerate: result.getInstitutions()
        ) {
            List<RegisterBookingBaseDomain> listRegisterBookingBaseDomain = registerBookingBaseDomains
                    .stream()
                    .filter(
                            registerBookingBaseDomainTemp ->
                                    registerBookingBaseDomainTemp.getSupplier().getId().equals(wrapSupplierRegisterBookingGenerate.getSupplier().getId())
                    ).collect(Collectors.toList());
            if (listRegisterBookingBaseDomain == null || listRegisterBookingBaseDomain.isEmpty()){
                registerBookingInstitutionUseCase.generate(idRegisterGroup, wrapSupplierRegisterBookingGenerate.getSupplier().getId());
            }
        }
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse addCostTourServiceItem(CostTourServiceItemCreateRequest request, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourServiceItemResponse result = costTourServiceItemUseCase.create(request, registerGroupDomain, costTourDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return result;
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse editCostTourServiceItem(String id, CostTourServiceItemUpdateRequest request, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        RegisterGroupDomain registerGroupDomain = new RegisterGroupDomain();
        registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourServiceItemResponse result = costTourServiceItemUseCase.edit(id, request, registerGroupDomain, costTourDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

        return result;
    }

    @Override
    @Transactional
    public void deleteCostTourServiceItem(String id, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("The register group id cannot be null");
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idCostTour == null) {
            throw new BadRequestException("The cost tour id cannot be null");
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        costTourServiceItemUseCase.deleteById(id);

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional(readOnly = true)
    public CostTourServiceItemResponse findByCostTourServiceItemAndRegisterGroupAndCostTour(String id, String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        return costTourServiceItemUseCase.searchByRegisterGroupAndCostTourAndCostTourServiceItem(idRegisterGroup, idCostTour, id);
    }

    @Override
    @Transactional
    public List<CostTourServiceItemResponse> findAllCostTourServiceItemByRegisterGroupAndCostTour(String idRegisterGroup, String idCostTour) {
        List<CostTourServiceItemResponse> result = new ArrayList<>();
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<CostTourServiceItemDomain> listCostTourServiceItem = costTourServiceItemRepo.searchAllByRegisterGroupAndCostTourSimpleDomainCaseShowCostTourServiceItem(idRegisterGroup, idCostTour);
        if (listCostTourServiceItem != null && !listCostTourServiceItem.isEmpty()){
            result = listCostTourServiceItem.stream().map(costTourServiceItemDomain -> CostTourServiceItemResponse.from(costTourServiceItemDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public List<ItineraryDateResponse> findAllItineraryDateByRegisterGroupFromCostTour(String idRegisterGroup, String idCostTour) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idCostTour == null) {
            return null;
        }
        CostTourDomain costTourDomain = costTourRepo.findById(idCostTour);
        if (costTourDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<ItineraryDateDomain> list = itineraryDateUseCase.searchAllDomainByRegisterGroup(idRegisterGroup);
        List<ItineraryDateDomain> result = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            list = list.stream().collect(Collectors.toList());
            Collections.sort(list);

            Integer indexListSource = list.indexOf(costTourDomain.getItineraryDate());
            Integer indexLast = indexListSource + costTourDomain.getTour().getCantDay();
            Integer indexCurrent = indexListSource;
            while (indexCurrent < indexLast) {
                result.add(list.get(indexCurrent));
                indexCurrent++;
            }
        }
        return result.stream().map(itineraryDateDomain -> ItineraryDateResponse.from(itineraryDateDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SupplierResponse> findAllSupplierByRegisterGroupAndSupplierType(String idRegisterGroup, String idSupplierType) {
        List<SupplierDomain> listSupplier = new ArrayList<>();
        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()) {
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            for (ItineraryDateDomain itineraryDateDomain : listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseVoucherEgressSupplier(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()) {
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    for (ItineraryItemDomain itineraryItemDomain : listItineraryItemResult){
                        if (itineraryItemDomain.getSupplierType().getId().equals(idSupplierType)){
                            if (!listSupplier.contains(itineraryItemDomain.getSupplier())){
                                listSupplier.add(itineraryItemDomain.getSupplier());
                            }
                        }
                    }
                }
            }
        }
        return listSupplier.stream().map(supplierDomain -> SupplierResponse.from(supplierDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItineraryItemResponse> findAllServiceRequest(String idRegisterGroup, String idSupplier) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = null;
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain != null){
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idSupplier == null) {
            return null;
        }
        SupplierDomain supplierDomain = null;
        IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(idSupplier);
        if (supplierIdBaseDomain != null){
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        if (supplierDomain == null) {
            throw new NotFoundException("No supplier found with id '%s'.".formatted(idSupplier));
        }
        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllRegisterGroupAndSupplierSimpleDomainCaseServiceRequest(idRegisterGroup, idSupplier);
        return list.stream().map(itineraryItemDomain -> ItineraryItemResponse.from(itineraryItemDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItineraryItemResponse findAllServiceRequestByItineraryItem(String idRegisterGroup, String idItineraryItem) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = null;
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain != null){
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        if (idItineraryItem == null) {
            return null;
        }
        ItineraryItemDomain itineraryItemDomain = null;
        IdBaseDomain itineraryItemIdBaseDomain = itineraryItemRepo.findByIdEntity(idItineraryItem);
        if (itineraryItemIdBaseDomain != null){
            itineraryItemDomain = new ItineraryItemDomain();
            itineraryItemDomain.setId(itineraryItemIdBaseDomain.getId());
        }
        if (itineraryItemDomain == null) {
            throw new NotFoundException("No itinerary iyem found with id '%s'.".formatted(idItineraryItem));
        }
        ItineraryItemDomain item = itineraryItemRepo.searchAllRegisterGroupAndSupplierSimpleDomainCaseServiceRequestById(idItineraryItem, idRegisterGroup);
        return ItineraryItemResponse.from(item);
    }

    @Override
    @Transactional
    public void changeStateAllBookingToAnnulled(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);

        List<RegisterBookingBaseDomain> registerBookingBaseDomains = new ArrayList<>();

        registerBookingBaseDomains = registerBookingBaseRepo.searchAllByRegisterGroupAndActiveSimpleDomainCaseAnnulled(idRegisterGroup);

        for (RegisterBookingBaseDomain registerBookingBaseDomain: registerBookingBaseDomains
        ) {
            // registerBookingBaseDomain.setStateBooking(StateBookingEnum.ANNULLED.getName());
            // registerBookingBaseDomain.setActiveRegisterBooking(false);
            // registerBookingBaseRepo.edit(registerBookingBaseDomain);

            if (!registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                registerBookingBaseRepo.updateStateBookingAndActiveRegisterBookingByIdSimpleDomainCaseUpdateRegisterBookingBaseAnnulled(registerBookingBaseDomain.getId(), StateBookingEnum.ANNULLED.getName(), false);

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseAnnulled(idRegisterGroup, registerBookingBaseDomain.getSupplier().getId());
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
                    // itineraryItemDomain.setRegisterBookingBase(null);
                    // itineraryItemRepo.edit(itineraryItemDomain);
                    itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryAnnulled(itineraryItemDomain.getId());
                }
            }else if (registerBookingBaseDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findByIdSimpleDomainCaseGenerateRequestOrderService(idRegisterGroup, registerBookingBaseDomain.getSupplier().getId(), registerBookingBaseDomain.getId());
                if (requestOrderServiceDomain == null){
                    registerBookingBaseRepo.updateStateBookingAndActiveRegisterBookingByIdSimpleDomainCaseUpdateRegisterBookingBaseAnnulled(registerBookingBaseDomain.getId(), StateBookingEnum.ANNULLED.getName(), false);

                    List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseAnnulled(idRegisterGroup, registerBookingBaseDomain.getSupplier().getId());
                    for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
                        // itineraryItemDomain.setRegisterBookingBase(null);
                        // itineraryItemRepo.edit(itineraryItemDomain);
                        itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryAnnulled(itineraryItemDomain.getId());
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public void changeCantPaxAdult(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryItemDomain> listValidation = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
        listValidation = listValidation.stream().collect(Collectors.toList());
        for(ItineraryItemDomain itemDomain : listValidation){
            if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingInstitutionDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            }
        }

        if (registerGroupDomain != null){
            registerGroupDomain.setCantPaxAdult(changeCantPaxRequest.getCantPax());
            registerGroupDomain.setTotalPax(
                    (registerGroupDomain.getCantPaxAdult() != null ? registerGroupDomain.getCantPaxAdult() : 0)
                    + (registerGroupDomain.getCantPaxChildren() != null ? registerGroupDomain.getCantPaxChildren() : 0)
                    + (registerGroupDomain.getTC() != null ? registerGroupDomain.getTC() : 0)
                    + (registerGroupDomain.getTCLocal() != null ? registerGroupDomain.getTCLocal() : 0)
            );
            repo.edit(registerGroupDomain);

            List<ItineraryItemDomain> listItem = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
            listItem = listItem.stream().collect(Collectors.toList());

            for (ItineraryItemDomain itineraryItemDomain: listItem){
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    itineraryItemRepo.updateCantByIdSimpleDomainCaseUpdateCantPax(
                            itineraryItemDomain.getId(),
                            registerGroupDomain.getCantPaxAdult(),
                            registerGroupDomain.getCantPaxAdult(),
                            0,
                            0,
                            0,
                            0
                            );
                }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    itineraryItemRepo.updateCantByIdSimpleDomainCaseUpdateCantPax(
                            itineraryItemDomain.getId(),
                            0,
                            registerGroupDomain.getCantPaxAdult(),
                            registerGroupDomain.getCantPaxChildren(),
                            registerGroupDomain.getTC(),
                            registerGroupDomain.getTCLocal(),
                            0
                    );
                }
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void changeCantPaxChild(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryItemDomain> listValidation = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
        listValidation = listValidation.stream().collect(Collectors.toList());
        for(ItineraryItemDomain itemDomain : listValidation){
            if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingInstitutionDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            }
        }

        if (registerGroupDomain != null){
            registerGroupDomain.setCantPaxChildren(changeCantPaxRequest.getCantPax());
            registerGroupDomain.setTotalPax(
                    (registerGroupDomain.getCantPaxAdult() != null ? registerGroupDomain.getCantPaxAdult() : 0)
                            + (registerGroupDomain.getCantPaxChildren() != null ? registerGroupDomain.getCantPaxChildren() : 0)
                            + (registerGroupDomain.getTC() != null ? registerGroupDomain.getTC() : 0)
                            + (registerGroupDomain.getTCLocal() != null ? registerGroupDomain.getTCLocal() : 0)
            );
            repo.edit(registerGroupDomain);

            List<ItineraryItemDomain> listItem = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
            listItem = listItem.stream().collect(Collectors.toList());

            for (ItineraryItemDomain itineraryItemDomain: listItem){
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    itineraryItemRepo.updateCantByIdSimpleDomainCaseUpdateCantPax(
                            itineraryItemDomain.getId(),
                            0,
                            registerGroupDomain.getCantPaxAdult(),
                            registerGroupDomain.getCantPaxChildren(),
                            registerGroupDomain.getTC(),
                            registerGroupDomain.getTCLocal(),
                            0
                    );
                }
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void changeCantTc(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryItemDomain> listValidation = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
        listValidation = listValidation.stream().collect(Collectors.toList());
        for(ItineraryItemDomain itemDomain : listValidation){
            if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingInstitutionDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            }
        }

        if (registerGroupDomain != null){
            registerGroupDomain.setTC(changeCantPaxRequest.getCantPax());
            registerGroupDomain.setTotalPax(
                    (registerGroupDomain.getCantPaxAdult() != null ? registerGroupDomain.getCantPaxAdult() : 0)
                            + (registerGroupDomain.getCantPaxChildren() != null ? registerGroupDomain.getCantPaxChildren() : 0)
                            + (registerGroupDomain.getTC() != null ? registerGroupDomain.getTC() : 0)
                            + (registerGroupDomain.getTCLocal() != null ? registerGroupDomain.getTCLocal() : 0)
            );
            repo.edit(registerGroupDomain);

            List<ItineraryItemDomain> listItem = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
            listItem = listItem.stream().collect(Collectors.toList());

            for (ItineraryItemDomain itineraryItemDomain: listItem){
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    itineraryItemRepo.updateCantByIdSimpleDomainCaseUpdateCantPax(
                            itineraryItemDomain.getId(),
                            0,
                            registerGroupDomain.getCantPaxAdult(),
                            registerGroupDomain.getCantPaxChildren(),
                            registerGroupDomain.getTC(),
                            registerGroupDomain.getTCLocal(),
                            0
                    );
                }
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);

    }

    @Override
    @Transactional
    public void changeCantTcLocal(String idRegisterGroup, ChangeCantPaxRequest changeCantPaxRequest) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<ItineraryItemDomain> listValidation = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
        listValidation = listValidation.stream().collect(Collectors.toList());
        for(ItineraryItemDomain itemDomain : listValidation){
            if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())){
                RegisterBookingInstitutionDomain registerBookingInstitutionDomainExist = registerBookingInstitutionRepo.findByIdRegisterGroupCaseValidationExistBooking(idRegisterGroup, itemDomain.getSupplier().getId());
                if (registerBookingInstitutionDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_UPDATE_CANT);
                }
            }
        }

        if (registerGroupDomain != null){
            registerGroupDomain.setTCLocal(changeCantPaxRequest.getCantPax());
            registerGroupDomain.setTotalPax(
                    (registerGroupDomain.getCantPaxAdult() != null ? registerGroupDomain.getCantPaxAdult() : 0)
                            + (registerGroupDomain.getCantPaxChildren() != null ? registerGroupDomain.getCantPaxChildren() : 0)
                            + (registerGroupDomain.getTC() != null ? registerGroupDomain.getTC() : 0)
                            + (registerGroupDomain.getTCLocal() != null ? registerGroupDomain.getTCLocal() : 0)
            );
            repo.edit(registerGroupDomain);

            List<ItineraryItemDomain> listItem = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseValidationExistBooking(idRegisterGroup);
            listItem = listItem.stream().collect(Collectors.toList());

            for (ItineraryItemDomain itineraryItemDomain: listItem){
                if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
                    itineraryItemRepo.updateCantByIdSimpleDomainCaseUpdateCantPax(
                            itineraryItemDomain.getId(),
                            0,
                            registerGroupDomain.getCantPaxAdult(),
                            registerGroupDomain.getCantPaxChildren(),
                            registerGroupDomain.getTC(),
                            registerGroupDomain.getTCLocal(),
                            0
                    );
                }
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idRegisterGroup);
    }

    @Override
    @Transactional
    public WrapItineraryEditAdvancedResponse getItineraryAdvancedToEdit(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            throw new BadRequestException("Id is null");
        }
        RegisterGroupDomain registerGroupDomain = repo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        List<ItineraryItemDomain> listItineraryItems = itineraryItemRepo.searchAllByRegisterGroupSimpleDomainCaseAdvanced(idRegisterGroup);
        listItineraryItems = listItineraryItems.stream().collect(Collectors.toList());
        Collections.sort(listItineraryItems);

        Map<String, List<ItineraryItemDomain>> mapSupplier = new HashMap<>();
        for (ItineraryItemDomain itineraryItemDomain : listItineraryItems){
            if (!mapSupplier.containsKey(itineraryItemDomain.getSupplier().getId())){
                mapSupplier.put(itineraryItemDomain.getSupplier().getId(), new ArrayList<>());
            }
            mapSupplier.get(itineraryItemDomain.getSupplier().getId()).add(itineraryItemDomain);
        }

        List<WrapItineraryEditAdvancedSupplierResponse> listWrapSupplier = new ArrayList<>();
        for (String idSupplier : mapSupplier.keySet()){
            List<ItineraryItemDomain> listTemp = mapSupplier.get(idSupplier);
            SupplierDomain supplierDomain = supplierRepo.findById(idSupplier);
            WrapItineraryEditAdvancedSupplierResponse wrapItineraryEditAdvancedSupplierResponse = WrapItineraryEditAdvancedSupplierResponse.from(
                    supplierDomain.getId(),
                    SupplierResponse.from(supplierDomain),
                    supplierDomain.getSupplierType().getId(),
                    supplierDomain.getSupplierType().getIdentify(),
                    listTemp.stream().map(itineraryItemDomain -> ItineraryItemResponse.from(itineraryItemDomain)).collect(Collectors.toList())
            );
            listWrapSupplier.add(wrapItineraryEditAdvancedSupplierResponse);
        }
        return WrapItineraryEditAdvancedResponse.from(listWrapSupplier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllRegisterGroupNotInCostSheetTwo() {
        return repo.findAllRegisterGroupNotInCostSheetTwo().stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterGroupResponse> findAllRegisterGroupForCostSheetTwo() {
        return repo.findAllRegisterGroupForCostSheetTwo().stream().map(registerGroupDomain -> RegisterGroupResponse.from(registerGroupDomain)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RegisterGroupResponse getRegisterGroupModificationUserData(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        IdBaseDomain registerGroupIdBaseDomain = repo.findByIdEntity(idRegisterGroup);
        if (registerGroupIdBaseDomain == null){
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        return RegisterGroupResponse.from(repo.findByIdRegisterGroupCaseGetModificationUserData(idRegisterGroup));
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

    private void _changeStateOpened(RegisterGroupDomain registerGroupDomain, RegisterGroupChangeStateRequest request){
        // Update Register Group

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        registerGroupDomain.setInitTour(registerGroupDomain.getQuotation().getDateInitTour());
        registerGroupDomain.setEndTour(registerGroupDomain.getQuotation().getDateEndTour());
        registerGroupDomain.setProgram(registerGroupDomain.getQuotation().getProgram());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> list = itineraryDateCrmRepo.searchAllByQuotationSimpleDomainCaseGeneric(registerGroupDomain.getQuotation().getId()).stream().collect(Collectors.toList());
            list.sort(Comparator.comparing(com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain::getIndex));
            registerGroupDomain.setIdentify(persistRegisterGroupNumberTypeGuruCallback.generateNumber(
                    registerGroupDomain.getQuotation().getDateEndTour().getYear(),
                    registerGroupDomain.getQuotation().getDateEndTour().getMonthValue(),
                    GURU_SEDE_CUSCO_01,
                    true)
            );
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            registerGroupDomain.setIdentify(persistRegisterGroupNumberCallbackImpl.generateNumber(
                    LocalDate.now().getYear(),
                    LocalDate.now().getMonthValue(),
                    registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().getIdentify(),
                    true)
            );
        }else{
            registerGroupDomain.setIdentify(persistRegisterGroupNumberCallbackImpl.generateNumber(
                    LocalDate.now().getYear(),
                    LocalDate.now().getMonthValue(),
                    registerGroupDomain.getCustomer().getPartner().getAddress().getCountry().getIdentify(),
                    true)
            );
        }

        registerGroupDomain.setIdiom(registerGroupDomain.getQuotation().getIdiom());
        registerGroupDomain.setCantPaxAdult(registerGroupDomain.getQuotation().getPaxAdult());
        registerGroupDomain.setCantPaxChildren(registerGroupDomain.getQuotation().getPaxChild());
        registerGroupDomain.setTC(registerGroupDomain.getQuotation().getPaxTc());
        registerGroupDomain.setTCLocal(registerGroupDomain.getQuotation().getPaxTcLocal());
        registerGroupDomain.setTotalPax(registerGroupDomain.getQuotation().getPaxTotal());
        registerGroupDomain.setDays(registerGroupDomain.getQuotation().getCantDayTour());
        registerGroupDomain.setApertureWrite(true);
        registerGroupDomain.setDateAperture(LocalDate.now());
        registerGroupDomain.setCountry(registerGroupDomain.getQuotation().getCountry());
        registerGroupDomain.setLink(registerGroupDomain.getQuotation().getLinkGDrive());
        registerGroupDomain.setLinkBitrix(registerGroupDomain.getQuotation().getLinkBitrix());
        registerGroupDomain.setLinkWetravel(registerGroupDomain.getQuotation().getLinkWetravel());
        registerGroupDomain.setEmailPassengerPrincipal(registerGroupDomain.getQuotation().getEmailPassengerPrincipal());
        registerGroupDomain.setWhatsappPassengerPrincipal(registerGroupDomain.getQuotation().getWhatsappPassengerPrincipal());
        registerGroupDomain.setMobilePassengerPrincipal(registerGroupDomain.getQuotation().getMobilePassengerPrincipal());
        repo.edit(registerGroupDomain);

        List<ItineraryItemDomain> listItineraryItemUpdateCostTour = new ArrayList<>();
        Map<String, ItineraryDateDomain> mapItineraryDateByItemItinerary = new HashMap<>();

        // Generate Itinerary Date and Item
        List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDate = itineraryDateCrmRepo.searchAllByQuotationSimpleDomainCaseChangeStateOpened(registerGroupDomain.getQuotation().getId());
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain itineraryDateDomainSource: listItineraryDateResult
            ) {
                ItineraryDateDomain itineraryDateDomainDestination = new ItineraryDateDomain();
                itineraryDateDomainDestination.setName(itineraryDateDomainSource.getName());
                itineraryDateDomainDestination.setDescription(itineraryDateDomainSource.getDescription());
                itineraryDateDomainDestination.setIdentify(itineraryDateDomainSource.getIdentify());
                itineraryDateDomainDestination.setItineraryDate(itineraryDateDomainSource.getItineraryDate());
                itineraryDateDomainDestination.setIndex(itineraryDateDomainSource.getIndex());
                itineraryDateDomainDestination.setItemItinerary(itineraryDateDomainSource.getItemItinerary());
                itineraryDateDomainDestination.setRegisterGroup(registerGroupDomain);
                itineraryDateDomainDestination.setTours(itineraryDateDomainSource.getTours());
                itineraryDateDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                itineraryDateDomainDestination.setIdItineraryDateQuotation(itineraryDateDomainSource.getId());

                ItineraryDateDomain resultItineraryDateDomainDestination = itineraryDateRepo.create(itineraryDateDomainDestination);
                if (resultItineraryDateDomainDestination.getItemItinerary() != null) {
                    mapItineraryDateByItemItinerary.put(resultItineraryDateDomainDestination.getItemItinerary(), resultItineraryDateDomainDestination);
                }

                List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain> listItineraryItem = itineraryItemCrmRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseChangeStateOpened(itineraryDateDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();

                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain itineraryItemDomainSource: listItineraryItemResult
                    ) {
                        ItineraryItemDomain itineraryItemDomainDestination = new ItineraryItemDomain();
                        itineraryItemDomainDestination.setName(itineraryItemDomainSource.getName());
                        itineraryItemDomainDestination.setDescription(itineraryItemDomainSource.getDescription());
                        itineraryItemDomainDestination.setIdentify(itineraryItemDomainSource.getIdentify());
                        itineraryItemDomainDestination.setServiceGroup(itineraryItemDomainSource.getServiceGroup());
                        itineraryItemDomainDestination.setObservation(itineraryItemDomainSource.getObservation());
                        itineraryItemDomainDestination.setItineraryDateTime(itineraryItemDomainSource.getItineraryDateTime());
                        itineraryItemDomainDestination.setPaxTotal(itineraryItemDomainSource.getPaxTotal());
                        itineraryItemDomainDestination.setPrice(itineraryItemDomainSource.getPrice());
                        itineraryItemDomainDestination.setPaxAdultTotal(itineraryItemDomainSource.getPaxAdultTotal());
                        itineraryItemDomainDestination.setPriceAdult(itineraryItemDomainSource.getPriceAdult());
                        itineraryItemDomainDestination.setPaxChildrenTotal(itineraryItemDomainSource.getPaxChildrenTotal());
                        itineraryItemDomainDestination.setPriceChildren(itineraryItemDomainSource.getPriceChildren());
                        itineraryItemDomainDestination.setPaxTcTotal(itineraryItemDomainSource.getPaxTcTotal());
                        itineraryItemDomainDestination.setPriceTc(itineraryItemDomainSource.getPriceTc());
                        itineraryItemDomainDestination.setPaxTcLocalTotal(itineraryItemDomainSource.getPaxTcLocalTotal());
                        itineraryItemDomainDestination.setPriceTcLocal(itineraryItemDomainSource.getPriceTcLocal());
                        itineraryItemDomainDestination.setPaxGuideTotal(itineraryItemDomainSource.getPaxGuideTotal());
                        itineraryItemDomainDestination.setPriceGuide(itineraryItemDomainSource.getPriceGuide());

                        itineraryItemDomainDestination.setItineraryDate(resultItineraryDateDomainDestination);
                        itineraryItemDomainDestination.setIndex(itineraryItemDomainSource.getIndex());
                        itineraryItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        itineraryItemDomainDestination.setSupplierType(itineraryItemDomainSource.getSupplierType());
                        itineraryItemDomainDestination.setSupplier(itineraryItemDomainSource.getSupplier());
                        itineraryItemDomainDestination.setService(itineraryItemDomainSource.getService());
                        itineraryItemDomainDestination.setDepartmentRegion(itineraryItemDomainSource.getDepartmentRegion());
                        itineraryItemDomainDestination.setTour(itineraryItemDomainSource.getTour());
                        itineraryItemDomainDestination.setCustomer(registerGroupDomain.getCustomer());
                        // Supplier Type Lodging

                        itineraryItemDomainDestination.setLodgingCantSwb(itineraryItemDomainSource.getLodgingCantSwb());
                        itineraryItemDomainDestination.setLodgingCantMat(itineraryItemDomainSource.getLodgingCantMat());
                        itineraryItemDomainDestination.setLodgingCantTwin(itineraryItemDomainSource.getLodgingCantTwin());
                        itineraryItemDomainDestination.setLodgingCantTrp(itineraryItemDomainSource.getLodgingCantTrp());

                        itineraryItemDomainDestination.setLodgingCantBreakfast(itineraryItemDomainSource.getLodgingCantBreakfast());
                        itineraryItemDomainDestination.setLodgingCantLunch(itineraryItemDomainSource.getLodgingCantLunch());
                        itineraryItemDomainDestination.setLodgingCantDinner(itineraryItemDomainSource.getLodgingCantDinner());
                        itineraryItemDomainDestination.setLodgingCantBoxLunch(itineraryItemDomainSource.getLodgingCantBoxLunch());

                        itineraryItemDomainDestination.setLodgingCantNight(itineraryItemDomainSource.getLodgingCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastTcGuide(itineraryItemDomainSource.getLodgingCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setLodgingCantLunchTcGuide(itineraryItemDomainSource.getLodgingCantLunchTcGuide());
                        itineraryItemDomainDestination.setLodgingCantDinnerTcGuide(itineraryItemDomainSource.getLodgingCantDinnerTcGuide());
                        itineraryItemDomainDestination.setLodgingCantBoxLunchTcGuide(itineraryItemDomainSource.getLodgingCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setLodgingCantSwbTcGuide(itineraryItemDomainSource.getLodgingCantSwbTcGuide());
                        itineraryItemDomainDestination.setLodgingCantTwinTcGuide(itineraryItemDomainSource.getLodgingCantTwinTcGuide());

                        itineraryItemDomainDestination.setLodgingTcCantNight(itineraryItemDomainSource.getLodgingTcCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastTcLocalGuide(itineraryItemDomainSource.getLodgingCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantLunchTcLocalGuide(itineraryItemDomainSource.getLodgingCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantDinnerTcLocalGuide(itineraryItemDomainSource.getLodgingCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getLodgingCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setLodgingCantSwbTcLocalGuide(itineraryItemDomainSource.getLodgingCantSwbTcLocalGuide());
                        itineraryItemDomainDestination.setLodgingCantTwinTcLocalGuide(itineraryItemDomainSource.getLodgingCantTwinTcLocalGuide());

                        itineraryItemDomainDestination.setLodgingTcLocalCantNight(itineraryItemDomainSource.getLodgingTcLocalCantNight());

                        itineraryItemDomainDestination.setLodgingCantBreakfastGuide(0);
                        itineraryItemDomainDestination.setLodgingCantLunchGuide(0);
                        itineraryItemDomainDestination.setLodgingCantDinnerGuide(0);

                        itineraryItemDomainDestination.setLodgingCantSwbGuide(0);
                        itineraryItemDomainDestination.setLodgingCantTwinGuide(0);

                        itineraryItemDomainDestination.setLodgingGuideCantNight(0);

                        itineraryItemDomainDestination.setLodgingForOperator(itineraryItemDomainSource.getLodgingForOperator());
                        // Supplier Type Lodge

                        itineraryItemDomainDestination.setLodgeCantSwb(itineraryItemDomainSource.getLodgeCantSwb());
                        itineraryItemDomainDestination.setLodgeCantMat(itineraryItemDomainSource.getLodgeCantMat());
                        itineraryItemDomainDestination.setLodgeCantTwin(itineraryItemDomainSource.getLodgeCantTwin());
                        itineraryItemDomainDestination.setLodgeCantTrp(itineraryItemDomainSource.getLodgeCantTrp());

                        itineraryItemDomainDestination.setLodgeCantBreakfast(itineraryItemDomainSource.getLodgeCantBreakfast());
                        itineraryItemDomainDestination.setLodgeCantLunch(itineraryItemDomainSource.getLodgeCantLunch());
                        itineraryItemDomainDestination.setLodgeCantDinner(itineraryItemDomainSource.getLodgeCantDinner());
                        itineraryItemDomainDestination.setLodgeCantBoxLunch(itineraryItemDomainSource.getLodgeCantBoxLunch());

                        itineraryItemDomainDestination.setLodgeCantNight(itineraryItemDomainSource.getLodgeCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastTcGuide(itineraryItemDomainSource.getLodgeCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setLodgeCantLunchTcGuide(itineraryItemDomainSource.getLodgeCantLunchTcGuide());
                        itineraryItemDomainDestination.setLodgeCantDinnerTcGuide(itineraryItemDomainSource.getLodgeCantDinnerTcGuide());
                        itineraryItemDomainDestination.setLodgeCantBoxLunchTcGuide(itineraryItemDomainSource.getLodgeCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setLodgeCantSwbTcGuide(itineraryItemDomainSource.getLodgeCantSwbTcGuide());
                        itineraryItemDomainDestination.setLodgeCantTwinTcGuide(itineraryItemDomainSource.getLodgeCantTwinTcGuide());

                        itineraryItemDomainDestination.setLodgeTcCantNight(itineraryItemDomainSource.getLodgeTcCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastTcLocalGuide(itineraryItemDomainSource.getLodgeCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantLunchTcLocalGuide(itineraryItemDomainSource.getLodgeCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantDinnerTcLocalGuide(itineraryItemDomainSource.getLodgeCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getLodgeCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setLodgeCantSwbTcLocalGuide(itineraryItemDomainSource.getLodgeCantSwbTcLocalGuide());
                        itineraryItemDomainDestination.setLodgeCantTwinTcLocalGuide(itineraryItemDomainSource.getLodgeCantTwinTcLocalGuide());

                        itineraryItemDomainDestination.setLodgeTcLocalCantNight(itineraryItemDomainSource.getLodgeTcLocalCantNight());

                        itineraryItemDomainDestination.setLodgeCantBreakfastGuide(0);
                        itineraryItemDomainDestination.setLodgeCantLunchGuide(0);
                        itineraryItemDomainDestination.setLodgeCantDinnerGuide(0);

                        itineraryItemDomainDestination.setLodgeCantSwbGuide(0);
                        itineraryItemDomainDestination.setLodgeCantTwinGuide(0);

                        itineraryItemDomainDestination.setLodgeGuideCantNight(0);

                        itineraryItemDomainDestination.setLodgePvt(itineraryItemDomainSource.getLodgePvt());
                        itineraryItemDomainDestination.setLodgeSic(itineraryItemDomainSource.getLodgeSic());

                        // Supplier Type Restaurant

                        itineraryItemDomainDestination.setRestaurantCantBreakfast(itineraryItemDomainSource.getRestaurantCantBreakfast());
                        itineraryItemDomainDestination.setRestaurantCantLunch(itineraryItemDomainSource.getRestaurantCantLunch());
                        itineraryItemDomainDestination.setRestaurantCantDinner(itineraryItemDomainSource.getRestaurantCantDinner());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunch(itineraryItemDomainSource.getRestaurantCantBoxLunch());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastTcGuide(itineraryItemDomainSource.getRestaurantCantBreakfastTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchTcGuide(itineraryItemDomainSource.getRestaurantCantLunchTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerTcGuide(itineraryItemDomainSource.getRestaurantCantDinnerTcGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchTcGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchTcGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastTcLocalGuide(itineraryItemDomainSource.getRestaurantCantBreakfastTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchTcLocalGuide(itineraryItemDomainSource.getRestaurantCantLunchTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerTcLocalGuide(itineraryItemDomainSource.getRestaurantCantDinnerTcLocalGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchTcLocalGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchTcLocalGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastGuide(itineraryItemDomainSource.getRestaurantCantBreakfastGuide());
                        itineraryItemDomainDestination.setRestaurantCantLunchGuide(itineraryItemDomainSource.getRestaurantCantLunchGuide());
                        itineraryItemDomainDestination.setRestaurantCantDinnerGuide(itineraryItemDomainSource.getRestaurantCantDinnerGuide());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchGuide(itineraryItemDomainSource.getRestaurantCantBoxLunchGuide());

                        itineraryItemDomainDestination.setRestaurantCantBreakfastDrive(itineraryItemDomainSource.getRestaurantCantBreakfastDrive());
                        itineraryItemDomainDestination.setRestaurantCantLunchDrive(itineraryItemDomainSource.getRestaurantCantLunchDrive());
                        itineraryItemDomainDestination.setRestaurantCantDinnerDrive(itineraryItemDomainSource.getRestaurantCantDinnerDrive());
                        itineraryItemDomainDestination.setRestaurantCantBoxLunchDrive(itineraryItemDomainSource.getRestaurantCantBoxLunchDrive());

                        // Supplier Type Operator

                        itineraryItemDomainDestination.setOperatorPvt(itineraryItemDomainSource.getOperatorPvt());
                        itineraryItemDomainDestination.setOperatorSic(itineraryItemDomainSource.getOperatorSic());

                        itineraryItemDomainDestination.setLodgingForOperator(itineraryItemDomainSource.getLodgingForOperator());

                        itineraryItemDomainDestination.setLodgingForOperatorStr(itineraryItemDomainSource.getLodgingForOperatorStr());

                        itineraryItemDomainDestination.setIdItineraryItemQuotation(itineraryItemDomainSource.getId());

                        itineraryItemDomainDestination.setStateItineraryService(StateBookingEnum.DRAFT.getName());
                        itineraryItemDomainDestination.setStateServiceInvoice(StateServiceInvoiceEnum.PENDING_PAYMENT.getName());

                        itineraryItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        listItineraryItemUpdateCostTour.add(itineraryItemRepo.create(itineraryItemDomainDestination));
                    }
                }
            }
        }
        // Generate Passenger Group
        List<PassengerDomain> listPassenger = passengerRepo.searchAllByQuotationSimpleDomainCaseChangeStateOpened(registerGroupDomain.getQuotation().getId());
        if (listPassenger != null && !listPassenger.isEmpty()) {
            List<PassengerDomain> listPassengerResult = listPassenger.stream().collect(Collectors.toList());
            for (PassengerDomain passengerDomainSource: listPassengerResult
            ) {
                PassengerGroupDomain passengerGroupDomainDestination = new PassengerGroupDomain();
                passengerGroupDomainDestination.setName(passengerDomainSource.getName());
                passengerGroupDomainDestination.setDescription(passengerDomainSource.getDescription());
                passengerGroupDomainDestination.setIdentify(passengerDomainSource.getIdentify());
                passengerGroupDomainDestination.setSocialMediaInformation(passengerDomainSource.getSocialMediaInformation());
                passengerGroupDomainDestination.setWebSiteInformation(passengerDomainSource.getWebSiteInformation());
                passengerGroupDomainDestination.setContactInformation(passengerDomainSource.getContactInformation());
                passengerGroupDomainDestination.setAddress(passengerDomainSource.getAddress());
                passengerGroupDomainDestination.setDateInit(passengerDomainSource.getDateInit());
                passengerGroupDomainDestination.setDateEnd(passengerDomainSource.getDateEnd());
                passengerGroupDomainDestination.setDocument(passengerDomainSource.getDocument());
                passengerGroupDomainDestination.setObservation(passengerDomainSource.getObservation());
                passengerGroupDomainDestination.setPrincipal(passengerDomainSource.getPrincipal());
                passengerGroupDomainDestination.setImageUrl(passengerDomainSource.getImageUrl());
                passengerGroupDomainDestination.setTitlePerson(passengerDomainSource.getTitlePerson());
                passengerGroupDomainDestination.setTypePassengerTcBooking(passengerDomainSource.getTypePassengerTcBooking());
                passengerGroupDomainDestination.setPerson(passengerDomainSource.getPerson());
                passengerGroupDomainDestination.setNationality(passengerDomainSource.getNationality());
                passengerGroupDomainDestination.setDocumentType(passengerDomainSource.getDocumentType());
                passengerGroupDomainDestination.setPassengerType(passengerDomainSource.getPassengerType());
                passengerGroupDomainDestination.setRegisterGroup(registerGroupDomain);
                passengerGroupDomainDestination.setIndex(passengerDomainSource.getIndex());
                passengerGroupDomainDestination.setIdPassengerQuotation(passengerDomainSource.getId());

                passengerGroupDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                passengerGroupRepo.create(passengerGroupDomainDestination);
            }
        }
        // Generate Cost Tour
        List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTour = costTourCrmRepo.searchAllByQuotationSimpleDomainCaseChangeStateOpened(registerGroupDomain.getQuotation().getId());
        if (listCostTour != null && !listCostTour.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain> listCostTourResult = listCostTour.stream().collect(Collectors.toList());
            for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain costTourDomainSource: listCostTourResult
            ) {
                Map<String, CostTourItemDomain> mapCostTourItemService = new HashMap<>();

                CostTourDomain costTourDomainDestination = new CostTourDomain();
                costTourDomainDestination.setName(costTourDomainSource.getName());
                costTourDomainDestination.setDescription(costTourDomainSource.getDescription());
                costTourDomainDestination.setIdentify(costTourDomainSource.getIdentify());
                ItineraryDateDomain itineraryDateDomain = mapItineraryDateByItemItinerary.get(costTourDomainSource.getItineraryDate().getItemItinerary());
                costTourDomainDestination.setItineraryDate(itineraryDateDomain);
                costTourDomainDestination.setNumMaxPax(costTourDomainSource.getNumMaxPax());
                costTourDomainDestination.setRegisterGroup(registerGroupDomain);
                costTourDomainDestination.setSubTotalAmount(costTourDomainSource.getSubTotalAmount());
                costTourDomainDestination.setTour(costTourDomainSource.getTour());
                costTourDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                CostTourDomain costTourDomainDestinationResult = costTourRepo.create(costTourDomainDestination);

                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain> listCostTourItem = costTourItemCrmRepo.searchAllByCostTourAndQuotationSimpleDomainCaseChangeStateOpened(costTourDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain> listCostTourItemResult = new ArrayList<>();
                if (listCostTourItem != null && !listCostTourItem.isEmpty()) {
                    listCostTourItemResult = listCostTourItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain costTourItemDomainSource : listCostTourItemResult
                    ) {
                        CostTourItemDomain costTourItemDomainDestination = new CostTourItemDomain();
                        costTourItemDomainDestination.setName(costTourItemDomainSource.getName());
                        costTourItemDomainDestination.setDescription(costTourItemDomainSource.getDescription());
                        costTourItemDomainDestination.setIdentify(costTourItemDomainSource.getIdentify());
                        costTourItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        costTourItemDomainDestination.setItineraryDate(itineraryDateDomain);
                        costTourItemDomainDestination.setTour(costTourItemDomainSource.getTour());
                        costTourItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        costTourItemDomainDestination.setPassengerType(costTourItemDomainSource.getPassengerType());
                        costTourItemDomainDestination.setCantPax(costTourItemDomainSource.getCantPax());
                        costTourItemDomainDestination.setPricePax(costTourItemDomainSource.getPricePax());

                        costTourItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        costTourItemDomainDestination = costTourItemRepo.create(costTourItemDomainDestination);
                        if (costTourItemDomainSource.getCostTourServiceItem() != null){
                            mapCostTourItemService.put(costTourItemDomainSource.getCostTourServiceItem().getId(), costTourItemDomainDestination);
                        }
                    }
                }

                List<ItineraryItemDomain>  tempForUpdates = listItineraryItemUpdateCostTour.stream()
                        .filter(itineraryItemDomain -> _matchesCostTourAndItineraryDate(itineraryItemDomain, costTourDomainDestinationResult))
                        .collect(Collectors.toList());
                if (tempForUpdates != null && !tempForUpdates.isEmpty()){
                    for (ItineraryItemDomain itemTempForUpdate: tempForUpdates){
                        itemTempForUpdate.setCostTour(costTourDomainDestinationResult);
                        itineraryItemRepo.edit(itemTempForUpdate);
                    }
                }

                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain> listCostTourServiceItem = costTourServiceItemCrmRepo.searchAllByCostTourAndQuotationCaseChangeStateOpened(costTourDomainSource.getId(), registerGroupDomain.getQuotation().getId());
                List<com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain> listCostTourServiceItemResult = new ArrayList<>();
                if (listCostTourServiceItem != null && !listCostTourServiceItem.isEmpty()) {
                    listCostTourServiceItemResult = listCostTourServiceItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain costTourServiceItemDomainSource : listCostTourServiceItemResult
                    ) {
                        CostTourServiceItemDomain costTourServiceItemDomainDestination = new CostTourServiceItemDomain();
                        costTourServiceItemDomainDestination.setName(costTourServiceItemDomainSource.getName());
                        costTourServiceItemDomainDestination.setDescription(costTourServiceItemDomainSource.getDescription());
                        costTourServiceItemDomainDestination.setIdentify(costTourServiceItemDomainSource.getIdentify());
                        costTourServiceItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        ItineraryDateDomain itineraryDateDomain1 = mapItineraryDateByItemItinerary.get(costTourServiceItemDomainSource.getItineraryDate().getItemItinerary());
                        costTourServiceItemDomainDestination.setItineraryDate(itineraryDateDomain1);
                        costTourServiceItemDomainDestination.setTour(costTourDomainDestinationResult.getTour());
                        costTourServiceItemDomainDestination.setRegisterGroup(registerGroupDomain);
                        costTourServiceItemDomainDestination.setSupplierType(costTourServiceItemDomainSource.getSupplierType());
                        costTourServiceItemDomainDestination.setSupplier(costTourServiceItemDomainSource.getSupplier());
                        costTourServiceItemDomainDestination.setService(costTourServiceItemDomainSource.getService());
                        costTourServiceItemDomainDestination.setObservation(costTourServiceItemDomainSource.getObservation());

                        costTourServiceItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        costTourServiceItemDomainDestination = costTourServiceItemRepo.create(costTourServiceItemDomainDestination);

                        if (mapCostTourItemService.containsKey(costTourServiceItemDomainSource.getId())){
                            mapCostTourItemService.get(costTourServiceItemDomainSource.getId()).setCostTourServiceItem(costTourServiceItemDomainDestination);
                            costTourItemRepo.edit(mapCostTourItemService.get(costTourServiceItemDomainSource.getId()));
                        }
                    }
                }
            }
        }
        // Update Quotation
        QuotationDomain quotationDomainDbEdit = quotationRepo.findById(registerGroupDomain.getQuotation().getId());
        quotationDomainDbEdit.setResponsibleFile(registerGroupDomain.getResponsible());
        quotationDomainDbEdit.setIdentifyFile(registerGroupDomain.getIdentify());
        quotationDomainDbEdit.setStateQuotation(StateQuotationEnum.IN_BOOKING.getName());
        quotationRepo.edit(quotationDomainDbEdit);
    }

    private void _changeStateCompleted(RegisterGroupDomain registerGroupDomain, RegisterGroupChangeStateRequest request){

    }

    private void _changeStateInitialized(RegisterGroupDomain registerGroupDomain, RegisterGroupChangeStateRequest request){

    }

    private void _changeStateFinished(RegisterGroupDomain registerGroupDomain, RegisterGroupChangeStateRequest request){

    }

    private void _changeStateClosed(RegisterGroupDomain registerGroupDomain, RegisterGroupChangeStateRequest request){

    }

    private void _changeStateAnnulled(RegisterGroupDomain registerGroupDomain, RegisterGroupChangeStateRequest request){
        QuotationChangeStateRequest quotationChangeStateRequest = new QuotationChangeStateRequest();
        quotationChangeStateRequest.setStateQuotation(StateQuotationEnum.CANCEL.getName());
        quotationUseCase.changeState(registerGroupDomain.getQuotation().getId(), quotationChangeStateRequest);
    }

    private void _changeStateReview(RegisterGroupDomain registerGroupDomain, RegisterGroupChangeStateRequest request){

    }
}
