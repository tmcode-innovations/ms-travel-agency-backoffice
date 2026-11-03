package com.saas.tourismagency.admin.backoffice.usecase.operation.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.departmentregion.DepartmentRegionRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.itineraryitem.ItineraryItemRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.passengergroup.PassengerGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.booking.registergroup.RegisterGroupRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.customer.CustomerRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.maintenance.supplier.SupplierRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.*;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.passengergroup.JRPassengerGroupRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.operation.transfercontrol.JRTransferControlByTourRepository;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.operation.transfercontrol.JRTransferControlRepository;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.DateUtil;
import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.DepartmentRegionDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierDomain;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.core.exceptions.MessagesValidation;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.operation.TransferControlUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import dev.mm.javaee.core.exceptions.BadRequestException;
import dev.mm.javaee.core.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransferControlUseCaseImpl implements TransferControlUseCase {

    @Autowired
    private ItineraryItemRepo repo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRTransferControlByTourRepository jRTransferControlByTourRepository;

    @Autowired
    private JRTransferControlRepository jrTransferControlRepository;

    @Autowired
    private PassengerGroupRepo passengerGroupRepo;

    @Autowired
    private RegisterGroupRepo registerGroupRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private DepartmentRegionRepo departmentRegionRepo;

    @Autowired
    private DateUtil dateUtil;

    @Override
    @Transactional
    public TransferControlSearchResponse searchFilterAllTransferControl(TransferControlSearchFilterRequest searchFilterRequest) {
        PageDTO<ItineraryItemDomain> pageDTO = repo.searchAggregatePageableTransferControlBible(searchFilterRequest);
        TransferControlSearchResponse transferControlSearchResponse = new TransferControlSearchResponse();
        transferControlSearchResponse.setItems(pageDTO.getContent().stream().map(domain ->  TransferControlResponse.from(domain)).toList());
        transferControlSearchResponse.setTotalElements(pageDTO.getTotalElements());
        transferControlSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return transferControlSearchResponse;
    }

    @Override
    @Transactional
    public TransferControlByTourBibleSearchResponse searchFilterAllTransferControlByTourBible(TransferControlByTourBibleSearchFilterRequest searchFilterRequest) {
        PageDTO<ItineraryItemDomain> pageDTO = repo.searchAggregatePageableTransferControlByTourBible(searchFilterRequest);
        TransferControlByTourBibleSearchResponse transferControlByTourBibleSearchResponse = new TransferControlByTourBibleSearchResponse();
        transferControlByTourBibleSearchResponse.setItems(pageDTO.getContent().stream().map(domain ->  TransferControlByTourBibleResponse.from(domain)).toList());
        transferControlByTourBibleSearchResponse.setTotalElements(pageDTO.getTotalElements());
        transferControlByTourBibleSearchResponse.setTotalPages(pageDTO.getTotalPages());
        return transferControlByTourBibleSearchResponse;
    }

    @Override
    @Transactional
    public byte[] searchFilterAllTransferControlExport(TransferControlSearchFilterRequest searchFilterRequest) {
        List<ItineraryItemDomain> list = repo.searchAggregateTransferControlBible(searchFilterRequest);
        list = list.stream().collect(Collectors.toList());
        Collections.sort(list);
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("cantRegister", ((list != null && !list.isEmpty()) ? list.size() : 0));

        String idRegisterGroup = null;
        String idSupplier = null;
        String idDepartmentRegion = null;
        String idCustomer = null;
        String idGuideTransport = null;
        String idGuideTransferDrive = null;
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
                    case "idRegisterGroup" -> {
                        idRegisterGroup = entry.getValue();
                    }
                    case "idDepartmentRegion" -> {
                        idDepartmentRegion = entry.getValue();
                    }
                    case "idGuideTransport" -> {
                        idGuideTransport = entry.getValue();
                    }
                    case "idGuideTransferDrive" -> {
                        idGuideTransferDrive = entry.getValue();
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

        if (StringUtils.isNotEmpty(idRegisterGroup)) {
            List<PassengerGroupDomain> passengerGroupDomains = passengerGroupRepo.searchAllByRegisterGroup(idRegisterGroup);
            if (passengerGroupDomains != null && !passengerGroupDomains.isEmpty()) {
                passengerGroupDomains = passengerGroupDomains.stream().collect(Collectors.toList());
                StringBuffer result = new StringBuffer();
                for (PassengerGroupDomain passengerGroupDomain: passengerGroupDomains) {
                    result.append((passengerGroupDomain.getTitlePersonDescription() != null ? passengerGroupDomain.getTitlePersonDescription() + " " : "") +  passengerGroupDomain.getPerson().getFirstName() + " " + passengerGroupDomain.getPerson().getLastName() + ", ");
                }
                result.delete(result.length()-2, result.length()-1);
                parameters.put("strPassengers", result.toString());
            }
            RegisterGroupDomain registerGroupDomain = registerGroupRepo.findById(idRegisterGroup);
            parameters.put("registerGroup", registerGroupDomain.getName());
        }else{
            parameters.put("registerGroup", "TODOS LOS GRUPOS");
        }

        if (StringUtils.isNotEmpty(idDepartmentRegion)) {
            DepartmentRegionDomain departmentRegionDomain = departmentRegionRepo.findById(idDepartmentRegion);
            parameters.put("localidades", departmentRegionDomain.getName());
        }else {
            parameters.put("localidades", "TODAS LAS LOCALIDADES");
        }

        if (StringUtils.isNotEmpty(idSupplier)) {
            SupplierDomain supplierDomain = supplierRepo.findById(idSupplier);
            parameters.put("operator", supplierDomain.getName());
        }else {
            parameters.put("operator", "TODAS LAS RESERVAS");
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

        return jrTransferControlRepository.export(list, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] searchFilterAllTransferControlByTourBibleExportPdf(TransferControlByTourBibleSearchFilterRequest searchFilterRequest) {
        validateExportDateRange(searchFilterRequest.getFilter());
        List<ItineraryItemDomain> list = repo.searchAggregateTransferControlByTourBible(searchFilterRequest);
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

        return jRTransferControlByTourRepository.export(list, ExportFormatEnum.PDF.getName(), parameters);
    }

    @Override
    @Transactional
    public byte[] searchFilterAllTransferControlByTourBibleExportExcel(TransferControlByTourBibleSearchFilterRequest searchFilterRequest) {
        validateExportDateRange(searchFilterRequest.getFilter());
        List<ItineraryItemDomain> list = repo.searchAggregateTransferControlByTourBible(searchFilterRequest);
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
        return jRTransferControlByTourRepository.export(list, ExportFormatEnum.XLSX.getName(), parameters);
    }

    private void validateExportDateRange(Map<String, String> filter) {
        String idCustomer = filter != null ? filter.get("idCustomer") : null;
        String idSupplier = filter != null ? filter.get("idSupplier") : null;

        if (StringUtils.isNotEmpty(idCustomer) || StringUtils.isNotEmpty(idSupplier)) {
            return;
        }

        String itineraryDateTimeStart = filter != null ? filter.get("itineraryDateTimeStart") : null;
        String itineraryDateTimeEnd = filter != null ? filter.get("itineraryDateTimeEnd") : null;

        if (StringUtils.isEmpty(itineraryDateTimeStart) || StringUtils.isEmpty(itineraryDateTimeEnd)) {
            throw new BadRequestException(MessagesValidation.VALIDATION_EXPORT_DATE_RANGE_REQUIRED);
        }

        LocalDate startDate = dateUtil.formatIso8601ToLocalDate(itineraryDateTimeStart);
        LocalDate endDate = dateUtil.formatIso8601ToLocalDate(itineraryDateTimeEnd);

        if (endDate.isBefore(startDate)) {
            throw new BadRequestException(MessagesValidation.VALIDATION_EXPORT_DATE_RANGE_INVALID);
        }

        if (startDate.plusMonths(6).isBefore(endDate)) {
            throw new BadRequestException(MessagesValidation.VALIDATION_EXPORT_DATE_RANGE_EXCEEDED);
        }
    }
}
