package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tasinirdepo.dao.IIslemCesidiRepository;
import com.tasinirdepo.model.IslemCesidi;

@Repository("islemCesidiRepository")
@Qualifier("islemCesidiRepository")
public class IslemCesidiRepositoryImpl implements IIslemCesidiRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<IslemCesidi> findAll() {
		return manager.createQuery("from IslemCesidi",IslemCesidi.class).getResultList();
	}

	@Override
	public int create(IslemCesidi model) {
		try {
			manager.persist(model);
			return 1;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public IslemCesidi update(IslemCesidi model) {
		try {
			return manager.merge(model);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int id) {
		manager.remove(manager.getReference(IslemCesidi.class, id));
	}

	@Override
	public IslemCesidi getById(int id) {
		return manager.find(IslemCesidi.class, id);
	}
}
