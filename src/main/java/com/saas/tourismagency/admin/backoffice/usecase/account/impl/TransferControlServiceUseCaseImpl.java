package com.saas.tourismagency.admin.backoffice.usecase.account.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.account.transfercontrol.JRTransferControlByServiceRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.account.TransferControlServiceUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.operation.TransferControlUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransferControlServiceUseCaseImpl implements TransferControlServiceUseCase {

    @Autowired
    private ItineraryItemRepo repo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRTransferControlByServiceRepository jRTransferControlByServiceRepository;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private DateUtil dateUtil;

    @Override
    @Transactional
    public TransferControlByTourBibleSearchResponse searchFilterAllTransferControlByServiceBible(TransferControlByTourBibleSearchFilterRequest searchFilterRequest) {
        PageDTO<ItineraryItemDomain> pageDTO = repo.searchAggregatePageableTransferControlByServiceBible(searchFilterRequest);
        TransferControlByTourBibleSearchResponse transferControlByTourBibleSearchResponse = new TransferControlByTourBibleSearchResponse();
        transferControlByTourBibleSearchResponse.setItems(pageDTO.getContent().stream().map(domain ->  TransferControlByTourBibleResponse.from(domain)).toList());
        transferControlByTourBibleSearchResponse.setTotalElements(pageDTO.getTotalElements());
        transferControlByTourBibleSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return transferControlByTourBibleSearchResponse;
    }

    @Override
    @Transactional
    public byte[] searchFilterAllTransferControlByServiceBibleExportPdf(TransferControlByTourBibleSearchFilterRequest searchFilterRequest) {
        List<ItineraryItemDomain> list = repo.searchAggregateTransferControlByServiceBible(searchFilterRequest);
        list = list.stream().collect(Collectors.toList());
        Collections.sort(list);
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);

        String idCustomer = null;
        String idSupplier = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;

        if (searchFilterRequest.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterRequest.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
                    }
                    default -> {

                    }
                }
            }
        }

        if (StringUtils.isNotEmpty(idCustomer)) {
            CustomerDomain customerDomain = customerRepo.findById(idCustomer);
            parameters.put("customer", customerDomain.getName());
        }else {
            parameters.put("customer", "");
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            SupplierDomain supplierDomain = supplierRepo.findById(idSupplier);
            parameters.put("operator", supplierDomain.getName());
        }else {
            parameters.put("operator", "");
        }

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            String dtStart = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            String dtEnd = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            parameters.put("date_init_end", dtStart + " - " + dtEnd);
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            String dtStart = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            parameters.put("date_init_end", dtStart);
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            String dtEnd = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            parameters.put("date_init_end", dtEnd);
        }

        return jRTransferControlByServiceRepository.export(list, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] searchFilterAllTransferControlByServiceBibleExportExcel(TransferControlByTourBibleSearchFilterRequest searchFilterRequest) {
        List<ItineraryItemDomain> list = repo.searchAggregateTransferControlByServiceBible(searchFilterRequest);
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);

        String idCustomer = null;
        String idSupplier = null;
        String itineraryDateTimeStart = null;
        String itineraryDateTimeEnd = null;

        if (searchFilterRequest.getFilter() != null) {
            for (Map.Entry<String, String> entry : searchFilterRequest.getFilter().entrySet()) {
                switch (entry.getKey()) {
                    case "idCustomer" -> {
                        idCustomer = entry.getValue();
                    }
                    case "idSupplier" -> {
                        idSupplier = entry.getValue();
                    }
                    case "itineraryDateTimeStart" -> {
                        itineraryDateTimeStart = entry.getValue();
                    }
                    case "itineraryDateTimeEnd" -> {
                        itineraryDateTimeEnd = entry.getValue();
                    }
                    default -> {

                    }
                }
            }
        }

        if (StringUtils.isNotEmpty(idCustomer)) {
            CustomerDomain customerDomain = customerRepo.findById(idCustomer);
            parameters.put("customer", customerDomain.getName());
        }else {
            parameters.put("customer", "");
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            SupplierDomain supplierDomain = supplierRepo.findById(idSupplier);
            parameters.put("operator", supplierDomain.getName());
        }else {
            parameters.put("operator", "");
        }

        if (StringUtils.isNotEmpty(itineraryDateTimeStart) && StringUtils.isNotEmpty(itineraryDateTimeEnd)) {
            String dtStart = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            String dtEnd = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            parameters.put("date_init_end", dtStart + " - " + dtEnd);
        } else if (StringUtils.isNotEmpty(itineraryDateTimeStart) && (itineraryDateTimeEnd == null || StringUtils.isEmpty(itineraryDateTimeEnd) || StringUtils.isBlank(itineraryDateTimeEnd))) {
            String dtStart = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart));
            parameters.put("date_init_end", dtStart);
        } else if (StringUtils.isNotEmpty(itineraryDateTimeEnd) && (itineraryDateTimeStart == null || StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isBlank(itineraryDateTimeStart))) {
            String dtEnd = LocalDateTimeUtil.toLocalDateFormatShort(dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd));
            parameters.put("date_init_end", dtEnd);
        }
        return jRTransferControlByServiceRepository.export(list, ExportFormatEnum.XLSX.getName(), parameters);
    }
}
