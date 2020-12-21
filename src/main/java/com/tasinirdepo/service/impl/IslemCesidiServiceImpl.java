package com.tasinirdepo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.model.IslemCesidi;
import com.tasinirdepo.service.IBaseService;

@Service
@Transactional
@Qualifier("islemCesidiService")
public class IslemCesidiServiceImpl implements IBaseService<IslemCesidi> {

	private IBaseRepository<IslemCesidi> islemCesidiRepository;
	
	@Autowired
	@Qualifier("islemCesidiRepository")
	public void setIslemCesidiRepository(IBaseRepository<IslemCesidi> islemCesidiRepository) {
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
