package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.employee;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.area.AreaConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.partner.PartnerConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.base.person.PersonConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.hr.position.PositionConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.security.user.UserConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.hr.EmployeeEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.AreaDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PersonDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.SimpleBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.PositionDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.SimpleEmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.repo.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeConverter implements Converter<EmployeeDomain, EmployeeEntity> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private AreaConverter areaConverter;

    @Autowired
    private PositionConverter positionConverter;

    @Autowired
    private PartnerConverter partnerConverter;

    @Override
    public EmployeeDomain toDomain(EmployeeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        EmployeeDomain employeeDomain = new EmployeeDomain();

        employeeDomain.setId( source.getId() );
        employeeDomain.setDeleted( source.getDeleted() );
        employeeDomain.setEnabled( source.getEnabled() );
        employeeDomain.setMigrated( source.getMigrated() );
        employeeDomain.setCreatedAt( source.getCreatedAt() );
        employeeDomain.setUpdatedAt( source.getUpdatedAt() );
        employeeDomain.setDeletedAt( source.getDeletedAt() );
        employeeDomain.setCreatedBy( userConverter.userEntityToUserBaseDomain( source.getCreatedBy() ) );
        employeeDomain.setUpdatedBy( userConverter.userEntityToUserBaseDomain( source.getUpdatedBy() ) );
        employeeDomain.setDeletedBy( userConverter.userEntityToUserBaseDomain( source.getDeletedBy() ) );
        employeeDomain.setIdentifierName( source.getIdentifierName() );
        employeeDomain.setTenant( source.getTenant() );
        employeeDomain.setName( source.getName() );
        employeeDomain.setDescription( source.getDescription() );
        employeeDomain.setIdentify( source.getIdentify() );
        employeeDomain.setPerson( personConverter.toDomain( source.getPerson() ) );
        employeeDomain.setArea( areaConverter.toDomain( source.getArea() ) );
        employeeDomain.setPosition( positionConverter.toDomain( source.getPosition() ) );
        employeeDomain.setPartner( partnerConverter.toDomain( source.getPartner() ) );
        employeeDomain.setFileNameImageSign( source.getFileNameImageSign() );
        employeeDomain.setUrlFileImageSign( source.getUrlFileImageSign() );
        return employeeDomain;
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDomain source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setId( source.getId() );
        employeeEntity.setDeleted( source.getDeleted() );
        employeeEntity.setEnabled( source.getEnabled() );
        employeeEntity.setMigrated( source.getMigrated() );
        employeeEntity.setCreatedAt( source.getCreatedAt() );
        employeeEntity.setUpdatedAt( source.getUpdatedAt() );
        employeeEntity.setDeletedAt( source.getDeletedAt() );
        employeeEntity.setCreatedBy( userConverter.userBaseDomainToUserEntity( source.getCreatedBy() ) );
        employeeEntity.setUpdatedBy( userConverter.userBaseDomainToUserEntity( source.getUpdatedBy() ) );
        employeeEntity.setDeletedBy( userConverter.userBaseDomainToUserEntity( source.getDeletedBy() ) );
        employeeEntity.setIdentifierName( source.getIdentifierName() );
        employeeEntity.setTenant( source.getTenant() );
        employeeEntity.setName( source.getName() );
        employeeEntity.setDescription( source.getDescription() );
        employeeEntity.setIdentify( source.getIdentify() );
        employeeEntity.setPerson( personConverter.toEntity( source.getPerson() ) );
        employeeEntity.setArea( areaConverter.toEntity( source.getArea() ) );
        employeeEntity.setPosition( positionConverter.toEntity( source.getPosition() ) );
        employeeEntity.setPartner( partnerConverter.toEntity( source.getPartner() ) );
        employeeEntity.setFileNameImageSign( source.getFileNameImageSign() );
        employeeEntity.setUrlFileImageSign( source.getUrlFileImageSign() );
        return employeeEntity;
    }

    public IdBaseDomain toDomainIdBase(EmployeeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        IdBaseDomain idBaseDomain = new IdBaseDomain();

        idBaseDomain.setId( source.getId() );
        return idBaseDomain;
    }

    public SimpleBaseDomain toDomainSimpleBase(EmployeeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleBaseDomain simpleBaseDomain = new SimpleBaseDomain();

        simpleBaseDomain.setId( source.getId() );
        if (source.getPerson() != null &&
                source.getPerson().getId() != null &&
                !source.getPerson().getId().isEmpty()) {
            simpleBaseDomain.setName( source.getPerson().getFirstName() + " " + source.getPerson().getLastName() );
        }
        return simpleBaseDomain;
    }

    public List<SimpleBaseDomain> toDomainAllSimple(List<EmployeeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBase(entity)).toList();
    }

    public SimpleEmployeeDomain toDomainSimpleBaseWithConditionalArea(EmployeeEntity source) throws RuntimeException {
        if ( source == null ) {
            return null;
        }

        SimpleEmployeeDomain simpleEmployeeDomain = new SimpleEmployeeDomain();

        simpleEmployeeDomain.setId( source.getId() );
        if (source.getPerson() != null &&
                source.getPerson().getId() != null &&
                !source.getPerson().getId().isEmpty()) {
            simpleEmployeeDomain.setName( source.getPerson().getFirstName() + " " + source.getPerson().getLastName() );
        }

        if (source.getArea() != null &&
                source.getArea().getId() != null &&
                !source.getArea().getId().isEmpty()) {
            simpleEmployeeDomain.setArea(new AreaDomain());
            simpleEmployeeDomain.getArea().setId(source.getArea().getId());
            simpleEmployeeDomain.getArea().setName(source.getArea().getName());
        }

        return simpleEmployeeDomain;
    }

    public List<SimpleEmployeeDomain> toDomainAllSimpleWithConditionalArea(List<EmployeeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomainSimpleBaseWithConditionalArea(entity)).toList();
    }

    public List<EmployeeDomain> toDomainAllSimpleToDomainCaseListEmployee(List<EmployeeEntity> list) throws RuntimeException {
        return list.stream().map((entity) -> {
            EmployeeDomain employeeDomain = new EmployeeDomain();
            employeeDomain.setId(entity.getId());
            employeeDomain.setIdentify(entity.getIdentify());
            if (entity.getPerson() != null && entity.getPerson().getId() != null && !entity.getPerson().getId().isEmpty()){
                employeeDomain.setPerson(new PersonDomain());
                employeeDomain.getPerson().setId(entity.getPerson().getId());
                employeeDomain.getPerson().setFirstName(entity.getPerson().getFirstName());
                employeeDomain.getPerson().setLastName(entity.getPerson().getLastName());
                employeeDomain.getPerson().setIdentifyPerson(entity.getPerson().getIdentifyPerson());
                employeeDomain.getPerson().setGender(entity.getPerson().getGender());
            }
            if (entity.getArea() != null && entity.getArea().getId() != null && !entity.getArea().getId().isEmpty()){
                employeeDomain.setArea(new AreaDomain());
                employeeDomain.getArea().setId(entity.getArea().getId());
                employeeDomain.getArea().setName(entity.getArea().getName());
            }
            if (entity.getPosition() != null && entity.getPosition().getId() != null && !entity.getPosition().getId().isEmpty()){
                employeeDomain.setPosition(new PositionDomain());
                employeeDomain.getPosition().setId(entity.getPosition().getId());
                employeeDomain.getPosition().setName(entity.getPosition().getName());
            }
            return employeeDomain;
        }).toList();
    }

    public PageDTO<EmployeeDomain> toDomainPageCaseListEmployee(Page<EmployeeEntity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<EmployeeDomain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAllSimpleToDomainCaseListEmployee(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }
}
