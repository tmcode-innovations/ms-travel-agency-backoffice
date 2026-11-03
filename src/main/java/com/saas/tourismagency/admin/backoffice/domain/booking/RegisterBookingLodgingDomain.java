package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegisterBookingLodgingDomain extends RegisterBookingBaseDomain implements Serializable {

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

}
