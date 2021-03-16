package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IKullaniciIslemTurleriRepository;
import com.tasinirdepo.model.KullaniciIslemTurleri;
import com.tasinirdepo.service.IKullaniciIslemTurleriService;

@Service
@Qualifier("islemTurleriService")
public class KullaniciIslemTurleriServiceImpl implements IKullaniciIslemTurleriService {

	private IKullaniciIslemTurleriRepository repository;

	@Autowired
	public KullaniciIslemTurleriServiceImpl(
			@Qualifier("kullaniciIslemRepo") IKullaniciIslemTurleriRepository repository) {
	}

	@Override
	public List<KullaniciIslemTurleri> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int create(KullaniciIslemTurleri model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public KullaniciIslemTurleri update(KullaniciIslemTurleri model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public KullaniciIslemTurleri getById(int id) {
		return repository.getById(id);
	}

}
