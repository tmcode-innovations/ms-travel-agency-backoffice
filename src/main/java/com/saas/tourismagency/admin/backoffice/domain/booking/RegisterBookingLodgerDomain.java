package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierLodgingDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.SupplierOperatorDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegisterBookingLodgerDomain extends RegisterBookingBaseDomain implements Serializable {

    // Data Operator

    private Integer numberService;
    private LocalDate dateInitActivity;
    private LocalDate dateEndActivity;

    private LocalDateTime dateTimeInitService;

    private String noCupon;
    private String noCuponPreImpress;
    private Integer cuponTcLocal;
    private Integer cuponTc;
    private Integer cuponCantPaxChildren;
    private Integer cuponCantPaxAdult;

    private SupplierOperatorDomain operatorArrive;
    private SupplierOperatorDomain operatorDeparture;
    private SupplierLodgingDomain supplierLodging;

    // Data Lodging

    private LocalDate initTour;
    private LocalDate endTour;

    private LocalDateTime initHourTour;
    private LocalDateTime endHourTour;

    private Integer cantSwb;
    private Integer cantSwbTcGuide;
    private Integer cantSwbTcLocalGuide;
    private Integer cantSwbGuide;
    private Integer cantTrp;
    private Integer cantCdp;
    private Integer cantQtp;
    private Integer cantSxtp;
    private Integer cantTwin;
    private Integer cantTwinTcGuide;
    private Integer cantTwinTcLocalGuide;
    private Integer cantTwinGuide;
    private Integer cantMat;
    private Integer numberIngress;

    @Getter(AccessLevel.NONE)
    private String strInitTour;

    public String getStrInitTour(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getInitTour());
    }

    @Getter(AccessLevel.NONE)
    private String strEndTour;

    public String getStrEndTour(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getEndTour());
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strPaxFoods;

    public String getStrPaxFoods(){
        return this.strPaxFoods;
    }

    public void setStrPaxFoods(String strPaxFoods){
        this.strPaxFoods = strPaxFoods;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strTcFoods;

    public String getStrTcFoods(){
        return this.strTcFoods;
    }

    public void setStrTcFoods(String strTcFoods){
        this.strTcFoods = strTcFoods;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strTcLocalFoods;

    public String getStrTcLocalFoods(){
        return this.strTcLocalFoods;
    }

    public void setStrTcLocalFoods(String strTcLocalFoods){
        this.strTcLocalFoods = strTcLocalFoods;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strGuideFoods;

    public String getStrGuideFoods(){
        return this.strGuideFoods;
    }

    public void setStrGuideFoods(String strGuideFoods){
        this.strGuideFoods = strGuideFoods;
    }

    @Getter(AccessLevel.NONE)
    private String strCuponIsNoInternAndImpress;

    @Getter(AccessLevel.NONE)
    private String strPaxCuponNewVersion;

    public String getStrCuponIsNoInternAndImpress(){
        String result = null;
        if ( noCuponPreImpress == null ){
            return noCupon;
        }else if ( noCuponPreImpress != null && noCuponPreImpress.isEmpty() ){
            return noCupon;
        }else if ( noCuponPreImpress != null && !noCuponPreImpress.isEmpty() ){
            return noCuponPreImpress;
        }
        return result;
    }

    public String getStrPaxCuponNewVersion(){
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<>();

        if ( cuponCantPaxAdult != null && cuponCantPaxAdult > 0 ){
            map_list.put("ADL", cuponCantPaxAdult);
        }else if ( cantPaxAdult != null && cantPaxAdult > 0 ){
            map_list.put("ADL", cantPaxAdult);
        }else{
            map_list.put("ADL", 0);
        }

        if ( cuponCantPaxChildren != null && cuponCantPaxChildren > 0 ){
            map_list.put("CHL", cuponCantPaxChildren);
        }else if ( cantPaxChildren != null && cantPaxChildren > 0 ){
            map_list.put("CHL", cantPaxChildren);
        }else{
            map_list.put("CHL", 0);
        }

        if ( cuponTc != null && cuponTc > 0 ){
            map_list.put("TC", cuponTc);
        }else if ( tc != null && tc > 0 ){
            map_list.put("TC", tc);
        }else{
            map_list.put("TC", 0);
        }

        if ( cuponTcLocal != null && cuponTcLocal > 0 ){
            map_list.put("TC LOCAL", cuponTcLocal);
        }else if ( tcLocal != null && tcLocal > 0 ){
            map_list.put("TC LOCAL", tcLocal);
        }else{
            map_list.put("TC LOCAL", 0);
        }

        if ( guide != null && guide > 0 ){
            map_list.put("GUIA", guide);
        }else{
            map_list.put("GUIA", 0);
        }

        // map_list.put("FREE", 0);

        if (!map_list.get("ADL").equals(0)){
            result.append(map_list.get("ADL").toString() + " ADL + ");
        }
        if (!map_list.get("CHL").equals(0)){
            result.append(map_list.get("CHL").toString() + " CHL + ");
        }
        if (!map_list.get("TC").equals(0)){
            result.append(map_list.get("TC").toString() + " TC + ");
        }
        if (!map_list.get("TC LOCAL").equals(0)){
            result.append(map_list.get("TC LOCAL").toString() + " TC LOCAL + ");
        }
        if (!map_list.get("GUIA").equals(0)){
            result.append(map_list.get("GUIA").toString() + " GUIA + ");
        }

        if (result.length() > 1){
            result.delete(result.length()-2, result.length()-1);
        }

        return result.toString();
    }

}
