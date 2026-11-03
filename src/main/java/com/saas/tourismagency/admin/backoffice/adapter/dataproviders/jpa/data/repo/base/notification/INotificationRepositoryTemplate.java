package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.base.notification;

import com.saas.tourismagency.admin.backoffice.domain.base.*;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface INotificationRepositoryTemplate {

    public PageDTO<NotificationDomain> query(Pageable pageable, Query query);

    public List<NotificationDomain> query(Query query);

    public IdBaseDomain queryOneId(Query query);

    public List<SimpleBaseDomain> queryAllSimple(Query query);

    public List<NotificationDomain> queryAllSimpleDomainCaseGeneric(Query query);

    public PageDTO<NotificationDomain> queryAllSimpleDomainCaseListNotification(Pageable pageable, Query query);
}
