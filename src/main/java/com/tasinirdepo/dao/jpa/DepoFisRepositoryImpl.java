package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.dao.IDepoFisRepository;
import com.tasinirdepo.model.DepoFis;

@Repository("depoFisRepository")
@Qualifier("depoFisRepository")
public class DepoFisRepositoryImpl implements IBaseRepository<DepoFis>, IDepoFisRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<DepoFis> findAll(boolean giris) {
		String sql = "from DepoFis d where d.giris=:giris";
		List<DepoFis> data = manager.createQuery(sql, DepoFis.class).setParameter("giris", giris).getResultList();
		/*
		 * for (DepoFis depoFis : data) { depoFis.setGirisList(null); }
		 */
		return data;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int create(DepoFis model) {
		try {
			manager.persist(model);
			return model.getId();

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public DepoFis update(DepoFis model) {
		try {
			manager.merge(model);
			return manager.find(DepoFis.class, model.getId());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int id) {
		manager.remove(manager.getReference(DepoFis.class, id));
	}

	@Override
	public DepoFis getById(int id) {
		return manager.find(DepoFis.class, id);
	}

	@Override
	public List<Integer> getNewFisNo() {
		try {
			List<Integer> data = manager.createQuery("select d.fisNo from DepoFis d order by d.fisNo desc", Integer.class)
					.setMaxResults(1).getResultList();
			return data;

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<DepoFis> findAll() {
		return manager.createQuery("from DepoFis", DepoFis.class).getResultList();
	}

}
