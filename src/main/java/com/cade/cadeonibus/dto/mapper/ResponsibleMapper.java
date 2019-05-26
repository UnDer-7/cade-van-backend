package com.cade.cadeonibus.dto.mapper;

import com.cade.cadeonibus.domain.Child;
import com.cade.cadeonibus.domain.Responsible;
import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.ResponsibleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsibleMapper extends EntityMapper<ResponsibleDTO, Responsible> {
}
