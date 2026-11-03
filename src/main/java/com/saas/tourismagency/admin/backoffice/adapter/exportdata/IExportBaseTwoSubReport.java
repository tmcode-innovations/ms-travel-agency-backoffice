package com.saas.tourismagency.admin.backoffice.adapter.exportdata;

import java.util.List;
import java.util.Map;

/**
 * @param <Domain>
 * @param <OneSubDomain>
 * @param <TwoSubDomain>
 */
public interface IExportBaseTwoSubReport<Domain, OneSubDomain, TwoSubDomain> {

    public byte[] getExportReportWithTwoSubReport(List<Domain> items, List<OneSubDomain> oneSubReportItems, List<TwoSubDomain> twoSubReportItems, String format, String source, String oneSubReportSource, String twoSubReportSource);

    public byte[] getExportReportWithTwoSubReport(List<Domain> items, List<OneSubDomain> oneSubReportItems, List<TwoSubDomain> twoSubReportItems, String format, String source, String oneSubReportSource, String twoSubReportSource, Map<String, Object> parametersOld);
}
