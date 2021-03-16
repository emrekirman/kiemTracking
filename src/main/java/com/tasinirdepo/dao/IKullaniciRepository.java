package com.tasinirdepo.dao;

import com.tasinirdepo.model.Kullanici;

public interface IKullaniciRepository extends IBaseRepository<Kullanici>{
    Kullanici startSession(String kadi,String sifre );
    Kullanici getByUsername(String username);
}
