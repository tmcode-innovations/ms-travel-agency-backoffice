package dev.mm.javaee.core.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @param <Entity>
 * @param <ID>
 * @param <SpringJpaRepo>
 */
public class DefaultJPARepository <Entity, ID, SpringJpaRepo extends MongoRepository<Entity, ID>> implements CRUDBaseExternalRepository<Entity, ID> {

    private final SpringJpaRepo repo;

    public DefaultJPARepository(SpringJpaRepo repo) {
        this.repo = repo;
    }

    public SpringJpaRepo repo() {
        return repo;
    }

    @Override
    public Entity create(Entity newObject) throws RuntimeException {
        return repo.save(newObject);
    }

    @Override
    public Entity edit(Entity objectToEdit) throws RuntimeException {
        return repo.save(objectToEdit);
    }

    @Override
    public void destroy(Entity objectToDestroy) throws RuntimeException {
        repo.delete(objectToDestroy);
    }

    @Override
    public void destroyById(ID keyId) throws RuntimeException {
        repo.deleteById(keyId);
    }

    @Override
    public Entity findBy(ID keyId) throws RuntimeException {
        Optional<Entity> finded = repo.findById(keyId);
        return finded.isPresent() ? finded.get() : null;
    }

    @Override
    public List<Entity> findAll() throws RuntimeException {
        return repo.findAll();
    }

    @Override
    public long count() throws RuntimeException {
        return repo.count();
    }

}
