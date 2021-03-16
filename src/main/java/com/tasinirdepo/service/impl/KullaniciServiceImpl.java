package com.tasinirdepo.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IKullaniciRepository;
import com.tasinirdepo.dao.session.SessionCommonImpl;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.Kullanici;
import com.tasinirdepo.model.KullaniciIslem;
import com.tasinirdepo.service.IBaseService;
import com.tasinirdepo.service.IKullaniciIslemService;
import com.tasinirdepo.service.IKullaniciService;

@Service
@Transactional
@Qualifier("kullaniciService")
public class KullaniciServiceImpl implements IKullaniciService {

	private IKullaniciRepository kullaniciRepository;

	private SessionCommonImpl session;

	private IKullaniciIslemService kullaniciIslemService;

	private IBaseService<KullaniciIslem> kullaniciIslemBaseService;

	public KullaniciServiceImpl(SessionCommonImpl session) {
		this.session = session;
	}

	@Autowired
	@Qualifier("kullaniciRepository")
	public void setKullaniciRepository(IKullaniciRepository kullaniciRepository) {
		this.kullaniciRepository = kullaniciRepository;
	}


	@Override
	public List<Kullanici> findAll() {
		return kullaniciRepository.findAll();
	}

	@Override
	public int create(Kullanici kullanici) {
		kullaniciIslemBaseService.create(kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Ekleme,
				kullanici, kullanici.getId()));
		kullaniciRepository.create(kullanici);
		return 0;
	}

	@Override
	public Kullanici update(Kullanici model) {
		kullaniciIslemBaseService.create(
				kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Ekleme, model, model.getId()));
		kullaniciRepository.update(model);
		return null;
	}

	@Override
	public void delete(int id) {
		kullaniciIslemBaseService.create(
				kullaniciIslemService.kullaniciIslemOlustur(KullaniciIslemTurleriEnum.Ekleme, Kullanici.class, id));
		kullaniciRepository.delete(id);

	}

	@Override
	public Kullanici getById(int id) {
		return kullaniciRepository.getById(id);
	}

	@Override
	public Kullanici startSession(String kadi, String sifre) {
		return kullaniciRepository.startSession(kadi, sifre);
	}

	@Override
	public Kullanici getSession(HttpSession session) {
		return null;
	}

	@Override
	public void destroySession(HttpServletRequest request) {

	}

	@Override
	public void destroySession() {
		session.setToken(null);
		session.setUsername(null);
		session.setYil(0);
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
