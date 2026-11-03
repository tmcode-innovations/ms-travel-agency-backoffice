package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingoperator.RegisterBookingOperatorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingOperator;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistCuponNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistRegisterBookingNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemoperator.RegisterBookingItemOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.requestorderservice.RequestOrderServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemoperator.RegisterBookingItemOperatorUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingoperator.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingoperator.JRRegisterBookingOperatorCuponRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingoperator.JRRegisterBookingOperatorRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelItemCuponOperator;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking.RptModelPassenger;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.GeneratedRegisterBookingOperatorItemEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeInheritanceBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.RequestOrderServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerBookingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemOperatorUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingOperatorUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.operation.RequestOrderServiceUseCase;
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
public class RegisterBookingOperatorUseCaseImpl implements RegisterBookingOperatorUseCase {

    @Autowired
    private RegisterBookingOperatorRepo repo;

    @Autowired
    private RegisterBookingOperatorConverter converter;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

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
    private RegisterBookingItemOperatorRepo registerBookingItemOperatorRepo;

    @Autowired
    private RegisterBookingItemOperatorUseCase registerBookingItemOperatorUseCase;

    @Autowired
    PersistRegisterBookingNumberCallbackImpl persistRegisterBookingNumberCallback;

    @Autowired
    PersistCuponNumberCallbackImpl persistCuponNumberCallback;

    @Autowired
    private SetFieldDefaultRegisterBookingOperator setFieldDefault;

    @Autowired
    private PassengerBookingUseCase passengerBookingUseCase;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRRegisterBookingOperatorCuponRepository jrRegisterBookingOperatorCuponRepository;

    @Autowired
    private JRRegisterBookingOperatorRepository jrRegisterBookingOperatorRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Autowired
    private RequestOrderServiceRepo requestOrderServiceRepo;

    @Autowired
    private RequestOrderServiceUseCase requestOrderServiceUseCase;

    @Override
    @Transactional
    public RegisterBookingOperatorResponse create(RegisterBookingOperatorCreateRequest request) {
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

        RegisterBookingOperatorDomain newDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        newDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        newDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_OPERATOR.getName());
        RegisterBookingOperatorDomain createdDomain = repo.create(newDomain);
        return RegisterBookingOperatorResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingOperatorResponse edit(String id, RegisterBookingOperatorUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingOperatorDomain old = repo.findById(id);
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

        RegisterBookingOperatorDomain registerBookingOperatorDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain, operatorArriveDomain, operatorDepartureDomain, supplierLodgingDomain);
        registerBookingOperatorDomain.setIdentify(old.getIdentify());
        registerBookingOperatorDomain.setStateBooking(old.getStateBooking());
        registerBookingOperatorDomain.setTypeInheritanceBooking(old.getTypeInheritanceBooking());

        registerBookingOperatorDomain.setNoCupon(old.getNoCupon());
        registerBookingOperatorDomain.setDateAperture(old.getDateAperture());
        registerBookingOperatorDomain.setDateInitActivity(old.getDateInitActivity());
        registerBookingOperatorDomain.setDateEndActivity(old.getDateEndActivity());
        registerBookingOperatorDomain.setNumberService(old.getNumberService());

        registerBookingOperatorDomain.setCantPax(old.getCantPax());
        registerBookingOperatorDomain.setCantFree(old.getCantFree());
        registerBookingOperatorDomain.setCantPaxAdult(old.getCantPaxAdult());
        registerBookingOperatorDomain.setCantPaxChildren(old.getCantPaxChildren());
        registerBookingOperatorDomain.setTc(old.getTc());
        registerBookingOperatorDomain.setTcLocal(old.getTcLocal());

        registerBookingOperatorDomain.setDateConfirmation(old.getDateConfirmation());
        registerBookingOperatorDomain.setDateReConfirmation(old.getDateReConfirmation());

        registerBookingOperatorDomain.setDateTimeInitService(old.getDateTimeInitService());

        registerBookingOperatorDomain.setActiveRegisterBooking(old.getActiveRegisterBooking());

        RegisterBookingOperatorDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingOperatorDomain, old));
        return RegisterBookingOperatorResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingOperatorResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingOperatorResponse> findAll() {
        return repo.findAll(TypeInheritanceBookingEnum.REGISTER_BOOKING_OPERATOR.getName()).stream().map(registerBookingOperatorDomain -> RegisterBookingOperatorResponse.from(registerBookingOperatorDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingOperatorResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingOperatorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingOperatorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingOperatorSearchResponse searchAll(RegisterBookingOperatorSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingOperatorDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingOperatorSearchResponse bankSearchRespose = new RegisterBookingOperatorSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingOperatorResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingOperatorSearchResponse searchFilterAll(RegisterBookingOperatorSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingOperatorDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingOperatorSearchResponse bankSearchRespose = new RegisterBookingOperatorSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingOperatorResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingOperatorResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingOperatorDomain old = repo.findByNameAndTypeInheritanceBooking(name, TypeInheritanceBookingEnum.REGISTER_BOOKING_OPERATOR.getName());
        if (old == null) {
            throw new NotFoundException("No register booking operator found with name '%s'.".formatted(name));
        }
        return RegisterBookingOperatorResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingOperatorResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingOperatorDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceBookingEnum.REGISTER_BOOKING_OPERATOR.getName());
        if (old == null) {
            throw new NotFoundException("No register booking operator found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingOperatorResponse.from(old);
    }

    @Override
    @Transactional
    public RegisterBookingOperatorResponse generate(String idRegisterGroup, String idSupplier) {
        RegisterBookingOperatorResponse result = null;
        SupplierOperatorDomain supplierDomain = null;
        if (idSupplier != null && !idSupplier.isEmpty()){
            supplierDomain = supplierOperatorRepo.findById(idSupplier);
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        }

        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);

        List<RegisterBookingItemOperatorDomain> registerBookingItemOperatorDomains = new ArrayList<>();
        RegisterBookingOperatorDomain registerBookingOperatorDomain = new RegisterBookingOperatorDomain();
        registerBookingOperatorDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_OPERATOR.getName());
        registerBookingOperatorDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        registerBookingOperatorDomain.setSupplier(supplierDomain);
        registerBookingOperatorDomain.setSupplierType(supplierTypeDomainOperator);
        registerBookingOperatorDomain.setRegisterGroup(registerGroupDomain);
        registerBookingOperatorDomain.setEmployee(registerGroupDomain.getResponsible());
        registerBookingOperatorDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        registerBookingOperatorDomain.setDateAperture(LocalDate.now());
        registerBookingOperatorDomain.setCustomer(registerGroupDomain.getCustomer());
        registerBookingOperatorDomain.setGastosExtrasNoIncluidos(true);
        registerBookingOperatorDomain.setArriveAir(registerGroupDomain.getArriveAir());
        registerBookingOperatorDomain.setDepartureAir(registerGroupDomain.getDepartureAir());
        registerBookingOperatorDomain.setNoCupon(persistCuponNumberCallback.generateNumber());
        registerBookingOperatorDomain.setNumberService(0);
        registerBookingOperatorDomain.setCantPax(0);
        registerBookingOperatorDomain.setCantFree(0);
        registerBookingOperatorDomain.setCantPaxAdult(0);
        registerBookingOperatorDomain.setCantPaxChildren(0);
        registerBookingOperatorDomain.setTc(0);
        registerBookingOperatorDomain.setTcLocal(0);
        registerBookingOperatorDomain.setGuide(0);
        registerBookingOperatorDomain.setTotalPax(0);
        registerBookingOperatorDomain.setActiveRegisterBooking(true);
        registerBookingOperatorDomain.setTenant(UserResolver.resolveUser().getTenant());

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

        registerBookingOperatorDomain.setCantPaxAdult(cantAdult);
        registerBookingOperatorDomain.setCantPaxChildren(cantChildren);
        registerBookingOperatorDomain.setCantFree(cantFree);
        registerBookingOperatorDomain.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        registerBookingOperatorDomain.setTc(cantTc);
        registerBookingOperatorDomain.setTcLocal(cantTcLocal);
        registerBookingOperatorDomain.setGuide(cantGuide);
        registerBookingOperatorDomain.setTotalPax(
                (registerBookingOperatorDomain.getCantPax() != null ?  registerBookingOperatorDomain.getCantPax() : 0)
                        + (registerBookingOperatorDomain.getTc() != null ?  registerBookingOperatorDomain.getTc() : 0)
                        + (registerBookingOperatorDomain.getTcLocal() != null ?  registerBookingOperatorDomain.getTcLocal() : 0)
                        + (registerBookingOperatorDomain.getGuide() != null ?  registerBookingOperatorDomain.getGuide() : 0)
        );*/

        if (supplierDomain.getConfirmation() != null && supplierDomain.getConfirmation()){
            registerBookingOperatorDomain.setConfirmation(true);
            registerBookingOperatorDomain.setValueConfirmation(supplierDomain.getValueConfirmation());
            registerBookingOperatorDomain.setDateConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueConfirmation()));
        }else{
            registerBookingOperatorDomain.setConfirmation(false);
            registerBookingOperatorDomain.setValueConfirmation(0);
        }

        if (supplierDomain.getReConfirmation() != null && supplierDomain.getReConfirmation()){
            registerBookingOperatorDomain.setReConfirmation(true);
            registerBookingOperatorDomain.setValueReConfirmation(supplierDomain.getValueReConfirmation());
            registerBookingOperatorDomain.setDateReConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueReConfirmation()));
        }else{
            registerBookingOperatorDomain.setReConfirmation(false);
            registerBookingOperatorDomain.setValueReConfirmation(0);
        }
        Map<Integer, List<ItineraryItemDomain>> mapItemOperatorService = new HashMap<>();
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
                            if ( !mapItemOperatorService.containsKey( Integer.parseInt(itineraryDateDomain.getItemItinerary()) ) ){
                                mapItemOperatorService.put(Integer.parseInt(itineraryDateDomain.getItemItinerary()), new ArrayList<ItineraryItemDomain>());
                                mapItemOperatorService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }else{
                                mapItemOperatorService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }
                        }
                    }
                }
            }
        }
        RegisterBookingOperatorDomain createdDomainRegisterBookingOperator = repo.create(registerBookingOperatorDomain);
        Integer sequence = 1;
        for (Integer itemLodgingServiceType : mapItemOperatorService.keySet()) {
            List<ItineraryItemDomain> list = new ArrayList<ItineraryItemDomain>();
            list.addAll(mapItemOperatorService.get(itemLodgingServiceType));
            for (ItineraryItemDomain itineraryItemDomain: list) {
                Integer cantAdultItem = itineraryItemDomain.getPaxAdultTotal();
                Integer cantChildrenItem = itineraryItemDomain.getPaxChildrenTotal();
                Integer cantTcItem = itineraryItemDomain.getPaxTcTotal();
                Integer cantTcLocalItem = itineraryItemDomain.getPaxTcLocalTotal();
                Integer cantGuideItem = itineraryItemDomain.getPaxGuideTotal();

                RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain = new RegisterBookingItemOperatorDomain();
                registerBookingItemOperatorDomain.setRegisterBookingOperator(createdDomainRegisterBookingOperator);
                registerBookingItemOperatorDomain.setRegisterGroup(registerGroupDomain);
                registerBookingItemOperatorDomain.setService(itineraryItemDomain.getService());
                registerBookingItemOperatorDomain.setObservation(itineraryItemDomain.getObservation());
                registerBookingItemOperatorDomain.setPvt(itineraryItemDomain.getOperatorPvt());
                registerBookingItemOperatorDomain.setSic(itineraryItemDomain.getOperatorSic());
                registerBookingItemOperatorDomain.setDate(itineraryItemDomain.getItineraryDate().getItineraryDate());
                registerBookingItemOperatorDomain.setDateTime(itineraryItemDomain.getItineraryDateTime());
                registerBookingItemOperatorDomain.setIdItineraryItemIdentify(itineraryItemDomain.getId());
                registerBookingItemOperatorDomain.setSequence(sequence);
                registerBookingItemOperatorDomain.setDateService(registerGroupDomain.getInitTour().plusDays(Integer.parseInt(itineraryItemDomain.getItineraryDate().getItemItinerary()) - 1));

                registerBookingItemOperatorDomain.setCantPax(
                        (cantAdultItem != null ?  cantAdultItem : 0)
                        + (cantChildrenItem != null ?  cantChildrenItem : 0)
                );
                registerBookingItemOperatorDomain.setCantPaxAdult(cantAdultItem != null ?  cantAdultItem : 0);
                registerBookingItemOperatorDomain.setCantPaxChildren(cantChildrenItem != null ?  cantChildrenItem : 0);
                registerBookingItemOperatorDomain.setTc(cantTcItem != null ?  cantTcItem : 0);
                registerBookingItemOperatorDomain.setTcLocal(cantTcLocalItem != null ?  cantTcLocalItem : 0);
                registerBookingItemOperatorDomain.setGuide(cantGuideItem != null ?  cantGuideItem : 0);

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

                // registerBookingItemOperatorDomain.setGeneratedBy(GeneratedRegisterBookingOperatorItemEnum.BOOKING.getName());
                registerBookingItemOperatorDomain.setGeneratedBy(itineraryItemDomain.getGeneratedBy());

                registerBookingItemOperatorDomain.setTenant(UserResolver.resolveUser().getTenant());

                RegisterBookingItemOperatorDomain createdDomainRegisterBookingItemOperator = registerBookingItemOperatorRepo.create(registerBookingItemOperatorDomain);
                registerBookingItemOperatorDomains.add(createdDomainRegisterBookingItemOperator);
                itineraryItemRepo.updateCustomerByIdSimpleDomainCaseUpdateRegisterBookingItemOperator(itineraryItemDomain.getId(), createdDomainRegisterBookingItemOperator.getId());
                sequence = sequence + 1;
                numberService = numberService + 1;
            }
        }

        // Update models

        if ( registerBookingItemOperatorDomains.size() == 1 ){
            createdDomainRegisterBookingOperator.setDateInitActivity(registerBookingItemOperatorDomains.get(0).getDate());
            createdDomainRegisterBookingOperator.setDateEndActivity(registerBookingItemOperatorDomains.get(0).getDate());

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    registerBookingItemOperatorDomains.get(0).getDate().getYear(),
                    registerBookingItemOperatorDomains.get(0).getDate().getMonth(),
                    registerBookingItemOperatorDomains.get(0).getDate().getDayOfMonth(),
                    registerBookingItemOperatorDomains.get(0).getDateTime().getHour(),
                    registerBookingItemOperatorDomains.get(0).getDateTime().getMinute(),
                    registerBookingItemOperatorDomains.get(0).getDateTime().getSecond());

            createdDomainRegisterBookingOperator.setDateTimeInitService(localDateTimeInitService);
        }else if ( registerBookingItemOperatorDomains.size() > 1 ){
            Collections.sort(registerBookingItemOperatorDomains);
            Object [] arrayRegisterBookingItemOperatorDomains = registerBookingItemOperatorDomains.toArray();
            createdDomainRegisterBookingOperator.setDateInitActivity(((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[0]).getDate());
            createdDomainRegisterBookingOperator.setDateEndActivity(((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[arrayRegisterBookingItemOperatorDomains.length-1]).getDate());

            LocalDateTime localDateTimeInitService = LocalDateTime.of(
                    ((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[0]).getDate().getYear(),
                    ((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[0]).getDate().getMonth(),
                    ((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[0]).getDate().getDayOfMonth(),
                    ((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[0]).getDateTime().getHour(),
                    ((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[0]).getDateTime().getMinute(),
                    ((RegisterBookingItemOperatorDomain)arrayRegisterBookingItemOperatorDomains[0]).getDateTime().getSecond());

            createdDomainRegisterBookingOperator.setDateTimeInitService(localDateTimeInitService);
        }

        createdDomainRegisterBookingOperator.setNumberService(numberService);

        createdDomainRegisterBookingOperator.setCantPaxAdult(cantAdult);
        createdDomainRegisterBookingOperator.setCantPaxChildren(cantChildren);
        createdDomainRegisterBookingOperator.setCantFree(cantFree);
        createdDomainRegisterBookingOperator.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                        + (cantChildren != null ? cantChildren : 0)
                        + (cantFree != null ? cantFree : 0)
        );
        createdDomainRegisterBookingOperator.setTc(cantTc);
        createdDomainRegisterBookingOperator.setTcLocal(cantTcLocal);
        createdDomainRegisterBookingOperator.setGuide(cantGuide);

        createdDomainRegisterBookingOperator.setTotalPax(
                (createdDomainRegisterBookingOperator.getCantPax() != null ?  createdDomainRegisterBookingOperator.getCantPax() : 0)
                        + (createdDomainRegisterBookingOperator.getTc() != null ?  createdDomainRegisterBookingOperator.getTc() : 0)
                        + (createdDomainRegisterBookingOperator.getTcLocal() != null ?  createdDomainRegisterBookingOperator.getTcLocal() : 0)
                        + (createdDomainRegisterBookingOperator.getGuide() != null ?  createdDomainRegisterBookingOperator.getGuide() : 0)
        );

        RegisterBookingOperatorDomain editedRegisterBookingOperatorDomain = repo.edit(createdDomainRegisterBookingOperator);
        result = RegisterBookingOperatorResponse.from(editedRegisterBookingOperatorDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(idRegisterGroup, idSupplier);
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), editedRegisterBookingOperatorDomain.getId());
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemOperatorResponse> findAllRegisterBookingItemOperatorByBooking(String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemOperatorUseCase.searchAllByBooking(idBooking);
    }

    @Override
    @Transactional
    public RegisterBookingItemOperatorResponse addRegisterBookingItemOperator(RegisterBookingItemOperatorCreateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemOperatorUseCase.create(request, registerBookingOperatorDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemOperatorResponse editRegisterBookingItemOperator(String id, RegisterBookingItemOperatorUpdateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemOperatorUseCase.edit(id, request, registerBookingOperatorDomain);
    }

    @Override
    @Transactional
    public void deleteRegisterBookingItemOperator(String id, String idBooking) {
        RegisterBookingOperatorResponse old = findById(idBooking);
        registerBookingItemOperatorUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemOperatorResponse findByIdAndBooking(String id, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemOperatorUseCase.searchByBookingAndRegisterBookingItem(idBooking, id);
    }

    @Override
    @Transactional
    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idRegisterBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        PassengerGroupDomain passengerGroupDomain = null;
        if (request.getPassengerGroup() != null && !request.getPassengerGroup().isEmpty()){
            passengerGroupDomain = passengerGroupRepo.findById(request.getPassengerGroup());
        }
        return passengerBookingUseCase.create(request, passengerGroupDomain, registerBookingOperatorDomain, null, null, null, null, null, registerBookingOperatorDomain.getRegisterGroup(), null);
    }

    @Override
    @Transactional
    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idRegisterBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.createMultiple(request, registerBookingOperatorDomain, null, null, null, null, null, registerBookingOperatorDomain.getRegisterGroup(), null);
    }

    @Override
    @Transactional
    public void deletePassengerBooking(String id, String idRegisterBooking) {
        RegisterBookingOperatorResponse old = findById(idRegisterBooking);
        passengerBookingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idRegisterBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingOperatorAndRegisterGroup(idRegisterBooking, registerBookingOperatorDomain.getRegisterGroup().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingAndTypePassengerTcBooking(String idRegisterBooking, String typePassengerTcBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idRegisterBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingOperatorAndRegisterGroupAndTypePassengerTcBooking(idRegisterBooking, registerBookingOperatorDomain.getRegisterGroup().getId(), typePassengerTcBooking);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingOperatorCuponPdf(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idRegisterBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        // Map<String, Object> parametersOther = new HashMap<>();

        parameters.put("activeUser", UserResolver.resolveUser());
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());
        parameters.put("registerBookingOperator", registerBookingOperatorDomain);
        parameters.put("noCupon", registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress());

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

/*        parametersOther.put("activeUser", UserResolver.resolveUser());
        parametersOther.put("activeCompany", companyDomain);
        parametersOther.put("dateNow", LocalDate.now());
        parametersOther.put("registerBookingOperator", registerBookingOperatorDomain);
        parametersOther.put("noCupon", registerBookingOperatorDomain.getStrCuponIsNoInternAndImpress());

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

        List<RegisterBookingItemOperatorDomain> registerBookingItemOperatorDomains = registerBookingItemOperatorRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemOperatorDomains != null && !registerBookingItemOperatorDomains.isEmpty()) {
            registerBookingItemOperatorDomains = registerBookingItemOperatorDomains.stream().collect(Collectors.toList());
            Collections.sort(registerBookingItemOperatorDomains);
        }

        List<RptModelItemCuponOperator> list = new ArrayList<RptModelItemCuponOperator>();
        // List<RptModelItemCuponOperator> listOther = new ArrayList<RptModelItemCuponOperator>();

        Integer identify = 1;
        // Integer identifyOther = 1;
        Integer count = 0;
        // Integer countOther = 0;

        Integer cantGuide = 0;

        for (RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain: registerBookingItemOperatorDomains) {
            /*if (registerBookingItemOperatorDomain.getTcLocal() > 0){
                if ( countOther == 0){
                    RptModelItemCuponOperator rptModelItemCupon = new RptModelItemCuponOperator();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identifyOther);
                    rptModelItemCupon.getList().add(registerBookingItemOperatorDomain);
                    listOther.add(rptModelItemCupon);
                    identifyOther = identifyOther + 1;
                }else if ( countOther % 8 == 0){
                    RptModelItemCuponOperator rptModelItemCupon = new RptModelItemCuponOperator();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identifyOther);
                    rptModelItemCupon.getList().add(registerBookingItemOperatorDomain);
                    listOther.add(rptModelItemCupon);
                    identifyOther = identifyOther + 1;
                }else{
                    RptModelItemCuponOperator temp = new RptModelItemCuponOperator();
                    temp.setId(identifyOther-1);
                    RptModelItemCuponOperator rptModelItemCupon = listOther.get(listOther.indexOf(temp));
                    rptModelItemCupon.getList().add(registerBookingItemOperatorDomain);
                }
                countOther = countOther + 1;
            }*/
            if (
                    registerBookingItemOperatorDomain.getCantPaxAdult() > 0 ||
                    registerBookingItemOperatorDomain.getCantPaxChildren() > 0 ||
                    registerBookingItemOperatorDomain.getTc() > 0 ||
                    registerBookingItemOperatorDomain.getGuide() > 0 ||
                            registerBookingItemOperatorDomain.getTcLocal() > 0
            ){
                if (registerBookingItemOperatorDomain.getGuide() != null && registerBookingItemOperatorDomain.getGuide() > cantGuide){
                    cantGuide = registerBookingItemOperatorDomain.getGuide();
                }
                if ( count == 0){
                    RptModelItemCuponOperator rptModelItemCupon = new RptModelItemCuponOperator();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identify);
                    rptModelItemCupon.getList().add(registerBookingItemOperatorDomain);
                    list.add(rptModelItemCupon);
                    identify = identify + 1;
                }else if ( count % 8 == 0){
                    RptModelItemCuponOperator rptModelItemCupon = new RptModelItemCuponOperator();
                    rptModelItemCupon.setList(new ArrayList<>());
                    rptModelItemCupon.setId(identify);
                    rptModelItemCupon.getList().add(registerBookingItemOperatorDomain);
                    list.add(rptModelItemCupon);
                    identify = identify + 1;
                }else{
                    RptModelItemCuponOperator temp = new RptModelItemCuponOperator();
                    temp.setId(identify-1);
                    RptModelItemCuponOperator rptModelItemCupon = list.get(list.indexOf(temp));
                    rptModelItemCupon.getList().add(registerBookingItemOperatorDomain);
                }
                count = count + 1;
            }
        }
        String strPaxCupon = registerBookingOperatorDomain.getStrPaxCuponNewVersion();
        if ((registerBookingOperatorDomain.getGuide() == null || registerBookingOperatorDomain.getGuide() == 0) && cantGuide > 0){
            strPaxCupon = strPaxCupon + "+ " + cantGuide + " GUIA ";
        }
        parameters.put("strPaxCupon", strPaxCupon);

        /*String strPaxCuponTcLocal = registerBookingOperatorDomain.getStrPaxCuponNewVersionOnlyTcLocal();
        parametersOther.put("strPaxCupon", strPaxCuponTcLocal);*/

        List<RegisterBookingItemOperatorDomain> listSubReport = new ArrayList<>();

        return jrRegisterBookingOperatorCuponRepository.export(list, listSubReport, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingOperatorPdf(String idRegisterBooking) {
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
        RegisterBookingOperatorDomain registerBookingOperatorDomain = repo.findById(idRegisterBooking);
        if (registerBookingOperatorDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        List<RegisterBookingOperatorDomain> list = new ArrayList<RegisterBookingOperatorDomain>();
        list.add(registerBookingOperatorDomain);

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

        List<RegisterBookingItemOperatorDomain> registerBookingItemOperatorDomains = registerBookingItemOperatorRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemOperatorDomains != null && !registerBookingItemOperatorDomains.isEmpty()) {
            registerBookingItemOperatorDomains = registerBookingItemOperatorDomains.stream().collect(Collectors.toList());
            Collections.sort(registerBookingItemOperatorDomains);
        }

        Integer cantGuide = 0;

        for (RegisterBookingItemOperatorDomain registerBookingItemOperatorDomain: registerBookingItemOperatorDomains){
            if (registerBookingItemOperatorDomain.getGuide() != null && registerBookingItemOperatorDomain.getGuide() > cantGuide){
                cantGuide = registerBookingItemOperatorDomain.getGuide();
            }
        }

        if ((registerBookingOperatorDomain.getGuide() == null || registerBookingOperatorDomain.getGuide() == 0) && cantGuide > 0){
            registerBookingOperatorDomain.setGuide(cantGuide);
            registerBookingOperatorDomain.setTotalPax(registerBookingOperatorDomain.getTotalPax() + registerBookingOperatorDomain.getGuide());
        }

        List<PassengerBookingDomain> passengerBookingItems = passengerBookingUseCase.searchAllByRegisterBookingOperatorAndRegisterGroupDomain(idRegisterBooking, registerBookingOperatorDomain.getRegisterGroup().getId());
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
        return jrRegisterBookingOperatorRepository.export(list, registerBookingItemOperatorDomains, passengerBookingItemsResult, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public RegisterBookingOperatorResponse changeState(String id, RegisterBookingChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingOperatorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking operator found with id '%s'.".formatted(id));
        }
        old.setStateBooking(request.getStateBooking());
        RegisterBookingOperatorDomain editedDomain = repo.edit(old);
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
        return RegisterBookingOperatorResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingOperatorResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingOperatorDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking operator found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
        SupplierOperatorDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierOperatorRepo.findById(request.getSupplier());
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
                    if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())) {
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
        RegisterBookingOperatorDomain editedDomain = repo.edit(old);

        return RegisterBookingOperatorResponse.from(editedDomain);
    }

    private void _changeStateDraft(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRequest(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateConfirmed(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateReconfirmed(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateModified(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateAnnulled(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){
        registerBookingOperatorDomain.setActiveRegisterBooking(false);
        repo.edit(registerBookingOperatorDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplierSimpleDomainCaseGenerateAllBooking(registerBookingOperatorDomain.getRegisterGroup().getId(), registerBookingOperatorDomain.getSupplier().getId());
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemRepo.updateRegisterBookingBaseByIdSimpleDomainCaseUpdateItineraryGenerateBookingAll(itineraryItemDomain.getId(), registerBookingOperatorDomain.getId());
        }
        RequestOrderServiceDomain requestOrderServiceDomain = requestOrderServiceRepo.findByIdSimpleDomainCaseGenerateRequestOrderService(registerBookingOperatorDomain.getRegisterGroup().getId(), registerBookingOperatorDomain.getSupplier().getId(), registerBookingOperatorDomain.getId());
        if (requestOrderServiceDomain != null){
            requestOrderServiceUseCase.deleteById(requestOrderServiceDomain.getId());
        }
    }

    private void _changeStateCancelled(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateBooking(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateToReschedule(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRescheduled(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateFinished(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingMp(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingAbc(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingBcd(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedConfirmPeruRail(RegisterBookingOperatorDomain registerBookingOperatorDomain, RegisterBookingChangeStateRequest request){

    }
}
