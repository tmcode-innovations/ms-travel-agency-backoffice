package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.idiom.IdiomConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.service.ServiceConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.IdiomEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierGuideEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.*;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierGuideDomain;
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
public class SupplierGuideConverter implements Converter<SupplierGuideDomain, SupplierGuideEntity> {

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
    private IdiomConverter idiomConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public SupplierGuideDomain toDomain(SupplierGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGuideDomain supplierGuideDomain = new SupplierGuideDomain();

        supplierGuideDomain.setId( source.getId() );
        supplierGuideDomain.setDeleted( source.getDeleted() );
        supplierGuideDomain.setEnabled( source.getEnabled() );
        supplierGuideDomain.setMigrated( source.getMigrated() );
        supplierGuideDomain.setCreatedAt( source.getCreatedAt() );
        supplierGuideDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierGuideDomain.setDeletedAt( source.getDeletedAt() );
        supplierGuideDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierGuideDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierGuideDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierGuideDomain.setIdentifierName( source.getIdentifierName() );
        supplierGuideDomain.setTenant( source.getTenant() );
        supplierGuideDomain.setName( source.getName() );
        supplierGuideDomain.setDescription( source.getDescription() );
        supplierGuideDomain.setIdentify( source.getIdentify() );
        supplierGuideDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierGuideDomain.setRuc( source.getRuc() );
        supplierGuideDomain.setConfirmation( source.getConfirmation() );
        supplierGuideDomain.setReConfirmation( source.getReConfirmation() );
        supplierGuideDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierGuideDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierGuideDomain.setSummary( source.getSummary() );
        supplierGuideDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierGuideDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierGuideDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierGuideDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierGuideDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierGuideDomain.setAdministrative( source.getAdministrative() );
        supplierGuideDomain.setCheckIn( source.getCheckIn() );
        supplierGuideDomain.setCheckOut( source.getCheckOut() );
        supplierGuideDomain.setServiceGroup( source.getServiceGroup() );
        supplierGuideDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierGuideDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierGuideDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierGuideDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierGuideDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );
        supplierGuideDomain.setIdioms( idiomEntitySetToIdiomDomainSet( source.getIdioms() ) );
        supplierGuideDomain.setServices( serviceEntitySetToServiceDomainSet( source.getServices() ) );

        return supplierGuideDomain;
    }

    @Override
    public SupplierGuideEntity toEntity(SupplierGuideDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGuideEntity supplierGuideEntity = new SupplierGuideEntity();

        supplierGuideEntity.setId( source.getId() );
        supplierGuideEntity.setDeleted( source.getDeleted() );
        supplierGuideEntity.setEnabled( source.getEnabled() );
        supplierGuideEntity.setMigrated( source.getMigrated() );
        supplierGuideEntity.setCreatedAt( source.getCreatedAt() );
        supplierGuideEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierGuideEntity.setDeletedAt( source.getDeletedAt() );
        supplierGuideEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierGuideEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierGuideEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierGuideEntity.setIdentifierName( source.getIdentifierName() );
        supplierGuideEntity.setTenant( source.getTenant() );
        supplierGuideEntity.setName( source.getName() );
        supplierGuideEntity.setDescription( source.getDescription() );
        supplierGuideEntity.setIdentify( source.getIdentify() );
        supplierGuideEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierGuideEntity.setRuc( source.getRuc() );
        supplierGuideEntity.setConfirmation( source.getConfirmation() );
        supplierGuideEntity.setReConfirmation( source.getReConfirmation() );
        supplierGuideEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierGuideEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierGuideEntity.setSummary( source.getSummary() );
        supplierGuideEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierGuideEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierGuideEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierGuideEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierGuideEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierGuideEntity.setAdministrative( source.getAdministrative() );
        supplierGuideEntity.setCheckIn( source.getCheckIn() );
        supplierGuideEntity.setCheckOut( source.getCheckOut() );
        supplierGuideEntity.setServiceGroup( source.getServiceGroup() );
        supplierGuideEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierGuideEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierGuideEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierGuideEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierGuideEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );
        supplierGuideEntity.setIdioms( idiomDomainSetToIdiomEntitySet( source.getIdioms() ) );
        supplierGuideEntity.setServices( serviceDomainSetToServiceEntitySet( source.getServices() ) );

        return supplierGuideEntity;
    }

    public Set<IdiomDomain> idiomEntitySetToIdiomDomainSet(Set<IdiomEntity> set) {
        if ( set == null ) {
            return null;
        }
        Set<IdiomDomain> set1 = new LinkedHashSet<>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IdiomEntity idiomEntity : set ) {
            set1.add( idiomConverter.toDomain( idiomEntity ) );
        }
        return set1;
    }

    protected Set<IdiomEntity> idiomDomainSetToIdiomEntitySet(Set<IdiomDomain> set) {
        if ( set == null ) {
            return null;
        }
        Set<IdiomEntity> set1 = new LinkedHashSet<IdiomEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( IdiomDomain idiomDomain : set ) {
            set1.add( idiomConverter.toEntity( idiomDomain ) );
        }
        return set1;
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

    public IdBaseDomain toDomainIdBase(SupplierGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierGuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierGuideDomain toDomainSimpleBaseWithType(SupplierGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGuideDomain supplierGuideDomain = new SupplierGuideDomain();

        supplierGuideDomain.setId( source.getId() );
        supplierGuideDomain.setName( source.getName() );
        supplierGuideDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierGuideDomain.setSupplierType( new SupplierTypeDomain() );
            supplierGuideDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierGuideDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierGuideDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierGuideDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierGuideDomain;
    }

    public List<SupplierGuideDomain> toDomainAllSimpleWithType(List<SupplierGuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierGuideDomain toDomainSimpleBaseWithDepartmentRegion(SupplierGuideEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierGuideDomain supplierGuideDomain = new SupplierGuideDomain();

        supplierGuideDomain.setId( source.getId() );
        supplierGuideDomain.setName( source.getName() );
        supplierGuideDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierGuideDomain.setSupplierType( new SupplierTypeDomain() );
            supplierGuideDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierGuideDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierGuideDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierGuideDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierGuideDomain.setPartner( new PartnerDomain());
            supplierGuideDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierGuideDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierGuideDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierGuideDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierGuideDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierGuideDomain;
    }

    public List<SupplierGuideDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierGuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierGuideDomain> toDomainAllSimpleToDomainCaseListSupplierGuide(List<SupplierGuideEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierGuideDomain supplierGuideDomain = new SupplierGuideDomain();
            supplierGuideDomain.setId(entity.getId());
            supplierGuideDomain.setIdentify(entity.getIdentify());
            supplierGuideDomain.setName(entity.getName());
            return supplierGuideDomain;
        }).toList();
    }

    public PageDTO<SupplierGuideDomain> toDomainPageCaseListSupplierGuide(Page<SupplierGuideEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierGuideDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierGuide(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
