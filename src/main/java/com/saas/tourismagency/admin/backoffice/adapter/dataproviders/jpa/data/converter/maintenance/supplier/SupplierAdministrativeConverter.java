package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.supplier;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.documenttype.DocumentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.sector.SectorConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.suppliertype.SupplierTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.touristdestine.TouristDestineConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.SupplierAdministrativeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.embedded.AddressEmbeddedDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierAdministrativeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierTypeDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierAdministrativeConverter implements Converter<SupplierAdministrativeDomain, SupplierAdministrativeEntity> {

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

    @Override
    public SupplierAdministrativeDomain toDomain(SupplierAdministrativeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAdministrativeDomain supplierAdministrativeDomain = new SupplierAdministrativeDomain();

        supplierAdministrativeDomain.setId( source.getId() );
        supplierAdministrativeDomain.setDeleted( source.getDeleted() );
        supplierAdministrativeDomain.setEnabled( source.getEnabled() );
        supplierAdministrativeDomain.setMigrated( source.getMigrated() );
        supplierAdministrativeDomain.setCreatedAt( source.getCreatedAt() );
        supplierAdministrativeDomain.setUpdatedAt( source.getUpdatedAt() );
        supplierAdministrativeDomain.setDeletedAt( source.getDeletedAt() );
        supplierAdministrativeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        supplierAdministrativeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        supplierAdministrativeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        supplierAdministrativeDomain.setIdentifierName( source.getIdentifierName() );
        supplierAdministrativeDomain.setTenant( source.getTenant() );
        supplierAdministrativeDomain.setName( source.getName() );
        supplierAdministrativeDomain.setDescription( source.getDescription() );
        supplierAdministrativeDomain.setIdentify( source.getIdentify() );
        supplierAdministrativeDomain.setTypeInheritance( source.getTypeInheritance() );
        supplierAdministrativeDomain.setRuc( source.getRuc() );
        supplierAdministrativeDomain.setConfirmation( source.getConfirmation() );
        supplierAdministrativeDomain.setReConfirmation( source.getReConfirmation() );
        supplierAdministrativeDomain.setValueConfirmation( source.getValueConfirmation() );
        supplierAdministrativeDomain.setValueReConfirmation( source.getValueReConfirmation() );
        supplierAdministrativeDomain.setSummary( source.getSummary() );
        supplierAdministrativeDomain.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierAdministrativeDomain.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierAdministrativeDomain.setPoliticsFree( source.getPoliticsFree() );
        supplierAdministrativeDomain.setPoliticsChildren( source.getPoliticsChildren() );
        supplierAdministrativeDomain.setPoliticsShows( source.getPoliticsShows() );
        supplierAdministrativeDomain.setAdministrative( source.getAdministrative() );
        supplierAdministrativeDomain.setCheckIn( source.getCheckIn() );
        supplierAdministrativeDomain.setCheckOut( source.getCheckOut() );
        supplierAdministrativeDomain.setServiceGroup( source.getServiceGroup() );
        supplierAdministrativeDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        supplierAdministrativeDomain.setSupplierType( supplierTypeConverter.toDomain( source.getSupplierType() ) );
        supplierAdministrativeDomain.setSector( sectorConverter.toDomain( source.getSector() ) );
        supplierAdministrativeDomain.setDocumentType( documentTypeConverter.toDomain( source.getDocumentType() ) );
        supplierAdministrativeDomain.setTouristDestine( touristDestineConverter.toDomain( source.getTouristDestine() ) );

        return supplierAdministrativeDomain;
    }

    @Override
    public SupplierAdministrativeEntity toEntity(SupplierAdministrativeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAdministrativeEntity supplierAdministrativeEntity = new SupplierAdministrativeEntity();

        supplierAdministrativeEntity.setId( source.getId() );
        supplierAdministrativeEntity.setDeleted( source.getDeleted() );
        supplierAdministrativeEntity.setEnabled( source.getEnabled() );
        supplierAdministrativeEntity.setMigrated( source.getMigrated() );
        supplierAdministrativeEntity.setCreatedAt( source.getCreatedAt() );
        supplierAdministrativeEntity.setUpdatedAt( source.getUpdatedAt() );
        supplierAdministrativeEntity.setDeletedAt( source.getDeletedAt() );
        supplierAdministrativeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        supplierAdministrativeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        supplierAdministrativeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        supplierAdministrativeEntity.setIdentifierName( source.getIdentifierName() );
        supplierAdministrativeEntity.setTenant( source.getTenant() );
        supplierAdministrativeEntity.setName( source.getName() );
        supplierAdministrativeEntity.setDescription( source.getDescription() );
        supplierAdministrativeEntity.setIdentify( source.getIdentify() );
        supplierAdministrativeEntity.setTypeInheritance( source.getTypeInheritance() );
        supplierAdministrativeEntity.setRuc( source.getRuc() );
        supplierAdministrativeEntity.setConfirmation( source.getConfirmation() );
        supplierAdministrativeEntity.setReConfirmation( source.getReConfirmation() );
        supplierAdministrativeEntity.setValueConfirmation( source.getValueConfirmation() );
        supplierAdministrativeEntity.setValueReConfirmation( source.getValueReConfirmation() );
        supplierAdministrativeEntity.setSummary( source.getSummary() );
        supplierAdministrativeEntity.setPoliticsCancel1( source.getPoliticsCancel1() );
        supplierAdministrativeEntity.setPoliticsCancel2( source.getPoliticsCancel2() );
        supplierAdministrativeEntity.setPoliticsFree( source.getPoliticsFree() );
        supplierAdministrativeEntity.setPoliticsChildren( source.getPoliticsChildren() );
        supplierAdministrativeEntity.setPoliticsShows( source.getPoliticsShows() );
        supplierAdministrativeEntity.setAdministrative( source.getAdministrative() );
        supplierAdministrativeEntity.setCheckIn( source.getCheckIn() );
        supplierAdministrativeEntity.setCheckOut( source.getCheckOut() );
        supplierAdministrativeEntity.setServiceGroup( source.getServiceGroup() );
        supplierAdministrativeEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        supplierAdministrativeEntity.setSupplierType( supplierTypeConverter.toEntity( source.getSupplierType() ) );
        supplierAdministrativeEntity.setSector( sectorConverter.toEntity( source.getSector() ) );
        supplierAdministrativeEntity.setDocumentType( documentTypeConverter.toEntity( source.getDocumentType() ) );
        supplierAdministrativeEntity.setTouristDestine( touristDestineConverter.toEntity( source.getTouristDestine() ) );

        return supplierAdministrativeEntity;
    }

    public IdBaseDomain toDomainIdBase(SupplierAdministrativeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(SupplierAdministrativeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        simpleBaseDomain.setName( source.getName() );

        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<SupplierAdministrativeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SupplierAdministrativeDomain toDomainSimpleBaseWithType(SupplierAdministrativeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAdministrativeDomain supplierAdministrativeDomain = new SupplierAdministrativeDomain();

        supplierAdministrativeDomain.setId( source.getId() );
        supplierAdministrativeDomain.setName( source.getName() );
        supplierAdministrativeDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierAdministrativeDomain.setSupplierType( new SupplierTypeDomain() );
            supplierAdministrativeDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierAdministrativeDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierAdministrativeDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierAdministrativeDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        return supplierAdministrativeDomain;
    }

    public List<SupplierAdministrativeDomain> toDomainAllSimpleWithType(List<SupplierAdministrativeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithType(entity)).toList();
    }

    public SupplierAdministrativeDomain toDomainSimpleBaseWithDepartmentRegion(SupplierAdministrativeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SupplierAdministrativeDomain supplierAdministrativeDomain = new SupplierAdministrativeDomain();

        supplierAdministrativeDomain.setId( source.getId() );
        supplierAdministrativeDomain.setName( source.getName() );
        supplierAdministrativeDomain.setDescription( source.getDescription() );
        if (source.getSupplierType() != null &&
                source.getSupplierType().getId() != null &&
                !source.getSupplierType().getId().isEmpty()) {
            supplierAdministrativeDomain.setSupplierType( new SupplierTypeDomain() );
            supplierAdministrativeDomain.getSupplierType().setId( source.getSupplierType().getId() );
            supplierAdministrativeDomain.getSupplierType().setName( source.getSupplierType().getName() );
            supplierAdministrativeDomain.getSupplierType().setDescription( source.getSupplierType().getDescription() );
            supplierAdministrativeDomain.getSupplierType().setIdentify( source.getSupplierType().getIdentify() );
        }
        if (source.getPartner() != null &&
                source.getPartner().getId() != null &&
                !source.getPartner().getId().isEmpty()) {
            supplierAdministrativeDomain.setPartner( new PartnerDomain());
            supplierAdministrativeDomain.getPartner().setId(source.getPartner().getId());
            if (source.getPartner().getAddress() != null){
                supplierAdministrativeDomain.getPartner().setAddress(new AddressEmbeddedDomain());
                if (source.getPartner().getAddress().getDepartmentRegion() != null &&
                        source.getPartner().getAddress().getDepartmentRegion().getId() != null &&
                        !source.getPartner().getAddress().getDepartmentRegion().getId().isEmpty()) {
                    supplierAdministrativeDomain.getPartner().getAddress().setDepartmentRegion(new DepartmentRegionDomain());
                    supplierAdministrativeDomain.getPartner().getAddress().getDepartmentRegion().setId(source.getPartner().getAddress().getDepartmentRegion().getId());
                    supplierAdministrativeDomain.getPartner().getAddress().getDepartmentRegion().setName(source.getPartner().getAddress().getDepartmentRegion().getName());
                }
            }
        }
        return supplierAdministrativeDomain;
    }

    public List<SupplierAdministrativeDomain> toDomainAllSimpleWithDepartmentRegion(List<SupplierAdministrativeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithDepartmentRegion(entity)).toList();
    }

    public List<SupplierAdministrativeDomain> toDomainAllSimpleToDomainCaseListSupplierAdministrative(List<SupplierAdministrativeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            SupplierAdministrativeDomain supplierAdministrativeDomain = new SupplierAdministrativeDomain();
            supplierAdministrativeDomain.setId(entity.getId());
            supplierAdministrativeDomain.setIdentify(entity.getIdentify());
            supplierAdministrativeDomain.setName(entity.getName());
            return supplierAdministrativeDomain;
        }).toList();
    }

    public PageDTO<SupplierAdministrativeDomain> toDomainPageCaseListSupplierAdministrative(Page<SupplierAdministrativeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<SupplierAdministrativeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListSupplierAdministrative(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
