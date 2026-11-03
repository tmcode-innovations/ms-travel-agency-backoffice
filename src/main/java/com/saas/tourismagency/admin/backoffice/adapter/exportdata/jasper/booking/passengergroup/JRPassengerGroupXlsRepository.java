package com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.booking.passengergroup;

import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperReportService;
import com.saas.tourismagency.admin.backoffice.adapter.exportdata.jasper.base.JasperSubReportService;
import com.saas.tourismagency.admin.backoffice.domain.booking.PassengerGroupDomain;
import com.saas.tourismagency.admin.backoffice.domain.booking.RegisterGroupDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JRPassengerGroupXlsRepository extends JasperReportService<PassengerGroupDomain> {

    public byte[] export(List<PassengerGroupDomain> items, String format){
        return getExportReport(items, format, "jrxml/booking/JRPassengerStandardXLS");
    }

}
