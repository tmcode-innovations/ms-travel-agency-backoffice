package com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.impl;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.defaultfield.core.SetFieldDefaultPersistConsecutive;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.core.PersistConsecutiveNumberEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.PersistConsecutiveMongoTemplate;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.PersistConsecutiveNumberCallback;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.PersistConsecutiveNumberRepo;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.repo.core.utils.AtomicSequenceGenerator;
import com.saas.tourismagency.admin.backoffice.core.context.UserResolver;
import com.saas.tourismagency.admin.backoffice.core.utils.StringUtil;
import com.saas.tourismagency.admin.backoffice.domain.security.UserDomain;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class PersistConsecutiveNumberCallbackImpl implements PersistConsecutiveNumberCallback {

    @Autowired
    private PersistConsecutiveNumberRepo repo;

    @Autowired
    private AtomicSequenceGenerator atomicSequenceGenerator;

    @Autowired
    private PersistConsecutiveMongoTemplate persistConsecutiveMongoTemplate;

    @Autowired
    private SetFieldDefaultPersistConsecutive setFieldDefaultPersistConsecutive;

    protected abstract String getObjective();

    protected abstract String generateNumberEntity(PersistConsecutiveNumberEntity persistConsecutiveNumberEntity);

    protected int increment(int value) {
        atomicSequenceGenerator.setValue(value);
        return atomicSequenceGenerator.getNext();
    }

    protected void configurationQuery(Query query, String objective) {
        if (StringUtils.isNotEmpty(objective)) {
            query.addCriteria(Criteria.where("objective").is(objective));
        }
        if (UserResolver.resolve() != null){
            UserDomain userDomain = UserResolver.resolveUser();
            if (userDomain != null){
                String tenant = userDomain.getTenant();
                if (StringUtils.isNotEmpty(tenant)) {
                    query.addCriteria(Criteria.where("tenant").is(tenant));
                }
            }
        }
    }

    protected void configurationQuery(Query query, String objective, Integer year) {
        if (StringUtils.isNotEmpty(objective)) {
            query.addCriteria(Criteria.where("objective").is(objective));
        }
        if (year != null) {
            query.addCriteria(Criteria.where("year").is(year));
        }
        if (UserResolver.resolve() != null){
            UserDomain userDomain = UserResolver.resolveUser();
            if (userDomain != null){
                String tenant = userDomain.getTenant();
                if (StringUtils.isNotEmpty(tenant)) {
                    query.addCriteria(Criteria.where("tenant").is(tenant));
                }
            }
        }
    }

    protected void configurationQuery(Query query, String objective, Integer year, Integer month) {
        if (StringUtils.isNotEmpty(objective)) {
            query.addCriteria(Criteria.where("objective").is(objective));
        }
        if (year != null) {
            query.addCriteria(Criteria.where("year").is(year));
        }
        if (month != null) {
            query.addCriteria(Criteria.where("month").is(month));
        }
        if (UserResolver.resolve() != null){
            UserDomain userDomain = UserResolver.resolveUser();
            if (userDomain != null){
                String tenant = userDomain.getTenant();
                if (StringUtils.isNotEmpty(tenant)) {
                    query.addCriteria(Criteria.where("tenant").is(tenant));
                }
            }
        }
    }

    protected void configurationQuery(Query query, String objective, Integer year, Integer month, Integer day) {
        if (StringUtils.isNotEmpty(objective)) {
            query.addCriteria(Criteria.where("objective").is(objective));
        }
        if (year != null) {
            query.addCriteria(Criteria.where("year").is(year));
        }
        if (month != null) {
            query.addCriteria(Criteria.where("month").is(month));
        }
        if (day != null) {
            query.addCriteria(Criteria.where("day").is(day));
        }
        if (UserResolver.resolve() != null){
            UserDomain userDomain = UserResolver.resolveUser();
            if (userDomain != null){
                String tenant = userDomain.getTenant();
                if (StringUtils.isNotEmpty(tenant)) {
                    query.addCriteria(Criteria.where("tenant").is(tenant));
                }
            }
        }
    }

    @Override
    public String generateNumber(){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber().intValue()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(String prefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber().intValue()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + "-" + generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(String prefix, Boolean withoutSpacePrefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber().intValue()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + (withoutSpacePrefix ? "" : "-") + generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, String prefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + "-" + generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, String prefix, Boolean withoutSpacePrefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + (withoutSpacePrefix ? "" : "-") + generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, Integer month){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year, month);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            persistConsecutiveNumberEntity.setMonth(month);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, Integer month, String prefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year, month);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            persistConsecutiveNumberEntity.setMonth(month);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + "-" + generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, Integer month, String prefix, Boolean withoutSpacePrefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year, month);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            persistConsecutiveNumberEntity.setMonth(month);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + (withoutSpacePrefix ? "" : "-") + generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, Integer month, Integer day){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year, month, day);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            persistConsecutiveNumberEntity.setMonth(month);
            persistConsecutiveNumberEntity.setDay(day);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, Integer month, Integer day, String prefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year, month, day);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            persistConsecutiveNumberEntity.setMonth(month);
            persistConsecutiveNumberEntity.setDay(day);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + "-" + generateNumberEntity(persistConsecutiveNumberEntity);
    }

    @Override
    public String generateNumber(Integer year, Integer month, Integer day, String prefix, Boolean withoutSpacePrefix){
        Query query = new Query();
        String objective = getObjective();
        PersistConsecutiveNumberEntity persistConsecutiveNumberEntity = null;
        configurationQuery(query, objective, year, month, day);
        persistConsecutiveNumberEntity = persistConsecutiveMongoTemplate.query(query);
        if (persistConsecutiveNumberEntity == null){
            persistConsecutiveNumberEntity = new PersistConsecutiveNumberEntity();
            setFieldDefaultPersistConsecutive.setDefaultFieldBaseCreate(persistConsecutiveNumberEntity);
            persistConsecutiveNumberEntity.setObjective(objective);
            persistConsecutiveNumberEntity.setNumber(1);
            persistConsecutiveNumberEntity.setYear(year);
            persistConsecutiveNumberEntity.setMonth(month);
            persistConsecutiveNumberEntity.setDay(day);
            if (UserResolver.resolve() != null){
                UserDomain userDomain = UserResolver.resolveUser();
                if (userDomain != null){
                    String tenant = userDomain.getTenant();
                    if (StringUtils.isNotEmpty(tenant)) {
                        persistConsecutiveNumberEntity.setTenant(tenant);
                    }
                }
            }
            repo.create(persistConsecutiveNumberEntity);
        }else{
            persistConsecutiveNumberEntity.setNumber(increment(persistConsecutiveNumberEntity.getNumber()));
            repo.edit(persistConsecutiveNumberEntity);
        }
        return prefix + (withoutSpacePrefix ? "" : "-") + generateNumberEntity(persistConsecutiveNumberEntity);
    }

}
