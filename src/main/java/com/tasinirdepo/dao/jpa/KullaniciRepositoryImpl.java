package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tasinirdepo.dao.IKullaniciRepository;
import com.tasinirdepo.model.Kullanici;

@Repository("kullaniciRepository")
@Qualifier("kullaniciRepository")
public class KullaniciRepositoryImpl implements IKullaniciRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Kullanici> findAll() {
		return entityManager.createQuery("from Kullanici", Kullanici.class).getResultList();
	}

	@Override
	public int create(Kullanici kullanici) {
		try {
			entityManager.persist(kullanici);
			return kullanici.getId();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Kullanici update(Kullanici kullanici) {
		try {
			return entityManager.merge(kullanici);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int id) {
		entityManager.remove(entityManager.getReference(Kullanici.class, id));

	}

	@Override
	public Kullanici getById(int id) {
		return entityManager.find(Kullanici.class, id);
	}

	@Override
	public Kullanici startSession(String kadi, String sifre) {
		String hql = "from Kullanici k where k.kullaniciAd = '" + kadi + "' and k.sifre='" + sifre + "'";
		Kullanici model = entityManager.createQuery(hql, Kullanici.class).getSingleResult();
		if (model != null) {
			return model;
		}
		return null;
	}

	@Override
	public Kullanici getByUsername(String username) {
		try {
			String hql = "From Kullanici k Where k.kullaniciAd=:username";
			Query query = entityManager.createQuery(hql, Kullanici.class);
			query.setParameter("username", username);
			Kullanici data = (Kullanici) query.getSingleResult();
			return data;

		} catch (Exception e) {
			throw e;
		}
	}
}
