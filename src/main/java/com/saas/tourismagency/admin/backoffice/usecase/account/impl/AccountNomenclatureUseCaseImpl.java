package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature.*;
import com.saas.tourismagency.admin.backoffice.domain.account.enums.*;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.usecase.account.AccountNomenclatureUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AccountNomenclatureUseCaseImpl implements AccountNomenclatureUseCase {

    @Autowired
    private CompanyUseCase companyUseCase;

    @Override
    public List<PaymentTypeResponse> searchAllPaymentType() {
        return EnumMappeableService
                .map(PaymentTypeEnum.class)
                .stream()
                .map(paymentTypeModel -> PaymentTypeResponse.from(paymentTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherStateResponse> searchAllVoucherState() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        List<VoucherStateResponse> result = null;
        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            result = EnumMappeableService
                    .map(VoucherStateEnum.class)
                    .stream()
                    .map(voucherStateModel -> VoucherStateResponse.fromConfiguration(voucherStateModel))
                    .toList();
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            result = EnumMappeableService
                    .map(VoucherStateEnum.class)
                    .stream()
                    .map(voucherStateModel -> VoucherStateResponse.from(voucherStateModel))
                    .toList();
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherTypeResponse> searchAllVoucherType() {
        return EnumMappeableService
                .map(VoucherTypeEnum.class)
                .stream()
                .map(voucherTypeModel -> VoucherTypeResponse.from(voucherTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostSheetTwoStateResponse> searchAllCostSheetTwoState() {
        return EnumMappeableService
                .map(CostSheetTwoStateEnum.class)
                .stream()
                .map(costSheetTwoStateModel -> CostSheetTwoStateResponse.from(costSheetTwoStateModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseGuideStateResponse> searchAllExpenseGuideState() {
        return EnumMappeableService
                .map(ExpenseGuideStateEnum.class)
                .stream()
                .map(expenseGuideStateModel -> ExpenseGuideStateResponse.from(expenseGuideStateModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaStateResponse> searchAllProformaState() {
        return EnumMappeableService
                .map(ProformaStateEnum.class)
                .stream()
                .map(proformaStateModel -> ProformaStateResponse.from(proformaStateModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProformaTypeResponse> searchAllProformaType() {
        return EnumMappeableService
                .map(ProformaTypeEnum.class)
                .stream()
                .map(proformaTypeModel -> ProformaTypeResponse.from(proformaTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentAccountStateResponse> searchAllDocumentAccountState() {
        return EnumMappeableService
                .map(DocumentAccountStateEnum.class)
                .stream()
                .map(documentAccountStateModel -> DocumentAccountStateResponse.from(documentAccountStateModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentAccountTypeResponse> searchAllDocumentAccountType() {
        return EnumMappeableService
                .map(DocumentAccountTypeEnum.class)
                .stream()
                .map(documentAccountTypeModel -> DocumentAccountTypeResponse.from(documentAccountTypeModel))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ValueTypeShowSupplierResponse> searchAllValueTypeShowSupplier() {
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();
        return EnumMappeableService
                .map(ValueTypeShowSupplierEnum.class)
                .stream()
                .filter(valueTypeShowSupplierModel -> belongsValueTypeShowSupplierToTheConfiguration(valueTypeShowSupplierModel, companyDomain.getSetting().getGeneralConfigurationType()))
                .map(valueTypeShowSupplierModel -> ValueTypeShowSupplierResponse.from(valueTypeShowSupplierModel))
                .toList();
    }

    private boolean belongsValueTypeShowSupplierToTheConfiguration(ValueTypeShowSupplierEnum.ValueTypeShowSupplierModel model, String generalConfigurationType){
        if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            if (
                    model.name().equals(ValueTypeShowSupplierEnum.BOOKIG.getName()) ||
                            model.name().equals(ValueTypeShowSupplierEnum.ADMINISTRATIVE.getName())
            ){
                return true;
            }else{
                return false;
            }
        }else if (generalConfigurationType.equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            if (
                    model.name().equals(ValueTypeShowSupplierEnum.BOOKIG.getName()) ||
                            model.name().equals(ValueTypeShowSupplierEnum.ADMINISTRATIVE.getName()) ||
                            model.name().equals(ValueTypeShowSupplierEnum.FREE.getName())
            ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}
