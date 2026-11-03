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
public class RegisterBookingGlampingDomain extends RegisterBookingBaseDomain implements Serializable {

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

}
