package com.saas.tourismagency.admin.backoffice.adapter.exportdata;

import java.util.List;
import java.util.Map;

/**
 * @param <Domain>
 * @param <OneSubDomain>
 * @param <TwoSubDomain>
 * @param <ThreeSubDomain>
 */
public interface IExportBaseThreeSubReport<Domain, OneSubDomain, TwoSubDomain, ThreeSubDomain> {

    public byte[] getExportReportWithThreeSubReport(List<Domain> items, List<OneSubDomain> oneSubReportItems, List<TwoSubDomain> twoSubReportItems, List<ThreeSubDomain> threeSubReportItems, String format, String source, String oneSubReportSource, String twoSubReportSource, String threeSubReportSource);

    public byte[] getExportReportWithThreeSubReport(List<Domain> items, List<OneSubDomain> oneSubReportItems, List<TwoSubDomain> twoSubReportItems, List<ThreeSubDomain> threeSubReportItems, String format, String source, String oneSubReportSource, String twoSubReportSource, String threeSubReportSource, Map<String, Object> parametersOld);
}
