package com.tasinirdepo.web;

import java.util.List;

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
import com.tasinirdepo.model.DepoOlcuBirim;
import com.tasinirdepo.service.IBaseService;

@RestController
@RequestMapping("/rest")
public class DepoOlcuBirimRestController {

	private IBaseService<DepoOlcuBirim> service;

	private ILogginManager logManager;

	@Autowired
	@Qualifier("depoOlcumBirimService")
	public void setPetService(IBaseService<DepoOlcuBirim> petService) {
		this.service = petService;
	}

	@Autowired
	@Qualifier("logRepo")
	public void setLogManager(ILogginManager logManager) {
		this.logManager = logManager;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/depoOlcuBirim/getAll")
	@KimlikDogrulama
	public ResponseEntity<List<DepoOlcuBirim>> getDepoOlcuBirims(@RequestHeader("token") String token) {

		try {
			List<DepoOlcuBirim> data = service.findAll();
			return ResponseEntity.ok(data);

		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "depoOlcuBirim/ekle")
	@KimlikDogrulama
	public ResponseEntity<Boolean> addDepoOlcuBirim(@RequestHeader("token") String token,
			@RequestBody DepoOlcuBirim model) {
		try {
			service.create(model);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "depoOlcuBirim/getById/{id}")
	@KimlikDogrulama
	public ResponseEntity<DepoOlcuBirim> getDepoOlcuBirimById(@RequestHeader("token") String token,
			@PathVariable("id") int id) {
		try {
			DepoOlcuBirim model = service.getById(id);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, value = "depoOlcuBirim/update")
	@KimlikDogrulama
	public ResponseEntity<Boolean> updateDepoOlcuBirim(@RequestHeader("token") String token,
			@RequestBody DepoOlcuBirim olcuBirim) {
		try {
			service.update(olcuBirim);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "depoOlcuBirim/delete/{id}")
	@KimlikDogrulama
	public ResponseEntity<Boolean> deleteDepoOlcuBirim(@RequestHeader("token") String token,
			@PathVariable("id") int id) {
		try {
			service.delete(id);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			logManager.hataEkle(e, this);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
