package com.tasinirdepo.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.dao.IDepoFisRepository;
import com.tasinirdepo.dto.FaturaListeVM;
import com.tasinirdepo.dto.StokMevcuduDto;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.model.DepoFis;
import com.tasinirdepo.model.FisHareketCikis;
import com.tasinirdepo.model.FisHareketGiris;
import com.tasinirdepo.model.KullaniciIslem;
import com.tasinirdepo.service.IBaseService;
import com.tasinirdepo.service.IDepoFisService;
import com.tasinirdepo.service.IKullaniciIslemService;

@Service
@Transactional(rollbackFor = Exception.class)
@Qualifier("depoFisService")
public class DepoFisServiceImpl implements IBaseService<DepoFis>, IDepoFisService {

	private IBaseRepository<DepoFis> depoFisRepository;

	private IDepoFisRepository depoFisRepository2;

	private IBaseRepository<FisHareketGiris> girisRepository;

	private IBaseRepository<FisHareketCikis> cikisRepository;

	private ILogginManager logRepo;

	private IKullaniciIslemService kullaniciIslemService;

	private IBaseService<KullaniciIslem> kullaniciIslemBaseService;

	@Override
	public List<DepoFis> findAll(boolean giris) {
		return depoFisRepository2.findAll(giris);
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
		List<Integer> fisNolar = depoFisRepository2.getNewFisNo();
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
	@Qualifier("depoFisRepository")
	public void setDepoFisRepository(IBaseRepository<DepoFis> depoFisRepository) {
		this.depoFisRepository = depoFisRepository;
	}

	@Autowired
	@Qualifier("depoFisRepository")
	public void setDepoFisRepository2(IDepoFisRepository depoFisRepository2) {
		this.depoFisRepository2 = depoFisRepository2;
	}

	@Autowired
	@Qualifier("fisHareketGirisRepository")
	public void setGirisRepository(IBaseRepository<FisHareketGiris> girisRepository) {
		this.girisRepository = girisRepository;
	}

	@Autowired
	@Qualifier("fisHareketCikisRepository")
	public void setCikisRepository(IBaseRepository<FisHareketCikis> cikisRepository) {
		this.cikisRepository = cikisRepository;
	}

	@Autowired
	@Qualifier("logRepo")
	public void setLogRepo(ILogginManager logRepo) {
		this.logRepo = logRepo;
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