package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tasinirdepo.dao.IBirimRepository;
import com.tasinirdepo.model.Birim;

@Repository("birimRepository")
public class BirimRepositoryImpl implements IBirimRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Birim> findAll() {
		List<Birim> data = manager.createQuery("from Birim", Birim.class).getResultList();
		return data;

	}

	@Override
	public int create(Birim birim) {
		manager.persist(birim);
		return 1;
	}

	@Override
	public Birim update(Birim birim) {
		return manager.merge(birim);
	}

	@Override
	public void delete(int id) {
		manager.remove(manager.getReference(Birim.class, id));
	}

	@Override
	public Birim getById(int id) {
		return manager.find(Birim.class, id);
	}

}
