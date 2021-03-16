package com.tasinirdepo.service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tasinirdepo.model.Kullanici;

public interface IKullaniciService extends IBaseService<Kullanici>{
	Kullanici startSession(String kadi,String sifre );	
	Kullanici getSession(HttpSession session);
	void destroySession(HttpServletRequest request);
	void destroySession();
}
