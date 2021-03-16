package com.tasinirdepo.dao;

import java.util.List;

import com.tasinirdepo.model.FisHareketGiris;

public interface IFisHareketRepository extends IBaseRepository<FisHareketGiris> {
	List<FisHareketGiris> createAll(List<FisHareketGiris> list);

	List<FisHareketGiris> getAllByFisId(int id);
}
