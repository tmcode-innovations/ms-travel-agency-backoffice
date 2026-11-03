package com.saas.tourismagency.admin.backoffice.usecase.analytics;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchResponse;

public interface AnalyticsAccountUseCase {

    public VoucherEgressDetailSearchResponse searchFilterAllVoucherPurchaseBySupplier(VoucherEgressDetailSearchFilterRequest searchFilterRequest);

    public byte[] exportVoucherPurchaseBySupplierPdf(VoucherEgressDetailSearchReportFilterRequest searchFilterRequest);

    public byte[] exportVoucherPurchaseBySupplierExcel(VoucherEgressDetailSearchReportFilterRequest searchFilterRequest);
}
