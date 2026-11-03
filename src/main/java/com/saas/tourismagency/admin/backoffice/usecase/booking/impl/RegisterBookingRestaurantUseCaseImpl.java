package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingrestaurant.RegisterBookingRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingRestaurant;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistCuponNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistRegisterBookingNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant.RegisterBookingRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemrestaurant.RegisterBookingItemRestaurantUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.RegisterBookingOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingrestaurant.*;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingrestaurant.JRRegisterBookingRestaurantCuponRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingrestaurant.JRRegisterBookingRestaurantRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponOperator;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponRestaurant;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelPassenger;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeInheritanceBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerBookingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemRestaurantUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingRestaurantUseCase;
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

@Slf4j
@Service
public class RegisterBookingRestaurantUseCaseImpl implements RegisterBookingRestaurantUseCase {

    @Autowired
    private RegisterBookingRestaurantRepo repo;

    @Autowired
    private RegisterBookingRestaurantConverter converter;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

    @Autowired
    private SupplierRestaurantRepo supplierRestaurantRepo;

    @Autowired
    private SupplierLodgingRepo supplierLodgingRepo;

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
    private RegisterBookingItemRestaurantRepo registerBookingItemRestaurantRepo;

    @Autowired
    private RegisterBookingItemRestaurantUseCase registerBookingItemRestaurantUseCase;

    @Autowired
    PersistRegisterBookingNumberCallbackImpl persistRegisterBookingNumberCallback;

    @Autowired
    PersistCuponNumberCallbackImpl persistCuponNumberCallback;

    @Autowired
    private SetFieldDefaultRegisterBookingRestaurant setFieldDefault;

    @Autowired
    private PassengerBookingUseCase passengerBookingUseCase;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRRegisterBookingRestaurantCuponRepository jrRegisterBookingRestaurantCuponRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private JRRegisterBookingRestaurantRepository jrRegisterBookingRestaurantRepository;

