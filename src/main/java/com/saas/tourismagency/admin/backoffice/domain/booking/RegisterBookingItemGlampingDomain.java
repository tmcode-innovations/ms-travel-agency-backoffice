package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.LodgingCategoryDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegisterBookingItemGlampingDomain extends BaseDomain implements Serializable, Comparable<RegisterBookingItemGlampingDomain> {

    private String name;
    private String description;
    private String identify;

    private String noCupon;
    private String noCuponPreImpress;

    private LocalDate dateFrom;
    private LocalDate dateTo;

    private LocalDateTime dateTimeInitService;

    private Integer noNight;
    private Integer noNightTcGuide;
    private Integer noNightTcLocalGuide;
    private Integer cantCb;
    private Integer cantAb;
    private Integer cantBreakfastBuffet;
    private Integer cantDinner;
    private Integer cantLunch;
    private Integer cantTcGuideBreakfastBuffet;
    private Integer cantTcGuideDinner;
    private Integer cantTcGuideLunch;
    private Integer cantTcLocalGuideBreakfastBuffet;
    private Integer cantTcLocalGuideDinner;
    private Integer cantTcLocalGuideLunch;
    private Integer cantGuideBreakfastBuffet;
    private Integer cantGuideDinner;
    private Integer cantGuideLunch;

    private String observation;
    private String category;

    private LodgingCategoryDomain lodgingCategory;

    private Integer tcLocal;
    private Integer tc;
    private Integer guide;
    private Integer cantPaxChildren;
    private Integer cantPaxAdult;

    private RegisterBookingGlampingDomain registerBookingGlamping;

    private RegisterGroupDomain registerGroup;

    @Getter(AccessLevel.NONE)
    private String strRooms;

    public String getStrRooms(){
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<>();
        if ( registerBookingGlamping.getCantSwb() != null && registerBookingGlamping.getCantSwb() > 0 ){
            map_list.put("SWB", registerBookingGlamping.getCantSwb());
        }else{
            map_list.put("SWB", 0);
        }

        if ( registerBookingGlamping.getCantTwin() != null && registerBookingGlamping.getCantTwin() > 0 ){
            map_list.put("TWIN", registerBookingGlamping.getCantTwin());
        }else{
            map_list.put("TWIN", 0);
        }

        if ( registerBookingGlamping.getCantMat() != null && registerBookingGlamping.getCantMat() > 0 ){
            map_list.put("MAT", registerBookingGlamping.getCantMat());
        }else{
            map_list.put("MAT", 0);
        }

        if ( registerBookingGlamping.getCantTrp() != null && registerBookingGlamping.getCantTrp() > 0 ){
            map_list.put("TRP", registerBookingGlamping.getCantTrp());
        }else{
            map_list.put("TRP", 0);
        }

        if ( registerBookingGlamping.getCantSwbTcGuide() != null && registerBookingGlamping.getCantSwbTcGuide() > 0 ){
            map_list.put("SWB TC", registerBookingGlamping.getCantSwbTcGuide());
        }else{
            map_list.put("SWB TC", 0);
        }

        if ( registerBookingGlamping.getCantSwbTcLocalGuide() != null && registerBookingGlamping.getCantSwbTcLocalGuide() > 0 ){
            map_list.put("SWB TC LOCAL", registerBookingGlamping.getCantSwbTcLocalGuide());
        }else{
            map_list.put("SWB TC LOCAL", 0);
        }

        if ( registerBookingGlamping.getCantTwinTcGuide() != null && registerBookingGlamping.getCantTwinTcGuide() > 0 ){
            map_list.put("TWIN TC", registerBookingGlamping.getCantTwinTcGuide());
        }else{
            map_list.put("TWIN TC", 0);
        }

        if ( registerBookingGlamping.getCantTwinTcLocalGuide() != null && registerBookingGlamping.getCantTwinTcLocalGuide() > 0 ){
            map_list.put("TWIN TC LOCAL", registerBookingGlamping.getCantTwinTcLocalGuide());
        }else{
            map_list.put("TWIN TC LOCAL", 0);
        }

        for (String type: map_list.keySet()) {
            if (!map_list.get(type).equals(0)){
                if ( type.equals("SWB") ){
                    result.append(map_list.get(type).toString() + " SWB + ");
                }
                if ( type.equals("TWIN") ){
                    result.append(map_list.get(type).toString() + " TWIN + ");
                }
                if ( type.equals("MAT") ){
                    result.append(map_list.get(type).toString() + " MAT + ");
                }
                if ( type.equals("TRP") ){
                    result.append(map_list.get(type).toString() + " TRP + ");
                }
                if ( type.equals("SWB TC") ){
                    result.append(map_list.get(type).toString() + " SWB TC + ");
                }
                if ( type.equals("SWB TC LOCAL") ){
                    result.append(map_list.get(type).toString() + " SWB TC LOCAL + ");
                }
                if ( type.equals("TWIN TC") ){
                    result.append(map_list.get(type).toString() + " TWIN TC + ");
                }
                if ( type.equals("TWIN TC LOCAL") ){
                    result.append(map_list.get(type).toString() + " TWIN TC LOCAL + ");
                }
            }
        }
        if ( result.length() > 1 ){
            result.delete(result.length()-2, result.length()-1);
        }

		return result.toString();
    }

    @Getter(AccessLevel.NONE)
    private String strFoods;

    public String getStrFoods(){
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<>();
        if ( cantBreakfastBuffet != null && cantBreakfastBuffet > 0 ){
            map_list.put("DESAYUNOS BUFFET", cantBreakfastBuffet);
        }else{
            map_list.put("DESAYUNOS BUFFET", 0);
        }

        if ( cantLunch != null && cantLunch > 0 ){
            map_list.put("ALMUERZO", cantLunch);
        }else{
            map_list.put("ALMUERZO", 0);
        }

        if ( cantDinner != null && cantDinner > 0 ){
            map_list.put("CENA", cantDinner);
        }else{
            map_list.put("CENA", 0);
        }

        for (String type: map_list.keySet()) {
            if (!map_list.get(type).equals(0)){
                if ( type.equals("DESAYUNOS BUFFET") ){
                    result.append(map_list.get(type).toString() + " DESAYUNOS BUFFET + ");
                }
                if ( type.equals("ALMUERZO") ){
                    result.append(map_list.get(type).toString() + " ALMUERZO + ");
                }
                if ( type.equals("CENA") ){
                    result.append(map_list.get(type).toString() + " CENA + ");
                }
            }
        }
        if ( result.length() > 1 ){
            result.delete(result.length()-2, result.length()-1);
        }
		return result.toString();
    }

    @Getter(AccessLevel.NONE)
    private String strPaxCupon;

    public String getStrPaxCupon() {
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<String, Integer>();

        if ( tcLocal != null && tcLocal > 0 ){
            map_list.put("TC LOCAL", tcLocal);
        }else if ( registerBookingGlamping.getTcLocal() != null && registerBookingGlamping.getTcLocal() > 0 ){
            map_list.put("TC LOCAL", registerBookingGlamping.getTcLocal());
        }else{
            map_list.put("TC LOCAL", 0);
        }

        if ( tc != null && tc > 0 ){
            map_list.put("TC", tc);
        }else if ( registerBookingGlamping.getTc() != null && registerBookingGlamping.getTc() > 0 ){
            map_list.put("TC", registerBookingGlamping.getTc());
        }else{
            map_list.put("TC", 0);
        }

        map_list.put("FREE", 0);

        if ( cantPaxChildren != null && cantPaxChildren > 0 ){
            map_list.put("CHL", cantPaxChildren);
        }else if ( registerBookingGlamping.getCantPaxChildren() != null && registerBookingGlamping.getCantPaxChildren() > 0 ){
            map_list.put("CHL", registerBookingGlamping.getCantPaxChildren());
        }else{
            map_list.put("CHL", 0);
        }

        if ( cantPaxAdult != null && cantPaxAdult > 0 ){
            map_list.put("ADL", cantPaxAdult);
        }else if ( registerBookingGlamping.getCantPaxAdult() != null && registerBookingGlamping.getCantPaxAdult() > 0 ){
            map_list.put("ADL", registerBookingGlamping.getCantPaxAdult());
        }else{
            map_list.put("ADL", 0);
        }

        for (String type: map_list.keySet()) {
            if (!map_list.get(type).equals(0)){
                if ( type.equals("ADL") ){
                    result.append(map_list.get(type).toString() + " ADL + ");
                }
                if ( type.equals("CHL") ){
                    result.append(map_list.get(type).toString() + " CHL + ");
                }
                if ( type.equals("FREE") ){
                    result.append(map_list.get(type).toString() + " FREE + ");
                }
                if ( type.equals("TC") ){
                    result.append(map_list.get(type).toString() + " TC + ");
                }
                if ( type.equals("TC LOCAL") ){
                    result.append(map_list.get(type).toString() + " TC LOCAL + ");
                }
            }
        }
        if (result.length() > 1){
            result.delete(result.length()-2, result.length()-1);
        }

        return result.toString();
    }

    @Getter(AccessLevel.NONE)
    private String strTruncateObservation;

    public String getStrTruncateObservation(){
        String result = null;
        if ( observation != null && !observation.isEmpty() ){
            if ( observation.length() >= 40 ){
                result = observation.substring(0, 39);
            }else{
                result = observation;
            }
        }
        return result;
    }

    @Getter(AccessLevel.NONE)
    private String strCuponIsNoInternAndImpress;

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

    @Getter(AccessLevel.NONE)
    private String strDateFrom;

    public String getStrDateFrom(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateFrom());
    }

    @Getter(AccessLevel.NONE)
    private String strDateTo;

    public String getStrDateTo(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateTo());
    }

    @Override
    public int compareTo(RegisterBookingItemGlampingDomain other) {
        return dateFrom.compareTo(this.dateFrom);
    }
}
