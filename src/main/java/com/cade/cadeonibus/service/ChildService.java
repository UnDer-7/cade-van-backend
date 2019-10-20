package com.cade.cadeonibus.service;

import com.cade.cadeonibus.dto.ChildDTO;
import com.cade.cadeonibus.dto.ChildStatusDTO;

import java.util.List;

public interface ChildService {
  List<ChildDTO> findAll() throws Exception;

  ChildDTO getOne(Long id);

  void save(ChildDTO child);

  void updateStatus(ChildStatusDTO childStatus);
}
