package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierInstitutionEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierInstitutionDomain;
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
public class SupplierInstitutionConverter implements Converter<SupplierInstitutionDomain, SupplierInstitutionEntity> {

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
    private ServiceConverter serviceConverter;

    @Override
    public SupplierInstitutionDomain toDomain(SupplierInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierInstitutionDomain supplierInstitutionDomain = new SupplierInstitutionDomain();

        supplierInstitutionDomain.setId( source.getId() );
        supplierInstitutionDomain.setDeleted( source.getDeleted() );
        supplierInstitutionDomain.setEnabled( source.getEnabled() );
        supplierInstitutionDomain.setMigrated( source.getMigrated() );
        supplierInstitutionDomain.setCreatedAt( source.getCreatedAt() );
        supplierInstitutionDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierInstitutionDomain.setDeletedAt( source.getDeletedAt() );
        supplierInstitutionDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierInstitutionDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierInstitutionDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierInstitutionDomain.setIdentifierName( source.getIdentifierName() );
        supplierInstitutionDomain.setTenant( source.getTenant() );
        supplierInstitutionDomain.setName( source.getName() );
        supplierInstitutionDomain.setDescription( source.getDescription() );
        supplierInstitutionDomain.setIdentify( source.getIdentify() );
        supplierInstitutionDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierInstitutionDomain.setRuc( source.getRuc() );
        supplierInstitutionDomain.setConfirmation( source.getConfirmation() );
        supplierInstitutionDomain.setReConfirmation( source.getReConfirmation() );
        supplierInstitutionDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierInstitutionDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierInstitutionDomain.setSummary( source.getSummary() );
        supplierInstitutionDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierInstitutionDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierInstitutionDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierInstitutionDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierInstitutionDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierInstitutionDomain.setAdministrative( source.getAdministrative() );
        supplierInstitutionDomain.setCheckIn( source.getCheckIn() );
        supplierInstitutionDomain.setCheckOut( source.getCheckOut() );
        supplierInstitutionDomain.setServiceGroup( source.getServiceGroup() );
        supplierInstitutionDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierInstitutionDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierInstitutionDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierInstitutionDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierInstitutionDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierInstitutionDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierInstitutionDomain;
    }

    @Override
    public SupplierInstitutionEntity toEntity(SupplierInstitutionDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierInstitutionEntity supplierInstitutionEntity = new SupplierInstitutionEntity();

        supplierInstitutionEntity.setId( source.getId() );
        supplierInstitutionEntity.setDeleted( source.getDeleted() );
        supplierInstitutionEntity.setEnabled( source.getEnabled() );
        supplierInstitutionEntity.setMigrated( source.getMigrated() );
        supplierInstitutionEntity.setCreatedAt( source.getCreatedAt() );
        supplierInstitutionEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierInstitutionEntity.setDeletedAt( source.getDeletedAt() );
        supplierInstitutionEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierInstitutionEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierInstitutionEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierInstitutionEntity.setIdentifierName( source.getIdentifierName() );
        supplierInstitutionEntity.setTenant( source.getTenant() );
        supplierInstitutionEntity.setName( source.getName() );
        supplierInstitutionEntity.setDescription( source.getDescription() );
        supplierInstitutionEntity.setIdentify( source.getIdentify() );
        supplierInstitutionEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierInstitutionEntity.setRuc( source.getRuc() );
        supplierInstitutionEntity.setConfirmation( source.getConfirmation() );
        supplierInstitutionEntity.setReConfirmation( source.getReConfirmation() );
        supplierInstitutionEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierInstitutionEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierInstitutionEntity.setSummary( source.getSummary() );
        supplierInstitutionEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierInstitutionEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierInstitutionEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierInstitutionEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierInstitutionEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierInstitutionEntity.setAdministrative( source.getAdministrative() );
        supplierInstitutionEntity.setCheckIn( source.getCheckIn() );
        supplierInstitutionEntity.setCheckOut( source.getCheckOut() );
        supplierInstitutionEntity.setServiceGroup( source.getServiceGroup() );
        supplierInstitutionEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierInstitutionEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierInstitutionEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierInstitutionEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierInstitutionEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierInstitutionEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierInstitutionEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierInstitutionDomain toDomainSimpleBaseWithType(SupplierInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierInstitutionDomain supplierInstitutionDomain = new SupplierInstitutionDomain();

        supplierInstitutionDomain.setId( source.getId() );
        supplierInstitutionDomain.setName( source.getName() );
        supplierInstitutionDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierInstitutionDomain.setSupplierType( new SupplierTypeDomain() );
            supplierInstitutionDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierInstitutionDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierInstitutionDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierInstitutionDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierInstitutionDomain;
    }

    public List<SupplierInstitutionDomain> toDomainAllSimpleWithType(List<SupplierInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierInstitutionDomain toDomainSimpleBaseWithDepartmentRegion(SupplierInstitutionEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierInstitutionDomain supplierInstitutionDomain = new SupplierInstitutionDomain();

        supplierInstitutionDomain.setId( source.getId() );
        supplierInstitutionDomain.setName( source.getName() );
        supplierInstitutionDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierInstitutionDomain.setSupplierType( new SupplierTypeDomain() );
            supplierInstitutionDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierInstitutionDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierInstitutionDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierInstitutionDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierInstitutionDomain.setPartner( new PartnerDomain());
            supplierInstitutionDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierInstitutionDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierInstitutionDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierInstitutionDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierInstitutionDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierInstitutionDomain;
    }

    public List<SupplierInstitutionDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierInstitutionDomain> toDomainAllSimpleToDomainCaseListSupplierInstitution(List<SupplierInstitutionEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierInstitutionDomain supplierInstitutionDomain = new SupplierInstitutionDomain();
            supplierInstitutionDomain.setId(entity.getId());
            supplierInstitutionDomain.setIdentify(entity.getIdentify());
            supplierInstitutionDomain.setName(entity.getName());
            return supplierInstitutionDomain;
        }).toList();
    }

    public PageDTO<SupplierInstitutionDomain> toDomainPageCaseListSupplierInstitution(Page<SupplierInstitutionEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierInstitutionDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierInstitution(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
