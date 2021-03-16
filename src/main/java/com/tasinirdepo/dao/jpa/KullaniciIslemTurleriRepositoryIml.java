package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IKullaniciIslemTurleriRepository;
import com.tasinirdepo.enums.KullaniciIslemTurleriEnum;
import com.tasinirdepo.model.KullaniciIslemTurleri;

@Repository("kullaniciIslemRepo")
@Qualifier("kullaniciIslemRepo")
public class KullaniciIslemTurleriRepositoryIml
		implements IKullaniciIslemTurleriRepository {

	@PersistenceContext
	private EntityManager manager;

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
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public KullaniciIslemTurleri getById(int id) {
		return manager.find(KullaniciIslemTurleri.class, id);
	}

	@Override
	public KullaniciIslemTurleri getByEnum(KullaniciIslemTurleriEnum islem) {
		return getById(islem.getValue());
	}
}
