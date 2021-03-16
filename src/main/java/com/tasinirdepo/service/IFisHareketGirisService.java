package com.tasinirdepo.service;

import java.util.List;

import com.tasinirdepo.model.FisHareketGiris;

public interface IFisHareketGirisService extends IBaseService<FisHareketGiris>{
	List<FisHareketGiris> createAll(List<FisHareketGiris> girisList);

	List<FisHareketGiris> getAllByFisId(int id);
	
	double toplamTutarHesapla(List<FisHareketGiris> list);
	
	double cikilabilecekMiktarGetir(int id);
}
