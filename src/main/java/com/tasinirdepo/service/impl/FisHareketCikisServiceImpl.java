package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IFisHareketCikisRepository;
import com.tasinirdepo.dao.StokTanimRepository;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.model.FisHareketGiris;
import com.tasinirdepo.service.IFisHareketCikisService;
import com.tasinirdepo.service.IFisHareketGirisService;
import com.tasinirdepo.service.IKullaniciIslemService;

@Service
public class FisHareketCikisServiceImpl implements IFisHareketCikisService {

	private IFisHareketCikisRepository fisHareketCikisRepository;

	private StokTanimRepository stokTanimRepository;

	private IFisHareketGirisService fisHareketGirisService;
	
	private IKullaniciIslemService kullaniciIslemService;

	@Override
	@Transactional
	public List<FisHareketCikis> findAll() {
		return fisHareketCikisRepository.findAll();
	}

	@Override
	@Transactional
	public int create(FisHareketCikis model) {
		kullaniciIslemService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Ekleme,
				FisHareketCikis.class, model.getId()));
		return fisHareketCikisRepository.create(model);
	}

	@Override
	@Transactional
	public FisHareketCikis update(FisHareketCikis model) {
		kullaniciIslemService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Guncelleme,
				FisHareketCikis.class, model.getId()));
		return fisHareketCikisRepository.update(model);
	}

	@Override
	@Transactional
	public void delete(int id) {
		kullaniciIslemService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Silme,
				FisHareketCikis.class, id));
		fisHareketCikisRepository.delete(id);
	}

	@Override
	@Transactional
	public FisHareketCikis getById(int id) {
		return fisHareketCikisRepository.getById(id);
	}

	@Override
	@Transactional
	public List<FisHareketCikis> createAll(List<FisHareketCikis> girisList) {
		return fisHareketCikisRepository.createAll(girisList);
	}

	@Override
	@Transactional
	public void cikisIslemi(FisHareketCikis fisHareketCikis, DepoFis fisModel) {
		try {
			List<FisHareketGiris> girisler = stokTanimRepository.getById(fisHareketCikis.getStokTanim().getId())
					.getGirisler();
			for (FisHareketGiris fisHareketGiris : girisler) {

				double cikilabilecekMiktar = fisHareketGirisService.cikilabilecekMiktarGetir(fisHareketGiris.getId());
				double cikilacakMiktar = 0;
				if (fisHareketCikis.getOlcuBirim().getId() == fisHareketGiris.getOlcuBirim().getId()) {
					if (fisHareketCikis.getMiktar() <= cikilabilecekMiktar) {
						cikilacakMiktar = fisHareketCikis.getMiktar();
					} else if (cikilabilecekMiktar <= 0) {
						continue;
					} else {
						cikilacakMiktar = cikilabilecekMiktar;
					}
					FisHareketCikis cikisModel = new FisHareketCikis(fisHareketCikis, cikilacakMiktar, fisHareketGiris);
					cikisModel.setDepoFis(fisModel);
					fisHareketCikisRepository.create(cikisModel);
					fisHareketCikis.setMiktar(fisHareketCikis.getMiktar() - cikilacakMiktar);
					if (fisHareketCikis.getMiktar() == 0) {
						break;
					}
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Autowired
	public void setFisHareketCikisRepository(IFisHareketCikisRepository fisHareketCikisRepository) {
		this.fisHareketCikisRepository = fisHareketCikisRepository;
	}

	@Autowired
	public void setStokTanimRepository(StokTanimRepository stokTanimRepository) {
		this.stokTanimRepository = stokTanimRepository;
	}

	@Autowired
	public void setFisHareketGirisService(IFisHareketGirisService fisHareketGirisService) {
		this.fisHareketGirisService = fisHareketGirisService;
	}
	
	@Autowired
	public void setKullanisiIslemRepo(IKullaniciIslemService kullaniciIslemService) {
		this.kullaniciIslemService = kullaniciIslemService;
	}

}
