package com.saas.tourismagency.admin.backoffice.domain.maintenance;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.maintenance.ServiceEntity;
import com.saas.tourismagency.admin.backoffice.domain.base.EntityBaseDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.PartnerDomain;
import com.saas.tourismagency.admin.backoffice.domain.base.enums.TypeInheritanceEnum;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SupplierDomain extends EntityBaseDomain implements Serializable {

    protected String ruc;
    protected Boolean confirmation;
    protected Boolean reConfirmation;
    protected Integer valueConfirmation;
    protected Integer valueReConfirmation;
    protected String summary;
    protected String politicsCancel1;
    protected String politicsCancel2;
    protected String politicsFree;
    protected String politicsChildren;
    protected String politicsShows;
    protected Boolean administrative;
    protected String checkIn;
    protected String checkOut;

    protected String serviceGroup;

    protected PartnerDomain partner;
    protected SupplierTypeDomain supplierType;
    protected SectorDomain sector;
    protected DocumentTypeDomain documentType;
    protected TouristDestineDomain touristDestine;

    protected Set<ServiceDomain> services;

    @Getter(AccessLevel.NONE)
    protected String strTruncateName;

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

    @Getter(AccessLevel.NONE)
    protected String strAbbrevTypeSupplier;

    public String getStrAbbrevTypeSupplier(){
        String result = null;
        if (typeInheritance != null){
            if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_LODGING.getName()) ){
                result = "HTL";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_RESTAURANT.getName()) ){
                result = "RES";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_GUIDE.getName()) ){
                result = "GUI";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_TRANSPORT.getName()) ){
                result = "TRA";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_OPERATOR.getName()) ){
                result = "OPE";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_AIR_LINE.getName()) ){
                result = "LIN";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_LODGE.getName()) ){
                result = "LOD";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_ADMINISTRATIVE.getName()) ){
                result = "ADM";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_GLAMPING.getName()) ){
                result = "GLA";
            }else if ( typeInheritance.equals(TypeInheritanceEnum.SUPPLIER_INSTITUTION.getName()) ){
                result = "INS";
            }
        }
        return result;
    }
}
