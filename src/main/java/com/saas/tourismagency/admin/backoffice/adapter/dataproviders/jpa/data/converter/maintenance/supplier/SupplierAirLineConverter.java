package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierAirLineEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAirLineDomain;
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
public class SupplierAirLineConverter implements Converter<SupplierAirLineDomain, SupplierAirLineEntity> {

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
    public SupplierAirLineDomain toDomain(SupplierAirLineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAirLineDomain supplierAirLineDomain = new SupplierAirLineDomain();

        supplierAirLineDomain.setId( source.getId() );
        supplierAirLineDomain.setDeleted( source.getDeleted() );
        supplierAirLineDomain.setEnabled( source.getEnabled() );
        supplierAirLineDomain.setMigrated( source.getMigrated() );
        supplierAirLineDomain.setCreatedAt( source.getCreatedAt() );
        supplierAirLineDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierAirLineDomain.setDeletedAt( source.getDeletedAt() );
        supplierAirLineDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierAirLineDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierAirLineDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierAirLineDomain.setIdentifierName( source.getIdentifierName() );
        supplierAirLineDomain.setTenant( source.getTenant() );
        supplierAirLineDomain.setName( source.getName() );
        supplierAirLineDomain.setDescription( source.getDescription() );
        supplierAirLineDomain.setIdentify( source.getIdentify() );
        supplierAirLineDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierAirLineDomain.setRuc( source.getRuc() );
        supplierAirLineDomain.setConfirmation( source.getConfirmation() );
        supplierAirLineDomain.setReConfirmation( source.getReConfirmation() );
        supplierAirLineDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierAirLineDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierAirLineDomain.setSummary( source.getSummary() );
        supplierAirLineDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierAirLineDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierAirLineDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierAirLineDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierAirLineDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierAirLineDomain.setAdministrative( source.getAdministrative() );
        supplierAirLineDomain.setCheckIn( source.getCheckIn() );
        supplierAirLineDomain.setCheckOut( source.getCheckOut() );
        supplierAirLineDomain.setServiceGroup( source.getServiceGroup() );
        supplierAirLineDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierAirLineDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierAirLineDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierAirLineDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierAirLineDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierAirLineDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierAirLineDomain;
    }

    @Override
    public SupplierAirLineEntity toEntity(SupplierAirLineDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAirLineEntity supplierAirLineEntity = new SupplierAirLineEntity();

        supplierAirLineEntity.setId( source.getId() );
        supplierAirLineEntity.setDeleted( source.getDeleted() );
        supplierAirLineEntity.setEnabled( source.getEnabled() );
        supplierAirLineEntity.setMigrated( source.getMigrated() );
        supplierAirLineEntity.setCreatedAt( source.getCreatedAt() );
        supplierAirLineEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierAirLineEntity.setDeletedAt( source.getDeletedAt() );
        supplierAirLineEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierAirLineEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierAirLineEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierAirLineEntity.setIdentifierName( source.getIdentifierName() );
        supplierAirLineEntity.setTenant( source.getTenant() );
        supplierAirLineEntity.setName( source.getName() );
        supplierAirLineEntity.setDescription( source.getDescription() );
        supplierAirLineEntity.setIdentify( source.getIdentify() );
        supplierAirLineEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierAirLineEntity.setRuc( source.getRuc() );
        supplierAirLineEntity.setConfirmation( source.getConfirmation() );
        supplierAirLineEntity.setReConfirmation( source.getReConfirmation() );
        supplierAirLineEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierAirLineEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierAirLineEntity.setSummary( source.getSummary() );
        supplierAirLineEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierAirLineEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierAirLineEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierAirLineEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierAirLineEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierAirLineEntity.setAdministrative( source.getAdministrative() );
        supplierAirLineEntity.setCheckIn( source.getCheckIn() );
        supplierAirLineEntity.setCheckOut( source.getCheckOut() );
        supplierAirLineEntity.setServiceGroup( source.getServiceGroup() );
        supplierAirLineEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierAirLineEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierAirLineEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierAirLineEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierAirLineEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierAirLineEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierAirLineEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierAirLineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierAirLineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierAirLineEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierAirLineDomain toDomainSimpleBaseWithType(SupplierAirLineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAirLineDomain supplierAirLineDomain = new SupplierAirLineDomain();

        supplierAirLineDomain.setId( source.getId() );
        supplierAirLineDomain.setName( source.getName() );
        supplierAirLineDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierAirLineDomain.setSupplierType( new SupplierTypeDomain() );
            supplierAirLineDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierAirLineDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierAirLineDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierAirLineDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierAirLineDomain;
    }

    public List<SupplierAirLineDomain> toDomainAllSimpleWithType(List<SupplierAirLineEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierAirLineDomain toDomainSimpleBaseWithDepartmentRegion(SupplierAirLineEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAirLineDomain supplierAirLineDomain = new SupplierAirLineDomain();

        supplierAirLineDomain.setId( source.getId() );
        supplierAirLineDomain.setName( source.getName() );
        supplierAirLineDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierAirLineDomain.setSupplierType( new SupplierTypeDomain() );
            supplierAirLineDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierAirLineDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierAirLineDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierAirLineDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierAirLineDomain.setPartner( new PartnerDomain());
            supplierAirLineDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierAirLineDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierAirLineDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierAirLineDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierAirLineDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierAirLineDomain;
    }

    public List<SupplierAirLineDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierAirLineEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierAirLineDomain> toDomainAllSimpleToDomainCaseListSupplierAirLine(List<SupplierAirLineEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierAirLineDomain supplierAirLineDomain = new SupplierAirLineDomain();
            supplierAirLineDomain.setId(entity.getId());
            supplierAirLineDomain.setIdentify(entity.getIdentify());
            supplierAirLineDomain.setName(entity.getName());
            return supplierAirLineDomain;
        }).toList();
    }

    public PageDTO<SupplierAirLineDomain> toDomainPageCaseListSupplierAirLine(Page<SupplierAirLineEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierAirLineDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierAirLine(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
