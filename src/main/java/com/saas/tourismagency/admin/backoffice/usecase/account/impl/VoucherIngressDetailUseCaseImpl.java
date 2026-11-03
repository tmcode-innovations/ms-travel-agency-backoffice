package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheringressdetail.VoucherIngressDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherIngressDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.changetype.ChangeTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringress.VoucherIngressRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheringressdetail.VoucherIngressDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheringressdetail.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.ChangeTypeDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherIngressDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherIngressDetailUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class VoucherIngressDetailUseCaseImpl implements VoucherIngressDetailUseCase {

    @Autowired
    private VoucherIngressDetailRepo repo;

    @Autowired
    private VoucherIngressRepo voucherIngressRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private ChangeTypeRepo changeTypeRepo;

    @Autowired
    private VoucherIngressDetailConverter converter;

    @Autowired
    private SetFieldDefaultVoucherIngressDetail setFieldDefault;

    @Override
    @Transactional
    public VoucherIngressDetailResponse create(VoucherIngressDetailCreateRequest request) {
        VoucherIngressDomain voucherIngressDomain = null;
        if (request.getVoucherIngress() != null && !request.getVoucherIngress().isEmpty()){
            IdBaseDomain voucherIngressIdBaseDomain = voucherIngressRepo.findByIdEntity(request.getVoucherIngress());
            voucherIngressDomain = new VoucherIngressDomain();
            voucherIngressDomain.setId(voucherIngressIdBaseDomain.getId());
        }
        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }
        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }
        VoucherIngressDetailDomain newDomain = request.to(voucherIngressDomain, customerDomain, supplierDomain, registerGroupDomain, changeTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        VoucherIngressDetailDomain createdDomain = repo.create(newDomain);
        return VoucherIngressDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherIngressDetailResponse create(VoucherIngressDetailCreateRequest request, VoucherIngressDomain voucherIngressDomain) {
        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }
        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }
        VoucherIngressDetailDomain newDomain = request.to(voucherIngressDomain, customerDomain, supplierDomain, registerGroupDomain, changeTypeDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        VoucherIngressDetailDomain createdDomain = repo.create(newDomain);
        return VoucherIngressDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherIngressDetailResponse edit(String id, VoucherIngressDetailUpdateRequest request) {
        if (id == null) {
            return null;
        }
        VoucherIngressDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        VoucherIngressDomain voucherIngressDomain = null;
        if (request.getVoucherIngress() != null && !request.getVoucherIngress().isEmpty()){
            IdBaseDomain voucherIngressIdBaseDomain = voucherIngressRepo.findByIdEntity(request.getVoucherIngress());
            voucherIngressDomain = new VoucherIngressDomain();
            voucherIngressDomain.setId(voucherIngressIdBaseDomain.getId());
        }
        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }
        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }
        VoucherIngressDetailDomain voucherIngressDetailDomain = request.to(voucherIngressDomain, customerDomain, supplierDomain, registerGroupDomain, changeTypeDomain);
        VoucherIngressDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherIngressDetailDomain, old));
        return VoucherIngressDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public VoucherIngressDetailResponse edit(String id, VoucherIngressDetailUpdateRequest request, VoucherIngressDomain voucherIngressDomain) {
        if (id == null) {
            return null;
        }
        VoucherIngressDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        CustomerDomain customerDomain = null;
        if (request.getCustomer() != null && !request.getCustomer().isEmpty()){
            IdBaseDomain customerIdBaseDomain = customerRepo.findByIdEntity(request.getCustomer());
            customerDomain = new CustomerDomain();
            customerDomain.setId(customerIdBaseDomain.getId());
        }
        SupplierDomain supplierDomain = null;
        if (request.getSupplier() != null && !request.getSupplier().isEmpty()){
            IdBaseDomain supplierIdBaseDomain = supplierRepo.findByIdEntity(request.getSupplier());
            supplierDomain = new SupplierDomain();
            supplierDomain.setId(supplierIdBaseDomain.getId());
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            registerGroupDomain = new RegisterGroupDomain();
            registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
        }
        ChangeTypeDomain changeTypeDomain = null;
        if (request.getChangeType() != null && !request.getChangeType().isEmpty()){
            IdBaseDomain changeTypeIdBaseDomain = changeTypeRepo.findByIdEntity(request.getChangeType());
            changeTypeDomain = new ChangeTypeDomain();
            changeTypeDomain.setId(changeTypeIdBaseDomain.getId());
        }
        VoucherIngressDetailDomain voucherIngressDetailDomain = request.to(voucherIngressDomain, customerDomain, supplierDomain, registerGroupDomain, changeTypeDomain);
        VoucherIngressDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherIngressDetailDomain, old));
        return VoucherIngressDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        VoucherIngressDetailResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherIngressDetailResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> VoucherIngressDetailResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressDetailResponse findById(String id) {
        if (id == null) {
            return null;
        }
        VoucherIngressDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return VoucherIngressDetailResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressDetailSearchResponse searchAll(VoucherIngressDetailSearchRequest voucherIngressDetailSearchRequest) {
       PageDTO<VoucherIngressDetailDomain> pageDTO = repo.searchAll(voucherIngressDetailSearchRequest);
       VoucherIngressDetailSearchResponse voucherIngressDetailSearchResponse = new VoucherIngressDetailSearchResponse();
       voucherIngressDetailSearchResponse.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherIngressDetailResponse.from(bankDomain)).toList());
       voucherIngressDetailSearchResponse.setTotalElements(pageDTO.getTotalElements());
       voucherIngressDetailSearchResponse.setTotalPages(pageDTO.getTotalPages());
       return voucherIngressDetailSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressDetailSearchResponse searchFilterAll(VoucherIngressDetailSearchFilterRequest voucherIngressDetailSearchFilterRequest) {
        PageDTO<VoucherIngressDetailDomain> pageDTO = repo.searchAllFilter(voucherIngressDetailSearchFilterRequest);
        VoucherIngressDetailSearchResponse voucherIngressDetailSearchResponse = new VoucherIngressDetailSearchResponse();
        voucherIngressDetailSearchResponse.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherIngressDetailResponse.from(bankDomain)).toList());
        voucherIngressDetailSearchResponse.setTotalElements(pageDTO.getTotalElements());
        voucherIngressDetailSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return voucherIngressDetailSearchResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherIngressDetailResponse> searchAllByVoucherIngress(String idVoucherIngress) {
        List<VoucherIngressDetailDomain> list = repo.searchAllByVoucherIngress(idVoucherIngress);
        return list.stream().map(voucherIngressDetailDomain ->  VoucherIngressDetailResponse.from(voucherIngressDetailDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherIngressDetailResponse searchByVoucherIngressAndVoucherIngressDetail(String idVoucherIngress, String idVoucherIngressDetail) {
        VoucherIngressDetailDomain voucherIngressDetailDomain = repo.searchAllByVoucherIngressAndVoucherIngressDetail(idVoucherIngress, idVoucherIngressDetail);
        return VoucherIngressDetailResponse.from(voucherIngressDetailDomain);
    }

}
