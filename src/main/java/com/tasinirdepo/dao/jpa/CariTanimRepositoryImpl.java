package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tasinirdepo.dao.ICariTanimRepository;
import com.tasinirdepo.model.CariTanim;

@Repository("cariTanimRepository")
@Qualifier("cariTanimRepository")
public class CariTanimRepositoryImpl implements ICariTanimRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<CariTanim> findAll() {
		return entityManager.createQuery("from CariTanim",CariTanim.class).getResultList();
	}

	public int create(CariTanim model) {
		entityManager.persist(model);
		return 0;
	}

	public CariTanim update(CariTanim model) {
		entityManager.merge(model);
		return null;
	}

	public void delete(int id) {
		entityManager.remove(entityManager.getReference(CariTanim.class, id));
		
	}

	public CariTanim getById(int id) {
		return entityManager.find(CariTanim.class, id);
	}


}
