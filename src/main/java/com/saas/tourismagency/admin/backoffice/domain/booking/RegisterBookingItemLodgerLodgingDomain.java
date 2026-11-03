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
public class RegisterBookingItemLodgerLodgingDomain extends BaseDomain implements Serializable, Comparable<RegisterBookingItemLodgerLodgingDomain> {

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
    private Integer noNightGuide;
    private Integer cantCb;
    private Integer cantAb;
    private Integer cantBreakfastBuffet;
    private Integer cantDinner;
    private Integer cantLunch;
    private Integer cantBoxLunch;
    private Integer cantTcGuideBreakfastBuffet;
    private Integer cantTcGuideDinner;
    private Integer cantTcGuideLunch;
    private Integer cantTcGuideBoxLunch;
    private Integer cantTcLocalGuideBreakfastBuffet;
    private Integer cantTcLocalGuideDinner;
    private Integer cantTcLocalGuideLunch;
    private Integer cantTcLocalGuideBoxLunch;
    private Integer cantGuideBreakfastBuffet;
    private Integer cantGuideDinner;
    private Integer cantGuideLunch;
    private Integer cantGuideBoxLunch;

    private String observation;
    private String category;

    private LodgingCategoryDomain lodgingCategory;
    private Integer tcLocal;
    private Integer tc;
    private Integer guide;
    private Integer cantPaxChildren;
    private Integer cantPaxAdult;

    private RegisterBookingLodgerDomain registerBookingLodger;
    private RegisterGroupDomain registerGroup;

    @Getter(AccessLevel.NONE)
    private String strRooms;

    @Getter(AccessLevel.NONE)
    private String strRoomsTcLocal;