    @Override
    @Transactional
    public RegisterBookingRestaurantResponse create(RegisterBookingRestaurantCreateRequest request) {
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

        SupplierRestaurantDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRestaurantRepo.findById(request.getSupplier());
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

        RegisterBookingRestaurantDomain newDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        newDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        newDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_RESTAURANT.getName());
        RegisterBookingRestaurantDomain createdDomain = repo.create(newDomain);
        return RegisterBookingRestaurantResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRestaurantResponse edit(String id, RegisterBookingRestaurantUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingRestaurantDomain old = repo.findById(id);
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

        SupplierRestaurantDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRestaurantRepo.findById(request.getSupplier());
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

        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        registerBookingRestaurantDomain.setIdentify(old.getIdentify());
        registerBookingRestaurantDomain.setStateBooking(old.getStateBooking());

        registerBookingRestaurantDomain.setTypeInheritanceBooking(old.getTypeInheritanceBooking());

        registerBookingRestaurantDomain.setNoCupon(old.getNoCupon());
        registerBookingRestaurantDomain.setDateAperture(old.getDateAperture());
        registerBookingRestaurantDomain.setDateInitActivity(old.getDateInitActivity());
        registerBookingRestaurantDomain.setDateEndActivity(old.getDateEndActivity());
        registerBookingRestaurantDomain.setNumberService(old.getNumberService());
        registerBookingRestaurantDomain.setCantPax(old.getCantPax());
        registerBookingRestaurantDomain.setCantFree(old.getCantFree());
        registerBookingRestaurantDomain.setCantPaxAdult(old.getCantPaxAdult());
        registerBookingRestaurantDomain.setCantPaxChildren(old.getCantPaxChildren());
        registerBookingRestaurantDomain.setTc(old.getTc());
        registerBookingRestaurantDomain.setTcLocal(old.getTcLocal());
        registerBookingRestaurantDomain.setDateConfirmation(old.getDateConfirmation());
        registerBookingRestaurantDomain.setDateReConfirmation(old.getDateReConfirmation());

        registerBookingRestaurantDomain.setDateTimeInitService(old.getDateTimeInitService());

        registerBookingRestaurantDomain.setActiveRegisterBooking(old.getActiveRegisterBooking());

        RegisterBookingRestaurantDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingRestaurantDomain, old));
        return RegisterBookingRestaurantResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingRestaurantResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingRestaurantResponse> findAll() {
        return repo.findAll(TypeInheritanceBookingEnum.REGISTER_BOOKING_RESTAURANT.getName()).stream().map(registerBookingRestaurantDomain -> RegisterBookingRestaurantResponse.from(registerBookingRestaurantDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRestaurantResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingRestaurantDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRestaurantSearchResponse searchAll(RegisterBookingRestaurantSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingRestaurantDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingRestaurantSearchResponse bankSearchRespose = new RegisterBookingRestaurantSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingRestaurantResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRestaurantSearchResponse searchFilterAll(RegisterBookingRestaurantSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingRestaurantDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingRestaurantSearchResponse bankSearchRespose = new RegisterBookingRestaurantSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingRestaurantResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRestaurantResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingRestaurantDomain old = repo.findByNameAndTypeInheritanceBooking(name, TypeInheritanceBookingEnum.REGISTER_BOOKING_RESTAURANT.getName());
        if (old == null) {
            throw new NotFoundException("No register booking restaurant found with name '%s'.".formatted(name));
        }
        return RegisterBookingRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingRestaurantResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingRestaurantDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceBookingEnum.REGISTER_BOOKING_RESTAURANT.getName());
        if (old == null) {
            throw new NotFoundException("No register booking restaurant found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingRestaurantResponse.from(old);
    }

    @Override
    @Transactional
    public RegisterBookingRestaurantResponse generate(String idRegisterGroup, String idSupplier) {
        RegisterBookingRestaurantResponse result = null;
        SupplierRestaurantDomain supplierDomain = null;
        if (idSupplier != null && !idSupplier.isEmpty()){
            supplierDomain = supplierRestaurantRepo.findById(idSupplier);
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        }

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("2", null);
        List<RegisterBookingItemRestaurantDomain> registerBookingItemRestaurantDomains = new ArrayList<>();
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = new RegisterBookingRestaurantDomain();
        registerBookingRestaurantDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_RESTAURANT.getName());
        registerBookingRestaurantDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        registerBookingRestaurantDomain.setSupplier(supplierDomain);
        registerBookingRestaurantDomain.setSupplierType(supplierTypeDomainOperator);
        registerBookingRestaurantDomain.setRegisterGroup(registerGroupDomain);
        registerBookingRestaurantDomain.setEmployee(registerGroupDomain.getResponsible());
        registerBookingRestaurantDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        registerBookingRestaurantDomain.setDateAperture(LocalDate.now());
        registerBookingRestaurantDomain.setCustomer(registerGroupDomain.getCustomer());
        registerBookingRestaurantDomain.setGastosExtrasNoIncluidos(true);
        registerBookingRestaurantDomain.setArriveAir(registerGroupDomain.getArriveAir());
        registerBookingRestaurantDomain.setDepartureAir(registerGroupDomain.getDepartureAir());
        registerBookingRestaurantDomain.setNoCupon(persistCuponNumberCallback.generateNumber());
        registerBookingRestaurantDomain.setNumberService(0);
        registerBookingRestaurantDomain.setCantPax(0);
        registerBookingRestaurantDomain.setCantFree(0);
        registerBookingRestaurantDomain.setCantPaxAdult(0);
        registerBookingRestaurantDomain.setCantPaxChildren(0);
        registerBookingRestaurantDomain.setTc(0);
        registerBookingRestaurantDomain.setTcLocal(0);
        registerBookingRestaurantDomain.setGuide(0);
        registerBookingRestaurantDomain.setTotalPax(0);
        registerBookingRestaurantDomain.setActiveRegisterBooking(true);
        registerBookingRestaurantDomain.setTenant(UserResolver.resolveUser().getTenant());

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

        registerBookingRestaurantDomain.setCantPaxAdult(cantAdult);
        registerBookingRestaurantDomain.setCantPaxChildren(cantChildren);
        registerBookingRestaurantDomain.setCantFree(cantFree);
        registerBookingRestaurantDomain.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        registerBookingRestaurantDomain.setTc(cantTc);
        registerBookingRestaurantDomain.setTcLocal(cantTcLocal);
        registerBookingRestaurantDomain.setGuide(cantGuide);
        registerBookingRestaurantDomain.setTotalPax(
                (registerBookingRestaurantDomain.getCantPax() != null ?  registerBookingRestaurantDomain.getCantPax() : 0)
                        + (registerBookingRestaurantDomain.getTc() != null ?  registerBookingRestaurantDomain.getTc() : 0)
                        + (registerBookingRestaurantDomain.getTcLocal() != null ?  registerBookingRestaurantDomain.getTcLocal() : 0)
                        + (registerBookingRestaurantDomain.getGuide() != null ?  registerBookingRestaurantDomain.getGuide() : 0)
        );*/

        if (supplierDomain.getConfirmation() != null && supplierDomain.getConfirmation()){
            registerBookingRestaurantDomain.setConfirmation(true);
            registerBookingRestaurantDomain.setValueConfirmation(supplierDomain.getValueConfirmation());
            registerBookingRestaurantDomain.setDateConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueConfirmation()));
        }else{
            registerBookingRestaurantDomain.setConfirmation(false);
            registerBookingRestaurantDomain.setValueConfirmation(0);
        }

        if (supplierDomain.getReConfirmation() != null && supplierDomain.getReConfirmation()){
            registerBookingRestaurantDomain.setReConfirmation(true);
            registerBookingRestaurantDomain.setValueReConfirmation(supplierDomain.getValueReConfirmation());
            registerBookingRestaurantDomain.setDateReConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueReConfirmation()));
        }else{
            registerBookingRestaurantDomain.setReConfirmation(false);
            registerBookingRestaurantDomain.setValueReConfirmation(0);
        }
        Map<Integer, List<ItineraryItemDomain>> mapItemRestaurantService = new HashMap<>();
        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByRegisterGroupSimpleDomainCaseGenerateBookingAction(idRegisterGroup);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()) {
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseGenerateBookingAction(itineraryDateDomain.getId(), idRegisterGroup);
                List<ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();
                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                    for (ItineraryItemDomain itineraryItemDomain: listItineraryItemResult){
                        if ( supplierDomain.getId().equals(itineraryItemDomain.getSupplier().getId()) ){
                            if ( !mapItemRestaurantService.containsKey( Integer.parseInt(itineraryDateDomain.getItemItinerary()) ) ){
                                mapItemRestaurantService.put(Integer.parseInt(itineraryDateDomain.getItemItinerary()), new ArrayList<ItineraryItemDomain>());
                                mapItemRestaurantService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }else{
                                mapItemRestaurantService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }
                        }
                    }
                }
            }
        }
        RegisterBookingRestaurantDomain createdDomainRegisterBookingRestaurant = repo.create(registerBookingRestaurantDomain);
        Integer sequence = 1;
        for (Integer itemLodgingServiceType : mapItemRestaurantService.keySet()) {
            List<ItineraryItemDomain> list = new ArrayList<ItineraryItemDomain>();
            list.addAll(mapItemRestaurantService.get(itemLodgingServiceType));
            for (ItineraryItemDomain itineraryItemDomain: list) {
                Integer cantAdultItem = itineraryItemDomain.getPaxAdultTotal();
                Integer cantChildrenItem = itineraryItemDomain.getPaxChildrenTotal();
                Integer cantTcItem = itineraryItemDomain.getPaxTcTotal();
                Integer cantTcLocalItem = itineraryItemDomain.getPaxTcLocalTotal();
                Integer cantGuideItem = itineraryItemDomain.getPaxGuideTotal();

                RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain = new RegisterBookingItemRestaurantDomain();
                registerBookingItemRestaurantDomain.setRegisterBookingRestaurant(createdDomainRegisterBookingRestaurant);
                registerBookingItemRestaurantDomain.setRegisterGroup(registerGroupDomain);
                registerBookingItemRestaurantDomain.setService(itineraryItemDomain.getService());
                registerBookingItemRestaurantDomain.setObservation(itineraryItemDomain.getObservation());
                registerBookingItemRestaurantDomain.setDate(itineraryItemDomain.getItineraryDate().getItineraryDate());
                registerBookingItemRestaurantDomain.setDateTime(itineraryItemDomain.getItineraryDateTime());
                registerBookingItemRestaurantDomain.setIdItineraryItemIdentify(itineraryItemDomain.getId());
                registerBookingItemRestaurantDomain.setSequence(sequence);
                registerBookingItemRestaurantDomain.setDateService(registerGroupDomain.getInitTour().plusDays(Integer.parseInt(itineraryItemDomain.getItineraryDate().getItemItinerary()) - 1));

                registerBookingItemRestaurantDomain.setCantPax(
                        (cantAdultItem != null ?  cantAdultItem : 0)
                                + (cantChildrenItem != null ?  cantChildrenItem : 0)
                );
                registerBookingItemRestaurantDomain.setCantPaxAdult(cantAdultItem != null ?  cantAdultItem : 0);
                registerBookingItemRestaurantDomain.setCantPaxChildren(cantChildrenItem != null ?  cantChildrenItem : 0);
                registerBookingItemRestaurantDomain.setTc(cantTcItem != null ?  cantTcItem : 0);
                registerBookingItemRestaurantDomain.setTcLocal(cantTcLocalItem != null ?  cantTcLocalItem : 0);
                registerBookingItemRestaurantDomain.setGuide(cantGuideItem != null ?  cantGuideItem : 0);

                registerBookingItemRestaurantDomain.setCant(
                        (itineraryItemDomain.getPaxTotal() != null ?  itineraryItemDomain.getPaxTotal() : 0)
                        + (itineraryItemDomain.getPaxChildrenTotal() != null ?  itineraryItemDomain.getPaxChildrenTotal() : 0)
                );

                registerBookingItemRestaurantDomain.setCantGuideTc(
                        (itineraryItemDomain.getPaxTcTotal() != null ?  itineraryItemDomain.getPaxTcTotal() : 0)
                                + (itineraryItemDomain.getPaxTcLocalTotal() != null ?  itineraryItemDomain.getPaxTcLocalTotal() : 0)
                                + (itineraryItemDomain.getPaxGuideTotal() != null ?  itineraryItemDomain.getPaxGuideTotal() : 0)
                );

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

                registerBookingItemRestaurantDomain.setTenant(UserResolver.resolveUser().getTenant());

                RegisterBookingItemRestaurantDomain createdDomainRegisterBookingItemRestaurant = registerBookingItemRestaurantRepo.create(registerBookingItemRestaurantDomain);
                registerBookingItemRestaurantDomains.add(createdDomainRegisterBookingItemRestaurant);
                sequence = sequence + 1;
                numberService = numberService + 1;
            }
        }

        // Update models

        if ( registerBookingItemRestaurantDomains.size() == 1 ){
            createdDomainRegisterBookingRestaurant.setDateInitActivity(registerBookingItemRestaurantDomains.get(0).getDate());
            createdDomainRegisterBookingRestaurant.setDateEndActivity(registerBookingItemRestaurantDomains.get(0).getDate());

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    registerBookingItemRestaurantDomains.get(0).getDate().getYear(),
                    registerBookingItemRestaurantDomains.get(0).getDate().getMonth(),
                    registerBookingItemRestaurantDomains.get(0).getDate().getDayOfMonth(),
                    registerBookingItemRestaurantDomains.get(0).getDateTime().getHour(),
                    registerBookingItemRestaurantDomains.get(0).getDateTime().getMinute(),
                    registerBookingItemRestaurantDomains.get(0).getDateTime().getSecond());

            createdDomainRegisterBookingRestaurant.setDateTimeInitService(localDateTimeInitService);

        }else if ( registerBookingItemRestaurantDomains.size() > 1 ){
            Collections.sort(registerBookingItemRestaurantDomains);
            Object [] arrayRegisterBookingItemRestaurantDomains = registerBookingItemRestaurantDomains.toArray();
            createdDomainRegisterBookingRestaurant.setDateInitActivity(((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[0]).getDate());
            createdDomainRegisterBookingRestaurant.setDateEndActivity(((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[arrayRegisterBookingItemRestaurantDomains.length-1]).getDate());

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    ((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[0]).getDate().getYear(),
                    ((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[0]).getDate().getMonth(),
                    ((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[0]).getDate().getDayOfMonth(),
                    ((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[0]).getDateTime().getHour(),
                    ((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[0]).getDateTime().getMinute(),
                    ((RegisterBookingItemRestaurantDomain)arrayRegisterBookingItemRestaurantDomains[0]).getDateTime().getSecond());

            createdDomainRegisterBookingRestaurant.setDateTimeInitService(localDateTimeInitService);
        }

        createdDomainRegisterBookingRestaurant.setNumberService(numberService);

        createdDomainRegisterBookingRestaurant.setCantPaxAdult(cantAdult);
        createdDomainRegisterBookingRestaurant.setCantPaxChildren(cantChildren);
        createdDomainRegisterBookingRestaurant.setCantFree(cantFree);
        createdDomainRegisterBookingRestaurant.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        createdDomainRegisterBookingRestaurant.setTc(cantTc);
        createdDomainRegisterBookingRestaurant.setTcLocal(cantTcLocal);
        createdDomainRegisterBookingRestaurant.setGuide(cantGuide);

        createdDomainRegisterBookingRestaurant.setTotalPax(
                (createdDomainRegisterBookingRestaurant.getCantPax() != null ?  createdDomainRegisterBookingRestaurant.getCantPax() : 0)
                        + (createdDomainRegisterBookingRestaurant.getTc() != null ?  createdDomainRegisterBookingRestaurant.getTc() : 0)
                        + (createdDomainRegisterBookingRestaurant.getTcLocal() != null ?  createdDomainRegisterBookingRestaurant.getTcLocal() : 0)
                        + (createdDomainRegisterBookingRestaurant.getGuide() != null ?  createdDomainRegisterBookingRestaurant.getGuide() : 0)
        );

        RegisterBookingRestaurantDomain editedRegisterBookingRestaurantDomain = repo.edit(createdDomainRegisterBookingRestaurant);
        result = RegisterBookingRestaurantResponse.from(editedRegisterBookingRestaurantDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(idRegisterGroup, idSupplier);
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), editedRegisterBookingRestaurantDomain.getId());
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemRestaurantResponse> findAllRegisterBookingItemRestaurantByBooking(String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemRestaurantUseCase.searchAllByBooking(idBooking);
    }

    @Override
    @Transactional
    public RegisterBookingItemRestaurantResponse addRegisterBookingItemRestaurant(RegisterBookingItemRestaurantCreateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemRestaurantUseCase.create(request, registerBookingRestaurantDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemRestaurantResponse editRegisterBookingItemRestaurant(String id, RegisterBookingItemRestaurantUpdateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemRestaurantUseCase.edit(id, request, registerBookingRestaurantDomain);
    }

    @Override
    @Transactional
    public void deleteRegisterBookingItemRestaurant(String id, String idBooking) {
        RegisterBookingRestaurantResponse old = findById(idBooking);
        registerBookingItemRestaurantUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemRestaurantResponse findByIdAndBooking(String id, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemRestaurantUseCase.searchByBookingAndRegisterBookingItem(idBooking, id);
    }

    @Override
    @Transactional
    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idRegisterBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        PassengerGroupDomain passengerGroupDomain = null;
        if (request.getPassengerGroup() != null && !request.getPassengerGroup().isEmpty()){
            passengerGroupDomain = passengerGroupRepo.findById(request.getPassengerGroup());
        }
        return passengerBookingUseCase.create(request, passengerGroupDomain, null, registerBookingRestaurantDomain, null, null, null, null, registerBookingRestaurantDomain.getRegisterGroup(), null);
    }

    @Override
    @Transactional
    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idRegisterBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.createMultiple(request, null, registerBookingRestaurantDomain, null, null, null, null, registerBookingRestaurantDomain.getRegisterGroup(), null);
    }

    @Override
    @Transactional
    public void deletePassengerBooking(String id, String idRegisterBooking) {
        RegisterBookingRestaurantResponse old = findById(idRegisterBooking);
        passengerBookingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idRegisterBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingRestaurantAndRegisterGroup(idRegisterBooking, registerBookingRestaurantDomain.getRegisterGroup().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idRegisterBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingRestaurantAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, registerBookingRestaurantDomain.getRegisterGroup().getId(), typePassengerTcBooking);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingRestaurantCuponPdf(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idRegisterBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        // Map<String, Object> parametersOther = new HashMap<>();

        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());
        parameters.put("registerBookingRestaurant", registerBookingRestaurantDomain);
        parameters.put("noCupon", registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress());

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

        /*parametersOther.put("activeUser", UserResolver.resolveUser());
        parametersOther.put("activeCompany", companyDomain);
        parametersOther.put("dateNow", LocalDate.now());
        parametersOther.put("registerBookingRestaurant", registerBookingRestaurantDomain);
        parametersOther.put("noCupon", registerBookingRestaurantDomain.getStrCuponIsNoInternAndImpress());

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
        }*/

        List<RegisterBookingItemRestaurantDomain> registerBookingItemRestaurantDomains = registerBookingItemRestaurantRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemRestaurantDomains != null && !registerBookingItemRestaurantDomains.isEmpty()) {
            registerBookingItemRestaurantDomains = registerBookingItemRestaurantDomains.stream().collect(Collectors.toList());
            Collections.sort(registerBookingItemRestaurantDomains);
        }


        List<RptModelItemCuponRestaurant> list = new ArrayList<>();
        // List<RptModelItemCuponRestaurant> listOther = new ArrayList<>();

        Integer identify = 1;
        // Integer identifyOther = 1;
        Integer count = 0;
        // Integer countOther = 0;

        Integer cantGuide = 0;

        for (RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain: registerBookingItemRestaurantDomains) {
            /*if (registerBookingItemRestaurantDomain.getTcLocal() > 0){
                if ( countOther == 0){
                    RptModelItemCuponRestaurant rptModelItemCupon = new RptModelItemCuponRestaurant();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identifyOther);
                    rptModelItemCupon.getList().add(registerBookingItemRestaurantDomain);
                    listOther.add(rptModelItemCupon);
                    identifyOther = identifyOther + 1;
                }else if ( countOther % 8 == 0){
                    RptModelItemCuponRestaurant rptModelItemCupon = new RptModelItemCuponRestaurant();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identifyOther);
                    rptModelItemCupon.getList().add(registerBookingItemRestaurantDomain);
                    listOther.add(rptModelItemCupon);
                    identifyOther = identifyOther + 1;
                }else{
                    RptModelItemCuponRestaurant temp = new RptModelItemCuponRestaurant();
                    temp.setId(identifyOther-1);
                    RptModelItemCuponRestaurant rptModelItemCupon = listOther.get(listOther.indexOf(temp));
                    rptModelItemCupon.getList().add(registerBookingItemRestaurantDomain);
                }
                countOther = countOther + 1;
            }*/
            if (
                    registerBookingItemRestaurantDomain.getCantPaxAdult() > 0 ||
                            registerBookingItemRestaurantDomain.getCantPaxChildren() > 0 ||
                            registerBookingItemRestaurantDomain.getTc() > 0 ||
                            registerBookingItemRestaurantDomain.getGuide() > 0 ||
                            registerBookingItemRestaurantDomain.getTcLocal() > 0
            ){
                if (registerBookingItemRestaurantDomain.getGuide() != null && registerBookingItemRestaurantDomain.getGuide() > cantGuide){
                    cantGuide = registerBookingItemRestaurantDomain.getGuide();
                }
                if ( count == 0){
                    RptModelItemCuponRestaurant rptModelItemCupon = new RptModelItemCuponRestaurant();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identify);
                    rptModelItemCupon.getList().add(registerBookingItemRestaurantDomain);
                    list.add(rptModelItemCupon);
                    identify = identify + 1;
                }else if ( count % 8 == 0){
                    RptModelItemCuponRestaurant rptModelItemCupon = new RptModelItemCuponRestaurant();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identify);
                    rptModelItemCupon.getList().add(registerBookingItemRestaurantDomain);
                    list.add(rptModelItemCupon);
                    identify = identify + 1;
                }else{
                    RptModelItemCuponRestaurant temp = new RptModelItemCuponRestaurant();
                    temp.setId(identify-1);
                    RptModelItemCuponRestaurant rptModelItemCupon = list.get(list.indexOf(temp));
                    rptModelItemCupon.getList().add(registerBookingItemRestaurantDomain);
                }
                count = count + 1;
            }
        }

        String strPaxCupon = registerBookingRestaurantDomain.getStrPaxCuponNewVersion();
        if ((registerBookingRestaurantDomain.getGuide() == null || registerBookingRestaurantDomain.getGuide() == 0) && cantGuide > 0){
            strPaxCupon = strPaxCupon + "+ " + cantGuide + " GUIA ";
        }
        parameters.put("strPaxCupon", strPaxCupon);

        /*String strPaxCuponTcLocal = registerBookingRestaurantDomain.getStrPaxCuponNewVersionOnlyTcLocal();
        parametersOther.put("strPaxCupon", strPaxCuponTcLocal);
*/
        List<RegisterBookingItemRestaurantDomain> listSubReport = new ArrayList<>();

        return jrRegisterBookingRestaurantCuponRepository.export(list, listSubReport, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingRestaurantPdf(String idRegisterBooking) {
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
        RegisterBookingRestaurantDomain registerBookingRestaurantDomain = repo.findById(idRegisterBooking);
        if (registerBookingRestaurantDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        List<RegisterBookingRestaurantDomain> list = new ArrayList<RegisterBookingRestaurantDomain>();
        list.add(registerBookingRestaurantDomain);

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

        List<RegisterBookingItemRestaurantDomain> registerBookingItemRestaurantDomains = registerBookingItemRestaurantRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemRestaurantDomains != null && !registerBookingItemRestaurantDomains.isEmpty()) {
            registerBookingItemRestaurantDomains = registerBookingItemRestaurantDomains.stream().collect(Collectors.toList());
            Collections.sort(registerBookingItemRestaurantDomains);
        }

        Integer cantGuide = 0;

        for (RegisterBookingItemRestaurantDomain registerBookingItemRestaurantDomain: registerBookingItemRestaurantDomains){
            if (registerBookingItemRestaurantDomain.getGuide() != null && registerBookingItemRestaurantDomain.getGuide() > cantGuide){
                cantGuide = registerBookingItemRestaurantDomain.getGuide();
            }
        }

        if ((registerBookingRestaurantDomain.getGuide() == null || registerBookingRestaurantDomain.getGuide() == 0) && cantGuide > 0){
            registerBookingRestaurantDomain.setGuide(cantGuide);
            registerBookingRestaurantDomain.setTotalPax(registerBookingRestaurantDomain.getTotalPax() + registerBookingRestaurantDomain.getGuide());
        }

        List<PassengerBookingDomain> passengerBookingItems = passengerBookingUseCase.searchAllByRegisterBookingRestaurantAndRegisterGroupDomain(idRegisterBooking, registerBookingRestaurantDomain.getRegisterGroup().getId());
        List<RptModelPassenger> passengerBookingItemsResult = new ArrayList<RptModelPassenger>();
        if (passengerBookingItems != null && !passengerBookingItems.isEmpty()) {
            int id = 1;
            for (PassengerBookingDomain passengerBookingDomain: passengerBookingItems) {
                RptModelPassenger rptModelPassenger = new RptModelPassenger();
                rptModelPassenger.setId(id);
                rptModelPassenger.setPassenger(passengerBookingDomain);
                passengerBookingItemsResult.add(rptModelPassenger);
                id = id + 1;
            }
        }
        return jrRegisterBookingRestaurantRepository.export(list, registerBookingItemRestaurantDomains, passengerBookingItemsResult, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public RegisterBookingRestaurantResponse changeState(String id, RegisterBookingChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingRestaurantDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking restaurant found with id '%s'.".formatted(id));
        }
        old.setStateBooking(request.getStateBooking());
        RegisterBookingRestaurantDomain editedDomain = repo.edit(old);
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
        return RegisterBookingRestaurantResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingRestaurantResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingRestaurantDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking restaurant found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
        SupplierRestaurantDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierRestaurantRepo.findById(request.getSupplier());
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
                    if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())) {
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
        RegisterBookingRestaurantDomain editedDomain = repo.edit(old);

        return RegisterBookingRestaurantResponse.from(editedDomain);
    }

    private void _changeStateDraft(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRequest(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateConfirmed(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateReconfirmed(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateModified(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateAnnulled(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){
        registerBookingRestaurantDomain.setActiveRegisterBooking(false);
        repo.edit(registerBookingRestaurantDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(registerBookingRestaurantDomain.getRegisterGroup().getId(), registerBookingRestaurantDomain.getSupplier().getId());
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), registerBookingRestaurantDomain.getId());
        }
    }

    private void _changeStateCancelled(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateBooking(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateToReschedule(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRescheduled(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateFinished(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingMp(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingAbc(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingBcd(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedConfirmPeruRail(RegisterBookingRestaurantDomain registerBookingRestaurantDomain, RegisterBookingChangeStateRequest request){

    }
}
