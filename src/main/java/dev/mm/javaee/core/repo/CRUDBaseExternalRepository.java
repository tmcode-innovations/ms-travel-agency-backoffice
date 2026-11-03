package dev.mm.javaee.core.repo;

import java.util.List;

/**
 * @param <Entity>
 * @param <ID>
 */
public interface CRUDBaseExternalRepository <Entity, ID>{

    public Entity create(Entity newObject) throws RuntimeException;

    public Entity edit(Entity objectToEdit) throws RuntimeException;

    public void destroy(Entity objectToDestroy) throws RuntimeException;

    public void destroyById(ID keyId) throws RuntimeException;

    public Entity findBy(ID keyId) throws RuntimeException;

    public List<Entity> findAll() throws RuntimeException;

    public default long count() throws RuntimeException {
        return findAll().size();
    }

}
