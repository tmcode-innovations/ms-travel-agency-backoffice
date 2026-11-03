package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.ReferenceGuard;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier.SupplierRestaurantConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.base.SetFieldDefaultPartner;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultSupplierRestaurant;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.country.CountryRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.district.DistrictRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.partner.PartnerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.province.ProvinceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.state.StateRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.documenttype.DocumentTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.restauranttype.RestaurantTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.sector.SectorRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.PersistSupplierAdministrativeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.PersistSupplierRestaurantNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRestaurantRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.suppliertype.SupplierTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.touristdestine.TouristDestineRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.base.SimpleBaseResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.*;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.SupplierRestaurantUseCase;
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
public class SupplierRestaurantUseCaseImpl implements SupplierRestaurantUseCase {
    @Autowired
    private ReferenceGuard referenceGuard;


    @Autowired
    private SupplierRestaurantRepo repo;

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
    private RestaurantTypeRepo restaurantTypeRepo;

    @Autowired
    private SupplierRestaurantConverter converter;

    @Autowired
    private SetFieldDefaultSupplierRestaurant setFieldDefault;

    @Autowired
    private SetFieldDefaultPartner setFieldDefaultPartner;

    @Autowired
    PersistSupplierRestaurantNumberCallbackImpl persistSupplierRestaurantNumberCallbackImpl;

    @Override
    @Transactional
    public SupplierRestaurantResponse create(SupplierRestaurantCreateRequest request) {
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
        supplierTypeDomain = supplierTypeRepo.findOne(SupplierTypeDomain.RESTAURANT, SupplierTypeDomain.RESTAURANT_NAME);

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

        RestaurantTypeDomain restaurantTypeDomain = null;
        if (request.getRestaurantType() != null && !request.getRestaurantType().isEmpty()){
            restaurantTypeDomain = restaurantTypeRepo.findById(request.getRestaurantType());
        }

        SupplierRestaurantDomain newDomain = request.to(
                createdPartnerDomain,
                supplierTypeDomain,
                sectorDomain,
                documentTypeDomain,
                touristDestineDomain,
                restaurantTypeDomain
        );
        newDomain.setTypeInheritance(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName());
        newDomain.setIdentify(persistSupplierRestaurantNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        SupplierRestaurantDomain createdDomain = repo.create(newDomain);
        return SupplierRestaurantResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public SupplierRestaurantResponse edit(String id, SupplierRestaurantUpdateRequest request) {
        if (id == null) {
            return null;
        }
        SupplierRestaurantDomain old = repo.findById(id);
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
        supplierTypeDomain = supplierTypeRepo.findOne(SupplierTypeDomain.RESTAURANT, SupplierTypeDomain.RESTAURANT_NAME);

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

        RestaurantTypeDomain restaurantTypeDomain = null;
        if (request.getRestaurantType() != null && !request.getRestaurantType().isEmpty()){
            restaurantTypeDomain = restaurantTypeRepo.findById(request.getRestaurantType());
        }

        SupplierRestaurantDomain supplierRestaurantDomain = request.to(
                editedDomainPartner,
                supplierTypeDomain,
                sectorDomain,
                documentTypeDomain,
                touristDestineDomain,
                restaurantTypeDomain
        );
        supplierRestaurantDomain.setTypeInheritance(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName());
        supplierRestaurantDomain.setIdentify(old.getIdentify());
        SupplierRestaurantDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(supplierRestaurantDomain, old));
        return SupplierRestaurantResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        SupplierRestaurantResponse old = findById(id);
        if (referenceGuard.isReferenced("Supplier", old.getId())) {
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_REFERENCED);
        }
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierRestaurantResponse> findAll() {
        return repo.findAll(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()).stream().map(supplierDomain -> SupplierRestaurantResponse.from(supplierDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierRestaurantResponse findById(String id) {
        if (id == null) {
            return null;
        }
        SupplierRestaurantDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No supplier found with id '%s'.".formatted(id));
        }
        return SupplierRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierRestaurantSearchResponse searchAll(SupplierSearchRequest supplierSearchRequest) {
       PageDTO<SupplierRestaurantDomain> pageDTO = repo.searchAll(supplierSearchRequest);
       SupplierRestaurantSearchResponse supplierSearchRespose = new SupplierRestaurantSearchResponse();
       supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierRestaurantResponse.from(supplierDomain)).toList());
       supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
       supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierRestaurantSearchResponse searchFilterAll(SupplierSearchFilterRequest supplierSearchFilterRequest) {
        PageDTO<SupplierRestaurantDomain> pageDTO = repo.searchAllFilter(supplierSearchFilterRequest);
        SupplierRestaurantSearchResponse supplierSearchRespose = new SupplierRestaurantSearchResponse();
        supplierSearchRespose.setSuppliers(pageDTO.getContent().stream().map(supplierDomain ->  SupplierRestaurantResponse.from(supplierDomain)).toList());
        supplierSearchRespose.setTotalElements(pageDTO.getTotalElements());
        supplierSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return supplierSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierRestaurantResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        SupplierRestaurantDomain old = repo.findByNameAndTypeInheritance(name, TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName());
        if (old == null) {
            throw new NotFoundException("No supplier found with name '%s'.".formatted(name));
        }
        return SupplierRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierRestaurantResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        SupplierRestaurantDomain old = repo.findByIdentifyAndTypeInheritance(identify, TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName());
        if (old == null) {
            throw new NotFoundException("No customer found with identify '%s'.".formatted(identify));
        }
        return SupplierRestaurantResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierRestaurantResponse> findAllSimple() {
        return repo.findAllSimpleWithType().stream().map(simpleBaseDomain -> SupplierRestaurantResponse.from(simpleBaseDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierRestaurantResponse> findAllSimpleVoucherEgress() {
        return repo.findAllSimpleWithDepartmentRegion().stream().map(simpleBaseDomain -> SupplierRestaurantResponse.from(simpleBaseDomain)).toList();
    }
}
