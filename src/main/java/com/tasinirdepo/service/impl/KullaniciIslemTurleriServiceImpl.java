package com.tasinirdepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.model.KullaniciIslemTurleri;
import com.tasinirdepo.service.IBaseService;

@Service
@Qualifier("islemTurleriService")
public class KullaniciIslemTurleriServiceImpl implements IBaseService<KullaniciIslemTurleri> {

	private IBaseRepository<KullaniciIslemTurleri> repository;

	@Autowired
	public KullaniciIslemTurleriServiceImpl(
			@Qualifier("kullaniciIslemRepo") IBaseRepository<KullaniciIslemTurleri> repository) {
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
