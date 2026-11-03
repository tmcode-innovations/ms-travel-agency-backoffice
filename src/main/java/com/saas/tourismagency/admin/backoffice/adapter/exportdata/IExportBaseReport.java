package com.saas.tourismagency.admin.backoffice.adapter.exportdata;

import net.sf.jasperreports.engine.JasperReport;

import java.util.List;
import java.util.Map;

/**
 * @param <Domain>
 */

public interface IExportBaseReport<Domain> {

    public byte[] getExportReport(List<Domain> items, String format, String source);

    public byte[] getExportReport(List<Domain> items, Map<String, Object> parameters, String format, String source);

    public JasperReport getJasperReportCompiler(String source);
}
