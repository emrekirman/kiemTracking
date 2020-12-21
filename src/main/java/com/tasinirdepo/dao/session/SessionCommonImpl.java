package com.tasinirdepo.dao.session;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

//@Repository("session")
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SessionCommonImpl {

	private int Yil;

	private String username;

	private String token;

	public int getYil() {
		return Yil;
	}

	public void setYil(int yil) {
		Yil = yil;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
