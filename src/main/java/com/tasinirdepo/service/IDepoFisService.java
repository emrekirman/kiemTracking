package com.tasinirdepo.service;

import java.util.List;

import com.tasinirdepo.dto.FaturaListeVM;
import com.tasinirdepo.dto.StokMevcuduDto;
import com.tasinirdepo.model.DepoFis;

public interface IDepoFisService extends IBaseService<DepoFis>{

	List<DepoFis> findAll(boolean giris);

	int getNewFisNo();

	List<StokMevcuduDto> stokMevcudu();

	List<FaturaListeVM> createFaturaListeVm(List<DepoFis> models);
	
	DepoFis getFisByHareketId(int hareketId);
}
