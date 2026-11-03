package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.crm.quotation;

import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourItemModelView;
import com.saas.tourismagency.admin.backoffice.adapter.entrypoints.restapi.v1.model.crm.costtour.CostTourModelView;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.IExportBaseSubReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @param <Domain>
 * @param <SubDomain>
 */
@Service
public class JasperSubReportQuotationService<Domain, SubDomain> implements IExportBaseSubReport<Domain, SubDomain> {

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
                default -> throw new RuntimeException("Unknown report format");
            }
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        return reportContent;
    }

    public byte[] getExportReportWithSubReportOther(List<Domain> items, List<SubDomain> subReportItems, List<CostTourModelView> subReportItemsOther, String format, String source, String subReportSource, String subReportOtherSource, String subReportOtherInternalSource, Map<String, Object> parametersOld) {
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

        JasperReport jasperSubReportOther = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOther = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherSource + ".jrxml");
                jasperSubReportOther = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOther, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportOtherDataSource = new JRBeanCollectionDataSource(subReportItemsOther);

        JasperReport jasperSubReportOtherInternal = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherInternalSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOtherInternal = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherInternalSource + ".jrxml");
                jasperSubReportOtherInternal = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOtherInternal, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_subreport_item", jasperSubReport);
        parameters.put("JRDetail_subreport_item_data_source", subReportDataSource);
        parameters.put("JRDetail_subreport_item_other", jasperSubReportOther);
        parameters.put("JRDetail_subreport_item_other_data_source", subReportOtherDataSource);
        parameters.put("JRDetail_subreport_item_other_internal", jasperSubReportOtherInternal);

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

    public byte[] getExportReportWithSubReportOtherV1(List<Domain> items, List<CostTourModelView> subReportItems, List<CostTourModelView> subReportItemsOther, String format, String source, String subReportSource, String subReportOtherSource, String subReportOtherInternalSource, String subReportOtherInternalSource1, Map<String, Object> parametersOld) {
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

        JasperReport jasperSubReportOther = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOther = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherSource + ".jrxml");
                jasperSubReportOther = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOther, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportOtherDataSource = new JRBeanCollectionDataSource(subReportItemsOther);

        JasperReport jasperSubReportOtherInternal = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherInternalSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOtherInternal = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherInternalSource + ".jrxml");
                jasperSubReportOtherInternal = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOtherInternal, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JasperReport jasperSubReportOtherInternal1 = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherInternalSource1 + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOtherInternal1 = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherInternalSource1 + ".jrxml");
                jasperSubReportOtherInternal1 = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOtherInternal1, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_subreport_item", jasperSubReport);
        parameters.put("JRDetail_subreport_item_data_source", subReportDataSource);
        parameters.put("JRDetail_subreport_item_other", jasperSubReportOther);
        parameters.put("JRDetail_subreport_item_other_data_source", subReportOtherDataSource);
        parameters.put("JRDetail_subreport_item_other_internal", jasperSubReportOtherInternal);
        parameters.put("JRDetail_subreport_item_other_internal_1", jasperSubReportOtherInternal1);

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

    public byte[] getExportReportWithSubReportOtherV1(List<Domain> items, List<CostTourModelView> subReportItems, List<CostTourModelView> subReportItemsLodging, List<CostTourModelView> subReportItemsOther, String format, String source, String subReportSource, String subReportLodgingSource, String subReportOtherSource, String subReportOtherInternalSource, String subReportOtherInternalSource1, Map<String, Object> parametersOld) {
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

        JasperReport jasperSubReportLodging = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportLodgingSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportLodging = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportLodgingSource + ".jrxml");
                jasperSubReportLodging = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportLodging, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportLodgingDataSource = new JRBeanCollectionDataSource(subReportItemsLodging);

        JasperReport jasperSubReportOther = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOther = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherSource + ".jrxml");
                jasperSubReportOther = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOther, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportOtherDataSource = new JRBeanCollectionDataSource(subReportItemsOther);

        JasperReport jasperSubReportOtherInternal = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherInternalSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOtherInternal = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherInternalSource + ".jrxml");
                jasperSubReportOtherInternal = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOtherInternal, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JasperReport jasperSubReportOtherInternal1 = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherInternalSource1 + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOtherInternal1 = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherInternalSource1 + ".jrxml");
                jasperSubReportOtherInternal1 = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOtherInternal1, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_subreport_item", jasperSubReport);
        parameters.put("JRDetail_subreport_item_data_source", subReportDataSource);
        parameters.put("JRDetail_subreport_item_lodging", jasperSubReportLodging);
        parameters.put("JRDetail_subreport_item_lodging_data_source", subReportLodgingDataSource);
        parameters.put("JRDetail_subreport_item_other", jasperSubReportOther);
        parameters.put("JRDetail_subreport_item_other_data_source", subReportOtherDataSource);
        parameters.put("JRDetail_subreport_item_other_internal", jasperSubReportOtherInternal);
        parameters.put("JRDetail_subreport_item_other_internal_1", jasperSubReportOtherInternal1);

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

    public byte[] getExportReportWithSubReportOtherV2(List<Domain> items, List<CostTourModelView> subReportItems, List<CostTourModelView> subReportItemsLodging, List<CostTourItemModelView> subReportItemsGroupPassengerType, List<CostTourModelView> subReportItemsOther, String format, String source, String subReportSource, String subReportLodgingSource, String subReportGroupPassengerTypeSource, String subReportOtherSource, String subReportOtherInternalSource, String subReportOtherInternalSource1, Map<String, Object> parametersOld) {
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

        JasperReport jasperSubReportLodging = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportLodgingSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportLodging = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportLodgingSource + ".jrxml");
                jasperSubReportLodging = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportLodging, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportLodgingDataSource = new JRBeanCollectionDataSource(subReportItemsLodging);

        JasperReport jasperSubReportGroupPassengerType = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportGroupPassengerTypeSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportGroupPassengerType = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportGroupPassengerTypeSource + ".jrxml");
                jasperSubReportGroupPassengerType = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportGroupPassengerType, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportGroupPassengerTypeDataSource = new JRBeanCollectionDataSource(subReportItemsGroupPassengerType);

        JasperReport jasperSubReportOther = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOther = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherSource + ".jrxml");
                jasperSubReportOther = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOther, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource subReportOtherDataSource = new JRBeanCollectionDataSource(subReportItemsOther);

        JasperReport jasperSubReportOtherInternal = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherInternalSource + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOtherInternal = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherInternalSource + ".jrxml");
                jasperSubReportOtherInternal = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOtherInternal, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JasperReport jasperSubReportOtherInternal1 = null;

        try {
            ClassPathResource classPathResource = new ClassPathResource(subReportOtherInternalSource1 + ".jasper");
            /*jasperSubReport = (JasperReport)
                    JRLoader.loadObject(ResourceUtils.getFile("classpath:" + subReportSource + ".jasper"));*/
            jasperSubReportOtherInternal1 = (JasperReport)
                    JRLoader.loadObject(classPathResource.getInputStream());
        } catch (FileNotFoundException | JRException e) {
            try {
                File file = ResourceUtils.getFile("classpath:" + subReportOtherInternalSource1 + ".jrxml");
                jasperSubReportOtherInternal1 = JasperCompileManager.compileReport(file.getAbsolutePath());
                JRSaver.saveObject(jasperSubReportOtherInternal1, file.getAbsolutePath().replace(".jrxml", ".jasper"));
            } catch (FileNotFoundException | JRException ex) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<>();

        // Sub Report Parameter
        parameters.put("JRDetail_subreport_item", jasperSubReport);
        parameters.put("JRDetail_subreport_item_data_source", subReportDataSource);
        parameters.put("JRDetail_subreport_item_lodging", jasperSubReportLodging);
        parameters.put("JRDetail_subreport_item_lodging_data_source", subReportLodgingDataSource);
        parameters.put("JRDetail_subreport_item_group_passenger_type", jasperSubReportGroupPassengerType);
        parameters.put("JRDetail_subreport_item_group_passenger_type_data_source", subReportGroupPassengerTypeDataSource);
        parameters.put("JRDetail_subreport_item_other", jasperSubReportOther);
        parameters.put("JRDetail_subreport_item_other_data_source", subReportOtherDataSource);
        parameters.put("JRDetail_subreport_item_other_internal", jasperSubReportOtherInternal);
        parameters.put("JRDetail_subreport_item_other_internal_1", jasperSubReportOtherInternal1);

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
