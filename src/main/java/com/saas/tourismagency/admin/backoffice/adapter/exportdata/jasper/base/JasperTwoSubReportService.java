package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.IExportBaseTwoSubReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @param <Domain>
 * @param <OneSubDomain>
 * @param <TwoSubDomain>
 */
public class JasperTwoSubReportService<Domain, OneSubDomain, TwoSubDomain> implements IExportBaseTwoSubReport<Domain, OneSubDomain, TwoSubDomain> {

    @Override
    public byte[] getExportReportWithTwoSubReport(List<Domain> items, List<OneSubDomain> oneSubReportItems, List<TwoSubDomain> twoSubReportItems, String format, String source, String oneSubReportSource, String twoSubReportSource) {
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

        JasperReport jasperOneSubReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(oneSubReportSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperOneSubReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + oneSubReportSource + ".jrxml");
                jasperOneSubReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperOneSubReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource oneSubReportDataSource = new JRBeanCollectionDataSource(oneSubReportItems);

        JasperReport jasperTwoSubReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(twoSubReportSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperTwoSubReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + twoSubReportSource + ".jrxml");
                jasperTwoSubReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperTwoSubReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource twoSubReportDataSource = new JRBeanCollectionDataSource(twoSubReportItems);

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_one_subreport_item", jasperOneSubReport);
        parameters.put("JRDetail_one_subreport_item_data_source", oneSubReportDataSource);

        parameters.put("JRDetail_two_subreport_item", jasperTwoSubReport);
        parameters.put("JRDetail_two_subreport_item_data_source", twoSubReportDataSource);

        JasperPrint jasperPrint = null;
        byte[] reportContent;

        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            switch (format) {
                case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
                case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }

    @Override
    public byte[] getExportReportWithTwoSubReport(List<Domain> items, List<OneSubDomain> oneSubReportItems, List<TwoSubDomain> twoSubReportItems, String format, String source, String oneSubReportSource, String twoSubReportSource, Map<String, Object> parametersOld) {
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

        JasperReport jasperOneSubReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(oneSubReportSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperOneSubReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + oneSubReportSource + ".jrxml");
                jasperOneSubReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperOneSubReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource oneSubReportDataSource = new JRBeanCollectionDataSource(oneSubReportItems);

        JasperReport jasperTwoSubReport = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(twoSubReportSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperTwoSubReport = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + twoSubReportSource + ".jrxml");
                jasperTwoSubReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperTwoSubReport, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource twoSubReportDataSource = new JRBeanCollectionDataSource(twoSubReportItems);

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_one_subreport_item", jasperOneSubReport);
        parameters.put("JRDetail_one_subreport_item_data_source", oneSubReportDataSource);

        parameters.put("JRDetail_two_subreport_item", jasperTwoSubReport);
        parameters.put("JRDetail_two_subreport_item_data_source", twoSubReportDataSource);

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
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }
}
