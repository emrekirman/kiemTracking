package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.model.CariTanim;
import com.tasinirdepo.service.ICariTanimService;

@Service
@Transactional
@Qualifier("cariTanimService")
public class CariTanimServiceImpl implements ICariTanimService {

	private IBaseRepository<CariTanim> cariTanimRepository;

	@Autowired
	@Qualifier("cariTanimRepository")
	public void setCariTanimRepository(IBaseRepository<CariTanim> cariTanimRepository) {
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
