package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.IExportBaseReport;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @param <Domain>
 */
public class JasperReportService<Domain> implements IExportBaseReport<Domain> {

    @Override
    public byte[] getExportReport(List<Domain> items, String format, String source) {
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
                JRSaver.saveObject(jasperReport, source + ".jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);

        Map<String, Object> parameters = new HashMap<>();

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
    public byte[] getExportReport(List<Domain> items, Map<String, Object> parameters, String format, String source) {
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
                JRSaver.saveObject(jasperReport, source + ".jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);

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

    public byte[] getExportReportWithUnionOtherReport(List<Domain> items, List<Domain> itemsOther, Map<String, Object> parameters, Map<String, Object> parametersOther, String format, String source, String sourceOther) {
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
                JRSaver.saveObject(jasperReport, source + ".jasper");
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
                File file = ResourceUtils.getFile("classpath:" + sourceOther + ",jrxml");
                jasperReportOther = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperReportOther, source + ".jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource dataSourceOther = new JRBeanCollectionDataSource(itemsOther);

        List<JasperPrint> jasperPrints = new ArrayList<>();

        JasperPrint jasperPrint = null;
        JasperPrint jasperPrintOther = null;
        byte[] reportContent;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            jasperPrintOther = JasperFillManager.fillReport(jasperReportOther, parametersOther, dataSourceOther);
            if (dataSource != null &&  dataSource.getData() != null && !dataSource.getData().isEmpty()){
                jasperPrints.add(jasperPrint);
            }
            if (dataSourceOther != null &&  dataSourceOther.getData() != null && !dataSourceOther.getData().isEmpty()){
                jasperPrints.add(jasperPrintOther);
            }
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
                /*case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
                case "xlsx" -> {
                    JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                    xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    xlsxExporter.exportReport();
                    reportContent = outputStream.toByteArray();
                }*/
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }

    @Override
    public JasperReport getJasperReportCompiler(String source) {
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
                JRSaver.saveObject(jasperReport, source + ".jasper");
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jasperReport;
    }

}
