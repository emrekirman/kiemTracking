package com.tasinirdepo.dao;

import java.util.List;

public interface IBaseRepository<T> {
	List<T> findAll();

	int create(T model);

	T update(T model);

	void delete(int id);

	T getById(int id);
}
