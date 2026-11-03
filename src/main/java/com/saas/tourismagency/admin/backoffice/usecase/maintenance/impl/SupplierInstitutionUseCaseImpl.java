package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierInstitutionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPartner;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSupplierInstitution;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.DistrictRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.StateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.DocumentTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.sector.SectorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.PersistSupplierInstitutionNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierInstitutionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.TouristDestineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierInstitutionUseCase;
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
public class SupplierInstitutionUseCaseImpl implements SupplierInstitutionUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private SupplierInstitutionRepo repo;

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
    private SupplierTypeRepo supplierTypeRepo;

    @Autowired
    private SectorRepo sectorRepo;

    @Autowired
    private DocumentTypeRepo documentTypeRepo;

    @Autowired
    private TouristDestineRepo touristDestineRepo;

    @Autowired
    private SupplierInstitutionConverter converter;

    @Autowired
    private SetFieldDefaultSupplierInstitution setFieldDefault;

    @Autowired
    private SetFieldDefaultPartner setFieldDefaultPartner;

    @Autowired
    PersistSupplierInstitutionNumberCallbackImpl persistSupplierInstitutionNumberCallbackImpl;

    @Override
    @Transactional
    public SupplierInstitutionResponse create(SupplierInstitutionCreateRequest request) {
        if (repo.existsByNameGlobal(request.getName(), null)){
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
        newPartnerDomain.setTenant(UserResolver.resolveUser().getTenant());
        PartnerDomain createdPartnerDomain = partnerRepo.create(newPartnerDomain);

        SupplierTypeDomain supplierTypeDomain = null;
        supplierTypeDomain = supplierTypeRepo.findOne(SupplierTypeDomain.INSTITUTION, SupplierTypeDomain.INSTITUTION_NAME);

        SectorDomain sectorDomain = null;
        if (request.getSector() != null && !request.getSector().isEmpty()){
            sectorDomain = sectorRepo.findById(request.getSector());
        }

        DocumentTypeDomain documentTypeDomain = null;
        if (request.getDocumentType() != null && !request.getDocumentType().isEmpty()){
            documentTypeDomain = documentTypeRepo.findById(request.getDocumentType());
        }

        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            touristDestineDomain = touristDestineRepo.findById(request.getTouristDestine());
        }

        SupplierInstitutionDomain newDomain = request.to(
                createdPartnerDomain,
                supplierTypeDomain,
                sectorDomain,
                documentTypeDomain,
                touristDestineDomain
        );
        newDomain.setTypeInheritance(TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName());
        newDomain.setIdentify(persistSupplierInstitutionNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        SupplierInstitutionDomain createdDomain = repo.create(newDomain);
        return SupplierInstitutionResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public SupplierInstitutionResponse edit(String id, SupplierInstitutionUpdateRequest request) {
        if (id == null) {
            return null;
        }
        SupplierInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier found with id '%s'.".formatted(id));
        }
        if (repo.existsByNameGlobal(request.getName(), old.getId())){
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

        SupplierTypeDomain supplierTypeDomain = null;
        supplierTypeDomain = supplierTypeRepo.findOne(SupplierTypeDomain.INSTITUTION, SupplierTypeDomain.INSTITUTION_NAME);

        SectorDomain sectorDomain = null;
        if (request.getSector() != null && !request.getSector().isEmpty()){
            sectorDomain = sectorRepo.findById(request.getSector());
        }

        DocumentTypeDomain documentTypeDomain = null;
        if (request.getDocumentType() != null && !request.getDocumentType().isEmpty()){
            documentTypeDomain = documentTypeRepo.findById(request.getDocumentType());
        }

        TouristDestineDomain touristDestineDomain = null;
        if (request.getTouristDestine() != null && !request.getTouristDestine().isEmpty()){
            touristDestineDomain = touristDestineRepo.findById(request.getTouristDestine());
        }

        SupplierInstitutionDomain supplierInstitutionDomain = request.to(
                editedDomainPartner,
                supplierTypeDomain,
                sectorDomain,
                documentTypeDomain,
                touristDestineDomain
        );
        supplierInstitutionDomain.setTypeInheritance(TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName());
        supplierInstitutionDomain.setIdentify(old.getIdentify());
        SupplierInstitutionDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(supplierInstitutionDomain, old));
        return SupplierInstitutionResponse.from(editedDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteById(String id) {
        SupplierInstitutionResponse old = findById(id);
        if (referenceGuard.isReferenced("Supplier", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierInstitutionResponse> findAll() {
        return repo.findAll(TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName()).stream().map(supplierDomain -> SupplierInstitutionResponse.from(supplierDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierInstitutionResponse findById(String id) {
        if (id == null) {
            return null;
        }
        SupplierInstitutionDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier found with id '%s'.".formatted(id));
        }
        return SupplierInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierInstitutionSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest) {
       PageDTO<SupplierInstitutionDomain> pageDTO = repo.searchAll(supplierSearchRequest);
       SupplierInstitutionSearchResponse supplierSearchRespose = new SupplierInstitutionSearchResponse();
       supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierInstitutionResponse.from(supplierDomain)).toList());
       supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
       supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierInstitutionSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest) {
        PageDTO<SupplierInstitutionDomain> pageDTO = repo.searchAllFilter(supplierSearchFilterRequest);
        SupplierInstitutionSearchResponse supplierSearchRespose = new SupplierInstitutionSearchResponse();
        supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierInstitutionResponse.from(supplierDomain)).toList());
        supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
        supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierInstitutionResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        SupplierInstitutionDomain old = repo.findByNameAndTypeInheritance(name, TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName());
        if (old == null) {
            throw new NotFoundException("No supplier found with name '%s'.".formatted(name));
        }
        return SupplierInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierInstitutionResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        SupplierInstitutionDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName());
        if (old == null) {
            throw new NotFoundException("No customer found with identify '%s'.".formatted(identify));
        }
        return SupplierInstitutionResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierInstitutionResponse> findAllSimple() {
        return repo.findAllSimpleWithType().stream().map(simpleBaseDomain -> SupplierInstitutionResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierInstitutionResponse> findAllSimpleVoucherEgress() {
        return repo.findAllSimpleWithDepartmentRegion().stream().map(simpleBaseDomain -> SupplierInstitutionResponse.from(simpleBaseDomain)).toList();
    }
}
