package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.IExportBaseReport;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.IExportBaseSubReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @param <Domain>
 * @param <SubDomain>
 */
public class JasperSubReportService<Domain, SubDomain> implements IExportBaseSubReport<Domain, SubDomain> {

    @Override
    public byte[] getExportReportWithSubReport(List<Domain> items, List<SubDomain> subReportItems, String format, String source, String subReportSource) {
        JasperReport jasperReport = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(source + ".jasper");
            /*jasperReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + source + ".jasper"));*/
            jasperReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + source + ".jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);

        JasperReport jasperSubReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportSource + ".jrxml");
                jasperSubReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportDataSource = new JRBeanCollectionDataSource(subReportItems);

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_subreport_item", jasperSubReport);
        parameters.put("JRDetail_subreport_item_data_source", subReportDataSource);

        JasperPrint jasperPrint = null;
        byte[] reportContent;

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            switch (format) {
                case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
                case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
                case "xlsx" -> {
                    JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    xlsxExporter.exportReport();
                    reportContent = outputStream.toByteArray();
                }
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }

    @Override
    public byte[] getExportReportWithSubReport(List<Domain> items, List<SubDomain> subReportItems, String format, String source, String subReportSource, Map<String, Object> parametersOld) {
        JasperReport jasperReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(source + ".jasper");
            /*jasperReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + source + ".jasper"));*/
            jasperReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + source + ".jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);

        JasperReport jasperSubReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportSource + ".jrxml");
                jasperSubReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportDataSource = new JRBeanCollectionDataSource(subReportItems);

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_subreport_item", jasperSubReport);
        parameters.put("JRDetail_subreport_item_data_source", subReportDataSource);

        if (parametersOld != null && !parametersOld.isEmpty()){
            parameters.putAll(parametersOld);
        }

        JasperPrint jasperPrint = null;
        byte[] reportContent;

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            switch (format) {
                case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
                case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
                case "xlsx" -> {
                    JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    xlsxExporter.exportReport();
                    reportContent = outputStream.toByteArray();
                }
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }

    public byte[] getExportReportWithSubReportWithUnionOtherReport(List<Domain> items, List<SubDomain> subReportItems, List<Domain> itemsOther, List<SubDomain> subReportItemsOther, String format, String source, String sourceOther, String subReportSource, Map<String, Object> parametersOld, Map<String, Object> parametersOldOther) {
        JasperReport jasperReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(source + ".jasper");
            /*jasperReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + source + ".jasper"));*/
            jasperReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + source + ".jrxml");
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);

        JasperReport jasperReportOther = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(sourceOther + ".jasper");
            /*jasperReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + source + ".jasper"));*/
            jasperReportOther = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + sourceOther + ".jrxml");
                jasperReportOther = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReportOther, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource dataSourceOther = new JRBeanCollectionDataSource(itemsOther);

        JasperReport jasperSubReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportSource + ".jrxml");
                jasperSubReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportDataSource = new JRBeanCollectionDataSource(subReportItems);

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_subreport_item", jasperSubReport);
        parameters.put("JRDetail_subreport_item_data_source", subReportDataSource);

        if (parametersOld != null && !parametersOld.isEmpty()){
            parameters.putAll(parametersOld);
        }

        Map<String, Object> parametersOther = new HashMap<>();

        // Sub Report Parameter
        parametersOther.put("JRDetail_subreport_item", jasperSubReport);
        parametersOther.put("JRDetail_subreport_item_data_source", subReportDataSource);

        if (parametersOldOther != null && !parametersOldOther.isEmpty()){
            parametersOther.putAll(parametersOldOther);
        }

        List<JasperPrint> jasperPrints = new ArrayList<>();

        JasperPrint jasperPrint = null;
        JasperPrint jasperPrintOther = null;
        byte[] reportContent;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            jasperPrintOther = JasperFillManager.fillReport(jasperReportOther, parametersOther, dataSourceOther);
            jasperPrints.add(jasperPrint);
            jasperPrints.add(jasperPrintOther);
            switch (format) {
                case "pdf" -> {

                    JRPdfExporter exporter = new JRPdfExporter();

                    exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

                    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                    exporter.setConfiguration(configuration);

                    exporter.exportReport();

                    reportContent = outputStream.toByteArray();
                }
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }
}
