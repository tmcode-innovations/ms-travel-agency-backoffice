package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierAirLineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPartner;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSupplierAirLine;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.DistrictRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.StateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.DocumentTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.sector.SectorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.PersistSupplierAdministrativeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.PersistSupplierAirLineNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierAirLineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.TouristDestineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierAirLineUseCase;
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
public class SupplierAirLineUseCaseImpl implements SupplierAirLineUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private SupplierAirLineRepo repo;

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
    private SupplierAirLineConverter converter;

    @Autowired
    private SetFieldDefaultSupplierAirLine setFieldDefault;

    @Autowired
    private SetFieldDefaultPartner setFieldDefaultPartner;

    @Autowired
    PersistSupplierAirLineNumberCallbackImpl persistSupplierAirLineNumberCallbackImpl;

    @Override
    @Transactional
    public SupplierAirLineResponse create(SupplierAirLineCreateRequest request) {
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
        supplierTypeDomain = supplierTypeRepo.findOne(SupplierTypeDomain.AIR_LINE, SupplierTypeDomain.AIR_LINE_NAME);

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

        SupplierAirLineDomain newDomain = request.to(
                createdPartnerDomain,
                supplierTypeDomain,
                sectorDomain,
                documentTypeDomain,
                touristDestineDomain
        );
        newDomain.setTypeInheritance(TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName());
        newDomain.setIdentify(persistSupplierAirLineNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        SupplierAirLineDomain createdDomain = repo.create(newDomain);
        return SupplierAirLineResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public SupplierAirLineResponse edit(String id, SupplierAirLineUpdateRequest request) {
        if (id == null) {
            return null;
        }
        SupplierAirLineDomain old = repo.findById(id);
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
        supplierTypeDomain = supplierTypeRepo.findOne(SupplierTypeDomain.AIR_LINE, SupplierTypeDomain.AIR_LINE_NAME);

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

        SupplierAirLineDomain supplierAirLineDomain = request.to(
                editedDomainPartner,
                supplierTypeDomain,
                sectorDomain,
                documentTypeDomain,
                touristDestineDomain
        );
        supplierAirLineDomain.setTypeInheritance(TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName());
        supplierAirLineDomain.setIdentify(old.getIdentify());
        SupplierAirLineDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(supplierAirLineDomain, old));
        return SupplierAirLineResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        SupplierAirLineResponse old = findById(id);
        if (referenceGuard.isReferenced("Supplier", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierAirLineResponse> findAll() {
        return repo.findAll(TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName()).stream().map(supplierDomain -> SupplierAirLineResponse.from(supplierDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierAirLineResponse findById(String id) {
        if (id == null) {
            return null;
        }
        SupplierAirLineDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier found with id '%s'.".formatted(id));
        }
        return SupplierAirLineResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierAirLineSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest) {
       PageDTO<SupplierAirLineDomain> pageDTO = repo.searchAll(supplierSearchRequest);
       SupplierAirLineSearchResponse supplierSearchRespose = new SupplierAirLineSearchResponse();
       supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierAirLineResponse.from(supplierDomain)).toList());
       supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
       supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierAirLineSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest) {
        PageDTO<SupplierAirLineDomain> pageDTO = repo.searchAllFilter(supplierSearchFilterRequest);
        SupplierAirLineSearchResponse supplierSearchRespose = new SupplierAirLineSearchResponse();
        supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierAirLineResponse.from(supplierDomain)).toList());
        supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
        supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierAirLineResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        SupplierAirLineDomain old = repo.findByNameAndTypeInheritance(name, TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName());
        if (old == null) {
            throw new NotFoundException("No supplier found with name '%s'.".formatted(name));
        }
        return SupplierAirLineResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierAirLineResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        SupplierAirLineDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName());
        if (old == null) {
            throw new NotFoundException("No customer found with identify '%s'.".formatted(identify));
        }
        return SupplierAirLineResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierAirLineResponse> findAllSimple() {
        return repo.findAllSimpleWithType().stream().map(simpleBaseDomain -> SupplierAirLineResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierAirLineResponse> findAllSimpleVoucherEgress() {
        return repo.findAllSimpleWithDepartmentRegion().stream().map(simpleBaseDomain -> SupplierAirLineResponse.from(simpleBaseDomain)).toList();
    }
}
