package com.tasinirdepo.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.dao.IBaseRepository;
import com.tasinirdepo.dao.IFisHareketRepository;
import com.tasinirdepo.model.FisHareketCikis;

@Repository("fisHareketCikisRepository")
@Qualifier("fisHareketCikisRepository")
public class FisHareketCikisRepositoryImpl
		implements IBaseRepository<FisHareketCikis>, IFisHareketRepository<FisHareketCikis> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FisHareketCikis> findAll() {
		return manager.createQuery("from FisHareketCikis", FisHareketCikis.class).getResultList();
	}

	@Override
	public int create(FisHareketCikis model) {
		try {
			manager.persist(model);
			return model.getId();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public FisHareketCikis update(FisHareketCikis model) {
		return manager.merge(model);
	}

	@Override
	public void delete(int id) {
		manager.remove(manager.getReference(FisHareketCikis.class, id));
	}

	@Override
	public FisHareketCikis getById(int id) {
		return manager.find(FisHareketCikis.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<FisHareketCikis> createAll(List<FisHareketCikis> girisList) {
		try {
			for (FisHareketCikis item : girisList) {
				manager.persist(item);
			}
			return girisList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<FisHareketCikis> getAllByFisId(int id) {
		return manager.createQuery("from FisHareketCikis f where f.depoFis.id=" + id, FisHareketCikis.class)
				.getResultList();
	}

}
