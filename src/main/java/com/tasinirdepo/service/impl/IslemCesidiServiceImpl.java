package com.tasinirdepo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IIslemCesidiRepository;
import com.tasinirdepo.model.IslemCesidi;
import com.tasinirdepo.service.IIslemCesidiService;

@Service
@Transactional
public class IslemCesidiServiceImpl implements IIslemCesidiService {

	private IIslemCesidiRepository islemCesidiRepository;
	
	@Autowired
	public void setIslemCesidiRepository(IIslemCesidiRepository islemCesidiRepository) {
		this.islemCesidiRepository = islemCesidiRepository;
	}

	@Override
	public List<IslemCesidi> findAll() {
		return islemCesidiRepository.findAll();
	}

	@Override
	public int create(IslemCesidi model) {
		return islemCesidiRepository.create(model);
	}

	@Override
	public IslemCesidi update(IslemCesidi model) {
		return islemCesidiRepository.update(model);
	}

	@Override
	public void delete(int id) {
		islemCesidiRepository.delete(id);
	}

	@Override
	public IslemCesidi getById(int id) {
		return islemCesidiRepository.getById(id);
	}

}
