package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookinginstitution.RegisterBookingInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingInstitution;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistCuponNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistRegisterBookingNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinginstitution.RegisterBookingInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinginstitution.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingiteminstitution.RegisterBookingItemInstitutionUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinginstitution.JRRegisterBookingInstitutionCuponRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookinginstitution.JRRegisterBookingInstitutionRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponInstitution;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponOperator;
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
import com.saas.tourismagency.admin.backoffice.usecase.booking.*;
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
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegisterBookingInstitutionUseCaseImpl implements RegisterBookingInstitutionUseCase {

    @Autowired
    private RegisterBookingInstitutionRepo repo;

    @Autowired
    private RegisterBookingInstitutionConverter converter;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierInstitutionRepo supplierInstitutionRepo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

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
    private RegisterBookingItemInstitutionRepo registerBookingItemInstitutionRepo;

    @Autowired
    private RegisterBookingItemInstitutionUseCase registerBookingItemInstitutionUseCase;

    @Autowired
    PersistRegisterBookingNumberCallbackImpl persistRegisterBookingNumberCallback;

    @Autowired
    PersistCuponNumberCallbackImpl persistCuponNumberCallback;

    @Autowired
    private SetFieldDefaultRegisterBookingInstitution setFieldDefault;

    @Autowired
    private PassengerBookingUseCase passengerBookingUseCase;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRRegisterBookingInstitutionCuponRepository jrRegisterBookingInstitutionCuponRepository;

    @Autowired
    private JRRegisterBookingInstitutionRepository jrRegisterBookingInstitutionRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public RegisterBookingInstitutionResponse create(RegisterBookingInstitutionCreateRequest request) {
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

        SupplierOperatorDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierOperatorRepo.findById(request.getSupplier());
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

        RegisterBookingInstitutionDomain newDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        newDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        newDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_INSTITUTION.getName());
        RegisterBookingInstitutionDomain createdDomain = repo.create(newDomain);
        return RegisterBookingInstitutionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingInstitutionResponse edit(String id, RegisterBookingInstitutionUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingInstitutionDomain old = repo.findById(id);
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

        SupplierOperatorDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierOperatorRepo.findById(request.getSupplier());
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

        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        registerBookingInstitutionDomain.setIdentify(old.getIdentify());
        registerBookingInstitutionDomain.setStateBooking(old.getStateBooking());
        registerBookingInstitutionDomain.setTypeInheritanceBooking(old.getTypeInheritanceBooking());

        registerBookingInstitutionDomain.setNoCupon(old.getNoCupon());
        registerBookingInstitutionDomain.setDateAperture(old.getDateAperture());
        registerBookingInstitutionDomain.setDateInitActivity(old.getDateInitActivity());
        registerBookingInstitutionDomain.setDateEndActivity(old.getDateEndActivity());
        registerBookingInstitutionDomain.setNumberService(old.getNumberService());

        registerBookingInstitutionDomain.setCantPax(old.getCantPax());
        registerBookingInstitutionDomain.setCantFree(old.getCantFree());
        registerBookingInstitutionDomain.setCantPaxAdult(old.getCantPaxAdult());
        registerBookingInstitutionDomain.setCantPaxChildren(old.getCantPaxChildren());
        registerBookingInstitutionDomain.setTc(old.getTc());
        registerBookingInstitutionDomain.setTcLocal(old.getTcLocal());

        registerBookingInstitutionDomain.setDateConfirmation(old.getDateConfirmation());
        registerBookingInstitutionDomain.setDateReConfirmation(old.getDateReConfirmation());

        registerBookingInstitutionDomain.setDateTimeInitService(old.getDateTimeInitService());

        registerBookingInstitutionDomain.setActiveRegisterBooking(old.getActiveRegisterBooking());

        RegisterBookingInstitutionDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingInstitutionDomain, old));
        return RegisterBookingInstitutionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingInstitutionResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingInstitutionResponse> findAll() {
        return repo.findAll(TypeInheritanceBookingEnum.REGISTER_BOOKING_INSTITUTION.getName()).stream().map(registerBookingInstitutionDomain -> RegisterBookingInstitutionResponse.from(registerBookingInstitutionDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingInstitutionResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingInstitutionSearchResponse searchAll(RegisterBookingInstitutionSearchRequest searchRequest) {
       PageDTO<RegisterBookingInstitutionDomain> pageDTO = repo.searchAll(searchRequest);
       RegisterBookingInstitutionSearchResponse searchRespose = new RegisterBookingInstitutionSearchResponse();
       searchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingInstitutionResponse.from(bankDomain)).toList());
       searchRespose.setTotalElements(pageDTO.getTotalElements());
       searchRespose.setTotalPages(pageDTO.getTotalPages());
       return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingInstitutionSearchResponse searchFilterAll(RegisterBookingInstitutionSearchFilterRequest searchFilterRequest) {
        PageDTO<RegisterBookingInstitutionDomain> pageDTO = repo.searchAllFilter(searchFilterRequest);
        RegisterBookingInstitutionSearchResponse searchRespose = new RegisterBookingInstitutionSearchResponse();
        searchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingInstitutionResponse.from(bankDomain)).toList());
        searchRespose.setTotalElements(pageDTO.getTotalElements());
        searchRespose.setTotalPages(pageDTO.getTotalPages());
        return searchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingInstitutionResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingInstitutionDomain old = repo.findByNameAndTypeInheritanceBooking(name, TypeInheritanceBookingEnum.REGISTER_BOOKING_INSTITUTION.getName());
        if (old == null) {
            throw new NotFoundException("No register booking operator found with name '%s'.".formatted(name));
        }
        return RegisterBookingInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingInstitutionResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingInstitutionDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceBookingEnum.REGISTER_BOOKING_INSTITUTION.getName());
        if (old == null) {
            throw new NotFoundException("No register booking operator found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingInstitutionResponse.from(old);
    }

    @Override
    @Transactional
    public RegisterBookingInstitutionResponse generate(String idRegisterGroup, String idSupplier) {
        RegisterBookingInstitutionResponse result = null;
        SupplierInstitutionDomain supplierDomain = null;
        if (idSupplier != null && !idSupplier.isEmpty()){
            supplierDomain = supplierInstitutionRepo.findById(idSupplier);
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        }

        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);

        List<RegisterBookingItemInstitutionDomain> registerBookingItemInstitutionDomains = new ArrayList<>();
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = new RegisterBookingInstitutionDomain();
        registerBookingInstitutionDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_INSTITUTION.getName());
        registerBookingInstitutionDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        registerBookingInstitutionDomain.setSupplier(supplierDomain);
        registerBookingInstitutionDomain.setSupplierType(supplierTypeDomainInstitution);
        registerBookingInstitutionDomain.setRegisterGroup(registerGroupDomain);
        registerBookingInstitutionDomain.setEmployee(registerGroupDomain.getResponsible());
        registerBookingInstitutionDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        registerBookingInstitutionDomain.setDateAperture(LocalDate.now());
        registerBookingInstitutionDomain.setCustomer(registerGroupDomain.getCustomer());
        registerBookingInstitutionDomain.setGastosExtrasNoIncluidos(true);
        registerBookingInstitutionDomain.setArriveAir(registerGroupDomain.getArriveAir());
        registerBookingInstitutionDomain.setDepartureAir(registerGroupDomain.getDepartureAir());
        registerBookingInstitutionDomain.setNoCupon(persistCuponNumberCallback.generateNumber());
        registerBookingInstitutionDomain.setNumberService(0);
        registerBookingInstitutionDomain.setCantPax(0);
        registerBookingInstitutionDomain.setCantFree(0);
        registerBookingInstitutionDomain.setCantPaxAdult(0);
        registerBookingInstitutionDomain.setCantPaxChildren(0);
        registerBookingInstitutionDomain.setTc(0);
        registerBookingInstitutionDomain.setTcLocal(0);
        registerBookingInstitutionDomain.setGuide(0);
        registerBookingInstitutionDomain.setTotalPax(0);
        registerBookingInstitutionDomain.setActiveRegisterBooking(true);
        registerBookingInstitutionDomain.setTenant(UserResolver.resolveUser().getTenant());

        Integer numberService = 0;

        Integer cantAdult = 0;
        Integer cantChildren = 0;
        Integer cantTc = 0;
        Integer cantTcLocal = 0;
        Integer cantGuide = 0;
        Integer cantFree = 0;

        cantAdult = registerGroupDomain.getCantPaxAdult();
        cantChildren = registerGroupDomain.getCantPaxChildren();
        cantFree = registerGroupDomain.getCantFree();
        cantTc = registerGroupDomain.getTC();
        cantTcLocal = registerGroupDomain.getTCLocal();
        cantGuide = registerGroupDomain.getGuide();

        registerBookingInstitutionDomain.setCantPaxAdult(cantAdult);
        registerBookingInstitutionDomain.setCantPaxChildren(cantChildren);
        registerBookingInstitutionDomain.setCantFree(cantFree);
        registerBookingInstitutionDomain.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        registerBookingInstitutionDomain.setTc(cantTc);
        registerBookingInstitutionDomain.setTcLocal(cantTcLocal);
        registerBookingInstitutionDomain.setGuide(cantGuide);
        registerBookingInstitutionDomain.setTotalPax(
                (registerBookingInstitutionDomain.getCantPax() != null ?  registerBookingInstitutionDomain.getCantPax() : 0)
                        + (registerBookingInstitutionDomain.getTc() != null ?  registerBookingInstitutionDomain.getTc() : 0)
                        + (registerBookingInstitutionDomain.getTcLocal() != null ?  registerBookingInstitutionDomain.getTcLocal() : 0)
                        + (registerBookingInstitutionDomain.getGuide() != null ?  registerBookingInstitutionDomain.getGuide() : 0)
        );

        if (supplierDomain.getConfirmation() != null && supplierDomain.getConfirmation()){
            registerBookingInstitutionDomain.setConfirmation(true);
            registerBookingInstitutionDomain.setValueConfirmation(supplierDomain.getValueConfirmation());
            registerBookingInstitutionDomain.setDateConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueConfirmation()));
        }else{
            registerBookingInstitutionDomain.setConfirmation(false);
            registerBookingInstitutionDomain.setValueConfirmation(0);
        }

        if (supplierDomain.getReConfirmation() != null && supplierDomain.getReConfirmation()){
            registerBookingInstitutionDomain.setReConfirmation(true);
            registerBookingInstitutionDomain.setValueReConfirmation(supplierDomain.getValueReConfirmation());
            registerBookingInstitutionDomain.setDateReConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueReConfirmation()));
        }else{
            registerBookingInstitutionDomain.setReConfirmation(false);
            registerBookingInstitutionDomain.setValueReConfirmation(0);
        }
        Map<Integer, List<ItineraryItemDomain>> mapItemInstitutionService = new HashMap<>();
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
                            if ( !mapItemInstitutionService.containsKey( Integer.parseInt(itineraryDateDomain.getItemItinerary()) ) ){
                                mapItemInstitutionService.put(Integer.parseInt(itineraryDateDomain.getItemItinerary()), new ArrayList<ItineraryItemDomain>());
                                mapItemInstitutionService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }else{
                                mapItemInstitutionService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }
                        }
                    }
                }
            }
        }
        RegisterBookingInstitutionDomain createdDomainRegisterBookingInstitution = repo.create(registerBookingInstitutionDomain);
        Integer sequence = 1;
        for (Integer itemLodgingServiceType : mapItemInstitutionService.keySet()) {
            List<ItineraryItemDomain> list = new ArrayList<ItineraryItemDomain>();
            list.addAll(mapItemInstitutionService.get(itemLodgingServiceType));
            for (ItineraryItemDomain itineraryItemDomain: list) {
                RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain = new RegisterBookingItemInstitutionDomain();
                registerBookingItemInstitutionDomain.setRegisterBookingInstitution(createdDomainRegisterBookingInstitution);
                registerBookingItemInstitutionDomain.setRegisterGroup(registerGroupDomain);
                registerBookingItemInstitutionDomain.setService(itineraryItemDomain.getService());
                registerBookingItemInstitutionDomain.setObservation(itineraryItemDomain.getObservation());
                registerBookingItemInstitutionDomain.setPvt(itineraryItemDomain.getOperatorPvt());
                registerBookingItemInstitutionDomain.setSic(itineraryItemDomain.getOperatorSic());
                registerBookingItemInstitutionDomain.setDate(itineraryItemDomain.getItineraryDate().getItineraryDate());
                registerBookingItemInstitutionDomain.setDateTime(itineraryItemDomain.getItineraryDateTime());
                registerBookingItemInstitutionDomain.setIdItineraryItemIdentify(itineraryItemDomain.getId());
                registerBookingItemInstitutionDomain.setSequence(sequence);
                registerBookingItemInstitutionDomain.setDateService(registerGroupDomain.getInitTour().plusDays(Integer.parseInt(itineraryItemDomain.getItineraryDate().getItemItinerary()) - 1));
                registerBookingItemInstitutionDomain.setCantPax(itineraryItemDomain.getPaxTotal());
                registerBookingItemInstitutionDomain.setTenant(UserResolver.resolveUser().getTenant());

                RegisterBookingItemInstitutionDomain createdDomainRegisterBookingItemInstitution = registerBookingItemInstitutionRepo.create(registerBookingItemInstitutionDomain);
                registerBookingItemInstitutionDomains.add(createdDomainRegisterBookingItemInstitution);
                sequence = sequence + 1;
                numberService = numberService + 1;
            }
        }

        // Update models

        if ( registerBookingItemInstitutionDomains.size() == 1 ){
            createdDomainRegisterBookingInstitution.setDateInitActivity(registerBookingItemInstitutionDomains.get(0).getDate());
            createdDomainRegisterBookingInstitution.setDateEndActivity(registerBookingItemInstitutionDomains.get(0).getDate());
        }else if ( registerBookingItemInstitutionDomains.size() > 1 ){
            Collections.sort(registerBookingItemInstitutionDomains);
            Object [] arrayRegisterBookingItemInstitutionDomains = registerBookingItemInstitutionDomains.toArray();
            createdDomainRegisterBookingInstitution.setDateInitActivity(((RegisterBookingItemInstitutionDomain)arrayRegisterBookingItemInstitutionDomains[0]).getDate());
            createdDomainRegisterBookingInstitution.setDateEndActivity(((RegisterBookingItemInstitutionDomain)arrayRegisterBookingItemInstitutionDomains[arrayRegisterBookingItemInstitutionDomains.length-1]).getDate());
        }

        createdDomainRegisterBookingInstitution.setNumberService(numberService);

        RegisterBookingInstitutionDomain editedRegisterBookingInstitutionDomain = repo.edit(createdDomainRegisterBookingInstitution);
        result = RegisterBookingInstitutionResponse.from(editedRegisterBookingInstitutionDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplier(idRegisterGroup, idSupplier);
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemDomain.setRegisterBookingBase(editedRegisterBookingInstitutionDomain);
            itineraryItemRepo.edit(itineraryItemDomain);
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemInstitutionResponse> findAllRegisterBookingItemInstitutionByBooking(String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemInstitutionUseCase.searchAllByBooking(idBooking);
    }

    @Override
    @Transactional
    public RegisterBookingItemInstitutionResponse addRegisterBookingItemInstitution(RegisterBookingItemInstitutionCreateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemInstitutionUseCase.create(request, registerBookingInstitutionDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemInstitutionResponse editRegisterBookingItemInstitution(String id, RegisterBookingItemInstitutionUpdateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemInstitutionUseCase.edit(id, request, registerBookingInstitutionDomain);
    }

    @Override
    @Transactional
    public void deleteRegisterBookingItemInstitution(String id, String idBooking) {
        RegisterBookingInstitutionResponse old = findById(idBooking);
        registerBookingItemInstitutionUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemInstitutionResponse findByIdAndBooking(String id, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemInstitutionUseCase.searchByBookingAndRegisterBookingItem(idBooking, id);
    }

    @Override
    @Transactional
    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idRegisterBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        PassengerGroupDomain passengerGroupDomain = null;
        if (request.getPassengerGroup() != null && !request.getPassengerGroup().isEmpty()){
            passengerGroupDomain = passengerGroupRepo.findById(request.getPassengerGroup());
        }
        return passengerBookingUseCase.create(request, passengerGroupDomain, null, null, null, null, null, registerBookingInstitutionDomain, registerBookingInstitutionDomain.getRegisterGroup(), null);
    }

    @Override
    @Transactional
    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idRegisterBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.createMultiple(request, null, null, null, null, null, registerBookingInstitutionDomain, registerBookingInstitutionDomain.getRegisterGroup(), null);
    }

    @Override
    @Transactional
    public void deletePassengerBooking(String id, String idRegisterBooking) {
        RegisterBookingInstitutionResponse old = findById(idRegisterBooking);
        passengerBookingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idRegisterBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingInstitutionAndRegisterGroup(idRegisterBooking, registerBookingInstitutionDomain.getRegisterGroup().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idRegisterBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingInstitutionAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, registerBookingInstitutionDomain.getRegisterGroup().getId(), typePassengerTcBooking);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingInstitutionCuponPdf(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idRegisterBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());
        parameters.put("registerBookingInstitution", registerBookingInstitutionDomain);
        parameters.put("noCupon", registerBookingInstitutionDomain.getStrCuponIsNoInternAndImpress());
        parameters.put("strPaxCupon", registerBookingInstitutionDomain.getStrPaxCuponNewVersion());

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

        List<RegisterBookingItemInstitutionDomain> registerBookingItemInstitutionDomains = registerBookingItemInstitutionRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemInstitutionDomains != null && !registerBookingItemInstitutionDomains.isEmpty()) {
            registerBookingItemInstitutionDomains = registerBookingItemInstitutionDomains.stream().collect(Collectors.toList());
        }
        Collections.sort(registerBookingItemInstitutionDomains);

        List<RptModelItemCuponInstitution> list = new ArrayList<RptModelItemCuponInstitution>();

        Integer identify = 1;
        Integer count = 0;

        for (RegisterBookingItemInstitutionDomain registerBookingItemInstitutionDomain: registerBookingItemInstitutionDomains) {
            if ( count == 0){
                RptModelItemCuponInstitution rptModelItemCupon = new RptModelItemCuponInstitution();
                rptModelItemCupon.setList(new ArrayList<>());
                rptModelItemCupon.setId(identify);
                rptModelItemCupon.getList().add(registerBookingItemInstitutionDomain);
                list.add(rptModelItemCupon);
                identify = identify + 1;
            }else if ( count % 8 == 0){
                RptModelItemCuponInstitution rptModelItemCupon = new RptModelItemCuponInstitution();
                rptModelItemCupon.setList(new ArrayList<>());
                rptModelItemCupon.setId(identify);
                rptModelItemCupon.getList().add(registerBookingItemInstitutionDomain);
                list.add(rptModelItemCupon);
                identify = identify + 1;
            }else{
                RptModelItemCuponOperator temp = new RptModelItemCuponOperator();
                temp.setId(identify-1);
                RptModelItemCuponInstitution rptModelItemCupon = list.get(list.indexOf(temp));
                rptModelItemCupon.getList().add(registerBookingItemInstitutionDomain);
            }
            count = count + 1;
        }
        
        List<RegisterBookingItemInstitutionDomain> listSubReport = new ArrayList<>();

        return jrRegisterBookingInstitutionCuponRepository.export(list, listSubReport, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingInstitutionPdf(String idRegisterBooking) {
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
        RegisterBookingInstitutionDomain registerBookingInstitutionDomain = repo.findById(idRegisterBooking);
        if (registerBookingInstitutionDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        List<RegisterBookingInstitutionDomain> list = new ArrayList<RegisterBookingInstitutionDomain>();
        list.add(registerBookingInstitutionDomain);

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

        List<RegisterBookingItemInstitutionDomain> registerBookingItemInstitutionDomains = registerBookingItemInstitutionRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemInstitutionDomains != null && !registerBookingItemInstitutionDomains.isEmpty()) {
            registerBookingItemInstitutionDomains = registerBookingItemInstitutionDomains.stream().collect(Collectors.toList());
        }
        Collections.sort(registerBookingItemInstitutionDomains);

        List<PassengerBookingDomain> passengerBookingItems = passengerBookingUseCase.searchAllByRegisterBookingOperatorAndRegisterGroupDomain(idRegisterBooking, registerBookingInstitutionDomain.getRegisterGroup().getId());
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
        return jrRegisterBookingInstitutionRepository.export(list, registerBookingItemInstitutionDomains, passengerBookingItemsResult, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public RegisterBookingInstitutionResponse changeState(String id, RegisterBookingChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking institution found with id '%s'.".formatted(id));
        }
        old.setStateBooking(request.getStateBooking());
        RegisterBookingInstitutionDomain editedDomain = repo.edit(old);
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
        return RegisterBookingInstitutionResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingInstitutionResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking institution found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainInstitution = supplierTypeRepo.findOne("10", null);
        SupplierInstitutionDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierInstitutionRepo.findById(request.getSupplier());
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
                    if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainInstitution.getId())) {
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

        RegisterBookingInstitutionDomain editedDomain = repo.edit(old);

        return RegisterBookingInstitutionResponse.from(editedDomain);
    }

    private void _changeStateDraft(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRequest(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateConfirmed(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateReconfirmed(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateModified(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateAnnulled(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){
        registerBookingInstitutionDomain.setActiveRegisterBooking(false);
        repo.edit(registerBookingInstitutionDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplier(registerBookingInstitutionDomain.getRegisterGroup().getId(), registerBookingInstitutionDomain.getSupplier().getId());
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemDomain.setRegisterBookingBase(null);
            itineraryItemRepo.edit(itineraryItemDomain);
        }
    }

    private void _changeStateCancelled(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateBooking(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateToReschedule(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRescheduled(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateFinished(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingMp(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingAbc(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingBcd(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedConfirmPeruRail(RegisterBookingInstitutionDomain registerBookingInstitutionDomain, RegisterBookingChangeStateRequest request){

    }
}
