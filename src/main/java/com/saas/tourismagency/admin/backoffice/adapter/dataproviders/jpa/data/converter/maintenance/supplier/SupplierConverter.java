package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class SupplierConverter implements Converter<SupplierDomain, SupplierEntity> {

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
    public SupplierDomain toDomain(SupplierEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierDomain supplierDomain = new SupplierDomain();

        supplierDomain.setId( source.getId() );
        supplierDomain.setDeleted( source.getDeleted() );
        supplierDomain.setEnabled( source.getEnabled() );
        supplierDomain.setMigrated( source.getMigrated() );
        supplierDomain.setCreatedAt( source.getCreatedAt() );
        supplierDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierDomain.setDeletedAt( source.getDeletedAt() );
        supplierDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierDomain.setIdentifierName( source.getIdentifierName() );
        supplierDomain.setTenant( source.getTenant() );
        supplierDomain.setName( source.getName() );
        supplierDomain.setDescription( source.getDescription() );
        supplierDomain.setIdentify( source.getIdentify() );
        supplierDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierDomain.setRuc( source.getRuc() );
        supplierDomain.setConfirmation( source.getConfirmation() );
        supplierDomain.setReConfirmation( source.getReConfirmation() );
        supplierDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierDomain.setSummary( source.getSummary() );
        supplierDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierDomain.setAdministrative( source.getAdministrative() );
        supplierDomain.setCheckIn( source.getCheckIn() );
        supplierDomain.setCheckOut( source.getCheckOut() );
        supplierDomain.setServiceGroup( source.getServiceGroup() );
        supplierDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierDomain;
    }

    @Override
    public SupplierEntity toEntity(SupplierDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierEntity supplierEntity = new SupplierEntity();

        supplierEntity.setId( source.getId() );
        supplierEntity.setDeleted( source.getDeleted() );
        supplierEntity.setEnabled( source.getEnabled() );
        supplierEntity.setMigrated( source.getMigrated() );
        supplierEntity.setCreatedAt( source.getCreatedAt() );
        supplierEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierEntity.setDeletedAt( source.getDeletedAt() );
        supplierEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierEntity.setIdentifierName( source.getIdentifierName() );
        supplierEntity.setTenant( source.getTenant() );
        supplierEntity.setName( source.getName() );
        supplierEntity.setDescription( source.getDescription() );
        supplierEntity.setIdentify( source.getIdentify() );
        supplierEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierEntity.setRuc( source.getRuc() );
        supplierEntity.setConfirmation( source.getConfirmation() );
        supplierEntity.setReConfirmation( source.getReConfirmation() );
        supplierEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierEntity.setSummary( source.getSummary() );
        supplierEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierEntity.setAdministrative( source.getAdministrative() );
        supplierEntity.setCheckIn( source.getCheckIn() );
        supplierEntity.setCheckOut( source.getCheckOut() );
        supplierEntity.setServiceGroup( source.getServiceGroup() );
        supplierEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public List<SupplierDomain> toDomainAllSimpleToDomainCaseToolUpdateSummary(List<SupplierEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierDomain supplierDomain = new SupplierDomain();
            supplierDomain.setId( entity.getId() );
            supplierDomain.setSummary( entity.getSummary() );
            supplierDomain.setName( entity.getName() );
            return supplierDomain;
        }).toList();
    }

    public SupplierDomain toDomainWithDepartmentRegion(SupplierEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierDomain supplierDomain = new SupplierDomain();

        supplierDomain.setId( source.getId() );

        if (source.getPartner() != null){
            supplierDomain.setPartner( new PartnerDomain());
            supplierDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null){
                    supplierDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierDomain;

    }

    public SupplierDomain toDomainSimpleBaseForServiceOperation(SupplierEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierDomain supplierDomain = new SupplierDomain();

        supplierDomain.setId( source.getId() );
        supplierDomain.setName( source.getName() );
        supplierDomain.setDescription( source.getDescription() );
        return supplierDomain;
    }

    public List<SupplierDomain> toDomainAllSimpleForServiceOperation(List<SupplierEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseForServiceOperation(entity)).toList();
    }
}
