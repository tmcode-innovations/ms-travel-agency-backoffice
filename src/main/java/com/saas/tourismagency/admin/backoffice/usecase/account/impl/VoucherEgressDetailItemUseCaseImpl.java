package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.voucheregressdetailitem.VoucherEgressDetailItemConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultVoucherEgressDetailItem;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.currency.CurrencyRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.servicepqttourist.ServicePqtTouristRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregress.VoucherEgressRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetail.VoucherEgressDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.voucheregressdetailitem.VoucherEgressDetailItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.detraction.DetractionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.igv.IgvRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetailitem.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.*;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.DetractionDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.IgvDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.VoucherEgressDetailItemUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VoucherEgressDetailItemUseCaseImpl implements VoucherEgressDetailItemUseCase {

    @Autowired
    private VoucherEgressDetailItemRepo repo;

    @Autowired
    private VoucherEgressRepo voucherEgressRepo;

    @Autowired
    private VoucherEgressDetailRepo voucherEgressDetailRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ServicePqtTouristRepo servicePqtTouristRepo;

    @Autowired
    private IgvRepo igvRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private DetractionRepo detractionRepo;

    @Autowired
    private VoucherEgressDetailItemConverter converter;

    @Autowired
    private SetFieldDefaultVoucherEgressDetailItem setFieldDefault;

    @Override
    @Transactional
    public VoucherEgressDetailItemResponse create(VoucherEgressDetailItemCreateRequest request) {
        VoucherEgressDomain voucherEgressDomain = null;
        if (request.getVoucherEgress() != null && !request.getVoucherEgress().isEmpty()){
            IdBaseDomain voucherEgressIdBaseDomain = voucherEgressRepo.findByIdEntity(request.getVoucherEgress());
            if (voucherEgressIdBaseDomain != null){
                voucherEgressDomain = new VoucherEgressDomain();
                voucherEgressDomain.setId(voucherEgressIdBaseDomain.getId());
            }
        }
        VoucherEgressDetailDomain voucherEgressDetailDomain = null;
        if (request.getVoucherEgressDetail() != null && !request.getVoucherEgressDetail().isEmpty()){
            IdBaseDomain voucherEgressDetailIdBaseDomain = voucherEgressDetailRepo.findByIdEntity(request.getVoucherEgressDetail());
            if (voucherEgressDetailIdBaseDomain != null){
                voucherEgressDetailDomain = new VoucherEgressDetailDomain();
                voucherEgressDetailDomain.setId(voucherEgressDetailIdBaseDomain.getId());
            }
        }
        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            if (currencyIdBaseDomain != null){
                currencyDomain = new CurrencyDomain();
                currencyDomain.setId(currencyIdBaseDomain.getId());
            }
        }
        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            if (servicePqtTouristIdBaseDomain != null){
                servicePqtTouristDomain = new ServicePqtTouristDomain();
                servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
            }
        }
        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            if (igvIdBaseDomain != null){
                igvDomain = new IgvDomain();
                igvDomain.setId(igvIdBaseDomain.getId());
            }
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            if (registerGroupIdBaseDomain != null){
                registerGroupDomain = new RegisterGroupDomain();
                registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
            }
        }
        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            if (detractionIdBaseDomain != null){
                detractionDomain = new DetractionDomain();
                detractionDomain.setId(detractionIdBaseDomain.getId());
            }
        }
        VoucherEgressDetailItemDomain newDomain = request.to(voucherEgressDomain, voucherEgressDetailDomain, currencyDomain, servicePqtTouristDomain, igvDomain, registerGroupDomain, detractionDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        List<VoucherEgressDetailItemDomain> listTemp = repo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListSimpleNoItem(voucherEgressDomain.getId(), voucherEgressDetailDomain.getId());
        if (listTemp != null && !listTemp.isEmpty()){
            listTemp = listTemp.stream().collect(Collectors.toList());
            Collections.sort(listTemp);
            newDomain.setNoItem(listTemp.getLast().getNoItem() + 1);
        }else{
            newDomain.setNoItem(1);
        }

        VoucherEgressDetailItemDomain createdDomain = repo.create(newDomain);
        return VoucherEgressDetailItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherEgressDetailItemResponse create(VoucherEgressDetailItemCreateRequest request, VoucherEgressDomain voucherEgressDomain, VoucherEgressDetailDomain voucherEgressDetailDomain) {
        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            if (currencyIdBaseDomain != null){
                currencyDomain = new CurrencyDomain();
                currencyDomain.setId(currencyIdBaseDomain.getId());
            }
        }
        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            if (servicePqtTouristIdBaseDomain != null){
                servicePqtTouristDomain = new ServicePqtTouristDomain();
                servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
            }
        }
        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            if (igvIdBaseDomain != null){
                igvDomain = new IgvDomain();
                igvDomain.setId(igvIdBaseDomain.getId());
            }
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            if (registerGroupIdBaseDomain != null){
                registerGroupDomain = new RegisterGroupDomain();
                registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
            }
        }
        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            if (detractionIdBaseDomain != null){
                detractionDomain = new DetractionDomain();
                detractionDomain.setId(detractionIdBaseDomain.getId());
            }
        }
        VoucherEgressDetailItemDomain newDomain = request.to(voucherEgressDomain, voucherEgressDetailDomain, currencyDomain, servicePqtTouristDomain, igvDomain, registerGroupDomain, detractionDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        List<VoucherEgressDetailItemDomain> listTemp = repo.searchAllByVoucherEgressAndVoucherEgressDetailCaseListSimpleNoItem(voucherEgressDomain.getId(), voucherEgressDetailDomain.getId());
        if (listTemp != null && !listTemp.isEmpty()){
            listTemp = listTemp.stream().collect(Collectors.toList());
            Collections.sort(listTemp);
            newDomain.setNoItem(listTemp.getLast().getNoItem() + 1);
        }else{
            newDomain.setNoItem(1);
        }

        VoucherEgressDetailItemDomain createdDomain = repo.create(newDomain);
        return VoucherEgressDetailItemResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public VoucherEgressDetailItemResponse edit(String id, VoucherEgressDetailItemUpdateRequest request) {
        if (id == null) {
            return null;
        }
        VoucherEgressDetailItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        VoucherEgressDomain voucherEgressDomain = null;
        if (request.getVoucherEgress() != null && !request.getVoucherEgress().isEmpty()){
            IdBaseDomain voucherEgressIdBaseDomain = voucherEgressRepo.findByIdEntity(request.getVoucherEgress());
            if (voucherEgressIdBaseDomain != null){
                voucherEgressDomain = new VoucherEgressDomain();
                voucherEgressDomain.setId(voucherEgressIdBaseDomain.getId());
            }
        }
        VoucherEgressDetailDomain voucherEgressDetailDomain = null;
        if (request.getVoucherEgressDetail() != null && !request.getVoucherEgressDetail().isEmpty()){
            IdBaseDomain voucherEgressDetailIdBaseDomain = voucherEgressDetailRepo.findByIdEntity(request.getVoucherEgressDetail());
            if (voucherEgressDetailIdBaseDomain != null){
                voucherEgressDetailDomain = new VoucherEgressDetailDomain();
                voucherEgressDetailDomain.setId(voucherEgressDetailIdBaseDomain.getId());
            }
        }
        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            if (currencyIdBaseDomain != null){
                currencyDomain = new CurrencyDomain();
                currencyDomain.setId(currencyIdBaseDomain.getId());
            }
        }
        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            if (servicePqtTouristIdBaseDomain != null){
                servicePqtTouristDomain = new ServicePqtTouristDomain();
                servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
            }
        }
        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            if (igvIdBaseDomain != null){
                igvDomain = new IgvDomain();
                igvDomain.setId(igvIdBaseDomain.getId());
            }
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            if (registerGroupIdBaseDomain != null){
                registerGroupDomain = new RegisterGroupDomain();
                registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
            }
        }
        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            if (detractionIdBaseDomain != null){
                detractionDomain = new DetractionDomain();
                detractionDomain.setId(detractionIdBaseDomain.getId());
            }
        }
        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = request.to(voucherEgressDomain, voucherEgressDetailDomain, currencyDomain, servicePqtTouristDomain, igvDomain, registerGroupDomain, detractionDomain);
        voucherEgressDetailItemDomain.setNoItem(old.getNoItem());
        VoucherEgressDetailItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherEgressDetailItemDomain, old));
        return VoucherEgressDetailItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public VoucherEgressDetailItemResponse edit(String id, VoucherEgressDetailItemUpdateRequest request, VoucherEgressDomain voucherEgressDomain, VoucherEgressDetailDomain voucherEgressDetailDomain) {
        if (id == null) {
            return null;
        }
        VoucherEgressDetailItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        CurrencyDomain currencyDomain = null;
        if (request.getCurrency() != null && !request.getCurrency().isEmpty()){
            IdBaseDomain currencyIdBaseDomain = currencyRepo.findByIdEntity(request.getCurrency());
            if (currencyIdBaseDomain != null){
                currencyDomain = new CurrencyDomain();
                currencyDomain.setId(currencyIdBaseDomain.getId());
            }
        }
        ServicePqtTouristDomain servicePqtTouristDomain = null;
        if (request.getServicePqtTourist() != null && !request.getServicePqtTourist().isEmpty()){
            IdBaseDomain servicePqtTouristIdBaseDomain = servicePqtTouristRepo.findByIdEntity(request.getServicePqtTourist());
            if (servicePqtTouristIdBaseDomain != null){
                servicePqtTouristDomain = new ServicePqtTouristDomain();
                servicePqtTouristDomain.setId(servicePqtTouristIdBaseDomain.getId());
            }
        }
        IgvDomain igvDomain = null;
        if (request.getIgv() != null && !request.getIgv().isEmpty()){
            IdBaseDomain igvIdBaseDomain = igvRepo.findByIdEntity(request.getIgv());
            if (igvIdBaseDomain != null){
                igvDomain = new IgvDomain();
                igvDomain.setId(igvIdBaseDomain.getId());
            }
        }
        RegisterGroupDomain registerGroupDomain = null;
        if (request.getRegisterGroup() != null && !request.getRegisterGroup().isEmpty()){
            IdBaseDomain registerGroupIdBaseDomain = registerGroupRepo.findByIdEntity(request.getRegisterGroup());
            if (registerGroupIdBaseDomain != null){
                registerGroupDomain = new RegisterGroupDomain();
                registerGroupDomain.setId(registerGroupIdBaseDomain.getId());
            }
        }
        DetractionDomain detractionDomain = null;
        if (request.getDetraction() != null && !request.getDetraction().isEmpty()){
            IdBaseDomain detractionIdBaseDomain = detractionRepo.findByIdEntity(request.getDetraction());
            if (detractionIdBaseDomain != null){
                detractionDomain = new DetractionDomain();
                detractionDomain.setId(detractionIdBaseDomain.getId());
            }
        }
        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = request.to(voucherEgressDomain, voucherEgressDetailDomain, currencyDomain, servicePqtTouristDomain, igvDomain, registerGroupDomain, detractionDomain);
        voucherEgressDetailItemDomain.setNoItem(old.getNoItem());
        VoucherEgressDetailItemDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(voucherEgressDetailItemDomain, old));
        return VoucherEgressDetailItemResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        VoucherEgressDetailItemResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressDetailItemResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> VoucherEgressDetailItemResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailItemResponse findById(String id) {
        if (id == null) {
            return null;
        }
        VoucherEgressDetailItemDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return VoucherEgressDetailItemResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailItemSearchResponse searchAll(VoucherEgressDetailItemSearchRequest bankSearchRequest) {
       PageDTO<VoucherEgressDetailItemDomain> pageDTO = repo.searchAll(bankSearchRequest);
       VoucherEgressDetailItemSearchResponse bankSearchRespose = new VoucherEgressDetailItemSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherEgressDetailItemResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailItemSearchResponse searchFilterAll(VoucherEgressDetailItemSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<VoucherEgressDetailItemDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        VoucherEgressDetailItemSearchResponse bankSearchRespose = new VoucherEgressDetailItemSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  VoucherEgressDetailItemResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherEgressDetailItemResponse> searchAllByVoucherEgressAndVoucherEgressDetail(String idVoucherEgress, String idVoucherEgressDetail) {
        List<VoucherEgressDetailItemDomain> list = repo.searchAllByVoucherEgressAndVoucherEgressDetail(idVoucherEgress, idVoucherEgressDetail);
        return list.stream().map(voucherEgressDetailItemDomain ->  VoucherEgressDetailItemResponse.from(voucherEgressDetailItemDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailItemResponse searchByVoucherEgressAndVoucherEgressDetailAndVoucherEgressDetailItem(String idVoucherEgress, String idVoucherEgressDetail, String idVoucherEgressDetailItem) {
        VoucherEgressDetailItemDomain voucherEgressDetailItemDomain = repo.searchAllByVoucherEgressAndVoucherEgressDetailAndVoucherEgressDetailItem(idVoucherEgress, idVoucherEgressDetail, idVoucherEgressDetailItem);
        return VoucherEgressDetailItemResponse.from(voucherEgressDetailItemDomain);
    }
}
