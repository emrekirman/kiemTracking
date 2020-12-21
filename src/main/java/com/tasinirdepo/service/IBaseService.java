package com.tasinirdepo.service;

import java.util.List;

public interface IBaseService<T> {

	List<T> findAll();
	int create(T model);
	T update(T model);
	void delete(int id) throws Exception;
	T getById(int id);
}
