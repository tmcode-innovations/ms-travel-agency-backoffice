package com.saas.tourismagency.admin.backoffice.usecase.booking.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.booking.registerbookingglamping.RegisterBookingGlampingConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.booking.SetFieldDefaultRegisterBookingGlamping;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.nationality.NationalityRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistCuponNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingbase.PersistRegisterBookingNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.RegisterBookingGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemglamping.RegisterBookingItemGlamRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingitemlodgingfeeding.RegisterBookingItemLodgingFeedingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingroom.RegisterBookingRoomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengerbooking.PassengerBookingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeStateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingbase.RegisterBookingChangeSupplierRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingglamping.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookingitemglamping.RegisterBookingItemGlampingUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingglamping.JRRegisterBookingGlampingCuponRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.registerbookingglamping.JRRegisterBookingGlampingRepository;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.base.NationalityDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeFeedingBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeInheritanceBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.TypeRoomBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGlampingDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierInstitutionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.booking.PassengerBookingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingGlampingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.booking.RegisterBookingItemGlampingUseCase;
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
import java.util.stream.Stream;

@Slf4j
@Service
public class RegisterBookingGlampingUseCaseImpl implements RegisterBookingGlampingUseCase {

    @Autowired
    private RegisterBookingGlampingRepo repo;

    @Autowired
    private RegisterBookingGlampingConverter converter;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SupplierGlampingRepo supplierGlampingRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RegisterBookingItemGlamRepo registerBookingItemGlampingRepo;

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
    private RegisterBookingItemGlampingUseCase registerBookingItemGlampingUseCase;

    @Autowired
    PersistRegisterBookingNumberCallbackImpl persistRegisterBookingNumberCallback;

    @Autowired
    PersistCuponNumberCallbackImpl persistCuponNumberCallback;

    @Autowired
    private SetFieldDefaultRegisterBookingGlamping setFieldDefault;

    @Autowired
    private PassengerBookingUseCase passengerBookingUseCase;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRRegisterBookingGlampingCuponRepository jrRegisterBookingGlampingCuponRepository;

