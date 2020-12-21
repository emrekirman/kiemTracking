package com.tasinirdepo.dao;

import com.tasinirdepo.model.Kullanici;

public interface IKullaniciRepository {
    Kullanici startSession(String kadi,String sifre );
    Kullanici getByUsername(String username);
}
