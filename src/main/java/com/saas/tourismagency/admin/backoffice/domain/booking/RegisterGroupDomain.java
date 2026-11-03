package com.saas.tourismagency.admin.backoffice.domain.booking;

import com.saas.tourismagency.admin.backoffice.core.utils.LocalDateTimeUtil;
import com.saas.tourismagency.admin.backoffice.domain.base.CountryDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.enums.StateRegisterGroupEnum;
import dev.mm.javaee.core.domain.BaseDomain;
import dev.mm.javaee.core.utils.EnumMappeableService;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.saas.tourismagency.admin.backoffice.domain.base.IdiomDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.ProgramDomain;
import com.saas.tourismagency.admin.backoffice.domain.crm.QuotationDomain;
import com.saas.tourismagency.admin.backoffice.domain.hr.EmployeeDomain;
import com.saas.tourismagency.admin.backoffice.domain.maintenance.CustomerDomain;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegisterGroupDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Integer days;
    private Integer cantPax;
    private Integer cantFree;
    private Integer cantPaxAdult;
    private Integer cantPaxChildren;
    private Integer tC;
    private Integer tCLocal;
    private Integer guide;
    private Integer totalPax;

    private LocalDate dateAperture;
    private LocalDate initTour;
    private LocalDate endTour;

    private String arriveAir;
    private String departureAir;

    private Integer cantSWB;
    private Integer cantTRP;
    private Integer cantCDP;
    private Integer cantQTP;
    private Integer cantSXTP;
    private Integer cantTWIN;
    private Integer cantMAT;

    private String observation;

    private String link;
    private String linkBitrix;
    private String linkWetravel;

    private ProgramDomain program;
    private QuotationDomain quotation;
    private CustomerDomain customer;
    private IdiomDomain idiom;
    private CountryDomain country;

    private String stateRegisterGroup;

    private EmployeeDomain responsible;

    private Integer versionQuotation;
    private Boolean apertureWrite;

    private String emailPassengerPrincipal;
    private String mobilePassengerPrincipal;
    private String whatsappPassengerPrincipal;

    @Getter(AccessLevel.NONE)
    private String dateApertureStr;

    @Getter(AccessLevel.NONE)
    private String initTourStr;

    @Getter(AccessLevel.NONE)
    private String endTourStr;

    @Getter(AccessLevel.NONE)
    private String strPax;

    @Getter(AccessLevel.NONE)
    private String stateRegisterGroupDescription;

    public String getDateApertureStr(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getDateAperture());
    }

    public String getInitTourStr(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getInitTour());
    }

    public String getEndTourStr(){
        return LocalDateTimeUtil.toLocalDateFormatShort(this.getEndTour());
    }

    public String getStrPax() {
        StringBuffer stringBuffer = new StringBuffer();
        Integer cantPaxTemp = (cantPaxAdult != null ? cantPaxAdult : 0) + (cantPaxChildren != null ? cantPaxChildren : 0);

        if ( cantPaxTemp != null && cantPaxTemp > 0 ){
            stringBuffer.append( cantPaxTemp + " PAX ");
            stringBuffer.append("+");
        }
		if ( tC != null && tC > 0 ){
            stringBuffer.append( tC + " TC ");
            stringBuffer.append("+");
        }
        if ( tCLocal != null && tCLocal > 0 ){
            stringBuffer.append( tCLocal + " TC LOCAL ");
            stringBuffer.append("+");
        }
        if ( stringBuffer.length() > 0 ){
            stringBuffer.delete(stringBuffer.length()-1, stringBuffer.length());
        }
        return stringBuffer.toString();
    }

    public String getStateRegisterGroupDescription(){
        if (stateRegisterGroup != null && !stateRegisterGroup.isEmpty()){
            StateRegisterGroupEnum.StateRegisterGroupModel stateRegisterGroupModelData = EnumMappeableService
                    .map(StateRegisterGroupEnum.class)
                    .stream().filter(
                            stateRegisterGroupModel -> stateRegisterGroupModel.name().equals(stateRegisterGroup))
                    .findFirst().get();
            if (stateRegisterGroupModelData != null){
                return stateRegisterGroupModelData.description();
            }
        }
        return null;
    }
}
