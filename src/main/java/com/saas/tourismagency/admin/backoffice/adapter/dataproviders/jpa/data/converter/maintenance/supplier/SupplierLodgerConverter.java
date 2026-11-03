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
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierLodgerEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgerDomain;
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
public class SupplierLodgerConverter implements Converter<SupplierLodgerDomain, SupplierLodgerEntity> {

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
    public SupplierLodgerDomain toDomain(SupplierLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgerDomain supplierLodgerDomain = new SupplierLodgerDomain();

        supplierLodgerDomain.setId( source.getId() );
        supplierLodgerDomain.setDeleted( source.getDeleted() );
        supplierLodgerDomain.setEnabled( source.getEnabled() );
        supplierLodgerDomain.setMigrated( source.getMigrated() );
        supplierLodgerDomain.setCreatedAt( source.getCreatedAt() );
        supplierLodgerDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierLodgerDomain.setDeletedAt( source.getDeletedAt() );
        supplierLodgerDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierLodgerDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierLodgerDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierLodgerDomain.setIdentifierName( source.getIdentifierName() );
        supplierLodgerDomain.setTenant( source.getTenant() );
        supplierLodgerDomain.setName( source.getName() );
        supplierLodgerDomain.setDescription( source.getDescription() );
        supplierLodgerDomain.setIdentify( source.getIdentify() );
        supplierLodgerDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierLodgerDomain.setRuc( source.getRuc() );
        supplierLodgerDomain.setConfirmation( source.getConfirmation() );
        supplierLodgerDomain.setReConfirmation( source.getReConfirmation() );
        supplierLodgerDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierLodgerDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierLodgerDomain.setSummary( source.getSummary() );
        supplierLodgerDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierLodgerDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierLodgerDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierLodgerDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierLodgerDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierLodgerDomain.setAdministrative( source.getAdministrative() );
        supplierLodgerDomain.setCheckIn( source.getCheckIn() );
        supplierLodgerDomain.setCheckOut( source.getCheckOut() );
        supplierLodgerDomain.setServiceGroup( source.getServiceGroup() );
        supplierLodgerDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierLodgerDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierLodgerDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierLodgerDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierLodgerDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierLodgerDomain.setLodgingType( source.getLodgingType() );
        supplierLodgerDomain.setSupplierTypeOperator( source.getSupplierTypeOperator() );
        supplierLodgerDomain.setLodgingCategory( lodgingCategoryConverter.toDomain( source.getLodgingCategory() ) );
        supplierLodgerDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierLodgerDomain;
    }

    @Override
    public SupplierLodgerEntity toEntity(SupplierLodgerDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgerEntity supplierLodgerEntity = new SupplierLodgerEntity();

        supplierLodgerEntity.setId( source.getId() );
        supplierLodgerEntity.setDeleted( source.getDeleted() );
        supplierLodgerEntity.setEnabled( source.getEnabled() );
        supplierLodgerEntity.setMigrated( source.getMigrated() );
        supplierLodgerEntity.setCreatedAt( source.getCreatedAt() );
        supplierLodgerEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierLodgerEntity.setDeletedAt( source.getDeletedAt() );
        supplierLodgerEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierLodgerEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierLodgerEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierLodgerEntity.setIdentifierName( source.getIdentifierName() );
        supplierLodgerEntity.setTenant( source.getTenant() );
        supplierLodgerEntity.setName( source.getName() );
        supplierLodgerEntity.setDescription( source.getDescription() );
        supplierLodgerEntity.setIdentify( source.getIdentify() );
        supplierLodgerEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierLodgerEntity.setRuc( source.getRuc() );
        supplierLodgerEntity.setConfirmation( source.getConfirmation() );
        supplierLodgerEntity.setReConfirmation( source.getReConfirmation() );
        supplierLodgerEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierLodgerEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierLodgerEntity.setSummary( source.getSummary() );
        supplierLodgerEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierLodgerEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierLodgerEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierLodgerEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierLodgerEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierLodgerEntity.setAdministrative( source.getAdministrative() );
        supplierLodgerEntity.setCheckIn( source.getCheckIn() );
        supplierLodgerEntity.setCheckOut( source.getCheckOut() );
        supplierLodgerEntity.setServiceGroup( source.getServiceGroup() );
        supplierLodgerEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierLodgerEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierLodgerEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierLodgerEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierLodgerEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierLodgerEntity.setLodgingType( source.getLodgingType() );
        supplierLodgerEntity.setSupplierTypeOperator( source.getSupplierTypeOperator() );
        supplierLodgerEntity.setLodgingCategory( lodgingCategoryConverter.toEntity( source.getLodgingCategory() ) );
        supplierLodgerEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierLodgerEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierLodgerDomain toDomainSimpleBaseWithType(SupplierLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgerDomain supplierLodgerDomain = new SupplierLodgerDomain();

        supplierLodgerDomain.setId( source.getId() );
        supplierLodgerDomain.setName( source.getName() );
        supplierLodgerDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierLodgerDomain.setSupplierType( new SupplierTypeDomain() );
            supplierLodgerDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierLodgerDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierLodgerDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierLodgerDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierLodgerDomain;
    }

    public List<SupplierLodgerDomain> toDomainAllSimpleWithType(List<SupplierLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierLodgerDomain toDomainSimpleBaseWithDepartmentRegion(SupplierLodgerEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierLodgerDomain supplierLodgerDomain = new SupplierLodgerDomain();

        supplierLodgerDomain.setId( source.getId() );
        supplierLodgerDomain.setName( source.getName() );
        supplierLodgerDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierLodgerDomain.setSupplierType( new SupplierTypeDomain() );
            supplierLodgerDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierLodgerDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierLodgerDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierLodgerDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierLodgerDomain.setPartner( new PartnerDomain());
            supplierLodgerDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierLodgerDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierLodgerDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierLodgerDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierLodgerDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierLodgerDomain;
    }

    public List<SupplierLodgerDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierLodgerDomain> toDomainAllSimpleToDomainCaseListSupplierLodger(List<SupplierLodgerEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierLodgerDomain supplierLodgerDomain = new SupplierLodgerDomain();
            supplierLodgerDomain.setId(entity.getId());
            supplierLodgerDomain.setIdentify(entity.getIdentify());
            supplierLodgerDomain.setName(entity.getName());
            return supplierLodgerDomain;
        }).toList();
    }

    public PageDTO<SupplierLodgerDomain> toDomainPageCaseListSupplierLodger(Page<SupplierLodgerEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierLodgerDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierLodger(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
