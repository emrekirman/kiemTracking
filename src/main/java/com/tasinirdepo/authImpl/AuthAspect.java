package com.tasinirdepo.authImpl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.tasinirdepo.dao.session.SessionCommonImpl;

@Aspect
@Configuration
public class AuthAspect {

	private SessionCommonImpl session;

	@Before("@annotation(com.tasinirdepo.interfaces.KimlikDogrulama) && args(token,..)")
	public void before(String token) {

		if (session.getToken() == null || !session.getToken().equals(token)) {
			throw new RuntimeException("Kimlik Doğrulama Hatası");
		}

		/*
		 * if(authBean.authorize(request.getHeadr("Authorization"))){ req.setAttribute(
		 * "userSession", "session information which cann be acces in controller" );
		 * }else { throw new RuntimeException("auth error..!!!"); }
		 */

	}

	@Autowired
	public void setSession(SessionCommonImpl session) {
		this.session = session;
	}

}
