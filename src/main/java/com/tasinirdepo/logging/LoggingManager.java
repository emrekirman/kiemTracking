package com.tasinirdepo.logging;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.model.Logging;

@Repository("logRepo")
@Qualifier("logRepo")
public class LoggingManager implements ILogginManager {

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void hataEkle(Exception ex, Object classProperty) {
		try {
			Logging model = new Logging();
			model.setHataTanim(ex.toString());
			model.setSinifAdi(classProperty.getClass().getName());
			model.setIslemTarih(new Date());
			manager.persist(model);
		} catch (Exception e) {
		}
	}
}
