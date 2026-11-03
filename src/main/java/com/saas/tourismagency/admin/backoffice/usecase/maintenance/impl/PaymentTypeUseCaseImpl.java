package com.saas.tourismagency.admin.backoffice.usecase.maintenance.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.converter.maintenance.paymenttype.PaymentTypeConverter;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.maintenance.SetFieldDefaultPaymentType;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.paymenttype.PaymentTypeRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.paymenttype.PersistPaymentTypeNumberCallbackImpl;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.paymenttype.*;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.PaymentTypeDomain;
import com.saas.tourismagency.admin.backoffice.usecase.maintenance.PaymentTypeUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PaymentTypeUseCaseImpl implements PaymentTypeUseCase {

    @Autowired
    private PaymentTypeRepo repo;

    @Autowired
    private PaymentTypeConverter converter;

    @Autowired
    private SetFieldDefaultPaymentType setFieldDefault;

    @Autowired
    PersistPaymentTypeNumberCallbackImpl persistPaymentTypeNumberCallbackImpl;

    @Override
    @Transactional
    public PaymentTypeResponse create(PaymentTypeCreateRequest request) {
        PaymentTypeDomain paymentTypeDomainExist = repo.findByName(request.getName());
        if (paymentTypeDomainExist != null){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PaymentTypeDomain newDomain = request.to();
        newDomain.setIdentify(persistPaymentTypeNumberCallbackImpl.generateNumber());
        newDomain.setTenant(UserResolver.resolveUser().getTenant());
        PaymentTypeDomain createdDomain = repo.create(newDomain);
        return PaymentTypeResponse.from(createdDomain);
    }

    @Override
    @Transactional
    public PaymentTypeResponse edit(String id, PaymentTypeUpdateRequest request) {
        if (id == null) {
            return null;
        }
        PaymentTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No payment type found with id '%s'.".formatted(id));
        }
        PaymentTypeDomain paymentTypeDomainExist = repo.findByName(request.getName());
        if (paymentTypeDomainExist != null && !paymentTypeDomainExist.getId().equals(old.getId())){
            throw new BadRequestException(MessagesValidation.VALIDATION_ENTITY_EXIST_BY_NAME);
        }
        PaymentTypeDomain paymentTypeDomain = request.to();
        paymentTypeDomain.setIdentify(old.getIdentify());
        PaymentTypeDomain editedDomain = repo.edit(setFieldDefault.setDefaultFieldBaseUpdated(paymentTypeDomain, old));
        return PaymentTypeResponse.from(editedDomain);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        PaymentTypeResponse old = findById(id);
        repo.deleteById(old.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentTypeResponse> findAll() {
        return repo.findAll(false).stream().map(paymentTypeDomain -> PaymentTypeResponse.from(paymentTypeDomain)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentTypeResponse findById(String id) {
        if (id == null) {
            return null;
        }
        PaymentTypeDomain old = repo.findById(id);
        if (old == null) {
            throw new NotFoundException("No payment type found with id '%s'.".formatted(id));
        }
        return PaymentTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentTypeSearchResponse searchAll(PaymentTypeSearchRequest paymentTypeSearchRequest) {
       PageDTO<PaymentTypeDomain> pageDTO = repo.searchAll(paymentTypeSearchRequest);
       PaymentTypeSearchResponse paymentTypeSearchRespose = new PaymentTypeSearchResponse();
       paymentTypeSearchRespose.setPaymentTypes(pageDTO.getContent().stream().map(paymentTypeDomain ->  PaymentTypeResponse.from(paymentTypeDomain)).toList());
       paymentTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
       paymentTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
       return paymentTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentTypeSearchResponse searchFilterAll(PaymentTypeSearchFilterRequest paymentTypeSearchFilterRequest) {
        PageDTO<PaymentTypeDomain> pageDTO = repo.searchAllFilter(paymentTypeSearchFilterRequest);
        PaymentTypeSearchResponse paymentTypeSearchRespose = new PaymentTypeSearchResponse();
        paymentTypeSearchRespose.setPaymentTypes(pageDTO.getContent().stream().map(paymentTypeDomain ->  PaymentTypeResponse.from(paymentTypeDomain)).toList());
        paymentTypeSearchRespose.setTotalElements(pageDTO.getTotalElements());
        paymentTypeSearchRespose.setTotalPages(pageDTO.getTotalPages());
        return paymentTypeSearchRespose;
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentTypeResponse findByName(String name) {
        if (name == null) {
            return null;
        }
        PaymentTypeDomain old = repo.findByName(name);
        if (old == null) {
            throw new NotFoundException("No payment type found with name '%s'.".formatted(name));
        }
        return PaymentTypeResponse.from(old);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentTypeResponse findByIdentify(String identify) {
        if (identify == null) {
            return null;
        }
        PaymentTypeDomain old = repo.findByIdentify(identify);
        if (old == null) {
            throw new NotFoundException("No payment type found with identify '%s'.".formatted(identify));
        }
        return PaymentTypeResponse.from(old);
    }
}
