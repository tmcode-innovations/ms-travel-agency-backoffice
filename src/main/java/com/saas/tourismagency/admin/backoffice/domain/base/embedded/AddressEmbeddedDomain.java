package com.saas.tourismagency.admin.backoffice.domain.base.embedded;

import com.saas.tourismagency.admin.backoffice.domain.base.*;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEmbeddedDomain implements Serializable {

    private String id;
    private String name;
    private String description;

    private String city;
    private String zip;
    private String street;
    private String street2;
    private String address1;
    private String address2;
    private String address3;
    private String county;
    private String town;

    private CountryDomain country;
    private DepartmentRegionDomain departmentRegion;
    private ProvinceDomain province;
    private DistrictDomain district;
    private StateDomain state;

    @Getter(AccessLevel.NONE)
    private String strAddress;

    @Getter(AccessLevel.NONE)
    private String strTruncateAddress;

    public String getStrAddress(){
        StringBuffer result = new StringBuffer();
        if ( street != null && !street.isEmpty() ){
            result.append(street + ", ");
        }
        if ( street2 != null && !street2.isEmpty() ){
            result.append(street2);
        }
        return result.toString();
    }

    public String getStrTruncateAddress(){
        String result = null;
        String str = getStrAddress();
        if ( str != null && !str.isEmpty() ){
            if ( str.length() >= 30 ){
                result = str.substring(0, 29);
            }else{
                result = str;
            }
        }
        return result;
    }
}
