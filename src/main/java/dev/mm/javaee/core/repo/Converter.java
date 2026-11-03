package dev.mm.javaee.core.repo;

import dev.mm.javaee.core.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @param <Domain>
 * @param <Entity>
 */
public interface Converter<Domain, Entity> {

    public Domain toDomain(Entity entity) throws RuntimeException;

    public Entity toEntity(Domain domain) throws RuntimeException;

    public default List<Domain> toDomainAll(List<Entity> list) throws RuntimeException {
        return list.stream().map((entity) -> toDomain(entity)).toList();
    }

    public default List<Entity> toEntityAll(List<Domain> list) throws RuntimeException {//convert entities to domain
        return list.stream().map((domain) -> toEntity(domain)).toList();
    }

    public default PageDTO<Domain> toDomainPage(Page<Entity> page) throws RuntimeException {
        if(page==null){
            return new PageDTO<>();
        }
        PageDTO<Domain> pageDTO = new PageDTO<>();
        pageDTO.setContent(toDomainAll(page.getContent()));
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        return pageDTO;
    }

}
