package dev.mm.javaee.core.repo;

import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @param <Domain>
 * @param <Entity>
 */
public interface SetFieldDefaultEntity<Entity, Domain> {

    public Entity setDefaultFieldBaseCreate(Entity entity) throws RuntimeException;

    public Entity setDefaultFieldBaseUpdate(Entity entity) throws RuntimeException;

    public Entity setDefaultFieldBaseDelete(Entity entity) throws RuntimeException;

    public Domain setDefaultFieldBaseUpdated(Domain domainTarget, Domain domainSource) throws RuntimeException;
}
