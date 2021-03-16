package com.tasinirdepo.dao;

import java.util.List;

import com.tasinirdepo.model.StokTanim;

public interface StokTanimRepository extends IBaseRepository<StokTanim> {
	List<String> getLastDepoKod();
}
