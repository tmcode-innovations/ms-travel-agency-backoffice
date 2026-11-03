package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinglodger.RegisterBookingLodgerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingLodger;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistCuponNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistRegisterBookingNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodger.RegisterBookingItemLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom.RegisterBookingRoomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodger.RegisterBookingItemLodgerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgerlodging.RegisterBookingItemLodgerLodgingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.WrapFeedingAdvancedManyDateItem;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemlodgingfeeding.WrapFeedingItem;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodger.*;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodger.JRRegisterBookingLodgerCuponRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodger.JRRegisterBookingLodgerRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinglodgerlodging.JRRegisterBookingLodLodgingCuponRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponLodger;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelPassenger;
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
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.enums.ServiceGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerBookingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgerLodgingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemLodgerUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingLodgerUseCase;
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
public class RegisterBookingLodgerUseCaseImpl implements RegisterBookingLodgerUseCase {

    @Autowired
    private RegisterBookingLodgerRepo repo;

    @Autowired
    private RegisterBookingLodgerConverter converter;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private RegisterBookingItemLodgerLodgingRepo registerBookingItemLodgerLodgingRepo;

    @Autowired
    private RegisterBookingItemLodgingFeedingRepo registerBookingItemLodgingFeedingRepo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

    @Autowired
    private SupplierLodgingRepo supplierLodgingRepo;

    @Autowired
    private SupplierLodgerRepo supplierLodgerRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

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
    private RegisterBookingItemLodgerRepo registerBookingItemLodgerRepo;

    @Autowired
    private RegisterBookingItemLodgerUseCase registerBookingItemLodgerUseCase;

    @Autowired
    private RegisterBookingItemLodgerLodgingUseCase registerBookingItemLodgerLodgingUseCase;

    @Autowired
    PersistRegisterBookingNumberCallbackImpl persistRegisterBookingNumberCallback;

    @Autowired
    PersistCuponNumberCallbackImpl persistCuponNumberCallback;

    @Autowired
    private SetFieldDefaultRegisterBookingLodger setFieldDefault;

    @Autowired
    private PassengerBookingUseCase passengerBookingUseCase;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private RegisterBookingRoomRepo registerBookingRoomRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRRegisterBookingLodgerCuponRepository jrRegisterBookingLodgerCuponRepository;

    @Autowired
    private JRRegisterBookingLodLodgingCuponRepository jRRegisterBookingLodLodgingCuponRepository;

    @Autowired
    private JRRegisterBookingLodgerRepository jrRegisterBookingLodgerRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public RegisterBookingLodgerResponse create(RegisterBookingLodgerCreateRequest request) {
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

        SupplierLodgerDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierLodgerRepo.findById(request.getSupplier());
        }

        SupplierOperatorDomain operatorArriveDomain = null;
        if (request.getOperatorArrive() != null && !request.getOperatorArrive().isEmpty()){
            operatorArriveDomain = supplierOperatorRepo.findById(request.getOperatorArrive());
        }

