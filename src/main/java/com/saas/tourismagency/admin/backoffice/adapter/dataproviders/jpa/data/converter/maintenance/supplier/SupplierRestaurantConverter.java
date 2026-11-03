package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.restauranttype.RestaurantTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierRestaurantEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierRestaurantDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class SupplierRestaurantConverter implements Converter<SupplierRestaurantDomain, SupplierRestaurantEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PartnerConverter partnerConverter;

    @Autowired
    private SupplierTypeConverter supplierTypeConverter;

    @Autowired
    private SectorConverter sectorConverter;

    @Autowired
    private DocumentTypeConverter documentTypeConverter;

    @Autowired
    private TouristDestineConverter touristDestineConverter;

    @Autowired
    private RestaurantTypeConverter restaurantTypeConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public SupplierRestaurantDomain toDomain(SupplierRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierRestaurantDomain supplierRestaurantDomain = new SupplierRestaurantDomain();

        supplierRestaurantDomain.setId( source.getId() );
        supplierRestaurantDomain.setDeleted( source.getDeleted() );
        supplierRestaurantDomain.setEnabled( source.getEnabled() );
        supplierRestaurantDomain.setMigrated( source.getMigrated() );
        supplierRestaurantDomain.setCreatedAt( source.getCreatedAt() );
        supplierRestaurantDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierRestaurantDomain.setDeletedAt( source.getDeletedAt() );
        supplierRestaurantDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierRestaurantDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierRestaurantDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierRestaurantDomain.setIdentifierName( source.getIdentifierName() );
        supplierRestaurantDomain.setTenant( source.getTenant() );
        supplierRestaurantDomain.setName( source.getName() );
        supplierRestaurantDomain.setDescription( source.getDescription() );
        supplierRestaurantDomain.setIdentify( source.getIdentify() );
        supplierRestaurantDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierRestaurantDomain.setRuc( source.getRuc() );
        supplierRestaurantDomain.setConfirmation( source.getConfirmation() );
        supplierRestaurantDomain.setReConfirmation( source.getReConfirmation() );
        supplierRestaurantDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierRestaurantDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierRestaurantDomain.setSummary( source.getSummary() );
        supplierRestaurantDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierRestaurantDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierRestaurantDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierRestaurantDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierRestaurantDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierRestaurantDomain.setAdministrative( source.getAdministrative() );
        supplierRestaurantDomain.setCheckIn( source.getCheckIn() );
        supplierRestaurantDomain.setCheckOut( source.getCheckOut() );
        supplierRestaurantDomain.setServiceGroup( source.getServiceGroup() );
        supplierRestaurantDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierRestaurantDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierRestaurantDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierRestaurantDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierRestaurantDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierRestaurantDomain.setRestaurantType( restaurantTypeConverter.toDomain( source.getRestaurantType() ) );
        supplierRestaurantDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierRestaurantDomain;
    }

    @Override
    public SupplierRestaurantEntity toEntity(SupplierRestaurantDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierRestaurantEntity supplierRestaurantEntity = new SupplierRestaurantEntity();

        supplierRestaurantEntity.setId( source.getId() );
        supplierRestaurantEntity.setDeleted( source.getDeleted() );
        supplierRestaurantEntity.setEnabled( source.getEnabled() );
        supplierRestaurantEntity.setMigrated( source.getMigrated() );
        supplierRestaurantEntity.setCreatedAt( source.getCreatedAt() );
        supplierRestaurantEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierRestaurantEntity.setDeletedAt( source.getDeletedAt() );
        supplierRestaurantEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierRestaurantEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierRestaurantEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierRestaurantEntity.setIdentifierName( source.getIdentifierName() );
        supplierRestaurantEntity.setTenant( source.getTenant() );
        supplierRestaurantEntity.setName( source.getName() );
        supplierRestaurantEntity.setDescription( source.getDescription() );
        supplierRestaurantEntity.setIdentify( source.getIdentify() );
        supplierRestaurantEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierRestaurantEntity.setRuc( source.getRuc() );
        supplierRestaurantEntity.setConfirmation( source.getConfirmation() );
        supplierRestaurantEntity.setReConfirmation( source.getReConfirmation() );
        supplierRestaurantEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierRestaurantEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierRestaurantEntity.setSummary( source.getSummary() );
        supplierRestaurantEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierRestaurantEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierRestaurantEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierRestaurantEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierRestaurantEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierRestaurantEntity.setAdministrative( source.getAdministrative() );
        supplierRestaurantEntity.setCheckIn( source.getCheckIn() );
        supplierRestaurantEntity.setCheckOut( source.getCheckOut() );
        supplierRestaurantEntity.setServiceGroup( source.getServiceGroup() );
        supplierRestaurantEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierRestaurantEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierRestaurantEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierRestaurantEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierRestaurantEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierRestaurantEntity.setRestaurantType( restaurantTypeConverter.toEntity( source.getRestaurantType() ) );
        supplierRestaurantEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierRestaurantEntity;
    }

    public Set<ServiceDomain> serviceEntitySetToServiceDomainSet(Set<ServiceEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<ServiceDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServiceEntity serviceEntity : set ) {
            set1.add( serviceConverter.toDomain( serviceEntity ) );
        }
        return set1;
    }

    protected Set<ServiceEntity> serviceDomainSetToServiceEntitySet(Set<ServiceDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<ServiceEntity> set1 = new LinkedHashSet<ServiceEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ServiceDomain serviceDomain : set ) {
            set1.add( serviceConverter.toEntity( serviceDomain ) );
        }
        return set1;
    }

    public IdBaseDomain toDomainIdBase(SupplierRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierRestaurantDomain toDomainSimpleBaseWithType(SupplierRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierRestaurantDomain supplierRestaurantDomain = new SupplierRestaurantDomain();

        supplierRestaurantDomain.setId( source.getId() );
        supplierRestaurantDomain.setName( source.getName() );
        supplierRestaurantDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierRestaurantDomain.setSupplierType( new SupplierTypeDomain() );
            supplierRestaurantDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierRestaurantDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierRestaurantDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierRestaurantDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierRestaurantDomain;
    }

    public List<SupplierRestaurantDomain> toDomainAllSimpleWithType(List<SupplierRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierRestaurantDomain toDomainSimpleBaseWithDepartmentRegion(SupplierRestaurantEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierRestaurantDomain supplierRestaurantDomain = new SupplierRestaurantDomain();

        supplierRestaurantDomain.setId( source.getId() );
        supplierRestaurantDomain.setName( source.getName() );
        supplierRestaurantDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierRestaurantDomain.setSupplierType( new SupplierTypeDomain() );
            supplierRestaurantDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierRestaurantDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierRestaurantDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierRestaurantDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierRestaurantDomain.setPartner( new PartnerDomain());
            supplierRestaurantDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierRestaurantDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierRestaurantDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierRestaurantDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierRestaurantDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierRestaurantDomain;
    }

    public List<SupplierRestaurantDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierRestaurantDomain> toDomainAllSimpleToDomainCaseListSupplierRestaurant(List<SupplierRestaurantEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierRestaurantDomain supplierRestaurantDomain = new SupplierRestaurantDomain();
            supplierRestaurantDomain.setId(entity.getId());
            supplierRestaurantDomain.setIdentify(entity.getIdentify());
            supplierRestaurantDomain.setName(entity.getName());
            return supplierRestaurantDomain;
        }).toList();
    }

    public PageDTO<SupplierRestaurantDomain> toDomainPageCaseListSupplierRestaurant(Page<SupplierRestaurantEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierRestaurantDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierRestaurant(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
