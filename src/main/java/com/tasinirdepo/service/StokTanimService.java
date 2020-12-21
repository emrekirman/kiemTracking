package com.tasinirdepo.service;

import java.util.List;

import com.tasinirdepo.dto.FisHareketDto;

public interface StokTanimService {
	String getLastDepoKod() throws Exception;

	double urunStokMiktariGetir(int stokTanimId);

	List<FisHareketDto> urunGirisCikislariGetir(int stokTanimId);

	List<FisHareketDto> getFisHareketByTarih(List<FisHareketDto> data, String baslangic, String bitis) throws Exception;
}
