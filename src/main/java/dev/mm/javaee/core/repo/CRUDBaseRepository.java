package dev.mm.javaee.core.repo;

import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @param <Domain>
 * @param <ID>
 */
public interface CRUDBaseRepository<Domain, ID> {

    public Domain create(Domain newObject) throws RuntimeException;

    public List<Domain> createAll(List<Domain> newObjects) throws RuntimeException;

    public Domain edit(Domain objectToEdit) throws RuntimeException;

    public List<Domain> editAll(List<Domain> objectsToUpdate) throws RuntimeException;

    public void delete(Domain objectToDestroy) throws RuntimeException;

    public void deleteById(ID keyId) throws RuntimeException;

    public void deleteAllById(List<ID> keyIds) throws RuntimeException;

    public Domain findById(ID keyId) throws RuntimeException;

    public List<Domain> findAllById(List<ID> keyId) throws RuntimeException;

    public List<Domain> findAll() throws RuntimeException;

    public List<Domain> findAll(Sort sort) throws RuntimeException;

    public PageDTO<Domain> findAll(Pageable pageable) throws RuntimeException;

    public default long count() throws RuntimeException {
        return findAll().size();
    }

    public Domain findOneWithExample(Example example) throws RuntimeException;

    public List<Domain> findAllWithExample(Example example) throws RuntimeException;

}
