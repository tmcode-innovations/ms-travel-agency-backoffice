package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.transporttype.TransportTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierTransportEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTransportDomain;
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
public class SupplierTransportConverter implements Converter<SupplierTransportDomain, SupplierTransportEntity> {

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
    private TransportTypeConverter transportTypeConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public SupplierTransportDomain toDomain(SupplierTransportEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierTransportDomain supplierTransportDomain = new SupplierTransportDomain();

        supplierTransportDomain.setId( source.getId() );
        supplierTransportDomain.setDeleted( source.getDeleted() );
        supplierTransportDomain.setEnabled( source.getEnabled() );
        supplierTransportDomain.setMigrated( source.getMigrated() );
        supplierTransportDomain.setCreatedAt( source.getCreatedAt() );
        supplierTransportDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierTransportDomain.setDeletedAt( source.getDeletedAt() );
        supplierTransportDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierTransportDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierTransportDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierTransportDomain.setIdentifierName( source.getIdentifierName() );
        supplierTransportDomain.setTenant( source.getTenant() );
        supplierTransportDomain.setName( source.getName() );
        supplierTransportDomain.setDescription( source.getDescription() );
        supplierTransportDomain.setIdentify( source.getIdentify() );
        supplierTransportDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierTransportDomain.setRuc( source.getRuc() );
        supplierTransportDomain.setConfirmation( source.getConfirmation() );
        supplierTransportDomain.setReConfirmation( source.getReConfirmation() );
        supplierTransportDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierTransportDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierTransportDomain.setSummary( source.getSummary() );
        supplierTransportDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierTransportDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierTransportDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierTransportDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierTransportDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierTransportDomain.setAdministrative( source.getAdministrative() );
        supplierTransportDomain.setCheckIn( source.getCheckIn() );
        supplierTransportDomain.setCheckOut( source.getCheckOut() );
        supplierTransportDomain.setServiceGroup( source.getServiceGroup() );
        supplierTransportDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierTransportDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierTransportDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierTransportDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierTransportDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierTransportDomain.setTransportType( transportTypeConverter.toDomain( source.getTransportType() ) );
        supplierTransportDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierTransportDomain;
    }

    @Override
    public SupplierTransportEntity toEntity(SupplierTransportDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierTransportEntity supplierTransportEntity = new SupplierTransportEntity();

        supplierTransportEntity.setId( source.getId() );
        supplierTransportEntity.setDeleted( source.getDeleted() );
        supplierTransportEntity.setEnabled( source.getEnabled() );
        supplierTransportEntity.setMigrated( source.getMigrated() );
        supplierTransportEntity.setCreatedAt( source.getCreatedAt() );
        supplierTransportEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierTransportEntity.setDeletedAt( source.getDeletedAt() );
        supplierTransportEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierTransportEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierTransportEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierTransportEntity.setIdentifierName( source.getIdentifierName() );
        supplierTransportEntity.setTenant( source.getTenant() );
        supplierTransportEntity.setName( source.getName() );
        supplierTransportEntity.setDescription( source.getDescription() );
        supplierTransportEntity.setIdentify( source.getIdentify() );
        supplierTransportEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierTransportEntity.setRuc( source.getRuc() );
        supplierTransportEntity.setConfirmation( source.getConfirmation() );
        supplierTransportEntity.setReConfirmation( source.getReConfirmation() );
        supplierTransportEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierTransportEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierTransportEntity.setSummary( source.getSummary() );
        supplierTransportEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierTransportEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierTransportEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierTransportEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierTransportEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierTransportEntity.setAdministrative( source.getAdministrative() );
        supplierTransportEntity.setCheckIn( source.getCheckIn() );
        supplierTransportEntity.setCheckOut( source.getCheckOut() );
        supplierTransportEntity.setServiceGroup( source.getServiceGroup() );
        supplierTransportEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierTransportEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierTransportEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierTransportEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierTransportEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierTransportEntity.setTransportType( transportTypeConverter.toEntity( source.getTransportType() ) );
        supplierTransportEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierTransportEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierTransportEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierTransportEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierTransportEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierTransportDomain toDomainSimpleBaseWithType(SupplierTransportEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierTransportDomain supplierTransportDomain = new SupplierTransportDomain();

        supplierTransportDomain.setId( source.getId() );
        supplierTransportDomain.setName( source.getName() );
        supplierTransportDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierTransportDomain.setSupplierType( new SupplierTypeDomain() );
            supplierTransportDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierTransportDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierTransportDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierTransportDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierTransportDomain;
    }

    public List<SupplierTransportDomain> toDomainAllSimpleWithType(List<SupplierTransportEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierTransportDomain toDomainSimpleBaseWithDepartmentRegion(SupplierTransportEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierTransportDomain supplierTransportDomain = new SupplierTransportDomain();

        supplierTransportDomain.setId( source.getId() );
        supplierTransportDomain.setName( source.getName() );
        supplierTransportDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierTransportDomain.setSupplierType( new SupplierTypeDomain() );
            supplierTransportDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierTransportDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierTransportDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierTransportDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierTransportDomain.setPartner( new PartnerDomain());
            supplierTransportDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierTransportDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierTransportDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierTransportDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierTransportDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierTransportDomain;
    }

    public List<SupplierTransportDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierTransportEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierTransportDomain> toDomainAllSimpleToDomainCaseListSupplierTransport(List<SupplierTransportEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierTransportDomain supplierTransportDomain = new SupplierTransportDomain();
            supplierTransportDomain.setId(entity.getId());
            supplierTransportDomain.setIdentify(entity.getIdentify());
            supplierTransportDomain.setName(entity.getName());
            return supplierTransportDomain;
        }).toList();
    }

    public PageDTO<SupplierTransportDomain> toDomainPageCaseListSupplierTransport(Page<SupplierTransportEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierTransportDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierTransport(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
