package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.ChildDTO;

public interface ChildService {
  ChildDTO getChild(Long id);
  ChildDTO saveChild(ChildDTO child);
}