        SupplierOperatorDomain operatorDepartureDomain = null;
        if (request.getOperatorDeparture() != null && !request.getOperatorDeparture().isEmpty()){
            operatorDepartureDomain = supplierOperatorRepo.findById(request.getOperatorDeparture());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            supplierLodgingDomain = supplierLodgingRepo.findById(request.getSupplierLodging());
        }

        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingLodgerDomain newDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        newDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        newDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGE.getName());
        RegisterBookingLodgerDomain createdDomain = repo.create(newDomain);
        return RegisterBookingLodgerResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingLodgerResponse edit(String id, RegisterBookingLodgerUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgerDomain old = repo.findById(id);
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

        SupplierLodgerDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierLodgerRepo.findById(request.getSupplier());
        }

        SupplierOperatorDomain operatorArriveDomain = null;
        if (request.getOperatorArrive() != null && !request.getOperatorArrive().isEmpty()){
            operatorArriveDomain = supplierOperatorRepo.findById(request.getOperatorArrive());
        }

        SupplierOperatorDomain operatorDepartureDomain = null;
        if (request.getOperatorDeparture() != null && !request.getOperatorDeparture().isEmpty()){
            operatorDepartureDomain = supplierOperatorRepo.findById(request.getOperatorDeparture());
        }

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSupplierLodging() != null && !request.getSupplierLodging().isEmpty()){
            supplierLodgingDomain = supplierLodgingRepo.findById(request.getSupplierLodging());
        }

        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingLodgerDomain registerBookingLodgerDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        registerBookingLodgerDomain.setIdentify(old.getIdentify());
        registerBookingLodgerDomain.setStateBooking(old.getStateBooking());
        registerBookingLodgerDomain.setTypeInheritanceBooking(old.getTypeInheritanceBooking());

        registerBookingLodgerDomain.setNoCupon(old.getNoCupon());
        registerBookingLodgerDomain.setDateAperture(old.getDateAperture());
        registerBookingLodgerDomain.setDateInitActivity(old.getDateInitActivity());
        registerBookingLodgerDomain.setDateEndActivity(old.getDateEndActivity());
        registerBookingLodgerDomain.setNumberService(old.getNumberService());

        registerBookingLodgerDomain.setCantPax(old.getCantPax());
        registerBookingLodgerDomain.setCantFree(old.getCantFree());
        registerBookingLodgerDomain.setCantPaxAdult(old.getCantPaxAdult());
        registerBookingLodgerDomain.setCantPaxChildren(old.getCantPaxChildren());
        registerBookingLodgerDomain.setTc(old.getTc());
        registerBookingLodgerDomain.setTcLocal(old.getTcLocal());

        registerBookingLodgerDomain.setInitTour(old.getInitTour());
        registerBookingLodgerDomain.setEndTour(old.getEndTour());

        if (request.getInitHourTour() != null){
            LocalDateTime localDateTimeInit = LocalDateTime.of(
                    old.getInitTour().getYear(),
                    old.getInitTour().getMonth(),
                    old.getInitTour().getDayOfMonth(),
                    request.getInitHourTour().getHour(),
                    request.getInitHourTour().getMinute(),
                    request.getInitHourTour().getSecond()
            );
            registerBookingLodgerDomain.setInitHourTour(localDateTimeInit);
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
            registerBookingLodgerDomain.setEndHourTour(localDateTimeEnd);
        }

        registerBookingLodgerDomain.setNumberIngress(old.getNumberIngress());

        registerBookingLodgerDomain.setCantSwb(old.getCantSwb());
        registerBookingLodgerDomain.setCantSwbTcGuide(old.getCantSwbTcGuide());
        registerBookingLodgerDomain.setCantSwbTcLocalGuide(old.getCantSwbTcLocalGuide());
        registerBookingLodgerDomain.setCantTrp(old.getCantTrp());
        registerBookingLodgerDomain.setCantCdp(old.getCantCdp());
        registerBookingLodgerDomain.setCantQtp(old.getCantQtp());
        registerBookingLodgerDomain.setCantSxtp(old.getCantSxtp());
        registerBookingLodgerDomain.setCantTwin(old.getCantTwin());
        registerBookingLodgerDomain.setCantTwinTcGuide(old.getCantTwinTcGuide());
        registerBookingLodgerDomain.setCantTwinTcLocalGuide(old.getCantTwinTcLocalGuide());
        registerBookingLodgerDomain.setCantMat(old.getCantMat());

        registerBookingLodgerDomain.setDateConfirmation(old.getDateConfirmation());
        registerBookingLodgerDomain.setDateReConfirmation(old.getDateReConfirmation());

        registerBookingLodgerDomain.setDateTimeInitService(old.getDateTimeInitService());

        registerBookingLodgerDomain.setActiveRegisterBooking(old.getActiveRegisterBooking());

        RegisterBookingLodgerDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingLodgerDomain, old));
        return RegisterBookingLodgerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingLodgerResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingLodgerResponse> findAll() {
        return repo.findAll(TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGE.getName()).stream().map(bankDomain -> RegisterBookingLodgerResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgerResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingLodgerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgerSearchResponse searchAll(RegisterBookingLodgerSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingLodgerDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingLodgerSearchResponse bankSearchRespose = new RegisterBookingLodgerSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingLodgerResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgerSearchResponse searchFilterAll(RegisterBookingLodgerSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingLodgerDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingLodgerSearchResponse bankSearchRespose = new RegisterBookingLodgerSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingLodgerResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgerResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingLodgerDomain old = repo.findByNameAndTypeInheritanceBooking(name, TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGE.getName());
        if (old == null) {
            throw new NotFoundException("No register booking lodger found with name '%s'.".formatted(name));
        }
        return RegisterBookingLodgerResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingLodgerResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingLodgerDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGE.getName());
        if (old == null) {
            throw new NotFoundException("No register booking lodger found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingLodgerResponse.from(old);
    }

    @Override
    @Transactional
    public RegisterBookingLodgerResponse generate(String idRegisterGroup, String idSupplier) {
        RegisterBookingLodgerResponse result = null;
        SupplierLodgerDomain supplierDomain = null;
        if (idSupplier != null && !idSupplier.isEmpty()){
            supplierDomain = supplierLodgerRepo.findById(idSupplier);
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        }

        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        List<RegisterBookingItemLodgerDomain> registerBookingItemLodgerDomains = new ArrayList<>();
        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = new ArrayList<>();
        RegisterBookingLodgerDomain registerBookingLodgerDomain = new RegisterBookingLodgerDomain();
        registerBookingLodgerDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_LODGE.getName());
        registerBookingLodgerDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        registerBookingLodgerDomain.setSupplier(supplierDomain);
        registerBookingLodgerDomain.setSupplierType(supplierTypeDomainLodge);
        registerBookingLodgerDomain.setRegisterGroup(registerGroupDomain);
        registerBookingLodgerDomain.setEmployee(registerGroupDomain.getResponsible());
        registerBookingLodgerDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        registerBookingLodgerDomain.setDateAperture(LocalDate.now());
        registerBookingLodgerDomain.setCustomer(registerGroupDomain.getCustomer());
        registerBookingLodgerDomain.setGastosExtrasNoIncluidos(true);
        registerBookingLodgerDomain.setArriveAir(registerGroupDomain.getArriveAir());
        registerBookingLodgerDomain.setDepartureAir(registerGroupDomain.getDepartureAir());
        registerBookingLodgerDomain.setNoCupon(persistCuponNumberCallback.generateNumber());
        registerBookingLodgerDomain.setNumberService(0);
        registerBookingLodgerDomain.setCantPax(0);
        registerBookingLodgerDomain.setCantFree(0);
        registerBookingLodgerDomain.setCantPaxAdult(0);
        registerBookingLodgerDomain.setCantPaxChildren(0);
        registerBookingLodgerDomain.setTc(0);
        registerBookingLodgerDomain.setTcLocal(0);
        registerBookingLodgerDomain.setGuide(0);
        registerBookingLodgerDomain.setTotalPax(0);
        registerBookingLodgerDomain.setCantSwb(0);
        registerBookingLodgerDomain.setCantTrp(0);
        registerBookingLodgerDomain.setCantCdp(0);
        registerBookingLodgerDomain.setCantTwin(0);
        registerBookingLodgerDomain.setCantMat(0);
        registerBookingLodgerDomain.setCantQtp(0);
        registerBookingLodgerDomain.setCantSxtp(0);
        registerBookingLodgerDomain.setCantSwbTcGuide(0);
        registerBookingLodgerDomain.setCantTwinTcGuide(0);
        registerBookingLodgerDomain.setCantSwbTcLocalGuide(0);
        registerBookingLodgerDomain.setCantTwinTcLocalGuide(0);
        registerBookingLodgerDomain.setCantSwbGuide(0);
        registerBookingLodgerDomain.setCantTwinGuide(0);
        registerBookingLodgerDomain.setActiveRegisterBooking(true);
        registerBookingLodgerDomain.setTenant(UserResolver.resolveUser().getTenant());

        Integer numberService = 0;
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

        registerBookingLodgerDomain.setCantPaxAdult(cantAdult);
        registerBookingLodgerDomain.setCantPaxChildren(cantChildren);
        registerBookingLodgerDomain.setCantFree(cantFree);
        registerBookingLodgerDomain.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        registerBookingLodgerDomain.setTc(cantTc);
        registerBookingLodgerDomain.setTcLocal(cantTcLocal);
        registerBookingLodgerDomain.setGuide(cantGuide);
        registerBookingLodgerDomain.setTotalPax(
                (registerBookingLodgerDomain.getCantPax() != null ?  registerBookingLodgerDomain.getCantPax() : 0)
                        + (registerBookingLodgerDomain.getTc() != null ?  registerBookingLodgerDomain.getTc() : 0)
                        + (registerBookingLodgerDomain.getTcLocal() != null ?  registerBookingLodgerDomain.getTcLocal() : 0)
                        + (registerBookingLodgerDomain.getGuide() != null ?  registerBookingLodgerDomain.getGuide() : 0)
        );*/

        if (supplierDomain.getConfirmation() != null && supplierDomain.getConfirmation()){
            registerBookingLodgerDomain.setConfirmation(true);
            registerBookingLodgerDomain.setValueConfirmation(supplierDomain.getValueConfirmation());
            registerBookingLodgerDomain.setDateConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueConfirmation()));
        }else{
            registerBookingLodgerDomain.setConfirmation(false);
            registerBookingLodgerDomain.setValueConfirmation(0);
        }

        if (supplierDomain.getReConfirmation() != null && supplierDomain.getReConfirmation()){
            registerBookingLodgerDomain.setReConfirmation(true);
            registerBookingLodgerDomain.setValueReConfirmation(supplierDomain.getValueReConfirmation());
            registerBookingLodgerDomain.setDateReConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueReConfirmation()));
        }else{
            registerBookingLodgerDomain.setReConfirmation(false);
            registerBookingLodgerDomain.setValueReConfirmation(0);
        }

        Map<Integer, List<ItineraryItemDomain>> mapItemLodgerService = new HashMap<>();
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
                            if (itineraryItemDomain.getServiceGroup() != null){
                                if (itineraryItemDomain.getServiceGroup().equals(ServiceGroupEnum.TRADITIONAL_GROUP.getName())){
                                    if ( !mapItemLodgerService.containsKey( Integer.parseInt(itineraryDateDomain.getItemItinerary()) ) ){
                                        mapItemLodgerService.put(Integer.parseInt(itineraryDateDomain.getItemItinerary()), new ArrayList<ItineraryItemDomain>());
                                        mapItemLodgerService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                                    }else{
                                        mapItemLodgerService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                                    }
                                }else if (itineraryItemDomain.getServiceGroup().equals(ServiceGroupEnum.LODGING.getName())){
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

        registerBookingLodgerDomain.setNumberIngress(mapItemLodgingServiceResult.size());

        RegisterBookingLodgerDomain createdDomainRegisterBookingLodger = repo.create(registerBookingLodgerDomain);

        // Traditional Item

        Integer sequence = 1;
        for (Integer itemLodgingServiceType : mapItemLodgerService.keySet()) {
            List<ItineraryItemDomain> list = new ArrayList<ItineraryItemDomain>();
            list.addAll(mapItemLodgerService.get(itemLodgingServiceType));
            for (ItineraryItemDomain itineraryItemDomain: list) {
                Integer cantAdultItem = itineraryItemDomain.getPaxAdultTotal();
                Integer cantChildrenItem = itineraryItemDomain.getPaxChildrenTotal();
                Integer cantTcItem = itineraryItemDomain.getPaxTcTotal();
                Integer cantTcLocalItem = itineraryItemDomain.getPaxTcLocalTotal();
                Integer cantGuideItem = itineraryItemDomain.getPaxGuideTotal();

                RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain = new RegisterBookingItemLodgerDomain();
                registerBookingItemLodgerDomain.setRegisterBookingLodger(createdDomainRegisterBookingLodger);
                registerBookingItemLodgerDomain.setRegisterGroup(registerGroupDomain);
                registerBookingItemLodgerDomain.setService(itineraryItemDomain.getService());
                registerBookingItemLodgerDomain.setObservation(itineraryItemDomain.getObservation());
                registerBookingItemLodgerDomain.setPvt(itineraryItemDomain.getLodgePvt());
                registerBookingItemLodgerDomain.setSic(itineraryItemDomain.getLodgeSic());
                registerBookingItemLodgerDomain.setDate(itineraryItemDomain.getItineraryDate().getItineraryDate());
                registerBookingItemLodgerDomain.setDateTime(itineraryItemDomain.getItineraryDateTime());
                registerBookingItemLodgerDomain.setIdItineraryItemIdentify(itineraryItemDomain.getId());
                registerBookingItemLodgerDomain.setSequence(sequence);
                registerBookingItemLodgerDomain.setDateService(registerGroupDomain.getInitTour().plusDays(Integer.parseInt(itineraryItemDomain.getItineraryDate().getItemItinerary()) - 1));

                registerBookingItemLodgerDomain.setCantPax(
                        (cantAdultItem != null ?  cantAdultItem : 0)
                                + (cantChildrenItem != null ?  cantChildrenItem : 0)
                );
                registerBookingItemLodgerDomain.setCantPaxAdult(cantAdultItem != null ?  cantAdultItem : 0);
                registerBookingItemLodgerDomain.setCantPaxChildren(cantChildrenItem != null ?  cantChildrenItem : 0);
                registerBookingItemLodgerDomain.setTc(cantTcItem != null ?  cantTcItem : 0);
                registerBookingItemLodgerDomain.setTcLocal(cantTcLocalItem != null ?  cantTcLocalItem : 0);
                registerBookingItemLodgerDomain.setGuide(cantGuideItem != null ?  cantGuideItem : 0);

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

                registerBookingItemLodgerDomain.setTenant(UserResolver.resolveUser().getTenant());

                RegisterBookingItemLodgerDomain createdDomainRegisterBookingItemLodger = registerBookingItemLodgerRepo.create(registerBookingItemLodgerDomain);
                registerBookingItemLodgerDomains.add(createdDomainRegisterBookingItemLodger);
                sequence = sequence + 1;
                numberService = numberService + 1;
            }
        }

        // Lodging Item

        for (Integer itemLodgingServiceType : mapItemLodgingServiceResult.keySet()) {
            List<ItineraryItemDomain> list = new ArrayList<ItineraryItemDomain>();
            list.addAll(mapItemLodgingServiceResult.get(itemLodgingServiceType));
            RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = new RegisterBookingItemLodgerLodgingDomain();
            registerBookingItemLodgerLodgingDomain.setRegisterBookingLodger(createdDomainRegisterBookingLodger);
            registerBookingItemLodgerLodgingDomain.setRegisterGroup(registerGroupDomain);
            List<RegisterBookingItemLodgingFeedingDomain> registerBookingItemLodgingFeedingDomains = new ArrayList<>();
            registerBookingItemLodgerLodgingDomain.setNoCupon(persistCuponNumberCallback.generateNumber());
            registerBookingItemLodgerLodgingDomain.setNoNight(0);
            registerBookingItemLodgerLodgingDomain.setNoNightTcGuide(0);
            registerBookingItemLodgerLodgingDomain.setNoNightTcLocalGuide(0);
            registerBookingItemLodgerLodgingDomain.setNoNightGuide(0);
            registerBookingItemLodgerLodgingDomain.setCantBreakfastBuffet(0);
            registerBookingItemLodgerLodgingDomain.setCantLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantDinner(0);
            registerBookingItemLodgerLodgingDomain.setCantBoxLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantTcGuideBreakfastBuffet(0);
            registerBookingItemLodgerLodgingDomain.setCantTcGuideLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantTcGuideDinner(0);
            registerBookingItemLodgerLodgingDomain.setCantTcGuideBoxLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBreakfastBuffet(0);
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideDinner(0);
            registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBoxLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantGuideBreakfastBuffet(0);
            registerBookingItemLodgerLodgingDomain.setCantGuideLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantGuideDinner(0);
            registerBookingItemLodgerLodgingDomain.setCantGuideBoxLunch(0);
            registerBookingItemLodgerLodgingDomain.setCantAb(0);
            registerBookingItemLodgerLodgingDomain.setCantCb(0);
            registerBookingItemLodgerLodgingDomain.setCantPaxAdult(0);
            registerBookingItemLodgerLodgingDomain.setCantPaxChildren(0);
            registerBookingItemLodgerLodgingDomain.setTc(0);
            registerBookingItemLodgerLodgingDomain.setTcLocal(0);
            registerBookingItemLodgerLodgingDomain.setGuide(0);
            for (ItineraryItemDomain itineraryItemDomain: list) {
                Integer cantAdultItem = itineraryItemDomain.getPaxAdultTotal();
                Integer cantChildrenItem = itineraryItemDomain.getPaxChildrenTotal();
                Integer cantTcItem = itineraryItemDomain.getPaxTcTotal();
                Integer cantTcLocalItem = itineraryItemDomain.getPaxTcLocalTotal();
                Integer cantGuideItem = itineraryItemDomain.getPaxGuideTotal();

                if ( itineraryItemDomain.getPaxAdultTotal() != null ){
                    registerBookingItemLodgerLodgingDomain.setCantPaxAdult(itineraryItemDomain.getPaxAdultTotal());
                }
                if ( itineraryItemDomain.getPaxChildrenTotal() != null ){
                    registerBookingItemLodgerLodgingDomain.setCantPaxChildren(itineraryItemDomain.getPaxChildrenTotal());
                }
                if ( itineraryItemDomain.getPaxTcTotal() != null ){
                    registerBookingItemLodgerLodgingDomain.setTc(itineraryItemDomain.getPaxTcTotal());
                }
                if ( itineraryItemDomain.getPaxTcLocalTotal() != null ){
                    registerBookingItemLodgerLodgingDomain.setTcLocal(itineraryItemDomain.getPaxTcLocalTotal());
                }
                if ( itineraryItemDomain.getPaxGuideTotal() != null ){
                    registerBookingItemLodgerLodgingDomain.setGuide(itineraryItemDomain.getPaxGuideTotal());
                }
                if ( itineraryItemDomain.getLodgeCantNight() != null ){
                    registerBookingItemLodgerLodgingDomain.setNoNight(registerBookingItemLodgerLodgingDomain.getNoNight() + itineraryItemDomain.getLodgeCantNight());
                }
                if ( itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal().intValue() > 0 ){
                    registerBookingItemLodgerLodgingDomain.setNoNightTcGuide(registerBookingItemLodgerLodgingDomain.getNoNightTcGuide() + 1);
                }
                if ( itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal().intValue() > 0 ){
                    registerBookingItemLodgerLodgingDomain.setNoNightTcLocalGuide(registerBookingItemLodgerLodgingDomain.getNoNightTcLocalGuide() + 1);
                }
                if ( itineraryItemDomain.getPaxGuideTotal() != null && itineraryItemDomain.getPaxGuideTotal().intValue() > 0 ){
                    registerBookingItemLodgerLodgingDomain.setNoNightGuide(registerBookingItemLodgerLodgingDomain.getNoNightGuide() + 1);
                }
                if ( itineraryItemDomain.getLodgeCantBreakfast() != null &&
                        itineraryItemDomain.getLodgeCantBreakfast() > 0 &&
                        itineraryItemDomain.getLodgeCantBreakfastItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantBreakfastBuffet(registerBookingItemLodgerLodgingDomain.getCantBreakfastBuffet() + itineraryItemDomain.getLodgeCantBreakfast());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBreakfast());
                    if (itineraryItemDomain.getLodgeCantBreakfastItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBreakfastItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBreakfastDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantLunch() != null &&
                        itineraryItemDomain.getLodgeCantLunch() > 0 &&
                        itineraryItemDomain.getLodgeCantLunchItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantLunch(registerBookingItemLodgerLodgingDomain.getCantLunch() + itineraryItemDomain.getLodgeCantLunch());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantLunch());
                    if (itineraryItemDomain.getLodgeCantLunchItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantLunchItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantLunchDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantDinner() != null &&
                        itineraryItemDomain.getLodgeCantDinner() > 0 &&
                        itineraryItemDomain.getLodgeCantDinnerItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantDinner(registerBookingItemLodgerLodgingDomain.getCantDinner() + itineraryItemDomain.getLodgeCantDinner());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantDinner());
                    if (itineraryItemDomain.getLodgeCantDinnerItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantDinnerItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantDinnerDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantBoxLunch() != null &&
                        itineraryItemDomain.getLodgeCantBoxLunch() > 0 &&
                        itineraryItemDomain.getLodgeCantBoxLunchItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantBoxLunch(registerBookingItemLodgerLodgingDomain.getCantBoxLunch() + itineraryItemDomain.getLodgeCantBoxLunch());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBoxLunch());
                    if (itineraryItemDomain.getLodgeCantBoxLunchItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBoxLunchItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBoxLunchDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.PAX.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }

                if ( itineraryItemDomain.getLodgeCantBreakfastTcGuide() != null &&
                        itineraryItemDomain.getLodgeCantBreakfastTcGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcGuideBreakfastBuffet(registerBookingItemLodgerLodgingDomain.getCantTcGuideBreakfastBuffet() + itineraryItemDomain.getLodgeCantBreakfastTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBreakfastTcGuide());
                    if (itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBreakfastTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBreakfastTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantLunchTcGuide() != null &&
                        itineraryItemDomain.getLodgeCantLunchTcGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcGuideLunch(registerBookingItemLodgerLodgingDomain.getCantTcGuideLunch() + itineraryItemDomain.getLodgeCantLunchTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantLunchTcGuide());
                    if (itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantLunchTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantLunchTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantDinnerTcGuide() != null &&
                        itineraryItemDomain.getLodgeCantDinnerTcGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcGuideDinner(registerBookingItemLodgerLodgingDomain.getCantTcGuideDinner() + itineraryItemDomain.getLodgeCantDinnerTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantDinnerTcGuide());
                    if (itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantDinnerTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantDinnerTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantBoxLunchTcGuide() != null &&
                        itineraryItemDomain.getLodgeCantBoxLunchTcGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcGuideBoxLunch(registerBookingItemLodgerLodgingDomain.getCantTcGuideBoxLunch() + itineraryItemDomain.getLodgeCantBoxLunchTcGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBoxLunchTcGuide());
                    if (itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBoxLunchTcGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBoxLunchTcGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }

                if ( itineraryItemDomain.getLodgeCantBreakfastTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgeCantBreakfastTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBreakfastBuffet(registerBookingItemLodgerLodgingDomain.getCantTcLocalGuideBreakfastBuffet() + itineraryItemDomain.getLodgeCantBreakfastTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBreakfastTcLocalGuide());
                    if (itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBreakfastTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantLunchTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgeCantLunchTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideLunch(registerBookingItemLodgerLodgingDomain.getCantTcLocalGuideLunch() + itineraryItemDomain.getLodgeCantLunchTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantLunchTcLocalGuide());
                    if (itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantLunchTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantLunchTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantDinnerTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgeCantDinnerTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideDinner(registerBookingItemLodgerLodgingDomain.getCantTcLocalGuideDinner() + itineraryItemDomain.getLodgeCantDinnerTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantDinnerTcLocalGuide());
                    if (itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantDinnerTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantDinnerTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuide() != null &&
                        itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantTcLocalGuideBoxLunch(registerBookingItemLodgerLodgingDomain.getCantTcLocalGuideBoxLunch() + itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuide());
                    if (itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBoxLunchTcLocalGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.TC_LOCAL.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }

                if ( itineraryItemDomain.getLodgeCantBreakfastGuide() != null &&
                        itineraryItemDomain.getLodgeCantBreakfastGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantGuideBreakfastBuffet(registerBookingItemLodgerLodgingDomain.getCantGuideBreakfastBuffet() + itineraryItemDomain.getLodgeCantBreakfastGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBreakfastGuide());
                    if (itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBreakfastGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBreakfastGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantLunchGuide() != null &&
                        itineraryItemDomain.getLodgeCantLunchGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantLunchGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantGuideLunch(registerBookingItemLodgerLodgingDomain.getCantGuideLunch() + itineraryItemDomain.getLodgeCantLunchGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantLunchGuide());
                    if (itineraryItemDomain.getLodgeCantLunchGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantLunchGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantLunchGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantDinnerGuide() != null &&
                        itineraryItemDomain.getLodgeCantDinnerGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantGuideDinner(registerBookingItemLodgerLodgingDomain.getCantGuideDinner() + itineraryItemDomain.getLodgeCantDinnerGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantDinnerGuide());
                    if (itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantDinnerGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantDinnerGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCantBoxLunchGuide() != null &&
                        itineraryItemDomain.getLodgeCantBoxLunchGuide() > 0 &&
                        itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate() != null){
                    registerBookingItemLodgerLodgingDomain.setCantGuideBoxLunch(registerBookingItemLodgerLodgingDomain.getCantGuideBoxLunch() + itineraryItemDomain.getLodgeCantBoxLunchGuide());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgeCantBoxLunchGuide());
                    if (itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate() != null){
                        registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getLodgeCantBoxLunchGuideItineraryDate().getItineraryDate());
                    }
                    registerBookingItemLodgingFeedingDomain.setDateTimeFeeding(itineraryItemDomain.getLodgeCantBoxLunchGuideDateTime());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BOX_LUNCH.getName());
                    registerBookingItemLodgingFeedingDomain.setTypePassengerBooking(TypePassengerBookingEnum.GUIDE.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgeCategory() != null ){
                    registerBookingItemLodgerLodgingDomain.setLodgingCategory(itineraryItemDomain.getLodgeCategory());
                }
                if ( itineraryItemDomain.getLodgeCategoryStr() != null ){
                    registerBookingItemLodgerLodgingDomain.setCategory(itineraryItemDomain.getLodgeCategoryStr());
                }
                if ( itineraryItemDomain.getLodgeCantSwb() != null ){
                    if ( itineraryItemDomain.getLodgeCantSwb() > registerBookingLodgerDomain.getCantSwb() ){
                        createdDomainRegisterBookingLodger.setCantSwb(itineraryItemDomain.getLodgeCantSwb());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantTrp() != null ){
                    if ( itineraryItemDomain.getLodgeCantTrp() > registerBookingLodgerDomain.getCantTrp() ){
                        createdDomainRegisterBookingLodger.setCantTrp(itineraryItemDomain.getLodgeCantTrp());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantTwin() != null ){
                    if ( itineraryItemDomain.getLodgeCantTwin() > registerBookingLodgerDomain.getCantTwin() ){
                        createdDomainRegisterBookingLodger.setCantTwin(itineraryItemDomain.getLodgeCantTwin());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantMat() != null ){
                    if ( itineraryItemDomain.getLodgeCantMat() > registerBookingLodgerDomain.getCantMat() ){
                        createdDomainRegisterBookingLodger.setCantMat(itineraryItemDomain.getLodgeCantMat());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantSwbTcGuide() != null ){
                    if ( itineraryItemDomain.getLodgeCantSwbTcGuide() > registerBookingLodgerDomain.getCantSwbTcGuide() ){
                        createdDomainRegisterBookingLodger.setCantSwbTcGuide(itineraryItemDomain.getLodgeCantSwbTcGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantTwinTcGuide() != null ){
                    if ( itineraryItemDomain.getLodgeCantTwinTcGuide() > registerBookingLodgerDomain.getCantTwinTcGuide() ){
                        createdDomainRegisterBookingLodger.setCantTwinTcGuide(itineraryItemDomain.getLodgeCantTwinTcGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantSwbTcLocalGuide() != null ){
                    if ( itineraryItemDomain.getLodgeCantSwbTcLocalGuide() > registerBookingLodgerDomain.getCantSwbTcLocalGuide() ){
                        createdDomainRegisterBookingLodger.setCantSwbTcLocalGuide(itineraryItemDomain.getLodgeCantSwbTcLocalGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantTwinTcLocalGuide() != null ){
                    if ( itineraryItemDomain.getLodgeCantTwinTcLocalGuide() > registerBookingLodgerDomain.getCantTwinTcLocalGuide() ){
                        createdDomainRegisterBookingLodger.setCantTwinTcLocalGuide(itineraryItemDomain.getLodgeCantTwinTcLocalGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantSwbGuide() != null ){
                    if ( itineraryItemDomain.getLodgeCantSwbGuide() > registerBookingLodgerDomain.getCantSwbGuide() ){
                        createdDomainRegisterBookingLodger.setCantSwbGuide(itineraryItemDomain.getLodgeCantSwbGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgeCantTwinGuide() != null ){
                    if ( itineraryItemDomain.getLodgeCantTwinGuide() > registerBookingLodgerDomain.getCantTwinGuide() ){
                        createdDomainRegisterBookingLodger.setCantTwinGuide(itineraryItemDomain.getLodgeCantTwinGuide());
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
            registerBookingItemLodgerLodgingDomain.setDateFrom(registerGroupDomain.getInitTour().plusDays(Integer.parseInt( list.get(0).getItineraryDate().getItemItinerary()) - 1));
            registerBookingItemLodgerLodgingDomain.setDateTo(registerBookingItemLodgerLodgingDomain.getDateFrom().plusDays(registerBookingItemLodgerLodgingDomain.getNoNight()));

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    registerBookingItemLodgerLodgingDomain.getDateFrom().getYear(),
                    registerBookingItemLodgerLodgingDomain.getDateFrom().getMonth(),
                    registerBookingItemLodgerLodgingDomain.getDateFrom().getDayOfMonth(),
                    0,
                    0,
                    0);
            registerBookingItemLodgerLodgingDomain.setDateTimeInitService(localDateTimeInitService);
            registerBookingItemLodgerLodgingDomain.setTenant(UserResolver.resolveUser().getTenant());
            RegisterBookingItemLodgerLodgingDomain createdDomainRegisterBookingItemLodgerLodging = registerBookingItemLodgerLodgingRepo.create(registerBookingItemLodgerLodgingDomain);
            registerBookingItemLodgerLodgingDomains.add(createdDomainRegisterBookingItemLodgerLodging);
            for (RegisterBookingItemLodgingFeedingDomain itemLodgingFeedingDomain: registerBookingItemLodgingFeedingDomains){
                itemLodgingFeedingDomain.setRegisterBookingItemLodgerLodging(createdDomainRegisterBookingItemLodgerLodging);
                itemLodgingFeedingDomain.setRegisterGroup(registerGroupDomain);
                itemLodgingFeedingDomain.setRegisterBookingLodger(createdDomainRegisterBookingLodger);
                itemLodgingFeedingDomain.setTenant(UserResolver.resolveUser().getTenant());
                registerBookingItemLodgingFeedingRepo.create(itemLodgingFeedingDomain);
            }
        }

        this.generateRoomBooking(TypeRoomBookingEnum.SWB.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantSwb());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantTwin());
        this.generateRoomBooking(TypeRoomBookingEnum.MAT.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantMat());
        this.generateRoomBooking(TypeRoomBookingEnum.TRP.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantTrp());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_TC.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantSwbTcGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_TC.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantTwinTcGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_TC_LOCAL.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantSwbTcLocalGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_TC_LOCAL.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantTwinTcLocalGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_GUIDE.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantSwbGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_GUIDE.getName(), registerGroupDomain, createdDomainRegisterBookingLodger, createdDomainRegisterBookingLodger.getCantTwinGuide());

        // Update models

        if ( registerBookingItemLodgerLodgingDomains.size() == 1 ){
            createdDomainRegisterBookingLodger.setInitTour(registerBookingItemLodgerLodgingDomains.get(0).getDateFrom());
            createdDomainRegisterBookingLodger.setEndTour(registerBookingItemLodgerLodgingDomains.get(0).getDateTo());
        }else if ( registerBookingItemLodgerLodgingDomains.size() > 1 ){
            Collections.sort(registerBookingItemLodgerLodgingDomains);
            Object [] arrayRegisterBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.toArray();
            createdDomainRegisterBookingLodger.setInitTour(((RegisterBookingItemLodgerLodgingDomain)arrayRegisterBookingItemLodgerLodgingDomains[0]).getDateFrom());
            createdDomainRegisterBookingLodger.setEndTour(((RegisterBookingItemLodgerLodgingDomain)arrayRegisterBookingItemLodgerLodgingDomains[arrayRegisterBookingItemLodgerLodgingDomains.length-1]).getDateTo());
        }


        if ( registerBookingItemLodgerDomains.size() == 1 ){
            createdDomainRegisterBookingLodger.setDateInitActivity(registerBookingItemLodgerDomains.get(0).getDate());
            createdDomainRegisterBookingLodger.setDateEndActivity(registerBookingItemLodgerDomains.get(0).getDate());

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    registerBookingItemLodgerDomains.get(0).getDate().getYear(),
                    registerBookingItemLodgerDomains.get(0).getDate().getMonth(),
                    registerBookingItemLodgerDomains.get(0).getDate().getDayOfMonth(),
                    registerBookingItemLodgerDomains.get(0).getDateTime().getHour(),
                    registerBookingItemLodgerDomains.get(0).getDateTime().getMinute(),
                    registerBookingItemLodgerDomains.get(0).getDateTime().getSecond());

            createdDomainRegisterBookingLodger.setDateTimeInitService(localDateTimeInitService);
        }else if ( registerBookingItemLodgerDomains.size() > 1 ){
            Collections.sort(registerBookingItemLodgerDomains);
            Object [] arrayRegisterBookingItemLodgerDomains = registerBookingItemLodgerDomains.toArray();
            createdDomainRegisterBookingLodger.setDateInitActivity(((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[0]).getDate());
            createdDomainRegisterBookingLodger.setDateEndActivity(((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[arrayRegisterBookingItemLodgerDomains.length-1]).getDate());

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    ((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[0]).getDate().getYear(),
                    ((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[0]).getDate().getMonth(),
                    ((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[0]).getDate().getDayOfMonth(),
                    ((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[0]).getDateTime().getHour(),
                    ((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[0]).getDateTime().getMinute(),
                    ((RegisterBookingItemLodgerDomain)arrayRegisterBookingItemLodgerDomains[0]).getDateTime().getSecond());

            createdDomainRegisterBookingLodger.setDateTimeInitService(localDateTimeInitService);
        }

        createdDomainRegisterBookingLodger.setNumberService(numberService);

        createdDomainRegisterBookingLodger.setCantPaxAdult(cantAdult);
        createdDomainRegisterBookingLodger.setCantPaxChildren(cantChildren);
        createdDomainRegisterBookingLodger.setCantFree(cantFree);
        createdDomainRegisterBookingLodger.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        createdDomainRegisterBookingLodger.setTc(cantTc);
        createdDomainRegisterBookingLodger.setTcLocal(cantTcLocal);
        createdDomainRegisterBookingLodger.setGuide(cantGuide);

        createdDomainRegisterBookingLodger.setTotalPax(
                (createdDomainRegisterBookingLodger.getCantPax() != null ?  createdDomainRegisterBookingLodger.getCantPax() : 0)
                        + (createdDomainRegisterBookingLodger.getTc() != null ?  createdDomainRegisterBookingLodger.getTc() : 0)
                        + (createdDomainRegisterBookingLodger.getTcLocal() != null ?  createdDomainRegisterBookingLodger.getTcLocal() : 0)
                        + (createdDomainRegisterBookingLodger.getGuide() != null ?  createdDomainRegisterBookingLodger.getGuide() : 0)
        );

        RegisterBookingLodgerDomain editedRegisterBookingLodgerDomain = repo.edit(createdDomainRegisterBookingLodger);
        result = RegisterBookingLodgerResponse.from(editedRegisterBookingLodgerDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(idRegisterGroup, idSupplier);
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), editedRegisterBookingLodgerDomain.getId());
        }

        return result;
    }

    private void generateRoomBooking(String typeRoomBooking, RegisterGroupDomain registerGroup, RegisterBookingLodgerDomain registerBooking, Integer cant){
        if (cant > 0){
            Stream.iterate(0, n -> n + 1)
                    .limit(cant)
                    .forEach(item -> createRoomBooking(typeRoomBooking, registerGroup, registerBooking, cant, item));
        }
    }

    private void createRoomBooking(String typeRoomBooking, RegisterGroupDomain registerGroup, RegisterBookingLodgerDomain registerBooking, Integer total, Integer current){
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
        registerBookingRoomDomain.setRegisterBookingLodger(registerBooking);
        registerBookingRoomDomain.setRegisterGroup(registerGroup);
        registerBookingRoomDomain.setTenant(UserResolver.resolveUser().getTenant());
        registerBookingRoomRepo.create(registerBookingRoomDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgerResponse> findAllRegisterBookingItemLodgerByBooking(String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerUseCase.searchAllByBooking(idBooking);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerResponse addRegisterBookingItemLodger(RegisterBookingItemLodgerCreateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerUseCase.create(request, registerBookingLodgerDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerResponse editRegisterBookingItemLodger(String id, RegisterBookingItemLodgerUpdateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerUseCase.edit(id, request, registerBookingLodgerDomain);
    }

    @Override
    @Transactional
    public void deleteRegisterBookingItemLodger(String id, String idBooking) {
        RegisterBookingLodgerResponse old = findById(idBooking);
        registerBookingItemLodgerUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerResponse findByIdLodgerAndBooking(String id, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerUseCase.searchByBookingAndRegisterBookingItem(idBooking, id);
    }

    @Override
    @Transactional
    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgerDomain == null) {
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
        return passengerBookingUseCase.create(request, passengerGroupDomain, null, null, null, registerBookingLodgerDomain, null, null, registerBookingLodgerDomain.getRegisterGroup(), registerBookingRoomDomain);
    }

    @Override
    @Transactional
    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        RegisterBookingRoomDomain registerBookingRoomDomain = null;
        if (request.getRegisterBookingRoom() != null && !request.getRegisterBookingRoom().isEmpty()){
            registerBookingRoomDomain = registerBookingRoomRepo.findById(request.getRegisterBookingRoom());
        }
        return passengerBookingUseCase.createMultiple(request, null, null, null, registerBookingLodgerDomain, null, null, registerBookingLodgerDomain.getRegisterGroup(), registerBookingRoomDomain);
    }

    @Override
    @Transactional
    public void deletePassengerBooking(String id, String idRegisterBooking) {
        RegisterBookingLodgerResponse old = findById(idRegisterBooking);
        passengerBookingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingLodgerAndRegisterGroup(idRegisterBooking, registerBookingLodgerDomain.getRegisterGroup().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingLodgerAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, registerBookingLodgerDomain.getRegisterGroup().getId(), typePassengerTcBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingLodgingAndTypeRoomBooking(String idRegisterBooking, String typeRoomBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingLodgerAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, registerBookingLodgerDomain.getRegisterGroup().getId(), typeRoomBooking);
    }


    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemLodgerLodgingResponse> findAllRegisterBookingItemLodgerLodgingByBooking(String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerLodgingUseCase.searchAllByBooking(idBooking);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerLodgingResponse addRegisterBookingItemLodgerLodging(RegisterBookingItemLodgerLodgingCreateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerLodgingUseCase.create(request, registerBookingLodgerDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemLodgerLodgingResponse editRegisterBookingItemLodgerLodging(String id, RegisterBookingItemLodgerLodgingUpdateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerLodgingUseCase.edit(id, request, registerBookingLodgerDomain);
    }

    @Override
    @Transactional
    public void deleteRegisterBookingItemLodgerLodging(String id, String idBooking) {
        RegisterBookingLodgerResponse old = findById(idBooking);
        registerBookingItemLodgerLodgingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemLodgerLodgingResponse findByIdLodgerLodgingAndBooking(String id, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemLodgerLodgingUseCase.searchByBookingAndRegisterBookingItem(idBooking, id);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgerCuponPdf(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());
        parameters.put("registerBookingLodger", registerBookingLodgerDomain);
        parameters.put("noCupon", registerBookingLodgerDomain.getStrCuponIsNoInternAndImpress());

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

        List<RegisterBookingItemLodgerDomain> registerBookingItemLodgerDomains = registerBookingItemLodgerRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemLodgerDomains != null && !registerBookingItemLodgerDomains.isEmpty()) {
            registerBookingItemLodgerDomains = registerBookingItemLodgerDomains.stream().collect(Collectors.toList());
            Collections.sort(registerBookingItemLodgerDomains);
        }

        List<RptModelItemCuponLodger> list = new ArrayList<RptModelItemCuponLodger>();

        Integer identify = 1;
        Integer count = 0;

        Integer cantGuide = 0;

        for (RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain: registerBookingItemLodgerDomains) {
            if (registerBookingItemLodgerDomain.getGuide() != null && registerBookingItemLodgerDomain.getGuide() > cantGuide){
                cantGuide = registerBookingItemLodgerDomain.getGuide();
            }
            if ( count == 0){
                RptModelItemCuponLodger rptModelItemCupon = new RptModelItemCuponLodger();
                rptModelItemCupon.setList(new ArrayList<>());
                rptModelItemCupon.setId(identify);
                rptModelItemCupon.getList().add(registerBookingItemLodgerDomain);
                list.add(rptModelItemCupon);
                identify = identify + 1;
            }else if ( count % 8 == 0){
                RptModelItemCuponLodger rptModelItemCupon = new RptModelItemCuponLodger();
                rptModelItemCupon.setList(new ArrayList<>());
                rptModelItemCupon.setId(identify);
                rptModelItemCupon.getList().add(registerBookingItemLodgerDomain);
                list.add(rptModelItemCupon);
                identify = identify + 1;
            }else{
                RptModelItemCuponLodger temp = new RptModelItemCuponLodger();
                temp.setId(identify-1);
                RptModelItemCuponLodger rptModelItemCupon = list.get(list.indexOf(temp));
                rptModelItemCupon.getList().add(registerBookingItemLodgerDomain);
            }
            count = count + 1;
        }
        String strPaxCupon = registerBookingLodgerDomain.getStrPaxCuponNewVersion();
        if ((registerBookingLodgerDomain.getGuide() == null || registerBookingLodgerDomain.getGuide() == 0) && cantGuide > 0){
            strPaxCupon = strPaxCupon + "+ " + cantGuide + " GUIA ";
        }

        parameters.put("strPaxCupon", strPaxCupon);

        List<RegisterBookingItemLodgerDomain> listSubReport = new ArrayList<>();

        return jrRegisterBookingLodgerCuponRepository.export(list, listSubReport, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgerLodgingItemCuponPdf(String idRegisterBookingItem) {
        if (idRegisterBookingItem == null) {
            return null;
        }
        RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = registerBookingItemLodgerLodgingRepo.findById(idRegisterBookingItem);
        if (registerBookingItemLodgerLodgingDomain == null) {
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

        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomainsResult = new ArrayList<>();
        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomainsResultOther = new ArrayList<>();

        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodging(idRegisterBookingItem);

        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

        if (registerBookingItemLodgerLodgingDomain.getTcLocal() > 0){
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
            registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());

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

            registerBookingItemLodgerLodgingDomainsResultOther.add(registerBookingItemLodgerLodgingDomain);
        }

        mapFeedings = new HashMap<>();

        if (
                registerBookingItemLodgerLodgingDomain.getCantPaxAdult() > 0 ||
                        registerBookingItemLodgerLodgingDomain.getCantPaxChildren() > 0 ||
                        registerBookingItemLodgerLodgingDomain.getTc() > 0 ||
                        registerBookingItemLodgerLodgingDomain.getGuide() > 0
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
            registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());

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

            registerBookingItemLodgerLodgingDomainsResult.add(registerBookingItemLodgerLodgingDomain);
        }

        return jRRegisterBookingLodLodgingCuponRepository.exportWithUnion(registerBookingItemLodgerLodgingDomainsResult, registerBookingItemLodgerLodgingDomainsResultOther, ExportFormatEnum.PDF.getName(), parameters, parametersOther);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgerLodgingItemCuponPdf(String idRegisterBookingItem, String exportCuponTypePassengerBooking) {
        if (idRegisterBookingItem == null) {
            return null;
        }
        RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain = registerBookingItemLodgerLodgingRepo.findById(idRegisterBookingItem);
        if (registerBookingItemLodgerLodgingDomain == null) {
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

        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomainsResult = new ArrayList<>();
        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomainsResultOther = new ArrayList<>();

        List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodging(idRegisterBookingItem);

        Map<LocalDateTime, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

        if (ExportCuponTypePassengerBookingEnum.PASSENGER_ONLY_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){

            if (registerBookingItemLodgerLodgingDomain.getTcLocal() > 0){
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
                registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());

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

                registerBookingItemLodgerLodgingDomainsResultOther.add(registerBookingItemLodgerLodgingDomain);
            }

        }else if (ExportCuponTypePassengerBookingEnum.PASSENGER_ALL_WITHOUT_TC_LOCAL.getName().equals(exportCuponTypePassengerBooking)){

            mapFeedings = new HashMap<>();

            if (
                    registerBookingItemLodgerLodgingDomain.getCantPaxAdult() > 0 ||
                            registerBookingItemLodgerLodgingDomain.getCantPaxChildren() > 0 ||
                            registerBookingItemLodgerLodgingDomain.getTc() > 0 ||
                            registerBookingItemLodgerLodgingDomain.getGuide() > 0
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
                registerBookingItemLodgerLodgingDomain.setStrFoods(resultFods.toString());

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

                registerBookingItemLodgerLodgingDomainsResult.add(registerBookingItemLodgerLodgingDomain);
            }

        }

        return jRRegisterBookingLodLodgingCuponRepository.exportWithUnion(registerBookingItemLodgerLodgingDomainsResult, registerBookingItemLodgerLodgingDomainsResultOther, ExportFormatEnum.PDF.getName(), parameters, parametersOther);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingLodgerPdf(String idRegisterBooking) {
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
        RegisterBookingLodgerDomain registerBookingLodgerDomain = repo.findById(idRegisterBooking);
        if (registerBookingLodgerDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        List<RegisterBookingLodgerDomain> list = new ArrayList<RegisterBookingLodgerDomain>();
        list.add(registerBookingLodgerDomain);

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

        List<RegisterBookingItemLodgerDomain> registerBookingItemLodgerDomains = registerBookingItemLodgerRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemLodgerDomains != null && !registerBookingItemLodgerDomains.isEmpty()) {
            registerBookingItemLodgerDomains = registerBookingItemLodgerDomains.stream().collect(Collectors.toList());
            Collections.sort(registerBookingItemLodgerDomains);
        }

        Integer cantGuide = 0;

        for (RegisterBookingItemLodgerDomain registerBookingItemLodgerDomain: registerBookingItemLodgerDomains){
            if (registerBookingItemLodgerDomain.getGuide() != null && registerBookingItemLodgerDomain.getGuide() > cantGuide){
                cantGuide = registerBookingItemLodgerDomain.getGuide();
            }
        }

        if ((registerBookingLodgerDomain.getGuide() == null || registerBookingLodgerDomain.getGuide() == 0) && cantGuide > 0){
            registerBookingLodgerDomain.setGuide(cantGuide);
            registerBookingLodgerDomain.setTotalPax(registerBookingLodgerDomain.getTotalPax() + registerBookingLodgerDomain.getGuide());
        }

        List<PassengerBookingDomain> passengerBookingItems = passengerBookingUseCase.searchAllByRegisterBookingLodgerAndRegisterGroupDomain(idRegisterBooking, registerBookingLodgerDomain.getRegisterGroup().getId());

        List<RptModelPassenger> passengerBookingItemsResult = new ArrayList<RptModelPassenger>();
        if (passengerBookingItems != null && !passengerBookingItems.isEmpty()) {
            int id = 1;
            for (PassengerBookingDomain passengerBookingDomain: passengerBookingItems) {
                if (passengerBookingDomain.getTypePassengerTcBooking() != null){
                    RptModelPassenger rptModelPassenger = new RptModelPassenger();
                    rptModelPassenger.setId(id);
                    rptModelPassenger.setPassenger(passengerBookingDomain);
                    passengerBookingItemsResult.add(rptModelPassenger);
                    id = id + 1;
                }
            }
        }

        Map<String, List<RegisterBookingItemLodgingFeedingDomain>> mapFeedings = new HashMap<>();

        List<WrapFeedingAdvancedManyDateItem> listWrapFeedingItem = new ArrayList<>();

        List<RegisterBookingItemLodgerLodgingDomain> registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemLodgerLodgingDomains != null && !registerBookingItemLodgerLodgingDomains.isEmpty()) {
            registerBookingItemLodgerLodgingDomains = registerBookingItemLodgerLodgingDomains.stream().collect(Collectors.toList());
            for (RegisterBookingItemLodgerLodgingDomain registerBookingItemLodgerLodgingDomain : registerBookingItemLodgerLodgingDomains){
                List<RegisterBookingItemLodgingFeedingDomain> feedings = registerBookingItemLodgingFeedingRepo.searchAllByRegisterBookingItemLodgerLodging(registerBookingItemLodgerLodgingDomain.getId());
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

        registerBookingLodgerDomain.setStrPaxFoods(resultPaxFoods.toString());
        registerBookingLodgerDomain.setStrTcFoods(resultTcFoods.toString());
        registerBookingLodgerDomain.setStrTcLocalFoods(resultTcLocalFoods.toString());
        registerBookingLodgerDomain.setStrGuideFoods(resultGuideFoods.toString());

        Collections.sort(registerBookingItemLodgerLodgingDomains);

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
            if ( registerBookingLodgerDomain.getCantSwb() != null && registerBookingLodgerDomain.getCantSwb() > 0 && value.equals(1) ){
                parameters.put("strCantRoom"+current, "SWB");
                parameters.put("valueCantRoom"+current, registerBookingLodgerDomain.getCantSwb());

                sumRoom = sumRoom + registerBookingLodgerDomain.getCantSwb();
                current = current + 1;
            }else if ( registerBookingLodgerDomain.getCantTwin() != null && registerBookingLodgerDomain.getCantTwin() > 0 && value.equals(2) ){
                parameters.put("strCantRoom"+current, "TWIN");
                parameters.put("valueCantRoom"+current, registerBookingLodgerDomain.getCantTwin());

                sumRoom = sumRoom + registerBookingLodgerDomain.getCantTwin();
                current = current + 1;
            }else if ( registerBookingLodgerDomain.getCantMat() != null && registerBookingLodgerDomain.getCantMat() > 0 && value.equals(3) ){
                parameters.put("strCantRoom"+current, "MAT");
                parameters.put("valueCantRoom"+current, registerBookingLodgerDomain.getCantMat());

                sumRoom = sumRoom + registerBookingLodgerDomain.getCantMat();
                current = current + 1;
            }else if ( registerBookingLodgerDomain.getCantTrp() != null && registerBookingLodgerDomain.getCantTrp() > 0 && value.equals(4) ){
                parameters.put("strCantRoom"+current, "TRP");
                parameters.put("valueCantRoom"+current, registerBookingLodgerDomain.getCantTrp());

                sumRoom = sumRoom + registerBookingLodgerDomain.getCantTrp();
                current = current + 1;
            }else if ( registerBookingLodgerDomain.getCantCdp() != null && registerBookingLodgerDomain.getCantCdp() > 0 && value.equals(5) ){
                parameters.put("strCantRoom"+current, "CDP");
                parameters.put("valueCantRoom"+current, registerBookingLodgerDomain.getCantCdp());

                sumRoom = sumRoom + registerBookingLodgerDomain.getCantCdp();
                current = current + 1;
            }else if ( registerBookingLodgerDomain.getCantQtp() != null && registerBookingLodgerDomain.getCantQtp() > 0 && value.equals(6) ){
                parameters.put("strCantRoom"+current, "QTP");
                parameters.put("valueCantRoom"+current, registerBookingLodgerDomain.getCantQtp());

                sumRoom = sumRoom + registerBookingLodgerDomain.getCantQtp();
                current = current + 1;
            }else if ( registerBookingLodgerDomain.getCantSxtp() != null && registerBookingLodgerDomain.getCantSxtp() > 0 && value.equals(7)){
                parameters.put("strCantRoom"+current, "SXTP");
                parameters.put("valueCantRoom"+current, registerBookingLodgerDomain.getCantSxtp());

                sumRoom = sumRoom + registerBookingLodgerDomain.getCantSxtp();
            }
        }

        List<Integer> listStrRoomTcGuide = new ArrayList<Integer>();
        listStrRoomTcGuide.add(1);
        listStrRoomTcGuide.add(2);


        Integer currentTcGuide = 1;

        for (Integer value: listStrRoomTcGuide) {
            if ( registerBookingLodgerDomain.getCantSwbTcGuide() != null && registerBookingLodgerDomain.getCantSwbTcGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomTcGuide"+currentTcGuide, "SWB");
                parameters.put("valueCantRoomTcGuide"+currentTcGuide, registerBookingLodgerDomain.getCantSwbTcGuide());

                sumRoomTcGuide = sumRoomTcGuide + registerBookingLodgerDomain.getCantSwbTcGuide();
                currentTcGuide = currentTcGuide + 1;
            }else if ( registerBookingLodgerDomain.getCantTwinTcGuide() != null && registerBookingLodgerDomain.getCantTwinTcGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomTcGuide"+currentTcGuide, "TWIN");
                parameters.put("valueCantRoomTcGuide"+currentTcGuide, registerBookingLodgerDomain.getCantTwinTcGuide());

                sumRoomTcGuide = sumRoomTcGuide + registerBookingLodgerDomain.getCantTwinTcGuide();
                currentTcGuide = currentTcGuide + 1;
            }
        }

        List<Integer> listStrRoomTcLocalGuide = new ArrayList<Integer>();
        listStrRoomTcLocalGuide.add(1);
        listStrRoomTcLocalGuide.add(2);

        Integer currentTcLocalGuide = 1;

        for (Integer value: listStrRoomTcLocalGuide) {
            if ( registerBookingLodgerDomain.getCantSwbTcLocalGuide() != null && registerBookingLodgerDomain.getCantSwbTcLocalGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomTcLocalGuide"+currentTcLocalGuide, "SWB");
                parameters.put("valueCantRoomTcLocalGuide"+currentTcLocalGuide, registerBookingLodgerDomain.getCantSwbTcLocalGuide());

                sumRoomTcLocalGuide = sumRoomTcLocalGuide + registerBookingLodgerDomain.getCantSwbTcLocalGuide();
                currentTcLocalGuide = currentTcLocalGuide + 1;
            }else if ( registerBookingLodgerDomain.getCantTwinTcLocalGuide() != null && registerBookingLodgerDomain.getCantTwinTcLocalGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomTcLocalGuide"+currentTcLocalGuide, "TWIN");
                parameters.put("valueCantRoomTcLocalGuide"+currentTcLocalGuide, registerBookingLodgerDomain.getCantTwinTcLocalGuide());

                sumRoomTcLocalGuide = sumRoomTcLocalGuide + registerBookingLodgerDomain.getCantTwinTcLocalGuide();
                currentTcLocalGuide = currentTcLocalGuide + 1;
            }
        }

        List<Integer> listStrRoomGuide = new ArrayList<Integer>();
        listStrRoomGuide.add(1);
        listStrRoomGuide.add(2);


        Integer currentGuide = 1;

        for (Integer value: listStrRoomGuide) {
            if ( registerBookingLodgerDomain.getCantSwbGuide() != null && registerBookingLodgerDomain.getCantSwbGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomGuide"+currentGuide, "SWB");
                parameters.put("valueCantRoomGuide"+currentGuide, registerBookingLodgerDomain.getCantSwbGuide());

                sumRoomGuide = sumRoomGuide + registerBookingLodgerDomain.getCantSwbGuide();
                currentGuide = currentGuide + 1;
            }else if ( registerBookingLodgerDomain.getCantTwinGuide() != null && registerBookingLodgerDomain.getCantTwinGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomGuide"+currentGuide, "TWIN");
                parameters.put("valueCantRoomGuide"+currentGuide, registerBookingLodgerDomain.getCantTwinGuide());

                sumRoomGuide = sumRoomGuide + registerBookingLodgerDomain.getCantTwinGuide();
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

        if (passengerBookingItems != null && !passengerBookingItems.isEmpty()) {
            passengerBookingItems = passengerBookingItems.stream().collect(Collectors.toList());
            for (PassengerBookingDomain itemPassengerBooking: passengerBookingItems) {
                if (itemPassengerBooking.getTypeRoomBooking() != null){
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

        return jrRegisterBookingLodgerRepository.export(list, registerBookingItemLodgerDomains, registerBookingItemLodgerLodgingDomains, passengerBookingItemsResult, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public RegisterBookingLodgerResponse changeState(String id, RegisterBookingChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking lodger found with id '%s'.".formatted(id));
        }
        old.setStateBooking(request.getStateBooking());
        RegisterBookingLodgerDomain editedDomain = repo.edit(old);
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
        return RegisterBookingLodgerResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingLodgerResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingLodgerDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking lodger found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
        SupplierLodgerDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierLodgerRepo.findById(request.getSupplier());
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
                    if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())) {
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
        RegisterBookingLodgerDomain editedDomain = repo.edit(old);

        return RegisterBookingLodgerResponse.from(editedDomain);
    }

    private void _changeStateDraft(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRequest(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateConfirmed(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateReconfirmed(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateModified(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateAnnulled(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){
        registerBookingLodgerDomain.setActiveRegisterBooking(false);
        repo.edit(registerBookingLodgerDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(registerBookingLodgerDomain.getRegisterGroup().getId(), registerBookingLodgerDomain.getSupplier().getId());
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), registerBookingLodgerDomain.getId());
        }
    }

    private void _changeStateCancelled(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateBooking(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateToReschedule(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRescheduled(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateFinished(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingMp(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingAbc(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingBcd(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedConfirmPeruRail(RegisterBookingLodgerDomain registerBookingLodgerDomain, RegisterBookingChangeStateRequest request){

    }
}
