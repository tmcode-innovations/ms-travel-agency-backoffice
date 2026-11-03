package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.account.expenseguidedetail.ExpenseGuideDetailConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.account.SetFieldDefaultExpenseGuideDetail;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguide.ExpenseGuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.expenseguidedetail.ExpenseGuideDetailRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.guide.GuideRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.service.ServiceRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.operation.orderserviceitem.OrderServiceItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.expenseguidedetail.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.ExpenseGuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.IdBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.GuideDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import com.saas.tourismagency.admin.backoffice.domain.operation.OrderServiceItemDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.ExpenseGuideDetailUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ExpenseGuideDetailUseCaseImpl implements ExpenseGuideDetailUseCase {

    @Autowired
    private ExpenseGuideDetailRepo repo;

    @Autowired
    private ExpenseGuideDetailConverter converter;

    @Autowired
    private SetFieldDefaultExpenseGuideDetail setFieldDefault;

    @Autowired
    private ExpenseGuideRepo expenseGuideRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private OrderServiceItemRepo orderServiceItemRepo;

    @Autowired
    private GuideRepo guideRepo;

    @Override
    @Transactional
    public ExpenseGuideDetailResponse create(ExpenseGuideDetailCreateRequest request) {
        ExpenseGuideDomain expenseGuideDomain = null;
        if (request.getExpenseGuide() != null && !request.getExpenseGuide().isEmpty()){
            IdBaseDomain expenseGuideIdBaseDomain = expenseGuideRepo.findByIdEntity(request.getExpenseGuide());
            expenseGuideDomain = new ExpenseGuideDomain();
            expenseGuideDomain.setId(expenseGuideIdBaseDomain.getId());
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        OrderServiceItemDomain orderServiceItemDomain = null;
        if (request.getOrderServiceItem() != null && !request.getOrderServiceItem().isEmpty()){
            orderServiceItemDomain = orderServiceItemRepo.findById(request.getOrderServiceItem());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            guideDriveDomain = guideRepo.findById(request.getGuideDrive());
        }

        ExpenseGuideDetailDomain newDomain = request.to(expenseGuideDomain, serviceDomain, orderServiceItemDomain, guideDriveDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        ExpenseGuideDetailDomain createdDomain = repo.create(newDomain);
        return ExpenseGuideDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ExpenseGuideDetailResponse create(ExpenseGuideDetailCreateRequest request, ExpenseGuideDomain expenseGuideDomain) {
        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        OrderServiceItemDomain orderServiceItemDomain = null;
        if (request.getOrderServiceItem() != null && !request.getOrderServiceItem().isEmpty()){
            orderServiceItemDomain = orderServiceItemRepo.findById(request.getOrderServiceItem());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            guideDriveDomain = guideRepo.findById(request.getGuideDrive());
        }

        ExpenseGuideDetailDomain newDomain = request.to(expenseGuideDomain, serviceDomain, orderServiceItemDomain, guideDriveDomain);
        newDomain.setTenant(UserResolver.resolveUser().getTenant());

        ExpenseGuideDetailDomain createdDomain = repo.create(newDomain);
        return ExpenseGuideDetailResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public ExpenseGuideDetailResponse edit(String id, ExpenseGuideDetailUpdateRequest request) {
        if (id == null) {
            return null;
        }
        ExpenseGuideDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ExpenseGuideDomain expenseGuideDomain = null;
        if (request.getExpenseGuide() != null && !request.getExpenseGuide().isEmpty()){
            IdBaseDomain expenseGuideIdBaseDomain = expenseGuideRepo.findByIdEntity(request.getExpenseGuide());
            expenseGuideDomain = new ExpenseGuideDomain();
            expenseGuideDomain.setId(expenseGuideIdBaseDomain.getId());
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        OrderServiceItemDomain orderServiceItemDomain = null;
        if (request.getOrderServiceItem() != null && !request.getOrderServiceItem().isEmpty()){
            orderServiceItemDomain = orderServiceItemRepo.findById(request.getOrderServiceItem());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            guideDriveDomain = guideRepo.findById(request.getGuideDrive());
        }

        ExpenseGuideDetailDomain expenseGuideDetailDomain = request.to(expenseGuideDomain, serviceDomain, orderServiceItemDomain, guideDriveDomain);
        ExpenseGuideDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(expenseGuideDetailDomain, old));
        return ExpenseGuideDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public ExpenseGuideDetailResponse edit(String id, ExpenseGuideDetailUpdateRequest request, ExpenseGuideDomain expenseGuideDomain) {
        if (id == null) {
            return null;
        }
        ExpenseGuideDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }

        ServiceDomain serviceDomain = null;
        if (request.getService() != null && !request.getService().isEmpty()){
            serviceDomain = serviceRepo.findById(request.getService());
        }

        OrderServiceItemDomain orderServiceItemDomain = null;
        if (request.getOrderServiceItem() != null && !request.getOrderServiceItem().isEmpty()){
            orderServiceItemDomain = orderServiceItemRepo.findById(request.getOrderServiceItem());
        }

        GuideDomain guideDriveDomain = null;
        if (request.getGuideDrive() != null && !request.getGuideDrive().isEmpty()){
            guideDriveDomain = guideRepo.findById(request.getGuideDrive());
        }

        ExpenseGuideDetailDomain expenseGuideDetailDomain = request.to(expenseGuideDomain, serviceDomain, orderServiceItemDomain, guideDriveDomain);
        ExpenseGuideDetailDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(expenseGuideDetailDomain, old));
        return ExpenseGuideDetailResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        ExpenseGuideDetailResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseGuideDetailResponse> findAll() {
        return repo.findAll().stream().map(bankDomain -> ExpenseGuideDetailResponse.from(bankDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideDetailResponse findById(String id) {
        if (id == null) {
            return null;
        }
        ExpenseGuideDetailDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No entity found with id '%s'.".formatted(id));
        }
        return ExpenseGuideDetailResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseGuideDetailResponse> findAllByExpenseGuide(String idExpenseGuide) {
        return repo.searchAllByExpenseGuide(idExpenseGuide).stream().map(ExpenseGuideDetailResponse::from).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideDetailSearchResponse searchAll(ExpenseGuideDetailSearchRequest bankSearchRequest) {
       PageDTO<ExpenseGuideDetailDomain> pageDTO = repo.searchAll(bankSearchRequest);
       ExpenseGuideDetailSearchResponse bankSearchRespose = new ExpenseGuideDetailSearchResponse();
       bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ExpenseGuideDetailResponse.from(bankDomain)).toList());
       bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
       bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return bankSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseGuideDetailSearchResponse searchFilterAll(ExpenseGuideDetailSearchFilterRequest bankSearchFilterRequest) {
        PageDTO<ExpenseGuideDetailDomain> pageDTO = repo.searchAllFilter(bankSearchFilterRequest);
        ExpenseGuideDetailSearchResponse bankSearchRespose = new ExpenseGuideDetailSearchResponse();
        bankSearchRespose.setItems(pageDTO.getContent().stream().map(bankDomain ->  ExpenseGuideDetailResponse.from(bankDomain)).toList());
        bankSearchRespose.setTotalElements(pageDTO.getTotalElements());
        bankSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return bankSearchRespose;
    }
}
