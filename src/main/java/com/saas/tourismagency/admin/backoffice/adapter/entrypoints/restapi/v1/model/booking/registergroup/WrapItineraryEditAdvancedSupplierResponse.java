package com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.registergroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.ItineraryItemResponse;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.booking.itineraryitem.enums.ModeUpdateServiceAdvancedEnum;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.maintenance.supplier.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapItineraryEditAdvancedSupplierResponse {

    @JsonProperty("id")
    String id;

    @JsonProperty("supplier")
    private SupplierResponse supplier;

    @JsonProperty("supplierType")
    String supplierType;

    @JsonProperty("supplierTypeIdentify")
    String supplierTypeIdentify;

    @JsonProperty("services")
    private List<ItineraryItemResponse> services;

    @JsonProperty("hasServices")
    private Boolean hasServices;

    @JsonProperty("hasServicesDescription")
    private String hasServicesDescription;

    @JsonProperty("listServicesDescription")
    private String listServicesDescription;

    @JsonProperty("modeUpdateServiceAdvancedFlag")
    private String modeUpdateServiceAdvancedFlag;

    public static WrapItineraryEditAdvancedSupplierResponse from(
            String id,
            SupplierResponse supplier,
            String supplierType,
            String supplierTypeIdentify,
            List<ItineraryItemResponse> services
    ) {
        WrapItineraryEditAdvancedSupplierResponse response = new WrapItineraryEditAdvancedSupplierResponse();
        response.setId(id);
        response.setSupplier(supplier);
        response.setSupplierType(supplierType);
        response.setSupplierTypeIdentify(supplierTypeIdentify);
        response.setServices(services);
        if (services != null && !services.isEmpty()){
            response.setHasServices(true);
            response.setHasServicesDescription("SI");
        }else{
            response.setHasServices(false);
            response.setHasServicesDescription("NO");
        }
        StringBuffer listServiceDescriptionBuffer = new StringBuffer();
        listServiceDescriptionBuffer.append("<table>");
        for (ItineraryItemResponse itineraryItemResponse : services){
            listServiceDescriptionBuffer.append("<tr>");
            listServiceDescriptionBuffer.append("<td>");
            listServiceDescriptionBuffer.append(itineraryItemResponse.getItineraryDate().getItineraryDateStr());
            listServiceDescriptionBuffer.append("</td>");
            listServiceDescriptionBuffer.append("<td>");
            listServiceDescriptionBuffer.append(itineraryItemResponse.getItineraryDateTimeStr());
            listServiceDescriptionBuffer.append("</td>");
            listServiceDescriptionBuffer.append("<td>");
            listServiceDescriptionBuffer.append(itineraryItemResponse.getService().getName());
            listServiceDescriptionBuffer.append("</td>");
            listServiceDescriptionBuffer.append("</tr>");
        }
        listServiceDescriptionBuffer.append("</table>");
        /*if ( listServiceDescriptionBuffer.length() > 1 ){
            listServiceDescriptionBuffer.delete(listServiceDescriptionBuffer.length()-2, listServiceDescriptionBuffer.length()-1);
        }*/
        response.setListServicesDescription(listServiceDescriptionBuffer.toString());
        response.setModeUpdateServiceAdvancedFlag(ModeUpdateServiceAdvancedEnum.SUPPLIER_WITHOUT_CHANGE.getName());
        return response;
    }

}
