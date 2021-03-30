package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.ICariTanimRepository;
import com.tasinirdepo.model.CariTanim;
import com.tasinirdepo.service.ICariTanimService;

@Service
@Transactional
public class CariTanimServiceImpl implements ICariTanimService {

	private ICariTanimRepository cariTanimRepository;

	@Autowired
	public void setCariTanimRepository(ICariTanimRepository cariTanimRepository) {
		this.cariTanimRepository = cariTanimRepository;
	}

	@Override
	public int create(CariTanim cariTanim) {
		return cariTanimRepository.create(cariTanim);
	}

	@Override
	public CariTanim update(CariTanim cariTanim) {
		return cariTanimRepository.update(cariTanim);
	}

	@Override
	public void delete(int id) {
		cariTanimRepository.delete(id);

	}

	@Override
	public CariTanim getById(int id) {
		return cariTanimRepository.getById(id);
	}

	@Override
	public List<CariTanim> findAll() {
		return cariTanimRepository.findAll();
	}
}
