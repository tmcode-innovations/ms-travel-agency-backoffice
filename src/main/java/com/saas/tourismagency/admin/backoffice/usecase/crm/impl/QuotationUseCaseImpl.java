package com.saas.tourismagency.admin.backoffice.usecase.crm.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.crm.quotation.QuotationConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.crm.SetFieldDefaultQuotation;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.crm.TourEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.idiom.IdiomRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification.NotificationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtour.CostTourBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtouritem.CostTourItemBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.costtourserviceitem.CostTourServiceItemBookRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itinerarydate.ItineraryDateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingglamping.RegisterBookingGlampingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodger.RegisterBookingLodgerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookinglodging.RegisterBookingLodgingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingoperator.RegisterBookingOperatorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registerbookingrestaurant.RegisterBookingRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtour.CostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtouritem.CostTourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.costtourserviceitem.CostTourServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itinerarydate.ItineraryDateCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.itineraryitem.ItineraryItemCrmRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.passenger.PassengerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.program.ProgramRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.programcosttour.ProgramCostTourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.FileItineraryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.FileQuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.PersistQuotationNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.quotation.QuotationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.tour.TourRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.crm.touritem.TourItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.objectsecurity.ObjectRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtouritem.CostTourItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtourserviceitem.CostTourServiceItemUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.fileitinerary.FileItineraryResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.filequotation.FileQuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itinerarydate.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.passenger.PassengerUpdateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.tour.TourResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.crm.quotation.JRQuotationCostTypeGuruRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.crm.quotation.JRQuotationTypeGuruRepository;
import com.saas.tourismagency.admin.backoffice.adapter.notification.NotificationService;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.core.utils.FileStorageService;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.ContactInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.SocialMediaInformationEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationStatusEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.NotificationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateBookingEnum;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.QuotationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.crm.*;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.CostTourServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryDateDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.enums.StateQuotationEnum;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.ObjectDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.crm.*;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class QuotationUseCaseImpl implements QuotationUseCase {

    private final String UPLOAD_FOLDER_QUOTATION = "c:\\Temp";

    @Autowired
    private QuotationRepo repo;

    @Autowired
    private ProgramRepo programRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private IdiomRepo idiomRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ItineraryDateCrmRepo itineraryDateRepo;

    @Autowired
    private ItineraryItemCrmRepo itineraryItemRepo;

    @Autowired
    private FileQuotationRepo fileQuotationRepo;

    @Autowired
    private FileItineraryRepo fileItineraryRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private QuotationConverter converter;

    @Autowired
    private SetFieldDefaultQuotation setFieldDefault;

    @Autowired
    PersistQuotationNumberCallbackImpl persistQuotationNumberCallbackImpl;

    @Autowired
    private PassengerUseCaseImpl passengerUseCase;

    @Autowired
    private ItineraryDateCrmUseCase itineraryDateUseCase;

    @Autowired
    private ItineraryItemCrmUseCase itineraryItemUseCase;

    @Autowired
    private FileItineraryUseCase fileItineraryUseCase;

    @Autowired
    private FileQuotationUseCase fileQuotationUseCase;

    @Autowired
    private JRQuotationTypeGuruRepository jrQuotationTypeGuruRepository;

    @Autowired
    private JRQuotationCostTypeGuruRepository jrQuotationCostTypeGuruRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RegisterBookingLodgingRepo registerBookingLodgingRepo;

    @Autowired
    private RegisterBookingOperatorRepo registerBookingOperatorRepo;

    @Autowired
    private RegisterBookingRestaurantRepo registerBookingRestaurantRepo;

    @Autowired
    private RegisterBookingLodgerRepo registerBookingLodgerRepo;

    @Autowired
    private RegisterBookingGlampingRepo registerBookingGlampingRepo;

    @Autowired
    private ItineraryDateRepo itineraryDateFromBookingRepo;

    @Autowired
    private ItineraryItemRepo itineraryItemFromBookingRepo;

    @Autowired
    private CostTourRepo costTourRepo;

    @Autowired
    private CostTourItemRepo costTourItemRepo;

    @Autowired
    private CostTourServiceItemRepo costTourServiceItemRepo;

    @Autowired
    private ProgramCostTourRepo programCostTourRepo;

    @Autowired
    private CostTourUseCase costTourUseCase;

    @Autowired
    private CostTourItemUseCase costTourItemUseCase;

    @Autowired
    private CostTourServiceItemUseCase costTourServiceItemUseCase;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ObjectRepo objectRepo;

    @Autowired
    private TourRepo tourRepo;

    @Autowired
    private TourItemRepo tourItemRepo;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private CostTourBookingRepo costTourFromBookingRepo;

    @Autowired
    private CostTourItemBookingRepo costTourItemFromBookingRepo;

    @Autowired
    private CostTourServiceItemBookRepo costTourServiceItemFromBookingRepo;

    @Override
    @Transactional
    public QuotationResponse create(QuotationCreateRequest request) {
        QuotationDomain quotationDomainExist = repo.findByName(request.getName());
        if (quotationDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
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
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            IdBaseDomain employeeIdBaseDomain = employeeRepo.findByIdEntity(request.getEmployee());
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
        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            if (currencyIdBaseDomain != null){
                currencyDomain = new CurrencyDomain();
                currencyDomain.setId(currencyIdBaseDomain.getId());
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

        QuotationDomain newDomain = request.to(customerDomain, programDomain, employeeDomain, idiomDomain, currencyDomain, countryDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistQuotationNumberCallbackImpl.generateNumber(LocalDate.now().getYear(), "CT"));
        newDomain.setAmount(0.0);
        newDomain.setStateQuotation(StateQuotationEnum.DRAFT.getName());
        newDomain.setQuotationType(QuotationTypeEnum.TYPE_GURU_EXPLORER.getName());
        // newDomain.setDateEndTour(newDomain.getDateInitTour().plusDays(newDomain.getCantDayTour()-1));
        newDomain.setCantDayTour( (int)(newDomain.getDateEndTour().toEpochDay() - newDomain.getDateInitTour().toEpochDay()) + 1);
        QuotationDomain createdDomain = repo.create(newDomain);
        if (programDomain != null){
            itineraryDateUseCase.generateDateItineraryByProgram(createdDomain, programDomain, createdDomain.getDateInitTour());
        }else{
            itineraryDateUseCase.generateDateItinerary(createdDomain, createdDomain.getCantDayTour(), createdDomain.getDateInitTour());
        }

        PassengerDomain passengerPrincipal = new PassengerDomain();
        passengerPrincipal.setTenant(UserResolver.resolveUser().getTenant());
        passengerPrincipal.setPrincipal(true);
        passengerPrincipal.setQuotation(createdDomain);

        passengerPrincipal.setSocialMediaInformation(new SocialMediaInformationEmbeddedDomain());
        if (customerDomain.getPartner() != null && customerDomain.getPartner().getSocialMediaInformation() != null){
            passengerPrincipal.getSocialMediaInformation().setWhatsApp(customerDomain.getPartner().getSocialMediaInformation().getWhatsApp());
        }

        passengerPrincipal.setContactInformation(new ContactInformationEmbeddedDomain());
        if (customerDomain.getPartner() != null && customerDomain.getPartner().getContactInformation() != null){
            passengerPrincipal.getContactInformation().setEmail(customerDomain.getPartner().getContactInformation().getEmail());
            passengerPrincipal.getContactInformation().setMobile(customerDomain.getPartner().getContactInformation().getMobile());

        }

        PersonDomain newPersonDomain = new PersonDomain();
        newPersonDomain.setTenant(UserResolver.resolveUser().getTenant());
        newPersonDomain.setFirstName(customerDomain.getName());

        PersonDomain createdPersonDomain = personRepo.create(newPersonDomain);
        passengerPrincipal.setPerson(createdPersonDomain);

        passengerPrincipal.setIndex(1);

        passengerRepo.create(passengerPrincipal);

        return QuotationResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public QuotationResponse create_v0(MultipartFile[] multipartFile){
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        String urlFileQuotation = null;
        String fileNameQuotation = null;
        String extensionQuotation = null;
        String identifyQuotation = null;
        FileQuotationDomain fileQuotationDomain = null;

        String urlFileItinerary = null;
        String fileNameItinerary = null;
        String extensionItinerary = null;
        String identifyItinerary = null;
        FileItineraryDomain fileItineraryDomain = null;

        final MultipartFile[] multipartFileQuotation = {null};
        final MultipartFile[] multipartFileItinerary = {null};
        CustomerDomain customerOld = null;
        String nameQuotation = null;
        List<MultipartFile> list = Arrays.asList(multipartFile);
        list.forEach(multipartFileItem -> {
            String extension = getExtension(multipartFileItem.getOriginalFilename());
            if (extension.equals("xls") || extension.equals("xlsx")){
                multipartFileQuotation[0] = multipartFileItem;
            }else if (extension.equals("pdf") || extension.equals("doc") || extension.equals("docx")){
                multipartFileItinerary[0] = multipartFileItem;
            }
        });

        if (multipartFileQuotation[0] != null && !multipartFileQuotation[0].isEmpty()){
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(multipartFileQuotation[0].getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                Row rowCustomer = sheet.getRow(0);
                Row rowName = sheet.getRow(1);
                Cell cellCustomer = rowCustomer.getCell(0);
                Cell cellName = rowName.getCell(0);

                QuotationDomain quotationDomainExist = repo.findByName(cellName.getStringCellValue());
                if (quotationDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
                }

                customerOld = customerRepo.findByNameAndTypeInheritance(cellCustomer.getStringCellValue(), TypeInheritanceEnum.CUSTOMER.getName());
                if (customerOld == null) {
                    throw new ValidationException(Arrays.asList(new ValidationErrorMessage(
                            "quotation",
                            cellCustomer.getStringCellValue(),
                            MessagesValidation.VALIDATION_CUSTOMER_NOT_FOUND_QUOTATION_EXCEL)));
                }

                nameQuotation = cellName.getStringCellValue();

                // Creating the directory to store file
                String uploadFolderQuotation = "";

                if (companyDomain.getSetting() != null && companyDomain.getSetting().getUrlSaveDocumentQuotation() != null && !companyDomain.getSetting().getUrlSaveDocumentQuotation().isEmpty()){
                    uploadFolderQuotation = companyDomain.getSetting().getUrlSaveDocumentQuotation();
                }else{
                    uploadFolderQuotation = UPLOAD_FOLDER_QUOTATION;
                }

                FileStorageService.StoredFile storedQuotation = fileStorageService.store(multipartFileQuotation[0], uploadFolderQuotation);
                fileNameQuotation = storedQuotation.getFileName();
                extensionQuotation = storedQuotation.getExtension();
                identifyQuotation = storedQuotation.getIdentify();
                urlFileQuotation = storedQuotation.getPath();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (multipartFileItinerary[0] != null && !multipartFileItinerary[0].isEmpty()){
            // Creating the directory to store file
            String uploadFolderQuotation = "";

            if (companyDomain.getSetting() != null && companyDomain.getSetting().getUrlSaveDocumentQuotation() != null && !companyDomain.getSetting().getUrlSaveDocumentQuotation().isEmpty()){
                uploadFolderQuotation = companyDomain.getSetting().getUrlSaveDocumentQuotation();
            }else{
                uploadFolderQuotation = UPLOAD_FOLDER_QUOTATION;
            }

            FileStorageService.StoredFile storedItinerary = fileStorageService.store(multipartFileItinerary[0], uploadFolderQuotation);
            fileNameItinerary = storedItinerary.getFileName();
            extensionItinerary = storedItinerary.getExtension();
            identifyItinerary = storedItinerary.getIdentify();
            urlFileItinerary = storedItinerary.getPath();
        }

        if (multipartFileQuotation[0] != null && !multipartFileQuotation[0].isEmpty()){
            fileQuotationDomain = new FileQuotationDomain();
            fileQuotationDomain.setFileName(fileNameQuotation);
            fileQuotationDomain.setPath(urlFileQuotation);
            fileQuotationDomain.setExtension(extensionQuotation);
            fileQuotationDomain.setIdentify(identifyQuotation);
            fileQuotationDomain.setName(fileNameQuotation);
        }

        if (multipartFileItinerary[0] != null && !multipartFileItinerary[0].isEmpty()){
            fileItineraryDomain = new FileItineraryDomain();
            fileItineraryDomain.setFileName(fileNameItinerary);
            fileItineraryDomain.setPath(urlFileItinerary);
            fileItineraryDomain.setExtension(extensionItinerary);
            fileItineraryDomain.setIdentify(identifyItinerary);
            fileItineraryDomain.setName(fileNameItinerary);
        }

        QuotationDomain newDomain = new QuotationDomain();
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setCustomer(customerOld);
        newDomain.setName(nameQuotation);
        newDomain.setUrlFileQuotation(urlFileQuotation);
        newDomain.setUrlFileItinerary(urlFileItinerary);
        newDomain.setFileNameQuotation(fileNameQuotation);
        newDomain.setFileNameItinerary(fileNameItinerary);
        newDomain.setIdentify(persistQuotationNumberCallbackImpl.generateNumber(LocalDate.now().getYear(), "CT"));
        newDomain.setStateQuotation(StateQuotationEnum.DRAFT.getName());
        newDomain.setQuotationType(QuotationTypeEnum.TYPE_LEGENDARY.getName());

        UserDomain currentUser = UserResolver.resolveUser();
        if (currentUser != null && currentUser.getId() != null) {
            EmployeeUserDomain employeeUser = employeeUserRepo.getByUser(currentUser.getId());
            if (employeeUser != null && employeeUser.getEmployee() != null) {
                EmployeeDomain assignedEmployee = new EmployeeDomain();
                assignedEmployee.setId(employeeUser.getEmployee().getId());
                newDomain.setEmployee(assignedEmployee);
            }
        }

        QuotationDomain createdDomain = repo.create(newDomain);

        if (fileQuotationDomain != null){
            fileQuotationDomain.setQuotation(createdDomain);
            fileQuotationRepo.create(fileQuotationDomain);
        }
        if (fileItineraryDomain != null){
            fileItineraryDomain.setQuotation(createdDomain);
            fileItineraryRepo.create(fileItineraryDomain);
        }
        return QuotationResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public QuotationResponse edit(String id, QuotationUpdateRequest request) {
        if (id == null) {
            return null;
        }
        QuotationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        QuotationDomain quotationDomainExist = repo.findByName(request.getName());
        if (quotationDomainExist != null && !quotationDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
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
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            employeeDomain = employeeRepo.findById(request.getEmployee());
        }
        IdiomDomain idiomDomain = null;
        if (request.getIdiom() != null && !request.getIdiom().isEmpty()){
            idiomDomain = idiomRepo.findById(request.getIdiom());
        }
        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            currencyDomain = currencyRepo.findById(request.getCurrency());
        }
        CountryDomain countryDomain = null;
        if (request.getCountry() != null && !request.getCountry().isEmpty()){
            countryDomain = countryRepo.findById(request.getCountry());
        }
        QuotationDomain quotationDomain = request.to(customerDomain, programDomain, employeeDomain, idiomDomain, currencyDomain, countryDomain);
        quotationDomain.setIdentify(old.getIdentify());
        quotationDomain.setQuotationType(old.getQuotationType());
        quotationDomain.setStateQuotation(old.getStateQuotation());
        quotationDomain.setAmount(old.getAmount());
        quotationDomain.setResponsibleFile(old.getResponsibleFile());
        quotationDomain.setIdentifyFile(old.getIdentifyFile());
        quotationDomain.setEmailPassengerPrincipal(old.getEmailPassengerPrincipal());
        quotationDomain.setMobilePassengerPrincipal(old.getMobilePassengerPrincipal());
        quotationDomain.setWhatsappPassengerPrincipal(old.getWhatsappPassengerPrincipal());
        QuotationDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(quotationDomain, old));
        return QuotationResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public QuotationResponse edit_v0(String id, MultipartFile[] multipartFile) {
        if (id == null) {
            return null;
        }
        QuotationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        String urlFileQuotation = null;
        String fileNameQuotation = null;
        String extensionQuotation = null;
        String identifyQuotation = null;
        FileQuotationDomain fileQuotationDomain = null;

        String urlFileItinerary = null;
        String fileNameItinerary = null;
        String extensionItinerary = null;
        String identifyItinerary = null;
        FileItineraryDomain fileItineraryDomain = null;

        CustomerDomain customerOld = null;

        final MultipartFile[] multipartFileQuotation = {null};
        final MultipartFile[] multipartFileItinerary = {null};
        List<MultipartFile> list = Arrays.asList(multipartFile);
        list.forEach(multipartFileItem -> {
            String extension = getExtension(multipartFileItem.getOriginalFilename());
            if (extension.equals("xls") || extension.equals("xlsx")){
                multipartFileQuotation[0] = multipartFileItem;
            }else if (extension.equals("pdf") || extension.equals("doc") || extension.equals("docx")){
                multipartFileItinerary[0] = multipartFileItem;
            }
        });

        if (multipartFileQuotation[0] != null && !multipartFileQuotation[0].isEmpty()){
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(multipartFileQuotation[0].getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                Row rowCustomer = sheet.getRow(0);
                Row rowName = sheet.getRow(1);
                Cell cellCustomer = rowCustomer.getCell(0);
                Cell cellName = rowName.getCell(0);

                QuotationDomain quotationDomainExist = repo.findByName(cellName.getStringCellValue());
                if (quotationDomainExist != null && !quotationDomainExist.getId().equals(old.getId())){
                    throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
                }

                customerOld = customerRepo.findByNameAndTypeInheritance(cellCustomer.getStringCellValue(), TypeInheritanceEnum.CUSTOMER.getName());
                if (customerOld == null) {
                    throw new ValidationException(Arrays.asList(new ValidationErrorMessage(
                            "quotation",
                            cellCustomer.getStringCellValue(),
                            MessagesValidation.VALIDATION_CUSTOMER_NOT_FOUND_QUOTATION_EXCEL)));
                }
                if (!old.getCustomer().getId().equals(customerOld.getId())){
                    throw new ValidationException(Arrays.asList(new ValidationErrorMessage(
                            "quotation",
                            cellCustomer.getStringCellValue(),
                            MessagesValidation.VALIDATION_CUSTOMER_IS_NOT_EQUAL_REGISTER_QUOTATION_EXCEL)));
                }

                // Creating the directory to store file
                String uploadFolderQuotation = "";

                if (companyDomain.getSetting() != null && companyDomain.getSetting().getUrlSaveDocumentQuotation() != null && !companyDomain.getSetting().getUrlSaveDocumentQuotation().isEmpty()){
                    uploadFolderQuotation = companyDomain.getSetting().getUrlSaveDocumentQuotation();
                }else{
                    uploadFolderQuotation = UPLOAD_FOLDER_QUOTATION;
                }

                FileStorageService.StoredFile storedQuotation = fileStorageService.store(multipartFileQuotation[0], uploadFolderQuotation);
                fileNameQuotation = storedQuotation.getFileName();
                extensionQuotation = storedQuotation.getExtension();
                identifyQuotation = storedQuotation.getIdentify();
                urlFileQuotation = storedQuotation.getPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (multipartFileItinerary[0] != null && !multipartFileItinerary[0].isEmpty()){
            // Creating the directory to store file
            String uploadFolderQuotation = "";

            if (companyDomain.getSetting() != null && companyDomain.getSetting().getUrlSaveDocumentQuotation() != null && !companyDomain.getSetting().getUrlSaveDocumentQuotation().isEmpty()){
                uploadFolderQuotation = companyDomain.getSetting().getUrlSaveDocumentQuotation();
            }else{
                uploadFolderQuotation = UPLOAD_FOLDER_QUOTATION;
            }

            FileStorageService.StoredFile storedItinerary = fileStorageService.store(multipartFileItinerary[0], uploadFolderQuotation);
            fileNameItinerary = storedItinerary.getFileName();
            extensionItinerary = storedItinerary.getExtension();
            identifyItinerary = storedItinerary.getIdentify();
            urlFileItinerary = storedItinerary.getPath();
        }
        if (multipartFileQuotation[0] != null && !multipartFileQuotation[0].isEmpty()){
            fileQuotationDomain = new FileQuotationDomain();
            fileQuotationDomain.setFileName(fileNameQuotation);
            fileQuotationDomain.setPath(urlFileQuotation);
            fileQuotationDomain.setExtension(extensionQuotation);
            fileQuotationDomain.setIdentify(identifyQuotation);
            fileQuotationDomain.setName(fileNameQuotation);
        }

        if (multipartFileItinerary[0] != null && !multipartFileItinerary[0].isEmpty()){
            fileItineraryDomain = new FileItineraryDomain();
            fileItineraryDomain.setFileName(fileNameItinerary);
            fileItineraryDomain.setPath(urlFileItinerary);
            fileItineraryDomain.setExtension(extensionItinerary);
            fileItineraryDomain.setIdentify(identifyItinerary);
            fileItineraryDomain.setName(fileNameItinerary);
        }

        old.setUrlFileQuotation(urlFileQuotation);
        old.setUrlFileItinerary(urlFileItinerary);
        old.setFileNameQuotation(fileNameQuotation);
        old.setFileNameItinerary(fileNameItinerary);
        QuotationDomain editedDomain = repo.edit(old);

        if (fileQuotationDomain != null){
            fileQuotationDomain.setQuotation(editedDomain);
            fileQuotationRepo.create(fileQuotationDomain);
        }
        if (fileItineraryDomain != null){
            fileItineraryDomain.setQuotation(editedDomain);
            fileItineraryRepo.create(fileItineraryDomain);
        }

        return QuotationResponse.from(editedDomain);
    }

    private String getExtension(String filename){
        if (filename == null) {
            return null;
        }
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1) {
            return "";
        }
        return filename.substring(extensionIndex + 1);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        QuotationResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuotationResponse> findAll() {
        return repo.findAll(false).stream().map(quotationDomain -> QuotationResponse.from(quotationDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationResponse findById(String id) {
        if (id == null) {
            return null;
        }
        QuotationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(id));
        }
        return QuotationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationSearchResponse searchAll(QuotationSearchRequest quotationSearchRequest) {
       PageDTO<QuotationDomain> pageDTO = repo.searchAll(quotationSearchRequest);
       QuotationSearchResponse quotationSearchRespose = new QuotationSearchResponse();
       quotationSearchRespose.setQuotations(pageDTO.getContent().stream().map(quotationDomain ->  QuotationResponse.from(quotationDomain)).toList());
       quotationSearchRespose.setTotalElements(pageDTO.getTotalElements());
       quotationSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return quotationSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationSearchResponse searchFilterAll(QuotationSearchFilterRequest quotationSearchFilterRequest) {
        PageDTO<QuotationDomain> pageDTO = repo.searchAllFilter(quotationSearchFilterRequest);
        QuotationSearchResponse quotationSearchRespose = new QuotationSearchResponse();
        quotationSearchRespose.setQuotations(pageDTO.getContent().stream().map(quotationDomain ->  QuotationResponse.from(quotationDomain)).toList());
        quotationSearchRespose.setTotalElements(pageDTO.getTotalElements());
        quotationSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return quotationSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        QuotationDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No quotation found with name '%s'.".formatted(name));
        }
        return QuotationResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        QuotationDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No quotation found with identify '%s'.".formatted(identify));
        }
        return QuotationResponse.from(old);
    }

    @Override
    @Transactional
    public PassengerResponse addPassenger(PassengerCreateRequest request, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        PassengerResponse result = passengerUseCase.create(request, quotationDomain);
        if (request.getPrincipal()){
            quotationDomain.setMobilePassengerPrincipal(request.getContactInformation().getMobile());
            quotationDomain.setEmailPassengerPrincipal(request.getContactInformation().getEmail());
            quotationDomain.setWhatsappPassengerPrincipal(request.getSocialMediaInformation().getWhatsApp());
            repo.edit(quotationDomain);
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return result;
    }

    @Override
    @Transactional
    public PassengerResponse editPassenger(String id, PassengerUpdateRequest request, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        PassengerResponse result = passengerUseCase.edit(id, request, quotationDomain);
        if (request.getPrincipal()){
            quotationDomain.setMobilePassengerPrincipal(request.getContactInformation().getMobile());
            quotationDomain.setEmailPassengerPrincipal(request.getContactInformation().getEmail());
            quotationDomain.setWhatsappPassengerPrincipal(request.getSocialMediaInformation().getWhatsApp());
            repo.edit(quotationDomain);
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return result;
    }

    @Override
    @Transactional
    public void deletePassenger(String id, String idQuotation) {
        QuotationResponse old = findById(idQuotation);
        passengerUseCase.deleteById(id);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public List<PassengerResponse> findAllPassengerByQuotation(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return passengerUseCase.searchAllByQuotation(idQuotation);
    }

    @Override
    @Transactional
    public PassengerResponse findByIdAndQuotation(String id, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return passengerUseCase.searchByQuotationAndPassenger(idQuotation, id);
    }

    @Override
    @Transactional
    public List<ItineraryItemResponse> findAllItineraryItemByQuotation(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return itineraryItemUseCase.searchAllByQuotation(idQuotation);
    }

    @Override
    @Transactional
    public List<ItineraryDateResponse> findAllItineraryDateByQuotation(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return itineraryDateUseCase.searchAllByQuotation(idQuotation);
    }

    @Override
    @Transactional
    public ItineraryItemResponse addItinerary(ItineraryItemCreateRequest request, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(quotationDomain.getIdentifyFile());

            SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
            SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
            SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

            if (request.getSupplierType().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }else if (request.getSupplierType().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }
        }

        ItineraryItemResponse resultCreate = itineraryItemUseCase.create(request, quotationDomain);

        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByQuotationSimpleDomainCaseGetCalculateCost(idQuotation);
        Double totalPrice = 0.0;
        if (list != null && !list.isEmpty()){
            for (ItineraryItemDomain item: list
            ) {
                if (item.getService().getWithoutCost() != null && !item.getService().getWithoutCost()){
                    totalPrice = totalPrice + (
                            ((item.getPriceAdult() != null && item.getPaxAdultTotal() != null) ? item.getPriceAdult() * item.getPaxAdultTotal() : 0)
                                    + ((item.getPriceChildren() != null && item.getPaxChildrenTotal() != null) ? item.getPriceChildren() * item.getPaxChildrenTotal() : 0)
                                    + ((item.getPriceTc() != null && item.getPaxTcTotal() != null) ? item.getPriceTc() * item.getPaxTcTotal() : 0)
                                    + ((item.getPriceTcLocal() != null && item.getPaxTcLocalTotal() != null) ? item.getPriceTcLocal() * item.getPaxTcLocalTotal() : 0)
                                    + ((item.getPriceGuide() != null && item.getPaxGuideTotal() != null) ? item.getPriceGuide() * item.getPaxGuideTotal() : 0)
                    );
                }
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, totalPrice);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return resultCreate;
    }

    @Override
    @Transactional
    public void addItineraryMultiple(ItineraryItemCreateRequest request, String idQuotation) {
        QuotationDomain quotationDomain = repo.findByIdQuotationCaseGeneric(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(quotationDomain.getIdentifyFile());

            SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
            SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
            SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

            if (request.getSupplierType().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }else if (request.getSupplierType().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }
        }

        itineraryItemUseCase.createMultiple(request, quotationDomain);

        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByQuotationSimpleDomainCaseGetCalculateCost(idQuotation);
        Double totalPrice = 0.0;
        if (list != null && !list.isEmpty()){
            for (ItineraryItemDomain item: list
            ) {
                if (item.getService().getWithoutCost() != null && !item.getService().getWithoutCost()){
                    totalPrice = totalPrice + (
                            ((item.getPriceAdult() != null && item.getPaxAdultTotal() != null) ? item.getPriceAdult() * item.getPaxAdultTotal() : 0)
                                    + ((item.getPriceChildren() != null && item.getPaxChildrenTotal() != null) ? item.getPriceChildren() * item.getPaxChildrenTotal() : 0)
                                    + ((item.getPriceTc() != null && item.getPaxTcTotal() != null) ? item.getPriceTc() * item.getPaxTcTotal() : 0)
                                    + ((item.getPriceTcLocal() != null && item.getPaxTcLocalTotal() != null) ? item.getPriceTcLocal() * item.getPaxTcLocalTotal() : 0)
                                    + ((item.getPriceGuide() != null && item.getPaxGuideTotal() != null) ? item.getPriceGuide() * item.getPaxGuideTotal() : 0)
                            );
                }
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, totalPrice);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public void addItineraryAdvanced(ItineraryServiceAdvancedCreateRequest request, String idQuotation) {
        QuotationDomain quotationDomain = repo.findByIdQuotationCaseGeneric(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        itineraryItemUseCase.createAdvanced(request, quotationDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public void addItineraryWithTour(ItineraryTourItemCreateRequest request, String idQuotation) {
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        itineraryItemUseCase.createTour(request, quotationDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public void addItineraryWithTourServiceAdditional(ItineraryCostTourServiceItemCreateRequest request, String idQuotation) {
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        itineraryItemUseCase.createTourServiceAdditional(request, quotationDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public ItineraryItemResponse editItinerary(String id, ItineraryItemUpdateRequest request, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(quotationDomain.getIdentifyFile());

            SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
            SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
            SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

            if (request.getSupplierType().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (request.getSupplierType().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }else if (request.getSupplierType().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), request.getSupplier());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }
        }

        ItineraryItemResponse resultEdit = itineraryItemUseCase.edit(id, request, quotationDomain);

        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByQuotationSimpleDomainCaseGetCalculateCost(idQuotation);
        Double totalPrice = 0.0;
        if (list != null && !list.isEmpty()){
            for (ItineraryItemDomain item: list
            ) {
                totalPrice = totalPrice + (
                        ((item.getPriceAdult() != null && item.getPaxAdultTotal() != null) ? item.getPriceAdult() * item.getPaxAdultTotal() : 0)
                                + ((item.getPriceChildren() != null && item.getPaxChildrenTotal() != null) ? item.getPriceChildren() * item.getPaxChildrenTotal() : 0)
                                + ((item.getPriceTc() != null && item.getPaxTcTotal() != null) ? item.getPriceTc() * item.getPaxTcTotal() : 0)
                                + ((item.getPriceTcLocal() != null && item.getPaxTcLocalTotal() != null) ? item.getPriceTcLocal() * item.getPaxTcLocalTotal() : 0)
                                + ((item.getPriceGuide() != null && item.getPaxGuideTotal() != null) ? item.getPriceGuide() * item.getPaxGuideTotal() : 0)
                        );
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, totalPrice);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return resultEdit;
    }

    @Override
    @Transactional
    public void deleteItinerary(String id, String idQuotation) {
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        ItineraryItemDomain itineraryItemDomain = itineraryItemRepo.findById(id);
        if (itineraryItemDomain == null) {
            throw new NotFoundException("No itinerary item found with id '%s'.".formatted(id));
        }
        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(quotationDomain.getIdentifyFile());

            SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
            SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
            SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

            if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itineraryItemDomain.getSupplier().getId());
                if (registerBookingLodgingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itineraryItemDomain.getSupplier().getId());
                if (registerBookingOperatorDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itineraryItemDomain.getSupplier().getId());
                if (registerBookingRestaurantDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            } else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itineraryItemDomain.getSupplier().getId());
                if (registerBookingLodgerDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }else if (itineraryItemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itineraryItemDomain.getSupplier().getId());
                if (registerBookingGlampingDomainExist != null){
                    throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE);
                }
            }
        }

        itineraryItemUseCase.deleteById(id);

        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByQuotationSimpleDomainCaseGetCalculateCost(idQuotation);
        Double totalPrice = 0.0;
        if (list != null && !list.isEmpty()){
            for (ItineraryItemDomain item: list
            ) {
                totalPrice = totalPrice + (
                        ((item.getPriceAdult() != null && item.getPaxAdultTotal() != null) ? item.getPriceAdult() * item.getPaxAdultTotal() : 0)
                                + ((item.getPriceChildren() != null && item.getPaxChildrenTotal() != null) ? item.getPriceChildren() * item.getPaxChildrenTotal() : 0)
                                + ((item.getPriceTc() != null && item.getPaxTcTotal() != null) ? item.getPriceTc() * item.getPaxTcTotal() : 0)
                                + ((item.getPriceTcLocal() != null && item.getPaxTcLocalTotal() != null) ? item.getPriceTcLocal() * item.getPaxTcLocalTotal() : 0)
                                + ((item.getPriceGuide() != null && item.getPaxGuideTotal() != null) ? item.getPriceGuide() * item.getPaxGuideTotal() : 0)
                );
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, totalPrice);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryItemResponse findByItineraryItemIdAndQuotation(String id, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return itineraryItemUseCase.searchByQuotationAndItineraryItem(idQuotation, id);
    }

    @Override
    @Transactional(readOnly = true)
    public ItineraryDateResponse findByItineraryDateIdAndQuotation(String id, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return itineraryDateUseCase.searchByQuotationAndItineraryDate(idQuotation, id);
    }

    @Override
    @Transactional
    public List<ItineraryDateAllItemResponse> findAllItineraryDateWithItineraryItemByQuotation(String idQuotation) {
        List<ItineraryDateAllItemResponse> result = new ArrayList<>();
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseShowItinerary(idQuotation);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
            ) {
                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseShowItinerary(itineraryDateDomain.getId(), idQuotation);
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
    public List<ItineraryModelViewResponse> findAllItineraryDateWithItineraryItemByQuotationModelView(String idQuotation) {
        List<ItineraryDateAllItemResponse> list = this.findAllItineraryDateWithItineraryItemByQuotation(idQuotation);
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
                                itineraryItemResponse.getPrice(),
                                itineraryItemResponse.getPaxAdultTotal(),
                                itineraryItemResponse.getPriceAdult(),
                                itineraryItemResponse.getPaxChildrenTotal(),
                                itineraryItemResponse.getPriceChildren(),
                                itineraryItemResponse.getPaxTcTotal(),
                                itineraryItemResponse.getPriceTc(),
                                itineraryItemResponse.getPaxTcLocalTotal(),
                                itineraryItemResponse.getPriceTcLocal(),
                                itineraryItemResponse.getPaxGuideTotal(),
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
    public QuotationResponse changeState(String id, QuotationChangeStateRequest request) {
        if (id == null) {
            return null;
        }
        QuotationDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(id));
        }
        switch (request.getStateQuotation()) {
            case "Cancel" -> {
                _changeStateCancelPre(old, request);
            }
            case "Completed" -> {
                _changeStateCompletedPre(old, request);
            }
            case "Accepted" -> {
                _changeStateAcceptedPre(old, request);
            }
            case "In Booking" -> {
                _changeStateInBookingPre(old, request);
            }
            case "Finished" -> {
                _changeStateFinishedPre(old, request);
            }
            case "Sent" -> {
                _changeStateSentPre(old, request);
            }
            case "Rejected" -> {
                _changeStateRejectedPre(old, request);
            }
            case "Review" -> {
                _changeStateReviewPre(old, request);
            }
            default -> {

            }
        }

        old.setStateQuotation(request.getStateQuotation());
        QuotationDomain editedDomain = repo.edit(old);
        switch (request.getStateQuotation()) {
            case "Cancel" -> {
                _changeStateCancelPost(old, request);
            }
            case "Completed" -> {
                _changeStateCompletedPost(old, request);
            }
            case "Accepted" -> {
                _changeStateAcceptedPost(old, request);
            }
            case "In Booking" -> {
                _changeStateInBookingPost(old, request);
            }
            case "Finished" -> {
                _changeStateFinishedPost(old, request);
            }
            case "Sent" -> {
                _changeStateSentPost(old, request);
            }
            case "Rejected" -> {
                _changeStateRejectedPost(old, request);
            }
            case "Review" -> {
                _changeStateReviewPost(old, request);
            }
            default -> {

                    }
        }
        repo.updateIdSimpleDomainCaseUserDataModification(id);

        return QuotationResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ItineraryDateResponse editItineraryDate(String id, ItineraryDateUpdateOnlyNameRequest request, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        ItineraryDateDomain itineraryDateDomain = itineraryDateUseCase.findByIdDomain(id);

        ItineraryDateUpdateRequest itineraryDateUpdateRequest = new ItineraryDateUpdateRequest();
        itineraryDateUpdateRequest.setId(request.getId());
        itineraryDateUpdateRequest.setName(request.getName());
        itineraryDateUpdateRequest.setDescription(itineraryDateDomain.getDescription());
        itineraryDateUpdateRequest.setIdentify(itineraryDateDomain.getIdentify());
        itineraryDateUpdateRequest.setItineraryDate(itineraryDateDomain.getItineraryDate());
        itineraryDateUpdateRequest.setQuotation(request.getQuotation());

        ItineraryDateResponse resultEdit = itineraryDateUseCase.edit(id, itineraryDateUpdateRequest);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return resultEdit;
    }

    @Override
    @Transactional
    public void deleteItineraryDate(String id, String idQuotation) {
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(quotationDomain.getIdentifyFile());

            ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.findById(id);
            if (itineraryDateDomain == null) {
                throw new NotFoundException("No itinerary date found with id '%s'.".formatted(id));
            }

            com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain itineraryDateDomainBooking = itineraryDateFromBookingRepo.findByItemItinerarySimpleDomainCaseGeneric(itineraryDateDomain.getItemItinerary());

            SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
            SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
            SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

            List<com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain> listValidation = itineraryItemFromBookingRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseValidationExistBooking(itineraryDateDomainBooking.getId(), registerGroupDomain.getId());
            listValidation = listValidation.stream().collect(Collectors.toList());
            for(com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain itemDomain : listValidation){
                if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                    RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingLodgingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                    RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingOperatorDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                    RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingRestaurantDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                    RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingLodgerDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                }else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                    RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingGlampingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                }
            }

        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationAndItineraryDateSimpleDomainCaseDelete(idQuotation, id);
            if (listCostTour != null && !listCostTour.isEmpty()){
                throw new BadRequestException(MessagesValidation.VALIDATION_TOUR_EXIST_IN_DAY);
            }
        }

        Integer indexCurrent = 0;
        Integer indexCurrentPass = 0;

        List<ItineraryDateDomain> list = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseUpdateOrderItinerary(idQuotation).stream().collect(Collectors.toList());
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
                    this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, itineraryDateEditDb));
                }*/

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idQuotation);
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
                        itemDomain.setItineraryDateTime(localDateTime);

                        itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                            this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
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
                this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, itineraryDateEditDb));
            }*/

            List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(itineraryIndexSource.getId(), idQuotation);
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
                    itemDomain.setItineraryDateTime(localDateTime);

                    itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                        this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
                    }*/
                }
            }
        }

        itineraryDateUseCase.deleteById(id);

        repo.updateIdSimpleDomainCaseDeleteItineraryDate(idQuotation, quotationDomain.getDateInitTour(), quotationDomain.getDateEndTour().minusDays(1), quotationDomain.getCantDayTour() - 1);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public ByteArrayResource downloadDocumentQuotation(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain old = repo.findById(idQuotation);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(idQuotation));
        }
        ByteArrayResource byteArrayResource = null;
        String dataUrlFile = old.getUrlFileQuotation();
        byte[] fileBytes = fileStorageService.read(dataUrlFile);
        if (fileBytes != null){
            byteArrayResource = new ByteArrayResource(fileBytes);
        }
        return byteArrayResource;
    }

    @Override
    @Transactional
    public ByteArrayResource downloadDocumentItinerary(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain old = repo.findById(idQuotation);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(idQuotation));
        }
        ByteArrayResource byteArrayResource = null;
        String dataUrlFile = old.getUrlFileItinerary();
        byte[] fileBytes = fileStorageService.read(dataUrlFile);
        if (fileBytes != null){
            byteArrayResource = new ByteArrayResource(fileBytes);
        }
        return byteArrayResource;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileItineraryResponse> findAllFileItineraryByQuotation(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return fileItineraryUseCase.searchAllByQuotation(idQuotation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileQuotationResponse> findAllFileQuotationByQuotation(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return fileQuotationUseCase.searchAllByQuotation(idQuotation);
    }

    @Override
    @Transactional
    public ByteArrayResource downloadDocumentQuotationByFileItinerary(String idQuotation, String idFileItinerary) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain old = repo.findById(idQuotation);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(idQuotation));
        }
        return fileItineraryUseCase.downloadDocument(idFileItinerary);
    }

    @Override
    @Transactional
    public ByteArrayResource downloadDocumentQuotationByFileQuotation(String idQuotation, String idFileQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain old = repo.findById(idQuotation);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(idQuotation));
        }
        return fileQuotationUseCase.downloadDocument(idFileQuotation);
    }

    @Override
    @Transactional
    public byte[] exportQuotationTypeGuruPdf(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        List<QuotationDomain> listReport = new ArrayList<>();
        listReport.add(quotationDomain);

        /*List<ItineraryItemDomain> listItineraryItem = itineraryItemUseCase.searchAllDomainByQuotation(idQuotation);
        List<ItineraryItemDomain> listSubReport = new ArrayList<>();
        if (listItineraryItem != null && !listItineraryItem.isEmpty()){
            listSubReport = listItineraryItem.stream().collect(Collectors.toList());
            Collections.sort(listSubReport);
        }*/
        List<CostTourModelView> listCostTourModelView = new ArrayList<>();
        List<CostTourModelView> listCostTourModelViewLodging = new ArrayList<>();
        Map<String, CostTourItemModelView> mapCostTourModelViewGroup = new HashMap<>();
        List<CostTourItemModelView> listCostTourModelViewGroupPassengerType = new ArrayList<>();

        SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);

        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        List<CostTourDomain> listSubReport = new ArrayList<>();
        if (listCostTour != null && !listCostTour.isEmpty()){
            listSubReport = listCostTour.stream().collect(Collectors.toList());
            Collections.sort(listSubReport);
            for (CostTourDomain costTourDomain : listSubReport){
                List<CostTourModelView> listCostTourModelViewServiceAdditional = new ArrayList<>();
                CostTourModelView costTourModelView = new CostTourModelView();
                costTourModelView.setId(costTourDomain.getId());
                costTourModelView.setName(costTourDomain.getTour().getName());
                costTourModelView.setTour(costTourDomain.getTour());
                costTourModelView.setItineraryDate(costTourDomain.getItineraryDate());
                costTourModelView.setItems(new ArrayList<>());
                // costTourModelView.setAdditionalItems(new ArrayList<>());
                List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, costTourDomain.getId());
                listCostTourItem = listCostTourItem.stream().collect(Collectors.toList());

                Comparator<CostTourItemDomain> itemComparator = Comparator.nullsFirst(
                        Comparator.comparing(
                                CostTourItemDomain::getCostTourServiceItem,
                                Comparator.nullsFirst(
                                        Comparator.comparing(
                                                CostTourServiceItemDomain::getId,
                                                Comparator.nullsFirst(Comparator.naturalOrder())
                                        )
                                )
                        )
                );

                listCostTourItem.sort(itemComparator);

                List<TourItemDomain> listTourItem = tourItemRepo.searchAllByTour(costTourDomain.getTour().getId());
                listTourItem = listTourItem.stream().collect(Collectors.toList());
                TourItemDomain tourItemDomain = listTourItem.getFirst();

                if (listCostTourItem != null && !listCostTourItem.isEmpty()){
                    for (CostTourItemDomain costTourItemDomain : listCostTourItem){
                        if (costTourItemDomain.getCostTourServiceItem() != null && costTourItemDomain.getCostTourServiceItem().getService() != null){
                            // costTourItemModelView.setService(costTourItemDomain.getCostTourServiceItem().getService().getName());
                            // costTourModelView.getAdditionalItems().add(costTourItemModelView);
                            CostTourModelView costTourModelViewAdditionalService = new CostTourModelView();
                            costTourModelViewAdditionalService.setId(costTourDomain.getId());
                            costTourModelViewAdditionalService.setName(costTourItemDomain.getCostTourServiceItem().getService().getName());
                            costTourModelViewAdditionalService.setTour(costTourDomain.getTour());
                            costTourModelViewAdditionalService.setItineraryDate(costTourDomain.getItineraryDate());
                            costTourModelViewAdditionalService.setItems(new ArrayList<>());

                            CostTourItemModelView costTourItemModelViewAdditionalService = new CostTourItemModelView();
                            costTourItemModelViewAdditionalService.setId(costTourItemDomain.getId());
                            costTourItemModelViewAdditionalService.setPassengerType(costTourItemDomain.getPassengerType().getName());
                            costTourItemModelViewAdditionalService.setAmount(costTourItemDomain.getAmount());
                            costTourItemModelViewAdditionalService.setCurrency(quotationDomain.getCurrency().getAbbrev());
                            costTourItemModelViewAdditionalService.setCantPax(costTourItemDomain.getCantPax());
                            costTourItemModelViewAdditionalService.setPricePax(costTourItemDomain.getPricePax());
                            costTourModelViewAdditionalService.getItems().add(costTourItemModelViewAdditionalService);

                            listCostTourModelViewServiceAdditional.add(costTourModelViewAdditionalService);

                        }else{
                            CostTourItemModelView costTourItemModelView = new CostTourItemModelView();
                            costTourItemModelView.setId(costTourItemDomain.getId());
                            costTourItemModelView.setPassengerType(costTourItemDomain.getPassengerType().getName());
                            costTourItemModelView.setAmount(costTourItemDomain.getAmount());
                            costTourItemModelView.setCurrency(quotationDomain.getCurrency().getAbbrev());
                            costTourItemModelView.setCantPax(costTourItemDomain.getCantPax());
                            costTourItemModelView.setPricePax(costTourItemDomain.getPricePax());
                            costTourModelView.getItems().add(costTourItemModelView);
                        }
                        if (!mapCostTourModelViewGroup.containsKey(costTourItemDomain.getPassengerType().getId())){
                            mapCostTourModelViewGroup.put(costTourItemDomain.getPassengerType().getId(), new CostTourItemModelView());
                            mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).setPassengerType(costTourItemDomain.getPassengerType().getName());
                            // mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).setPricePax(costTourItemDomain.getPricePax());
                            mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).setCantPax(costTourItemDomain.getCantPax());
                            mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).setAmount(costTourItemDomain.getAmount());
                        }else{
                            if (mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).getCantPax().intValue() < costTourItemDomain.getCantPax().intValue()){
                                mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).setCantPax(costTourItemDomain.getCantPax());
                            }
                            mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).setAmount( mapCostTourModelViewGroup.get(costTourItemDomain.getPassengerType().getId()).getAmount() + costTourItemDomain.getAmount() );
                        }
                    }
                }
                if (!tourItemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                    listCostTourModelView.add(costTourModelView);
                    listCostTourModelView.addAll(listCostTourModelViewServiceAdditional);
                }else{
                    listCostTourModelViewLodging.add(costTourModelView);
                }
            }
        }

        if (mapCostTourModelViewGroup != null && !mapCostTourModelViewGroup.isEmpty()){
            for (String keyCostTourItemModelView : mapCostTourModelViewGroup.keySet()){
                listCostTourModelViewGroupPassengerType.add(mapCostTourModelViewGroup.get(keyCostTourItemModelView));
            }
        }

        Map<String, Object> parameters = new HashMap<>();
        // UserDomain userDomain = UserResolver.resolveUser();
        // EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByEmployee(quotationDomain.getEmployee().getId());
        /*if (employeeUserDomain != null){
            parameters.put("activeEmployee", employeeUserDomain.getEmployee());

            ByteArrayResource byteArrayResource = null;
            String dataUrlFile = employeeUserDomain.getEmployee().getUrlFileImageSign();
            Path file = Paths.get(dataUrlFile);
            if ( Files.exists(file) ){
                try {
                    byte[] byteArray = Files.readAllBytes(file);
                    byteArrayResource = new ByteArrayResource(byteArray);
                    parameters.put("sign", byteArrayResource.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            throw new BadRequestException(MessagesValidation.VALIDATION_EMPLOYEE_USER_NOT_ASSOCIATE);
        }*/

        if (quotationDomain.getEmployee() != null){
            parameters.put("activeEmployee", quotationDomain.getEmployee());
            ByteArrayResource byteArrayResource = null;
            String dataUrlFile = quotationDomain.getEmployee().getUrlFileImageSign();
            if (dataUrlFile != null && !dataUrlFile.isEmpty() && !dataUrlFile.isBlank()){
                byte[] signBytes = fileStorageService.read(dataUrlFile);
                if (signBytes != null){
                    byteArrayResource = new ByteArrayResource(signBytes);
                    try {
                        parameters.put("sign", byteArrayResource.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruExplorersWatermark());
            parameters.put("watermark", classPathResource.getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruFooter());
            parameters.put("footer", classPathResource.getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruHeader());
            parameters.put("header", classPathResource.getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruExplorersIconEmail());
            parameters.put("iconNegocio", classPathResource.getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruExplorersIconWorld());
            parameters.put("iconGlobo", classPathResource.getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruExplorersIconPhone());
            parameters.put("iconPhone", classPathResource.getInputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jrQuotationCostTypeGuruRepository.exportV2(listReport, listCostTourModelView, listCostTourModelViewLodging, listCostTourModelViewGroupPassengerType, listCostTourModelView, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public void changeDateItineraryDate(String idQuotation, ChangeItineraryDateCrmRequest changeItineraryDateRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<ItineraryDateDomain> listItineraryDate = itineraryDateUseCase.searchAllDomainByQuotation(idQuotation);
        if (listItineraryDate != null && !listItineraryDate.isEmpty()) {
            List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            Integer i = 0;
            for (ItineraryDateDomain itineraryDateDomain : listItineraryDateResult
            ) {
                itineraryDateDomain.setItineraryDate(changeItineraryDateRequest.getDateInit().plusDays(i));
                itineraryDateDomain = itineraryDateRepo.edit(itineraryDateDomain);

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, itineraryDateDomain));
                }*/

                List<ItineraryItemDomain> listItineraryItem = itineraryItemUseCase.searchAllDomainByQuotationAndItineraryDate(itineraryDateDomain.getId(), idQuotation);
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
                    itineraryItemRepo.edit(itineraryItemDomain);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemDomain));
                    }*/
                }
                i++;
            }
        }
        quotationDomain.setDateInitTour(changeItineraryDateRequest.getDateInit());
        quotationDomain.setDateEndTour(changeItineraryDateRequest.getDateEnd());
        QuotationDomain quotationDomainEdit = repo.edit(quotationDomain);

        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new QuotationEditEvent(this, quotationDomainEdit));
        }*/
        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public void changeDateItineraryDay(String idQuotation, ChangeItineraryDayCrmRequest changeItineraryDayRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Integer days = changeItineraryDayRequest.getDays();
        LocalDate lastDate = quotationDomain.getDateEndTour().plusDays(days);
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> {
                    ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
                    Integer indexDay = quotationDomain.getCantDayTour() + item + 1;
                    itineraryDateDomain.setName("");
                    itineraryDateDomain.setItineraryDate(quotationDomain.getDateEndTour().plusDays(item + 1));
                    itineraryDateDomain.setQuotation(quotationDomain);
                    itineraryDateDomain.setIndex(indexDay);
                    itineraryDateDomain.setItemItinerary(indexDay.toString());
                    itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                    ItineraryDateDomain itineraryDateDomainCreated = itineraryDateRepo.create(itineraryDateDomain);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryDateCrmCreateEvent(this, itineraryDateDomainCreated));
                    }*/
                });

        quotationDomain.setCantDayTour(quotationDomain.getCantDayTour() + changeItineraryDayRequest.getDays());
        quotationDomain.setDateEndTour(lastDate);

        QuotationDomain quotationDomainEdit = repo.edit(quotationDomain);

        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new QuotationEditEvent(this, quotationDomainEdit));
        }*/
        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public void changeDateItineraryDayInit(String idQuotation, ChangeItineraryDayCrmRequest changeItineraryDayRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<ItineraryDateDomain> listItineraryDateForUpdateOrder = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseUpdateOrderItinerary(idQuotation);

        Integer days = changeItineraryDayRequest.getDays();
        LocalDate initDate = quotationDomain.getDateInitTour().minusDays(days);
        Stream.iterate(0, n -> n + 1)
                .limit(days)
                .forEach(item -> {
                    ItineraryDateDomain itineraryDateDomain = new ItineraryDateDomain();
                    Integer indexDay = item + 1;
                    itineraryDateDomain.setName("");
                    itineraryDateDomain.setItineraryDate(initDate.plusDays(item));
                    itineraryDateDomain.setQuotation(quotationDomain);
                    itineraryDateDomain.setIndex(indexDay);
                    itineraryDateDomain.setItemItinerary(indexDay.toString());
                    itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                    ItineraryDateDomain itineraryDateDomainCreated = itineraryDateRepo.create(itineraryDateDomain);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryDateCrmCreateEvent(this, itineraryDateDomainCreated));
                    }*/
                });

        for (ItineraryDateDomain itineraryDateDomain : listItineraryDateForUpdateOrder){
            days = days + 1;
            itineraryDateRepo.updateIndexByIdSimpleDomainCaseUpdateOrderItineraryDate(itineraryDateDomain.getId(), days);
        }

        quotationDomain.setCantDayTour(quotationDomain.getCantDayTour() + changeItineraryDayRequest.getDays());
        quotationDomain.setDateInitTour(initDate);

        QuotationDomain quotationDomainEdit = repo.edit(quotationDomain);

        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            this.eventPublisher.publishEvent(new QuotationEditEvent(this, quotationDomainEdit));
        }*/

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public void changeDateItineraryDayMiddle(String idQuotation, ChangeItineraryDayCrmRequest changeItineraryDayRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        List<ItineraryDateDomain> listItineraryDateForUpdateOrder = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseUpdateOrderItinerary(idQuotation).stream().collect(Collectors.toList());
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
                    itineraryDateDomain.setQuotation(quotationDomain);
                    indexDay.set(indexDay.get() + 1);
                    itineraryDateDomain.setIndex(indexDay.get());
                    itineraryDateDomain.setItemItinerary(indexDay.get().toString());
                    itineraryDateDomain.setTenant(UserResolver.resolveUser().getTenant());
                    ItineraryDateDomain itineraryDateDomainCreated = itineraryDateRepo.create(itineraryDateDomain);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        this.eventPublisher.publishEvent(new ItineraryDateCrmCreateEvent(this, itineraryDateDomainCreated));
                    }*/
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

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idQuotation);
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

        quotationDomain.setCantDayTour(quotationDomain.getCantDayTour() + changeItineraryDayRequest.getDays());
        quotationDomain.setDateEndTour(dateInitPlus.plusDays(indexDay.get() - 1));

        QuotationDomain quotationDomainEdit = repo.edit(quotationDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public void updateOrderItineraryDate(String idQuotation, UpdateOrderItineraryDateCrmRequest updateOrderItineraryDateRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        if (quotationDomain.getIdentifyFile() != null && !quotationDomain.getIdentifyFile().isEmpty()){
            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findByIdentify(quotationDomain.getIdentifyFile());

            ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.findById(updateOrderItineraryDateRequest.getSourceItineraryDate());
            if (itineraryDateDomain == null) {
                throw new NotFoundException("No itinerary date found with id '%s'.".formatted(updateOrderItineraryDateRequest.getSourceItineraryDate()));
            }

            com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain itineraryDateDomainBooking = itineraryDateFromBookingRepo.findByItemItinerarySimpleDomainCaseGeneric(itineraryDateDomain.getItemItinerary());

            SupplierTypeDomain supplierTypeDomainLodging = supplierTypeRepo.findOne("1", null);
            SupplierTypeDomain supplierTypeDomainOperator = supplierTypeRepo.findOne("5", null);
            SupplierTypeDomain supplierTypeDomainLodge = supplierTypeRepo.findOne("7", null);
            SupplierTypeDomain supplierTypeDomainRestaurant = supplierTypeRepo.findOne("2", null);
            SupplierTypeDomain supplierTypeDomainGlamping = supplierTypeRepo.findOne("9", null);

            List<com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain> listValidation = itineraryItemFromBookingRepo.searchAllByItineraryDateAndRegisterGroupSimpleDomainCaseValidationExistBooking(itineraryDateDomainBooking.getId(), registerGroupDomain.getId());
            listValidation = listValidation.stream().collect(Collectors.toList());
            for(com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain itemDomain : listValidation){
                if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodging.getId())){
                    RegisterBookingLodgingDomain registerBookingLodgingDomainExist = registerBookingLodgingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingLodgingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainOperator.getId())){
                    RegisterBookingOperatorDomain registerBookingOperatorDomainExist = registerBookingOperatorRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingOperatorDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainRestaurant.getId())){
                    RegisterBookingRestaurantDomain registerBookingRestaurantDomainExist = registerBookingRestaurantRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingRestaurantDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                } else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainLodge.getId())){
                    RegisterBookingLodgerDomain registerBookingLodgerDomainExist = registerBookingLodgerRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingLodgerDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                }else if (itemDomain.getSupplierType().getId().equals(supplierTypeDomainGlamping.getId())){
                    RegisterBookingGlampingDomain registerBookingGlampingDomainExist = registerBookingGlampingRepo.findByIdRegisterGroupCaseValidationExistBooking(registerGroupDomain.getId(), itemDomain.getSupplier().getId());
                    if (registerBookingGlampingDomainExist != null){
                        throw new BadRequestException(MessagesValidation.VALIDATION_EXIST_REGISTER_BOOKING_ACTIVE_IN_DELETE);
                    }
                }
            }

        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<ItineraryDateDomain> list = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseUpdateOrderItinerary(idQuotation).stream().collect(Collectors.toList());
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
                    this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, itineraryDateEditDb));
                }*/

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idQuotation);
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
                        itemDomain.setItineraryDateTime(localDateTime);

                        itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                            this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
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
                this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, itineraryDateEditDb));
            }*/

            List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(itineraryIndexSource.getId(), idQuotation);
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
                    itemDomain.setItineraryDateTime(localDateTime);

                    itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                        this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
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
                    this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, itineraryDateEditDb));
                }*/

                List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(itineraryDatePassCurrent.getId(), idQuotation);
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
                        itemDomain.setItineraryDateTime(localDateTime);

                        itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                        /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                            this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
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
                this.eventPublisher.publishEvent(new ItineraryDateCrmEditEvent(this, itineraryDateEditDb));
            }*/

            List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseUpdateOrderItinerary(itineraryIndexSource.getId(), idQuotation);
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
                    itemDomain.setItineraryDateTime(localDateTime);

                    itineraryItemRepo.updateItineraryDateTimeByIdSimpleDomainCaseUpdateOrderItinerary(itemDomain.getId(), localDateTime);

                    /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                        ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.findById(itemDomain.getId());
                        this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
                    }*/
                }
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public void updateOrderItineraryItem(String idQuotation, UpdateOrderItineraryItemCrmRequest updateOrderItineraryItemRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        List<ItineraryItemDomain> list = itineraryItemRepo.searchAllByQuotation(idQuotation).stream().collect(Collectors.toList());
        list.sort(Comparator.comparing(ItineraryItemDomain::getIndex));

        Integer indexCurrent = 0;
        Integer indexCurrentPass = 0;

        ItineraryItemDomain source = itineraryItemRepo.findById(updateOrderItineraryItemRequest.getSourceItineraryItem());
        ItineraryItemDomain target = itineraryItemRepo.findById(updateOrderItineraryItemRequest.getTargetItineraryItem());

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

                ItineraryItemDomain dbItineraryItem = itineraryItemRepo.findById(itineraryItemPassCurrent.getId());
                dbItineraryItem.setIndex(itineraryItemCurrent.getIndex());
                ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.edit(dbItineraryItem);

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
                }*/

                indexCurrent--;
                indexCurrentPass--;
            }
            itineraryIndexSource.setIndex(itineraryIndexTarget.getIndex());
            LocalDateTime localDateTime = LocalDateTime.of(
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getYear(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getMonth(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getDayOfMonth(),
                    itineraryIndexSource.getItineraryDateTime().getHour(),
                    itineraryIndexSource.getItineraryDateTime().getMinute(),
                    itineraryIndexSource.getItineraryDateTime().getSecond());
            itineraryIndexSource.setItineraryDateTime(localDateTime);
            itineraryIndexSource.setItineraryDate(itineraryIndexTarget.getItineraryDate());
            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.edit(itineraryIndexSource);

            /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
            }*/

        }else if (indexListSource < indexListTarget){
            indexCurrent = indexListSource;
            indexCurrentPass = indexCurrent + 1;
            while (indexCurrent < indexListTarget){
                ItineraryItemDomain itineraryItemCurrent = list.get(indexCurrent);
                ItineraryItemDomain itineraryItemPassCurrent = list.get(indexCurrentPass);

                ItineraryItemDomain dbItineraryItem = itineraryItemRepo.findById(itineraryItemPassCurrent.getId());
                dbItineraryItem.setIndex(itineraryItemCurrent.getIndex());
                ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.edit(dbItineraryItem);

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                    this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
                }*/

                indexCurrent++;
                indexCurrentPass++;
            }
            itineraryIndexSource.setIndex(itineraryIndexTarget.getIndex());
            LocalDateTime localDateTime = LocalDateTime.of(
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getYear(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getMonth(),
                    itineraryIndexTarget.getItineraryDate().getItineraryDate().getDayOfMonth(),
                    itineraryIndexSource.getItineraryDateTime().getHour(),
                    itineraryIndexSource.getItineraryDateTime().getMinute(),
                    itineraryIndexSource.getItineraryDateTime().getSecond());
            itineraryIndexSource.setItineraryDateTime(localDateTime);
            itineraryIndexSource.setItineraryDate(itineraryIndexTarget.getItineraryDate());
            ItineraryItemDomain itineraryItemEditDb = itineraryItemRepo.edit(itineraryIndexSource);

            /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
                this.eventPublisher.publishEvent(new ItineraryItemCrmEditEvent(this, itineraryItemEditDb));
            }*/
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public void updateOrderItineraryDateToItem(String idQuotation, UpdateOrderItineraryDateToItemCrmRequest updateOrderItineraryDateToItemRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public void updateOrderItineraryItemToDate(String idQuotation, UpdateOrderItineraryItemToDateCrmRequest updateOrderItineraryItemToDateRequest) {
        if (idQuotation == null) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ID_NULL);
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public NewDateQuotationResponse getNewDateQuotation(String idQuotation, NewDateCrmRequest newDateRequest) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (quotationDomain.getDateInitTour().equals(newDateRequest.getNewDateInit())){
            throw new BadRequestException(MessagesValidation.VALIDATION_SAME_DATE_TOUR);
        }
        if (LocalDate.now().isAfter(newDateRequest.getNewDateInit())){
            throw new BadRequestException(MessagesValidation.VALIDATION_DATE_TOUR_IS_LESS_THAN_CURRENT_DATE);
        }
        LocalDate newDateInit = newDateRequest.getNewDateInit();
        LocalDate newDateEnd = newDateInit.plusDays(quotationDomain.getCantDayTour()-1);
        return NewDateQuotationResponse.from(newDateInit, newDateEnd);
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationResponse getQuotationModificationData(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null){
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        return QuotationResponse.from(repo.findByIdQuotationCaseGetModificationData(idQuotation));
    }

    @Override
    @Transactional
    public List<TourResponse> findAllToursByItineraryDate(String idQuotation, String idItineraryDate) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
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
    public void removeToursByItineraryDate(String idQuotation, String idItineraryDate, String idTour) {
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
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

        List<ItineraryItemDomain> itineraryItemDomainListToDelete = itineraryItemRepo.searchAllByQuotationAndItineraryDateSimpleDomainCaseDeleteTours(idQuotation, idItineraryDate, idTour);
        if (itineraryItemDomainListToDelete != null && !itineraryItemDomainListToDelete.isEmpty()){
            itineraryItemDomainListToDelete = itineraryItemDomainListToDelete.stream().toList();
            for (ItineraryItemDomain itineraryItemDomain : itineraryItemDomainListToDelete){
                itineraryItemRepo.deleteById(itineraryItemDomain.getId());

                /*if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
                    this.eventPublisher.publishEvent(new ItineraryItemCrmDeleteEvent(this, itineraryItemRepo.findById(itineraryItemDomain.getId())));
                }*/
            }
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public List<CostTourResponse> findAllCostTourByQuotation(String idQuotation) {
        List<CostTourResponse> result = new ArrayList<>();
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        if (listCostTour != null && !listCostTour.isEmpty()){
            listCostTour = listCostTour.stream().collect(Collectors.toList());
            Collections.sort(listCostTour);
            result = listCostTour.stream().map(costTourDomain -> CostTourResponse.from(costTourDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public CostTourResponse addCostTour(CostTourCreateRequest request, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId(quotationIdBaseDomain.getId());

        CostTourResponse costTourResponse = costTourUseCase.create(request, quotationDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return costTourResponse;
    }

    @Override
    @Transactional
    public CostTourResponse editCostTour(String id, CostTourUpdateRequest request, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId(quotationIdBaseDomain.getId());

        CostTourResponse costTourResponse = costTourUseCase.edit(id, request, quotationDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return costTourResponse;
    }

    @Override
    @Transactional
    public void deleteCostTour(String id, String idQuotation) {
        if (idQuotation == null) {
            throw new BadRequestException("The quotation id cannot be null");
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        costTourUseCase.deleteById(id);
    }

    @Override
    @Transactional
    public CostTourResponse findByCostTourAndQuotation(String id, String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        return costTourUseCase.searchByQuotationAndCostTour(idQuotation, id);
    }

    @Override
    @Transactional
    public CostTourItemResponse addCostTourItem(CostTourItemCreateRequest request, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId(quotationIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourItemResponse result = costTourItemUseCase.create(request, quotationDomain, costTourDomain);

        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, idCostTour);
        Double subTotalAmount = 0.0;
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItem){
                subTotalAmount = subTotalAmount + costTourItemDomain.getAmount();
            }
        }
        costTourRepo.updateIdSimpleDomainCaseUpdateSubTotalAmount(idCostTour, subTotalAmount);

        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return result;
    }

    @Override
    @Transactional
    public CostTourItemResponse editCostTourItem(String id, CostTourItemUpdateRequest request, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId(quotationIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourItemResponse result = costTourItemUseCase.edit(id, request, quotationDomain, costTourDomain);

        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, idCostTour);
        Double subTotalAmount = 0.0;
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItem){
                subTotalAmount = subTotalAmount + costTourItemDomain.getAmount();
            }
        }
        costTourRepo.updateIdSimpleDomainCaseUpdateSubTotalAmount(idCostTour, subTotalAmount);

        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return result;
    }

    @Override
    @Transactional
    public void deleteCostTourItem(String id, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            throw new BadRequestException("The quotation id cannot be null");
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idCostTour == null) {
            throw new BadRequestException("The cost tour id cannot be null");
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        costTourItemUseCase.deleteById(id);

        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, idCostTour);
        Double subTotalAmount = 0.0;
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            for (CostTourItemDomain costTourItemDomain: listCostTourItem){
                subTotalAmount = subTotalAmount + costTourItemDomain.getAmount();
            }
        }
        costTourRepo.updateIdSimpleDomainCaseUpdateSubTotalAmount(idCostTour, subTotalAmount);

        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public CostTourItemResponse findByCostTourItemAndQuotationAndCostTour(String id, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        return costTourItemUseCase.searchByQuotationAndCostTourAndCostTourItem(idQuotation, idCostTour, id);
    }

    @Override
    @Transactional
    public List<CostTourItemResponse> findAllCostTourItemByQuotationAndCostTour(String idQuotation, String idCostTour) {
        List<CostTourItemResponse> result = new ArrayList<>();
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, idCostTour);
        if (listCostTourItem != null && !listCostTourItem.isEmpty()){
            result = listCostTourItem.stream().map(costTourItemDomain -> CostTourItemResponse.from(costTourItemDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public void removeToursAndCostTourByQuotation(String idQuotation, String idTour) {
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        List<ItineraryDateDomain> listDates = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseDeleteTour(idQuotation);
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

                List<ItineraryItemDomain> itineraryItemDomainListToDelete = itineraryItemRepo.searchAllByQuotationAndItineraryDateSimpleDomainCaseDeleteTours(idQuotation, itineraryDateDomain.getId(), idTour);
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

        List<CostTourDomain> listCostTourDelete = costTourRepo.searchAllByQuotationAndTourSimpleDomainCaseShowCostTour(idQuotation, idTour);
        if (listCostTourDelete != null && !listCostTourDelete.isEmpty()){
            listCostTourDelete = listCostTourDelete.stream().toList();
            for (CostTourDomain costTourDomain : listCostTourDelete) {
                costTourRepo.deleteById(costTourDomain.getId());
                List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, costTourDomain.getId());
                if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
                    for (CostTourItemDomain costTourItemDomain : listCostTourItemDelete) {
                        costTourItemRepo.deleteById(costTourItemDomain.getId());
                    }
                }
            }
        }

        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public void removeToursAndCostTourByQuotationAndCostTour(String idQuotation, String idTour, String idCostTour) {
        // CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        TourDomain tourExistDomain = tourRepo.findById(idTour);
        if (tourExistDomain == null) {
            throw new NotFoundException("No tour found with id '%s'.".formatted(idTour));
        }
        CostTourDomain costTourExistDomain = costTourRepo.findById(idCostTour);
        if (costTourExistDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<ItineraryDateDomain> listDates = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseDeleteTour(idQuotation);
        if (listDates != null && !listDates.isEmpty()){
            listDates = listDates.stream().collect(Collectors.toList());
            for(ItineraryDateDomain itineraryDateDomain: listDates){
                List<ItineraryItemDomain> itineraryItemDomainListToDelete = itineraryItemRepo.searchAllByQuotationAndItineraryDateCostTourSimpleDomainCaseDeleteTours(idQuotation, itineraryDateDomain.getId(), idTour, idCostTour);
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

        /*List<CostTourDomain> listCostTourDelete = costTourRepo.searchAllByQuotationAndTourSimpleDomainCaseShowCostTour(idQuotation, idTour);
        if (listCostTourDelete != null && !listCostTourDelete.isEmpty()){
            listCostTourDelete = listCostTourDelete.stream().toList();
            for (CostTourDomain costTourDomain : listCostTourDelete) {
                costTourRepo.deleteById(costTourDomain.getId());
                List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, costTourDomain.getId());
                if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
                    for (CostTourItemDomain costTourItemDomain : listCostTourItemDelete) {
                        costTourItemRepo.deleteById(costTourItemDomain.getId());
                    }
                }
            }
        }*/
        costTourRepo.deleteById(costTourExistDomain.getId());
        List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, costTourExistDomain.getId());
        if (listCostTourItemDelete != null && !listCostTourItemDelete.isEmpty()){
            for (CostTourItemDomain costTourItemDomain : listCostTourItemDelete) {
                costTourItemRepo.deleteById(costTourItemDomain.getId());
            }
        }

        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        Double amount = 0.0;
        if (listCostTour != null && !listCostTour.isEmpty()){
            for (CostTourDomain costTourDomain1: listCostTour){
                amount = amount + costTourDomain1.getSubTotalAmount();
            }
        }
        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse addCostTourServiceItem(CostTourServiceItemCreateRequest request, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId(quotationIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourServiceItemResponse result = costTourServiceItemUseCase.create(request, quotationDomain, costTourDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return result;
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse editCostTourServiceItem(String id, CostTourServiceItemUpdateRequest request, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        QuotationDomain quotationDomain = new QuotationDomain();
        quotationDomain.setId(quotationIdBaseDomain.getId());
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        CostTourDomain costTourDomain = new CostTourDomain();
        costTourDomain.setId(costTourIdBaseDomain.getId());
        CostTourServiceItemResponse result = costTourServiceItemUseCase.edit(id, request, quotationDomain, costTourDomain);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

        return result;
    }

    @Override
    @Transactional
    public void deleteCostTourServiceItem(String id, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            throw new BadRequestException("The quotation id cannot be null");
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idCostTour == null) {
            throw new BadRequestException("The cost tour id cannot be null");
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        costTourServiceItemUseCase.deleteById(id);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);
    }

    @Override
    @Transactional
    public CostTourServiceItemResponse findByCostTourServiceItemAndQuotationAndCostTour(String id, String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        return costTourServiceItemUseCase.searchByQuotationAndCostTourAndCostTourServiceItem(idQuotation, idCostTour, id);
    }

    @Override
    @Transactional
    public List<CostTourServiceItemResponse> findAllCostTourServiceItemByQuotationAndCostTour(String idQuotation, String idCostTour) {
        List<CostTourServiceItemResponse> result = new ArrayList<>();
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idCostTour == null) {
            return null;
        }
        IdBaseDomain costTourIdBaseDomain = costTourRepo.findByIdEntity(idCostTour);
        if (costTourIdBaseDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<CostTourServiceItemDomain> listCostTourServiceItem = costTourServiceItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourServiceItem(idQuotation, idCostTour);
        if (listCostTourServiceItem != null && !listCostTourServiceItem.isEmpty()){
            result = listCostTourServiceItem.stream().map(costTourServiceItemDomain -> CostTourServiceItemResponse.from(costTourServiceItemDomain)).toList();
        }
        return result;
    }

    @Override
    @Transactional
    public List<ItineraryDateResponse> findAllItineraryDateByQuotationFromCostTour(String idQuotation, String idCostTour) {
        if (idQuotation == null) {
            return null;
        }
        QuotationDomain quotationDomain = repo.findById(idQuotation);
        if (quotationDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        if (idCostTour == null) {
            return null;
        }
        CostTourDomain costTourDomain = costTourRepo.findById(idCostTour);
        if (costTourDomain == null) {
            throw new NotFoundException("No cost tour found with id '%s'.".formatted(idCostTour));
        }
        List<ItineraryDateDomain> list = itineraryDateUseCase.searchAllDomainByQuotation(idQuotation);
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
    public void updatePriceQuotation(String idQuotation) {
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null) {
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }
        Double amount = 0.0;
        List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(idQuotation);
        if (listCostTour != null && !listCostTour.isEmpty()){
            listCostTour = listCostTour.stream().collect(Collectors.toList());
            for (CostTourDomain costTourDomain: listCostTour){
                List<CostTourItemDomain> listCostTourItem = costTourItemRepo.searchAllByQuotationAndCostTourSimpleDomainCaseShowCostTourItem(idQuotation, costTourDomain.getId());
                if (listCostTourItem != null && !listCostTourItem.isEmpty()){
                    for (CostTourItemDomain costTourItemDomain: listCostTourItem){
                        amount = amount + costTourItemDomain.getAmount();
                    }
                }
            }
        }

        repo.updateIdSimpleDomainCaseUpdateAmount(idQuotation, amount);

        repo.updateIdSimpleDomainCaseUserDataModification(idQuotation);

    }

    @Override
    @Transactional
    public QuotationResponse resendFileToSale(String idRegisterGroup) {
        if (idRegisterGroup == null) {
            return null;
        }
        RegisterGroupDomain registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
        if (registerGroupDomain == null) {
            throw new NotFoundException("No register group found with id '%s'.".formatted(idRegisterGroup));
        }

        QuotationDomain quotationDomain = registerGroupDomain.getQuotation();
        // Delete All By Register Group

        List<CostTourDomain> listCostTourDelete = costTourRepo.searchAllByQuotationSimpleDomainCaseDelete(quotationDomain.getId());
        List<CostTourItemDomain> listCostTourItemDelete = costTourItemRepo.searchAllByQuotationSimpleDomainCaseDelete(quotationDomain.getId());
        List<CostTourServiceItemDomain> listCostTourServiceItemDelete = costTourServiceItemRepo.searchAllByQuotationSimpleDomainCaseDelete(quotationDomain.getId());
        List<ItineraryDateDomain> listItineraryDateDelete = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseDelete(quotationDomain.getId());
        List<ItineraryItemDomain> listItineraryItemDelete = itineraryItemRepo.searchAllByQuotationSimpleDomainCaseDelete(quotationDomain.getId());
        List<PassengerDomain> listPassengerDelete = passengerRepo.searchAllByQuotationSimpleDomainCaseDelete(quotationDomain.getId());

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

        if (listPassengerDelete != null && !listPassengerDelete.isEmpty()){
            for (PassengerDomain passengerDomain: listPassengerDelete){
                passengerRepo.deleteById(passengerDomain.getId());
            }
        }

        quotationDomain.setDateInitTour(registerGroupDomain.getInitTour());
        quotationDomain.setDateEndTour(registerGroupDomain.getEndTour());
        quotationDomain.setProgram(registerGroupDomain.getProgram());
        quotationDomain.setIdiom(registerGroupDomain.getIdiom());
        quotationDomain.setPaxAdult(registerGroupDomain.getCantPaxAdult());
        quotationDomain.setPaxChild(registerGroupDomain.getCantPaxChildren());
        quotationDomain.setPaxTc(registerGroupDomain.getTC());
        quotationDomain.setPaxTcLocal(registerGroupDomain.getTCLocal());
        quotationDomain.setPaxTotal(registerGroupDomain.getTotalPax());
        quotationDomain.setCantDayTour(registerGroupDomain.getDays());

        quotationDomain.setCountry(registerGroupDomain.getCountry());
        quotationDomain.setLinkGDrive(registerGroupDomain.getLink());
        quotationDomain.setLinkBitrix(registerGroupDomain.getLinkBitrix());
        quotationDomain.setLinkWetravel(registerGroupDomain.getLinkWetravel());
        quotationDomain.setEmailPassengerPrincipal(registerGroupDomain.getEmailPassengerPrincipal());
        quotationDomain.setWhatsappPassengerPrincipal(registerGroupDomain.getWhatsappPassengerPrincipal());
        quotationDomain.setMobilePassengerPrincipal(registerGroupDomain.getMobilePassengerPrincipal());

        quotationDomain.setObservation(registerGroupDomain.getObservation());
        quotationDomain = repo.edit(quotationDomain);

        List<ItineraryItemDomain> listItineraryItemUpdateCostTour = new ArrayList<>();

        // Generate Itinerary Date and Item
        List<com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain> listItineraryDate = itineraryDateFromBookingRepo.searchAllByRegisterGroup(registerGroupDomain.getId());
        if (listItineraryDate != null && !listItineraryDate.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
            Collections.sort(listItineraryDateResult);
            for (com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryDateDomain itineraryDateDomainSource: listItineraryDateResult
            ) {
                ItineraryDateDomain itineraryDateDomainDestination = new ItineraryDateDomain();
                itineraryDateDomainDestination.setName(itineraryDateDomainSource.getName());
                itineraryDateDomainDestination.setDescription(itineraryDateDomainSource.getDescription());
                itineraryDateDomainDestination.setIdentify(itineraryDateDomainSource.getIdentify());
                itineraryDateDomainDestination.setItineraryDate(itineraryDateDomainSource.getItineraryDate());
                itineraryDateDomainDestination.setIndex(itineraryDateDomainSource.getIndex());
                itineraryDateDomainDestination.setItemItinerary(itineraryDateDomainSource.getItemItinerary());
                itineraryDateDomainDestination.setQuotation(quotationDomain);
                itineraryDateDomainDestination.setTours(itineraryDateDomainSource.getTours());
                itineraryDateDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                // itineraryDateDomainDestination.setIdItineraryDateQuotation(itineraryDateDomainSource.getId());

                ItineraryDateDomain resultItineraryDateDomainDestination = itineraryDateRepo.create(itineraryDateDomainDestination);
                // Update IdItineraryDateQuotation in ItineraryDateDomain from Booking
                itineraryDateDomainSource.setIdItineraryDateQuotation(resultItineraryDateDomainDestination.getId());
                //itineraryDateFromBookingRepo.edit(itineraryDateDomainSource);
                itineraryDateFromBookingRepo.updateIdItineraryDateQuotationSimpleDomainCaseResend(itineraryDateDomainSource.getId(), resultItineraryDateDomainDestination.getId());

                List<com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain> listItineraryItem = itineraryItemFromBookingRepo.searchAllByRegisterGroupSimpleDomainCaseResendFileToSale(itineraryDateDomainSource.getId(), registerGroupDomain.getId());
                List<com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain> listItineraryItemResult = new ArrayList<>();

                if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                    listItineraryItemResult = listItineraryItem.stream().collect(Collectors.toList());
                    Collections.sort(listItineraryItemResult);
                    for (com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain itineraryItemDomainSource: listItineraryItemResult
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
                        itineraryItemDomainDestination.setQuotation(quotationDomain);
                        itineraryItemDomainDestination.setSupplierType(itineraryItemDomainSource.getSupplierType());
                        itineraryItemDomainDestination.setSupplier(itineraryItemDomainSource.getSupplier());
                        itineraryItemDomainDestination.setService(itineraryItemDomainSource.getService());
                        itineraryItemDomainDestination.setDepartmentRegion(itineraryItemDomainSource.getDepartmentRegion());
                        itineraryItemDomainDestination.setTour(itineraryItemDomainSource.getTour());
                        // itineraryItemDomainDestination.setCustomer(registerGroupDomain.getCustomer());
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

                        // itineraryItemDomainDestination.setIdItineraryItemQuotation(itineraryItemDomainSource.getId());

                        // itineraryItemDomainDestination.setStateItineraryService(StateBookingEnum.DRAFT.getName());

                        itineraryItemDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                        ItineraryItemDomain resultItineraryItemDomainDestination = itineraryItemRepo.create(itineraryItemDomainDestination);
                        // Update idItineraryItemQuotation in ItineraryItemDomain from Booking
                        itineraryItemDomainSource.setIdItineraryItemQuotation(resultItineraryItemDomainDestination.getId());
                        // itineraryItemFromBookingRepo.edit(itineraryItemDomainSource);
                        itineraryItemFromBookingRepo.updateIdItineraryItemQuotationSimpleDomainCaseResend(itineraryItemDomainSource.getId(), resultItineraryItemDomainDestination.getId());

                        listItineraryItemUpdateCostTour.add(itineraryItemDomainDestination);
                    }
                }
            }
        }
        // Generate Passenger Group
        List<PassengerGroupDomain> listPassenger = passengerGroupRepo.searchAllByRegisterGroup(registerGroupDomain.getId());
        if (listPassenger != null && !listPassenger.isEmpty()) {
            List<PassengerGroupDomain> listPassengerResult = listPassenger.stream().collect(Collectors.toList());
            for (PassengerGroupDomain passengerDomainSource: listPassengerResult
            ) {
                PassengerDomain passengerDomainDestination = new PassengerDomain();
                passengerDomainDestination.setName(passengerDomainSource.getName());
                passengerDomainDestination.setDescription(passengerDomainSource.getDescription());
                passengerDomainDestination.setIdentify(passengerDomainSource.getIdentify());
                passengerDomainDestination.setSocialMediaInformation(passengerDomainSource.getSocialMediaInformation());
                passengerDomainDestination.setWebSiteInformation(passengerDomainSource.getWebSiteInformation());
                passengerDomainDestination.setContactInformation(passengerDomainSource.getContactInformation());
                passengerDomainDestination.setAddress(passengerDomainSource.getAddress());
                passengerDomainDestination.setDateInit(passengerDomainSource.getDateInit());
                passengerDomainDestination.setDateEnd(passengerDomainSource.getDateEnd());
                passengerDomainDestination.setDocument(passengerDomainSource.getDocument());
                passengerDomainDestination.setObservation(passengerDomainSource.getObservation());
                passengerDomainDestination.setPrincipal(passengerDomainSource.getPrincipal());
                passengerDomainDestination.setImageUrl(passengerDomainSource.getImageUrl());
                passengerDomainDestination.setTitlePerson(passengerDomainSource.getTitlePerson());
                passengerDomainDestination.setTypePassengerTcBooking(passengerDomainSource.getTypePassengerTcBooking());
                passengerDomainDestination.setPerson(passengerDomainSource.getPerson());
                passengerDomainDestination.setNationality(passengerDomainSource.getNationality());
                passengerDomainDestination.setDocumentType(passengerDomainSource.getDocumentType());
                passengerDomainDestination.setPassengerType(passengerDomainSource.getPassengerType());
                passengerDomainDestination.setQuotation(quotationDomain);
                passengerDomainDestination.setIndex(passengerDomainSource.getIndex());

                // passengerGroupDomainDestination.setIdPassengerQuotation(passengerDomainSource.getId());

                passengerDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                PassengerDomain resultPassengerDomainDestination = passengerRepo.create(passengerDomainDestination);

                // Update idItineraryItemQuotation in ItineraryItemDomain from Booking
                passengerDomainSource.setIdPassengerQuotation(resultPassengerDomainDestination.getId());
                passengerGroupRepo.edit(passengerDomainSource);

            }
        }
        // Generate Cost Tour
        List<com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain> listCostTour = costTourFromBookingRepo.searchAllByQuotationSimpleDomainCaseResendFileToSale(registerGroupDomain.getId());
        if (listCostTour != null && !listCostTour.isEmpty()){
            List<com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain> listCostTourResult = listCostTour.stream().collect(Collectors.toList());
            for (com.saas.tourismagency.admin.backoffice.domain.booking.CostTourDomain costTourDomainSource: listCostTourResult
            ) {
                Map<String, CostTourItemDomain> mapCostTourItemService = new HashMap<>();

                CostTourDomain costTourDomainDestination = new CostTourDomain();
                costTourDomainDestination.setName(costTourDomainSource.getName());
                costTourDomainDestination.setDescription(costTourDomainSource.getDescription());
                costTourDomainDestination.setIdentify(costTourDomainSource.getIdentify());
                ItineraryDateDomain itineraryDateDomain = itineraryDateRepo.getByQuotationAndItemItinerary(quotationDomain.getId(), costTourDomainSource.getItineraryDate().getItemItinerary());
                costTourDomainDestination.setItineraryDate(itineraryDateDomain);
                costTourDomainDestination.setNumMaxPax(costTourDomainSource.getNumMaxPax());
                costTourDomainDestination.setQuotation(quotationDomain);
                costTourDomainDestination.setSubTotalAmount(costTourDomainSource.getSubTotalAmount());
                costTourDomainDestination.setTour(costTourDomainSource.getTour());
                costTourDomainDestination.setTenant(UserResolver.resolveUser().getTenant());

                CostTourDomain costTourDomainDestinationResult = costTourRepo.create(costTourDomainDestination);

                List<com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain> listCostTourItem = costTourItemFromBookingRepo.searchAllByCostTourAndQuotationSimpleDomainCaseResendFileToSale(costTourDomainSource.getId(), registerGroupDomain.getId());
                List<com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain> listCostTourItemResult = new ArrayList<>();
                if (listCostTourItem != null && !listCostTourItem.isEmpty()) {
                    listCostTourItemResult = listCostTourItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.booking.CostTourItemDomain costTourItemDomainSource : listCostTourItemResult
                    ) {
                        CostTourItemDomain costTourItemDomainDestination = new CostTourItemDomain();
                        costTourItemDomainDestination.setName(costTourItemDomainSource.getName());
                        costTourItemDomainDestination.setDescription(costTourItemDomainSource.getDescription());
                        costTourItemDomainDestination.setIdentify(costTourItemDomainSource.getIdentify());
                        costTourItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        costTourItemDomainDestination.setItineraryDate(itineraryDateDomain);
                        costTourItemDomainDestination.setTour(costTourItemDomainSource.getTour());
                        costTourItemDomainDestination.setQuotation(quotationDomain);
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

                List<ItineraryItemDomain>  tempForUpdates = listItineraryItemUpdateCostTour.stream().filter(
                        itineraryItemDomain -> _matchesCostTourAndItineraryDate(itineraryItemDomain, costTourDomainDestinationResult))
                        .collect(Collectors.toList());
                if (tempForUpdates != null && !tempForUpdates.isEmpty()){
                    for (ItineraryItemDomain itemTempForUpdate: tempForUpdates){
                        itemTempForUpdate.setCostTour(costTourDomainDestinationResult);
                        itineraryItemRepo.edit(itemTempForUpdate);
                    }
                }

                List<com.saas.tourismagency.admin.backoffice.domain.booking.CostTourServiceItemDomain> listCostTourServiceItem = costTourServiceItemFromBookingRepo.searchAllByCostTourAndQuotationCaseResendFileToSale(costTourDomainSource.getId(), registerGroupDomain.getId());
                List<com.saas.tourismagency.admin.backoffice.domain.booking.CostTourServiceItemDomain> listCostTourServiceItemResult = new ArrayList<>();
                if (listCostTourServiceItem != null && !listCostTourServiceItem.isEmpty()) {
                    listCostTourServiceItemResult = listCostTourServiceItem.stream().collect(Collectors.toList());
                    for (com.saas.tourismagency.admin.backoffice.domain.booking.CostTourServiceItemDomain costTourServiceItemDomainSource : listCostTourServiceItemResult
                    ) {
                        CostTourServiceItemDomain costTourServiceItemDomainDestination = new CostTourServiceItemDomain();
                        costTourServiceItemDomainDestination.setName(costTourServiceItemDomainSource.getName());
                        costTourServiceItemDomainDestination.setDescription(costTourServiceItemDomainSource.getDescription());
                        costTourServiceItemDomainDestination.setIdentify(costTourServiceItemDomainSource.getIdentify());
                        costTourServiceItemDomainDestination.setCostTour(costTourDomainDestinationResult);
                        ItineraryDateDomain itineraryDateDomain1 = itineraryDateRepo.getByQuotationAndItemItinerary(quotationDomain.getId(), costTourServiceItemDomainSource.getItineraryDate().getItemItinerary());
                        costTourServiceItemDomainDestination.setItineraryDate(itineraryDateDomain1);
                        costTourServiceItemDomainDestination.setTour(costTourDomainDestinationResult.getTour());
                        costTourServiceItemDomainDestination.setQuotation(quotationDomain);
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

        repo.updateIdSimpleDomainCaseUserDataModification(quotationDomain.getId());

        return QuotationResponse.from(quotationDomain);
    }

    @Override
    @Transactional
    public QuotationResponse getQuotationModificationUserData(String idQuotation) {
        if (idQuotation == null) {
            return null;
        }
        IdBaseDomain quotationIdBaseDomain = repo.findByIdEntity(idQuotation);
        if (quotationIdBaseDomain == null){
            throw new NotFoundException("No quotation found with id '%s'.".formatted(idQuotation));
        }

        return QuotationResponse.from(repo.findByIdQuotationCaseGetModificationUserData(idQuotation));
    }

    private void _changeStateCancelPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateCompletedPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateAcceptedPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateInBookingPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateFinishedPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateSentPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        RegisterGroupDomain newDomain = new RegisterGroupDomain();
        newDomain.setApertureWrite(false);
        newDomain.setCustomer(quotationDomain.getCustomer());
        newDomain.setName(quotationDomain.getName());
        EmployeeDomain employeeDomain = null;
        if (request.getEmployee() != null && !request.getEmployee().isEmpty()){
            employeeDomain = employeeRepo.findById(request.getEmployee());
        }
        newDomain.setResponsible(employeeDomain);
        newDomain.setStateRegisterGroup(StateRegisterGroupEnum.GENERATED.getName());
        newDomain.setCantPaxAdult(quotationDomain.getPaxAdult());
        newDomain.setCantPaxChildren(quotationDomain.getPaxChild());
        newDomain.setGuide(quotationDomain.getGuide());
        newDomain.setTC(quotationDomain.getPaxTc());
        newDomain.setTCLocal(quotationDomain.getPaxTcLocal());
        newDomain.setTotalPax(quotationDomain.getPaxTotal());
        newDomain.setDays(quotationDomain.getCantDayTour());
        newDomain.setObservation(quotationDomain.getObservation());
        newDomain.setQuotation(quotationDomain);
        newDomain.setCountry(quotationDomain.getCountry());
        newDomain.setIdiom(quotationDomain.getIdiom());
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            newDomain.setInitTour(quotationDomain.getDateInitTour());
            newDomain.setEndTour(quotationDomain.getDateEndTour());
        }
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        RegisterGroupDomain registerGroupCreatedDomain = registerGroupRepo.create(newDomain);

        NotificationDomain notificationDomain = new NotificationDomain();
        notificationDomain.setType(NotificationTypeEnum.QUOTATION_SENDING_TO_BOOKING.getName());
        notificationDomain.setRead(false);
        notificationDomain.setDateTime(LocalDateTime.now());
        notificationDomain.setSubject("Has recibido una nueva cotización");
        notificationDomain.setStatus(NotificationStatusEnum.DRAFT.getName());

        notificationDomain.setUserSource(UserResolver.resolveUser());
        EmployeeUserDomain employeeUserSourceDomain = employeeUserRepo.getByUser(notificationDomain.getUserSource().getId());
        if (employeeUserSourceDomain != null){
            notificationDomain.setEmployeeSource(employeeUserSourceDomain.getEmployee());
        }

        notificationDomain.setEmployeeDestine(employeeDomain);
        EmployeeUserDomain employeeUserDestineDomain = employeeUserRepo.getByEmployee(notificationDomain.getEmployeeDestine().getId());
        if (employeeUserDestineDomain != null){
            notificationDomain.setUserDestine(employeeUserDestineDomain.getUser());
        }
        notificationDomain.setTenant(UserResolver.resolveUser().getTenant());

        ObjectDomain objectDomainSource = objectRepo.findByIdentify("CODE_PERM_QUOTATION");
        ObjectDomain objectDomainDestine = objectRepo.findByIdentify("CODE_PERM_REGISTER_GROUP");

        notificationDomain.setIdObjectSource(quotationDomain.getId());
        notificationDomain.setNameObjectSource(quotationDomain.getName());
        notificationDomain.setIdentifyObjectSource(quotationDomain.getIdentify());
        notificationDomain.setObjectEntitySource(objectDomainSource);

        notificationDomain.setIdObjectDestine(registerGroupCreatedDomain.getId());
        notificationDomain.setNameObjectDestine(registerGroupCreatedDomain.getName());
        notificationDomain.setObjectEntityDestine(objectDomainDestine);

        notificationRepo.create(notificationDomain);

    }

    private void _changeStateRejectedPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateReviewPost(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateCancelPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateCompletedPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateAcceptedPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            Integer cantDaysCompleted = 0;
            List<ItineraryDateDomain> listItineraryDate = itineraryDateRepo.searchAllByQuotationSimpleDomainCaseShowItinerary(quotationDomain.getId());
            if (listItineraryDate != null && !listItineraryDate.isEmpty()){
                List<ItineraryDateDomain> listItineraryDateResult = listItineraryDate.stream().collect(Collectors.toList());
                for (ItineraryDateDomain itineraryDateDomain: listItineraryDateResult
                ) {
                    List<ItineraryItemDomain> listItineraryItem = itineraryItemRepo.searchAllByItineraryDateAndQuotationSimpleDomainCaseShowItinerary(itineraryDateDomain.getId(), quotationDomain.getId());
                    if (listItineraryItem != null && !listItineraryItem.isEmpty()){
                        cantDaysCompleted = cantDaysCompleted + 1;
                    }
                }
            }
            if (!quotationDomain.getCantDayTour().equals(cantDaysCompleted)){
                throw new BadRequestException(MessagesValidation.VALIDATION_QUOTATION_NOT_COMPLETED);
            }
            List<CostTourDomain> listCostTour = costTourRepo.searchAllByQuotationSimpleDomainCaseShowCostTour(quotationDomain.getId());
            if (listCostTour != null && !listCostTour.isEmpty()){
                List<CostTourDomain> listCostTourResult = listCostTour.stream().collect(Collectors.toList());
                for (CostTourDomain costTourDomain: listCostTourResult
                ) {
                    if (costTourDomain.getSubTotalAmount() == 0 && (costTourDomain.getTour().getWithoutCost() == null || !costTourDomain.getTour().getWithoutCost() )){
                        throw new BadRequestException(MessagesValidation.VALIDATION_QUOTATION_NOT_COST_TOUR_REGISTERED);
                    }
                }
            }
        }
    }

    private void _changeStateInBookingPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateFinishedPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateSentPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateRejectedPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

    }

    private void _changeStateReviewPre(QuotationDomain quotationDomain, QuotationChangeStateRequest request){

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
}