    @Autowired
    private JRRegisterBookingGlampingRepository jrRegisterBookingGlampingRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional
    public RegisterBookingGlampingResponse create(RegisterBookingGlampingCreateRequest request) {
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

        SupplierGlampingDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierGlampingRepo.findById(request.getSupplier());
        }

        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingGlampingDomain newDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        newDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        newDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName());
        RegisterBookingGlampingDomain createdDomain = repo.create(newDomain);
        return RegisterBookingGlampingResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public RegisterBookingGlampingResponse edit(String id, RegisterBookingGlampingUpdateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingGlampingDomain old = repo.findById(id);
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

        SupplierGlampingDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierGlampingRepo.findById(request.getSupplier());
        }

        NationalityDomain nationalityDomain = null;
        if (request.getNationality() != null && !request.getNationality().isEmpty()){
            nationalityDomain = nationalityRepo.findById(request.getNationality());
        }

        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(request.getRegisterGroup());
        }

        RegisterBookingGlampingDomain registerBookingGlampingDomain = request.to(supplierTypeDomain, supplierDomain, registerGroupDomain, customerDomain, nationalityDomain, employeeDomain);
        registerBookingGlampingDomain.setIdentify(old.getIdentify());
        registerBookingGlampingDomain.setStateBooking(old.getStateBooking());
        registerBookingGlampingDomain.setTypeInheritanceBooking(old.getTypeInheritanceBooking());

        registerBookingGlampingDomain.setDateAperture(old.getDateAperture());
        registerBookingGlampingDomain.setInitTour(old.getInitTour());
        registerBookingGlampingDomain.setEndTour(old.getEndTour());
        registerBookingGlampingDomain.setInitHourTour(old.getInitHourTour());
        registerBookingGlampingDomain.setEndHourTour(old.getEndHourTour());
        registerBookingGlampingDomain.setNumberIngress(old.getNumberIngress());

        registerBookingGlampingDomain.setCantPax(old.getCantPax());
        registerBookingGlampingDomain.setCantFree(old.getCantFree());
        registerBookingGlampingDomain.setCantPaxAdult(old.getCantPaxAdult());
        registerBookingGlampingDomain.setCantPaxChildren(old.getCantPaxChildren());
        registerBookingGlampingDomain.setTc(old.getTc());
        registerBookingGlampingDomain.setTcLocal(old.getTcLocal());

        registerBookingGlampingDomain.setCantSwb(old.getCantSwb());
        registerBookingGlampingDomain.setCantSwbTcGuide(old.getCantSwbTcGuide());
        registerBookingGlampingDomain.setCantSwbTcLocalGuide(old.getCantSwbTcLocalGuide());
        registerBookingGlampingDomain.setCantTrp(old.getCantTrp());
        registerBookingGlampingDomain.setCantCdp(old.getCantCdp());
        registerBookingGlampingDomain.setCantQtp(old.getCantQtp());
        registerBookingGlampingDomain.setCantSxtp(old.getCantSxtp());
        registerBookingGlampingDomain.setCantTwin(old.getCantTwin());
        registerBookingGlampingDomain.setCantTwinTcGuide(old.getCantTwinTcGuide());
        registerBookingGlampingDomain.setCantTwinTcLocalGuide(old.getCantTwinTcLocalGuide());
        registerBookingGlampingDomain.setCantMat(old.getCantMat());

        registerBookingGlampingDomain.setDateConfirmation(old.getDateConfirmation());
        registerBookingGlampingDomain.setDateReConfirmation(old.getDateReConfirmation());

        registerBookingGlampingDomain.setActiveRegisterBooking(old.getActiveRegisterBooking());

        RegisterBookingGlampingDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(registerBookingGlampingDomain, old));
        return RegisterBookingGlampingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        RegisterBookingGlampingResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingGlampingResponse> findAll() {
        return repo.findAll(TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName()).stream().map(registerBookingGlampingDomain -> RegisterBookingGlampingResponse.from(registerBookingGlampingDomain)).toList();
    }

    @Override
    public RegisterBookingGlampingResponse findById(String id) {
        if (id == null) {
            return null;
        }
        RegisterBookingGlampingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return RegisterBookingGlampingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingGlampingSearchResponse searchAll(RegisterBookingGlampingSearchRequest bankSearchRequest) {
       PageDTO<RegisterBookingGlampingDomain> pageDTO = repo.searchAll(bankSearchRequest);
       RegisterBookingGlampingSearchResponse bankSearchRespose = new RegisterBookingGlampingSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingGlampingResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingGlampingSearchResponse searchFilterAll(RegisterBookingGlampingSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<RegisterBookingGlampingDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        RegisterBookingGlampingSearchResponse bankSearchRespose = new RegisterBookingGlampingSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  RegisterBookingGlampingResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingGlampingResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        RegisterBookingGlampingDomain old = repo.findByNameAndTypeInheritanceBooking(name, TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName());
        if (old == null) {
            throw new NotFoundException("No register booking glamping found with name '%s'.".formatted(name));
        }
        return RegisterBookingGlampingResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingGlampingResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        RegisterBookingGlampingDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName());
        if (old == null) {
            throw new NotFoundException("No register booking glamping found with identify '%s'.".formatted(identify));
        }
        return RegisterBookingGlampingResponse.from(old);
    }

    @Override
    @Transactional
    public RegisterBookingGlampingResponse generate(String idRegisterGroup, String idSupplier) {
        RegisterBookingGlampingResponse result = null;
        SupplierGlampingDomain supplierDomain = null;
        if (idSupplier != null && !idSupplier.isEmpty()){
            supplierDomain = supplierGlampingRepo.findById(idSupplier);
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (idRegisterGroup != null && !idRegisterGroup.isEmpty()){
            registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        }
        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("9", null);
        List<RegisterBookingItemGlampingDomain> registerBookingItemGlampingDomains = new ArrayList<>();
        RegisterBookingGlampingDomain registerBookingGlampingDomain = new RegisterBookingGlampingDomain();
        registerBookingGlampingDomain.setTypeInheritanceBooking(TypeInheritanceBookingEnum.REGISTER_BOOKING_GLAMPING.getName());
        registerBookingGlampingDomain.setIdentify(persistRegisterBookingNumberCallback.generateNumber());
        registerBookingGlampingDomain.setSupplier(supplierDomain);
        registerBookingGlampingDomain.setSupplierType(supplierTypeDomainLodging);
        registerBookingGlampingDomain.setRegisterGroup(registerGroupDomain);
        registerBookingGlampingDomain.setEmployee(registerGroupDomain.getResponsible());
        registerBookingGlampingDomain.setStateBooking(StateBookingEnum.DRAFT.getName());
        registerBookingGlampingDomain.setDateAperture(LocalDate.now());
        registerBookingGlampingDomain.setCustomer(registerGroupDomain.getCustomer());
        registerBookingGlampingDomain.setGastosExtrasNoIncluidos(true);
        registerBookingGlampingDomain.setArriveAir(registerGroupDomain.getArriveAir());
        registerBookingGlampingDomain.setDepartureAir(registerGroupDomain.getDepartureAir());
        registerBookingGlampingDomain.setCantSwb(0);
        registerBookingGlampingDomain.setCantTrp(0);
        registerBookingGlampingDomain.setCantCdp(0);
        registerBookingGlampingDomain.setCantTwin(0);
        registerBookingGlampingDomain.setCantMat(0);
        registerBookingGlampingDomain.setCantQtp(0);
        registerBookingGlampingDomain.setCantSxtp(0);
        registerBookingGlampingDomain.setCantSwbTcGuide(0);
        registerBookingGlampingDomain.setCantTwinTcGuide(0);
        registerBookingGlampingDomain.setCantSwbTcLocalGuide(0);
        registerBookingGlampingDomain.setCantTwinTcLocalGuide(0);
        registerBookingGlampingDomain.setCantPax(0);
        registerBookingGlampingDomain.setCantFree(0);
        registerBookingGlampingDomain.setCantPaxAdult(0);
        registerBookingGlampingDomain.setCantPaxChildren(0);
        registerBookingGlampingDomain.setTc(0);
        registerBookingGlampingDomain.setTcLocal(0);
        registerBookingGlampingDomain.setTotalPax(0);
        registerBookingGlampingDomain.setActiveRegisterBooking(true);

        registerBookingGlampingDomain.setTenant(UserResolver.resolveUser().getTenant());

        Integer cantAdult = 0;
        Integer cantChildren = 0;
        Integer cantTc = 0;
        Integer cantTcLocal = 0;
        Integer cantFree = 0;

        cantAdult = registerGroupDomain.getCantPaxAdult();
        cantChildren = registerGroupDomain.getCantPaxChildren();
        cantFree = registerGroupDomain.getCantFree();
        cantTc = registerGroupDomain.getTC();
        cantTcLocal = registerGroupDomain.getTCLocal();

        registerBookingGlampingDomain.setCantPaxAdult(cantAdult);
        registerBookingGlampingDomain.setCantPaxChildren(cantChildren);
        registerBookingGlampingDomain.setCantFree(cantFree);
        registerBookingGlampingDomain.setCantPax(
                (cantAdult != null ? cantAdult : 0)
                + (cantChildren != null ? cantChildren : 0)
                + (cantFree != null ? cantFree : 0)
        );
        registerBookingGlampingDomain.setTc(cantTc);
        registerBookingGlampingDomain.setTcLocal(cantTcLocal);
        registerBookingGlampingDomain.setTotalPax(
                (registerBookingGlampingDomain.getCantPax() != null ?  registerBookingGlampingDomain.getCantPax() : 0)
                + (registerBookingGlampingDomain.getTc() != null ?  registerBookingGlampingDomain.getTc() : 0)
                + (registerBookingGlampingDomain.getTcLocal() != null ?  registerBookingGlampingDomain.getTcLocal() : 0)
        );

        if (supplierDomain.getConfirmation() != null && supplierDomain.getConfirmation()){
            registerBookingGlampingDomain.setConfirmation(true);
            registerBookingGlampingDomain.setValueConfirmation(supplierDomain.getValueConfirmation());
            registerBookingGlampingDomain.setDateConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueConfirmation()));
        }else{
            registerBookingGlampingDomain.setConfirmation(false);
            registerBookingGlampingDomain.setValueConfirmation(0);
        }

        if (supplierDomain.getReConfirmation() != null && supplierDomain.getReConfirmation()){
            registerBookingGlampingDomain.setReConfirmation(true);
            registerBookingGlampingDomain.setValueReConfirmation(supplierDomain.getValueReConfirmation());
            registerBookingGlampingDomain.setDateReConfirmation(registerGroupDomain.getInitTour().minusDays(supplierDomain.getValueReConfirmation()));
        }else{
            registerBookingGlampingDomain.setReConfirmation(false);
            registerBookingGlampingDomain.setValueReConfirmation(0);
        }
        Map<Integer, List<ItineraryItemDomain>> mapItemGlampingService = new HashMap<>();
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
                            if ( !mapItemGlampingService.containsKey( Integer.parseInt(itineraryDateDomain.getItemItinerary()) ) ){
                                mapItemGlampingService.put(Integer.parseInt(itineraryDateDomain.getItemItinerary()), new ArrayList<ItineraryItemDomain>());
                                mapItemGlampingService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }else{
                                mapItemGlampingService.get(Integer.parseInt(itineraryDateDomain.getItemItinerary())).add(itineraryItemDomain);
                            }
                        }
                    }
                }
            }
        }
        Map<Integer, List<ItineraryItemDomain>> mapItemGlampingServiceResult = new HashMap<>();
        Integer countNumberIngres = 1;
        Integer typeItemItineraryTemp = 0;
        for (Integer keyLodgingService: mapItemGlampingService.keySet()) {
            Integer typeItemItinerary = keyLodgingService;
            if ( typeItemItineraryTemp == 0 ){
                typeItemItineraryTemp = typeItemItinerary;
                if ( !mapItemGlampingServiceResult.containsKey( countNumberIngres ) ){
                    mapItemGlampingServiceResult.put( countNumberIngres, new ArrayList<>());
                    for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemGlampingService.get(typeItemItinerary)) {
                        mapItemGlampingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                    }
                }else{
                    for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemGlampingService.get(typeItemItinerary)) {
                        mapItemGlampingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                    }
                }
            }else{
                if (typeItemItineraryTemp + 1 == typeItemItinerary){
                    typeItemItineraryTemp = typeItemItinerary;
                    if ( !mapItemGlampingServiceResult.containsKey( countNumberIngres ) ){
                        mapItemGlampingServiceResult.put( countNumberIngres, new ArrayList<>());
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemGlampingService.get(typeItemItinerary)) {
                            mapItemGlampingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }else{
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemGlampingService.get(typeItemItinerary)) {
                            mapItemGlampingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }
                }else{
                    countNumberIngres = countNumberIngres + 1;
                    typeItemItineraryTemp = typeItemItinerary;
                    if ( !mapItemGlampingServiceResult.containsKey( countNumberIngres ) ){
                        mapItemGlampingServiceResult.put( countNumberIngres, new ArrayList<>());
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemGlampingService.get(typeItemItinerary)) {
                            mapItemGlampingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }else{
                        for (ItineraryItemDomain itineraryItemLodgingServiceDomain: mapItemGlampingService.get(typeItemItinerary)) {
                            mapItemGlampingServiceResult.get(countNumberIngres).add(itineraryItemLodgingServiceDomain);
                        }
                    }
                }
            }
        }
        registerBookingGlampingDomain.setNumberIngress(mapItemGlampingServiceResult.size());
        RegisterBookingGlampingDomain createdDomainRegisterBookingGlamping = repo.create(registerBookingGlampingDomain);
        for (Integer itemLodgingServiceType : mapItemGlampingServiceResult.keySet()) {
            List<ItineraryItemDomain> list = new ArrayList<ItineraryItemDomain>();
            list.addAll(mapItemGlampingServiceResult.get(itemLodgingServiceType));
            RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain = new RegisterBookingItemGlampingDomain();
            registerBookingItemGlampingDomain.setRegisterBookingGlamping(createdDomainRegisterBookingGlamping);
            registerBookingItemGlampingDomain.setRegisterGroup(registerGroupDomain);
            List<RegisterBookingItemLodgingFeedingDomain> registerBookingItemLodgingFeedingDomains = new ArrayList<>();
            registerBookingItemGlampingDomain.setNoCupon(persistCuponNumberCallback.generateNumber());
            registerBookingItemGlampingDomain.setNoNight(0);
            registerBookingItemGlampingDomain.setNoNightTcGuide(0);
            registerBookingItemGlampingDomain.setNoNightTcLocalGuide(0);
            registerBookingItemGlampingDomain.setCantBreakfastBuffet(0);
            registerBookingItemGlampingDomain.setCantLunch(0);
            registerBookingItemGlampingDomain.setCantDinner(0);
            registerBookingItemGlampingDomain.setCantTcGuideBreakfastBuffet(0);
            registerBookingItemGlampingDomain.setCantTcGuideLunch(0);
            registerBookingItemGlampingDomain.setCantTcGuideDinner(0);
            registerBookingItemGlampingDomain.setCantTcLocalGuideBreakfastBuffet(0);
            registerBookingItemGlampingDomain.setCantTcLocalGuideLunch(0);
            registerBookingItemGlampingDomain.setCantTcLocalGuideDinner(0);
            registerBookingItemGlampingDomain.setCantAb(0);
            registerBookingItemGlampingDomain.setCantCb(0);
            registerBookingItemGlampingDomain.setCantPaxAdult(0);
            registerBookingItemGlampingDomain.setCantPaxChildren(0);
            registerBookingItemGlampingDomain.setTc(0);
            registerBookingItemGlampingDomain.setTcLocal(0);
            for (ItineraryItemDomain itineraryItemDomain: list) {
                if ( itineraryItemDomain.getPaxTotal() != null ){
                    registerBookingItemGlampingDomain.setCantPaxAdult(itineraryItemDomain.getPaxTotal());
                }
                if ( itineraryItemDomain.getPaxChildrenTotal() != null ){
                    registerBookingItemGlampingDomain.setCantPaxChildren(itineraryItemDomain.getPaxChildrenTotal());
                }
                if ( itineraryItemDomain.getLodgingCantNight() != null ){
                    registerBookingItemGlampingDomain.setNoNight(registerBookingItemGlampingDomain.getNoNight() + itineraryItemDomain.getLodgingCantNight());
                }
                if ( itineraryItemDomain.getPaxTcTotal() != null && itineraryItemDomain.getPaxTcTotal().intValue() > 0 ){
                    registerBookingItemGlampingDomain.setNoNightTcGuide(registerBookingItemGlampingDomain.getNoNightTcGuide() + 1);
                }
                if ( itineraryItemDomain.getPaxTcLocalTotal() != null && itineraryItemDomain.getPaxTcLocalTotal().intValue() > 0 ){
                    registerBookingItemGlampingDomain.setNoNightTcLocalGuide(registerBookingItemGlampingDomain.getNoNightTcLocalGuide() + 1);
                }
                if ( itineraryItemDomain.getLodgingCantBreakfast() != null ){
                    registerBookingItemGlampingDomain.setCantBreakfastBuffet(registerBookingItemGlampingDomain.getCantBreakfastBuffet() + itineraryItemDomain.getLodgingCantBreakfast());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantBreakfast());
                    registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getItineraryDate().getItineraryDate());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.BREAKFAST_BUFFET.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantLunch() != null ){
                    registerBookingItemGlampingDomain.setCantLunch(registerBookingItemGlampingDomain.getCantLunch() + itineraryItemDomain.getLodgingCantLunch());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantLunch());
                    registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getItineraryDate().getItineraryDate());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.LUNCH.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantDinner() != null ){
                    registerBookingItemGlampingDomain.setCantDinner(registerBookingItemGlampingDomain.getCantDinner() + itineraryItemDomain.getLodgingCantDinner());
                    RegisterBookingItemLodgingFeedingDomain registerBookingItemLodgingFeedingDomain = new RegisterBookingItemLodgingFeedingDomain();
                    registerBookingItemLodgingFeedingDomain.setCant(itineraryItemDomain.getLodgingCantDinner());
                    registerBookingItemLodgingFeedingDomain.setDateFeeding(itineraryItemDomain.getItineraryDate().getItineraryDate());
                    registerBookingItemLodgingFeedingDomain.setTypeFeedingBooking(TypeFeedingBookingEnum.DINNER.getName());
                    registerBookingItemLodgingFeedingDomains.add(registerBookingItemLodgingFeedingDomain);
                }
                if ( itineraryItemDomain.getLodgingCantBreakfastTcGuide() != null ){
                    registerBookingItemGlampingDomain.setCantTcGuideBreakfastBuffet(registerBookingItemGlampingDomain.getCantTcGuideBreakfastBuffet() + itineraryItemDomain.getLodgingCantBreakfastTcGuide());
                }
                if ( itineraryItemDomain.getLodgingCantLunchTcGuide() != null ){
                    registerBookingItemGlampingDomain.setCantTcGuideLunch(registerBookingItemGlampingDomain.getCantTcGuideLunch() + itineraryItemDomain.getLodgingCantLunchTcGuide());
                }
                if ( itineraryItemDomain.getLodgingCantDinnerTcGuide() != null ){
                    registerBookingItemGlampingDomain.setCantTcGuideDinner(registerBookingItemGlampingDomain.getCantTcGuideDinner() + itineraryItemDomain.getLodgingCantDinnerTcGuide());
                }
                if ( itineraryItemDomain.getLodgingCantBreakfastTcLocalGuide() != null ){
                    registerBookingItemGlampingDomain.setCantTcLocalGuideBreakfastBuffet(registerBookingItemGlampingDomain.getCantTcLocalGuideBreakfastBuffet() + itineraryItemDomain.getLodgingCantBreakfastTcLocalGuide());
                }
                if ( itineraryItemDomain.getLodgingCantLunchTcLocalGuide() != null ){
                    registerBookingItemGlampingDomain.setCantTcLocalGuideLunch(registerBookingItemGlampingDomain.getCantTcLocalGuideLunch() + itineraryItemDomain.getLodgingCantLunchTcLocalGuide());
                }
                if ( itineraryItemDomain.getLodgingCantDinnerTcLocalGuide() != null ){
                    registerBookingItemGlampingDomain.setCantTcLocalGuideDinner(registerBookingItemGlampingDomain.getCantTcLocalGuideDinner() + itineraryItemDomain.getLodgingCantDinnerTcLocalGuide());
                }
                if ( itineraryItemDomain.getLodgingCategory() != null ){
                    registerBookingItemGlampingDomain.setLodgingCategory(itineraryItemDomain.getLodgingCategory());
                }
                if ( itineraryItemDomain.getLodgingCantSwb() != null ){
                    if ( itineraryItemDomain.getLodgingCantSwb() > registerBookingGlampingDomain.getCantSwb() ){
                        createdDomainRegisterBookingGlamping.setCantSwb(itineraryItemDomain.getLodgingCantSwb());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTrp() != null ){
                    if ( itineraryItemDomain.getLodgingCantTrp() > registerBookingGlampingDomain.getCantTrp() ){
                        createdDomainRegisterBookingGlamping.setCantTrp(itineraryItemDomain.getLodgingCantTrp());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTwin() != null ){
                    if ( itineraryItemDomain.getLodgingCantTwin() > registerBookingGlampingDomain.getCantTwin() ){
                        createdDomainRegisterBookingGlamping.setCantTwin(itineraryItemDomain.getLodgingCantTwin());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantMat() != null ){
                    if ( itineraryItemDomain.getLodgingCantMat() > registerBookingGlampingDomain.getCantMat() ){
                        createdDomainRegisterBookingGlamping.setCantMat(itineraryItemDomain.getLodgingCantMat());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantSwbTcGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantSwbTcGuide() > registerBookingGlampingDomain.getCantSwbTcGuide() ){
                        createdDomainRegisterBookingGlamping.setCantSwbTcGuide(itineraryItemDomain.getLodgingCantSwbTcGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTwinTcGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantTwinTcGuide() > registerBookingGlampingDomain.getCantTwinTcGuide() ){
                        createdDomainRegisterBookingGlamping.setCantTwinTcGuide(itineraryItemDomain.getLodgingCantTwinTcGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantSwbTcLocalGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantSwbTcLocalGuide() > registerBookingGlampingDomain.getCantSwbTcLocalGuide() ){
                        createdDomainRegisterBookingGlamping.setCantSwbTcLocalGuide(itineraryItemDomain.getLodgingCantSwbTcLocalGuide());
                    }
                }
                if ( itineraryItemDomain.getLodgingCantTwinTcLocalGuide() != null ){
                    if ( itineraryItemDomain.getLodgingCantTwinTcLocalGuide() > registerBookingGlampingDomain.getCantTwinTcLocalGuide() ){
                        createdDomainRegisterBookingGlamping.setCantTwinTcLocalGuide(itineraryItemDomain.getLodgingCantTwinTcLocalGuide());
                    }
                }
            }
            registerBookingItemGlampingDomain.setDateFrom(registerGroupDomain.getInitTour().plusDays(Integer.parseInt( list.get(0).getItineraryDate().getItemItinerary()) - 1));
            registerBookingItemGlampingDomain.setDateTo(registerBookingItemGlampingDomain.getDateFrom().plusDays(registerBookingItemGlampingDomain.getNoNight()));
            registerBookingItemGlampingDomain.setTenant(UserResolver.resolveUser().getTenant());
            RegisterBookingItemGlampingDomain createdDomainRegisterBookingItemGlamping = registerBookingItemGlampingRepo.create(registerBookingItemGlampingDomain);
            registerBookingItemGlampingDomains.add(createdDomainRegisterBookingItemGlamping);
            for (RegisterBookingItemLodgingFeedingDomain itemLodgingFeedingDomain: registerBookingItemLodgingFeedingDomains){
                itemLodgingFeedingDomain.setRegisterBookingItemGlamping(createdDomainRegisterBookingItemGlamping);
                itemLodgingFeedingDomain.setRegisterGroup(registerGroupDomain);
                itemLodgingFeedingDomain.setTenant(UserResolver.resolveUser().getTenant());
                registerBookingItemLodgingFeedingRepo.create(itemLodgingFeedingDomain);
            }
        }

        this.generateRoomBooking(TypeRoomBookingEnum.SWB.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantSwb());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantTwin());
        this.generateRoomBooking(TypeRoomBookingEnum.MAT.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantMat());
        this.generateRoomBooking(TypeRoomBookingEnum.TRP.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantTrp());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_TC.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantSwbTcGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_TC.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantTwinTcGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.SWB_TC_LOCAL.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantSwbTcLocalGuide());
        this.generateRoomBooking(TypeRoomBookingEnum.TWIN_TC_LOCAL.getName(), registerGroupDomain, createdDomainRegisterBookingGlamping, createdDomainRegisterBookingGlamping.getCantTwinTcLocalGuide());

        // Update models

        if ( registerBookingItemGlampingDomains.size() == 1 ){
            createdDomainRegisterBookingGlamping.setInitTour(registerBookingItemGlampingDomains.get(0).getDateFrom());
            createdDomainRegisterBookingGlamping.setEndTour(registerBookingItemGlampingDomains.get(0).getDateTo());
        }else if ( registerBookingItemGlampingDomains.size() > 1 ){
            Collections.sort(registerBookingItemGlampingDomains);
            Object [] arrayRegisterBookingItemGlampingDomains = registerBookingItemGlampingDomains.toArray();
            createdDomainRegisterBookingGlamping.setInitTour(((RegisterBookingItemGlampingDomain)arrayRegisterBookingItemGlampingDomains[0]).getDateFrom());
            createdDomainRegisterBookingGlamping.setEndTour(((RegisterBookingItemGlampingDomain)arrayRegisterBookingItemGlampingDomains[arrayRegisterBookingItemGlampingDomains.length-1]).getDateTo());
        }

        RegisterBookingGlampingDomain editedRegisterBookingGlampingDomain = repo.edit(createdDomainRegisterBookingGlamping);
        result = RegisterBookingGlampingResponse.from(editedRegisterBookingGlampingDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplier(idRegisterGroup, idSupplier);
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemDomain.setRegisterBookingBase(editedRegisterBookingGlampingDomain);
            itineraryItemRepo.edit(itineraryItemDomain);
        }

        return result;
    }

    private void generateRoomBooking(String typeRoomBooking, RegisterGroupDomain registerGroup, RegisterBookingGlampingDomain registerBooking, Integer cant){
        if (cant > 0){
            Stream.iterate(0, n -> n + 1)
                    .limit(cant)
                    .forEach(item -> createRoomBooking(typeRoomBooking, registerGroup, registerBooking, cant, item));
        }
    }

    private void createRoomBooking(String typeRoomBooking, RegisterGroupDomain registerGroup, RegisterBookingGlampingDomain registerBooking, Integer total, Integer current){
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
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.TWIN_TC.getName())){
            registerBookingRoomDomain.setName("HAB TWIN TC - " + (current + 1));
        }else if (typeRoomBooking.equals(TypeRoomBookingEnum.TWIN_TC_LOCAL.getName())){
            registerBookingRoomDomain.setName("HAB TWIN TC LOCAL - " + (current + 1));
        }
        registerBookingRoomDomain.setRegisterBookingGlamping(registerBooking);
        registerBookingRoomDomain.setRegisterGroup(registerGroup);
        registerBookingRoomDomain.setTenant(UserResolver.resolveUser().getTenant());
        registerBookingRoomRepo.create(registerBookingRoomDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegisterBookingItemGlampingResponse> findAllRegisterBookingItemGlampingByBooking(String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemGlampingUseCase.searchAllByBooking(idBooking);
    }

    @Override
    @Transactional
    public RegisterBookingItemGlampingResponse addRegisterBookingItemGlamping(RegisterBookingItemGlampingCreateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemGlampingUseCase.create(request, registerBookingGlampingDomain);
    }

    @Override
    @Transactional
    public RegisterBookingItemGlampingResponse editRegisterBookingItemGlamping(String id, RegisterBookingItemGlampingUpdateRequest request, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemGlampingUseCase.edit(id, request, registerBookingGlampingDomain);
    }

    @Override
    @Transactional
    public void deleteRegisterBookingItemGlamping(String id, String idBooking) {
        RegisterBookingGlampingResponse old = findById(idBooking);
        registerBookingItemGlampingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RegisterBookingItemGlampingResponse findByIdAndBooking(String id, String idBooking) {
        if (idBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idBooking));
        }
        return registerBookingItemGlampingUseCase.searchByBookingAndRegisterBookingItem(idBooking, id);
    }

    @Override
    @Transactional
    public PassengerBookingResponse addPassengerBooking(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idRegisterBooking);
        if (registerBookingGlampingDomain == null) {
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
        return passengerBookingUseCase.create(request, passengerGroupDomain, null, null, null, null, registerBookingGlampingDomain, null, registerBookingGlampingDomain.getRegisterGroup(), registerBookingRoomDomain);
    }

    @Override
    @Transactional
    public List<PassengerBookingResponse> addPassengerBookingMultiple(PassengerBookingCreateRequest request, String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idRegisterBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        RegisterBookingRoomDomain registerBookingRoomDomain = null;
        if (request.getRegisterBookingRoom() != null && !request.getRegisterBookingRoom().isEmpty()){
            registerBookingRoomDomain = registerBookingRoomRepo.findById(request.getRegisterBookingRoom());
        }
        return passengerBookingUseCase.createMultiple(request, null, null, null, null, registerBookingGlampingDomain, null, registerBookingGlampingDomain.getRegisterGroup(), registerBookingRoomDomain);
    }

    @Override
    @Transactional
    public void deletePassengerBooking(String id, String idRegisterBooking) {
        RegisterBookingGlampingResponse old = findById(idRegisterBooking);
        passengerBookingUseCase.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBooking(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idRegisterBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingGlampingAndRegisterGroup(idRegisterBooking, registerBookingGlampingDomain.getRegisterGroup().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerBookingResponse> findAllPassengerBookingByRegisterBookingGlampingAndTypeRoomBooking(String idRegisterBooking, String typeRoomBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idRegisterBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }
        return passengerBookingUseCase.searchAllByRegisterBookingGlampingAndRegisterGroupAndTypeRoomBooking(idRegisterBooking, registerBookingGlampingDomain.getRegisterGroup().getId(), typeRoomBooking);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingGlampingCuponPdf(String idRegisterBooking) {
        if (idRegisterBooking == null) {
            return null;
        }
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idRegisterBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
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

        List<RegisterBookingItemGlampingDomain> registerBookingItemGlampingDomains = registerBookingItemGlampingRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemGlampingDomains != null && !registerBookingItemGlampingDomains.isEmpty()) {
            registerBookingItemGlampingDomains = registerBookingItemGlampingDomains.stream().collect(Collectors.toList());
        }

        return jrRegisterBookingGlampingCuponRepository.export(registerBookingItemGlampingDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingGlampingItemCuponPdf(String idRegisterBookingItem) {
        if (idRegisterBookingItem == null) {
            return null;
        }
        RegisterBookingItemGlampingDomain registerBookingItemGlampingDomain = registerBookingItemGlampingRepo.findById(idRegisterBookingItem);
        if (registerBookingItemGlampingDomain == null) {
            throw new NotFoundException("No register booking item found with id '%s'.".formatted(idRegisterBookingItem));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
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

        List<RegisterBookingItemGlampingDomain> registerBookingItemGlampingDomains = new ArrayList<>();
        registerBookingItemGlampingDomains.add(registerBookingItemGlampingDomain);

        return jrRegisterBookingGlampingCuponRepository.export(registerBookingItemGlampingDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportRegisterBookingGlampingPdf(String idRegisterBooking) {
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
        RegisterBookingGlampingDomain registerBookingGlampingDomain = repo.findById(idRegisterBooking);
        if (registerBookingGlampingDomain == null) {
            throw new NotFoundException("No register booking found with id '%s'.".formatted(idRegisterBooking));
        }

        List<RegisterBookingGlampingDomain> list = new ArrayList<RegisterBookingGlampingDomain>();
        list.add(registerBookingGlampingDomain);

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

        List<RegisterBookingItemGlampingDomain> registerBookingItemGlampingDomains = registerBookingItemGlampingRepo.searchAllByBooking(idRegisterBooking);
        if (registerBookingItemGlampingDomains != null && !registerBookingItemGlampingDomains.isEmpty()) {
            registerBookingItemGlampingDomains = registerBookingItemGlampingDomains.stream().collect(Collectors.toList());
        }
        Collections.sort(registerBookingItemGlampingDomains);

        Integer sumRoom = 0;
        Integer sumRoomTcGuide = 0;
        Integer sumRoomTcLocalGuide = 0;

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
            if ( registerBookingGlampingDomain.getCantSwb() != null && registerBookingGlampingDomain.getCantSwb() > 0 && value.equals(1) ){
                parameters.put("strCantRoom"+current, "SWB");
                parameters.put("valueCantRoom"+current, registerBookingGlampingDomain.getCantSwb());

                sumRoom = sumRoom + registerBookingGlampingDomain.getCantSwb();
                current = current + 1;
            }else if ( registerBookingGlampingDomain.getCantTwin() != null && registerBookingGlampingDomain.getCantTwin() > 0 && value.equals(2) ){
                parameters.put("strCantRoom"+current, "TWIN");
                parameters.put("valueCantRoom"+current, registerBookingGlampingDomain.getCantTwin());

                sumRoom = sumRoom + registerBookingGlampingDomain.getCantTwin();
                current = current + 1;
            }else if ( registerBookingGlampingDomain.getCantMat() != null && registerBookingGlampingDomain.getCantMat() > 0 && value.equals(3) ){
                parameters.put("strCantRoom"+current, "MAT");
                parameters.put("valueCantRoom"+current, registerBookingGlampingDomain.getCantMat());

                sumRoom = sumRoom + registerBookingGlampingDomain.getCantMat();
                current = current + 1;
            }else if ( registerBookingGlampingDomain.getCantTrp() != null && registerBookingGlampingDomain.getCantTrp() > 0 && value.equals(4) ){
                parameters.put("strCantRoom"+current, "TRP");
                parameters.put("valueCantRoom"+current, registerBookingGlampingDomain.getCantTrp());

                sumRoom = sumRoom + registerBookingGlampingDomain.getCantTrp();
                current = current + 1;
            }else if ( registerBookingGlampingDomain.getCantCdp() != null && registerBookingGlampingDomain.getCantCdp() > 0 && value.equals(5) ){
                parameters.put("strCantRoom"+current, "CDP");
                parameters.put("valueCantRoom"+current, registerBookingGlampingDomain.getCantCdp());

                sumRoom = sumRoom + registerBookingGlampingDomain.getCantCdp();
                current = current + 1;
            }else if ( registerBookingGlampingDomain.getCantQtp() != null && registerBookingGlampingDomain.getCantQtp() > 0 && value.equals(6) ){
                parameters.put("strCantRoom"+current, "QTP");
                parameters.put("valueCantRoom"+current, registerBookingGlampingDomain.getCantQtp());

                sumRoom = sumRoom + registerBookingGlampingDomain.getCantQtp();
                current = current + 1;
            }else if ( registerBookingGlampingDomain.getCantSxtp() != null && registerBookingGlampingDomain.getCantSxtp() > 0 && value.equals(7)){
                parameters.put("strCantRoom"+current, "SXTP");
                parameters.put("valueCantRoom"+current, registerBookingGlampingDomain.getCantSxtp());

                sumRoom = sumRoom + registerBookingGlampingDomain.getCantSxtp();
            }
        }

        List<Integer> listStrRoomTcGuide = new ArrayList<Integer>();
        listStrRoomTcGuide.add(1);
        listStrRoomTcGuide.add(2);


        Integer currentTcGuide = 1;

        for (Integer value: listStrRoomTcGuide) {
            if ( registerBookingGlampingDomain.getCantSwbTcGuide() != null && registerBookingGlampingDomain.getCantSwbTcGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomTcGuide"+currentTcGuide, "SWB");
                parameters.put("valueCantRoomTcGuide"+currentTcGuide, registerBookingGlampingDomain.getCantSwbTcGuide());

                sumRoomTcGuide = sumRoomTcGuide + registerBookingGlampingDomain.getCantSwbTcGuide();
                currentTcGuide = currentTcGuide + 1;
            }else if ( registerBookingGlampingDomain.getCantTwinTcGuide() != null && registerBookingGlampingDomain.getCantTwinTcGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomTcGuide"+currentTcGuide, "TWIN");
                parameters.put("valueCantRoomTcGuide"+currentTcGuide, registerBookingGlampingDomain.getCantTwinTcGuide());

                sumRoomTcGuide = sumRoomTcGuide + registerBookingGlampingDomain.getCantTwinTcGuide();
                currentTcGuide = currentTcGuide + 1;
            }
        }

        List<Integer> listStrRoomTcLocalGuide = new ArrayList<Integer>();
        listStrRoomTcLocalGuide.add(1);
        listStrRoomTcLocalGuide.add(2);


        Integer currentTcLocalGuide = 1;

        for (Integer value: listStrRoomTcLocalGuide) {
            if ( registerBookingGlampingDomain.getCantSwbTcLocalGuide() != null && registerBookingGlampingDomain.getCantSwbTcLocalGuide() > 0 && value.equals(1) ){
                parameters.put("strCantRoomTcLocalGuide"+currentTcLocalGuide, "SWB");
                parameters.put("valueCantRoomTcLocalGuide"+currentTcLocalGuide, registerBookingGlampingDomain.getCantSwbTcLocalGuide());

                sumRoomTcLocalGuide = sumRoomTcLocalGuide + registerBookingGlampingDomain.getCantSwbTcLocalGuide();
                currentTcLocalGuide = currentTcLocalGuide + 1;
            }else if ( registerBookingGlampingDomain.getCantTwinTcLocalGuide() != null && registerBookingGlampingDomain.getCantTwinTcLocalGuide() > 0 && value.equals(2) ){
                parameters.put("strCantRoomTcLocalGuide"+currentTcLocalGuide, "TWIN");
                parameters.put("valueCantRoomTcLocalGuide"+currentTcLocalGuide, registerBookingGlampingDomain.getCantTwinTcLocalGuide());

                sumRoomTcLocalGuide = sumRoomTcLocalGuide + registerBookingGlampingDomain.getCantTwinTcLocalGuide();
                currentTcLocalGuide = currentTcLocalGuide + 1;
            }
        }

        parameters.put("sumRoom", sumRoom);
        parameters.put("sumRoomTcGuide", sumRoomTcGuide);
        parameters.put("sumRoomTcLocalGuide", sumRoomTcGuide);

        Map<RegisterBookingRoomDomain, List<PassengerBookingDomain>> mapPassengerRooms = new HashMap<>();
        Map<RegisterBookingRoomDomain, List<PassengerBookingDomain>> mapPassengerTcGuideRooms = new HashMap<>();
        Map<RegisterBookingRoomDomain, List<PassengerBookingDomain>> mapPassengerTcLocalGuideRooms = new HashMap<>();

        List<PassengerBookingDomain> passengerBookingItems = passengerBookingUseCase.searchAllByRegisterBookingLodgingAndRegisterGroupDomain(idRegisterBooking, registerBookingGlampingDomain.getRegisterGroup().getId());
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

        return jrRegisterBookingGlampingRepository.export(list, registerBookingItemGlampingDomains, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public RegisterBookingGlampingResponse changeState(String id, RegisterBookingChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingGlampingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking lodging found with id '%s'.".formatted(id));
        }
        old.setStateBooking(request.getStateBooking());
        RegisterBookingGlampingDomain editedDomain = repo.edit(old);
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
        return RegisterBookingGlampingResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public RegisterBookingGlampingResponse changeSupplier(String id, RegisterBookingChangeSupplierRequest request) {
        if (id == null) {
            return null;
        }
        RegisterBookingGlampingDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No register booking lodging found with id '%s'.".formatted(id));
        }
        SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);
        SupplierGlampingDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            supplierDomain = supplierGlampingRepo.findById(request.getSupplier());
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
                    if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())) {
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

        RegisterBookingGlampingDomain editedDomain = repo.edit(old);

        return RegisterBookingGlampingResponse.from(editedDomain);
    }

    private void _changeStateDraft(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRequest(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateConfirmed(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateReconfirmed(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateModified(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateAnnulled(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){
        registerBookingGlampingDomain.setActiveRegisterBooking(false);
        repo.edit(registerBookingGlampingDomain);

        List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByRegisterGroupAndSupplier(registerBookingGlampingDomain.getRegisterGroup().getId(), registerBookingGlampingDomain.getSupplier().getId());
        for (ItineraryItemDomain itineraryItemDomain: listItineraryItem) {
            itineraryItemDomain.setRegisterBookingBase(null);
            itineraryItemRepo.edit(itineraryItemDomain);
        }
    }

    private void _changeStateCancelled(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateBooking(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateToReschedule(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateRescheduled(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateFinished(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingMp(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingAbc(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedPendingBcd(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }

    private void _changeStateGeneratedConfirmPeruRail(RegisterBookingGlampingDomain registerBookingGlampingDomain, RegisterBookingChangeStateRequest request){

    }
}
