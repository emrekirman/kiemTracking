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
import com.tasinirdepo.model.FisHareketGiris;

@Repository("fisHareketGirisRepository")
@Qualifier("fisHareketGirisRepository")
public class FisHareketGirisRepositoryImpl
		implements IBaseRepository<FisHareketGiris>, IFisHareketRepository<FisHareketGiris> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FisHareketGiris> findAll() {
		return manager.createQuery("from FisHareketGiris", FisHareketGiris.class).getResultList();
	}

	@Override
	public int create(FisHareketGiris model) {
		try {
			manager.persist(model);
			return model.getId();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public FisHareketGiris update(FisHareketGiris model) {
		try {
			return manager.merge(model);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void delete(int id) {
		manager.remove(manager.getReference(FisHareketGiris.class, id));
	}

	@Override
	public FisHareketGiris getById(int id) {
		return manager.find(FisHareketGiris.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<FisHareketGiris> createAll(List<FisHareketGiris> girisList) {
		try {
			for (FisHareketGiris fisHareketGiris : girisList) {
				manager.persist(fisHareketGiris);
			}
			return girisList;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<FisHareketGiris> getAllByFisId(int id) {
		return manager.createQuery("from FisHareketGiris f where f.depoFis.id=" + id, FisHareketGiris.class)
				.getResultList();
	}

}
