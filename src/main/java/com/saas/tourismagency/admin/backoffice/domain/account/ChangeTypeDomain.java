package com.saas.tourismagency.admin.backoffice.domain.account;

import com.saas.tourismagency.admin.backoffice.core.utils.NumberUtil;
import dev.mm.javaee.core.domain.BaseDomain;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ChangeTypeDomain extends BaseDomain implements Serializable {

    private String name;
    private String description;
    private String identify;

    private Double priceSale;
    private LocalDate date;

    private DateProcessDomain dateProcess;

    @Getter(AccessLevel.NONE)
    private String priceSaleFormat;

    public String getPriceSaleFormat(){
        return NumberUtil.getImportFormatThreeDecimal(priceSale);
    }
}
