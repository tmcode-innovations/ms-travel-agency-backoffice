package com.saas.tourismagency.admin.backoffice.usecase.analytics.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.AnalyticsBookingRepo;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchReportFilterRequest;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.analytics.booking.AnalyticsBookingSearchResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.ItineraryItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.passengergroup.PassengerGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup.RegisterGroupResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registerbookinglodging.RegisterBookingLodgingResponse;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.analytics.booking.*;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.enums.ExportFormatEnum;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.JasperReportResource;
import com.saas.tourismagency.admin.backoffice.domain.base.EntityBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.ItineraryItemDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterBookingLodgingDomain;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.analytics.RegisterGroupReportItem;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.CompanyDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.enums.GeneralConfigurationTypeEnum;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import com.saas.tourismagency.admin.backoffice.usecase.analytics.AnalyticsBookingUseCase;
import com.saas.tourismagency.admin.backoffice.usecase.core.CompanyUseCase;
import dev.mm.javaee.core.dto.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AnalyticsBookingUseCaseImpl implements AnalyticsBookingUseCase {

    @Autowired
    private AnalyticsBookingRepo analyticsBookingRepo;

    @Autowired
    private CompanyUseCase companyUseCase;

    @Autowired
    private JRReportItineraryRepository jrReportItineraryRepository;

    @Autowired
    private JRReportRegisterGroupRepository jrReportRegisterGroupRepository;

    @Autowired
    private JRReportPassengerRepository jrReportPassengerRepository;

    @Autowired
    private JRReportSupplierRepository jrReportSupplierRepository;

    @Autowired
    private JRReportLodgingRepository jrReportLodgingRepository;

    @Autowired
    private JRReportGuideRepository jrReportGuideRepository;

    @Autowired
    private JRReportStatisticsRepository jrReportStatisticsRepository;

    @Autowired
    private JasperReportResource jasperReportResource;

    private Map<String, Object> buildBaseParameters() {
        Map<String, Object> parameters = new HashMap<>();
        UserDomain userDomain = UserResolver.resolveUser();
        CompanyDomain companyDomain = companyUseCase.getMyActiveCompany();

        parameters.put("activeUser", userDomain);
        parameters.put("activeCompany", companyDomain);
        parameters.put("dateNow", LocalDate.now());

        if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_LEGENDARY.getName())) {
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlLegendaryLogoReport());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                log.error("Logo not found", e);
            } catch (IOException e) {
                log.error("Error loading logo", e);
            }
        } else if (companyDomain.getSetting().getGeneralConfigurationType().equals(GeneralConfigurationTypeEnum.TYPE_GURU_EXPLORER.getName())) {
            try {
                ClassPathResource classPathResource = new ClassPathResource(jasperReportResource.getUrlGuruLogoGuruExplorers());
                parameters.put("logo", classPathResource.getInputStream());
            } catch (FileNotFoundException e) {
                log.error("Logo not found", e);
            } catch (IOException e) {
                log.error("Error loading logo", e);
            }
        }

        return parameters;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateItinerary(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<ItineraryItemDomain> pageDTO = analyticsBookingRepo.searchPageableItinerary(request);
        AnalyticsBookingSearchResponse<ItineraryItemResponse> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ItineraryItemResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateGroup(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<RegisterGroupDomain> pageDTO = analyticsBookingRepo.searchPageableGroup(request);
        AnalyticsBookingSearchResponse<RegisterGroupResponse> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(RegisterGroupResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateBibleActivity(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<ItineraryItemDomain> pageDTO = analyticsBookingRepo.searchPageableBibleActivity(request);
        AnalyticsBookingSearchResponse<ItineraryItemResponse> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ItineraryItemResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginatePassenger(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<PassengerGroupDomain> pageDTO = analyticsBookingRepo.searchPageablePassenger(request);
        AnalyticsBookingSearchResponse<PassengerGroupResponse> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(PassengerGroupResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateHotel(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<RegisterBookingLodgingDomain> pageDTO = analyticsBookingRepo.searchPageableHotel(request);
        AnalyticsBookingSearchResponse<RegisterBookingLodgingDomain> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateOperator(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<RegisterBookingLodgingDomain> pageDTO = analyticsBookingRepo.searchPageableOperator(request);
        AnalyticsBookingSearchResponse<RegisterBookingLodgingDomain> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateRestaurant(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<RegisterBookingLodgingDomain> pageDTO = analyticsBookingRepo.searchPageableRestaurant(request);
        AnalyticsBookingSearchResponse<RegisterBookingLodgingDomain> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateTransport(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<ItineraryItemDomain> pageDTO = analyticsBookingRepo.searchPageableTransport(request);
        AnalyticsBookingSearchResponse<ItineraryItemDomain> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateAirLine(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<ItineraryItemDomain> pageDTO = analyticsBookingRepo.searchPageableAirLine(request);
        AnalyticsBookingSearchResponse<ItineraryItemDomain> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateGroupByProgram(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<RegisterGroupDomain> pageDTO = analyticsBookingRepo.searchPageableGroupByProgram(request);
        AnalyticsBookingSearchResponse<RegisterGroupResponse> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(RegisterGroupResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateGroupByActivity(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<ItineraryItemDomain> pageDTO = analyticsBookingRepo.searchPageableGroupByActivity(request);
        AnalyticsBookingSearchResponse<ItineraryItemResponse> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(ItineraryItemResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateHotelExpiration(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<RegisterBookingLodgingDomain> pageDTO = analyticsBookingRepo.searchPageableHotelExpiration(request);
        AnalyticsBookingSearchResponse<RegisterBookingLodgingResponse> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent().stream().map(RegisterBookingLodgingResponse::from).toList());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsForeignOperator(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<Map> pageDTO = analyticsBookingRepo.searchPageableStatisticsForeignOperator(request);
        AnalyticsBookingSearchResponse<Map<String, Object>> response = new AnalyticsBookingSearchResponse<>();
        response.setItems((java.util.List<Map<String, Object>>) (java.util.List<?>) pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsCountry(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<Map> pageDTO = analyticsBookingRepo.searchPageableStatisticsCountry(request);
        AnalyticsBookingSearchResponse<Map<String, Object>> response = new AnalyticsBookingSearchResponse<>();
        response.setItems((java.util.List<Map<String, Object>>) (java.util.List<?>) pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsResponsible(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<Map> pageDTO = analyticsBookingRepo.searchPageableStatisticsResponsible(request);
        AnalyticsBookingSearchResponse<Map<String, Object>> response = new AnalyticsBookingSearchResponse<>();
        response.setItems((java.util.List<Map<String, Object>>) (java.util.List<?>) pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateStatisticsHotel(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<Map> pageDTO = analyticsBookingRepo.searchPageableStatisticsHotel(request);
        AnalyticsBookingSearchResponse<Map<String, Object>> response = new AnalyticsBookingSearchResponse<>();
        response.setItems((java.util.List<Map<String, Object>>) (java.util.List<?>) pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    @Override
    
    public AnalyticsBookingSearchResponse<?> searchFilterPaginateGuide(AnalyticsBookingSearchFilterRequest request) {
        PageDTO<ItineraryItemDomain> pageDTO = analyticsBookingRepo.searchPageableGuide(request);
        AnalyticsBookingSearchResponse<ItineraryItemDomain> response = new AnalyticsBookingSearchResponse<>();
        response.setItems(pageDTO.getContent());
        response.setTotalElements(pageDTO.getTotalElements());
        response.setTotalPages(pageDTO.getTotalPages());
        return response;
    }

    // ===================== EXPORTS =====================

    @Override
    
    public byte[] exportItineraryPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllItinerary(request);
        return jrReportItineraryRepository.exportPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportItineraryExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllItinerary(request);
        return jrReportItineraryRepository.exportExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGroupPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterGroupReportItem> list = analyticsBookingRepo.searchAllGroup(request);
        return jrReportRegisterGroupRepository.exportPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGroupExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterGroupReportItem> list = analyticsBookingRepo.searchAllGroup(request);
        return jrReportRegisterGroupRepository.exportExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportBibleActivityPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllBibleActivity(request);
        return jrReportItineraryRepository.exportBibleActivityPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportBibleActivityExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllBibleActivity(request);
        return jrReportItineraryRepository.exportBibleActivityExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportPassengerPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<PassengerGroupDomain> list = analyticsBookingRepo.searchAllPassenger(request);
        return jrReportPassengerRepository.exportPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportPassengerExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<PassengerGroupDomain> list = analyticsBookingRepo.searchAllPassenger(request);
        return jrReportPassengerRepository.exportExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportHotelPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllHotel(request);
        return jrReportSupplierRepository.exportHotelPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportHotelExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllHotel(request);
        return jrReportSupplierRepository.exportHotelExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportOperatorPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllOperator(request);
        return jrReportSupplierRepository.exportOperatorPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportOperatorExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllOperator(request);
        return jrReportSupplierRepository.exportOperatorExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportRestaurantPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllRestaurant(request);
        return jrReportSupplierRepository.exportRestaurantPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportRestaurantExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllRestaurant(request);
        return jrReportSupplierRepository.exportRestaurantExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportTransportPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllTransport(request);
        return jrReportSupplierRepository.exportTransportPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportTransportExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllTransport(request);
        return jrReportSupplierRepository.exportTransportExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportAirLinePdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllAirLine(request);
        return jrReportSupplierRepository.exportAirLinePdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportAirLineExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllAirLine(request);
        return jrReportSupplierRepository.exportAirLineExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGroupByProgramPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterGroupReportItem> list = analyticsBookingRepo.searchAllGroupByProgram(request);
        return jrReportRegisterGroupRepository.exportGroupByProgramPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGroupByProgramExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterGroupReportItem> list = analyticsBookingRepo.searchAllGroupByProgram(request);
        return jrReportRegisterGroupRepository.exportGroupByProgramExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGroupByActivityPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllGroupByActivity(request);
        return jrReportItineraryRepository.exportGroupByActivityPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGroupByActivityExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllGroupByActivity(request);
        return jrReportItineraryRepository.exportGroupByActivityExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportHotelExpirationPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllHotelExpiration(request);
        return jrReportLodgingRepository.exportHotelExpirationPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportHotelExpirationExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<RegisterBookingLodgingDomain> list = analyticsBookingRepo.searchAllHotelExpiration(request);
        return jrReportLodgingRepository.exportHotelExpirationExcel(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsForeignOperatorPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsForeignOperator(request);
        return jrReportStatisticsRepository.exportForeignOperatorPdf(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsForeignOperatorExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsForeignOperator(request);
        return jrReportStatisticsRepository.exportForeignOperatorExcel(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsCountryPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsCountry(request);
        return jrReportStatisticsRepository.exportCountryPdf(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsCountryExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsCountry(request);
        return jrReportStatisticsRepository.exportCountryExcel(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsResponsiblePdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsResponsible(request);
        return jrReportStatisticsRepository.exportResponsiblePdf(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsResponsibleExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsResponsible(request);
        return jrReportStatisticsRepository.exportResponsibleExcel(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsHotelPdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsHotel(request);
        return jrReportStatisticsRepository.exportHotelPdf(list, buildBaseParameters());
    }

    @Override
    
    @SuppressWarnings("unchecked")
    public byte[] exportStatisticsHotelExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<Map> list = analyticsBookingRepo.searchAllStatisticsHotel(request);
        return jrReportStatisticsRepository.exportHotelExcel(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGuidePdf(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllGuideReport(request);
        return jrReportGuideRepository.exportPdf(list, buildBaseParameters());
    }

    @Override
    
    public byte[] exportGuideExcel(AnalyticsBookingSearchReportFilterRequest request) {
        List<ItineraryItemDomain> list = analyticsBookingRepo.searchAllGuideReport(request);
        return jrReportGuideRepository.exportExcel(list, buildBaseParameters());
    }

}
