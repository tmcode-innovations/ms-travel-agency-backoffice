package dev.mm.javaee.core.repo;

import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.base.BaseEntity;
import com.saas.tourismagency.admin.backoffice.adapter.dataproviders.jpa.data.entity.base.EntityBaseEntity;
import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @param <Domain>
 * @param <Entity>
 * @param <ID>
 * @param <GeneralConverter>
 * @param <GeneralSetFieldDefault>
 * @param <SpringMongoRepo>
 */
public class DelegatedSpringJpaRepo<Domain, Entity, ID, GeneralConverter extends Converter<Domain, Entity>, GeneralSetFieldDefault extends SetFieldDefaultEntity<Entity, Domain>, SpringMongoRepo extends MongoRepository<Entity, ID>> implements CRUDBaseRepository<Domain, ID> {

    protected final SpringMongoRepo mongoRepo;
    protected final GeneralConverter converter;
    protected final GeneralSetFieldDefault generalSetFieldDefault;

    public DelegatedSpringJpaRepo(SpringMongoRepo externalRepo, GeneralConverter converter, GeneralSetFieldDefault generalSetFieldDefault) {
        this.mongoRepo = externalRepo;
        this.converter = converter;
        this.generalSetFieldDefault = generalSetFieldDefault;
    }

    protected SpringMongoRepo repo() {
        return mongoRepo;
    }

    @Override
    public Domain create(Domain newObject) throws RuntimeException {
        Entity entity = converter.toEntity(newObject);
        entity = mongoRepo.save(generalSetFieldDefault.setDefaultFieldBaseCreate(entity));
        return converter.toDomain(entity);
    }

    @Override
    public List<Domain> createAll(List<Domain> newObjects) throws RuntimeException {
        List<Entity> entities = converter.toEntityAll(newObjects);
        entities = mongoRepo.saveAll(entities.stream().map(entity -> generalSetFieldDefault.setDefaultFieldBaseCreate(entity)).toList());
        return converter.toDomainAll(entities);
    }

    @Override
    public Domain edit(Domain objectToUpdate) throws RuntimeException {
        Entity entity = converter.toEntity(objectToUpdate);
        entity = mongoRepo.save(generalSetFieldDefault.setDefaultFieldBaseUpdate(entity));
        return converter.toDomain(entity);
    }

    @Override
    public List<Domain> editAll(List<Domain> objectsToUpdate) throws RuntimeException {
        return createAll(objectsToUpdate);
    }

    @Override
    public void delete(Domain objectToDestroy) throws RuntimeException {
        Entity entity = converter.toEntity(objectToDestroy);
        mongoRepo.save(generalSetFieldDefault.setDefaultFieldBaseDelete(entity));
    }

    @Override
    public void deleteById(ID keyId) throws RuntimeException {
        if (keyId != null) {
            Optional<Entity> finded = mongoRepo.findById(keyId);
            Entity entity = finded.isPresent() ? finded.get() : null;
            if (entity != null){
                mongoRepo.save(generalSetFieldDefault.setDefaultFieldBaseDelete(entity));
            }
        }
    }

    @Override
    public void deleteAllById(List<ID> keyIds) throws RuntimeException {
        if (keyIds != null && !keyIds.isEmpty()){
            keyIds.stream().forEach(id -> deleteById(id));
        }
    }

    @Override
    public Domain findById(ID keyId) throws RuntimeException {
        if (keyId == null) {
            return null;
        }
        Optional<Entity> finded = mongoRepo.findById(keyId);
        Entity entity = finded.isPresent() ? finded.get() : null;
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    @Override
    public List<Domain> findAllById(List<ID> keyId) throws RuntimeException {
        List<Entity> entities = mongoRepo.findAllById(keyId);
        if (entities == null) {
            return null;
        }
        return converter.toDomainAll(entities);
    }

    @Override
    public List<Domain> findAll() throws RuntimeException {
        List<Entity> allEntities = mongoRepo.findAll();
        if (allEntities == null) {
            return null;
        }
        return converter.toDomainAll(allEntities);
    }

    @Override
    public List<Domain> findAll(Sort sort) throws RuntimeException {
        List<Entity> allEntities = mongoRepo.findAll(sort);
        if (allEntities == null) {
            return null;
        }
        return converter.toDomainAll(allEntities);
    }

    @Override
    public PageDTO<Domain> findAll(Pageable pageable) throws RuntimeException {
        Page<Entity> pageableEntities = mongoRepo.findAll(pageable);
        if (pageableEntities == null) {
            return null;
        }
        return converter.toDomainPage(pageableEntities);
    }

    @Override
    public long count() throws RuntimeException {
        return mongoRepo.count();
    }

    @Override
    public Domain findOneWithExample(Example example) throws RuntimeException {
        if (example == null) {
            return null;
        }
        Optional<Entity> finded = mongoRepo.findOne(example);
        Entity entity = finded.isPresent() ? finded.get() : null;
        if (entity == null) {
            return null;
        }
        return converter.toDomain(entity);
    }

    @Override
    public List<Domain> findAllWithExample(Example example) throws RuntimeException {
        List<Entity> allEntities = mongoRepo.findAll(example);
        if (allEntities == null) {
            return null;
        }
        return converter.toDomainAll(allEntities);
    }

}