    public String getStrRooms(){
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<>();
        if ( registerBookingLodger.getCantSwb() != null && registerBookingLodger.getCantSwb() > 0 ){
            map_list.put("SWB", registerBookingLodger.getCantSwb());
        }else{
            map_list.put("SWB", 0);
        }

        if ( registerBookingLodger.getCantTwin() != null && registerBookingLodger.getCantTwin() > 0 ){
            map_list.put("TWIN", registerBookingLodger.getCantTwin());
        }else{
            map_list.put("TWIN", 0);
        }

        if ( registerBookingLodger.getCantMat() != null && registerBookingLodger.getCantMat() > 0 ){
            map_list.put("MAT", registerBookingLodger.getCantMat());
        }else{
            map_list.put("MAT", 0);
        }

        if ( registerBookingLodger.getCantTrp() != null && registerBookingLodger.getCantTrp() > 0 ){
            map_list.put("TRP", registerBookingLodger.getCantTrp());
        }else{
            map_list.put("TRP", 0);
        }

        if ( registerBookingLodger.getCantSwbTcGuide() != null && registerBookingLodger.getCantSwbTcGuide() > 0 ){
            map_list.put("SWB", map_list.get("SWB") + registerBookingLodger.getCantSwbTcGuide());
            // map_list.put("SWB TC", registerBookingLodging.getCantSwbTcGuide());
        }else{
            // map_list.put("SWB TC", 0);
        }

        /*if ( registerBookingLodging.getCantSwbTcLocalGuide() != null && registerBookingLodging.getCantSwbTcLocalGuide() > 0 ){
            map_list.put("SWB TC LOCAL", registerBookingLodging.getCantSwbTcLocalGuide());
        }else{
            map_list.put("SWB TC LOCAL", 0);
        }*/

        if ( registerBookingLodger.getCantSwbGuide() != null && registerBookingLodger.getCantSwbGuide() > 0 ){
            map_list.put("SWB", map_list.get("SWB") + registerBookingLodger.getCantSwbGuide());
            // map_list.put("SWB GUIA", registerBookingLodging.getCantSwbGuide());
        }else{
            // map_list.put("SWB GUIA", 0);
        }

        if ( registerBookingLodger.getCantTwinTcGuide() != null && registerBookingLodger.getCantTwinTcGuide() > 0 ){
            map_list.put("TWIN", map_list.get("TWIN") + registerBookingLodger.getCantTwinTcGuide());
            // map_list.put("TWIN TC", registerBookingLodging.getCantTwinTcGuide());
        }else{
            // map_list.put("TWIN TC", 0);
        }

        /*if ( registerBookingLodging.getCantTwinTcLocalGuide() != null && registerBookingLodging.getCantTwinTcLocalGuide() > 0 ){
            map_list.put("TWIN TC LOCAL", registerBookingLodging.getCantTwinTcLocalGuide());
        }else{
            map_list.put("TWIN TC LOCAL", 0);
        }*/

        if ( registerBookingLodger.getCantTwinGuide() != null && registerBookingLodger.getCantTwinGuide() > 0 ){
            map_list.put("TWIN", map_list.get("TWIN") + registerBookingLodger.getCantTwinGuide());
            // map_list.put("TWIN GUIA", registerBookingLodging.getCantTwinGuide());
        }else{
            // map_list.put("TWIN GUIA", 0);
        }

        if (!map_list.get("SWB").equals(0)){
            result.append(map_list.get("SWB").toString() + " SWB + ");
        }
        if (!map_list.get("TWIN").equals(0)){
            result.append(map_list.get("TWIN").toString() + " TWIN + ");
        }
        if (!map_list.get("MAT").equals(0)){
            result.append(map_list.get("MAT").toString() + " MAT + ");
        }
        if (!map_list.get("TRP").equals(0)){
            result.append(map_list.get("TRP").toString() + " TRP + ");
        }
        /*if (!map_list.get("SWB TC").equals(0)){
            result.append(map_list.get("SWB TC").toString() + " SWB TC + ");
        }*/
        /*if (!map_list.get("SWB TC LOCAL").equals(0)){
            result.append(map_list.get("SWB TC LOCAL").toString() + " SWB TC LOCAL + ");
        }*/
        /*if (!map_list.get("SWB GUIA").equals(0)){
            result.append(map_list.get("SWB GUIA").toString() + " SWB GUIA + ");
        }*/
        /*if (!map_list.get("TWIN TC").equals(0)){
            result.append(map_list.get("TWIN TC").toString() + " TWIN TC + ");
        }*/
        /*if (!map_list.get("TWIN TC LOCAL").equals(0)){
            result.append(map_list.get("TWIN TC LOCAL").toString() + " TWIN TC LOCAL + ");
        }*/
        /*if (!map_list.get("TWIN GUIA").equals(0)){
            result.append(map_list.get("TWIN GUIA").toString() + " TWIN GUIA + ");
        }*/

        if ( result.length() > 1 ){
            result.delete(result.length()-2, result.length()-1);
        }

        return result.toString();
    }

