package com.saas.tourismagency.admin.backoffice.adapter.scheduled.account;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.account.dateprocess.DateProcessRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.OrganizationRepo;
import com.saas.tourismagency.admin.backoffice.domain.account.DateProcessDomain;
import com.saas.tourismagency.admin.backoffice.domain.core.OrganizationDomain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

// @Component
public class SchedulerDateProcess {

    @Autowired
    private DateProcessRepo dateProcessRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    Logger logger = LogManager.getLogger(SchedulerDateProcess.class);

    @Scheduled(cron = "0 */10 * * * *")
    public void scheduleTask()
    {
        logger.info("INIT - SchedulerDateProcess");
        List<OrganizationDomain> organizations = organizationRepo.findAllOrganizations(false);
        if (organizations != null && !organizations.isEmpty()){
            for (OrganizationDomain organizationDomain: organizations){
                DateProcessDomain dateProcessDomain = null;
                LocalDate dateCurrent = LocalDate.now();
                dateProcessDomain = dateProcessRepo.findByDate(dateCurrent, organizationDomain.getTenant());
                if (dateProcessDomain == null){
                    dateProcessDomain = new DateProcessDomain();
                    dateProcessDomain.setDate(LocalDate.now());
                    dateProcessDomain.setTenant(organizationDomain.getTenant());
                    dateProcessRepo.create(dateProcessDomain);
                }
            }
        }
        logger.info("END - SchedulerDateProcess");
    }
}
