package com.saas.tourismagency.admin.backoffice.adapter.exportdata;

import java.util.List;
import java.util.Map;

/**
 * @param <Domain>
 * @param <SubDomain>
 */
public interface IExportBaseSubReport<Domain, SubDomain> {

    public byte[] getExportReportWithSubReport(List<Domain> items, List<SubDomain> subReportItems, String format, String source, String subReportSource);

    public byte[] getExportReportWithSubReport(List<Domain> items, List<SubDomain> subReportItems, String format, String source, String subReportSource, Map<String, Object> parametersOld);

}
