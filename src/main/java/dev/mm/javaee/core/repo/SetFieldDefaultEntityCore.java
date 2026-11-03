package dev.mm.javaee.core.repo;

/**
 * @param <Entity>
 */
public interface SetFieldDefaultEntityCore<Entity> {

    public Entity setDefaultFieldBaseCreate(Entity entity) throws RuntimeException;

    public Entity setDefaultFieldBaseUpdate(Entity entity) throws RuntimeException;

    public Entity setDefaultFieldBaseDelete(Entity entity) throws RuntimeException;

}