    public String getStrRoomsTcLocal(){
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<>();
        /*if ( registerBookingLodging.getCantSwb() != null && registerBookingLodging.getCantSwb() > 0 ){
            map_list.put("SWB", registerBookingLodging.getCantSwb());
        }else{
            map_list.put("SWB", 0);
        }

        if ( registerBookingLodging.getCantTwin() != null && registerBookingLodging.getCantTwin() > 0 ){
            map_list.put("TWIN", registerBookingLodging.getCantTwin());
        }else{
            map_list.put("TWIN", 0);
        }

        if ( registerBookingLodging.getCantMat() != null && registerBookingLodging.getCantMat() > 0 ){
            map_list.put("MAT", registerBookingLodging.getCantMat());
        }else{
            map_list.put("MAT", 0);
        }

        if ( registerBookingLodging.getCantTrp() != null && registerBookingLodging.getCantTrp() > 0 ){
            map_list.put("TRP", registerBookingLodging.getCantTrp());
        }else{
            map_list.put("TRP", 0);
        }

        if ( registerBookingLodging.getCantSwbTcGuide() != null && registerBookingLodging.getCantSwbTcGuide() > 0 ){
            map_list.put("SWB TC", registerBookingLodging.getCantSwbTcGuide());
        }else{
            map_list.put("SWB TC", 0);
        }*/

        if ( registerBookingLodger.getCantSwbTcLocalGuide() != null && registerBookingLodger.getCantSwbTcLocalGuide() > 0 ){
            map_list.put("SWB", registerBookingLodger.getCantSwbTcLocalGuide());
        }else{
            map_list.put("SWB", 0);
        }

        /*if ( registerBookingLodging.getCantSwbGuide() != null && registerBookingLodging.getCantSwbGuide() > 0 ){
            map_list.put("SWB GUIA", registerBookingLodging.getCantSwbGuide());
        }else{
            map_list.put("SWB GUIA", 0);
        }

        if ( registerBookingLodging.getCantTwinTcGuide() != null && registerBookingLodging.getCantTwinTcGuide() > 0 ){
            map_list.put("TWIN TC", registerBookingLodging.getCantTwinTcGuide());
        }else{
            map_list.put("TWIN TC", 0);
        }*/

        if ( registerBookingLodger.getCantTwinTcLocalGuide() != null && registerBookingLodger.getCantTwinTcLocalGuide() > 0 ){
            map_list.put("TWIN", registerBookingLodger.getCantTwinTcLocalGuide());
        }else{
            map_list.put("TWIN", 0);
        }

        /*if ( registerBookingLodging.getCantTwinGuide() != null && registerBookingLodging.getCantTwinGuide() > 0 ){
            map_list.put("TWIN GUIA", registerBookingLodging.getCantTwinGuide());
        }else{
            map_list.put("TWIN GUIA", 0);
        }*/

        /*if (!map_list.get("SWB").equals(0)){
            result.append(map_list.get("SWB").toString() + " SWB + ");
        }
        if (!map_list.get("TWIN").equals(0)){
            result.append(map_list.get("TWIN").toString() + " TWIN + ");
        }
        if (!map_list.get("MAT").equals(0)){
            result.append(map_list.get("MAT").toString() + " MAT + ");
        }
        if (!map_list.get("TRP").equals(0)){
            result.append(map_list.get("TRP").toString() + " TRP + ");
        }
        if (!map_list.get("SWB TC").equals(0)){
            result.append(map_list.get("SWB TC").toString() + " SWB TC + ");
        }*/
        if (!map_list.get("SWB").equals(0)){
            result.append(map_list.get("SWB").toString() + " SWB + ");
        }
        /*if (!map_list.get("SWB GUIA").equals(0)){
            result.append(map_list.get("SWB GUIA").toString() + " SWB GUIA + ");
        }
        if (!map_list.get("TWIN TC").equals(0)){
            result.append(map_list.get("TWIN TC").toString() + " TWIN TC + ");
        }*/
        if (!map_list.get("TWIN").equals(0)){
            result.append(map_list.get("TWIN").toString() + " TWIN + ");
        }
        /*if (!map_list.get("TWIN GUIA").equals(0)){
            result.append(map_list.get("TWIN GUIA").toString() + " TWIN GUIA + ");
        }*/

        if ( result.length() > 1 ){
            result.delete(result.length()-2, result.length()-1);
        }

        return result.toString();
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strFoods;

    public String getStrFoods(){
        /*StringBuffer result = new StringBuffer();
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

        if (!map_list.get("DESAYUNOS BUFFET").equals(0)){
            result.append(map_list.get("DESAYUNOS BUFFET").toString() + " DESAYUNOS BUFFET + ");
        }
        if (!map_list.get("ALMUERZO").equals(0)){
            result.append(map_list.get("ALMUERZO").toString() + " ALMUERZO + ");
        }
        if (!map_list.get("CENA").equals(0)){
            result.append(map_list.get("CENA").toString() + " CENA + ");
        }

        if ( result.length() > 1 ){
            result.delete(result.length()-2, result.length()-1);
        }*/
        return this.strFoods;
    }

    public void setStrFoods(String strFoods){
        this.strFoods = strFoods;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strBreakfastBuffetFoods;

    public String getStrBreakfastBuffetFoods(){
        return this.strBreakfastBuffetFoods;
    }

    public void setStrBreakfastBuffetFoods(String strBreakfastBuffetFoods){
        this.strBreakfastBuffetFoods = strBreakfastBuffetFoods;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strDinnerFoods;

    public String getStrDinnerFoods(){
        return this.strDinnerFoods;
    }

    public void setStrDinnerFoods(String strDinnerFoods){
        this.strDinnerFoods = strDinnerFoods;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strLunchFoods;

    public String getStrLunchFoods(){
        return this.strLunchFoods;
    }

    public void setStrLunchFoods(String strLunchFoods){
        this.strLunchFoods = strLunchFoods;
    }

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String strBoxLunchFoods;

    public String getStrBoxLunchFoods(){
        return this.strBoxLunchFoods;
    }

    public void setStrBoxLunchFoods(String strBoxLunchFoods){
        this.strBoxLunchFoods = strBoxLunchFoods;
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
    private String strPaxCupon;

    public String getStrPaxCupon() {
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<String, Integer>();

        if ( cantPaxAdult != null && cantPaxAdult > 0 ){
            map_list.put("ADL", cantPaxAdult);
        }else if ( registerBookingLodger.getCantPaxAdult() != null && registerBookingLodger.getCantPaxAdult() > 0 ){
            map_list.put("ADL", registerBookingLodger.getCantPaxAdult());
        }else{
            map_list.put("ADL", 0);
        }

        if ( cantPaxChildren != null && cantPaxChildren > 0 ){
            map_list.put("CHL", cantPaxChildren);
        }else if ( registerBookingLodger.getCantPaxChildren() != null && registerBookingLodger.getCantPaxChildren() > 0 ){
            map_list.put("CHL", registerBookingLodger.getCantPaxChildren());
        }else{
            map_list.put("CHL", 0);
        }

        if ( tc != null && tc > 0 ){
            map_list.put("TC", tc);
        }else if ( registerBookingLodger.getTc() != null && registerBookingLodger.getTc() > 0 ){
            map_list.put("TC", registerBookingLodger.getTc());
        }else{
            map_list.put("TC", 0);
        }

        if ( tcLocal != null && tcLocal > 0 ){
            map_list.put("TC LOCAL", tcLocal);
        }else if ( registerBookingLodger.getTcLocal() != null && registerBookingLodger.getTcLocal() > 0 ){
            map_list.put("TC LOCAL", registerBookingLodger.getTcLocal());
        }else{
            map_list.put("TC LOCAL", 0);
        }

        if ( guide != null && guide > 0 ){
            map_list.put("GUIA", tcLocal);
        }else if ( registerBookingLodger.getGuide() != null && registerBookingLodger.getGuide() > 0 ){
            map_list.put("GUIA", registerBookingLodger.getGuide());
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

    @Getter(AccessLevel.NONE)
    private String strPaxCuponWithoutTcLocal;

    public String getStrPaxCuponWithoutTcLocal() {
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<String, Integer>();

        if ( cantPaxAdult != null && cantPaxAdult > 0 ){
            map_list.put("ADL", cantPaxAdult);
        }else if ( registerBookingLodger.getCantPaxAdult() != null && registerBookingLodger.getCantPaxAdult() > 0 ){
            map_list.put("ADL", registerBookingLodger.getCantPaxAdult());
        }else{
            map_list.put("ADL", 0);
        }

        if ( cantPaxChildren != null && cantPaxChildren > 0 ){
            map_list.put("CHL", cantPaxChildren);
        }else if ( registerBookingLodger.getCantPaxChildren() != null && registerBookingLodger.getCantPaxChildren() > 0 ){
            map_list.put("CHL", registerBookingLodger.getCantPaxChildren());
        }else{
            map_list.put("CHL", 0);
        }

        if ( tc != null && tc > 0 ){
            map_list.put("TC", tc);
        }else if ( registerBookingLodger.getTc() != null && registerBookingLodger.getTc() > 0 ){
            map_list.put("TC", registerBookingLodger.getTc());
        }else{
            map_list.put("TC", 0);
        }

        if ( guide != null && guide > 0 ){
            map_list.put("GUIA", guide);
        }else if ( registerBookingLodger.getGuide() != null && registerBookingLodger.getGuide() > 0 ){
            map_list.put("GUIA", registerBookingLodger.getGuide());
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
        if (!map_list.get("GUIA").equals(0)){
            result.append(map_list.get("GUIA").toString() + " GUIA + ");
        }

        if (result.length() > 1){
            result.delete(result.length()-2, result.length()-1);
        }

        return result.toString();
    }

    @Getter(AccessLevel.NONE)
    private String strPaxCuponOnlyTcLocal;

    public String getStrPaxCuponOnlyTcLocal() {
        StringBuffer result = new StringBuffer();
        Map<String, Integer> map_list= new HashMap<String, Integer>();

        if ( tcLocal != null && tcLocal > 0 ){
            map_list.put("TC LOCAL", tcLocal);
        }else if ( registerBookingLodger.getTcLocal() != null && registerBookingLodger.getTcLocal() > 0 ){
            map_list.put("TC LOCAL", registerBookingLodger.getTcLocal());
        }else{
            map_list.put("TC LOCAL", 0);
        }

        // map_list.put("FREE", 0);

        if (!map_list.get("TC LOCAL").equals(0)){
            result.append(map_list.get("TC LOCAL").toString() + " TC LOCAL + ");
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
    public int compareTo(RegisterBookingItemLodgerLodgingDomain other) {
        return dateFrom.compareTo(this.dateFrom);
    }

    @Getter(AccessLevel.NONE)
    private Integer cantBreakfastBuffetTotal;

    public Integer getCantBreakfastBuffetTotal(){
        return (cantBreakfastBuffet != null ?  cantBreakfastBuffet : 0)
                + (cantTcGuideBreakfastBuffet != null ?  cantTcGuideBreakfastBuffet : 0)
                + (cantTcLocalGuideBreakfastBuffet != null ?  cantTcLocalGuideBreakfastBuffet : 0)
                + (cantGuideBreakfastBuffet != null ?  cantGuideBreakfastBuffet : 0);
    }

    @Getter(AccessLevel.NONE)
    private Integer cantDinnerTotal;

    public Integer getCantDinnerTotal(){
        return (cantDinner != null ?  cantDinner : 0)
                + (cantTcGuideDinner != null ?  cantTcGuideDinner : 0)
                + (cantTcLocalGuideDinner != null ?  cantTcLocalGuideDinner : 0)
                + (cantGuideDinner != null ?  cantGuideDinner : 0);
    }

    @Getter(AccessLevel.NONE)
    private Integer cantLunchTotal;

    public Integer getCantLunchTotal(){
        return (cantLunch != null ?  cantLunch : 0)
                + (cantTcGuideLunch != null ?  cantTcGuideLunch : 0)
                + (cantTcLocalGuideLunch != null ?  cantTcLocalGuideLunch : 0)
                + (cantGuideLunch != null ?  cantGuideLunch : 0);
    }

    @Getter(AccessLevel.NONE)
    private Integer cantBoxLunchTotal;

    public Integer getCantBoxLunchTotal(){
        return (cantBoxLunch != null ?  cantBoxLunch : 0)
                + (cantTcGuideBoxLunch != null ?  cantTcGuideBoxLunch : 0)
                + (cantTcLocalGuideBoxLunch != null ?  cantTcLocalGuideBoxLunch : 0)
                + (cantGuideBoxLunch != null ?  cantGuideBoxLunch : 0);
    }

}
