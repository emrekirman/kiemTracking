package com.tasinirdepo.dao;

import java.util.List;

public interface IFisHareketRepository<T> {
	List<T> createAll(List<T> list);

	List<T> getAllByFisId(int id);
}
