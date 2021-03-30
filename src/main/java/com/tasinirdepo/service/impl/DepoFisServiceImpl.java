package com.tasinirdepo.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IDepoFisRepository;
import com.tasinirdepo.dao.IFisHareketCikisRepository;
import com.tasinirdepo.dao.IFisHareketRepository;
import com.tasinirdepo.dao.IKullaniciIslemRepository;
import com.tasinirdepo.dto.FaturaListeVM;
import com.tasinirdepo.dto.StokMevcuduDto;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.model.FisHareketGiris;
import com.tasinirdepo.service.IDepoFisService;
import com.tasinirdepo.service.IKullaniciIslemService;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepoFisServiceImpl implements IDepoFisService {

	private IDepoFisRepository depoFisRepository;

	private IFisHareketRepository girisRepository;

	private IFisHareketCikisRepository cikisRepository;

	private ILogginManager logRepo;

	private IKullaniciIslemService kullaniciIslemService;

	private IKullaniciIslemRepository kullaniciIslemBaseService;

	@Override
	public List<DepoFis> findAll(boolean giris) {
		return depoFisRepository.findAll(giris);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int create(DepoFis model) {
		kullaniciIslemBaseService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Ekleme,
				DepoFis.class, model.getId()));
		return depoFisRepository.create(model);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DepoFis getById(int id) {
		DepoFis data = depoFisRepository.getById(id);
		return data;
	}

	@Override
	public DepoFis update(DepoFis model) {
		kullaniciIslemBaseService.create(kullaniciIslemService
				.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Guncelleme, model, model.getId()));
		return depoFisRepository.update(model);
	}

	@Override
	public void delete(int id) {
		kullaniciIslemBaseService.create(
				kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Silme, DepoFis.class, id));
		depoFisRepository.delete(id);
	}

	@Override
	public int getNewFisNo() {
		List<Integer> fisNolar = depoFisRepository.getNewFisNo();
		return fisNolar.size() > 0 ? (fisNolar.get(0) + 1) : 1;
	}

	@Override
	public List<StokMevcuduDto> stokMevcudu() {
		try {
			List<FisHareketGiris> girisData = girisRepository.findAll();
			List<FisHareketCikis> cikisData = cikisRepository.findAll();
			Hashtable<String, StokMevcuduDto> data = new Hashtable<String, StokMevcuduDto>();

			for (FisHareketGiris item : girisData) {
				String key = item.getStokTanim().getDepoKod() + "_" + item.getOlcuBirim().getTanim();
				double miktar = item.getMiktar();
				if (data.containsKey(key)) {
					data.get(key).setMiktar(data.get(key).getMiktar() + miktar);
				} else {
					data.put(key, new StokMevcuduDto(item));
				}
			}

			for (FisHareketCikis item : cikisData) {
				String key = item.getStokTanim().getDepoKod() + "_" + item.getOlcuBirim().getTanim();
				double miktar = item.getMiktar();
				if (data.containsKey(key)) {
					data.get(key).setMiktar(data.get(key).getMiktar() - miktar);
				}
			}
			List<StokMevcuduDto> viewData = new ArrayList<StokMevcuduDto>();
			for (StokMevcuduDto item : data.values()) {
				if (item.getMiktar() > 0) {
					viewData.add(item);
				}
			}
			return viewData;

		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			throw e;
		}
	}

	@Override
	public List<FaturaListeVM> createFaturaListeVm(List<DepoFis> models) {
		/*
		 * Fişleri json parse hatası almamak için buradan düzeltip controller üzerinden
		 * çağıracağız.
		 */
		try {
			List<FaturaListeVM> data = new ArrayList<FaturaListeVM>();
			for (DepoFis item : models) {
				data.add(new FaturaListeVM(item));
			}

			return data;
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return null;
		}
	}

	@Override
	public DepoFis getFisByHareketId(int hareketId) {
		FisHareketGiris model = girisRepository.getById(hareketId);
		return model.getDepoFis();
	}

	@Override
	public List<DepoFis> findAll() {
		return depoFisRepository.findAll();
	}

	@Autowired
	public void setDepoFisRepository2(IDepoFisRepository depoFisRepository2) {
		this.depoFisRepository = depoFisRepository2;
	}

	@Autowired
	public void setGirisRepository(IFisHareketRepository girisRepository) {
		this.girisRepository = girisRepository;
	}

	@Autowired
	public void setCikisRepository(IFisHareketCikisRepository cikisRepository) {
		this.cikisRepository = cikisRepository;
	}

	@Autowired
	public void setLogRepo(ILogginManager logRepo) {
		this.logRepo = logRepo;
	}

	@Autowired
	public void setKullanisiIslemRepo(IKullaniciIslemService kullaniciIslemService) {
		this.kullaniciIslemService = kullaniciIslemService;
	}

	@Autowired
	public void setKullaniciIslemBaseService(IKullaniciIslemRepository kullaniciIslemBaseService) {
		this.kullaniciIslemBaseService = kullaniciIslemBaseService;
	}

}
