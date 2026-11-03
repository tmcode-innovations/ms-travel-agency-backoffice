package com.saas.tourismagency.admin.backoffice.domain.base;

import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class DepartmentRegionDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;
    private String capital;
    private CountryDomain country;

    @Getter(AccessLevel.NONE)
    private String strTruncateName;

    @Getter(AccessLevel.NONE)
    private String strAbrevName;

    public String getStrTruncateName(){
        String result = null;
        if ( name != null && !name.isEmpty() ){
            if ( name.length() >= 30 ){
                result = name.substring(0, 29);
            }else{
                result = name;
            }
        }
        return result;
    }

    public String getStrAbrevName(){
        String result = null;
        if ( name != null && !name.isEmpty() ){
            if ( name.length() >= 3 ){
                result = name.substring(0, 3);
            }else{
                result = name;
            }
        }
        return result;
    }
}
