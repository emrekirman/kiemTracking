package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IKullaniciIslemRepository;
import com.tasinirdepo.model.KullaniciIslem;

@Repository("kullaniciIslemRepository")
@Qualifier("kullaniciIslemRepository")
public class KullaniciIslemRepositoryImpl implements IKullaniciIslemRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public List<KullaniciIslem> findAll() {
		return manager.createQuery("from KullaniciIslem", KullaniciIslem.class).getResultList();
	}

	@Override
	@Transactional
	public int create(KullaniciIslem model) {
		try {
			manager.persist(model);
			return model.getId();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional
	public KullaniciIslem update(KullaniciIslem model) {
		try {
			KullaniciIslem data = manager.merge(model);
			return data;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int id) {
		try {
			manager.remove(manager.find(KullaniciIslem.class, id));
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public KullaniciIslem getById(int id) {
		try {
			return manager.find(KullaniciIslem.class, id);
		} catch (Exception e) {
			throw e;
		}
	}

}
