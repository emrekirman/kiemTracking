package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.dao.IFisHareketRepository;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.model.FisHareketGiris;
import com.tasinirdepo.model.KullaniciIslem;
import com.tasinirdepo.model.StokTanim;
import com.tasinirdepo.service.IBaseService;
import com.tasinirdepo.service.IFisHareketCikisService;
import com.tasinirdepo.service.IFisHareketGirisService;
import com.tasinirdepo.service.IKullaniciIslemService;

@Service
@Qualifier("fisHareketCikisService")
public class FisHareketCikisServiceImpl implements IBaseService<FisHareketCikis>, IFisHareketCikisService {

	private IBaseRepository<FisHareketCikis> fisHareketCikisRepository;

	private IFisHareketRepository<FisHareketCikis> fisHareketRepository;

	private IBaseRepository<StokTanim> stokTanimRepository;

	private IFisHareketGirisService fisHareketGirisService;
	
	private IKullaniciIslemService kullaniciIslemService;

	private IBaseService<KullaniciIslem> kullaniciIslemBaseService;

	@Override
	@Transactional
	public List<FisHareketCikis> findAll() {
		return fisHareketCikisRepository.findAll();
	}

	@Override
	@Transactional
	public int create(FisHareketCikis model) {
		kullaniciIslemBaseService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Ekleme,
				FisHareketCikis.class, model.getId()));
		return fisHareketCikisRepository.create(model);
	}

	@Override
	@Transactional
	public FisHareketCikis update(FisHareketCikis model) {
		kullaniciIslemBaseService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Guncelleme,
				FisHareketCikis.class, model.getId()));
		return fisHareketCikisRepository.update(model);
	}

	@Override
	@Transactional
	public void delete(int id) {
		kullaniciIslemBaseService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Silme,
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
		return fisHareketRepository.createAll(girisList);
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
	@Qualifier("fisHareketCikisRepository")
	public void setFisHareketCikisRepository(IBaseRepository<FisHareketCikis> fisHareketCikisRepository) {
		this.fisHareketCikisRepository = fisHareketCikisRepository;
	}

	@Autowired
	@Qualifier("fisHareketCikisRepository")
	public void setFisHareketRepository(IFisHareketRepository<FisHareketCikis> fisHareketRepository) {
		this.fisHareketRepository = fisHareketRepository;
	}

	@Autowired
	@Qualifier("stokTanimRepository")
	public void setStokTanimRepository(IBaseRepository<StokTanim> stokTanimRepository) {
		this.stokTanimRepository = stokTanimRepository;
	}

	@Autowired
	@Qualifier("fisHareketGirisService")
	public void setFisHareketGirisService(IFisHareketGirisService fisHareketGirisService) {
		this.fisHareketGirisService = fisHareketGirisService;
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
