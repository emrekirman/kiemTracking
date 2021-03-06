package com.tasinirdepo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.StokTanimRepository;
import com.tasinirdepo.dto.FisHareketDto;
import com.tasinirdepo.model.StokTanim;
import com.tasinirdepo.service.StokTanimService;

@Service
@Transactional
public class StokTanimServiceImpl implements StokTanimService {

	private StokTanimRepository stokTanimRepository;

	@Autowired
	public void setRepository(StokTanimRepository stokTanimRepository) {
		this.stokTanimRepository = stokTanimRepository;
	}

	@Override
	public String getLastDepoKod() throws Exception {
		List<String> data = stokTanimRepository.getLastDepoKod();
		if (data.size() > 0) {
			return data.get(0).split("-")[1];
		}
		else {
			throw new Exception("Stok kodu getirilemedi");
		}
	}

	@Override
	public List<StokTanim> findAll() {
		return stokTanimRepository.findAll();
	}

	@Override
	public int create(StokTanim model) {
		try {
			return stokTanimRepository.create(model);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public StokTanim update(StokTanim model) {
		return stokTanimRepository.update(model);
	}

	@Override
	public void delete(int id) {
		stokTanimRepository.delete(id);
	}

	@Override
	public StokTanim getById(int id) {
		return stokTanimRepository.getById(id);
	}

	@Override
	public double urunStokMiktariGetir(int stokTanimId) {
		List<FisHareketDto> data = this.urunGirisCikislariGetir(stokTanimId);
		double stokMiktar = 0;
		for (FisHareketDto fisHareketDto : data) {
			if (fisHareketDto.isGiris()) {
				stokMiktar += fisHareketDto.getMiktar();
			} else {
				stokMiktar -= fisHareketDto.getMiktar();
			}

		}
		return stokMiktar;
	}

	@Override
	public List<FisHareketDto> urunGirisCikislariGetir(int stokTanimId) {
		try {
			StokTanim model = stokTanimRepository.getById(stokTanimId);

			List<FisHareketDto> data = new ArrayList<FisHareketDto>();
			model.getGirisler().forEach(x -> data.add(new FisHareketDto(x)));
			model.getCikislar().forEach(x -> data.add(new FisHareketDto(x)));
			return data;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<FisHareketDto> getFisHareketByTarih(List<FisHareketDto> data, String baslangic, String bitis)
			throws Exception {
		try {
			List<FisHareketDto> list = new ArrayList<FisHareketDto>();
			SimpleDateFormat dateOptions = new SimpleDateFormat("yyyy-MM-dd");

			Date baslangicDate = dateOptions.parse(baslangic);
			Date bitisDate = dateOptions.parse(bitis);

			for (FisHareketDto item : data) {
				if ((item.getFisTarih().after(baslangicDate) && item.getFisTarih().before(bitisDate))) {
					list.add(item);
				}
			}
			return list;
		} catch (Exception e) {
			throw e;
		}
	}

}
