package com.tasinirdepo.service;

import java.util.List;

import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;

public interface IFisHareketCikisService {
	List<FisHareketCikis> createAll(List<FisHareketCikis> girisList);
	
	void cikisIslemi(FisHareketCikis fisHareketCikis, DepoFis fisModel);
}