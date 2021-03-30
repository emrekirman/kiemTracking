package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IBirimRepository;
import com.tasinirdepo.model.Birim;
import com.tasinirdepo.service.IBirimService;

@Service
@Transactional
public class BirimServiceImpl implements IBirimService{

	private IBirimRepository birimRepository;

	@Autowired
	public void setBirimRepository(IBirimRepository birimRepository) {
		this.birimRepository = birimRepository;
	}

	@Override
	public List<Birim> findAll() {
		return birimRepository.findAll();
	}

	@Override
	public int create(Birim model) {
		return birimRepository.create(model);
	}

	@Override
	public Birim update(Birim model) {
		return birimRepository.update(model);
	}

	@Override
	public void delete(int id) {
		birimRepository.delete(id);
	}

	@Override
	public Birim getById(int id) {
		return birimRepository.getById(id);
	}

}
