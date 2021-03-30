package com.tasinirdepo.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tasinirdepo.dao.session.SessionCommonImpl;
import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.model.Kullanici;
import com.tasinirdepo.service.IKullaniciService;

@RestController
@RequestMapping("/rest")
public class KullaniciRestController {

	@Autowired
	private IKullaniciService service;

	private SessionCommonImpl session;

	private ILogginManager logRepo;

	@Autowired
	public KullaniciRestController(SessionCommonImpl session,ILogginManager logrepo) {
		this.session = session;
		this.logRepo = logrepo;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/kullanici/getAll")
	@KimlikDogrulama
	public ResponseEntity<List<Kullanici>> getAll(@RequestHeader("token") String token) {
		try {
			List<Kullanici> data = service.findAll();
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			// TODO: handle exception
		}
	}

	/*
	 * @GetMapping("/kullanici/sessionStart")
	 * 
	 * @ResponseBody
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/kullanici/sessionStart")
	public ResponseEntity<Kullanici> startSession(@RequestParam String kadi, @RequestParam String sifre,
			@RequestParam int yil) {
		try {
			Kullanici model = service.startSession(kadi, sifre);
			session.setYil(yil);
			session.setUsername(kadi);

			Algorithm algorithm = Algorithm.HMAC256("secret");
			String token = JWT.create().withIssuer("auth0").sign(algorithm);
			session.setToken(token);

			return ResponseEntity.ok().header("Authorization", token)
					.header("Access-Control-Expose-Headers", "Authorization")
					.header("Access-Control-Allow-Headers",
							"Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header")
					.body(model);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/kullanici/getSession")
	public ResponseEntity<Kullanici> getSession(HttpSession session) {
		try {
			Kullanici model = service.getSession(session);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/kullanici/destroySession")
	@KimlikDogrulama
	public ResponseEntity<Boolean> destroy(@RequestHeader("token") String token) {
		try {
			service.destroySession();
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logRepo.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
