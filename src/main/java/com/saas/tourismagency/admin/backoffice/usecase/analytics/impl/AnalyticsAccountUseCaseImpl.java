package com.saas.tourismagency.admin.backoffice.usecase.analytics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.AnalyticsAccountRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.account.voucheregressdetail.VoucherEgressDetailSearchResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.voucheregress.JRVoucherEgressRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.account.JRReportVoucherPurchaseBySupplierRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDetailDomain;
import com.saas.tourismagency.admin.backoffice.domain.account.VoucherEgressDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsAccountUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.ValidationErrorMessage;
import dev.mm.javaee.core.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnalyticsAccountUseCaseImpl implements AnalyticsAccountUseCase {

    @Autowired
    private AnalyticsAccountRepo analyticsAccountRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRReportVoucherPurchaseBySupplierRepository jrReportVoucherPurchaseBySupplierRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    @Override
    @Transactional(readOnly = true)
    public VoucherEgressDetailSearchResponse searchFilterAllVoucherPurchaseBySupplier(VoucherEgressDetailSearchFilterRequest searchFilterRequest) {
        PageDTO<VoucherEgressDetailDomain> pageDTO = analyticsAccountRepo.searchPageableVoucherPurchaseBySupplier(searchFilterRequest);
        VoucherEgressDetailSearchResponse searchRespose = new VoucherEgressDetailSearchResponse();
        searchRespose.setItems(pageDTO.getContent().stream().map(domain ->  VoucherEgressDetailResponse.from(domain)).toList());
        searchRespose.setTotalElements(pageDTO.getTotalElements());
        searchRespose.setTotalPages(pageDTO.getTotalPages());
        return searchRespose;
    }

    final Map<String, String> VOUCHER_PURCHASE_SUPPLIER_FIELDS_FILTER_REPORT = Map.ofEntries(
            Map.entry("nameValueTypeShowSupplier", "nameValueTypeShowSupplier"),
            Map.entry("nameRegisterGroup", "nameRegisterGroup"),
            Map.entry("nameSupplierType", "nameSupplierType"),
            Map.entry("nameSupplier", "nameSupplier"),
            Map.entry("nameDocumentAccountType", "nameDocumentAccountType"),
            Map.entry("numberDoc", "numberDoc"),
            Map.entry("noCheck", "noCheck"),
            Map.entry("dateStart", "dateStart"),
            Map.entry("dateEnd", "dateEnd")
    );

    @Override
    @Transactional
    public byte[] exportVoucherPurchaseBySupplierPdf(VoucherEgressDetailSearchReportFilterRequest searchFilterRequest) {
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        List<VoucherEgressDetailDomain> list = analyticsAccountRepo.searchVoucherPurchaseBySupplier(searchFilterRequest);
        if (list != null && !list.isEmpty()){
            list = list.stream().collect(Collectors.toList());
        }else{
            list = new ArrayList<>();
        }

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();

        String nameValueTypeShowSupplier = null;
        String nameRegisterGroup = null;
        String nameSupplierType = null;
        String nameSupplier = null;
        String nameDocumentAccountType = null;
        String numberDoc = null;
        String noCheck = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterRequest.getFilterReport() != null) {
            for (Map.Entry<String, String> entry : searchFilterRequest.getFilterReport().entrySet()) {
                switch (entry.getKey()) {
                    case "nameValueTypeShowSupplier" -> {
                        nameValueTypeShowSupplier = entry.getValue();
                    }
                    case "nameRegisterGroup" -> {
                        nameRegisterGroup = entry.getValue();
                    }
                    case "nameSupplierType" -> {
                        nameSupplierType = entry.getValue();
                    }
                    case "nameSupplier" -> {
                        nameSupplier = entry.getValue();
                    }
                    case "nameDocumentAccountType" -> {
                        nameDocumentAccountType = entry.getValue();
                    }
                    case "numberDoc" -> {
                        numberDoc = entry.getValue();
                    }
                    case "noCheck" -> {
                        noCheck = entry.getValue();
                    }
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
                    }
                    default ->
                            filterErrorMessages.add(
                                    new ValidationErrorMessage(
                                            "filter.key",
                                            entry.getKey(),
                                            "Unknown property to filter for"
                                    )
                            );
                }
            }
        }

        if (StringUtils.isNotEmpty(nameValueTypeShowSupplier)) {
            parameters.put("voucher_type", nameValueTypeShowSupplier);
        }else{
            parameters.put("voucher_type", "");
        }
        if (StringUtils.isNotEmpty(nameRegisterGroup)) {
            parameters.put("group", nameRegisterGroup);
        }else{
            parameters.put("group", "");
        }
        if (StringUtils.isNotEmpty(nameSupplierType)) {
            parameters.put("supplier_type", nameSupplierType);
        }else{
            parameters.put("supplier_type", "");
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            parameters.put("supplier", nameSupplier);
        }else{
            parameters.put("supplier", "");
        }
        if (StringUtils.isNotEmpty(nameDocumentAccountType)) {
            parameters.put("document_type", nameDocumentAccountType);
        }else{
            parameters.put("document_type", "");
        }
        if (StringUtils.isNotEmpty(noCheck)) {
            parameters.put("no_cheque", noCheck);
        }else{
            parameters.put("no_cheque", "");
        }
        if (StringUtils.isNotEmpty(numberDoc)) {
            parameters.put("no_document", numberDoc);
        }else{
            parameters.put("no_document", "");
        }
        if (StringUtils.isNotEmpty(dateStart)) {
            parameters.put("date_init", LocalDateTimeUtil.toShortDateFromFilter(dateStart));
        }else{
            parameters.put("date_init", "");
        }
        if (StringUtils.isNotEmpty(dateEnd)) {
            parameters.put("date_end", LocalDateTimeUtil.toShortDateFromFilter(dateEnd));
        }else{
            parameters.put("date_end", "");
        }

        return jrReportVoucherPurchaseBySupplierRepository.exportPdf(list, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] exportVoucherPurchaseBySupplierExcel(VoucherEgressDetailSearchReportFilterRequest searchFilterRequest) {
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();

        List<VoucherEgressDetailDomain> list = analyticsAccountRepo.searchVoucherPurchaseBySupplier(searchFilterRequest);

        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())){
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<ValidationErrorMessage> filterErrorMessages = new ArrayList<>();

        String nameValueTypeShowSupplier = null;
        String nameRegisterGroup = null;
        String nameSupplierType = null;
        String nameSupplier = null;
        String nameDocumentAccountType = null;
        String numberDoc = null;
        String noCheck = null;
        String dateStart = null;
        String dateEnd = null;

        if (searchFilterRequest.getFilterReport() != null) {
            for (Map.Entry<String, String> entry : searchFilterRequest.getFilterReport().entrySet()) {
                switch (entry.getKey()) {
                    case "nameValueTypeShowSupplier" -> {
                        nameValueTypeShowSupplier = entry.getValue();
                    }
                    case "nameRegisterGroup" -> {
                        nameRegisterGroup = entry.getValue();
                    }
                    case "nameSupplierType" -> {
                        nameSupplierType = entry.getValue();
                    }
                    case "nameSupplier" -> {
                        nameSupplier = entry.getValue();
                    }
                    case "nameDocumentAccountType" -> {
                        nameDocumentAccountType = entry.getValue();
                    }
                    case "numberDoc" -> {
                        numberDoc = entry.getValue();
                    }
                    case "noCheck" -> {
                        noCheck = entry.getValue();
                    }
                    case "dateStart" -> {
                        dateStart = entry.getValue();
                    }
                    case "dateEnd" -> {
                        dateEnd = entry.getValue();
                    }
                    default ->
                            filterErrorMessages.add(
                                    new ValidationErrorMessage(
                                            "filter.key",
                                            entry.getKey(),
                                            "Unknown property to filter for"
                                    )
                            );
                }
            }
        }

        if (StringUtils.isNotEmpty(nameValueTypeShowSupplier)) {
            parameters.put("voucher_type", nameValueTypeShowSupplier);
        }else{
            parameters.put("voucher_type", "");
        }
        if (StringUtils.isNotEmpty(nameRegisterGroup)) {
            parameters.put("group", nameRegisterGroup);
        }else{
            parameters.put("group", "");
        }
        if (StringUtils.isNotEmpty(nameSupplierType)) {
            parameters.put("supplier_type", nameSupplierType);
        }else{
            parameters.put("supplier_type", "");
        }
        if (StringUtils.isNotEmpty(nameSupplier)) {
            parameters.put("supplier", nameSupplier);
        }else{
            parameters.put("supplier", "");
        }
        if (StringUtils.isNotEmpty(nameDocumentAccountType)) {
            parameters.put("document_type", nameDocumentAccountType);
        }else{
            parameters.put("document_type", "");
        }
        if (StringUtils.isNotEmpty(noCheck)) {
            parameters.put("no_cheque", noCheck);
        }else{
            parameters.put("no_cheque", "");
        }
        if (StringUtils.isNotEmpty(numberDoc)) {
            parameters.put("no_document", numberDoc);
        }else{
            parameters.put("no_document", "");
        }
        if (StringUtils.isNotEmpty(dateStart)) {
            parameters.put("date_init", LocalDateTimeUtil.toShortDateFromFilter(dateStart));
        }else{
            parameters.put("date_init", "");
        }
        if (StringUtils.isNotEmpty(dateEnd)) {
            parameters.put("date_end", LocalDateTimeUtil.toShortDateFromFilter(dateEnd));
        }else{
            parameters.put("date_end", "");
        }

        return jrReportVoucherPurchaseBySupplierRepository.exportExcel(list, ExportFormatEnum.XLSX.getName(), parameters);
    }
}
