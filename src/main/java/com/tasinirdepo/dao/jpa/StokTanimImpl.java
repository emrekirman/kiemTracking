package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tasinirdepo.dao.StokTanimRepository;
import com.tasinirdepo.model.StokTanim;

@Repository("stokTanimRepository")
@Qualifier("stokTanimRepository")
public class StokTanimImpl implements StokTanimRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<StokTanim> findAll() {
		return entityManager.createQuery("from StokTanim", StokTanim.class).getResultList();
	}

	@Override
	public int create(StokTanim stokTanim) {
		entityManager.persist(stokTanim);
		return 0;
	}

	@Override
	public StokTanim update(StokTanim stokTanim) {
		return entityManager.merge(stokTanim);
	}

	@Override
	public void delete(int id) {
		entityManager.remove(entityManager.getReference(StokTanim.class, id));

	}

	@Override
	public StokTanim getById(int id) {
		return entityManager.find(StokTanim.class, id);
	}

	@Override
	public List<String> getLastDepoKod() {
		List<String> data = entityManager
				.createQuery("select s.depoKod from StokTanim s order by id desc", String.class).setMaxResults(1)
				.getResultList();
		return data;
	}

}
