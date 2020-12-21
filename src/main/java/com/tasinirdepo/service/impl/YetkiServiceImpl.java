package com.tasinirdepo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.dao.jpa.YetkiRepositoryImpl;
import com.tasinirdepo.model.Yetki;
import com.tasinirdepo.service.IBaseService;

@Service
@Transactional
@Qualifier("yetkiService")
public class YetkiServiceImpl implements IBaseService<Yetki> {

	private IBaseRepository<Yetki> yetkiRepository;
	
	@Autowired
	@Qualifier("yetkiRepository")
	public void setYetkiRepository(YetkiRepositoryImpl yetkiRepository) {
		this.yetkiRepository = yetkiRepository;
	}


	@Override
	public List<Yetki> findAll() {
		return yetkiRepository.findAll();
	}

	@Override
	public int create(Yetki model) {
		return yetkiRepository.create(model);
	}

	@Override
	public Yetki update(Yetki model) {
		return yetkiRepository.update(model);
	}

	@Override
	public void delete(int id) {
		yetkiRepository.delete(id);
		
	}

	@Override
	public Yetki getById(int id) {
		return yetkiRepository.getById(id);
	}

}
