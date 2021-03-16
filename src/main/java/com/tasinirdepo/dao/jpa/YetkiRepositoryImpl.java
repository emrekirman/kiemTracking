package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tasinirdepo.dao.IYetkiRepository;
import com.tasinirdepo.model.Yetki;

@Repository("yetkiRepository")
@Qualifier("yetkiRepository")
public class YetkiRepositoryImpl implements IYetkiRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Yetki> findAll() {
		return entityManager.createQuery("from Yetki",Yetki.class).getResultList();
	}

	@Override
	public int create(Yetki yetki) {
		try {
			entityManager.persist(yetki);
			return yetki.getId();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Yetki update(Yetki yetki) {
		return entityManager.merge(yetki);
	}

	@Override
	public void delete(int id) {
		entityManager.remove(entityManager.getReference(Yetki.class, id));
	}

	@Override
	public Yetki getById(int id) {
		return entityManager.find(Yetki.class, id);
	}

}
