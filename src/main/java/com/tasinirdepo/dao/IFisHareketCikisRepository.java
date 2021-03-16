package com.tasinirdepo.dao;

import java.util.List;

import com.tasinirdepo.model.FisHareketCikis;

public interface IFisHareketCikisRepository extends IBaseRepository<FisHareketCikis>{
	List<FisHareketCikis> createAll(List<FisHareketCikis> list);

	List<FisHareketCikis> getAllByFisId(int id);
}
