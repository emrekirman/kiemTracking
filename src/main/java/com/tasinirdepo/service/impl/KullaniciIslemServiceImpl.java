package com.tasinirdepo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.dao.IKullaniciRepository;
import com.tasinirdepo.dao.session.SessionCommonImpl;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.model.Kullanici;
import com.tasinirdepo.model.KullaniciIslem;
import com.tasinirdepo.model.KullaniciIslemTurleri;
import com.tasinirdepo.service.IBaseService;
import com.tasinirdepo.service.IKullaniciIslemService;

@Service
@Qualifier("kullaniciIslemService")
public class KullaniciIslemServiceImpl implements IBaseService<KullaniciIslem>, IKullaniciIslemService {

	private IBaseRepository<KullaniciIslem> repository;

	private IKullaniciRepository repository2;

	private ILogginManager laggingManager;

	private SessionCommonImpl session;

	private IBaseRepository<KullaniciIslemTurleri> islemTurleriRepo;

	@Autowired
	public KullaniciIslemServiceImpl(@Qualifier("kullaniciIslemRepository") IBaseRepository<KullaniciIslem> repository,
			@Qualifier("logRepo") ILogginManager logginManager,
			@Qualifier("kullaniciRepository") IKullaniciRepository repository2, SessionCommonImpl session,
			@Qualifier("kullaniciIslemRepo") IBaseRepository<KullaniciIslemTurleri> islemTurleriRepo) {
		this.repository = repository;
		this.laggingManager = logginManager;
		this.repository2 = repository2;
		this.session = session;
		this.islemTurleriRepo = islemTurleriRepo;
	}

	@Override
	public List<KullaniciIslem> findAll() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			laggingManager.hataEkle(e, this);
			throw e;
		}
	}

	@Override
	public int create(KullaniciIslem model) {
		try {
			return repository.create(model);
		} catch (Exception e) {
			laggingManager.hataEkle(e, this);
			throw e;
		}
	}

	@Override
	public KullaniciIslem update(KullaniciIslem model) {
		return null;
	}

	@Override
	public void delete(int id) throws Exception {

	}

	@Override
	public KullaniciIslem getById(int id) {
		try {
			return repository.getById(id);
		} catch (Exception e) {
			laggingManager.hataEkle(e, this);
			throw e;
		}
	}

	@Override
	public KullaniciIslem kullaniciIslemOlustur(KullaniciIslemTurleriEnum islem, Object entity, int EntityId) {
		try {
			Kullanici kModel = repository2.getByUsername(session.getUsername());
			KullaniciIslemTurleri islemTuru = islemTurleriRepo.getById(islem.getValue());
			KullaniciIslem model = new KullaniciIslem(new Date(), islemTuru, kModel,
					("Entity adı: " + entity.getClass().getName()), EntityId);
			int id = repository.create(model);
			KullaniciIslem data = repository.getById(id);
			return data;
		} catch (Exception e) {
			throw e;
		}
	}

}
