package com.casa.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.domain.dtos.DiaRegistroDto;
import com.casa.domain.mappers.DiaMapper;
import com.casa.repositories.DiaRepository;

@Service
public class DiaService {
	
	private final Logger log = LoggerFactory.getLogger(DiaService.class);
	
	@Autowired
	private DiaRepository diaRepository;
	
	public Map<String, Object> registrar(DiaRegistroDto dia) {
		log.info("DiaService.class - registrar -> Registrando dia");
		Map<String, Object> map = new HashMap<>();
		map.put("response", diaRepository.save(DiaMapper.convertirDtoAEntity(dia)).getId());
		return map;
	}

}
