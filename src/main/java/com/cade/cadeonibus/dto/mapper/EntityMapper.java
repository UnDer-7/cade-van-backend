package com.cade.cadeonibus.dto.mapper;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */
public interface EntityMapper <D, E>{
  E toEntity(D dto);
  D toDTO(E entity);

  List<E> toEntity(List<D> dList);
  List<D> toDTO(List<E> eList);
}
