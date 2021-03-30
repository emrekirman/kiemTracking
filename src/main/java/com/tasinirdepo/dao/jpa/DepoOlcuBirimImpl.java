package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tasinirdepo.dao.IDepoOlcuBirimRepository;
import com.tasinirdepo.model.DepoOlcuBirim;

@Repository("depoOlcuBirimRepository")
public class DepoOlcuBirimImpl implements IDepoOlcuBirimRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DepoOlcuBirim> findAll() {
		return entityManager.createQuery("from DepoOlcuBirim", DepoOlcuBirim.class).getResultList();
	}

	@Override
	public int create(DepoOlcuBirim model) {
		entityManager.persist(model);
		return 0;
	}

	@Override
	public DepoOlcuBirim update(DepoOlcuBirim model) {
		return entityManager.merge(model);
	}

	@Override
	public void delete(int id) {
		entityManager.remove(entityManager.getReference(DepoOlcuBirim.class, id));

	}

	@Override
	public DepoOlcuBirim getById(int id) {
		return entityManager.find(DepoOlcuBirim.class, id);
	}

}
