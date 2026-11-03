package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.ServiceDomain;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegisterBookingItemInstitutionDomain extends BaseDomain implements Serializable, Comparable<RegisterBookingItemInstitutionDomain> {

    private String name;
    private String description;
    private String identify;

    private LocalDate dateService;
    private Integer cantPax;
    private Integer sequence;

    private String observation;
    private String otherService;

    private Boolean pvt;
    private Boolean sic;
    private LocalDate date;
    private LocalDateTime dateTime;
    private String idItineraryItemIdentify;

    private ServiceDomain service;
    private RegisterBookingInstitutionDomain registerBookingInstitution;
    private RegisterGroupDomain registerGroup;

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
    private String strDateService;

    public String getStrDateService(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateService());
    }

    @Getter(AccessLevel.NONE)
    private String strServiceComplete;

    public String getStrServiceComplete(){
        StringBuffer result = new StringBuffer();
        if ( service != null ){
            result.append(service.getAbbrev());
        }
        if ( pvt != null && pvt ){
            result.append("/PVT");
        }
        if ( sic != null && sic ){
            result.append("/SIC");
        }
        return result.toString();
    }

    @Getter(AccessLevel.NONE)
    private String strTruncateAbbrevName;

    public String getStrTruncateAbbrevName(){
        String result = null;
        if ( service.getAbbrev() != null && !service.getAbbrev().equals("") ){
            if ( service.getAbbrev().length() >= 40 ){
                result = service.getAbbrev().substring(0, 39);
            }else{
                result = service.getAbbrev();
            }
        }else if ( service.getName() != null && !service.getName().equals("") ){
            if ( service.getName().length() >= 40 ){
                result = service.getName().substring(0, 39);
            }else{
                result = service.getName();
            }
        }
        return result;
    }

    @Override
    public int compareTo(RegisterBookingItemInstitutionDomain other) {
        int value = date.compareTo(other.date);
        if (value == 0){
            if (sequence != null){
                int value1 = sequence.compareTo(other.getSequence());
                return value1;
            }
        }
        return value;
    }
}
