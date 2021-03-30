package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.model.DepoOlcuBirim;
import com.tasinirdepo.service.IDepoOlcuBirimService;

@Service
@Transactional
public class DepoOlcuBirimServiceImpl implements IDepoOlcuBirimService {

	private IBaseRepository<DepoOlcuBirim> depoOlcuBirimRepository;

	@Autowired
	public void setDepoOlcuBirimRepository(IBaseRepository<DepoOlcuBirim> depoOlcuBirimRepository) {
		this.depoOlcuBirimRepository = depoOlcuBirimRepository;
	}

	@Override
	public List<DepoOlcuBirim> findAll() {
		return depoOlcuBirimRepository.findAll();
	}

	@Override
	public int create(DepoOlcuBirim model) {
		return depoOlcuBirimRepository.create(model);
	}

	@Override
	public DepoOlcuBirim update(DepoOlcuBirim model) {
		try {
			return depoOlcuBirimRepository.update(model);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void delete(int id) {
		try {
			depoOlcuBirimRepository.delete(id);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public DepoOlcuBirim getById(int id) {
		return depoOlcuBirimRepository.getById(id);
	}

}
