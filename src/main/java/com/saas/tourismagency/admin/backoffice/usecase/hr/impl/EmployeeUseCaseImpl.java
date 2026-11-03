package com.saas.tourismagency.admin.backoffice.usecase.hr.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee.EmployeeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPartner;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPerson;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.hr.SetFieldDefaultEmployee;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.hr.SetFieldDefaultEmployeeUser;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.area.AreaRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.DistrictRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.person.PersonRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.EmployeeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employee.PersistEmployeeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.employeeuser.EmployeeUserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.hr.position.PositionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.security.user.UserRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.quotation.QuotationResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employee.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.EmployeeUserCreateRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.hr.employeeuser.EmployeeUserUpdateRequest;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeUserDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.SimpleEmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.hr.EmployeeUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.hr.EmployeeUserUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeUseCaseImpl implements EmployeeUseCase {

    private final String UPLOAD_FOLDER_EMPLOYEE_SIGN = "c:\\Temp";

    @Autowired
    private EmployeeRepo repo;

    @Autowired
    private PartnerRepo partnerRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private DepartmentRegionRepo departmentRegionRepo;

    @Autowired
    private ProvinceRepo provinceRepo;

    @Autowired
    private DistrictRepo districtRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private AreaRepo areaRepo;

    @Autowired
    private PositionRepo positionRepo;

    @Autowired
    private EmployeeUserRepo employeeUserRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeUserUseCase employeeUserUseCase;

    @Autowired
    private EmployeeConverter converter;

    @Autowired
    private SetFieldDefaultEmployee setFieldDefault;

    @Autowired
    private SetFieldDefaultPerson setFieldDefaultPerson;

    @Autowired
    private SetFieldDefaultPartner setFieldDefaultPartner;

    @Autowired
    private SetFieldDefaultEmployeeUser setFieldDefaultEmployeeUser;

    @Autowired
    PersistEmployeeNumberCallbackImpl persistEmployeeNumberCallbackImpl;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    @Transactional
    public EmployeeResponse create(EmployeeCreateRequest request) {
        PartnerDomain newPartnerDomain = request.getPartner().to();
        PersonDomain newPersonDomain = request.getPerson().to();
        PositionDomain positionDomain = null;
        AreaDomain areaDomain = null;

        if (request.getPosition() != null && !request.getPosition().isEmpty()){
            positionDomain = positionRepo.findById(request.getPosition());
        }
        if (request.getArea() != null && !request.getArea().isEmpty()){
            areaDomain = areaRepo.findById(request.getArea());
        }
        newPartnerDomain.setTenant(UserResolver.resolveUser().getTenant());
        PartnerDomain createdPartnerDomain = partnerRepo.create(newPartnerDomain);

        newPersonDomain.setTenant(UserResolver.resolveUser().getTenant());
        PersonDomain createdPersonDomain = personRepo.create(newPersonDomain);

        EmployeeDomain newDomain = request.to(
                createdPersonDomain,
                areaDomain,
                positionDomain,
                createdPartnerDomain
        );
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        newDomain.setIdentify(persistEmployeeNumberCallbackImpl.generateNumber());
        EmployeeDomain createdDomain = repo.create(newDomain);
        return EmployeeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public EmployeeResponse edit(String id, EmployeeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        EmployeeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No employee found with id '%s'.".formatted(id));
        }

        UserDomain userDomain = null;
        if (request.getUser() != null && !request.getUser().isEmpty()){
            EmployeeUserDomain employeeUserValidationDomain = employeeUserRepo.getByUser(request.getUser());
            if (employeeUserValidationDomain != null && !employeeUserValidationDomain.getEmployee().getId().equals(old.getId())){
                throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_USER_ASSOCIATED_WITH_EMPLOYEE);
            }
            userDomain = userRepo.findById(request.getUser());
        }

        PersonDomain oldPerson = personRepo.findById(request.getPerson().getId());
        if (oldPerson == null) {
            throw new NotFoundException("No person found with id '%s'.".formatted(request.getPerson().getId()));
        }
        PersonDomain updatePersonDomain = request.getPerson().to();

        PartnerDomain oldPartner = partnerRepo.findById(request.getPartner().getId());
        if (oldPartner == null) {
            throw new NotFoundException("No partner found with id '%s'.".formatted(request.getPartner().getId()));
        }
        PartnerDomain updatePartnerDomain = request.getPartner().to();

        CountryDomain countryDomain = null;
        if (request.getPartner().getAddress().getCountry().getId() != null
                && request.getPartner().getAddress().getCountry() != null
                && !request.getPartner().getAddress().getCountry().getId().isEmpty()) {
            countryDomain = countryRepo.findById(request.getPartner().getAddress().getCountry().getId());
        }
        updatePartnerDomain.getAddress().setCountry(countryDomain);
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getPartner().getAddress().getDepartmentRegion() != null
                && request.getPartner().getAddress().getDepartmentRegion().getId() != null
                && !request.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
            departmentRegionDomain = departmentRegionRepo.findById(request.getPartner().getAddress().getDepartmentRegion().getId());
        }
        updatePartnerDomain.getAddress().setDepartmentRegion(departmentRegionDomain);
        ProvinceDomain provinceDomain = null;
        if (request.getPartner().getAddress().getProvince() != null
                && request.getPartner().getAddress().getProvince().getId() != null
                && !request.getPartner().getAddress().getProvince().getId().isEmpty()) {
            provinceDomain = provinceRepo.findById(request.getPartner().getAddress().getProvince().getId());
        }
        updatePartnerDomain.getAddress().setProvince(provinceDomain);
        DistrictDomain districtDomain = null;
        if (request.getPartner().getAddress().getDistrict() != null
                && request.getPartner().getAddress().getDistrict().getId() != null
                && !request.getPartner().getAddress().getDistrict().getId().isEmpty()) {
            districtDomain = districtRepo.findById(request.getPartner().getAddress().getDistrict().getId());
        }
        updatePartnerDomain.getAddress().setDistrict(districtDomain);
        PositionDomain positionDomain = null;
        AreaDomain areaDomain = null;
        if (request.getPosition() != null && !request.getPosition().isEmpty()){
            positionDomain = positionRepo.findById(request.getPosition());
        }
        if (request.getArea() != null && !request.getArea().isEmpty()){
            areaDomain = areaRepo.findById(request.getArea());
        }

        PartnerDomain editedDomainPartner = partnerRepo.edit(setFieldDefaultPartner.setDefaultFieldBaseUpdated(updatePartnerDomain, oldPartner));
        PersonDomain editedDomainPerson = personRepo.edit(setFieldDefaultPerson.setDefaultFieldBaseUpdated(updatePersonDomain, oldPerson));

        EmployeeDomain employeeDomain = request.to(
                editedDomainPerson,
                areaDomain,
                positionDomain,
                editedDomainPartner
        );
        employeeDomain.setIdentify(old.getIdentify());
        EmployeeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(employeeDomain, old));

        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByEmployee(id);
        UserDomain userDomainResponse = null;
        if (request.getUser() != null && !request.getUser().isEmpty()){
            if (employeeUserDomain == null){
                EmployeeUserCreateRequest employeeUserCreateRequest = new EmployeeUserCreateRequest();
                employeeUserDomain = employeeUserCreateRequest.to(editedDomain, userDomain);
                employeeUserDomain.setTenant(UserResolver.resolveUser().getTenant());
                employeeUserDomain = employeeUserRepo.create(employeeUserDomain);
                userDomainResponse = employeeUserDomain.getUser();
            }else{
                EmployeeUserUpdateRequest employeeUserUpdateRequest = new EmployeeUserUpdateRequest();
                employeeUserUpdateRequest.setId(employeeUserDomain.getId());
                employeeUserDomain = employeeUserRepo.edit(setFieldDefaultEmployeeUser.setDefaultFieldBaseUpdated(employeeUserUpdateRequest.to(editedDomain, userDomain), employeeUserDomain));
                userDomainResponse = employeeUserDomain.getUser();
            }
        }else{
            if (employeeUserDomain != null){
                employeeUserRepo.delete(employeeUserDomain);
                userDomainResponse = null;
            }
        }
        return EmployeeResponse.from(editedDomain, userDomainResponse);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        EmployeeResponse old = findById(id);
        repo.deleteById(old.getId());
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByEmployee(id);
        if (employeeUserDomain != null){
            employeeUserRepo.deleteById(employeeUserDomain.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> findAll() {
        return repo.findAll(false).stream().map(employeeDomain -> EmployeeResponse.from(employeeDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        EmployeeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No employee found with id '%s'.".formatted(id));
        }
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByEmployee(id);
        UserDomain userDomain = null;
        if (employeeUserDomain != null){
            userDomain = employeeUserDomain.getUser();
        }
        EmployeeResponse result = EmployeeResponse.from(old, userDomain);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeSearchResponse searchAll(EmployeeSearchRequest employeeSearchRequest) {
       PageDTO<EmployeeDomain> pageDTO = repo.searchAll(employeeSearchRequest);
       EmployeeSearchResponse employeeSearchRespose = new EmployeeSearchResponse();
       employeeSearchRespose.setEmployees(pageDTO.getContent().stream().map(employeeDomain ->  EmployeeResponse.from(employeeDomain)).toList());
       employeeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       employeeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return employeeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeSearchResponse searchFilterAll(EmployeeSearchFilterRequest employeeSearchFilterRequest) {
        PageDTO<EmployeeDomain> pageDTO = repo.searchAllFilter(employeeSearchFilterRequest);
        EmployeeSearchResponse employeeSearchRespose = new EmployeeSearchResponse();
        employeeSearchRespose.setEmployees(pageDTO.getContent().stream().map(employeeDomain ->  EmployeeResponse.from(employeeDomain)).toList());
        employeeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        employeeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return employeeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        EmployeeDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No employee found with name '%s'.".formatted(name));
        }
        return EmployeeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        EmployeeDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No employee found with identify '%s'.".formatted(identify));
        }
        return EmployeeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBaseResponse> findAllSimple() {
        return repo.findAllSimple().stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional
    public List<SimpleBaseResponse> findAllSimpleWithConditionalArea() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        List<SimpleBaseDomain> list = new ArrayList<>();
        List<SimpleEmployeeDomain> listEmployee = repo.findAllSimpleWithConditionalArea().stream().collect(Collectors.toList());
        List<AreaDomain> listArea = companyDomain.getSetting().getAreaListQuotationFilter().stream().toList();
        for (SimpleEmployeeDomain simpleEmployeeDomain: listEmployee){
            for (AreaDomain areaDomain: listArea){
                if (simpleEmployeeDomain.getArea().getId().equals(areaDomain.getId())){
                    SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();
                    simpleBaseDomain.setId(simpleEmployeeDomain.getId());
                    simpleBaseDomain.setName(simpleEmployeeDomain.getName());
                    list.add(simpleBaseDomain);
                    break;
                }
            }
        }
        return list.stream().map(simpleBaseDomain -> SimpleBaseResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional
    public EmployeeResponse uploadSign(String id, MultipartFile[] multipartFile) {
        if (id == null) {
            return null;
        }
        EmployeeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No employee found with id '%s'.".formatted(id));
        }
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByEmployee(id);
        UserDomain userDomain = null;
        if (employeeUserDomain != null){
            userDomain = employeeUserDomain.getUser();
        }
        String urlFileSign = null;
        String fileNameSign = null;
        final MultipartFile[] multipartFileSign = {null};
        List<MultipartFile> list = Arrays.asList(multipartFile);
        multipartFileSign[0] = list.get(0);
        if (multipartFileSign[0] != null && !multipartFileSign[0].isEmpty()){
            try {
                byte[] bytes = multipartFileSign[0].getBytes();

                // Creating the directory to store file
                File dir = new File(UPLOAD_FOLDER_EMPLOYEE_SIGN);
                if (!dir.exists())
                    dir.mkdirs();

                String randomFile = UUID.randomUUID().toString().replaceAll("-", "");
                fileNameSign = multipartFileSign[0].getOriginalFilename();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + randomFile + "." + getExtension(multipartFileSign[0].getOriginalFilename()) );

                urlFileSign = UPLOAD_FOLDER_EMPLOYEE_SIGN + File.separator + randomFile + "." + getExtension(multipartFileSign[0].getOriginalFilename());

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            old.setUrlFileImageSign(urlFileSign);
            old.setFileNameImageSign(fileNameSign);

            repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(old, old));

        }
        return EmployeeResponse.from(old, userDomain);
    }

    @Override
    @Transactional
    public ByteArrayResource downloadSign(String idEmployee) {
        if (idEmployee == null) {
            return null;
        }
        EmployeeDomain old = repo.findById(idEmployee);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(idEmployee));
        }
        ByteArrayResource byteArrayResource = null;
        String dataUrlFile = old.getUrlFileImageSign();
        Path file = Paths.get(dataUrlFile);
        if ( Files.exists(file) ){
            try {
                byte[] byteArray = Files.readAllBytes(file);
                byteArrayResource = new ByteArrayResource(byteArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayResource;
    }

    @Override
    @Transactional
    public EmployeeResponse findByActiveUser() {
        EmployeeUserDomain employeeUserDomain = employeeUserRepo.getByUser(UserResolver.resolveUser().getId());
        if (employeeUserDomain != null){
            return EmployeeResponse.from(employeeUserDomain.getEmployee());
        }
        return null;
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

}
