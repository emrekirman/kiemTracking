package com.tasinirdepo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IFisHareketRepository;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.model.FisHareketGiris;
import com.tasinirdepo.model.KullaniciIslem;
import com.tasinirdepo.service.IBaseService;
import com.tasinirdepo.service.IFisHareketGirisService;
import com.tasinirdepo.service.IKullaniciIslemService;

@Service
@Transactional
@Qualifier("fisHareketGirisService")
public class FisHareketGirisServiceImpl implements IFisHareketGirisService {

	private IFisHareketRepository fisHareketRepository;

	private IBaseService<DepoFis> depoFisService;

	private IKullaniciIslemService kullaniciIslemService;

	private IBaseService<KullaniciIslem> kullaniciIslemBaseService;

	@Override
	public List<FisHareketGiris> findAll() {
		return fisHareketRepository.findAll();
	}

	@Override
	public int create(FisHareketGiris model) {
		kullaniciIslemBaseService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Ekleme,
				model, model.getId()));
		return fisHareketRepository.create(model);
	}

	@Override
	public FisHareketGiris update(FisHareketGiris model) {
		kullaniciIslemBaseService.create(kullaniciIslemService.kullaniciIslemOlustur(
				KullaniciIslemTurleriEnum.Guncelleme, model, model.getId()));
		FisHareketGiris data = fisHareketRepository.update(model);
		return data;
	}

	@Override
	public void delete(int id) throws Exception {
		kullaniciIslemBaseService.create(kullaniciIslemService
				.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Guncelleme, FisHareketGiris.class, id));
		FisHareketGiris data = fisHareketRepository.getById(id);
		if (data.getCikisList().size() <= 0) {
			fisHareketRepository.delete(id);
			DepoFis model = depoFisService.getById(data.getDepoFis().getId());
			depoFisService.update(model);
		} else {
			throw new Exception();
		}
	}

	@Override
	public FisHareketGiris getById(int id) {
		return fisHareketRepository.getById(id);
	}

	@Override
	public List<FisHareketGiris> createAll(List<FisHareketGiris> girisList) {
		return fisHareketRepository.createAll(girisList);
	}

	@Override
	public List<FisHareketGiris> getAllByFisId(int id) {
		return fisHareketRepository.getAllByFisId(id);
	}

	@Override
	public double toplamTutarHesapla(List<FisHareketGiris> list) {
		double toplam = 0;
		for (FisHareketGiris fisHareketGiris : list) {
			toplam += fisHareketGiris.getTutar();
		}
		return toplam;
	}

	@Override
	public double cikilabilecekMiktarGetir(int id) {
		/*
		 * Bu metot giriş tifinin çıkış işleminde ne kadar miktar çıkılabileceğini
		 * getirir.
		 */
		try {
			FisHareketGiris model = fisHareketRepository.getById(id);
			double toplamMiktar = model.getMiktar();
			for (FisHareketCikis item : model.getCikisList()) {
				toplamMiktar -= item.getMiktar();
			}
			return toplamMiktar;

		} catch (Exception e) {
			throw e;
		}
	}

	@Autowired
	@Qualifier("fisHareketGirisRepository")
	public void setFisHareketGirisRepository(IFisHareketRepository fisHareketGirisRepository) {
		this.fisHareketRepository = fisHareketGirisRepository;
	}

	@Autowired
	@Qualifier("depoFisService")
	public void setDepoFisService(IBaseService<DepoFis> depoFisService) {
		this.depoFisService = depoFisService;
	}

	@Autowired
	public void setKullanisiIslemRepo(IKullaniciIslemService kullaniciIslemService) {
		this.kullaniciIslemService = kullaniciIslemService;
	}

	@Autowired
	@Qualifier("kullaniciIslemService")
	public void setKullaniciIslemBaseService(IBaseService<KullaniciIslem> kullaniciIslemBaseService) {
		this.kullaniciIslemBaseService = kullaniciIslemBaseService;
	}
}
