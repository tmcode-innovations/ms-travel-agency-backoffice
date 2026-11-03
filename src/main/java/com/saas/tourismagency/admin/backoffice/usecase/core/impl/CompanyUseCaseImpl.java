package com.saas.tourismagency.admin.backoffice.usecase.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.core.company.CompanyConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPartner;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.core.SetFieldDefaultCompany;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.DistrictRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.StateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.CompanyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.OrganizationRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.PersistCompanyNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.bank.BankRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.core.company.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.account.CurrencyDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CompanyUseCaseImpl implements CompanyUseCase {

    @Autowired
    private CompanyRepo repo;

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
    private StateRepo stateRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private CompanyConverter converter;

    @Autowired
    private SetFieldDefaultCompany setFieldDefault;

    @Autowired
    private SetFieldDefaultPartner setFieldDefaultPartner;

    @Autowired
    PersistCompanyNumberCallbackImpl persistCompanyNumberCallbackImpl;

    @Autowired
    private SupplierLodgingRepo supplierLodgingRepo;

    @Autowired
    private SupplierLodgerRepo supplierLodgerRepo;

    @Autowired
    private SupplierOperatorRepo supplierOperatorRepo;

    @Autowired
    private SupplierTransportRepo supplierTransportRepo;

    @Autowired
    private SupplierRestaurantRepo supplierRestaurantRepo;

    @Autowired
    private SupplierGuideRepo supplierGuideRepo;

    @Autowired
    private SupplierAirLineRepo supplierAirLineRepo;

    @Autowired
    private SupplierAdministrativeRepo supplierAdministrativeRepo;

    @Autowired
    private SupplierGlampingRepo supplierGlampingRepo;

    @Autowired
    private SupplierInstitutionRepo supplierInstitutionRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private BankRepo bankRepo;

    @Override
    @Transactional
    public CompanyResponse create(CompanyCreateRequest request) {
        CompanyDomain companyDomainExist = repo.findByNameAndTenant(request.getName());
        if (companyDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }

        PartnerDomain newPartnerDomain = request.getPartner().to();
        CountryDomain countryDomain = null;
        if (request.getPartner().getAddress().getCountry() != null
                && request.getPartner().getAddress().getCountry().getId() != null
                && !request.getPartner().getAddress().getCountry().getId().isEmpty()) {
            countryDomain = countryRepo.findById(request.getPartner().getAddress().getCountry().getId());
        }
        newPartnerDomain.getAddress().setCountry(countryDomain);
        DepartmentRegionDomain departmentRegionDomain = null;
        if (request.getPartner().getAddress().getDepartmentRegion() != null
                && request.getPartner().getAddress().getDepartmentRegion().getId() != null
                && !request.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
            departmentRegionDomain = departmentRegionRepo.findById(request.getPartner().getAddress().getDepartmentRegion().getId());
        }
        newPartnerDomain.getAddress().setDepartmentRegion(departmentRegionDomain);
        ProvinceDomain provinceDomain = null;
        if (request.getPartner().getAddress().getProvince() != null
                && request.getPartner().getAddress().getProvince().getId() != null
                && !request.getPartner().getAddress().getProvince().getId().isEmpty()) {
            provinceDomain = provinceRepo.findById(request.getPartner().getAddress().getProvince().getId());
        }
        newPartnerDomain.getAddress().setProvince(provinceDomain);
        DistrictDomain districtDomain = null;
        if (request.getPartner().getAddress().getDistrict() != null
                && request.getPartner().getAddress().getDistrict().getId() != null
                && !request.getPartner().getAddress().getDistrict().getId().isEmpty()) {
            districtDomain = districtRepo.findById(request.getPartner().getAddress().getDistrict().getId());
        }
        newPartnerDomain.getAddress().setDistrict(districtDomain);
        StateDomain stateDomain = null;
        if (request.getPartner().getAddress().getState() != null
                && request.getPartner().getAddress().getState().getId() != null
                && !request.getPartner().getAddress().getState().getId().isEmpty()) {
            stateDomain = stateRepo.findById(request.getPartner().getAddress().getState().getId());
        }
        newPartnerDomain.getAddress().setState(stateDomain);
        PartnerDomain createdPartnerDomain = partnerRepo.create(newPartnerDomain);

        OrganizationDomain organizationDomain = null;
        if (request.getOrganization() != null && !request.getOrganization().isEmpty()){
            organizationDomain = organizationRepo.findById(request.getOrganization());
        }

        CompanyDomain newDomain = request.to(createdPartnerDomain, organizationDomain);

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSetting().getLodging() != null
                && request.getSetting().getLodging().getId() != null
                && !request.getSetting().getLodging().getId().isEmpty()) {
            supplierLodgingDomain = supplierLodgingRepo.findById(request.getSetting().getLodging().getId());
        }
        newDomain.getSetting().setLodging(supplierLodgingDomain);

        SupplierLodgerDomain supplierLodgerDomain = null;
        if (request.getSetting().getLodger()!= null
                && request.getSetting().getLodger().getId() != null
                && !request.getSetting().getLodger().getId().isEmpty()) {
            supplierLodgerDomain = supplierLodgerRepo.findById(request.getSetting().getLodger().getId());
        }
        newDomain.getSetting().setLodger(supplierLodgerDomain);

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSetting().getOperator()!= null
                && request.getSetting().getOperator().getId() != null
                && !request.getSetting().getOperator().getId().isEmpty()) {
            supplierOperatorDomain = supplierOperatorRepo.findById(request.getSetting().getOperator().getId());
        }
        newDomain.getSetting().setOperator(supplierOperatorDomain);

        SupplierRestaurantDomain supplierRestaurantDomain = null;
        if (request.getSetting().getRestaurant()!= null
                && request.getSetting().getRestaurant().getId() != null
                && !request.getSetting().getRestaurant().getId().isEmpty()) {
            supplierRestaurantDomain = supplierRestaurantRepo.findById(request.getSetting().getRestaurant().getId());
        }
        newDomain.getSetting().setRestaurant(supplierRestaurantDomain);

        SupplierTransportDomain supplierTransportDomain = null;
        if (request.getSetting().getTransport()!= null
                && request.getSetting().getTransport().getId() != null
                && !request.getSetting().getTransport().getId().isEmpty()) {
            supplierTransportDomain = supplierTransportRepo.findById(request.getSetting().getTransport().getId());
        }
        newDomain.getSetting().setTransport(supplierTransportDomain);

        SupplierGuideDomain supplierGuideDomain = null;
        if (request.getSetting().getGuide()!= null
                && request.getSetting().getGuide().getId() != null
                && !request.getSetting().getGuide().getId().isEmpty()) {
            supplierGuideDomain = supplierGuideRepo.findById(request.getSetting().getGuide().getId());
        }
        newDomain.getSetting().setGuide(supplierGuideDomain);

        SupplierAirLineDomain supplierAirLineDomain = null;
        if (request.getSetting().getAirLine()!= null
                && request.getSetting().getAirLine().getId() != null
                && !request.getSetting().getAirLine().getId().isEmpty()) {
            supplierAirLineDomain = supplierAirLineRepo.findById(request.getSetting().getAirLine().getId());
        }
        newDomain.getSetting().setAirLine(supplierAirLineDomain);

        SupplierAdministrativeDomain supplierAdministrativeDomain = null;
        if (request.getSetting().getAdministrative()!= null
                && request.getSetting().getAdministrative().getId() != null
                && !request.getSetting().getAdministrative().getId().isEmpty()) {
            supplierAdministrativeDomain = supplierAdministrativeRepo.findById(request.getSetting().getAdministrative().getId());
        }
        newDomain.getSetting().setAdministrative(supplierAdministrativeDomain);

        SupplierGlampingDomain supplierGlampingDomain = null;
        if (request.getSetting().getGlamping()!= null
                && request.getSetting().getGlamping().getId() != null
                && !request.getSetting().getGlamping().getId().isEmpty()) {
            supplierGlampingDomain = supplierGlampingRepo.findById(request.getSetting().getGlamping().getId());
        }
        newDomain.getSetting().setGlamping(supplierGlampingDomain);

        SupplierInstitutionDomain supplierInstitutionDomain = null;
        if (request.getSetting().getInstitution()!= null
                && request.getSetting().getInstitution().getId() != null
                && !request.getSetting().getInstitution().getId().isEmpty()) {
            supplierInstitutionDomain = supplierInstitutionRepo.findById(request.getSetting().getInstitution().getId());
        }
        newDomain.getSetting().setInstitution(supplierInstitutionDomain);

        CurrencyDomain currencyUsdDomain = null;
        if (request.getSetting().getCurrencyUsdDefault()!= null
                && request.getSetting().getCurrencyUsdDefault().getId() != null
                && !request.getSetting().getCurrencyUsdDefault().getId().isEmpty()) {
            currencyUsdDomain = currencyRepo.findById(request.getSetting().getCurrencyUsdDefault().getId());
        }
        newDomain.getSetting().setCurrencyUsdDefault(currencyUsdDomain);

        CurrencyDomain currencySolesDomain = null;
        if (request.getSetting().getCurrencySolesDefault()!= null
                && request.getSetting().getCurrencySolesDefault().getId() != null
                && !request.getSetting().getCurrencySolesDefault().getId().isEmpty()) {
            currencySolesDomain = currencyRepo.findById(request.getSetting().getCurrencySolesDefault().getId());
        }
        newDomain.getSetting().setCurrencySolesDefault(currencySolesDomain);

        newDomain.setIdentify(persistCompanyNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        CompanyDomain createdDomain = repo.create(newDomain);
        return CompanyResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public CompanyResponse edit(String id, CompanyUpdateRequest request) {
        if (id == null) {
            return null;
        }
        CompanyDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No company found with id '%s'.".formatted(id));
        }
        CompanyDomain companyDomainExist = repo.findByNameAndTenant(request.getName());
        if (companyDomainExist != null && !companyDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }

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
        StateDomain stateDomain = null;
        if (request.getPartner().getAddress().getState() != null
                && request.getPartner().getAddress().getState().getId() != null
                && !request.getPartner().getAddress().getState().getId().isEmpty()) {
            stateDomain = stateRepo.findById(request.getPartner().getAddress().getState().getId());
        }
        updatePartnerDomain.getAddress().setState(stateDomain);
        PartnerDomain editedDomainPartner = partnerRepo.edit(setFieldDefaultPartner.setDefaultFieldBaseUpdated(updatePartnerDomain, oldPartner));

        OrganizationDomain organizationDomain = null;
        if (request.getOrganization() != null && !request.getOrganization().isEmpty()){
            organizationDomain = organizationRepo.findById(request.getOrganization());
        }

        CompanyDomain companyDomain = request.to(editedDomainPartner, organizationDomain);

        SupplierLodgingDomain supplierLodgingDomain = null;
        if (request.getSetting().getLodging() != null
                && request.getSetting().getLodging().getId() != null
                && !request.getSetting().getLodging().getId().isEmpty()) {
            supplierLodgingDomain = supplierLodgingRepo.findById(request.getSetting().getLodging().getId());
        }
        companyDomain.getSetting().setLodging(supplierLodgingDomain);

        SupplierLodgerDomain supplierLodgerDomain = null;
        if (request.getSetting().getLodger()!= null
                && request.getSetting().getLodger().getId() != null
                && !request.getSetting().getLodger().getId().isEmpty()) {
            supplierLodgerDomain = supplierLodgerRepo.findById(request.getSetting().getLodger().getId());
        }
        companyDomain.getSetting().setLodger(supplierLodgerDomain);

        SupplierOperatorDomain supplierOperatorDomain = null;
        if (request.getSetting().getOperator()!= null
                && request.getSetting().getOperator().getId() != null
                && !request.getSetting().getOperator().getId().isEmpty()) {
            supplierOperatorDomain = supplierOperatorRepo.findById(request.getSetting().getOperator().getId());
        }
        companyDomain.getSetting().setOperator(supplierOperatorDomain);

        SupplierRestaurantDomain supplierRestaurantDomain = null;
        if (request.getSetting().getRestaurant()!= null
                && request.getSetting().getRestaurant().getId() != null
                && !request.getSetting().getRestaurant().getId().isEmpty()) {
            supplierRestaurantDomain = supplierRestaurantRepo.findById(request.getSetting().getRestaurant().getId());
        }
        companyDomain.getSetting().setRestaurant(supplierRestaurantDomain);

        SupplierTransportDomain supplierTransportDomain = null;
        if (request.getSetting().getTransport()!= null
                && request.getSetting().getTransport().getId() != null
                && !request.getSetting().getTransport().getId().isEmpty()) {
            supplierTransportDomain = supplierTransportRepo.findById(request.getSetting().getTransport().getId());
        }
        companyDomain.getSetting().setTransport(supplierTransportDomain);

        SupplierGuideDomain supplierGuideDomain = null;
        if (request.getSetting().getGuide()!= null
                && request.getSetting().getGuide().getId() != null
                && !request.getSetting().getGuide().getId().isEmpty()) {
            supplierGuideDomain = supplierGuideRepo.findById(request.getSetting().getGuide().getId());
        }
        companyDomain.getSetting().setGuide(supplierGuideDomain);

        SupplierAirLineDomain supplierAirLineDomain = null;
        if (request.getSetting().getAirLine()!= null
                && request.getSetting().getAirLine().getId() != null
                && !request.getSetting().getAirLine().getId().isEmpty()) {
            supplierAirLineDomain = supplierAirLineRepo.findById(request.getSetting().getAirLine().getId());
        }
        companyDomain.getSetting().setAirLine(supplierAirLineDomain);

        SupplierAdministrativeDomain supplierAdministrativeDomain = null;
        if (request.getSetting().getAirLine()!= null
                && request.getSetting().getAdministrative().getId() != null
                && !request.getSetting().getAdministrative().getId().isEmpty()) {
            supplierAdministrativeDomain = supplierAdministrativeRepo.findById(request.getSetting().getAdministrative().getId());
        }
        companyDomain.getSetting().setAdministrative(supplierAdministrativeDomain);

        SupplierGlampingDomain supplierGlampingDomain = null;
        if (request.getSetting().getGlamping()!= null
                && request.getSetting().getGlamping().getId() != null
                && !request.getSetting().getGlamping().getId().isEmpty()) {
            supplierGlampingDomain = supplierGlampingRepo.findById(request.getSetting().getGlamping().getId());
        }
        companyDomain.getSetting().setGlamping(supplierGlampingDomain);

        SupplierInstitutionDomain supplierInstitutionDomain = null;
        if (request.getSetting().getInstitution()!= null
                && request.getSetting().getInstitution().getId() != null
                && !request.getSetting().getInstitution().getId().isEmpty()) {
            supplierInstitutionDomain = supplierInstitutionRepo.findById(request.getSetting().getInstitution().getId());
        }
        companyDomain.getSetting().setInstitution(supplierInstitutionDomain);

        CurrencyDomain currencyUsdDomain = null;
        if (request.getSetting().getCurrencyUsdDefault()!= null
                && request.getSetting().getCurrencyUsdDefault().getId() != null
                && !request.getSetting().getCurrencyUsdDefault().getId().isEmpty()) {
            currencyUsdDomain = currencyRepo.findById(request.getSetting().getCurrencyUsdDefault().getId());
        }
        companyDomain.getSetting().setCurrencyUsdDefault(currencyUsdDomain);

        CurrencyDomain currencySolesDomain = null;
        if (request.getSetting().getCurrencySolesDefault()!= null
                && request.getSetting().getCurrencySolesDefault().getId() != null
                && !request.getSetting().getCurrencySolesDefault().getId().isEmpty()) {
            currencySolesDomain = currencyRepo.findById(request.getSetting().getCurrencySolesDefault().getId());
        }
        companyDomain.getSetting().setCurrencySolesDefault(currencySolesDomain);

        CurrencyDomain currencyVoucherEgressDefaultDomain = null;
        if (request.getSetting().getCurrencyVoucherEgressDefault()!= null
                && request.getSetting().getCurrencyVoucherEgressDefault().getId() != null
                && !request.getSetting().getCurrencyVoucherEgressDefault().getId().isEmpty()) {
            currencyVoucherEgressDefaultDomain = currencyRepo.findById(request.getSetting().getCurrencyVoucherEgressDefault().getId());
        }
        companyDomain.getSetting().setCurrencyVoucherEgressDefault(currencyVoucherEgressDefaultDomain);

        BankDomain bankVoucherEgressDefaultDomain = null;
        if (request.getSetting().getBankVoucherEgressDefault()!= null
                && request.getSetting().getBankVoucherEgressDefault().getId() != null
                && !request.getSetting().getBankVoucherEgressDefault().getId().isEmpty()) {
            bankVoucherEgressDefaultDomain = bankRepo.findById(request.getSetting().getBankVoucherEgressDefault().getId());
        }
        companyDomain.getSetting().setBankVoucherEgressDefault(bankVoucherEgressDefaultDomain);

        companyDomain.setIdentify(old.getIdentify());
        CompanyDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(companyDomain, old));
        return CompanyResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        CompanyResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> findAll() {
        return repo.findAll(false).stream().map(companyDomain -> CompanyResponse.from(companyDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse findById(String id) {
        if (id == null) {
            return null;
        }
        CompanyDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No company found with id '%s'.".formatted(id));
        }
        return CompanyResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanySearchResponse searchAll(CompanySearchRequest companySearchRequest) {
       PageDTO<CompanyDomain> pageDTO = repo.searchAll(companySearchRequest);
       CompanySearchResponse companySearchRespose = new CompanySearchResponse();
       companySearchRespose.setCompanies(pageDTO.getContent().stream().map(companyDomain ->  CompanyResponse.from(companyDomain)).toList());
       companySearchRespose.setTotalElements(pageDTO.getTotalElements());
       companySearchRespose.setTotalPages(pageDTO.getTotalPages());
       return companySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CompanySearchResponse searchFilterAll(CompanySearchFilterRequest companySearchFilterRequest) {
        PageDTO<CompanyDomain> pageDTO = repo.searchAllFilter(companySearchFilterRequest);
        CompanySearchResponse companySearchRespose = new CompanySearchResponse();
        companySearchRespose.setCompanies(pageDTO.getContent().stream().map(companyDomain ->  CompanyResponse.from(companyDomain)).toList());
        companySearchRespose.setTotalElements(pageDTO.getTotalElements());
        companySearchRespose.setTotalPages(pageDTO.getTotalPages());
        return companySearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        CompanyDomain old = repo.findByNameAndTenant(name);
        if (old == null) {
            throw new NotFoundException("No company found with name '%s'.".formatted(name));
        }
        return CompanyResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        CompanyDomain old = repo.findByIdentifyAndTenant(identify);
        if (old == null) {
            throw new NotFoundException("No company found with identify '%s'.".formatted(identify));
        }
        return CompanyResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse findByTenant(String tenant) {
        if (tenant == null) {
            return null;
        }
        CompanyDomain old = repo.findByTenant(tenant);
        if (old == null) {
            throw new NotFoundException("No company found with tenant '%s'.".formatted(tenant));
        }
        return CompanyResponse.from(old);
    }

    @Override
    @Transactional
    public CompanyDomain getMyActiveCompany() {
        CompanyDomain old = repo.findMyCompany();
        if (old == null) {
            throw new NotFoundException("No active company found");
        }
        return old;
    }
}
