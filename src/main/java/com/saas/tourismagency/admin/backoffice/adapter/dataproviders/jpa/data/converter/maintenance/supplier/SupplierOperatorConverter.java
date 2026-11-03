package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierOperatorEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
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
public class SupplierOperatorConverter implements Converter<SupplierOperatorDomain, SupplierOperatorEntity> {

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
    public SupplierOperatorDomain toDomain(SupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierOperatorDomain supplierOperatorDomain = new SupplierOperatorDomain();

        supplierOperatorDomain.setId( source.getId() );
        supplierOperatorDomain.setDeleted( source.getDeleted() );
        supplierOperatorDomain.setEnabled( source.getEnabled() );
        supplierOperatorDomain.setMigrated( source.getMigrated() );
        supplierOperatorDomain.setCreatedAt( source.getCreatedAt() );
        supplierOperatorDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierOperatorDomain.setDeletedAt( source.getDeletedAt() );
        supplierOperatorDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierOperatorDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierOperatorDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierOperatorDomain.setIdentifierName( source.getIdentifierName() );
        supplierOperatorDomain.setTenant( source.getTenant() );
        supplierOperatorDomain.setName( source.getName() );
        supplierOperatorDomain.setDescription( source.getDescription() );
        supplierOperatorDomain.setIdentify( source.getIdentify() );
        supplierOperatorDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierOperatorDomain.setRuc( source.getRuc() );
        supplierOperatorDomain.setConfirmation( source.getConfirmation() );
        supplierOperatorDomain.setReConfirmation( source.getReConfirmation() );
        supplierOperatorDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierOperatorDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierOperatorDomain.setSummary( source.getSummary() );
        supplierOperatorDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierOperatorDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierOperatorDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierOperatorDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierOperatorDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierOperatorDomain.setAdministrative( source.getAdministrative() );
        supplierOperatorDomain.setCheckIn( source.getCheckIn() );
        supplierOperatorDomain.setCheckOut( source.getCheckOut() );
        supplierOperatorDomain.setServiceGroup( source.getServiceGroup() );
        supplierOperatorDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierOperatorDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierOperatorDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierOperatorDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierOperatorDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierOperatorDomain.setSupplierTypeOperator( source.getSupplierTypeOperator() );
        supplierOperatorDomain.setAvailableOrderService( source.getAvailableOrderService() );
        supplierOperatorDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierOperatorDomain;
    }

    @Override
    public SupplierOperatorEntity toEntity(SupplierOperatorDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierOperatorEntity supplierOperatorEntity = new SupplierOperatorEntity();

        supplierOperatorEntity.setId( source.getId() );
        supplierOperatorEntity.setDeleted( source.getDeleted() );
        supplierOperatorEntity.setEnabled( source.getEnabled() );
        supplierOperatorEntity.setMigrated( source.getMigrated() );
        supplierOperatorEntity.setCreatedAt( source.getCreatedAt() );
        supplierOperatorEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierOperatorEntity.setDeletedAt( source.getDeletedAt() );
        supplierOperatorEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierOperatorEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierOperatorEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierOperatorEntity.setIdentifierName( source.getIdentifierName() );
        supplierOperatorEntity.setTenant( source.getTenant() );
        supplierOperatorEntity.setName( source.getName() );
        supplierOperatorEntity.setDescription( source.getDescription() );
        supplierOperatorEntity.setIdentify( source.getIdentify() );
        supplierOperatorEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierOperatorEntity.setRuc( source.getRuc() );
        supplierOperatorEntity.setConfirmation( source.getConfirmation() );
        supplierOperatorEntity.setReConfirmation( source.getReConfirmation() );
        supplierOperatorEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierOperatorEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierOperatorEntity.setSummary( source.getSummary() );
        supplierOperatorEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierOperatorEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierOperatorEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierOperatorEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierOperatorEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierOperatorEntity.setAdministrative( source.getAdministrative() );
        supplierOperatorEntity.setCheckIn( source.getCheckIn() );
        supplierOperatorEntity.setCheckOut( source.getCheckOut() );
        supplierOperatorEntity.setServiceGroup( source.getServiceGroup() );
        supplierOperatorEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierOperatorEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierOperatorEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierOperatorEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierOperatorEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierOperatorEntity.setSupplierTypeOperator( source.getSupplierTypeOperator() );
        supplierOperatorEntity.setAvailableOrderService( source.getAvailableOrderService() );
        supplierOperatorEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierOperatorEntity;
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

    public IdBaseDomain toDomainIdBase(SupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierOperatorDomain toDomainSimpleBaseWithType(SupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierOperatorDomain supplierOperatorDomain = new SupplierOperatorDomain();

        supplierOperatorDomain.setId( source.getId() );
        supplierOperatorDomain.setName( source.getName() );
        supplierOperatorDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierOperatorDomain.setSupplierType( new SupplierTypeDomain() );
            supplierOperatorDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierOperatorDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierOperatorDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierOperatorDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierOperatorDomain;
    }

    public List<SupplierOperatorDomain> toDomainAllSimpleWithType(List<SupplierOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierOperatorDomain toDomainSimpleBaseWithDepartmentRegion(SupplierOperatorEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierOperatorDomain supplierOperatorDomain = new SupplierOperatorDomain();

        supplierOperatorDomain.setId( source.getId() );
        supplierOperatorDomain.setName( source.getName() );
        supplierOperatorDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierOperatorDomain.setSupplierType( new SupplierTypeDomain() );
            supplierOperatorDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierOperatorDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierOperatorDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierOperatorDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierOperatorDomain.setPartner( new PartnerDomain());
            supplierOperatorDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierOperatorDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierOperatorDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierOperatorDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierOperatorDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierOperatorDomain;
    }

    public List<SupplierOperatorDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierOperatorDomain> toDomainAllSimpleToDomainCaseListSupplierOperator(List<SupplierOperatorEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierOperatorDomain supplierOperatorDomain = new SupplierOperatorDomain();
            supplierOperatorDomain.setId(entity.getId());
            supplierOperatorDomain.setIdentify(entity.getIdentify());
            supplierOperatorDomain.setName(entity.getName());
            return supplierOperatorDomain;
        }).toList();
    }

    public PageDTO<SupplierOperatorDomain> toDomainPageCaseListSupplierOperator(Page<SupplierOperatorEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierOperatorDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierOperator(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
