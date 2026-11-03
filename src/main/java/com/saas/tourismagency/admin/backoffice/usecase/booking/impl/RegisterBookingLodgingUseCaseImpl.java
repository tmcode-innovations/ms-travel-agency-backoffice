package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodging.RegisterBookingLodgingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingLodging;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistCuponNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistRegisterBookingNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodging.RegisterBookingItemLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.RegisterBookingLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom.RegisterBookingRoomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodging.RegisterBookingItemLodgingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.WrapFeedingAdvancedManyDateItem;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.WrapFeedingItem;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.RegisterBookingRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodging.JRRegisterBookingLodgingCuponRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodging.JRRegisterBookingLodgingRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingrestaurant.JRRegisterBookingRestaurantCuponRepository;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.*;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerBookingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingLodgingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class RegisterBookingLodgingUseCaseImpl implements RegisterBookingLodgingUseCase {

    @Autowired
    private RegisterBookingLodgingRepo repo;

    @Autowired
    private RegisterBookingLodgingConverter converter;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierLodgingRepo supplierLodgingRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RegisterBookingItemLodgingRepo registerBookingItemLodgingRepo;

    @Autowired
    private RegisterBookingItemLodgingFeedingRepo registerBookingItemLodgingFeedingRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private NationalityRepo nationalityRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private RegisterBookingRoomRepo registerBookingRoomRepo;

    @Autowired
    private RegisterBookingItemLodgingUseCase registerBookingItemLodgingUseCase;

    @Autowired
    PersistRegisterBookingNumberCallbackImpl persistRegisterBookingNumberCallback;

    @Autowired
    PersistCuponNumberCallbackImpl persistCuponNumberCallback;

    @Autowired
    private SetFieldDefaultRegisterBookingLodging setFieldDefault;

    @Autowired
    private PassengerBookingUseCase passengerBookingUseCase;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRRegisterBookingLodgingCuponRepository jrRegisterBookingLodgingCuponRepository;

    @Autowired
    private JRRegisterBookingLodgingRepository jrRegisterBookingLodgingRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public RegisterBookingLodgingResponse create(RegisterBookingLodgingCreateRequest request) {
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }

        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            customerDomain = customerRepo.findById(request.getCustomer());
        }

        EmployeeDomain employeeDomain = null;
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            employeeDomain = employeeRepo.findById(request.getEmployee());
        }

        SupplierLodgingDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierLodgingRepo.findById(request.getSupplier());
        }

        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingLodgingDomain newDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        newDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        newDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGING.getName());
        RegisterBookingLodgingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingLodgingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingLodgingResponse edit(String id, RegisterBookingLodgingUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomain = null;
        if (request.getSupplierType() != null && !request.getSupplierType().isEmpty()){
            supplierTypeDomain = supplierTypeRepo.findById(request.getSupplierType());
        }

        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            customerDomain = customerRepo.findById(request.getCustomer());
        }

        EmployeeDomain employeeDomain = null;
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            employeeDomain = employeeRepo.findById(request.getEmployee());
        }

        SupplierLodgingDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierLodgingRepo.findById(request.getSupplier());
        }

        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain);
        registerBookingLodgingDomain.setIdentify(old.getIdentify());
        registerBookingLodgingDomain.setStateBooking(old.getStateBooking());
        registerBookingLodgingDomain.setTypeInheritanceBooking(old.getTypeInheritanceBooking());

        registerBookingLodgingDomain.setDateAperture(old.getDateAperture());
        registerBookingLodgingDomain.setInitTour(old.getInitTour());
        registerBookingLodgingDomain.setEndTour(old.getEndTour());

        if (request.getInitHourTour() != null){
            LocalDateTime localDateTimeInit = LocalDateTime.of(
                    old.getInitTour().getYear(),
                    old.getInitTour().getMonth(),
                    old.getInitTour().getDayOfMonth(),
                    request.getInitHourTour().getHour(),
                    request.getInitHourTour().getMinute(),
                    request.getInitHourTour().getSecond()
            );
            registerBookingLodgingDomain.setInitHourTour(localDateTimeInit);
        }

        if (request.getEndHourTour() != null){
            LocalDateTime localDateTimeEnd = LocalDateTime.of(
                    old.getEndTour().getYear(),
                    old.getEndTour().getMonth(),
                    old.getEndTour().getDayOfMonth(),
                    request.getEndHourTour().getHour(),
                    request.getEndHourTour().getMinute(),
                    request.getEndHourTour().getSecond()
            );
            registerBookingLodgingDomain.setEndHourTour(localDateTimeEnd);
        }

        registerBookingLodgingDomain.setNumberIngress(old.getNumberIngress());

        registerBookingLodgingDomain.setCantPax(old.getCantPax());
        registerBookingLodgingDomain.setCantFree(old.getCantFree());
        registerBookingLodgingDomain.setCantPaxAdult(old.getCantPaxAdult());
        registerBookingLodgingDomain.setCantPaxChildren(old.getCantPaxChildren());
        registerBookingLodgingDomain.setTc(old.getTc());
        registerBookingLodgingDomain.setTcLocal(old.getTcLocal());

        registerBookingLodgingDomain.setCantSwb(old.getCantSwb());
        registerBookingLodgingDomain.setCantSwbTcGuide(old.getCantSwbTcGuide());
        registerBookingLodgingDomain.setCantSwbTcLocalGuide(old.getCantSwbTcLocalGuide());
        registerBookingLodgingDomain.setCantTrp(old.getCantTrp());
        registerBookingLodgingDomain.setCantCdp(old.getCantCdp());
        registerBookingLodgingDomain.setCantQtp(old.getCantQtp());
        registerBookingLodgingDomain.setCantSxtp(old.getCantSxtp());
        registerBookingLodgingDomain.setCantTwin(old.getCantTwin());
        registerBookingLodgingDomain.setCantTwinTcGuide(old.getCantTwinTcGuide());
        registerBookingLodgingDomain.setCantTwinTcLocalGuide(old.getCantTwinTcLocalGuide());
        registerBookingLodgingDomain.setCantMat(old.getCantMat());

        registerBookingLodgingDomain.setDateConfirmation(old.getDateConfirmation());
        registerBookingLodgingDomain.setDateReConfirmation(old.getDateReConfirmation());

        registerBookingLodgingDomain.setActiveRegisterBooking(old.getActiveRegisterBooking());

        RegisterBookingLodgingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingLodgingDomain, old));
        return RegisterBookingLodgingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingLodgingResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingLodgingResponse> findAll() {
        return repo.findAll(TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGING.getName()).stream().map(registerBookingLodgingDomain -> RegisterBookingLodgingResponse.from(registerBookingLodgingDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgingResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingLodgingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgingSearchResponse searchAll(RegisterBookingLodgingSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingLodgingDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingLodgingSearchResponse bankSearchRespose = new RegisterBookingLodgingSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingLodgingResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgingSearchResponse searchFilterAll(RegisterBookingLodgingSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingLodgingDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingLodgingSearchResponse bankSearchRespose = new RegisterBookingLodgingSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingLodgingResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgingResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingLodgingDomain old = repo.findByNameAndTypeInheritanceBooking(name, TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGING.getName());
        if (old == null) {
            throw new NotFoundException("No register booking lodging found with name '%s'.".formatted(name));
        }
        return RegisterBookingLodgingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgingResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingLodgingDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGING.getName());
        if (old == null) {
            throw new NotFoundException("No register booking lodging found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingLodgingResponse.from(old);
    }

    @Override
    @Transactional
    public RegisterBookingLodgingResponse generate(String idRegisterGroup, String idSupplier) {
        RegisterBookingLodgingResponse result = null;
        SupplierLodgingDomain supplierDomain = null;
        if (idSupplier != null && !idSupplier.isEmpty()){
            supplierDomain = supplierLodgingRepo.findById(idSupplier);
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        }
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = new ArrayList<>();
        RegisterBookingLodgingDomain registerBookingLodgingDomain = new RegisterBookingLodgingDomain();
        registerBookingLodgingDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGING.getName());
        registerBookingLodgingDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        registerBookingLodgingDomain.setSupplier(supplierDomain);
        registerBookingLodgingDomain.setSupplierType(supplierTypeDomainLodging);
        registerBookingLodgingDomain.setRegisterGroup(registerGroupDomain);
        registerBookingLodgingDomain.setEmployee(registerGroupDomain.getResponsible());
        registerBookingLodgingDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        registerBookingLodgingDomain.setDateAperture(LocalDate.now());
        registerBookingLodgingDomain.setCustomer(registerGroupDomain.getCustomer());
        registerBookingLodgingDomain.setGastosExtrasNoIncluidos(true);
        registerBookingLodgingDomain.setArriveAir(registerGroupDomain.getArriveAir());
        registerBookingLodgingDomain.setDepartureAir(registerGroupDomain.getDepartureAir());
        registerBookingLodgingDomain.setCantSwb(0);
        registerBookingLodgingDomain.setCantTrp(0);
        registerBookingLodgingDomain.setCantCdp(0);
        registerBookingLodgingDomain.setCantTwin(0);
        registerBookingLodgingDomain.setCantMat(0);
        registerBookingLodgingDomain.setCantQtp(0);
        registerBookingLodgingDomain.setCantSxtp(0);
        registerBookingLodgingDomain.setCantSwbTcGuide(0);
        registerBookingLodgingDomain.setCantTwinTcGuide(0);
        registerBookingLodgingDomain.setCantSwbTcLocalGuide(0);
        registerBookingLodgingDomain.setCantTwinTcLocalGuide(0);
        registerBookingLodgingDomain.setCantSwbGuide(0);
        registerBookingLodgingDomain.setCantTwinGuide(0);
        registerBookingLodgingDomain.setCantPax(0);
        registerBookingLodgingDomain.setCantFree(0);
        registerBookingLodgingDomain.setCantPaxAdult(0);
        registerBookingLodgingDomain.setCantPaxChildren(0);
        registerBookingLodgingDomain.setTc(0);
        registerBookingLodgingDomain.setTcLocal(0);
        registerBookingLodgingDomain.setGuide(0);
        registerBookingLodgingDomain.setTotalPax(0);
        registerBookingLodgingDomain.setActiveRegisterBooking(true);

        registerBookingLodgingDomain.setTenant(UserResolver.resolveUser().getTenant());

        Integer cantAdult = 0;
        Integer cantChildren = 0;
        Integer cantTc = 0;
        Integer cantTcLocal = 0;
        Integer cantGuide = 0;
        Integer cantFree = 0;

        /*cantAdult = registerGroupDomain.getCantPaxAdult();
        cantChildren = registerGroupDomain.getCantPaxChildren();
        cantFree = registerGroupDomain.getCantFree();
        cantTc = registerGroupDomain.getTC();
        cantTcLocal = registerGroupDomain.getTCLocal();
        cantGuide = registerGroupDomain.getGuide();

        registerBookingLodgingDomain.setCantPaxAdult(cantAdult);
        registerBookingLodgingDomain.setCantPaxChildren(cantChildren);
        registerBookingLodgingDomain.setCantFree(cantFree);
        registerBookingLodgingDomain.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                + (cantChildren != null ? cantChildren : 0)
                + (cantFree != null ? cantFree : 0)
        );
        registerBookingLodgingDomain.setTc(cantTc);
        registerBookingLodgingDomain.setTcLocal(cantTcLocal);
        registerBookingLodgingDomain.setGuide(cantGuide);
        registerBookingLodgingDomain.setTotalPax(
                (registerBookingLodgingDomain.getCantPax() != null ?  registerBookingLodgingDomain.getCantPax() : 0)
                + (registerBookingLodgingDomain.getTc() != null ?  registerBookingLodgingDomain.getTc() : 0)
                + (registerBookingLodgingDomain.getTcLocal() != null ?  registerBookingLodgingDomain.getTcLocal() : 0)
                        + (registerBookingLodgingDomain.getGuide() != null ?  registerBookingLodgingDomain.getGuide() : 0)
        );*/

        if (supplierDomain.getConfirmation() != null && supplierDomain.getConfirmation()){
            registerBookingLodgingDomain.setConfirmation(true);
            registerBookingLodgingDomain.setValueConfirmation(supplierDomain.getValueConfirmation());
            registerBookingLodgingDomain.setDateConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueConfirmation()));
        }else{
            registerBookingLodgingDomain.setConfirmation(false);
            registerBookingLodgingDomain.setValueConfirmation(0);
        }

        if (supplierDomain.getReConfirmation() != null && supplierDomain.getReConfirmation()){
            registerBookingLodgingDomain.setReConfirmation(true);
            registerBookingLodgingDomain.setValueReConfirmation(supplierDomain.getValueReConfirmation());
            registerBookingLodgingDomain.setDateReConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueReConfirmation()));
        }else{
            registerBookingLodgingDomain.setReConfirmation(false);
            registerBookingLodgingDomain.setValueReConfirmation(0);
        }
        Map<Integer, List<ItineraryItemDomain>> mapItemLodgingService = new HashMap<>();
        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBookingAction(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()) {
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBookingActionHotel(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                    for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult){
                        if ( supplierDomain.getId().equals(itineraryItemDomain.getSupplier().getId()) ){
                            if ( !mapItemLodgingService.containsKey( Integer.parseInt(itineraryDateDomain.getItemItinerary()) ) ){
                                mapItemLodgingService.put(Integer.parseInt(itineraryDateDomain.getItemItinerary()), new ArrayList<ItineraryItemDomain>());
                                mapItemLodgingService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }else{
                                mapItemLodgingService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }
                        }
                    }
                }
            }
        }
        Map<Integer, List<ItineraryItemDomain>> mapItemLodgingServiceResult = new HashMap<>();
        Integer countNumberIngres = 1;
        Integer typeItemItineraryTemp = 0;
        for (Integer keyLodgingService: mapItemLodgingService.keySet()) {
            Integer typeItemItinerary = keyLodgingService;
            if ( typeItemItineraryTemp == 0 ){
                typeItemItineraryTemp = typeItemItinerary;
                if ( !mapItemLodgingServiceResult.containsKey( countNumberIngres ) ){
                    mapItemLodgingServiceResult.put( countNumberIngres, new ArrayList<>());
                    for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemLodgingService.get(typeItemItinerary)) {
                        mapItemLodgingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                    }
                }else{
                    for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemLodgingService.get(typeItemItinerary)) {
                        mapItemLodgingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                    }
                }
            }else{
                if (typeItemItineraryTemp + 1 == typeItemItinerary){
                    typeItemItineraryTemp = typeItemItinerary;
                    if ( !mapItemLodgingServiceResult.containsKey( countNumberIngres ) ){
                        mapItemLodgingServiceResult.put( countNumberIngres, new ArrayList<>());
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemLodgingService.get(typeItemItinerary)) {
                            mapItemLodgingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }else{
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemLodgingService.get(typeItemItinerary)) {
                            mapItemLodgingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }
                }else{
                    countNumberIngres = countNumberIngres + 1;
                    typeItemItineraryTemp = typeItemItinerary;
                    if ( !mapItemLodgingServiceResult.containsKey( countNumberIngres ) ){
                        mapItemLodgingServiceResult.put( countNumberIngres, new ArrayList<>());
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemLodgingService.get(typeItemItinerary)) {
                            mapItemLodgingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }else{
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemLodgingService.get(typeItemItinerary)) {
                            mapItemLodgingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }
                }
            }
        }
        registerBookingLodgingDomain.setNumberIngress(mapItemLodgingServiceResult.size());
        RegisterBookingLodgingDomain createdDomainRegisterBookingLodging = repo.create(registerBookingLodgingDomain);
        for (Integer itemLodgingServiceType : mapItemLodgingServiceResult.keySet()) {
            List<ItineraryItemDomain> list = new ArrayList<ItineraryItemDomain>();
            list.addAll(mapItemLodgingServiceResult.get(itemLodgingServiceType));
            RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = new RegisterBookingItemLodgingDomain();
            registerBookingItemLodgingDomain.setRegisterBookingLodging(createdDomainRegisterBookingLodging);
            registerBookingItemLodgingDomain.setRegisterGroup(registerGroupDomain);
            List<RegisterBookingItemLodgingFeedingDomain> registerBookingItemLodgingFeedingDomains = new ArrayList<>();
            registerBookingItemLodgingDomain.setNoCupon(persistCuponNumberCallback.generateNumber());
            registerBookingItemLodgingDomain.setNoNight(0);
            registerBookingItemLodgingDomain.setNoNightTcGuide(0);
            registerBookingItemLodgingDomain.setNoNightTcLocalGuide(0);
            registerBookingItemLodgingDomain.setNoNightGuide(0);
            registerBookingItemLodgingDomain.setCantBreakfastBuffet(0);
            registerBookingItemLodgingDomain.setCantLunch(0);
            registerBookingItemLodgingDomain.setCantDinner(0);
            registerBookingItemLodgingDomain.setCantBoxLunch(0);
            registerBookingItemLodgingDomain.setCantTcGuideBreakfastBuffet(0);
            registerBookingItemLodgingDomain.setCantTcGuideLunch(0);
            registerBookingItemLodgingDomain.setCantTcGuideDinner(0);
            registerBookingItemLodgingDomain.setCantTcGuideBoxLunch(0);
            registerBookingItemLodgingDomain.setCantTcLocalGuideBreakfastBuffet(0);
            registerBookingItemLodgingDomain.setCantTcLocalGuideLunch(0);
            registerBookingItemLodgingDomain.setCantTcLocalGuideDinner(0);
            registerBookingItemLodgingDomain.setCantTcLocalGuideBoxLunch(0);
            registerBookingItemLodgingDomain.setCantGuideBreakfastBuffet(0);
            registerBookingItemLodgingDomain.setCantGuideLunch(0);
            registerBookingItemLodgingDomain.setCantGuideDinner(0);
            registerBookingItemLodgingDomain.setCantGuideBoxLunch(0);
            registerBookingItemLodgingDomain.setCantAb(0);
            registerBookingItemLodgingDomain.setCantCb(0);
            registerBookingItemLodgingDomain.setCantPaxAdult(0);
            registerBookingItemLodgingDomain.setCantPaxChildren(0);
            registerBookingItemLodgingDomain.setTc(0);
            registerBookingItemLodgingDomain.setTcLocal(0);
            registerBookingItemLodgingDomain.setGuide(0);
            registerBookingItemLodgingDomain.setCategory("");
            registerBookingItemLodgingDomain.setLodgingCategory(null);
            for (ItineraryItemDomain itineraryItemDomain: list) {
                Integer cantAdultItem = itineraryItemDomain.getPaxAdultTotal();
                Integer cantChildrenItem = itineraryItemDomain.getPaxChildrenTotal();
                Integer cantTcItem = itineraryItemDomain.getPaxTcTotal();
                Integer cantTcLocalItem = itineraryItemDomain.getPaxTcLocalTotal();
                Integer cantGuideItem = itineraryItemDomain.getPaxGuideTotal();

                if ( itineraryItemDomain.getPaxAdultTotal() != null ){
                    registerBookingItemLodgingDomain.setCantPaxAdult(itineraryItemDomain.getPaxAdultTotal());
                }
                if ( itineraryItemDomain.getPaxChildrenTotal() != null ){
                    registerBookingItemLodgingDomain.setCantPaxChildren(itineraryItemDomain.getPaxChildrenTotal());
                }
                if ( itineraryItemDomain.getPaxTcTotal() != null ){
                    registerBookingItemLodgingDomain.setTc(itineraryItemDomain.getPaxTcTotal());
                }
                if ( itineraryItemDomain.getPaxTcLocalTotal() != null ){
                    registerBookingItemLodgingDomain.setTcLocal(itineraryItemDomain.getPaxTcLocalTotal());
                }
                if ( itineraryItemDomain.getPaxGuideTotal() != null ){
                    registerBookingItemLodgingDomain.setGuide(itineraryItemDomain.getPaxGuideTotal());
                }
                if ( itineraryItemDomain.getLodgingCantNight() != null ){
                    registerBookingItemLodgingDomain.setNoNight(registerBookingItemLodgingDomain.getNoNight() + itineraryItemDomain.getLodgingCantNight());
                }
                if ( itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal().intValue() > 0 ){
                    registerBookingItemLodgingDomain.setNoNightTcGuide(registerBookingItemLodgingDomain.getNoNightTcGuide() + 1);
                }
                if ( itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal().intValue() > 0 ){
                    registerBookingItemLodgingDomain.setNoNightTcLocalGuide(registerBookingItemLodgingDomain.getNoNightTcLocalGuide() + 1);
                }
                if ( itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal().intValue() > 0 ){
                    registerBookingItemLodgingDomain.setNoNightGuide(registerBookingItemLodgingDomain.getNoNightGuide() + 1);
                }
                if (
                        itineraryItemDomain.getLodgingCantBreakfast() != null &&
                                itineraryItemDomain.getLodgingCantBreakfast() > 0 &&
                                itineraryItemDomain.getLodgingCantBreakfastItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantBreakfastBuffet(registerBookingItemLodgingDomain.getCantBreakfastBuffet() + itineraryItemDomain.getLodgingCantBreakfast());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBreakfast());
                    if (itineraryItemDomain.getLodgingCantBreakfastItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBreakfastItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBreakfastDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantLunch() != null &&
                        itineraryItemDomain.getLodgingCantLunch() > 0 &&
                        itineraryItemDomain.getLodgingCantLunchItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantLunch(registerBookingItemLodgingDomain.getCantLunch() + itineraryItemDomain.getLodgingCantLunch());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantLunch());
                    if (itineraryItemDomain.getLodgingCantLunchItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantLunchItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantLunchDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantDinner() != null &&
                        itineraryItemDomain.getLodgingCantDinner() > 0 &&
                        itineraryItemDomain.getLodgingCantDinnerItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantDinner(registerBookingItemLodgingDomain.getCantDinner() + itineraryItemDomain.getLodgingCantDinner());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantDinner());
                    if (itineraryItemDomain.getLodgingCantDinnerItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantDinnerItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantDinnerDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantBoxLunch() != null &&
                        itineraryItemDomain.getLodgingCantBoxLunch() > 0 &&
                        itineraryItemDomain.getLodgingCantBoxLunchItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantBoxLunch(registerBookingItemLodgingDomain.getCantBoxLunch() + itineraryItemDomain.getLodgingCantBoxLunch());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBoxLunch());
                    if (itineraryItemDomain.getLodgingCantBoxLunchItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBoxLunchItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBoxLunchDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }

                if ( itineraryItemDomain.getLodgingCantBreakfastTcGuide() != null &&
                        itineraryItemDomain.getLodgingCantBreakfastTcGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcGuideBreakfastBuffet(registerBookingItemLodgingDomain.getCantTcGuideBreakfastBuffet() + itineraryItemDomain.getLodgingCantBreakfastTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBreakfastTcGuide());
                    if (itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBreakfastTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBreakfastTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantLunchTcGuide() != null &&
                        itineraryItemDomain.getLodgingCantLunchTcGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcGuideLunch(registerBookingItemLodgingDomain.getCantTcGuideLunch() + itineraryItemDomain.getLodgingCantLunchTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantLunchTcGuide());
                    if (itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantLunchTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantLunchTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantDinnerTcGuide() != null &&
                        itineraryItemDomain.getLodgingCantDinnerTcGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcGuideDinner(registerBookingItemLodgingDomain.getCantTcGuideDinner() + itineraryItemDomain.getLodgingCantDinnerTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantDinnerTcGuide());
                    if (itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantDinnerTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantDinnerTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantBoxLunchTcGuide() != null &&
                        itineraryItemDomain.getLodgingCantBoxLunchTcGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcGuideBoxLunch(registerBookingItemLodgingDomain.getCantTcGuideBoxLunch() + itineraryItemDomain.getLodgingCantBoxLunchTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBoxLunchTcGuide());
                    if (itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBoxLunchTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBoxLunchTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }

                if ( itineraryItemDomain.getLodgingCantBreakfastTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgingCantBreakfastTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcLocalGuideBreakfastBuffet(registerBookingItemLodgingDomain.getCantTcLocalGuideBreakfastBuffet() + itineraryItemDomain.getLodgingCantBreakfastTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBreakfastTcLocalGuide());
                    if (itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBreakfastTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantLunchTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgingCantLunchTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcLocalGuideLunch(registerBookingItemLodgingDomain.getCantTcLocalGuideLunch() + itineraryItemDomain.getLodgingCantLunchTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantLunchTcLocalGuide());
                    if (itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantLunchTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantLunchTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantDinnerTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgingCantDinnerTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcLocalGuideDinner(registerBookingItemLodgingDomain.getCantTcLocalGuideDinner() + itineraryItemDomain.getLodgingCantDinnerTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantDinnerTcLocalGuide());
                    if (itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantDinnerTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantTcLocalGuideBoxLunch(registerBookingItemLodgingDomain.getCantTcLocalGuideBoxLunch() + itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuide());
                    if (itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBoxLunchTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }

                if ( itineraryItemDomain.getLodgingCantBreakfastGuide() != null &&
                        itineraryItemDomain.getLodgingCantBreakfastGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantGuideBreakfastBuffet(registerBookingItemLodgingDomain.getCantGuideBreakfastBuffet() + itineraryItemDomain.getLodgingCantBreakfastGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBreakfastGuide());
                    if (itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBreakfastGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBreakfastGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantLunchGuide() != null &&
                        itineraryItemDomain.getLodgingCantLunchGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantLunchGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantGuideLunch(registerBookingItemLodgingDomain.getCantGuideLunch() + itineraryItemDomain.getLodgingCantLunchGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantLunchGuide());
                    if (itineraryItemDomain.getLodgingCantLunchGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantLunchGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantLunchGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantDinnerGuide() != null &&
                        itineraryItemDomain.getLodgingCantDinnerGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantGuideDinner(registerBookingItemLodgingDomain.getCantGuideDinner() + itineraryItemDomain.getLodgingCantDinnerGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantDinnerGuide());
                    if (itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantDinnerGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantDinnerGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantBoxLunchGuide() != null &&
                        itineraryItemDomain.getLodgingCantBoxLunchGuide() > 0 &&
                        itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate() != null){
                    registerBookingItemLodgingDomain.setCantGuideBoxLunch(registerBookingItemLodgingDomain.getCantGuideBoxLunch() + itineraryItemDomain.getLodgingCantBoxLunchGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBoxLunchGuide());
                    if (itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgingCantBoxLunchGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgingCantBoxLunchGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCategory() != null ){
                    registerBookingItemLodgingDomain.setLodgingCategory(itineraryItemDomain.getLodgingCategory());
                }
                if ( itineraryItemDomain.getLodgingCategoryStr() != null ){
                    registerBookingItemLodgingDomain.setCategory(itineraryItemDomain.getLodgingCategoryStr());
                }
                if ( itineraryItemDomain.getLodgingCantSwb() != null ){
                    if ( itineraryItemDomain.getLodgingCantSwb() > registerBookingLodgingDomain.getCantSwb() ){
                        createdDomainRegisterBookingLodging.setCantSwb(itineraryItemDomain.getLodgingCantSwb());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTrp() != null ){
                    if ( itineraryItemDomain.getLodgingCantTrp() > registerBookingLodgingDomain.getCantTrp() ){
                        createdDomainRegisterBookingLodging.setCantTrp(itineraryItemDomain.getLodgingCantTrp());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTwin() != null ){
                    if ( itineraryItemDomain.getLodgingCantTwin() > registerBookingLodgingDomain.getCantTwin() ){
                        createdDomainRegisterBookingLodging.setCantTwin(itineraryItemDomain.getLodgingCantTwin());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantMat() != null ){
                    if ( itineraryItemDomain.getLodgingCantMat() > registerBookingLodgingDomain.getCantMat() ){
                        createdDomainRegisterBookingLodging.setCantMat(itineraryItemDomain.getLodgingCantMat());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantSwbTcGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantSwbTcGuide() > registerBookingLodgingDomain.getCantSwbTcGuide() ){
                        createdDomainRegisterBookingLodging.setCantSwbTcGuide(itineraryItemDomain.getLodgingCantSwbTcGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTwinTcGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantTwinTcGuide() > registerBookingLodgingDomain.getCantTwinTcGuide() ){
                        createdDomainRegisterBookingLodging.setCantTwinTcGuide(itineraryItemDomain.getLodgingCantTwinTcGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantSwbTcLocalGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantSwbTcLocalGuide() > registerBookingLodgingDomain.getCantSwbTcLocalGuide() ){
                        createdDomainRegisterBookingLodging.setCantSwbTcLocalGuide(itineraryItemDomain.getLodgingCantSwbTcLocalGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTwinTcLocalGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantTwinTcLocalGuide() > registerBookingLodgingDomain.getCantTwinTcLocalGuide() ){
                        createdDomainRegisterBookingLodging.setCantTwinTcLocalGuide(itineraryItemDomain.getLodgingCantTwinTcLocalGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantSwbGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantSwbGuide() > registerBookingLodgingDomain.getCantSwbGuide() ){
                        createdDomainRegisterBookingLodging.setCantSwbGuide(itineraryItemDomain.getLodgingCantSwbGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTwinGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantTwinGuide() > registerBookingLodgingDomain.getCantTwinGuide() ){
                        createdDomainRegisterBookingLodging.setCantTwinGuide(itineraryItemDomain.getLodgingCantTwinGuide());
                    }
                }

                if (cantAdultItem != null && cantAdultItem > cantAdult){
                    cantAdult = cantAdultItem;
                }

                if (cantChildrenItem != null && cantChildrenItem > cantChildren){
                    cantChildren = cantChildrenItem;
                }

                if (cantTcItem != null && cantTcItem > cantTc){
                    cantTc = cantTcItem;
                }

                if (cantTcLocalItem != null && cantTcLocalItem > cantTcLocal){
                    cantTcLocal = cantTcLocalItem;
                }

                if (cantGuideItem != null && cantGuideItem > cantGuide){
                    cantGuide = cantGuideItem;
                }
            }
            registerBookingItemLodgingDomain.setDateFrom(registerGroupDomain.getInitTour().plusDays(Integer.parseInt( list.get(0).getItineraryDate().getItemItinerary()) - 1));
            registerBookingItemLodgingDomain.setDateTo(registerBookingItemLodgingDomain.getDateFrom().plusDays(registerBookingItemLodgingDomain.getNoNight()));

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    registerBookingItemLodgingDomain.getDateFrom().getYear(),
                    registerBookingItemLodgingDomain.getDateFrom().getMonth(),
                    registerBookingItemLodgingDomain.getDateFrom().getDayOfMonth(),
                    0,
                    0,
                    0);
            registerBookingItemLodgingDomain.setDateTimeInitService(localDateTimeInitService);
            registerBookingItemLodgingDomain.setTenant(UserResolver.resolveUser().getTenant());
            RegisterBookingItemLodgingDomain createdDomainRegisterBookingItemLodging = registerBookingItemLodgingRepo.create(registerBookingItemLodgingDomain);
            registerBookingItemLodgingDomains.add(createdDomainRegisterBookingItemLodging);
            for (RegisterBookingItemLodgingFeedingDomain itemLodgingFeedingDomain: registerBookingItemLodgingFeedingDomains){
                itemLodgingFeedingDomain.setRegisterBookingItemLodging(createdDomainRegisterBookingItemLodging);
                itemLodgingFeedingDomain.setRegisterGroup(registerGroupDomain);
                itemLodgingFeedingDomain.setRegisterBookingLodging(createdDomainRegisterBookingLodging);
                itemLodgingFeedingDomain.setTenant(UserResolver.resolveUser().getTenant());
                registerBookingItemLodgingFeedingRepo.create(itemLodgingFeedingDomain);
            }

        }

        this.generateRoomBooking(TypeRoomBookingEnum.SWB.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantSwb());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantTwin());
        this.generateRoomBooking(TypeRoomBookingEnum.MAT.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantMat());
        this.generateRoomBooking(TypeRoomBookingEnum.TRP.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantTrp());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_TC.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantSwbTcGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_TC.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantTwinTcGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_TC_LOCAL.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantSwbTcLocalGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_TC_LOCAL.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantTwinTcLocalGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_GUIDE.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantSwbGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_GUIDE.getName(), registerGroupDomain, createdDomainRegisterBookingLodging, createdDomainRegisterBookingLodging.getCantTwinGuide());

        // Update models

        if ( registerBookingItemLodgingDomains.size() == 1 ){
            createdDomainRegisterBookingLodging.setInitTour(registerBookingItemLodgingDomains.get(0).getDateFrom());
            createdDomainRegisterBookingLodging.setEndTour(registerBookingItemLodgingDomains.get(0).getDateTo());
        }else if ( registerBookingItemLodgingDomains.size() > 1 ){
            Collections.sort(registerBookingItemLodgingDomains);
            Object [] arrayRegisterBookingItemLodgingDomains = registerBookingItemLodgingDomains.toArray();
            createdDomainRegisterBookingLodging.setInitTour(((RegisterBookingItemLodgingDomain)arrayRegisterBookingItemLodgingDomains[0]).getDateFrom());
            createdDomainRegisterBookingLodging.setEndTour(((RegisterBookingItemLodgingDomain)arrayRegisterBookingItemLodgingDomains[arrayRegisterBookingItemLodgingDomains.length-1]).getDateTo());
        }

        createdDomainRegisterBookingLodging.setCantPaxAdult(cantAdult);
        createdDomainRegisterBookingLodging.setCantPaxChildren(cantChildren);
        createdDomainRegisterBookingLodging.setCantFree(cantFree);
        createdDomainRegisterBookingLodging.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        createdDomainRegisterBookingLodging.setTc(cantTc);
        createdDomainRegisterBookingLodging.setTcLocal(cantTcLocal);
        createdDomainRegisterBookingLodging.setGuide(cantGuide);

        createdDomainRegisterBookingLodging.setTotalPax(
                (createdDomainRegisterBookingLodging.getCantPax() != null ?  createdDomainRegisterBookingLodging.getCantPax() : 0)
                        + (createdDomainRegisterBookingLodging.getTc() != null ?  createdDomainRegisterBookingLodging.getTc() : 0)
                        + (createdDomainRegisterBookingLodging.getTcLocal() != null ?  createdDomainRegisterBookingLodging.getTcLocal() : 0)
                        + (createdDomainRegisterBookingLodging.getGuide() != null ?  createdDomainRegisterBookingLodging.getGuide() : 0)
        );


        RegisterBookingLodgingDomain editedRegisterBookingLodgingDomain = repo.edit(createdDomainRegisterBookingLodging);
        result = RegisterBookingLodgingResponse.from(editedRegisterBookingLodgingDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(idRegisterGroup, idSupplier);
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), editedRegisterBookingLodgingDomain.getId());
        }

        return result;
    }

    private void generateRoomBooking(String typeRoomBooking, RegisterGroupDomain registerGroup, RegisterBookingLodgingDomain registerBooking, Integer cant){
        if (cant > 0){
            Stream.iterate(0, n -> n + 1)
                    .limit(cant)
                    .forEach(item -> createRoomBooking(typeRoomBooking, registerGroup, registerBooking, cant, item));
        }
    }

    private void createRoomBooking(String typeRoomBooking, RegisterGroupDomain registerGroup, RegisterBookingLodgingDomain registerBooking, Integer total, Integer current){
        RegisterBookingRoomDomain registerBookingRoomDomain = new RegisterBookingRoomDomain();
        registerBookingRoomDomain.setTypeRoomBooking(typeRoomBooking);
        if (typeRoomBooking.equals(TypeRoomBookingEnum.SWB.getName())){
            registerBookingRoomDomain.setName("HAB SWB - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.TWIN.getName())){
            registerBookingRoomDomain.setName("HAB TWIN - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.MAT.getName())){
            registerBookingRoomDomain.setName("HAB MAT - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.TRP.getName())){
            registerBookingRoomDomain.setName("HAB TRP - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.CDP.getName())){
            registerBookingRoomDomain.setName("HAB CDP - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.QTP.getName())){
            registerBookingRoomDomain.setName("HAB QTP - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.SXTP.getName())){
            registerBookingRoomDomain.setName("HAB SXTP - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.SWB_TC.getName())){
            registerBookingRoomDomain.setName("HAB SWB TC - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.SWB_TC_LOCAL.getName())){
            registerBookingRoomDomain.setName("HAB SWB TC LOCAL - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.SWB_GUIDE.getName())){
            registerBookingRoomDomain.setName("HAB SWB GUIA - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.TWIN_TC.getName())){
            registerBookingRoomDomain.setName("HAB TWIN TC - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())){
            registerBookingRoomDomain.setName("HAB TWIN TC LOCAL - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.TWIN_GUIDE.getName())){
            registerBookingRoomDomain.setName("HAB TWIN GUIA - " + (current + 1));
        }
        registerBookingRoomDomain.setRegisterBookingLodging(registerBooking);
        registerBookingRoomDomain.setRegisterGroup(registerGroup);
        registerBookingRoomDomain.setTenant(UserResolver.resolveUser().getTenant());
        registerBookingRoomRepo.create(registerBookingRoomDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgingResponse> findAllRegisterBookingItemLodgingByBooking(String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgingUseCase.searchAllByBooking(idBooking);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgingResponse addRegisterBookingItemLodging(RegisterBookingItemLodgingCreateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgingUseCase.create(request, registerBookingLodgingDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgingResponse editRegisterBookingItemLodging(String id, RegisterBookingItemLodgingUpdateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgingUseCase.edit(id, request, registerBookingLodgingDomain);
    }

    @Override
    @Transactional
    public void deleteRegisterBookingItemLodging(String id, String idBooking) {
        RegisterBookingLodgingResponse old = findById(idBooking);
        registerBookingItemLodgingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgingResponse findByIdAndBooking(String id, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgingUseCase.searchByBookingAndRegisterBookingItem(idBooking, id);
    }

    @Override
    @Transactional
    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        PassengerGroupDomain passengerGroupDomain = null;
        if (request.getPassengerGroup() != null && !request.getPassengerGroup().isEmpty()){
            passengerGroupDomain = passengerGroupRepo.findById(request.getPassengerGroup());
        }
        RegisterBookingRoomDomain registerBookingRoomDomain = null;
        if (request.getRegisterBookingRoom() != null && !request.getRegisterBookingRoom().isEmpty()){
            registerBookingRoomDomain = registerBookingRoomRepo.findById(request.getRegisterBookingRoom());
        }
        return passengerBookingUseCase.create(request, passengerGroupDomain, null, null, registerBookingLodgingDomain, null, null, null, registerBookingLodgingDomain.getRegisterGroup(), registerBookingRoomDomain);
    }

    @Override
    @Transactional
    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        RegisterBookingRoomDomain registerBookingRoomDomain = null;
        if (request.getRegisterBookingRoom() != null && !request.getRegisterBookingRoom().isEmpty()){
            registerBookingRoomDomain = registerBookingRoomRepo.findById(request.getRegisterBookingRoom());
        }
        return passengerBookingUseCase.createMultiple(request, null, null, registerBookingLodgingDomain, null, null, null, registerBookingLodgingDomain.getRegisterGroup(), registerBookingRoomDomain);
    }

    @Override
    @Transactional
    public void deletePassengerBooking(String id, String idRegisterBooking) {
        RegisterBookingLodgingResponse old = findById(idRegisterBooking);
        passengerBookingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingLodgingAndRegisterGroup(idRegisterBooking, registerBookingLodgingDomain.getRegisterGroup().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(String idRegisterBooking, String typeRoomBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingLodgingAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, registerBookingLodgingDomain.getRegisterGroup().getId(), typeRoomBooking);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgingCuponPdf(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> parametersOther = new HashMap<>();

        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

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

        parametersOther.put("activeUser", UserResolver.resolveUser());
        parametersOther.put("activeCompany", companyDomain);
        parametersOther.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResult = new ArrayList<>();
        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResultOther = new ArrayList<>();

        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(idRegisterBooking);
        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
            registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
        }

        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()){
            for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain : registerBookingItemLodgingDomains){
                RegisterBookingItemLodgingDomain temp = registerBookingItemLodgingRepo.findById(registerBookingItemLodgingDomain.getId());
                if (temp.getTcLocal() > 0){
                    List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodging(registerBookingItemLodgingDomain.getId());
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
                    temp.setStrFoods(resultFods.toString());

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

                    parametersOther.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

                    for (String localDateTime : dateTimeDinner){
                        resultDinnerFoods.append(localDateTime + ", " );
                    }
                    if ( resultDinnerFoodsV1.length() > 1 ){
                        resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                    }

                    parametersOther.put("strDinnerFoods", resultDinnerFoodsV1.toString());

                    for (String localDateTime : dateTimeLunch){
                        resultLunchFoods.append(localDateTime + ", " );
                    }
                    if ( resultLunchFoodsV1.length() > 1 ){
                        resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                    }

                    parametersOther.put("strLunchFoods", resultLunchFoodsV1.toString());

                    for (String localDateTime : dateTimeBoxLunch){
                        resultBoxLunchFoods.append(localDateTime + ", " );
                    }
                    if ( resultBoxLunchFoodsV1.length() > 1 ){
                        resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                    }

                    parametersOther.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

                    registerBookingItemLodgingDomainsResultOther.add(temp);
                }
                if (
                        temp.getCantPaxAdult() > 0 ||
                                temp.getCantPaxChildren() > 0 ||
                                temp.getTc() > 0 ||
                                temp.getGuide() > 0
                ){
                    List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodging(registerBookingItemLodgingDomain.getId());
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
                    temp.setStrFoods(resultFods.toString());

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

                    parameters.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

                    for (String localDateTime : dateTimeDinner){
                        resultDinnerFoods.append(localDateTime + ", " );
                    }
                    if ( resultDinnerFoodsV1.length() > 1 ){
                        resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                    }

                    parameters.put("strDinnerFoods", resultDinnerFoodsV1.toString());

                    for (String localDateTime : dateTimeLunch){
                        resultLunchFoods.append(localDateTime + ", " );
                    }
                    if ( resultLunchFoodsV1.length() > 1 ){
                        resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                    }

                    parameters.put("strLunchFoods", resultLunchFoodsV1.toString());

                    for (String localDateTime : dateTimeBoxLunch){
                        resultBoxLunchFoods.append(localDateTime + ", " );
                    }
                    if ( resultBoxLunchFoodsV1.length() > 1 ){
                        resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                    }

                    parameters.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

                    registerBookingItemLodgingDomainsResult.add(temp);
                }
            }
        }

        return jrRegisterBookingLodgingCuponRepository.exportWithUnion(registerBookingItemLodgingDomainsResult, registerBookingItemLodgingDomainsResultOther, ExportFormatEnum.PDF.getName(), parameters, parametersOther);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgingCuponPdf(String idRegisterBooking, String exportCuponTypePassengerBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> parametersOther = new HashMap<>();

        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

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

        parametersOther.put("activeUser", UserResolver.resolveUser());
        parametersOther.put("activeCompany", companyDomain);
        parametersOther.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResult = new ArrayList<>();
        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResultOther = new ArrayList<>();

        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllRegisterBookingItemLodgingSimpleDomainCaseOnluId(idRegisterBooking);
        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
            registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
        }

        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()){
            for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain : registerBookingItemLodgingDomains){
                RegisterBookingItemLodgingDomain temp = registerBookingItemLodgingRepo.findById(registerBookingItemLodgingDomain.getId());
                if (ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){
                    if (temp.getTcLocal() > 0){
                        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodging(registerBookingItemLodgingDomain.getId());
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
                        temp.setStrFoods(resultFods.toString());

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

                        parametersOther.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

                        for (String localDateTime : dateTimeDinner){
                            resultDinnerFoods.append(localDateTime + ", " );
                        }
                        if ( resultDinnerFoodsV1.length() > 1 ){
                            resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                        }

                        parametersOther.put("strDinnerFoods", resultDinnerFoodsV1.toString());

                        for (String localDateTime : dateTimeLunch){
                            resultLunchFoods.append(localDateTime + ", " );
                        }
                        if ( resultLunchFoodsV1.length() > 1 ){
                            resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                        }

                        parametersOther.put("strLunchFoods", resultLunchFoodsV1.toString());

                        for (String localDateTime : dateTimeBoxLunch){
                            resultBoxLunchFoods.append(localDateTime + ", " );
                        }
                        if ( resultBoxLunchFoodsV1.length() > 1 ){
                            resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                        }

                        parametersOther.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

                        registerBookingItemLodgingDomainsResultOther.add(temp);
                    }
                }else if (ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){

                    if (
                            temp.getCantPaxAdult() > 0 ||
                                    temp.getCantPaxChildren() > 0 ||
                                    temp.getTc() > 0 ||
                                    temp.getGuide() > 0
                    ){
                        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodging(registerBookingItemLodgingDomain.getId());
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
                        temp.setStrFoods(resultFods.toString());

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

                        parameters.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

                        for (String localDateTime : dateTimeDinner){
                            resultDinnerFoods.append(localDateTime + ", " );
                        }
                        if ( resultDinnerFoodsV1.length() > 1 ){
                            resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                        }

                        parameters.put("strDinnerFoods", resultDinnerFoodsV1.toString());

                        for (String localDateTime : dateTimeLunch){
                            resultLunchFoods.append(localDateTime + ", " );
                        }
                        if ( resultLunchFoodsV1.length() > 1 ){
                            resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                        }

                        parameters.put("strLunchFoods", resultLunchFoodsV1.toString());

                        for (String localDateTime : dateTimeBoxLunch){
                            resultBoxLunchFoods.append(localDateTime + ", " );
                        }
                        if ( resultBoxLunchFoodsV1.length() > 1 ){
                            resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                        }

                        parameters.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

                        registerBookingItemLodgingDomainsResult.add(temp);
                    }
                }

            }
        }

        return jrRegisterBookingLodgingCuponRepository.exportWithUnion(registerBookingItemLodgingDomainsResult, registerBookingItemLodgingDomainsResultOther, ExportFormatEnum.PDF.getName(), parameters, parametersOther);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgingItemCuponPdf(String idRegisterBookingItem) {
        if (idRegisterBookingItem == null) {
            return null;
        }
        RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = registerBookingItemLodgingRepo.findById(idRegisterBookingItem);
        if (registerBookingItemLodgingDomain == null) {
            throw new NotFoundException("No register booking item found with id '%s'.".formatted(idRegisterBookingItem));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> parametersOther = new HashMap<>();

        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

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

        parametersOther.put("activeUser", UserResolver.resolveUser());
        parametersOther.put("activeCompany", companyDomain);
        parametersOther.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResult = new ArrayList<>();
        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResultOther = new ArrayList<>();

        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodging(idRegisterBookingItem);

        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

        if (registerBookingItemLodgingDomain.getTcLocal() > 0){

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
                    List<RegisterBookingItemLodgingFeedingDomain> list = mapFeedings.get(itemLocalDateTime);
                    if (list != null && !list.isEmpty()){
                        for (RegisterBookingItemLodgingFeedingDomain item : list){
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

            parametersOther.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

            for (String localDateTime : dateTimeDinner){
                resultDinnerFoods.append(localDateTime + ", " );
            }
            if ( resultDinnerFoodsV1.length() > 1 ){
                resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
            }

            parametersOther.put("strDinnerFoods", resultDinnerFoodsV1.toString());

            for (String localDateTime : dateTimeLunch){
                resultLunchFoods.append(localDateTime + ", " );
            }
            if ( resultLunchFoodsV1.length() > 1 ){
                resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
            }

            parametersOther.put("strLunchFoods", resultLunchFoodsV1.toString());

            for (String localDateTime : dateTimeBoxLunch){
                resultBoxLunchFoods.append(localDateTime + ", " );
            }
            if ( resultBoxLunchFoodsV1.length() > 1 ){
                resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
            }

            parametersOther.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

            registerBookingItemLodgingDomainsResultOther.add(registerBookingItemLodgingDomain);
        }

        mapFeedings = new HashMap<>();

        if (
                registerBookingItemLodgingDomain.getCantPaxAdult() > 0 ||
                        registerBookingItemLodgingDomain.getCantPaxChildren() > 0 ||
                        registerBookingItemLodgingDomain.getTc() > 0 ||
                        registerBookingItemLodgingDomain.getGuide() > 0
        ){
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
                    List<RegisterBookingItemLodgingFeedingDomain> list = mapFeedings.get(itemLocalDateTime);
                    if (list != null && !list.isEmpty()){
                        for (RegisterBookingItemLodgingFeedingDomain item : list){
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

            parameters.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

            for (String localDateTime : dateTimeDinner){
                resultDinnerFoods.append(localDateTime + ", " );
            }
            if ( resultDinnerFoodsV1.length() > 1 ){
                resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
            }

            parameters.put("strDinnerFoods", resultDinnerFoodsV1.toString());

            for (String localDateTime : dateTimeLunch){
                resultLunchFoods.append(localDateTime + ", " );
            }
            if ( resultLunchFoodsV1.length() > 1 ){
                resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
            }

            parameters.put("strLunchFoods", resultLunchFoodsV1.toString());

            for (String localDateTime : dateTimeBoxLunch){
                resultBoxLunchFoods.append(localDateTime + ", " );
            }
            if ( resultBoxLunchFoodsV1.length() > 1 ){
                resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
            }

            parameters.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

            registerBookingItemLodgingDomainsResult.add(registerBookingItemLodgingDomain);
        }

        return jrRegisterBookingLodgingCuponRepository.exportWithUnion(registerBookingItemLodgingDomainsResult, registerBookingItemLodgingDomainsResultOther, ExportFormatEnum.PDF.getName(), parameters, parametersOther);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgingItemCuponPdf(String idRegisterBookingItem, String exportCuponTypePassengerBooking) {
        if (idRegisterBookingItem == null) {
            return null;
        }
        RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain = registerBookingItemLodgingRepo.findById(idRegisterBookingItem);
        if (registerBookingItemLodgingDomain == null) {
            throw new NotFoundException("No register booking item found with id '%s'.".formatted(idRegisterBookingItem));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> parametersOther = new HashMap<>();

        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

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

        parametersOther.put("activeUser", UserResolver.resolveUser());
        parametersOther.put("activeCompany", companyDomain);
        parametersOther.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parametersOther.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResult = new ArrayList<>();
        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomainsResultOther = new ArrayList<>();

        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodging(idRegisterBookingItem);

        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

        if (ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){

            if (registerBookingItemLodgingDomain.getTcLocal() > 0){

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
                        List<RegisterBookingItemLodgingFeedingDomain> list = mapFeedings.get(itemLocalDateTime);
                        if (list != null && !list.isEmpty()){
                            for (RegisterBookingItemLodgingFeedingDomain item : list){
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

                parametersOther.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

                for (String localDateTime : dateTimeDinner){
                    resultDinnerFoods.append(localDateTime + ", " );
                }
                if ( resultDinnerFoodsV1.length() > 1 ){
                    resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                }

                parametersOther.put("strDinnerFoods", resultDinnerFoodsV1.toString());

                for (String localDateTime : dateTimeLunch){
                    resultLunchFoods.append(localDateTime + ", " );
                }
                if ( resultLunchFoodsV1.length() > 1 ){
                    resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                }

                parametersOther.put("strLunchFoods", resultLunchFoodsV1.toString());

                for (String localDateTime : dateTimeBoxLunch){
                    resultBoxLunchFoods.append(localDateTime + ", " );
                }
                if ( resultBoxLunchFoodsV1.length() > 1 ){
                    resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                }

                parametersOther.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

                registerBookingItemLodgingDomainsResultOther.add(registerBookingItemLodgingDomain);
            }

        }else if (ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){

            mapFeedings = new HashMap<>();

            if (
                    registerBookingItemLodgingDomain.getCantPaxAdult() > 0 ||
                            registerBookingItemLodgingDomain.getCantPaxChildren() > 0 ||
                            registerBookingItemLodgingDomain.getTc() > 0 ||
                            registerBookingItemLodgingDomain.getGuide() > 0
            ){
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
                        List<RegisterBookingItemLodgingFeedingDomain> list = mapFeedings.get(itemLocalDateTime);
                        if (list != null && !list.isEmpty()){
                            for (RegisterBookingItemLodgingFeedingDomain item : list){
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

                parameters.put("strBreakfastBuffetFoods", resultBreakFastFoodsV1.toString());

                for (String localDateTime : dateTimeDinner){
                    resultDinnerFoods.append(localDateTime + ", " );
                }
                if ( resultDinnerFoodsV1.length() > 1 ){
                    resultDinnerFoodsV1.delete(resultDinnerFoodsV1.length()-2, resultDinnerFoodsV1.length()-1);
                }

                parameters.put("strDinnerFoods", resultDinnerFoodsV1.toString());

                for (String localDateTime : dateTimeLunch){
                    resultLunchFoods.append(localDateTime + ", " );
                }
                if ( resultLunchFoodsV1.length() > 1 ){
                    resultLunchFoodsV1.delete(resultLunchFoodsV1.length()-2, resultLunchFoodsV1.length()-1);
                }

                parameters.put("strLunchFoods", resultLunchFoodsV1.toString());

                for (String localDateTime : dateTimeBoxLunch){
                    resultBoxLunchFoods.append(localDateTime + ", " );
                }
                if ( resultBoxLunchFoodsV1.length() > 1 ){
                    resultBoxLunchFoodsV1.delete(resultBoxLunchFoodsV1.length()-2, resultBoxLunchFoodsV1.length()-1);
                }

                parameters.put("strBoxLunchFoods", resultBoxLunchFoodsV1.toString());

                registerBookingItemLodgingDomainsResult.add(registerBookingItemLodgingDomain);
            }

        }

        return jrRegisterBookingLodgingCuponRepository.exportWithUnion(registerBookingItemLodgingDomainsResult, registerBookingItemLodgingDomainsResultOther, ExportFormatEnum.PDF.getName(), parameters, parametersOther);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgingPdf(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByUser(userDomain.getId());
        if (employeeUserDomain != null){
            parameters.put("activeEmployee", employeeUserDomain.getEmployee());
        }else{
            throw new BadRequestException(MessagesValidation.VALIDATION_EMPLOYEE_USER_NOT_ASSOCIATE);
        }

        RegisterBookingLodgingDomain registerBookingLodgingDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        List<RegisterBookingLodgingDomain> list = new ArrayList<RegisterBookingLodgingDomain>();
        list.add(registerBookingLodgingDomain);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

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
        Map<String, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

        List<WrapFeedingAdvancedManyDateItem> listWrapFeedingItem = new ArrayList<>();

        String lodgingCategory;
        List<RegisterBookingItemLodgingDomain> registerBookingItemLodgingDomains = registerBookingItemLodgingRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemLodgingDomains != null && !registerBookingItemLodgingDomains.isEmpty()) {
            registerBookingItemLodgingDomains = registerBookingItemLodgingDomains.stream().collect(Collectors.toList());
            RegisterBookingItemLodgingDomain firstElement = registerBookingItemLodgingDomains.getFirst();
            lodgingCategory = firstElement.getCategory();
            parameters.put("paramLodgingCategory", lodgingCategory);
            for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain : registerBookingItemLodgingDomains){
                List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodging(registerBookingItemLodgingDomain.getId());
                if (feedings != null && !feedings.isEmpty()){
                    for (RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain : feedings){
                        if (!mapFeedings.containsKey(registerBookingItemLodgingFeedingDomain.getTypePassengerBooking())){
                            mapFeedings.put(registerBookingItemLodgingFeedingDomain.getTypePassengerBooking(), new ArrayList<>());
                        }
                        mapFeedings.get(registerBookingItemLodgingFeedingDomain.getTypePassengerBooking()).add(registerBookingItemLodgingFeedingDomain);
                    }
                }
            }
        }

        if (!mapFeedings.isEmpty()){
            for (String itemPassengerType : mapFeedings.keySet()){
                WrapFeedingAdvancedManyDateItem wrapFeedingAdvancedManyDateItem = new WrapFeedingAdvancedManyDateItem();
                wrapFeedingAdvancedManyDateItem.setLocalDateTimeBreakfast(new ArrayList<>());
                wrapFeedingAdvancedManyDateItem.setCantBreakfast(0);
                wrapFeedingAdvancedManyDateItem.setLocalDateTimeLunch(new ArrayList<>());
                wrapFeedingAdvancedManyDateItem.setCantLunch(0);
                wrapFeedingAdvancedManyDateItem.setLocalDateTimeDinner(new ArrayList<>());
                wrapFeedingAdvancedManyDateItem.setCantDinner(0);
                wrapFeedingAdvancedManyDateItem.setLocalDateTimeDinner(new ArrayList<>());
                wrapFeedingAdvancedManyDateItem.setCantBoxLunch(0);
                wrapFeedingAdvancedManyDateItem.setTypePassenger(itemPassengerType);
                List<RegisterBookingItemLodgingFeedingDomain> listFood = mapFeedings.get(itemPassengerType);
                if (listFood != null && !listFood.isEmpty()){
                    for (RegisterBookingItemLodgingFeedingDomain item : listFood){
                        LocalDateTime itemLocalDateTime = null;
                        if (item.getDateFeeding() != null && item.getDateTimeFeeding() != null){
                            itemLocalDateTime = LocalDateTime.of(
                                    item.getDateFeeding().getYear(),
                                    item.getDateFeeding().getMonth(),
                                    item.getDateFeeding().getDayOfMonth(),
                                    item.getDateTimeFeeding().getHour(),
                                    item.getDateTimeFeeding().getMinute(),
                                    item.getDateTimeFeeding().getSecond()
                            );
                        }else if (item.getDateFeeding() != null && item.getDateTimeFeeding() == null){
                            itemLocalDateTime = LocalDateTime.of(
                                    item.getDateFeeding().getYear(),
                                    item.getDateFeeding().getMonth(),
                                    item.getDateFeeding().getDayOfMonth(),
                                    0,
                                    0,
                                    0
                            );
                        }

                        if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName())){
                            wrapFeedingAdvancedManyDateItem.setCantBreakfast(wrapFeedingAdvancedManyDateItem.getCantBreakfast() + item.getCant());
                            if (!wrapFeedingAdvancedManyDateItem.getLocalDateTimeBreakfast().contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                wrapFeedingAdvancedManyDateItem.getLocalDateTimeBreakfast().add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                            }
                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.LUNCH.getName())){
                            wrapFeedingAdvancedManyDateItem.setCantLunch(wrapFeedingAdvancedManyDateItem.getCantLunch() + item.getCant());
                            if (!wrapFeedingAdvancedManyDateItem.getLocalDateTimeLunch().contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                wrapFeedingAdvancedManyDateItem.getLocalDateTimeLunch().add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                            }
                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.DINNER.getName())){
                            wrapFeedingAdvancedManyDateItem.setCantDinner(wrapFeedingAdvancedManyDateItem.getCantDinner() + item.getCant());
                            if (!wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner().contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner().add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                            }
                        }else if (item.getTypeFeedingBooking().equals(TypeFeedingBookingEnum.BOX_LUNCH.getName())){
                            wrapFeedingAdvancedManyDateItem.setCantBoxLunch(wrapFeedingAdvancedManyDateItem.getCantBoxLunch() + item.getCant());
                            if (!wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner().contains(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase())){
                                wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner().add(LocalDateTimeUtil.toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(itemLocalDateTime).toUpperCase());
                            }
                        }
                    }
                }
                listWrapFeedingItem.add(wrapFeedingAdvancedManyDateItem);
            }
        }

        StringBuffer resultPaxFoods = new StringBuffer();
        StringBuffer resultTcFoods = new StringBuffer();
        StringBuffer resultTcLocalFoods = new StringBuffer();
        StringBuffer resultGuideFoods = new StringBuffer();

        for (WrapFeedingAdvancedManyDateItem wrapFeedingAdvancedManyDateItem : listWrapFeedingItem){
            if (wrapFeedingAdvancedManyDateItem.getTypePassenger().equals(TypePassengerBookingEnum.PAX.getName())){
                if (wrapFeedingAdvancedManyDateItem.getCantBreakfast() > 0){
                    resultPaxFoods.append(" DES " + wrapFeedingAdvancedManyDateItem.getCantBreakfast() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBreakfast()){
                        resultPaxFoods.append(item + ", ");
                    }
                    if ( resultPaxFoods.length() > 1 ){
                        resultPaxFoods.delete(resultPaxFoods.length()-2, resultPaxFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantLunch() > 0){
                    resultPaxFoods.append(" ALM " + wrapFeedingAdvancedManyDateItem.getCantLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeLunch()){
                        resultPaxFoods.append(item + ", ");
                    }
                    if ( resultPaxFoods.length() > 1 ){
                        resultPaxFoods.delete(resultPaxFoods.length()-2, resultPaxFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantDinner() > 0){
                    resultPaxFoods.append(" CENA " + wrapFeedingAdvancedManyDateItem.getCantDinner() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner()){
                        resultPaxFoods.append(item + ", ");
                    }
                    if ( resultPaxFoods.length() > 1 ){
                        resultPaxFoods.delete(resultPaxFoods.length()-2, resultPaxFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantBoxLunch() > 0){
                    resultPaxFoods.append(" BOX LUNCH " + wrapFeedingAdvancedManyDateItem.getCantBoxLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBoxLunch()){
                        resultPaxFoods.append(item + ", ");
                    }
                    if ( resultPaxFoods.length() > 1 ){
                        resultPaxFoods.delete(resultPaxFoods.length()-2, resultPaxFoods.length()-1);
                    }
                }
            }else if (wrapFeedingAdvancedManyDateItem.getTypePassenger().equals(TypePassengerBookingEnum.TC.getName())){
                if (wrapFeedingAdvancedManyDateItem.getCantBreakfast() > 0){
                    resultTcFoods.append(" DES " + wrapFeedingAdvancedManyDateItem.getCantBreakfast() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBreakfast()){
                        resultTcFoods.append(item + ", ");
                    }
                    if ( resultTcFoods.length() > 1 ){
                        resultTcFoods.delete(resultTcFoods.length()-2, resultTcFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantLunch() > 0){
                    resultTcFoods.append(" ALM " + wrapFeedingAdvancedManyDateItem.getCantLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeLunch()){
                        resultTcFoods.append(item + ", ");
                    }
                    if ( resultTcFoods.length() > 1 ){
                        resultTcFoods.delete(resultTcFoods.length()-2, resultTcFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantDinner() > 0){
                    resultTcFoods.append(" CENA " + wrapFeedingAdvancedManyDateItem.getCantDinner() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner()){
                        resultTcFoods.append(item + ", ");
                    }
                    if ( resultTcFoods.length() > 1 ){
                        resultTcFoods.delete(resultTcFoods.length()-2, resultTcFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantBoxLunch() > 0){
                    resultTcFoods.append(" BOX LUNCH " + wrapFeedingAdvancedManyDateItem.getCantBoxLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBoxLunch()){
                        resultTcFoods.append(item + ", ");
                    }
                    if ( resultTcFoods.length() > 1 ){
                        resultTcFoods.delete(resultTcFoods.length()-2, resultTcFoods.length()-1);
                    }
                }
            }else if (wrapFeedingAdvancedManyDateItem.getTypePassenger().equals(TypePassengerBookingEnum.TC_LOCAL.getName())){
                if (wrapFeedingAdvancedManyDateItem.getCantBreakfast() > 0){
                    resultTcLocalFoods.append(" DES " + wrapFeedingAdvancedManyDateItem.getCantBreakfast() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBreakfast()){
                        resultTcLocalFoods.append(item + ", ");
                    }
                    if ( resultTcLocalFoods.length() > 1 ){
                        resultTcLocalFoods.delete(resultTcLocalFoods.length()-2, resultTcLocalFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantLunch() > 0){
                    resultTcLocalFoods.append(" ALM " + wrapFeedingAdvancedManyDateItem.getCantLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeLunch()){
                        resultTcLocalFoods.append(item + ", ");
                    }
                    if ( resultTcLocalFoods.length() > 1 ){
                        resultTcLocalFoods.delete(resultTcLocalFoods.length()-2, resultTcLocalFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantDinner() > 0){
                    resultTcLocalFoods.append(" CENA " + wrapFeedingAdvancedManyDateItem.getCantDinner() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner()){
                        resultTcLocalFoods.append(item + ", ");
                    }
                    if ( resultTcLocalFoods.length() > 1 ){
                        resultTcLocalFoods.delete(resultTcLocalFoods.length()-2, resultTcLocalFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantBoxLunch() > 0){
                    resultTcLocalFoods.append(" BOX LUNCH " + wrapFeedingAdvancedManyDateItem.getCantBoxLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBoxLunch()){
                        resultTcLocalFoods.append(item + ", ");
                    }
                    if ( resultTcLocalFoods.length() > 1 ){
                        resultTcLocalFoods.delete(resultPaxFoods.length()-2, resultTcLocalFoods.length()-1);
                    }
                }
            }else if (wrapFeedingAdvancedManyDateItem.getTypePassenger().equals(TypePassengerBookingEnum.GUIDE.getName())){
                if (wrapFeedingAdvancedManyDateItem.getCantBreakfast() > 0){
                    resultGuideFoods.append(" DES " + wrapFeedingAdvancedManyDateItem.getCantBreakfast() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBreakfast()){
                        resultGuideFoods.append(item + ", ");
                    }
                    if ( resultGuideFoods.length() > 1 ){
                        resultGuideFoods.delete(resultGuideFoods.length()-2, resultGuideFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantLunch() > 0){
                    resultGuideFoods.append(" ALM " + wrapFeedingAdvancedManyDateItem.getCantLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeLunch()){
                        resultGuideFoods.append(item + ", ");
                    }
                    if ( resultGuideFoods.length() > 1 ){
                        resultGuideFoods.delete(resultGuideFoods.length()-2, resultGuideFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantDinner() > 0){
                    resultGuideFoods.append(" CENA " + wrapFeedingAdvancedManyDateItem.getCantDinner() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeDinner()){
                        resultGuideFoods.append(item + ", ");
                    }
                    if ( resultGuideFoods.length() > 1 ){
                        resultGuideFoods.delete(resultGuideFoods.length()-2, resultGuideFoods.length()-1);
                    }
                }
                if (wrapFeedingAdvancedManyDateItem.getCantBoxLunch() > 0){
                    resultGuideFoods.append(" BOX LUNCH " + wrapFeedingAdvancedManyDateItem.getCantBoxLunch() + ": " );
                    for (String item: wrapFeedingAdvancedManyDateItem.getLocalDateTimeBoxLunch()){
                        resultGuideFoods.append(item + ", ");
                    }
                    if ( resultGuideFoods.length() > 1 ){
                        resultGuideFoods.delete(resultPaxFoods.length()-2, resultGuideFoods.length()-1);
                    }
                }
            }
        }

        registerBookingLodgingDomain.setStrPaxFoods(resultPaxFoods.toString());
        registerBookingLodgingDomain.setStrTcFoods(resultTcFoods.toString());
        registerBookingLodgingDomain.setStrTcLocalFoods(resultTcLocalFoods.toString());
        registerBookingLodgingDomain.setStrGuideFoods(resultGuideFoods.toString());

        Collections.sort(registerBookingItemLodgingDomains);

        Integer cantGuide = 0;

        for (RegisterBookingItemLodgingDomain registerBookingItemLodgingDomain: registerBookingItemLodgingDomains){
            if (registerBookingItemLodgingDomain.getGuide() != null && registerBookingItemLodgingDomain.getGuide() > cantGuide){
                cantGuide = registerBookingItemLodgingDomain.getGuide();
            }
        }

        if ((registerBookingLodgingDomain.getGuide() == null || registerBookingLodgingDomain.getGuide() == 0) && cantGuide > 0){
            registerBookingLodgingDomain.setGuide(cantGuide);
            registerBookingLodgingDomain.setTotalPax(registerBookingLodgingDomain.getTotalPax() + registerBookingLodgingDomain.getGuide());
        }

        Integer sumRoom = 0;
        Integer sumRoomTcGuide = 0;
        Integer sumRoomTcLocalGuide = 0;
        Integer sumRoomGuide = 0;

        List<Integer> listStrRooms = new ArrayList<Integer>();
        listStrRooms.add(1);
        listStrRooms.add(2);
        listStrRooms.add(3);
        listStrRooms.add(4);
        listStrRooms.add(5);
        listStrRooms.add(6);
        listStrRooms.add(7);
        listStrRooms.add(8);

        Integer current = 1;

        for (Integer value: listStrRooms) {
            if ( registerBookingLodgingDomain.getCantSwb() != null && registerBookingLodgingDomain.getCantSwb() > 0 && value.equals(1) ){
                parameters.put("strCantRoom"+current, "SWB");
                parameters.put("valueCantRoom"+current, registerBookingLodgingDomain.getCantSwb());

                sumRoom = sumRoom + registerBookingLodgingDomain.getCantSwb();
                current = current + 1;
            }else if ( registerBookingLodgingDomain.getCantTwin() != null && registerBookingLodgingDomain.getCantTwin() > 0 && value.equals(2) ){
                parameters.put("strCantRoom"+current, "TWIN");
                parameters.put("valueCantRoom"+current, registerBookingLodgingDomain.getCantTwin());

                sumRoom = sumRoom + registerBookingLodgingDomain.getCantTwin();
                current = current + 1;
            }else if ( registerBookingLodgingDomain.getCantMat() != null && registerBookingLodgingDomain.getCantMat() > 0 && value.equals(3) ){
                parameters.put("strCantRoom"+current, "MAT");
                parameters.put("valueCantRoom"+current, registerBookingLodgingDomain.getCantMat());

                sumRoom = sumRoom + registerBookingLodgingDomain.getCantMat();
                current = current + 1;
            }else if ( registerBookingLodgingDomain.getCantTrp() != null && registerBookingLodgingDomain.getCantTrp() > 0 && value.equals(4) ){
                parameters.put("strCantRoom"+current, "TRP");
                parameters.put("valueCantRoom"+current, registerBookingLodgingDomain.getCantTrp());

                sumRoom = sumRoom + registerBookingLodgingDomain.getCantTrp();
                current = current + 1;
            }else if ( registerBookingLodgingDomain.getCantCdp() != null && registerBookingLodgingDomain.getCantCdp() > 0 && value.equals(5) ){
                parameters.put("strCantRoom"+current, "CDP");
                parameters.put("valueCantRoom"+current, registerBookingLodgingDomain.getCantCdp());

                sumRoom = sumRoom + registerBookingLodgingDomain.getCantCdp();
                current = current + 1;
            }else if ( registerBookingLodgingDomain.getCantQtp() != null && registerBookingLodgingDomain.getCantQtp() > 0 && value.equals(6) ){
                parameters.put("strCantRoom"+current, "QTP");
                parameters.put("valueCantRoom"+current, registerBookingLodgingDomain.getCantQtp());

                sumRoom = sumRoom + registerBookingLodgingDomain.getCantQtp();
                current = current + 1;
            }else if ( registerBookingLodgingDomain.getCantSxtp() != null && registerBookingLodgingDomain.getCantSxtp() > 0 && value.equals(7)){
                parameters.put("strCantRoom"+current, "SXTP");
                parameters.put("valueCantRoom"+current, registerBookingLodgingDomain.getCantSxtp());

                sumRoom = sumRoom + registerBookingLodgingDomain.getCantSxtp();
            }
        }

        List<Integer> listStrRoomTcGuide = new ArrayList<Integer>();
        listStrRoomTcGuide.add(1);
        listStrRoomTcGuide.add(2);


        Integer currentTcGuide = 1;

        for (Integer value: listStrRoomTcGuide) {
            if ( registerBookingLodgingDomain.getCantSwbTcGuide() != null && registerBookingLodgingDomain.getCantSwbTcGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomTcGuide"+currentTcGuide, "SWB");
                parameters.put("valueCantRoomTcGuide"+currentTcGuide, registerBookingLodgingDomain.getCantSwbTcGuide());

                sumRoomTcGuide = sumRoomTcGuide + registerBookingLodgingDomain.getCantSwbTcGuide();
                currentTcGuide = currentTcGuide + 1;
            }else if ( registerBookingLodgingDomain.getCantTwinTcGuide() != null && registerBookingLodgingDomain.getCantTwinTcGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomTcGuide"+currentTcGuide, "TWIN");
                parameters.put("valueCantRoomTcGuide"+currentTcGuide, registerBookingLodgingDomain.getCantTwinTcGuide());

                sumRoomTcGuide = sumRoomTcGuide + registerBookingLodgingDomain.getCantTwinTcGuide();
                currentTcGuide = currentTcGuide + 1;
            }
        }

        List<Integer> listStrRoomTcLocalGuide = new ArrayList<Integer>();
        listStrRoomTcLocalGuide.add(1);
        listStrRoomTcLocalGuide.add(2);


        Integer currentTcLocalGuide = 1;

        for (Integer value: listStrRoomTcLocalGuide) {
            if ( registerBookingLodgingDomain.getCantSwbTcLocalGuide() != null && registerBookingLodgingDomain.getCantSwbTcLocalGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomTcLocalGuide"+currentTcLocalGuide, "SWB");
                parameters.put("valueCantRoomTcLocalGuide"+currentTcLocalGuide, registerBookingLodgingDomain.getCantSwbTcLocalGuide());

                sumRoomTcLocalGuide = sumRoomTcLocalGuide + registerBookingLodgingDomain.getCantSwbTcLocalGuide();
                currentTcLocalGuide = currentTcLocalGuide + 1;
            }else if ( registerBookingLodgingDomain.getCantTwinTcLocalGuide() != null && registerBookingLodgingDomain.getCantTwinTcLocalGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomTcLocalGuide"+currentTcLocalGuide, "TWIN");
                parameters.put("valueCantRoomTcLocalGuide"+currentTcLocalGuide, registerBookingLodgingDomain.getCantTwinTcLocalGuide());

                sumRoomTcLocalGuide = sumRoomTcLocalGuide + registerBookingLodgingDomain.getCantTwinTcLocalGuide();
                currentTcLocalGuide = currentTcLocalGuide + 1;
            }
        }

        List<Integer> listStrRoomGuide = new ArrayList<Integer>();
        listStrRoomGuide.add(1);
        listStrRoomGuide.add(2);


        Integer currentGuide = 1;

        for (Integer value: listStrRoomGuide) {
            if ( registerBookingLodgingDomain.getCantSwbGuide() != null && registerBookingLodgingDomain.getCantSwbGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomGuide"+currentGuide, "SWB");
                parameters.put("valueCantRoomGuide"+currentGuide, registerBookingLodgingDomain.getCantSwbGuide());

                sumRoomGuide = sumRoomGuide + registerBookingLodgingDomain.getCantSwbGuide();
                currentGuide = currentGuide + 1;
            }else if ( registerBookingLodgingDomain.getCantTwinGuide() != null && registerBookingLodgingDomain.getCantTwinGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomGuide"+currentGuide, "TWIN");
                parameters.put("valueCantRoomGuide"+currentGuide, registerBookingLodgingDomain.getCantTwinGuide());

                sumRoomGuide = sumRoomGuide + registerBookingLodgingDomain.getCantTwinGuide();
                currentGuide = currentGuide + 1;
            }
        }

        parameters.put("sumRoom", sumRoom);
        parameters.put("sumRoomTcGuide", sumRoomTcGuide);
        parameters.put("sumRoomTcLocalGuide", sumRoomTcLocalGuide);
        parameters.put("sumRoomGuide", sumRoomGuide);

        Map<RegisterBookingRoomDomain, List<PassengerBookingDomain>> mapPassengerRooms = new HashMap<>();
        Map<RegisterBookingRoomDomain, List<PassengerBookingDomain>> mapPassengerTcGuideRooms = new HashMap<>();
        Map<RegisterBookingRoomDomain, List<PassengerBookingDomain>> mapPassengerTcLocalGuideRooms = new HashMap<>();
        Map<RegisterBookingRoomDomain, List<PassengerBookingDomain>> mapPassengerGuideRooms = new HashMap<>();

        List<PassengerBookingDomain> passengerBookingItems = passengerBookingUseCase.searchAllByRegisterBookingLodgingAndRegisterGroupDomain(idRegisterBooking, registerBookingLodgingDomain.getRegisterGroup().getId());
        if (passengerBookingItems != null && !passengerBookingItems.isEmpty()) {
            passengerBookingItems = passengerBookingItems.stream().collect(Collectors.toList());
            for (PassengerBookingDomain itemPassengerBooking: passengerBookingItems) {
                if (itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.SWB.getName()) ||
                            itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.TWIN.getName()) ||
                                itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.MAT.getName()) ||
                                    itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.TRP.getName())){
                    if (!mapPassengerRooms.containsKey(itemPassengerBooking.getRegisterBookingRoom())){
                        mapPassengerRooms.put(itemPassengerBooking.getRegisterBookingRoom(), new ArrayList<>());
                    }
                    mapPassengerRooms.get(itemPassengerBooking.getRegisterBookingRoom()).add(itemPassengerBooking);
                }else if (itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.SWB_TC.getName()) ||
                        itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.TWIN_TC.getName())){
                    if (!mapPassengerTcGuideRooms.containsKey(itemPassengerBooking.getRegisterBookingRoom())){
                        mapPassengerTcGuideRooms.put(itemPassengerBooking.getRegisterBookingRoom(), new ArrayList<>());
                    }
                    mapPassengerTcGuideRooms.get(itemPassengerBooking.getRegisterBookingRoom()).add(itemPassengerBooking);
                }else if (itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.SWB_TC_LOCAL.getName()) ||
                        itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())){
                    if (!mapPassengerTcLocalGuideRooms.containsKey(itemPassengerBooking.getRegisterBookingRoom())){
                        mapPassengerTcLocalGuideRooms.put(itemPassengerBooking.getRegisterBookingRoom(), new ArrayList<>());
                    }
                    mapPassengerTcLocalGuideRooms.get(itemPassengerBooking.getRegisterBookingRoom()).add(itemPassengerBooking);
                }else if (itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.SWB_GUIDE.getName()) ||
                        itemPassengerBooking.getTypeRoomBooking().equals(TypeRoomBookingEnum.TWIN_GUIDE.getName())){
                    if (!mapPassengerGuideRooms.containsKey(itemPassengerBooking.getRegisterBookingRoom())){
                        mapPassengerGuideRooms.put(itemPassengerBooking.getRegisterBookingRoom(), new ArrayList<>());
                    }
                    mapPassengerGuideRooms.get(itemPassengerBooking.getRegisterBookingRoom()).add(itemPassengerBooking);
                }
            }
        }

        StringBuffer resultPassengerRooms = new StringBuffer();
        if (!mapPassengerRooms.isEmpty()){
            for (RegisterBookingRoomDomain keyItem: mapPassengerRooms.keySet()) {
                boolean band = false;
                StringBuffer resultTemp = new StringBuffer();
                resultTemp.append("1 " + TypeRoomBookingEnum.resolveByName(keyItem.getTypeRoomBooking()).getDescription() + ": ");
                List<PassengerBookingDomain> listTemp = mapPassengerRooms.get(keyItem);
                if (listTemp != null && !listTemp.isEmpty()) {
                    band = true;
                    for (PassengerBookingDomain itemPassengerBooking: listTemp) {
                        resultTemp.append(itemPassengerBooking.getPassengerGroup().getTitlePersonDescription() + " " +  itemPassengerBooking.getPassengerGroup().getPerson().getFirstName() + " " + itemPassengerBooking.getPassengerGroup().getPerson().getLastName() + ( (itemPassengerBooking.getPassengerGroup().getDocument() != null && !itemPassengerBooking.getPassengerGroup().getDocument().equals("")) ? " ( PASAPORTE: " + itemPassengerBooking.getPassengerGroup().getDocument() + " )": "" ) + ( (itemPassengerBooking.getPassengerGroup().getObservation() != null && !itemPassengerBooking.getPassengerGroup().getObservation().equals("")) ? " ( " + itemPassengerBooking.getPassengerGroup().getObservation() + " )": "" ) + " + ");
                    }
                    resultTemp.delete(resultTemp.length()-3, resultTemp.length()-1);
                }
                if ( band ){
                    resultPassengerRooms.append(resultTemp.toString());
                    resultPassengerRooms.append("\n");
                }
            }
        }
        parameters.put("strPassengerRooms", resultPassengerRooms.toString());

        StringBuffer resultPassengerTcGuideRooms = new StringBuffer();
        if (!mapPassengerTcGuideRooms.isEmpty()){
            for (RegisterBookingRoomDomain keyItem: mapPassengerTcGuideRooms.keySet()) {
                boolean band = false;
                StringBuffer resultTemp = new StringBuffer();
                resultTemp.append("1 " + TypeRoomBookingEnum.resolveByName(keyItem.getTypeRoomBooking()).getDescription() + ": ");
                List<PassengerBookingDomain> listTemp = mapPassengerTcGuideRooms.get(keyItem);
                if (listTemp != null && !listTemp.isEmpty()) {
                    band = true;
                    for (PassengerBookingDomain itemPassengerBooking: listTemp) {
                        resultTemp.append(itemPassengerBooking.getPassengerGroup().getTitlePersonDescription() + " " +  itemPassengerBooking.getPassengerGroup().getPerson().getFirstName() + " " + itemPassengerBooking.getPassengerGroup().getPerson().getLastName() + ( (itemPassengerBooking.getPassengerGroup().getDocument() != null && !itemPassengerBooking.getPassengerGroup().getDocument().equals("")) ? " ( PASAPORTE: " + itemPassengerBooking.getPassengerGroup().getDocument() + " )": "" ) + ( (itemPassengerBooking.getPassengerGroup().getObservation() != null && !itemPassengerBooking.getPassengerGroup().getObservation().equals("")) ? " ( " + itemPassengerBooking.getPassengerGroup().getObservation() + " )": "" ) + " + ");
                    }
                    resultTemp.delete(resultTemp.length()-3, resultTemp.length()-1);
                }
                if ( band ){
                    resultPassengerTcGuideRooms.append(resultTemp.toString());
                    resultPassengerTcGuideRooms.append("\n");
                }
            }
        }
        parameters.put("strPassengerTcGuideRooms", resultPassengerTcGuideRooms.toString());

        StringBuffer resultPassengerTcLocalGuideRooms = new StringBuffer();
        if (!mapPassengerTcLocalGuideRooms.isEmpty()){
            for (RegisterBookingRoomDomain keyItem: mapPassengerTcLocalGuideRooms.keySet()) {
                boolean band = false;
                StringBuffer resultTemp = new StringBuffer();
                resultTemp.append("1 " + TypeRoomBookingEnum.resolveByName(keyItem.getTypeRoomBooking()).getDescription() + ": ");
                List<PassengerBookingDomain> listTemp = mapPassengerTcLocalGuideRooms.get(keyItem);
                if (listTemp != null && !listTemp.isEmpty()) {
                    band = true;
                    for (PassengerBookingDomain itemPassengerBooking: listTemp) {
                        resultTemp.append(itemPassengerBooking.getPassengerGroup().getTitlePersonDescription() + " " +  itemPassengerBooking.getPassengerGroup().getPerson().getFirstName() + " " + itemPassengerBooking.getPassengerGroup().getPerson().getLastName() + ( (itemPassengerBooking.getPassengerGroup().getDocument() != null && !itemPassengerBooking.getPassengerGroup().getDocument().equals("")) ? " ( PASAPORTE: " + itemPassengerBooking.getPassengerGroup().getDocument() + " )": "" ) + ( (itemPassengerBooking.getPassengerGroup().getObservation() != null && !itemPassengerBooking.getPassengerGroup().getObservation().equals("")) ? " ( " + itemPassengerBooking.getPassengerGroup().getObservation() + " )": "" ) + " + ");
                    }
                    resultTemp.delete(resultTemp.length()-3, resultTemp.length()-1);
                }
                if ( band ){
                    resultPassengerTcLocalGuideRooms.append(resultTemp.toString());
                    resultPassengerTcLocalGuideRooms.append("\n");
                }
            }
        }
        parameters.put("strPassengerTcLocalGuideRooms", resultPassengerTcLocalGuideRooms.toString());

        StringBuffer resultPassengerGuideRooms = new StringBuffer();
        if (!mapPassengerGuideRooms.isEmpty()){
            for (RegisterBookingRoomDomain keyItem: mapPassengerGuideRooms.keySet()) {
                boolean band = false;
                StringBuffer resultTemp = new StringBuffer();
                resultTemp.append("1 " + TypeRoomBookingEnum.resolveByName(keyItem.getTypeRoomBooking()).getDescription() + ": ");
                List<PassengerBookingDomain> listTemp = mapPassengerGuideRooms.get(keyItem);
                if (listTemp != null && !listTemp.isEmpty()) {
                    band = true;
                    for (PassengerBookingDomain itemPassengerBooking: listTemp) {
                        resultTemp.append(itemPassengerBooking.getPassengerGroup().getTitlePersonDescription() + " " +  itemPassengerBooking.getPassengerGroup().getPerson().getFirstName() + " " + itemPassengerBooking.getPassengerGroup().getPerson().getLastName() + ( (itemPassengerBooking.getPassengerGroup().getDocument() != null && !itemPassengerBooking.getPassengerGroup().getDocument().equals("")) ? " ( PASAPORTE: " + itemPassengerBooking.getPassengerGroup().getDocument() + " )": "" ) + ( (itemPassengerBooking.getPassengerGroup().getObservation() != null && !itemPassengerBooking.getPassengerGroup().getObservation().equals("")) ? " ( " + itemPassengerBooking.getPassengerGroup().getObservation() + " )": "" ) + " + ");
                    }
                    resultTemp.delete(resultTemp.length()-3, resultTemp.length()-1);
                }
                if ( band ){
                    resultPassengerGuideRooms.append(resultTemp.toString());
                    resultPassengerGuideRooms.append("\n");
                }
            }
        }
        parameters.put("strPassengerGuideRooms", resultPassengerGuideRooms.toString());

        return jrRegisterBookingLodgingRepository.export(list, registerBookingItemLodgingDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public RegisterBookingLodgingResponse changeState(String id, RegisterBookingChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking lodging found with id '%s'.".formatted(id));
        }
        old.setStateBooking(request.getStateBooking());
        RegisterBookingLodgingDomain editedDomain = repo.edit(old);
        switch (request.getStateBooking()) {
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
        return RegisterBookingLodgingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingLodgingResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking lodging found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
        SupplierLodgingDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierLodgingRepo.findById(request.getSupplier());
        }

        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBooking(old.getRegisterGroup().getId());
        if (listItineraryDate != null && !listItineraryDate.isEmpty()) {
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBooking(itineraryDateDomain.getId(), old.getRegisterGroup().getId());
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()) {
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                }
                for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult
                ) {
                    if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())) {
                        if ( old.getSupplier().getId().equals(itineraryItemDomain.getSupplier().getId())) {
                            ItineraryItemDomain itineraryItemDomain1 = itineraryItemRepo.findById(itineraryItemDomain.getId());
                            itineraryItemDomain1.setSupplier(supplierDomain);
                            itineraryItemRepo.edit(itineraryItemDomain1);
                        }
                    }
                }
            }
        }

        old.setSupplier(supplierDomain);
        RegisterBookingLodgingDomain editedDomain = repo.edit(old);

        return RegisterBookingLodgingResponse.from(editedDomain);
    }

    private void _changeStateDraft(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRequest(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateConfirmed(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateReconfirmed(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateModified(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateAnnulled(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){
        registerBookingLodgingDomain.setActiveRegisterBooking(false);
        repo.edit(registerBookingLodgingDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(registerBookingLodgingDomain.getRegisterGroup().getId(), registerBookingLodgingDomain.getSupplier().getId());
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), registerBookingLodgingDomain.getId());
        }
    }

    private void _changeStateCancelled(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateBooking(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateToReschedule(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRescheduled(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateFinished(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingMp(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingAbc(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingBcd(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedConfirmPeruRail(RegisterBookingLodgingDomain registerBookingLodgingDomain, RegisterBookingChangeStateRequest request){

    }
}
