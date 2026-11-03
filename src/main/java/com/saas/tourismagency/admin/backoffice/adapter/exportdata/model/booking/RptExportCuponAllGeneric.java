package com.saas.tourismagency.admin.backoffice.adapter.exportdata.model.booking;

import lombok.*;
import net.sf.jasperreports.engine.JasperReport;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RptExportCuponAllGeneric {

    @EqualsAndHashCode.Include
    private Integer id;

    private Integer identify;
    private RptCuponGeneric rptCuponGeneric;
    private List<RptCuponGenericDetail> list;
    private JasperReport inputStream;

}
