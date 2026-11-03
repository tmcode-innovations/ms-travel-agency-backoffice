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
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierGlampingEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGlampingDomain;
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
public class SupplierGlampingConverter implements Converter<SupplierGlampingDomain, SupplierGlampingEntity> {

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
    public SupplierGlampingDomain toDomain(SupplierGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGlampingDomain supplierGlampingDomain = new SupplierGlampingDomain();

        supplierGlampingDomain.setId( source.getId() );
        supplierGlampingDomain.setDeleted( source.getDeleted() );
        supplierGlampingDomain.setEnabled( source.getEnabled() );
        supplierGlampingDomain.setMigrated( source.getMigrated() );
        supplierGlampingDomain.setCreatedAt( source.getCreatedAt() );
        supplierGlampingDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierGlampingDomain.setDeletedAt( source.getDeletedAt() );
        supplierGlampingDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierGlampingDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierGlampingDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierGlampingDomain.setIdentifierName( source.getIdentifierName() );
        supplierGlampingDomain.setTenant( source.getTenant() );
        supplierGlampingDomain.setName( source.getName() );
        supplierGlampingDomain.setDescription( source.getDescription() );
        supplierGlampingDomain.setIdentify( source.getIdentify() );
        supplierGlampingDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierGlampingDomain.setRuc( source.getRuc() );
        supplierGlampingDomain.setConfirmation( source.getConfirmation() );
        supplierGlampingDomain.setReConfirmation( source.getReConfirmation() );
        supplierGlampingDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierGlampingDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierGlampingDomain.setSummary( source.getSummary() );
        supplierGlampingDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierGlampingDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierGlampingDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierGlampingDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierGlampingDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierGlampingDomain.setAdministrative( source.getAdministrative() );
        supplierGlampingDomain.setCheckIn( source.getCheckIn() );
        supplierGlampingDomain.setCheckOut( source.getCheckOut() );
        supplierGlampingDomain.setServiceGroup( source.getServiceGroup() );
        supplierGlampingDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierGlampingDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierGlampingDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierGlampingDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierGlampingDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierGlampingDomain.setLodgingType( source.getLodgingType() );
        supplierGlampingDomain.setLodgingCategory( lodgingCategoryConverter.toDomain( source.getLodgingCategory() ) );
        supplierGlampingDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierGlampingDomain;
    }

    @Override
    public SupplierGlampingEntity toEntity(SupplierGlampingDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGlampingEntity supplierGlampingEntity = new SupplierGlampingEntity();

        supplierGlampingEntity.setId( source.getId() );
        supplierGlampingEntity.setDeleted( source.getDeleted() );
        supplierGlampingEntity.setEnabled( source.getEnabled() );
        supplierGlampingEntity.setMigrated( source.getMigrated() );
        supplierGlampingEntity.setCreatedAt( source.getCreatedAt() );
        supplierGlampingEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierGlampingEntity.setDeletedAt( source.getDeletedAt() );
        supplierGlampingEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierGlampingEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierGlampingEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierGlampingEntity.setIdentifierName( source.getIdentifierName() );
        supplierGlampingEntity.setTenant( source.getTenant() );
        supplierGlampingEntity.setName( source.getName() );
        supplierGlampingEntity.setDescription( source.getDescription() );
        supplierGlampingEntity.setIdentify( source.getIdentify() );
        supplierGlampingEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierGlampingEntity.setRuc( source.getRuc() );
        supplierGlampingEntity.setConfirmation( source.getConfirmation() );
        supplierGlampingEntity.setReConfirmation( source.getReConfirmation() );
        supplierGlampingEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierGlampingEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierGlampingEntity.setSummary( source.getSummary() );
        supplierGlampingEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierGlampingEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierGlampingEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierGlampingEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierGlampingEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierGlampingEntity.setAdministrative( source.getAdministrative() );
        supplierGlampingEntity.setCheckIn( source.getCheckIn() );
        supplierGlampingEntity.setCheckOut( source.getCheckOut() );
        supplierGlampingEntity.setServiceGroup( source.getServiceGroup() );
        supplierGlampingEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierGlampingEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierGlampingEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierGlampingEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierGlampingEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierGlampingEntity.setLodgingType( source.getLodgingType() );
        supplierGlampingEntity.setLodgingCategory( lodgingCategoryConverter.toEntity( source.getLodgingCategory() ) );
        supplierGlampingEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierGlampingEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierGlampingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierGlampingDomain toDomainSimpleBaseWithType(SupplierGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGlampingDomain supplierGlampingDomain = new SupplierGlampingDomain();

        supplierGlampingDomain.setId( source.getId() );
        supplierGlampingDomain.setName( source.getName() );
        supplierGlampingDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierGlampingDomain.setSupplierType( new SupplierTypeDomain() );
            supplierGlampingDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierGlampingDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierGlampingDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierGlampingDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierGlampingDomain;
    }

    public List<SupplierGlampingDomain> toDomainAllSimpleWithType(List<SupplierGlampingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierGlampingDomain toDomainSimpleBaseWithDepartmentRegion(SupplierGlampingEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGlampingDomain supplierGlampingDomain = new SupplierGlampingDomain();

        supplierGlampingDomain.setId( source.getId() );
        supplierGlampingDomain.setName( source.getName() );
        supplierGlampingDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierGlampingDomain.setSupplierType( new SupplierTypeDomain() );
            supplierGlampingDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierGlampingDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierGlampingDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierGlampingDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierGlampingDomain.setPartner( new PartnerDomain());
            supplierGlampingDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierGlampingDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierGlampingDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierGlampingDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierGlampingDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierGlampingDomain;
    }

    public List<SupplierGlampingDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierGlampingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierGlampingDomain> toDomainAllSimpleToDomainCaseListSupplierGlamping(List<SupplierGlampingEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierGlampingDomain supplierGlampingDomain = new SupplierGlampingDomain();
            supplierGlampingDomain.setId(entity.getId());
            supplierGlampingDomain.setIdentify(entity.getIdentify());
            supplierGlampingDomain.setName(entity.getName());
            return supplierGlampingDomain;
        }).toList();
    }

    public PageDTO<SupplierGlampingDomain> toDomainPageCaseListSupplierGlamping(Page<SupplierGlampingEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierGlampingDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierGlamping(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
