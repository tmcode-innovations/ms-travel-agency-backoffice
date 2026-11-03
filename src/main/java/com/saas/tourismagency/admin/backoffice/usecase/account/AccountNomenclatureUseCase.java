package com.saas.tourismagency.admin.backoffice.usecase.account;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.nomenclature.*;

import java.util.List;

public interface AccountNomenclatureUseCase {

    public List<PaymentTypeResponse> searchAllPaymentType();

    public List<VoucherStateResponse> searchAllVoucherState();

    public List<VoucherTypeResponse> searchAllVoucherType();

    public List<CostSheetTwoStateResponse> searchAllCostSheetTwoState();

    public List<ExpenseGuideStateResponse> searchAllExpenseGuideState();

    public List<ProformaStateResponse> searchAllProformaState();

    public List<ProformaTypeResponse> searchAllProformaType();

    public List<DocumentAccountStateResponse> searchAllDocumentAccountState();

    public List<DocumentAccountTypeResponse> searchAllDocumentAccountType();

    public List<ValueTypeShowSupplierResponse> searchAllValueTypeShowSupplier();
}
