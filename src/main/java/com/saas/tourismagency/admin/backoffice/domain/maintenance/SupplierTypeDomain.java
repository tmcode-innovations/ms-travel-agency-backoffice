package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SupplierTypeDomain extends BaseDomain implements Serializable {

    public static final String LODGING = "1";
    public static final String LODGING_NAME = "ALOJAMIENTOS";
    public static final String RESTAURANT = "2";
    public static final String RESTAURANT_NAME = "RESTAURANTES";
    public static final String GUIDE = "3";
    public static final String GUIDE_NAME = "GUIAS / TRASLADISTAS";
    public static final String TRANSPORT = "4";
    public static final String TRANSPORT_NAME = "TRANSPORTES";
    public static final String OPERATOR = "5";
    public static final String OPERATOR_NAME = "OPERADORES";
    public static final String AIR_LINE = "6";
    public static final String AIR_LINE_NAME = "LINEAS AEREAS";
    public static final String LODGE = "7";
    public static final String LODGE_NAME = "LODGE";
    public static final String ADMINISTRATIVE = "8";
    public static final String ADMINISTRATIVE_NAME = "ADMINISTRATIVO";
    public static final String GLAMPING = "9";
    public static final String GLAMPING_NAME = "GLAMPING";
    public static final String INSTITUTION = "10";
    public static final String INSTITUTION_NAME = "INSTITUCIONES";

    private String name;
    private String identify;
    private String description;

    /*@Getter(AccessLevel.NONE)
    private String strAbbrevTypeSupplier;

    public String getStrAbbrevTypeSupplier(){
        String result = null;
        if ( identify.equals("1") ){
            result = "HTL";
        }else if ( identify.equals("2") ){
            result = "RES";
        }else if ( identify.equals("3") ){
            result = "GUI";
        }else if ( identify.equals("4") ){
            result = "TRA";
        }else if ( identify.equals("5") ){
            result = "OPE";
        }else if ( identify.equals("6") ){
            result = "LIN";
        }else if ( identify.equals("7") ){
            result = "LOD";
        }else if ( identify.equals("8") ){
            result = "ADM";
        }else if ( identify.equals("9") ){
            result = "GLA";
        }else if ( identify.equals("10") ){
            result = "INS";
        }
        return result;
    }*/
}
