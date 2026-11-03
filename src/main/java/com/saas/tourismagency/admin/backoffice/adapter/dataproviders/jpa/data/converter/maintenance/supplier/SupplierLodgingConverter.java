package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.lodgingcategory.LodgingCategoryConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
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
public class SupplierLodgingConverter implements Converter<SupplierLodgingDomain, SupplierLodgingEntity> {

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
    private LodgingCategoryConverter lodgingCategoryConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public SupplierLodgingDomain toDomain(SupplierLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgingDomain supplierLodgingDomain = new SupplierLodgingDomain();

        supplierLodgingDomain.setId( source.getId() );
        supplierLodgingDomain.setDeleted( source.getDeleted() );
        supplierLodgingDomain.setEnabled( source.getEnabled() );
        supplierLodgingDomain.setMigrated( source.getMigrated() );
        supplierLodgingDomain.setCreatedAt( source.getCreatedAt() );
        supplierLodgingDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierLodgingDomain.setDeletedAt( source.getDeletedAt() );
        supplierLodgingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierLodgingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierLodgingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierLodgingDomain.setIdentifierName( source.getIdentifierName() );
        supplierLodgingDomain.setTenant( source.getTenant() );
        supplierLodgingDomain.setName( source.getName() );
        supplierLodgingDomain.setDescription( source.getDescription() );
        supplierLodgingDomain.setIdentify( source.getIdentify() );
        supplierLodgingDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierLodgingDomain.setRuc( source.getRuc() );
        supplierLodgingDomain.setConfirmation( source.getConfirmation() );
        supplierLodgingDomain.setReConfirmation( source.getReConfirmation() );
        supplierLodgingDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierLodgingDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierLodgingDomain.setSummary( source.getSummary() );
        supplierLodgingDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierLodgingDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierLodgingDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierLodgingDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierLodgingDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierLodgingDomain.setAdministrative( source.getAdministrative() );
        supplierLodgingDomain.setCheckIn( source.getCheckIn() );
        supplierLodgingDomain.setCheckOut( source.getCheckOut() );
        supplierLodgingDomain.setServiceGroup( source.getServiceGroup() );
        supplierLodgingDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierLodgingDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierLodgingDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierLodgingDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierLodgingDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierLodgingDomain.setLodgingType( source.getLodgingType() );
        supplierLodgingDomain.setLodgingCategory( lodgingCategoryConverter.toDomain( source.getLodgingCategory() ) );
        supplierLodgingDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierLodgingDomain;
    }

    @Override
    public SupplierLodgingEntity toEntity(SupplierLodgingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgingEntity supplierLodgingEntity = new SupplierLodgingEntity();

        supplierLodgingEntity.setId( source.getId() );
        supplierLodgingEntity.setDeleted( source.getDeleted() );
        supplierLodgingEntity.setEnabled( source.getEnabled() );
        supplierLodgingEntity.setMigrated( source.getMigrated() );
        supplierLodgingEntity.setCreatedAt( source.getCreatedAt() );
        supplierLodgingEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierLodgingEntity.setDeletedAt( source.getDeletedAt() );
        supplierLodgingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierLodgingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierLodgingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierLodgingEntity.setIdentifierName( source.getIdentifierName() );
        supplierLodgingEntity.setTenant( source.getTenant() );
        supplierLodgingEntity.setName( source.getName() );
        supplierLodgingEntity.setDescription( source.getDescription() );
        supplierLodgingEntity.setIdentify( source.getIdentify() );
        supplierLodgingEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierLodgingEntity.setRuc( source.getRuc() );
        supplierLodgingEntity.setConfirmation( source.getConfirmation() );
        supplierLodgingEntity.setReConfirmation( source.getReConfirmation() );
        supplierLodgingEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierLodgingEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierLodgingEntity.setSummary( source.getSummary() );
        supplierLodgingEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierLodgingEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierLodgingEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierLodgingEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierLodgingEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierLodgingEntity.setAdministrative( source.getAdministrative() );
        supplierLodgingEntity.setCheckIn( source.getCheckIn() );
        supplierLodgingEntity.setCheckOut( source.getCheckOut() );
        supplierLodgingEntity.setServiceGroup( source.getServiceGroup() );
        supplierLodgingEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierLodgingEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierLodgingEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierLodgingEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierLodgingEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierLodgingEntity.setLodgingType( source.getLodgingType() );
        supplierLodgingEntity.setLodgingCategory( lodgingCategoryConverter.toEntity( source.getLodgingCategory() ) );
        supplierLodgingEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierLodgingEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierLodgingDomain toDomainSimpleBaseWithType(SupplierLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgingDomain supplierLodgingDomain = new SupplierLodgingDomain();

        supplierLodgingDomain.setId( source.getId() );
        supplierLodgingDomain.setName( source.getName() );
        supplierLodgingDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierLodgingDomain.setSupplierType( new SupplierTypeDomain() );
            supplierLodgingDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierLodgingDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierLodgingDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierLodgingDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierLodgingDomain;
    }

    public List<SupplierLodgingDomain> toDomainAllSimpleWithType(List<SupplierLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierLodgingDomain toDomainSimpleBaseWithDepartmentRegion(SupplierLodgingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgingDomain supplierLodgingDomain = new SupplierLodgingDomain();

        supplierLodgingDomain.setId( source.getId() );
        supplierLodgingDomain.setName( source.getName() );
        supplierLodgingDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierLodgingDomain.setSupplierType( new SupplierTypeDomain() );
            supplierLodgingDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierLodgingDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierLodgingDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierLodgingDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierLodgingDomain.setPartner( new PartnerDomain());
            supplierLodgingDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierLodgingDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierLodgingDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierLodgingDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierLodgingDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierLodgingDomain;
    }

    public List<SupplierLodgingDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierLodgingDomain> toDomainAllSimpleToDomainCaseListSupplierLodging(List<SupplierLodgingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierLodgingDomain supplierLodgingDomain = new SupplierLodgingDomain();
            supplierLodgingDomain.setId(entity.getId());
            supplierLodgingDomain.setIdentify(entity.getIdentify());
            supplierLodgingDomain.setName(entity.getName());
            return supplierLodgingDomain;
        }).toList();
    }

    public PageDTO<SupplierLodgingDomain> toDomainPageCaseListSupplierLodging(Page<SupplierLodgingEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierLodgingDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierLodging(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
