package com.tasinirdepo.dao;

import java.util.List;

import com.tasinirdepo.model.DepoFis;

public interface IDepoFisRepository extends IBaseRepository<DepoFis>{
	List<DepoFis> findAll(boolean giris);
	
	List<Integer> getNewFisNo();
}
