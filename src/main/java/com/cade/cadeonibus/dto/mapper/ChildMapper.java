package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.dto.ChildDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChildMapper extends EntityMapper<ChildDTO, Child> {
}
