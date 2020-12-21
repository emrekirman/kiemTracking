package com.tasinirdepo.dao;

import java.util.List;

import com.tasinirdepo.model.DepoFis;

public interface IDepoFisRepository {
	List<DepoFis> findAll(boolean giris);
//	int create(DepoFis model);
//	DepoFis update(DepoFis model);
//	void delete(int id);
//	DepoFis getById(int id);
	List<Integer> getNewFisNo();
}
