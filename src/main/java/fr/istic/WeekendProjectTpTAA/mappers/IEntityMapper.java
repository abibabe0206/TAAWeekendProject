package fr.istic.WeekendProjectTpTAA.mappers;

import org.mapstruct.MapperConfig;

import java.util.List;
import java.util.stream.Collectors;

@MapperConfig(componentModel = "spring")
public interface IEntityMapper<D, E> {

    D toDto(E e);

    E toDomain(D d);

    default List<D> toDto(List<E> eList) {
        return eList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<E> toDomain(List<D> dList) {
        return dList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
