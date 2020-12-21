package com.tasinirdepo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasinirdepo.interfaces.ILogginManager;
import com.tasinirdepo.interfaces.KimlikDogrulama;
import com.tasinirdepo.model.CariTanim;
import com.tasinirdepo.service.IBaseService;

@RestController
@RequestMapping("/rest")
public class CariTanimRestController {

	private IBaseService<CariTanim> service;

	private ILogginManager logManager;

	@Autowired
	@Qualifier("cariTanimService")
	public void setService(IBaseService<CariTanim> service) {
		this.service = service;
	}

	@Autowired
	@Qualifier("logRepo")
	public void setLogManager(ILogginManager logManager) {
		this.logManager = logManager;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/cariTanim/getAll")
	@KimlikDogrulama
	public ResponseEntity<List<CariTanim>> getAll(@RequestHeader("token") String token) {
		try {
			List<CariTanim> data = service.findAll();
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/cariTanim/getById/{id}")
	@KimlikDogrulama
	public ResponseEntity<CariTanim> getById(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			CariTanim data = service.getById(id);
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "/cariTanim/ekle")
	@KimlikDogrulama
	public ResponseEntity<Boolean> ekle(@RequestHeader("token") String token, @RequestBody CariTanim model,
			HttpServletRequest request) {
		try {
			service.create(model);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "cariTanim/delete/{id}")
	@KimlikDogrulama
	public ResponseEntity<Boolean> deleteCariTanim(@RequestHeader("token") String token, @PathVariable("id") int id) {
		try {
			service.delete(id);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "cariTanim/update")
	@KimlikDogrulama
	public ResponseEntity<Boolean> update(@RequestHeader("token") String token, @RequestBody CariTanim cariTanim) {
		try {
			service.update(cariTanim);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
